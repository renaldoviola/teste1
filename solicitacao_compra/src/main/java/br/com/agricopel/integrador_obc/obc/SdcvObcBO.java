package br.com.agricopel.integrador_obc.obc;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.comp.utils.StrUtil;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraDbg;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraItemDbg;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoRatCCustoDbg;
import br.com.agricopel.integrador_obc.obc.model.SdcvObc;
import br.com.agricopel.integrador_obc.obc.model.SdcvRateioObc;

public class SdcvObcBO {

	public List<SdcvObc> getSdcvs(SolicitacaoCompraDbg solicitacaoCompraDbg) throws Exception {

		StrUtil strUtil = new StrUtil();
		List<SdcvObc> sdcvs = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_EVEN);

		for (SolicitacaoCompraItemDbg solicitacaoCompraItemDbg : solicitacaoCompraDbg.getItens()) {

			String codRequisicao = "DBG-".concat(solicitacaoCompraDbg.getSTG_GEN_TABEMP_Codigo().toString()).concat("-")
					.concat(solicitacaoCompraItemDbg.getSTG_GEN_TABFIL_Codigo().toString()).concat("-")
					.concat(solicitacaoCompraDbg.getCOM_SOLICI_Numero().toString());

			SdcvObc sdcvObc = new SdcvObc();
			solicitacaoCompraItemDbg.setSdcvObc(sdcvObc);

			sdcvObc.setDescProduto(solicitacaoCompraItemDbg.getProdutoDbg().getEST_TABPRO_Descricao());

			String descAdicional = "";

			if (solicitacaoCompraItemDbg.getCOM_PROSOL_Observacao() != null
					&& !solicitacaoCompraItemDbg.getCOM_PROSOL_Observacao().isEmpty()) {
				descAdicional = solicitacaoCompraItemDbg.getCOM_PROSOL_Observacao();
			}

			if (solicitacaoCompraDbg.getCOM_SOLICI_Observacao() != null
					&& !solicitacaoCompraDbg.getCOM_SOLICI_Observacao().isEmpty()) {

				if (!descAdicional.isEmpty()) {
					descAdicional = descAdicional.concat(" - ");
				}

				descAdicional = solicitacaoCompraDbg.getCOM_SOLICI_Observacao();
			}

			sdcvObc.setDescAdicional(descAdicional.isEmpty() ? "-" : strUtil.paraCharsetObc(descAdicional));

			sdcvObc.setCodCapaRequisicao(codRequisicao);
			sdcvObc.setCodLinhaRequisicao(
					codRequisicao.concat("-".concat(solicitacaoCompraItemDbg.getCOM_PROSOL_Sequencia().toString())));

			sdcvObc.setCodProduto("DBG-".concat(solicitacaoCompraDbg.getSTG_GEN_TABEMP_Codigo().toString()).concat("-")
					.concat(solicitacaoCompraItemDbg.getProdutoDbg().getEST_TABPRO_Codigo()));
			sdcvObc.setQuantidade(solicitacaoCompraItemDbg.getCOM_PROSOL_Quantidade());

			if (solicitacaoCompraDbg.getCOM_SOLICI_Tipo().contentEquals("C")) {
				if (solicitacaoCompraItemDbg.getRateios().isEmpty()) {
					SdcvRateioObc sdcvRateioObc = new SdcvRateioObc();

					sdcvRateioObc.setCodCentroCusto("DBG-".concat(solicitacaoCompraItemDbg.getCentroCustoObc()));

					sdcvRateioObc.setCodContaContabil(
							"DBG-".concat(solicitacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo().toString()).concat("-")
									.concat(solicitacaoCompraItemDbg.getProdutoDbg().getEST_TABPRO_ContaCusto()));
					sdcvRateioObc.setpRateio(Double.valueOf(100));

					sdcvObc.getRateios().add(sdcvRateioObc);
				} else {

					Double pTotalRateio = Double.valueOf(0);

					for (SolicitacaoRatCCustoDbg solicitacaoCompraRateioDbg : solicitacaoCompraItemDbg.getRateios()) {

						SdcvRateioObc sdcvRateioObc = new SdcvRateioObc();

						sdcvRateioObc.setCodCentroCusto("DBG-".concat(solicitacaoCompraRateioDbg.getCentroCustoObc()));

						sdcvRateioObc.setCodContaContabil("DBG-"
								.concat(solicitacaoCompraRateioDbg.getSTG_GEN_TABEMP_Codigo().toString()).concat("-")
								.concat(solicitacaoCompraItemDbg.getProdutoDbg().getEST_TABPRO_ContaCusto()));

						Double pRateio = solicitacaoCompraRateioDbg.getCOM_CONSOL_Quantidade()
								/ solicitacaoCompraItemDbg.getCOM_PROSOL_Quantidade() * 100;
						sdcvRateioObc.setpRateio(df.parse(df.format(pRateio)).doubleValue());

						sdcvObc.getRateios().add(sdcvRateioObc);
						pTotalRateio += sdcvRateioObc.getpRateio();
					}

					Double sobraRateio = 100 - pTotalRateio;
					sobraRateio = df.parse(df.format(sobraRateio)).doubleValue();

					if (sobraRateio != 0) {
						SdcvRateioObc primeiroRateio = sdcvObc.getRateios().get(0);

						Double novoPRateio = primeiroRateio.getpRateio() + sobraRateio;
						novoPRateio = df.parse(df.format(novoPRateio)).doubleValue();

						primeiroRateio.setpRateio(novoPRateio);
					}
				}
			} else {
				SdcvRateioObc sdcvRateioObc = new SdcvRateioObc();

				sdcvRateioObc.setCodCentroCusto("COMPRA_ESTOQUE");

				sdcvRateioObc.setCodContaContabil(
						"DBG-".concat(solicitacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo().toString()).concat("-")
								.concat(solicitacaoCompraItemDbg.getProdutoDbg().getEST_TABPRO_ContaCusto()));
				sdcvRateioObc.setpRateio(Double.valueOf(100));

				sdcvObc.getRateios().add(sdcvRateioObc);
			}

			sdcvObc.setCodFilial("DBG-"
					.concat(strUtil.cnpjSemFormatacao(solicitacaoCompraDbg.getFilialEmpresa().getGEN_TABFIL_CNPJ())));
			sdcvObc.setUnMedida(solicitacaoCompraItemDbg.getProdutoDbg().getEST_UNIMED_Codigo());
			sdcvObc.setLoginComprador("rodrigo.goncalves");
			sdcvObc.setLoginRequisitante("rodrigo.goncalves");
			sdcvObc.setCentroCustoINATIVO("");

			if (solicitacaoCompraDbg.getCOM_SOLICI_Tipo().contentEquals("C")) {
				sdcvObc.setCodMotivoCompra("CD"); // CD = Consumo direto
			} else {
				sdcvObc.setCodMotivoCompra("RP"); // RP = Reposição de estoque
			}

			sdcvObc.setDataGeracao(LocalDate.now());
			sdcvObc.setNomeAnexoErp("");
			sdcvObc.setDataPrevisaoCompra(LocalDate.now().plus(7, ChronoUnit.DAYS));
			sdcvObc.setCodContaContabil("");

			String descricaoInterna = "";

			if (solicitacaoCompraDbg.getCOM_SOLICI_Gerada() != null
					&& solicitacaoCompraDbg.getCOM_SOLICI_Gerada().equals("O")
					&& solicitacaoCompraDbg.getCOM_SOLICI_IDOSTerceiro() != null) {

				descricaoInterna = descricaoInterna
						.concat("Num OS: ".concat(solicitacaoCompraDbg.getCOM_SOLICI_IDOSTerceiro().toString()));
			}

			if (solicitacaoCompraDbg.getCOM_SOLICI_OSGarantia() != null
					&& solicitacaoCompraItemDbg.getCOM_PROSOL_DataGarantia() != null
					&& solicitacaoCompraDbg.getCOM_SOLICI_OSGarantia() > 0) {

				if (!descricaoInterna.isEmpty()) {
					descricaoInterna = descricaoInterna.concat(" - ");
				}

				descricaoInterna = descricaoInterna
						.concat("Num OS garantia: ".concat(solicitacaoCompraDbg.getCOM_SOLICI_OSGarantia().toString()));

				descricaoInterna = descricaoInterna.concat(" - ");

				descricaoInterna = descricaoInterna.concat("Garantia até: ").concat(solicitacaoCompraItemDbg
						.getCOM_PROSOL_DataGarantia().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}

			sdcvObc.setDescricaoInterna(strUtil.paraCharsetObc(descricaoInterna));

			sdcvObc.setPermiteSimilares("N");
			sdcvObc.setTipoSdcv("C");
			sdcvObc.setCodAlmoxarifado("");
			sdcvObc.setAgrupamentoSdcv("");
			sdcvObc.setLocalEntrega("");

			if (solicitacaoCompraDbg.getCOM_SOLICI_Prioridade() != null
					&& solicitacaoCompraDbg.getCOM_SOLICI_Prioridade().equals("U")) {
				sdcvObc.setPrioridade(1); // Urgente=1
			} else {
				sdcvObc.setPrioridade(0); // Normal=0
			}

			sdcvObc.setCnpjFornecedor("");
			sdcvObc.setMarcaProduto("");
			sdcvObc.setTipoFrete("");
			sdcvObc.setDiasPrazoEntrega(null);
			sdcvObc.setCodCondPagto("");
			sdcvObc.setObservacao("");
			sdcvObc.setValorUnitario(null);
			sdcvObc.setpIpi(null);
			sdcvObc.setpIcmsIva(null);
			sdcvObc.setpIcmsSt(null);
			sdcvObc.setDtValidadeProposta(null);
			sdcvObc.setCodSubTipoSdcv("");
			sdcvObc.setCodProjeto("");
			sdcvObc.setCodMoeda(null);
			sdcvObc.setNumeroNota("");
			sdcvObc.setSerieNota("");
			sdcvObc.setCodCst("");
			sdcvObc.setCodOrigem("");
			sdcvObc.setCodIva("");

			if (solicitacaoCompraDbg.getFornecedor() != null
					&& !solicitacaoCompraDbg.getFornecedor().getCnpj().isEmpty()) {
				sdcvObc.setCnpjFornecedorSugerido(solicitacaoCompraDbg.getFornecedor().getCnpj());
			} else {
				sdcvObc.setCnpjFornecedorSugerido("");
			}

			sdcvObc.setSistemaOrigem("DBG");
			sdcvObc.setValorTarget(null);
			sdcvObc.setMotivoCompra("");
			sdcvObc.setFaseProjeto("");
			sdcvObc.setPlanejador("");
			sdcvObc.setFornecedorExclusivo("");

			sdcvs.add(sdcvObc);
		}

		return sdcvs;
	}

}

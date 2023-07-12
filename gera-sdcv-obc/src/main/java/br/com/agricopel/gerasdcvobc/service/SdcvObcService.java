package br.com.agricopel.gerasdcvobc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.agricopel.gerasdcvobc.exception.AgrException;
import br.com.agricopel.gerasdcvobc.model.Comprador;
import br.com.agricopel.gerasdcvobc.model.Filial;
import br.com.agricopel.gerasdcvobc.model.Produto;
import br.com.agricopel.gerasdcvobc.model.SdcvObc;
import br.com.agricopel.gerasdcvobc.utils.PlanilhaExcelUtils;

@Service
public class SdcvObcService {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CompradorService compradorService;

	@Autowired
	private FilialService filialService;

	@Autowired
	private EnviarSdcvService enviarSdcvService;

	public void gerarSDCV(MultipartFile file, Integer idComprador, Integer idFilial, String email) throws Exception {

		Comprador comprador = compradorService.findById(idComprador);
		Filial filial = filialService.findById(idFilial).get();

		List<List<String>> planilhaExcel = procPlanilha(file);
		List<SdcvObc> sdcvObc = getSdcvObc(planilhaExcel, comprador, filial);

		enviarSdcvService.processar(sdcvObc, comprador.getEmail(), email);
	}

	private List<List<String>> procPlanilha(MultipartFile file) throws Exception {

		if (file != null) {
			PlanilhaExcelUtils planilhaExcelUtils = new PlanilhaExcelUtils();

			List<List<String>> planilhaExcel = planilhaExcelUtils.importar(file.getInputStream(),
					file.getOriginalFilename());

			return planilhaExcel;
		} else {
			throw new AgrException("PLanilha excel para importação inválida ou inexistente!");
		}
	}

	private List<SdcvObc> getSdcvObc(List<List<String>> planilhaExcel, Comprador comprador, Filial filial)
			throws Exception {

		Integer sequencia = 0;

		String nomeCampoQuantidade = "QUANTIDADE";
		String nomeCampoCodProduto = "COD_PRODUTO";
		String nomeCampoDescAdicional = "DESCRICAO_ADICIONAL";
		String nomeCampoSolicitar = "SOLICITAR";
		String nomeCampoDescProduto = "DESC_PRODUTO";
		String nomeCampoCentroCusto = "CENTRO_CUSTO";

		List<SdcvObc> sdcvsObc = new ArrayList<>();

		List<String> cabecalho = planilhaExcel.get(0);
		planilhaExcel.remove(0);

		int posCampoQuantidade = getPosCampo(cabecalho, nomeCampoQuantidade);
		int posCampoCodProduto = getPosCampo(cabecalho, nomeCampoCodProduto);
		int posCampoDescAdicional = getPosCampo(cabecalho, nomeCampoDescAdicional);
		int posCampoSolicitar = getPosCampo(cabecalho, nomeCampoSolicitar);
		int posCampoDescProduto = getPosCampo(cabecalho, nomeCampoDescProduto);
		int posCampoCentroCusto = getPosCampo(cabecalho, nomeCampoCentroCusto);

		String codRequisicao = calcCodRequisicao(comprador);

		for (List<String> linhaPlanilha : planilhaExcel) {

			String codProduto = linhaPlanilha.get(posCampoCodProduto);

			if (!codProduto.isEmpty()) {

				sequencia++;
				SdcvObc sdcvObc = new SdcvObc();

				try {
					sdcvObc.setSolicitar(linhaPlanilha.get(posCampoSolicitar).trim().equalsIgnoreCase("SIM"));
					sdcvObc.setDescProduto(linhaPlanilha.get(posCampoDescProduto));

					sdcvObc.setDescAdicional(linhaPlanilha.get(posCampoDescAdicional));
					
					sdcvObc.setCodCapaRequisicao(codRequisicao);
					sdcvObc.setCodLinhaRequisicao(codRequisicao.concat("-".concat(sequencia.toString())));

					sdcvObc.setCodProduto(codProduto);
					sdcvObc.setQuantidade(Double.valueOf(linhaPlanilha.get(posCampoQuantidade)));

					sdcvObc.getRateio().setCodCentroCusto(linhaPlanilha.get(posCampoCentroCusto));

					calcSdcv(sdcvObc, comprador, filial);
				} catch (Exception e) {
					sdcvObc.setSolicitar(Boolean.FALSE);
					sdcvObc.addRetornoProc("Falha ao carregar dados, acione o suporte. Erro: ".concat(e.getMessage()));
				}

				sdcvsObc.add(sdcvObc);
			}
		}

		return sdcvsObc;
	}

	private void calcSdcv(SdcvObc sdcvObc, Comprador comprador, Filial filial) throws Exception {

		if (!sdcvObc.getCodProduto().startsWith(filial.getPref_cod_produto())) {
			sdcvObc.setCodProduto(filial.getPref_cod_produto().concat(sdcvObc.getCodProduto()));
		}

		Optional<Produto> produto = produtoService.findByCodigo(sdcvObc.getCodProduto());

		if (validarSdcv(produto, sdcvObc)) {

			sdcvObc.getRateio().setCodContaContabil(produto.get().getContaContabil());
			sdcvObc.getRateio().setpRateio(Double.valueOf(100));

			sdcvObc.setCodFilial(filial.getCodigo());
			sdcvObc.setUnMedida(produto.get().getUnCompra());
			sdcvObc.setLoginComprador(comprador.getLoginObc());
			sdcvObc.setLoginRequisitante(comprador.getLoginObc());
			sdcvObc.setCentroCustoINATIVO("");
			sdcvObc.setCodMotivoCompra("CR");
			sdcvObc.setDataGeracao(LocalDate.now());
			sdcvObc.setNomeAnexoErp("");

			if (!produto.get().getPrazo().isEmpty() && !produto.get().getPrazo().equals("0")) {
				sdcvObc.setDataPrevisaoCompra(
						LocalDate.now().plus(Integer.parseInt(produto.get().getPrazo()), ChronoUnit.DAYS));
			} else {
				sdcvObc.setDataPrevisaoCompra(LocalDate.now().plus(7, ChronoUnit.DAYS));
			}

			sdcvObc.setCodContaContabil("");
			sdcvObc.setDescricaoInterna("");
			sdcvObc.setPermiteSimilares("");
			sdcvObc.setTipoSdcv("C");
			sdcvObc.setCodAlmoxarifado("");
			sdcvObc.setAgrupamentoSdcv("");
			sdcvObc.setLocalEntrega("");
			sdcvObc.setPrioridade(0);
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
			sdcvObc.setCnpjFornecedorSugerido("");
			sdcvObc.setSistemaOrigem("");
			sdcvObc.setValorTarget(null);
			sdcvObc.setMotivoCompra("");
			sdcvObc.setFaseProjeto("");
		}
	}

	private Boolean validarSdcv(Optional<Produto> optProduto, SdcvObc sdcvObc) {

		Boolean validOk = Boolean.TRUE;

		if (!optProduto.isPresent()) {
			sdcvObc.addRetornoProc("Produto não encontrado! Codigo: [".concat(sdcvObc.getCodProduto()).concat("]"));

			validOk = Boolean.FALSE;
		} else {
			if (!optProduto.get().getStatus().equals("S")) {
				sdcvObc.addRetornoProc("Produto INATIVO!");

				validOk = Boolean.FALSE;
			}

			if (optProduto.get().getContaContabil().isEmpty()) {
				sdcvObc.addRetornoProc("Produto sem conta contábil!");

				validOk = Boolean.FALSE;
			}
		}

		sdcvObc.setSolicitar(validOk);
		return validOk;
	}

	private int getPosCampo(List<String> cabecalhoExcel, String nomeCampo) throws Exception {

		if (cabecalhoExcel.contains(nomeCampo)) {
			return cabecalhoExcel.indexOf(nomeCampo);
		} else {
			throw new AgrException("Campo [".concat(nomeCampo).concat("] não encontrado na planilha Anexada!"));
		}
	}

	private String calcCodRequisicao(Comprador comprador) {

		StringBuilder codRequisicao = new StringBuilder();
		LocalDateTime agora = LocalDateTime.now();

		if (comprador.getLoginObc().length() > 20) {
			codRequisicao.append(comprador.getLoginObc().substring(0, 20));
		} else {
			codRequisicao.append(comprador.getLoginObc());
		}

		codRequisicao.append("-");
		codRequisicao.append(agora.getYear());
		codRequisicao.append("-");
		codRequisicao.append(agora.getMonthValue());
		codRequisicao.append("-");
		codRequisicao.append(agora.getDayOfMonth());
		codRequisicao.append("|");
		codRequisicao.append(agora.getHour());
		codRequisicao.append(":");
		codRequisicao.append(agora.getMinute());
		codRequisicao.append(":");
		codRequisicao.append(agora.getSecond());

		return codRequisicao.toString();
	}

}

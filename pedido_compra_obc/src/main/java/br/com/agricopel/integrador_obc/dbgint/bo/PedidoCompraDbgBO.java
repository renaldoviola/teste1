package br.com.agricopel.integrador_obc.dbgint.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.exception.DbGintException;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.StrUtil;
import br.com.agricopel.integrador_obc.dbgint.dao.CentroCustoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.CondPagtoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoCompraDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoCompraItemDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.EnderecoClienteFornecedorDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.FilialEmpresaDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.NaturezaOperacaoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.ParametrosDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.PedidoCompraAprovacaoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.PedidoCompraDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.PedidoCompraItemDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.PedidoRatCCustoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.ProdutoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.SolicitacaoCompraDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.SolicitacaoCompraItemDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.VeiculoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.model.CentroCustoDbg;
import br.com.agricopel.integrador_obc.dbgint.model.CondPagtoDbg;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraItemDbg;
import br.com.agricopel.integrador_obc.dbgint.model.EnderecoClienteFornecedorDbg;
import br.com.agricopel.integrador_obc.dbgint.model.FilialEmpresaDbg;
import br.com.agricopel.integrador_obc.dbgint.model.NaturezaOperacaoDbg;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoCompraAprovacaoDbg;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoCompraDbg;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoCompraItemDbg;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoRatCCustoDbg;
import br.com.agricopel.integrador_obc.dbgint.model.ProdutoDbg;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraItemDbg;
import br.com.agricopel.integrador_obc.dbgint.model.VeiculoDbg;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraItemObc;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraObc;
import br.com.agricopel.integrador_obc.obc.model.PedidoRatCCustoObc;
import br.com.agricopel.integrador_obc.obc.model.RetGravarPedidoObc;

public class PedidoCompraDbgBO {

	private ConexaoDbGint conexaoDbGint;

	public RetGravarPedidoObc gravar(PedidoCompraObc pedidoCompraObc) throws Exception {

		this.conexaoDbGint = new ConexaoDbGint();

		String sdcv = pedidoCompraObc.getItens().get(0).getCodSdcv();

		PedidoCompraDbg pedidoCompraDbg = new PedidoCompraDbgDAO(this.conexaoDbGint).getPedidoSdcv(sdcv);

		if (pedidoCompraDbg != null && pedidoCompraDbg.getCOM_PEDIDO_Numero() != null
				&& pedidoCompraDbg.getCOM_PEDIDO_Numero() > 0) {

			LogUtils.escreverLogInfo(
					"Encontrado pedido já existente [".concat(String.valueOf(pedidoCompraDbg.getCOM_PEDIDO_Numero()))
							.concat("] a partir da SDCV [".concat(sdcv).concat("]")));

			return getRetorno(pedidoCompraDbg);
		} else {
			return procPedido(pedidoCompraObc);
		}
	}

	private RetGravarPedidoObc procPedido(PedidoCompraObc pedidoCompraObc) throws Exception {

		try {
			this.conexaoDbGint.abrirTransacao();

			CotacaoCompraDbgBO cotacaoCompraDbgBO = new CotacaoCompraDbgBO(this.conexaoDbGint);
			PedidoCompraDbg pedidoCompraDbg = this.getPedido(pedidoCompraObc);

			for (PedidoCompraItemDbg pedidoCompraItemDbg : pedidoCompraDbg.getItens()) {

				if (pedidoCompraItemDbg.getSolicitacaoCompraItemDbg() != null) {

					SolicitacaoCompraItemDbg solicitacaoCompraItemDbg = pedidoCompraItemDbg
							.getSolicitacaoCompraItemDbg();

					baixarStatusSolicitacao(solicitacaoCompraItemDbg);

					if (solicitacaoCompraItemDbg.getSolicitacaoCompraDbg().getCOM_SOLICI_Gerada() != null
							&& solicitacaoCompraItemDbg.getSolicitacaoCompraDbg().getCOM_SOLICI_Gerada().equals("O")
							&& solicitacaoCompraItemDbg.getSolicitacaoCompraDbg()
									.getCOM_SOLICI_IDOSTerceiro() != null) {

						pedidoCompraDbg.setCOM_PEDIDO_IDOSTerceiro(
								solicitacaoCompraItemDbg.getSolicitacaoCompraDbg().getCOM_SOLICI_IDOSTerceiro());
					}

					if (pedidoCompraItemDbg.getCotacaoCompraItemDbg() != null) {
						cotacaoCompraDbgBO.procCotacao(pedidoCompraDbg, pedidoCompraItemDbg);

						pedidoCompraItemDbg.setCOM_COTACA_Numero(
								pedidoCompraItemDbg.getCotacaoCompraItemDbg().getCOM_COTACA_Numero());
						pedidoCompraItemDbg.setCOM_PROCOT_Sequencia(
								pedidoCompraItemDbg.getCotacaoCompraItemDbg().getCOM_PROCOT_Sequencia());
					}
				}
			}

			inserirPedido(pedidoCompraDbg);
			this.conexaoDbGint.fecharTransacao(true);

			RetGravarPedidoObc retGravarPedidoObc = getRetorno(pedidoCompraDbg);

			return retGravarPedidoObc;
		} catch (Exception e) {
			this.conexaoDbGint.fecharTransacao(false);
			throw e;
		} finally {
			this.conexaoDbGint.close();
		}
	}

	private RetGravarPedidoObc getRetorno(PedidoCompraDbg pedidoCompraDbg) {

		RetGravarPedidoObc retGravarPedidoObc = new RetGravarPedidoObc();

		retGravarPedidoObc.setCodPedido(pedidoCompraDbg.getCOM_PEDIDO_Numero().toString());
		retGravarPedidoObc.setSucesso(true);

		return retGravarPedidoObc;
	}

	private void inserirPedido(PedidoCompraDbg pedidoCompraDbg) throws Exception {

		pedidoCompraDbg.setCOM_PEDIDO_Numero(getUltCodPedidoCompra(pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo(),
				pedidoCompraDbg.getSTG_GEN_TABFIL_Codigo()));

		new PedidoCompraDbgDAO(this.conexaoDbGint).inserir(pedidoCompraDbg);

		inserirItens(pedidoCompraDbg);
		inserirAprovacao(pedidoCompraDbg);

		new ParametrosDbgDAO(this.conexaoDbGint).setUltCodigoPedidoCompra(pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo(),
				pedidoCompraDbg.getSTG_GEN_TABFIL_Codigo(), pedidoCompraDbg.getCOM_PEDIDO_Numero());
	}

	private void inserirItens(PedidoCompraDbg pedidoCompraDbg) throws Exception {

		PedidoCompraItemDbgDAO pedidoCompraItemDbgDAO = new PedidoCompraItemDbgDAO(this.conexaoDbGint);

		for (PedidoCompraItemDbg pedidoCompraItemDbg : pedidoCompraDbg.getItens()) {
			pedidoCompraItemDbg.setCOM_PEDIDO_Numero(pedidoCompraDbg.getCOM_PEDIDO_Numero());
			pedidoCompraItemDbgDAO.inserir(pedidoCompraItemDbg);

			inserirRateioCC(pedidoCompraItemDbg);
		}
	}

	private void inserirAprovacao(PedidoCompraDbg pedidoCompraDbg) throws Exception {

		PedidoCompraAprovacaoDbg pedidoCompraAprovacaoDbg = new PedidoCompraAprovacaoDbg();

		pedidoCompraAprovacaoDbg.setSTG_GEN_TABEMP_Codigo(pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo());
		pedidoCompraAprovacaoDbg.setSTG_GEN_TABFIL_Codigo(pedidoCompraDbg.getSTG_GEN_TABFIL_Codigo());
		pedidoCompraAprovacaoDbg.setCOM_PEDIDO_Numero(pedidoCompraDbg.getCOM_PEDIDO_Numero());
		pedidoCompraAprovacaoDbg.setCOM_APRPED_DHAprovacao(pedidoCompraDbg.getCOM_PEDIDO_Created());
		pedidoCompraAprovacaoDbg.setSTG_GEN_TABUSU_Apr_Login(pedidoCompraDbg.getSTG_GEN_TABUSU_Dig_Login());
		pedidoCompraAprovacaoDbg.setCOM_APRPED_Situacao("A");
		pedidoCompraAprovacaoDbg.setCOM_MOTCAN_Codigo(null);
		pedidoCompraAprovacaoDbg.setCOM_APRPED_Created(pedidoCompraDbg.getCOM_PEDIDO_Created());
		pedidoCompraAprovacaoDbg.setCOM_APRPED_Updated(pedidoCompraDbg.getCOM_PEDIDO_Created());

		new PedidoCompraAprovacaoDbgDAO(this.conexaoDbGint).inserir(pedidoCompraAprovacaoDbg);
	}

	private void inserirRateioCC(PedidoCompraItemDbg pedidoCompraItemDbg) throws Exception {

		PedidoRatCCustoDbgDAO ratCCustoDbgDAO = new PedidoRatCCustoDbgDAO(this.conexaoDbGint);

		for (PedidoRatCCustoDbg ratCCustoDbg : pedidoCompraItemDbg.getRatCCusto()) {
			ratCCustoDbg.setCOM_PEDIDO_Numero(pedidoCompraItemDbg.getCOM_PEDIDO_Numero());
			ratCCustoDbgDAO.inserir(ratCCustoDbg);
		}
	}

	private Integer getUltCodPedidoCompra(Integer codigoEmpresa, Integer codigoFilial) throws Exception {

		Integer ultCodPedidoCompra = new ParametrosDbgDAO(this.conexaoDbGint).getUltCodigoPedidoCompra(codigoEmpresa,
				codigoFilial);

		if (ultCodPedidoCompra == null) {
			throw new DbGintException(
					"Nao foi possivel encontrar a numeracao de pedido de compra da filial do pedido! CodEmpresa: "
							.concat(codigoEmpresa.toString())
							.concat(" / CodFilial: ".concat(codigoEmpresa.toString())));
		}

		return ultCodPedidoCompra;
	}

	private PedidoCompraDbg getPedido(PedidoCompraObc pedidoCompraObc) throws Exception {

		PedidoCompraDbg pedidoCompraDbg = new PedidoCompraDbg();

		FilialEmpresaDbg filialEmpresa = this.getFilialEmpresa(pedidoCompraObc.getFilialCob());
		pedidoCompraDbg.setFilial(filialEmpresa);
		pedidoCompraDbg.setSTG_GEN_TABEMP_Codigo(filialEmpresa.getGEN_TABEMP_Codigo());
		pedidoCompraDbg.setSTG_GEN_TABFIL_Codigo(filialEmpresa.getGEN_TABFIL_Codigo());

		pedidoCompraDbg.setCOM_PEDIDO_Numero(0);
		pedidoCompraDbg.setCOM_PEDIDO_Emissao(new DateUtil().agora().toLocalDate());
		pedidoCompraDbg.setCOM_PEDIDO_Entrega(pedidoCompraObc.getItens().get(0).getDtEntrega());

		EnderecoClienteFornecedorDbg fornecedor = this.getFornecedor(pedidoCompraObc.getCnpjFornecedor());
		pedidoCompraDbg.setFornecedor(fornecedor);
		pedidoCompraDbg.setSTG_GEN_TABENT_For_Codigo(fornecedor.getGEN_TABENT_Codigo());
		pedidoCompraDbg.setSTG_GEN_ENDENT_For_Codigo(fornecedor.getGEN_ENDENT_Codigo());

		pedidoCompraDbg.setCOM_PEDIDO_TipoFrete(pedidoCompraObc.getTipoFrete().equals("1") ? "C" : "F");

		LogUtils.escreverLogInfo("LOGSAMUEL " + pedidoCompraDbg.getCOM_PEDIDO_TipoFrete());
		
		if (pedidoCompraDbg.getCOM_PEDIDO_TipoFrete().equals("F")) {
			EnderecoClienteFornecedorDbg transportadora = this
					.getTransportadora(pedidoCompraObc.getCodTransportadora());
			
			if (transportadora != null) {
				LogUtils.escreverLogInfo("LOGSAMUEL ENTROU" + transportadora.getGEN_TABENT_Codigo() );
				LogUtils.escreverLogInfo("LOGSAMUEL ENTROU" + transportadora.getGEN_ENDENT_Codigo() );
				pedidoCompraDbg.setSTG_GEN_TABENT_Tra_Codigo(transportadora.getGEN_TABENT_Codigo());
				pedidoCompraDbg.setSTG_GEN_ENDENT_Tra_Codigo(transportadora.getGEN_ENDENT_Codigo());
			}
		}

		pedidoCompraDbg.setCOM_PEDIDO_DHEnvioEMail(new DateUtil().agora());
		pedidoCompraDbg.setCOM_PEDIDO_Created(new DateUtil().agora());
		pedidoCompraDbg.setCOM_PEDIDO_Updated(new DateUtil().agora());

		pedidoCompraDbg.setGEN_TABCPG_Codigo(
				this.getCodCondPagto(filialEmpresa.getGEN_TABEMP_Codigo(), pedidoCompraObc.getCondPagto()));

		pedidoCompraDbg.setCOM_TABCOM_Codigo(9); // LEONARDO COMPRAS //Rodrigo gonçalves
		pedidoCompraDbg.setSTG_GEN_TABUSU_Dig_Login("TI.AGRICOPEL");
		pedidoCompraDbg.setCOM_PEDIDO_Observacao(pedidoCompraObc.getObservacao());
		pedidoCompraDbg.setCOM_PEDIDO_Situacao("P");

		pedidoCompraDbg.setGEN_TABORG_Codigo(5); // Origem pedido de compra

		pedidoCompraDbg.setGEN_NATOPE_Codigo("1126003"); // COMPRA USO/CONSUMO COM ICMS_PIS/COFINS E COM ESTOQUE
		pedidoCompraDbg.setCOM_PEDIDO_ValorFrete(Double.valueOf(0)); // Valor do Frete
		pedidoCompraDbg.setCOM_PEDIDO_Emergencial(2); // Pedido é Emergencial ?
		pedidoCompraDbg.setCOM_PEDIDO_DHIntOBC(LocalDateTime.now());
		pedidoCompraDbg.setCOM_PEDIDO_Origem("E"); // Origem da inserção do registro (I-DB.Gint, E-Sistema Externo)

		pedidoCompraDbg.getItens().addAll(this.getItens(pedidoCompraObc, pedidoCompraDbg));

		pedidoCompraDbg.setGEN_NATOPE_Codigo(pedidoCompraDbg.getItens().get(0).getSTG_GEN_NATOPE_Codigo());
		pedidoCompraDbg.setCOM_PEDIDO_SeqProduto(pedidoCompraDbg.getItens().size()); // Última sequencia do item do
																						// pedido

		return pedidoCompraDbg;
	}

	private List<PedidoCompraItemDbg> getItens(PedidoCompraObc pedidoCompraObc, PedidoCompraDbg pedidoCompraDbg)
			throws Exception {

		List<PedidoCompraItemDbg> itens = new ArrayList<>();
		Integer sequencia = 0;

		LocalDateTime agora = new DateUtil().agora();

		for (PedidoCompraItemObc pedidoItemObc : pedidoCompraObc.getItens()) {

			PedidoCompraItemDbg pedidoItemDbg = new PedidoCompraItemDbg();
			sequencia++;

			pedidoItemDbg.setSTG_GEN_TABEMP_Codigo(pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo()); // Código da Empresa
			pedidoItemDbg.setSTG_GEN_TABFIL_Codigo(pedidoCompraDbg.getSTG_GEN_TABFIL_Codigo()); // Código da Filial
			pedidoItemDbg.setCOM_PEDIDO_Numero(pedidoCompraDbg.getCOM_PEDIDO_Numero()); // Número do Pedido de Compra
			pedidoItemDbg.setCOM_PROPED_Sequencia(sequencia); // Sequencia do Item

			pedidoItemDbg.setSTG_GEN_NATOPE_Codigo(
					getNaturezaOperacao(pedidoCompraObc, pedidoCompraDbg, pedidoItemObc.getMotivoCompra()));

			ProdutoDbg produtoDbg = getProduto(pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo(),
					pedidoItemObc.getCodProduto());

			pedidoItemDbg.setSTG_EST_TABPRO_Ped_Codigo(produtoDbg.getEST_TABPRO_Codigo());
			pedidoItemDbg.setSTG_EST_TABDEP_Ped_Codigo(produtoDbg.getEST_TABDEP_Codigo());

			pedidoItemDbg.setCOM_PROPED_Quantidade(pedidoItemObc.getQuantidade()); // Quantidade pedida
			pedidoItemDbg.setCOM_PROPED_Valor(pedidoItemObc.getValorUnit()); // Valor Unitário do item
			pedidoItemDbg.setCOM_PROPED_Entregue(0d); // Quantidade já entregue do item
			pedidoItemDbg.setCOM_PROPED_Cancelado(0d); // Quantidade cancelada do item
			pedidoItemDbg.setCOM_PROPED_PercIPI(pedidoItemObc.getpIpi()); // Percentual de IPI

			if (pedidoItemObc.getMarca() != null && !pedidoItemObc.getMarca().isEmpty()) {
				pedidoItemDbg.setCOM_PROPED_Observacao("Marca: ".concat(pedidoItemObc.getMarca())); // Observações
			} else {
				pedidoItemDbg.setCOM_PROPED_Observacao("");
			}

			pedidoItemDbg.setCOM_PROPED_Situacao("P"); // Situação do Item do Pedido
			pedidoItemDbg.setCOM_PROPED_Created(agora); // Data e hora da criação do registro
			pedidoItemDbg.setCOM_PROPED_Updated(agora); // Data e hora da última alteração do registro
			pedidoItemDbg.setCOM_PROPED_DiasGarantia(pedidoItemObc.getTempoGarantia());

			pedidoItemDbg.setCOM_PROPED_SDCV_OBC(pedidoItemObc.getCodSdcv());
			pedidoItemDbg.setCOM_PROPED_TipoSDCV(pedidoItemObc.getTipoSdcv());

			if (pedidoItemObc.getCentroCusto().equals("COMPRA_REVENDA")
					|| pedidoItemObc.getCentroCusto().equals("COMPRA_ESTOQUE")) {
				pedidoItemDbg.setSTG_GEN_TABCEN_Ped_Codigo(null);
				pedidoItemDbg.setSTG_FRT_TABCAR_Ped_Codigo(null);

				pedidoCompraDbg.setCOM_PEDIDO_Tipo("E"); // Tipo do Pedido (E-Estoque, C-Consumo)
			} else {
				if (pedidoItemObc.getMotivoCompra().equals("RP")) { // RP = Reposição de estoque
					pedidoCompraDbg.setCOM_PEDIDO_Tipo("E"); // Tipo do Pedido (E-Estoque, C-Consumo
				} else {
					pedidoCompraDbg.setCOM_PEDIDO_Tipo("C"); // Tipo do Pedido (E-Estoque, C-Consumo)
				}

				if (pedidoItemObc.getRatCCusto().size() > 1) {
					pedidoItemDbg.getRatCCusto()
							.addAll(this.getRatCCusto(pedidoCompraDbg, pedidoItemDbg, pedidoItemObc));

					pedidoItemDbg.setSTG_GEN_TABCEN_Ped_Codigo(null);
					pedidoItemDbg.setSTG_FRT_TABCAR_Ped_Codigo(null);
				} else {
					if (pedidoItemObc.getCentroCusto().startsWith("DBG-PLACA")) {

						pedidoItemDbg.setSTG_GEN_TABCEN_Ped_Codigo(null);
						pedidoItemDbg.setSTG_FRT_TABCAR_Ped_Codigo(this
								.getVeiculo(pedidoItemDbg.getSTG_GEN_TABEMP_Codigo(), pedidoItemObc.getCentroCusto()));
					} else {
						pedidoItemDbg.setSTG_GEN_TABCEN_Ped_Codigo(this.getCentroCusto(
								pedidoItemDbg.getSTG_GEN_TABEMP_Codigo(), pedidoItemObc.getCentroCusto()));

						pedidoItemDbg.setSTG_FRT_TABCAR_Ped_Codigo(null);
					}
				}
			}

			pedidoItemDbg.setSolicitacaoCompraItemDbg(this.getSolicitacaoItem(pedidoItemDbg));
			pedidoItemDbg.setCotacaoCompraItemDbg(this.getCotacaoCompraItem(pedidoItemDbg));

			itens.add(pedidoItemDbg);
		}

		return itens;
	}

	private String getVeiculo(Integer codEmpresa, String centroCustoObc) throws Exception {

		String[] centroCustoObcSplit = centroCustoObc.split("-");
		String placaVeiculoDbg = centroCustoObcSplit[2].concat("-").concat(centroCustoObcSplit[3]);

		VeiculoDbg veiculoDbg = new VeiculoDbgDAO(this.conexaoDbGint).getByCodEmpresaPlaca(codEmpresa, placaVeiculoDbg);

		if (veiculoDbg == null) {

			veiculoDbg = new VeiculoDbgDAO(this.conexaoDbGint).getByCodEmpresaPlacaAnterior(codEmpresa,
					placaVeiculoDbg);

			if (veiculoDbg == null) {
				throw new DbGintException("Veiculo nao encontrado! Empresa: ".concat(codEmpresa.toString())
						.concat(" / Placa: ".concat(centroCustoObc).concat(" (".concat(placaVeiculoDbg).concat(")"))));
			}
		}

		if (!veiculoDbg.getFRT_TABCAR_Situacao().equals("A")) {
			throw new DbGintException("Veiculo nao esta ativo! Empresa: ".concat(codEmpresa.toString())
					.concat(" / Placa: ".concat(centroCustoObc).concat(
							" (".concat(placaVeiculoDbg).concat(") - ").concat(veiculoDbg.getFRT_TABCAR_Descricao()))));
		}

		return veiculoDbg.getFRT_TABCAR_Codigo();
	}

	private List<PedidoRatCCustoDbg> getRatCCusto(PedidoCompraDbg pedidoCompraDbg, PedidoCompraItemDbg pedidoItemDbg,
			PedidoCompraItemObc pedidoItemObc) throws Exception {

		int sequencia = 0;

		List<PedidoRatCCustoDbg> ratCCustoDbgs = new ArrayList<>();

		for (PedidoRatCCustoObc ratCCustoObc : pedidoItemObc.getRatCCusto()) {

			PedidoRatCCustoDbg ratCCustoDbg = new PedidoRatCCustoDbg();

			ratCCustoDbg.setSTG_GEN_TABEMP_Codigo(pedidoItemDbg.getSTG_GEN_TABEMP_Codigo());
			ratCCustoDbg.setSTG_GEN_TABFIL_Codigo(pedidoItemDbg.getSTG_GEN_TABFIL_Codigo());
			ratCCustoDbg.setCOM_PEDIDO_Numero(pedidoItemDbg.getCOM_PEDIDO_Numero());
			ratCCustoDbg.setCOM_PROPED_Sequencia(pedidoItemDbg.getCOM_PROPED_Sequencia());
			ratCCustoDbg.setCOM_CONPED_Sequencia(++sequencia);
			ratCCustoDbg.setCOM_CONPED_Created(pedidoItemDbg.getCOM_PROPED_Created());
			ratCCustoDbg.setCOM_CONPED_Updated(pedidoItemDbg.getCOM_PROPED_Updated());

			ratCCustoDbg.setCOM_CONPED_Quantidade(
					(pedidoItemDbg.getCOM_PROPED_Quantidade() * ratCCustoObc.getpRateio()) / 100);

			if (ratCCustoObc.getCentroCusto().equals("COMPRA_REVENDA")
					|| ratCCustoObc.getCentroCusto().equals("COMPRA_ESTOQUE")) {
				ratCCustoDbg.setSTG_GEN_TABCEN_Cop_Codigo(null);
				ratCCustoDbg.setSTG_FRT_TABCAR_Cop_Codigo(null);
			} else {
				if (ratCCustoObc.getCentroCusto().startsWith("DBG-PLACA")) {
					ratCCustoDbg.setSTG_GEN_TABCEN_Cop_Codigo(null);
					ratCCustoDbg.setSTG_FRT_TABCAR_Cop_Codigo(
							this.getVeiculo(ratCCustoDbg.getSTG_GEN_TABEMP_Codigo(), ratCCustoObc.getCentroCusto()));
				} else {
					ratCCustoDbg.setSTG_GEN_TABCEN_Cop_Codigo(this
							.getCentroCusto(ratCCustoDbg.getSTG_GEN_TABEMP_Codigo(), ratCCustoObc.getCentroCusto()));

					ratCCustoDbg.setSTG_FRT_TABCAR_Cop_Codigo(null);
				}
			}

			ratCCustoDbgs.add(ratCCustoDbg);
		}

		return ratCCustoDbgs;
	}

	private String getNaturezaOperacao(PedidoCompraObc pedidoCompraObc, PedidoCompraDbg pedidoCompraDbg,
			String motivoCompra) throws Exception {

		boolean dentroEstado = pedidoCompraDbg.getFornecedor().getGEN_ESTMUN_Estado()
				.equalsIgnoreCase(pedidoCompraDbg.getFilial().getGEN_ESTMUN_Estado());

		boolean movEstoque = motivoCompraMovEstoque(motivoCompra);

		boolean fornecedorSimples = pedidoCompraDbg.getFornecedor().getGEN_TABENT_Simples().equals(1);

		String codNaturezaOperacao = "";

		if (dentroEstado) {
			if (movEstoque) {
				if (fornecedorSimples) {
					codNaturezaOperacao = "1407006"; // COMPRA USO/CONS ST SIMPLES NACIONAL FROTA COM ESTOQUE
				} else {
					codNaturezaOperacao = "1407004"; // COMPRA USO/CONSUMO ST, COM ICMS_PIS/COF E ESTOQUE
				}
			} else {
				if (fornecedorSimples) {
					codNaturezaOperacao = "1407003"; // COMPRA USO/CONS ST SIMPLES NACIONAL FROTA SEM ESTOQUE
				} else {
					codNaturezaOperacao = "1407001"; // COMPRA USO/CONSUMO ST, COM ICMS_PIS/COF SEM ESTOQUE
				}
			}
		} else {
			if (movEstoque) {
				if (fornecedorSimples) {
					codNaturezaOperacao = "2407006"; // COMPRA USO/CONS ST SIMPLES NACIONAL COM ESTOQUE
				} else {
					codNaturezaOperacao = "2407004"; // COMPRA USO/CONSUMO ST, COM ICMS_PIS/COF COM ESTOQUE
				}
			} else {
				if (fornecedorSimples) {
					codNaturezaOperacao = "2407003"; // COMPRA USO/CONS ST SIMPLES NACIONAL SEM ESTOQUE
				} else {
					codNaturezaOperacao = "2407001"; // COMPRA USO/CONSUMO ST, COM ICMS_PIS/COF SEM ESTOQUE
				}
			}
		}

		NaturezaOperacaoDbg naturezaOperacaoDbg = new NaturezaOperacaoDbgDAO(this.conexaoDbGint)
				.getByCodEmpresaCodigo(pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo(), codNaturezaOperacao);

		if (naturezaOperacaoDbg == null) {
			throw new DbGintException("Natureza de operação de codigo "
					.concat(codNaturezaOperacao.concat(" nao encontrada na empresa de codigo "
							.concat(pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo().toString()))));
		}

		if (!naturezaOperacaoDbg.getGEN_NATOPE_Ativo().equals("1")) {
			throw new DbGintException(
					"Natureza de operação de codigo ".concat(codNaturezaOperacao).concat(" esta inativa!"));
		}

		return codNaturezaOperacao;
	}

	private FilialEmpresaDbg getFilialEmpresa(String codigoEmpresa) throws Exception {

		String cnpj = "";

		if (codigoEmpresa.startsWith("DBG-")) {
			cnpj = codigoEmpresa.split("-")[1];
		} else {
			cnpj = codigoEmpresa;
		}

		String cnpjFormatado = new StrUtil().cnpjComFormatacao(cnpj);

		FilialEmpresaDbg filialEmpresaDbg = new FilialEmpresaDbgDAO(this.conexaoDbGint).getByCnpj(cnpjFormatado);

		if (filialEmpresaDbg == null) {
			throw new DbGintException("Nao foi encontrada uma empresa ativa com o CNPJ: ".concat(cnpjFormatado));
		}

		return filialEmpresaDbg;
	}

	private EnderecoClienteFornecedorDbg getFornecedor(String cnpj) throws Exception {

		EnderecoClienteFornecedorDbg fornecedor = new EnderecoClienteFornecedorDbgDAO(this.conexaoDbGint)
				.getFornecedorByCnpj(cnpj);

		if (fornecedor == null) {
			throw new DbGintException("Não foi encontrado um fornecedor/endereco ativo com o CNPJ: ".concat(cnpj));
		}

		return fornecedor;
	}

	private EnderecoClienteFornecedorDbg getTransportadora(String cnpj) throws Exception {

		return new EnderecoClienteFornecedorDbgDAO(this.conexaoDbGint).getTransportadorByCnpj(cnpj);

	}

	private Integer getCodCondPagto(Integer codigoEmpresa, String codCondPagtoOBC) throws Exception {

		CondPagtoDbgDAO condPagtoDbgDAO = new CondPagtoDbgDAO(this.conexaoDbGint);
		CondPagtoDbg condPagtoDbg = condPagtoDbgDAO.getByCodEmpresaCodCondPagto(codigoEmpresa, codCondPagtoOBC);

		String condObc = "";

		if (condPagtoDbg == null) {
			throw new DbGintException("Condicao de pagamento nao encontrada! CodEmpresa: "
					.concat(codigoEmpresa.toString()).concat(" / Codigo Obc: ")
					.concat(condObc != null && !condObc.isEmpty() ? condObc : codCondPagtoOBC));
		}

		return condPagtoDbg.getGEN_TABCPG_Codigo();
	}

	private ProdutoDbg getProduto(Integer codEmpresa, String codProdutoObc) throws Exception {

		if (!codProdutoObc.startsWith("DBG-")) {
			throw new DbGintException(
					"Pedido apontado para filial DbGint que contém produtos sem 'DBG' no início do código! ["
							.concat(codProdutoObc.concat("]")));
		}

		String[] codigoProdutoObc = codProdutoObc.split("-");
		String codProdutoDbg = codigoProdutoObc[codigoProdutoObc.length - 1];

		ProdutoDbg produtoDbg = new ProdutoDbgDAO(conexaoDbGint).getByCodigo(codEmpresa, codProdutoDbg);

		if (produtoDbg == null) {
			throw new DbGintException("Produto nao encontrado! Codigo: ".concat(codProdutoDbg));
		}

		if (!produtoDbg.getEST_TABPRO_Ativo().equals("1")) {
			throw new DbGintException("Produto esta inativo! Codigo Empresa: ".concat(codEmpresa.toString())
					.concat(" / Codigo Produto: ").concat(codProdutoDbg));
		}

		produtoDbg.setEST_TABPRO_Codigo(codProdutoDbg);

		return produtoDbg;
	}

	private String getCentroCusto(Integer codEmpresa, String codCentroCustoObc) throws Exception {

		CentroCustoDbgDAO centroCustoDbgDAO = new CentroCustoDbgDAO(conexaoDbGint);

		String codCentroCustoDbg = "";
		String[] arrCodCentroCustoDbg = codCentroCustoObc.split("-");
		codCentroCustoDbg = arrCodCentroCustoDbg[arrCodCentroCustoDbg.length - 1];

		if (codCentroCustoObc.startsWith("PRT-")) {
			codCentroCustoDbg = "000".concat(codCentroCustoDbg);
		}

		CentroCustoDbg centroCustoDbg = centroCustoDbgDAO.getByCodigoEmpresaCodigo(codEmpresa, codCentroCustoDbg);

		if (centroCustoDbg == null && codCentroCustoObc.startsWith("PRT-")) {
			codCentroCustoDbg = "0".concat(codCentroCustoDbg);

			centroCustoDbg = centroCustoDbgDAO.getByCodigoEmpresaCodigo(codEmpresa, codCentroCustoDbg);
		}

		if (centroCustoDbg == null) {
			throw new DbGintException("Centro de custo nao encontrado! Codigo: ".concat(codCentroCustoDbg));
		}

		if (!centroCustoDbg.getGEN_TABCEN_Ativo().equals("1")) {
			throw new DbGintException("Centro de custo está inativo! Codigo: ".concat(codCentroCustoDbg));
		}

		return codCentroCustoDbg;
	}

	private boolean motivoCompraMovEstoque(String codMotivoCompra) throws Exception {

		if (codMotivoCompra.equalsIgnoreCase("RP")  || codMotivoCompra.equalsIgnoreCase("CA") ) { // RP = Reposição de estoque
			return true;
		}

		if (codMotivoCompra.equalsIgnoreCase("CD") || codMotivoCompra.equalsIgnoreCase("EM")) { // CD = Consumo direto
																								// EM = Emergencial
			return false;
		}

		throw new DbGintException("Motivo de compra nao identificado para compras DbGint: ".concat(codMotivoCompra));
	}

	private SolicitacaoCompraItemDbg getSolicitacaoItem(PedidoCompraItemDbg pedidoCompraItemDbg) throws Exception {

		SolicitacaoCompraItemDbgDAO solicitacaoCompraItemDbgDAO = new SolicitacaoCompraItemDbgDAO(this.conexaoDbGint);

		SolicitacaoCompraItemDbg solicitacaoCompraItemDbg = solicitacaoCompraItemDbgDAO.getSolicitacaoSDCV(
				pedidoCompraItemDbg.getSTG_GEN_TABEMP_Codigo(), pedidoCompraItemDbg.getSTG_GEN_TABFIL_Codigo(),
				pedidoCompraItemDbg.getCOM_PROPED_SDCV_OBC());

		if (solicitacaoCompraItemDbg != null) {
			solicitacaoCompraItemDbg.setSolicitacaoCompraDbg(new SolicitacaoCompraDbgDAO(this.conexaoDbGint)
					.getSolicitacao(solicitacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo(),
							solicitacaoCompraItemDbg.getSTG_GEN_TABFIL_Codigo(),
							solicitacaoCompraItemDbg.getCOM_SOLICI_Numero()));
		}

		return solicitacaoCompraItemDbg;
	}

	private CotacaoCompraItemDbg getCotacaoCompraItem(PedidoCompraItemDbg pedidoCompraItemDbg) throws Exception {

		CotacaoCompraItemDbgDAO cotacaoCompraItemDbgDAO = new CotacaoCompraItemDbgDAO(this.conexaoDbGint);

		CotacaoCompraItemDbg cotacaoCompraItemDbg = cotacaoCompraItemDbgDAO.getCotacaoSdcv(
				pedidoCompraItemDbg.getSTG_GEN_TABEMP_Codigo(), pedidoCompraItemDbg.getSTG_GEN_TABFIL_Codigo(),
				pedidoCompraItemDbg.getCOM_PROPED_SDCV_OBC());

		if (cotacaoCompraItemDbg != null) {
			cotacaoCompraItemDbg.setCotacaoCompraDbg(new CotacaoCompraDbgDAO(this.conexaoDbGint).getCotacao(
					cotacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo(), cotacaoCompraItemDbg.getSTG_GEN_TABFIL_Codigo(),
					cotacaoCompraItemDbg.getCOM_COTACA_Numero()));
		}

		return cotacaoCompraItemDbg;
	}

	private void baixarStatusSolicitacao(SolicitacaoCompraItemDbg solicitacaoCompraItemDbg) throws Exception {

		new SolicitacaoCompraItemDbgDAO(this.conexaoDbGint).updStatusAtendida(solicitacaoCompraItemDbg);
	}

}

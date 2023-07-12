package br.com.agricopel.integrador_obc.emsys.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.exception.EmsysException;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.StrUtil;
import br.com.agricopel.integrador_obc.emsys.dao.CentroCustoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.CondPagtoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.EnderecoClienteFornecedorEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.FilialEmpresaEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.NaturezaOperacaoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.ParametrosEmvDAO;
/*import br.com.agricopel.integrador_obc.emsys.dao.CentroCustoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.CondPagtoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.CotacaoCompraEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.CotacaoCompraItemEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.EnderecoClienteFornecedorEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.FilialEmpresaEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.NaturezaOperacaoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.ParametrosEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.PedidoCompraAprovacaoEmvDAO;*/
import br.com.agricopel.integrador_obc.emsys.dao.PedidoCompraEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.PedidoCompraItemEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.ProdutoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.model.CentroCustoEmv;
import br.com.agricopel.integrador_obc.emsys.model.CondPagtoEmv;
import br.com.agricopel.integrador_obc.emsys.model.EnderecoClienteFornecedorEmv;
import br.com.agricopel.integrador_obc.emsys.model.FilialEmpresaEmv;
import br.com.agricopel.integrador_obc.emsys.model.NaturezaOperacaoEmv;
import br.com.agricopel.integrador_obc.emsys.model.PedidoCompraEmv;
import br.com.agricopel.integrador_obc.emsys.model.PedidoCompraItemEmv;
import br.com.agricopel.integrador_obc.emsys.model.ProdutoEmv;
/*import br.com.agricopel.integrador_obc.emsys.dao.PedidoRatCCustoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.ProdutoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.SolicitacaoCompraEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.SolicitacaoCompraItemEmvDAO;
import br.com.agricopel.integrador_obc.emsys.dao.VeiculoEmvDAO;
import br.com.agricopel.integrador_obc.emsys.model.CentroCustoEmv;
import br.com.agricopel.integrador_obc.emsys.model.CondPagtoEmv;
import br.com.agricopel.integrador_obc.emsys.model.CotacaoCompraItemEmv;
import br.com.agricopel.integrador_obc.emsys.model.EnderecoClienteFornecedorEmv;
import br.com.agricopel.integrador_obc.emsys.model.FilialEmpresaEmv;*/
//import br.com.agricopel.integrador_obc.emsys.model.NaturezaOperacaoEmv;
/*import br.com.agricopel.integrador_obc.emsys.model.PedidoCompraAprovacaoEmv;
import br.com.agricopel.integrador_obc.emsys.model.PedidoCompraEmv;
import br.com.agricopel.integrador_obc.emsys.model.PedidoCompraItemEmv;
import br.com.agricopel.integrador_obc.emsys.model.PedidoRatCCustoEmv;
import br.com.agricopel.integrador_obc.emsys.model.ProdutoEmv;
import br.com.agricopel.integrador_obc.emsys.model.SolicitacaoCompraItemEmv;
import br.com.agricopel.integrador_obc.emsys.model.VeiculoEmv;*/
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraItemObc;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraObc;
//import br.com.agricopel.integrador_obc.obc.model.PedidoRatCCustoObc;
import br.com.agricopel.integrador_obc.obc.model.RetGravarPedidoObc;

public class PedidoCompraEmvBO {

	private ConexaoEmsys conexaoEmsys;

	public RetGravarPedidoObc gravar(PedidoCompraObc pedidoCompraObc) throws Exception {

		this.conexaoEmsys = new ConexaoEmsys();

		String sdcv = pedidoCompraObc.getItens().get(0).getCodSdcv();

		PedidoCompraEmv pedidoCompraEmv = new PedidoCompraEmvDAO(this.conexaoEmsys).getPedidoSdcv(sdcv);

		if (pedidoCompraEmv != null && pedidoCompraEmv.getseq_pedido() != null
				&& pedidoCompraEmv.getseq_pedido() > 0) {

			LogUtils.escreverLogInfo(
					"Encontrado pedido já existente [".concat(String.valueOf(pedidoCompraEmv.getseq_pedido()))
							.concat("] a partir da SDCV [".concat(sdcv).concat("]")));

			return getRetorno(pedidoCompraEmv);
		} else {
			return procPedido(pedidoCompraObc);
		}
	}

	private RetGravarPedidoObc procPedido(PedidoCompraObc pedidoCompraObc) throws Exception {

		try {
			this.conexaoEmsys.abrirTransacao();

			//CotacaoCompraEmvBO cotacaoCompraEmvBO = new CotacaoCompraEmvBO(this.conexaoEmsys);
			PedidoCompraEmv pedidoCompraEmv = this.getPedido(pedidoCompraObc);

			inserirPedido(pedidoCompraEmv);
			this.conexaoEmsys.fecharTransacao(true);

			RetGravarPedidoObc retGravarPedidoObc = getRetorno(pedidoCompraEmv);

			return retGravarPedidoObc;
		} catch (Exception e) {
			this.conexaoEmsys.fecharTransacao(false);
			throw e;
		} finally {
			this.conexaoEmsys.close();
		}
	}

	private RetGravarPedidoObc getRetorno(PedidoCompraEmv pedidoCompraEmv) {

		RetGravarPedidoObc retGravarPedidoObc = new RetGravarPedidoObc();

		retGravarPedidoObc.setCodPedido(pedidoCompraEmv.getseq_pedido().toString());
		retGravarPedidoObc.setSucesso(true);

		return retGravarPedidoObc;
	}

	private void inserirPedido(PedidoCompraEmv pedidoCompraEmv) throws Exception {

		pedidoCompraEmv.setseq_pedido(getUltCodPedidoCompra(pedidoCompraEmv.getcod_empresa(),
				0));

		new PedidoCompraEmvDAO(this.conexaoEmsys).inserir(pedidoCompraEmv);

		inserirItens(pedidoCompraEmv);
		//inserirAprovacao(pedidoCompraEmv);

		//new ParametrosEmvDAO(this.conexaoEmsys).setUltCodigoPedidoCompra(pedidoCompraEmv.getcod_empresa(),
		//		0, pedidoCompraEmv.getseq_pedido());
	}

	private void inserirItens(PedidoCompraEmv pedidoCompraEmv) throws Exception {

		PedidoCompraItemEmvDAO pedidoCompraItemEmvDAO = new PedidoCompraItemEmvDAO(this.conexaoEmsys);

		for (PedidoCompraItemEmv pedidoCompraItemEmv : pedidoCompraEmv.getItens()) {
			
			pedidoCompraItemEmv.setseq_pedido(pedidoCompraEmv.getseq_pedido());
			
			//NATUREZA DA OPERAÇÃO
			PedidoCompraItemEmv naturezaoperacao = new PedidoCompraItemEmvDAO(this.conexaoEmsys).getNatureza( pedidoCompraEmv.getcod_empresa(),pedidoCompraItemEmv.getcod_item());
			pedidoCompraItemEmv.setcod_natureza_operacao(naturezaoperacao.getcod_natureza_operacao());
		
			
			//pedidoCompraItemEmv.setcod_natureza_operacao(pedidoCompraItemEmvDAO.getnatureza(null, null).getcod_natureza_operacao());
			
			pedidoCompraItemEmvDAO.inserir(pedidoCompraItemEmv);

			//inserirRateioCC(pedidoCompraItemEmv);
		}
		
		//Grava tabela com valores das parcelas de  acordo com a condição de pagamento
		String[] parcelas;
		LogUtils.escreverLogInfo(pedidoCompraEmv.getcondpagto());
		parcelas = pedidoCompraEmv.getcondpagto().split(",");
		
		LogUtils.escreverLogInfo("parcelas.length");
		LogUtils.escreverLogInfo(String.valueOf(parcelas.length));
		
		int    numpedido = 0 ; 
		int    numparcela = 0 ; 
		double valparcela = 0.00;
		
		
		numpedido  = pedidoCompraEmv.getseq_pedido();
		valparcela = ( pedidoCompraEmv.getval_pedido() / parcelas.length );
		valparcela = Math.round(valparcela*100.0)/100.0;
		
		for (int i = 0; i < parcelas.length ; i++) {
			
			LogUtils.escreverLogInfo("LOGSPILLER numpedido " + String.valueOf(numpedido));
			LogUtils.escreverLogInfo("LOGSPILLER i " + String.valueOf(i));
			LogUtils.escreverLogInfo("LOGSPILLER parcelas " + parcelas[i]);
			LogUtils.escreverLogInfo("LOGSPILLER valparcela " + String.valueOf(valparcela));
			
			//Adiciona diferenca de aredondamento na ultima parcela 
			if( parcelas.length == (i+1) ) {
				valparcela = (valparcela  + ( pedidoCompraEmv.getval_pedido()  - (parcelas.length  * valparcela)));	
			}
			
			//remove tab e espaço
			LogUtils.escreverLogInfo("LOGSPILLER  parcela "+parcelas[i] + "|");
			parcelas[i] = parcelas[i].replace(" ", "");
			LogUtils.escreverLogInfo("LOGSPILLER  parcela "+parcelas[i] + "|");
			parcelas[i] = parcelas[i].replace("\t", "");
			LogUtils.escreverLogInfo("LOGSPILLER  parcela "+parcelas[i] + "|");
			
			
			numparcela =  Integer.valueOf(parcelas[i]);
			
			pedidoCompraItemEmvDAO.inserirparcela(numpedido, (i+1) , numparcela	, valparcela);
						
		}
		
	
	}

	/*private void inserirAprovacao(PedidoCompraEmv pedidoCompraEmv) throws Exception {

		PedidoCompraAprovacaoEmv pedidoCompraAprovacaoEmv = new PedidoCompraAprovacaoEmv();

		pedidoCompraAprovacaoEmv.setSTG_GEN_TABEMP_Codigo(pedidoCompraEmv.getSTG_GEN_TABEMP_Codigo());
		pedidoCompraAprovacaoEmv.setSTG_GEN_TABFIL_Codigo(pedidoCompraEmv.getSTG_GEN_TABFIL_Codigo());
		pedidoCompraAprovacaoEmv.setCOM_PEDIDO_Numero(pedidoCompraEmv.getCOM_PEDIDO_Numero());
		pedidoCompraAprovacaoEmv.setCOM_APRPED_DHAprovacao(pedidoCompraEmv.getCOM_PEDIDO_Created());
		pedidoCompraAprovacaoEmv.setSTG_GEN_TABUSU_Apr_Login(pedidoCompraEmv.getSTG_GEN_TABUSU_Dig_Login());
		pedidoCompraAprovacaoEmv.setCOM_APRPED_Situacao("A");
		pedidoCompraAprovacaoEmv.setCOM_MOTCAN_Codigo(null);
		pedidoCompraAprovacaoEmv.setCOM_APRPED_Created(pedidoCompraEmv.getCOM_PEDIDO_Created());
		pedidoCompraAprovacaoEmv.setCOM_APRPED_Updated(pedidoCompraEmv.getCOM_PEDIDO_Created());

		new PedidoCompraAprovacaoEmvDAO(this.conexaoEmsys).inserir(pedidoCompraAprovacaoEmv);
	}*/

	/*private void inserirRateioCC(PedidoCompraItemEmv pedidoCompraItemEmv) throws Exception {

		PedidoRatCCustoEmvDAO ratCCustoEmvDAO = new PedidoRatCCustoEmvDAO(this.conexaoEmsys);

		for (PedidoRatCCustoEmv ratCCustoEmv : pedidoCompraItemEmv.getRatCCusto()) {
			ratCCustoEmv.setCOM_PEDIDO_Numero(pedidoCompraItemEmv.getCOM_PEDIDO_Numero());
			ratCCustoEmvDAO.inserir(ratCCustoEmv);
		}
	}*/

	private Integer getUltCodPedidoCompra(Integer codigoEmpresa, Integer codigoFilial) throws Exception {

		Integer ultCodPedidoCompra = new ParametrosEmvDAO(this.conexaoEmsys).getUltCodigoPedidoCompra(/*codigoEmpresa,
				codigoFilial*/);

		if (ultCodPedidoCompra == null) {
			throw new EmsysException(
					"Nao foi possivel encontrar a numeracao de pedido de compra da filial do pedido! CodEmpresa: "
							.concat(codigoEmpresa.toString())
							.concat(" / CodFilial: ".concat(codigoEmpresa.toString())));
		}

		return ultCodPedidoCompra;
	}

	private PedidoCompraEmv getPedido(PedidoCompraObc pedidoCompraObc) throws Exception {

		PedidoCompraEmv pedidoCompraEmv = new PedidoCompraEmv();
		
		FilialEmpresaEmv filialEmpresa = this.getFilialEmpresa(pedidoCompraObc.getFilialCob());
		pedidoCompraEmv.setcod_empresa(filialEmpresa.getcod_empresa());
		LogUtils.escreverLogInfo("LOGSPILLER setval_pedido" ) ;
		pedidoCompraEmv.setseq_pedido(0);
		pedidoCompraEmv.setval_pedido(0.00);
		pedidoCompraEmv.setind_pendente("S");
		pedidoCompraEmv.setnom_usuario(pedidoCompraObc.getComprador());
		pedidoCompraEmv.setval_adiantamento(0.00);
		pedidoCompraEmv.setdta_cadastro(pedidoCompraObc.getDataCompra());
		pedidoCompraEmv.setdta_emissao(new DateUtil().agora().toLocalDate());
		LogUtils.escreverLogInfo("LOGSPILLER fornecedor" ) ;
		EnderecoClienteFornecedorEmv fornecedor = this.getFornecedor(pedidoCompraObc.getCnpjFornecedor());
		pedidoCompraEmv.setcod_pessoa_fornecedor(fornecedor.getcod_pessoa()); //ajustar STRING TO INTEGER
		pedidoCompraEmv.setnum_pedido_fornecedor(pedidoCompraObc.getCodPedidoObc());
		pedidoCompraEmv.setind_tipo_frete(pedidoCompraObc.getTipoFrete().equals("1") ? "C" : "F");
		pedidoCompraEmv.setdes_observacao(pedidoCompraObc.getObservacao());
		pedidoCompraEmv.setval_frete(Double.valueOf(0)); // Valor do Frete
		
		pedidoCompraEmv.getItens().addAll(this.getItens(pedidoCompraObc, pedidoCompraEmv));

		pedidoCompraEmv.setseqProduto(pedidoCompraEmv.getItens().size()); // Última sequencia do item do
		// pedido
		LogUtils.escreverLogInfo("LOGSPILLER frete" + pedidoCompraEmv.getind_tipo_frete());
		LogUtils.escreverLogInfo("LOGSPILLER CONDICAO PAGAMENTO OBC " + pedidoCompraObc.getCondPagto());
		 
		
		PedidoCompraEmv condpagto = new PedidoCompraEmvDAO(this.conexaoEmsys).getCondPagto(pedidoCompraObc.getCondPagto());
		
		LogUtils.escreverLogInfo("LOGSPILLER CONDICAO PAGAMENTO ERP " + condpagto.getcondpagto());
		
		pedidoCompraEmv.setcondpagto(condpagto.getcondpagto());
		
		
	

		return pedidoCompraEmv;
	}

	private List<PedidoCompraItemEmv> getItens(PedidoCompraObc pedidoCompraObc, PedidoCompraEmv pedidoCompraEmv)
			throws Exception {

		List<PedidoCompraItemEmv> itens = new ArrayList<>();
		Integer sequencia = 0;
		Double  totalped = 0.00 ;
		Double  fatorconv = 0.00 ;

		LocalDateTime agora = new DateUtil().agora();
		
//		PedidoCompraItemEmv pedidoItemEmv = new PedidoCompraItemEmv();
		
		for (PedidoCompraItemObc pedidoItemObc : pedidoCompraObc.getItens()) {

			PedidoCompraItemEmv pedidoItemEmv = new PedidoCompraItemEmv();
			sequencia++;
						
			pedidoItemEmv.setcod_empresa(pedidoCompraEmv.getcod_empresa()); // Código da Empresa
			pedidoItemEmv.setseq_pedido(pedidoCompraEmv.getseq_pedido()); // Número do Pedido de Compra
			pedidoItemEmv.setseq_item(sequencia); // Sequencia do Item
 
			ProdutoEmv produtoEmv = getProduto(pedidoCompraEmv.getcod_empresa(), pedidoItemObc.getCodProduto());

			PedidoCompraItemEmv almoxarifado = new PedidoCompraItemEmvDAO(this.conexaoEmsys).getAlmoxarifado( /*String.valueOf(*/pedidoCompraEmv.getcod_empresa()/*)*/,/*String.valueOf(*/produtoEmv.getcod_item()/*)*/);
			pedidoItemEmv.setcod_almoxarifado(almoxarifado.getcod_almoxarifado());
			
			
			//UNIDADE DE MEDIDA
			LogUtils.escreverLogInfo("LOGSPILLER frete" + pedidoItemObc.getUnMedida());
			PedidoCompraItemEmv unidade = new PedidoCompraItemEmvDAO(this.conexaoEmsys).getUnidade(produtoEmv.getcod_item());
			pedidoItemEmv.setcod_unidade(unidade.getcod_unidade());
			
			pedidoItemEmv.setcod_item(produtoEmv.getcod_item());//Aqui resolver Integer to string
		
			//Captura o fator de conversão
			PedidoCompraItemEmv unidadeConv = new PedidoCompraItemEmvDAO(this.conexaoEmsys).getUnidConv( produtoEmv.getcod_item());
			fatorconv = unidadeConv.getfator_conversao();
					
			//Caso tenha fator de conversão precisa ajustar
			// - qtd_item
			// - Val_unitario
			// - val_total
			if (fatorconv == 0 ) {
				pedidoItemEmv.setqtd_item( (pedidoItemObc.getQuantidade() ) ); 
				pedidoItemEmv.setval_unitario(pedidoItemObc.getValorUnit() - pedidoItemObc.getpIpi()); // Valor Unitário do item
				pedidoItemEmv.setval_total( (pedidoItemObc.getValorUnit() * pedidoItemObc.getQuantidade()) ); // Valor Total
			}else {
				
				pedidoItemEmv.setqtd_item( (pedidoItemObc.getQuantidade() / fatorconv) );
				pedidoItemEmv.setval_unitario(( pedidoItemObc.getValorUnit() - pedidoItemObc.getpIpi() ) * fatorconv); // Valor Unitário do item
				pedidoItemEmv.setval_total( (pedidoItemEmv.getval_unitario() * pedidoItemEmv.getqtd_item()) ); // Valor Total
				
			} 
			
			//Quantidade convertida sempre vai ser o Unitario 
			pedidoItemEmv.setqtd_item_convertido(pedidoItemObc.getQuantidade()); 
					
			pedidoItemEmv.setdta_previsao_entrega(pedidoItemObc.getDtEntrega()); 
			 
			pedidoItemEmv.setval_desconto(pedidoItemObc.getDescTotalItem());
			pedidoItemEmv.setval_ipi(pedidoItemObc.getpIpi());
			pedidoItemEmv.setqtd_atendido(0.00); 
			pedidoItemEmv.setqtd_cancelado(0.00); 
			pedidoItemEmv.setval_desconto_rateado(0.00);
			
			pedidoItemEmv.setdes_observacao("#SDCV-"+pedidoItemObc.getCodSdcv()+"-SDCV# - Solicitante: "+pedidoItemObc.getSolicitante() );
			
			LogUtils.escreverLogInfo("LOGSPILLER UM " + pedidoItemObc.getUnMedida());
			LogUtils.escreverLogInfo("LOGSPILLER ARMAZEM " + pedidoItemObc.getArmazem());
			LogUtils.escreverLogInfo("LOGSPILLER DES SDCV " + pedidoItemObc.getDesSdcv());
			LogUtils.escreverLogInfo("LOGSPILLER CONTA CONTABIL " + pedidoItemObc.getContaContabil());
			
			totalped = (totalped + pedidoItemEmv.getval_total());
			
			//pedidoItemEmv.setCOM_PROPED_Entregue(0d); // Quantidade já entregue do item
			//pedidoItemEmv.setCOM_PROPED_Cancelado(0d); // Quantidade cancelada do item
			//pedidoItemEmv.setCOM_PROPED_PercIPI(pedidoItemObc.getpIpi()); // Percentual de IPI
			//pedidoItemEmv.setSTG_GEN_NATOPE_Codigo(
			//		getNaturezaOperacao(pedidoCompraObc, pedidoCompraEmv, pedidoItemObc.getMotivoCompra()));

			
			//if (pedidoItemObc.getMarca() != null && !pedidoItemObc.getMarca().isEmpty()) {
			//	pedidoItemEmv.setCOM_PROPED_Observacao("Marca: ".concat(pedidoItemObc.getMarca())); // Observações
			//} else {
			//	pedidoItemEmv.setCOM_PROPED_Observacao("");
			//}

			//pedidoItemEmv.setCOM_PROPED_Situacao("P"); // Situação do Item do Pedido
			//pedidoItemEmv.setCOM_PROPED_Created(agora); // Data e hora da criação do registro
			//pedidoItemEmv.setCOM_PROPED_Updated(agora); // Data e hora da última alteração do registro
			//pedidoItemEmv.setCOM_PROPED_DiasGarantia(pedidoItemObc.getTempoGarantia());

			//pedidoItemEmv.setCOM_PROPED_SDCV_OBC(pedidoItemObc.getCodSdcv());
			//pedidoItemEmv.setCOM_PROPED_TipoSDCV(pedidoItemObc.getTipoSdcv());

			/*if (pedidoItemObc.getCentroCusto().equals("COMPRA_REVENDA")
					|| pedidoItemObc.getCentroCusto().equals("COMPRA_ESTOQUE")) {
				pedidoItemEmv.setSTG_GEN_TABCEN_Ped_Codigo(null);
				pedidoItemEmv.setSTG_FRT_TABCAR_Ped_Codigo(null);

				pedidoCompraEmv.setCOM_PEDIDO_Tipo("E"); // Tipo do Pedido (E-Estoque, C-Consumo)
			} else {
				if (pedidoItemObc.getMotivoCompra().equals("RP")) { // RP = Reposição de estoque
					pedidoCompraEmv.setCOM_PEDIDO_Tipo("E"); // Tipo do Pedido (E-Estoque, C-Consumo
				} else {
					pedidoCompraEmv.setCOM_PEDIDO_Tipo("C"); // Tipo do Pedido (E-Estoque, C-Consumo)
				}*/

				/*if (pedidoItemObc.getRatCCusto().size() > 1) {
					pedidoItemEmv.getRatCCusto()
							.addAll(this.getRatCCusto(pedidoCompraEmv, pedidoItemEmv, pedidoItemObc));

					pedidoItemEmv.setSTG_GEN_TABCEN_Ped_Codigo(null);
					pedidoItemEmv.setSTG_FRT_TABCAR_Ped_Codigo(null);
				} else {
					if (pedidoItemObc.getCentroCusto().startsWith("Emv-PLACA")) {

						pedidoItemEmv.setSTG_GEN_TABCEN_Ped_Codigo(null);
						pedidoItemEmv.setSTG_FRT_TABCAR_Ped_Codigo(this
								.getVeiculo(pedidoItemEmv.getSTG_GEN_TABEMP_Codigo(), pedidoItemObc.getCentroCusto()));
					} else {*/
						/*pedidoItemEmv.setSTG_GEN_TABCEN_Ped_Codigo(this.getCentroCusto(
								pedidoItemEmv.getSTG_GEN_TABEMP_Codigo(), pedidoItemObc.getCentroCusto()));

						pedidoItemEmv.setSTG_FRT_TABCAR_Ped_Codigo(null);*/
					//}
				//}
			//}

			//pedidoItemEmv.setSolicitacaoCompraItemEmv(this.getSolicitacaoItem(pedidoItemEmv));
			//pedidoItemEmv.setCotacaoCompraItemEmv(this.getCotacaoCompraItem(pedidoItemEmv));
			
			itens.add(pedidoItemEmv);
		}
		
			pedidoCompraEmv.setval_pedido(totalped);
			return itens;			
	}
	
			

	/*private String getVeiculo(Integer codEmpresa, String centroCustoObc) throws Exception {

		String[] centroCustoObcSplit = centroCustoObc.split("-");
		String placaVeiculoEmv = centroCustoObcSplit[2].concat("-").concat(centroCustoObcSplit[3]);

		VeiculoEmv veiculoEmv = new VeiculoEmvDAO(this.conexaoEmsys).getByCodEmpresaPlaca(codEmpresa, placaVeiculoEmv);

		if (veiculoEmv == null) {

			veiculoEmv = new VeiculoEmvDAO(this.conexaoEmsys).getByCodEmpresaPlacaAnterior(codEmpresa,
					placaVeiculoEmv);

			if (veiculoEmv == null) {
				throw new emsysException("Veiculo nao encontrado! Empresa: ".concat(codEmpresa.toString())
						.concat(" / Placa: ".concat(centroCustoObc).concat(" (".concat(placaVeiculoEmv).concat(")"))));
			}
		}

		if (!veiculoEmv.getFRT_TABCAR_Situacao().equals("A")) {
			throw new emsysException("Veiculo nao esta ativo! Empresa: ".concat(codEmpresa.toString())
					.concat(" / Placa: ".concat(centroCustoObc).concat(
							" (".concat(placaVeiculoEmv).concat(") - ").concat(veiculoEmv.getFRT_TABCAR_Descricao()))));
		}

		return veiculoEmv.getFRT_TABCAR_Codigo();
	}*/

	/*private List<PedidoRatCCustoEmv> getRatCCusto(PedidoCompraEmv pedidoCompraEmv, PedidoCompraItemEmv pedidoItemEmv,
			PedidoCompraItemObc pedidoItemObc) throws Exception {

		int sequencia = 0;

		List<PedidoRatCCustoEmv> ratCCustoEmvs = new ArrayList<>();

		for (PedidoRatCCustoObc ratCCustoObc : pedidoItemObc.getRatCCusto()) {

			PedidoRatCCustoEmv ratCCustoEmv = new PedidoRatCCustoEmv();

			ratCCustoEmv.setSTG_GEN_TABEMP_Codigo(pedidoItemEmv.getSTG_GEN_TABEMP_Codigo());
			ratCCustoEmv.setSTG_GEN_TABFIL_Codigo(pedidoItemEmv.getSTG_GEN_TABFIL_Codigo());
			ratCCustoEmv.setCOM_PEDIDO_Numero(pedidoItemEmv.getCOM_PEDIDO_Numero());
			ratCCustoEmv.setCOM_PROPED_Sequencia(pedidoItemEmv.getCOM_PROPED_Sequencia());
			ratCCustoEmv.setCOM_CONPED_Sequencia(++sequencia);
			ratCCustoEmv.setCOM_CONPED_Created(pedidoItemEmv.getCOM_PROPED_Created());
			ratCCustoEmv.setCOM_CONPED_Updated(pedidoItemEmv.getCOM_PROPED_Updated());

			ratCCustoEmv.setCOM_CONPED_Quantidade(
					(pedidoItemEmv.getCOM_PROPED_Quantidade() * ratCCustoObc.getpRateio()) / 100);

			if (ratCCustoObc.getCentroCusto().equals("COMPRA_REVENDA")
					|| ratCCustoObc.getCentroCusto().equals("COMPRA_ESTOQUE")) {
				ratCCustoEmv.setSTG_GEN_TABCEN_Cop_Codigo(null);
				ratCCustoEmv.setSTG_FRT_TABCAR_Cop_Codigo(null);
			} else {
				if (ratCCustoObc.getCentroCusto().startsWith("Emv-PLACA")) {
					ratCCustoEmv.setSTG_GEN_TABCEN_Cop_Codigo(null);
					ratCCustoEmv.setSTG_FRT_TABCAR_Cop_Codigo(
							this.getVeiculo(ratCCustoEmv.getSTG_GEN_TABEMP_Codigo(), ratCCustoObc.getCentroCusto()));
				} else {
					ratCCustoEmv.setSTG_GEN_TABCEN_Cop_Codigo(this
							.getCentroCusto(ratCCustoEmv.getSTG_GEN_TABEMP_Codigo(), ratCCustoObc.getCentroCusto()));

					ratCCustoEmv.setSTG_FRT_TABCAR_Cop_Codigo(null);
				}
			}

			ratCCustoEmvs.add(ratCCustoEmv);
		}

		return ratCCustoEmvs;
	}*/

	/*private String getNaturezaOperacao(PedidoCompraObc pedidoCompraObc, PedidoCompraEmv pedidoCompraEmv,
			String motivoCompra) throws Exception {

		boolean dentroEstado = pedidoCompraEmv.getFornecedor().getGEN_ESTMUN_Estado()
				.equalsIgnoreCase(pedidoCompraEmv.getFilial().getGEN_ESTMUN_Estado());

		boolean movEstoque = motivoCompraMovEstoque(motivoCompra);

		boolean fornecedorSimples = pedidoCompraEmv.getFornecedor().getGEN_TABENT_Simples().equals(1);

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

		NaturezaOperacaoEmv naturezaOperacaoEmv = new NaturezaOperacaoEmvDAO(this.conexaoEmsys)
				.getByCodEmpresaCodigo(pedidoCompraEmv.getcod_empresa(), codNaturezaOperacao);

		if (naturezaOperacaoEmv == null) {
			throw new EmsysException("Natureza de operação de codigo "
					.concat(codNaturezaOperacao.concat(" nao encontrada na empresa de codigo "
							.concat(pedidoCompraEmv.getcod_empresa().toString()))));
		}

		if (!naturezaOperacaoEmv.getGEN_NATOPE_Ativo().equals("1")) {
			throw new EmsysException(
					"Natureza de operação de codigo ".concat(codNaturezaOperacao).concat(" esta inativa!"));
		}

		return codNaturezaOperacao;
	}*/

	private FilialEmpresaEmv getFilialEmpresa(String codigoEmpresa) throws Exception {

		String cnpj = "";

		if (codigoEmpresa.startsWith("EMV-")) { 
			cnpj = codigoEmpresa.split("-")[1];
				
		} else {
			cnpj = codigoEmpresa;
		}

		String cnpjFormatado = new StrUtil().cnpjSemFormatacao(cnpj);

		FilialEmpresaEmv filialEmpresaEmv = new FilialEmpresaEmvDAO(this.conexaoEmsys).getByCnpj(cnpjFormatado);

		if (filialEmpresaEmv == null) {
			throw new EmsysException("Nao foi encontrada uma empresa ativa com o CNPJ: ".concat(cnpjFormatado));
		}

		return filialEmpresaEmv;
	}

	private EnderecoClienteFornecedorEmv getFornecedor(String cnpj) throws Exception {

		EnderecoClienteFornecedorEmv fornecedor = new EnderecoClienteFornecedorEmvDAO(this.conexaoEmsys)
				.getFornecedorByCnpj(cnpj);

		if (fornecedor == null) {
			throw new EmsysException("Não foi encontrado um fornecedor/endereco ativo com o CNPJ: ".concat(cnpj));
		}

		return fornecedor;
	}

	private EnderecoClienteFornecedorEmv getTransportadora(String cnpj) throws Exception {

		return new EnderecoClienteFornecedorEmvDAO(this.conexaoEmsys).getTransportadorByCnpj(cnpj);

	}

	private Integer getCodCondPagto(Integer codigoEmpresa, String codCondPagtoOBC) throws Exception {

		CondPagtoEmvDAO condPagtoEmvDAO = new CondPagtoEmvDAO(this.conexaoEmsys);
		CondPagtoEmv condPagtoEmv = condPagtoEmvDAO.getByCodEmpresaCodCondPagto(codigoEmpresa, codCondPagtoOBC);

		String condObc = "";

		if (condPagtoEmv == null) {
			throw new EmsysException("Condicao de pagamento nao encontrada! CodEmpresa: "
					.concat(codigoEmpresa.toString()).concat(" / Codigo Obc: ")
					.concat(condObc != null && !condObc.isEmpty() ? condObc : codCondPagtoOBC));
		}

		return condPagtoEmv.getGEN_TABCPG_Codigo();
	}

	private ProdutoEmv getProduto(Integer codEmpresa, String codProdutoObc) throws Exception {

		if (!codProdutoObc.startsWith("EMV-")) {
			throw new EmsysException(
					"Pedido apontado para filial emsys que contém produtos sem 'Emv' no início do código! ["
							.concat(codProdutoObc.concat("]")));
		}

		String[] codigoProdutoObc = codProdutoObc.split("-");
		String codProdutoEmv = codigoProdutoObc[codigoProdutoObc.length - 1];

		ProdutoEmv produtoEmv = new ProdutoEmvDAO(conexaoEmsys).getByCodigo(/*codEmpresa,*/ codProdutoEmv);

		if (produtoEmv == null) {
			throw new EmsysException("Produto nao encontrado! Codigo: ".concat(codProdutoEmv));
		}

		/*if (!produtoEmv.getEST_TABPRO_Ativo().equals("1")) {
			throw new EmsysException("Produto esta inativo! Codigo Empresa: ".concat(codEmpresa.toString())
					.concat(" / Codigo Produto: ").concat(codProdutoEmv));
		}*/

		produtoEmv.setcod_item(Integer.parseInt(codProdutoEmv));

		return produtoEmv;
	}

	private String getCentroCusto(Integer codEmpresa, String codCentroCustoObc) throws Exception {

		CentroCustoEmvDAO centroCustoEmvDAO = new CentroCustoEmvDAO(conexaoEmsys);

		String codCentroCustoEmv = "";
		String[] arrCodCentroCustoEmv = codCentroCustoObc.split("-");
		codCentroCustoEmv = arrCodCentroCustoEmv[arrCodCentroCustoEmv.length - 1];

		if (codCentroCustoObc.startsWith("PRT-")) {
			codCentroCustoEmv = "000".concat(codCentroCustoEmv);
		}

		CentroCustoEmv centroCustoEmv = centroCustoEmvDAO.getByCodigoEmpresaCodigo(codEmpresa, codCentroCustoEmv);

		if (centroCustoEmv == null && codCentroCustoObc.startsWith("PRT-")) {
			codCentroCustoEmv = "0".concat(codCentroCustoEmv);

			centroCustoEmv = centroCustoEmvDAO.getByCodigoEmpresaCodigo(codEmpresa, codCentroCustoEmv);
		}

		if (centroCustoEmv == null) {
			throw new EmsysException("Centro de custo nao encontrado! Codigo: ".concat(codCentroCustoEmv));
		}

		if (!centroCustoEmv.getGEN_TABCEN_Ativo().equals("1")) {
			throw new EmsysException("Centro de custo está inativo! Codigo: ".concat(codCentroCustoEmv));
		}

		return codCentroCustoEmv;
	}

	private boolean motivoCompraMovEstoque(String codMotivoCompra) throws Exception {

		if (codMotivoCompra.equalsIgnoreCase("RP")) { // RP = Reposição de estoque
			return true;
		}

		if (codMotivoCompra.equalsIgnoreCase("CD") || codMotivoCompra.equalsIgnoreCase("EM")) { // CD = Consumo direto
																								// EM = Emergencial
			return false;
		}

		throw new EmsysException("Motivo de compra nao identificado para compras emsys: ".concat(codMotivoCompra));
	}

	/*private SolicitacaoCompraItemEmv getSolicitacaoItem(PedidoCompraItemEmv pedidoCompraItemEmv) throws Exception {

		SolicitacaoCompraItemEmvDAO solicitacaoCompraItemEmvDAO = new SolicitacaoCompraItemEmvDAO(this.conexaoemsys);

		SolicitacaoCompraItemEmv solicitacaoCompraItemEmv = solicitacaoCompraItemEmvDAO.getSolicitacaoSDCV(
				pedidoCompraItemEmv.getSTG_GEN_TABEMP_Codigo(), pedidoCompraItemEmv.getSTG_GEN_TABFIL_Codigo(),
				pedidoCompraItemEmv.getCOM_PROPED_SDCV_OBC());

		if (solicitacaoCompraItemEmv != null) {
			solicitacaoCompraItemEmv.setSolicitacaoCompraEmv(new SolicitacaoCompraEmvDAO(this.conexaoemsys)
					.getSolicitacao(solicitacaoCompraItemEmv.getSTG_GEN_TABEMP_Codigo(),
							solicitacaoCompraItemEmv.getSTG_GEN_TABFIL_Codigo(),
							solicitacaoCompraItemEmv.getCOM_SOLICI_Numero()));
		}

		return solicitacaoCompraItemEmv;
	}*/

	/*private CotacaoCompraItemEmv getCotacaoCompraItem(PedidoCompraItemEmv pedidoCompraItemEmv) throws Exception {

		CotacaoCompraItemEmvDAO cotacaoCompraItemEmvDAO = new CotacaoCompraItemEmvDAO(this.conexaoemsys);

		CotacaoCompraItemEmv cotacaoCompraItemEmv = cotacaoCompraItemEmvDAO.getCotacaoSdcv(
				pedidoCompraItemEmv.getSTG_GEN_TABEMP_Codigo(), pedidoCompraItemEmv.getSTG_GEN_TABFIL_Codigo(),
				pedidoCompraItemEmv.getCOM_PROPED_SDCV_OBC());

		if (cotacaoCompraItemEmv != null) {
			cotacaoCompraItemEmv.setCotacaoCompraEmv(new CotacaoCompraEmvDAO(this.conexaoemsys).getCotacao(
					cotacaoCompraItemEmv.getSTG_GEN_TABEMP_Codigo(), cotacaoCompraItemEmv.getSTG_GEN_TABFIL_Codigo(),
					cotacaoCompraItemEmv.getCOM_COTACA_Numero()));
		}

		return cotacaoCompraItemEmv;
	}*/

	/*private void baixarStatusSolicitacao(SolicitacaoCompraItemEmv solicitacaoCompraItemEmv) throws Exception {

		new SolicitacaoCompraItemEmvDAO(this.conexaoemsys).updStatusAtendida(solicitacaoCompraItemEmv);
	}*/

}

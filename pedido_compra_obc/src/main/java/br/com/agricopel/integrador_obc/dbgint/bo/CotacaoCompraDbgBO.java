package br.com.agricopel.integrador_obc.dbgint.bo;

import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoCompraDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoCompraFornDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoCompraItemDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoCompraValorDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraDbg;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraFornDbg;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraValorDbg;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoCompraDbg;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoCompraItemDbg;
import br.com.agricopel.integrador_obc.obc.bo.PedidoCompraObcBO;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraItemAnexoObc;

public class CotacaoCompraDbgBO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoCompraDbgBO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void procCotacao(PedidoCompraDbg pedidoCompraDbg, PedidoCompraItemDbg pedidoCompraItemDbg) throws Exception {

		CotacaoCompraDbg cotacaoCompraDbg = pedidoCompraItemDbg.getCotacaoCompraItemDbg().getCotacaoCompraDbg();

		new CotacaoCompraItemDbgDAO(this.conexaoDbGint).updStatus(pedidoCompraItemDbg.getCotacaoCompraItemDbg());
		new CotacaoCompraDbgDAO(this.conexaoDbGint).updStatus(cotacaoCompraDbg);

		gravarArquivoCotacao(pedidoCompraItemDbg);
		gravarFornecedorCotacao(pedidoCompraDbg, pedidoCompraItemDbg);
		gravarPrecoCotacao(pedidoCompraDbg, pedidoCompraItemDbg);
	}

	private CotacaoCompraValorDbg gravarPrecoCotacao(PedidoCompraDbg pedidoCompraDbg,
			PedidoCompraItemDbg pedidoCompraItemDbg) throws Exception {

		CotacaoCompraValorDbg cotacaoCompraValorDbg = new CotacaoCompraValorDbg();

		cotacaoCompraValorDbg.setSTG_GEN_TABEMP_Codigo(pedidoCompraItemDbg.getSTG_GEN_TABEMP_Codigo());
		cotacaoCompraValorDbg.setSTG_GEN_TABFIL_Codigo(pedidoCompraItemDbg.getSTG_GEN_TABFIL_Codigo());
		cotacaoCompraValorDbg
				.setCOM_COTACA_Numero(pedidoCompraItemDbg.getCotacaoCompraItemDbg().getCOM_COTACA_Numero());
		cotacaoCompraValorDbg
				.setCOM_PROCOT_Sequencia(pedidoCompraItemDbg.getCotacaoCompraItemDbg().getCOM_PROCOT_Sequencia());
		cotacaoCompraValorDbg.setGEN_TABENT_Codigo(pedidoCompraDbg.getSTG_GEN_TABENT_For_Codigo());
		cotacaoCompraValorDbg.setGEN_ENDENT_Codigo(pedidoCompraDbg.getSTG_GEN_ENDENT_For_Codigo());
		cotacaoCompraValorDbg.setCOM_VALORE_Quantidade(pedidoCompraItemDbg.getCOM_PROPED_Quantidade());
		cotacaoCompraValorDbg.setCOM_VALORE_Valor(pedidoCompraItemDbg.getCOM_PROPED_Valor());
		cotacaoCompraValorDbg.setCOM_VALORE_PercIPI(pedidoCompraItemDbg.getCOM_PROPED_PercIPI());
		cotacaoCompraValorDbg.setGEN_TABCPG_Codigo(pedidoCompraDbg.getGEN_TABCPG_Codigo());
		cotacaoCompraValorDbg.setCOM_VALORE_Created(pedidoCompraDbg.getCOM_PEDIDO_Created());
		cotacaoCompraValorDbg.setCOM_VALORE_Updated(pedidoCompraDbg.getCOM_PEDIDO_Updated());
		cotacaoCompraValorDbg.setCOM_VALORE_MelhorPreco(1);
		cotacaoCompraValorDbg.setCOM_VALORE_GerarPedido(1);
		cotacaoCompraValorDbg.setCOM_VALORE_Observacao("");
		cotacaoCompraValorDbg.setCOM_VALORE_Entrega(pedidoCompraDbg.getCOM_PEDIDO_Entrega());
//		cotacaoCompraValorDbg.setCOM_VALORE_DHAltValor();
		cotacaoCompraValorDbg.setCOM_VALORE_NAltValor(0);
		cotacaoCompraValorDbg.setCOM_VALORE_ValorFrete(pedidoCompraDbg.getCOM_PEDIDO_ValorFrete());
		cotacaoCompraValorDbg.setCOM_VALORE_TipoFrete(pedidoCompraDbg.getCOM_PEDIDO_TipoFrete());
		cotacaoCompraValorDbg.setCOM_VALORE_DiasGarantia(pedidoCompraItemDbg.getCOM_PROPED_DiasGarantia());
		cotacaoCompraValorDbg.setCOM_VALORE_DiasMinGarantia(pedidoCompraItemDbg.getCOM_PROPED_DiasMinGarantia());
		cotacaoCompraValorDbg.setCOM_VALORE_ValorMaxGarantia(pedidoCompraItemDbg.getCOM_PROPED_ValorMaxGarantia());

		new CotacaoCompraValorDbgDAO(this.conexaoDbGint).inserir(cotacaoCompraValorDbg);

		return cotacaoCompraValorDbg;
	}

	private void gravarFornecedorCotacao(PedidoCompraDbg pedidoCompraDbg, PedidoCompraItemDbg pedidoCompraItemDbg)
			throws Exception {

		CotacaoCompraFornDbg cotacaoCompraFornDbg = new CotacaoCompraFornDbg();

		cotacaoCompraFornDbg.setSTG_GEN_TABEMP_Codigo(pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo());
		cotacaoCompraFornDbg.setSTG_GEN_TABFIL_Codigo(pedidoCompraDbg.getSTG_GEN_TABFIL_Codigo());
		cotacaoCompraFornDbg.setCOM_COTACA_Numero(
				pedidoCompraItemDbg.getCotacaoCompraItemDbg().getCotacaoCompraDbg().getCOM_COTACA_Numero());
		cotacaoCompraFornDbg.setGEN_TABENT_Codigo(pedidoCompraDbg.getSTG_GEN_TABENT_For_Codigo());
		cotacaoCompraFornDbg.setGEN_ENDENT_Codigo(pedidoCompraDbg.getSTG_GEN_ENDENT_For_Codigo());
		cotacaoCompraFornDbg.setCOM_ENTCOT_DHEnvioEMail(pedidoCompraDbg.getCOM_PEDIDO_Created());
		cotacaoCompraFornDbg.setCOM_ENTCOT_Created(pedidoCompraDbg.getCOM_PEDIDO_Created());
		cotacaoCompraFornDbg.setCOM_ENTCOT_Updated(pedidoCompraDbg.getCOM_PEDIDO_Updated());

		CotacaoCompraFornDbgDAO cotacaoCompraFornDbgDAO = new CotacaoCompraFornDbgDAO(this.conexaoDbGint);
		if (!cotacaoCompraFornDbgDAO.validarExistencia(cotacaoCompraFornDbg)) {
			cotacaoCompraFornDbgDAO.inserir(cotacaoCompraFornDbg);
		}

	}

	private void gravarArquivoCotacao(PedidoCompraItemDbg pedidoCompraItemDbg) throws Exception {

		PedidoCompraItemAnexoObc arqCotacao = this.getArqCotacao(pedidoCompraItemDbg.getCOM_PROPED_SDCV_OBC());
		if (arqCotacao != null) {
			CotacaoCompraDbg cotacaoCompraDbg = pedidoCompraItemDbg.getCotacaoCompraItemDbg().getCotacaoCompraDbg();

			cotacaoCompraDbg.setCOM_COTACA_ArqCotacao(arqCotacao.getAnexo());
			cotacaoCompraDbg.setCOM_COTACA_NomeArqCotacao(arqCotacao.getNomeArquivo());
			new CotacaoCompraDbgDAO(this.conexaoDbGint).updAnexo(cotacaoCompraDbg);
		}
	}

	private PedidoCompraItemAnexoObc getArqCotacao(String numSDCV) throws Exception {

		List<PedidoCompraItemAnexoObc> anexosSDCV = new PedidoCompraObcBO().carregarAnexos(numSDCV);

		for (PedidoCompraItemAnexoObc pedidoCompraAnexoObc : anexosSDCV) {
			if (pedidoCompraAnexoObc.getNomeArquivo() != null && pedidoCompraAnexoObc.getAnexo() != null
					&& pedidoCompraAnexoObc.getAnexo().length > 0
					&& pedidoCompraAnexoObc.getNomeArquivo().toUpperCase().endsWith(".PDF")) {
				return pedidoCompraAnexoObc;
			}
		}

		return null;
	}
}

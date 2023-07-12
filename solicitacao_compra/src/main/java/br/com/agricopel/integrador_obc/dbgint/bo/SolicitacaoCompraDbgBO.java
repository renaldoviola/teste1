package br.com.agricopel.integrador_obc.dbgint.bo;

import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.integrador_obc.dbgint.dao.FilialEmpresaDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.ProdutoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.SolicitacaoCompraDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.SolicitacaoCompraFornDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.SolicitacaoCompraItemDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.SolicitacaoRatCCustoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraDbg;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraItemDbg;

public class SolicitacaoCompraDbgBO {

	public List<SolicitacaoCompraDbg> getSolicitacoes() throws Exception {

		try (ConexaoDbGint conexaoDbGint = new ConexaoDbGint()) {
			List<SolicitacaoCompraDbg> solicitacaoCompraDbgs = new SolicitacaoCompraDbgDAO(conexaoDbGint)
					.getSolicitacoes();

			for (SolicitacaoCompraDbg solicitacaoCompraDbg : solicitacaoCompraDbgs) {
				carregarDados(conexaoDbGint, solicitacaoCompraDbg);
			}

			return solicitacaoCompraDbgs;
		}
	}

	public void confirmarGeracaoSDCVs(SolicitacaoCompraDbg solicitacaoCompraDbg) throws Exception {

		 if (solicitacaoCompraDbg.getItens().stream().anyMatch(item -> item.getSdcvObc().getRetornoProc().getSdc_codigo() > 0)) {
			try (ConexaoDbGint conexaoDbGint = new ConexaoDbGint()) {
				try {
					conexaoDbGint.abrirTransacao();

					new SolicitacaoCompraDbgDAO(conexaoDbGint).confirmarGeracaoSDCV(solicitacaoCompraDbg);

					SolicitacaoCompraItemDbgDAO solicitacaoCompraItemDbgDAO = new SolicitacaoCompraItemDbgDAO(
							conexaoDbGint);

					for (SolicitacaoCompraItemDbg solicitacaoCompraItemDbg : solicitacaoCompraDbg.getItens()) {
						if (solicitacaoCompraItemDbg.getSdcvObc().getRetornoProc().getSdc_codigo() > 0) {

							solicitacaoCompraItemDbg.setCOM_PROSOL_SDCV_OBC(
									solicitacaoCompraItemDbg.getSdcvObc().getRetornoProc().getSdc_codigo().toString());
							solicitacaoCompraItemDbgDAO.confirmarGeracaoSDCV(solicitacaoCompraItemDbg);
						}
					}

					new CotacaoCompraDbgBO(conexaoDbGint).gerarCotacao(solicitacaoCompraDbg);

					conexaoDbGint.fecharTransacao(Boolean.TRUE);
				} catch (Exception e) {
					conexaoDbGint.fecharTransacao(Boolean.FALSE);
					throw e;
				}
			}
		}
	}

	private void carregarDados(ConexaoDbGint conexaoDbGint, SolicitacaoCompraDbg solicitacaoCompraDbg)
			throws Exception {

		solicitacaoCompraDbg.setFilialEmpresa(new FilialEmpresaDbgDAO(conexaoDbGint).getByCodigo(
				solicitacaoCompraDbg.getSTG_GEN_TABEMP_Codigo(), solicitacaoCompraDbg.getSTG_GEN_TABFIL_Codigo()));

		solicitacaoCompraDbg.setItens(
				new SolicitacaoCompraItemDbgDAO(conexaoDbGint).getItens(solicitacaoCompraDbg.getSTG_GEN_TABEMP_Codigo(),
						solicitacaoCompraDbg.getSTG_GEN_TABFIL_Codigo(), solicitacaoCompraDbg.getCOM_SOLICI_Numero()));

		SolicitacaoRatCCustoDbgDAO rateioDbgDAO = new SolicitacaoRatCCustoDbgDAO(conexaoDbGint);
		ProdutoDbgDAO produtoDbgDAO = new ProdutoDbgDAO(conexaoDbGint);

		if (solicitacaoCompraDbg.getCOM_SOLICI_Gerada() != null && solicitacaoCompraDbg.getCOM_SOLICI_Gerada().equals("O")) {
			solicitacaoCompraDbg.setFornecedor(new SolicitacaoCompraFornDbgDAO(conexaoDbGint).getBySolicitacao(
					solicitacaoCompraDbg.getSTG_GEN_TABEMP_Codigo(), solicitacaoCompraDbg.getSTG_GEN_TABFIL_Codigo(),
					solicitacaoCompraDbg.getCOM_SOLICI_Numero()));
		}

		for (SolicitacaoCompraItemDbg solicitacaoCompraItemDbg : solicitacaoCompraDbg.getItens()) {
			solicitacaoCompraItemDbg
					.setRateios(rateioDbgDAO.getRateios(solicitacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo(),
							solicitacaoCompraItemDbg.getSTG_GEN_TABFIL_Codigo(),
							solicitacaoCompraItemDbg.getCOM_SOLICI_Numero(),
							solicitacaoCompraItemDbg.getCOM_PROSOL_Sequencia()));

			solicitacaoCompraItemDbg
					.setProdutoDbg(produtoDbgDAO.getByCodigo(solicitacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo(),
							solicitacaoCompraItemDbg.getEST_TABPRO_Codigo()));
		}
	}
}

package br.com.agricopel.integrador_obc.dbgint.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoCompraDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoCompraItemDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.CotacaoRatCCustoDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.dao.ParametrosDbgDAO;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraDbg;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraItemDbg;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoRatCCustoDbg;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraDbg;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraItemDbg;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoRatCCustoDbg;

public class CotacaoCompraDbgBO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoCompraDbgBO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void gerarCotacao(SolicitacaoCompraDbg solicitacaoCompraDbg) throws Exception {

		ParametrosDbgDAO parametrosDbgDAO = new ParametrosDbgDAO(this.conexaoDbGint);

		CotacaoCompraDbg cotacaoCompraDbg = this.getCotacao(solicitacaoCompraDbg);

		cotacaoCompraDbg.setCOM_COTACA_Numero(parametrosDbgDAO.getUltCodigoCotacaoCompra(
				cotacaoCompraDbg.getSTG_GEN_TABEMP_Codigo(), cotacaoCompraDbg.getSTG_GEN_TABFIL_Codigo()) + 1);

		new CotacaoCompraDbgDAO(this.conexaoDbGint).inserir(cotacaoCompraDbg);

		CotacaoCompraItemDbgDAO cotacaoCompraItemDbgDAO = new CotacaoCompraItemDbgDAO(this.conexaoDbGint);
		CotacaoRatCCustoDbgDAO cotacaoRatCCustoDbgDAO = new CotacaoRatCCustoDbgDAO(this.conexaoDbGint);
		for (CotacaoCompraItemDbg cotacaoCompraItemDbg : cotacaoCompraDbg.getItens()) {

			cotacaoCompraItemDbg.setCOM_COTACA_Numero(cotacaoCompraDbg.getCOM_COTACA_Numero());
			cotacaoCompraItemDbgDAO.inserir(cotacaoCompraItemDbg);

			for (CotacaoRatCCustoDbg cotacaoRatCCustoDbg : cotacaoCompraItemDbg.getRatCCusto()) {
				cotacaoRatCCustoDbg.setCOM_COTACA_Numero(cotacaoCompraDbg.getCOM_COTACA_Numero());
				cotacaoRatCCustoDbgDAO.inserir(cotacaoRatCCustoDbg);
			}
		}

		parametrosDbgDAO.setUltCodigoCotacaoCompra(cotacaoCompraDbg.getSTG_GEN_TABEMP_Codigo(),
				cotacaoCompraDbg.getSTG_GEN_TABFIL_Codigo(), cotacaoCompraDbg.getCOM_COTACA_Numero());
	}

	private CotacaoCompraDbg getCotacao(SolicitacaoCompraDbg solicitacaoCompraDbg) throws Exception {

		DateUtil dateUtil = new DateUtil();
		CotacaoCompraDbg cotacaoCompraDbg = new CotacaoCompraDbg();

		cotacaoCompraDbg.setSTG_GEN_TABEMP_Codigo(solicitacaoCompraDbg.getSTG_GEN_TABEMP_Codigo());
		cotacaoCompraDbg.setSTG_GEN_TABFIL_Codigo(solicitacaoCompraDbg.getSTG_GEN_TABFIL_Codigo());
		cotacaoCompraDbg.setCOM_COTACA_Numero(Long.valueOf(0));
		cotacaoCompraDbg.setCOM_COTACA_Emissao(LocalDate.now());
		cotacaoCompraDbg.setCOM_TABCOM_Codigo(9); // LEONARDO COMPRAS//Rodrigo gonçalves
		cotacaoCompraDbg.setGEN_TABUSU_Login("TI.AGRICOPEL");
		cotacaoCompraDbg.setCOM_COTACA_Prioridade(solicitacaoCompraDbg.getCOM_SOLICI_Prioridade());
		cotacaoCompraDbg.setCOM_COTACA_Situacao("P"); // Pendente
		cotacaoCompraDbg.setCOM_COTACA_Observacao(solicitacaoCompraDbg.getCOM_SOLICI_Observacao());
		cotacaoCompraDbg.setCOM_COTACA_Created(dateUtil.agora());
		cotacaoCompraDbg.setCOM_COTACA_Updated(dateUtil.agora());
		cotacaoCompraDbg.setCOM_COTACA_Tipo(solicitacaoCompraDbg.getCOM_SOLICI_Tipo());
//		cotacaoCompraDbg.setCOM_COTACA_Critica();
//		cotacaoCompraDbg.setCOM_COTACA_ValidadeFinal();
//		cotacaoCompraDbg.setCOM_COTACA_ValidadeInicial();
//		cotacaoCompraDbg.setSTG_COM_VLDCOT_Codigo();
//		cotacaoCompraDbg.setCOM_COTACA_EmailsNEnviados();
//		cotacaoCompraDbg.setCOM_COTACA_EmailsEnviados();
//		cotacaoCompraDbg.setCOM_COTACA_EmailsTotal();
//		cotacaoCompraDbg.setCOM_COTACA_ArqCotacao();
//		cotacaoCompraDbg.setCOM_COTACA_NomeArqCotacao("");
		cotacaoCompraDbg.setCOM_COTACA_EncCotacao(1);
		cotacaoCompraDbg.setCOM_COTACA_DataHoraAprovacao(dateUtil.agora());
		cotacaoCompraDbg.setCOM_COTACA_Aprovacao("S");
		cotacaoCompraDbg.setSTG_GEN_TABUSU_CotApr_Login(cotacaoCompraDbg.getGEN_TABUSU_Login());
		cotacaoCompraDbg.setCOM_COTACA_IDOSTerceiro(solicitacaoCompraDbg.getCOM_SOLICI_IDOSTerceiro());

		cotacaoCompraDbg.setCOM_COTACA_OSGarantia(0);
		cotacaoCompraDbg.setCOM_COTACA_DHIntOBC(dateUtil.agora());

		cotacaoCompraDbg.setItens(this.getItensCotacao(cotacaoCompraDbg, solicitacaoCompraDbg));

		cotacaoCompraDbg.setCOM_COTACA_SeqProduto(cotacaoCompraDbg.getItens().size() + 2);

		return cotacaoCompraDbg;
	}

	private List<CotacaoCompraItemDbg> getItensCotacao(CotacaoCompraDbg cotacaoCompraDbg,
			SolicitacaoCompraDbg solicitacaoCompraDbg) throws Exception {

		List<CotacaoCompraItemDbg> cotacaoCompraItens = new ArrayList<CotacaoCompraItemDbg>();

		for (SolicitacaoCompraItemDbg solicitacaoCompraItemDbg : solicitacaoCompraDbg.getItens()) {

			if (solicitacaoCompraItemDbg.getCOM_PROSOL_SDCV_OBC() != null && !solicitacaoCompraItemDbg.getCOM_PROSOL_SDCV_OBC().isEmpty()) {
				CotacaoCompraItemDbg cotacaoCompraItemDbg = new CotacaoCompraItemDbg();

				cotacaoCompraItemDbg.setSTG_GEN_TABEMP_Codigo(solicitacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo());
				cotacaoCompraItemDbg.setSTG_GEN_TABFIL_Codigo(solicitacaoCompraItemDbg.getSTG_GEN_TABFIL_Codigo());
				cotacaoCompraItemDbg.setCOM_COTACA_Numero(Long.valueOf(0));
				cotacaoCompraItemDbg.setCOM_PROCOT_Sequencia(cotacaoCompraItens.size() + 1);
				cotacaoCompraItemDbg.setCOM_PROCOT_Quantidade(solicitacaoCompraItemDbg.getCOM_PROSOL_Quantidade());
				cotacaoCompraItemDbg.setCOM_PROCOT_Observacao(solicitacaoCompraItemDbg.getCOM_PROSOL_Observacao());
				cotacaoCompraItemDbg.setCOM_PROCOT_Situacao("P");
				cotacaoCompraItemDbg.setCOM_PROCOT_Created(cotacaoCompraDbg.getCOM_COTACA_Created());
				cotacaoCompraItemDbg.setCOM_PROCOT_Updated(cotacaoCompraDbg.getCOM_COTACA_Updated());
				cotacaoCompraItemDbg.setCOM_SOLICI_Numero(solicitacaoCompraDbg.getCOM_SOLICI_Numero());
				cotacaoCompraItemDbg.setCOM_PROSOL_Sequencia(solicitacaoCompraItemDbg.getCOM_PROSOL_Sequencia());
				cotacaoCompraItemDbg.setSTG_EST_TABPRO_Cot_Codigo(solicitacaoCompraItemDbg.getEST_TABPRO_Codigo());
				cotacaoCompraItemDbg.setCOM_PROCOT_Valor(Double.valueOf(0));

				if (solicitacaoCompraItemDbg.getRateios().isEmpty()) {
					cotacaoCompraItemDbg.setSTG_FRT_TABCAR_Cot_Codigo(solicitacaoCompraItemDbg.getFRT_TABCAR_Codigo());
					cotacaoCompraItemDbg.setSTG_GEN_TABCEN_Cot_Codigo(solicitacaoCompraItemDbg.getGEN_TABCEN_Codigo());
				} else {

					for (SolicitacaoRatCCustoDbg ratSolicitacao : solicitacaoCompraItemDbg.getRateios()) {
						CotacaoRatCCustoDbg ratCotacao = new CotacaoRatCCustoDbg();

						ratCotacao.setSTG_GEN_TABEMP_Codigo(ratSolicitacao.getSTG_GEN_TABEMP_Codigo());
						ratCotacao.setSTG_GEN_TABFIL_Codigo(ratSolicitacao.getSTG_GEN_TABFIL_Codigo());
						ratCotacao.setCOM_COTACA_Numero(Long.valueOf(0));
						ratCotacao.setCOM_PROCOT_Sequencia(cotacaoCompraItemDbg.getCOM_PROCOT_Sequencia());
						ratCotacao.setCOM_CONCOT_Sequencia(ratSolicitacao.getCOM_CONSOL_Sequencia());
						ratCotacao.setCOM_CONCOT_Created(ratSolicitacao.getCOM_CONSOL_Created());
						ratCotacao.setCOM_CONCOT_Updated(ratSolicitacao.getCOM_CONSOL_Updated());
						ratCotacao.setCOM_CONCOT_Quantidade(ratSolicitacao.getCOM_CONSOL_Quantidade());
						ratCotacao.setSTG_FRT_TABCAR_Coc_Codigo(ratSolicitacao.getSTG_FRT_TABCAR_Cos_Codigo());
						ratCotacao.setSTG_GEN_TABCEN_Coc_Codigo(ratSolicitacao.getSTG_GEN_TABCEN_Cos_Codigo());

						cotacaoCompraItemDbg.getRatCCusto().add(ratCotacao);
					}
				}

				cotacaoCompraItemDbg.setCOM_PROCOT_Entrega(null);
				cotacaoCompraItemDbg.setCOM_PROCOT_PrefFabricanteDesc("");
				cotacaoCompraItemDbg.setCOM_PROCOT_PrefFabricante(0);
				cotacaoCompraItemDbg.setCOM_PROCOT_PrefCondPgtoDesc("");
				cotacaoCompraItemDbg.setCOM_PROCOT_PrefCondPgto(0);
				cotacaoCompraItemDbg.setCOM_PROCOT_SDCV_OBC(solicitacaoCompraItemDbg.getCOM_PROSOL_SDCV_OBC());
				cotacaoCompraItemDbg.setCOM_PROCOT_TIPO_OBC(solicitacaoCompraItemDbg.getCOM_PROSOL_TIPO_OBC());

				cotacaoCompraItens.add(cotacaoCompraItemDbg);
			}
		}

		return cotacaoCompraItens;
	}

}

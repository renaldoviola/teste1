package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraDbg;

public class SolicitacaoCompraDbgDAO {

	ConexaoDbGint conexaoDbGint;

	public SolicitacaoCompraDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public SolicitacaoCompraDbg getSolicitacao(Integer STG_GEN_TABEMP_Codigo, Integer STG_GEN_TABFIL_Codigo,
			Long COM_SOLICI_Numero) throws Exception {

		DateUtil dateUtil = new DateUtil();

		return new SqlReadUtil<SolicitacaoCompraDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
				sql.append(" select ");
				sql.append("     STG_GEN_TABEMP_Codigo, ");
				sql.append("     STG_GEN_TABFIL_Codigo, ");
				sql.append("     COM_SOLICI_Numero, ");
				sql.append("     COM_SOLICI_Emissao, ");
				sql.append("     GEN_TABUSU_Login, ");
				sql.append("     COM_SOLICI_Prioridade, ");
				sql.append("     COM_SOLICI_Situacao, ");
				sql.append("     COM_SOLICI_Observacao, ");
				sql.append("     COM_SOLICI_Created, ");
				sql.append("     COM_SOLICI_Updated, ");
				sql.append("     COM_SOLICI_SeqProduto, ");
				sql.append("     COM_SOLICI_Tipo, ");
				sql.append("     COM_SOLICI_Critica, ");
				sql.append("     COM_SOLICI_Gerada, ");
				sql.append("     COM_SOLICI_DtHoraCancAtend, ");
				sql.append("     STG_GEN_TABUSU_CAS_Login, ");
				sql.append("     COM_SOLICI_JustCancAtend, ");
				sql.append("     COM_SOLICI_IDOSTerceiro, ");
				sql.append("     COM_SOLICI_OSGarantia, ");
				sql.append("     COM_SOLICI_DHIntOBC, ");
				sql.append("     COM_SOLICI_NrSolOBC, ");
				sql.append("     COM_SOLICI_MotivoCancelamento ");
				sql.append(" from COM_SOLICI ");
				sql.append(" where STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" and STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" and COM_SOLICI_Numero = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, STG_GEN_TABEMP_Codigo);
				preparedStatement.setInt(2, STG_GEN_TABFIL_Codigo);
				preparedStatement.setLong(3, COM_SOLICI_Numero);
			}

			@Override
			public SolicitacaoCompraDbg criarModel(ResultSet resultSet) throws Exception {

				SolicitacaoCompraDbg solicitacaoCompraDbg = new SolicitacaoCompraDbg();

				solicitacaoCompraDbg.setSTG_GEN_TABEMP_Codigo(resultSet.getInt("STG_GEN_TABEMP_Codigo"));
				solicitacaoCompraDbg.setSTG_GEN_TABFIL_Codigo(resultSet.getInt("STG_GEN_TABFIL_Codigo"));
				solicitacaoCompraDbg.setCOM_SOLICI_Numero(resultSet.getLong("COM_SOLICI_Numero"));
				solicitacaoCompraDbg.setCOM_SOLICI_Emissao(resultSet.getDate("COM_SOLICI_Emissao").toLocalDate());
				solicitacaoCompraDbg.setGEN_TABUSU_Login(resultSet.getString("GEN_TABUSU_Login"));
				solicitacaoCompraDbg.setCOM_SOLICI_Prioridade(resultSet.getString("COM_SOLICI_Prioridade"));
				solicitacaoCompraDbg.setCOM_SOLICI_Situacao(resultSet.getString("COM_SOLICI_Situacao"));
				solicitacaoCompraDbg.setCOM_SOLICI_Observacao(resultSet.getString("COM_SOLICI_Observacao"));
				solicitacaoCompraDbg
						.setCOM_SOLICI_Created(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_SOLICI_Created")));
				solicitacaoCompraDbg
						.setCOM_SOLICI_Updated(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_SOLICI_Updated")));
				solicitacaoCompraDbg.setCOM_SOLICI_SeqProduto(resultSet.getInt("COM_SOLICI_SeqProduto"));
				solicitacaoCompraDbg.setCOM_SOLICI_Tipo(resultSet.getString("COM_SOLICI_Tipo"));
				solicitacaoCompraDbg.setCOM_SOLICI_Critica(resultSet.getInt("COM_SOLICI_Critica"));
				solicitacaoCompraDbg.setCOM_SOLICI_Gerada(resultSet.getString("COM_SOLICI_Gerada"));
				solicitacaoCompraDbg.setCOM_SOLICI_DtHoraCancAtend(
						dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_SOLICI_DtHoraCancAtend")));
				solicitacaoCompraDbg.setSTG_GEN_TABUSU_CAS_Login(resultSet.getString("STG_GEN_TABUSU_CAS_Login"));
				solicitacaoCompraDbg.setCOM_SOLICI_JustCancAtend(resultSet.getString("COM_SOLICI_JustCancAtend"));
				solicitacaoCompraDbg.setCOM_SOLICI_IDOSTerceiro(resultSet.getLong("COM_SOLICI_IDOSTerceiro"));
				solicitacaoCompraDbg.setCOM_SOLICI_OSGarantia(resultSet.getInt("COM_SOLICI_OSGarantia"));
				solicitacaoCompraDbg.setCOM_SOLICI_DHIntOBC(
						dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_SOLICI_DHIntOBC")));
				solicitacaoCompraDbg.setCOM_SOLICI_NrSolOBC(resultSet.getString("COM_SOLICI_NrSolOBC"));
				solicitacaoCompraDbg
						.setCOM_SOLICI_MotivoCancelamento(resultSet.getInt("COM_SOLICI_MotivoCancelamento"));

				return solicitacaoCompraDbg;
			}

		}.getModel();
	}

}

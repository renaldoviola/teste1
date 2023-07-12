package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraItemDbg;

public class SolicitacaoCompraItemDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public SolicitacaoCompraItemDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public List<SolicitacaoCompraItemDbg> getItens(Integer STG_GEN_TABEMP_Codigo, Integer STG_GEN_TABFIL_Codigo,
			Long COM_SOLICI_Numero) throws Exception {

		DateUtil dateUtil = new DateUtil();
		
		return new SqlReadUtil<SolicitacaoCompraItemDbg>(conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    STG_GEN_TABEMP_Codigo, ");
				sql.append("    STG_GEN_TABFIL_Codigo, ");
				sql.append("    COM_SOLICI_Numero, ");
				sql.append("    COM_PROSOL_Sequencia, ");
				sql.append("    EST_TABPRO_Codigo, ");
				sql.append("    COM_PROSOL_Quantidade, ");
				sql.append("    COM_PROSOL_Observacao, ");
				sql.append("    COM_PROSOL_Created, ");
				sql.append("    COM_PROSOL_Updated, ");
				sql.append("    COM_PROSOL_Situacao, ");
				sql.append("    COM_PROSOL_Valor, ");
				sql.append("    FRT_TABCAR_Codigo, ");
				sql.append("    GEN_TABCEN_Codigo, ");
				sql.append("    COM_PROSOL_Cancelado, ");
				sql.append("    COM_PROSOL_NumSolicitacao, ");
				sql.append("    COM_PROSOL_SeqSolicitacao, ");
				sql.append("    COM_PROSOL_Log, ");
				sql.append("    COM_PROSOL_Justificativa, ");
				sql.append("    COM_PROSOL_DataGarantia, ");
				sql.append("    COM_PROSOL_OSOrigem, ");
				sql.append("    COM_PROSOL_SDCV_OBC, ");
				sql.append("    COM_PROSOL_TIPO_OBC ");
				sql.append(" from COM_PROSOL ");
				sql.append(" where STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" and   STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" and   COM_SOLICI_Numero = ? ");
				sql.append(" order by COM_PROSOL_Sequencia ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, STG_GEN_TABEMP_Codigo);
				preparedStatement.setInt(2, STG_GEN_TABFIL_Codigo);
				preparedStatement.setLong(3, COM_SOLICI_Numero);
			}

			@Override
			public SolicitacaoCompraItemDbg criarModel(ResultSet resultSet) throws Exception {

				SolicitacaoCompraItemDbg solicitacaoCompraItemDbg = new SolicitacaoCompraItemDbg();

				solicitacaoCompraItemDbg.setSTG_GEN_TABEMP_Codigo(resultSet.getInt("STG_GEN_TABEMP_Codigo"));
				solicitacaoCompraItemDbg.setSTG_GEN_TABFIL_Codigo(resultSet.getInt("STG_GEN_TABFIL_Codigo"));
				solicitacaoCompraItemDbg.setCOM_SOLICI_Numero(resultSet.getLong("COM_SOLICI_Numero"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Sequencia(resultSet.getInt("COM_PROSOL_Sequencia"));
				solicitacaoCompraItemDbg.setEST_TABPRO_Codigo(resultSet.getString("EST_TABPRO_Codigo"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Quantidade(resultSet.getDouble("COM_PROSOL_Quantidade"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Observacao(resultSet.getString("COM_PROSOL_Observacao"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Created(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_PROSOL_Created")));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Updated(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_PROSOL_Updated")));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Situacao(resultSet.getString("COM_PROSOL_Situacao"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Valor(resultSet.getDouble("COM_PROSOL_Valor"));
				solicitacaoCompraItemDbg.setFRT_TABCAR_Codigo(resultSet.getString("FRT_TABCAR_Codigo"));
				solicitacaoCompraItemDbg.setGEN_TABCEN_Codigo(resultSet.getString("GEN_TABCEN_Codigo"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Cancelado(resultSet.getDouble("COM_PROSOL_Cancelado"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_NumSolicitacao(resultSet.getLong("COM_PROSOL_NumSolicitacao"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_SeqSolicitacao(resultSet.getInt("COM_PROSOL_SeqSolicitacao"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Log(resultSet.getString("COM_PROSOL_Log"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_Justificativa(resultSet.getString("COM_PROSOL_Justificativa"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_DataGarantia(dateUtil.toLocalDate(resultSet.getDate("COM_PROSOL_DataGarantia")));
				solicitacaoCompraItemDbg.setCOM_PROSOL_OSOrigem(resultSet.getLong("COM_PROSOL_OSOrigem"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_SDCV_OBC(resultSet.getString("COM_PROSOL_SDCV_OBC"));
				solicitacaoCompraItemDbg.setCOM_PROSOL_TIPO_OBC(resultSet.getString("COM_PROSOL_TIPO_OBC"));

				return solicitacaoCompraItemDbg;
			}

		}.getList();
	}

	public void confirmarGeracaoSDCV(SolicitacaoCompraItemDbg solicitacaoCompraItemDbg) throws Exception {

		new SqlWriteUtil(conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
				sql.append(" update COM_PROSOL set ");
				sql.append("    COM_PROSOL_Situacao = ?, ");
				sql.append("    COM_PROSOL_SDCV_OBC = ?, ");
				sql.append("    COM_PROSOL_TIPO_OBC = ? ");
				sql.append(" where STG_GEN_TABEMP_Codigo = ? ");
				sql.append("    and STG_GEN_TABFIL_Codigo = ? ");
				sql.append("    and COM_SOLICI_Numero = ? ");
				sql.append("    and COM_PROSOL_Sequencia = ? ");
				sql.append("    and EST_TABPRO_Codigo = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setString(1, "A");
				preparedStatement.setString(2, solicitacaoCompraItemDbg.getCOM_PROSOL_SDCV_OBC());
				preparedStatement.setString(3, "C");
				preparedStatement.setInt(4, solicitacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(5, solicitacaoCompraItemDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(6, solicitacaoCompraItemDbg.getCOM_SOLICI_Numero());
				preparedStatement.setInt(7, solicitacaoCompraItemDbg.getCOM_PROSOL_Sequencia());
				preparedStatement.setString(8, solicitacaoCompraItemDbg.getEST_TABPRO_Codigo());
			}
		}.execUpd();;
	}

}

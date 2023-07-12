package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoRatCCustoDbg;

public class SolicitacaoRatCCustoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public SolicitacaoRatCCustoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(SolicitacaoRatCCustoDbg solicitacaoRatCCustoDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append("  INSERT INTO COM_CONCOT ( ");
				sql.append("     STG_GEN_TABEMP_Codigo, ");
				sql.append("     STG_GEN_TABFIL_Codigo, ");
				sql.append("     COM_SOLICI_Numero, ");
				sql.append("     COM_PROSOL_Sequencia, ");
				sql.append("     COM_CONSOL_Sequencia, ");
				sql.append("     COM_CONSOL_Created, ");
				sql.append("     COM_CONSOL_Updated, ");
				sql.append("     COM_CONSOL_Quantidade, ");
				sql.append("     STG_FRT_TABCAR_Cos_Codigo, ");
				sql.append("     STG_GEN_TABCEN_Cos_Codigo, ");
				sql.append("     COM_CONSOL_Cancelado, ");
				sql.append("     COM_CONSOL_Log ");
				sql.append("  VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setInt(count++, solicitacaoRatCCustoDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, solicitacaoRatCCustoDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, solicitacaoRatCCustoDbg.getCOM_SOLICI_Numero());
				preparedStatement.setInt(count++, solicitacaoRatCCustoDbg.getCOM_PROSOL_Sequencia());
				preparedStatement.setInt(count++, solicitacaoRatCCustoDbg.getCOM_CONSOL_Sequencia());
				preparedStatement.setTimestamp(count++, Timestamp.valueOf(solicitacaoRatCCustoDbg.getCOM_CONSOL_Created()));
				preparedStatement.setTimestamp(count++, Timestamp.valueOf(solicitacaoRatCCustoDbg.getCOM_CONSOL_Updated()));
				preparedStatement.setDouble(count++, solicitacaoRatCCustoDbg.getCOM_CONSOL_Quantidade());
				preparedStatement.setString(count++, solicitacaoRatCCustoDbg.getSTG_FRT_TABCAR_Cos_Codigo());
				preparedStatement.setString(count++, solicitacaoRatCCustoDbg.getSTG_GEN_TABCEN_Cos_Codigo());
				preparedStatement.setDouble(count++, solicitacaoRatCCustoDbg.getCOM_CONSOL_Cancelado());
				preparedStatement.setString(count++, solicitacaoRatCCustoDbg.getCOM_CONSOL_Log());
			}

		}.execUpd();

	}
	
	public List<SolicitacaoRatCCustoDbg> getRateios(Integer STG_GEN_TABEMP_Codigo, Integer STG_GEN_TABFIL_Codigo,
			Long COM_SOLICI_Numero, Integer COM_PROSOL_Sequencia) throws Exception {

		DateUtil dateUtil = new DateUtil();
		
		return new SqlReadUtil<SolicitacaoRatCCustoDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    STG_GEN_TABEMP_Codigo, ");
				sql.append("    STG_GEN_TABFIL_Codigo, ");
				sql.append("    COM_SOLICI_Numero, ");
				sql.append("    COM_PROSOL_Sequencia, ");
				sql.append("    COM_CONSOL_Sequencia, ");
				sql.append("    COM_CONSOL_Created, ");
				sql.append("    COM_CONSOL_Updated, ");
				sql.append("    COM_CONSOL_Quantidade, ");
				sql.append("    STG_FRT_TABCAR_Cos_Codigo, ");
				sql.append("    STG_GEN_TABCEN_Cos_Codigo, ");
				sql.append("    COM_CONSOL_Cancelado, ");
				sql.append("    COM_CONSOL_Log ");
				sql.append(" from COM_CONSOL ");
				sql.append(" where STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" and   STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" and   COM_SOLICI_Numero = ? ");
				sql.append(" and   COM_PROSOL_Sequencia = ? ");
			}

			public void setParameters(java.sql.PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, STG_GEN_TABEMP_Codigo);
				preparedStatement.setInt(2, STG_GEN_TABFIL_Codigo);
				preparedStatement.setDouble(3, COM_SOLICI_Numero);
				preparedStatement.setInt(4, COM_PROSOL_Sequencia);
			};

			@Override
			public SolicitacaoRatCCustoDbg criarModel(ResultSet resultSet) throws Exception {

				SolicitacaoRatCCustoDbg solicitacaoRatCCustoDbg = new SolicitacaoRatCCustoDbg();

				solicitacaoRatCCustoDbg.setSTG_GEN_TABEMP_Codigo(resultSet.getInt("STG_GEN_TABEMP_Codigo"));
				solicitacaoRatCCustoDbg.setSTG_GEN_TABFIL_Codigo(resultSet.getInt("STG_GEN_TABFIL_Codigo"));
				solicitacaoRatCCustoDbg.setCOM_SOLICI_Numero(resultSet.getLong("COM_SOLICI_Numero"));
				solicitacaoRatCCustoDbg.setCOM_PROSOL_Sequencia(resultSet.getInt("COM_PROSOL_Sequencia"));
				solicitacaoRatCCustoDbg.setCOM_CONSOL_Sequencia(resultSet.getInt("COM_CONSOL_Sequencia"));
				solicitacaoRatCCustoDbg.setCOM_CONSOL_Created(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_CONSOL_Created")));
				solicitacaoRatCCustoDbg.setCOM_CONSOL_Updated(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_CONSOL_Updated")));
				solicitacaoRatCCustoDbg.setCOM_CONSOL_Quantidade(resultSet.getDouble("COM_CONSOL_Quantidade"));
				solicitacaoRatCCustoDbg.setSTG_FRT_TABCAR_Cos_Codigo(resultSet.getString("STG_FRT_TABCAR_Cos_Codigo"));
				solicitacaoRatCCustoDbg.setSTG_GEN_TABCEN_Cos_Codigo(resultSet.getString("STG_GEN_TABCEN_Cos_Codigo"));
				solicitacaoRatCCustoDbg.setCOM_CONSOL_Cancelado(resultSet.getDouble("COM_CONSOL_Cancelado"));
				solicitacaoRatCCustoDbg.setCOM_CONSOL_Log(resultSet.getString("COM_CONSOL_Log"));

				return solicitacaoRatCCustoDbg;
			}
		}.getList();
	}

}

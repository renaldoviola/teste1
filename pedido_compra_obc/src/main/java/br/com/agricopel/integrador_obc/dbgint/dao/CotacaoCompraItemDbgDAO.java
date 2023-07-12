package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraItemDbg;

public class CotacaoCompraItemDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoCompraItemDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public CotacaoCompraItemDbg getCotacaoSdcv(Integer stg_GEN_TABEMP_Codigo, Integer stg_GEN_TABFIL_Codigo,
			String com_PROPED_SDCV_OBC) throws Exception {

		return new SqlReadUtil<CotacaoCompraItemDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" SELECT STG_GEN_TABEMP_Codigo, ");
				sql.append("     STG_GEN_TABFIL_Codigo, ");
				sql.append("     COM_COTACA_Numero, ");
				sql.append("     COM_PROCOT_Sequencia, ");
				sql.append("     COM_PROCOT_Quantidade, ");
				sql.append("     COM_PROCOT_Observacao, ");
				sql.append("     COM_PROCOT_Situacao, ");
				sql.append("     COM_PROCOT_Created, ");
				sql.append("     COM_PROCOT_Updated, ");
				sql.append("     COM_SOLICI_Numero, ");
				sql.append("     COM_PROSOL_Sequencia, ");
				sql.append("     STG_EST_TABPRO_Cot_Codigo, ");
				sql.append("     COM_PROCOT_Valor, ");
				sql.append("     STG_FRT_TABCAR_Cot_Codigo, ");
				sql.append("     STG_GEN_TABCEN_Cot_Codigo, ");
				sql.append("     COM_PROCOT_Entrega, ");
				sql.append("     COM_PROCOT_PrefFabricanteDesc, ");
				sql.append("     COM_PROCOT_PrefFabricante, ");
				sql.append("     COM_PROCOT_PrefCondPgtoDesc, ");
				sql.append("     COM_PROCOT_PrefCondPgto, ");
				sql.append("     COM_PROCOT_SDCV_OBC, ");
				sql.append("     COM_PROCOT_TIPO_OBC ");
				sql.append(" FROM COM_PROCOT ");
				sql.append(" WHERE STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" AND   STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" AND   COM_PROCOT_SDCV_OBC = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, stg_GEN_TABEMP_Codigo);
				preparedStatement.setInt(2, stg_GEN_TABFIL_Codigo);
				preparedStatement.setString(3, com_PROPED_SDCV_OBC);
			};

			@Override
			public CotacaoCompraItemDbg criarModel(ResultSet resultSet) throws Exception {

				DateUtil dateUtil = new DateUtil();
				CotacaoCompraItemDbg cotacaoCompraItemDbg = new CotacaoCompraItemDbg();

				cotacaoCompraItemDbg.setSTG_GEN_TABEMP_Codigo(resultSet.getInt("STG_GEN_TABEMP_Codigo"));
				cotacaoCompraItemDbg.setSTG_GEN_TABFIL_Codigo(resultSet.getInt("STG_GEN_TABFIL_Codigo"));
				cotacaoCompraItemDbg.setCOM_COTACA_Numero(resultSet.getLong("COM_COTACA_Numero"));
				cotacaoCompraItemDbg.setCOM_PROCOT_Sequencia(resultSet.getInt("COM_PROCOT_Sequencia"));
				cotacaoCompraItemDbg.setCOM_PROCOT_Quantidade(resultSet.getDouble("COM_PROCOT_Quantidade"));
				cotacaoCompraItemDbg.setCOM_PROCOT_Observacao(resultSet.getString("COM_PROCOT_Observacao"));
				cotacaoCompraItemDbg.setCOM_PROCOT_Situacao(resultSet.getString("COM_PROCOT_Situacao"));
				cotacaoCompraItemDbg
						.setCOM_PROCOT_Created(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_PROCOT_Created")));
				cotacaoCompraItemDbg
						.setCOM_PROCOT_Updated(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_PROCOT_Updated")));
				cotacaoCompraItemDbg.setCOM_SOLICI_Numero(resultSet.getLong("COM_SOLICI_Numero"));
				cotacaoCompraItemDbg.setCOM_PROSOL_Sequencia(resultSet.getInt("COM_PROSOL_Sequencia"));
				cotacaoCompraItemDbg.setSTG_EST_TABPRO_Cot_Codigo(resultSet.getString("STG_EST_TABPRO_Cot_Codigo"));
				cotacaoCompraItemDbg.setCOM_PROCOT_Valor(resultSet.getDouble("COM_PROCOT_Valor"));
				cotacaoCompraItemDbg.setSTG_FRT_TABCAR_Cot_Codigo(resultSet.getString("STG_FRT_TABCAR_Cot_Codigo"));
				cotacaoCompraItemDbg.setSTG_GEN_TABCEN_Cot_Codigo(resultSet.getString("STG_GEN_TABCEN_Cot_Codigo"));
				cotacaoCompraItemDbg
						.setCOM_PROCOT_Entrega(dateUtil.toLocalDate(resultSet.getDate("COM_PROCOT_Entrega")));
				cotacaoCompraItemDbg
						.setCOM_PROCOT_PrefFabricanteDesc(resultSet.getString("COM_PROCOT_PrefFabricanteDesc"));
				cotacaoCompraItemDbg.setCOM_PROCOT_PrefFabricante(resultSet.getInt("COM_PROCOT_PrefFabricante"));
				cotacaoCompraItemDbg.setCOM_PROCOT_PrefCondPgtoDesc(resultSet.getString("COM_PROCOT_PrefCondPgtoDesc"));
				cotacaoCompraItemDbg.setCOM_PROCOT_PrefCondPgto(resultSet.getInt("COM_PROCOT_PrefCondPgto"));
				cotacaoCompraItemDbg.setCOM_PROCOT_SDCV_OBC(resultSet.getString("COM_PROCOT_SDCV_OBC"));
				cotacaoCompraItemDbg.setCOM_PROCOT_TIPO_OBC(resultSet.getString("COM_PROCOT_TIPO_OBC"));

				return cotacaoCompraItemDbg;
			}

		}.getModel();
	}

	public void updStatus(CotacaoCompraItemDbg cotacaoCompraItemDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" UPDATE COM_PROCOT set ");
				sql.append("   COM_PROCOT_Situacao = 'A' ");
				sql.append(" WHERE STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" AND   STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" AND   COM_COTACA_Numero = ? ");
				sql.append(" AND   COM_PROCOT_Sequencia = ? ");
				
				LogUtils.escreverLogInfo("cOTACAO COMPRA ITEM updStatus COTACAO " + sql.toString());
				

			};

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraItemDbg.getCOM_COTACA_Numero());
				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getCOM_PROCOT_Sequencia());
			};

		}.execUpd();
	}

}

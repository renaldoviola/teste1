package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoRatCCustoDbg;

public class CotacaoRatCCustoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoRatCCustoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(CotacaoRatCCustoDbg cotacaoRatCCustoDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
				
				sql.append("  INSERT INTO COM_CONCOT ( ");
				sql.append("     STG_GEN_TABEMP_Codigo, ");
				sql.append("     STG_GEN_TABFIL_Codigo, ");
				sql.append("     COM_COTACA_Numero, ");
				sql.append("     COM_PROCOT_Sequencia, ");
				sql.append("     COM_CONCOT_Sequencia, ");
				sql.append("     COM_CONCOT_Created, ");
				sql.append("     COM_CONCOT_Updated, ");
				sql.append("     COM_CONCOT_Quantidade, ");
				sql.append("     STG_FRT_TABCAR_Coc_Codigo, ");
				sql.append("     STG_GEN_TABCEN_Coc_Codigo) ");
				sql.append("  VALUES (?,?,?,?,?,?,?,?,?,?) ");
				LogUtils.escreverLogInfo("CotacaoRatCCustoDbgDAO COM_CONCOT  " + sql.toString());
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				
				int count = 1;
				
				preparedStatement.setInt(count++, cotacaoRatCCustoDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoRatCCustoDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, cotacaoRatCCustoDbg.getCOM_COTACA_Numero());
				preparedStatement.setInt(count++, cotacaoRatCCustoDbg.getCOM_PROCOT_Sequencia());
				preparedStatement.setInt(count++, cotacaoRatCCustoDbg.getCOM_CONCOT_Sequencia());
				preparedStatement.setTimestamp(count++, Timestamp.valueOf(cotacaoRatCCustoDbg.getCOM_CONCOT_Created()));
				preparedStatement.setTimestamp(count++, Timestamp.valueOf(cotacaoRatCCustoDbg.getCOM_CONCOT_Updated()));
				preparedStatement.setDouble(count++, cotacaoRatCCustoDbg.getCOM_CONCOT_Quantidade());
				preparedStatement.setString(count++, cotacaoRatCCustoDbg.getSTG_FRT_TABCAR_Coc_Codigo());
				preparedStatement.setString(count++, cotacaoRatCCustoDbg.getSTG_GEN_TABCEN_Coc_Codigo());
			}

		}.execUpd();

	}

}

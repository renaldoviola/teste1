package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraItemDbg;

public class CotacaoCompraItemDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoCompraItemDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(CotacaoCompraItemDbg cotacaoCompraItemDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append("  INSERT INTO COM_PROCOT ( ");
				sql.append("     STG_GEN_TABEMP_Codigo, ");
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
				sql.append("     COM_PROCOT_TIPO_OBC) ");
				sql.append("  VALUES ");
				sql.append("  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				LogUtils.escreverLogInfo("CotacaoCompraItemDbgDao 52  " + sql.toString());
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraItemDbg.getCOM_COTACA_Numero());
				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getCOM_PROCOT_Sequencia());
				preparedStatement.setDouble(count++, cotacaoCompraItemDbg.getCOM_PROCOT_Quantidade());
				preparedStatement.setString(count++, cotacaoCompraItemDbg.getCOM_PROCOT_Observacao());
				preparedStatement.setString(count++, cotacaoCompraItemDbg.getCOM_PROCOT_Situacao());
				preparedStatement.setTimestamp(count++,
						Timestamp.valueOf(cotacaoCompraItemDbg.getCOM_PROCOT_Created()));
				preparedStatement.setTimestamp(count++,
						Timestamp.valueOf(cotacaoCompraItemDbg.getCOM_PROCOT_Updated()));
				preparedStatement.setLong(count++, cotacaoCompraItemDbg.getCOM_SOLICI_Numero());
				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getCOM_PROSOL_Sequencia());
				preparedStatement.setString(count++, cotacaoCompraItemDbg.getSTG_EST_TABPRO_Cot_Codigo());
				preparedStatement.setDouble(count++, cotacaoCompraItemDbg.getCOM_PROCOT_Valor());
				preparedStatement.setString(count++, cotacaoCompraItemDbg.getSTG_FRT_TABCAR_Cot_Codigo());
				preparedStatement.setString(count++, cotacaoCompraItemDbg.getSTG_GEN_TABCEN_Cot_Codigo());

				if (cotacaoCompraItemDbg.getCOM_PROCOT_Entrega() != null) {
					preparedStatement.setDate(count++, Date.valueOf(cotacaoCompraItemDbg.getCOM_PROCOT_Entrega()));
				} else {
					preparedStatement.setObject(count++, null);
				}

				preparedStatement.setString(count++, cotacaoCompraItemDbg.getCOM_PROCOT_PrefFabricanteDesc());
				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getCOM_PROCOT_PrefFabricante());
				preparedStatement.setString(count++, cotacaoCompraItemDbg.getCOM_PROCOT_PrefCondPgtoDesc());
				preparedStatement.setInt(count++, cotacaoCompraItemDbg.getCOM_PROCOT_PrefCondPgto());
				preparedStatement.setString(count++, cotacaoCompraItemDbg.getCOM_PROCOT_SDCV_OBC());
				preparedStatement.setString(count++, cotacaoCompraItemDbg.getCOM_PROCOT_TIPO_OBC());
			}

		}.execUpd();

	}

}

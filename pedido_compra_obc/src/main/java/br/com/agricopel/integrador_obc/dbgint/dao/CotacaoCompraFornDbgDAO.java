package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraFornDbg;

public class CotacaoCompraFornDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoCompraFornDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(CotacaoCompraFornDbg cotacaoCompraFornDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append("  INSERT INTO COM_ENTCOT ( ");
				sql.append("     STG_GEN_TABEMP_Codigo, ");
				sql.append("     STG_GEN_TABFIL_Codigo, ");
				sql.append("     COM_COTACA_Numero, ");
				sql.append("     GEN_TABENT_Codigo, ");
				sql.append("     GEN_ENDENT_Codigo, ");
				sql.append("     COM_ENTCOT_DHEnvioEMail, ");
				sql.append("     COM_ENTCOT_Created, ");
				sql.append("     COM_ENTCOT_Updated) ");
				sql.append("  VALUES (?,?,?,?,?,?,?,?) ");
				LogUtils.escreverLogInfo("inserir COTACAO" + sql.toString());
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setInt(count++, cotacaoCompraFornDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoCompraFornDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraFornDbg.getCOM_COTACA_Numero());
				preparedStatement.setLong(count++, cotacaoCompraFornDbg.getGEN_TABENT_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraFornDbg.getGEN_ENDENT_Codigo());
				preparedStatement.setTimestamp(count++,
						Timestamp.valueOf(cotacaoCompraFornDbg.getCOM_ENTCOT_DHEnvioEMail()));
				preparedStatement.setTimestamp(count++,
						Timestamp.valueOf(cotacaoCompraFornDbg.getCOM_ENTCOT_Created()));
				preparedStatement.setTimestamp(count++,
						Timestamp.valueOf(cotacaoCompraFornDbg.getCOM_ENTCOT_Updated()));
			}

		}.execUpd();

	}

	public Boolean validarExistencia(CotacaoCompraFornDbg cotacaoCompraFornDbg) throws Exception {

		CotacaoCompraFornDbg cotacaoCompraFornVal = new SqlReadUtil<CotacaoCompraFornDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" SELECT COM_COTACA_Numero ");
				sql.append(" FROM COM_ENTCOT ");
				sql.append(" WHERE STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" AND   STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" AND   COM_COTACA_Numero = ? ");
				sql.append(" AND   GEN_TABENT_Codigo = ? ");
				sql.append(" AND   GEN_ENDENT_Codigo = ? ");
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setInt(count++, cotacaoCompraFornDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoCompraFornDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraFornDbg.getCOM_COTACA_Numero());
				preparedStatement.setLong(count++, cotacaoCompraFornDbg.getGEN_TABENT_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraFornDbg.getGEN_ENDENT_Codigo());
			};

			@Override
			public CotacaoCompraFornDbg criarModel(ResultSet resultSet) throws Exception {

				CotacaoCompraFornDbg cotacaoCompraFornDbgRet = new CotacaoCompraFornDbg();

				cotacaoCompraFornDbgRet.setCOM_COTACA_Numero(resultSet.getLong("COM_COTACA_Numero"));

				return cotacaoCompraFornDbgRet;
			}

		}.getModel();

		return (cotacaoCompraFornVal != null) && (cotacaoCompraFornVal.getCOM_COTACA_Numero() > 0);
	}

}

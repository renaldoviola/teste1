package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraDbg;

public class CotacaoCompraDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoCompraDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(CotacaoCompraDbg cotacaoCompraDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" INSERT INTO COM_COTACA ( ");
				sql.append("    STG_GEN_TABEMP_Codigo, ");
				sql.append("    STG_GEN_TABFIL_Codigo, ");
				sql.append("    COM_COTACA_Numero, ");
				sql.append("    COM_COTACA_Emissao, ");
				sql.append("    COM_TABCOM_Codigo, ");
				sql.append("    GEN_TABUSU_Login, ");
				sql.append("    COM_COTACA_Prioridade, ");
				sql.append("    COM_COTACA_Situacao, ");
				sql.append("    COM_COTACA_Observacao, ");
				sql.append("    COM_COTACA_Created, ");
				sql.append("    COM_COTACA_Updated, ");
				sql.append("    COM_COTACA_SeqProduto, ");
				sql.append("    COM_COTACA_Tipo, ");
				sql.append("    COM_COTACA_Critica, ");
				sql.append("    COM_COTACA_ValidadeFinal, ");
				sql.append("    COM_COTACA_ValidadeInicial, ");
				sql.append("    STG_COM_VLDCOT_Codigo, ");
				sql.append("    COM_COTACA_EmailsNEnviados, ");
				sql.append("    COM_COTACA_EmailsEnviados, ");
				sql.append("    COM_COTACA_EmailsTotal, ");
				sql.append("    COM_COTACA_ArqCotacao, ");
				sql.append("    COM_COTACA_NomeArqCotacao, ");
				sql.append("    COM_COTACA_EncCotacao, ");
				sql.append("    COM_COTACA_DataHoraAprovacao, ");
				sql.append("    COM_COTACA_Aprovacao, ");
				sql.append("    STG_GEN_TABUSU_CotApr_Login, ");
				sql.append("    COM_COTACA_IDOSTerceiro, ");
				sql.append("    COM_COTACA_OSGarantia, ");
				sql.append("    COM_COTACA_DHIntOBC) ");
				sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setInt(count++, cotacaoCompraDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoCompraDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraDbg.getCOM_COTACA_Numero());
				preparedStatement.setDate(count++, Date.valueOf(cotacaoCompraDbg.getCOM_COTACA_Emissao()));
				preparedStatement.setInt(count++, cotacaoCompraDbg.getCOM_TABCOM_Codigo());
				preparedStatement.setString(count++, cotacaoCompraDbg.getGEN_TABUSU_Login());
				preparedStatement.setString(count++, cotacaoCompraDbg.getCOM_COTACA_Prioridade());
				preparedStatement.setString(count++, cotacaoCompraDbg.getCOM_COTACA_Situacao());
				preparedStatement.setString(count++, cotacaoCompraDbg.getCOM_COTACA_Observacao());
				preparedStatement.setTimestamp(count++, Timestamp.valueOf(cotacaoCompraDbg.getCOM_COTACA_Created()));
				preparedStatement.setTimestamp(count++, Timestamp.valueOf(cotacaoCompraDbg.getCOM_COTACA_Updated()));
				preparedStatement.setInt(count++, cotacaoCompraDbg.getCOM_COTACA_SeqProduto());
				preparedStatement.setString(count++, cotacaoCompraDbg.getCOM_COTACA_Tipo());

				if (cotacaoCompraDbg.getCOM_COTACA_Critica() != null)
					preparedStatement.setInt(count++, cotacaoCompraDbg.getCOM_COTACA_Critica());
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraDbg.getCOM_COTACA_ValidadeFinal() != null)
					preparedStatement.setDate(count++, Date.valueOf(cotacaoCompraDbg.getCOM_COTACA_ValidadeFinal()));
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraDbg.getCOM_COTACA_ValidadeInicial() != null)
					preparedStatement.setDate(count++, Date.valueOf(cotacaoCompraDbg.getCOM_COTACA_ValidadeInicial()));
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraDbg.getSTG_COM_VLDCOT_Codigo() != null)
					preparedStatement.setInt(count++, cotacaoCompraDbg.getSTG_COM_VLDCOT_Codigo());
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraDbg.getCOM_COTACA_EmailsNEnviados() != null)
					preparedStatement.setInt(count++, cotacaoCompraDbg.getCOM_COTACA_EmailsNEnviados());
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraDbg.getCOM_COTACA_EmailsEnviados() != null)
					preparedStatement.setInt(count++, cotacaoCompraDbg.getCOM_COTACA_EmailsEnviados());
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraDbg.getCOM_COTACA_EmailsTotal() != null)
					preparedStatement.setInt(count++, cotacaoCompraDbg.getCOM_COTACA_EmailsTotal());
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraDbg.getCOM_COTACA_ArqCotacao() != null)
					preparedStatement.setBytes(count++, cotacaoCompraDbg.getCOM_COTACA_ArqCotacao());
				else
					preparedStatement.setObject(count++, null);

				preparedStatement.setString(count++, cotacaoCompraDbg.getCOM_COTACA_NomeArqCotacao());

				if (cotacaoCompraDbg.getCOM_COTACA_EncCotacao() != null)
					preparedStatement.setInt(count++, cotacaoCompraDbg.getCOM_COTACA_EncCotacao());
				else
					preparedStatement.setObject(count++, null);

				preparedStatement.setTimestamp(count++,
						Timestamp.valueOf(cotacaoCompraDbg.getCOM_COTACA_DataHoraAprovacao()));
				preparedStatement.setString(count++, cotacaoCompraDbg.getCOM_COTACA_Aprovacao());
				preparedStatement.setString(count++, cotacaoCompraDbg.getSTG_GEN_TABUSU_CotApr_Login());

				if (cotacaoCompraDbg.getCOM_COTACA_IDOSTerceiro() != null)
					preparedStatement.setLong(count++, cotacaoCompraDbg.getCOM_COTACA_IDOSTerceiro());
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraDbg.getCOM_COTACA_OSGarantia() != null)
					preparedStatement.setInt(count++, cotacaoCompraDbg.getCOM_COTACA_OSGarantia());
				else
					preparedStatement.setObject(count++, null);

				preparedStatement.setTimestamp(count++, Timestamp.valueOf(cotacaoCompraDbg.getCOM_COTACA_DHIntOBC()));
			}

		}.execUpd();
	}

}

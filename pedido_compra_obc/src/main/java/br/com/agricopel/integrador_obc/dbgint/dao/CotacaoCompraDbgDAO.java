package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraDbg;

public class CotacaoCompraDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoCompraDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public CotacaoCompraDbg getCotacao(Integer STG_GEN_TABEMP_Codigo, Integer STG_GEN_TABFIL_Codigo,
			Long COM_COTACA_Numero) throws Exception {

		return new SqlReadUtil<CotacaoCompraDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" SELECT STG_GEN_TABEMP_Codigo, ");
				sql.append("     STG_GEN_TABFIL_Codigo, ");
				sql.append("     COM_COTACA_Numero, ");
				sql.append("     COM_COTACA_Emissao, ");
				sql.append("     COM_TABCOM_Codigo, ");
				sql.append("     GEN_TABUSU_Login, ");
				sql.append("     COM_COTACA_Prioridade, ");
				sql.append("     COM_COTACA_Situacao, ");
				sql.append("     COM_COTACA_Observacao, ");
				sql.append("     COM_COTACA_Created, ");
				sql.append("     COM_COTACA_Updated, ");
				sql.append("     COM_COTACA_SeqProduto, ");
				sql.append("     COM_COTACA_Tipo, ");
				sql.append("     COM_COTACA_Critica, ");
				sql.append("     COM_COTACA_ValidadeFinal, ");
				sql.append("     COM_COTACA_ValidadeInicial, ");
				sql.append("     STG_COM_VLDCOT_Codigo, ");
				sql.append("     COM_COTACA_EmailsNEnviados, ");
				sql.append("     COM_COTACA_EmailsEnviados, ");
				sql.append("     COM_COTACA_EmailsTotal, ");
				sql.append("     COM_COTACA_ArqCotacao, ");
				sql.append("     COM_COTACA_NomeArqCotacao, ");
				sql.append("     COM_COTACA_EncCotacao, ");
				sql.append("     COM_COTACA_DataHoraAprovacao, ");
				sql.append("     COM_COTACA_Aprovacao, ");
				sql.append("     STG_GEN_TABUSU_CotApr_Login, ");
				sql.append("     COM_COTACA_IDOSTerceiro, ");
				sql.append("     COM_COTACA_OSGarantia, ");
				sql.append("     COM_COTACA_DHIntOBC ");
				sql.append(" FROM COM_COTACA ");
				sql.append(" WHERE STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" AND   STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" AND   COM_COTACA_Numero = ? ");
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, STG_GEN_TABEMP_Codigo);
				preparedStatement.setInt(2, STG_GEN_TABFIL_Codigo);
				preparedStatement.setLong(3, COM_COTACA_Numero);
			};

			@Override
			public CotacaoCompraDbg criarModel(ResultSet resultSet) throws Exception {

				DateUtil dateUtil = new DateUtil();
				CotacaoCompraDbg cotacaoCompraDbg = new CotacaoCompraDbg();

				cotacaoCompraDbg.setSTG_GEN_TABEMP_Codigo(resultSet.getInt("STG_GEN_TABEMP_Codigo"));
				cotacaoCompraDbg.setSTG_GEN_TABFIL_Codigo(resultSet.getInt("STG_GEN_TABFIL_Codigo"));
				cotacaoCompraDbg.setCOM_COTACA_Numero(resultSet.getLong("COM_COTACA_Numero"));
				cotacaoCompraDbg.setCOM_COTACA_Emissao(dateUtil.toLocalDate(resultSet.getDate("COM_COTACA_Emissao")));
				cotacaoCompraDbg.setCOM_TABCOM_Codigo(resultSet.getInt("COM_TABCOM_Codigo"));
				cotacaoCompraDbg.setGEN_TABUSU_Login(resultSet.getString("GEN_TABUSU_Login"));
				cotacaoCompraDbg.setCOM_COTACA_Prioridade(resultSet.getString("COM_COTACA_Prioridade"));
				cotacaoCompraDbg.setCOM_COTACA_Situacao(resultSet.getString("COM_COTACA_Situacao"));
				cotacaoCompraDbg.setCOM_COTACA_Observacao(resultSet.getString("COM_COTACA_Observacao"));
				cotacaoCompraDbg
						.setCOM_COTACA_Created(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_COTACA_Created")));
				cotacaoCompraDbg
						.setCOM_COTACA_Updated(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_COTACA_Updated")));
				cotacaoCompraDbg.setCOM_COTACA_SeqProduto(resultSet.getInt("COM_COTACA_SeqProduto"));
				cotacaoCompraDbg.setCOM_COTACA_Tipo(resultSet.getString("COM_COTACA_Tipo"));
				cotacaoCompraDbg.setCOM_COTACA_Critica(resultSet.getInt("COM_COTACA_Critica"));
				cotacaoCompraDbg.setCOM_COTACA_ValidadeFinal(
						dateUtil.toLocalDate(resultSet.getDate("COM_COTACA_ValidadeFinal")));
				cotacaoCompraDbg.setCOM_COTACA_ValidadeInicial(
						dateUtil.toLocalDate(resultSet.getDate("COM_COTACA_ValidadeInicial")));
				cotacaoCompraDbg.setSTG_COM_VLDCOT_Codigo(resultSet.getInt("STG_COM_VLDCOT_Codigo"));
				cotacaoCompraDbg.setCOM_COTACA_EmailsNEnviados(resultSet.getInt("COM_COTACA_EmailsNEnviados"));
				cotacaoCompraDbg.setCOM_COTACA_EmailsEnviados(resultSet.getInt("COM_COTACA_EmailsEnviados"));
				cotacaoCompraDbg.setCOM_COTACA_EmailsTotal(resultSet.getInt("COM_COTACA_EmailsTotal"));
				cotacaoCompraDbg.setCOM_COTACA_ArqCotacao(resultSet.getBytes("COM_COTACA_ArqCotacao"));
				cotacaoCompraDbg.setCOM_COTACA_NomeArqCotacao(resultSet.getString("COM_COTACA_NomeArqCotacao"));
				cotacaoCompraDbg.setCOM_COTACA_EncCotacao(resultSet.getInt("COM_COTACA_EncCotacao"));
				cotacaoCompraDbg.setCOM_COTACA_DataHoraAprovacao(
						dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_COTACA_DataHoraAprovacao")));
				cotacaoCompraDbg.setCOM_COTACA_Aprovacao(resultSet.getString("COM_COTACA_Aprovacao"));
				cotacaoCompraDbg.setSTG_GEN_TABUSU_CotApr_Login(resultSet.getString("STG_GEN_TABUSU_CotApr_Login"));
				cotacaoCompraDbg.setCOM_COTACA_IDOSTerceiro(resultSet.getLong("COM_COTACA_IDOSTerceiro"));
				cotacaoCompraDbg.setCOM_COTACA_OSGarantia(resultSet.getInt("COM_COTACA_OSGarantia"));
				cotacaoCompraDbg.setCOM_COTACA_DHIntOBC(
						dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_COTACA_DHIntOBC")));

				return cotacaoCompraDbg;
			}

		}.getModel();
	}

	public void updAnexo(CotacaoCompraDbg cotacaoCompraDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" UPDATE COM_COTACA set ");
				sql.append("   COM_COTACA_ArqCotacao = ?, ");
				sql.append("   COM_COTACA_NomeArqCotacao = ? ");
				sql.append(" WHERE STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" AND   STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" AND   COM_COTACA_Numero = ? ");
				
				LogUtils.escreverLogInfo("Atualizar COTACAO" + sql.toString());
					
				
			};

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setBytes(count++, cotacaoCompraDbg.getCOM_COTACA_ArqCotacao());
				preparedStatement.setString(count++, cotacaoCompraDbg.getCOM_COTACA_NomeArqCotacao());
				preparedStatement.setInt(count++, cotacaoCompraDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoCompraDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraDbg.getCOM_COTACA_Numero());
			};

		}.execUpd();
	}

	public void updStatus(CotacaoCompraDbg cotacaoCompraDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" UPDATE COM_COTACA set ");
				sql.append("   COM_COTACA_Situacao = case when not exists (select COM_SOLICI_Numero ");
				sql.append("                                               from COM_PROCOT ");
				sql.append(" 									           where COM_COTACA.STG_GEN_TABEMP_Codigo = COM_PROCOT.STG_GEN_TABEMP_Codigo ");
				sql.append(" 									           and   COM_COTACA.STG_GEN_TABFIL_Codigo = COM_PROCOT.STG_GEN_TABFIL_Codigo ");
				sql.append(" 									           and   COM_COTACA.COM_COTACA_Numero = COM_PROCOT.COM_COTACA_Numero ");
				sql.append(" 									           and   COM_PROCOT.COM_PROCOT_Situacao not in ('C','A')) ");
				sql.append(" 							  then 'A' else 'L' end ");
				sql.append(" WHERE STG_GEN_TABEMP_Codigo = ? ");
				sql.append(" AND   STG_GEN_TABFIL_Codigo = ? ");
				sql.append(" AND   COM_COTACA_Numero = ? ");
				LogUtils.escreverLogInfo("updStatus COTACAO " + sql.toString());
			};

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setInt(count++, cotacaoCompraDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoCompraDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraDbg.getCOM_COTACA_Numero());
			};

		}.execUpd();
	}

}

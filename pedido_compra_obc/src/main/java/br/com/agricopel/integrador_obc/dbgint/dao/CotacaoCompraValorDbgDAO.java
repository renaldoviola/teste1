package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CotacaoCompraValorDbg;

public class CotacaoCompraValorDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CotacaoCompraValorDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(CotacaoCompraValorDbg cotacaoCompraValorDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append("  REPLACE INTO COM_VALORE ( ");
				sql.append("     STG_GEN_TABEMP_Codigo, ");
				sql.append("     STG_GEN_TABFIL_Codigo, ");
				sql.append("     COM_COTACA_Numero, ");
				sql.append("     COM_PROCOT_Sequencia, ");
				sql.append("     GEN_TABENT_Codigo, ");
				sql.append("     GEN_ENDENT_Codigo, ");
				sql.append("     COM_VALORE_Quantidade, ");
				sql.append("     COM_VALORE_Valor, ");
				sql.append("     COM_VALORE_PercIPI, ");
				sql.append("     GEN_TABCPG_Codigo, ");
				sql.append("     COM_VALORE_Created, ");
				sql.append("     COM_VALORE_Updated, ");
				sql.append("     COM_VALORE_MelhorPreco, ");
				sql.append("     COM_VALORE_GerarPedido, ");
				sql.append("     COM_VALORE_Observacao, ");
				sql.append("     COM_VALORE_Entrega, ");
				sql.append("     COM_VALORE_DHAltValor, ");
				sql.append("     COM_VALORE_NAltValor, ");
				sql.append("     COM_VALORE_ValorFrete, ");
				sql.append("     COM_VALORE_TipoFrete, ");
				sql.append("     COM_VALORE_DiasGarantia, ");
				sql.append("     COM_VALORE_DiasMinGarantia, ");
				sql.append("     COM_VALORE_ValorMaxGarantia) ");
				sql.append("  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				LogUtils.escreverLogInfo("INTO COM_VALORE " + sql.toString());		
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 1;

				preparedStatement.setInt(count++, cotacaoCompraValorDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setInt(count++, cotacaoCompraValorDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setDouble(count++, cotacaoCompraValorDbg.getCOM_COTACA_Numero());
				preparedStatement.setInt(count++, cotacaoCompraValorDbg.getCOM_PROCOT_Sequencia());
				preparedStatement.setDouble(count++, cotacaoCompraValorDbg.getGEN_TABENT_Codigo());
				preparedStatement.setLong(count++, cotacaoCompraValorDbg.getGEN_ENDENT_Codigo());
				preparedStatement.setDouble(count++, cotacaoCompraValorDbg.getCOM_VALORE_Quantidade());
				preparedStatement.setDouble(count++, cotacaoCompraValorDbg.getCOM_VALORE_Valor());
				preparedStatement.setDouble(count++, cotacaoCompraValorDbg.getCOM_VALORE_PercIPI());
				preparedStatement.setInt(count++, cotacaoCompraValorDbg.getGEN_TABCPG_Codigo());
				preparedStatement.setTimestamp(count++,
						Timestamp.valueOf(cotacaoCompraValorDbg.getCOM_VALORE_Created()));
				preparedStatement.setTimestamp(count++,
						Timestamp.valueOf(cotacaoCompraValorDbg.getCOM_VALORE_Updated()));
				preparedStatement.setInt(count++, cotacaoCompraValorDbg.getCOM_VALORE_MelhorPreco());
				preparedStatement.setInt(count++, cotacaoCompraValorDbg.getCOM_VALORE_GerarPedido());
				preparedStatement.setString(count++, cotacaoCompraValorDbg.getCOM_VALORE_Observacao());
				preparedStatement.setDate(count++, Date.valueOf(cotacaoCompraValorDbg.getCOM_VALORE_Entrega()));

				if (cotacaoCompraValorDbg.getCOM_VALORE_DHAltValor() != null)
					preparedStatement.setTimestamp(count++,
							Timestamp.valueOf(cotacaoCompraValorDbg.getCOM_VALORE_DHAltValor()));
				else
					preparedStatement.setObject(count++, null);

				preparedStatement.setInt(count++, cotacaoCompraValorDbg.getCOM_VALORE_NAltValor());
				preparedStatement.setDouble(count++, cotacaoCompraValorDbg.getCOM_VALORE_ValorFrete());
				preparedStatement.setString(count++, cotacaoCompraValorDbg.getCOM_VALORE_TipoFrete());
				preparedStatement.setInt(count++, cotacaoCompraValorDbg.getCOM_VALORE_DiasGarantia());

				if (cotacaoCompraValorDbg.getCOM_VALORE_DiasMinGarantia() != null)
					preparedStatement.setInt(count++, cotacaoCompraValorDbg.getCOM_VALORE_DiasMinGarantia());
				else
					preparedStatement.setObject(count++, null);

				if (cotacaoCompraValorDbg.getCOM_VALORE_ValorMaxGarantia() != null)
					preparedStatement.setDouble(count++, cotacaoCompraValorDbg.getCOM_VALORE_ValorMaxGarantia());
				else
					preparedStatement.setObject(count++, null);
			}
			
			
		}.execUpd();

	}

}

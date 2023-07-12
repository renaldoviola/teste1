package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoRatCCustoDbg;

public class PedidoRatCCustoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public PedidoRatCCustoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(PedidoRatCCustoDbg pedidoRatCCustoDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" insert into COM_CONPED ( ");
				sql.append("    STG_GEN_TABEMP_Codigo, ");
				sql.append("    STG_GEN_TABFIL_Codigo, ");
				sql.append("    COM_PEDIDO_Numero, ");
				sql.append("    COM_PROPED_Sequencia, ");
				sql.append("    COM_CONPED_Sequencia, ");
				sql.append("    COM_CONPED_Created, ");
				sql.append("    COM_CONPED_Updated, ");
				sql.append("    COM_CONPED_Quantidade, ");
				sql.append("    STG_FRT_TABCAR_Cop_Codigo, ");
				sql.append("    STG_GEN_TABCEN_Cop_Codigo) ");
				sql.append(" values (?,?,?,?,?,?,?,?,?,?) ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;

				preparedStatement.setLong(++count, pedidoRatCCustoDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setLong(++count, pedidoRatCCustoDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setInt(++count, pedidoRatCCustoDbg.getCOM_PEDIDO_Numero());
				preparedStatement.setInt(++count, pedidoRatCCustoDbg.getCOM_PROPED_Sequencia());
				preparedStatement.setInt(++count, pedidoRatCCustoDbg.getCOM_CONPED_Sequencia());
				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoRatCCustoDbg.getCOM_CONPED_Created()));
				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoRatCCustoDbg.getCOM_CONPED_Updated()));
				preparedStatement.setDouble(++count, pedidoRatCCustoDbg.getCOM_CONPED_Quantidade());

				if (pedidoRatCCustoDbg.getSTG_FRT_TABCAR_Cop_Codigo() != null) {
					preparedStatement.setString(++count, pedidoRatCCustoDbg.getSTG_FRT_TABCAR_Cop_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.VARCHAR);
				}

				if (pedidoRatCCustoDbg.getSTG_GEN_TABCEN_Cop_Codigo() != null) {
					preparedStatement.setString(++count, pedidoRatCCustoDbg.getSTG_GEN_TABCEN_Cop_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.VARCHAR);
				}
			}

		}.execUpd();

	}

}

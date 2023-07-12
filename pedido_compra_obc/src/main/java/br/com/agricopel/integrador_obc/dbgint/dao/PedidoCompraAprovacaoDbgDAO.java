package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoCompraAprovacaoDbg;

public class PedidoCompraAprovacaoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public PedidoCompraAprovacaoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(PedidoCompraAprovacaoDbg pedidoCompraAprovacaoDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append("  insert into COM_APRPED ( ");
				sql.append("     STG_GEN_TABEMP_Codigo, ");
				sql.append("     STG_GEN_TABFIL_Codigo, ");
				sql.append("     COM_PEDIDO_Numero, ");
				sql.append("     COM_APRPED_DHAprovacao, ");
				sql.append("     STG_GEN_TABUSU_Apr_Login, ");
				sql.append("     COM_APRPED_Situacao, ");
				sql.append("     COM_MOTCAN_Codigo, ");
				sql.append("     COM_APRPED_Created, ");
				sql.append("     COM_APRPED_Updated) ");
				sql.append("  values (?,?,?,?,?,?,?,?,?) ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;

				preparedStatement.setLong(++count, pedidoCompraAprovacaoDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setLong(++count, pedidoCompraAprovacaoDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setInt(++count, pedidoCompraAprovacaoDbg.getCOM_PEDIDO_Numero());

				preparedStatement.setTimestamp(++count,
						Timestamp.valueOf(pedidoCompraAprovacaoDbg.getCOM_APRPED_DHAprovacao()));

				preparedStatement.setString(++count, pedidoCompraAprovacaoDbg.getSTG_GEN_TABUSU_Apr_Login());
				preparedStatement.setString(++count, pedidoCompraAprovacaoDbg.getCOM_APRPED_Situacao());

				if (pedidoCompraAprovacaoDbg.getCOM_MOTCAN_Codigo() != null) {
					preparedStatement.setInt(++count, pedidoCompraAprovacaoDbg.getCOM_MOTCAN_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.INTEGER);
				}

				preparedStatement.setTimestamp(++count,
						Timestamp.valueOf(pedidoCompraAprovacaoDbg.getCOM_APRPED_Created()));

				preparedStatement.setTimestamp(++count,
						Timestamp.valueOf(pedidoCompraAprovacaoDbg.getCOM_APRPED_Updated()));
			}

		}.execUpd();
	}

}

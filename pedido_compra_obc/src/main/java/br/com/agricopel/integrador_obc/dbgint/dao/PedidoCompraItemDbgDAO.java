package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoCompraItemDbg;

public class PedidoCompraItemDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public PedidoCompraItemDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(PedidoCompraItemDbg pedidoCompraItemDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" INSERT INTO COM_PROPED ( ");
				sql.append("    STG_GEN_TABEMP_Codigo, ");
				sql.append("    STG_GEN_TABFIL_Codigo, ");
				sql.append("    COM_PEDIDO_Numero, ");
				sql.append("    COM_PROPED_Sequencia, ");
				sql.append("    STG_GEN_NATOPE_Codigo, ");
				sql.append("    STG_EST_TABPRO_Ped_Codigo, ");
				sql.append("    STG_EST_TABDEP_Ped_Codigo, ");
				sql.append("    COM_PROPED_Quantidade, ");
				sql.append("    COM_PROPED_Valor, ");
				sql.append("    COM_PROPED_Entregue, ");
				sql.append("    COM_PROPED_Cancelado, ");
				sql.append("    COM_PROPED_PercIPI, ");
				sql.append("    COM_PROPED_Observacao, ");
				sql.append("    COM_PROPED_Situacao, ");
				sql.append("    COM_PROPED_Created, ");
				sql.append("    COM_PROPED_Updated, ");
				sql.append("    COM_COTACA_Numero, ");
				sql.append("    COM_PROCOT_Sequencia, ");
				sql.append("    STG_FRT_TABCAR_Ped_Codigo, ");
				sql.append("    STG_GEN_TABCEN_Ped_Codigo, ");
				sql.append("    COM_PROPED_SDCV_OBC, ");
				sql.append("    COM_PROPED_DiasGarantia, ");
				sql.append("    COM_PROPED_TipoSDCV) ");
				sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				LogUtils.escreverLogInfo("Inserir  Item Pedido " + sql.toString());
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;
				double valUnit = 0.00 ;

				preparedStatement.setLong(++count, pedidoCompraItemDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setLong(++count, pedidoCompraItemDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setInt(++count, pedidoCompraItemDbg.getCOM_PEDIDO_Numero());
				preparedStatement.setInt(++count, pedidoCompraItemDbg.getCOM_PROPED_Sequencia());
				preparedStatement.setString(++count, pedidoCompraItemDbg.getSTG_GEN_NATOPE_Codigo());
				preparedStatement.setString(++count, pedidoCompraItemDbg.getSTG_EST_TABPRO_Ped_Codigo());
				preparedStatement.setInt(++count, pedidoCompraItemDbg.getSTG_EST_TABDEP_Ped_Codigo());
				preparedStatement.setDouble(++count, pedidoCompraItemDbg.getCOM_PROPED_Quantidade());
				if (pedidoCompraItemDbg.getCOM_PROPED_PercIPI() == 0) {
					preparedStatement.setDouble(++count, pedidoCompraItemDbg.getCOM_PROPED_Valor());
				}else {
					valUnit = pedidoCompraItemDbg.getCOM_PROPED_Valor();			
					preparedStatement.setDouble(++count, valUnit / ( 1 + ( pedidoCompraItemDbg.getCOM_PROPED_PercIPI() / 100 ) ));
				}
				preparedStatement.setDouble(++count, pedidoCompraItemDbg.getCOM_PROPED_Entregue());
				preparedStatement.setDouble(++count, pedidoCompraItemDbg.getCOM_PROPED_Cancelado());
				preparedStatement.setDouble(++count, pedidoCompraItemDbg.getCOM_PROPED_PercIPI());
				preparedStatement.setString(++count, pedidoCompraItemDbg.getCOM_PROPED_Observacao());
				preparedStatement.setString(++count, pedidoCompraItemDbg.getCOM_PROPED_Situacao());
				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoCompraItemDbg.getCOM_PROPED_Created()));
				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoCompraItemDbg.getCOM_PROPED_Updated()));

				if (pedidoCompraItemDbg.getCOM_COTACA_Numero() != null) {
					preparedStatement.setLong(++count, pedidoCompraItemDbg.getCOM_COTACA_Numero());
				} else {
					preparedStatement.setNull(++count, Types.DOUBLE);
				}

				if (pedidoCompraItemDbg.getCOM_PROCOT_Sequencia() != null) {
					preparedStatement.setInt(++count, pedidoCompraItemDbg.getCOM_PROCOT_Sequencia());
				} else {
					preparedStatement.setNull(++count, Types.INTEGER);
				}

				if (pedidoCompraItemDbg.getSTG_FRT_TABCAR_Ped_Codigo() != null
						&& !pedidoCompraItemDbg.getSTG_FRT_TABCAR_Ped_Codigo().isEmpty()
						&& pedidoCompraItemDbg.getRatCCusto().size() < 2) {
					preparedStatement.setString(++count, pedidoCompraItemDbg.getSTG_FRT_TABCAR_Ped_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.VARCHAR);
				}

				if (pedidoCompraItemDbg.getSTG_GEN_TABCEN_Ped_Codigo() != null
						&& !pedidoCompraItemDbg.getSTG_GEN_TABCEN_Ped_Codigo().isEmpty()
						&& pedidoCompraItemDbg.getRatCCusto().size() < 2) {
					preparedStatement.setString(++count, pedidoCompraItemDbg.getSTG_GEN_TABCEN_Ped_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.VARCHAR);
				}

				preparedStatement.setString(++count, pedidoCompraItemDbg.getCOM_PROPED_SDCV_OBC());
				preparedStatement.setInt(++count, pedidoCompraItemDbg.getCOM_PROPED_DiasGarantia());
				preparedStatement.setString(++count, pedidoCompraItemDbg.getCOM_PROPED_TipoSDCV());
			}

		}.execUpd();
	}

}

package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.dbgint.model.PedidoCompraDbg;

public class PedidoCompraDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public PedidoCompraDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public void inserir(PedidoCompraDbg pedidoCompraDbg) throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" INSERT INTO COM_PEDIDO ( ");
				sql.append("    STG_GEN_TABEMP_Codigo, ");
				sql.append("    STG_GEN_TABFIL_Codigo, ");
				sql.append("    COM_PEDIDO_Numero, ");
				sql.append("    COM_PEDIDO_Emissao, ");
				sql.append("    COM_PEDIDO_Entrega, ");
				sql.append("    STG_GEN_TABENT_For_Codigo, ");
				sql.append("    STG_GEN_TABENT_Tra_Codigo, ");
				sql.append("    COM_PEDIDO_DHEnvioEMail, ");
				sql.append("    COM_PEDIDO_Created, ");
				sql.append("    COM_PEDIDO_Updated, ");
				sql.append("    STG_GEN_ENDENT_For_Codigo, ");
				sql.append("    STG_GEN_ENDENT_Tra_Codigo, ");
				sql.append("    GEN_TABCPG_Codigo, ");
				sql.append("    COM_TABCOM_Codigo, ");
				sql.append("    STG_GEN_TABUSU_Dig_Login, ");
				sql.append("    COM_PEDIDO_Observacao, ");
				sql.append("    COM_PEDIDO_Situacao, ");
				sql.append("    COM_PEDIDO_TipoFrete, ");
				sql.append("    GEN_TABORG_Codigo, ");
				sql.append("    COM_PEDIDO_SeqProduto, ");
				sql.append("    GEN_NATOPE_Codigo, ");
				sql.append("    COM_PEDIDO_Tipo, ");
				sql.append("    COM_PEDIDO_ValorFrete, ");
				sql.append("    COM_PEDIDO_Emergencial, ");
				sql.append("    COM_PEDIDO_IDOSTerceiro, ");
				sql.append("    COM_PEDIDO_DHIntOBC, ");
				sql.append("    COM_PEDIDO_Origem) ");
				sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				LogUtils.escreverLogInfo("PedidoCompraDbgDAO inserir  " + sql.toString());
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;

				preparedStatement.setLong(++count, pedidoCompraDbg.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setLong(++count, pedidoCompraDbg.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setInt(++count, pedidoCompraDbg.getCOM_PEDIDO_Numero());
				preparedStatement.setDate(++count, Date.valueOf(pedidoCompraDbg.getCOM_PEDIDO_Emissao()));
				preparedStatement.setDate(++count, Date.valueOf(pedidoCompraDbg.getCOM_PEDIDO_Entrega()));
				preparedStatement.setLong(++count, pedidoCompraDbg.getSTG_GEN_TABENT_For_Codigo());

				if (pedidoCompraDbg.getSTG_GEN_TABENT_Tra_Codigo() != null) {
					preparedStatement.setLong(++count, pedidoCompraDbg.getSTG_GEN_TABENT_Tra_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.INTEGER);
				}

				preparedStatement.setTimestamp(++count,
						Timestamp.valueOf(pedidoCompraDbg.getCOM_PEDIDO_DHEnvioEMail()));

				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoCompraDbg.getCOM_PEDIDO_Created()));
				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoCompraDbg.getCOM_PEDIDO_Updated()));
				preparedStatement.setLong(++count, pedidoCompraDbg.getSTG_GEN_ENDENT_For_Codigo());

				if (pedidoCompraDbg.getSTG_GEN_ENDENT_Tra_Codigo() != null) {
					preparedStatement.setLong(++count, pedidoCompraDbg.getSTG_GEN_ENDENT_Tra_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.INTEGER);
				}

				preparedStatement.setInt(++count, pedidoCompraDbg.getGEN_TABCPG_Codigo());
				preparedStatement.setInt(++count, pedidoCompraDbg.getCOM_TABCOM_Codigo());
				preparedStatement.setString(++count, pedidoCompraDbg.getSTG_GEN_TABUSU_Dig_Login());
				preparedStatement.setString(++count, pedidoCompraDbg.getCOM_PEDIDO_Observacao());
				preparedStatement.setString(++count, pedidoCompraDbg.getCOM_PEDIDO_Situacao());

				if (pedidoCompraDbg.getCOM_PEDIDO_TipoFrete() != null) {
					preparedStatement.setString(++count, pedidoCompraDbg.getCOM_PEDIDO_TipoFrete());
				} else {
					preparedStatement.setNull(++count, Types.INTEGER);
				}

				preparedStatement.setInt(++count, pedidoCompraDbg.getGEN_TABORG_Codigo());
				preparedStatement.setInt(++count, pedidoCompraDbg.getCOM_PEDIDO_SeqProduto());
				preparedStatement.setString(++count, pedidoCompraDbg.getGEN_NATOPE_Codigo());
				preparedStatement.setString(++count, pedidoCompraDbg.getCOM_PEDIDO_Tipo());
				preparedStatement.setDouble(++count, pedidoCompraDbg.getCOM_PEDIDO_ValorFrete());
				preparedStatement.setInt(++count, pedidoCompraDbg.getCOM_PEDIDO_Emergencial());

				if (pedidoCompraDbg.getCOM_PEDIDO_IDOSTerceiro() != null) {
					preparedStatement.setLong(++count, pedidoCompraDbg.getCOM_PEDIDO_IDOSTerceiro());
				} else {
					preparedStatement.setNull(++count, Types.DOUBLE);
				}

				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoCompraDbg.getCOM_PEDIDO_DHIntOBC()));
				preparedStatement.setString(++count, pedidoCompraDbg.getCOM_PEDIDO_Origem());
			}
		}.execUpd();
	}

	public PedidoCompraDbg getPedidoSdcv(String codSdcv) throws Exception {

		return new SqlReadUtil<PedidoCompraDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select distinct(COM_PEDIDO.COM_PEDIDO_Numero) as COM_PEDIDO_Numero ");
				sql.append(" from COM_PEDIDO, COM_PROPED ");
				sql.append(" where COM_PEDIDO.STG_GEN_TABEMP_Codigo = COM_PROPED.STG_GEN_TABEMP_Codigo ");
				sql.append(" and   COM_PEDIDO.STG_GEN_TABFIL_Codigo = COM_PROPED.STG_GEN_TABFIL_Codigo ");
				sql.append(" and   COM_PEDIDO.COM_PEDIDO_Numero     = COM_PROPED.COM_PEDIDO_Numero ");
				sql.append(" and   COM_PROPED.COM_PROPED_SDCV_OBC = ? ");
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setString(1, codSdcv);
			};

			public PedidoCompraDbg criarModel(ResultSet resultSet) throws Exception {

				PedidoCompraDbg pedidoCompraDbg = new PedidoCompraDbg();

				pedidoCompraDbg.setCOM_PEDIDO_Numero(resultSet.getInt("COM_PEDIDO_Numero"));

				return pedidoCompraDbg;
			};

		}.getModel();
	}
}
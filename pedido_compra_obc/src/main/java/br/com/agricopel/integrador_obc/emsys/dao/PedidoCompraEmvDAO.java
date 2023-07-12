package br.com.agricopel.integrador_obc.emsys.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Timestamp;
//import java.sql.Types;
import java.text.DateFormat;

import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.emsys.model.PedidoCompraEmv;

public class PedidoCompraEmvDAO {

	private ConexaoEmsys conexaoEmsys;

	public PedidoCompraEmvDAO(ConexaoEmsys conexaoEmsys) {
		this.conexaoEmsys = conexaoEmsys;
	}

	public void inserir(PedidoCompraEmv pedidoCompraEmv) throws Exception {

		new SqlWriteUtil(this.conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				/*sql.append(" INSERT INTO COM_PEDIDO ( ");
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
				sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");*/
				LogUtils.escreverLogInfo("LOGSPILLER select  INTO tab_pedido_compra" ) ;
				sql.append("INSERT INTO tab_pedido_compra ( ");
				sql.append("seq_pedido,");
				sql.append("cod_empresa,");
				sql.append("cod_pessoa_fornecedor,");
				sql.append("dta_emissao,");
				sql.append("val_frete,");
				sql.append("ind_tipo_frete,");
				sql.append("val_pedido,");
				sql.append("ind_pendente,");
				sql.append("nom_usuario,");
				sql.append("dta_cadastro,");
				sql.append("val_adiantamento,");
				sql.append("num_pedido_fornecedor,");
				sql.append("des_observacao)");
				sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;
				
				LogUtils.escreverLogInfo("LOGSPILLER INSERT pedidoCompraEmvpedidoCompraEmv" );
				LogUtils.escreverLogInfo( Integer.toString(pedidoCompraEmv.getseq_pedido()) );//12
				LogUtils.escreverLogInfo( Integer.toString( pedidoCompraEmv.getcod_empresa() )) ;//21
				LogUtils.escreverLogInfo( Long.toString(pedidoCompraEmv.getcod_pessoa_fornecedor()) );//35082
				//LogUtils.escreverLogInfo( Date.parse(pedidoCompraEmv.getdta_emissao()) );
				LogUtils.escreverLogInfo( pedidoCompraEmv.getind_tipo_frete() );//C
				LogUtils.escreverLogInfo(  Double.toString(pedidoCompraEmv.getval_pedido()) );//null
				LogUtils.escreverLogInfo( pedidoCompraEmv.getind_pendente() );
				LogUtils.escreverLogInfo( pedidoCompraEmv.getnom_usuario() );
				//LogUtils.escreverLogInfo(  Integer.toString(Date.valueOf(pedidoCompraEmv.getdta_cadastro())));
				LogUtils.escreverLogInfo( Double.toString( pedidoCompraEmv.getval_adiantamento() ));
				 
				preparedStatement.setInt(++count, pedidoCompraEmv.getseq_pedido());
				preparedStatement.setInt(++count, pedidoCompraEmv.getcod_empresa());
				preparedStatement.setLong(++count, pedidoCompraEmv.getcod_pessoa_fornecedor());
				preparedStatement.setDate(++count, Date.valueOf(pedidoCompraEmv.getdta_emissao()));
				preparedStatement.setDouble(++count, pedidoCompraEmv.getval_frete());
				preparedStatement.setString(++count, pedidoCompraEmv.getind_tipo_frete());
				preparedStatement.setDouble(++count, pedidoCompraEmv.getval_pedido());
				preparedStatement.setString(++count, pedidoCompraEmv.getind_pendente());
				preparedStatement.setString(++count, pedidoCompraEmv.getnom_usuario());
				preparedStatement.setDate(++count, Date.valueOf(pedidoCompraEmv.getdta_cadastro()));
				preparedStatement.setDouble(++count, pedidoCompraEmv.getval_adiantamento());
				preparedStatement.setString(++count, pedidoCompraEmv.getnum_pedido_fornecedor());
				preparedStatement.setString(++count, pedidoCompraEmv.getdes_observacao());
			
				
			}
		}.execUpd();
	}

	public PedidoCompraEmv getPedidoSdcv(String codSdcv) throws Exception {

		return new SqlReadUtil<PedidoCompraEmv>(this.conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				/*sql.append(" select distinct(COM_PEDIDO.COM_PEDIDO_Numero) as COM_PEDIDO_Numero ");
				sql.append(" from COM_PEDIDO, COM_PROPED ");
				sql.append(" where COM_PEDIDO.STG_GEN_TABEMP_Codigo = COM_PROPED.STG_GEN_TABEMP_Codigo ");
				sql.append(" and   COM_PEDIDO.STG_GEN_TABFIL_Codigo = COM_PROPED.STG_GEN_TABFIL_Codigo ");
				sql.append(" and   COM_PEDIDO.COM_PEDIDO_Numero     = COM_PROPED.COM_PEDIDO_Numero ");
				sql.append(" and   COM_PROPED.COM_PROPED_SDCV_OBC = ? ");*/
				LogUtils.escreverLogInfo("LOGSPILLER distinct(seq_pedido)" );
				sql.append(" select distinct(seq_pedido) as seq_pedido ");
				sql.append(" from tab_pedido_compra ");
				sql.append(" where  ");
				sql.append(" num_pedido_fornecedor = ? ");
				
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setString(1, codSdcv);
			};

			public PedidoCompraEmv criarModel(ResultSet resultSet) throws Exception {

				PedidoCompraEmv pedidoCompraEmv = new PedidoCompraEmv();

				pedidoCompraEmv.setseq_pedido(resultSet.getInt("seq_pedido"));

				return pedidoCompraEmv;
			};

		}.getModel();
	}
	
	
	public PedidoCompraEmv getCondPagto(String codCond) throws Exception {

		return new SqlReadUtil<PedidoCompraEmv>(this.conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				LogUtils.escreverLogInfo("LOGSPILLER distinct(condicao)" );
				sql.append(" select condicao ");
				sql.append(" from OBC_CONDPAGTO ");
				sql.append(" where  ");
				sql.append(" codigo_erp =  ? "); 
				
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setString(1, codCond);
			};

			public PedidoCompraEmv criarModel(ResultSet resultSet) throws Exception {

				PedidoCompraEmv pedidoCompraEmv = new PedidoCompraEmv();

				pedidoCompraEmv.setcondpagto(resultSet.getString("condicao"));

				return pedidoCompraEmv;
			};

		}.getModel();
	}
	
	
	
	
}
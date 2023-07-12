package br.com.agricopel.integrador_obc.emsys.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;

import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.comp.utils.SqlReadUtil;
//import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.emsys.model.PedidoCompraEmv;
import br.com.agricopel.integrador_obc.emsys.model.PedidoCompraItemEmv;

public class PedidoCompraItemEmvDAO {

	private ConexaoEmsys conexaoEmsys;

	public PedidoCompraItemEmvDAO(ConexaoEmsys conexaoEmsys) {
		this.conexaoEmsys = conexaoEmsys;
	}

	public void inserir(PedidoCompraItemEmv pedidoCompraItemEmv) throws Exception {

		new SqlWriteUtil(this.conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				/*sql.append(" INSERT INTO COM_PROPED ( ");
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
				sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");*/
				LogUtils.escreverLogInfo("LOGSPILLER INSERT INTO tab_item_pedido_compra" ) ;
				
				sql.append("INSERT INTO tab_item_pedido_compra ( ");
				sql.append("seq_pedido, ");
				sql.append("seq_item, ");
				sql.append("cod_item, ");
				sql.append("qtd_item, ");
				sql.append("cod_unidade, ");
				sql.append("qtd_item_convertido, ");
				sql.append("val_unitario, ");
				sql.append("val_total, ");
				sql.append("cod_almoxarifado,");
				sql.append("dta_previsao_entrega,"); 
				sql.append("qtd_atendido, ");
				sql.append("qtd_cancelado, ");
				sql.append("val_desconto, ");
				sql.append("val_ipi, ");
				sql.append("val_desconto_rateado,");
				sql.append("des_observacao");
				
				if (pedidoCompraItemEmv.getcod_natureza_operacao() > 0 ){
					sql.append(",cod_natureza_operacao");
				};
				
				sql.append(")");
				
				sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
				
				if (pedidoCompraItemEmv.getcod_natureza_operacao() > 0 ){
					sql.append(",?");
				};
				
				sql.append(" )");
				
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;
				LogUtils.escreverLogInfo("LOGSPILLER preparedStatement tab_item_pedido_compra" ) ;
				preparedStatement.setInt(++count,  pedidoCompraItemEmv.getseq_pedido());
				preparedStatement.setInt(++count, pedidoCompraItemEmv.getseq_item());
				preparedStatement.setInt(++count, pedidoCompraItemEmv.getcod_item());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getqtd_item());
				preparedStatement.setInt(++count, pedidoCompraItemEmv.getcod_unidade());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getqtd_item_convertido());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getval_unitario());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getval_total());
				preparedStatement.setInt(++count, pedidoCompraItemEmv.getcod_almoxarifado());
				preparedStatement.setDate(++count, Date.valueOf(pedidoCompraItemEmv.getdta_previsao_entrega()));
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getqtd_atendido());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getqtd_cancelado());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getval_desconto());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getval_ipi());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getval_desconto_rateado());
				preparedStatement.setString(++count, pedidoCompraItemEmv.getdes_observacao());
				if (pedidoCompraItemEmv.getcod_natureza_operacao() > 0 ){
					preparedStatement.setInt(++count, pedidoCompraItemEmv.getcod_natureza_operacao());
				}
				
				/*
				preparedStatement.setLong(++count, pedidoCompraItemEmv.getSTG_GEN_TABEMP_Codigo());
				preparedStatement.setLong(++count, pedidoCompraItemEmv.getSTG_GEN_TABFIL_Codigo());
				preparedStatement.setInt(++count, pedidoCompraItemEmv.getCOM_PEDIDO_Numero());
				preparedStatement.setInt(++count, pedidoCompraItemEmv.getCOM_PROPED_Sequencia());
				preparedStatement.setString(++count, pedidoCompraItemEmv.getSTG_GEN_NATOPE_Codigo());
				preparedStatement.setString(++count, pedidoCompraItemEmv.getSTG_EST_TABPRO_Ped_Codigo());
				preparedStatement.setInt(++count, pedidoCompraItemEmv.getSTG_EST_TABDEP_Ped_Codigo());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getCOM_PROPED_Quantidade());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getCOM_PROPED_Valor());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getCOM_PROPED_Entregue());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getCOM_PROPED_Cancelado());
				preparedStatement.setDouble(++count, pedidoCompraItemEmv.getCOM_PROPED_PercIPI());
				preparedStatement.setString(++count, pedidoCompraItemEmv.getCOM_PROPED_Observacao());
				preparedStatement.setString(++count, pedidoCompraItemEmv.getCOM_PROPED_Situacao());
				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoCompraItemEmv.getCOM_PROPED_Created()));
				preparedStatement.setTimestamp(++count, Timestamp.valueOf(pedidoCompraItemEmv.getCOM_PROPED_Updated()));

				if (pedidoCompraItemEmv.getCOM_COTACA_Numero() != null) {
					preparedStatement.setLong(++count, pedidoCompraItemEmv.getCOM_COTACA_Numero());
				} else {
					preparedStatement.setNull(++count, Types.DOUBLE);
				}

				if (pedidoCompraItemEmv.getCOM_PROCOT_Sequencia() != null) {
					preparedStatement.setInt(++count, pedidoCompraItemEmv.getCOM_PROCOT_Sequencia());
				} else {
					preparedStatement.setNull(++count, Types.INTEGER);
				}

				if (pedidoCompraItemEmv.getSTG_FRT_TABCAR_Ped_Codigo() != null
						&& !pedidoCompraItemEmv.getSTG_FRT_TABCAR_Ped_Codigo().isEmpty()
						&& pedidoCompraItemEmv.getRatCCusto().size() < 2) {
					preparedStatement.setString(++count, pedidoCompraItemEmv.getSTG_FRT_TABCAR_Ped_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.VARCHAR);
				}

				if (pedidoCompraItemEmv.getSTG_GEN_TABCEN_Ped_Codigo() != null
						&& !pedidoCompraItemEmv.getSTG_GEN_TABCEN_Ped_Codigo().isEmpty()
						&& pedidoCompraItemEmv.getRatCCusto().size() < 2) {
					preparedStatement.setString(++count, pedidoCompraItemEmv.getSTG_GEN_TABCEN_Ped_Codigo());
				} else {
					preparedStatement.setNull(++count, Types.VARCHAR);
				}

				preparedStatement.setString(++count, pedidoCompraItemEmv.getCOM_PROPED_SDCV_OBC());
				preparedStatement.setInt(++count, pedidoCompraItemEmv.getCOM_PROPED_DiasGarantia());
				preparedStatement.setString(++count, pedidoCompraItemEmv.getCOM_PROPED_TipoSDCV());*/
			}

		}.execUpd();
	}

	
	public PedidoCompraItemEmv getAlmoxarifado( Integer cod_filial, Integer cod_produto/*codSdcv*/) throws Exception {

		return new SqlReadUtil<PedidoCompraItemEmv>(this.conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
					
				LogUtils.escreverLogInfo("LOGSPILLER select  cod_almoxarifado" ) ;
				sql.append(" select  cod_almoxarifado ");
				sql.append(" from tab_almoxarifado ");
				sql.append(" where ind_desativado = 'N' and ");
				sql.append(" cod_empresa = ? ");
				sql.append(" and ((cod_item_tanque = ? and ind_tanque = 'S') ");
				sql.append(" or ind_tanque = 'N') ");
				sql.append(" ORDER BY cod_item_tanque ");
				sql.append(" limit 1");
				
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, cod_filial);
				preparedStatement.setInt(2, cod_produto);
			};

			public PedidoCompraItemEmv criarModel(ResultSet resultSet) throws Exception {

				PedidoCompraItemEmv pedidoCompraItemEmv = new PedidoCompraItemEmv();
				
				pedidoCompraItemEmv.setcod_almoxarifado(resultSet.getInt("cod_almoxarifado"));
				
				return pedidoCompraItemEmv;
			};

		}.getModel();
	}

		
	public void inserirparcela( Integer numpedido, Integer num_parcela, Integer qtd_dias_prazo, double val_parcela) throws Exception {

		new SqlWriteUtil(this.conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				LogUtils.escreverLogInfo("LOGSPILLER " ) ;
				LogUtils.escreverLogInfo("INSERT INTO tab_parcela_pedido_compra ( ");
				LogUtils.escreverLogInfo("seq_pedido, ");
				LogUtils.escreverLogInfo("num_parcela, ");
				LogUtils.escreverLogInfo("qtd_dias_prazo, ");
				LogUtils.escreverLogInfo("val_parcela, ");
				LogUtils.escreverLogInfo("seq_parcela) ");
				LogUtils.escreverLogInfo(" VALUES (?,?,?,?,?)");
				
				//insert into tab_parcela_pedido_compra( seq_pedido, num_parcela , qtd_dias_prazo  ,  val_parcela, seq_parcela)
				//values( ? , ? , ? , ? , ? )
				
				sql.append("INSERT INTO tab_parcela_pedido_compra ( ");
				sql.append("seq_pedido, ");
				sql.append("num_parcela, ");
				sql.append("qtd_dias_prazo, ");
				sql.append("val_parcela, ");
				sql.append("seq_parcela) ");
				sql.append(" VALUES (?,?,?,?,?)");
				
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				
				preparedStatement.setInt(1,numpedido);
				preparedStatement.setInt(2, num_parcela);
				preparedStatement.setInt(3, qtd_dias_prazo);
				preparedStatement.setDouble(4, val_parcela);
				preparedStatement.setInt(5, num_parcela);

			}
		}.execUpd();
	}
	
	
	public PedidoCompraItemEmv getNatureza( Integer cod_filial, Integer cod_produto) throws Exception {

		return new SqlReadUtil<PedidoCompraItemEmv>(this.conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
					
				LogUtils.escreverLogInfo("LOGSPILLER select  tab_item_empresa" ) ;
				sql.append(" select  tno.cod_natureza_operacao");
				sql.append(" from tab_item_empresa tbi ");
				sql.append(" left join tab_natureza_operacao tno on ");
				sql.append(" tbi.cod_nop_de_ent = tno.cod_natureza_operacao ");
				sql.append(" where tbi.cod_empresa = ? and  ");
				sql.append(" cod_item = ?  ");
				
				
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, cod_filial);
				preparedStatement.setInt(2, cod_produto);
			};

			public PedidoCompraItemEmv criarModel(ResultSet resultSet) throws Exception {

				PedidoCompraItemEmv pedidoCompraItemEmv = new PedidoCompraItemEmv();
				
				pedidoCompraItemEmv.setcod_natureza_operacao(resultSet.getInt("cod_natureza_operacao"));
				
				return pedidoCompraItemEmv;
			};

		}.getModel();
	}
	
	
	public PedidoCompraItemEmv getUnidade( Integer coditem) throws Exception {

		return new SqlReadUtil<PedidoCompraItemEmv>(this.conexaoEmsys) {
 
			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
					
				LogUtils.escreverLogInfo("LOGSPILLER select  cod_unidade from tab_unidade " ) ;
				sql.append(" select  case 	when cod_unidade_agrupamento_1 is null then cod_unidade ");
				sql.append(" when cod_unidade_agrupamento_1 = 0  then cod_unidade  ");
				sql.append(" else cod_unidade_agrupamento_1 end  as codigo_unidade ");
				sql.append(" from tab_item  ");
				sql.append(" where cod_item = ?   ");
				
				
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, coditem);
				//preparedStatement.setInt(2, cod_produto);
			};

			public PedidoCompraItemEmv criarModel(ResultSet resultSet) throws Exception {

				PedidoCompraItemEmv pedidoCompraItemEmv = new PedidoCompraItemEmv();
				
				pedidoCompraItemEmv.setcod_unidade(resultSet.getInt("codigo_unidade"));
				
				return pedidoCompraItemEmv;
			};

		}.getModel();
	}
	
	public PedidoCompraItemEmv getUnidConv( Integer cod_item) throws Exception {

		return new SqlReadUtil<PedidoCompraItemEmv>(this.conexaoEmsys) {
 
			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
					
				LogUtils.escreverLogInfo("LOGSPILLER select  cod_unidade from tab_unidade " ) ;
				sql.append(" select  qtd_unidade_agrupamento_1");
				sql.append(" from tab_item  ");
				sql.append(" where cod_item = ?   ");
				
				
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, cod_item);
				//preparedStatement.setInt(2, cod_produto);
			};

			public PedidoCompraItemEmv criarModel(ResultSet resultSet) throws Exception {

				PedidoCompraItemEmv pedidoCompraItemEmv = new PedidoCompraItemEmv();
				
				pedidoCompraItemEmv.setfator_conversao(resultSet.getDouble("qtd_unidade_agrupamento_1"));
				
				return pedidoCompraItemEmv;
			};

		}.getModel();
	}
	

}

	

package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;

public class ParametrosDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public ParametrosDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public Integer getUltCodigoPedidoCompra(Integer codigoEmpresa, Integer codigoFilial) throws Exception {
		return getParametroInt(codigoEmpresa, codigoFilial, "GEN_TABPAR_ProxPedCompra");
	}

	public void setUltCodigoPedidoCompra(Integer codigoEmpresa, Integer codigoFilial, Integer codPedidoCompra)
			throws Exception {

		this.setParametro(codigoEmpresa, codigoFilial, "GEN_TABPAR_ProxPedCompra", codPedidoCompra + 1);
	}

	private Integer getParametroInt(Integer codigoEmpresa, Integer codigoFilial, String nomeParam) throws Exception {

		return new SqlReadUtil<Integer>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ").append(nomeParam);
				sql.append(" from GEN_TABPAR ");
				sql.append(" where GEN_TABPAR_Empresa_Codigo = ? ");
				sql.append(" and   GEN_TABPAR_Filial_Codigo = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, codigoEmpresa);
				preparedStatement.setInt(2, codigoFilial);
			}

			@Override
			public Integer criarModel(ResultSet resultSet) throws Exception {
				return resultSet.getInt(nomeParam);
			}
		}.getModel();

	}

	private void setParametro(Integer codigoEmpresa, Integer codigoFilial, String nomeParam, Integer valorParam)
			throws Exception {

		new SqlWriteUtil(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" update GEN_TABPAR set ");
				sql.append(nomeParam).append(" = ? ");
				sql.append(" where GEN_TABPAR_Empresa_Codigo = ? ");
				sql.append(" and   GEN_TABPAR_Filial_Codigo = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, valorParam);
				preparedStatement.setInt(2, codigoEmpresa);
				preparedStatement.setInt(3, codigoFilial);
			}
		}.execUpd();
	}

}
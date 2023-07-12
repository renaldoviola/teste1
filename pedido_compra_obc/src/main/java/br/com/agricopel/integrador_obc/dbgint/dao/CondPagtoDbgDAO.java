package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CondPagtoDbg;

public class CondPagtoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CondPagtoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public CondPagtoDbg getByCodEmpresaCodCondPagto(Integer codEmpresa, String codCondPagtoObc) throws Exception {

		return new SqlReadUtil<CondPagtoDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    GEN_TABCPG_Codigo ");
				sql.append(" from GEN_TABCPG ");
				sql.append(" where GEN_TABEMP_Codigo = ? ");
				sql.append(" and   GEN_TABCPG_CodigoOBC = ? ");
				sql.append(" and   GEN_TABCPG_Ativo = 1 ");
			}

			public void setParameters(java.sql.PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, codEmpresa);
				preparedStatement.setString(2, codCondPagtoObc);

			};

			@Override
			public CondPagtoDbg criarModel(ResultSet resultSet) throws Exception {

				CondPagtoDbg condPagtoDbg = new CondPagtoDbg();

				condPagtoDbg.setGEN_TABCPG_Codigo(resultSet.getInt("GEN_TABCPG_Codigo"));

				return condPagtoDbg;
			}

		}.getModel();

	}

}

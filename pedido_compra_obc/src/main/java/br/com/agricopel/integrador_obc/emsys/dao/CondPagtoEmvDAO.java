package br.com.agricopel.integrador_obc.emsys.dao;

import java.sql.ResultSet;

import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.emsys.model.CondPagtoEmv;
import br.com.agricopel.comp.conexao.ConexaoEmsys;
//import br.com.agricopel.comp.utils.SqlReadUtil;
//import br.com.agricopel.integrador_obc.emsys.model.CondPagtoEmv;

public class CondPagtoEmvDAO {

	private ConexaoEmsys  conexaoEmsys;
	
	public CondPagtoEmvDAO(ConexaoEmsys conexaoEmsys) {
		this.conexaoEmsys= conexaoEmsys;
	}

	public CondPagtoEmv getByCodEmpresaCodCondPagto(Integer codEmpresa, String codCondPagtoObc) throws Exception {

		return new SqlReadUtil<CondPagtoEmv>(this.conexaoEmsys) {

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
			public CondPagtoEmv criarModel(ResultSet resultSet) throws Exception {

				CondPagtoEmv condPagtoEmv = new CondPagtoEmv();

				condPagtoEmv.setGEN_TABCPG_Codigo(resultSet.getInt("GEN_TABCPG_Codigo"));

				return condPagtoEmv;
			}

		}.getModel();

	}

}

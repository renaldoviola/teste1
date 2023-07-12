package br.com.agricopel.integrador_obc.emsys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.emsys.model.CentroCustoEmv;
//import br.com.agricopel.integrador_obc.Emsys.model.CentroCustoEmv;

public class CentroCustoEmvDAO {

	private ConexaoEmsys conexaoEmsys;

	public CentroCustoEmvDAO(ConexaoEmsys conexaoEmsys) {
		this.conexaoEmsys = conexaoEmsys;
	}

	public CentroCustoEmv getByCodigoEmpresaCodigo(Integer codEmpresa, String codigo) throws Exception {

		return new SqlReadUtil<CentroCustoEmv>(conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    GEN_TABCEN_Descricao, ");
				sql.append("    GEN_TABCEN_Ativo ");
				sql.append(" from GEN_TABCEN ");
				sql.append(" where GEN_TABEMP_Codigo = ? ");
				sql.append(" and   GEN_TABCEN_Codigo = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, codEmpresa);
				preparedStatement.setString(2, codigo);
			};

			@Override
			public CentroCustoEmv criarModel(ResultSet resultSet) throws Exception {

				CentroCustoEmv centroCustoEmv = new CentroCustoEmv();

				centroCustoEmv.setGEN_TABCEN_Descricao(resultSet.getString("GEN_TABCEN_Descricao"));
				centroCustoEmv.setGEN_TABCEN_Ativo(resultSet.getString("GEN_TABCEN_Ativo"));

				return centroCustoEmv;
			}

		}.getModel();
	}

}

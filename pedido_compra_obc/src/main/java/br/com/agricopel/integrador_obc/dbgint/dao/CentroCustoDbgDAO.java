package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.CentroCustoDbg;

public class CentroCustoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public CentroCustoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public CentroCustoDbg getByCodigoEmpresaCodigo(Integer codEmpresa, String codigo) throws Exception {

		return new SqlReadUtil<CentroCustoDbg>(conexaoDbGint) {

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
			public CentroCustoDbg criarModel(ResultSet resultSet) throws Exception {

				CentroCustoDbg centroCustoDbg = new CentroCustoDbg();

				centroCustoDbg.setGEN_TABCEN_Descricao(resultSet.getString("GEN_TABCEN_Descricao"));
				centroCustoDbg.setGEN_TABCEN_Ativo(resultSet.getString("GEN_TABCEN_Ativo"));

				return centroCustoDbg;
			}

		}.getModel();
	}

}

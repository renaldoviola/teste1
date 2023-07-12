package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.NaturezaOperacaoDbg;

public class NaturezaOperacaoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public NaturezaOperacaoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public NaturezaOperacaoDbg getByCodEmpresaCodigo(Integer codEmpresa, String codigo) throws Exception {

		return new SqlReadUtil<NaturezaOperacaoDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    GEN_NATOPE_Codigo, ");
				sql.append("    GEN_NATOPE_Descricao, ");
				sql.append("    GEN_NATOPE_Ativo ");
				sql.append(" from GEN_NATOPE ");
				sql.append(" where GEN_NATOPE.GEN_TABEMP_Codigo = ? ");
				sql.append(" and   GEN_NATOPE.GEN_NATOPE_Codigo = ? ");
			}

			public void setParameters(java.sql.PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, codEmpresa);
				preparedStatement.setString(2, codigo);
			};

			@Override
			public NaturezaOperacaoDbg criarModel(ResultSet resultSet) throws Exception {

				NaturezaOperacaoDbg naturezaOperacaoDbg = new NaturezaOperacaoDbg();

				naturezaOperacaoDbg.setGEN_NATOPE_Descricao(resultSet.getString("GEN_NATOPE_Descricao"));
				naturezaOperacaoDbg.setGEN_NATOPE_Ativo(resultSet.getString("GEN_NATOPE_Ativo"));

				return naturezaOperacaoDbg;
			}

		}.getModel();
	}

}

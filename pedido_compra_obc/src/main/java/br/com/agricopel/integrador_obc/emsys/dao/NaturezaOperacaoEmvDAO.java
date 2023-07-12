package br.com.agricopel.integrador_obc.emsys.dao;

import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.emsys.model.NaturezaOperacaoEmv;
//import br.com.agricopel.integrador_obc.Emvint.model.NaturezaOperacaoEmv;
//import br.com.agricopel.integrador_obc.emv.model.NaturezaOperacaoEmv;

public class NaturezaOperacaoEmvDAO {

	private ConexaoEmsys conexaoEmsys;

	public NaturezaOperacaoEmvDAO(ConexaoEmsys conexaoEmsys) {
		this.conexaoEmsys = conexaoEmsys;
	}

	public NaturezaOperacaoEmv getByCodEmpresaCodigo(Integer codEmpresa, String codigo) throws Exception {

		return new SqlReadUtil<NaturezaOperacaoEmv>(this.conexaoEmsys) {

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
			public NaturezaOperacaoEmv criarModel(ResultSet resultSet) throws Exception {

				NaturezaOperacaoEmv naturezaOperacaoEmv = new NaturezaOperacaoEmv();

				naturezaOperacaoEmv.setGEN_NATOPE_Descricao(resultSet.getString("GEN_NATOPE_Descricao"));
				naturezaOperacaoEmv.setGEN_NATOPE_Ativo(resultSet.getString("GEN_NATOPE_Ativo"));

				return naturezaOperacaoEmv;
			}

		}.getModel();
	}

}

package br.com.agricopel.integrador_obc.emsys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.utils.SqlReadUtil;
//import br.com.agricopel.integrador_obc.Emsys.model.EnderecoClienteFornecedorEmv;
import br.com.agricopel.integrador_obc.emsys.model.EnderecoClienteFornecedorEmv;

public class EnderecoClienteFornecedorEmvDAO {

	private ConexaoEmsys conexaoEmsys;

	public EnderecoClienteFornecedorEmvDAO(ConexaoEmsys conexaoEmsys) {
		this.conexaoEmsys = conexaoEmsys;
	}

	public EnderecoClienteFornecedorEmv getFornecedorByCnpj(String cnpj) throws Exception {

		return new SqlReadUtil<EnderecoClienteFornecedorEmv>(conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select cod_pessoa ");
				sql.append(" from tab_pessoa");
				sql.append(" where num_cnpj_cpf = ? ");
				sql.append(" and  ind_fornecedor = 'S' ");
				sql.append(" and  ind_pessoa_ativa = 'S' ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setString(1, cnpj);
			}

			@Override
			public EnderecoClienteFornecedorEmv criarModel(ResultSet resultSet) throws Exception {

				EnderecoClienteFornecedorEmv clienteFornecedorEmv = new EnderecoClienteFornecedorEmv();

				clienteFornecedorEmv.setcod_pessoa(resultSet.getLong("cod_pessoa"));
				//clienteFornecedorEmv.setGEN_TABENT_Simples(resultSet.getInt("GEN_TABENT_Simples"));
				//clienteFornecedorEmv.setGEN_ESTMUN_Estado(resultSet.getString("GEN_ESTMUN_Estado"));

				return clienteFornecedorEmv;
			}

		}.getModel();
	}

	public EnderecoClienteFornecedorEmv getTransportadorByCnpj(String cnpj) throws Exception {

		return new SqlReadUtil<EnderecoClienteFornecedorEmv>(conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    GEN_ENDENT.GEN_TABENT_Codigo, ");
				sql.append("    GEN_ENDENT.GEN_ENDENT_Codigo ");
				sql.append(" from GEN_ENDENT, GEN_TABENT ");
				sql.append(" where GEN_ENDENT.GEN_ENDENT_IF = ? ");
				sql.append(" and   GEN_ENDENT.GEN_TABENT_Codigo = GEN_TABENT.GEN_TABENT_Codigo ");
				sql.append(" and   GEN_TABENT.GEN_TABENT_Ativo = 1 ");
				sql.append(" and   GEN_ENDENT.GEN_ENDENT_Ativo = 1 ");
				sql.append(" and   GEN_TABENT.GEN_TABENT_Transp = 1 ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setString(1, cnpj);
			}

			@Override
			public EnderecoClienteFornecedorEmv criarModel(ResultSet resultSet) throws Exception {

				EnderecoClienteFornecedorEmv clienteFornecedorEmv = new EnderecoClienteFornecedorEmv();

				clienteFornecedorEmv.setGEN_TABENT_Codigo(resultSet.getLong("GEN_TABENT_Codigo"));
				clienteFornecedorEmv.setGEN_ENDENT_Codigo(resultSet.getLong("GEN_ENDENT_Codigo"));

				return clienteFornecedorEmv;
			}

		}.getModel();
	}

}

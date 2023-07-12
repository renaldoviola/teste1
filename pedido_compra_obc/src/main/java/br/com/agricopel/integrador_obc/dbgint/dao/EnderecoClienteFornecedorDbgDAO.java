package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.EnderecoClienteFornecedorDbg;

public class EnderecoClienteFornecedorDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public EnderecoClienteFornecedorDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public EnderecoClienteFornecedorDbg getFornecedorByCnpj(String cnpj) throws Exception {

		return new SqlReadUtil<EnderecoClienteFornecedorDbg>(conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    GEN_ENDENT.GEN_TABENT_Codigo, ");
				sql.append("    GEN_ENDENT.GEN_ENDENT_Codigo, ");
				sql.append("    GEN_TABENT.GEN_TABENT_Simples, ");
				sql.append("    GEN_ENDENT.GEN_ESTMUN_Estado ");
				sql.append(" from GEN_ENDENT, GEN_TABENT ");
				sql.append(" where GEN_ENDENT.GEN_ENDENT_IF = ? ");
				sql.append(" and   GEN_ENDENT.GEN_TABENT_Codigo = GEN_TABENT.GEN_TABENT_Codigo ");
				sql.append(" and   GEN_TABENT.GEN_TABENT_Ativo = 1 ");
				sql.append(" and   GEN_ENDENT.GEN_ENDENT_Ativo = 1 ");
				sql.append(" and   GEN_TABENT.GEN_TABENT_Forn = 1 ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setString(1, cnpj);
			}

			@Override
			public EnderecoClienteFornecedorDbg criarModel(ResultSet resultSet) throws Exception {

				EnderecoClienteFornecedorDbg clienteFornecedorDbg = new EnderecoClienteFornecedorDbg();

				clienteFornecedorDbg.setGEN_TABENT_Codigo(resultSet.getLong("GEN_TABENT_Codigo"));
				clienteFornecedorDbg.setGEN_ENDENT_Codigo(resultSet.getLong("GEN_ENDENT_Codigo"));
				clienteFornecedorDbg.setGEN_TABENT_Simples(resultSet.getInt("GEN_TABENT_Simples"));
				clienteFornecedorDbg.setGEN_ESTMUN_Estado(resultSet.getString("GEN_ESTMUN_Estado"));

				return clienteFornecedorDbg;
			}

		}.getModel();
	}

	public EnderecoClienteFornecedorDbg getTransportadorByCnpj(String cnpj) throws Exception {

		return new SqlReadUtil<EnderecoClienteFornecedorDbg>(conexaoDbGint) {

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
			public EnderecoClienteFornecedorDbg criarModel(ResultSet resultSet) throws Exception {

				EnderecoClienteFornecedorDbg clienteFornecedorDbg = new EnderecoClienteFornecedorDbg();

				clienteFornecedorDbg.setGEN_TABENT_Codigo(resultSet.getLong("GEN_TABENT_Codigo"));
				clienteFornecedorDbg.setGEN_ENDENT_Codigo(resultSet.getLong("GEN_ENDENT_Codigo"));

				return clienteFornecedorDbg;
			}

		}.getModel();
	}

}

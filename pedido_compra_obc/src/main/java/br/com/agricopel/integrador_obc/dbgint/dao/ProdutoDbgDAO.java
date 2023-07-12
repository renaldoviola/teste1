package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.ProdutoDbg;

public class ProdutoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public ProdutoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public ProdutoDbg getByCodigo(Integer codEmpresa, String codProduto) throws Exception {

		return new SqlReadUtil<ProdutoDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select EST_TABDEP_Codigo, ");
				sql.append("        EST_TABPRO_Ativo ");
				sql.append(" from EST_TABPRO ");
				sql.append(" where GEN_TABEMP_Codigo = ? ");
				sql.append(" and   EST_TABPRO_Codigo = ? ");
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, codEmpresa);
				preparedStatement.setString(2, codProduto);
			};

			@Override
			public ProdutoDbg criarModel(ResultSet resultSet) throws Exception {

				ProdutoDbg produtoDbg = new ProdutoDbg();

				produtoDbg.setEST_TABDEP_Codigo(resultSet.getInt("EST_TABDEP_Codigo"));
				produtoDbg.setEST_TABPRO_Ativo(resultSet.getString("EST_TABPRO_Ativo"));

				return produtoDbg;
			}

		}.getModel();
	}

}

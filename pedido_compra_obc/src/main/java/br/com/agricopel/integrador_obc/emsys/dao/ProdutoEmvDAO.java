package br.com.agricopel.integrador_obc.emsys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.emsys.model.ProdutoEmv;

public class ProdutoEmvDAO {

	private ConexaoEmsys conexaoEmsys;

	public ProdutoEmvDAO(ConexaoEmsys conexaoEmsys) {
		this.conexaoEmsys = conexaoEmsys;
	}

	public ProdutoEmv getByCodigo(/*Integer codEmpresa,*/ String codProduto) throws Exception {

		return new SqlReadUtil<ProdutoEmv>(this.conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select cod_item ");
				sql.append(" from tab_item ");
				sql.append(" where cod_item = ? ");
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				//preparedStatement.setInt(1, codEmpresa);
				preparedStatement.setInt(1, Integer.parseInt(codProduto));
			};

			@Override
			public ProdutoEmv criarModel(ResultSet resultSet) throws Exception {

				ProdutoEmv produtoEmv = new ProdutoEmv();

				produtoEmv.setcod_item(resultSet.getInt("cod_item"));
				//produtoEmv.setEST_TABPRO_Ativo(resultSet.getString("EST_TABPRO_Ativo"));

				return produtoEmv;
			}

		}.getModel();
	}

}

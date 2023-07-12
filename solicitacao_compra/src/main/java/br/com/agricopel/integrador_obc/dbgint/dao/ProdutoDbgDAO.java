package br.com.agricopel.integrador_obc.dbgint.dao;

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

				sql.append(" select EST_TABPRO_Codigo, ");
				sql.append("        EST_TABDEP_Codigo, ");
				sql.append("        EST_TABPRO_Ativo, ");
				sql.append("        EST_TABPRO_Descricao, ");
				sql.append("        EST_UNIMED_Codigo, ");

				sql.append("        case when EST_TABPRO_ContaCusto != '' ");
				sql.append("            then EST_TABPRO_ContaCusto ");
				sql.append("            else ");
				sql.append("               COALESCE((SELECT EST_CLAPRO_ContaCusto ");
				sql.append("                         FROM EST_CLAPRO CLAPRO ");
				sql.append("                         WHERE CLAPRO.GEN_TABEMP_Codigo = EST_TABPRO.GEN_TABEMP_Codigo ");
				sql.append("                         AND   CLAPRO.EST_CLAPRO_Codigo = EST_TABPRO.EST_CLAPRO_Codigo ");
				sql.append("                         AND   CLAPRO.EST_SGRPRO_Codigo = EST_TABPRO.EST_SGRPRO_Codigo ");
				sql.append("                         AND   CLAPRO.EST_GRPPRO_Codigo = EST_TABPRO.EST_GRPPRO_Codigo ");
				sql.append("               	        AND   EST_CLAPRO_ContaCusto != '' ");
				sql.append("               ), ");
				sql.append("                  COALESCE((SELECT EST_SGRPRO_ContaCusto ");
				sql.append("                            FROM EST_SGRPRO SGRPRO ");
				sql.append(
						"                            WHERE SGRPRO.GEN_TABEMP_Codigo = EST_TABPRO.GEN_TABEMP_Codigo ");
				sql.append(
						"                            AND   SGRPRO.EST_SGRPRO_Codigo = EST_TABPRO.EST_SGRPRO_Codigo ");
				sql.append(
						"                            AND   SGRPRO.EST_GRPPRO_Codigo = EST_TABPRO.EST_GRPPRO_Codigo ");
				sql.append("                            AND   EST_SGRPRO_ContaCusto != '' ");
				sql.append("                  ), COALESCE((SELECT EST_GRPPRO_ContaCusto ");
				sql.append("                               FROM EST_GRPPRO GRPPRO ");
				sql.append(
						"                               WHERE GRPPRO.GEN_TABEMP_Codigo = EST_TABPRO.GEN_TABEMP_Codigo ");
				sql.append(
						"                               AND   GRPPRO.EST_GRPPRO_Codigo = EST_TABPRO.EST_GRPPRO_Codigo ");
				sql.append("                     ), '') ");
				sql.append("                  ) ");
				sql.append("               ) ");
				sql.append("        end as EST_TABPRO_ContaCusto ");

				sql.append(" from EST_TABPRO ");
				sql.append(" where GEN_TABEMP_Codigo = ? ");
				sql.append(" and   EST_TABPRO_Codigo = ? ");
			}

			public void setParameters(java.sql.PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, codEmpresa);
				preparedStatement.setString(2, codProduto);
			};

			@Override
			public ProdutoDbg criarModel(ResultSet resultSet) throws Exception {

				ProdutoDbg produtoDbg = new ProdutoDbg();

				produtoDbg.setEST_TABPRO_Codigo(resultSet.getString("EST_TABPRO_Codigo"));
				produtoDbg.setEST_TABDEP_Codigo(resultSet.getInt("EST_TABDEP_Codigo"));
				produtoDbg.setEST_TABPRO_Ativo(resultSet.getString("EST_TABPRO_Ativo"));
				produtoDbg.setEST_TABPRO_ContaCusto(resultSet.getString("EST_TABPRO_ContaCusto"));
				produtoDbg.setEST_TABPRO_Descricao(resultSet.getString("EST_TABPRO_Descricao"));
				produtoDbg.setEST_UNIMED_Codigo(resultSet.getString("EST_UNIMED_Codigo"));

				return produtoDbg;
			}

		}.getModel();
	}

}

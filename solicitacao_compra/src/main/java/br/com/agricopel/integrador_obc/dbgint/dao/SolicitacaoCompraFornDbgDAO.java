package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraFornDbg;

public class SolicitacaoCompraFornDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public SolicitacaoCompraFornDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public SolicitacaoCompraFornDbg getBySolicitacao(Integer STG_GEN_TABEMP_Codigo, Integer STG_GEN_TABFIL_Codigo,
			Long COM_SOLICI_Numero) throws Exception {

		DateUtil dateUtil = new DateUtil();

		return new SqlReadUtil<SolicitacaoCompraFornDbg>(conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select  ");
				sql.append("    COM_ENTSOL.STG_GEN_TABEMP_Codigo,  ");
				sql.append("    COM_ENTSOL.STG_GEN_TABFIL_Codigo,  ");
				sql.append("    COM_ENTSOL.COM_SOLICI_Numero,  ");
				sql.append("    COM_ENTSOL.GEN_TABENT_Codigo,  ");
				sql.append("    COM_ENTSOL.GEN_ENDENT_Codigo,  ");
				sql.append("    COM_ENTSOL.COM_ENTSOL_Created,  ");
				sql.append("    COM_ENTSOL.COM_ENTSOL_Updated,  ");
				sql.append("    COM_ENTSOL.COM_ENTSOL_Sugerido,  ");
				sql.append("    GEN_ENDENT.GEN_ENDENT_IF  ");
				sql.append(" from COM_ENTSOL, GEN_ENDENT  ");
				sql.append(" where COM_ENTSOL.STG_GEN_TABEMP_Codigo = ?  ");
				sql.append(" and   COM_ENTSOL.STG_GEN_TABFIL_Codigo = ?  ");
				sql.append(" and   COM_ENTSOL.COM_SOLICI_Numero = ?  ");
				sql.append(" and   COM_ENTSOL.GEN_TABENT_Codigo = GEN_ENDENT.GEN_TABENT_Codigo  ");
				sql.append(" and   COM_ENTSOL.GEN_ENDENT_Codigo = GEN_ENDENT.GEN_ENDENT_Codigo  ");
			}

			public void setParameters(java.sql.PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, STG_GEN_TABEMP_Codigo);
				preparedStatement.setInt(2, STG_GEN_TABFIL_Codigo);
				preparedStatement.setLong(3, COM_SOLICI_Numero);
			};

			@Override
			public SolicitacaoCompraFornDbg criarModel(ResultSet resultSet) throws Exception {

				SolicitacaoCompraFornDbg solicitacaoCompraFornDbg = new SolicitacaoCompraFornDbg();

				solicitacaoCompraFornDbg.setSTG_GEN_TABEMP_Codigo(resultSet.getInt("STG_GEN_TABEMP_Codigo"));
				solicitacaoCompraFornDbg.setSTG_GEN_TABFIL_Codigo(resultSet.getInt("STG_GEN_TABEMP_Codigo"));
				solicitacaoCompraFornDbg.setCOM_SOLICI_Numero(resultSet.getLong("COM_SOLICI_Numero"));
				solicitacaoCompraFornDbg.setGEN_TABENT_Codigo(resultSet.getLong("GEN_TABENT_Codigo"));
				solicitacaoCompraFornDbg.setGEN_ENDENT_Codigo(resultSet.getInt("GEN_ENDENT_Codigo"));
				solicitacaoCompraFornDbg
						.setCOM_ENTSOL_Created(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_ENTSOL_Created")));
				solicitacaoCompraFornDbg
						.setCOM_ENTSOL_Updated(dateUtil.toLocalDateTime(resultSet.getTimestamp("COM_ENTSOL_Updated")));
				solicitacaoCompraFornDbg.setCOM_ENTSOL_Sugerido(resultSet.getString("COM_ENTSOL_Sugerido"));
				solicitacaoCompraFornDbg.setCnpj(resultSet.getString("GEN_ENDENT_IF"));

				return solicitacaoCompraFornDbg;
			}

		}.getModel();

	}

}

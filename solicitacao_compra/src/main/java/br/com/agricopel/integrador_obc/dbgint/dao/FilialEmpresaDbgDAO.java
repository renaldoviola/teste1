package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.FilialEmpresaDbg;

public class FilialEmpresaDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public FilialEmpresaDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public FilialEmpresaDbg getByCodigo(Integer GEN_TABEMP_Codigo, Integer GEN_TABFIL_Codigo) throws Exception {

		return new SqlReadUtil<FilialEmpresaDbg>(conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    GEN_TABEMP_Codigo, ");
				sql.append("    GEN_TABFIL_Codigo, ");
				sql.append("    GEN_TABFIL_Descricao, ");
				sql.append("    GEN_TABFIL.GEN_ESTMUN_Estado, ");
				sql.append("    GEN_TABFIL.GEN_TABFIL_CNPJ ");
				sql.append(" from GEN_TABFIL ");
				sql.append(" where GEN_TABEMP_Codigo = ? ");
				sql.append(" and   GEN_TABFIL_Codigo = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, GEN_TABEMP_Codigo);
				preparedStatement.setInt(2, GEN_TABFIL_Codigo);
			}

			@Override
			public FilialEmpresaDbg criarModel(ResultSet resultSet) throws Exception {

				FilialEmpresaDbg filialEmpresaDbg = new FilialEmpresaDbg();

				filialEmpresaDbg.setGEN_TABEMP_Codigo(resultSet.getLong("GEN_TABEMP_Codigo"));
				filialEmpresaDbg.setGEN_TABFIL_Codigo(resultSet.getLong("GEN_TABFIL_Codigo"));
				filialEmpresaDbg.setGEN_TABFIL_Descricao(resultSet.getString("GEN_TABFIL_Descricao"));
				filialEmpresaDbg.setGEN_ESTMUN_Estado(resultSet.getString("GEN_ESTMUN_Estado"));
				filialEmpresaDbg.setGEN_TABFIL_CNPJ(resultSet.getString("GEN_TABFIL_CNPJ"));

				return filialEmpresaDbg;
			}

		}.getModel();
	}

}

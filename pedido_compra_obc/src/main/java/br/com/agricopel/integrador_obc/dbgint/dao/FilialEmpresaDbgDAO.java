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

	public FilialEmpresaDbg getByCnpj(String cnpj) throws Exception {

		return new SqlReadUtil<FilialEmpresaDbg>(conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    GEN_TABEMP_Codigo, ");
				sql.append("    GEN_TABFIL_Codigo, ");
				sql.append("    GEN_TABFIL_Descricao, ");
				sql.append("    GEN_TABFIL.GEN_ESTMUN_Estado ");
				sql.append(" from GEN_TABFIL ");
				sql.append(" where GEN_TABFIL_CNPJ = ? ");
				sql.append(" and   GEN_TABFIL_Ativo = 1 ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setString(1, cnpj);
			}

			@Override
			public FilialEmpresaDbg criarModel(ResultSet resultSet) throws Exception {

				FilialEmpresaDbg filialEmpresaDbg = new FilialEmpresaDbg();

				filialEmpresaDbg.setGEN_TABEMP_Codigo(resultSet.getInt("GEN_TABEMP_Codigo"));
				filialEmpresaDbg.setGEN_TABFIL_Codigo(resultSet.getInt("GEN_TABFIL_Codigo"));
				filialEmpresaDbg.setGEN_TABFIL_Descricao(resultSet.getString("GEN_TABFIL_Descricao"));
				filialEmpresaDbg.setGEN_ESTMUN_Estado(resultSet.getString("GEN_ESTMUN_Estado"));

				return filialEmpresaDbg;
			}

		}.getModel();
	}

}

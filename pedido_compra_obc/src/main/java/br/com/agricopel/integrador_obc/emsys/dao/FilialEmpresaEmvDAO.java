package br.com.agricopel.integrador_obc.emsys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoEmsys;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.emsys.model.FilialEmpresaEmv;

public class FilialEmpresaEmvDAO {

	private ConexaoEmsys conexaoEmsys;

	public FilialEmpresaEmvDAO(ConexaoEmsys conexaoEmsys) {
		this.conexaoEmsys = conexaoEmsys;
	}

	public FilialEmpresaEmv getByCnpj(String cnpj) throws Exception {

		return new SqlReadUtil<FilialEmpresaEmv>(conexaoEmsys) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    cod_empresa, nom_fantasia as descri_empresa  ");
				sql.append(" from tab_empresa ");
				sql.append(" where num_cnpj = ? ");

			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setString(1, cnpj);
			}

			@Override
			public FilialEmpresaEmv criarModel(ResultSet resultSet) throws Exception {

				FilialEmpresaEmv filialEmpresaEmv = new FilialEmpresaEmv();

				filialEmpresaEmv.setcod_empresa(resultSet.getInt("cod_empresa"));
				//filialEmpresaEmv.setGEN_TABFIL_Codigo(resultSet.getInt("GEN_TABFIL_Codigo"));
				filialEmpresaEmv.setdescri_empresa(resultSet.getString("descri_empresa"));
				//filialEmpresaEmv.setestado_empresa(resultSet.getString("estado_empresa"));
				
				/*private Integer cod_empresa;
				//private Integer GEN_TABFIL_Codigo;
				private String descri_empresa;
				private String estado_empresa;*/

				return filialEmpresaEmv;
			}

		}.getModel();
	}

}

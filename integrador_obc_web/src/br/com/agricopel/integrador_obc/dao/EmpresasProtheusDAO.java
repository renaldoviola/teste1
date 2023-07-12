package br.com.agricopel.integrador_obc.dao;

import java.sql.ResultSet;
import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoProtheus;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;
import br.com.agricopel.integrador_obc.utils.SqlReadUtil;

public class EmpresasProtheusDAO {

	public List<CfgEmpresaModel> getEmpresasProtheus(ConexaoProtheus conexaoProtheus) throws Exception {

		return new SqlReadUtil<CfgEmpresaModel>(conexaoProtheus) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" SELECT "); 
				sql.append("    DISTINCT(EMP_COD) AS EMP_COD, ");
				sql.append("    EMP_RAZAO ");
				sql.append(" FROM EMPRESAS ");
				sql.append(" WHERE EMP_FIL = '01' ");
			}

			@Override
			public CfgEmpresaModel criarModel(ResultSet resultSet) throws Exception {

				CfgEmpresaModel cfgEmpresaModel = new CfgEmpresaModel();
				
				cfgEmpresaModel.setCodProtheus(resultSet.getString("EMP_COD"));
				cfgEmpresaModel.setDescricao(resultSet.getString("EMP_RAZAO"));
				cfgEmpresaModel.setStatus("I");
				
				return cfgEmpresaModel;
			}

		}.getList();
	}

}

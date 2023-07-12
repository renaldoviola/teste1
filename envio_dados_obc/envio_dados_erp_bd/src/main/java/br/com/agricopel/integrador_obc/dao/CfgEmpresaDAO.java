package br.com.agricopel.integrador_obc.dao;

import java.sql.ResultSet;
import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;

public class CfgEmpresaDAO {

	private ConexaoIntegracaoOBC conexaoIntegracaoOBC;

	public CfgEmpresaDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
	}

	public List<CfgEmpresaModel> getEmpresasAtivas() throws Exception {

		return new SqlReadUtil<CfgEmpresaModel>(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    id, ");
				sql.append("    trim(cod_protheus) as cod_protheus, ");
				sql.append("    descricao, ");
				sql.append("    status ");
				sql.append(" from cfg_empresa ");
				sql.append(" where status = 'A' ");
				sql.append(" order by cod_protheus ");
			}

			@Override
			public CfgEmpresaModel criarModel(ResultSet resultSet) throws Exception {

				CfgEmpresaModel cfgEmpresaModel = new CfgEmpresaModel();

				cfgEmpresaModel.setId(resultSet.getInt("id"));
				cfgEmpresaModel.setCodProtheus(resultSet.getString("cod_protheus"));
				cfgEmpresaModel.setDescricao(resultSet.getString("descricao"));
				cfgEmpresaModel.setStatus(resultSet.getString("status"));

				return cfgEmpresaModel;
			}

		}.getList();
	}

}

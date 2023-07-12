package br.com.agricopel.integrador_obc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;
import br.com.agricopel.integrador_obc.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.utils.SqlWriteUtil;

public class CfgEmpresaDAO {

	private ConexaoIntegracaoOBC conexaoIntegracaoOBC;

	public CfgEmpresaDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
	}

	public CfgEmpresaModel getEmpresa(Integer id) throws Exception {

		return new SqlReadUtil<CfgEmpresaModel>(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    id, ");
				sql.append("    cod_protheus, ");
				sql.append("    descricao, ");
				sql.append("    status ");
				sql.append(" from cfg_empresa ");
				sql.append(" where id = ? ");
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

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, id);
			}

		}.getModel();
	}

	public List<CfgEmpresaModel> getEmpresas() throws Exception {

		return new SqlReadUtil<CfgEmpresaModel>(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    id, ");
				sql.append("    cod_protheus, ");
				sql.append("    descricao, ");
				sql.append("    status ");
				sql.append(" from cfg_empresa ");
				sql.append(" order by status, id ");
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

	public void gravar(CfgEmpresaModel cfgEmpresaModel) throws Exception {

		if (cfgEmpresaModel.getId() != null && cfgEmpresaModel.getId() > 0) {
			atualizar(cfgEmpresaModel);
		} else {
			inserir(cfgEmpresaModel);
		}
	}

	private void inserir(CfgEmpresaModel cfgEmpresaModel) throws Exception {

		new SqlWriteUtil(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" insert into cfg_empresa (cod_protheus, descricao, status) ");
				sql.append(" values (?,?,?)  ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;

				preparedStatement.setString(++count, cfgEmpresaModel.getCodProtheus());
				preparedStatement.setString(++count, cfgEmpresaModel.getDescricao());
				preparedStatement.setString(++count, cfgEmpresaModel.getStatus());
			}

		}.exec();
	}

	private void atualizar(CfgEmpresaModel cfgEmpresaModel) throws Exception {

		new SqlWriteUtil(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" update cfg_empresa set ");
				sql.append("    cod_protheus = ?, ");
				sql.append("    descricao = ?, ");
				sql.append("    status = ? ");
				sql.append(" where id = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;

				preparedStatement.setString(++count, cfgEmpresaModel.getCodProtheus());
				preparedStatement.setString(++count, cfgEmpresaModel.getDescricao());
				preparedStatement.setString(++count, cfgEmpresaModel.getStatus());
				preparedStatement.setInt(++count, cfgEmpresaModel.getId());
			}
		}.exec();
	}

	public CfgEmpresaModel getCodProtheus(String codProtheus) throws Exception {

		return new SqlReadUtil<CfgEmpresaModel>(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    id, ");
				sql.append("    cod_protheus, ");
				sql.append("    descricao, ");
				sql.append("    status ");
				sql.append(" from cfg_empresa ");
				sql.append(" where cod_protheus = ? ");
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

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setString(1, codProtheus);
			}

		}.getModel();
	}

}

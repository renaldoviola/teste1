package br.com.agricopel.integrador_obc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;
import br.com.agricopel.integrador_obc.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.utils.SqlWriteUtil;

public class CfgCargaEmpresaDAO {

	private ConexaoIntegracaoOBC conexaoIntegracaoOBC;

	public CfgCargaEmpresaDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
	}

	public void gravar(CfgCargaEmpresaModel cfgCargaEmpresa) throws Exception {

		if (getCargaEmpresa(cfgCargaEmpresa.getIdCarga(), cfgCargaEmpresa.getIdEmpresa()) != null) {
			atualizar(cfgCargaEmpresa);
		} else {
			inserir(cfgCargaEmpresa);
		}
	}

	public CfgCargaEmpresaModel getCargaEmpresa(Integer idCarga, Integer idEmpresa) throws Exception {

		return new SqlReadUtil<CfgCargaEmpresaModel>(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				selectAll(sql);

				sql.append(" from cfg_carga_empresa ");
				sql.append(" where cfg_carga_empresa.id_carga = ? ");
				sql.append(" and   cfg_carga_empresa.id_empresa = ? ");
			}

			@Override
			public CfgCargaEmpresaModel criarModel(ResultSet resultSet) throws Exception {

				CfgCargaEmpresaModel cfgCargaEmpresa = new CfgCargaEmpresaModel();
				todosCampos(cfgCargaEmpresa, resultSet);

				return cfgCargaEmpresa;
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, idCarga);
				preparedStatement.setInt(2, idEmpresa);
			};

		}.getModel();
	}

	private void inserir(CfgCargaEmpresaModel cfgCargaEmpresa) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
				sql.append(" insert into cfg_carga_empresa ");
				sql.append("    (id_carga, id_empresa, sequencia, status) ");
				sql.append(" values (?,?,?,?) ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;

				preparedStatement.setInt(++count, cfgCargaEmpresa.getIdCarga());
				preparedStatement.setInt(++count, cfgCargaEmpresa.getIdEmpresa());
				preparedStatement.setInt(++count, cfgCargaEmpresa.getSequencia());
				preparedStatement.setString(++count, cfgCargaEmpresa.getStatus());
			}

		}.exec();
	}

	private void atualizar(CfgCargaEmpresaModel cfgCargaEmpresa) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
				sql.append(" update cfg_carga_empresa set ");
				sql.append("    sequencia = ?, ");
				sql.append("    status = ? ");
				sql.append(" where cfg_carga_empresa.id_carga = ? ");
				sql.append(" and   cfg_carga_empresa.id_empresa = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				Integer count = 1;

				preparedStatement.setInt(count++, cfgCargaEmpresa.getSequencia());
				preparedStatement.setString(count++, cfgCargaEmpresa.getStatus());
				preparedStatement.setInt(count++, cfgCargaEmpresa.getIdCarga());
				preparedStatement.setInt(count++, cfgCargaEmpresa.getIdEmpresa());
			}

		}.exec();
	}
	
	public void zerar(CfgCargaEmpresaModel cfgCargaEmpresa) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
				sql.append(" update cfg_carga_empresa set ");
				sql.append("    ciclo_change_tracking = null, ");
				sql.append("    ciclo_data_hora = null ");
				sql.append(" where cfg_carga_empresa.id_carga = ? ");
				sql.append(" and   cfg_carga_empresa.id_empresa = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				Integer count = 1;

				preparedStatement.setInt(count++, cfgCargaEmpresa.getIdCarga());
				preparedStatement.setInt(count++, cfgCargaEmpresa.getIdEmpresa());
			}

		}.exec();
	}

	private void selectAll(StringBuilder sql) {

		sql.append(" select ");
		sql.append("   id, ");
		sql.append("   id_carga, ");
		sql.append("   id_empresa, ");
		sql.append("   status, ");
		sql.append("   ciclo_change_tracking, ");
		sql.append("   ciclo_data_hora, ");
		sql.append("   sequencia ");
	}

	private void todosCampos(CfgCargaEmpresaModel cfgCargaEmpresa, ResultSet resultSet) throws Exception {

		cfgCargaEmpresa.setId(resultSet.getInt("id"));
		cfgCargaEmpresa.setIdCarga(resultSet.getInt("id_carga"));
		cfgCargaEmpresa.setIdEmpresa(resultSet.getInt("id_empresa"));
		cfgCargaEmpresa.setStatus(resultSet.getString("status"));

		if (resultSet.getLong("ciclo_change_tracking") > 0) {
			cfgCargaEmpresa.setCicloChangeTracking(resultSet.getLong("ciclo_change_tracking"));
		}

		if (resultSet.getTimestamp("ciclo_data_hora") != null) {
			cfgCargaEmpresa.setCicloDataHora(resultSet.getTimestamp("ciclo_data_hora").toLocalDateTime());
		}

		cfgCargaEmpresa.setSequencia(resultSet.getInt("sequencia"));
	}

}

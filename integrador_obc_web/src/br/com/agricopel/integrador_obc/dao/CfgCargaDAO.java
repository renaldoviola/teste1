package br.com.agricopel.integrador_obc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.model.CfgCargaModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.SoftwareEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.TipoCicloEnum;
import br.com.agricopel.integrador_obc.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.utils.SqlWriteUtil;

public class CfgCargaDAO {

	private ConexaoIntegracaoOBC conexaoIntegracaoOBC;

	public CfgCargaDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
	}

	public List<CfgCargaModel> getCfgCargas(boolean somenteAtivas) throws Exception {

		return new SqlReadUtil<CfgCargaModel>(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				selectAll(sql);
				sql.append(" from cfg_carga ");
				
				if (somenteAtivas) {
					sql.append(" where status = 'A' ");
				}
				
				sql.append(" order by software, entidade, tipo_ciclo, status, descricao ");
			}

			@Override
			public CfgCargaModel criarModel(ResultSet resultSet) throws Exception {

				CfgCargaModel cfgCargaModel = new CfgCargaModel();

				todosCampos(cfgCargaModel, resultSet);

				return cfgCargaModel;
			}

		}.getList();
	}

	public CfgCargaModel getCarga(Integer id) throws Exception {

		return new SqlReadUtil<CfgCargaModel>(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				selectAll(sql);
				sql.append(" from cfg_carga ");
				sql.append(" where cfg_carga.id = ? ");
			}

			@Override
			public CfgCargaModel criarModel(ResultSet resultSet) throws Exception {

				CfgCargaModel cfgCargaModel = new CfgCargaModel();

				todosCampos(cfgCargaModel, resultSet);

				return cfgCargaModel;
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, id);
			}

		}.getModel();
	}

	private void selectAll(StringBuilder sql) {

		sql.append(" select ");
		sql.append("   cfg_carga.id, ");
		sql.append("   cfg_carga.descricao, ");
		sql.append("   cfg_carga.status, ");
		sql.append("   cfg_carga.entidade, ");
		sql.append("   cfg_carga.sql_carga, ");
		sql.append("   cfg_carga.software, ");
		sql.append("   cfg_carga.tipo_ciclo ");
	}

	private void todosCampos(CfgCargaModel cfgCargaModel, ResultSet resultSet) throws Exception {

		cfgCargaModel.setId(resultSet.getInt("id"));
		cfgCargaModel.setDescricao(resultSet.getString("descricao"));
		cfgCargaModel.setStatus(resultSet.getString("status"));
		cfgCargaModel.setEntidade(EntidadeObcEnum.valueOf(resultSet.getString("entidade")));
		cfgCargaModel.setSqlCarga(resultSet.getString("sql_carga"));
		cfgCargaModel.setSoftware(SoftwareEnum.valueOf(resultSet.getString("software")));
		cfgCargaModel.setTipoCiclo(TipoCicloEnum.valueOf(resultSet.getString("tipo_ciclo")));
	}

	public void gravar(CfgCargaModel cfgCargaModel) throws Exception {

		if (cfgCargaModel.getId() > 0) {
			atualizar(cfgCargaModel);
		} else {
			inserir(cfgCargaModel);
		}
	}

	private void atualizar(CfgCargaModel cfgCargaModel) throws Exception {

		new SqlWriteUtil(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" update cfg_carga set ");
				sql.append("    descricao = ?, ");
				sql.append("    status = ?, ");
				sql.append("    entidade = ?, ");
				sql.append("    sql_carga = ?, ");
				sql.append("    software = ?, ");
				sql.append("    tipo_ciclo = ? ");
				sql.append(" where id = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;

				preparedStatement.setString(++count, cfgCargaModel.getDescricao());
				preparedStatement.setString(++count, cfgCargaModel.getStatus());
				preparedStatement.setString(++count, cfgCargaModel.getEntidade().name());
				preparedStatement.setString(++count, cfgCargaModel.getSqlCarga());
				preparedStatement.setString(++count, cfgCargaModel.getSoftware().name());
				preparedStatement.setString(++count, cfgCargaModel.getTipoCiclo().name());
				preparedStatement.setInt(++count, cfgCargaModel.getId());
			}
		}.exec();
	}

	private void inserir(CfgCargaModel cfgCargaModel) throws Exception {

		new SqlWriteUtil(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" insert into cfg_carga (descricao, status, entidade, sql_carga, software, tipo_ciclo) ");
				sql.append(" values (?,?,?,?,?,?) ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				int count = 0;

				preparedStatement.setString(++count, cfgCargaModel.getDescricao());
				preparedStatement.setString(++count, cfgCargaModel.getStatus());
				preparedStatement.setString(++count, cfgCargaModel.getEntidade().name());
				preparedStatement.setString(++count, cfgCargaModel.getSqlCarga());
				preparedStatement.setString(++count, cfgCargaModel.getSoftware().name());
				preparedStatement.setString(++count, cfgCargaModel.getTipoCiclo().name());
			}
		}.exec();
	}

}

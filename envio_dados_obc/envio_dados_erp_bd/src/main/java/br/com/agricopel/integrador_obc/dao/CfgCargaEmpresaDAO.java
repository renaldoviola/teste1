package br.com.agricopel.integrador_obc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.comp.utils.SqlWriteUtil;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;
import br.com.agricopel.integrador_obc.model.CfgCargaModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.SoftwareEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.TipoCicloEnum;

public class CfgCargaEmpresaDAO {

	private ConexaoIntegracaoOBC conexaoIntegracaoOBC;

	public CfgCargaEmpresaDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
	}

	public List<CfgCargaEmpresaModel> getCargaEmpresa(Integer idEmpresa) throws Exception {

		return new SqlReadUtil<CfgCargaEmpresaModel>(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");

				sql.append("   cfg_carga_empresa.id, ");
				sql.append("   cfg_carga_empresa.id_carga, ");
				sql.append("   cfg_carga_empresa.id_empresa, ");
				sql.append("   cfg_carga_empresa.status, ");
				sql.append("   cfg_carga_empresa.ciclo_change_tracking, ");
				sql.append("   cfg_carga_empresa.ciclo_data_hora, ");
				sql.append("   cfg_carga_empresa.sequencia, ");

				sql.append("   cfg_carga.descricao, ");
				sql.append("   cfg_carga.entidade, ");
				sql.append("   cfg_carga.sql_carga, ");
				sql.append("   cfg_carga.software, ");
				sql.append("   cfg_carga.tipo_ciclo ");

				sql.append(" from cfg_carga_empresa, cfg_carga ");

				sql.append(" where cfg_carga_empresa.id_empresa = ? ");
				sql.append(" and  cfg_carga_empresa.id_carga = cfg_carga.id ");
				sql.append(" and  cfg_carga_empresa.status = 'A' ");
				sql.append(" and  cfg_carga.status = 'A' ");
				
				sql.append(" order by cfg_carga_empresa.sequencia, cfg_carga.entidade ");
			}

			@Override
			public CfgCargaEmpresaModel criarModel(ResultSet resultSet) throws Exception {

				CfgCargaEmpresaModel cfgCargaEmpresa = new CfgCargaEmpresaModel();

				cfgCargaEmpresa.setId(resultSet.getInt("id"));
				cfgCargaEmpresa.setIdCarga(resultSet.getInt("id_carga"));
				cfgCargaEmpresa.setIdEmpresa(resultSet.getInt("id_empresa"));
				cfgCargaEmpresa.setStatus(resultSet.getString("status"));

				if (resultSet.getLong("ciclo_change_tracking") > 0) {
					cfgCargaEmpresa.setCicloChangeTracking(resultSet.getLong("ciclo_change_tracking"));
				} else {
					cfgCargaEmpresa.setCicloChangeTracking(new Long(0));
				}

				if (resultSet.getTimestamp("ciclo_data_hora") != null) {
					cfgCargaEmpresa.setCicloDataHora(resultSet.getTimestamp("ciclo_data_hora").toLocalDateTime());
				} else {
					cfgCargaEmpresa.setCicloDataHora(LocalDateTime.MIN);
				}

				cfgCargaEmpresa.setSequencia(resultSet.getInt("sequencia"));

				CfgCargaModel cfgCarga = new CfgCargaModel();

				cfgCarga.setId(resultSet.getInt("id_carga"));
				cfgCarga.setDescricao(resultSet.getString("descricao"));
				cfgCarga.setStatus(resultSet.getString("status"));
				cfgCarga.setEntidade(EntidadeObcEnum.valueOf(resultSet.getString("entidade")));
				cfgCarga.setSqlCarga(resultSet.getString("sql_carga"));
				cfgCarga.setSoftware(SoftwareEnum.valueOf(resultSet.getString("software")));
				cfgCarga.setTipoCiclo(TipoCicloEnum.valueOf(resultSet.getString("tipo_ciclo")));

				cfgCargaEmpresa.setCfgCarga(cfgCarga);

				return cfgCargaEmpresa;
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, idEmpresa);
			};

		}.getList();
	}

	public void atualizarCicloChangeTracking(CfgCargaEmpresaModel cargaEmpresa) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" update cfg_carga_empresa set ");
				sql.append("    ciclo_change_tracking = ? ");
				sql.append(" where id = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setLong(1, cargaEmpresa.getCicloChangeTracking());
				preparedStatement.setInt(2, cargaEmpresa.getId());
			}

		}.exec();
	}
	
	public void inativar(CfgCargaEmpresaModel cargaEmpresa) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" update cfg_carga_empresa set ");
				sql.append("    status = ? ");
				sql.append(" where id = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setString(1, "I");
				preparedStatement.setInt(2, cargaEmpresa.getId());
			}

		}.exec();
	}

	public void updDataHoraCiclo(CfgCargaEmpresaModel cargaEmpresa) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" update cfg_carga_empresa set ");
				sql.append("    ciclo_data_hora = ? ");
				sql.append(" where id = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setTimestamp(1, Timestamp.valueOf(cargaEmpresa.getCicloDataHora()));
				preparedStatement.setInt(2, cargaEmpresa.getId());
			}

		}.exec();
	}

}

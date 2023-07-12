package br.com.agricopel.integrador_obc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.model.CfgCicloEnvioObcModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.utils.SqlWriteUtil;

public class CfgCicloEnvioObcDAO {

	private ConexaoIntegracaoOBC conexaoIntegracaoOBC;

	public CfgCicloEnvioObcDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
	}

	public void inserirInexistente(CfgCicloEnvioObcModel cicloEnvioObc) throws Exception {

		if (this.getCicloEnvio(cicloEnvioObc.getEntidade()) == null) {
			inserir(cicloEnvioObc);
		}
	}

	private void inserir(CfgCicloEnvioObcModel cicloEnvioObc) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" INSERT INTO cfg_ciclo_envio_obc ( ");
				sql.append("     entidade, status) ");
				sql.append(" VALUES (?, ?) ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setString(1, cicloEnvioObc.getEntidade().name());
				preparedStatement.setString(2, "A");
			}

		}.execUpd();
	}

	private CfgCicloEnvioObcModel getCicloEnvio(EntidadeObcEnum entidadeObc) throws Exception {

		return new SqlReadUtil<CfgCicloEnvioObcModel>(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    id ");
				sql.append(" from cfg_ciclo_envio_obc ");
				sql.append(" where entidade = ? ");
			}

			@Override
			public CfgCicloEnvioObcModel criarModel(ResultSet resultSet) throws Exception {

				CfgCicloEnvioObcModel cicloEnvioObc = new CfgCicloEnvioObcModel();

				cicloEnvioObc.setId(resultSet.getInt("id"));

				return cicloEnvioObc;
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setString(1, entidadeObc.name());
			}

		}.getModel();
	}

	public List<CfgCicloEnvioObcModel> getCiclosAtivos() throws Exception {

		return new SqlReadUtil<CfgCicloEnvioObcModel>(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    id, ");
				sql.append("    entidade, ");
				sql.append("    status, ");
				sql.append("    data_hora ");
				sql.append(" from cfg_ciclo_envio_obc ");
				sql.append(" where status = 'A' ");
			}

			@Override
			public CfgCicloEnvioObcModel criarModel(ResultSet resultSet) throws Exception {

				CfgCicloEnvioObcModel cicloEnvioObc = new CfgCicloEnvioObcModel();

				cicloEnvioObc.setId(resultSet.getInt("id"));

				try {
					cicloEnvioObc.setEntidade(EntidadeObcEnum.valueOf(resultSet.getString("entidade")));
				} catch (IllegalArgumentException e) {
					cicloEnvioObc.setEntidade(null);
				}

				cicloEnvioObc.setStatus(resultSet.getString("status"));

				return cicloEnvioObc;
			}

		}.getList();
	}

}

package br.com.agricopel.integrador_obc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.model.CfgCicloEnvioObcModel;
import br.com.agricopel.integrador_obc.model.EnvioObcModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.utils.SqlWriteUtil;

public class DadosEnvioDAO {

	private ConexaoIntegracaoOBC conexaoIntegracaoOBC;

	public DadosEnvioDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
	}

	public List<EnvioObcModel> getDados(CfgCicloEnvioObcModel cicloEnvioObc) throws Exception {

		return new SqlReadUtil<EnvioObcModel>(conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {
				sql.append(montarSql(cicloEnvioObc));
			}

			@Override
			public EnvioObcModel criarModel(ResultSet resultSet) throws Exception {

				EnvioObcModel envioObc = new EnvioObcModel();

				envioObc.setId(resultSet.getInt("id"));
				envioObc.setAcao(resultSet.getString("acao"));
				envioObc.setChave(resultSet.getString("chave"));
				envioObc.setEntidade(cicloEnvioObc.getEntidade());
				envioObc.setUltimoErro(resultSet.getString("ultimo_erro"));
				envioObc.setTentativasEnvio(resultSet.getInt("tentativas_envio"));
				envioObc.setDataHora(resultSet.getTimestamp("data_hora").toLocalDateTime());

				envioObc.setDados(montarDados(cicloEnvioObc.getEntidade(), resultSet));

				return envioObc;
			}

		}.getList();
	}

	private String montarSql(CfgCicloEnvioObcModel cicloEnvioObc) throws Exception {

		StringBuilder sql = new StringBuilder();
		List<String> campos = new ArrayList<>(Arrays.asList(cicloEnvioObc.getEntidade().getCampos()));

		campos.add("acao");
		campos.add("chave");
		campos.add("id");
		campos.add("ultimo_erro");
		campos.add("tentativas_envio");
		campos.add("data_hora");

		String tabela = "obc_".concat(cicloEnvioObc.getEntidade().name().toLowerCase());

		sql.append(" select ");
		sql.append(campos.stream().collect(Collectors.joining(",")));
		sql.append(" from ");
		sql.append(tabela);

		sql.append(" where integrado = 'N' ");
		sql.append(" and coalesce(tentativas_envio, 0) < 3 ");
		sql.append(" order by data_hora desc ");
		sql.append(" limit 50 ");

		return sql.toString();
	}

	private String montarDados(EntidadeObcEnum entidadeObc, ResultSet resultSet) throws Exception {

		StringBuilder dados = new StringBuilder();

		for (String campo : entidadeObc.getCampos()) {
			dados.append("#SEP#").append(resultSet.getString(campo));
		}

		return dados.toString().replaceFirst("#SEP#", "");
	}

	public void envioErro(EnvioObcModel envioObc, boolean incTentativa) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" update ");
				sql.append(" obc_").append(envioObc.getEntidade().name().toLowerCase());
				sql.append(" set ");
				
				if (incTentativa)
					sql.append("   tentativas_envio = coalesce(tentativas_envio, 0) + 1, ");
				
				sql.append("   ultimo_erro = ? ");
				sql.append(" where id = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setString(1, envioObc.getUltimoErro());
				preparedStatement.setInt(2, envioObc.getId());
			}
		}.execUpd();
	}

	public void envioSucesso(EnvioObcModel envioObc) throws Exception {

		new SqlWriteUtil(this.conexaoIntegracaoOBC) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" update ");
				sql.append(" obc_").append(envioObc.getEntidade().name().toLowerCase());
				sql.append(" set ");
				sql.append("   integrado = 'S' ");
				sql.append(" where id = ? ");
				sql.append(" and data_hora = ? ");
			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {
				preparedStatement.setInt(1, envioObc.getId());
				preparedStatement.setTimestamp(2, Timestamp.valueOf(envioObc.getDataHora()));
			}
		}.execUpd();

	}

}

package br.com.agricopel.integrador_obc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoBD;
import br.com.agricopel.integrador_obc.model.SqlModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.utils.AgrException;
import br.com.agricopel.integrador_obc.utils.SqlReadUtil;

public class EnvioObcDAO {

	private ConexaoBD conexaoBD;

	public EnvioObcDAO(ConexaoBD conexaoBD) {
		this.conexaoBD = conexaoBD;
	}

	public void validar(SqlModel sqlModel) throws Exception {

		try (PreparedStatement preparedStatement = this.conexaoBD.getStmt(sqlModel.getSql())) {

			switch (sqlModel.getTipoCiclo()) {
			case CTR:
				Long versaoCT = this.getVersaoCt(conexaoBD);
				preparedStatement.setLong(1, versaoCT);
				break;
			case DTH:
				preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
				break;
			case NEN:
				break;
			default:
				break;
			}

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				EntidadeObcEnum entidadeOBC = sqlModel.getEntidade();

				List<String> camposSql = validarCampos(resultSet, entidadeOBC.getCampos());
				List<String> camposFixos = validarCampos(resultSet, entidadeOBC.getCamposFixo());

				if (!camposFixos.isEmpty() || !camposSql.isEmpty()) {
					gerarExceptionCampos(camposSql, camposFixos);
				}
			}
		} catch (SQLException e) {
			throw new AgrException("Falha na execução do SQL - ".concat(e.getMessage()));
		}
	}

	private List<String> validarCampos(ResultSet resultSet, String[] campos) throws Exception {

		List<String> camposNaoEncontrados = new ArrayList<>();

		for (String campo : campos) {

			try {
				resultSet.findColumn(campo);
			} catch (SQLException e) {
				camposNaoEncontrados.add(e.getMessage().concat(" - ").concat(campo));
			}
		}

		return camposNaoEncontrados;
	}

	private void gerarExceptionCampos(List<String> camposFixos, List<String> camposSql) throws Exception {

		String novaLinha = System.getProperty("line.separator");
		StringBuilder campos = new StringBuilder();

		for (String campo : camposFixos) {
			campos.append(campo + novaLinha);
		}

		for (String campo : camposSql) {
			campos.append(campo + novaLinha);
		}

		throw new AgrException(
				"Faltam um ou mais campos no SQL informado:".concat(novaLinha).concat(campos.toString().trim()));
	}

	public Long getVersaoCt(ConexaoBD conexaoBD) throws Exception {

		return new SqlReadUtil<Long>(conexaoBD) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" SELECT CHANGE_TRACKING_CURRENT_VERSION() AS VERSAOCT ");
			}

			@Override
			public Long criarModel(ResultSet resultSet) throws Exception {

				return resultSet.getLong("VERSAOCT");
			}

		}.getModel();

	}
	
}

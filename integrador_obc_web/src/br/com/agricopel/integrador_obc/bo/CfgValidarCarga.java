package br.com.agricopel.integrador_obc.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoAutoSystem;
import br.com.agricopel.integrador_obc.conexao.ConexaoBD;
import br.com.agricopel.integrador_obc.conexao.ConexaoDbGint;
import br.com.agricopel.integrador_obc.conexao.ConexaoEMSysVarejo;
import br.com.agricopel.integrador_obc.conexao.ConexaoProtheus;
import br.com.agricopel.integrador_obc.model.CfgCargaModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.utils.AgrException;
import br.com.agricopel.integrador_obc.utils.NamedParamStatement;
import br.com.agricopel.integrador_obc.utils.SqlReadUtil;

public class CfgValidarCarga {

	public void validar(CfgCargaModel cfgCarga, String codEmp) throws Exception {

		try (ConexaoBD conexaoBD = this.getConexaoSql(cfgCarga);
				NamedParamStatement namedStmt = conexaoBD.getNamedStmt(cfgCarga.getSqlCarga())) {

			conexaoBD.abrirTransacao();
			namedStmt.getPreparedStatement().setFetchSize(100);
			
			configSoftware(cfgCarga, namedStmt, codEmp);
			configTipoCiclo(cfgCarga, namedStmt, conexaoBD);
			
			try (ResultSet resultSet = namedStmt.executeQuery()) {

				EntidadeObcEnum entidadeOBC = cfgCarga.getEntidade();

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

	private void configSoftware(CfgCargaModel cfgCarga, NamedParamStatement namedStmt, String codEmp)
			throws SQLException {

		switch (cfgCarga.getSoftware()) {
		case ATS:
			break;
		case DBG:
			break;
		case PRT:
			if (namedStmt.hasParam("codemp")) {
				namedStmt.setString("codemp", codEmp);
			}
			break;
		default:
			break;
		}
	}

	private void configTipoCiclo(CfgCargaModel cfgCarga, NamedParamStatement namedStmt, ConexaoBD conexaoBD)
			throws Exception {

		switch (cfgCarga.getTipoCiclo()) {
		case CTR:
			Long versaoCT = this.getVersaoCt(conexaoBD);
			namedStmt.setLong("ctr", versaoCT);
			break;
		case DTH:
			namedStmt.setTimeStamp("dth", Timestamp.valueOf(LocalDateTime.now()));
			break;
		case NEN:
			break;
		default:
			break;
		}
	}

	private List<String> validarCampos(ResultSet resultSet, String[] campos) throws SQLException {

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

	private void gerarExceptionCampos(List<String> camposFixos, List<String> camposSql) throws SQLException {

		String novaLinha = System.getProperty("line.separator");
		StringBuilder campos = new StringBuilder();

		for (String campo : camposFixos) {
			campos.append(campo).append(novaLinha);
		}

		for (String campo : camposSql) {
			campos.append(campo).append(novaLinha);
		}

		throw new SQLException(
				"Faltam um ou mais campos no SQL informado:".concat(novaLinha).concat(campos.toString().trim()));
	}

	private ConexaoBD getConexaoSql(CfgCargaModel cfgCarga) throws SQLException {

		switch (cfgCarga.getSoftware()) {
		case ATS:
			return new ConexaoAutoSystem();
		case DBG:
			return new ConexaoDbGint();
		case PRT:
			return new ConexaoProtheus();
		case EMV:
			return new ConexaoEMSysVarejo();
		}

		throw new SQLException(
				"Software não configurado no cadastro do SQL - [".concat(cfgCarga.getDescricao()).concat("]"));
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
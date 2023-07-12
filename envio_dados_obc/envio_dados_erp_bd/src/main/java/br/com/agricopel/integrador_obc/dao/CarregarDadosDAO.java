package br.com.agricopel.integrador_obc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import br.com.agricopel.comp.conexao.ConexaoBD;
import br.com.agricopel.comp.utils.NamedParamStatement;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;

public class CarregarDadosDAO {

	private ConexaoBD conexaoBD;

	public CarregarDadosDAO(ConexaoBD conexaoBD) {
		this.conexaoBD = conexaoBD;
	}

	public ResultSet getDadosCarga(CfgCargaEmpresaModel cargaEmpresa) throws Exception {

		NamedParamStatement namedStmt = this.conexaoBD.getNamedStmt(cargaEmpresa.getCfgCarga().getSqlCarga());

		configSoftware(cargaEmpresa, namedStmt);
		configTipoCiclo(cargaEmpresa, namedStmt);

		namedStmt.getPreparedStatement().getConnection().setAutoCommit(false);
		namedStmt.getPreparedStatement().setFetchSize(100);

		return namedStmt.executeQuery();
	}

	private void configSoftware(CfgCargaEmpresaModel cfgCargaEmpresa, NamedParamStatement namedStmt)
			throws SQLException {

		switch (cfgCargaEmpresa.getCfgCarga().getSoftware()) {
		case ATS:
			break;
		case DBG:
			break;
		case PRT:
			if (namedStmt.hasParam("codemp")) {
				namedStmt.setString("codemp", cfgCargaEmpresa.getCfgEmpresa().getCodProtheus());
			}
			break;
		default:
			break;
		}
	}

	private void configTipoCiclo(CfgCargaEmpresaModel cargaEmpresa, NamedParamStatement namedStmt) throws Exception {

		switch (cargaEmpresa.getCfgCarga().getTipoCiclo()) {
		case CTR:
			namedStmt.setLong("ctr", cargaEmpresa.getCicloChangeTracking());
			break;
		case DTH:
			if (cargaEmpresa.getCicloDataHora().equals(LocalDateTime.MIN)) {
				namedStmt.setTimeStamp("dth", Timestamp.valueOf(LocalDateTime.now().minusYears(2)));
			} else {
				namedStmt.setTimeStamp("dth", Timestamp.valueOf(cargaEmpresa.getCicloDataHora()));
			}
			break;
		case NEN:
			break;
		default:
			break;
		}
	}

	public Long getVersaoCt() throws Exception {

		return new SqlReadUtil<Long>(this.conexaoBD) {

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
package br.com.agricopel.integrador_obc.utils;

import java.sql.PreparedStatement;

import br.com.agricopel.integrador_obc.conexao.ConexaoBD;

public abstract class SqlWriteUtil {

	private ConexaoBD conexaoBD;

	public abstract void appendSQL(StringBuilder sql) throws Exception;

	public SqlWriteUtil(ConexaoBD conexaoBD) {
		this.conexaoBD = conexaoBD;
	}

	public void setParameters(PreparedStatement preparedStatement) throws Exception {
	}

	private String getSql() throws Exception {

		StringBuilder sql = new StringBuilder();
		appendSQL(sql);

		return sql.toString();
	}

	public void execUpd() throws Exception {

		try (PreparedStatement preparedStatement = this.conexaoBD.getWriteStmt(getSql())) {

			setParameters(preparedStatement);
			preparedStatement.execute();
		}
	}

}

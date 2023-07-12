package br.com.agricopel.comp.utils;

import java.sql.PreparedStatement;

import br.com.agricopel.comp.conexao.ConexaoBD;

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

	public void exec() throws Exception {

		try (PreparedStatement preparedStatement = this.conexaoBD.getStmt(getSql())) {

			setParameters(preparedStatement);
			preparedStatement.execute();
		}
	}

}

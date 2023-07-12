package br.com.agricopel.integrador_obc.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.agricopel.integrador_obc.utils.LogUtils;

public abstract class ConexaoBD implements AutoCloseable {

	protected Connection connection;

	public abstract void conectar() throws Exception;

	public PreparedStatement getReadStmt(String sql) throws Exception {

		if (this.connection == null || this.connection.isClosed()) {
			this.conectar();
		}

		return this.connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	}

	public PreparedStatement getWriteStmt(String sql) throws Exception {

		if (this.connection == null || this.connection.isClosed()) {
			this.conectar();
		}

		return this.connection.prepareStatement(sql);
	}

	@Override
	public void close() {

		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			LogUtils.escreverLogErro(e);
		}
	}

	public void abrirTransacao() throws Exception {

		this.conectar();
		connection.setAutoCommit(false);
	}

	public void fecharTransacao(boolean commit) throws Exception {

		if (this.connection != null || !this.connection.isClosed()) {
			if (commit) {
				connection.commit();
			} else {
				connection.rollback();
			}
		}
	}
}
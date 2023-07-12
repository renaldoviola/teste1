package br.com.agricopel.comp.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.agricopel.comp.utils.NamedParamStatement;

public abstract class ConexaoBD implements AutoCloseable {

	protected Connection connection;

	public abstract void conectar() throws Exception;

	public PreparedStatement getStmt(String sql) throws Exception {

		if (this.connection == null || this.connection.isClosed()) {
			this.conectar();
		}

		return this.connection.prepareStatement(sql);
	}

	public NamedParamStatement getNamedStmt(String statementWithNames) throws Exception {

		if (this.connection == null || this.connection.isClosed()) {
			this.conectar();
		}

		return new NamedParamStatement(this.connection, statementWithNames);
	}

	@Override
	public void close() {

		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Falha ao desconectar");
			e.printStackTrace();
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
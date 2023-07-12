package br.com.agricopel.comp.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoBD;

public abstract class SqlReadUtil<T> {

	private ConexaoBD conexaoBD;

	public abstract void appendSQL(StringBuilder sql) throws Exception;

	public abstract T criarModel(ResultSet resultSet) throws Exception;

	public SqlReadUtil(ConexaoBD conexaoBD) {
		this.conexaoBD = conexaoBD;
	}

	public void setParameters(PreparedStatement preparedStatement) throws Exception {
	}

	private String getSql() throws Exception {

		StringBuilder sql = new StringBuilder();
		appendSQL(sql);

		return sql.toString();
	}

	public List<T> getList() throws Exception {

		try (PreparedStatement preparedStatement = this.conexaoBD.getReadStmt(getSql())) {
			setParameters(preparedStatement);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				List<T> list = new ArrayList<>();

				while (resultSet.next()) {
					list.add(criarModel(resultSet));
				}

				return list;
			}
		}
	}

	public T getModel() throws Exception {

		try {
			List<T> lista = getList();

			return lista.isEmpty() ? null : lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
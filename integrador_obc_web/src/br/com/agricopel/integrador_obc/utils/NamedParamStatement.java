package br.com.agricopel.integrador_obc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedParamStatement implements AutoCloseable {

	private PreparedStatement prepStmt;
	private Map<String, List<Integer>> parameters = new HashMap<>();
	private List<String> parametersWithValue = new ArrayList<>();

	public NamedParamStatement(Connection conn, String statementWithNames) throws SQLException {

		int indexCount = 0;
		List<Integer> indexes = null;
		String parameter = "";

		statementWithNames = prepareSQL(statementWithNames);
		
		Pattern findParametersPattern = Pattern.compile("(?<!')(:[\\w]*)(?!')");
		Matcher matcher = findParametersPattern.matcher(statementWithNames);

		while (matcher.find()) {

			parameter = matcher.group().substring(1);

			if (!parameter.isEmpty()) {
				indexCount++;

				if (parameters.containsKey(parameter)) {
					indexes = parameters.get(parameter);
				} else {
					indexes = new ArrayList<>();
					parameters.put(parameter, indexes);
				}

				indexes.add(indexCount);
			}
		}

		prepStmt = conn.prepareStatement(statementWithNames.replaceAll(findParametersPattern.pattern(), "?"));
	}

	private void paramValued(String paramName) {
		parametersWithValue.add(paramName);
	}

	private void checkNotValuedParameters() throws SQLException {

		String notValuedParameters = "";

		for (String paramName : parameters.keySet()) {
			if (!parametersWithValue.contains(paramName)) {
				notValuedParameters = notValuedParameters.concat(notValuedParameters.isEmpty() ? "" : " - ")
						.concat(paramName);
			}
		}

		if (!notValuedParameters.isEmpty()) {
			throw new SQLException("Um ou mais parâmetros não tiveram valor informado: ".concat(notValuedParameters)
					.concat(System.getProperty("line.separator"))
					.concat("Obs: o caractere ':' é um um caractere reservado para identificar parâmetros no SQL!"));
		}
	}

	public PreparedStatement getPreparedStatement() {
		return prepStmt;
	}

	public ResultSet executeQuery() throws SQLException {
		checkNotValuedParameters();
		return prepStmt.executeQuery();
	}

	public void setInt(String paramName, int paramValue) throws SQLException {

		List<Integer> indexes = this.getIndexes(paramName);
		paramValued(paramName);

		for (Integer index : indexes) {
			prepStmt.setInt(index, paramValue);
		}
	}

	public void setString(String paramName, String paramValue) throws SQLException {

		List<Integer> indexes = this.getIndexes(paramName);
		paramValued(paramName);

		for (Integer index : indexes) {
			prepStmt.setString(index, paramValue);
		}
	}

	public void setLong(String paramName, Long paramValue) throws SQLException {

		List<Integer> indexes = this.getIndexes(paramName);
		paramValued(paramName);

		for (Integer index : indexes) {
			prepStmt.setLong(index, paramValue);
		}
	}

	public void setTimeStamp(String paramName, Timestamp paramValue) throws SQLException {

		List<Integer> indexes = this.getIndexes(paramName);
		paramValued(paramName);

		for (Integer index : indexes) {
			prepStmt.setTimestamp(index, paramValue);
		}
	}

	private List<Integer> getIndexes(String paramName) throws SQLException {

		if (this.hasParam(paramName)) {
			return parameters.get(paramName);
		} else {
			throw new SQLException("Parâmetro não encontrado! (".concat(paramName).concat(")")
					.concat(System.getProperty("line.separator"))
					.concat("Obs: o caractere ':' é um um caractere reservado para identificar parâmetros no SQL!"));
		}
	}

	public boolean hasParam(String paramName) {
		return parameters.containsKey(paramName);
	}

	@Override
	public void close() throws SQLException {
		prepStmt.close();
	}
	
	private String prepareSQL(String statementWithNames) {

		while (statementWithNames.contains("::")) {
			statementWithNames = statementWithNames.replaceAll("::", ":");
		}
		
		return statementWithNames;
	}
}
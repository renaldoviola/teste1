package br.com.agricopel.integrador_obc.conexao;

import java.sql.DriverManager;

public class ConexaoProtheus extends ConexaoBD {

	@Override
	public void conectar() throws Exception {

		if (connection == null || connection.isClosed()) {
			String user = "user_integracao_obc";
			String pwd = "Obc@1644!16";

			 String url = " ";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url, user, pwd);
		}
	}
}
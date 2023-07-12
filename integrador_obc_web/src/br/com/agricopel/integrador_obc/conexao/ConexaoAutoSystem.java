package br.com.agricopel.integrador_obc.conexao;

import java.sql.DriverManager;
import java.util.Properties;

public class ConexaoAutoSystem extends ConexaoBD {

	@Override
	public void conectar() throws Exception {

		if (connection == null || connection.isClosed()) {

			Properties props = new Properties();
			props.setProperty("user", "ws_integracaoobc");
			
			props.setProperty("charSet", "UTF-8");

			Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection("jdbc:postgresql://192.168.254.1:5432/matriz", props);
		}
	}
}
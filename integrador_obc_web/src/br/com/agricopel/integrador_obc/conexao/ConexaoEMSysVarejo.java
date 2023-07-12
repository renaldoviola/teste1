package br.com.agricopel.integrador_obc.conexao;

import java.sql.DriverManager;
import java.util.Properties;

public class ConexaoEMSysVarejo extends ConexaoBD {

	@Override
	public void conectar() throws Exception {

		if (connection == null || connection.isClosed()) {

			Properties props = new Properties();
			props.setProperty("user", "rez_usr_integracao_obc");
			

			Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection(" ", props);
		}
	}
}
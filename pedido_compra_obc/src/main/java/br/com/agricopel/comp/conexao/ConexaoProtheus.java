package br.com.agricopel.comp.conexao;

import java.sql.DriverManager;
import java.util.Properties;

import br.com.agricopel.comp.CompConfigs;

public class ConexaoProtheus extends ConexaoBD {

	@Override
	public void conectar() throws Exception {

		if (connection == null || connection.isClosed()) {
			
			Properties props = CompConfigs.getPropBdProtheus();
			String url = CompConfigs.getUrlBdProtheus();

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url, props);
		}
	}
}
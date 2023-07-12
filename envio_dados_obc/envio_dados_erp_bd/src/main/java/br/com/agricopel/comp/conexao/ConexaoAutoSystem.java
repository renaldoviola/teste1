package br.com.agricopel.comp.conexao;

import java.sql.DriverManager;
import java.util.Properties;

import br.com.agricopel.comp.utils.CompConfigs;

public class ConexaoAutoSystem extends ConexaoBD {

	@Override
	public void conectar() throws Exception {

		if (connection == null || connection.isClosed()) {

			Properties props = new Properties();
			props = CompConfigs.getPropBdAutoSystem();

			connection = DriverManager.getConnection(CompConfigs.getUrlBdAutoSystem(), props);
		}
	}
}
package br.com.agricopel.comp.conexao;

import java.sql.DriverManager;
import java.util.Properties;

import br.com.agricopel.comp.utils.CompConfigs;

public class ConexaoEMSysVarejo extends ConexaoBD {

	@Override
	public void conectar() throws Exception {

		if (connection == null || connection.isClosed()) {

			Properties props = new Properties();
			props = CompConfigs.getPropBdEMSys();

			connection = DriverManager.getConnection(CompConfigs.getUrlBdEMSys(), props);
		}
	}
}
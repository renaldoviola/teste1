package br.com.agricopel.comp.conexao;

import java.sql.DriverManager;
import java.util.Properties;

import br.com.agricopel.comp.CompConfigs;

public class ConexaoEmsys extends ConexaoBD {

	@Override
	public void conectar() throws Exception {

		String url = CompConfigs.getUrlBdEmsys();
		Properties props = CompConfigs.getPropBdEmsys();
		this.connection = DriverManager.getConnection(url, props);
	}

}

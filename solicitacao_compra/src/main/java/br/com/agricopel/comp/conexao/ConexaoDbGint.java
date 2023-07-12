package br.com.agricopel.comp.conexao;

import java.sql.DriverManager;
import java.util.Properties;

import br.com.agricopel.comp.CompConfigs;

public class ConexaoDbGint extends ConexaoBD {

	@Override
	public void conectar() throws Exception {

		String url = CompConfigs.getUrlBdDbGint();
		Properties props = CompConfigs.getPropBdDbgint();
		this.connection = DriverManager.getConnection(url, props);
	}

}

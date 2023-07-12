package br.com.agricopel.comp.conexao;

import java.sql.DriverManager;
import java.util.Properties;

import br.com.agricopel.comp.utils.CompConfigs;
import br.com.agricopel.comp.utils.LogUtils;

public class ConexaoIntegracaoOBC extends ConexaoBD {
    
	@Override
	public void conectar() throws Exception {

		if (connection == null || connection.isClosed()) {
			
			Properties props = CompConfigs.getPropBdOBC();
			String url = CompConfigs.getUrlBdOBC();

			connection = DriverManager.getConnection(url, props);
		}
	}
}
package br.com.agricopel.comp.conexao;

import java.sql.DriverManager;
import java.util.Properties;
import br.com.agricopel.comp.utils.CompConfigs;

public class ConexaoDbGint extends ConexaoBD {

	Properties props = new Properties();

	@Override
	public void conectar() throws Exception {

        props = CompConfigs.getPropBdDbgint();

        this.connection = DriverManager.getConnection(CompConfigs.getUrlBdDbGint(), props);
	}

}

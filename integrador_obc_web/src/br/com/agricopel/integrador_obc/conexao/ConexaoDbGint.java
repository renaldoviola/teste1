package br.com.agricopel.integrador_obc.conexao;

import java.sql.DriverManager;
import java.util.Properties;

import br.com.agricopel.integrador_obc.utils.CompConfigs;

public class ConexaoDbGint extends ConexaoBD {

	Properties props = new Properties();

	@Override
	public void conectar() throws Exception {

        props = CompConfigs.getPropBdDbgint();
        Class.forName("com.mysql.jdbc.Driver");
        
        this.connection = DriverManager.getConnection(CompConfigs.getUrlBdDbGint(), props);
	}

}

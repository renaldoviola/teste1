package br.com.agricopel.integrador_obc.conexao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import br.com.agricopel.integrador_obc.utils.CompConfigs;

public class ConexaoIntegracaoOBC extends ConexaoBD {

    @Override
    public void conectar() throws Exception {

        if (connection == null || connection.isClosed()) {

            Properties props = CompConfigs.getPropBdOBC();
            String url = CompConfigs.getUrlBdOBC();
            
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(url, props);
        }
    }
}
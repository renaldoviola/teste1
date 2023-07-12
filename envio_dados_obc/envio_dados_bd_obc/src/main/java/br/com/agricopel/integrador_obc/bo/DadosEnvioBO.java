package br.com.agricopel.integrador_obc.bo;

import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.dao.DadosEnvioDAO;
import br.com.agricopel.integrador_obc.model.CfgCicloEnvioObcModel;
import br.com.agricopel.integrador_obc.model.EnvioObcModel;

public class DadosEnvioBO {

	public List<EnvioObcModel> getDados(CfgCicloEnvioObcModel cicloEnvioObc) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			return new DadosEnvioDAO(conexaoIntegracaoOBC).getDados(cicloEnvioObc);
		}
	}

}

package br.com.agricopel.integrador_obc.bo;

import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.dao.CfgCicloEnvioObcDAO;
import br.com.agricopel.integrador_obc.model.CfgCicloEnvioObcModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.utils.LogUtils;

public class CfgCicloEnvioObcBO {

	public void sincronizar() throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {

			LogUtils.escreverLogInfo("Sincronizando configuracao dos Ciclos");
			
			CfgCicloEnvioObcDAO cicloEnvioObcDAO = new CfgCicloEnvioObcDAO(conexaoIntegracaoOBC);

			for (EntidadeObcEnum entidadeObc : EntidadeObcEnum.values()) {

				CfgCicloEnvioObcModel cicloEnvioObc = new CfgCicloEnvioObcModel();

				cicloEnvioObc.setEntidade(entidadeObc);
				cicloEnvioObc.setStatus("A");

				cicloEnvioObcDAO.inserirInexistente(cicloEnvioObc);
			}
			
			LogUtils.escreverLogInfo("Fim sincronizacao da configuracao dos Ciclos");
		}
	}

	public List<CfgCicloEnvioObcModel> getCiclosAtivos() throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			return new CfgCicloEnvioObcDAO(conexaoIntegracaoOBC).getCiclosAtivos();
		}
	}

}

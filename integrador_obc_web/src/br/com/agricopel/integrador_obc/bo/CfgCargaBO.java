package br.com.agricopel.integrador_obc.bo;

import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.dao.CfgCargaDAO;
import br.com.agricopel.integrador_obc.model.CfgCargaModel;

public class CfgCargaBO {

	public void gravar(CfgCargaModel cfgCarga) throws Exception {

		if (cfgCarga.getStatus().equals("A")) {
			new CfgValidarCarga().validar(cfgCarga, "01");
		}

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			CfgCargaDAO cfgCargaDao = new CfgCargaDAO(conexaoIntegracaoOBC);
			cfgCargaDao.gravar(cfgCarga);
		}
	}

	public CfgCargaModel getCargaModel(Integer id) throws Exception {
		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			return new CfgCargaDAO(conexaoIntegracaoOBC).getCarga(id);
		}
	}

	public List<CfgCargaModel> getCfgCargas(boolean somenteAtivas) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			return this.getCfgCargas(conexaoIntegracaoOBC, somenteAtivas);
		}
	}
	
	public List<CfgCargaModel> getCfgCargas(ConexaoIntegracaoOBC conexaoIntegracaoOBC, boolean somenteAtivas) throws Exception {
			return new CfgCargaDAO(conexaoIntegracaoOBC).getCfgCargas(somenteAtivas);
	}

}

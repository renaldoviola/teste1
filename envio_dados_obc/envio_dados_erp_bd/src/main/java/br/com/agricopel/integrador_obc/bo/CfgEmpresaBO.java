package br.com.agricopel.integrador_obc.bo;

import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.dao.CfgEmpresaDAO;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;

public class CfgEmpresaBO {

	public List<CfgEmpresaModel> getEmpresasAtivas() throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			return new CfgEmpresaDAO(conexaoIntegracaoOBC).getEmpresasAtivas();
		}

	}

}
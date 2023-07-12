package br.com.agricopel.integrador_obc.bo;

import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.conexao.ConexaoProtheus;
import br.com.agricopel.integrador_obc.dao.CfgEmpresaDAO;
import br.com.agricopel.integrador_obc.dao.EmpresasProtheusDAO;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;

public class CfgEmpresaBO {

	public void gravar(CfgEmpresaModel cfgEmpresaModel) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			new CfgEmpresaDAO(conexaoIntegracaoOBC).gravar(cfgEmpresaModel);
		}
	}

	public List<CfgEmpresaModel> getEmpresas() throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			return new CfgEmpresaDAO(conexaoIntegracaoOBC).getEmpresas();
		}

	}

	public CfgEmpresaModel getEmpresa(Integer id) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			return new CfgEmpresaDAO(conexaoIntegracaoOBC).getEmpresa(id);
		}

	}
	
	public void sincronizar() throws Exception {

		List<CfgEmpresaModel> cfgEmpresasProtheus = null;

		try (ConexaoProtheus conexaoProtheus = new ConexaoProtheus()) {
			cfgEmpresasProtheus = new EmpresasProtheusDAO().getEmpresasProtheus(conexaoProtheus);
		}

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			CfgEmpresaDAO cfgEmpresaDAO = new CfgEmpresaDAO(conexaoIntegracaoOBC);

			for (CfgEmpresaModel cfgEmpresaProtheus : cfgEmpresasProtheus) {

				CfgEmpresaModel cfgEmpresaIntegrador = cfgEmpresaDAO
						.getCodProtheus(cfgEmpresaProtheus.getCodProtheus());

				if (cfgEmpresaIntegrador != null) {

					cfgEmpresaProtheus.setId(cfgEmpresaIntegrador.getId());
					cfgEmpresaProtheus.setStatus(cfgEmpresaIntegrador.getStatus());
				}

				cfgEmpresaDAO.gravar(cfgEmpresaProtheus);

			}
		}

	}

}

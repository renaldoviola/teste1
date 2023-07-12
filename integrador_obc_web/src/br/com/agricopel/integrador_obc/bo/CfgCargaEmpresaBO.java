package br.com.agricopel.integrador_obc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.dao.CfgCargaDAO;
import br.com.agricopel.integrador_obc.dao.CfgCargaEmpresaDAO;
import br.com.agricopel.integrador_obc.dao.CfgEmpresaDAO;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;
import br.com.agricopel.integrador_obc.model.CfgCargaModel;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;

public class CfgCargaEmpresaBO {

	public void gravar(CfgCargaEmpresaModel cargaEmpresa) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {

			if (cargaEmpresa.getStatus().equals("A")) {
				CfgEmpresaModel empresa = new CfgEmpresaDAO(conexaoIntegracaoOBC)
						.getEmpresa(cargaEmpresa.getIdEmpresa());

				CfgCargaModel carga = new CfgCargaDAO(conexaoIntegracaoOBC).getCarga(cargaEmpresa.getIdCarga());

				new CfgValidarCarga().validar(carga, empresa.getCodProtheus());
			}

			new CfgCargaEmpresaDAO(conexaoIntegracaoOBC).gravar(cargaEmpresa);
		}
	}

	public void zerar(CfgCargaEmpresaModel cfgCargaEmpresa) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			new CfgCargaEmpresaDAO(conexaoIntegracaoOBC).zerar(cfgCargaEmpresa);
		}
	}

	public List<CfgCargaEmpresaModel> getCargas(Integer idEmpresa) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {

			List<CfgCargaEmpresaModel> cfgCargaEmpresas = new ArrayList<>();
			List<CfgCargaModel> cfgCargas = new CfgCargaBO().getCfgCargas(conexaoIntegracaoOBC, true);

			CfgCargaEmpresaDAO cfgCargaEmpresaDAO = new CfgCargaEmpresaDAO(conexaoIntegracaoOBC);

			for (CfgCargaModel cfgCarga : cfgCargas) {

				CfgCargaEmpresaModel cfgCargaEmpresa = cfgCargaEmpresaDAO.getCargaEmpresa(cfgCarga.getId(), idEmpresa);

				if (cfgCargaEmpresa == null) {
					cfgCargaEmpresa = new CfgCargaEmpresaModel();
				}

				cfgCargaEmpresa.setCfgCarga(cfgCarga);

				cfgCargaEmpresas.add(cfgCargaEmpresa);
			}

			return cfgCargaEmpresas;

		}
	}

}
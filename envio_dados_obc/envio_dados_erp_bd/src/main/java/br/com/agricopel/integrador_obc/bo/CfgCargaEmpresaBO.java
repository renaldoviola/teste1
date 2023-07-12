package br.com.agricopel.integrador_obc.bo;

import java.util.List;

import br.com.agricopel.comp.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.dao.CfgCargaEmpresaDAO;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;

public class CfgCargaEmpresaBO {

	public List<CfgCargaEmpresaModel> getCargas(Integer idEmpresa) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			return new CfgCargaEmpresaDAO(conexaoIntegracaoOBC).getCargaEmpresa(idEmpresa);

		}
	}

	public void atualizarCicloChangeTracking(CfgCargaEmpresaModel cargaEmpresa,
			ConexaoIntegracaoOBC conexaoIntegracaoOBC) throws Exception {

		new CfgCargaEmpresaDAO(conexaoIntegracaoOBC).atualizarCicloChangeTracking(cargaEmpresa);
	}

	public void inativar(CfgCargaEmpresaModel cargaEmpresa, ConexaoIntegracaoOBC conexaoIntegracaoOBC)
			throws Exception {

		new CfgCargaEmpresaDAO(conexaoIntegracaoOBC).inativar(cargaEmpresa);
	}

	public void atualizarCicloDataHora(CfgCargaEmpresaModel cargaEmpresa) throws Exception {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
			new CfgCargaEmpresaDAO(conexaoIntegracaoOBC).updDataHoraCiclo(cargaEmpresa);

		}

	}

}
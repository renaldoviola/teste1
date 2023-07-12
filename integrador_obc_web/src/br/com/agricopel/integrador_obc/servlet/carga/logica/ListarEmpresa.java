package br.com.agricopel.integrador_obc.servlet.carga.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgEmpresaBO;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;
import br.com.agricopel.integrador_obc.servlet.Logica;

public class ListarEmpresa implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		List<CfgEmpresaModel> cfgEmpresaModels = new CfgEmpresaBO().getEmpresas();

		req.setAttribute("listaEmpresa", cfgEmpresaModels);

		return "lista-empresa";
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

}

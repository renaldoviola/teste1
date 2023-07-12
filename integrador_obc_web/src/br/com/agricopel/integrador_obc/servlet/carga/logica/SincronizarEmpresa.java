package br.com.agricopel.integrador_obc.servlet.carga.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgEmpresaBO;
import br.com.agricopel.integrador_obc.servlet.Logica;

public class SincronizarEmpresa implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		new CfgEmpresaBO().sincronizar();

		req.setAttribute("mensagem", "Sincronização de empresas efetuada com sucesso!");

		return new ListarEmpresa().executa(req, res);
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

}

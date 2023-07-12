package br.com.agricopel.integrador_obc.servlet.carga.logica;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgEmpresaBO;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;
import br.com.agricopel.integrador_obc.servlet.Logica;

public class BuscarEmpresa implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Integer id = Integer.parseInt(Optional.ofNullable(req.getParameter("id")).orElse("0"));

		CfgEmpresaModel cfgEmpresa = new CfgEmpresaBO().getEmpresa(id);

		return carregar(cfgEmpresa, req);
	}

	public String executaErro(CfgEmpresaModel cfgEmpresa, String erro, HttpServletRequest req) {

		req.setAttribute("erro", erro);

		return carregar(cfgEmpresa, req);
	}

	private String carregar(CfgEmpresaModel cfgEmpresa, HttpServletRequest req) {

		req.setAttribute("empresa", cfgEmpresa);

		return "form-empresa";
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

}

package br.com.agricopel.integrador_obc.servlet.carga.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgCargaBO;
import br.com.agricopel.integrador_obc.model.CfgCargaModel;
import br.com.agricopel.integrador_obc.servlet.Logica;

public class ListarCarga implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		List<CfgCargaModel> cfgCargas = new CfgCargaBO().getCfgCargas(false);

		req.setAttribute("listaCarga", cfgCargas);

		return "lista-carga";
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

}

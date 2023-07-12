package br.com.agricopel.integrador_obc.servlet.carga.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.servlet.Logica;

public class MenuCarga implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "menu-carga";
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

}

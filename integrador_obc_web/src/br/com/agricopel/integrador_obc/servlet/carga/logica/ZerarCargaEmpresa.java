package br.com.agricopel.integrador_obc.servlet.carga.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgCargaEmpresaBO;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;
import br.com.agricopel.integrador_obc.servlet.Logica;
import br.com.agricopel.integrador_obc.utils.AgrException;

public class ZerarCargaEmpresa implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		CfgCargaEmpresaModel cfgCargaEmpresa = new CfgCargaEmpresaModel();

		try {
			CfgCargaEmpresaBO cfgCargaEmpresaBO = new CfgCargaEmpresaBO();

			cfgCargaEmpresa.setIdCarga(Integer.parseInt(req.getParameter("idCarga")));
			cfgCargaEmpresa.setIdEmpresa(Integer.parseInt(req.getParameter("idEmpresa")));

			cfgCargaEmpresaBO.zerar(cfgCargaEmpresa);

			return "Zerado com sucesso!";
		} catch (AgrException e) {
			return e.getMessage().replaceAll(System.getProperty("line.separator"), " || ");
		}
	}

	@Override
	public Integer getTpRetorno() {
		return 1;
	}

}

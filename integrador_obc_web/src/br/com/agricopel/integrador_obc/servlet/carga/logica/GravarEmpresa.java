package br.com.agricopel.integrador_obc.servlet.carga.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgEmpresaBO;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;
import br.com.agricopel.integrador_obc.servlet.Logica;
import br.com.agricopel.integrador_obc.utils.AgrException;

public class GravarEmpresa implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		CfgEmpresaModel cfgEmpresa = new CfgEmpresaModel();

		try {
			CfgEmpresaBO cfgEmpresaBO = new CfgEmpresaBO();

			cfgEmpresa.setCodProtheus(req.getParameter("codProtheus"));
			cfgEmpresa.setDescricao(req.getParameter("descricao"));
			cfgEmpresa.setId(Integer.parseInt(req.getParameter("id")));
			cfgEmpresa.setStatus(req.getParameter("status"));

			cfgEmpresaBO.gravar(cfgEmpresa);

			return new ListarEmpresa().executa(req, res);
		} catch (AgrException e) {
			return new BuscarEmpresa().executaErro(cfgEmpresa,
					e.getMessage().replaceAll(System.getProperty("line.separator"), " <br /> "), req);
		}
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

}

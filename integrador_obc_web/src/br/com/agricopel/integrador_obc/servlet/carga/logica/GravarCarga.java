package br.com.agricopel.integrador_obc.servlet.carga.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgCargaBO;
import br.com.agricopel.integrador_obc.model.CfgCargaModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.SoftwareEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.TipoCicloEnum;
import br.com.agricopel.integrador_obc.servlet.Logica;
import br.com.agricopel.integrador_obc.utils.AgrException;

public class GravarCarga implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		CfgCargaModel cfgCarga = new CfgCargaModel();

		try {
			CfgCargaBO cfgCargaBO = new CfgCargaBO();

			cfgCarga.setId(Integer.parseInt(req.getParameter("id")));
			cfgCarga.setDescricao(req.getParameter("descricao"));
			cfgCarga.setStatus(req.getParameter("status"));
			cfgCarga.setEntidade(EntidadeObcEnum.valueOf(req.getParameter("entidade")));
			cfgCarga.setSoftware(SoftwareEnum.valueOf(req.getParameter("software")));
			cfgCarga.setTipoCiclo(TipoCicloEnum.valueOf(req.getParameter("tipoCiclo")));
			cfgCarga.setSqlCarga(req.getParameter("sqlCarga"));

			cfgCargaBO.gravar(cfgCarga);

			return new ListarCarga().executa(req, res);
		} catch (AgrException e) {
			return new BuscarCarga().executaErro(cfgCarga,
					e.getMessage().replaceAll(System.getProperty("line.separator"), " <br /> "), req);
		}
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

}

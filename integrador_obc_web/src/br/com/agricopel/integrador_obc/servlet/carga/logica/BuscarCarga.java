package br.com.agricopel.integrador_obc.servlet.carga.logica;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgCargaBO;
import br.com.agricopel.integrador_obc.model.CfgCargaModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.SoftwareEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.TipoCicloEnum;
import br.com.agricopel.integrador_obc.servlet.Logica;

public class BuscarCarga implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Integer id = Integer.parseInt(Optional.ofNullable(req.getParameter("idCarga")).orElse("0"));

		CfgCargaModel cfgCarga = new CfgCargaBO().getCargaModel(id);

		if (cfgCarga == null) {
			cfgCarga = new CfgCargaModel();

			cfgCarga.setId(0);
			cfgCarga.setStatus("A");
		}

		return carregar(cfgCarga, req);
	}

	public String executaErro(CfgCargaModel cfgCarga, String erro, HttpServletRequest req) {

		req.setAttribute("erro", erro);

		return carregar(cfgCarga, req);
	}

	private String carregar(CfgCargaModel cfgCarga, HttpServletRequest req) {

		req.setAttribute("entidades", Arrays.asList(EntidadeObcEnum.values()));
		req.setAttribute("softwares", Arrays.asList(SoftwareEnum.values()));
		req.setAttribute("tipoCiclos", Arrays.asList(TipoCicloEnum.values()));

		req.setAttribute("carga", cfgCarga);

		return "form-carga";
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

}

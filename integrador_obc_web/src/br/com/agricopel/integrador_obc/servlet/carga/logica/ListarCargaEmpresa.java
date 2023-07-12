package br.com.agricopel.integrador_obc.servlet.carga.logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agricopel.integrador_obc.bo.CfgCargaEmpresaBO;
import br.com.agricopel.integrador_obc.bo.CfgEmpresaBO;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.SoftwareEnum;
import br.com.agricopel.integrador_obc.servlet.Logica;

public class ListarCargaEmpresa implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Integer idEmpresa = Integer.parseInt(Optional.ofNullable(req.getParameter("idEmpresa")).orElse("0"));
		String softwareStr = Optional.ofNullable(req.getParameter("software")).orElse("");

		List<CfgCargaEmpresaModel> cfgCargasEmpresa = new CfgCargaEmpresaBO().getCargas(idEmpresa);
		CfgEmpresaModel cfgEmpresa = new CfgEmpresaBO().getEmpresa(idEmpresa);

		if (!softwareStr.isEmpty()) {
			SoftwareEnum software = SoftwareEnum.valueOf(softwareStr);
			req.setAttribute("software", software);
			cfgCargasEmpresa = cfgCargasEmpresa.stream()
					.filter(item -> item.getCfgCarga().getSoftware().equals(software)).collect(Collectors.toList());
		}

		ordenar(cfgCargasEmpresa);

		req.setAttribute("listaCargasEmpresa", cfgCargasEmpresa);
		req.setAttribute("empresa", cfgEmpresa);
		req.setAttribute("softwares", SoftwareEnum.values());

		return "lista-carga-empresa";
	}

	@Override
	public Integer getTpRetorno() {
		return 0;
	}

	private void ordenar(List<CfgCargaEmpresaModel> cfgCargasEmpresa) {

		Collections.sort(cfgCargasEmpresa, new Comparator<CfgCargaEmpresaModel>() {
			@Override
			public int compare(CfgCargaEmpresaModel cargaEmpresa1, CfgCargaEmpresaModel cargaEmpresa2) {

				String compStatus1 = Optional.ofNullable(cargaEmpresa1.getStatus()).orElse("Z");
				String compStatus2 = Optional.ofNullable(cargaEmpresa2.getStatus()).orElse("Z");

				int compareStatus = compStatus1.compareTo(compStatus2);

				if (compareStatus != 0) {
					return compareStatus;
				}

				Integer compSequencia1 = Optional.ofNullable(cargaEmpresa1.getSequencia()).orElse(100);
				Integer compSequencia2 = Optional.ofNullable(cargaEmpresa2.getSequencia()).orElse(100);

				int compareSequencia = compSequencia1.compareTo(compSequencia2);

				if (compareSequencia != 0) {
					return compareSequencia;
				}

				Integer compId1 = Optional.ofNullable(cargaEmpresa1.getId()).orElse(0);
				Integer compId2 = Optional.ofNullable(cargaEmpresa2.getId()).orElse(0);

				int compareId = compId1.compareTo(compId2);

				if (compareId != 0) {
					return compareId;
				}

				return cargaEmpresa1.getCfgCarga().getId().compareTo(cargaEmpresa2.getCfgCarga().getId());
			}
		});
	}

}

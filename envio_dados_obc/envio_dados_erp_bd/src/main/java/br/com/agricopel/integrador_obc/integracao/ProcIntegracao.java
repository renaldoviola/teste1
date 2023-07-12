package br.com.agricopel.integrador_obc.integracao;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.bo.CfgEmpresaBO;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;

public class ProcIntegracao {

	public void executar() {

		try {
			List<CfgEmpresaModel> empresas = new CfgEmpresaBO().getEmpresasAtivas();

			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

			for (CfgEmpresaModel empresa : empresas) {

				ProcEmpresa procEmpresa = new ProcEmpresa(empresa);
				scheduler.scheduleWithFixedDelay(procEmpresa, 0, 5, TimeUnit.MINUTES);
			}
		} catch (Exception e) {
			LogUtils.escreverLogErro(e);
		}
	}

}

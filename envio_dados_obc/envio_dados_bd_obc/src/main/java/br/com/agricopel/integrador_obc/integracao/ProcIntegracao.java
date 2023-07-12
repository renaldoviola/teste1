package br.com.agricopel.integrador_obc.integracao;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.agricopel.integrador_obc.bo.CfgCicloEnvioObcBO;
import br.com.agricopel.integrador_obc.model.CfgCicloEnvioObcModel;
import br.com.agricopel.integrador_obc.utils.LogUtils;

public class ProcIntegracao {

	public void executar() {

		try {
			LogUtils.escreverLogInfo("INCIANDO EXECUÇÃO");

			new CfgCicloEnvioObcBO().sincronizar();

			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

			List<CfgCicloEnvioObcModel> enviosObc = new CfgCicloEnvioObcBO().getCiclosAtivos();

			for (CfgCicloEnvioObcModel envioObc : enviosObc) {
				if (envioObc.getEntidade() != null) {
					scheduler.scheduleWithFixedDelay(new ProcEntidadeObc(envioObc), 0, 20, TimeUnit.SECONDS);
				}
			}

		} catch (Exception e) {
			LogUtils.escreverLogErro(e);
		}
	}

}
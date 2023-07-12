package br.com.agricopel.integrador_obc.obc;

import br.com.agricopel.integrador_obc.obc.model.envio.ObcEnvSdcv;
import br.com.agricopel.integrador_obc.obc.model.recebimento.ObcRecConfEntidadeObc;

public class ServicosRestObc {

	public ObcRecConfEntidadeObc addSdcv(ObcEnvSdcv obcEnvSdcv) throws Exception {

		obcEnvSdcv.setAcao("I");
		obcEnvSdcv.setAutenticacao(AutenticacaoObc.getInstance().getAutenticacao());

		ObcRecConfEntidadeObc obcRecConfEntidadeObc = new ConexaoRestObc().execPost(ObcRecConfEntidadeObc.class,
				obcEnvSdcv, "addSdcv");

		if (obcRecConfEntidadeObc.getSucesso() == 0) {
			obcRecConfEntidadeObc.setSdc_codigo(0);
		}
		
		return obcRecConfEntidadeObc;
	}

}

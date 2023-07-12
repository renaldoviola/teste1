package br.com.agricopel.gerasdcvobc.obc;

import br.com.agricopel.gerasdcvobc.obc.model.envio.ObcEnvSdcv;
import br.com.agricopel.gerasdcvobc.obc.model.recebimento.ObcRecConfEntidadeObc;

public class ServicosRestObc {

	public ObcRecConfEntidadeObc addSdcv(ObcEnvSdcv obcEnvSdcv) throws Exception {

		obcEnvSdcv.setAcao("I");
		obcEnvSdcv.setAutenticacao(AutenticacaoObc.getInstance().getAutenticacao());

		ObcRecConfEntidadeObc obcRecConfEntidadeObc = new ConexaoRestObc().execPost(ObcRecConfEntidadeObc.class,
				obcEnvSdcv, "addSdcv");

		return obcRecConfEntidadeObc;
	}

}

package br.com.agricopel.integrador_obc.comunicacaoWS;

import br.com.agricopel.integrador_obc.comunicacaoWS.model.envio.ObcEnvEntidadeObc;
import br.com.agricopel.integrador_obc.comunicacaoWS.model.recebimento.ObcRecConfEntidadeObc;
import br.com.agricopel.integrador_obc.model.EnvioObcModel;

public class ServicosRestObc {

	public ObcRecConfEntidadeObc addIntegraErpObc(EnvioObcModel envioObcModel) throws Exception {

		ObcEnvEntidadeObc obcEnvEntidadeObc = new ObcEnvEntidadeObc();

		obcEnvEntidadeObc.setAcao(envioObcModel.getAcao());
		obcEnvEntidadeObc.setAutenticacao(AutenticacaoObc.getInstance().getAutenticacao());
		obcEnvEntidadeObc.set_interface(envioObcModel.getEntidade().name());
		obcEnvEntidadeObc.setDados(envioObcModel.getDados());

		ObcRecConfEntidadeObc obcRecConfEntidadeObc = new ConexaoRestObc().execPost(ObcRecConfEntidadeObc.class,
				obcEnvEntidadeObc, "addIntegraErpObc");
		
		return obcRecConfEntidadeObc;
	}

}

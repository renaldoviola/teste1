package br.com.agricopel.integrador_obc.integracao;

import java.util.List;

import br.com.agricopel.integrador_obc.comunicacaoWS.AutenticacaoObc;
import br.com.agricopel.integrador_obc.comunicacaoWS.ServicosRestObc;
import br.com.agricopel.integrador_obc.comunicacaoWS.model.recebimento.ObcRecConfEntidadeObc;
import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.dao.DadosEnvioDAO;
import br.com.agricopel.integrador_obc.model.EnvioObcModel;
import br.com.agricopel.integrador_obc.utils.LogUtils;

public class ProcEnvioDados implements Runnable {

	private List<EnvioObcModel> enviosObc;
	private ServicosRestObc servicosRestObc;

	public ProcEnvioDados(List<EnvioObcModel> enviosObc) {
		this.enviosObc = enviosObc;
		this.servicosRestObc = new ServicosRestObc();
	}

	@Override
	public void run() {

		try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {

			DadosEnvioDAO dadosEnvioDAO = new DadosEnvioDAO(conexaoIntegracaoOBC);

			enviosObc.forEach(envioObcModel -> procEnvio(envioObcModel, dadosEnvioDAO));
		} catch (Exception e) {
			LogUtils.escreverLogErro(e);
		}
	}

	private void procEnvio(EnvioObcModel envioObcModel, DadosEnvioDAO dadosEnvioDAO) {

		try {
			try {
				ObcRecConfEntidadeObc obcRecConfEntidadeObc = servicosRestObc.addIntegraErpObc(envioObcModel);

				if (obcRecConfEntidadeObc.getSucesso() == 0) {

					envioObcModel.setUltimoErro(obcRecConfEntidadeObc.getErro());
					LogEnvioErro(envioObcModel);

					if (obcRecConfEntidadeObc.getErro().contains("Favor realizar autenticacao")) {
						AutenticacaoObc.getInstance().resetar();
						procEnvio(envioObcModel, dadosEnvioDAO);
					} else {
						dadosEnvioDAO.envioErro(envioObcModel, true);
					}

				} else {
					dadosEnvioDAO.envioSucesso(envioObcModel);
				}
			} catch (Exception e) {
				envioObcModel.setUltimoErro(e.getMessage());
				LogEnvioErro(envioObcModel);

				dadosEnvioDAO.envioErro(envioObcModel, false);
			}
		} catch (Exception e) {
			LogUtils.escreverLogErro(e);
		}
	}

	private void LogEnvioErro(EnvioObcModel envioObc) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("ENVIO_ERRO -");
		stringBuilder.append("[ENT:").append(envioObc.getEntidade()).append("]");
		stringBuilder.append("[MSG_ERRO:").append(envioObc.getUltimoErro().trim()).append("]");
		stringBuilder.append("[CHAVE:").append(envioObc.getChave()).append("]");
		stringBuilder.append("[DADOS:").append(envioObc.getDados().replaceAll("#SEP#", "|")).append("]");
		stringBuilder.append("[TENTATIVA:").append(envioObc.getTentativasEnvio()).append("]");

		LogUtils.escreverLogInfo(stringBuilder.toString());
	}

//	private void LogEnvioSucesso(EnvioObcModel envioObc) {
//
//		StringBuilder stringBuilder = new StringBuilder();
//
//		stringBuilder.append("ENVIO_OK -");
//		stringBuilder.append("[ENT:").append(envioObc.getEntidade()).append("]");
//		stringBuilder.append("[DADOS:").append(envioObc.getDados().replaceAll("#SEP#", "|")).append("]");
//
//		LogUtils.escreverLogInfo(stringBuilder.toString());
//	}

}

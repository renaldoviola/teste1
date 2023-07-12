package br.com.agricopel.integrador_obc.integracao;

import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.integrador_obc.bo.DadosEnvioBO;
import br.com.agricopel.integrador_obc.model.CfgCicloEnvioObcModel;
import br.com.agricopel.integrador_obc.model.EnvioObcModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.utils.LogUtils;

public class ProcEntidadeObc implements Runnable {

	private CfgCicloEnvioObcModel cicloEnvioObc;

	public ProcEntidadeObc(CfgCicloEnvioObcModel cicloEnvioObc) {
		this.cicloEnvioObc = cicloEnvioObc;
	}

	@Override
	public void run() {

		try {
			this.logIni();

			List<EnvioObcModel> envioObcs = new DadosEnvioBO().getDados(cicloEnvioObc);

			if (envioObcs.size() > 0) {
				new ProcEnvioDados(envioObcs).run();
			}

			this.logFim(envioObcs.size());

		} catch (Exception e) {
			LogUtils.escreverLogErro(e);
		}
	}

	private void logIni() {

		StringBuilder mensagem = new StringBuilder();

		mensagem.append("Inicio Envio - ");
		mensagem.append("[ENT: ").append(cicloEnvioObc.getEntidade().name().toUpperCase()).append("]");

		LogUtils.escreverLogInfo(mensagem.toString());
	}

	private void logFim(int qtdeRegs) {

		StringBuilder mensagem = new StringBuilder();

		mensagem.append("Fim Envio - ");
		mensagem.append("[ENT: ").append(cicloEnvioObc.getEntidade().name().toUpperCase()).append("]");
		mensagem.append("[REGS: ").append(qtdeRegs).append("]");

		LogUtils.escreverLogInfo(mensagem.toString());
	}

	private int definirQtdeThreads(List<EnvioObcModel> envioObcs) {

		int qtdeRegistros = envioObcs.size();
		int qtdeThreads = Math.round(qtdeRegistros / 50);
		int qtdeMaxThreads = 5;

		if (this.cicloEnvioObc.getEntidade().equals(EntidadeObcEnum.ITEM)
				|| this.cicloEnvioObc.getEntidade().equals(EntidadeObcEnum.ITEM_FILIAL)) {
			qtdeMaxThreads = 10;
		}

		if (qtdeThreads > qtdeMaxThreads) {
			return qtdeMaxThreads;
		} else {
			if (qtdeThreads <= 0) {
				return 1;
			} else {
				return qtdeRegistros;
			}
		}
	}

	private List<List<EnvioObcModel>> dividirListaEnvio(List<EnvioObcModel> ls, int iParts) {

		if (iParts <= 0) {
			iParts = 1;
		}

		final List<List<EnvioObcModel>> lsParts = new ArrayList<>();
		final int iChunkSize = ls.size() / iParts;
		int iLeftOver = ls.size() % iParts;
		int iTake = iChunkSize;

		for (int i = 0, iT = ls.size(); i < iT; i += iTake) {
			if (iLeftOver > 0) {
				iLeftOver--;

				iTake = iChunkSize + 1;
			} else {
				iTake = iChunkSize;
			}

			lsParts.add(new ArrayList<EnvioObcModel>(ls.subList(i, Math.min(iT, i + iTake))));
		}

		return lsParts;
	}

}

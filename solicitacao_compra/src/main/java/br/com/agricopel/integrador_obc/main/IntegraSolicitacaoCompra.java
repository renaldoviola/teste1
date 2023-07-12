package br.com.agricopel.integrador_obc.main;

import java.time.ZoneId;
import java.util.List;

import br.com.agricopel.comp.CompConfigs;
import br.com.agricopel.comp.exception.TratarException;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.dbgint.bo.SolicitacaoCompraDbgBO;
import br.com.agricopel.integrador_obc.dbgint.model.SolicitacaoCompraDbg;
import br.com.agricopel.integrador_obc.obc.EnviarSdcvService;
import br.com.agricopel.integrador_obc.obc.SdcvObcBO;
import br.com.agricopel.integrador_obc.obc.model.SdcvObc;

public class IntegraSolicitacaoCompra {

	public void integrarSolicitacaoNova() {

		if (CompConfigs.isTeste)
			LogUtils.escreverLogInfo("Iniciando integracao de pedidos de compra do portal OBC EM MODO DE TESTE!");
		else
			LogUtils.escreverLogInfo("Iniciando integracao de solicitacao de compra do DbGint!");

		LogUtils.escreverLogInfo("ZoneId: ".concat(ZoneId.systemDefault().toString()));

		while (true) {

			try {
				SolicitacaoCompraDbgBO solicitacaoCompraDbgBO = new SolicitacaoCompraDbgBO();
				List<SolicitacaoCompraDbg> solicitacaoCompraDbgs = solicitacaoCompraDbgBO.getSolicitacoes();

				if (solicitacaoCompraDbgs != null && !solicitacaoCompraDbgs.isEmpty()) {
					Integer totalSolicitacoes = solicitacaoCompraDbgs.size();
					LogUtils.escreverLogInfo(
							"Foram encontradas ".concat(totalSolicitacoes.toString()).concat(" solicitações"));

					for (SolicitacaoCompraDbg solicitacaoCompraDbg : solicitacaoCompraDbgs) {

						LogUtils.escreverLogInfo("Processando solicitação: "
								.concat(solicitacaoCompraDbg.getSTG_GEN_TABEMP_Codigo().toString()).concat("-")
								.concat(solicitacaoCompraDbg.getSTG_GEN_TABFIL_Codigo().toString()).concat("-")
								.concat(solicitacaoCompraDbg.getCOM_SOLICI_Numero().toString()));

						if (solicitacaoCompraDbg.getItens().size() > 0) {
							List<SdcvObc> sdcvs = new SdcvObcBO().getSdcvs(solicitacaoCompraDbg);
							new EnviarSdcvService().processar(sdcvs,
									"daniela.b@agricopel.com.br", "rodrigo.g@agricopel.com.br,suporte.sistemas@agricopel.com.br");
							solicitacaoCompraDbgBO.confirmarGeracaoSDCVs(solicitacaoCompraDbg);
						} else {
							LogUtils.escreverLogInfo("Solicitação sem itens!");
						}
					}

					aguardar(true);
				} else {
					LogUtils.escreverLogInfo("Não foram encontradas solicitações para integrar!");
					aguardar(false);
				}
			} catch (Exception e) {
				TratarException.tratarExcecao(e);
				aguardar(false);
			}
		}
	}

	private void aguardar(boolean cicloCurto) {

		try {
			long tempoEspera = 0;

			if (cicloCurto) {
				tempoEspera = 1000 * 10;
				LogUtils.escreverLogInfo("Aguardando 10 segundos para buscar novamente!");
			} else {
				tempoEspera = 1000 * 60 * 5;
				LogUtils.escreverLogInfo("Aguardando 5 minutos para buscar novamente!");
			}

			Thread.sleep(tempoEspera);
		} catch (InterruptedException e) {
			TratarException.tratarExcecao(e);
		}
	}
}
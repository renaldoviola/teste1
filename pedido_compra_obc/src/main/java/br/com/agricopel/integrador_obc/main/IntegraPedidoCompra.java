package br.com.agricopel.integrador_obc.main;

import java.time.ZoneId;

import br.com.agricopel.comp.CompConfigs;
import br.com.agricopel.comp.exception.AgrException;
import br.com.agricopel.comp.exception.FornecedorNaoEncontradoException;
import br.com.agricopel.comp.exception.TratarException;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.dbgint.bo.PedidoCompraDbgBO;
import br.com.agricopel.integrador_obc.emsys.bo.PedidoCompraEmvBO;
import br.com.agricopel.integrador_obc.obc.bo.FornecedorObcBO;
import br.com.agricopel.integrador_obc.obc.bo.PedidoCompraObcBO;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.AutenticacaoObc;
import br.com.agricopel.integrador_obc.obc.model.FornecedorObc;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraObc;
import br.com.agricopel.integrador_obc.obc.model.RetGravarPedidoObc;
import br.com.agricopel.integrador_obc.protheus.bo.PedidoCompraPrtBO;

public class IntegraPedidoCompra {

	public void integrarPedidoNovo() {

		Integer lastRecNo = 0;
		PedidoCompraObcBO pedidoCompraObcBO = new PedidoCompraObcBO();

		if (CompConfigs.isTeste)
			LogUtils.escreverLogInfo("Iniciando integracao de pedidos de compra do portal OBC EM MODO DE TESTE!");
		else
			LogUtils.escreverLogInfo("Iniciando integracao de pedidos de compra do portal OBC!");

		LogUtils.escreverLogInfo("ZoneId: ".concat(ZoneId.systemDefault().toString()));

		while (true) {
			try {
				PedidoCompraObc pedidoCompraObc = pedidoCompraObcBO.buscarPedido();

				if (pedidoCompraObc != null) {

					if (pedidoCompraObc.getRecNo() == lastRecNo) {
						LogUtils.escreverLogInfo("[PROBLEMA] - RECNO REPETIDO!");
					} else {
						lastRecNo = pedidoCompraObc.getRecNo();
					}

					RetGravarPedidoObc retGravarPedidoObc = gravarPedido(pedidoCompraObc);

					pedidoCompraObcBO.confirmarGravacaoPedido(retGravarPedidoObc, pedidoCompraObc);

					aguardar(true);
				} else {
					LogUtils.escreverLogInfo("Nao foi retornado nenhum pedido de getNextPedido do OBC!");

					aguardar(false);
				}

			} catch (Exception e) {
				TratarException.tratarExcecao(e);

				if (e.getMessage().contains("Sessao nao autenticada")) {
					LogUtils.escreverLogInfo("Resetando autenticacao!");
					AutenticacaoObc.getInstance().resetar();
				}

				aguardar(false);
			}
		}
	}

	private void carregarFornecedorPedido(PedidoCompraObc pedidoCompraObc) throws Exception {

		FornecedorObc fornecedorObc = new FornecedorObcBO()
				.getFornecedorCadastrado(pedidoCompraObc.getCnpjFornecedor());

		pedidoCompraObc.setFornecedorObc(fornecedorObc);

		LogUtils.escreverLogInfo("Buscado fornecedor em OBC. Pedido: ".concat(pedidoCompraObc.getCodPedidoObc())
				.concat(" - CNPJ: ").concat(pedidoCompraObc.getCnpjFornecedor()).concat(" - Razão: ")
				.concat(fornecedorObc.getRazaoSocial()));
	}

	private RetGravarPedidoObc gravarPedido(PedidoCompraObc pedidoCompraObc) {

		try {
			try {
				switch (pedidoCompraObc.getSoftware()) {
				case ATS:
					return procSucesso(pedidoCompraObc);
				case DBG:
					return new PedidoCompraDbgBO().gravar(pedidoCompraObc);
				case PRT:
					return new PedidoCompraPrtBO().gravar(pedidoCompraObc);
				case EMV:
					return new PedidoCompraEmvBO().gravar(pedidoCompraObc);
				default:
					throw new AgrException("Não foi possível identificar o tipo de software para integrar");
				}
			} catch (FornecedorNaoEncontradoException e) {

				LogUtils.escreverLogInfo(e.getMessage());

				if (pedidoCompraObc.getFornecedorObc() == null) {

					carregarFornecedorPedido(pedidoCompraObc);
					LogUtils.escreverLogInfo("Enviando pedido para nova gravação junto com dados do fornecedor!");

					return gravarPedido(pedidoCompraObc);
				} else {
					LogUtils.escreverLogInfo(
							"Fornecedor já enviado junto com pedido e ainda não encontrou o fornecedor, cancelando inserção do pedido!");
					throw e;
				}
			}
		} catch (Exception e) {
			LogUtils.escreverLogInfo("Houve Falha ao gravar Pedido");
			TratarException.tratarExcecao(e);

			RetGravarPedidoObc retGravarPedidoObc = new RetGravarPedidoObc();

			retGravarPedidoObc.setSucesso(Boolean.FALSE);
			retGravarPedidoObc.setMensagem(e.toString());

			return retGravarPedidoObc;
		}
	}

	private RetGravarPedidoObc procSucesso(PedidoCompraObc pedidoCompraObc) {
		RetGravarPedidoObc retGravarPedidoObc = new RetGravarPedidoObc();

		retGravarPedidoObc.setSucesso(Boolean.TRUE);
		retGravarPedidoObc.setMensagem("");
		retGravarPedidoObc.setCodPedido(pedidoCompraObc.getCodPedidoObc());

		return retGravarPedidoObc;
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
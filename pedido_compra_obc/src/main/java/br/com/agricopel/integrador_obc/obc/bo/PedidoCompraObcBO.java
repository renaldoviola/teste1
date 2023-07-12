package br.com.agricopel.integrador_obc.obc.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.ServicosRestObc;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecAnexoCapa;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecAnexoItem;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecPedido;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraItemAnexoObc;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraObc;
import br.com.agricopel.integrador_obc.obc.model.RetGravarPedidoObc;

public class PedidoCompraObcBO {

	public PedidoCompraObc buscarPedido() throws Exception {

		ObcRecPedido obcRecPedido = new ServicosRestObc().getNextPedido();

		if (obcRecPedido != null && obcRecPedido.getDados() != null && !obcRecPedido.getDados().isEmpty()) {
			return new PedidoCompraObc(obcRecPedido);
		} else {
			return null;
		}
	}

	public List<PedidoCompraItemAnexoObc> carregarAnexos(String numSDCV) throws Exception {

		ServicosRestObc servicosRestObc = new ServicosRestObc();

		LogUtils.escreverLogInfo("Carregando anexos para SDCV ".concat(numSDCV));

		List<PedidoCompraItemAnexoObc> anexosPedidoItemCompraObc = new ArrayList<>();
		ObcRecAnexoCapa obcRecAnexo = servicosRestObc.getAnexos("SDCV", numSDCV);

		if (obcRecAnexo.getAnexos() != null) {
			for (ObcRecAnexoItem obcRecAnexoItem : obcRecAnexo.getAnexos()) {
				PedidoCompraItemAnexoObc pedidoCompraItemAnexoObc = new PedidoCompraItemAnexoObc();

				pedidoCompraItemAnexoObc.setAnexo(servicosRestObc.getAnexo(obcRecAnexoItem.getUrl()));
				pedidoCompraItemAnexoObc.setNomeArquivo(obcRecAnexoItem.getNome());

				anexosPedidoItemCompraObc.add(pedidoCompraItemAnexoObc);
			}
		}

		return anexosPedidoItemCompraObc;
	}

	public void confirmarGravacaoPedido(RetGravarPedidoObc retGravarPedidoObc, PedidoCompraObc pedidoCompraObc)
			throws Exception {

		if (retGravarPedidoObc.getSucesso()) {
			LogUtils.escreverLogInfo("Pedido gerado: ".concat(retGravarPedidoObc.getCodPedido()));

			setPedidoProcessado(retGravarPedidoObc, pedidoCompraObc);
			setNrPedidoGerado(retGravarPedidoObc, pedidoCompraObc);
		} else {
			LogUtils.escreverLogInfo("Falha ao gerar pedido: ".concat(retGravarPedidoObc.getMensagem()));

			setPedidoProcessado(retGravarPedidoObc, pedidoCompraObc);
		}
	}

	private void setNrPedidoGerado(RetGravarPedidoObc retGravarPedidoObc, PedidoCompraObc pedidoCompraObc)
			throws Exception {

		LogUtils.escreverLogInfo("Confirmando número do pedido no portal OBC");

		new ServicosRestObc().setNrPedidoProcessado(pedidoCompraObc.getCodPedidoObc(),
				retGravarPedidoObc.getCodPedido());
	}

	private void setPedidoProcessado(RetGravarPedidoObc retGravarPedidoObc, PedidoCompraObc pedidoCompraObc)
			throws Exception {

		LogUtils.escreverLogInfo("Enviando resultado do processamento do pedido para portal OBC");

		new ServicosRestObc().setPedidoProcessado(retGravarPedidoObc.getSucesso(), retGravarPedidoObc.getMensagem(),
				pedidoCompraObc.getRecNo());
	}

}
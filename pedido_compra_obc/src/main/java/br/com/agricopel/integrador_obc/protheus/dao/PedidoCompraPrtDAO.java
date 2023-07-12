package br.com.agricopel.integrador_obc.protheus.dao;

import br.com.agricopel.integrador_obc.protheus.comunicacaoWs.ConexaoWSPrt;
import br.com.agricopel.integrador_obc.protheus.model.PedidoCompraPrt;
import br.com.agricopel.integrador_obc.protheus.model.RetGravarPedidoPrt;

public class PedidoCompraPrtDAO {

	public RetGravarPedidoPrt gravarPedido(PedidoCompraPrt pedidoCompraPrt) throws Exception {

		ConexaoWSPrt conexaoServer = new ConexaoWSPrt();
		return conexaoServer.execPost(RetGravarPedidoPrt.class, pedidoCompraPrt, "pedido_obc");
	}

}

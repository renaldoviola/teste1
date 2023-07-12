package br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento;

import java.util.List;

public class ObcRecPedido extends ObcRec {

	private Boolean temPedido;

	private String statusIntegracao;

	private String msgErro;

	private Integer nrecno;

	private Integer compra;

	private String dados;

	private List<ObcRecPedidoItem> pedidoItens;

	private List<ObcSubRecRateio> rateios;

	public Boolean getTemPedido() {
		return temPedido;
	}

	public void setTemPedido(Boolean temPedido) {
		this.temPedido = temPedido;
	}

	public String getStatusIntegracao() {
		return statusIntegracao;
	}

	public void setStatusIntegracao(String statusIntegracao) {
		this.statusIntegracao = statusIntegracao;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public Integer getNrecno() {
		return nrecno;
	}

	public void setNrecno(Integer nrecno) {
		this.nrecno = nrecno;
	}

	public Integer getCompra() {
		return compra;
	}

	public void setCompra(Integer compra) {
		this.compra = compra;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public List<ObcRecPedidoItem> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<ObcRecPedidoItem> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}

	public List<ObcSubRecRateio> getRateios() {
		return rateios;
	}

	public void setRateios(List<ObcSubRecRateio> rateios) {
		this.rateios = rateios;
	}

}

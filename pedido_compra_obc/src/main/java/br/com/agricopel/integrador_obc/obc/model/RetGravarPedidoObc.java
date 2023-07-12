package br.com.agricopel.integrador_obc.obc.model;

public class RetGravarPedidoObc {

	private Boolean sucesso;
	private String codPedido;
	private String mensagem;

	public RetGravarPedidoObc() {
	}

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}

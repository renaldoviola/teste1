package br.com.agricopel.integrador_obc.protheus.comunicacaoWs;

public class RetornoPrtWS {

	private Boolean sucesso;
	private String mensagem;
	private String erro;

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
}
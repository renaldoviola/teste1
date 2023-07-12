package br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.envio;

public class ObcEnvPedidoProcessado extends ObcEnv {

	private Boolean sucesso;

	private String msgErro;

	private Integer nrecno;
	
	private String email;

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}

	
	
	
	public Integer getNrecno() {
		return nrecno;
	}

	public void setNrecno(Integer nrecno) {
		this.nrecno = nrecno;
	}

}

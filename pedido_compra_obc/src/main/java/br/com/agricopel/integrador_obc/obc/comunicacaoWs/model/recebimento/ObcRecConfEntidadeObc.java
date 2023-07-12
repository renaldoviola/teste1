package br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento;

public class ObcRecConfEntidadeObc extends ObcRec {

	private String dados;

	private Integer nrecno;

	private String erro;

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public Integer getNrecno() {
		return nrecno;
	}

	public void setNrecno(Integer nrecno) {
		this.nrecno = nrecno;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}

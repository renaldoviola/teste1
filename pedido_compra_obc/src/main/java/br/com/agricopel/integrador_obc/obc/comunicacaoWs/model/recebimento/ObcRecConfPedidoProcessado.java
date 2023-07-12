package br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento;

public class ObcRecConfPedidoProcessado extends ObcRec {

	private int nrecno;

	private String erro;

	public int getNrecno() {
		return nrecno;
	}

	public void setNrecno(int nrecno) {
		this.nrecno = nrecno;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}

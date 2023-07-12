package br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento;

public class ObcRecAnexoItem {

	private String nome;
	private String data;
	private String hora;
	private String url;
	private String ambiente;
	private String sdcCodigo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getSdcCodigo() {
		return sdcCodigo;
	}

	public void setSdcCodigo(String sdcCodigo) {
		this.sdcCodigo = sdcCodigo;
	}

}

package br.com.agricopel.gerasdcvobc.obc.model.envio;

public class ObcEnvSdcv extends ObcEnv {

	private ObcEnvRateioSdcv[] vtRateios;

	private String acao;
	private String codigo;
	private String dados;

	public ObcEnvRateioSdcv[] getVtRateios() {
		return vtRateios;
	}

	public void setVtRateios(ObcEnvRateioSdcv[] vtRateios) {
		this.vtRateios = vtRateios;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

}

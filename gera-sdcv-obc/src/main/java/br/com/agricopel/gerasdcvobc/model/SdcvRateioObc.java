package br.com.agricopel.gerasdcvobc.model;

public class SdcvRateioObc {

	private String codCentroCusto;
	private String codContaContabil;
	private Double pRateio;

	public String getCodCentroCusto() {
		return codCentroCusto;
	}

	public void setCodCentroCusto(String codCentroCusto) {
		this.codCentroCusto = codCentroCusto;
	}

	public String getCodContaContabil() {
		return codContaContabil;
	}

	public void setCodContaContabil(String codContaContabil) {
		this.codContaContabil = codContaContabil;
	}

	public Double getpRateio() {
		return pRateio;
	}

	public void setpRateio(Double pRateio) {
		this.pRateio = pRateio;
	}

}

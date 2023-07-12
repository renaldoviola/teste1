package br.com.agricopel.integrador_obc.protheus.model;

public class PedidoRatCCustoPrt {

	private String codSdcv;
	private String centroCusto;
	private String contaContabil;
	private Double pRateio;

	public String getCodSdcv() {
		return codSdcv;
	}

	public void setCodSdcv(String codSdcv) {
		this.codSdcv = codSdcv;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public String getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}

	public Double getpRateio() {
		return pRateio;
	}

	public void setpRateio(Double pRateio) {
		this.pRateio = pRateio;
	}

}

package br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento;

import java.util.List;

public class ObcRecAnexoCapa extends ObcRec {

	private String codigoAmbiente;
	private String ambiente;
	private Integer qtd;

	private List<ObcRecAnexoItem> anexos;

	public String getCodigoAmbiente() {
		return codigoAmbiente;
	}

	public void setCodigoAmbiente(String codigoAmbiente) {
		this.codigoAmbiente = codigoAmbiente;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public List<ObcRecAnexoItem> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<ObcRecAnexoItem> anexos) {
		this.anexos = anexos;
	}

}

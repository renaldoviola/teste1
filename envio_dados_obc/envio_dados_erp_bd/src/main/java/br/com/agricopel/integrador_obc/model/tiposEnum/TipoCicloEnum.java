package br.com.agricopel.integrador_obc.model.tiposEnum;

public enum TipoCicloEnum {

	NEN("Nenhum"), CTR("Change Tracking (ctr)"), DTH("Data/Hora (dth)");

	private String label;

	private TipoCicloEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public String getValor() {
		return this.name();
	}
}

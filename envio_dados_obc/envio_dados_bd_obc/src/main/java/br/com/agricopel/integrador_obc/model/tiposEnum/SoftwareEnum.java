package br.com.agricopel.integrador_obc.model.tiposEnum;

public enum SoftwareEnum {

	PRT("Protheus"), ATS("Autosystem"), DBG("DBGint");

	private String label;

	private SoftwareEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public String getValor() {
		return this.name();
	}

}

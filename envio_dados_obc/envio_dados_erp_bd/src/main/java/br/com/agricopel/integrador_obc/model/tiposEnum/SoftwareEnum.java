package br.com.agricopel.integrador_obc.model.tiposEnum;

public enum SoftwareEnum {

	PRT("Protheus (codemp)"), ATS("Autosystem"), DBG("DBGint"), EMV("EmSys Varejo");

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

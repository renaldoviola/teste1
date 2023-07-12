package br.com.agricopel.gerasdcvobc.obc.model.recebimento;

public class ObcRecConfEntidadeObc extends ObcRec {

	private Integer sdc_codigo;
	private String sdc_status;
	private Integer nrecno;
	private String erro;

	public Integer getSdc_codigo() {
		return sdc_codigo;
	}

	public void setSdc_codigo(Integer sdc_codigo) {
		this.sdc_codigo = sdc_codigo;
	}

	public String getSdc_status() {
		return sdc_status;
	}

	public void setSdc_status(String sdc_status) {
		this.sdc_status = sdc_status;
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

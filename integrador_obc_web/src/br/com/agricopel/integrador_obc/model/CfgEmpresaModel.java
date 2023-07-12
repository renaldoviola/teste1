package br.com.agricopel.integrador_obc.model;

public class CfgEmpresaModel {

	private Integer id;
	private String descricao;
	private String status;
	private String codProtheus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodProtheus() {
		return codProtheus;
	}

	public void setCodProtheus(String codProtheus) {
		this.codProtheus = codProtheus;
	}

}

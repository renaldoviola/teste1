package br.com.agricopel.integrador_obc.model;

import java.util.List;

public class ThreadModel {

	private Integer codigo;
	private String descricao;
	private Integer ciclo_minutos;
	private String status;
	private List<SqlModel> sqlModels;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCiclo_minutos() {
		return ciclo_minutos;
	}

	public void setCiclo_minutos(Integer ciclo_minutos) {
		this.ciclo_minutos = ciclo_minutos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SqlModel> getSqlModels() {
		return sqlModels;
	}

	public void setSqlModels(List<SqlModel> sqlModels) {
		this.sqlModels = sqlModels;
	}

}

package br.com.agricopel.integrador_obc.model;

import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;

public class CfgCicloEnvioObcModel {

	private int id;
	private EntidadeObcEnum entidade;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EntidadeObcEnum getEntidade() {
		return entidade;
	}

	public void setEntidade(EntidadeObcEnum entidade) {
		this.entidade = entidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

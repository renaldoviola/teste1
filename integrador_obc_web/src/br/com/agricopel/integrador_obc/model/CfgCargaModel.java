package br.com.agricopel.integrador_obc.model;

import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.SoftwareEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.TipoCicloEnum;

public class CfgCargaModel {

	private Integer id;
	private String descricao;
	private String status;
	private EntidadeObcEnum entidade;
	private String sqlCarga;
	private SoftwareEnum software;
	private TipoCicloEnum tipoCiclo;

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

	public EntidadeObcEnum getEntidade() {
		return entidade;
	}

	public void setEntidade(EntidadeObcEnum entidade) {
		this.entidade = entidade;
	}

	public String getSqlCarga() {
		return sqlCarga;
	}

	public void setSqlCarga(String sqlCarga) {
		this.sqlCarga = sqlCarga;
	}

	public SoftwareEnum getSoftware() {
		return software;
	}

	public void setSoftware(SoftwareEnum software) {
		this.software = software;
	}

	public TipoCicloEnum getTipoCiclo() {
		return tipoCiclo;
	}

	public void setTipoCiclo(TipoCicloEnum tipoCiclo) {
		this.tipoCiclo = tipoCiclo;
	}
}
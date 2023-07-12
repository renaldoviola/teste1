package br.com.agricopel.gerasdcvobc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "cfg_filial")
public class Filial {

	@Id
	private Integer id;

	private String codigo;

	private String descricao;

	private String pref_cod_produto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPref_cod_produto() {
		return pref_cod_produto;
	}

	public void setPref_cod_produto(String pref_cod_produto) {
		this.pref_cod_produto = pref_cod_produto;
	}

}

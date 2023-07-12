package br.com.agricopel.gerasdcvobc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "obc_item")
public class Produto {

	@Id
	private Integer id;

	private String codigo;

	@Column(name = "conta_contabil")
	private String contaContabil;

	@Column(name = "un_compra")
	private String unCompra;

	private String prazo;

	private String status;

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

	public String getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}

	public String getUnCompra() {
		return unCompra;
	}

	public void setUnCompra(String unCompra) {
		this.unCompra = unCompra;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

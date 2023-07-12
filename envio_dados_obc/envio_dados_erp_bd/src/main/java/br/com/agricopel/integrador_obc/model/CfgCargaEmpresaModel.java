package br.com.agricopel.integrador_obc.model;

import java.time.LocalDateTime;

public class CfgCargaEmpresaModel {

	private Integer id;
	private Integer idCarga;
	private Integer idEmpresa;
	private Long cicloChangeTracking;
	private LocalDateTime cicloDataHora;
	private Integer sequencia;
	private String status;
	private CfgCargaModel cfgCarga;
	private CfgEmpresaModel cfgEmpresa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCarga() {
		return idCarga;
	}

	public void setIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getCicloChangeTracking() {
		return cicloChangeTracking;
	}

	public void setCicloChangeTracking(Long cicloChangeTracking) {
		this.cicloChangeTracking = cicloChangeTracking;
	}

	public LocalDateTime getCicloDataHora() {
		return cicloDataHora;
	}

	public void setCicloDataHora(LocalDateTime cicloDataHora) {
		this.cicloDataHora = cicloDataHora;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CfgCargaModel getCfgCarga() {
		return cfgCarga;
	}

	public void setCfgCarga(CfgCargaModel cfgCarga) {
		this.cfgCarga = cfgCarga;
	}

	public CfgEmpresaModel getCfgEmpresa() {
		return cfgEmpresa;
	}

	public void setCfgEmpresa(CfgEmpresaModel cfgEmpresa) {
		this.cfgEmpresa = cfgEmpresa;
	}

}

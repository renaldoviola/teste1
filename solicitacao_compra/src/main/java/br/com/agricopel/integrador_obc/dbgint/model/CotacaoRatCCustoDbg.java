package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDateTime;

public class CotacaoRatCCustoDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Long COM_COTACA_Numero;
	private Integer COM_PROCOT_Sequencia;
	private Integer COM_CONCOT_Sequencia;
	private LocalDateTime COM_CONCOT_Created;
	private LocalDateTime COM_CONCOT_Updated;
	private Double COM_CONCOT_Quantidade;
	private String STG_FRT_TABCAR_Coc_Codigo;
	private String STG_GEN_TABCEN_Coc_Codigo;

	public Integer getSTG_GEN_TABEMP_Codigo() {
		return STG_GEN_TABEMP_Codigo;
	}

	public void setSTG_GEN_TABEMP_Codigo(Integer sTG_GEN_TABEMP_Codigo) {
		STG_GEN_TABEMP_Codigo = sTG_GEN_TABEMP_Codigo;
	}

	public Integer getSTG_GEN_TABFIL_Codigo() {
		return STG_GEN_TABFIL_Codigo;
	}

	public void setSTG_GEN_TABFIL_Codigo(Integer sTG_GEN_TABFIL_Codigo) {
		STG_GEN_TABFIL_Codigo = sTG_GEN_TABFIL_Codigo;
	}

	public Long getCOM_COTACA_Numero() {
		return COM_COTACA_Numero;
	}

	public void setCOM_COTACA_Numero(Long cOM_COTACA_Numero) {
		COM_COTACA_Numero = cOM_COTACA_Numero;
	}

	public Integer getCOM_PROCOT_Sequencia() {
		return COM_PROCOT_Sequencia;
	}

	public void setCOM_PROCOT_Sequencia(Integer cOM_PROCOT_Sequencia) {
		COM_PROCOT_Sequencia = cOM_PROCOT_Sequencia;
	}

	public Integer getCOM_CONCOT_Sequencia() {
		return COM_CONCOT_Sequencia;
	}

	public void setCOM_CONCOT_Sequencia(Integer cOM_CONCOT_Sequencia) {
		COM_CONCOT_Sequencia = cOM_CONCOT_Sequencia;
	}

	public LocalDateTime getCOM_CONCOT_Created() {
		return COM_CONCOT_Created;
	}

	public void setCOM_CONCOT_Created(LocalDateTime cOM_CONCOT_Created) {
		COM_CONCOT_Created = cOM_CONCOT_Created;
	}

	public LocalDateTime getCOM_CONCOT_Updated() {
		return COM_CONCOT_Updated;
	}

	public void setCOM_CONCOT_Updated(LocalDateTime cOM_CONCOT_Updated) {
		COM_CONCOT_Updated = cOM_CONCOT_Updated;
	}

	public Double getCOM_CONCOT_Quantidade() {
		return COM_CONCOT_Quantidade;
	}

	public void setCOM_CONCOT_Quantidade(Double cOM_CONCOT_Quantidade) {
		COM_CONCOT_Quantidade = cOM_CONCOT_Quantidade;
	}

	public String getSTG_FRT_TABCAR_Coc_Codigo() {
		return STG_FRT_TABCAR_Coc_Codigo;
	}

	public void setSTG_FRT_TABCAR_Coc_Codigo(String sTG_FRT_TABCAR_Coc_Codigo) {
		STG_FRT_TABCAR_Coc_Codigo = sTG_FRT_TABCAR_Coc_Codigo;
	}

	public String getSTG_GEN_TABCEN_Coc_Codigo() {
		return STG_GEN_TABCEN_Coc_Codigo;
	}

	public void setSTG_GEN_TABCEN_Coc_Codigo(String sTG_GEN_TABCEN_Coc_Codigo) {
		STG_GEN_TABCEN_Coc_Codigo = sTG_GEN_TABCEN_Coc_Codigo;
	}

}

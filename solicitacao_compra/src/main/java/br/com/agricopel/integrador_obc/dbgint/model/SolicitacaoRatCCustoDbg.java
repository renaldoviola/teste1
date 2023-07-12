package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDateTime;

public class SolicitacaoRatCCustoDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Long COM_SOLICI_Numero;
	private Integer COM_PROSOL_Sequencia;
	private Integer COM_CONSOL_Sequencia;
	private LocalDateTime COM_CONSOL_Created;
	private LocalDateTime COM_CONSOL_Updated;
	private Double COM_CONSOL_Quantidade;
	private String STG_FRT_TABCAR_Cos_Codigo;
	private String STG_GEN_TABCEN_Cos_Codigo;
	private Double COM_CONSOL_Cancelado;
	private String COM_CONSOL_Log;

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

	public Long getCOM_SOLICI_Numero() {
		return COM_SOLICI_Numero;
	}

	public void setCOM_SOLICI_Numero(Long cOM_SOLICI_Numero) {
		COM_SOLICI_Numero = cOM_SOLICI_Numero;
	}

	public Integer getCOM_PROSOL_Sequencia() {
		return COM_PROSOL_Sequencia;
	}

	public void setCOM_PROSOL_Sequencia(Integer cOM_PROSOL_Sequencia) {
		COM_PROSOL_Sequencia = cOM_PROSOL_Sequencia;
	}

	public Integer getCOM_CONSOL_Sequencia() {
		return COM_CONSOL_Sequencia;
	}

	public void setCOM_CONSOL_Sequencia(Integer cOM_CONSOL_Sequencia) {
		COM_CONSOL_Sequencia = cOM_CONSOL_Sequencia;
	}

	public LocalDateTime getCOM_CONSOL_Created() {
		return COM_CONSOL_Created;
	}

	public void setCOM_CONSOL_Created(LocalDateTime cOM_CONSOL_Created) {
		COM_CONSOL_Created = cOM_CONSOL_Created;
	}

	public LocalDateTime getCOM_CONSOL_Updated() {
		return COM_CONSOL_Updated;
	}

	public void setCOM_CONSOL_Updated(LocalDateTime cOM_CONSOL_Updated) {
		COM_CONSOL_Updated = cOM_CONSOL_Updated;
	}

	public Double getCOM_CONSOL_Quantidade() {
		return COM_CONSOL_Quantidade;
	}

	public void setCOM_CONSOL_Quantidade(Double cOM_CONSOL_Quantidade) {
		COM_CONSOL_Quantidade = cOM_CONSOL_Quantidade;
	}

	public String getSTG_FRT_TABCAR_Cos_Codigo() {
		return STG_FRT_TABCAR_Cos_Codigo;
	}

	public void setSTG_FRT_TABCAR_Cos_Codigo(String sTG_FRT_TABCAR_Cos_Codigo) {
		STG_FRT_TABCAR_Cos_Codigo = sTG_FRT_TABCAR_Cos_Codigo;
	}

	public String getSTG_GEN_TABCEN_Cos_Codigo() {
		return STG_GEN_TABCEN_Cos_Codigo;
	}

	public void setSTG_GEN_TABCEN_Cos_Codigo(String sTG_GEN_TABCEN_Cos_Codigo) {
		STG_GEN_TABCEN_Cos_Codigo = sTG_GEN_TABCEN_Cos_Codigo;
	}

	public Double getCOM_CONSOL_Cancelado() {
		return COM_CONSOL_Cancelado;
	}

	public void setCOM_CONSOL_Cancelado(Double cOM_CONSOL_Cancelado) {
		COM_CONSOL_Cancelado = cOM_CONSOL_Cancelado;
	}

	public String getCOM_CONSOL_Log() {
		return COM_CONSOL_Log;
	}

	public void setCOM_CONSOL_Log(String cOM_CONSOL_Log) {
		COM_CONSOL_Log = cOM_CONSOL_Log;
	}

	public String getCentroCustoObc() {

		if (getSTG_FRT_TABCAR_Cos_Codigo() != null && !getSTG_FRT_TABCAR_Cos_Codigo().isEmpty()) {
			return "PLACA-".concat(getSTG_FRT_TABCAR_Cos_Codigo());
		} else if (getSTG_GEN_TABCEN_Cos_Codigo() != null && !getSTG_GEN_TABCEN_Cos_Codigo().isEmpty()) {
			return getSTG_GEN_TABEMP_Codigo().toString().concat("-").concat(getSTG_GEN_TABCEN_Cos_Codigo());
		} else {
			return "";
		}
	}
	
}

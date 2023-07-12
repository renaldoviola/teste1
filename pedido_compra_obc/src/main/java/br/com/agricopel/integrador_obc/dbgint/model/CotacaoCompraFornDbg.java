package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDateTime;

public class CotacaoCompraFornDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Long COM_COTACA_Numero;
	private Long GEN_TABENT_Codigo;
	private Long GEN_ENDENT_Codigo;
	private LocalDateTime COM_ENTCOT_DHEnvioEMail;
	private LocalDateTime COM_ENTCOT_Created;
	private LocalDateTime COM_ENTCOT_Updated;

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

	public Long getGEN_TABENT_Codigo() {
		return GEN_TABENT_Codigo;
	}

	public void setGEN_TABENT_Codigo(Long gEN_TABENT_Codigo) {
		GEN_TABENT_Codigo = gEN_TABENT_Codigo;
	}

	public Long getGEN_ENDENT_Codigo() {
		return GEN_ENDENT_Codigo;
	}

	public void setGEN_ENDENT_Codigo(Long gEN_ENDENT_Codigo) {
		GEN_ENDENT_Codigo = gEN_ENDENT_Codigo;
	}

	public LocalDateTime getCOM_ENTCOT_DHEnvioEMail() {
		return COM_ENTCOT_DHEnvioEMail;
	}

	public void setCOM_ENTCOT_DHEnvioEMail(LocalDateTime cOM_ENTCOT_DHEnvioEMail) {
		COM_ENTCOT_DHEnvioEMail = cOM_ENTCOT_DHEnvioEMail;
	}

	public LocalDateTime getCOM_ENTCOT_Created() {
		return COM_ENTCOT_Created;
	}

	public void setCOM_ENTCOT_Created(LocalDateTime cOM_ENTCOT_Created) {
		COM_ENTCOT_Created = cOM_ENTCOT_Created;
	}

	public LocalDateTime getCOM_ENTCOT_Updated() {
		return COM_ENTCOT_Updated;
	}

	public void setCOM_ENTCOT_Updated(LocalDateTime cOM_ENTCOT_Updated) {
		COM_ENTCOT_Updated = cOM_ENTCOT_Updated;
	}

}

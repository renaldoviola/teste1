package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDateTime;

public class SolicitacaoCompraFornDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Long COM_SOLICI_Numero;
	private Long GEN_TABENT_Codigo;
	private Integer GEN_ENDENT_Codigo;
	private LocalDateTime COM_ENTSOL_Created;
	private LocalDateTime COM_ENTSOL_Updated;
	private String COM_ENTSOL_Sugerido;
	private String cnpj;

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

	public Long getGEN_TABENT_Codigo() {
		return GEN_TABENT_Codigo;
	}

	public void setGEN_TABENT_Codigo(Long gEN_TABENT_Codigo) {
		GEN_TABENT_Codigo = gEN_TABENT_Codigo;
	}

	public Integer getGEN_ENDENT_Codigo() {
		return GEN_ENDENT_Codigo;
	}

	public void setGEN_ENDENT_Codigo(Integer gEN_ENDENT_Codigo) {
		GEN_ENDENT_Codigo = gEN_ENDENT_Codigo;
	}

	public LocalDateTime getCOM_ENTSOL_Created() {
		return COM_ENTSOL_Created;
	}

	public void setCOM_ENTSOL_Created(LocalDateTime cOM_ENTSOL_Created) {
		COM_ENTSOL_Created = cOM_ENTSOL_Created;
	}

	public LocalDateTime getCOM_ENTSOL_Updated() {
		return COM_ENTSOL_Updated;
	}

	public void setCOM_ENTSOL_Updated(LocalDateTime cOM_ENTSOL_Updated) {
		COM_ENTSOL_Updated = cOM_ENTSOL_Updated;
	}

	public String getCOM_ENTSOL_Sugerido() {
		return COM_ENTSOL_Sugerido;
	}

	public void setCOM_ENTSOL_Sugerido(String cOM_ENTSOL_Sugerido) {
		COM_ENTSOL_Sugerido = cOM_ENTSOL_Sugerido;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}

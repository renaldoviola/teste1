package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CotacaoCompraItemDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Long COM_COTACA_Numero;
	private Integer COM_PROCOT_Sequencia;
	private Double COM_PROCOT_Quantidade;
	private String COM_PROCOT_Observacao;
	private String COM_PROCOT_Situacao;
	private LocalDateTime COM_PROCOT_Created;
	private LocalDateTime COM_PROCOT_Updated;
	private Long COM_SOLICI_Numero;
	private Integer COM_PROSOL_Sequencia;
	private String STG_EST_TABPRO_Cot_Codigo;
	private Double COM_PROCOT_Valor;
	private String STG_FRT_TABCAR_Cot_Codigo;
	private String STG_GEN_TABCEN_Cot_Codigo;
	private LocalDate COM_PROCOT_Entrega;
	private String COM_PROCOT_PrefFabricanteDesc;
	private Integer COM_PROCOT_PrefFabricante;
	private String COM_PROCOT_PrefCondPgtoDesc;
	private Integer COM_PROCOT_PrefCondPgto;
	private String COM_PROCOT_SDCV_OBC;
	private String COM_PROCOT_TIPO_OBC;

	private List<CotacaoRatCCustoDbg> ratCCusto;

	public CotacaoCompraItemDbg() {
		this.ratCCusto = new ArrayList<>();
	}

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

	public Double getCOM_PROCOT_Quantidade() {
		return COM_PROCOT_Quantidade;
	}

	public void setCOM_PROCOT_Quantidade(Double cOM_PROCOT_Quantidade) {
		COM_PROCOT_Quantidade = cOM_PROCOT_Quantidade;
	}

	public String getCOM_PROCOT_Observacao() {
		return COM_PROCOT_Observacao;
	}

	public void setCOM_PROCOT_Observacao(String cOM_PROCOT_Observacao) {
		COM_PROCOT_Observacao = cOM_PROCOT_Observacao;
	}

	public String getCOM_PROCOT_Situacao() {
		return COM_PROCOT_Situacao;
	}

	public void setCOM_PROCOT_Situacao(String cOM_PROCOT_Situacao) {
		COM_PROCOT_Situacao = cOM_PROCOT_Situacao;
	}

	public LocalDateTime getCOM_PROCOT_Created() {
		return COM_PROCOT_Created;
	}

	public void setCOM_PROCOT_Created(LocalDateTime cOM_PROCOT_Created) {
		COM_PROCOT_Created = cOM_PROCOT_Created;
	}

	public LocalDateTime getCOM_PROCOT_Updated() {
		return COM_PROCOT_Updated;
	}

	public void setCOM_PROCOT_Updated(LocalDateTime cOM_PROCOT_Updated) {
		COM_PROCOT_Updated = cOM_PROCOT_Updated;
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

	public String getSTG_EST_TABPRO_Cot_Codigo() {
		return STG_EST_TABPRO_Cot_Codigo;
	}

	public void setSTG_EST_TABPRO_Cot_Codigo(String sTG_EST_TABPRO_Cot_Codigo) {
		STG_EST_TABPRO_Cot_Codigo = sTG_EST_TABPRO_Cot_Codigo;
	}

	public Double getCOM_PROCOT_Valor() {
		return COM_PROCOT_Valor;
	}

	public void setCOM_PROCOT_Valor(Double cOM_PROCOT_Valor) {
		COM_PROCOT_Valor = cOM_PROCOT_Valor;
	}

	public String getSTG_FRT_TABCAR_Cot_Codigo() {
		return STG_FRT_TABCAR_Cot_Codigo;
	}

	public void setSTG_FRT_TABCAR_Cot_Codigo(String sTG_FRT_TABCAR_Cot_Codigo) {
		STG_FRT_TABCAR_Cot_Codigo = sTG_FRT_TABCAR_Cot_Codigo;
	}

	public String getSTG_GEN_TABCEN_Cot_Codigo() {
		return STG_GEN_TABCEN_Cot_Codigo;
	}

	public void setSTG_GEN_TABCEN_Cot_Codigo(String sTG_GEN_TABCEN_Cot_Codigo) {
		STG_GEN_TABCEN_Cot_Codigo = sTG_GEN_TABCEN_Cot_Codigo;
	}

	public LocalDate getCOM_PROCOT_Entrega() {
		return COM_PROCOT_Entrega;
	}

	public void setCOM_PROCOT_Entrega(LocalDate cOM_PROCOT_Entrega) {
		COM_PROCOT_Entrega = cOM_PROCOT_Entrega;
	}

	public String getCOM_PROCOT_PrefFabricanteDesc() {
		return COM_PROCOT_PrefFabricanteDesc;
	}

	public void setCOM_PROCOT_PrefFabricanteDesc(String cOM_PROCOT_PrefFabricanteDesc) {
		COM_PROCOT_PrefFabricanteDesc = cOM_PROCOT_PrefFabricanteDesc;
	}

	public Integer getCOM_PROCOT_PrefFabricante() {
		return COM_PROCOT_PrefFabricante;
	}

	public void setCOM_PROCOT_PrefFabricante(Integer cOM_PROCOT_PrefFabricante) {
		COM_PROCOT_PrefFabricante = cOM_PROCOT_PrefFabricante;
	}

	public String getCOM_PROCOT_PrefCondPgtoDesc() {
		return COM_PROCOT_PrefCondPgtoDesc;
	}

	public void setCOM_PROCOT_PrefCondPgtoDesc(String cOM_PROCOT_PrefCondPgtoDesc) {
		COM_PROCOT_PrefCondPgtoDesc = cOM_PROCOT_PrefCondPgtoDesc;
	}

	public Integer getCOM_PROCOT_PrefCondPgto() {
		return COM_PROCOT_PrefCondPgto;
	}

	public void setCOM_PROCOT_PrefCondPgto(Integer cOM_PROCOT_PrefCondPgto) {
		COM_PROCOT_PrefCondPgto = cOM_PROCOT_PrefCondPgto;
	}

	public String getCOM_PROCOT_SDCV_OBC() {
		return COM_PROCOT_SDCV_OBC;
	}

	public void setCOM_PROCOT_SDCV_OBC(String cOM_PROCOT_SDCV_OBC) {
		COM_PROCOT_SDCV_OBC = cOM_PROCOT_SDCV_OBC;
	}

	public String getCOM_PROCOT_TIPO_OBC() {
		return COM_PROCOT_TIPO_OBC;
	}

	public void setCOM_PROCOT_TIPO_OBC(String cOM_PROCOT_TIPO_OBC) {
		COM_PROCOT_TIPO_OBC = cOM_PROCOT_TIPO_OBC;
	}

	public List<CotacaoRatCCustoDbg> getRatCCusto() {
		return ratCCusto;
	}

}

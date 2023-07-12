package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SolicitacaoCompraItemDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Long COM_SOLICI_Numero;
	private Integer COM_PROSOL_Sequencia;
	private String EST_TABPRO_Codigo;
	private Double COM_PROSOL_Quantidade;
	private String COM_PROSOL_Observacao;
	private LocalDateTime COM_PROSOL_Created;
	private LocalDateTime COM_PROSOL_Updated;
	private String COM_PROSOL_Situacao;
	private Double COM_PROSOL_Valor;
	private String FRT_TABCAR_Codigo;
	private String GEN_TABCEN_Codigo;
	private Double COM_PROSOL_Cancelado;
	private Long COM_PROSOL_NumSolicitacao;
	private Integer COM_PROSOL_SeqSolicitacao;
	private String COM_PROSOL_Log;
	private String COM_PROSOL_Justificativa;
	private LocalDate COM_PROSOL_DataGarantia;
	private Long COM_PROSOL_OSOrigem;
	private String COM_PROSOL_SDCV_OBC;
	private String COM_PROSOL_TIPO_OBC;

	private ProdutoDbg produtoDbg;
	private SolicitacaoCompraDbg solicitacaoCompraDbg;

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

	public String getEST_TABPRO_Codigo() {
		return EST_TABPRO_Codigo;
	}

	public void setEST_TABPRO_Codigo(String eST_TABPRO_Codigo) {
		EST_TABPRO_Codigo = eST_TABPRO_Codigo;
	}

	public Double getCOM_PROSOL_Quantidade() {
		return COM_PROSOL_Quantidade;
	}

	public void setCOM_PROSOL_Quantidade(Double cOM_PROSOL_Quantidade) {
		COM_PROSOL_Quantidade = cOM_PROSOL_Quantidade;
	}

	public String getCOM_PROSOL_Observacao() {
		return COM_PROSOL_Observacao;
	}

	public void setCOM_PROSOL_Observacao(String cOM_PROSOL_Observacao) {
		COM_PROSOL_Observacao = cOM_PROSOL_Observacao;
	}

	public LocalDateTime getCOM_PROSOL_Created() {
		return COM_PROSOL_Created;
	}

	public void setCOM_PROSOL_Created(LocalDateTime cOM_PROSOL_Created) {
		COM_PROSOL_Created = cOM_PROSOL_Created;
	}

	public LocalDateTime getCOM_PROSOL_Updated() {
		return COM_PROSOL_Updated;
	}

	public void setCOM_PROSOL_Updated(LocalDateTime cOM_PROSOL_Updated) {
		COM_PROSOL_Updated = cOM_PROSOL_Updated;
	}

	public String getCOM_PROSOL_Situacao() {
		return COM_PROSOL_Situacao;
	}

	public void setCOM_PROSOL_Situacao(String cOM_PROSOL_Situacao) {
		COM_PROSOL_Situacao = cOM_PROSOL_Situacao;
	}

	public Double getCOM_PROSOL_Valor() {
		return COM_PROSOL_Valor;
	}

	public void setCOM_PROSOL_Valor(Double cOM_PROSOL_Valor) {
		COM_PROSOL_Valor = cOM_PROSOL_Valor;
	}

	public String getFRT_TABCAR_Codigo() {
		return FRT_TABCAR_Codigo;
	}

	public void setFRT_TABCAR_Codigo(String fRT_TABCAR_Codigo) {
		FRT_TABCAR_Codigo = fRT_TABCAR_Codigo;
	}

	public String getGEN_TABCEN_Codigo() {
		return GEN_TABCEN_Codigo;
	}

	public void setGEN_TABCEN_Codigo(String gEN_TABCEN_Codigo) {
		GEN_TABCEN_Codigo = gEN_TABCEN_Codigo;
	}

	public Double getCOM_PROSOL_Cancelado() {
		return COM_PROSOL_Cancelado;
	}

	public void setCOM_PROSOL_Cancelado(Double cOM_PROSOL_Cancelado) {
		COM_PROSOL_Cancelado = cOM_PROSOL_Cancelado;
	}

	public Long getCOM_PROSOL_NumSolicitacao() {
		return COM_PROSOL_NumSolicitacao;
	}

	public void setCOM_PROSOL_NumSolicitacao(Long cOM_PROSOL_NumSolicitacao) {
		COM_PROSOL_NumSolicitacao = cOM_PROSOL_NumSolicitacao;
	}

	public Integer getCOM_PROSOL_SeqSolicitacao() {
		return COM_PROSOL_SeqSolicitacao;
	}

	public void setCOM_PROSOL_SeqSolicitacao(Integer cOM_PROSOL_SeqSolicitacao) {
		COM_PROSOL_SeqSolicitacao = cOM_PROSOL_SeqSolicitacao;
	}

	public String getCOM_PROSOL_Log() {
		return COM_PROSOL_Log;
	}

	public void setCOM_PROSOL_Log(String cOM_PROSOL_Log) {
		COM_PROSOL_Log = cOM_PROSOL_Log;
	}

	public String getCOM_PROSOL_Justificativa() {
		return COM_PROSOL_Justificativa;
	}

	public void setCOM_PROSOL_Justificativa(String cOM_PROSOL_Justificativa) {
		COM_PROSOL_Justificativa = cOM_PROSOL_Justificativa;
	}

	public LocalDate getCOM_PROSOL_DataGarantia() {
		return COM_PROSOL_DataGarantia;
	}

	public void setCOM_PROSOL_DataGarantia(LocalDate cOM_PROSOL_DataGarantia) {
		COM_PROSOL_DataGarantia = cOM_PROSOL_DataGarantia;
	}

	public Long getCOM_PROSOL_OSOrigem() {
		return COM_PROSOL_OSOrigem;
	}

	public void setCOM_PROSOL_OSOrigem(Long cOM_PROSOL_OSOrigem) {
		COM_PROSOL_OSOrigem = cOM_PROSOL_OSOrigem;
	}

	public String getCOM_PROSOL_SDCV_OBC() {
		return COM_PROSOL_SDCV_OBC;
	}

	public void setCOM_PROSOL_SDCV_OBC(String cOM_PROSOL_SDCV_OBC) {
		COM_PROSOL_SDCV_OBC = cOM_PROSOL_SDCV_OBC;
	}

	public String getCOM_PROSOL_TIPO_OBC() {
		return COM_PROSOL_TIPO_OBC;
	}

	public void setCOM_PROSOL_TIPO_OBC(String cOM_PROSOL_TIPO_OBC) {
		COM_PROSOL_TIPO_OBC = cOM_PROSOL_TIPO_OBC;
	}

	public ProdutoDbg getProdutoDbg() {
		return produtoDbg;
	}

	public void setProdutoDbg(ProdutoDbg produtoDbg) {
		this.produtoDbg = produtoDbg;
	}

	public String getCentroCustoObc() {

		if (getFRT_TABCAR_Codigo() != null && !getFRT_TABCAR_Codigo().isEmpty()) {
			return "PLACA-".concat(getFRT_TABCAR_Codigo());
		} else if (getGEN_TABCEN_Codigo() != null && !getGEN_TABCEN_Codigo().isEmpty()) {
			return getSTG_GEN_TABEMP_Codigo().toString().concat(getGEN_TABCEN_Codigo());
		} else {
			return "";
		}

	}

	public SolicitacaoCompraDbg getSolicitacaoCompraDbg() {
		return solicitacaoCompraDbg;
	}

	public void setSolicitacaoCompraDbg(SolicitacaoCompraDbg solicitacaoCompraDbg) {
		this.solicitacaoCompraDbg = solicitacaoCompraDbg;
	}

}

package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CotacaoCompraDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Long COM_COTACA_Numero;
	private LocalDate COM_COTACA_Emissao;
	private Integer COM_TABCOM_Codigo;
	private String GEN_TABUSU_Login;
	private String COM_COTACA_Prioridade;
	private String COM_COTACA_Situacao;
	private String COM_COTACA_Observacao;
	private LocalDateTime COM_COTACA_Created;
	private LocalDateTime COM_COTACA_Updated;
	private Integer COM_COTACA_SeqProduto;
	private String COM_COTACA_Tipo;
	private Integer COM_COTACA_Critica;
	private LocalDate COM_COTACA_ValidadeFinal;
	private LocalDate COM_COTACA_ValidadeInicial;
	private Integer STG_COM_VLDCOT_Codigo;
	private Integer COM_COTACA_EmailsNEnviados;
	private Integer COM_COTACA_EmailsEnviados;
	private Integer COM_COTACA_EmailsTotal;
	private byte[] COM_COTACA_ArqCotacao;
	private String COM_COTACA_NomeArqCotacao;
	private Integer COM_COTACA_EncCotacao;
	private LocalDateTime COM_COTACA_DataHoraAprovacao;
	private String COM_COTACA_Aprovacao;
	private String STG_GEN_TABUSU_CotApr_Login;
	private Long COM_COTACA_IDOSTerceiro;
	private Integer COM_COTACA_OSGarantia;
	private LocalDateTime COM_COTACA_DHIntOBC;

	private List<CotacaoCompraItemDbg> itens;
	private List<CotacaoCompraFornDbg> fornecedores;

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

	public LocalDate getCOM_COTACA_Emissao() {
		return COM_COTACA_Emissao;
	}

	public void setCOM_COTACA_Emissao(LocalDate cOM_COTACA_Emissao) {
		COM_COTACA_Emissao = cOM_COTACA_Emissao;
	}

	public Integer getCOM_TABCOM_Codigo() {
		return COM_TABCOM_Codigo;
	}

	public void setCOM_TABCOM_Codigo(Integer cOM_TABCOM_Codigo) {
		COM_TABCOM_Codigo = cOM_TABCOM_Codigo;
	}

	public String getGEN_TABUSU_Login() {
		return GEN_TABUSU_Login;
	}

	public void setGEN_TABUSU_Login(String gEN_TABUSU_Login) {
		GEN_TABUSU_Login = gEN_TABUSU_Login;
	}

	public String getCOM_COTACA_Prioridade() {
		return COM_COTACA_Prioridade;
	}

	public void setCOM_COTACA_Prioridade(String cOM_COTACA_Prioridade) {
		COM_COTACA_Prioridade = cOM_COTACA_Prioridade;
	}

	public String getCOM_COTACA_Situacao() {
		return COM_COTACA_Situacao;
	}

	public void setCOM_COTACA_Situacao(String cOM_COTACA_Situacao) {
		COM_COTACA_Situacao = cOM_COTACA_Situacao;
	}

	public String getCOM_COTACA_Observacao() {
		return COM_COTACA_Observacao;
	}

	public void setCOM_COTACA_Observacao(String cOM_COTACA_Observacao) {
		COM_COTACA_Observacao = cOM_COTACA_Observacao;
	}

	public LocalDateTime getCOM_COTACA_Created() {
		return COM_COTACA_Created;
	}

	public void setCOM_COTACA_Created(LocalDateTime cOM_COTACA_Created) {
		COM_COTACA_Created = cOM_COTACA_Created;
	}

	public LocalDateTime getCOM_COTACA_Updated() {
		return COM_COTACA_Updated;
	}

	public void setCOM_COTACA_Updated(LocalDateTime cOM_COTACA_Updated) {
		COM_COTACA_Updated = cOM_COTACA_Updated;
	}

	public Integer getCOM_COTACA_SeqProduto() {
		return COM_COTACA_SeqProduto;
	}

	public void setCOM_COTACA_SeqProduto(Integer cOM_COTACA_SeqProduto) {
		COM_COTACA_SeqProduto = cOM_COTACA_SeqProduto;
	}

	public String getCOM_COTACA_Tipo() {
		return COM_COTACA_Tipo;
	}

	public void setCOM_COTACA_Tipo(String cOM_COTACA_Tipo) {
		COM_COTACA_Tipo = cOM_COTACA_Tipo;
	}

	public Integer getCOM_COTACA_Critica() {
		return COM_COTACA_Critica;
	}

	public void setCOM_COTACA_Critica(Integer cOM_COTACA_Critica) {
		COM_COTACA_Critica = cOM_COTACA_Critica;
	}

	public LocalDate getCOM_COTACA_ValidadeFinal() {
		return COM_COTACA_ValidadeFinal;
	}

	public void setCOM_COTACA_ValidadeFinal(LocalDate cOM_COTACA_ValidadeFinal) {
		COM_COTACA_ValidadeFinal = cOM_COTACA_ValidadeFinal;
	}

	public LocalDate getCOM_COTACA_ValidadeInicial() {
		return COM_COTACA_ValidadeInicial;
	}

	public void setCOM_COTACA_ValidadeInicial(LocalDate cOM_COTACA_ValidadeInicial) {
		COM_COTACA_ValidadeInicial = cOM_COTACA_ValidadeInicial;
	}

	public Integer getSTG_COM_VLDCOT_Codigo() {
		return STG_COM_VLDCOT_Codigo;
	}

	public void setSTG_COM_VLDCOT_Codigo(Integer sTG_COM_VLDCOT_Codigo) {
		STG_COM_VLDCOT_Codigo = sTG_COM_VLDCOT_Codigo;
	}

	public Integer getCOM_COTACA_EmailsNEnviados() {
		return COM_COTACA_EmailsNEnviados;
	}

	public void setCOM_COTACA_EmailsNEnviados(Integer cOM_COTACA_EmailsNEnviados) {
		COM_COTACA_EmailsNEnviados = cOM_COTACA_EmailsNEnviados;
	}

	public Integer getCOM_COTACA_EmailsEnviados() {
		return COM_COTACA_EmailsEnviados;
	}

	public void setCOM_COTACA_EmailsEnviados(Integer cOM_COTACA_EmailsEnviados) {
		COM_COTACA_EmailsEnviados = cOM_COTACA_EmailsEnviados;
	}

	public Integer getCOM_COTACA_EmailsTotal() {
		return COM_COTACA_EmailsTotal;
	}

	public void setCOM_COTACA_EmailsTotal(Integer cOM_COTACA_EmailsTotal) {
		COM_COTACA_EmailsTotal = cOM_COTACA_EmailsTotal;
	}

	public byte[] getCOM_COTACA_ArqCotacao() {
		return COM_COTACA_ArqCotacao;
	}

	public void setCOM_COTACA_ArqCotacao(byte[] cOM_COTACA_ArqCotacao) {
		COM_COTACA_ArqCotacao = cOM_COTACA_ArqCotacao;
	}

	public String getCOM_COTACA_NomeArqCotacao() {
		return COM_COTACA_NomeArqCotacao;
	}

	public void setCOM_COTACA_NomeArqCotacao(String cOM_COTACA_NomeArqCotacao) {
		COM_COTACA_NomeArqCotacao = cOM_COTACA_NomeArqCotacao;
	}

	public Integer getCOM_COTACA_EncCotacao() {
		return COM_COTACA_EncCotacao;
	}

	public void setCOM_COTACA_EncCotacao(Integer cOM_COTACA_EncCotacao) {
		COM_COTACA_EncCotacao = cOM_COTACA_EncCotacao;
	}

	public LocalDateTime getCOM_COTACA_DataHoraAprovacao() {
		return COM_COTACA_DataHoraAprovacao;
	}

	public void setCOM_COTACA_DataHoraAprovacao(LocalDateTime cOM_COTACA_DataHoraAprovacao) {
		COM_COTACA_DataHoraAprovacao = cOM_COTACA_DataHoraAprovacao;
	}

	public String getCOM_COTACA_Aprovacao() {
		return COM_COTACA_Aprovacao;
	}

	public void setCOM_COTACA_Aprovacao(String cOM_COTACA_Aprovacao) {
		COM_COTACA_Aprovacao = cOM_COTACA_Aprovacao;
	}

	public String getSTG_GEN_TABUSU_CotApr_Login() {
		return STG_GEN_TABUSU_CotApr_Login;
	}

	public void setSTG_GEN_TABUSU_CotApr_Login(String sTG_GEN_TABUSU_CotApr_Login) {
		STG_GEN_TABUSU_CotApr_Login = sTG_GEN_TABUSU_CotApr_Login;
	}

	public Long getCOM_COTACA_IDOSTerceiro() {
		return COM_COTACA_IDOSTerceiro;
	}

	public void setCOM_COTACA_IDOSTerceiro(Long cOM_COTACA_IDOSTerceiro) {
		COM_COTACA_IDOSTerceiro = cOM_COTACA_IDOSTerceiro;
	}

	public Integer getCOM_COTACA_OSGarantia() {
		return COM_COTACA_OSGarantia;
	}

	public void setCOM_COTACA_OSGarantia(Integer cOM_COTACA_OSGarantia) {
		COM_COTACA_OSGarantia = cOM_COTACA_OSGarantia;
	}

	public LocalDateTime getCOM_COTACA_DHIntOBC() {
		return COM_COTACA_DHIntOBC;
	}

	public void setCOM_COTACA_DHIntOBC(LocalDateTime cOM_COTACA_DHIntOBC) {
		COM_COTACA_DHIntOBC = cOM_COTACA_DHIntOBC;
	}

	public List<CotacaoCompraItemDbg> getItens() {
		return itens;
	}

	public void setItens(List<CotacaoCompraItemDbg> itens) {
		this.itens = itens;
	}

	public List<CotacaoCompraFornDbg> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<CotacaoCompraFornDbg> fornecedores) {
		this.fornecedores = fornecedores;
	}

}

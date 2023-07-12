package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SolicitacaoCompraDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Long COM_SOLICI_Numero;
	private LocalDate COM_SOLICI_Emissao;
	private String GEN_TABUSU_Login;
	private String COM_SOLICI_Prioridade;
	private String COM_SOLICI_Situacao;
	private String COM_SOLICI_Observacao;
	private LocalDateTime COM_SOLICI_Created;
	private LocalDateTime COM_SOLICI_Updated;
	private Integer COM_SOLICI_SeqProduto;
	private String COM_SOLICI_Tipo;
	private Integer COM_SOLICI_Critica;
	private String COM_SOLICI_Gerada;
	private LocalDateTime COM_SOLICI_DtHoraCancAtend;
	private String STG_GEN_TABUSU_CAS_Login;
	private String COM_SOLICI_JustCancAtend;
	private Long COM_SOLICI_IDOSTerceiro;
	private Integer COM_SOLICI_OSGarantia;
	private LocalDateTime COM_SOLICI_DHIntOBC;
	private String COM_SOLICI_NrSolOBC;
	private Integer COM_SOLICI_MotivoCancelamento;

	private List<SolicitacaoCompraItemDbg> itens;
	private FilialEmpresaDbg filialEmpresa;
	private SolicitacaoCompraFornDbg fornecedor;

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

	public LocalDate getCOM_SOLICI_Emissao() {
		return COM_SOLICI_Emissao;
	}

	public void setCOM_SOLICI_Emissao(LocalDate cOM_SOLICI_Emissao) {
		COM_SOLICI_Emissao = cOM_SOLICI_Emissao;
	}

	public String getGEN_TABUSU_Login() {
		return GEN_TABUSU_Login;
	}

	public void setGEN_TABUSU_Login(String gEN_TABUSU_Login) {
		GEN_TABUSU_Login = gEN_TABUSU_Login;
	}

	public String getCOM_SOLICI_Prioridade() {
		return COM_SOLICI_Prioridade;
	}

	public void setCOM_SOLICI_Prioridade(String cOM_SOLICI_Prioridade) {
		COM_SOLICI_Prioridade = cOM_SOLICI_Prioridade;
	}

	public String getCOM_SOLICI_Situacao() {
		return COM_SOLICI_Situacao;
	}

	public void setCOM_SOLICI_Situacao(String cOM_SOLICI_Situacao) {
		COM_SOLICI_Situacao = cOM_SOLICI_Situacao;
	}

	public String getCOM_SOLICI_Observacao() {
		return COM_SOLICI_Observacao;
	}

	public void setCOM_SOLICI_Observacao(String cOM_SOLICI_Observacao) {
		COM_SOLICI_Observacao = cOM_SOLICI_Observacao;
	}

	public LocalDateTime getCOM_SOLICI_Created() {
		return COM_SOLICI_Created;
	}

	public void setCOM_SOLICI_Created(LocalDateTime cOM_SOLICI_Created) {
		COM_SOLICI_Created = cOM_SOLICI_Created;
	}

	public LocalDateTime getCOM_SOLICI_Updated() {
		return COM_SOLICI_Updated;
	}

	public void setCOM_SOLICI_Updated(LocalDateTime cOM_SOLICI_Updated) {
		COM_SOLICI_Updated = cOM_SOLICI_Updated;
	}

	public Integer getCOM_SOLICI_SeqProduto() {
		return COM_SOLICI_SeqProduto;
	}

	public void setCOM_SOLICI_SeqProduto(Integer cOM_SOLICI_SeqProduto) {
		COM_SOLICI_SeqProduto = cOM_SOLICI_SeqProduto;
	}

	public String getCOM_SOLICI_Tipo() {
		return COM_SOLICI_Tipo;
	}

	public void setCOM_SOLICI_Tipo(String cOM_SOLICI_Tipo) {
		COM_SOLICI_Tipo = cOM_SOLICI_Tipo;
	}

	public Integer getCOM_SOLICI_Critica() {
		return COM_SOLICI_Critica;
	}

	public void setCOM_SOLICI_Critica(Integer cOM_SOLICI_Critica) {
		COM_SOLICI_Critica = cOM_SOLICI_Critica;
	}

	public String getCOM_SOLICI_Gerada() {
		return COM_SOLICI_Gerada;
	}

	public void setCOM_SOLICI_Gerada(String cOM_SOLICI_Gerada) {
		COM_SOLICI_Gerada = cOM_SOLICI_Gerada;
	}

	public LocalDateTime getCOM_SOLICI_DtHoraCancAtend() {
		return COM_SOLICI_DtHoraCancAtend;
	}

	public void setCOM_SOLICI_DtHoraCancAtend(LocalDateTime cOM_SOLICI_DtHoraCancAtend) {
		COM_SOLICI_DtHoraCancAtend = cOM_SOLICI_DtHoraCancAtend;
	}

	public String getSTG_GEN_TABUSU_CAS_Login() {
		return STG_GEN_TABUSU_CAS_Login;
	}

	public void setSTG_GEN_TABUSU_CAS_Login(String sTG_GEN_TABUSU_CAS_Login) {
		STG_GEN_TABUSU_CAS_Login = sTG_GEN_TABUSU_CAS_Login;
	}

	public String getCOM_SOLICI_JustCancAtend() {
		return COM_SOLICI_JustCancAtend;
	}

	public void setCOM_SOLICI_JustCancAtend(String cOM_SOLICI_JustCancAtend) {
		COM_SOLICI_JustCancAtend = cOM_SOLICI_JustCancAtend;
	}

	public Long getCOM_SOLICI_IDOSTerceiro() {
		return COM_SOLICI_IDOSTerceiro;
	}

	public void setCOM_SOLICI_IDOSTerceiro(Long cOM_SOLICI_IDOSTerceiro) {
		COM_SOLICI_IDOSTerceiro = cOM_SOLICI_IDOSTerceiro;
	}

	public Integer getCOM_SOLICI_OSGarantia() {
		return COM_SOLICI_OSGarantia;
	}

	public void setCOM_SOLICI_OSGarantia(Integer cOM_SOLICI_OSGarantia) {
		COM_SOLICI_OSGarantia = cOM_SOLICI_OSGarantia;
	}

	public LocalDateTime getCOM_SOLICI_DHIntOBC() {
		return COM_SOLICI_DHIntOBC;
	}

	public void setCOM_SOLICI_DHIntOBC(LocalDateTime cOM_SOLICI_DHIntOBC) {
		COM_SOLICI_DHIntOBC = cOM_SOLICI_DHIntOBC;
	}

	public String getCOM_SOLICI_NrSolOBC() {
		return COM_SOLICI_NrSolOBC;
	}

	public void setCOM_SOLICI_NrSolOBC(String cOM_SOLICI_NrSolOBC) {
		COM_SOLICI_NrSolOBC = cOM_SOLICI_NrSolOBC;
	}

	public Integer getCOM_SOLICI_MotivoCancelamento() {
		return COM_SOLICI_MotivoCancelamento;
	}

	public void setCOM_SOLICI_MotivoCancelamento(Integer cOM_SOLICI_MotivoCancelamento) {
		COM_SOLICI_MotivoCancelamento = cOM_SOLICI_MotivoCancelamento;
	}

	public List<SolicitacaoCompraItemDbg> getItens() {
		return itens;
	}

	public void setItens(List<SolicitacaoCompraItemDbg> itens) {
		this.itens = itens;
	}

	public FilialEmpresaDbg getFilialEmpresa() {
		return filialEmpresa;
	}

	public void setFilialEmpresa(FilialEmpresaDbg filialEmpresa) {
		this.filialEmpresa = filialEmpresa;
	}

	public SolicitacaoCompraFornDbg getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(SolicitacaoCompraFornDbg fornecedor) {
		this.fornecedor = fornecedor;
	}

}

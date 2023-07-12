package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoCompraDbg {

	private EnderecoClienteFornecedorDbg fornecedor;
	private FilialEmpresaDbg filial;

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Integer COM_PEDIDO_Numero;
	private LocalDate COM_PEDIDO_Emissao;
	private LocalDate COM_PEDIDO_Entrega;
	private Long STG_GEN_TABENT_For_Codigo;
	private Long STG_GEN_TABENT_Tra_Codigo;
	private LocalDateTime COM_PEDIDO_DHEnvioEMail;
	private LocalDateTime COM_PEDIDO_Created;
	private LocalDateTime COM_PEDIDO_Updated;
	private Long STG_GEN_ENDENT_For_Codigo;
	private Long STG_GEN_ENDENT_Tra_Codigo;
	private Integer GEN_TABCPG_Codigo;
	private Integer COM_TABCOM_Codigo;
	private String STG_GEN_TABUSU_Dig_Login;
	private String COM_PEDIDO_Observacao;
	private String COM_PEDIDO_Situacao;
	private String COM_PEDIDO_TipoFrete;
	private Integer GEN_TABORG_Codigo;
	private Integer COM_PEDIDO_SeqProduto;
	private String GEN_NATOPE_Codigo;
	private String COM_PEDIDO_Tipo;
	private Double COM_PEDIDO_ValorFrete;
	private String COM_PEDIDO_Justificativa;
	private Integer COM_PEDIDO_Emergencial;
	private Long COM_PEDIDO_IDOSTerceiro;
	private Integer COM_PEDIDO_DiasGarantia;
	private Integer COM_PEDIDO_MotivoPreco;
	private LocalDateTime COM_PEDIDO_DHIntOBC;
	private String COM_PEDIDO_Origem;

	private List<PedidoCompraItemDbg> itens = new ArrayList<>();

	public EnderecoClienteFornecedorDbg getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(EnderecoClienteFornecedorDbg fornecedor) {
		this.fornecedor = fornecedor;
	}

	public FilialEmpresaDbg getFilial() {
		return filial;
	}

	public void setFilial(FilialEmpresaDbg filial) {
		this.filial = filial;
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

	public Integer getCOM_PEDIDO_Numero() {
		return COM_PEDIDO_Numero;
	}

	public void setCOM_PEDIDO_Numero(Integer cOM_PEDIDO_Numero) {
		COM_PEDIDO_Numero = cOM_PEDIDO_Numero;
	}

	public LocalDate getCOM_PEDIDO_Emissao() {
		return COM_PEDIDO_Emissao;
	}

	public void setCOM_PEDIDO_Emissao(LocalDate cOM_PEDIDO_Emissao) {
		COM_PEDIDO_Emissao = cOM_PEDIDO_Emissao;
	}

	public LocalDate getCOM_PEDIDO_Entrega() {
		return COM_PEDIDO_Entrega;
	}

	public void setCOM_PEDIDO_Entrega(LocalDate cOM_PEDIDO_Entrega) {
		COM_PEDIDO_Entrega = cOM_PEDIDO_Entrega;
	}

	public Long getSTG_GEN_TABENT_For_Codigo() {
		return STG_GEN_TABENT_For_Codigo;
	}

	public void setSTG_GEN_TABENT_For_Codigo(Long sTG_GEN_TABENT_For_Codigo) {
		STG_GEN_TABENT_For_Codigo = sTG_GEN_TABENT_For_Codigo;
	}

	public Long getSTG_GEN_TABENT_Tra_Codigo() {
		return STG_GEN_TABENT_Tra_Codigo;
	}

	public void setSTG_GEN_TABENT_Tra_Codigo(Long sTG_GEN_TABENT_Tra_Codigo) {
		STG_GEN_TABENT_Tra_Codigo = sTG_GEN_TABENT_Tra_Codigo;
	}

	public LocalDateTime getCOM_PEDIDO_DHEnvioEMail() {
		return COM_PEDIDO_DHEnvioEMail;
	}

	public void setCOM_PEDIDO_DHEnvioEMail(LocalDateTime cOM_PEDIDO_DHEnvioEMail) {
		COM_PEDIDO_DHEnvioEMail = cOM_PEDIDO_DHEnvioEMail;
	}

	public LocalDateTime getCOM_PEDIDO_Created() {
		return COM_PEDIDO_Created;
	}

	public void setCOM_PEDIDO_Created(LocalDateTime cOM_PEDIDO_Created) {
		COM_PEDIDO_Created = cOM_PEDIDO_Created;
	}

	public LocalDateTime getCOM_PEDIDO_Updated() {
		return COM_PEDIDO_Updated;
	}

	public void setCOM_PEDIDO_Updated(LocalDateTime cOM_PEDIDO_Updated) {
		COM_PEDIDO_Updated = cOM_PEDIDO_Updated;
	}

	public Long getSTG_GEN_ENDENT_For_Codigo() {
		return STG_GEN_ENDENT_For_Codigo;
	}

	public void setSTG_GEN_ENDENT_For_Codigo(Long sTG_GEN_ENDENT_For_Codigo) {
		STG_GEN_ENDENT_For_Codigo = sTG_GEN_ENDENT_For_Codigo;
	}

	public Long getSTG_GEN_ENDENT_Tra_Codigo() {
		return STG_GEN_ENDENT_Tra_Codigo;
	}

	public void setSTG_GEN_ENDENT_Tra_Codigo(Long sTG_GEN_ENDENT_Tra_Codigo) {
		STG_GEN_ENDENT_Tra_Codigo = sTG_GEN_ENDENT_Tra_Codigo;
	}

	public Integer getGEN_TABCPG_Codigo() {
		return GEN_TABCPG_Codigo;
	}

	public void setGEN_TABCPG_Codigo(Integer gEN_TABCPG_Codigo) {
		GEN_TABCPG_Codigo = gEN_TABCPG_Codigo;
	}

	public Integer getCOM_TABCOM_Codigo() {
		return COM_TABCOM_Codigo;
	}

	public void setCOM_TABCOM_Codigo(Integer cOM_TABCOM_Codigo) {
		COM_TABCOM_Codigo = cOM_TABCOM_Codigo;
	}

	public String getSTG_GEN_TABUSU_Dig_Login() {
		return STG_GEN_TABUSU_Dig_Login;
	}

	public void setSTG_GEN_TABUSU_Dig_Login(String sTG_GEN_TABUSU_Dig_Login) {
		STG_GEN_TABUSU_Dig_Login = sTG_GEN_TABUSU_Dig_Login;
	}

	public String getCOM_PEDIDO_Observacao() {
		return COM_PEDIDO_Observacao;
	}

	public void setCOM_PEDIDO_Observacao(String cOM_PEDIDO_Observacao) {
		COM_PEDIDO_Observacao = cOM_PEDIDO_Observacao;
	}

	public String getCOM_PEDIDO_Situacao() {
		return COM_PEDIDO_Situacao;
	}

	public void setCOM_PEDIDO_Situacao(String cOM_PEDIDO_Situacao) {
		COM_PEDIDO_Situacao = cOM_PEDIDO_Situacao;
	}

	public String getCOM_PEDIDO_TipoFrete() {
		return COM_PEDIDO_TipoFrete;
	}

	public void setCOM_PEDIDO_TipoFrete(String cOM_PEDIDO_TipoFrete) {
		COM_PEDIDO_TipoFrete = cOM_PEDIDO_TipoFrete;
	}

	public Integer getGEN_TABORG_Codigo() {
		return GEN_TABORG_Codigo;
	}

	public void setGEN_TABORG_Codigo(Integer gEN_TABORG_Codigo) {
		GEN_TABORG_Codigo = gEN_TABORG_Codigo;
	}

	public Integer getCOM_PEDIDO_SeqProduto() {
		return COM_PEDIDO_SeqProduto;
	}

	public void setCOM_PEDIDO_SeqProduto(Integer cOM_PEDIDO_SeqProduto) {
		COM_PEDIDO_SeqProduto = cOM_PEDIDO_SeqProduto;
	}

	public String getGEN_NATOPE_Codigo() {
		return GEN_NATOPE_Codigo;
	}

	public void setGEN_NATOPE_Codigo(String gEN_NATOPE_Codigo) {
		GEN_NATOPE_Codigo = gEN_NATOPE_Codigo;
	}

	public String getCOM_PEDIDO_Tipo() {
		return COM_PEDIDO_Tipo;
	}

	public void setCOM_PEDIDO_Tipo(String cOM_PEDIDO_Tipo) {
		COM_PEDIDO_Tipo = cOM_PEDIDO_Tipo;
	}

	public Double getCOM_PEDIDO_ValorFrete() {
		return COM_PEDIDO_ValorFrete;
	}

	public void setCOM_PEDIDO_ValorFrete(Double cOM_PEDIDO_ValorFrete) {
		COM_PEDIDO_ValorFrete = cOM_PEDIDO_ValorFrete;
	}

	public String getCOM_PEDIDO_Justificativa() {
		return COM_PEDIDO_Justificativa;
	}

	public void setCOM_PEDIDO_Justificativa(String cOM_PEDIDO_Justificativa) {
		COM_PEDIDO_Justificativa = cOM_PEDIDO_Justificativa;
	}

	public Integer getCOM_PEDIDO_Emergencial() {
		return COM_PEDIDO_Emergencial;
	}

	public void setCOM_PEDIDO_Emergencial(Integer cOM_PEDIDO_Emergencial) {
		COM_PEDIDO_Emergencial = cOM_PEDIDO_Emergencial;
	}

	public Long getCOM_PEDIDO_IDOSTerceiro() {
		return COM_PEDIDO_IDOSTerceiro;
	}

	public void setCOM_PEDIDO_IDOSTerceiro(Long cOM_PEDIDO_IDOSTerceiro) {
		COM_PEDIDO_IDOSTerceiro = cOM_PEDIDO_IDOSTerceiro;
	}

	public Integer getCOM_PEDIDO_DiasGarantia() {
		return COM_PEDIDO_DiasGarantia;
	}

	public void setCOM_PEDIDO_DiasGarantia(Integer cOM_PEDIDO_DiasGarantia) {
		COM_PEDIDO_DiasGarantia = cOM_PEDIDO_DiasGarantia;
	}

	public Integer getCOM_PEDIDO_MotivoPreco() {
		return COM_PEDIDO_MotivoPreco;
	}

	public void setCOM_PEDIDO_MotivoPreco(Integer cOM_PEDIDO_MotivoPreco) {
		COM_PEDIDO_MotivoPreco = cOM_PEDIDO_MotivoPreco;
	}

	public LocalDateTime getCOM_PEDIDO_DHIntOBC() {
		return COM_PEDIDO_DHIntOBC;
	}

	public void setCOM_PEDIDO_DHIntOBC(LocalDateTime cOM_PEDIDO_DHIntOBC) {
		COM_PEDIDO_DHIntOBC = cOM_PEDIDO_DHIntOBC;
	}

	public String getCOM_PEDIDO_Origem() {
		return COM_PEDIDO_Origem;
	}

	public void setCOM_PEDIDO_Origem(String cOM_PEDIDO_Origem) {
		COM_PEDIDO_Origem = cOM_PEDIDO_Origem;
	}

	public List<PedidoCompraItemDbg> getItens() {
		return itens;
	}

}

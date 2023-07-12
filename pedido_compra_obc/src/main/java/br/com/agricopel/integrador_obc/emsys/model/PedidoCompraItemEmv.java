package br.com.agricopel.integrador_obc.emsys.model;

import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;

public class PedidoCompraItemEmv { 

	private Integer seq_pedido;
	private Integer seq_item; 
	private Integer cod_item; 
	private Double qtd_item; 
	private Integer cod_unidade; 
	private Double qtd_item_convertido; 
	private Double fator_conversao; 
	private Double val_unitario; 
	private Double val_total; 
	private Integer cod_almoxarifado;
	private LocalDate dta_previsao_entrega; 
	private Double qtd_atendido; 
	private Double qtd_cancelado;
	private Double val_desconto;
	private Double val_ipi;
	private Double val_desconto_rateado;
	private Integer sequencia;
	private Integer cod_empresa;
	private String des_observacao;
	private Integer cod_natureza_operacao;
	
	public Integer   getseq_pedido() { 
		return seq_pedido;
	}

	public Integer   getseq_item() {
		return seq_item ; 
	}
		
	public Integer   getcod_item() {
		return cod_item; 
	}
		
	public Double    getqtd_item() {
		return qtd_item; 
	}
		
	public Integer   getcod_unidade() {
		return cod_unidade; 
	}
		
	public Double    getqtd_item_convertido() {
		return qtd_item_convertido; 
	}
	
	public Double    getfator_conversao() {
		return fator_conversao; 
	}
		
	public Double    getval_unitario() {
		return val_unitario;
	}
		
	public Double    getval_total() {
		return val_total;
	}
		
	public Integer   getcod_almoxarifado() {
		return cod_almoxarifado;
	}
		
	public LocalDate getdta_previsao_entrega() {
		return dta_previsao_entrega; 
	}
		
	public Double    getqtd_atendido() {
		return qtd_atendido;
	}
		
	public Double    getqtd_cancelado() {
		return qtd_cancelado;
	}
		
	public Double    getval_desconto() {
		return val_desconto ;
	}
		
	public Double    getval_ipi() {
		return val_ipi;
	}
		
	public Double    getval_desconto_rateado() {
		return val_desconto_rateado;
	}
	
	public void  setseq_pedido(Integer Seq_pedido) { 
		seq_pedido = Seq_pedido;
	}

	public  void  setseq_item(Integer Seq_item) {
		seq_item = Seq_item; 
	}
	
	public  void  setcod_natureza_operacao(Integer Cod_natureza_operacao) {
		cod_natureza_operacao = Cod_natureza_operacao; 
	}
		
	public  void  setcod_item(Integer Cod_item) {
		cod_item = Cod_item; 
	}
		
	public  void   setqtd_item(Double Qtd_item) {
		qtd_item = Qtd_item; 
	}
		
	public  void  setcod_unidade(Integer Cod_unidade ) {
		cod_unidade = Cod_unidade; 
	}
		
	public  void   setqtd_item_convertido(Double Qtd_item_convertido) {
		qtd_item_convertido = Qtd_item_convertido; 
	}
	
	public  void   setfator_conversao(Double Fator_conversao) {
		fator_conversao = Fator_conversao; 
	}
	
		
	public  void   setval_unitario(Double Val_unitario) {
		val_unitario = Val_unitario;
	}
		
	public  void  setval_total(Double Val_total) {
		val_total = Val_total;
	}
		
	public  void  setcod_almoxarifado(Integer Cod_almoxarifado) {
		cod_almoxarifado = Cod_almoxarifado;
	}
		
	public  void  setdta_previsao_entrega(LocalDate Dta_previsao_entrega) {
		dta_previsao_entrega = Dta_previsao_entrega; 
	}
		
	public  void   setqtd_atendido(Double Qtd_atendido) {
		qtd_atendido = Qtd_atendido;
	}
		
	public  void   setqtd_cancelado(Double Qtd_cancelado) {
		qtd_cancelado = Qtd_cancelado;
	}
		
	public  void  setval_desconto(Double Val_desconto) {
		val_desconto = Val_desconto ;
	}
		
	public  void   setval_ipi(Double Val_ipi) {
		val_ipi = Val_ipi;
	}
		
	public  void   setval_desconto_rateado(Double Val_desconto_rateado) {
		val_desconto_rateado = Val_desconto_rateado;
	}
	
	public Integer getsequencia() {
		return sequencia;
	}

	public void setsequencia(Integer Sequencia) {
		sequencia = Sequencia;
	}
	
	public Integer getcod_empresa() { 
		return cod_empresa;
	}
	
	public void   setcod_empresa(Integer Cod_empresa) { 
		 cod_empresa = Cod_empresa;
	}
	
	public String getdes_observacao() { 
		return des_observacao;
	}
	
	public Integer getcod_natureza_operacao() { 
		return cod_natureza_operacao;
	}
	
	
	
	
	public void   setdes_observacao(String Des_observacao) { 
		des_observacao = Des_observacao;
	}
	
	/*private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Integer COM_PEDIDO_Numero;
	private Integer COM_PROPED_Sequencia;
	private String STG_GEN_NATOPE_Codigo;
	private String STG_EST_TABPRO_Ped_Codigo;
	private Integer STG_EST_TABDEP_Ped_Codigo;
	private Double COM_PROPED_Quantidade;
	private Double COM_PROPED_Valor;
	private Double COM_PROPED_Entregue;
	private Double COM_PROPED_Cancelado;
	private Double COM_PROPED_PercIPI;
	private String COM_PROPED_Observacao;
	private String COM_PROPED_Situacao;
	private LocalDateTime COM_PROPED_Created;
	private LocalDateTime COM_PROPED_Updated;
	private Long COM_COTACA_Numero;
	private Integer COM_PROCOT_Sequencia;
	private String STG_FRT_TABCAR_Ped_Codigo;
	private String STG_GEN_TABCEN_Ped_Codigo;
	private Integer COM_PROPED_DiasGarantia;
	private Integer COM_PROPED_DiasMinGarantia;
	private Double COM_PROPED_ValorMaxGarantia;
	private String COM_PROPED_SDCV_OBC;
	private String COM_PROPED_TipoSDCV;
   
	private List<PedidoRatCCustoDbg> ratCCusto;
	//private SolicitacaoCompraItemDbg solicitacaoCompraItemDbg;
	private CotacaoCompraItemDbg cotacaoCompraItemDbg;
	
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

	public PedidoCompraItemEmv() {
		this.ratCCusto = new ArrayList<>();
	}

	public Integer getCOM_PEDIDO_Numero() {
		return COM_PEDIDO_Numero;
	}

	public void setCOM_PEDIDO_Numero(Integer cOM_PEDIDO_Numero) {
		COM_PEDIDO_Numero = cOM_PEDIDO_Numero;
	}

	public Integer getCOM_PROPED_Sequencia() {
		return COM_PROPED_Sequencia;
	}

	public void setCOM_PROPED_Sequencia(Integer cOM_PROPED_Sequencia) {
		COM_PROPED_Sequencia = cOM_PROPED_Sequencia;
	}

	public String getSTG_GEN_NATOPE_Codigo() {
		return STG_GEN_NATOPE_Codigo;
	}

	public void setSTG_GEN_NATOPE_Codigo(String sTG_GEN_NATOPE_Codigo) {
		STG_GEN_NATOPE_Codigo = sTG_GEN_NATOPE_Codigo;
	}

	public String getSTG_EST_TABPRO_Ped_Codigo() {
		return STG_EST_TABPRO_Ped_Codigo;
	}

	public void setSTG_EST_TABPRO_Ped_Codigo(String sTG_EST_TABPRO_Ped_Codigo) {
		STG_EST_TABPRO_Ped_Codigo = sTG_EST_TABPRO_Ped_Codigo;
	}

	public Integer getSTG_EST_TABDEP_Ped_Codigo() {
		return STG_EST_TABDEP_Ped_Codigo;
	}

	public void setSTG_EST_TABDEP_Ped_Codigo(Integer sTG_EST_TABDEP_Ped_Codigo) {
		STG_EST_TABDEP_Ped_Codigo = sTG_EST_TABDEP_Ped_Codigo;
	}

	public Double getCOM_PROPED_Quantidade() {
		return COM_PROPED_Quantidade;
	}

	public void setCOM_PROPED_Quantidade(Double cOM_PROPED_Quantidade) {
		COM_PROPED_Quantidade = cOM_PROPED_Quantidade;
	}

	public Double getCOM_PROPED_Valor() {
		return COM_PROPED_Valor;
	}

	public void setCOM_PROPED_Valor(Double cOM_PROPED_Valor) {
		COM_PROPED_Valor = cOM_PROPED_Valor;
	}

	public Double getCOM_PROPED_Entregue() {
		return COM_PROPED_Entregue;
	}

	public void setCOM_PROPED_Entregue(Double cOM_PROPED_Entregue) {
		COM_PROPED_Entregue = cOM_PROPED_Entregue;
	}

	public Double getCOM_PROPED_Cancelado() {
		return COM_PROPED_Cancelado;
	}

	public void setCOM_PROPED_Cancelado(Double cOM_PROPED_Cancelado) {
		COM_PROPED_Cancelado = cOM_PROPED_Cancelado;
	}

	public Double getCOM_PROPED_PercIPI() {
		return COM_PROPED_PercIPI;
	}

	public void setCOM_PROPED_PercIPI(Double cOM_PROPED_PercIPI) {
		COM_PROPED_PercIPI = cOM_PROPED_PercIPI;
	}

	public String getCOM_PROPED_Observacao() {
		return COM_PROPED_Observacao;
	}

	public void setCOM_PROPED_Observacao(String cOM_PROPED_Observacao) {
		COM_PROPED_Observacao = cOM_PROPED_Observacao;
	}

	public String getCOM_PROPED_Situacao() {
		return COM_PROPED_Situacao;
	}

	public void setCOM_PROPED_Situacao(String cOM_PROPED_Situacao) {
		COM_PROPED_Situacao = cOM_PROPED_Situacao;
	}

	public LocalDateTime getCOM_PROPED_Created() {
		return COM_PROPED_Created;
	}

	public void setCOM_PROPED_Created(LocalDateTime cOM_PROPED_Created) {
		COM_PROPED_Created = cOM_PROPED_Created;
	}

	public LocalDateTime getCOM_PROPED_Updated() {
		return COM_PROPED_Updated;
	}

	public void setCOM_PROPED_Updated(LocalDateTime cOM_PROPED_Updated) {
		COM_PROPED_Updated = cOM_PROPED_Updated;
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

	public String getSTG_FRT_TABCAR_Ped_Codigo() {
		return STG_FRT_TABCAR_Ped_Codigo;
	}

	public void setSTG_FRT_TABCAR_Ped_Codigo(String sTG_FRT_TABCAR_Ped_Codigo) {
		STG_FRT_TABCAR_Ped_Codigo = sTG_FRT_TABCAR_Ped_Codigo;
	}

	public String getSTG_GEN_TABCEN_Ped_Codigo() {
		return STG_GEN_TABCEN_Ped_Codigo;
	}

	public void setSTG_GEN_TABCEN_Ped_Codigo(String sTG_GEN_TABCEN_Ped_Codigo) {
		STG_GEN_TABCEN_Ped_Codigo = sTG_GEN_TABCEN_Ped_Codigo;
	}

	public Integer getCOM_PROPED_DiasGarantia() {
		return COM_PROPED_DiasGarantia;
	}

	public void setCOM_PROPED_DiasGarantia(Integer cOM_PROPED_DiasGarantia) {
		COM_PROPED_DiasGarantia = cOM_PROPED_DiasGarantia;
	}

	public Integer getCOM_PROPED_DiasMinGarantia() {
		return COM_PROPED_DiasMinGarantia;
	}

	public void setCOM_PROPED_DiasMinGarantia(Integer cOM_PROPED_DiasMinGarantia) {
		COM_PROPED_DiasMinGarantia = cOM_PROPED_DiasMinGarantia;
	}

	public Double getCOM_PROPED_ValorMaxGarantia() {
		return COM_PROPED_ValorMaxGarantia;
	}

	public void setCOM_PROPED_ValorMaxGarantia(Double cOM_PROPED_ValorMaxGarantia) {
		COM_PROPED_ValorMaxGarantia = cOM_PROPED_ValorMaxGarantia;
	}

	public String getCOM_PROPED_SDCV_OBC() {
		return COM_PROPED_SDCV_OBC;
	}

	public void setCOM_PROPED_SDCV_OBC(String cOM_PROPED_SDCV_OBC) {
		COM_PROPED_SDCV_OBC = cOM_PROPED_SDCV_OBC;
	}

	public String getCOM_PROPED_TipoSDCV() {
		return COM_PROPED_TipoSDCV;
	}

	public void setCOM_PROPED_TipoSDCV(String cOM_PROPED_TipoSDCV) {
		COM_PROPED_TipoSDCV = cOM_PROPED_TipoSDCV;
	}

	public List<PedidoRatCCustoDbg> getRatCCusto() {
		return ratCCusto;
	}

	//public SolicitacaoCompraItemDbg getSolicitacaoCompraItemDbg() {
	//	return solicitacaoCompraItemDbg;
//	}

	//public void setSolicitacaoCompraItemDbg(SolicitacaoCompraItemDbg solicitacaoCompraItemDbg) {
	//	this.solicitacaoCompraItemDbg = solicitacaoCompraItemDbg;
//	}

	public CotacaoCompraItemDbg getCotacaoCompraItemDbg() {
		return cotacaoCompraItemDbg;
	}

	public void setCotacaoCompraItemDbg(CotacaoCompraItemDbg cotacaoCompraItemDbg) {
		this.cotacaoCompraItemDbg = cotacaoCompraItemDbg;
	}
	*/
}

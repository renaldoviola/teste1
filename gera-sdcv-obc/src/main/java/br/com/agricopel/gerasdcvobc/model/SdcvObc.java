package br.com.agricopel.gerasdcvobc.model;

import java.time.LocalDate;

public class SdcvObc {

	private String codLinhaRequisicao;
	private String codFilial;
	private String codProduto;
	private String descAdicional;
	private String unMedida;
	private String loginComprador;
	private String loginRequisitante;
	private String centroCustoINATIVO;
	private String codMotivoCompra;
	private LocalDate dataGeracao;
	private String nomeAnexoErp;
	private Double quantidade;
	private LocalDate dataPrevisaoCompra;
	private String codContaContabil;
	private String descricaoInterna;
	private String permiteSimilares;
	private String tipoSdcv; // C= Compra / R = Registro
	private String codAlmoxarifado;
	private String codCapaRequisicao;
	private String agrupamentoSdcv;
	private String localEntrega;
	private Integer prioridade; // Normal = 0 / urgente = 1
	private String cnpjFornecedor;
	private String marcaProduto;
	private String tipoFrete; // CIF = C // FOB = F
	private Integer diasPrazoEntrega;
	private String codCondPagto;
	private String observacao;
	private Double valorUnitario;
	private Double pIpi;
	private Double pIcmsIva;
	private Double pIcmsSt;
	private LocalDate dtValidadeProposta;
	private String codSubTipoSdcv;
	private String codProjeto;
	private Integer codMoeda;
	private String numeroNota;
	private String serieNota;
	private String codCst;
	private String codOrigem;
	private String codIva;
	private String cnpjFornecedorSugerido;
	private String sistemaOrigem;
	private Double valorTarget;
	private String motivoCompra;
	private String faseProjeto;

	private Boolean sucesso = Boolean.FALSE;
	private String descProduto;
	private Boolean solicitar;
	private String retornoProc;

	private SdcvRateioObc rateio;

	public SdcvObc() {
		this.rateio = new SdcvRateioObc();
	}

	public String getCodLinhaRequisicao() {
		return codLinhaRequisicao;
	}

	public void setCodLinhaRequisicao(String codLinhaRequisicao) {
		this.codLinhaRequisicao = codLinhaRequisicao;
	}

	public String getCodFilial() {
		return codFilial;
	}

	public void setCodFilial(String codFilial) {
		this.codFilial = codFilial;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getDescAdicional() {
		return descAdicional;
	}

	public void setDescAdicional(String descAdicional) {
		this.descAdicional = descAdicional;
	}

	public String getUnMedida() {
		return unMedida;
	}

	public void setUnMedida(String unMedida) {
		this.unMedida = unMedida;
	}

	public String getLoginComprador() {
		return loginComprador;
	}

	public void setLoginComprador(String loginComprador) {
		this.loginComprador = loginComprador;
	}

	public String getLoginRequisitante() {
		return loginRequisitante;
	}

	public void setLoginRequisitante(String loginRequisitante) {
		this.loginRequisitante = loginRequisitante;
	}

	public String getCentroCustoINATIVO() {
		return centroCustoINATIVO;
	}

	public void setCentroCustoINATIVO(String centroCustoINATIVO) {
		this.centroCustoINATIVO = centroCustoINATIVO;
	}

	public String getCodMotivoCompra() {
		return codMotivoCompra;
	}

	public void setCodMotivoCompra(String codMotivoCompra) {
		this.codMotivoCompra = codMotivoCompra;
	}

	public LocalDate getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDate dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getNomeAnexoErp() {
		return nomeAnexoErp;
	}

	public void setNomeAnexoErp(String nomeAnexoErp) {
		this.nomeAnexoErp = nomeAnexoErp;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataPrevisaoCompra() {
		return dataPrevisaoCompra;
	}

	public void setDataPrevisaoCompra(LocalDate dataPrevisaoCompra) {
		this.dataPrevisaoCompra = dataPrevisaoCompra;
	}

	public String getCodContaContabil() {
		return codContaContabil;
	}

	public void setCodContaContabil(String codContaContabil) {
		this.codContaContabil = codContaContabil;
	}

	public String getDescricaoInterna() {
		return descricaoInterna;
	}

	public void setDescricaoInterna(String descricaoInterna) {
		this.descricaoInterna = descricaoInterna;
	}

	public String getPermiteSimilares() {
		return permiteSimilares;
	}

	public void setPermiteSimilares(String permiteSimilares) {
		this.permiteSimilares = permiteSimilares;
	}

	public String getTipoSdcv() {
		return tipoSdcv;
	}

	public void setTipoSdcv(String tipoSdcv) {
		this.tipoSdcv = tipoSdcv;
	}

	public String getCodAlmoxarifado() {
		return codAlmoxarifado;
	}

	public void setCodAlmoxarifado(String codAlmoxarifado) {
		this.codAlmoxarifado = codAlmoxarifado;
	}

	public String getCodCapaRequisicao() {
		return codCapaRequisicao;
	}

	public void setCodCapaRequisicao(String codCapaRequisicao) {
		this.codCapaRequisicao = codCapaRequisicao;
	}

	public String getAgrupamentoSdcv() {
		return agrupamentoSdcv;
	}

	public void setAgrupamentoSdcv(String agrupamentoSdcv) {
		this.agrupamentoSdcv = agrupamentoSdcv;
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getCnpjFornecedor() {
		return cnpjFornecedor;
	}

	public void setCnpjFornecedor(String cnpjFornecedor) {
		this.cnpjFornecedor = cnpjFornecedor;
	}

	public String getMarcaProduto() {
		return marcaProduto;
	}

	public void setMarcaProduto(String marcaProduto) {
		this.marcaProduto = marcaProduto;
	}

	public String getTipoFrete() {
		return tipoFrete;
	}

	public void setTipoFrete(String tipoFrete) {
		this.tipoFrete = tipoFrete;
	}

	public Integer getDiasPrazoEntrega() {
		return diasPrazoEntrega;
	}

	public void setDiasPrazoEntrega(Integer diasPrazoEntrega) {
		this.diasPrazoEntrega = diasPrazoEntrega;
	}

	public String getCodCondPagto() {
		return codCondPagto;
	}

	public void setCodCondPagto(String codCondPagto) {
		this.codCondPagto = codCondPagto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getpIpi() {
		return pIpi;
	}

	public void setpIpi(Double pIpi) {
		this.pIpi = pIpi;
	}

	public Double getpIcmsIva() {
		return pIcmsIva;
	}

	public void setpIcmsIva(Double pIcmsIva) {
		this.pIcmsIva = pIcmsIva;
	}

	public Double getpIcmsSt() {
		return pIcmsSt;
	}

	public void setpIcmsSt(Double pIcmsSt) {
		this.pIcmsSt = pIcmsSt;
	}

	public LocalDate getDtValidadeProposta() {
		return dtValidadeProposta;
	}

	public void setDtValidadeProposta(LocalDate dtValidadeProposta) {
		this.dtValidadeProposta = dtValidadeProposta;
	}

	public String getCodSubTipoSdcv() {
		return codSubTipoSdcv;
	}

	public void setCodSubTipoSdcv(String codSubTipoSdcv) {
		this.codSubTipoSdcv = codSubTipoSdcv;
	}

	public String getCodProjeto() {
		return codProjeto;
	}

	public void setCodProjeto(String codProjeto) {
		this.codProjeto = codProjeto;
	}

	public Integer getCodMoeda() {
		return codMoeda;
	}

	public void setCodMoeda(Integer codMoeda) {
		this.codMoeda = codMoeda;
	}

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getSerieNota() {
		return serieNota;
	}

	public void setSerieNota(String serieNota) {
		this.serieNota = serieNota;
	}

	public String getCodCst() {
		return codCst;
	}

	public void setCodCst(String codCst) {
		this.codCst = codCst;
	}

	public String getCodOrigem() {
		return codOrigem;
	}

	public void setCodOrigem(String codOrigem) {
		this.codOrigem = codOrigem;
	}

	public String getCodIva() {
		return codIva;
	}

	public void setCodIva(String codIva) {
		this.codIva = codIva;
	}

	public String getCnpjFornecedorSugerido() {
		return cnpjFornecedorSugerido;
	}

	public void setCnpjFornecedorSugerido(String cnpjFornecedorSugerido) {
		this.cnpjFornecedorSugerido = cnpjFornecedorSugerido;
	}

	public String getSistemaOrigem() {
		return sistemaOrigem;
	}

	public void setSistemaOrigem(String sistemaOrigem) {
		this.sistemaOrigem = sistemaOrigem;
	}

	public Double getValorTarget() {
		return valorTarget;
	}

	public void setValorTarget(Double valorTarget) {
		this.valorTarget = valorTarget;
	}

	public String getMotivoCompra() {
		return motivoCompra;
	}

	public void setMotivoCompra(String motivoCompra) {
		this.motivoCompra = motivoCompra;
	}

	public String getFaseProjeto() {
		return faseProjeto;
	}

	public void setFaseProjeto(String faseProjeto) {
		this.faseProjeto = faseProjeto;
	}

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}

	public Boolean getSolicitar() {
		return solicitar;
	}

	public void setSolicitar(Boolean solicitar) {
		this.solicitar = solicitar;
	}

	public String getRetornoProc() {
		return retornoProc;
	}

	public void addRetornoProc(String retornoProc) {
		if (this.retornoProc != null && !this.retornoProc.isEmpty()) {
			this.retornoProc = this.retornoProc.concat(" | ").concat(retornoProc);
		} else {
			this.retornoProc = retornoProc;
		}
	}

	public SdcvRateioObc getRateio() {
		return rateio;
	}
}

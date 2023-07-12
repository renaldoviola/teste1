package br.com.agricopel.integrador_obc.protheus.model;

import java.util.ArrayList;
import java.util.List;

public class PedidoCompraPrt {

	private String codPedidoObc;
	private String empresa;
	private String filial;
	private String cnpjFornecedor;
	private String codTransportadora;
	private String comprador;
	private String tipoFrete;
	private String observacao;
	private String condPagto;
	private String dataCompra;
	private String recno;
	private Boolean temFornecedor;

	private FornecedorPrt fornecedorPrt;

	private List<PedidoCompraItemPrt> itens;

	public PedidoCompraPrt() {

		this.itens = new ArrayList<>();
	}

	public String getCodPedidoObc() {
		return codPedidoObc;
	}

	public void setCodPedidoObc(String codPedidoObc) {
		this.codPedidoObc = codPedidoObc;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public String getCnpjFornecedor() {
		return cnpjFornecedor;
	}

	public void setCnpjFornecedor(String cnpjFornecedor) {
		this.cnpjFornecedor = cnpjFornecedor;
	}

	public String getCodTransportadora() {
		return codTransportadora;
	}

	public void setCodTransportadora(String codTransportadora) {
		this.codTransportadora = codTransportadora;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public String getTipoFrete() {
		return tipoFrete;
	}

	public void setTipoFrete(String tipoFrete) {
		this.tipoFrete = tipoFrete;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCondPagto() {
		return condPagto;
	}

	public void setCondPagto(String condPagto) {
		this.condPagto = condPagto;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getRecno() {
		return recno;
	}

	public void setRecno(String recno) {
		this.recno = recno;
	}

	public List<PedidoCompraItemPrt> getItens() {
		return itens;
	}

	public FornecedorPrt getFornecedorPrt() {
		return fornecedorPrt;
	}

	public void setFornecedorPrt(FornecedorPrt fornecedorPrt) {
		this.fornecedorPrt = fornecedorPrt;
	}

	public Boolean getTemFornecedor() {
		return temFornecedor;
	}

	public void setTemFornecedor(Boolean temFornecedor) {
		this.temFornecedor = temFornecedor;
	}

}

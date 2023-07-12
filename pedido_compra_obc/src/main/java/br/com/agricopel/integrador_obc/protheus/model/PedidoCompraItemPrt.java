package br.com.agricopel.integrador_obc.protheus.model;

import java.util.ArrayList;
import java.util.List;

public class PedidoCompraItemPrt {

	private String codPedidoObc;
	private String codPedidoErp;
	private String codProduto;
	private Double quantidade;
	private String dtEntrega;
	private Double valorUnit;
	private Double pIcms;
	private Double pIpi;
	private String codSdcv;
	private String solicitante;
	private String centroCusto;
	private String contaContabil;
	private String desSdcv;
	private String unMedida;
	private String tipoSdcv;
	private String armazem;
	private Double descTotalItem;
	private List<PedidoRatCCustoPrt> ratCCusto;

	public PedidoCompraItemPrt() {
		this.ratCCusto = new ArrayList<>();
	}

	public String getCodPedidoObc() {
		return codPedidoObc;
	}

	public void setCodPedidoObc(String codPedidoObc) {
		this.codPedidoObc = codPedidoObc;
	}

	public String getCodPedidoErp() {
		return codPedidoErp;
	}

	public void setCodPedidoErp(String codPedidoErp) {
		this.codPedidoErp = codPedidoErp;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public String getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(String dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public Double getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(Double valorUnit) {
		this.valorUnit = valorUnit;
	}

	public Double getpIcms() {
		return pIcms;
	}

	public void setpIcms(Double pIcms) {
		this.pIcms = pIcms;
	}

	public Double getpIpi() {
		return pIpi;
	}

	public void setpIpi(Double pIpi) {
		this.pIpi = pIpi;
	}

	public String getCodSdcv() {
		return codSdcv;
	}

	public void setCodSdcv(String codSdcv) {
		this.codSdcv = codSdcv;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public String getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}

	public String getDesSdcv() {
		return desSdcv;
	}

	public void setDesSdcv(String desSdcv) {
		this.desSdcv = desSdcv;
	}

	public String getUnMedida() {
		return unMedida;
	}

	public void setUnMedida(String unMedida) {
		this.unMedida = unMedida;
	}

	public String getTipoSdcv() {
		return tipoSdcv;
	}

	public void setTipoSdcv(String tipoSdcv) {
		this.tipoSdcv = tipoSdcv;
	}
	
	
	public Double getDescTotalItem() {
		return descTotalItem;
	}

	public void setDescTotalItem(Double descTotalItem) {
		this.descTotalItem = descTotalItem;
	}

	public String getArmazem() {
		return armazem;
	}

	public void setArmazem(String armazem) {
		this.armazem = armazem;
	}

	
	public List<PedidoRatCCustoPrt> getRatCCusto() {
		return ratCCusto;
	}

	public void setRatCCusto(List<PedidoRatCCustoPrt> ratCCusto) {
		this.ratCCusto = ratCCusto;
	}

}

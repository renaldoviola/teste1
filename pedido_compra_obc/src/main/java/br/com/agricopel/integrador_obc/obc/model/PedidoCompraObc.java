package br.com.agricopel.integrador_obc.obc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecPedido;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecPedidoItem;
import br.com.agricopel.integrador_obc.tiposEnum.SoftwareEnum;

public class PedidoCompraObc {

	private String codPedidoObc;
	private String filialCob;
	private String filialEnt;
	private String cnpjFornecedor;
	private String codTransportadora;
	private String comprador;
	private String tipoFrete;
	private String observacao;
	private String condPagto;
	private LocalDate dataCompra;
	private SoftwareEnum software;
	private Integer recNo;

	private FornecedorObc fornecedorObc;

	private List<PedidoCompraItemObc> itens;

	public PedidoCompraObc() {
		this.itens = new ArrayList<>();
	}

	public PedidoCompraObc(ObcRecPedido obcRecPedido) throws Exception {

		this();
		String[] dados = obcRecPedido.getDados().split("#SEP#");

		this.setCodPedidoObc(dados[0]);
		this.setFilialCob(dados[1]);
		this.setFilialEnt(dados[2]);
		this.setCnpjFornecedor(dados[3]);
		this.setCodTransportadora(dados[4]);
		this.setComprador(dados[5]);
		this.setTipoFrete(dados[6]);
		this.setObservacao(dados[7]);

		String[] condPagto = dados[8].split("-");
		this.setCondPagto(condPagto[condPagto.length - 1]);

		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.setDataCompra(LocalDate.parse(dados[9], pattern));

		for (ObcRecPedidoItem obcRecPedidoItem : obcRecPedido.getPedidoItens()) {
			this.getItens().add(new PedidoCompraItemObc(obcRecPedidoItem));
		}

		if (this.getFilialCob().toUpperCase().startsWith("PRT-")
				|| this.getFilialCob().toUpperCase().startsWith("DBG-")) {
			String[] codProduto = this.getFilialCob().split("-");
			this.setSoftware(SoftwareEnum.valueOf(codProduto[0]));
		} else {
			String[] codProduto = this.getItens().get(0).getCodProduto().split("-");
			this.setSoftware(SoftwareEnum.valueOf(codProduto[0]));
		}

		this.setRecNo(obcRecPedido.getNrecno());
	}

	public String getCodPedidoObc() {
		return codPedidoObc;
	}

	public void setCodPedidoObc(String codPedidoObc) {
		this.codPedidoObc = codPedidoObc;
	}

	public String getFilialCob() {
		return filialCob;
	}

	public void setFilialCob(String filialCob) {
		this.filialCob = filialCob;
	}

	public String getFilialEnt() {
		return filialEnt;
	}

	public void setFilialEnt(String filialEnt) {
		this.filialEnt = filialEnt;
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

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public SoftwareEnum getSoftware() {
		return software;
	}

	public void setSoftware(SoftwareEnum software) {
		this.software = software;
	}

	public List<PedidoCompraItemObc> getItens() {
		return itens;
	}

	public Integer getRecNo() {
		return recNo;
	}

	public void setRecNo(Integer recNo) {
		this.recNo = recNo;
	}

	public FornecedorObc getFornecedorObc() {
		return fornecedorObc;
	}

	public void setFornecedorObc(FornecedorObc fornecedorObc) {
		this.fornecedorObc = fornecedorObc;
	}

}

package br.com.agricopel.integrador_obc.obc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecPedidoItem;

public class PedidoCompraItemObc {

	private String codPedidoObc;
	private String codPedidoErp;
	private String codProduto;
	private Double quantidade;
	private LocalDate dtEntrega;
	private Double valorUnit;
	private Double pIcms;
	private Double pIpi;
	private String codSdcv;
	private String solicitante;
	private String centroCusto;
	private String contaContabil;
	private String desSdcv;
	private String rateio;
	private String unMedida;
	private String marca;
	private String grupo;
	private String motivoCompra;
	private Integer tempoGarantia;
	private String tipoSdcv;
	private Double descTotalItem;
	private String armazem;


	private Integer nRecNo;
	private List<PedidoRatCCustoObc> ratCCusto;
	private List<PedidoCompraItemAnexoObc> anexos;

	public PedidoCompraItemObc() {
		this.ratCCusto = new ArrayList<>();
	}

	public PedidoCompraItemObc(ObcRecPedidoItem obcRecPedidoItem) throws Exception {

		this();

		String[] dados = obcRecPedidoItem.getDados().split("#SEP#");

		this.setCodPedidoObc(dados[0]);
		this.setCodPedidoErp(dados[1]);
		this.setCodProduto(dados[2]);
		this.setQuantidade(new Double(dados[3]));
		this.setDtEntrega(LocalDate.parse(dados[4], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		this.setValorUnit(new Double(dados[5]));
		this.setpIcms(new Double(dados[6]));
		this.setpIpi(new Double(dados[7]));
		this.setCodSdcv(dados[8]);
		this.setSolicitante(dados[9]);
		this.setCentroCusto(dados[10]);
		this.setContaContabil(dados[11]);
		this.setDesSdcv(dados[12]);
		this.setRateio(dados[13]);
		this.setUnMedida(dados[14]);
		this.setMarca(dados[15]);
		this.setGrupo(dados[16]);
		this.setMotivoCompra(dados[17]);
		this.setTipoSdcv(dados[18]);

		if (dados.length > 19 && dados[19] != null && !dados[19].isEmpty()) {
			this.setTempoGarantia(Integer.parseInt(dados[19]));
		} else {
			this.setTempoGarantia(0);
		}

		if (dados.length > 21 && dados[21] != null && !dados[21].isEmpty()) {
			this.setDescTotalItem(Double.parseDouble(dados[21]));
		} else {
			this.setDescTotalItem(0d);
		}

		//Local de Entrega
		if (dados.length > 22) {
			this.setArmazem(dados[22]);
		} else {
			this.setArmazem("");
		}
		
		this.setnRecNo(obcRecPedidoItem.getNrecno());

		calcRateio();
	}

	private void calcRateio() {
		if (this.getRateio() != null && !this.getRateio().isEmpty()) {
			String[] rateios = this.getRateio().split("#RAT#");

			for (String rateio : rateios) {
				this.getRatCCusto().add(new PedidoRatCCustoObc(rateio));
			}
		}
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

	public LocalDate getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(LocalDate dtEntrega) {
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

	public String getRateio() {
		return rateio;
	}

	public void setRateio(String rateio) {
		this.rateio = rateio;
	}

	public String getUnMedida() {
		return unMedida;
	}

	public void setUnMedida(String unMedida) {
		this.unMedida = unMedida;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Integer getnRecNo() {
		return nRecNo;
	}

	public void setnRecNo(Integer nRecNo) {
		this.nRecNo = nRecNo;
	}

	public List<PedidoRatCCustoObc> getRatCCusto() {
		return ratCCusto;
	}

	public void setRatCCusto(List<PedidoRatCCustoObc> ratCCusto) {
		this.ratCCusto = ratCCusto;
	}

	public String getMotivoCompra() {
		return motivoCompra;
	}

	public void setMotivoCompra(String motivoCompra) {
		this.motivoCompra = motivoCompra;
	}

	public Integer getTempoGarantia() {
		return tempoGarantia;
	}

	public void setTempoGarantia(Integer tempoGarantia) {
		this.tempoGarantia = tempoGarantia;
	}

	public String getTipoSdcv() {
		return tipoSdcv;
	}

	public void setTipoSdcv(String tipoSdcv) {
		this.tipoSdcv = tipoSdcv;
	}
	
	public String getArmazem() {
		return armazem;
	}

	public void setArmazem(String armazem) {
		this.armazem = armazem;
	}

	public Double getDescTotalItem() {
		return descTotalItem;
	}

	public void setDescTotalItem(Double descTotalItem) {
		this.descTotalItem = descTotalItem;
	}

	public List<PedidoCompraItemAnexoObc> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<PedidoCompraItemAnexoObc> anexos) {
		this.anexos = anexos;
	}

}
package br.com.agricopel.integrador_obc.protheus.bo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.comp.exception.FornecedorNaoEncontradoException;
import br.com.agricopel.comp.exception.ProtheusException;
import br.com.agricopel.comp.exception.ProtheusServerException;
import br.com.agricopel.comp.utils.DateUtil;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraItemObc;
import br.com.agricopel.integrador_obc.obc.model.PedidoCompraObc;
import br.com.agricopel.integrador_obc.obc.model.PedidoRatCCustoObc;
import br.com.agricopel.integrador_obc.obc.model.RetGravarPedidoObc;
import br.com.agricopel.integrador_obc.protheus.dao.PedidoCompraPrtDAO;
import br.com.agricopel.integrador_obc.protheus.model.EmpresaPrt;
import br.com.agricopel.integrador_obc.protheus.model.PedidoCompraItemPrt;
import br.com.agricopel.integrador_obc.protheus.model.PedidoCompraPrt;
import br.com.agricopel.integrador_obc.protheus.model.PedidoRatCCustoPrt;
import br.com.agricopel.integrador_obc.protheus.model.RetGravarPedidoPrt;

public class PedidoCompraPrtBO {

	public RetGravarPedidoObc gravar(PedidoCompraObc pedidoCompraObc) throws Exception {

		PedidoCompraPrt pedidoCompraPrt = this.getPedido(pedidoCompraObc);

		RetGravarPedidoPrt retGravarPedidoPrt = new PedidoCompraPrtDAO().gravarPedido(pedidoCompraPrt);

		if (retGravarPedidoPrt.getSucesso()) {

			if (retGravarPedidoPrt.getErrorMessage() != null && !retGravarPedidoPrt.getErrorMessage().isEmpty()) {
				LogUtils.escreverLogInfo("Servidor Protheus retornou sucesso com a seguinte mensagem: "
						.concat(retGravarPedidoPrt.getErrorMessage()));
			}
		} else {
			if (retGravarPedidoPrt.getErrorCode().equals("CNPJ_NAO_ENCONTRADO")) {
				throw new FornecedorNaoEncontradoException(pedidoCompraObc.getCnpjFornecedor());
			} else {
				throw new ProtheusServerException(retGravarPedidoPrt.getErrorMessage());
			}
		}

		return getRetorno(retGravarPedidoPrt);
	}

	private EmpresaPrt getEmpresa(String codigoEmpresa) throws Exception {

		String cnpj = "";

		if (codigoEmpresa.startsWith("PRT-")) {
			cnpj = codigoEmpresa.split("-")[1];
		} else {
			cnpj = codigoEmpresa;
		}

		EmpresaPrt empresaPrt = new EmpresaPrtBO().getEmpresa(cnpj);

		if (empresaPrt == null || empresaPrt.getCodEmpresa().isEmpty() || empresaPrt.getCodFilial().isEmpty()) {
			throw new ProtheusException(
					"Nao foi possivel identificar a empresa/filial do protheus para inserir o pedido de compra. CNPJ - ["
							.concat(codigoEmpresa).concat("]"));
		}

		return empresaPrt;
	}

	private RetGravarPedidoObc getRetorno(RetGravarPedidoPrt retGravarPedidoPrt) {
		RetGravarPedidoObc retGravarPedidoObc = new RetGravarPedidoObc();

		retGravarPedidoObc.setSucesso(retGravarPedidoPrt.getSucesso());
		retGravarPedidoObc.setCodPedido(retGravarPedidoPrt.getCodPedido());
		retGravarPedidoObc.setMensagem(retGravarPedidoPrt.getErrorMessage());

		return retGravarPedidoObc;
	}

	private PedidoCompraPrt getPedido(PedidoCompraObc pedidoCompraObc) throws Exception {

		PedidoCompraPrt pedidoCompraPrt = new PedidoCompraPrt();

		EmpresaPrt empresaPrt = this.getEmpresa(pedidoCompraObc.getFilialCob());

		pedidoCompraPrt.setEmpresa(empresaPrt.getCodEmpresa());
		pedidoCompraPrt.setFilial(empresaPrt.getCodFilial());

		pedidoCompraPrt.setCnpjFornecedor(pedidoCompraObc.getCnpjFornecedor());
		pedidoCompraPrt.setCodPedidoObc(pedidoCompraObc.getCodPedidoObc());
		pedidoCompraPrt.setCodTransportadora(pedidoCompraObc.getCodTransportadora());
		pedidoCompraPrt.setComprador(pedidoCompraObc.getComprador());
		pedidoCompraPrt.setCondPagto(getCodCondPagto(pedidoCompraObc));
		pedidoCompraPrt.setObservacao(pedidoCompraObc.getObservacao());

		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMdd");
		pedidoCompraPrt.setDataCompra(pedidoCompraObc.getDataCompra().format(pattern));

		pedidoCompraPrt.setTipoFrete(pedidoCompraObc.getTipoFrete());
		pedidoCompraPrt.setRecno(pedidoCompraObc.getRecNo().toString());

		if (pedidoCompraObc.getFornecedorObc() != null) {
			pedidoCompraPrt.setTemFornecedor(Boolean.TRUE);
			pedidoCompraPrt.setFornecedorPrt(new FornecedorPrtBO().getFornecedor(pedidoCompraObc.getFornecedorObc()));
		} else {
			pedidoCompraPrt.setTemFornecedor(Boolean.FALSE);
		}

		pedidoCompraPrt.getItens().addAll(this.getItens(pedidoCompraObc, pedidoCompraPrt));

		return pedidoCompraPrt;

	}

	private List<PedidoCompraItemPrt> getItens(PedidoCompraObc pedidoCompraObc, PedidoCompraPrt pedidoCompraPrt)
			throws Exception {

		List<PedidoCompraItemPrt> pedidoCompraItemsObc = new ArrayList<>();

		for (PedidoCompraItemObc pedidoItemObc : pedidoCompraObc.getItens()) {

			PedidoCompraItemPrt pedidoItemPrt = new PedidoCompraItemPrt();

			if (!pedidoItemObc.getCodProduto().startsWith("PRT-")) {
				throw new ProtheusException(
						"Pedido apontado para filial Protheus que contém produtos sem 'PRT' no início do código! ["
								.concat(pedidoItemObc.getCodProduto().concat("]")));
			}

			pedidoItemPrt.setCodPedidoObc(pedidoItemObc.getCodPedidoObc());
			pedidoItemPrt.setCodPedidoErp(pedidoItemObc.getCodPedidoErp());

			String produto[] = pedidoItemObc.getCodProduto().split("-");
			pedidoItemPrt.setCodProduto(produto[produto.length - 1]);

			pedidoItemPrt.setQuantidade(pedidoItemObc.getQuantidade());
			pedidoItemPrt.setDtEntrega(new DateUtil().toProtheusData(pedidoItemObc.getDtEntrega()));
			pedidoItemPrt.setValorUnit(pedidoItemObc.getValorUnit());
			pedidoItemPrt.setpIcms(pedidoItemObc.getpIcms());
			pedidoItemPrt.setpIpi(pedidoItemObc.getpIpi());
			pedidoItemPrt.setCodSdcv(pedidoItemObc.getCodSdcv());
			pedidoItemPrt.setSolicitante(pedidoItemObc.getSolicitante());

			pedidoItemPrt.setCentroCusto(
					this.getCentroCustoPrt(pedidoItemObc.getCentroCusto(), pedidoItemObc.getCodProduto()));

			String[] contaContabil = pedidoItemObc.getContaContabil().split("-");
			pedidoItemPrt.setContaContabil(contaContabil[contaContabil.length - 1]);

			pedidoItemPrt.setDesSdcv(pedidoItemObc.getDesSdcv());
			pedidoItemPrt.setUnMedida(pedidoItemObc.getUnMedida());
			pedidoItemPrt.setTipoSdcv(pedidoItemObc.getTipoSdcv());
			pedidoItemPrt.setDescTotalItem(pedidoItemObc.getDescTotalItem());
			pedidoItemPrt.setArmazem(pedidoItemObc.getArmazem());
			
			pedidoItemPrt.getRatCCusto().addAll(this.getRateios(pedidoItemObc));

			pedidoCompraItemsObc.add(pedidoItemPrt);
		}

		return pedidoCompraItemsObc;
	}

	private List<PedidoRatCCustoPrt> getRateios(PedidoCompraItemObc pedidoItemObc) throws Exception {

		List<PedidoRatCCustoPrt> pedidoRatCCustoObcs = new ArrayList<>();

		for (PedidoRatCCustoObc pedidoRatCCustoObc : pedidoItemObc.getRatCCusto()) {

			PedidoRatCCustoPrt pedidoRatCCustoPrt = new PedidoRatCCustoPrt();

			pedidoRatCCustoPrt.setCentroCusto(
					this.getCentroCustoPrt(pedidoRatCCustoObc.getCentroCusto(), pedidoItemObc.getCodProduto()));

			String[] contaContabil = pedidoRatCCustoObc.getContaContabil().split("-");
			pedidoRatCCustoPrt.setContaContabil(contaContabil[contaContabil.length - 1]);

			pedidoRatCCustoPrt.setpRateio(pedidoRatCCustoObc.getpRateio());

			pedidoRatCCustoObcs.add(pedidoRatCCustoPrt);
		}

		return pedidoRatCCustoObcs;
	}

	private String getCodCondPagto(PedidoCompraObc pedidoCompraObc) throws Exception {

		if (pedidoCompraObc.getCondPagto().equalsIgnoreCase("29")) {
			pedidoCompraObc.setCondPagto("OBC66");
			LogUtils.escreverLogInfo("Transformando CondPagto para: ".concat(pedidoCompraObc.getCondPagto()));
		}

		if (pedidoCompraObc.getCondPagto().equalsIgnoreCase("OLD28")) {
			pedidoCompraObc.setCondPagto("28");
			LogUtils.escreverLogInfo("Transformando CondPagto para: ".concat(pedidoCompraObc.getCondPagto()));
		}

		return pedidoCompraObc.getCondPagto();
	}

	private String getCentroCustoPrt(String centroCustoObc, String produtoObc) throws Exception {

		if (centroCustoObc.equals("COMPRA_REVENDA") || centroCustoObc.equals("COMPRA_ESTOQUE")) {
			return centroCustoObc;
		}

		if (!centroCustoObc.startsWith("PRT")) {
			throw new ProtheusException(
					"Pedido contendo produto do Protheus e com Centro de Custo que não é do Protheus (não começa com 'PRT')!"
							.concat(" Produto: [").concat(produtoObc).concat("]").concat(" Centro de custo: [")
							.concat(centroCustoObc).concat("]"));
		}

		String[] centroCustoArr = centroCustoObc.split("-");
		String centroCustoPrt = centroCustoArr[centroCustoArr.length - 1];

		return centroCustoPrt;
	}
}

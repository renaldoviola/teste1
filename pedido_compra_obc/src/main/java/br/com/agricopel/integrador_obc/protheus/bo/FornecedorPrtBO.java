package br.com.agricopel.integrador_obc.protheus.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.integrador_obc.obc.model.FornecedorContatoObc;
import br.com.agricopel.integrador_obc.obc.model.FornecedorObc;
import br.com.agricopel.integrador_obc.protheus.model.FornecedorContatoPrt;
import br.com.agricopel.integrador_obc.protheus.model.FornecedorPrt;

public class FornecedorPrtBO {

	public FornecedorPrt getFornecedor(FornecedorObc fornecedorObc) {

		FornecedorPrt fornecedorPrt = new FornecedorPrt();

		fornecedorPrt.setCodigoErp(fornecedorObc.getCodigoErp());
		fornecedorPrt.setRazaoSocial(fornecedorObc.getRazaoSocial());
		fornecedorPrt.setEndereco(fornecedorObc.getEndereco());
		fornecedorPrt.setNumEndereco(fornecedorObc.getNumEndereco());
		fornecedorPrt.setBairro(fornecedorObc.getBairro());
		fornecedorPrt.setCep(fornecedorObc.getCep());
		fornecedorPrt.setCidade(fornecedorObc.getCidade());
		fornecedorPrt.setEstado(fornecedorObc.getEstado());
		fornecedorPrt.setCpfCnpj(fornecedorObc.getCpfCnpj());
		fornecedorPrt.setInscEstadual(fornecedorObc.getInscEstadual());
		fornecedorPrt.setInscMunicipal(fornecedorObc.getInscMunicipal());
		fornecedorPrt.setHomePage(fornecedorObc.getHomePage());
		fornecedorPrt.setDtCadastro(fornecedorObc.getDtCadastro());
		fornecedorPrt.setNomeFantasia(fornecedorObc.getNomeFantasia());
		fornecedorPrt.setPais(fornecedorObc.getPais());
		fornecedorPrt.setStatus(fornecedorObc.getStatus());
		fornecedorPrt.setTransportadora(fornecedorObc.getTransportadora());
		fornecedorPrt.setIdioma(fornecedorObc.getIdioma());
		fornecedorPrt.setObservacao(fornecedorObc.getObservacao());
		fornecedorPrt.setCondPagto(fornecedorObc.getCondPagto());
		fornecedorPrt.setMoeda(fornecedorObc.getMoeda());
		fornecedorPrt.setOptanteSimples(fornecedorObc.getOptanteSimples());
		fornecedorPrt.setCodigoCidadeIBGE(fornecedorObc.getCodigoCidadeIBGE());
		fornecedorPrt.setBanco(fornecedorObc.getBanco());
		fornecedorPrt.setAgencia(fornecedorObc.getAgencia());
		fornecedorPrt.setDigitoAgencia(fornecedorObc.getDigitoAgencia());
		fornecedorPrt.setConta(fornecedorObc.getConta());
		fornecedorPrt.setDigitoConta(fornecedorObc.getDigitoConta());

		fornecedorPrt.getFornecedorContatos().addAll(getContatos(fornecedorObc));

		return fornecedorPrt;
	}

	private List<FornecedorContatoPrt> getContatos(FornecedorObc fornecedorObc) {

		List<FornecedorContatoPrt> contatosPrt = new ArrayList<>();

		for (FornecedorContatoObc contatoObc : fornecedorObc.getFornecedorContatos()) {

			FornecedorContatoPrt contatoPrt = new FornecedorContatoPrt();
			String[] codigo = contatoObc.getCodigo().split("-");

			contatoPrt.setCodigo(codigo[codigo.length - 1]);
			contatoPrt.setCnpj(contatoObc.getCnpj());
			contatoPrt.setNome(contatoObc.getNome());
			contatoPrt.setTelefone(contatoObc.getTelefone());
			contatoPrt.setEmail(contatoObc.getEmail());
			contatoPrt.setPadrao(contatoObc.getPadrao());
			contatoPrt.setStatus(contatoObc.getPadrao());

			contatosPrt.add(contatoPrt);
		}

		return contatosPrt;
	}
}
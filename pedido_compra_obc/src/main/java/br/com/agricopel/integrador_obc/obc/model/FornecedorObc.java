package br.com.agricopel.integrador_obc.obc.model;

import java.util.ArrayList;
import java.util.List;

import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecFornecedor;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcSubRecFornecedorContato;

public class FornecedorObc {

	private String codigoErp;
	private String razaoSocial;
	private String endereco;
	private String numEndereco;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String cpfCnpj;
	private String inscEstadual;
	private String inscMunicipal;
	private String homePage;
	private String dtCadastro;
	private String nomeFantasia;
	private String pais;
	private String status;
	private String transportadora;
	private String idioma;
	private String observacao;
	private String condPagto;
	private String moeda;
	private String optanteSimples;
	private String codigoCidadeIBGE;
	private String contatos;
	private String banco;
	private String agencia;
	private String digitoAgencia;
	private String conta;
	private String digitoConta;

	private List<FornecedorContatoObc> fornecedorContatos;

	public FornecedorObc() {

		this.fornecedorContatos = new ArrayList<>();
	}

	public FornecedorObc(ObcRecFornecedor obcRecFornecedor) {

		this();

		String[] dados = obcRecFornecedor.getDados().split("#SEP#", -1);
		int campo = 0;

		this.setCodigoErp(dados[campo++]);
		this.setRazaoSocial(dados[campo++]);
		this.setEndereco(dados[campo++]);
		this.setNumEndereco(dados[campo++]);
		this.setBairro(dados[campo++]);
		this.setCep(dados[campo++]);
		this.setCidade(dados[campo++]);
		this.setEstado(dados[campo++]);
		this.setCpfCnpj(dados[campo++]);
		this.setInscEstadual(dados[campo++]);
		this.setInscMunicipal(dados[campo++]);
		this.setHomePage(dados[campo++]);
		this.setDtCadastro(dados[campo++]);
		this.setNomeFantasia(dados[campo++]);
		this.setPais(dados[campo++]);
		this.setStatus(dados[campo++]);
		this.setTransportadora(dados[campo++]);
		this.setIdioma(dados[campo++]);
		this.setObservacao(dados[campo++]);
		this.setCondPagto(dados[campo++]);
		this.setMoeda(dados[campo++]);
		this.setOptanteSimples(dados[campo++]);
		this.setCodigoCidadeIBGE(dados[campo++]);
		this.setContatos(dados[campo++]);
		this.setBanco(dados[campo++]);
		this.setAgencia(dados[campo++]);
		this.setDigitoAgencia(dados[campo++]);
		this.setConta(dados[campo++]);
		this.setDigitoConta(dados[campo++]);

		for (ObcSubRecFornecedorContato obcRecFornecedorContato : obcRecFornecedor.getRetFornecedorContatos()) {
			this.getFornecedorContatos().add(new FornecedorContatoObc(obcRecFornecedorContato.getDados()));
		}

	}

	public String getCodigoErp() {
		return codigoErp;
	}

	public void setCodigoErp(String codigoErp) {
		this.codigoErp = codigoErp;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumEndereco() {
		return numEndereco;
	}

	public void setNumEndereco(String numEndereco) {
		this.numEndereco = numEndereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getInscEstadual() {
		return inscEstadual;
	}

	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}

	public String getInscMunicipal() {
		return inscMunicipal;
	}

	public void setInscMunicipal(String inscMunicipal) {
		this.inscMunicipal = inscMunicipal;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
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

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public String getOptanteSimples() {
		return optanteSimples;
	}

	public void setOptanteSimples(String optanteSimples) {
		this.optanteSimples = optanteSimples;
	}

	public String getCodigoCidadeIBGE() {
		return codigoCidadeIBGE;
	}

	public void setCodigoCidadeIBGE(String codigoCidadeIBGE) {
		this.codigoCidadeIBGE = codigoCidadeIBGE;
	}

	public String getContatos() {
		return contatos;
	}

	public void setContatos(String contatos) {
		this.contatos = contatos;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getDigitoAgencia() {
		return digitoAgencia;
	}

	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(String digitoConta) {
		this.digitoConta = digitoConta;
	}

	public List<FornecedorContatoObc> getFornecedorContatos() {
		return fornecedorContatos;
	}

}

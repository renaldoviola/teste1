package br.com.agricopel.integrador_obc.obc.model;

public class FornecedorContatoObc {

	private String codigo;
	private String cnpj;
	private String nome;
	private String telefone;
	private String email;
	private String padrao;
	private String status;

	public FornecedorContatoObc(String contato) {

		String[] dados = contato.split("#SEP#");

		int campo = 0;

		this.setCodigo(dados[campo++]);
		this.setCnpj(dados[campo++]);
		this.setNome(dados[campo++]);
		this.setTelefone(dados[campo++]);
		this.setEmail(dados[campo++]);
		this.setPadrao(dados[campo++]);
		this.setStatus(dados[campo++]);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

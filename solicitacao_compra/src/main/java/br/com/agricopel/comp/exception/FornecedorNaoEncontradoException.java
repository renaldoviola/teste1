package br.com.agricopel.comp.exception;

public class FornecedorNaoEncontradoException extends Exception {

	private static final long serialVersionUID = -6284914661236849226L;

	public FornecedorNaoEncontradoException(String cnpj) {
		super("Fornecedor não encontrado! CNPJ: ".concat(cnpj));
	}

}

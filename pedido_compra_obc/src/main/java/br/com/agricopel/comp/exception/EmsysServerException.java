package br.com.agricopel.comp.exception;

public class EmsysServerException extends ProtheusException {

	private static final long serialVersionUID = 1L;

	public EmsysServerException(String mensagem) {
		super(mensagem);
	}
	
	@Override
	public String toString() {
		return "Protheus Server: ".concat(this.getMessage());
	}

}

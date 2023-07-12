package br.com.agricopel.comp.exception;

public class ProtheusServerException extends ProtheusException {

	private static final long serialVersionUID = 1L;

	public ProtheusServerException(String mensagem) {
		super(mensagem);
	}
	
	@Override
	public String toString() {
		return "Protheus Server: ".concat(this.getMessage());
	}

}

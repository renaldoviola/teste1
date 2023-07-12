package br.com.agricopel.comp.exception;

public class ProtheusException extends AgrException {

	private static final long serialVersionUID = 1L;

	public ProtheusException(String mensagem) {
		super(mensagem);
	}

	@Override
	public String toString() {
		return "Protheus: ".concat(this.getMessage());
	}

}

package br.com.agricopel.comp.exception;

public class EmsysException extends AgrException {

	private static final long serialVersionUID = 1L;

	public EmsysException(String mensagem) {
		super(mensagem);
	}

	@Override
	public String toString() {
		return "Emsys: ".concat(this.getMessage());
	}

}

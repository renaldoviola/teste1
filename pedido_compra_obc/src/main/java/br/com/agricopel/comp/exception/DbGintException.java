package br.com.agricopel.comp.exception;

public class DbGintException extends AgrException {

	private static final long serialVersionUID = 1L;

	public DbGintException(String mensagem) {
		super(mensagem);
	}
	
	@Override
	public String toString() {
		return "DbGint: ".concat(this.getMessage());
	}	

}

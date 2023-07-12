package br.com.agricopel.integrador_obc.obc.model;

public class PedidoCompraItemAnexoObc {

	private byte[] anexo;
	private String nomeArquivo;

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

}

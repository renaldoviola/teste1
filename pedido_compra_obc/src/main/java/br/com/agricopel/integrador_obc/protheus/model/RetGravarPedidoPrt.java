package br.com.agricopel.integrador_obc.protheus.model;

import com.google.gson.annotations.SerializedName;

public class RetGravarPedidoPrt {

	@SerializedName("SUCESSO")
	private Boolean sucesso;

	@SerializedName("CODPEDIDO")
	private String codPedido;

	@SerializedName(value = "ERRORCODE", alternate = { "errorCode" })
	private String errorCode;

	@SerializedName(value = "ERRORMESSAGE", alternate = { "errorMessage" })
	private String errorMessage;

	public RetGravarPedidoPrt() {
		this.sucesso = Boolean.FALSE;
		this.errorCode = "";
		this.errorMessage = "";
	}

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}

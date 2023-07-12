package br.com.agricopel.integrador_obc.comunicacaoWS.model.envio;

import com.google.gson.annotations.SerializedName;

public class ObcEnvEntidadeObc extends ObcEnv {

	@SerializedName("interface")
	private String _interface;

	private String acao;

	private String codigo;

	private String dados;

	public String get_interface() {
		return _interface;
	}

	public void set_interface(String _interface) {
		this._interface = _interface;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

}

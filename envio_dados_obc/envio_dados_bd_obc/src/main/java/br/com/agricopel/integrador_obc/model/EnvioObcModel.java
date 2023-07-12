package br.com.agricopel.integrador_obc.model;

import java.time.LocalDateTime;

import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;

public class EnvioObcModel {

	private int id;
	private String dados;
	private String chave;
	private String acao;
	private String ultimoErro;
	private int tentativasEnvio;
	private EntidadeObcEnum entidade;
	private LocalDateTime dataHora;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getUltimoErro() {
		return ultimoErro;
	}

	public void setUltimoErro(String ultimoErro) {
		this.ultimoErro = ultimoErro;
	}

	public int getTentativasEnvio() {
		return tentativasEnvio;
	}

	public void setTentativasEnvio(int tentativasEnvio) {
		this.tentativasEnvio = tentativasEnvio;
	}

	public EntidadeObcEnum getEntidade() {
		return entidade;
	}

	public void setEntidade(EntidadeObcEnum entidade) {
		this.entidade = entidade;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

}

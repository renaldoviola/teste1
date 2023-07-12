package br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento;

import java.util.List;

public class ObcRecFornecedor extends ObcRec {

	private String dados;

	private List<ObcSubRecFornecedorContato> retFornecedorContatos;

	private List<ObcSubRecFornecedorEmpresa> retFornecedorEmpresas;

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public List<ObcSubRecFornecedorContato> getRetFornecedorContatos() {
		return retFornecedorContatos;
	}

	public void setRetFornecedorContatos(List<ObcSubRecFornecedorContato> retFornecedorContatos) {
		this.retFornecedorContatos = retFornecedorContatos;
	}

	public List<ObcSubRecFornecedorEmpresa> getRetFornecedorEmpresas() {
		return retFornecedorEmpresas;
	}

	public void setRetFornecedorEmpresas(List<ObcSubRecFornecedorEmpresa> retFornecedorEmpresas) {
		this.retFornecedorEmpresas = retFornecedorEmpresas;
	}

}

package br.com.agricopel.integrador_obc.dao;

import br.com.agricopel.integrador_obc.conexao.ConexaoIntegracaoOBC;

abstract class IntegracaoObcDAO {

	protected ConexaoIntegracaoOBC conexaoIntegracaoOBC;

	public IntegracaoObcDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
	}

}

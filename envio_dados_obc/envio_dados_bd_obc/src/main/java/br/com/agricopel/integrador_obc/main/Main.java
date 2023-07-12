package br.com.agricopel.integrador_obc.main;

import br.com.agricopel.integrador_obc.integracao.ProcIntegracao;

public class Main {

	public static void main(String[] args) {

		try {
			new ProcIntegracao().executar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
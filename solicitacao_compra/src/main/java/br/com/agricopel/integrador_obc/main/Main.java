package br.com.agricopel.integrador_obc.main;

import java.time.LocalDateTime;

public class Main {

	public static LocalDateTime dataUltExec = LocalDateTime.parse("2021-12-17T13:02:25.217863");
    
	
	public static void main(String[] args) {
		new IntegraSolicitacaoCompra().integrarSolicitacaoNova();
	}
}
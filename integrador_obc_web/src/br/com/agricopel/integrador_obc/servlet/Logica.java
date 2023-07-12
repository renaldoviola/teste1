package br.com.agricopel.integrador_obc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logica {

	public Integer getTpRetorno(); 
	
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception;

}

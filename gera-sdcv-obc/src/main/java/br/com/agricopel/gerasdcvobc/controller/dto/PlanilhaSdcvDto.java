package br.com.agricopel.gerasdcvobc.controller.dto;

import java.util.List;

import br.com.agricopel.gerasdcvobc.model.Comprador;
import br.com.agricopel.gerasdcvobc.model.Filial;

public class PlanilhaSdcvDto {

	private List<Comprador> compradores;
	private List<Filial> filiais;

	public List<Comprador> getCompradores() {
		return compradores;
	}

	public void setCompradores(List<Comprador> compradores) {
		this.compradores = compradores;
	}

	public List<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}

}

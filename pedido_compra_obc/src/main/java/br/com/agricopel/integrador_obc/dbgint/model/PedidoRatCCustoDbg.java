package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDateTime;

public class PedidoRatCCustoDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Integer COM_PEDIDO_Numero;
	private Integer COM_PROPED_Sequencia;
	private Integer COM_CONPED_Sequencia;
	private LocalDateTime COM_CONPED_Created;
	private LocalDateTime COM_CONPED_Updated;
	private Double COM_CONPED_Quantidade;
	private String STG_FRT_TABCAR_Cop_Codigo;
	private String STG_GEN_TABCEN_Cop_Codigo;

	public Integer getSTG_GEN_TABEMP_Codigo() {
		return STG_GEN_TABEMP_Codigo;
	}

	public void setSTG_GEN_TABEMP_Codigo(Integer sTG_GEN_TABEMP_Codigo) {
		STG_GEN_TABEMP_Codigo = sTG_GEN_TABEMP_Codigo;
	}

	public Integer getSTG_GEN_TABFIL_Codigo() {
		return STG_GEN_TABFIL_Codigo;
	}

	public void setSTG_GEN_TABFIL_Codigo(Integer sTG_GEN_TABFIL_Codigo) {
		STG_GEN_TABFIL_Codigo = sTG_GEN_TABFIL_Codigo;
	}

	public Integer getCOM_PEDIDO_Numero() {
		return COM_PEDIDO_Numero;
	}

	public void setCOM_PEDIDO_Numero(Integer cOM_PEDIDO_Numero) {
		COM_PEDIDO_Numero = cOM_PEDIDO_Numero;
	}

	public Integer getCOM_PROPED_Sequencia() {
		return COM_PROPED_Sequencia;
	}

	public void setCOM_PROPED_Sequencia(Integer cOM_PROPED_Sequencia) {
		COM_PROPED_Sequencia = cOM_PROPED_Sequencia;
	}

	public Integer getCOM_CONPED_Sequencia() {
		return COM_CONPED_Sequencia;
	}

	public void setCOM_CONPED_Sequencia(Integer cOM_CONPED_Sequencia) {
		COM_CONPED_Sequencia = cOM_CONPED_Sequencia;
	}

	public LocalDateTime getCOM_CONPED_Created() {
		return COM_CONPED_Created;
	}

	public void setCOM_CONPED_Created(LocalDateTime cOM_CONPED_Created) {
		COM_CONPED_Created = cOM_CONPED_Created;
	}

	public LocalDateTime getCOM_CONPED_Updated() {
		return COM_CONPED_Updated;
	}

	public void setCOM_CONPED_Updated(LocalDateTime cOM_CONPED_Updated) {
		COM_CONPED_Updated = cOM_CONPED_Updated;
	}

	public Double getCOM_CONPED_Quantidade() {
		return COM_CONPED_Quantidade;
	}

	public void setCOM_CONPED_Quantidade(Double cOM_CONPED_Quantidade) {
		COM_CONPED_Quantidade = cOM_CONPED_Quantidade;
	}

	public String getSTG_FRT_TABCAR_Cop_Codigo() {
		return STG_FRT_TABCAR_Cop_Codigo;
	}

	public void setSTG_FRT_TABCAR_Cop_Codigo(String sTG_FRT_TABCAR_Cop_Codigo) {
		STG_FRT_TABCAR_Cop_Codigo = sTG_FRT_TABCAR_Cop_Codigo;
	}

	public String getSTG_GEN_TABCEN_Cop_Codigo() {
		return STG_GEN_TABCEN_Cop_Codigo;
	}

	public void setSTG_GEN_TABCEN_Cop_Codigo(String sTG_GEN_TABCEN_Cop_Codigo) {
		STG_GEN_TABCEN_Cop_Codigo = sTG_GEN_TABCEN_Cop_Codigo;
	}

}

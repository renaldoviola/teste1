package br.com.agricopel.integrador_obc.dbgint.model;

import java.time.LocalDateTime;

public class PedidoCompraAprovacaoDbg {

	private Integer STG_GEN_TABEMP_Codigo;
	private Integer STG_GEN_TABFIL_Codigo;
	private Integer COM_PEDIDO_Numero;
	private LocalDateTime COM_APRPED_DHAprovacao;
	private String STG_GEN_TABUSU_Apr_Login;
	private String COM_APRPED_Situacao;
	private Integer COM_MOTCAN_Codigo;
	private LocalDateTime COM_APRPED_Created;
	private LocalDateTime COM_APRPED_Updated;

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

	public LocalDateTime getCOM_APRPED_DHAprovacao() {
		return COM_APRPED_DHAprovacao;
	}

	public void setCOM_APRPED_DHAprovacao(LocalDateTime cOM_APRPED_DHAprovacao) {
		COM_APRPED_DHAprovacao = cOM_APRPED_DHAprovacao;
	}

	public String getSTG_GEN_TABUSU_Apr_Login() {
		return STG_GEN_TABUSU_Apr_Login;
	}

	public void setSTG_GEN_TABUSU_Apr_Login(String sTG_GEN_TABUSU_Apr_Login) {
		STG_GEN_TABUSU_Apr_Login = sTG_GEN_TABUSU_Apr_Login;
	}

	public String getCOM_APRPED_Situacao() {
		return COM_APRPED_Situacao;
	}

	public void setCOM_APRPED_Situacao(String cOM_APRPED_Situacao) {
		COM_APRPED_Situacao = cOM_APRPED_Situacao;
	}

	public Integer getCOM_MOTCAN_Codigo() {
		return COM_MOTCAN_Codigo;
	}

	public void setCOM_MOTCAN_Codigo(Integer cOM_MOTCAN_Codigo) {
		COM_MOTCAN_Codigo = cOM_MOTCAN_Codigo;
	}

	public LocalDateTime getCOM_APRPED_Created() {
		return COM_APRPED_Created;
	}

	public void setCOM_APRPED_Created(LocalDateTime cOM_APRPED_Created) {
		COM_APRPED_Created = cOM_APRPED_Created;
	}

	public LocalDateTime getCOM_APRPED_Updated() {
		return COM_APRPED_Updated;
	}

	public void setCOM_APRPED_Updated(LocalDateTime cOM_APRPED_Updated) {
		COM_APRPED_Updated = cOM_APRPED_Updated;
	}

}

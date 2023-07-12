package br.com.agricopel.integrador_obc.emsys.model;

public class FilialEmpresaEmv {

	private Integer cod_empresa;
	//private Integer GEN_TABFIL_Codigo;
	private String descri_empresa;
	private String estado_empresa;

	public Integer getcod_empresa() {
		return cod_empresa;
	}

	public void setcod_empresa(Integer Cod_empresa) {
		cod_empresa = Cod_empresa;
	}

	//public Integer getGEN_TABFIL_Codigo() {
	//	return GEN_TABFIL_Codigo;
	//}

	//public void setGEN_TABFIL_Codigo(Integer gEN_TABFIL_Codigo) {
	//	GEN_TABFIL_Codigo = gEN_TABFIL_Codigo;
	//}

	public String getdescri_empresa() {
		return descri_empresa;
	}

	public void setdescri_empresa(String Descri_empresa) {
		descri_empresa = Descri_empresa;
	}

	public String getestado_empresa() {
		return estado_empresa;
	}

	public void setestado_empresa(String Estado_empresa) {
		estado_empresa = Estado_empresa;
	}

}
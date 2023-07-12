package br.com.agricopel.comp.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EscreverTXTUtils {

	private String separador = "";
	private String novaLinha = System.getProperty("line.separator");
	private StringBuilder texto = new StringBuilder();
	private Boolean autoSeparar = Boolean.FALSE;

	public EscreverTXTUtils() {
	}

	public EscreverTXTUtils(String separador) {
		this();
		this.separador = separador;
		this.autoSeparar = Boolean.TRUE;
	}

	private void printNull() throws Exception {
		this.print("");
	}

	public String getTexto() {
		return this.texto.toString();
	}

	public void print(Double info, String format) throws Exception {

		if (info != null) {

			String infoStr;

			if (format != null) {
				infoStr = new DecimalFormat(format).format(info);
			} else {
				infoStr = info.toString();
			}

			this.print(infoStr);
		} else {
			this.printNull();
		}
	}

	public void print(Double info) throws Exception {

		this.print(info, "#.00");
	}

	public void print(Integer info) throws Exception {
		if (info != null) {
			this.print(info.toString());
		} else {
			this.printNull();
		}
	}

	public void print(Date info, String format) throws Exception {

		if (info != null) {

			String infoStr;

			if (format != null) {
				infoStr = new SimpleDateFormat(format).format(info);
			} else {
				infoStr = info.toString();
			}

			this.print(infoStr);
		} else {
			this.printNull();
		}
	}

	public void print(Date info) throws Exception {

		this.print(info, "dd/MM/AAAA");
	}

	public void print(String info) throws Exception {
		if (info != null) {
			this.texto.append(info);
		} else {
			this.printNull();
		}

		if (this.isAutoSeparar()) {
			this.printSeparador();
		}
	}

	public void printNovaLinha() throws Exception {
		this.texto.append(this.novaLinha);
	}

	public void printSeparador() throws Exception {
		this.texto.append(this.separador);
	}

	public Boolean isAutoSeparar() {
		return autoSeparar && !separador.isEmpty();
	}

	public void setAutoSeparar(Boolean autoSeparar) {
		this.autoSeparar = autoSeparar;
	}

}
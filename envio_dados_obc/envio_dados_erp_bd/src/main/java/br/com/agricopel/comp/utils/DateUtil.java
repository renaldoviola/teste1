package br.com.agricopel.comp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private SimpleDateFormat protheusDateFormat;
	private SimpleDateFormat autoSystemDateFormat;
	private SimpleDateFormat normalDateFormat;
	private SimpleDateFormat horaFormat;

	private SimpleDateFormat getProtheusDateFormat() {

		if (this.protheusDateFormat == null)
			this.protheusDateFormat = new SimpleDateFormat("yyyyMMdd");

		return this.protheusDateFormat;
	}

	private SimpleDateFormat getAutoSystemDateFormat() {

		if (this.autoSystemDateFormat == null)
			this.autoSystemDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		return this.autoSystemDateFormat;
	}

	private SimpleDateFormat getHoraFormat() {

		if (this.horaFormat == null)
			this.horaFormat = new SimpleDateFormat("HH:mm");

		return this.horaFormat;
	}

	private SimpleDateFormat getNormalDateFormat() {

		if (this.normalDateFormat == null)
			this.normalDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		return this.normalDateFormat;
	}

	public String dataToStrProtheus(Date data) {

		if (data == null) {
			return null;
		} else {
			return this.getProtheusDateFormat().format(data);
		}
	}

	public String horaToStrProtheus(Date hora) {

		if (hora == null) {
			return null;
		} else {
			return this.getHoraFormat().format(hora);
		}
	}

	public String dataStrProtheusToDataStr(String data) throws Exception {

		if (data == null) {
			return null;
		} else {
			return this.getNormalDateFormat().format(this.getProtheusDateFormat().parse(data));
		}
	}

	public String dataStrAutoSystemToProtheusDataStr(String data) throws Exception {

		if (data == null) {
			return null;
		} else {
			return this.getProtheusDateFormat().format(this.getAutoSystemDateFormat().parse(data));
		}
	}

}

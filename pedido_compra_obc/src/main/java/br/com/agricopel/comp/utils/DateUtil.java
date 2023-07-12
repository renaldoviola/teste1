package br.com.agricopel.comp.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private static boolean isHorarioVerao = ZoneId.systemDefault().getRules().isDaylightSavings(Instant.now());

	public String toProtheusData(LocalDate data) {
		return data.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	public String toEmsysData(LocalDate data) {
		return data.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	public LocalDateTime agora() {

		LocalDateTime agora = LocalDateTime.now();

		if (isHorarioVerao) {
			agora = agora.minusHours(1);
		}

		return agora;
	}

	public LocalDateTime toLocalDateTime(Timestamp timeStamp) {
		return timeStamp != null ? timeStamp.toLocalDateTime() : null;
	}

	public LocalDate toLocalDate(Date date) {
		return date != null ? date.toLocalDate() : null;
	}

}

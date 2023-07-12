package br.com.agricopel.comp.utils;

import java.text.ParseException;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

public class StrUtil {

	public String cnpjComFormatacao(String cnpj) {

		if (cnpj == null || cnpj.isEmpty()) {
			return "";
		}

		try {
			MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(cnpj);
		} catch (ParseException e) {
			return cnpj;
		}
	}

	public String cnpjSemFormatacao(String cnpj) throws Exception {

		cnpj = cnpj.replaceAll(Pattern.quote("."), "");
		cnpj = cnpj.replaceAll("/", "");
		cnpj = cnpj.replaceAll("-", "");

		return cnpj;
	}

	public String randomStr(int tamanho) {

		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		if (tamanho <= 0)
			tamanho = 20;

		for (int i = 0; i < tamanho; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		return sb.toString();
	}
}
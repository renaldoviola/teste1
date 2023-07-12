package br.com.agricopel.comp.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

	private static final String tagsPkg = "br.com.agricopel";
	private static final Logger logger = LogManager.getLogger();

	public static void escreverLogInfo(String observacao) {
		logger.info(observacao);
	}

	public static void escreverLogErro(Exception exception) {

		StringBuilder origem1 = new StringBuilder();

		logger.error("---------------------------------------------------");

		StackTraceElement[] stackTrace = exception.getStackTrace();
		String metodoOrigem = stackTrace[0].getMethodName();
		String classeOrigem = stackTrace[0].getClassName();

		origem1.append("Origem: ");
		origem1.append(classeOrigem);

		origem1.append(".");
		origem1.append(metodoOrigem);

		origem1.append(" - Linha: ");
		origem1.append(stackTrace[0].getLineNumber());

		logger.error(origem1);

		if (!classeOrigem.contains(tagsPkg)) {

			for (int i = 0; i < stackTrace.length; i++) {

				if (stackTrace[i].getClassName().contains(tagsPkg)) {

					StringBuilder origem2 = new StringBuilder();

					origem2.append("Origem 2: ");
					origem2.append(stackTrace[i].getClassName());

					origem2.append(".");
					origem2.append(stackTrace[i].getMethodName());

					origem2.append(" - Linha: ");
					origem2.append(stackTrace[i].getLineNumber());

					logger.error(origem2);
					break;
				}
			}
		}

		logger.error(("Erro: ").concat(exception.toString()));
		logger.error("---------------------------------------------------");
	}
}
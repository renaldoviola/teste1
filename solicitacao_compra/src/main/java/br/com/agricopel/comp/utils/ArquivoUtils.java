package br.com.agricopel.comp.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ArquivoUtils {

	public void escreverConteudoArquivo(String texto, File arquivo) throws Exception {
		
		if (arquivo.exists()) {
			arquivo.delete();
		}

		try (FileWriter fileWriter = new FileWriter(arquivo)) {
			fileWriter.write(texto);
			fileWriter.flush();
		}
	}
	
	public String lerConteudoArquivo(File arquivo) throws Exception {

		StringBuilder sb = new StringBuilder();

		try (Scanner scanner = new Scanner(arquivo)) {

			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}

			return sb.toString();
		}
	}

	public File[] getArquivosExtensao(String diretorio, String extensao) throws Exception {

		final String extencao = ".txt";
		final File diretorioSQL = new File(diretorio);

		return diretorioSQL.listFiles((File pathname) -> pathname.getName().endsWith(extencao));
	}
}
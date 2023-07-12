package br.com.agricopel.comp.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.agricopel.comp.exception.AgrException;

public class PlanilhaExcelUtils {

	public List<List<String>> importar(InputStream inputStream, String nomeArquivo) throws Exception {

		if (nomeArquivo.endsWith(".xls")) {
			return importarXls(inputStream);
		} else if (nomeArquivo.endsWith(".xlsx")) {
			return importarXlsx(inputStream);
		}

		throw new AgrException("Arquivo não reconhecido como arquivo Excel para ser importado!".concat(nomeArquivo));
	}

	private List<List<String>> importarXls(InputStream inputStream) throws Exception {

		DataFormatter fmt = new DataFormatter();

		POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);

		HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
		HSSFSheet sheet = workbook.getSheetAt(0);

		List<List<String>> planilhaList = new ArrayList<>();

		Iterator<Row> linhas = sheet.rowIterator();
		while (linhas.hasNext()) {

			List<String> linhasList = new ArrayList<>();

			HSSFRow linha = (HSSFRow) linhas.next();
			Iterator<Cell> celulas = linha.cellIterator();

			while (celulas.hasNext()) {
				HSSFCell celula = (HSSFCell) celulas.next();
				linhasList.add(fmt.formatCellValue(celula));
			}

			planilhaList.add(linhasList);
		}

		workbook.close();
		fileSystem.close();

		return planilhaList;

	}

	private List<List<String>> importarXlsx(InputStream inputStream) throws Exception {

		DataFormatter fmt = new DataFormatter();

		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> linhas = sheet.rowIterator();

		List<List<String>> planilhaList = new ArrayList<>();

		while (linhas.hasNext()) {

			List<String> linhasList = new ArrayList<>();

			XSSFRow linha = (XSSFRow) linhas.next();
			Iterator<Cell> celulas = linha.cellIterator();

			while (celulas.hasNext()) {
				XSSFCell celula = (XSSFCell) celulas.next();
				linhasList.add(fmt.formatCellValue(celula));
			}

			planilhaList.add(linhasList);
		}

		wb.close();

		return planilhaList;
	}

	public byte[] criarExcel(List<List<String>> listLinhas) throws Exception {

		try (ByteArrayOutputStream planilha = new ByteArrayOutputStream(); HSSFWorkbook workbook = new HSSFWorkbook()) {
			HSSFSheet sheet = workbook.createSheet("SDCVs criadas");

			int numLinha = 0;
			int numCampo = 0;

			for (List<String> linha : listLinhas) {
				HSSFRow row = sheet.createRow(numLinha++);

				numCampo = 0;
				for (String campo : linha) {
					row.createCell(numCampo++).setCellValue(campo);
				}
			}

			for (int i = 0; i < listLinhas.get(0).size(); i++) {
				sheet.autoSizeColumn(i);
			}

			workbook.write(planilha);
			planilha.flush();
			planilha.close();

			return planilha.toByteArray();
		}
	}

}

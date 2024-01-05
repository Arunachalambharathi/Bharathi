package com.techment.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations extends FunctionsLibrary {

	public static String getExcelData(int row, String key) throws Throwable {

		File f = new File("");
		FileInputStream f1 = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(f1);
		Sheet s = w.getSheet("");

		List<HashMap<String, String>> l = new ArrayList<>();
		Row headerRow = s.getRow(0);
		for (int i = 0; i < s.getPhysicalNumberOfRows(); i++) {

			HashMap<String, String> m = new HashMap<>();
			Row currentRow = s.getRow(i);
			for (int j = 0; j < s.getRow(0).getPhysicalNumberOfCells(); j++) {

				m.put(headerRow.getCell(j).getStringCellValue(), currentRow.getCell(j).getStringCellValue());

			}
			l.add(m);
		}

		return l.get(row).get(key);

	}

}

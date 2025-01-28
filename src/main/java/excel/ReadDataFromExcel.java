package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {

	public static void main(String[] args) throws Exception, IOException {

		String currentDirPath = System.getProperty("user.dir");

		File excelFile = new File(currentDirPath + "\\TestDataFiles\\Test.xlsx");

		XSSFWorkbook testDataExcel = new XSSFWorkbook(new FileInputStream(excelFile));

		XSSFSheet languagesSheet = testDataExcel.getSheet("Languages");

		Iterator<Row> allRows = languagesSheet.rowIterator();

		while (allRows.hasNext()) {
			Row eachRow = allRows.next();

			Iterator<Cell> allCells = eachRow.cellIterator();

			while (allCells.hasNext()) {
				Cell eachCell = allCells.next();

				switch (eachCell.getCellType()) {

				case STRING:
					System.out.println(eachCell.getStringCellValue());
					break;

				case NUMERIC:
					System.out.println(eachCell.getNumericCellValue());
					break;
				case BLANK:
					System.out.println(" ");
					break;
				}

			}
		}

	}

}

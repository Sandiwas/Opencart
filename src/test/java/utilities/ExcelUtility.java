package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	FileOutputStream fo;
	FileInputStream fi;
	XSSFWorkbook wb;
	XSSFSheet sh;
	XSSFRow row;
	XSSFCell cell;
	String path;
	public CellStyle style;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		int rowsCount = sh.getLastRowNum();

		wb.close();
		fi.close();
		return rowsCount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rownum);
		int cellcount = row.getLastCellNum();

		wb.close();
		fi.close();
		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {

		fi = new FileInputStream(path); // Open the Excel file for reading
		wb = new XSSFWorkbook(fi);      // Create a workbook instance for the Excel file
		sh = wb.getSheet(sheetName);    // Access the specified sheet within the workbook
		row = sh.getRow(rownum);        // Get the specified row in the sheet
		cell = row.getCell(cellnum);    // Get the specified cell in the row

		String data;                    // Declare a variable to store the cell data

		try {
			// data = cell.toString();
			DataFormatter df = new DataFormatter(); // Create a DataFormatter to format the cell value as a string
			data = df.formatCellValue(cell);        // Returns the formatted value of a cell as a String regardless of the cell
												    // type.
		} catch (Exception e) {

			data = "";                              // In case of an exception (e.g., if the cell is null), return an empty string
		}
		wb.close();                                 // Close the workbook to release resources
		fi.close();                                 // Close the file input stream to release resources
		return data;                                // Return the formatted cell data
	}

	public String setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {

		File xlfile = new File(path); // Create a File object named xlfile that represents the file or directory
										// specified by the path.

		if (!xlfile.exists()) { // If file not exists then create new file
			fo = new FileOutputStream(path);
			wb = new XSSFWorkbook();
			wb.write(fo);
		}

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);

		if (wb.getSheetIndex(sheetName) == -1) { // If sheet not exists then create new Sheet
			wb.createSheet(sheetName);
		}
		sh = wb.getSheet(sheetName);

		if (sh.getRow(rownum) == null) { // If row not exists then create new Row
			sh.createRow(rownum);
		}
		row = sh.getRow(rownum);

		cell = row.createCell(cellnum);
		cell.setCellValue(data);

		FileOutputStream fo = new FileOutputStream(path);
		wb.write(fo);

		wb.close();
		fo.close();
		fi.close();
		return sheetName;
	}

	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);

		row = sh.getRow(rownum);
		cell = row.getCell(colnum);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
		
		fi = new FileInputStream(path);        // Open the Excel file for reading
		wb = new XSSFWorkbook(fi);             // Create a workbook instance for the Excel file
		sh = wb.getSheet(sheetName);          // Access the specified sheet within the workbook
		row = sh.getRow(rownum);             // Get the specified row in the sheet
		cell = row.getCell(colnum);          // Get the specified cell in the row

		style = wb.createCellStyle();                                  // Create a new cell style for formatting
		style.setFillForegroundColor(IndexedColors.RED.getIndex());    // Set the foreground color of the cell style to red
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);        // Apply a solid fill pattern to the cell style
		cell.setCellStyle(style);                                     // Apply the created cell style to the cell

		fo = new FileOutputStream(path); // Open the Excel file for writing
		wb.write(fo); // Write the updated workbook to the file
		wb.close();   // Close the workbook to release resources
		fi.close();   // Close the file input stream to release resources
		fo.close();   // Close the file output stream to release resource
	}

}

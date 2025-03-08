package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "LoginData")  // Annotation to define a DataProvider named "loginData"
	public String[][] getData() throws IOException {
		
		String path = ".\\testData\\Opencart_LoginData.xlsx";  // Specify the path to the Excel file
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");         // Get the total number of rows in the specified sheet ("Sheet1")
		int totalcells = xlutil.getCellCount("Sheet1", 1);    // Get the total number of cells (columns) in the first row of the sheet

		
		                                                       // Initialize a two-dimensional array to store the login data
		                                                       // Rows: Total number of rows in the sheet
	                                                           // Columns: Total number of cells in each row
		String loginData[][] = new String[totalrows][totalcells];

	                                                          // Iterate through each row in the sheet (starting from row 1, as row 0 is often header)
		for (int i = 1; i <= totalrows; i++) {
		
			for (int j = 0; j < totalcells; j++) {             // Retrieve the cell data and store it in the array
	                                                         // Adjust the index (i - 1) because arrays are 0-indexed, but Excel rows are 1-indexed
			
				loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return loginData; //return two dimensional array 
	}
	
	//DataProviders1
	//DataProviders2
	//DataProviders3
	//DataProviders4
}

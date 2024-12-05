package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\LoginIdPass.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating object for Xlutility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String[][] logindata=new String[totalrows][totalcols]; // created for two dimension array to store data
		
		for(int i=1;i<=totalrows;i++) // read the data from xl storing in two dimensional array
		{
			for(int j=0;j<=totalcols;j++) // i is row and j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); // Store cell data
			}
		}
	return logindata ;	
	}
	//Data Provider 2
	
	//Data Provider 3
	
	//Data Provider 4
	
	/*
	 * @DataProvider(name = "LoginData")
public String[][] getData() {
    String path = ".\\testData\\LoginIdPass.xlsx";
    ExcelUtility xlutil = new ExcelUtility(path); // Creating object for ExcelUtility

    int totalRows = xlutil.getRowCount("Sheet1");
    int totalCols = xlutil.getCellCount("Sheet1", 1);

    String[][] loginData = new String[totalRows][totalCols]; // Create a two-dimensional array to store data

    for (int i = 1; i <= totalRows; i++) { // Assuming data starts from the second row (skip headers)
        for (int j = 0; j < totalCols; j++) { // Loop through columns
            loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // Store cell data
        }
    }
    return loginData;
}

	 */

}

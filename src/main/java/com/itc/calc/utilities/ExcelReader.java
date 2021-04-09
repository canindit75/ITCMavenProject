package com.itc.calc.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static Object[][] ReadFromExcelToObjArr(String filepath,String filename,String sheetname) throws IOException{
		File file = new File(filepath + "/" + filename);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum(); //non empty rows
		//Create Object Array
		Object[][] data = new Object[rowcount][1];
		System.out.println("rowcount = " + rowcount);
		Row keysRow = sheet.getRow(0); //Keys Row
		//Declaring a Hashtable - 
		Hashtable<String,String> rec = null;
		for(int r = 1; r < rowcount + 1;r++){
 			  Row dataRow = sheet.getRow(r);
 			  rec = new Hashtable<String,String>();
			  for(int c = 0;c < dataRow.getLastCellNum();c++){
				  //Place the data in the Hashtable
				  String key = keysRow.getCell(c).getStringCellValue();
				  String val = dataRow.getCell(c).getStringCellValue();
				  rec.put(key, val);
			  }
			  data[r-1][0] = rec;
		}	
		return data;
	}

	public static void ReadFromExcel(String filepath,String filename,String sheetname) throws IOException{
	    //Create an instance File object - points to the phyical location of file
		File file = new File(filepath + "/" + filename);
   	   //We are doing read operation - so open the file in input mode 
	   //for this create an instance FileInputStream and pass the file object which is pointing to the file location
		FileInputStream inputStream = new FileInputStream(file);
		//Create an instance of Workbook
		//there are 2 file formats in xls - xls and another is xlsx
		//for xls we use HSSFWorkbook and for xlsx we XSSFWorkbook
		//if(filename.endsWith(".xls") create an instance of HSSFWorkbook else XSSFWorkbook 
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum(); //non empty rows 
		//it will return only data rows count excluding header row count
		//rowcount = 3
		System.out.println("rowcount = " + rowcount);
		for(int r = 0; r < rowcount + 1;r++){
			  Row row = sheet.getRow(r);
			  for(int c = 0;c < row.getLastCellNum();c++){
				  Cell cell = row.getCell(c);
				  System.out.print(cell.getStringCellValue() + "\t");
			  }
			  System.out.println("");
		}		
	}
/*	public static void main(String[] args) throws IOException{
		String filepath  = System.getProperty("user.dir") +"/src/com/itc/calc/testdata";
		String filename = "CalorieTestDataSet.xlsx";
		String sheetname = "CalorieData";
		System.out.println("filepath = " + filepath);
		
		ExcelReader.ReadFromExcel(filepath, filename, sheetname);
	}
*/
}

package com.itc.calc.testcases;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.itc.calc.utilities.ExcelReader;
public class CalorieCalculatorDDT_ExcelTestCase {
	public static WebDriver driver = null;
	
	@BeforeClass
	public void LaunchBrowser(){
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.get("https://www.calculator.net/calorie-calculator.html");
		 driver.manage().window().maximize();		
	}
	@Test(dataProvider="getCalorieData")
	public void EnterCalorieDetails(Hashtable<String,String> data) throws InterruptedException{
	//public void EnterCalorieDetails(String age, String sex) throws InterruptedException{
		 WebElement ageTextbox = driver.findElement(By.id("cage"));
		 ageTextbox.clear();
		 ageTextbox.sendKeys(data.get("age"));
		 List<WebElement> genderList = driver.findElements(By.xpath("//*[@name='csex']"));
		 if(genderList.size()>0){
			 for(WebElement gender : genderList){
				 if(gender.getAttribute("value").equals(data.get("sex"))){
					 if(!gender.isSelected()){
						 gender.click();
						 break;
					 }
				 }
			 }
		 }
		 Thread.sleep(1000);
 	}
	
	@DataProvider
	public Object[][] getCalorieData() throws IOException{
		//String[][] data = new String[3][2];
		//3 data sets - each with age and gender
/*		data[0][0] = "45";
		data[0][1] = "f";
		data[1][0] = "56";
		data[1][1] = "m";
		data[2][0] = "67";
		data[2][1] = "f";
		return data;
*/		
/*		Object[][] data = new Object[3][1]; 
		Hashtable<String,String> rec1 = new Hashtable<String,String>();
		rec1.put("age", "45");
		rec1.put("sex", "f");
		
		data[0][0] = rec1;
		
		Hashtable<String,String> rec2 = new Hashtable<String,String>();
		rec2.put("age", "56");
		rec2.put("sex", "m");
		
		data[1][0] = rec2;
		
		Hashtable<String,String> rec3 = new Hashtable<String,String>();
		rec3.put("age", "67");
		rec3.put("sex", "f");
		
		data[2][0] = rec3;
		return data;
*/	
		String filepath  = System.getProperty("user.dir") +"/src/test/java/com/itc/calc/testdata";
		String filename = "CalorieTestDataSet.xlsx";
		String sheetname = "CalorieData";
		System.out.println("filepath = " + filepath);
		return ExcelReader.ReadFromExcelToObjArr(filepath, filename, sheetname);
	}
	
	@AfterClass
	public void CloseBrowser() throws InterruptedException{
		 Thread.sleep(3000);
		if(driver!=null)
		{
			 //driver.close(); //close the current active window
			 driver.quit(); //closes all browsers opened via script
		}
	}
}


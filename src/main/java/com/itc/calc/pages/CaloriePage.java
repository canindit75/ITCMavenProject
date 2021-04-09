package com.itc.calc.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class CaloriePage {
	

	public WebElement ageTextbox;
	public List<WebElement> genderList;
	
	
	public WebDriver driver;
	
	
	public CaloriePage(WebDriver driver){
		this.driver = driver;
	}
	public void EnterCalorieDetails(String age, String sex){
		ageTextbox = driver.findElement(By.id("cage"));
		ageTextbox.clear();
		ageTextbox.sendKeys(age);
		List<WebElement> genderList = driver.findElements(By.xpath("//*[@name='csex']"));
		if(genderList.size()>0){
			 for(WebElement gender : genderList){
				 if(gender.getAttribute("value").equals(sex)){
					 if(!gender.isSelected()){
						 gender.click();
						 break;
					 }
				 }
			 }
		 }
	}
}

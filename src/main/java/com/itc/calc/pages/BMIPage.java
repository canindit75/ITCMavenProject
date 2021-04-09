package com.itc.calc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.itc.calc.objectmap.ObjectRepos;

public class BMIPage {
	
	//@FindBy(id="cage")
	@FindBy(id=ObjectRepos.bmi_ageTextboxById)
	public WebElement ageTextbox;

	public WebDriver driver ;
	
	public BMIPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

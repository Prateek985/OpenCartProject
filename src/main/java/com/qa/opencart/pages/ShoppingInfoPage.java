package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Element_utils;

public class ShoppingInfoPage {
	
	
	private WebDriver driver;
	private Element_utils eleutil;

	// In this we need to first initialize private By locators
	private By emailId = By.id("input-email");
	

	// we will create a page class constructor as well
	public ShoppingInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new Element_utils(driver);
	}


	


}

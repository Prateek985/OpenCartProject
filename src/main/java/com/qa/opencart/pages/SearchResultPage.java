package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.contants.Appconstants;
import com.qa.opencart.util.Element_utils;

public class SearchResultPage {

	private WebDriver driver;
	private Element_utils eleutil;

	// In this we need to first initialize private By locators
	private By searchProduct = By.cssSelector("div.product-thumb");
	

	// we will create a page class constructor as well
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new Element_utils(driver);
	}
	
	public String getSearchPageTitle() {
		String title = eleutil.waitForTitleIs(Appconstants.SEARCH_PAGE_TITLE, 5);
		System.out.println("Search page title : " + title);
		return title;
	}

	public int getSearchProductCount() {
		return eleutil.waitForElementsVisible(searchProduct, 10).size();

	}

	public ProductInfoPage searchProduct(String productName) {
		System.out.println("searching for product: " + productName);
		eleutil.waitForElementVisible(By.linkText(productName), 10).click();
		return new ProductInfoPage(driver);
	}

}

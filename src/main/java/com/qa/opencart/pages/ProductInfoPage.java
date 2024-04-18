package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Element_utils;

public class ProductInfoPage {
	
	private WebDriver driver;
	private Element_utils eleutil;
	
	private Map<String, String> productMap = new HashMap<String, String>();

	// In this we need to first initialize private By locators
	private By productHeader = By.tagName("h1");
	private By images =By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By productQty = By.id("input-quantity");
	private By Button_Cart = By.id("button-cart");
	private By product_msg = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private By shopping_cart = By.xpath("//a[contains(text(),'shopping cart')]");
	

	// we will create a page class constructor as well
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new Element_utils(driver);
	}
	
	public String getProductHeader() {
		String header = eleutil.dogetText(productHeader);
		System.out.println(header);
		return header;
	}
	
	public int getProductImagesCount() {
		int totalimg = eleutil.waitForElementsVisible(images, 10).size();
		System.out.println("Image count for " + getProductHeader() + " : " + totalimg);
		return totalimg;
	}
	
	public void getProductMetaData() {
		List<WebElement> metaList = eleutil.getElements(productMetaData);
		for(WebElement e:metaList) {
			String text = e.getText();
			String metaKey = text.split(":")[0].trim();
			String metavalue = text.split(":")[1].trim();
			productMap.put(metaKey,metavalue);
		}
		}
		
		
		public void getProductPriceData() {
			List<WebElement> priceList = eleutil.getElements(productPriceData);
			String price = priceList.get(0).getText();
			String exTaxprice = priceList.get(1).getText().split(":")[1].trim();
			productMap.put("productprice",price);
			productMap.put("extraprice",exTaxprice);
	}
 
		
		public Map<String, String> getProductDetailsMap() {
			productMap.put("header",getProductHeader());
			productMap.put("productimage",String.valueOf(getProductImagesCount()));
			getProductMetaData();
			getProductPriceData();
			return productMap;
		}
		
		public void getProductQty(String qty) {
			System.out.println("Pro qty :  " + qty);
			eleutil.doSendkeys(productQty, "5");
			eleutil.doclick(Button_Cart);
		}
		
		public ShoppingInfoPage doShoppingCart() throws InterruptedException {
			Thread.sleep(5000);
			String text = eleutil.dogetText(product_msg);
			System.out.println(text);
			eleutil.waitForElementVisible(shopping_cart, 10).click();
			return new ShoppingInfoPage(driver);
		}
		

		
}

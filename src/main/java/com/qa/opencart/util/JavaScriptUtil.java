package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("return document.title;").toString();
	}

	public void goBackByJS() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("history.go(-1)");
	}
	
	public void goForwardByJS() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("history.go(1)");
	}
	
	public void goFreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("history.go(0)");
	}
	
	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("alert('" + message + "')");
	}
	
	public void generateConfirmPopUp(String message) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("confirm('" + message + "')");
	}
	
	public String getPageInnerText(String message) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight");
		// window is a object and scrollTo is a method 
		// 0 means from the top pixel or 0th pixel move to down 
	}
	
	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0");
		// window is a object and scrollTo is a method 
		// 0 means from the down pixel or 0th pixel move to up 
	}
	
	// sroll the particular page by upto certain height only
	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, '" + height + "')");
		// window is a object and scrollTo is a method 
		// 0 means from the down pixel or 0th pixel move to up 
	}
	
	// if we want to go at the middle of the page then we use scrollHeight/2;
	public void scrollPageDownMiddlePage(String height) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2");
		// window is a object and scrollTo is a method 
		// 0 means from the down pixel or 0th pixel move to up 
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		// window is a object and scrollTo is a method 
		// 0 means from the down pixel or 0th pixel move to up 
	}
	
	
	/**
	 * example: "document.body.style.zoom = '400.0%'"
	 * @param zoomPercentage
	 * this method will work in Chrome, Edge and Safari browser but in Mozilla firefox browser it will not work.
	 */
	public void zoomChromeEdgeSafari (String zoomPercentage) {
		String zoom = "document.body.style.zoom = '"+zoomPercentage+"%'";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(zoom);
	}
	
	/**
	 * example: "document.body.style.MozTransform = 'scale(0.5)'; ";
	 * @param zoomPercentage
	 * this method will work in mozilla edge.
	 */
	public void zoomFirefox(String zoomPercentage) {
		String zoom = "document.body.style.MozTransform = 'scale("+zoomPercentage+")'";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(zoom);
	}
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for(int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element);//1
			changeColor(bgcolor, element);//2
		}
	}
	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.backgroundColor = " + color + "'", element);
		
		try {
			Thread.sleep(20);
		}catch(InterruptedException e) {
			
		}
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void sendKeysUsingWithId(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
		//document.getElementById('input-email').value = 'tom@gmail.com'
	}
	 
}

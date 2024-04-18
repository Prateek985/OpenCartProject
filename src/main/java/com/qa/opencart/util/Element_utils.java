package com.qa.opencart.util;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;
import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.ElementException;
import com.qa.opencart.util.TimeUtil;

public class Element_utils {

	
	//SRP: = its a design pattern full form is single responsibility principle


		private final String DEFAULT_ELEMENT_TIME_OUT_MESSAGE = "Time out....Element is not found";
		
		private WebDriver driver;
		private JavaScriptUtil jsutil;
		
		public Element_utils(WebDriver driver) {
			this.driver = driver;
			jsutil = new JavaScriptUtil(driver);
		}
		
		private void NullBlankCheck(String value) {
			if(value == null || value.length() == 0) {
				throw new ElementException(value +"Value should not be Null or Blank");
			}
		}
		
		public By getBy(String locatorType,String locatorValue) {
			By locator = null;
			switch(locatorType.toLowerCase().trim())
			{
			
			case "id":
				locator = By.id(locatorValue);
				break;
			case "name":
				locator = By.name(locatorValue);
				break;
			case "classname":
				locator = By.className(locatorValue);
				break;
			case "xpath":
				locator = By.xpath(locatorValue);
				break;
			case "cssselector":
				locator = By.cssSelector(locatorValue);
				break;
			case "linktext":
				locator = By.linkText(locatorValue);
				break;
			case "partiallinktext":
				locator = By.partialLinkText(locatorValue);
				break;
			case "tagname":
				locator = By.tagName(locatorValue);
				break;
			default:
				//System.out.println("Plz pass the right locator Type : " +locatorType);
			//	throw new BrowserException("plz pass the right locator Type");
				
			}
			
			return locator;
		}
		
		//Find Element Utility Methods --------------------------------------------------------------------
		
		private void checkHighlight(WebElement element) {
			  if(Boolean.parseBoolean(DriverFactory.highlight)) {
			    	 jsutil.flash(element);
			     }
		}
		
		
		public WebElement getElement(By locator) {
			WebElement element = null;
			try {		
			     element = driver.findElement(locator);
			     checkHighlight(element);
			     
			}catch(NoSuchElementException e) {
				System.out.println("Element is not present on the page");
				e.printStackTrace();
				return null;
			}
			return element;
		}
		
		public String dogettext1(String locatorType,String locatorValue) {
			return getElement(locatorType,locatorValue).getText();
		}
		
		public void doSendKeys1(String locatorType,String locatorValue) {
			getElement(locatorType,locatorValue).click();
		}
		
		public WebElement getElement(String locatorType, String locatorValue) {
			return driver.findElement(getBy(locatorType,locatorValue));
			
		}
		
		/**
		 * when we do not want to wait for the respective element then we use this method
		 * @param locator
		 * @param value
		 */
		public void doSendkeys(By locator,String value) {
			NullBlankCheck(value);
			getElement(locator).clear();
			getElement(locator).sendKeys(value);
		}
		/**
		 * when we want to wait for the respective element then we use this method
		 * @param locator
		 * @param value
		 */
		public void doSendkeys(By locator,String value, int timeout) {
			NullBlankCheck(value);
			waitForElementVisible(locator, timeout).sendKeys(value);
		}
		
		public void doSendKeys(String locatorType,String locatorValue, String value) {
			NullBlankCheck(value);
			getElement(locatorType,locatorValue).sendKeys(value);
		}
		
		/**
		 * when we do not want to wait for the respective element then we use this method
		 * @param locator
		 */
		public void doclick(By locator) {
			getElement(locator).click();
		}
		/**
		 * when we want to wait for the respective element then we use this method
		 * @param locator
		 */
		public void doclick(By locator, int timeout) {
			waitForElementVisible(locator, timeout).click();;
		}
		
		
		public String dogetText(By locator) {
			return getElement(locator).getText();
		}
		
		
		public String doGetAttribute(By locator,String attrName) {
			return getElement(locator).getAttribute(attrName);
		}
		
		public boolean ElementIsDisplayed(By locator) {
			return getElement(locator).isDisplayed();
		}
		
		//if we need to check the element only 1 element then i need to call this below method
		public  boolean ElementsIsDisplayed(By locator) {
			if(getElements(locator).size() == 1) {
				return true;
			}
			return false;
		}
		
		//if we need to check the element more then 1 element then i need to call this below method
		public  boolean ElementsIsDisplayed1(By locator) {
			if(getElements(locator).size() > 0) {
				return true;
			}
			return false;
		}
		
		//Find Elements Utility Methods --------------------------------------------------------------------
		
		
		public List<WebElement> getElements(By locator) {
			return driver.findElements(locator);
		}
		
		public int getElementcount(By locator) {
			return getElements(locator).size();
		}
		
		public ArrayList<String> GetElementTextList(By locator) {
			List<WebElement> eleList = getElements(locator);
			ArrayList<String> eleText = new ArrayList<String>();
			for(WebElement e: eleList) {
				String text = e.getText();
				if(text.length() != 0 ) {
					eleText.add(text);
				}
			}
			
			return eleText;
		}
		
		public ArrayList<String> GetElementattribute(By locator,String attrname) {
			List<WebElement> eleAttr = getElements(locator);
			ArrayList<String> eletAttr = new ArrayList<String>();
			for(WebElement e : eleAttr) {
				String attrValue = e.getAttribute(attrname);
				if(attrValue.length() != 0) {
					eletAttr.add(attrValue);
				}
			}
			return eletAttr;
		}
		
		//**********************************Select Based drop Down utils*****************************
		
		
		public void doSelectByIndex(By locator, int index) {
			Select select = new Select(getElement(locator));
			select.selectByIndex(index);
		}
		
		public void doSelectByValue(By locator, String value) {
			NullBlankCheck(value);
			Select select = new Select(getElement(locator));
			select.selectByValue(value);
		}
		
		public void doSelectByVisibleText(By locator, String VisibleText) {
			NullBlankCheck(VisibleText);
			Select select = new Select(getElement(locator));
			select.selectByVisibleText(VisibleText);
		}
		
		public ArrayList<String> getDropDownOptionsTextList(By locator) {
			List<WebElement> OptionList = getDropDownOptionsList(locator);
			ArrayList<String> OptionsTextList = new ArrayList<String>();
			for(WebElement e: OptionList) {
				String OptionText = e.getText();
				OptionsTextList.add(OptionText);
			}
			return OptionsTextList;
		}
		
		public List<WebElement> getDropDownOptionsList(By locator) {	
		    Select select = new Select(getElement(locator));
		    return select.getOptions();
		}
		
		public int getDropDownValuesCount(By locator) {
			return getDropDownOptionsList(locator).size();
		}
		
		
		public void doSelectDropDownValue(By locator, String value) {
			NullBlankCheck(value);
		    List<WebElement> Country_List = getDropDownOptionsList(locator);
		    for(WebElement e: Country_List) {
		    	String Country_Text = e.getText();
		    	//System.out.println(Country_Text);
		    	if(Country_Text.equals(value)) {
		    		e.click();
		    		break;
		    	}
		    	System.out.println(Country_Text);
		    }
		}
		
		public void PrintSelectDropDownValue(By locator) {
		    List<WebElement> Country_List = getDropDownOptionsList(locator);
		    for(WebElement e: Country_List) {
		    	String Country_Text = e.getText();
		    	System.out.println(Country_Text);
		    }
		}
		
		public void doSelectValueFromDropDown(By locator,String Value) {
			List<WebElement> Contry_List = getElements(locator);
			for(WebElement e: Contry_List) {
				String country_Text = e.getText();
				if(country_Text.equals(Value)) {
					e.click();
					break;
				}
				//System.out.println(country_Text);
			}
			
		}
		
		//***************************Google Search Utility Method*******************************
		
		public void doSearch(By Searchlocator, By suggestion, String searchKey, String Value) throws InterruptedException {
			doSendkeys(Searchlocator,searchKey);
			Thread.sleep(3000);
			List<WebElement> suggList = getElements(suggestion);
			System.out.println(suggList.size());
			for(WebElement e: suggList) {
				String suggText = e.getText();
				if(suggText.contains(Value)) {
					e.click();
					break;
				}
			}
		}
		
		//******************** Actions Utils *****************************
		
		public void handleMenuSubMenuLevel2(By parentMenulocator, By SubMenulocator) throws InterruptedException {
			Actions act = new Actions(driver);
			act.moveToElement(getElement(parentMenulocator)).perform();
			Thread.sleep(5000);
			doclick(SubMenulocator);
		}
		
		public void handleMenuSubMenuLevel4(By level1Menu, By level2Menu, By level3Menu, By level4Menu) throws InterruptedException {
			doclick(level1Menu);
			Thread.sleep(5000);
			Actions act = new Actions(driver);
			act.moveToElement(getElement(level2Menu)).perform();
			Thread.sleep(5000);
			act.moveToElement(getElement(level3Menu)).perform();
			Thread.sleep(5000);
			doclick(level4Menu);
		}
		
		public  void doActionClick(By locator) {
			Actions act = new Actions(driver);
			act.click(getElement(locator)).perform();
		}

		public  void doActionSendKeys(By locator, String Value) {
			Actions act = new Actions(driver);
			act.sendKeys(getElement(locator), Value).perform();
		}
		
		// ****************************************************WebDriver wait******************************************
		
		public void clickWhenReady(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page. 
		 * This does not necessarily mean that the element is visible.
		 * @param locator
		 * @param timeout
		 * @return
		 */

		public WebElement waitForElementPresence(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		}
		
		public WebElement waitForElementsPresenceWithFluent(By locator, int timeout, int pollingtime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeout))
					.ignoring(NoSuchElementException.class)
					.pollingEvery(Duration.ofSeconds(pollingtime))
					.withMessage(DEFAULT_ELEMENT_TIME_OUT_MESSAGE);
		    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    }
		
		
		/**
		 * An expectation for checking that there is at least one element present on a web page.
		 * @param locator
		 * @param timeOut
		 */
		public void waitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
		}
		
		
		
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible.
		 * Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
		 * @param locator
		 * @param timeout
		 * @return
		 */
		public  WebElement waitForElementVisible(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		}
		
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locator are visible. 
		 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		public String waitForTitleContains(String titleFraction, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try {
				if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
					return driver.getTitle();
				}
			}catch(Exception e) {
				System.out.println("title is not found within: " +timeout);
			}
			
			return null;
		}
		
		public String waitForTitleIs(String title, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try {
				if(wait.until(ExpectedConditions.titleContains(title))) {
					return driver.getTitle();
				}
			}catch(Exception e) {
				System.out.println("title is not found within: " +timeout);
			}
			
			return null;
		}
		
		public String waitForUrlContains(String urlFraction, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try {
				if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
					return driver.getCurrentUrl();
				}
			}catch(Exception e) {
				System.out.println("Url fraction is not found within: " +timeout);
			}
			
			return null;
		}
		
		public String waitForUrlIs(String url, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try {
				if(wait.until(ExpectedConditions.urlToBe(url))) {
					return driver.getCurrentUrl();
				}
			}catch(Exception e) {
				System.out.println("Url is not found within: " +timeout);
			}
			
			return null;
		}
		
		public Alert waitForJsAlert(int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		
		public Alert waitForJsAlertWithFluentWait(int timeout, int pollingTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeout))
					.ignoring(NoAlertPresentException.class)
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.withMessage(DEFAULT_ELEMENT_TIME_OUT_MESSAGE);
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		public String getAlertText(int timeout) {
			return waitForJsAlert(timeout).getText();
		}
		
		public void acceptAlert(int timeout) {
		     waitForJsAlert(timeout).accept();;
		}
		
		public void dismissAlert(int timeout) {
		     waitForJsAlert(timeout).dismiss();
		}
		
		public void alertSendKeys(int timeout, String value) {
		    waitForJsAlert(timeout).sendKeys(value);
		}
		
		public Boolean waitForWindow(int totalNumberOfWindowsToBe, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.numberOfWindowsToBe(totalNumberOfWindowsToBe));
		}
		
		public void waitForFramesAndSwitchToIt(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		}
		
		public void waitForFramesAndSwitchToIt(int FrameIndex, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameIndex));
		}

		public void waitForFramesAndSwitchToIt(WebElement FrameElement, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));
		}
		
		
		/**
		 * The Below Method is a custom wait without interval time or polling Time 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
		public  WebElement retryingElement(By locator, int timeOut) {
			WebElement element = null;
			int attempts = 0;
			
			while(attempts < timeOut) {
				
				
				try {
					element = getElement(locator);
					System.out.println("element is found....." + locator + " in attempt "  +attempts);
					break;
				}catch(Exception e) {
					System.out.println("element is found....." + locator + " in attempt "  +attempts);
					TimeUtil.defaultTime();
					
				}	
				attempts++;
			}
			
			if(element == null) {
				System.out.println("element is found..... tried for " + timeOut + " with the interval of "  + 500 + " milliseconds...");
				throw new BrowserException("No such Element Exception");
			}
			return element;
		}
		
		/**
		 * The Below Method is a custom wait with interval time or polling Time 
		 * @param locator
		 * @param timeOut
		 * @return
		 */

		public  WebElement retryingElement(By locator, int timeOut, int IntervalTime) throws InterruptedException {
			WebElement element = null;
			int attempts = 0;
			
			while(attempts < timeOut) {
				
				
				try {
					element = getElement(locator);
					System.out.println("element is found....." + locator + " in attempt "  +attempts);
					break;
				}catch(Exception e) {
					System.out.println("element is found....." + locator + " in attempt "  +attempts);
					TimeUtil.applyWait(IntervalTime);
					
				}	
				attempts++;
			}
			
			if(element == null) {
				System.out.println("element is found..... tried for " + timeOut + " with the interval of "  + 500 + " milliseconds...");
				throw new BrowserException("No such Element Exception");
			}
			return element;
		}
	}




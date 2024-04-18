package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.contants.Appconstants;
import com.qa.opencart.logger.log;
import com.qa.opencart.util.Element_utils;

public class AccountPage {

	private WebDriver driver;
	private Element_utils eleutil;

	// In this we need to first initialize private By locators
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	// we will create a page class constructor as well
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new Element_utils(driver);
	}

	// public page Actions/Methods
	public String getAccountPageTitle() {
		String title = eleutil.waitForTitleIs(Appconstants.ACCOUNTS_PAGE_TITLE, 5);
		//System.out.println("Account page title : " + title);
		log.info("Account page title :" + title);
		return title;
	}

	public String getAccountPageURL() {
		String url = eleutil.waitForUrlContains(Appconstants.ACC_PAGE_URL_FRACTION, 5);
		//System.out.println("Account page url : " + url);
		log.info("Account page url : " + url);
		return url;
	}

	public boolean isLogoutLinkExit() {
		return eleutil.waitForElementVisible(logoutLink, 10).isDisplayed();
	}

	public boolean ismyAccountLinkExit() {
		return eleutil.waitForElementVisible(myAccountLink, 10).isDisplayed();
	}

	public SearchResultPage dosearch(String Searchkey) {
		//System.out.println("searching for : " + Searchkey);
		log.info("searching for : " +Searchkey);
		eleutil.doSendkeys(search, Searchkey);
		eleutil.doclick(searchIcon);
		return new SearchResultPage(driver);

	}

	public ArrayList<String> accountsPageHeaders() {
		List<WebElement> headersEleList = eleutil.getElements(headers);
		ArrayList<String> headerlist = new ArrayList<String>();
		for (WebElement e : headersEleList) {
			String header = e.getText();
			headerlist.add(header);
		}

		return headerlist;
	}

}

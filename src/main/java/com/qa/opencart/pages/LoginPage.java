package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.contants.Appconstants;
import com.qa.opencart.logger.log;
import com.qa.opencart.util.Element_utils;
import com.qa.opencart.util.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// in page class we will never write any testng code
	// page class is not responsible for test annotation

	// Page class/Page Library/Page Object
	private WebDriver driver;
	private Element_utils eleutil;

	// In this we need to first initialize private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By login = By.xpath("//input[@type='submit']");
	private By forgetPWdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// we will create a page class constructor as well
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new Element_utils(driver);
	}

	// public page Actions/Methods
	@Step("getting login page Title.......")
	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleIs(Appconstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		//System.out.println("login page title : " + title);
		log.info("login page title : " +title);
		return title;
	}

	@Step("Waiting for the expected URL.......")
	public String getLoginPageURL() {
		String url = eleutil.waitForUrlContains(Appconstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		//System.out.println("login page url : " + url);
		log.info("login page url : " + url);
		return url;
	}
	@Step("getting forget Passowrd Link Exit.......")
	public boolean isForgetPwdLinkExit() {
		return eleutil.ElementIsDisplayed(forgetPWdLink);
	}

	@Step("Login with username: {0} and password: {1}")
	public AccountPage doLogin(String username, String pwd) {
		//System.out.println("User creds :  " + username + " : " + pwd);
		log.info("User creds :  " + username + " : " + pwd);
		eleutil.waitForElementVisible(emailId, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eleutil.doSendkeys(password, pwd);
		eleutil.doclick(login);
		return new AccountPage(driver);

	}
	
	public RegisterationPage navigateToRegisterPage() {
		eleutil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME).click();
		return new RegisterationPage(driver);
	}

}

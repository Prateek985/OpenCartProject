package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.contants.Appconstants;
import com.qa.opencart.util.Element_utils;

import io.qameta.allure.Step;

public class RegisterationPage {

	
	private WebDriver driver;
	private Element_utils eleutil;

	// In this we need to first initialize private By locators
	private By firstname = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value = '1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value = '0']");
	private By agreeCheckBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit' and @value = 'Continue']");
	private By successMsg =  By.cssSelector("div#content h1");
	private By logoutlink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	// we will create a page class constructor as well
	public RegisterationPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new Element_utils(driver);
	}
	
	@Step("User registeration")
	public boolean userRegister(String firstName, String lastName,
			String email, String telephone, String password,String subscribe ) {
		eleutil.waitForElementVisible(firstname, 10).sendKeys(firstName);
		eleutil.doSendkeys(this.lastName, lastName);
		eleutil.doSendkeys(this.email, email);
		eleutil.doSendkeys(this.telephone, telephone);
		eleutil.doSendkeys(this.password, password);
		eleutil.doSendkeys(this.confirmpassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleutil.doclick(subscribeYes);
		}else {
			eleutil.doclick(subscribeNo);
		}
		
		eleutil.doclick(agreeCheckBox);
		eleutil.doclick(continueBtn);
		
		String regSuccessMessg = eleutil.waitForElementVisible(successMsg, 10).getText();
		if(regSuccessMessg.equals(Appconstants.USER_REG_SUCCESS_MESSG)) {
			eleutil.doclick(logoutlink);
			eleutil.doclick(registerLink);
			return true;
		}
		return false;
		
	}

}

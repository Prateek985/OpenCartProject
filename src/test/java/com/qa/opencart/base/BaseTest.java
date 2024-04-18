package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterationPage;
import com.qa.opencart.pages.SearchResultPage;

import io.qameta.allure.Step;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected LoginPage lg;
	protected AccountPage accpage;
	protected Properties prop;
	protected SearchResultPage srp;
	protected ProductInfoPage productInfoPage;
	protected RegisterationPage registerationPage;
	protected SoftAssert softAssert;

	@Step("Setup: launching {0} browser & init the properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		lg = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@Step("Closing the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

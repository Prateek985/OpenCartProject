package com.qa.opencartTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.StringUtil;

import io.qameta.allure.Step;

public class RegisterationPageTest extends BaseTest {

	@BeforeClass
	public void RegisterSetUp() {
		registerationPage = lg.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] { { "Rohan", "sharma", "6352417854", "rs@12345", "yes" },
				{ "shilpa", "parmar", "6452417854", "sp@12345", "no" },
				{ "om", "shukla", "6852417854", "os@12345", "yes" }, };
	}
//	@DataProvider
//	public Object[][] getUserRegTestDataFromExcel() {
//	    return ExcelUtil.getTestData(Appconstants.REGISTER_SHEET_NAME);
//	}

//	// Either we can give method name or this name as well below the data provider also
//	@DataProvider(name="cvsregisterData")
//	public Object[][] getUserRegTestDataFromCSV(){
//	    return CsvUtil.csvData(Appconstants.REGISTER_SHEET_NAME);
//	}

	@Step("Checking user registeration")
	@Test(dataProvider = "getUserRegTestData")
	public void userRegTest(String firstname, String lastname, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerationPage.userRegister(firstname, lastname, StringUtil.getRandomEmailId(), telephone,
				password, subscribe));
	}

}

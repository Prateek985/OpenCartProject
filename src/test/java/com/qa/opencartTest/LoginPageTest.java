package com.qa.opencartTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contants.Appconstants;
import com.qa.opencart.errors.Apperror;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: Design open cart login page")
@Story("US 101: Design login page features for open cart application")
@Feature("Feature 201: Adding login features")
public class LoginPageTest extends BaseTest {

	@Description("Checking logging page")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitletest() {
		String actTitle = lg.getLoginPageTitle();
		Assert.assertEquals(actTitle, Appconstants.LOGIN_PAGE_TITLE, Apperror.TITLE_NOT_FOUND);
	}

	@Description("Checking logging page URL")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageURLtest() {
		String acturl = lg.getLoginPageURL();
		Assert.assertTrue(acturl.contains(Appconstants.LOGIN_PAGE_URL_FRACTION),Apperror.URL_NOT_FOUND);

	}

	@Description("Forget password Link page")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgetPwdLinkExit() {
		Assert.assertTrue(lg.isForgetPwdLinkExit(), Apperror.LINK_NOT_FOUND);
	}

	@Description("Checking user is able to login")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {
		accpage = lg.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accpage.getAccountPageTitle(), Appconstants.ACCOUNTS_PAGE_TITLE,Apperror.TITLE_NOT_FOUND);
	}
}

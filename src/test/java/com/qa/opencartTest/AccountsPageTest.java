package com.qa.opencartTest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contants.Appconstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {

		accpage = lg.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1)
	public void accPageTitletest() {
		String actTitle = accpage.getAccountPageTitle();
		Assert.assertEquals(actTitle, Appconstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void accPageURLtest() {
		String acturl = accpage.getAccountPageURL();
		Assert.assertTrue(acturl.contains(Appconstants.ACC_PAGE_URL_FRACTION));

	}

	@Test(priority = 3)
	public void isLogoutLinkExit() {
		Assert.assertTrue(accpage.isLogoutLinkExit());
	}

	@Test(priority = 4)
	public void ismyAccountLinkExit() {
		Assert.assertTrue(accpage.ismyAccountLinkExit());
	}

	@Test(priority = 5)
	public void accPageHeadersTest() {
		List<String> accHeadersList = accpage.accountsPageHeaders();
		System.out.println(accHeadersList);
	}

	@Test(priority = 6)
	public void accSearch() {
		accpage.dosearch("macbook");
	}

}

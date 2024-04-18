package com.qa.opencartTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultTest extends BaseTest {

	@BeforeClass
	public void searchResultSetUp() {
		accpage = lg.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2}
		};
	}
	
	

	@Test(dataProvider = "getProductCountData")
	public void searchResultsCountTest(String searchKey, int ProductCount) {
		srp = accpage.dosearch(searchKey);
		Assert.assertEquals(srp.getSearchProductCount(), ProductCount);

	}

//	@Test
//	public void searchResultsTest() {
//		Assert.assertEquals(srp.getSearchProductCount(), 3);
//
//	}
}

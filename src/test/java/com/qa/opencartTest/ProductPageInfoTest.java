package com.qa.opencartTest;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.contants.Appconstants;
import com.qa.opencart.util.ExcelUtil;

public class ProductPageInfoTest extends BaseTest {
	// The AAA pattern is Arrange Act and Assert.
	// The Test Case which we have written or the test method should have one Hard
	// assertion and Multiple SoftAssertion in the method

	@BeforeClass
	public void infoPageSetUp() {
		accpage = lg.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductSearchData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
		};
	}

	@Test(priority = 1,dataProvider = "getProductSearchData")
	public void productHeaderTest(String searchKey, String productName) {
		srp = accpage.dosearch(searchKey);
		productInfoPage = srp.searchProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}
	
	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] {
			{"macbook","MacBook Pro", 4},
			{"imac","iMac", 3},
			{"samsung","Samsung SyncMaster 941BW", 1},
			{"samsung","Samsung Galaxy Tab 10.1", 7},
		};
	}
	
//	@DataProvider
//	public Object[][] getProductImageDataFromExcel() {
//			return ExcelUtil.getTestData(Appconstants.PRODUCT_SHEET_NAME);
//		
//	}

	@Test(priority = 2,dataProvider = "getProductImageData")
	public void ImagesCountTest(String searchKey, String productName, int imageCount) {
		srp = accpage.dosearch(searchKey);
		productInfoPage = srp.searchProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
	}

	@Test(priority = 3)
	public void productInfoTest() {
		srp = accpage.dosearch("macbook");
		productInfoPage = srp.searchProduct("MacBook Pro");
		Map<String, String> productActDetailsMap = productInfoPage.getProductDetailsMap();
		softAssert.assertEquals(productActDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productActDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productActDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productActDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productActDetailsMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productActDetailsMap.get("extraprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	@Test(priority = 4)
	public void getproductqtyTest() {
		srp = accpage.dosearch("macbook");
		productInfoPage = srp.searchProduct("MacBook Pro");
		productInfoPage.getProductQty(prop.getProperty("qty"));
	}

	@Test(priority = 5)
	public void doShopCartHeaderTest() throws InterruptedException {
		srp = accpage.dosearch("macbook");
		productInfoPage = srp.searchProduct("MacBook Pro");
		productInfoPage.getProductQty(prop.getProperty("5"));
		Assert.assertEquals(productInfoPage.doShoppingCart(), "Success: You have added MacBook Pro to your !");
			}
}

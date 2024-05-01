package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.Apperror;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;
import com.qa.opencart.logger.log;

public class DriverFactory {

	WebDriver driver;
	Properties prop;

	OptionsManager optionManager;

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public static String highlight;

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		// System.out.println("browser name : " + browserName);
		log.info("browser name : " + browserName);

		highlight = prop.getProperty("highlight");

		optionManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution
				init_remoteDriver("chrome");
			} else {
				tldriver.set(new ChromeDriver(optionManager.getChromeOptions()));

			}

			// driver = new ChromeDriver(optionManager.getChromeOptions());
			break;

		case "firefox":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution
				init_remoteDriver("firefox");
			} else {
				tldriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			}
			// driver = new FirefoxDriver(optionManager.getFirefoxOptions());
			break;

		case "edge":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution
				init_remoteDriver("edge");
			} else {
				tldriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			}
			// driver = new EdgeDriver(optionManager.getEdgeOptions());
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		default:
			// System.out.println("Plz pass the right browser....." + browserName);
			log.error("Plz pass the right browser....." + browserName);
			throw new BrowserException("Browser Not Found" + browserName);
		}
		myDriver().manage().deleteAllCookies();
		myDriver().manage().window().maximize();
		myDriver().get(prop.getProperty("url"));
		return myDriver();
	}

	private void init_remoteDriver(String browserName) {

		System.out.println("Running Test on GRID - REMOTE GRID" + browserName);
		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getChromeOptions()));
				break;
			case "firefox":
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getFirefoxOptions()));
				break;
			case "edge":
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getEdgeOptions()));
				break;
			default:
				System.out.println("Plz pass the right supported browser on GRID....." + browserName);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver myDriver() {
		return tldriver.get();
	}

//	public Properties initProp() {
//		
//		//envName = qa,stage, prod, uat, dev
//		//mvn clean install -Denv="qa"
//		
//		FileInputStream fis = null;
//		prop = new Properties();
//		
//		String envName = System.getProperty("env");
//		//System.out.println("Running Test in Env: " + envName);
//		log.info("Running Test in Env: " + envName);
//		try {
//		if(envName == "null") {
//			//System.out.println("No env is given.... hence running it on QA env");
//			log.info("No env is given.... hence running it on QA env");
//			fis = new FileInputStream("./src/test/resources/Config/Config.qa.properties");
//		}
//		else {
//		
//		switch (envName.toLowerCase().trim()) {
//		case "qa":
//			 fis = new FileInputStream("./src/test/resources/Config/Config.qa.properties");
//			break;
//		case "dev":
//			 fis = new FileInputStream("./src/test/resources/Config/Config.dev.properties");
//			break;
//		case "stage":
//			 fis = new FileInputStream("./src/test/resources/Config/Config.stage.properties");
//			break;
//		case "uat":
//			 fis = new FileInputStream("./src/test/resources/Config/Config.uat.properties");
//			break;
//		case "prod":
//			fis = new FileInputStream("./src/test/resources/Config/Config.properties");
//			break;
//		default:
//		//	System.out.println("plz pass the right browser name.... " + envName);
//			log.info("plz pass the right browser name...." + envName);
//			throw new FrameWorkException(Apperror.EN_NAME_NOT_FOUND + " : " + envName);
//		}
//	}
//		}catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//	try {
//		prop.load(fis);
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	
//	return prop;
//	}

	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/Config/Config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) myDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}

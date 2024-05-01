package com.qa.opencart.Factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.log;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
		}

		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			// System.out.println("Running chrome browser in headless mode");
			log.info("Running chrome browser in headless mode");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
		}
		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "Firefox");
		}

		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			// System.out.println("Running Firefox browser in headless mode");
			log.info("Running Firefox browser in headless mode");
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			fo.addArguments("--incognito");
		}
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "Edge");
		}

		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			// System.out.println("Running Edge browser in headless mode");
			log.info("Running Edge browser in headless mode");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			eo.addArguments("--inprivate");
		}
		return eo;
	}

}

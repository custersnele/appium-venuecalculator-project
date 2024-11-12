package be.pxl.venues.pages;

import io.appium.java_client.AppiumDriver;

public class BasePage {

	private final AppiumDriver driver;

	public BasePage(AppiumDriver driver) {
		this.driver = driver;
	}

	public AppiumDriver getDriver() {
		return driver;
	}
}

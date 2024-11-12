package be.pxl.venues.tests;

import be.pxl.venues.pages.MainPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculateVenueCostTest {

	private AndroidDriver driver;

	@BeforeAll
	public void setup() throws IOException, InterruptedException {

		// Replace with your actual app package
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("emulator-5554");
		options.setApp("./apps/venuecalculator-app-debug.apk");

		driver = new AndroidDriver(URI.create("http://localhost:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@AfterAll
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	//@Order(1)
	public void calculateCost() {
		MainPage mainPage = new MainPage(driver);
		mainPage.clickVenueDropDown();
		mainPage.selectVenueOption("City Hall");
		mainPage.fillNumberOfGuests(150);
		mainPage.selectPremiumLevelOfService();
		mainPage.clickCalculate();
		String cost = mainPage.getMessage();
		String expectedCost = "Total Cost: 25000 €\nUpfront Payment: 0 €";
		assertEquals(expectedCost, cost);
	}
}

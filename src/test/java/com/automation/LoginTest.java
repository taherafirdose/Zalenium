package com.automation;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

	RemoteWebDriver driver;

	@Parameters({ "browser" })
	@BeforeClass
	public void Setup(String browser) throws MalformedURLException {
		String url = "http://localhost:4444/wd/hub";
		DesiredCapabilities dc = new DesiredCapabilities();

		if (browser.equals("chrome")) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);

		}
		if (browser.equals("firefox")) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		}
		driver = new RemoteWebDriver(new URL(url), dc);

		driver.get("https://opensource-demo.orangehrmlive.com/");

	}

	@Test
	public void LoginTest() throws InterruptedException {

		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.name("Submit")).click();

		Thread.sleep(3000);
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");

	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

}

package com.grid.docker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BrowserTestUsingGridDocker {

//	WebDriver driver;
	ThreadLocal<RemoteWebDriver> drTL = new ThreadLocal<RemoteWebDriver>();
	RemoteWebDriver driver;

	/*
	 * start docker grid thru cmd > D: > docker compose -f docker-compose-grid.yml
	 * up
	 * 
	 * use http://localhost:4444 > sessions : use "secret" pwd to check the running
	 * application
	 */
	@BeforeSuite
	public void setUpGrid() {
//currently this is being done in jenkins ..ie. starting the docker grid and stopping it
	}

	public WebDriver getDriver() {
		return drTL.get();
	}

	public void setDriver(RemoteWebDriver dr) {
		drTL.set(dr);
	}

	@Test
	void remoteChromeDriverTest() throws InterruptedException, MalformedURLException {
//		driver = WebDriverManager.chromedriver().remoteAddress("http://localhost:4444").create();
		ChromeOptions opt = new ChromeOptions();
//		opt.setPlatformName("windows11");
		driver = new RemoteWebDriver(new URL("http://localhost:4444"), opt);
		drTL.set(driver);
		getDriver().get("https://www.myntra.com");
		String pageTitle = driver.getTitle();
		System.out.println("grid chrome .........." + pageTitle);
//		Thread.sleep(6000);
//		assertTrue(pageTitle.contains("Home"));
	}

	@Test
	void remoteEdgeDriverTest() throws InterruptedException, MalformedURLException {
//		driver = WebDriverManager.edgedriver().remoteAddress("http://localhost:4444").create();
		EdgeOptions opt = new EdgeOptions();
//		opt.setPlatformName("windows11");
		driver = new RemoteWebDriver(new URL("http://localhost:4444"), opt);
		drTL.set(driver);
		getDriver().get("https://flipkart.com");
//		String pageTitle = driver.getTitle();
		System.out.println("grid edge..........");
//		Thread.sleep(6000);
//		assertTrue(pageTitle.contains("Home"));
	}

	@Test
	void remoteFirefoxDriverTest() throws InterruptedException, MalformedURLException {
//		driver = WebDriverManager.firefoxdriver().remoteAddress("http://localhost:4444").create();
		FirefoxOptions opt = new FirefoxOptions();
		driver = new RemoteWebDriver(new URL("http://localhost:4444"), opt);
		drTL.set(driver);
		getDriver().get("https://amazon.com");
//		String pageTitle = driver.getTitle();
		System.out.println("grid firefox..........");
//		Thread.sleep(6000);
//		assertTrue(pageTitle.contains("Home"));
	}

	@AfterSuite
	public void tearDownGrid() {
		getDriver().quit();
	}
}

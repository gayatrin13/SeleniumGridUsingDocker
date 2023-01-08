package com.grid.docker;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserTestUsingGridDocker {

	WebDriver driver;

	/*
	 * start docker grid thru cmd > D: > docker compose -f docker-compose-grid.yml
	 * up
	 * 
	 * use http://localhost:4444 > sessions : use "secret" pwd to check the running
	 * application
	 */
	@AfterSuite
	public void setUpGrid() {

	}

	@Test
	void remoteChromeDriverTest() throws InterruptedException {
		driver = WebDriverManager.chromedriver().remoteAddress("http://localhost:4444").create();
		driver.get("https://practice-site.devops-automation.net");
		String pageTitle = driver.getTitle();
		System.out.println("grid chrome .........." + pageTitle);
		Thread.sleep(3000);
//		assertTrue(pageTitle.contains("Home"));
	}

	@Test
	void remoteEdgeDriverTest() throws InterruptedException {
		driver = WebDriverManager.edgedriver().remoteAddress("http://localhost:4444").create();
		driver.get("https://flipkart.com");
//		String pageTitle = driver.getTitle();
		System.out.println("grid edge..........");
		Thread.sleep(3000);
//		assertTrue(pageTitle.contains("Home"));
	}

	@Test
	void remoteFirefoxDriverTest() throws InterruptedException {
		driver = WebDriverManager.firefoxdriver().remoteAddress("http://localhost:4444").create();

		driver.get("https://amazon.com");
//		String pageTitle = driver.getTitle();
		System.out.println("grid firefox..........");
		Thread.sleep(3000);
//		assertTrue(pageTitle.contains("Home"));
	}

	@BeforeSuite
	public void tearDownGrid() {

	}
}

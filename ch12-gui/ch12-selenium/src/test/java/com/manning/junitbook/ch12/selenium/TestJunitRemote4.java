package com.manning.junitbook.ch12.selenium;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.SeleniumServer;

public class TestJunitRemote4 {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new ChromeDriver();
		selenium = new WebDriverBackedSelenium(driver, "https://github.com/");
//		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://github.com/");
//		selenium.start();
	}

	@Test
	public void testTestJunitRemote4() throws Exception {
		selenium.open("/SeleniumHQ/selenium/wiki/SeIDEReleaseNotes");
		selenium.click("link=Design Patterns");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}

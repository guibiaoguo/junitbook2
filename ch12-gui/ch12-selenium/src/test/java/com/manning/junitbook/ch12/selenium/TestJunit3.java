package com.manning.junitbook.ch12.selenium;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

public class TestJunit3 extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("https://github.com/", "*iexplore");
	}
	public void testTestJunit3() throws Exception {
		selenium.open("/SeleniumHQ/selenium/wiki/SeIDEReleaseNotes");
		selenium.click("link=Design Patterns");
		selenium.waitForPageToLoad("30000");
	}
}

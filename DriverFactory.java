package com.testSample.PropertiesFlow;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	private static RemoteWebDriver webDriver;
	private static final String operatingSystem = System.getProperty("os.name").toUpperCase();
	private static final String systemArchitecture = System.getProperty("os.arch");
	
	public static RemoteWebDriver getDriver() {
		if (null == webDriver) {
			System.out.println("  ");
			System.out.println("Current Operating System: " + operatingSystem);
			System.out.println("Current Architecture: " + systemArchitecture);
			System.out.println("Current Browser Selection: Chrome");
			
			Globals.loadProperties();
			System.setProperty("webdriver.chrome.driver", Globals.CHROMEDRIVER);
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", Globals.IEDRIVER);
			WebDriver driver=new InternetExplorerDriver();
			
			Globals.loadProperties();
			
			if (Globals.HEADLESS.equalsIgnoreCase("yes") || Globals.HEADLESS.equalsIgnoreCase("true")) {
				options.addArguments("--headless" , "--disable-gpu" , "--window-size=1280,1024" , "--ignore=certificate-errors" , "--disable-infobars");
			
			
			
			
			
			}
			else
			{options.addArguments("--ignore-certificate-errors" , "--disable-infobars");
		}
		options.setExperimentalOption("useAutomationExtension", false);
		webDriver = new ChromeDriver(options);
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	return webDriver;
	
}
public static void quitDriver() {
	if (null != webDriver) {
		webDriver.quit();
		webDriver = null ;
	}
}
	
}
	
	
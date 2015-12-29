package tests.userManagement;


import java.net.URL;

import org.junit.BeforeClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class LoginTests {

	static WebDriver driver;
	
	@Test
	public static void openLocalhostFromDocker() throws Exception {

			DesiredCapabilities capability = getDesiredCapabilities("FF");
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);	
			driver.manage().window().maximize();
			driver.get("http://localhost/"); 
				
	}
	
   

	
	
	
	static DesiredCapabilities capability = null;
	static WebDriver	rwd = null;
	
	public static DesiredCapabilities getDesiredCapabilities(String environment) {

		if (environment.equalsIgnoreCase("FF")) {
			capability = DesiredCapabilities.firefox();
		//	capability.setJavascriptEnabled(true);
		//	capability.setBrowserName("nodeff");
			capability.setPlatform(Platform.LINUX);

		} else if (environment.equalsIgnoreCase("CHROME_LocalHost_W7")) {

			capability = DesiredCapabilities.chrome();
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("chrome");
			capability.setVersion("CHROME_LocalHost_W7");
			capability.setPlatform(Platform.WINDOWS);

		} else if (environment.equalsIgnoreCase("IE_LocalHost_W7")) {

			capability = DesiredCapabilities.internetExplorer();
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("internet explorer");
			capability.setVersion("IE_LocalHost_W7");
			capability.setPlatform(Platform.WINDOWS);

		}

		return capability;

	}

	public static WebDriver driver(String browserPlattform) throws Exception{		
		try {
			rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getDesiredCapabilities(browserPlattform));
		} catch (Exception e) {
			System.out.println("konnte WebDriver nicht aufbauen");
		}
		return rwd;
		
		
	}
	
}

package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DriverTest {
	
	public static void openff(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.de");
	}
	
	@Test
	public static void getSystemproperties(){
		System.getProperties().list( System.out);
	}
	
	
	
}

package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InterfaceAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	static WebDriver driver;
	
	public static String getProperties(String key) {

		String value = "not set";
		
		String resourceName = "config.properties"; // could also be a constant
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
		    props.load(resourceStream);
			// get the property value and print it out
			System.out.println(props.getProperty(key));
			value = props.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;

	  }
	
	public static WebDriver initializeDriver() throws MalformedURLException {
		WebDriver driver = DriverProvider.getDriver();
		// DriverProvider.navigateToBaseUrl(driver);
		return driver;
	}

	public static String getDockerHubIp() throws SocketException {
		String ipDocker;
		try {
			NetworkInterface networkInterface = NetworkInterface.getByName("docker0");
			List<InterfaceAddress> ls = networkInterface.getInterfaceAddresses();
			InterfaceAddress ifa = ls.get(1);
			ipDocker = ifa.getAddress().getHostAddress();
			System.out.println("ip: " + ipDocker);

		} catch (Exception e) {
			System.out.println("docker0 interface nicht aktiv");
			ipDocker = getProperties("urlHomePage");
		}
		return ipDocker;
	}

	public static void waitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}

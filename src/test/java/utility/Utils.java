package utility;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class Utils {
	
    public static WebDriver initializeDriver() {
        WebDriver driver = DriverProvider.getDriver();
        //DriverProvider.navigateToBaseUrl(driver);
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
			ipDocker = "Docker Hub not running";
		}
		return ipDocker;
	}



}

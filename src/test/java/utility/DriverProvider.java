package utility;

import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.frontend.MainheaderPage;

public class DriverProvider {
	 private static RemoteWebDriver driver;
	 private static String dockerIp;


	    /**
	     * The shutdown hook
	     */
	    private static Thread shutdownHook = new Thread() {
	        @Override
	        public void run() {
	           // stopDriver();
	        }
	    };

	    /**
	     * Returns the {@link org.openqa.selenium.WebDriver} instance used for the tests.
	     * The driver navigates to the URL returned by  {@link #getHTTPBaseUrl()}
	     * @return The {@link org.openqa.selenium.WebDriver} instance used for the tests.
	     * @throws MalformedURLException 
	     */
	    public static WebDriver getDriver() throws MalformedURLException {
	        if (driver == null) {
	            startDriver();
	        }
	        return driver;
	    }


	    public static WebDriver restartDriver() throws MalformedURLException {
	        stopDriver();
	        startDriver();

	        return driver;
	    }



	    /**
	     * Starts the driver, adds a shutdown hook to stop the driver and navigates to {@link #getHTTPBaseUrl()}
	     * @throws MalformedURLException 
	     */
	    private static void startDriver() throws MalformedURLException {	    
	    	DesiredCapabilities capability = null;

			capability = DesiredCapabilities.firefox();
			capability.setJavascriptEnabled(true);
			
			try {
				dockerIp = Utils.getDockerHubIp();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				dockerIp = "http://172.17.0.1";
				e.printStackTrace();
			} 
			driver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), capability);
			//driver.get(Utils.getProperties("dockerIp"));
			driver.get(dockerIp);
			
	        registerShutdownHook();
	        // config.propeties --> url
	        // system.properties --> via mvn -DlocalhostPort or default 80
	        
	        
	        // --> alt:   driver.get(Utils.getProperties("urlHomePage") + ":" + System.getProperty("localhostPort", defaultPort));  
	         
	    }



	    private static void stopDriver() {
	        driver.quit();
	        driver = null;
	        
	    }

	    /**
	     * Registers a shutdown hook via {@link Runtime#addShutdownHook(Thread)}
	     */
	    private static void registerShutdownHook() {
	        Runtime.getRuntime().removeShutdownHook(shutdownHook);
	        Runtime.getRuntime().addShutdownHook(shutdownHook);
	    }




	    public static boolean isOnMainheaderPage(WebDriver driver) {
	        try {
	            MainheaderPage login = PageFactory.initElements(driver, MainheaderPage.class);
	            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	            login.isDisplayed();
	        } catch (NoSuchElementException e) {
	            return false;
	        }

	        return true;
	    }
}

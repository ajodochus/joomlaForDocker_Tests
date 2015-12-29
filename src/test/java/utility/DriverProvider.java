package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import pages.frontend.MainheaderPage;

public class DriverProvider {
	 private static WebDriver driver;
	 private static final String LOCALHOST = "localhost";
	    /**
	     * The shutdown hook
	     */
	    private static Thread shutdownHook = new Thread() {
	        @Override
	        public void run() {
	            stopDriver();
	        }
	    };

	    /**
	     * Returns the {@link org.openqa.selenium.WebDriver} instance used for the tests.
	     * The driver navigates to the URL returned by  {@link #getHTTPBaseUrl()}
	     * @return The {@link org.openqa.selenium.WebDriver} instance used for the tests.
	     */
	    public static WebDriver getDriver() {
	        if (driver == null) {
	            startDriver();
	        }
	        return driver;
	    }

	    /**
	    * Restarts the driver (means it stops the driver and then starts it again).
	    * @return The driver to be used now
	    */
	    public static WebDriver restartDriver() {
	        stopDriver();
	        startDriver();

	        return driver;
	    }



	    /**
	     * Starts the driver, adds a shutdown hook to stop the driver and navigates to {@link #getHTTPBaseUrl()}
	     */
	    private static void startDriver() {	        
	        driver = new FirefoxDriver();
	        registerShutdownHook();
	        driver.get(getHTTPBaseUrl());  
	    }




	    /**
	     * Stops the driver
	     */
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

	    /**
	     * Returns the http base URL
	     * @return the http base URL
	     */
	    private static String getHTTPBaseUrl() {
	        return "http://" + LOCALHOST;
	    }

	    /**
	     * Navigates to the base url ({@link #getHTTPBaseUrl()})
	     * @param driver The driver
	     */
	    public static void navigateToBaseUrl(WebDriver driver) {
	        driver.navigate().to(getHTTPBaseUrl());
	    }

	    /**
	     * Navigates to "{@link #getHTTPBaseUrl()} + {@code subUrl}"
	     * @param driver The driver
	     * @param subUrl The sub url
	     */
	    public static void navigateToSubUrl(WebDriver driver, String subUrl) {
	        driver.navigate().to(getHTTPBaseUrl() + subUrl);
	    }

	   

	    /**
	     * Returns whether the driver is located on the login page or not
	     * @param driver The driver
	     * @return {@code true} if the driver is on the login page {@code false} otherwise
	     */
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

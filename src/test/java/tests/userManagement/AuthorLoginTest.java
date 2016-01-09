package tests.userManagement;

import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.frontend.AuthorLoginPage;
import pages.frontend.AuthorProfilePage;
import pages.frontend.MainheaderPage;

import utility.DriverProvider;
import utility.Utils;

public class AuthorLoginTest {

	private static WebDriver driver;

	private static AuthorLoginPage authorLoginPage;
	private static AuthorProfilePage authorProfilePage;
	private static MainheaderPage  mainheaderPage;
	

	@Test(groups={"login"})
	public static void loginAuthorAdmin() {
		DriverProvider.setHTTPBaseUrl(Utils.getProperties("urlHomePage"));
		//driver = Utils.initializeDriver();
		driver  = DriverProvider.getDriver();

		initializePageObjects();
		//call localhost
		Assert.assertTrue(mainheaderPage.isDisplayedWithExplicitWait());
		mainheaderPage.get_lnk_AuthorLogin().click();
		
		
		//goto Author Login Page by link
		Assert.assertTrue(authorLoginPage.isDisplayedWithExplicitWait());
		System.out.println(authorLoginPage.getTitle());
		System.out.println("url: " + authorLoginPage.expectedUrl());
		System.out.println("currenturl: " + driver.getCurrentUrl());
		
		authorLoginPage.loginWithAdmin();
		
		
		// Author Profile Page
		//authorProfilePage.isDisplayedWithExplicitWait();
		System.out.println("Page Title after Author login: " + authorProfilePage.getH1Headline());
		

	}
	
	@Test(groups={"properties"})
	public static void getProperties(){
		System.out.println("url: " + Utils.getProperties("urlHomePage"));
		String localhostPort = "Port not readale";
		localhostPort = System.getProperty("localhostPort");
		System.out.println("Sytem.Properties via pom and commandline: " + localhostPort);
	}

	private static void initializePageObjects() {
		mainheaderPage = PageFactory.initElements(driver, MainheaderPage.class);
		authorLoginPage = PageFactory.initElements(driver, AuthorLoginPage.class);
		authorProfilePage = PageFactory.initElements(driver, AuthorProfilePage.class);

	}

}

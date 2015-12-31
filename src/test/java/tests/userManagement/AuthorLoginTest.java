package tests.userManagement;

import java.net.MalformedURLException;

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
	private static MainheaderPage mainHeaderPage;
	private static AuthorLoginPage authorLoginPage;
	private static AuthorProfilePage authorProfilePage;

	@Test(groups={"login"})
	public static void loginAuthorAdmin() throws MalformedURLException {
		
		driver = Utils.initializeDriver();

		initializePageObjects();
		//call localhost
		Assert.assertTrue(mainHeaderPage.isDisplayedWithExplicitWait());
		mainHeaderPage.get_lnk_AuthorLogin().click();
		
		//goto Author Login Page by link
		Assert.assertTrue(authorLoginPage.isDisplayedWithExplicitWait());
		System.out.println(authorLoginPage.getTitle());
		authorLoginPage.loginWithAdmin();
		
		// Author Profile Page
		authorProfilePage.isDisplayedWithExplicitWait();
		System.out.println(authorProfilePage.getH1Headline());
		

	}
	
	@Test(groups={"properties"})
	public static void getProperties(){
		System.out.println("url: " + Utils.getProperties("urlHomePage"));
		String localhostPort = "Port not readale";
		localhostPort = System.getProperty("localhostPort");
		System.out.println("Sytem.Properties via pom and commandline: " + localhostPort);
	}

	private static void initializePageObjects() {
		mainHeaderPage = PageFactory.initElements(driver, MainheaderPage.class);
		authorLoginPage = PageFactory.initElements(driver, AuthorLoginPage.class);
		authorProfilePage = PageFactory.initElements(driver, AuthorProfilePage.class);

	}

}

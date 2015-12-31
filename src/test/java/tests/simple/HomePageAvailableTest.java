package tests.simple;

import pages.frontend.MainheaderPage;
import utility.Utils;
import utility.DriverProvider;

import java.net.MalformedURLException;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class HomePageAvailableTest {
	private static WebDriver driver;
    private static MainheaderPage page;
    
    @Test
    public static void setupClass() throws MalformedURLException {
        driver = Utils.initializeDriver();
        initializePageObjects();
        System.out.println(page.getTitle());
      
       
    }
    
    
    private static void initializePageObjects() {
        page = PageFactory.initElements(driver, MainheaderPage.class);
       // createProjectPage = PageFactory.initElements(driver, CreateProjectPage.class);
        //editPage = PageFactory.initElements(driver, EditPage.class);
    }
    
     
}

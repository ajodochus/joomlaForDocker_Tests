package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	@FindBy(how = How.XPATH, using = "//html")
	private WebElement html;

	private static final long WAIT_FOR_PAGE_TO_LOAD_TIME = Long
			.parseLong(System.getProperty("selenium.waitForPageToLoad.timeout", "60"));
	protected WebDriver driver;
	protected String url;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		this.driver.getCurrentUrl();
    }
	public abstract boolean isDisplayed();
    protected abstract WebElement explicitWaitReturn();
    public abstract String expectedUrl();
    
    
    public void explicitWaitForPage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_FOR_PAGE_TO_LOAD_TIME);

        wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    if (isDisplayed()) {
                    	
                        return explicitWaitReturn();
                    }
                } catch (Exception e) {
                    //avoid that any exception causes the wait to be stopped
                    //print out the error message to identify which UI element cannot be found
                    System.out.println(e.getMessage());
                }

                return null;
            }
        });
    }

    public boolean isDisplayedWithExplicitWait() {
        explicitWaitForPage();
        return isDisplayed();
    }

    public String getTitle() {
        return driver.getTitle();
    }
    public String getUrl() {
        return url;
    }
	      
}

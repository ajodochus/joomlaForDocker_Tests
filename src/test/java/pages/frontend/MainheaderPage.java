package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainheaderPage extends AbstractPage {

	@FindBy(how = How.LINK_TEXT, using = "Author Login")
    private WebElement lnk_AuthorLogin;
	
	public MainheaderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isDisplayed() {
		return get_lnk_AuthorLogin().isDisplayed();
	}

	@Override
	protected WebElement explicitWaitReturn() {
		// TODO Auto-generated method stub
		return get_lnk_AuthorLogin();
	}
	public WebElement get_lnk_AuthorLogin() {
        return lnk_AuthorLogin;
    }



}

package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthorProfilePage extends MainheaderPage {

	@FindBy(how = How.XPATH, using = ".//*[@id='users-profile-core']/legend")
    private WebElement txt_profileheader;
	
	public AuthorProfilePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isDisplayed() {
		return super.isDisplayed() && txt_profileHeader().isDisplayed();
	}

	@Override
	protected WebElement explicitWaitReturn() {
		// TODO Auto-generated method stub
		return txt_profileHeader();
	}
	
	protected WebElement txt_profileHeader() {
        return txt_profileheader;
    }
	public String getH1Headline(){
		return txt_profileHeader().getText();
		
	}

}

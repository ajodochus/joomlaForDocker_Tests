package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthorLoginPage extends MainheaderPage{
	 @FindBy(how = How.ID, using = "username")
	    private WebElement tf_username;
	 @FindBy(how = How.ID, using = "password")
	    private WebElement tf_password;
	 @FindBy(how = How.XPATH, using = "//*[contains(text(),'Log in')]")
	    private WebElement btn_submit;
	 @FindBy(how=How.ID, using="remember")
	 	private WebElement chkb_remember;

	
	public AuthorLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isDisplayed() {
		return super.isDisplayed() && get_tf_username().isDisplayed();
	}

	@Override
	protected WebElement explicitWaitReturn() {
		// TODO Auto-generated method stub
		return get_tf_username();
	}
	@Override
	public String expectedUrl() {
		// TODO Auto-generated method stub
		String url = "http://localhost/index.php/author-login";
		return  url;
	}
	protected WebElement get_tf_username() {
        return tf_username;
    }
	public WebElement we_tf_password(){
		return tf_password;
	}
	
	
	public void loginWithAdmin(){
		tf_username.sendKeys("admin");
		tf_password.sendKeys("admin");
		btn_submit.submit();				
	}

}

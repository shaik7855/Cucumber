package MTMAutomation.DispatchTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.LoginLocators;

public class LoginObjects extends LoginLocators {
	WebDriver driver;

	public LoginObjects(WebDriver driver)
	{

		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(name=username_name_value)
	@CacheLookup
	WebElement UserName;
	public WebElement username()
	{
		return UserName;
	}

	@FindBy(id=nextbutton_id_value)
	@CacheLookup
	WebElement btnnext;
	public WebElement btnNext() {
	    return btnnext;
	}

    @FindBy(xpath=password_xpath)
	@CacheLookup
	WebElement Password;
	public WebElement password() {
	    return Password;
	}	

    @FindBy(id=signinbutton_id_value)
	@CacheLookup
	WebElement btnsignin;
	public WebElement btnSignIn() {
	    return btnsignin;
	}
	
	@FindBy(id=yesbutton_id_value)
	@CacheLookup
	WebElement btnyes;
	public WebElement btnYes() {
	    return btnyes;
	}
	
	@FindBy(xpath=logo_img)
	@CacheLookup
	WebElement logoimg;
	public WebElement getLogoImg() {
	    return logoimg;
	}
	
}

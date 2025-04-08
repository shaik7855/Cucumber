package MTMAutomation.DispatchTest.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.DispatchLocators;

public class DispatchObjects extends DispatchLocators {
	WebDriver driver;

	public DispatchObjects(WebDriver driver)
	{

		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath=dispatch_tab)
	@CacheLookup
	WebElement dispatchtab;
	public WebElement tabDispatch()
	{
		return dispatchtab;
	}

	@FindBy(xpath=lyft_tab)
	@CacheLookup
	WebElement lyfttab;
	public WebElement tabLyft() {
	    return lyfttab;
	}
	
	@FindBy(xpath=member_id)
	@CacheLookup
	WebElement memberid;
	public WebElement memberID() {
	    return memberid;
	}
	
	@FindBy(xpath=search_btn)
	@CacheLookup
	WebElement searchbtn;
	public WebElement searchBtn() {
	    return searchbtn;
	}

	@FindBy(xpath=clear_btn)
	@CacheLookup
	WebElement clearbtn;
	public WebElement clearBtn() {
	    return clearbtn;
	}
	
	@FindBy(xpath=olos_tab)
	@CacheLookup
	WebElement olostab;
	public WebElement tabOlos() {
	    return olostab;
	}
	
	@FindBy(xpath=close_icon)
	@CacheLookup
	WebElement closeicon;
	public WebElement iconClose() {
	    return closeicon;
	}
	
	@FindBy(xpath=comment_icon)
	@CacheLookup
	WebElement commenticon;
	public WebElement iconComment() {
	    return commenticon;
	}
	
	@FindBy(xpath=remarkclose_icon)
	@CacheLookup
	WebElement remarkcloseicon;
	public WebElement iconRemarkClose() {
	    return remarkcloseicon;
	}
	
	@FindBy(xpath=remarkadd_btn)
	@CacheLookup
	WebElement remarkaddbtn;
	public WebElement btnRemarkAdd() {
	    return remarkaddbtn;
	}
	
	@FindBy(xpath=remark_textarea)
	@CacheLookup
	WebElement remarktextarea;
	public WebElement txtareaRemark() {
	    return remarktextarea;
	}
	
	@FindBy(xpath=signout_btn)
	@CacheLookup
	WebElement signoutbtn;
	public WebElement btnSignOut() {
	    return signoutbtn;
	}
	
	@FindBy(xpath=searcherror_msg)
	@CacheLookup
	WebElement searcherrormsg;
	public WebElement getSearchErrormsg() {
	    return searcherrormsg;
	}
	
	@FindBy(xpath=dispatch_title)
	@CacheLookup
	WebElement dispatchtitle;
	public WebElement titleDispatch() {
	    return dispatchtitle;
	}
	
	@FindBy(xpath=remarks_username)
	@CacheLookup
	WebElement remarksusername;
	public WebElement getRemarkUsername() {
	    return remarksusername;
	}
	
	@FindBy(xpath=remarks_text)
	@CacheLookup
	WebElement remarktext;
	public WebElement getRemarkText() {
	    return remarktext;
	}
}

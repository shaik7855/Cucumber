package MTMAutomation.DispatchTest.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.Locators;


public class HomePageObjects 
{	
	WebDriver driver;
	
	 // Constructor 
	  public HomePageObjects(WebDriver driver) 
	  	{
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    } 

	//-----------clicking on dispatch tab--------------//
	
	@FindBy(xpath = Locators.DISPATCH_TAB)
	@CacheLookup
	private  WebElement dispatchTab;

	public  void clickOnDispatchTab() 
	{
	    dispatchTab.click();
	}
    

}	

package MTMAutomation.DispatchTest.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.HomePageLocators;


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
	
	@FindBy(xpath = HomePageLocators.DISPATCH_TAB)
	@CacheLookup
	private  WebElement dispatchTab;

	public  void clickOnDispatchTab() 
	{
	    dispatchTab.click();
	}
    
	//-----------clicking on Lyft tab--------------//
    
	@FindBy(xpath = HomePageLocators.lYFT_TAB)
	@CacheLookup
	private WebElement lyftTab;

	public void clickOnLyftTab() throws InterruptedException 
	{
		lyftTab.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//icon[@data-icon='close']")).click();
	}
    
	//-----------clicking on OLOS tab--------------//
    
		@FindBy(xpath = HomePageLocators.OLOS_TAB )
		@CacheLookup
		private WebElement olosTab;

		public void clickOnOlosTab() 
		{
			olosTab.click();
		}
}	

	package MTMAutomation.DispatchTest.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MTMAutomation.DispatchTest.Locators.HomePageLocators;
import io.netty.handler.timeout.TimeoutException;

public class HomePageObjects 
{
	WebDriver driver;
	
	//Constructor
    public HomePageObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    
    //-------------------------------Dispatch Tab visibility---------------------------------//

    
    @FindBy(xpath = HomePageLocators.Dispatch_Tab)
    @CacheLookup
	 private WebElement dispatchTab;
    
    public boolean dispatchTabVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try 
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Dispatch']")));
            return driver.findElement(By.xpath("//a[text()='Dispatch']")).isDisplayed();
        } 
        catch (TimeoutException e) 
        {
            return false;
        }
    }
    
    //-------------------------------LYFT Tab visibility---------------------------------//

    
    @FindBy(xpath = HomePageLocators.LYFT_Tab)
    @CacheLookup
	 WebElement lyftTab;
    
    public  boolean lyftTabTabVisibility()
    {
    	return lyftTab.isDisplayed();
    }
    
    
//-------------------------------OLOS Tab visibility---------------------------------//

    
    @FindBy(xpath = HomePageLocators.OLOS_Tab)
    @CacheLookup
	 WebElement olosTab;
    
    public  boolean OLOSTabTabVisibility()
    {
    	return olosTab.isDisplayed();
    }
    
}

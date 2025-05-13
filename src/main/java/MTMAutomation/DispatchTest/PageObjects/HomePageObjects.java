package MTMAutomation.DispatchTest.PageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import MTMAutomation.DispatchTest.Locators.HomePageLocators;
//import io.netty.handler.timeout.TimeoutException;
import org.openqa.selenium.TimeoutException;
import MTMAutomation.DispatchTest.Locators.HomePageLocators;

public class HomePageObjects {
    WebDriver driver;
    WebDriverWait wait;
    Actions action;

    // Constructor
    public HomePageObjects(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize wait
        PageFactory.initElements(driver, this);
        this.action = new Actions(driver);
    }

    //----------- Dispatch Tab --------------//
    @FindBy(xpath = HomePageLocators.DISPATCH_TAB)
    @CacheLookup
    private WebElement dispatchTab;

    public void clickOnDispatchTab() throws InterruptedException 
    {
        wait.until(ExpectedConditions.elementToBeClickable(dispatchTab));
        Thread.sleep(2000);
        action.moveToElement(dispatchTab).click().build().perform();
    }
    
    public boolean dispatchTabVisibility()
    {
        try 
        {
            return wait.until(ExpectedConditions.visibilityOf(dispatchTab)).isDisplayed();
        } 
        catch (TimeoutException e)
        {
            return false;
        }
    }

    //----------- Lyft Tab --------------//
    
    @FindBy(xpath = HomePageLocators.lYFT_TAB)
    @CacheLookup
    private WebElement lyftTab;

    public void clickOnLyftTab() throws InterruptedException 
    {
        wait.until(ExpectedConditions.elementToBeClickable(lyftTab));
        Thread.sleep(2000);
        action.moveToElement(lyftTab).click().build().perform();
        Thread.sleep(2000);
        try 
        {
            WebElement closeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//icon[@data-icon='close']")));
            closeIcon.click();
        } 
        catch (TimeoutException e)
        {
            System.out.println("Close icon not found, continuing...");
        }
    }

    public boolean lyftTabVisibility() 
    {
        try 
        {
            return wait.until(ExpectedConditions.visibilityOf(lyftTab)).isDisplayed();
        } 
        catch (TimeoutException e)
        {
            return false;
        }
    }

    //----------- OLOS Tab --------------//
    @FindBy(xpath = HomePageLocators.OLOS_TAB)	
    @CacheLookup
    private WebElement olosTab;

    public void clickOnOlosTab() throws InterruptedException 
    {
    	 wait.until(ExpectedConditions.elementToBeClickable(olosTab));
         Thread.sleep(2000);
         action.moveToElement(lyftTab).click().build().perform();
         Thread.sleep(2000);
    }

    public boolean olosTabVisibility() 
    {
        try 
        {
            return wait.until(ExpectedConditions.visibilityOf(olosTab)).isDisplayed();
        }
        catch (TimeoutException e) 
        {
            return false;
        }
    }
}

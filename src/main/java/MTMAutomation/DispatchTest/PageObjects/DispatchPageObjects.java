package MTMAutomation.DispatchTest.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.Locators;



public class DispatchPageObjects 
{
	WebDriver driver;

    // Constructor
    public DispatchPageObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
    // Method to click on 0-6 filter 
    
    @FindBy(xpath = Locators.TimeFrameFilter_0_6)
    @CacheLookup
    WebElement timeFrameFilter_0_6;

    public void clickOnTimeFrameFilter_0_6()
    {
        timeFrameFilter_0_6.click();
    }
    
	// Method to click on 6-12 filter 
    
    @FindBy(xpath = Locators.TimeFrameFilter_6_12 )
    @CacheLookup
    WebElement timeFrameFilter_6_12;

    public void clickOnTimeFrameFilter_6_12()
    {
        timeFrameFilter_6_12.click();
    }
	
    
    // Method to get count of counts
    
    @FindBy(xpath = "//table//tbody")
    @CacheLookup
    List<WebElement> tableRows;

    public int getRecordCount() 
    {
        return tableRows.size();
    }
}

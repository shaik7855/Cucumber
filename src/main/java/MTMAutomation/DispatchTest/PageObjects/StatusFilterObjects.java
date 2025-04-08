package MTMAutomation.DispatchTest.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.StatusFilterLocator;

public class StatusFilterObjects 
{
	WebDriver driver;

    // Constructor
    public StatusFilterObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    
    //---------Method to select Parked  filter ------------//
    
    @FindBy(xpath = StatusFilterLocator.Parked_Pending)
    WebElement parkedOrPending;
    public void selectParkedOrPending() 
    {
    	parkedOrPending.click();
    }
    
    //---------Method to select Reassigned filter ------------//
    
    @FindBy(xpath = StatusFilterLocator.Reassigned )
    WebElement reassigned;
    public void selectReassigned() 
    {
    	reassigned.click();
    }
    
    //---------Method to select Trip Bidding filter ------------//
    
    @FindBy(xpath = StatusFilterLocator.Trip_Bidding )
    WebElement tripBidding;
    public void selectTripBidding() 
    {
    	tripBidding.click();
    }
    
    //---------Method to select ALL filter ------------//
    
    @FindBy(xpath = StatusFilterLocator.ALL )
    WebElement aLL;
    public void selectAll() 
    {
    	aLL.click();
    }
    
    
    @FindBy(xpath = "(//table//tbody//tr/td)[12]")
    @CacheLookup
    List<WebElement> rowsCount;
    
    public boolean isStatusFilterApplied(String expectedStatus) 
    {
        // Check if table is empty
        if (rowsCount.isEmpty()) 
        { 
            System.out.println("No data available after applying the filter.");
            return false;
        }

        // Iterate through each row 
        for (WebElement row : rowsCount)
        {
            // Getting the text content 
            String text = row.getText().trim();
            
            // Validate if the row contains the expected status
            if (!text.equalsIgnoreCase(expectedStatus))
            {
                System.out.println("Mismatch found");
                return false; 
            }
        }

        System.out.println("Filter applied successfully: All rows match the status '" + expectedStatus + "'");
        return true; 
    }

    
    
}

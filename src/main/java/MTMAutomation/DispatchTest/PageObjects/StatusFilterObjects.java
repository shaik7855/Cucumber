package MTMAutomation.DispatchTest.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
    
    //-------- To check if the "Pending" status filter is applied -------- //

    @FindBy(xpath = "(//table//tbody//tr/td)[12]")
    @CacheLookup
    List<WebElement> rowsCount;


    public boolean isPendingStatusFilterApplied() 
    {
        // Check if table is empty
        if (rowsCount.isEmpty()) 
        { 
            System.out.println("No data available after applying the Parked/Pending status filter.");
            return true; 
        }
        else
        {
        // Iterate through each row 
        for (WebElement row : rowsCount)
        {
            // Getting the text content 
            String text = row.getText().trim();

            // Checking if the row contains "Pending or parked"
            if (!text.equalsIgnoreCase("Pending") && !text.equalsIgnoreCase("Parked")) 
            {
               
                System.out.println("Mismatch found");
                return false; 
            }
        }
        System.out.println("Filter applied successfully: All rows have 'Pending' or 'Parked' status.");
        return true;
    	}
    }

    
    //-------- To check if the "Reassigned" status filter is applied -------- //

    @FindBy(xpath = "(//table//tbody//tr/td)[12]")
    @CacheLookup
    List<WebElement> rowsCount1;


    public boolean isReassignedStatusFilterApplied() 
    {
        // Check if table is empty
        if (rowsCount1.isEmpty()) 
        { 
            System.out.println("No data available after applying the Reassigned status filter.");
            return true; 
        }
        
        else
        {	
        // Iterate through each row 
        for (WebElement row : rowsCount1)
        {
            // Getting the text content 
            String text = row.getText().trim();

            // Checking if the row contains "Reassigned"
            if (!text.equalsIgnoreCase("Reassigned")) 
            {
               
                System.out.println("Mismatch found");
                return false; 
            }
        }
        System.out.println("Filter applied successfully: All rows have 'Reassigned' status.");
        return true;
    	}
    }
    
    
    //-------- To check if the "Trip Bidding" status filter is applied -------- //

    @FindBy(xpath = "(//table//tbody//tr/td)[12]")
    @CacheLookup
    List<WebElement> rowsCount2;


    public boolean isTripBiddingStatusFilterApplied() 
    {
        // Check if table is empty
        if (rowsCount2.isEmpty()) 
        { 
            System.out.println("No data available after applying the Trip Bidding status filter.");
            return true; 
        }
        
        else
        {	
        // Iterate through each row 
        for (WebElement row : rowsCount2)
        {
            // Getting the text content 
            String text = row.getText().trim();

            // Checking if the row contains "Reassigned"
            if (!text.equalsIgnoreCase("Trip Bidding")) 
            {
               
                System.out.println("Mismatch found");
                return false; 
            }
        }
        System.out.println("Filter applied successfully: All rows have 'Reassigned' status.");
        return true;
    	}
    }
    
    
    //-------- To check if the "ALL" status filter is applied -------- //

    @FindBy(xpath = "(//table//tbody//tr/td)[12]")
    @CacheLookup
    List<WebElement> rowsCount3;


    public boolean isALLStatusFilterApplied() 
    {
        // Check if table is empty
        if (rowsCount3.isEmpty()) 
        { 
            System.out.println("No data available after applying the Parked/Pending status filter.");
            return true; 
        }
        else
        {
        // Iterate through each row 
        for (WebElement row : rowsCount3)
        {
            // Getting the text content 
            String text = row.getText().trim();

            // Checking if the row contains "Pending or parked or Trip Bidding or Reassigned"
            if (!text.equalsIgnoreCase("Pending") && !text.equalsIgnoreCase("Parked") && !text.equalsIgnoreCase("Trip Bidding") && !text.equalsIgnoreCase("Reassigned")) 
            {
               
                System.out.println("Mismatch found");
                return false; 
            }
        }
        System.out.println("Filter applied successfully: All rows have 'Pending' or 'Parked' or 'Trip Bidding status' or 'Reassigned'");
        return true;
    	}
    }
    
    

}

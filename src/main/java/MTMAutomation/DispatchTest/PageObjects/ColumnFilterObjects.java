package MTMAutomation.DispatchTest.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.ColumnFilterLocators;

public class ColumnFilterObjects
{
	WebDriver driver;

    // Constructor
    public ColumnFilterObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    
    //---------Method to insert the plan name in coulmn filter------------//
    
    @FindBy(xpath = ColumnFilterLocators.Plan_Name_Column)
    @CacheLookup
    WebElement planNameColumn;
    
    public void insertingPlanName() 
    {
    	planNameColumn.sendKeys("Molina IA Medicaid Waiver");
    }
    
    
    //---------Method to insert the Member in coulmn filter------------//
    
    @FindBy(xpath = ColumnFilterLocators.Member_Column)
    @CacheLookup
    WebElement memberColumn;
    
    public void insertingMember() 
    {
    	memberColumn.sendKeys("JONTRAY BLAIR");
    }
    
    
    //---------Method to insert the MemberID in coulmn filter------------//
    
    @FindBy(xpath = ColumnFilterLocators.MemberID_Column)
    @CacheLookup
    WebElement memberIDColumn;
    
    public void insertingMemberID() 
    {
    	memberIDColumn.sendKeys("2308284D");
    }
    
    
    //---------Method to insert the Trip in coulmn filter------------//
    
    @FindBy(xpath = ColumnFilterLocators.Trip_Column)
    @CacheLookup
    WebElement tripColumn;
    
    public void insertingTrip() 
    {
    	tripColumn.sendKeys("56747261");
    }
    
    
    //---------Method to Check column filter applied ------------//
    
    @FindBy(xpath = "//table//tbody//tr")
    List <WebElement> tableRows;
    
    public boolean isFilterApplied(String expectedValue) 
    {
        // Check if table is empty
        if (tableRows.isEmpty()) 
        {
            System.out.println("No data available after applying filter: " + expectedValue);
            return false; // If no data is found, return false 
        }
		
        // Iterate through each row in the results table
        for (WebElement row : tableRows) 
        {
        	// Get the text of the entire row and remove extra spaces
            String rowText = row.getText().trim();
            // Convert both row text and expected value to lowercase for case-insensitive comparison
            if (!rowText.toLowerCase().contains(expectedValue.toLowerCase())) 
            {
                System.out.println("Mismatch found! Expected: " + expectedValue + ", but got: " + rowText);
                return false; 
            }
        }
        	return true;	
    }
    
}

package MTMAutomation.DispatchTest.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
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
	  
	  
	  
	  
   // Method to get all column headers dynamically
	  
   /* Approach 1
    * public List<String> getAllColumnHeaders() 
    {
        List<WebElement> columnHeaders = driver.findElements(By.xpath(Locators.DISPATCH_TABLE_HEADERS_XPATH));

        return columnHeaders.stream()
                .map(header -> header.getText().trim()) // Trim spaces
                .filter(text -> !text.isEmpty()) // Remove empty headers
                .collect(Collectors.toList()); // Convert to list
    }*/
    
    // Method to get all column headers
    @FindBy(xpath = "//th[@class='sorting ']")
    List<WebElement> columnHeaders;
    
    public List<String> getAllColumnHeaders() 
    {
        return columnHeaders.stream()
                .map(header -> header.getText().trim()) // Trim spaces
                .filter(text -> !text.isEmpty()) // Remove empty headers (if any)
                .collect(Collectors.toList()); // Convert to list
    }
    
    
    // Method to see time left column is visible
    
    @FindBy(xpath = Locators.Time_Left_Column)
    @CacheLookup
    WebElement timeLeftColumn;

    public boolean isTimeLeftColumnDisplayed()
    {
        return timeLeftColumn.isDisplayed();
    }
    
    
}

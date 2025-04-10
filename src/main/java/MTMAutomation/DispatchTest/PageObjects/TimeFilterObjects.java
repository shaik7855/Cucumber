package MTMAutomation.DispatchTest.PageObjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.Locators;
import MTMAutomation.DispatchTest.Locators.TimeFilterLocators;



public class TimeFilterObjects 
{
	WebDriver driver;

    // Constructor
    public TimeFilterObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
    // Method to click on 0-6 filter 
    
    @FindBy(xpath = TimeFilterLocators.TimeFrameFilter_0_6)
    @CacheLookup
    WebElement timeFrameFilter_0_6;

    public void clickOnTimeFrameFilter_0_6()
    {
        timeFrameFilter_0_6.click();
    }
    
	// Method to click on 6-12 filter 
    
    @FindBy(xpath = TimeFilterLocators.TimeFrameFilter_6_12 )
    @CacheLookup
    WebElement timeFrameFilter_6_12;

    public void clickOnTimeFrameFilter_6_12()
    {
        timeFrameFilter_6_12.click();
    }
	
    // Method to click on 12-24 filter 
    
    @FindBy(xpath = TimeFilterLocators.TimeFrameFilter_12_24 )
    @CacheLookup
    WebElement timeFrameFilter_12_24;

    public void clickOnTimeFrameFilter_12_24()
    {
        timeFrameFilter_12_24.click();
    }
    
    // Method to click on Date Range filter 
    
    @FindBy(xpath = TimeFilterLocators.Date_Range )
    @CacheLookup
    WebElement dateRange;

    public void clickOnDateRangeFilter()
    {
    	dateRange.click();
    }	
    
    
    // Method to get count of counts
    
    @FindBy(xpath = "//table//tbody//tr")
    @CacheLookup
    List<WebElement> tableRows;

    public int getRecordCount() 
    {
        return tableRows.size();
    }
    

    
    // Method to check if we are getting correct records for 0-6 time filter 
    
    @FindBy (xpath = "//table//tbody//tr/td/div[@class='ember-view']")
    @CacheLookup
    List<WebElement> rowsCount;
    //To check the filter is applied 
    public boolean  isTimeFrameFilterApplied_0_6 ()
    {
    	// Check if table is empty
        if (rowsCount.isEmpty())
        { 
            System.out.println("No data available after applying 0-6 hours filter.");
            return true; 
        }
        else 
        {
    	//for loop for the iterate through each row 
    	for (WebElement row : rowsCount)
    	{
    		// getting the text content 
    		String text = row.getText().trim();
    			//checking that text contains the "hours"
    			if (text.toLowerCase().contains("hours")) 
    				{
    				//spliting the text and extracting the numeric value 
    					int hours = Integer.parseInt(text.split(" ")[0]);
    						//validating the extratced hours falling in range of (0-6)
    						if (hours < 0 && hours > 6 )
    						{
    							return false; // Return false if any row is out of range
    						}
    				}
    	}
        }
		return true; // Return true if all rows meet the condition
    }
    
    
 // Method to check if we are getting correct records for 6-12 time filter 
    
    @FindBy (xpath = "//table//tbody//tr/td/div[@class='ember-view']")
    @CacheLookup
    List<WebElement> rowsCount1;
    //To check the filter is applied 
    public boolean  isTimeFrameFilterApplied_6_12 ()
    {
    	// Check if table is empty
        if (rowsCount1.isEmpty())
        { 
            System.out.println("No data available after applying 6-12 hours filter.");
            return true; 
        }
        else 
        {
    	//for loop for the iterate through each row 
    	for (WebElement row : rowsCount1)
    		{
    		// getting the text content 
    		String text = row.getText().trim();
    			//checking that text contains the "hours"
    			if (text.toLowerCase().contains("hours")) 
    				{
    				//spliting the text and extracting the numeric value 
    					int hours = Integer.parseInt(text.split(" ")[0]);
    						//validating the extratced hours falling in range of (6-12)
    						if (hours < 6 && hours > 12 )
    						{
    							return false; // Return false if any row is out of range
    						}
    				}
    		}
        }
		return true; // Return true if all rows meet the condition
    }
    
    // Method to check if we are getting correct records for 6-12 time filter 
    
    @FindBy (xpath = "//table//tbody//tr/td/div[@class='ember-view']")
    @CacheLookup
    List<WebElement> rowsCount2;
    //To check the filter is applied 
    public boolean  isTimeFrameFilterApplied_12_24 ()
    {
    	// Check if table is empty
        if (rowsCount2.isEmpty())
        { 
            System.out.println("No data available after applying 12-24 hours filter.");
            return true; 
        }
        else 
        {	
    	//for loop for the iterate through each row 
    	for (WebElement row : rowsCount2)
    	{
    		// getting the text content 
    		String text = row.getText().trim();
    			//checking that text contains the "hours"
    			if (text.toLowerCase().contains("hours")) 
    				{
    				//spliting the text and extracting the numeric value 
    					int hours = Integer.parseInt(text.split(" ")[0]);
    						//validating the extratced hours falling in range of (12-24)
    						if (hours < 12 && hours > 24 )
    						{
    							return false; // Return false if any row is out of range
    						}
    				}
    	}
        }
		return true; // Return true if all rows meet the condition
    }
  

//--------------------------------------------------------------------------------------------------------------------//
    
    public boolean validateAppointmentDateIsInRange() {
        // Extract Start Date, End Date, and Appointment Date from the webpage
        String startDate = driver.findElement(By.xpath("(//input[@class='md-input ember-view'])[1]")).getDomProperty("value");
        String endDate = driver.findElement(By.xpath("//md-input-container[label[text()='End Date']]/input")).getDomProperty("value");
        String apptDate = driver.findElement(By.xpath("(//tbody/tr/td)[33]")).getText();
        
        
        try {
        	
        	// Extract only the date part from the appointment date 
            String apptDateOnly = apptDate.split(" ")[0];  
        	
            // Split the dates into MM, DD, YYYY parts
            String[] startDateParts = startDate.split("/");
            String[] endDateParts = endDate.split("/");
            String[] apptDateParts = apptDateOnly.split("/");

            // Convert to Integer for comparison
            int startMonth = Integer.parseInt(startDateParts[0]);
            int startDay = Integer.parseInt(startDateParts[1]);
            int startYear = Integer.parseInt(startDateParts[2]);

            int endMonth = Integer.parseInt(endDateParts[0]);
            int endDay = Integer.parseInt(endDateParts[1]);
            int endYear = Integer.parseInt(endDateParts[2]);

            int apptMonth = Integer.parseInt(apptDateParts[0]);
            int apptDay = Integer.parseInt(apptDateParts[1]);
            int apptYear = Integer.parseInt(apptDateParts[2]);

            // Check if appointment date is within the range
            boolean isApptDateValid = (apptYear > startYear || (apptYear == startYear && apptMonth > startMonth) || 
                                       (apptYear == startYear && apptMonth == startMonth && apptDay >= startDay))
                                   && (apptYear < endYear || (apptYear == endYear && apptMonth < endMonth) || 
                                       (apptYear == endYear && apptMonth == endMonth && apptDay <= endDay));

            if (isApptDateValid) {
                System.out.println("Appointment date is within the valid range.");
                return true;
            } else {
                System.out.println("Appointment date is NOT within the valid range.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error during date comparison: " + e.getMessage());
            return false;
        }
    }

  // Method to Click on Start date
    
    @FindBy(xpath = "(//input[@class='md-input ember-view'])[1]")
    @CacheLookup
    WebElement clickOnStartDate;

    public void ClickOnStartDate()
    {
    	clickOnStartDate.click();
    }
    
    //--------------------------------------------------------------------------------------------------------------------//
    
    // Method to Click on 1 st of april
      
      @FindBy(xpath = "/html/body/div[4]/div/table/tbody/tr[1]/td[2]/button")
      @CacheLookup
      WebElement clickOn1stApril;

      public void ClickOn1stApril()
      {
    	  clickOn1stApril.click();
      }
}

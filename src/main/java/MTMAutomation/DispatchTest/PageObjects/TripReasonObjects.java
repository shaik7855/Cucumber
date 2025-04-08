package MTMAutomation.DispatchTest.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.TripReasonsLocator;

public class TripReasonObjects 
{
	WebDriver driver;

    // Constructor
    public TripReasonObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    //---------Method to select Behavioral Health  filter ------------//
    
    @FindBy(xpath = TripReasonsLocator.Behavioral_Health)
    WebElement behavioralHealth;
    public void selectBehavioralHealth() 
    {
    	behavioralHealth.click();
    }
    
    //---------Method to select Chemotherapy  filter ------------//
    
    @FindBy(xpath = TripReasonsLocator.Chemotherapy)
    WebElement chemotherapy;
    public void selectChemotherapy() 
    {
    	chemotherapy.click();
    }
    
    //---------Method to select Dialysis  filter ------------//
    
    @FindBy(xpath = TripReasonsLocator.Dialysis)
    WebElement dialysis;
    public void selectDialysis() 
    {
    	dialysis.click();
    }
    
    
    //---------Method to select Hospital-Discharge  filter ------------//
    
    @FindBy(xpath = TripReasonsLocator.Hospital_Discharge)
    WebElement hospitalDischarge;
    public void selectHospitalDischarge() 
    {
    	hospitalDischarge.click();
    }
    
    //---------Method to select Methadone  filter ------------//
    
    @FindBy(xpath = TripReasonsLocator.Methadone)
    WebElement methadone;
    public void selectMethadone() 
    {
    	methadone.click();
    }
    
    //------------- method that verifies whether the Trip Reason ---------------//
    
    @FindBy(xpath = "//table//tbody//tr/td[16]") 
    @CacheLookup
    List<WebElement> tripReasonRows;

    public boolean isTripReasonFilterApplied(String expectedTripReason)  
    {
        // Check if table is empty
        if (tripReasonRows.isEmpty()) 
        { 
            System.out.println("No data available after applying the Trip Reason filter.");
            return true; 
        }
        
        // Iterate through each row
        for (WebElement row : tripReasonRows)
        {
            String text = row.getText().trim();

            // Normalize text by replacing hyphens with spaces
            String normalizedText = text.replace("-", " ").trim();
            String normalizedExpected = expectedTripReason.replace("-", " ").trim();

            // Check if the row contains the expected trip reason (case-insensitive)
            if (!normalizedText.equalsIgnoreCase(normalizedExpected)) 
            {
                System.out.println("Mismatch found: Expected '" + expectedTripReason + "', but found '" + text + "'.");
                return false; 
            }
        }
        System.out.println("Filter applied successfully: All rows have the trip reason '" + expectedTripReason + "'.");
        return true;
    }

    
}


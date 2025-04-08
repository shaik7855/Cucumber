package MTMAutomation.DispatchTest.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MTMAutomation.DispatchTest.Locators.HomePageLocators;
import MTMAutomation.DispatchTest.Locators.TripReasonsLocators;

public class TripReasonsObject
{
	WebDriver driver;
	
	 // Constructor 
	  public TripReasonsObject(WebDriver driver) 
	  	{
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    } 

	//-----------choosing the behavioralHealth  --------------//
	
	@FindBy(xpath = TripReasonsLocators.Behavioral_Health)
	@CacheLookup
	private  WebElement behavioralHealth;

	public  void clickOnBehavioralHealth() 
	{
		behavioralHealth.click();
	}
	
	
	//-----------choosing the Chemotherapy  --------------//

	 @FindBy(xpath = TripReasonsLocators.Chemotherapy)
	 @CacheLookup
	 private WebElement chemotherapy;
	 
	 public void clickOnChemotherapy() 
	 {
	     chemotherapy.click();
	 }
	 
	//-----------choosing the dialysis  --------------//

	 @FindBy(xpath = TripReasonsLocators.Dialysis)
	 @CacheLookup
	 private WebElement dialysis;
		 
	 public void clickOnDialysis() 
	 {
		 dialysis.click();
	 }	 
	 
	//-----------choosing the hospitalDischarge  --------------//
		
	@FindBy(xpath = TripReasonsLocators.Hospital_Discharge)
	@CacheLookup
	private  WebElement hospitalDischarge;

	public  void clickOnHospitalDischarge() 
	{
		hospitalDischarge.click();
	}
	
	//-----------choosing the hospitalDischarge  --------------//
	
	 @FindBy(xpath = TripReasonsLocators.Methadone)
	 @CacheLookup
	 private WebElement methadone;

	 public void clickOnMethadone()
	 {
		 methadone.click();
	 }
	 
	 //-----------   Click on Clear All button     -------------//
	 @FindBy(xpath = TripReasonsLocators.Clear_All_Button)
	 @CacheLookup
	 private WebElement clearAllButton;

	 public void clickOnclearAllButton()
	 {
		 clearAllButton.click();
	 }
	 
	 //-----------   getting selected filters     -------------//
	 @FindBy(xpath = "//dd/md-chip") 
	 @CacheLookup
	 private List<WebElement> selectedFilters;
	 public List<WebElement> getSelectedFilters() 
	 {
		  return selectedFilters;
	 }
	 
	 
	
	 // Verify if Checkbox is Selected 
	 public boolean isCheckboxSelected(WebElement checkbox) 
	 {
	     return checkbox.isSelected();
	 }

	 // Getter Methods for WebElements
	 public WebElement getChemotherapy() 
	 {
	     return chemotherapy;
	 }

	 public WebElement getDialysis()
	 {
	     return dialysis;
	 }

	 public WebElement getHospitalDischarge() 
	 {
	        return hospitalDischarge;
	 }

	 public WebElement getMethadone() 
	 {
	        return methadone;
	 }

	 public WebElement getBehavioralHealth() 
	 {
	        return behavioralHealth;
	 }
	 
	 
	// Method to get the count of records
	 public int getRecordCount() 
	 {
	     List<WebElement> records = driver.findElements(By.xpath("//tbody//tr"));
	     int count = records.size();
	     
	     if (count == 0) 
	     {
	         System.out.println("No trips found. Try changing Time or Status filters.");
	     }
	     
	     return count;
	 }
	 
}

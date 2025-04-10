package MTMAutomation.DispatchTest.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import MTMAutomation.DispatchTest.Locators.DispatchLocators;

public class DispatchObjects extends DispatchLocators {
	WebDriver driver;

	public DispatchObjects(WebDriver driver)
	{

		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath=dispatch_tab)
	@CacheLookup
	WebElement dispatchtab;
	public WebElement tabDispatch()
	{
		return dispatchtab;
	}

	@FindBy(xpath=lyft_tab)
	@CacheLookup
	WebElement lyfttab;
	public WebElement tabLyft() {
	    return lyfttab;
	}
	
	@FindBy(xpath=member_id)
	@CacheLookup
	WebElement memberid;
	public WebElement memberID() {
	    return memberid;
	}
	
	@FindBy(xpath=search_btn)
	@CacheLookup
	WebElement searchbtn;
	public WebElement searchBtn() {
	    return searchbtn;
	}

	@FindBy(xpath=clear_btn)
	@CacheLookup
	WebElement clearbtn;
	public WebElement clearBtn() {
	    return clearbtn;
	}
	
	@FindBy(xpath=olos_tab)
	@CacheLookup
	WebElement olostab;
	public WebElement tabOlos() {
	    return olostab;
	}
	
	@FindBy(xpath=close_icon)
	@CacheLookup
	WebElement closeicon;
	public WebElement iconClose() {
	    return closeicon;
	}
	
	@FindBy(xpath=comment_icon)
	@CacheLookup
	WebElement commenticon;
	public WebElement iconComment() {
	    return commenticon;
	}
	
	@FindBy(xpath=remarkclose_icon)
	@CacheLookup
	WebElement remarkcloseicon;
	public WebElement iconRemarkClose() {
	    return remarkcloseicon;
	}
	
	@FindBy(xpath=remarkadd_btn)
	@CacheLookup
	WebElement remarkaddbtn;
	public WebElement btnRemarkAdd() {
	    return remarkaddbtn;
	}
	
	@FindBy(xpath=remark_textarea)
	@CacheLookup
	WebElement remarktextarea;
	public WebElement txtareaRemark() {
	    return remarktextarea;
	}
	
	@FindBy(xpath=signout_btn)
	@CacheLookup
	WebElement signoutbtn;
	public WebElement btnSignOut() {
	    return signoutbtn;
	}
	
	@FindBy(xpath=searcherror_msg)
	@CacheLookup
	WebElement searcherrormsg;
	public WebElement getSearchErrormsg() {
	    return searcherrormsg;
	}
	
	@FindBy(xpath=dispatch_title)
	@CacheLookup
	WebElement dispatchtitle;
	public WebElement titleDispatch() {
	    return dispatchtitle;
	}
	
	@FindBy(xpath=remarks_username)
	@CacheLookup
	WebElement remarksusername;
	public WebElement getRemarkUsername() {
	    return remarksusername;
	}
	
	@FindBy(xpath=remarks_text)
	@CacheLookup
	WebElement remarktext;
	public WebElement getRemarkText() {
	    return remarktext;
	}
	
	@FindBy(xpath=firstname_text)
	@CacheLookup
	WebElement firstnametext;
	public WebElement setFirstName() {
	    return firstnametext;
	}
	
	@FindBy(xpath=lastname_text)
	@CacheLookup
	WebElement lastnametext;
	public WebElement setLastName() {
	    return lastnametext;
	}
	
	@FindBy(xpath=dob_text)
	@CacheLookup
	WebElement dobtext;
	public WebElement setDOB() {
	    return dobtext;
	}
	
	@FindBy(xpath=phone_text)
	@CacheLookup
	WebElement phonetext;
	public WebElement setPhone() {
	    return phonetext;
	}

	//================================================================================================================//
	
	
	@FindBy(xpath = "//td[contains(text(), 'U1477505001')]") 
	List<WebElement> tableRows;

	public boolean recordresult(String memberidLyft)
	{
	    // Check if table is empty
	    if (tableRows.isEmpty())
	    {
	        System.out.println("No data available after applying filter for member ID: " + memberidLyft);
	        return true; 
	    }

	    // Iterate through each row in the results table
	    for (WebElement row : tableRows)
	    {
	        // Get the entire row text and check if memberidLyft is present in the row's text
	        String rowText = row.getText().trim();
	        
	        // Check if the member ID is part of the row's text
	        if (rowText.contains(memberidLyft))
	        {
	            // If the memberId is found, return true
	            return true;
	        }
	    }

	    // If no match found after checking all rows
	    System.out.println("Member ID " + memberidLyft + " not found in the table.");
	    return false;
	}


	//================================================================================================================//

	public void calendardata() 
	{
	    // Loop until the year is 2023 and the month is August
	    boolean isYear2023AndMonthAugust = false;

	    while (!isYear2023AndMonthAugust) 
	    {
	        try {
	            // Get the current year and month displayed in the calendar
	            WebElement yearElement = driver.findElement(By.xpath("//div[@class='pika-label'][2]//select[@class='pika-select pika-select-year']"));
	            WebElement monthElement = driver.findElement(By.xpath("//div[@class='pika-label'][1]//select[@class='pika-select pika-select-month']"));

	            // Use Select to interact with the dropdowns
	            Select yearSelect = new Select(yearElement);
	            Select monthSelect = new Select(monthElement);

	            // Get the selected year and month as strings
	            String selectedYear = yearSelect.getFirstSelectedOption().getText();
	            String selectedMonth = monthSelect.getFirstSelectedOption().getText();

	            // Check if the current year is 2023 and the current month is August
	            if (selectedYear.equals("2023") && selectedMonth.equals("August"))
	            {
	                isYear2023AndMonthAugust = true;

	                // Now click the first date of August 2023
	                WebElement firstDate = driver.findElement(By.xpath("/html/body/div[4]/div/table/tbody/tr[1]/td[2]/button"));
	                
	                // Click the first date
	                firstDate.click();
	                
	            } 
	            else 
	            {
	                // Locate the "Previous Month" button and click it
	                WebElement previousMonthButton = driver.findElement(By.xpath("//button[@class='pika-prev']"));

	                // Click the button to go to the previous month
	                previousMonthButton.click();

	                // Wait for the calendar to update, can add an explicit wait if necessary
	                try {
	                    Thread.sleep(500);  // Use WebDriverWait here in a real scenario
	                } catch (InterruptedException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        } catch (StaleElementReferenceException e) 
	        {
	            // Catch the StaleElementReferenceException and continue looping
	            System.out.println("Element reference became stale. Retrying...");
	        }
	    }
	}



    
	//================================================================================================================//
    
    @FindBy(xpath = "(//md-input-container[@class='ember-view md-input-has-value'])[1]" )
    WebElement startDate;
    public void clickOnStartDate() 
    {
    	startDate.click();
    }
    
}

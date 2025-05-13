package MTMAutomation.DispatchTest;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import MTMAutomation.DispatchTest.Locators.Locators;
import MTMAutomation.DispatchTest.PageObjects.HomePageObjects;
import MTMAutomation.DispatchTest.PageObjects.LoginObjects;
import MTMAutomation.DispatchTest.PageObjects.StatusFilterObjects;
import MTMAutomation.DispatchTest.PageObjects.TimeFilterObjects;
import MTMAutomation.DispatchTest.PageObjects.TripReasonObjects;
import MTMAutomation.DispatchTest.PageObjects.TripReasonsObject;
import MTMAutomation.DispatchTest.PageObjects.ColumnFilterObjects;
import MTMAutomation.DispatchTest.PageObjects.DispatchObjects;
import MTMAutomation.DispatchTest.PageObjects.DispatchPageObjects;
import MTMAutomation.DispatchTest.PageObjects.HomePageObjects;
import MTMAutomation.DispatchTest.Utilities.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Unit test for simple App.
 */

public class MTMDispatchTest extends Base
{
	LoginObjects lo;
	DispatchObjects dp;
	WebDriverWait wait;
	Actions action;
	HomePageObjects homePageObjects;
	 DispatchPageObjects dispatchPageObjects;
	private static final Logger logger = LogManager.getLogger(MTMDispatchTest.class);;
	
	@BeforeMethod(alwaysRun=true)
    public void setup() throws InterruptedException, IOException
    {
    	lo = new LoginObjects(driver);
    	dp = new DispatchObjects(driver);
    	homePageObjects=new HomePageObjects(driver);
    	dispatchPageObjects = new DispatchPageObjects(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));  
        action = new Actions(driver);
        driver.navigate().to(baseURL);
        verifyUserLogin();		
    }
public void verifyUserLogin() throws IOException, InterruptedException
	{
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(lo.username()));
			Thread.sleep(3000);
			lo.username().sendKeys(username);
			Thread.sleep(3000);
			logger.info("Entered Username");
		}
		catch(StaleElementReferenceException e)
		{
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("loginfmt"))));
			driver.findElement(By.name("loginfmt")).sendKeys(username);
			Thread.sleep(3000);
			logger.info("Entered Username");
		}
		wait.until(ExpectedConditions.elementToBeClickable(lo.btnNext()));
		Thread.sleep(3000);
		action.moveToElement(lo.btnNext()).click().build().perform();
		Thread.sleep(3000);
		logger.info("Clicked on Next");
			try 
			{
				wait.until(ExpectedConditions.elementToBeClickable(lo.password()));
				Thread.sleep(3000);
				lo.password().sendKeys(password);
				Thread.sleep(3000);
				logger.info("Entered Password");
			}
		catch(StaleElementReferenceException e)
			{
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("passwd"))));
				Thread.sleep(3000);
				driver.findElement(By.name("passwd")).sendKeys(password);
				logger.info("Entered Password");
			}
				wait.until(ExpectedConditions.elementToBeClickable(lo.btnSignIn()));
				Thread.sleep(3000);
				action.moveToElement(lo.btnSignIn()).click().build().perform();
				Thread.sleep(3000);
				logger.info("Clicked on Sign in button");
				
				wait.until(ExpectedConditions.elementToBeClickable(lo.btnYes()));
				Thread.sleep(3000);
				action.moveToElement(lo.btnYes()).click().build().perform();
				Thread.sleep(3000);
				logger.info("Clicked on yes button");
				logger.info("Application is successfully opened");	
    
	}
	
	
	@Test(groups = { "regression" })
	public void ClearAllButtonFunctionality() throws InterruptedException
	{
		//---------------- TC 17 ----------------//

		// Initialize Page Objects
		HomePageObjects homePageObjects = new HomePageObjects(driver);
		TripReasonsObject tripReasonsObject = new TripReasonsObject(driver);

		//  Navigate to the Dispatch tab
		homePageObjects.clickOnDispatchTab();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL, "Dispatch tab URL is incorrect!");
		logger.info("Successfully navigated to Dispatch tab");
		System.out.println("Successfully navigated to Dispatch tab");

		Thread.sleep(3000); 

		// Get Initial Record Count
	    int initialRecordCount = tripReasonsObject.getRecordCount();
	    System.out.println("Initial record count: " + initialRecordCount);

		// Select Behavioral Health checkbox and verify
		tripReasonsObject.clickOnBehavioralHealth();
		System.out.println("Selected Behavioral Health");
		int countAfterBehavioralHealth = tripReasonsObject.getRecordCount();
	    System.out.println("Record count after selecting Behavioral Health: " + countAfterBehavioralHealth);
		Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getBehavioralHealth()), "Behavioral Health checkbox is NOT selected!");
		logger.info("Behavioral Health checkbox selected successfully.");
		System.out.println("Behavioral Health checkbox selected successfully.");

		// Select Chemotherapy checkbox and verify
		tripReasonsObject.clickOnChemotherapy();
		System.out.println("Selected Chemotherapy");
		int countAfterChemotherapy = tripReasonsObject.getRecordCount();
	    System.out.println("Record count after selecting Chemotherapy: " + countAfterChemotherapy);
		Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getChemotherapy()), "Chemotherapy checkbox is NOT selected!");
		logger.info("Chemotherapy checkbox selected successfully.");
		System.out.println("Chemotherapy checkbox selected successfully.");

		// Select Dialysis checkbox and verify
		tripReasonsObject.clickOnDialysis();
		System.out.println("Selected Dialysis");
		int countAfterDialysis = tripReasonsObject.getRecordCount();
	    System.out.println("Record count after selecting Dialysis: " + countAfterDialysis);
		Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getDialysis()), "Dialysis checkbox is NOT selected!");
		logger.info("Dialysis checkbox selected successfully.");
		System.out.println("Dialysis checkbox selected successfully.");

		// Select Hospital Discharge checkbox and verify
		tripReasonsObject.clickOnHospitalDischarge();
		System.out.println("Selected Hospital Discharge");
		int countAfterHospitalDischarge = tripReasonsObject.getRecordCount();
	    System.out.println("Record count after selecting Hospital Discharge: " + countAfterHospitalDischarge);
		Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getHospitalDischarge()), "Hospital Discharge checkbox is NOT selected!");
		logger.info("Hospital Discharge checkbox selected successfully.");
		System.out.println("Hospital Discharge checkbox selected successfully.");

		// Select Methadone checkbox and verify
		tripReasonsObject.clickOnMethadone();
		System.out.println("Selected Methadone");
		int countAfterMethadone = tripReasonsObject.getRecordCount();
	    System.out.println("Record count after selecting Methadone: " + countAfterMethadone);
		Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getMethadone()), "Methadone checkbox is NOT selected!");
		logger.info("Methadone checkbox selected successfully.");
		System.out.println("Methadone checkbox selected successfully.");

		// Click on "Clear All" button to remove all filters
		tripReasonsObject.clickOnclearAllButton();
		System.out.println("Clicked  Clear All button");
		
		// Get Record Count After Clicking "Clear All"
	    int finalRecordCount = tripReasonsObject.getRecordCount();
	    System.out.println("Record count after clicking 'Clear All': " + finalRecordCount);
	    
		// Verify all selected filters are cleared
		Assert.assertTrue(tripReasonsObject.getSelectedFilters().isEmpty(), "Filters are NOT cleared after clicking 'Clear All'!");
		logger.info("All filters successfully cleared.");
		System.out.println("All filters successfully cleared.");

	}
	
	@Test(groups = { "smoke" }, enabled = false)
	public void verifyUserLogin_TC_01() throws IOException, InterruptedException
	{
		try {
			wait.until(ExpectedConditions.elementToBeClickable(lo.username()));
			lo.username().sendKeys(username);
			logger.info("Entered Username");
		}
		catch(StaleElementReferenceException e){
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("loginfmt"))));
			driver.findElement(By.name("loginfmt")).sendKeys(username);
			logger.info("Entered Username");
		}
		wait.until(ExpectedConditions.elementToBeClickable(lo.btnNext()));
		action.moveToElement(lo.btnNext()).click().build().perform();
		logger.info("Clicked on Next");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(lo.password()));
			lo.password().sendKeys(password);
			logger.info("Entered Password");
		}
		catch(StaleElementReferenceException e){
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("passwd"))));
			driver.findElement(By.name("passwd")).sendKeys(password);
			logger.info("Entered Password");
		}
		wait.until(ExpectedConditions.elementToBeClickable(lo.btnSignIn()));
		action.moveToElement(lo.btnSignIn()).click().build().perform();
		logger.info("Clicked on Sign in button");
		
		wait.until(ExpectedConditions.elementToBeClickable(lo.btnYes()));
		action.moveToElement(lo.btnYes()).click().build().perform();
		logger.info("Clicked on yes button");
		logger.info("Application is successfully opened");	
		Thread.sleep(2000);
		Assert.assertTrue(lo.getLogoImg().isDisplayed());
    }

	@Test(groups = { "smoke" , "regression" })
	public void verifyRecordSelection() throws InterruptedException
	{	
		//------------TC_04------------// 
		HomePageObjects homePageObjects = new HomePageObjects(driver);
	    DispatchPageObjects dispatchPageObjects = new DispatchPageObjects(driver);

	    //  Click on Dispatch Tab
		homePageObjects.clickOnDispatchTab();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL , "Dispatch tab URL is incorrect");
		logger.info("Successfully navigated to Dispatch tab");
		System.out.println("Successfully navigated to Dispatch tab");
		
		
		 // Select a record by clicking on the checkbox
	    dispatchPageObjects.selectFirstRecordCheckbox();
	    Assert.assertTrue(dispatchPageObjects.isBurgerIconVisible(), "Burger icon is not visible");
	    logger.info("Burger icon is visible after selecting the record");
	    System.out.println("Burger icon is visible after selecting the record");
	    
	    
	    //Click on the burger icon
	    dispatchPageObjects.clickBurgerIcon();
	    Assert.assertTrue(dispatchPageObjects.isDetailsPanelDisplayed(), "Details panel is not displayed after clicking burger icon");
	    logger.info("Details panel is displayed for the selected record");
	    System.out.println("Details panel is displayed for the selected record");
	}


	@Test(groups = { "regression" })
	public void verifyAllColumnsVisible() throws InterruptedException
	{
		//---TC 10  view all the columns in Dispatch tab ---//
		// Click on Dispatch Tab
		homePageObjects.clickOnDispatchTab();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL , "Dispatch tab URL is incorrect!");
		logger.info("Successfully navigated to Dispatch tab");
	    System.out.println("Successfully navigated to Dispatch tab");
	    
	    Thread.sleep(2000);
	    
	    // Fetch and validate column headers 
	    Assert.assertTrue(dispatchPageObjects.isTimeLeftColumnDisplayed(), "'Time Left' column is missing from the table!");
	    System.out.println("Time Left column is Present");
	    List<String> columnNames = dispatchPageObjects.getAllColumnHeaders();
	    System.out.println("Columns in Dispatch Table: " + columnNames);
	    Assert.assertEquals(columnNames ,Locators.EXPECTED_COLUMNS , "Some expected columns are missing!" );
	    System.out.println("All columns are correctly displayed.");	
	}
	

	@Test(groups = { "smoke" , "regression" })
	public void navigationBetweenTabs() throws InterruptedException
	{
		//---TC 03  ---//
		
		homePageObjects.clickOnDispatchTab();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL , "Dispatch tab URL is incorrect!");
		logger.info("Successfully navigated to Dispatch tab");
		
		homePageObjects.clickOnLyftTab();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.LYFT_URL, "Lyft tab URL is incorrect!");
		logger.info("Successfully navigated to Lyft tab");
		
		homePageObjects.clickOnOlosTab();
		Thread.sleep(2000);
		Assert.assertEquals(driver.getCurrentUrl(), Locators.OLOS_URL, "Olos tab URL is incorrect!");
		logger.info("Successfully navigated to Olos tab");
	}
	

	@Test(groups = { "regression" }, enabled = false)
	public void verifySignOut_TC_09() throws IOException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(dp.btnSignOut()));
		action.moveToElement(dp.btnSignOut()).click().build().perform();
		logger.info("Clicked on Signout button");
		Thread.sleep(2000);
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		//Signout not working
		
	}
	
	@Test(groups = { "regression" })
	public void verifyLYFTSearchNoData_TC_12() throws IOException, InterruptedException
	{
		
		String searcherrormsg = "Please enter some search criteria to refine your results";
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabLyft()));
		action.moveToElement(dp.tabLyft()).click().build().perform();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.LYFT_URL, "Lyft tab URL is incorrect!");
		logger.info("Successfully navigated to Lyft tab");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.searchBtn()));
		action.moveToElement(dp.searchBtn()).click().build().perform();
		logger.info("Clicked on search button");
		Assert.assertEquals(dp.getSearchErrormsg().getText(), searcherrormsg);
		
	}
	
	@Test(groups = { "regression" })
	public void verifyRemarkCommentAdd_TC_18() throws IOException, InterruptedException
	{
		// Declare and initialize the remark value to be added
		String remark_value = "Remark Added";
		
		// Wait until the Dispatch tab is clickable, then move to it and click
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabDispatch()));
		action.moveToElement(dp.tabDispatch()).click().build().perform();
		
		// Verify that the current URL matches the expected Lyft URL
		Assert.assertEquals(driver.getCurrentUrl(), Locators.LYFT_URL, "Lyft tab URL is incorrect!");
		logger.info("Successfully navigated to Lyft tab");
		
		Thread.sleep(2000);	
		
		// Locate the comment box associated with the specific member ID
		WebElement commentbox = driver.findElement(By.xpath("(//icon[@title='has remarks'])[1]"));
		
		// Wait until the comment box is clickable, then click on it
		wait.until(ExpectedConditions.elementToBeClickable(commentbox));
		action.moveToElement(commentbox).click().build().perform();
		logger.info("Comment icon clicked");
		
		// Wait until the remark text area is clickable, then enter the remark
		wait.until(ExpectedConditions.elementToBeClickable(dp.txtareaRemark()));
		Assert.assertTrue(dp.txtareaRemark().isDisplayed(), "Remark textarea did not appear after clicking comment icon!");
		dp.txtareaRemark().sendKeys(remark_value);
		logger.info("Entered Remark Comment");

		// Click the 'Add' button to submit the remark
		dp.btnRemarkAdd().click();
		logger.info("Clicked on Add button");
		Thread.sleep(5000);
		Assert.assertEquals(dp.getRemarkUsername().getText().substring(0,4), username.substring(0,4));
		Assert.assertEquals(dp.getRemarkText().getText(), remark_value);
	
	}
	
	@Test(groups = { "regression" })
	public void verifyRemarkAddDialoguebox_TC_19() throws IOException, InterruptedException
	{		
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabDispatch()));
		action.moveToElement(dp.tabDispatch()).click().build().perform();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.LYFT_URL, "Lyft tab URL is incorrect!");
		logger.info("Successfully navigated to Lyft tab");
		
		Thread.sleep(2000);	
		
		WebElement commentbox = driver.findElement(By.xpath("//td[text()='" + memberid + "']/ancestor::tr/child::td[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(commentbox));
		action.moveToElement(commentbox).click().build().perform();
		Assert.assertTrue(dp.txtareaRemark().isDisplayed(), "Remark textarea did not appear after clicking comment icon!");
		logger.info("Comment icon clicked");	
		
		Assert.assertFalse(dp.btnRemarkAdd().isEnabled());	
	}
	
	@Test(groups = { "regression" })
	public void verifyRemarkCloseIcon_TC_20() throws IOException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabDispatch()));
		action.moveToElement(dp.tabDispatch()).click().build().perform();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.LYFT_URL, "Lyft tab URL is incorrect!");
		logger.info("Successfully navigated to Lyft tab");
		Thread.sleep(2000);	
		
		WebElement commentbox = driver.findElement(By.xpath("//td[text()='" + memberid + "']/ancestor::tr/child::td[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(commentbox));
		action.moveToElement(commentbox).click().build().perform();
		Assert.assertTrue(dp.txtareaRemark().isDisplayed(), "Remark textarea did not appear after clicking comment icon!");
		logger.info("Comment icon clicked");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.iconRemarkClose()));
		action.moveToElement(dp.iconRemarkClose()).click().build().perform();
		logger.info("Remark close icon clicked");
		
		Assert.assertTrue(dp.titleDispatch().isDisplayed());
	}

	
	@Test(groups = { "smoke" , "regression" })
	public void verifyTabsOnHomePage()
	{	
		//--- TC 02 ---//
		
		HomePageObjects homePageObjects  = new HomePageObjects(driver);
	
		Assert.assertTrue(homePageObjects.dispatchTabVisibility() , "Dispatch Tab is NOT visible"  );
		System.out.println("Dispatch tab is visible");
		Assert.assertTrue(homePageObjects.lyftTabVisibility()  , "LYFT Tab is NOT visible!" );
		System.out.println("LYFT tab is visible");
		Assert.assertTrue(homePageObjects.olosTabVisibility() , "OLOS Tab is NOT visible"  );
		System.out.println("OLOS tab is visible");
	}

	@Test(groups = { "regression" })
	public void verifyOLOSClear_TC_07() throws IOException, InterruptedException
	{	
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabOlos()));
		action.moveToElement(dp.tabOlos()).click().build().perform();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.OLOS_URL , "OLOS tab URL is incorrect!");
		logger.info("Successfully navigated to OLOS tab");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setFirstName()));
		dp.setFirstName().sendKeys(firstname);
		logger.info("Entered First name");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setLastName()));
		dp.setLastName().sendKeys(lastname);
		logger.info("Entered Last name");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setDOB()));
		dp.setDOB().sendKeys(dob);
		logger.info("Entered DOB");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setPhone()));
		dp.setPhone().sendKeys(phone);
		logger.info("Entered Phone");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.clearBtn()));
		action.moveToElement(dp.clearBtn()).click().build().perform();
		logger.info("Clicked on clear button");	
		
		Assert.assertTrue(dp.setFirstName().getText().isEmpty());
		Assert.assertTrue(dp.setLastName().getText().isEmpty());
		Assert.assertTrue(dp.setPhone().getText().isEmpty());
		Assert.assertTrue(dp.setDOB().getText().isEmpty());
		
	}

	
	@Test(groups = { "regression" }, enabled = false)
	public void verifyOLOSSearch_TC_08() throws IOException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabOlos()));
		action.moveToElement(dp.tabOlos()).click().build().perform();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.OLOS_URL , "OLOS tab URL is incorrect!");
		logger.info("Successfully navigated to OLOS tab");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setFirstName()));
		dp.setFirstName().sendKeys(firstname);
		logger.info("Entered First name");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setLastName()));
		dp.setLastName().sendKeys(lastname);
		logger.info("Entered Last name");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setDOB()));
		dp.setDOB().sendKeys(dob);
		logger.info("Entered DOB");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setPhone()));
		dp.setPhone().sendKeys(phone);
		logger.info("Entered Phone");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.searchBtn()));
		action.moveToElement(dp.searchBtn()).click().build().perform();
		logger.info("Clicked on search button");
	}
	
	@Test(groups = { "regression" })
	public void verifyLYFTClear_TC_13() throws IOException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabLyft()));
		action.moveToElement(dp.tabLyft()).click().build().perform();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.LYFT_URL , "LYFT tab URL is incorrect!");
		logger.info("Successfully navigated to LYFT tab");	
		Thread.sleep(2000);
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(dp.memberID()));
			dp.memberID().sendKeys(memberid);
			logger.info("Entered Member ID");
		}
		catch(StaleElementReferenceException e){
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='md-input ember-view']"))));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@class='md-input ember-view']")).sendKeys(memberid);
			logger.info("Entered Member ID");
		}
		wait.until(ExpectedConditions.elementToBeClickable(dp.setFirstName()));
		dp.setFirstName().sendKeys(firstname);
		logger.info("Entered First name");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setLastName()));
		dp.setLastName().sendKeys(lastname);
		logger.info("Entered Last name");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setDOB()));
		dp.setDOB().sendKeys(dob);
		logger.info("Entered DOB");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.setPhone()));
		dp.setPhone().sendKeys(phone);
		logger.info("Entered Phone");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.clearBtn()));
		action.moveToElement(dp.clearBtn()).click().build().perform();
		logger.info("Clicked on clear button");	
		
		Assert.assertTrue(dp.memberID().getText().isEmpty());
		Assert.assertTrue(dp.setFirstName().getText().isEmpty());
		Assert.assertTrue(dp.setLastName().getText().isEmpty());
		Assert.assertTrue(dp.setPhone().getText().isEmpty());
		Assert.assertTrue(dp.setDOB().getText().isEmpty());			
	}

	@Test(groups = { "smoke" , "regression"  })
	public void verifyLYFTSearch_TC_05() throws IOException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabLyft()));
		action.moveToElement(dp.tabLyft()).click().build().perform();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.LYFT_URL , "LYFT tab URL is incorrect!");
		logger.info("Successfully navigated to LYFT tab");		
		Thread.sleep(2000);
		
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(dp.memberID()));
			dp.memberID().sendKeys(memberidLyft);
			logger.info("Entered Member ID");
			}
		catch(StaleElementReferenceException e)
			{
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='md-input ember-view']"))));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@class='md-input ember-view']")).sendKeys(memberidLyft);
			logger.info("Entered Member ID");
		}
		
		dp.clickOnStartDate();
		dp.calendardata();
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.searchBtn()));
		action.moveToElement(dp.searchBtn()).click().build().perform();
		logger.info("Clicked on search button");	
		
		Assert.assertTrue(dp.recordresult(memberidLyft));
		
		
	}
	
	@Test(groups = { "regression" }, enabled = false)
	public void verfiyLocationOfTheTips() throws IOException, InterruptedException
	{
		//------TC 06------//
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabLyft()));
		action.moveToElement(dp.tabLyft()).click().build().perform();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.LYFT_URL , "LYFT tab URL is incorrect!");
		logger.info("Successfully navigated to LYFT tab");		
		Thread.sleep(2000);
		
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(dp.memberID()));
			dp.memberID().sendKeys(memberidLyft);
			logger.info("Entered Member ID");
			}
		catch(StaleElementReferenceException e)
			{
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@class='md-input ember-view']"))));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@class='md-input ember-view']")).sendKeys(memberidLyft);
			logger.info("Entered Member ID");
		}
		
		dp.clickOnStartDate();
		dp.calendardata();
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.searchBtn()));
		action.moveToElement(dp.searchBtn()).click().build().perform();
		logger.info("Clicked on search button");	
		
		Assert.assertTrue(dp.recordresult(memberidLyft));
		
		dp.clickOnViewButton();
		
		Assert.assertTrue(dp.recordresult(memberidLyft));
		
		dp.clickOnMapButton();
	}

	@Test(groups = { "smoke" , "regression" })
	public void verifyStatusFilter() throws InterruptedException 
	{	
	//------------TC_15------------// 
	HomePageObjects homePageObjects = new HomePageObjects(driver);
	StatusFilterObjects statusFilterObjects = new StatusFilterObjects(driver);

    //  Click on Dispatch Tab
	homePageObjects.clickOnDispatchTab();
	Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL , "Dispatch tab URL is incorrect");
	logger.info("Successfully navigated to Dispatch tab");
	System.out.println("Successfully navigated to Dispatch tab");
	
	Thread.sleep(2000); 
	
	//Clicking on Parked/Pending status and validating 
	statusFilterObjects.selectParkedOrPending();
	Assert.assertTrue(statusFilterObjects.isPendingStatusFilterApplied());
	logger.info("Successfully Parked/Pending Filter Applied");
	System.out.println("Successfully Parked/Pending Filter Applied");
	
	Thread.sleep(2000);
	
	//Clicking on Reassigned status and validating 
	statusFilterObjects.selectReassigned();
	Thread.sleep(2000);
	Assert.assertTrue(statusFilterObjects.isReassignedStatusFilterApplied());
	logger.info("Successfully Reassigned Filter Applied");
	System.out.println("Successfully Reassigned Filter Applied");
	
	Thread.sleep(2000);
	
	//Clicking on Trip Bidding status and validating 
	statusFilterObjects.selectTripBidding();
	Thread.sleep(2000);
	Assert.assertTrue(statusFilterObjects.isTripBiddingStatusFilterApplied());
	logger.info("Successfully Trip Bidding Filter Applied");
	System.out.println("Successfully Trip Bidding Filter Applied");
	
	Thread.sleep(2000);
	
	//Clicking on All status and validating 
	statusFilterObjects.selectAll();
	Thread.sleep(2000);
	Assert.assertTrue(statusFilterObjects.isALLStatusFilterApplied());
	logger.info("Successfully ALL Filter Applied");
	System.out.println("Successfully Parked/Pending Filter Applied");
	
	
	}
	
	@Test(groups = { "smoke" , "regression"})
	public void verifyTripReasonsFilter() throws InterruptedException
	{	
	//------------TC_16------------// 
	HomePageObjects homePageObjects = new HomePageObjects(driver);
	TripReasonObjects tripReasonObjects =  new TripReasonObjects (driver);
	
    //  Click on Dispatch Tab
	homePageObjects.clickOnDispatchTab();
	Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL , "Dispatch tab URL is incorrect");
	logger.info("Successfully navigated to Dispatch tab");
	System.out.println("Successfully navigated to Dispatch tab");
	
	
	tripReasonObjects.selectBehavioralHealth();
	Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Behavioral Health"), "Trip Reason filter is not applied correctly.");
	logger.info("Successfully Behavioral Health Filter Applied");
	System.out.println("Successfully Behavioral Health Filter Applied");
	
	tripReasonObjects.selectChemotherapy();
	Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Chemotherapy"), "Chemotherapy filter is not applied correctly.");
	logger.info("Successfully Chemotherapy Filter Applied");
	System.out.println("Successfully Chemotherapy Filter Applied");
	
	tripReasonObjects.selectDialysis();
	Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Dialysis"), "Chemotherapy filter is not applied correctly.");
	logger.info("Successfully Dialysis Filter Applied");
	System.out.println("Successfully Dialysis Filter Applied");
	
	tripReasonObjects.selectHospitalDischarge();
	Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Hospital - Discharge"), "Chemotherapy filter is not applied correctly.");
	logger.info("Successfully Hospital Discharge Filter Applied");
	System.out.println("Successfully Hospital Discharge Filter Applied");
	
	tripReasonObjects.selectMethadone();
	Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Methadone"), "Chemotherapy filter is not applied correctly.");
	logger.info("Successfully Methadone Filter Applied");
	System.out.println("Successfully Methadone Filter Applied");
	
	}
	
	@Test(groups = { "regression" })
	public void searchInAllColumnsFilter () throws InterruptedException
	{
		HomePageObjects homePageObjects = new HomePageObjects(driver);
		ColumnFilterObjects columnFilterObjects = new ColumnFilterObjects(driver);

		// Click on Dispatch Tab
		homePageObjects.clickOnDispatchTab();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL , "Dispatch tab URL is incorrect!");
		logger.info("Successfully navigated to Dispatch tab");
	    System.out.println("Successfully navigated to Dispatch tab");
	    
	    Thread.sleep(2000);
	    
	    columnFilterObjects.insertingPlanName();
	    Thread.sleep(2000);
	    Assert.assertTrue(columnFilterObjects.isFilterApplied("Molina IA Medicaid Waiver"), "Plan Name filter failed!");
		System.out.println("Plan Name filter Applied Succesfully");

	    columnFilterObjects.insertingMember();
	    Thread.sleep(2000);
	    Assert.assertTrue(columnFilterObjects.isFilterApplied("JONTRAY BLAIR"), "Member filter failed!");
		System.out.println("Member filter Applied Succesfully");

	    columnFilterObjects.insertingMemberID();
	    Thread.sleep(2000);
	    Assert.assertTrue(columnFilterObjects.isFilterApplied("2308284D"), "Member ID filter failed!");
		System.out.println("MemberID filter Applied Succesfully");
	    
	    columnFilterObjects.insertingTrip();
	    Thread.sleep(2000);
	    Assert.assertTrue(columnFilterObjects.isFilterApplied("56747261"), "Trip filter failed!");
		System.out.println("Trip filter Applied Succesfully");

	}
	
	
	@Test(groups = { "smoke" , "regression" })
	public void verifyTimeFrameFilter() throws InterruptedException
	{
	    HomePageObjects homePageObjects = new HomePageObjects(driver);
	    TimeFilterObjects timeFilterObjects = new TimeFilterObjects(driver);

	    // Navigate to Dispatch tab
	    homePageObjects.clickOnDispatchTab();
	    Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL, "Dispatch tab URL is incorrect!");
	    logger.info("Successfully navigated to Dispatch tab");
	    System.out.println("Successfully navigated to Dispatch tab");

	    Thread.sleep(2000);

	    // Get initial record count
	    int initialCount = timeFilterObjects.getRecordCount();
	    System.out.println("Initial Record Count: " + initialCount);
	    logger.info("Initial Record Count: " + initialCount);

	    Thread.sleep(2000);
	    
	    // Apply 0-6 filter and assert
	    timeFilterObjects.clickOnTimeFrameFilter_0_6();
	    Thread.sleep(7000);
	    Assert.assertTrue(timeFilterObjects.isTimeFrameFilterApplied_0_6(), "0-6 hours filter validation failed");

	    // Apply 6-12 filter and assert
	    timeFilterObjects.clickOnTimeFrameFilter_6_12();
	    Thread.sleep(7000);
	    Assert.assertTrue(timeFilterObjects.isTimeFrameFilterApplied_6_12(), "6-12 hours filter validation failed");

	    // Apply 12-24 filter and assert
	    timeFilterObjects.clickOnTimeFrameFilter_12_24();
	    Thread.sleep(7000);
	    Assert.assertTrue(timeFilterObjects.isTimeFrameFilterApplied_12_24(), "12-24 hours filter validation failed");

	    // Apply Date Range filter and assert
	    timeFilterObjects.clickOnDateRangeFilter();
	    Thread.sleep(1000);
	    timeFilterObjects.ClickOnStartDate();
	    Thread.sleep(500);
	    timeFilterObjects.ClickOn1stOfCurrentMonth();
	    Thread.sleep(2000); 

	    int countAfterDateRange = timeFilterObjects.getRecordCount();
	    System.out.println("Date Range Filter Record Count: " + countAfterDateRange);
	    logger.info("Date Range Filter Record Count: " + countAfterDateRange);

	    Assert.assertTrue(timeFilterObjects.validateAppointmentDateIsInRange(),"Appointment date is NOT within the selected date range");
	    System.out.println("All time filter validations passed successfully.");
	    logger.info("All time filter validations passed successfully.");
	}

}

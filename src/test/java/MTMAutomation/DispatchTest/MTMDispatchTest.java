package MTMAutomation.DispatchTest;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
import MTMAutomation.DispatchTest.PageObjects.DispatchObjects;
import MTMAutomation.DispatchTest.PageObjects.DispatchPageObjects;
import MTMAutomation.DispatchTest.PageObjects.HomePageObjects;
import MTMAutomation.DispatchTest.Utilities.Base;
/**
 * Unit test for simple App.
 */

public class MTMDispatchTest extends Base
{
	LoginObjects lo;
	DispatchObjects dp;
	WebDriverWait wait;
	Actions action;
	
	@BeforeMethod(alwaysRun=true)
    public void setup() throws InterruptedException, IOException
    {
    	lo = new LoginObjects(driver);
    	dp = new DispatchObjects(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));  
        action = new Actions(driver);
        driver.navigate().to(baseURL);
        verifyUserLogin();		
    }
	
	
	public void verifyUserLogin() throws IOException, InterruptedException
	{
		try {
				wait.until(ExpectedConditions.elementToBeClickable(lo.username()));
				lo.username().sendKeys(username);
				logger.info("Entered Username");
			}
		catch(StaleElementReferenceException e)
			{
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
		catch(StaleElementReferenceException e)
			{
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
    }
	
	

	
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


	
	public void verifyAllColumnsVisible() throws InterruptedException
	{
		//---TC 10  view all the columns in Dispatch tab ---//
		HomePageObjects homePageObjects = new HomePageObjects(driver);
	    DispatchPageObjects dispatchPageObjects = new DispatchPageObjects(driver);

	    
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
	

	
	public void navigationBetweenTabs() throws InterruptedException
	{
		HomePageObjects homePageObjects = new HomePageObjects(driver);
		
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
	

	@Test(enabled = false)
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

	
	public void verifyTabsOnHomePage()
	{	
		HomePageObjects homePageObjects  = new HomePageObjects(driver);
	
		Assert.assertTrue(homePageObjects.dispatchTabVisibility() , "Dispatch Tab is NOT visible"  );
		System.out.println("Dispatch tab is visible");
		Assert.assertTrue(homePageObjects.lyftTabVisibility()  , "LYFT Tab is NOT visible!" );
		System.out.println("LYFT tab is visible");
		Assert.assertTrue(homePageObjects.olosTabVisibility() , "OLOS Tab is NOT visible"  );
		System.out.println("OLOS tab is visible");
	}

	
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

	@Test(enabled = false)
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
	
	@Test(enabled = false)
	public void verfiyLocationOfTheTips() throws IOException, InterruptedException
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
		
		dp.clickOnViewButton();
		
		Assert.assertTrue(dp.recordresult(memberidLyft));
		
		dp.clickOnMapButton();
	}

}
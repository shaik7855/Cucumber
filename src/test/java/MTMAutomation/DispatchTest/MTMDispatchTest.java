package MTMAutomation.DispatchTest;

import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import MTMAutomation.DispatchTest.Locators.Locators;
import MTMAutomation.DispatchTest.PageObjects.HomePageObjects;
import MTMAutomation.DispatchTest.PageObjects.LoginObjects;
import MTMAutomation.DispatchTest.PageObjects.TimeFilterObjects;
import MTMAutomation.DispatchTest.Utilities.Base;
/**
 * Unit test for simple App.
 */
public class MTMDispatchTest extends Base
{
	LoginObjects lo;
	WebDriverWait wait;
	Actions action;
	@BeforeMethod(alwaysRun=true)
    public void setup() throws InterruptedException, IOException
    {
    	lo = new LoginObjects(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(90));  
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
	
    }
	@Test
	public void toVerifyTimeFrameFilter() throws InterruptedException
	{
		//--------TC 14 ---------//
		// Creating objects for HomePage and DispatchPage
		HomePageObjects homePageObjects = new HomePageObjects(driver);
		TimeFilterObjects timeFilterObjects = new TimeFilterObjects(driver);

		// Creating an explicit wait instance
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		// Navigating to the Dispatch tab
		homePageObjects.clickOnDispatchTab();
		wait.until(ExpectedConditions.urlToBe(Locators.DISPATCH_URL));
		Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL, "Dispatch tab URL is incorrect!");
		logger.info("Successfully navigated to Dispatch tab");
		System.out.println("Successfully navigated to Dispatch tab");

		Thread.sleep(3000); 

		// Fetching initial record count before applying any filters
		int initialRecordCount = timeFilterObjects.getRecordCount();
		logger.info("Initial record count: " + initialRecordCount);
		System.out.println("Initial record count: " + initialRecordCount);

		// Applying Time Frame Filter (0-6 hours)
		timeFilterObjects.clickOnTimeFrameFilter_0_6();
		Thread.sleep(3000); // Wait for filter to be applied
		boolean isFilterApplied_0_6 = timeFilterObjects.isTimeFrameFilterApplied_0_6();
		Assert.assertTrue(isFilterApplied_0_6, "Time frame filter (0-6 hours) is not applied correctly");
		logger.info("Successfully applied 0-6 hours filter");
		System.out.println("Successfully applied 0-6 hours filter");

		// Applying Time Frame Filter (6-12 hours)
		Thread.sleep(3000); 
		timeFilterObjects.clickOnTimeFrameFilter_6_12();
		Thread.sleep(3000);
		boolean isFilterApplied_6_12 = timeFilterObjects.isTimeFrameFilterApplied_6_12();
		Assert.assertTrue(isFilterApplied_6_12, "Time frame filter (6-12 hours) is not applied correctly");
		logger.info("Successfully applied 6-12 hours filter");
		System.out.println("Successfully applied 6-12 hours filter");

		// Applying Time Frame Filter (12-24 hours)
		Thread.sleep(3000);
		timeFilterObjects.clickOnTimeFrameFilter_12_24();
		Thread.sleep(3000);
		boolean isFilterApplied_12_24 = timeFilterObjects.isTimeFrameFilterApplied_12_24();
		Assert.assertTrue(isFilterApplied_12_24, "Time frame filter (12-24 hours) is not applied correctly");
		logger.info("Successfully applied 12-24 hours filter");
		System.out.println("Successfully applied 12-24 hours filter");

		// Applying Date Range Filter
		timeFilterObjects.clickOnDateRangeFilter();
		Thread.sleep(3000);
		logger.info("Successfully applied Date Range filter");
		System.out.println("Successfully applied Date Range filter");

	}

	
	
}
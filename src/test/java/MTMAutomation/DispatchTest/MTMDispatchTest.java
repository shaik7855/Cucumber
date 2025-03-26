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
import MTMAutomation.DispatchTest.PageObjects.DispatchPageObjects;
import MTMAutomation.DispatchTest.PageObjects.HomePageObjects;
import MTMAutomation.DispatchTest.PageObjects.LoginObjects;
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
@Test()
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
		HomePageObjects homePageObjects = new HomePageObjects(driver);
	    DispatchPageObjects dispatchPage = new DispatchPageObjects(driver);

		
		homePageObjects.clickOnDispatchTab();
		Assert.assertEquals(driver.getCurrentUrl(), Locators.DISPATCH_URL , "Dispatch tab URL is incorrect!");
		logger.info("Successfully navigated to Dispatch tab");
		System.out.println("Successfully navigated to Dispatch tab");
		
		Thread.sleep(3000); 
		
		int initialRecordCount   = dispatchPage.getRecordCount();
		logger.info("Initial record count: " + initialRecordCount );
		System.out.println("Initial record count: " + initialRecordCount );
		
		Thread.sleep(3000);	
		
		dispatchPage.clickOnTimeFrameFilter_0_6();
		Thread.sleep(3000);
		boolean isFilterApplied = dispatchPage.isTimeFrameFilterApplied_0_6();
		Assert.assertTrue(isFilterApplied , "Time frame filter (0-6 hours) is not applied correctly");
		logger.info("Successfully Filter Applied ");
		System.out.println("Successfully Filter Applied");
	}
	
}
package MTMAutomation.DispatchTest;

import java.io.IOException;
import java.time.Duration;
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

import MTMAutomation.DispatchTest.PageObjects.LoginObjects;
import MTMAutomation.DispatchTest.PageObjects.DispatchObjects;
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
		logger.info("Redirected to Lyft Page");	
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.searchBtn()));
		action.moveToElement(dp.searchBtn()).click().build().perform();
		logger.info("Clicked on search button");
		
		Assert.assertEquals(dp.getSearchErrormsg().getText(), searcherrormsg);
		
	}
	
	
	public void verifyRemarkCommentAdd_TC_18() throws IOException, InterruptedException
	{
		String remark_value = "Remark Added";
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabDispatch()));
		action.moveToElement(dp.tabDispatch()).click().build().perform();
		logger.info("Redirected to Dispatch Page");
		Thread.sleep(2000);	
		
		WebElement commentbox = driver.findElement(By.xpath("//td[text()='" + memberid + "']/ancestor::tr/child::td[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(commentbox));
		action.moveToElement(commentbox).click().build().perform();
		logger.info("Comment icon clicked");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.txtareaRemark()));
		dp.txtareaRemark().sendKeys(remark_value);
		logger.info("Entered Remark Comment");
		
		dp.btnRemarkAdd().click();
		logger.info("Clicked on Add button");
		Thread.sleep(2000);
		
		Assert.assertEquals(dp.getRemarkUsername().getText(), loggedinusername);
		Assert.assertEquals(dp.getRemarkText().getText(), remark_value);
		
	}

	public void verifyRemarkAddDialoguebox_TC_19() throws IOException, InterruptedException
	{		
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabDispatch()));
		action.moveToElement(dp.tabDispatch()).click().build().perform();
		logger.info("Redirected to Dispatch Page");
		Thread.sleep(2000);	
		WebElement commentbox = driver.findElement(By.xpath("//td[text()='" + memberid + "']/ancestor::tr/child::td[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(commentbox));
		action.moveToElement(commentbox).click().build().perform();
		logger.info("Comment icon clicked");
		
		Assert.assertFalse(dp.btnRemarkAdd().isEnabled());
		
	}

	public void verifyRemarkCloseIcon_TC_20() throws IOException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(dp.tabDispatch()));
		action.moveToElement(dp.tabDispatch()).click().build().perform();
		logger.info("Redirected to Dispatch Page");
		Thread.sleep(2000);	
		
		WebElement commentbox = driver.findElement(By.xpath("//td[text()='" + memberid + "']/ancestor::tr/child::td[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(commentbox));
		action.moveToElement(commentbox).click().build().perform();
		logger.info("Comment icon clicked");
		
		wait.until(ExpectedConditions.elementToBeClickable(dp.iconRemarkClose()));
		action.moveToElement(dp.iconRemarkClose()).click().build().perform();
		logger.info("Remark close icon clicked");
		
		Assert.assertTrue(dp.titleDispatch().isDisplayed());
	}
@Test	
	public void verifyTabsOnHomePage()
	{	
		HomePageObjects homePageObjects  = new HomePageObjects(driver);
	
		Assert.assertTrue(homePageObjects.dispatchTabVisibility() , "Dispatch Tab is NOT visible"  );
		System.out.println("Dispatch tab is visible");
		Assert.assertTrue(homePageObjects.lyftTabTabVisibility()  , "LYFT Tab is NOT visible!" );
		System.out.println("LYFT tab is visible");
		Assert.assertTrue(homePageObjects.OLOSTabTabVisibility() , "OLOS Tab is NOT visible"  );
		System.out.println("OLOS tab is visible");
	}
}
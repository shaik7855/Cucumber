package MTMAutomation.DispatchTest.Utilities;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import MTMAutomation.DispatchTest.Utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Base {
	public ReadConfig readconfig=new ReadConfig();
	
	public String baseURL = readconfig.getProperty("baseURL");
	public String username=readconfig.getProperty("username");
	public String password=readconfig.getProperty("password");
	public String memberid=readconfig.getProperty("memberid");
	public String firstname = readconfig.getProperty("firstname");
	public String lastname = readconfig.getProperty("lastname");
	public String phone = readconfig.getProperty("phone");
	public String dob=readconfig.getProperty("dob");
	//------------------------------------------------------------------------------------------------------
	
	public WebDriver driver;
	public static final Logger logger = LogManager.getLogger(Base.class);
	public String applicationEndpoint;
	public String productDetailsEndPoint;
	public OffsetDateTime executionStartDateTime;
	
	@BeforeClass(alwaysRun = true)
	@Parameters({"browserName"})
	public void setup(@Optional("chrome")String browserName){
		this.executionStartDateTime = OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC);
		logger.info("Test Class Execution Start time UTC: " + executionStartDateTime);
	
		if (System.getProperty("browser") != null){
			browserName = System.getProperty("browser");
			logger.info("System property browser found: " + browserName);
		}
		this.driver = WebDriverProvider.getDriver(browserName);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		webDriverThreadLocal.set(driver);
	}
	
	public static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
	//public static final String DOWNLOAD_DIRECTORY = "AutDownloads\\";
	
	@AfterClass(alwaysRun = true)
	public void tearDown()
	{
		if (this.driver != null) {
			driver.quit();
		}
	}
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		logger.info("Screenshot taken");
	}
	
	private String getExceptionDetails(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		return sw.toString();
	}
	
	private void logError(String message, Exception e) {
		// Log the error using your preferred logging framework
		// For example, with Log4j:
		// Logger.getLogger(YourTestClass.class).error(message, e);
		System.err.println(message);
		e.printStackTrace();
	}
	
	public void restartBrowserSession(String browserName){
		if (System.getProperty("browser") != null) {
			browserName = System.getProperty("browser");
				}
				this.driver.quit();
				this.driver = WebDriverProvider.getDriver(browserName);
				this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				webDriverThreadLocal.set(this.driver);
			}
		}
	

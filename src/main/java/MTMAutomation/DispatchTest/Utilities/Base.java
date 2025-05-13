package MTMAutomation.DispatchTest.Utilities;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;

public class Base {
    public ReadConfig readconfig = new ReadConfig();
    
    public String baseURL = readconfig.getProperty("baseURL");
    public String username = readconfig.getProperty("username");
    public String password = readconfig.getProperty("password");
    public String memberid = readconfig.getProperty("memberid");
    public String memberidLyft = readconfig.getProperty("memberidLyft");
    public String firstname = readconfig.getProperty("firstname");
    public String lastname = readconfig.getProperty("lastname");
    public String phone = readconfig.getProperty("phone");
    public String dob = readconfig.getProperty("dob");
    
    public WebDriver driver;
    public static final Logger logger = LogManager.getLogger(Base.class);
    public String applicationEndpoint;
    public String productDetailsEndPoint;
    
    public static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    
    public void setupDriver(String browserName) throws IOException {
        logger.info("Browser selected: " + browserName);
        this.driver = WebDriverProvider.getDriver(browserName);
        if (driver == null) {
            throw new IllegalStateException("WebDriver initialization failed for browser: " + browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriverThreadLocal.set(driver);
    }
    
    public void tearDownDriver() {
        if (this.driver != null) {
            driver.quit();
            webDriverThreadLocal.remove();
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
        System.err.println(message);
        e.printStackTrace();
    }
    
    public void restartBrowserSession(String browserName) {
        browserName = System.getProperty("browser", "chrome");
        if (this.driver != null) {
            this.driver.quit();
        }
        this.driver = WebDriverProvider.getDriver(browserName);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        webDriverThreadLocal.set(this.driver);
    }
}/*package MTMAutomation.DispatchTest.Utilities;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Base {
    public ReadConfig readconfig = new ReadConfig();
    
    public String baseURL = readconfig.getProperty("baseURL");
    public String username = readconfig.getProperty("username");
    public String password = readconfig.getProperty("password");
    public String memberid = readconfig.getProperty("memberid");
    public String memberidLyft = readconfig.getProperty("memberidLyft");
    public String firstname = readconfig.getProperty("firstname");
    public String lastname = readconfig.getProperty("lastname");
    public String phone = readconfig.getProperty("phone");
    public String dob = readconfig.getProperty("dob");
    
    public WebDriver driver;
    public static final Logger logger = LogManager.getLogger(Base.class);
    public String applicationEndpoint;
    public String productDetailsEndPoint;
    public OffsetDateTime executionStartDateTime;
    
    public static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    
    @BeforeAll
    public void setup()throws InterruptedException, IOException {
        this.executionStartDateTime = OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC);
        logger.info("Scenario Execution Start time UTC: " + executionStartDateTime);
        
        String browserName = System.getProperty("browser", "chrome");
        logger.info("Browser selected: " + browserName);
        
        this.driver = WebDriverProvider.getDriver(browserName);
        if (driver == null) {
            throw new IllegalStateException("WebDriver initialization failed for browser: " + browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriverThreadLocal.set(driver);
    }
    
    @AfterAll
    public void tearDown() {
        if (this.driver != null) {
            driver.quit();
            webDriverThreadLocal.remove();
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
        System.err.println(message);
        e.printStackTrace();
    }
    
    public void restartBrowserSession(String browserName) {
        browserName = System.getProperty("browser", "chrome");
        if (this.driver != null) {
            this.driver.quit();
        }
        this.driver = WebDriverProvider.getDriver(browserName);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        webDriverThreadLocal.set(this.driver);
    }
}*/
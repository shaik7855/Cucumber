package MTMAutomation.DispatchTest.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import MTMAutomation.DispatchTest.Utilities.ExtentReporter;

public class ExtentReporter extends TestListenerAdapter {
	public static final ExtentReports extentReports = new ExtentReports();

	public static Logger logger = LogManager.getLogger(ExtentReporter.class);

	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static String timeStamp;

	static {

		timeStamp = new SimpleDateFormat("yyyy.MM.dd.h.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentReports.attachReporter(htmlReporter);
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest.set(extentReports.createTest(result.getMethod().getMethodName()));
	}

	public void onTestSuccess(ITestResult tr) {
		if(tr.getMethod().isTest()) {
			extentTest.get().pass("Test passed.");
		}
	}

	public void onTestFailure(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			 ITestContext context = testResult.getTestContext();
		       WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String directoryPath = System.getProperty("user.dir") + File.separator + "test-output";
			File directory = new File(directoryPath);

			if (!directory.exists()) {
				directory.mkdirs();
			}

			String fileName = testResult.getName() + timeStamp + ".jpg";
			String filePath = directoryPath + File.separator + fileName;

            try {
                FileUtils.copyFile(scrFile, new File(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            logger.info("Screenshot saved to: " + filePath);
			extentTest.get().fail("Failure details:", MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());

		}
		extentTest.get().log(Status.FAIL, testResult.getThrowable());
	}

	public void onTestSkipped(ITestResult tr) {
		extentTest.get().skip("Test skipped.");
	}

	public void onFinish(ITestContext testContext) {
		extentReports.flush();
	}
}

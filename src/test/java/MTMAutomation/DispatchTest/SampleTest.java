package MTMAutomation.DispatchTest;

import org.testng.annotations.Test;

import MTMAutomation.DispatchTest.Utilities.Base;

public class SampleTest extends Base {
	@Test
	public void google() throws InterruptedException
	{
		driver.navigate().to("https://www.google.com");
		Thread.sleep(15000);
		
	}
}

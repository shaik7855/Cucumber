package MTMAutomation.DispatchTest.Locators;

public class Locators
{
	public static final String DISPATCH_TAB = "//a[@id='ember463']"; //xpath
	
	public static final String DISPATCH_URL = "https://dispatch-test.mtm-inc.net/"; // url 
	
	public static final String TimeFrameFilter_0_6   = "//span[contains(text(),'0 - 6 Hours Left')]";  //xpath
	
	public static final String TimeFrameFilter_6_12  ="//span[contains(text(),'6 - 12 Hours Left')]";  //xpath
	
	public static final String TimeFrameFilter_12_24 ="//span[contains(text(),'12 - 24 Hours Left')]"; //xpath
	
	public static final String Date_Range ="//span[contains(text(),'Date Range')]"; //xpath
	
	//common xpath for records = "//table//tbody//tr/td/div[@class='ember-view']"
}

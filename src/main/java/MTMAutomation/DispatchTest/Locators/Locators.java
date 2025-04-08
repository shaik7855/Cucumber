package MTMAutomation.DispatchTest.Locators;

import java.util.Arrays;
import java.util.List;


public class Locators 
{
	public static final String DISPATCH_URL = "https://dispatch-test.mtm-inc.net/";
	
	public static final String DISPATCH_TAB = "//a[@id='ember463']"; //xpath
	
	
    public static final String DISPATCH_TABLE_HEADERS_XPATH = "//th[@class='sorting ']";
    
    public static final String Time_Left_Column = "//div[@class='title no-wrap']";

    // Expected column names list
    public static final List<String> EXPECTED_COLUMNS = Arrays.asList
    		("Appt. Date", "Plan", "Member", "Member ID", "Trip #", "Status", "LOS", "Distance",
    		"Transportation Provider", "Trip Reason", "Pickup Address", "Pickup City", "Pickup State",
    		"Pickup County", "Pickup Zip", "Destination Facility", "Destination Address", "Destination City", 
    		"Destination State", "Destination County", "Destination Zip");

	public static final String LYFT_URL = "https://dispatch-test.mtm-inc.net/#/external-api?account=A2C&applicationAccount=A2C";
	public static final String OLOS_URL = "https://dispatch-test.mtm-inc.net/#/external-api?account=OLOS&applicationAccount=OLOS";
	
}

package MTMAutomation.DispatchTest.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DispatchPageObjects 
{
	WebDriver driver;

    // Constructor
    public DispatchPageObjects(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    
    //---------Method to select first record checkbox------------//
    
    @FindBy(xpath = "//table//tr[1]//td[1]//input[@type='checkbox']")
    WebElement firstRecordCheckbox;
    public void selectFirstRecordCheckbox() 
    {
        firstRecordCheckbox.click();
    }
    
    //---------Method to see burger icon is visible------------//
    
    @FindBy(xpath = "//icon[@data-icon='menu']")
    WebElement burgerIcon;
    public boolean isBurgerIconVisible()
    {
        return burgerIcon.isDisplayed();
    }
    
    //---------Method to click on burger icon------------//
    
    @FindBy(xpath = "//icon[@data-icon='menu']")
    WebElement burgerIcon1;
    
    public void clickBurgerIcon() 
    {
        burgerIcon1.click();
    }
    
    
    //---------Method to see the deatails in the pannel are displayed------------//
    
    @FindBy(xpath = "//span[.='Include Providers By County']")
    WebElement detailsPanel;
    public boolean isDetailsPanelDisplayed()
    {
        return detailsPanel.isDisplayed();
    }
     
	  
   // Method to get all column headers dynamically
	  
   /* Approach 1
    * public List<String> getAllColumnHeaders() 
    {
        List<WebElement> columnHeaders = driver.findElements(By.xpath(Locators.DISPATCH_TABLE_HEADERS_XPATH));

        return columnHeaders.stream()
                .map(header -> header.getText().trim()) // Trim spaces
                .filter(text -> !text.isEmpty()) // Remove empty headers
                .collect(Collectors.toList()); // Convert to list
    }*/
    
    // Method to get all column headers
    @FindBy(xpath = "//th[@class='sorting ']")
    List<WebElement> columnHeaders;
    
    public List<String> getAllColumnHeaders() 
    {
        return columnHeaders.stream()
                .map(header -> header.getText().trim()) // Trim spaces
                .filter(text -> !text.isEmpty()) // Remove empty headers (if any)
                .collect(Collectors.toList()); // Convert to list
    }
    
    
    // Method to see time left column is visible
    
    @FindBy(xpath = Locators.Time_Left_Column)
    @CacheLookup
    WebElement timeLeftColumn;

    public boolean isTimeLeftColumnDisplayed()
    {
        return timeLeftColumn.isDisplayed();
    }
    
    

}

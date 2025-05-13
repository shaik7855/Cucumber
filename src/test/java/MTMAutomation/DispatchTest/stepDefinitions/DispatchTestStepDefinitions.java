package MTMAutomation.DispatchTest.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import MTMAutomation.DispatchTest.Locators.Locators;
import MTMAutomation.DispatchTest.PageObjects.*;
import MTMAutomation.DispatchTest.Utilities.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class DispatchTestStepDefinitions {
    private static final Logger logger = LogManager.getLogger(DispatchTestStepDefinitions.class);
    private WebDriverWait wait;
    private Actions action;
    private LoginObjects lo;
    private DispatchObjects dp;
    private HomePageObjects homePageObjects;
    private DispatchPageObjects dispatchPageObjects;
    private TripReasonsObject tripReasonsObject;
    private StatusFilterObjects statusFilterObjects;
    private TripReasonObjects tripReasonObjects;
    private ColumnFilterObjects columnFilterObjects;
    private TimeFilterObjects timeFilterObjects;
    private int initialRecordCount;
    private Base base; // Instance of Base
    private OffsetDateTime executionStartDateTime;

    
    @Before
    public void setup() throws InterruptedException, IOException {
        base = new Base(); // Initialize Base instance
        String browserName = System.getProperty("browser", "chrome");
        base.setupDriver(browserName); // Setup WebDriver
        executionStartDateTime = OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC);
        logger.info("Scenario Execution Start time UTC: " + executionStartDateTime);
        
        lo = new LoginObjects(base.driver);
        dp = new DispatchObjects(base.driver);
        homePageObjects = new HomePageObjects(base.driver);
        dispatchPageObjects = new DispatchPageObjects(base.driver);
        tripReasonsObject = new TripReasonsObject(base.driver);
        statusFilterObjects = new StatusFilterObjects(base.driver);
        tripReasonObjects = new TripReasonObjects(base.driver);
        columnFilterObjects = new ColumnFilterObjects(base.driver);
        timeFilterObjects = new TimeFilterObjects(base.driver);
        wait = new WebDriverWait(base.driver, Duration.ofSeconds(60));
        action = new Actions(base.driver);
        base.driver.navigate().to(base.baseURL);
    }

    @After
    public void cleanup() {
        base.tearDownDriver();
        logger.info("WebDriver session closed");
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        logger.info("Navigated to login page: " + base.baseURL);
    }

    @When("the user enters valid username")
    public void the_user_enters_valid_username() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lo.username()));
            Thread.sleep(2000);
            lo.username().sendKeys(base.username);
            logger.info("Entered Username");
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(base.driver.findElement(By.name("loginfmt"))));
            base.driver.findElement(By.name("loginfmt")).sendKeys(base.username);
            Thread.sleep(2000);
            logger.info("Entered Username");
        }
    }

    @And("the user clicks on Next button after username")
    public void the_user_clicks_on_next_button_after_username() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(lo.btnNext()));
        Thread.sleep(2000);
        action.moveToElement(lo.btnNext()).click().build().perform();
        Thread.sleep(2000);
        logger.info("Clicked on Next after username");
    }

    @And("the user enters valid password")
    public void the_user_enters_valid_password() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lo.password()));
            Thread.sleep(2000);
            lo.password().sendKeys(base.password);
            logger.info("Entered Password");
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(base.driver.findElement(By.name("passwd"))));
            base.driver.findElement(By.name("passwd")).sendKeys(base.password);
            Thread.sleep(2000);
            logger.info("Entered Password");
        }
    }

    @And("the user clicks on Sign In button")
    public void the_user_clicks_on_sign_in_button() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(lo.btnSignIn()));
        Thread.sleep(2000);
        action.moveToElement(lo.btnSignIn()).click().build().perform();
        Thread.sleep(2000);
        logger.info("Clicked on Sign in button");
    }

    @And("the user clicks on Yes button")
    public void the_user_clicks_on_yes_button() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(lo.btnYes()));
        Thread.sleep(2000);
        action.moveToElement(lo.btnYes()).click().build().perform();
        Thread.sleep(2000);
        logger.info("Clicked on yes button");
    }

    @Then("the application should be successfully opened")
    public void the_application_should_be_successfully_opened() {
        logger.info("Application is successfully opened");
    }

    @Then("the application logo should be displayed")
    public void the_application_logo_should_be_displayed() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(lo.getLogoImg().isDisplayed(), "Application logo is not displayed");
        logger.info("Application logo is displayed");
    }

    @Given("the user navigates to the Dispatch tab")
    public void the_user_navigates_to_the_dispatch_tab() throws InterruptedException {
        homePageObjects.clickOnDispatchTab();
        wait.until(driver -> {
            String url = driver.getCurrentUrl();
            return url.contains("dispatch-test.mtm-inc.net") && 
                   (url.contains("/#/dispatch") || url.equals(Locators.DISPATCH_URL));
        });

    String actualUrl = base.driver.getCurrentUrl();
    boolean isUrlValid = actualUrl.contains(Locators.DISPATCH_URL) || 
                         actualUrl.contains("dispatch-test.mtm-inc.net/#/dispatch?applicationAccount=Dispatch");
    Assert.assertTrue(isUrlValid,
        "Dispatch tab URL is incorrect! Expected to contain: " + Locators.DISPATCH_URL + 
        " or dispatch-test.mtm-inc.net/#/dispatch?applicationAccount=Dispatch, but found: " + actualUrl);
    logger.info("Successfully navigated to Dispatch tab: {}", actualUrl);
    }

    @When("the user selects Behavioral Health checkbox")
    public void the_user_selects_behavioral_health_checkbox() throws InterruptedException {
        initialRecordCount = tripReasonsObject.getRecordCount();
        tripReasonsObject.clickOnBehavioralHealth();
        Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getBehavioralHealth()), "Behavioral Health checkbox is NOT selected!");
        logger.info("Behavioral Health checkbox selected successfully.");
        Thread.sleep(2000);
    }

    @And("the user selects Chemotherapy checkbox")
    public void the_user_selects_chemotherapy_checkbox() throws InterruptedException {
        tripReasonsObject.clickOnChemotherapy();
        Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getChemotherapy()), "Chemotherapy checkbox is NOT selected!");
        logger.info("Chemotherapy checkbox selected successfully.");
        Thread.sleep(2000);
    }

    @And("the user selects Dialysis checkbox")
    public void the_user_selects_dialysis_checkbox() throws InterruptedException {
        tripReasonsObject.clickOnDialysis();
        Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getDialysis()), "Dialysis checkbox is NOT selected!");
        logger.info("Dialysis checkbox selected successfully.");
        Thread.sleep(2000);
    }

    @And("the user selects Hospital Discharge checkbox")
    public void the_user_selects_hospital_discharge_checkbox() throws InterruptedException {
        tripReasonsObject.clickOnHospitalDischarge();
        Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getHospitalDischarge()), "Hospital Discharge checkbox is NOT selected!");
        logger.info("Hospital Discharge checkbox selected successfully.");
        Thread.sleep(2000);
    }

    @And("the user selects Methadone checkbox")
    public void the_user_selects_methadone_checkbox() throws InterruptedException {
        tripReasonsObject.clickOnMethadone();
        Assert.assertTrue(tripReasonsObject.isCheckboxSelected(tripReasonsObject.getMethadone()), "Methadone checkbox is NOT selected!");
        logger.info("Methadone checkbox selected successfully.");
        Thread.sleep(2000);
    }

    @And("the user clicks on Clear All button")
    public void the_user_clicks_on_clear_all_button() {
        tripReasonsObject.clickOnclearAllButton();
        logger.info("Clicked Clear All button");
    }

    @Then("all filters should be cleared")
    public void all_filters_should_be_cleared() {
        Assert.assertTrue(tripReasonsObject.getSelectedFilters().isEmpty(), "Filters are NOT cleared after clicking 'Clear All'!");
        logger.info("All filters successfully cleared.");
    }

    @And("the record count should match the initial count")
    public void the_record_count_should_match_the_initial_count() {
        int finalRecordCount = tripReasonsObject.getRecordCount();
        Assert.assertEquals(finalRecordCount, initialRecordCount, "Record count after clearing filters does not match initial count!");
        logger.info("Record count after clearing filters matches initial count: " + finalRecordCount);
    }

    @When("the user selects the first record checkbox")
    public void the_user_selects_the_first_record_checkbox() {
        dispatchPageObjects.selectFirstRecordCheckbox();
        logger.info("Selected first record checkbox");
    }

    @Then("the burger icon should be visible")
    public void the_burger_icon_should_be_visible() {
        Assert.assertTrue(dispatchPageObjects.isBurgerIconVisible(), "Burger icon is not visible");
        logger.info("Burger icon is visible after selecting the record");
    }

    @When("the user clicks on the burger icon")
    public void the_user_clicks_on_the_burger_icon() {
        dispatchPageObjects.clickBurgerIcon();
        logger.info("Clicked on burger icon");
    }

    @Then("the details panel should be displayed")
    public void the_details_panel_should_be_displayed() {
        Assert.assertTrue(dispatchPageObjects.isDetailsPanelDisplayed(), "Details panel is not displayed after clicking burger icon");
        logger.info("Details panel is displayed for the selected record");
    }

    @Then("the Time Left column should be visible")
    public void the_time_left_column_should_be_visible() {
        Assert.assertTrue(dispatchPageObjects.isTimeLeftColumnDisplayed(), "'Time Left' column is missing from the table!");
        logger.info("Time Left column is present");
    }

    @And("all expected columns should be displayed")
    public void all_expected_columns_should_be_displayed() {
        List<String> columnNames = dispatchPageObjects.getAllColumnHeaders();
        Assert.assertEquals(columnNames, Locators.EXPECTED_COLUMNS, "Some expected columns are missing!");
        logger.info("All columns are correctly displayed: " + columnNames);
    }

    @When("the user navigates to the Lyft tab")
    public void the_user_navigates_to_the_lyft_tab() throws InterruptedException {
        homePageObjects.clickOnLyftTab();
        Assert.assertEquals(base.driver.getCurrentUrl(), Locators.LYFT_URL, "Lyft tab URL is incorrect!");
        logger.info("Successfully navigated to Lyft tab");
        Thread.sleep(2000);
    }

    @When("the user navigates to the Olos tab")
    public void the_user_navigates_to_the_olos_tab() throws InterruptedException {
        homePageObjects.clickOnOlosTab();
        Assert.assertEquals(base.driver.getCurrentUrl(), Locators.OLOS_URL, "Olos tab URL is incorrect!");
        logger.info("Successfully navigated to Olos tab");
        Thread.sleep(2000);
    }

    @Then("the Dispatch tab URL should be correct")
    public void the_dispatch_tab_url_should_be_correct() {
        String actualUrl = base.driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,Locators.DISPATCH_URL, "Dispatch tab URL is incorrect! Expected to contain: " + Locators.DISPATCH_URL + ", but found: " + actualUrl);
        logger.info("Dispatch tab URL is correct");
    }

    @Then("the Lyft tab URL should be correct")
    public void the_lyft_tab_url_should_be_correct() {
        String actualUrl = base.driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,Locators.LYFT_URL,
            "Lyft tab URL is incorrect! Expected to contain: " + Locators.LYFT_URL + ", but found: " + actualUrl);
        logger.info("Lyft tab URL is correct");
    }

    @Then("the Olos tab URL should be correct")
    public void the_olos_tab_url_should_be_correct() {
        Assert.assertEquals(base.driver.getCurrentUrl(), Locators.OLOS_URL, "Olos tab URL is incorrect!");
        logger.info("Olos tab URL is correct");
    }

    @When("the user clicks on the search button without entering criteria")
    public void the_user_clicks_on_the_search_button_without_entering_criteria() {
        wait.until(ExpectedConditions.elementToBeClickable(dp.searchBtn()));
        action.moveToElement(dp.searchBtn()).click().build().perform();
        logger.info("Clicked on search button");
    }

    @Then("the error message {string} should be displayed")
    public void the_error_message_should_be_displayed(String expectedError) {
        Assert.assertEquals(dp.getSearchErrormsg().getText(), expectedError, "Search error message is incorrect!");
        logger.info("Error message displayed: " + expectedError);
    }

    @When("the user clicks on the comment icon for a specific member")
    public void the_user_clicks_on_the_comment_icon_for_a_specific_member() throws InterruptedException {
        WebElement commentbox = base.driver.findElement(By.xpath("(//icon[@title='has remarks'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(commentbox));
        action.moveToElement(commentbox).click().build().perform();
        logger.info("Comment icon clicked");
        Thread.sleep(2000);
    }

    @And("the user enters the remark {string}")
    public void the_user_enters_the_remark(String remark) {
        wait.until(ExpectedConditions.elementToBeClickable(dp.txtareaRemark()));
        Assert.assertTrue(dp.txtareaRemark().isDisplayed(), "Remark textarea did not appear after clicking comment icon!");
        dp.txtareaRemark().sendKeys(remark);
        logger.info("Entered Remark Comment: " + remark);
    }

    @And("the user clicks on the Add button")
    public void the_user_clicks_on_the_add_button() throws InterruptedException {
        dp.btnRemarkAdd().click();
        logger.info("Clicked on Add button");
        Thread.sleep(5000);
    }

    @Then("the remark should be added successfully")
    public void the_remark_should_be_added_successfully() {
        Assert.assertEquals(dp.getRemarkText().getText(), "Remark Added", "Remark text is incorrect!");
        logger.info("Remark added successfully");
    }

    @And("the username in the remark should match the logged-in user")
    public void the_username_in_the_remark_should_match_the_logged_in_user() {
        Assert.assertEquals(dp.getRemarkUsername().getText().substring(0, 4), base.username.substring(0, 4), "Remark username does not match logged-in user!");
        logger.info("Remark username matches logged-in user");
    }

    @Then("the remark textarea should be displayed")
    public void the_remark_textarea_should_be_displayed() {
        Assert.assertTrue(dp.txtareaRemark().isDisplayed(), "Remark textarea did not appear after clicking comment icon!");
        logger.info("Remark textarea is displayed");
    }

    @And("the Add button should be disabled")
    public void the_add_button_should_be_disabled() {
        Assert.assertFalse(dp.btnRemarkAdd().isEnabled(), "Add button is enabled when it should be disabled!");
        logger.info("Add button is disabled");
    }

    @And("the user clicks on the remark close icon")
    public void the_user_clicks_on_the_remark_close_icon() {
        wait.until(ExpectedConditions.elementToBeClickable(dp.iconRemarkClose()));
        action.moveToElement(dp.iconRemarkClose()).click().build().perform();
        logger.info("Remark close icon clicked");
    }

    @Then("the Dispatch title should be visible")
    public void the_dispatch_title_should_be_visible() {
        Assert.assertTrue(dp.titleDispatch().isDisplayed(), "Dispatch title is not visible!");
        logger.info("Dispatch title is visible");
    }

    @Then("the Dispatch tab should be visible")
    public void the_dispatch_tab_should_be_visible() {
        Assert.assertTrue(homePageObjects.dispatchTabVisibility(), "Dispatch Tab is NOT visible");
        logger.info("Dispatch tab is visible");
    }

    @And("the Lyft tab should be visible")
    public void the_lyft_tab_should_be_visible() {
        Assert.assertTrue(homePageObjects.lyftTabVisibility(), "LYFT Tab is NOT visible!");
        logger.info("Lyft tab is visible");
    }

    @And("the Olos tab should be visible")
    public void the_olos_tab_should_be_visible() {
        Assert.assertTrue(homePageObjects.olosTabVisibility(), "OLOS Tab is NOT visible");
        logger.info("Olos tab is visible");
    }

    @When("the user enters first name, last name, DOB, and phone")
    public void the_user_enters_first_name_last_name_dob_and_phone() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(dp.setFirstName()));
        dp.setFirstName().sendKeys(base.firstname);
        logger.info("Entered First name: " + base.firstname);

        wait.until(ExpectedConditions.elementToBeClickable(dp.setLastName()));
        dp.setLastName().sendKeys(base.lastname);
        logger.info("Entered Last name: " + base.lastname);

        wait.until(ExpectedConditions.elementToBeClickable(dp.setDOB()));
        dp.setDOB().sendKeys(base.dob);
        logger.info("Entered DOB: " + base.dob);

        wait.until(ExpectedConditions.elementToBeClickable(dp.setPhone()));
        dp.setPhone().sendKeys(base.phone);
        logger.info("Entered Phone: " + base.phone);
        Thread.sleep(2000);
    }

    @And("the user clicks on the clear button")
    public void the_user_clicks_on_the_clear_button() {
        wait.until(ExpectedConditions.elementToBeClickable(dp.clearBtn()));
        action.moveToElement(dp.clearBtn()).click().build().perform();
        logger.info("Clicked on clear button");
    }

    @Then("all input fields should be cleared")
    public void all_input_fields_should_be_cleared() {
        Assert.assertTrue(dp.setFirstName().getText().isEmpty(), "First name field is not cleared!");
        Assert.assertTrue(dp.setLastName().getText().isEmpty(), "Last name field is not cleared!");
        Assert.assertTrue(dp.setPhone().getText().isEmpty(), "Phone field is not cleared!");
        Assert.assertTrue(dp.setDOB().getText().isEmpty(), "DOB field is not cleared!");
        logger.info("All input fields are cleared");
    }

    @Then("the search results should be displayed")
    public void the_search_results_should_be_displayed() {
        logger.info("Search results should be displayed (validation not implemented)");
    }

    @When("the user enters member ID, first name, last name, DOB, and phone")
    public void the_user_enters_member_id_first_name_last_name_dob_and_phone() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dp.memberID()));
            dp.memberID().sendKeys(base.memberid);
            logger.info("Entered Member ID: " + base.memberid);
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(base.driver.findElement(By.xpath("//input[@class='md-input ember-view']"))));
            Thread.sleep(2000);
            base.driver.findElement(By.xpath("//input[@class='md-input ember-view']")).sendKeys(base.memberid);
            logger.info("Entered Member ID: " + base.memberid);
        }
        wait.until(ExpectedConditions.elementToBeClickable(dp.setFirstName()));
        dp.setFirstName().sendKeys(base.firstname);
        logger.info("Entered First name: " + base.firstname);

        wait.until(ExpectedConditions.elementToBeClickable(dp.setLastName()));
        dp.setLastName().sendKeys(base.lastname);
        logger.info("Entered Last name: " + base.lastname);

        wait.until(ExpectedConditions.elementToBeClickable(dp.setDOB()));
        dp.setDOB().sendKeys(base.dob);
        logger.info("Entered DOB: " + base.dob);

        wait.until(ExpectedConditions.elementToBeClickable(dp.setPhone()));
        dp.setPhone().sendKeys(base.phone);
        logger.info("Entered Phone: " + base.phone);
        Thread.sleep(2000);
    }

    @When("the user enters a valid member ID")
    public void the_user_enters_a_valid_member_id() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dp.memberID()));
            dp.memberID().sendKeys(base.memberidLyft);
            logger.info("Entered Member ID: " + base.memberidLyft);
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(base.driver.findElement(By.xpath("//input[@class='md-input ember-view']"))));
            Thread.sleep(2000);
            base.driver.findElement(By.xpath("//input[@class='md-input ember-view']")).sendKeys(base.memberidLyft);
            logger.info("Entered Member ID: " + base.memberidLyft);
        }
    }

    @And("the user selects a start date")
    public void the_user_selects_a_start_date() {
        dp.clickOnStartDate();
        dp.calendardata();
        logger.info("Selected start date");
    }

    @And("the user clicks on the search button")
    public void the_user_clicks_on_the_search_button() {
        wait.until(ExpectedConditions.elementToBeClickable(dp.searchBtn()));
        action.moveToElement(dp.searchBtn()).click().build().perform();
        logger.info("Clicked on search button");
    }

    @Then("the search results should display the correct member ID")
    public void the_search_results_should_display_the_correct_member_id() {
        Assert.assertTrue(dp.recordresult(base.memberidLyft), "Search results do not display the correct member ID!");
        logger.info("Search results display correct member ID: " + base.memberidLyft);
    }

    @When("the user clicks on the View button")
    public void the_user_clicks_on_the_view_button() {
        dp.clickOnViewButton();
        logger.info("Clicked on View button");
    }

    @Then("the record details should be displayed")
    public void the_record_details_should_be_displayed() {
        Assert.assertTrue(dp.recordresult(base.memberidLyft), "Record details are not displayed!");
        logger.info("Record details are displayed");
    }

    @When("the user clicks on the Map button")
    public void the_user_clicks_on_the_map_button() {
        dp.clickOnMapButton();
        logger.info("Clicked on Map button");
    }

    @Then("the map should be displayed")
    public void the_map_should_be_displayed() {
        logger.info("Map should be displayed (validation not implemented)");
    }

    @When("the user clicks on the Sign Out button")
    public void the_user_clicks_on_the_sign_out_button() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(dp.btnSignOut()));
        action.moveToElement(dp.btnSignOut()).click().build().perform();
        logger.info("Clicked on Signout button");
        Thread.sleep(2000);
    }

    @And("the page is refreshed")
    public void the_page_is_refreshed() throws InterruptedException {
        base.driver.navigate().refresh();
        Thread.sleep(2000);
        logger.info("Page refreshed");
    }

    @Then("the user should be logged out")
    public void the_user_should_be_logged_out() {
        logger.info("User should be logged out (validation not implemented)");
    }

    @When("the user applies the Parked\\/Pending filter")
    public void the_user_applies_the_parked_pending_filter() throws InterruptedException {
        statusFilterObjects.selectParkedOrPending();
        Thread.sleep(2000);
        logger.info("Applied Parked/Pending filter");
    }

    @Then("the Parked\\/Pending filter should be applied")
    public void the_parked_pending_filter_should_be_applied() {
        Assert.assertTrue(statusFilterObjects.isPendingStatusFilterApplied(), "Parked/Pending filter is not applied!");
        logger.info("Parked/Pending filter applied successfully");
    }

    @When("the user applies the Reassigned filter")
    public void the_user_applies_the_reassigned_filter() throws InterruptedException {
        statusFilterObjects.selectReassigned();
        Thread.sleep(2000);
        logger.info("Applied Reassigned filter");
    }

    @Then("the Reassigned filter should be applied")
    public void the_reassigned_filter_should_be_applied() {
        Assert.assertTrue(statusFilterObjects.isReassignedStatusFilterApplied(), "Reassigned filter is not applied!");
        logger.info("Reassigned filter applied successfully");
    }

    @When("the user applies the Trip Bidding filter")
    public void the_user_applies_the_trip_bidding_filter() throws InterruptedException {
        statusFilterObjects.selectTripBidding();
        Thread.sleep(2000);
        logger.info("Applied Trip Bidding filter");
    }

    @Then("the Trip Bidding filter should be applied")
    public void the_trip_bidding_filter_should_be_applied() {
        Assert.assertTrue(statusFilterObjects.isTripBiddingStatusFilterApplied(), "Trip Bidding filter is not applied!");
        logger.info("Trip Bidding filter applied successfully");
    }

    @When("the user applies the All filter")
    public void the_user_applies_the_all_filter() throws InterruptedException {
        statusFilterObjects.selectAll();
        Thread.sleep(2000);
        logger.info("Applied All filter");
    }

    @Then("the All filter should be applied")
    public void the_all_filter_should_be_applied() {
        Assert.assertTrue(statusFilterObjects.isALLStatusFilterApplied(), "All filter is not applied!");
        logger.info("All filter applied successfully");
    }

    @When("the user applies the Behavioral Health filter")
    public void the_user_applies_the_behavioral_health_filter() {
        tripReasonObjects.selectBehavioralHealth();
        logger.info("Applied Behavioral Health filter");
    }

    @Then("the Behavioral Health filter should be applied")
    public void the_behavioral_health_filter_should_be_applied() {
        Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Behavioral Health"), "Behavioral Health filter is not applied correctly!");
        logger.info("Behavioral Health filter applied successfully");
    }

    @When("the user applies the Chemotherapy filter")
    public void the_user_applies_the_chemotherapy_filter() {
        tripReasonObjects.selectChemotherapy();
        logger.info("Applied Chemotherapy filter");
    }

    @Then("the Chemotherapy filter should be applied")
    public void the_chemotherapy_filter_should_be_applied() {
        Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Chemotherapy"), "Chemotherapy filter is not applied correctly!");
        logger.info("Chemotherapy filter applied successfully");
    }

    @When("the user applies the Dialysis filter")
    public void the_user_applies_the_dialysis_filter() {
        tripReasonObjects.selectDialysis();
        logger.info("Applied Dialysis filter");
    }

    @Then("the Dialysis filter should be applied")
    public void the_dialysis_filter_should_be_applied() {
        Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Dialysis"), "Dialysis filter is not applied correctly!");
        logger.info("Dialysis filter applied successfully");
    }

    @When("the user applies the Hospital Discharge filter")
    public void the_user_applies_the_hospital_discharge_filter() {
        tripReasonObjects.selectHospitalDischarge();
        logger.info("Applied Hospital Discharge filter");
    }

    @Then("the Hospital Discharge filter should be applied")
    public void the_hospital_discharge_filter_should_be_applied() {
        Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Hospital - Discharge"), "Hospital Discharge filter is not applied correctly!");
        logger.info("Hospital Discharge filter applied successfully");
    }

    @When("the user applies the Methadone filter")
    public void the_user_applies_the_methadone_filter() {
        tripReasonObjects.selectMethadone();
        logger.info("Applied Methadone filter");
    }

    @Then("the Methadone filter should be applied")
    public void the_methadone_filter_should_be_applied() {
        Assert.assertTrue(tripReasonObjects.isTripReasonFilterApplied("Methadone"), "Methadone filter is not applied correctly!");
        logger.info("Methadone filter applied successfully");
    }

    @When("the user applies the Plan Name filter")
    public void the_user_applies_the_plan_name_filter() throws InterruptedException {
        columnFilterObjects.insertingPlanName();
        Thread.sleep(2000);
        logger.info("Applied Plan Name filter");
    }

    @Then("the Plan Name filter should be applied")
    public void the_plan_name_filter_should_be_applied() {
        Assert.assertTrue(columnFilterObjects.isFilterApplied("Molina IA Medicaid Waiver"), "Plan Name filter failed!");
        logger.info("Plan Name filter applied successfully");
    }

    @When("the user applies the Member filter")
    public void the_user_applies_the_member_filter() throws InterruptedException {
        columnFilterObjects.insertingMember();
        Thread.sleep(2000);
        logger.info("Applied Member filter");
    }

    @Then("the Member filter should be applied")
    public void the_member_filter_should_be_applied() {
        Assert.assertTrue(columnFilterObjects.isFilterApplied("JONTRAY BLAIR"), "Member filter failed!");
        logger.info("Member filter applied successfully");
    }

    @When("the user applies the Member ID filter")
    public void the_user_applies_the_member_id_filter() throws InterruptedException {
        columnFilterObjects.insertingMemberID();
        Thread.sleep(2000);
        logger.info("Applied Member ID filter");
    }

    @Then("the Member ID filter should be applied")
    public void the_member_id_filter_should_be_applied() {
        Assert.assertTrue(columnFilterObjects.isFilterApplied("2308284D"), "Member ID filter failed!");
        logger.info("Member ID filter applied successfully");
    }

    @When("the user applies the Trip filter")
    public void the_user_applies_the_trip_filter() throws InterruptedException {
        columnFilterObjects.insertingTrip();
        Thread.sleep(2000);
        logger.info("Applied Trip filter");
    }

    @Then("the Trip filter should be applied")
    public void the_trip_filter_should_be_applied() {
        Assert.assertTrue(columnFilterObjects.isFilterApplied("56747261"), "Trip filter failed!");
        logger.info("Trip filter applied successfully");
    }

    @When("the user applies the 0-6 hours filter")
    public void the_user_applies_the_0_6_hours_filter() throws InterruptedException {
        initialRecordCount = timeFilterObjects.getRecordCount();
        timeFilterObjects.clickOnTimeFrameFilter_0_6();
        Thread.sleep(7000);
        logger.info("Applied 0-6 hours filter");
    }

    @Then("the 0-6 hours filter should be applied")
    public void the_0_6_hours_filter_should_be_applied() {
        Assert.assertTrue(timeFilterObjects.isTimeFrameFilterApplied_0_6(), "0-6 hours filter validation failed!");
        logger.info("0-6 hours filter applied successfully");
    }

    @When("the user applies the 6-12 hours filter")
    public void the_user_applies_the_6_12_hours_filter() throws InterruptedException {
        timeFilterObjects.clickOnTimeFrameFilter_6_12();
        Thread.sleep(7000);
        logger.info("Applied 6-12 hours filter");
    }

    @Then("the 6-12 hours filter should be applied")
    public void the_6_12_hours_filter_should_be_applied() {
        Assert.assertTrue(timeFilterObjects.isTimeFrameFilterApplied_6_12(), "6-12 hours filter validation failed!");
        logger.info("6-12 hours filter applied successfully");
    }

    @When("the user applies the 12-24 hours filter")
    public void the_user_applies_the_12_24_hours_filter() throws InterruptedException {
        timeFilterObjects.clickOnTimeFrameFilter_12_24();
        Thread.sleep(7000);
        logger.info("Applied 12-24 hours filter");
    }

    @Then("the 12-24 hours filter should be applied")
    public void the_12_24_hours_filter_should_be_applied() {
        Assert.assertTrue(timeFilterObjects.isTimeFrameFilterApplied_12_24(), "12-24 hours filter validation failed!");
        logger.info("12-24 hours filter applied successfully");
    }

    @When("the user applies the Date Range filter with start date 1st April")
    public void the_user_applies_the_date_range_filter_with_start_date_1st_april() throws InterruptedException {
        timeFilterObjects.clickOnDateRangeFilter();
        Thread.sleep(1000);
        timeFilterObjects.ClickOnStartDate();
        Thread.sleep(2000);
        timeFilterObjects.ClickOn1stOfCurrentMonth();
        Thread.sleep(2000);
        logger.info("Applied Date Range filter with start date 1st April");
    }

    @Then("the appointment date should be within the selected range")
    public void the_appointment_date_should_be_within_the_selected_range() {
        Assert.assertTrue(timeFilterObjects.validateAppointmentDateIsInRange(), "Appointment date is NOT within the selected date range!");
        logger.info("Appointment date is within the selected range");
    }
}


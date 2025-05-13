Feature: Dispatch Application Testing

  Background: 
    Given the user is on the login page 
    When the user enters valid username 
    And the user clicks on Next button after username 
    And the user enters valid password 
    And the user clicks on Sign In button 
    And the user clicks on Yes button 
    Then the application should be successfully opened

  @ClearAllButtonFunctionality @regression
  Scenario: Verify Clear All Button Functionality (TC_17)
    Given the user navigates to the Dispatch tab
    When the user selects Behavioral Health checkbox
    And the user selects Chemotherapy checkbox
    And the user selects Dialysis checkbox
    And the user selects Hospital Discharge checkbox
    And the user selects Methadone checkbox
    And the user clicks on Clear All button
    Then all filters should be cleared
    And the record count should be cleared

  @verifyRecordSelection @smoke @regression
  Scenario: Verify Record Selection (TC_04)
    Given the user navigates to the Dispatch tab
    When the user selects the first record checkbox
    Then the burger icon should be visible
    When the user clicks on the burger icon
    Then the details panel should be displayed

  @verifyAllColumnsVisible @regression
  Scenario: Verify All Columns Visible (TC_10)
    Given the user navigates to the Dispatch tab
    Then the Time Left column should be visible
    And all expected columns should be displayed

  @verifyNavigationBetweenTabs @smoke @regression
  Scenario: Verify Navigation Between Tabs (TC_03)
    When the user navigates to the Dispatch tab
    Then the Dispatch tab URL should be correct
    When the user navigates to the Lyft tab
    Then the Lyft tab URL should be correct
    When the user navigates to the Olos tab
    Then the Olos tab URL should be correct

  @verifyLYFTSearchNoData @regression
  Scenario: Verify LYFT Search No Data (TC_12)
    Given the user navigates to the Lyft tab
    When the user clicks on the search button without entering criteria
    Then the error message "Please enter some search criteria to refine your results" should be displayed

  @verifyRemarkCommentAdd @regression
  Scenario: Verify Remark Comment Add (TC_18)
    Given the user navigates to the Dispatch tab
    When the user clicks on the comment icon for a specific member
    And the user enters the remark "Remark Added"
    And the user clicks on the Add button
    Then the remark should be added successfully
    And the username in the remark should match the logged-in user

  @verifyRemarkAddDialogueBox @regression
  Scenario: Verify Remark Add Dialogue Box (TC_19)
    Given the user navigates to the Dispatch tab
    When the user clicks on the comment icon for a specific member
    Then the remark textarea should be displayed
    And the Add button should be disabled

  @verifyRemarkCloseIcon @regression
  Scenario: Verify Remark Close Icon (TC_20)
    Given the user navigates to the Dispatch tab
    When the user clicks on the comment icon for a specific member
    And the user clicks on the remark close icon
    Then the Dispatch title should be visible

  @verifyTabsOnHomePage @smoke @regression
  Scenario: Verify Tabs on Home Page (TC_02)
    Then the Dispatch tab should be visible
    And the Lyft tab should be visible
    And the Olos tab should be visible

  @verifyOLOSClear @regression
  Scenario: Verify OLOS Clear (TC_07)
    Given the user navigates to the Olos tab
    When the user enters first name, last name, DOB, and phone
    And the user clicks on the clear button
    Then all input fields should be cleared

  @Disabled @regression
  Scenario: Verify OLOS Search (TC_08)
    Given the user navigates to the Olos tab
    When the user enters first name, last name, DOB, and phone
    And the user clicks on the search button
    Then the search results should be displayed

  @verifyLYFTClear @regression
  Scenario: Verify LYFT Clear (TC_13)
    Given the user navigates to the Lyft tab
    When the user enters member ID, first name, last name, DOB, and phone
    And the user clicks on the clear button
    Then all input fields should be cleared

  @verifyLYFTSearch @smoke @regression
  Scenario: Verify LYFT Search (TC_05)
    Given the user navigates to the Lyft tab
    When the user enters a valid member ID
    And the user selects a start date
    And the user clicks on the search button
    Then the search results should display the correct member ID

  @Disabled @regression
  Scenario: Verify Location of the Tips (TC_06)
    Given the user navigates to the Lyft tab
    When the user enters a valid member ID
    And the user selects a start date
    And the user clicks on the search button
    Then the search results should display the correct member ID
    When the user clicks on the View button
    Then the record details should be displayed
    When the user clicks on the Map button
    Then the map should be displayed

  @Disabled @regression
  Scenario: Verify Sign Out (TC_09)
    When the user clicks on the Sign Out button
    And the page is refreshed
    Then the user should be logged out

  @verifyStatusFilter @smoke @regression
  Scenario: Verify Status Filter (TC_15)
    Given the user navigates to the Dispatch tab
    When the user applies the Parked/Pending filter
    Then the Parked/Pending filter should be applied
    When the user applies the Reassigned filter
    Then the Reassigned filter should be applied
    When the user applies the Trip Bidding filter
    Then the Trip Bidding filter should be applied
    When the user applies the All filter
    Then the All filter should be applied

  @verifyTripReasonsFilter @smoke @regression
  Scenario: Verify Trip Reasons Filter (TC_16)
    Given the user navigates to the Dispatch tab
    When the user applies the Behavioral Health filter
    Then the Behavioral Health filter should be applied
    When the user applies the Chemotherapy filter
    Then the Chemotherapy filter should be applied
    When the user applies the Dialysis filter
    Then the Dialysis filter should be applied
    When the user applies the Hospital Discharge filter
    Then the Hospital Discharge filter should be applied
    When the user applies the Methadone filter
    Then the Methadone filter should be applied

  @SearchInAllColumnsFilter @regression
  Scenario: Search in All Columns Filter
    Given the user navigates to the Dispatch tab
    When the user applies the Plan Name filter
    Then the Plan Name filter should be applied
    When the user applies the Member filter
    Then the Member filter should be applied
    When the user applies the Member ID filter
    Then the Member ID filter should be applied
    When the user applies the Trip filter
    Then the Trip filter should be applied

  @verifyTimeFrameFilter @smoke @regression
  Scenario: Verify Time Frame Filter
    Given the user navigates to the Dispatch tab
    When the user applies the 0-6 hours filter
    Then the 0-6 hours filter should be applied
    When the user applies the 6-12 hours filter
    Then the 6-12 hours filter should be applied
    When the user applies the 12-24 hours filter
    Then the 12-24 hours filter should be applied
    When the user applies the Date Range filter with start date 1st April
    Then the appointment date should be within the selected range
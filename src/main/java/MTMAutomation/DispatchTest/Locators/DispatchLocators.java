package MTMAutomation.DispatchTest.Locators;
import MTMAutomation.DispatchTest.Utilities.Base;

public class DispatchLocators extends Base
{

	public  final String dispatch_tab = "//a[text()='Dispatch']";
	public  final String lyft_tab = "//a[text()='Lyft']";
	public  final String member_id = "//input[@class='md-input ember-view']";
	public  final String search_btn="//button[text()='Search']";
	public  final String clear_btn="//button[text()='Clear']";
	public  final String close_icon= "//icon[@class='modal-close-icon']";
	public  final String olos_tab = "//a[text()='OLOS']";
	//public final String comment_icon = "//td[text()='" + memberid + "']/ancestor::tr/child::td[3]";
	public final String comment_icon ="//icon[@title='has remarks']";
	public final String remarkclose_icon = "//icon[@class='modal-close-icon']";
	public final String remarkadd_btn = "//button[text()='Add']";
	public final String remark_textarea = "//textarea[contains(@class, 'ember-text-area')]";
	public final String signout_btn ="//button[text()='Sign Out']";
	public final String searcherror_msg = "//div[contains(@class, 'member-search-msg')]";
	public final String dispatch_title = "//span[text()='Dispatch']";
	public final String remarks_username = "(//div[@class='remark'])[1]/div[1]";
	public final String remarks_text = "(//div[@class='remark'])[1]/div[2]";
	public final String firstname_text ="//label[text()='First Name']/following-sibling::input";
	public final String lastname_text ="//label[text()='Last Name']/following-sibling::input";
	public final String dob_text = "//label[text()='Date Of Birth']/following-sibling::input";
	public final String phone_text ="//label[text()='Phone']/following-sibling::input";
	public final String start_date = "//label[text()='Start Date']/following-sibling::input";
	public final String end_date = "//label[text()='End Date']/following-sibling::input";
	public final String start_year ="(//div[@class='pika-label'])[2]";
	
}


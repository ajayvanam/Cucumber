package stepDefinitions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	@Before
	public void setup() throws IOException {
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		logger=Logger.getLogger("Cucumber");
		PropertyConfigurator.configure("log4j.properties");
		String br = configProp.getProperty("browser");
		if(br.equals("chrome")) {
	    System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
	    driver=new ChromeDriver();
	    logger.info("***************Launching Browser****************");
		}else if(br.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
		    driver=new FirefoxDriver();
		    logger.info("***************Launching Browser****************");
		}
		
	}
	

	@Given("^User Launch Chrome Browser$")
	public void user_Launch_Chrome_Browser() throws Throwable {
		
		lp = new LoginPage(driver);
	}
	
	@When("^User opens URL \"([^\"]*)\"$")
	public void user_opens_URL(String url) throws Throwable {
		logger.info("***************Launching URL****************");
		driver.get(url);
		driver.manage().window().maximize();
	   
	}
	
	@When("^User enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_Email_as_and_Password_as(String email, String password) throws Throwable {
		logger.info("***************User Information****************");
		lp.setUserName(email);
		lp.setPassword(password);
	   
	}
	
	@When("^Click on Login$")
	public void click_on_Login() throws Throwable {
		logger.info("***************Started Login****************");
	    lp.clickLogin();
	}
	
	@Then("^Page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be(String title) throws Throwable {
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			
			driver.close();
			logger.info("***************Login Passed****************");
			Assert.assertTrue(false);
		}else {
			logger.info("***************Launching Failed****************");
			Assert.assertEquals(title, driver.getTitle());
		}
	   
	}
	
	@When("^Click on LogOut link$")
	public void click_on_LogOut_link() throws Throwable {
		logger.info("***************Click on LogOut***************");
		lp.clickLogout();
		Thread.sleep(3000);
	
	}
	
	@Then("^Close Browser$")
	public void close_Browser() throws Throwable {
		logger.info("***************Closing Browser****************");
		driver.close();
		
	}
	
	//Add new Customer feature or Customer feature step definitions
	
	@Then("^User can view Dashboard$")
	public void user_can_view_Dashboard() throws Throwable {
	   addcust = new AddcustomerPage(driver);
	   Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getPageTitle());
	}

	@When("^User click on customers 	Menu$")
	public void user_click_on_customers_Menu() throws Throwable {
	   addcust.clickOnCustomersMenu();
	}

	@When("^click on customers Menu Item$")
	public void click_on_customers_Menu_Item() throws Throwable {
		addcust.clickOnCustomersMenuItem();
	}

	@When("^click on Add new button$")
	public void click_on_Add_new_button() throws Throwable {
	   addcust.clickOnAddnew();
	}

	@Then("^User can view Add new customer page$")
	public void user_can_view_Add_new_customer_page() throws Throwable {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addcust.getPageTitle());	
	    
	}

	@When("^User enter customer info$")
	public void user_enter_customer_info() throws Throwable {
	   String email = randomstring()+"@gmail.com";
	   addcust.setEmail(email);
	   addcust.setPassword("test123");
	   addcust.setGender("Mail");
	   addcust.setFirstName("Ajay");
	   addcust.setLatstName("Vanam");
	   addcust.setDOB("07/05/1990");
	   addcust.setCompanyName("QA");
	   addcust.setAdminContent("This is for Testing........");
	   addcust.setManagerOfVendor("Vendor 2");
	   addcust.setNewsLetter("Test store2");
//	   addcust.setCustomerRoles("Guests");
	   Thread.sleep(3000);
	   
	   
	  
	}

	@When("^click on Save button$")
	public void click_on_Save_button() throws Throwable {
		addcust.clickOnSave();
		Thread.sleep(3000);
	}

	@Then("^User can view confirmation message \"([^\"]*)\"$")
	public void user_can_view_confirmation_message(String arg1) throws Throwable {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	}

	@Then("^close browser$")
	public void close_browser() throws Throwable {
		driver.close();
	}
	

	// Search Customer by EmailID 
	
	
	@When("^Enter customer Email$")
	public void enter_customer_Email() throws Throwable {
	   search = new SearchCustomerPage(driver);	
	   search.setEmailSearch("victoria_victoria@nopCommerce.com");
//	   search.setFirstNameSearch("Victoria");
//	   search.setLastNameSearch("Terces");
//	   search.setCompanyNameSearch("QA");
	}
	
	@When("^Click on search button$")
	public void click_on_search_button() throws Throwable {
	    search.clickOnSearch();
	    Thread.sleep(3000);
	}
	
	@Then("^User should found Email in the Search table$")
	public void user_should_found_Email_in_the_Search_table() {
		
		boolean status=search.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}
	

	@When("^Enter customer FirstName$")
	public void enter_customer_FirstName() throws Throwable {
		search = new SearchCustomerPage(driver);	
		search.setFirstNameSearch("Victoria");
	}
	
	@When("^Enter customer LastName$")
	public void enter_customer_LastName() throws Throwable {
	    search.setLastNameSearch("Terces");
	}
	
	@Then("^User should found Name in the Search table$")
	public void user_should_found_Name_in_the_Search_table() throws Throwable {
		boolean status=search.searchCustomerByName("Victoria Terces");
	    Assert.assertEquals(true, status);
	}
	


}

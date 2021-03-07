package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver ldriver;
	WaitHelper waithelper;
	
	public SearchCustomerPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
		waithelper = new WaitHelper(ldriver);

	}
	@FindBy(how=How.ID,using="SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	@FindBy(how=How.ID,using="SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	@FindBy(how=How.ID,using="SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	@FindBy(how=How.ID,using="SearchCompany")
	@CacheLookup
	WebElement txtCompanyName;
	@FindBy(how=How.ID,using="SearchMonthOfBirth")
	@CacheLookup
	WebElement txthMonthOfBirth;
	@FindBy(how=How.ID,using="SearchDayOfBirth")
	@CacheLookup
	WebElement txthDayOfBirth;
	@FindBy(how=How.XPATH,using="//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement txtCustomerRoles;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement listitemAdministrators;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement listitemGuests;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Registered')]")
	@CacheLookup
	WebElement listitemRegistered;
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Vendors')]")
	@CacheLookup
	WebElement listitemVendors;
	@FindBy(how=How.XPATH,using="//table[@role='grid']")
	@CacheLookup
	WebElement tableSearchResults;
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']")
	WebElement table;
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;
	@FindBy(how=How.ID,using="search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	

	public void setEmailSearch(String email) {
		
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	public void setFirstNameSearch(String fname) {
			
		waithelper.WaitForElement(txtFirstName, 30);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
		
		}
	public void setLastNameSearch(String lname) {
		
		waithelper.WaitForElement(txtLastName, 30);
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	public void setCompanyNameSearch(String cname) {
		waithelper.WaitForElement(txtCompanyName, 30);
		txtCompanyName.clear();
		txtCompanyName.sendKeys(cname);
		
	}
	public void clickOnSearch() {
		
		btnSearch.click();
	}
	
	public int getNoOfRows() {
		return (tableRows.size());
		
	}
	public int getNoOfColumns() {
		return (tableColumns.size());
		
	}
	public boolean searchCustomerByEmail(String email) {
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++) {
			System.out.println(email);
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailid);
			
			if(emailid.equals(email)) {
				
				flag=true;
			}
		}
		
		return flag;
		
		
	}
	public boolean searchCustomerByName(String Name) {
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++) {
			
			String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
			String names[] = name.split(" ");//Separating first name and last name
			
			if(names[0].equals("Victoria") && names[1].equals("Terces")) {
				
				flag=true;
			}
		}
		
		return flag;
		
		
	}

//	By txtEmail = By.id("SearchEmail");
//	By txtFirstName = By.id("SearchFirstName");
//	By txtLastName = By.id("SearchLastName");
//	By txtCompanyName = By.id("SearchCompany");
//	By btnSearch = By.id("search-customers");
//	
}

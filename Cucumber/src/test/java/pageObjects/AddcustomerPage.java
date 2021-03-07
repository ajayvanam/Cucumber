package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {
public WebDriver ldriver;
	
	public AddcustomerPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);

	}
	
//	By lnkCustomers_menu = By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/a/span");
	By lnkCustomers_menu = By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	By lnkCustomers_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
//	By btnAddnew = By.xpath("/html/body/div[3]/div[3]/div/form[1]/div[1]/div/a");
	By btnAddnew = By.xpath("//a[@class='btn bg-blue']");
	By txtEmail = By.xpath("//*[@id=\"Email\"]");
	By txtPassword = By.xpath("//*[@id=\"Password\"]");		
	By txtcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
//	By txtcustomerRoles = By.cssSelector("body.skin-blue.sidebar-mini:nth-child(2) div.wrapper:nth-child(5) div.content-wrapper div.content:nth-child(4) div.form-horizontal div.panel.panel-default.collapsible-panel div.panel-container div.panel-body div.form-group:nth-child(10) div.col-md-9 div.input-group.input-group-required:nth-child(1) div.k-widget.k-multiselect.k-multiselect-clearable:nth-child(1) > div.k-multiselect-wrap.k-floatwrap:nth-child(1)");
	By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");
	By drpmgrOfVendor = By.xpath("//*[@id=\"VendorId\"]");
	By rdMaleGender = By.id("Gender_Male");
	By rdFeMaleGender = By.id("Gender_Female");
	By txtFirstName = By.xpath("//*[@id=\"FirstName\"]");
	By txtLastName = By.xpath("//*[@id=\"LastName\"]");
	By txtDob = By.xpath("//*[@id=\"DateOfBirth\"]");
	By txtCompanyName = By.xpath("//*[@id=\"Company\"]");
	By txtAdminContent = By.xpath("//*[@id=\"AdminComment\"]");
	By btnSave = By.xpath("//button[@name='save']");
	By txtNewsLetter = By.xpath("//*[@id=\"customer-info\"]/div[2]/div[1]/div[9]/div[2]/div/div[1]/div/input");
	
	//Action Methods
	public String getPageTitle() {
		
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		
		ldriver.findElement(lnkCustomers_menu).click();
	}
	public void clickOnCustomersMenuItem() {
			
			ldriver.findElement(lnkCustomers_menuitem).click();
		}
	public void clickOnAddnew() {
		
		ldriver.findElement(btnAddnew).click();
	}
	public void setEmail(String email) {
			
			ldriver.findElement(txtEmail).sendKeys(email);
		}
	public void setPassword(String password) {
		
		ldriver.findElement(txtPassword).sendKeys(password);
	}
public void setCustomerRoles(String role) throws InterruptedException {
		ldriver.findElement(txtcustomerRoles).clear();
		if(!role.equals("Vendors")) {
		ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[1]")).click();
		}
		ldriver.findElement(txtcustomerRoles).click();
		WebElement listitem;
		Thread.sleep(3000);
		if(role.equals("Administrators")) {
			
			listitem=ldriver.findElement(lstitemAdministrators);
		}else if(role.equals("Guests")) {
			
			listitem=ldriver.findElement(lstitemGuests);
		}else if(role.equals("Registered")) {
			
			listitem=ldriver.findElement(lstitemRegistered);
		}else if(role.equals("Vendors")) {
			
			listitem=ldriver.findElement(lstitemVendors);
		}else {
			
			listitem=ldriver.findElement(lstitemGuests);
		}
		listitem.click();
		JavascriptExecutor js =(JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listitem);
		
	}
	public void setManagerOfVendor(String value) {
		
		Select drp 	= new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	public void setGender(String gender) {
		
		if(gender.equals("Male")) {
			
			ldriver.findElement(rdFeMaleGender).click();
		}else if(gender.equals("Female")) {
			
			ldriver.findElement(rdFeMaleGender).click();
		}else{
			
			ldriver.findElement(rdFeMaleGender).click();
		}
		
	}
	public void setFirstName(String fName) {
		
		ldriver.findElement(txtFirstName).sendKeys(fName);
		
	}
	public void setLatstName(String lName) {
			
			ldriver.findElement(txtLastName).sendKeys(lName);
			
		}
	public void setDOB(String dob) {
		
		ldriver.findElement(txtDob).sendKeys(dob);
		
	}
	public void setCompanyName(String comname) {
		
		ldriver.findElement(txtCompanyName).sendKeys(comname);
		
	}
	public void setAdminContent(String content) {
		
		ldriver.findElement(txtAdminContent).sendKeys(content);
		
	}
	public void setNewsLetter(String news) {
		
		
		ldriver.findElement(txtNewsLetter).sendKeys(news);
		
	}
	public void clickOnSave() {
		
		ldriver.findElement(btnSave).click();
	}
}

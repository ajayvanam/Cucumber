package stepDefinitions;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage lp;
	public AddcustomerPage addcust;
	public SearchCustomerPage search;
	public static org.apache.log4j.Logger logger;
	public Properties configProp;
	
	
	public static String randomstring() {
		
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return generatedString1;
	}

}

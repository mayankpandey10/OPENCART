package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyaccount;
@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']") WebElement lnkRegister;
@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") WebElement linkLogin;
@FindBy(xpath = "//input[@placeholder='Search']") WebElement txtSearchbox;
@FindBy(xpath = "//div[@id='search']//button[@type='button']") WebElement btnSearch;

public void clickMyAccount()
{
	lnkMyaccount.click();
}

public  AccountRegisterPage clickRegister()
{
	try {
		lnkRegister.click();
		return new AccountRegisterPage(driver);
	} catch (Exception e) {
		System.out.println("Exception occurred while clicking 'Register': " + e.getMessage());
		return null;
	}
}

public LoginPage clickLogin() {
try {
	linkLogin.click();
	return new LoginPage(driver);
} catch (Exception e) {
	System.out.println("Exception occurred while clicking 'Login': " + e.getMessage());
	return null ;
}
}

public boolean isHomePageExists() {
	try {
		return driver.getTitle().equals("Your Store");

	} catch (Exception e) {
		return false;
	}
}

public void enterProductName(String pName) {
	try {
		
	} catch (Exception e) {
		System.out.println("Exception occurred while entering product name: " + e.getMessage());
	}
}

// Click the search button
public SearchResultsPage clickSearch() {
	try {
		
		return new SearchResultsPage(driver);
	} catch (Exception e) {
		System.out.println("Exception occurred while clicking 'Search': " + e.getMessage());
		return null;
	}
}

}

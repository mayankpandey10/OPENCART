package pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage{
	
	
	public LogoutPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btnContinue;

	public HomePage clickContinue() {
		try {
																						// clickable
			btnContinue.click();
			return new HomePage(driver);
		} catch (TimeoutException e) {
			System.out.println("Continue button not clickable: " + e.getMessage());
			return null;
		}
	}
	
	

}

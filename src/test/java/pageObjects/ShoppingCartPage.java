package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{
	
	
	
	public ShoppingCartPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//*[@id='content']/div[2]/div/table//strong[text()='Total:']//following::td") // Total price label
	WebElement lblTotalPrice;

	@FindBy(xpath = "//a[text()='Checkout']") // Checkout button
	WebElement btnCheckout;

	// Method to get the total price from the shopping cart
	public String getTotalPrice() {
		try {
			
			return lblTotalPrice.getText();
		} catch (Exception e) {
			System.out.println("Unable to retrieve total price: " + e.getMessage());
			return null;
		}
	}

	// Method to click on the Checkout button
	public CheckoutPage clickOnCheckout() {
		try {
																							// clickable
			btnCheckout.click();
			return new CheckoutPage(driver);
		} catch (Exception e) {
			System.out.println("Unable to click Checkout button: " + e.getMessage());
			return null;
		}
	}

}

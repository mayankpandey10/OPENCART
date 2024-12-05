package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{
	
		
	public ProductPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(name = "quantity") // Quantity input box
	WebElement txtQuantity;

	@FindBy(xpath = "//button[@id='button-cart']") // Add to cart button
	WebElement btnAddToCart;

	@FindBy(xpath = "//div[contains(text(),'Success: You have added')]") // Confirmation message
	WebElement cnfMsg;
	
    @FindBy(xpath = "//div[@id='cart']") // Items button
    WebElement btnItems;
    

    @FindBy(xpath = "//strong[normalize-space()='View Cart']") // View Cart link
    WebElement lnkViewCart;
    

	// Set the quantity for the product
	public void setQuantity(String qty) {
		try {
			
			txtQuantity.clear();
			txtQuantity.sendKeys(qty);
		} catch (Exception e) {
			System.out.println("Error setting quantity: " + e.getMessage());
		}
	}

	// Add the product to the cart
	public void addToCart() {
		try {
																							// clickable
			btnAddToCart.click();
		} catch (Exception e) {
			System.out.println("Error adding product to cart: " + e.getMessage());
		}
	}

	// Check if the confirmation message is displayed
	public boolean checkConfMsg() {
		try {
			return cnfMsg.isDisplayed();
		} catch (Exception e) {
			System.out.println("Confirmation message not found: " + e.getMessage());
			return false;
		}
	}
	
	  // Method to click on Items button to navigate to the cart
    public void clickItemsToNavigateToCart() {
        try {
            
            btnItems.click();
        } catch (Exception e) {
            System.out.println("Unable to click Items button: " + e.getMessage());
        }
    }
    
    // Method to click on View Cart link
    public ShoppingCartPage clickViewCart() {
        try {
            
            lnkViewCart.click();
            return new ShoppingCartPage(driver);
        } catch (Exception e) {
            System.out.println("Unable to click View Cart link: " + e.getMessage());
            return null;
        }
    }

}

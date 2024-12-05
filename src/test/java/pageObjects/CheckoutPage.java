package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage{
	
		
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@value='guest']")
    WebElement radioGuest;
    
    @FindBy(xpath = "//input[@id='button-account']")
    WebElement btnContinue;
    
    
    @FindBy(xpath = "//input[@id='input-payment-firstname']")
    WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='input-payment-lastname']")
    WebElement txtLastName;

    @FindBy(xpath = "//input[@id='input-payment-address-1']")
    WebElement txtAddress1;

    @FindBy(xpath = "//input[@id='input-payment-address-2']")
    WebElement txtAddress2;

    @FindBy(xpath = "//input[@id='input-payment-city']")
    WebElement txtCity;

    @FindBy(xpath = "//input[@id='input-payment-postcode']")
    WebElement txtPin;

    @FindBy(xpath = "//select[@id='input-payment-country']")
    WebElement drpCountry;

    @FindBy(xpath = "//select[@id='input-payment-zone']")
    WebElement drpState;

    @FindBy(xpath = "//input[@id='button-payment-address']")
    WebElement btnContinueBillingAddress;

    @FindBy(xpath = "//input[@id='button-shipping-address']")
    WebElement btnContinueDeliveryAddress;

    @FindBy(xpath = "//textarea[@name='comment']")
    WebElement txtDeliveryMethod;

    @FindBy(xpath = "//input[@id='button-shipping-method']")
    WebElement btnContinueShippingAddress;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkboxTerms;

    @FindBy(xpath = "//input[@id='button-payment-method']")
    WebElement btnContinuePaymentMethod;

    @FindBy(xpath = "//strong[text()='Total:']//following::td")
    WebElement lblTotalPrice;

    @FindBy(xpath = "//input[@id='button-confirm']")
    WebElement btnConfOrder;

    @FindBy(xpath = "//*[@id='content']/h1")
    WebElement lblOrderConMsg;

    // Methods with explicit waits and exception handling

    public boolean isCheckoutPageExists()
    {
    	if(driver.getTitle().equals("Checkout"))
    		return true;
    	else
    		return false;
    }
    
    public void chooseCheckoutOption(String checkOutOption)
    {
    	
    	if(checkOutOption.equals("Guest Checkout"))
    	{
    		radioGuest.click();
    	}
    	
    }
    public void clickOnContinue()
    {
    	btnContinue.click();
    }
    
    
    
    public void setFirstName(String firstName) {
        try {
            
            txtFirstName.sendKeys(firstName);
        } catch (Exception e) {
            System.out.println("Error setting first name: " + e.getMessage());
        }
    }

    public void setLastName(String lastName) {
        try {
            
            txtLastName.sendKeys(lastName);
        } catch (Exception e) {
            System.out.println("Error setting last name: " + e.getMessage());
        }
    }

    public void setAddress1(String address1) {
        try {
            
            txtAddress1.sendKeys(address1);
        } catch (Exception e) {
            System.out.println("Error setting address1: " + e.getMessage());
        }
    }

    public void setAddress2(String address2) {
        try {
            
            txtAddress2.sendKeys(address2);
        } catch (Exception e) {
            System.out.println("Error setting address2: " + e.getMessage());
        }
    }

    public void setCity(String city) {
        try {
            
            txtCity.sendKeys(city);
        } catch (Exception e) {
            System.out.println("Error setting city: " + e.getMessage());
        }
    }

    public void setPin(String pin) {
        try {
            
            txtPin.sendKeys(pin);
        } catch (Exception e) {
            System.out.println("Error setting pin: " + e.getMessage());
        }
    }

    public void setCountry(String country) {
        try {
          new Select(drpCountry).selectByVisibleText(country);
        } catch (Exception e) {
            System.out.println("Error selecting country: " + e.getMessage());
        }
    }

    public void setState(String state) {
        try {
            
            new Select(drpState).selectByVisibleText(state);
        } catch (Exception e) {
            System.out.println("Error selecting state: " + e.getMessage());
        }
    }

    public void clickOnContinueAfterBillingAddress() {
        try {
            
            btnContinueBillingAddress.click();
        } catch (Exception e) {
            System.out.println("Error clicking Continue after Billing Address: " + e.getMessage());
        }
    }

    public void clickOnContinueAfterDeliveryAddress() {
        try {
            
            btnContinueDeliveryAddress.click();
        } catch (Exception e) {
            System.out.println("Error clicking Continue after Delivery Address: " + e.getMessage());
        }
    }

    public void setDeliveryMethodComment(String deliveryMsg) {
        try {
            
            txtDeliveryMethod.sendKeys(deliveryMsg);
        } catch (Exception e) {
            System.out.println("Error setting delivery method comment: " + e.getMessage());
        }
    }

    public void clickOnContinueAfterDeliveryMethod() {
        try {
            
            btnContinueShippingAddress.click();
        } catch (Exception e) {
            System.out.println("Error clicking Continue after Delivery Method: " + e.getMessage());
        }
    }

    public void selectTermsAndConditions() {
        try {
            
            chkboxTerms.click();
        } catch (Exception e) {
            System.out.println("Error selecting terms and conditions: " + e.getMessage());
        }
    }

    public void clickOnContinueAfterPaymentMethod() {
        try {
            
            btnContinuePaymentMethod.click();
        } catch (Exception e) {
            System.out.println("Error clicking Continue after Payment Method: " + e.getMessage());
        }
    }

    public String getTotalPriceBeforeConfOrder() {
        try {
            
            return lblTotalPrice.getText();
        } catch (Exception e) {
            System.out.println("Error retrieving total price: " + e.getMessage());
            return null;
        }
    }

    public void clickOnConfirmOrder() {
        try {
            
            btnConfOrder.click();
        } catch (Exception e) {
            System.out.println("Error clicking Confirm Order: " + e.getMessage());
        }
    }

    public boolean isOrderPlaced() {
        try {
            if (driver.switchTo().alert() != null) {
                driver.switchTo().alert().accept();
            }
            
            return lblOrderConMsg.getText().equals("Your order has been placed!");
        } catch (Exception e) {
            System.out.println("Error verifying order placement: " + e.getMessage());
            return false;
        }
    }
	
}

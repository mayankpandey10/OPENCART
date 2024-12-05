package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {
	
	public AccountRegisterPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtfirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtlastname;
    
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
    
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTele;
    
	@FindBy(xpath="//input[@id='input-password']") WebElement txtpass;
    
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtconpass;
    
	@FindBy(xpath="//input[@name='agree']") WebElement chkpolicy;
    
	@FindBy(xpath="//input[@value='Continue']") WebElement btncontinue;
    
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgconfirmation;
	
	public void setFname(String fname)
	{
		txtfirstname.sendKeys(fname);
	}
    
	public void setLname(String flname)
	{
		txtlastname.sendKeys(flname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void setTele(String tele)
	{
		txtTele.sendKeys(tele);
	}
	
	public void setPassword(String pass)
	{
		txtpass.sendKeys(pass);
	}
	
	public void setConPass(String pass)
	{
		txtconpass.sendKeys(pass);
	}
	
	public void PrivacyPolicy()
	{
		chkpolicy.click();
	}
	
	public void clickcontinue()
	{
		btncontinue.click();
	}
	
	public String getConfirmationmsg()
	{
		try {
			return (msgconfirmation.getText());
		}catch (Exception e) {
			return (e.getMessage());
		}
	}

}

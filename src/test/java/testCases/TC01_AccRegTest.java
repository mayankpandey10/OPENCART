package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccRegTest extends BaseClass {
	
	
	
	@Test(groups={"regression", "Master"})
	public void verify_acc_reg()
	{
		logger.info("***** Starting TC01_AccRegTest  *****");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount LInk");
		
		hp.clickRegister();
		logger.info("Clicked on Register LInk");
		
		AccountRegisterPage reg=new AccountRegisterPage(driver);
		
		logger.info("Providing customer details...");
		reg.setFname(generateRandomString().toUpperCase());
		reg.setLname(generateRandomString().toUpperCase());
		reg.setEmail(generateRandomString());
		reg.setTele(generateRandomNumber());
		
		reg.setPassword("qwe1234");
		reg.setConPass("qwe1234");
		
		reg.PrivacyPolicy();
		reg.clickcontinue();
		
		logger.info("Validating expected message....");
		String conf=reg.getConfirmationmsg();
		if(conf.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed.....");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}	
			
		//Assert.assertEquals(conf, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("***** Finished TC01_AccRegTest  *****");
		
	}
	
    
    

}

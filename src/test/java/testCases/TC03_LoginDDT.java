package testCases;

import java.util.NoSuchElementException;

import org.testng.Assert;
//getting data provider from diffrent class we this dataProviderClass=className.class
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="datadriven") 
	public void verify_loginDDT(String email, String pwd, String exp) {
	    logger.info("****  Starting TC03_LoginDDT  ****");
	    
	    try {
	        // HomePage
	        HomePage hp = new HomePage(driver);
	        hp.clickMyAccount();
	        hp.clickLogin();
	        
	        // LoginPage
	        LoginPage lp = new LoginPage(driver);
	        lp.setEmail(email);
	        lp.setPassword(pwd);
	        logger.info("Attempting to log in with email: " + email);
	        lp.clickLogin();
	        
	        // MyAccount
	        MyAccountPage macc = new MyAccountPage(driver);
	        boolean targetPage = macc.isMyAccountPageExists();
	        
	        if ("Valid".equalsIgnoreCase(exp)) {
	            Assert.assertTrue(targetPage, "Login should be successful for valid credentials.");
	            if (targetPage) {
	                macc.clicklogout();
	                logger.info("Logged out successfully.");
	            }
	        } else if ("Invalid".equalsIgnoreCase(exp)) {
	            Assert.assertFalse(targetPage, "Login should fail for invalid credentials.");
	        }
	    } catch (NoSuchElementException e) {
	        logger.error("Element not found: " + e.getMessage());
	        Assert.fail("Test failed due to missing element: " + e.getMessage());
	    } catch (Exception e) {
	        logger.error("An unexpected error occurred: " + e.getMessage());
	        Assert.fail("Test failed due to exception: " + e.getMessage());
	    }
	    
	    logger.info("****  Finished TC03_LoginDDT  ****");
	}

	
	/*@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="datadriven") 
    public void verify_loginDDT(String email, String pwd, String exp) {
        logger.info("****  Starting TC03_LoginDDT  ****");
        
        try {
            // HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();
            
            // LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();
            
            // MyAccount
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();
            
            if ("Valid".equalsIgnoreCase(exp)) {
                Assert.assertTrue(targetPage, "Login should be successful for valid credentials.");
                if (targetPage) {
                    macc.clicklogout();
                }
            } else if ("Invalid".equalsIgnoreCase(exp)) {
                Assert.assertFalse(targetPage, "Login should fail for invalid credentials.");
            }
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
        
        logger.info("****  Finished TC03_LoginDDT  ****");
    }*/
}

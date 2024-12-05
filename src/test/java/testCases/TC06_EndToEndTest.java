package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import pageObjects.MyAccountPage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC06_EndToEndTest extends BaseClass {

    // Test method for executing end-to-end user flow
    @Test(groups = {"Master"})
    public void executeEndToEndTest() throws InterruptedException {
        // Initialize SoftAssert for non-blocking assertions
        SoftAssert softAssert = new SoftAssert();

        // Navigate to the application URL
        String appURL = p.getProperty("appURL");
        driver.get(appURL);
        logger.info("Navigated to application URL: " + appURL);

        // Step 1: Perform Account Registration
        String email = performRegistration(softAssert);

        // Step 2: Logout after successful registration
        performLogout();

        // Step 3: User Login with the registered email
        performLogin(email, softAssert);

        // Step 4: Search product and add to cart
        addProductToCart(softAssert);

        // Step 5: Verify Shopping Cart contents
        verifyShoppingCart(softAssert);

        // Step 6: Perform Checkout process
        //performCheckout(); //feature not available since it is demo site. so commented

        // Finalize all soft assertions and report any failures
        softAssert.assertAll();
    }

    // Method to handle user registration
    private String performRegistration(SoftAssert softAssert) throws InterruptedException {
        // Navigate to Registration Page
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        AccountRegisterPage registrationPage = homePage.clickRegister();

        // Generate random user details for registration
        String firstName = generateRandomEmail(5).toUpperCase();
        String lastName = generateRandomEmail(5).toUpperCase();
        String email = generateRandomString(); // Randomized email for testing

        // Fill out the registration form
        registrationPage.setFname(firstName);
        registrationPage.setLname(lastName);
        registrationPage.setEmail(email);
        registrationPage.setTele("1234567");
        registrationPage.setPassword("test123");
        registrationPage.setConPass("test123");
        registrationPage.PrivacyPolicy();
        registrationPage.clickcontinue();

        // Verify the registration success message
        String confirmationMessage = registrationPage.getConfirmationmsg();
        logger.info("Registration Confirmation: " + confirmationMessage);
        softAssert.assertEquals(confirmationMessage, "Your Account Has Been Created!");

        return email;  // Returning the registered email for subsequent login
    }

    // Method to perform logout action
    private void performLogout() throws InterruptedException {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        LogoutPage logoutPage = myAccountPage.clicklogout();
             
        logger.info("Clicking 'Continue' button to return to the Home Page...");
        HomePage postLogoutHomePage = logoutPage.clickContinue();

        // Step 7: Verify that Home Page is displayed after logout
        logger.info("Verifying if Home Page is displayed after logout...");
        boolean isHomePageVisible = postLogoutHomePage.isHomePageExists();
        softAssert.assertTrue(isHomePageVisible, "Logout failed: Home Page is not displayed.");
        logger.info(isHomePageVisible ? "Logout successful, navigated to Home Page."
                                      : "Logout verification failed.");
    }

    // Method to handle user login with the registered email
    private void performLogin(String email, SoftAssert softAssert) throws InterruptedException {
        logger.info("Logging into the application with email: " + email);

        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.setEmail(email);
        loginPage.setPassword("test123");
        loginPage.clickLogin();

        // Verify successful login and navigation to My Account Page
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        logger.info("Is My Account page displayed? " + myAccountPage.isMyAccountPageExists());
        softAssert.assertEquals(myAccountPage.isMyAccountPageExists(), true);
    }

    // Method to search and add a product to the cart
    private void addProductToCart(SoftAssert softAssert) throws InterruptedException {
        logger.info("Searching and adding product to the cart...");

        HomePage homePage = new HomePage(driver);
        String productName = p.getProperty("searchProductName");
        homePage.enterProductName(productName);
        SearchResultsPage searchResultsPage = homePage.clickSearch();

        ProductPage productPage = null;

        // Check if the product exists in the search results and add it to the cart
        if (searchResultsPage.isProductExist(productName)) {
            productPage = searchResultsPage.selectProduct(productName);
            productPage.setQuantity(p.getProperty("productQuantity"));
            productPage.addToCart();
        }

        // Wait for confirmation message after adding product to cart
        Thread.sleep(3000);
        logger.info("Product added to cart: " + productPage.checkConfMsg());
        softAssert.assertEquals(productPage.checkConfMsg(), true);
    }

    // Method to verify items in the shopping cart
    private void verifyShoppingCart(SoftAssert softAssert) throws InterruptedException {
        logger.info("Verifying items in the shopping cart...");

        ProductPage productPage = new ProductPage(driver);
        productPage.clickItemsToNavigateToCart();
        ShoppingCartPage shoppingCartPage = productPage.clickViewCart();
        Thread.sleep(3000);

        // Verify the total price in the shopping cart
        String totalPrice = shoppingCartPage.getTotalPrice();
        logger.info("Total price in shopping cart: " + totalPrice);
        softAssert.assertEquals(totalPrice, p.getProperty("totalPrice"));
    }

    // Method for filling guest details (commented out due to demo limitations)
    /*
    private void fillGuestDetails(CheckoutPage checkoutPage) throws InterruptedException {
        checkoutPage.setFirstName(generateString().toUpperCase());
        Thread.sleep(1000);
        checkoutPage.setLastName(generateString().toUpperCase());
        Thread.sleep(1000);
        checkoutPage.setAddress1("address1");
        Thread.sleep(1000);
        checkoutPage.setAddress2("address2");
        Thread.sleep(1000);
        checkoutPage.setCity("Delhi");
        Thread.sleep(1000);
        checkoutPage.setPin("500070");
        Thread.sleep(1000);
        checkoutPage.setCountry("India");
        Thread.sleep(1000);
        checkoutPage.setState("Delhi");
        Thread.sleep(1000);
        checkoutPage.clickOnContinueAfterBillingAddress();
        Thread.sleep(1000);
        checkoutPage.clickOnContinueAfterDeliveryAddress();
        Thread.sleep(1000);
        checkoutPage.setDeliveryMethodComment("testing...");
        Thread.sleep(1000);
        checkoutPage.clickOnContinueAfterDeliveryMethod();
        Thread.sleep(1000);
        checkoutPage.selectTermsAndConditions();
        Thread.sleep(1000);
        checkoutPage.clickOnContinueAfterPaymentMethod();
        Thread.sleep(2000);
    }
    */

}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;
import testBase.BaseClass;

public class TC05_AddToCart extends BaseClass {

    @Test(groups = { "Master" })
    public void testAddToCart() throws InterruptedException {
        // Step 1: Navigate to application URL
        String appURL = p.getProperty("appURL");
        driver.get(appURL);
        logger.info("Navigated to application URL: " + appURL);

        // Initialize the HomePage
        HomePage homePage = new HomePage(driver);
        String productName = p.getProperty("searchProductName");

        // Step 2: Enter the product name in the search text box
        homePage.enterProductName(productName);
        logger.info("Entered product name in the search box: " + productName);

        // Step 3: Perform the search
        SearchResultsPage searchResultsPage = homePage.clickSearch();
        logger.info("Clicked the Search button.");

        // Step 4: Verify the product exists in search results
        if (searchResultsPage.isProductExist(productName)) {
            logger.info("Product found in search results: " + productName);

            // Step 5: Select the product from the search results
            ProductPage productPage = searchResultsPage.selectProduct(productName);
            logger.info("Selected product: " + productName);

            // Step 6: Set the desired quantity
            productPage.setQuantity(p.getProperty("productQuantity"));
            logger.info("Set quantity to: " + p.getProperty("productQuantity"));

            // Step 7: Add the product to the cart
            productPage.addToCart();
            logger.info("Clicked 'Add to Cart' for product: " + productName);

            // Step 8: Verify the success message
            boolean isSuccessMessageDisplayed = productPage.checkConfMsg();
            Assert.assertTrue(isSuccessMessageDisplayed, "Success message not displayed!");
            logger.info("Success message verified successfully.");
        } else {
            logger.error("Product not found in search results: " + productName);
            Assert.fail("Product not found in search results.");
        }
    }

}

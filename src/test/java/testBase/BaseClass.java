package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;  //log4j
public Properties p;
public SoftAssert softAssert = new SoftAssert();

    @BeforeClass (groups={"sanity", "regression","Master",})
    @Parameters({"OS","browser"})
	public void setup(String os, String br) throws IOException, URISyntaxException 
	{
    	// LOading config.properties file
    	FileReader file=new FileReader("./src//test//resources//config.properties");
    	p=new Properties();
    	p.load(file);
		
    	logger=LogManager.getLogger(this.getClass());
    	
    	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
    	{
    		DesiredCapabilities capabilities=new DesiredCapabilities();
    		
    		//os
    		if(os.equalsIgnoreCase("windows"))
    		{
    			capabilities.setPlatform(Platform.WIN10);
    		}
    		else if(os.equalsIgnoreCase("linux"))
    		{
    			capabilities.setPlatform(Platform.LINUX);
    		}
    		else
    		{
    			System.out.println("no matching os");
    			return;
    		}
    		//browser
    		switch(br.toLowerCase())
    		{
    		case "chrome" : capabilities.setBrowserName("chrome"); break;
    		case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
    		default : System.out.println("no matching browser"); return;
    		}
    		
    		//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
    		driver = new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(), capabilities);
    		/*try {
    		    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    		} catch (MalformedURLException e) {
    		    e.printStackTrace();  // Handle the exception, log it, or throw a runtime exception
    		}*/

    	}
    	if(p.getProperty("execution_env").equalsIgnoreCase("local"))
    	{
    		switch(br.toLowerCase())
    		{
    		case "chrome" : driver=new ChromeDriver(); break;
    		case "edge" : driver=new EdgeDriver(); break;
    		default : System.out.println("Invalid browser name..."); return;
    		}
        	
    	}
    	
    	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		
	}
	
	@AfterClass (groups={"sanity", "regression","Master",})
	public void tearDown()
	{
		driver.quit();
	}
	
	// generate random email
	public String generateRandomString() {
        String randomString = generateRandomEmail(5); // Generates a random string of length 5
        return randomString + "@gmail.com";
    }

    // Custom method to generate a random string
    public String generateRandomEmail(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }
	
 // Generates a random number
    public String generateRandomNumber() {
        String randomNum = generateRandomphone(5); // Generates a random string of length 5
        return randomNum;
    }

    // Custom method to generate a random string
    public String generateRandomphone(int length) {
        String characters = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }
    
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        // Create the screenshots directory if it doesn't exist
        File screenshotDir = new File(System.getProperty("user.dir"), "screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        // Construct the target file path
        File targetFile = new File(screenshotDir, tname + "_" + timeStamp + ".png");

        // Copy the screenshot to the target location
        FileUtils.copyFile(sourceFile, targetFile);

        return targetFile.getAbsolutePath();
    }
	

}

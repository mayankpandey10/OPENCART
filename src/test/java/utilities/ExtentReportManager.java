package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent; 
	public ExtentTest test; 
	
	String repName;
	
	public void onStart(ITestContext testcontext){
		
		/*
		SimpleDateFprmat df=new DimpleDateFormat("yyyy.MM.dd.Hh.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=dt.format(dt);
		*/
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.Hh.mm.ss").format(new Date());
		
		repName = "Test-Report-" + timestamp + ".html";
		//sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName); //location of the report
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName);
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // title of report
		sparkReporter.config().setReportName("opencart Functional Testing");// name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		 // Validate parameters
	    String os = testcontext.getCurrentXmlTest().getParameter("os");
	    if (os != null) {
	        extent.setSystemInfo("Operating System", os);
	    }

	    String browser = testcontext.getCurrentXmlTest().getParameter("browser");
	    if (browser != null) {
	        extent.setSystemInfo("Browser", browser);
	    }

	    List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
	    if (!includedGroups.isEmpty()) 
	    {
	        extent.setSystemInfo("Groups", includedGroups.toString());
	    }
	    
	}	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.PASS, result.getName()+"get sucessfully executed");
		
	}
	
	public void onTestFailure(ITestResult result) {
	    test = extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());

	    test.log(Status.FAIL, result.getName() + " failed");
	    if (result.getThrowable() != null) {
	        test.log(Status.INFO, result.getThrowable().getMessage());
	    }

	    try {
	        String imgPath = new BaseClass().captureScreen(result.getName());
	        if (imgPath != null) {
	            test.addScreenCaptureFromPath(imgPath);
	        } else {
	            test.log(Status.WARNING, "Screenshot not captured.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
		public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage() );
	}
	
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		 // Log the report path
	    System.out.println("Extent Report Path: " + extentReport.getAbsolutePath());
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*try { 
			URL url = new URL("file:///"+ System.getProperty("user.dir")+"\\reports\\" +repName);
				
		// Create the email message
		ImageHtmlEmail email= new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("mail id", "pass"));
		email.setSSLOnConnect(true);
		email.setFrom("mail id"); // sender
		email.setSubject("Test Result");
		email.setMsg("Please find attached report...");
		email.addTo("mailid"); // Receiver
		email.attach(url, "extent report", "please check report...");
		email.send(); //send the email
				
		}catch(Exception e)
		{
			e.printStackTrace(); 
			}*/
		
		// Given by gpt
		/* try {
		    // Constructing the file URL
		    String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName;
		    URL url = new URL("file:///" + reportPath);

		    // Create the email message
		    ImageHtmlEmail email = new ImageHtmlEmail();
		    email.setDataSourceResolver(new DataSourceUrlResolver(url));
		    email.setHostName("smtp.googlemail.com");
		    email.setSmtpPort(465);
		    email.setAuthenticator(new DefaultAuthenticator("your_email@gmail.com", "your_password")); // Use environment variables for security
		    email.setSSLOnConnect(true);
		    email.setFrom("your_email@gmail.com"); // Sender
		    email.setSubject("Test Result");
		    email.setMsg("Please find attached report...");
		    email.addTo("receiver_email@example.com"); // Receiver
		    email.attach(url, "Extent Report", "Please check the report...");

		    // Send the email
		    email.send();
		    
		} catch (Exception e) {
		    // Log the exception or handle it as needed
		    e.printStackTrace(); 
		}
		*/
	}
	

}

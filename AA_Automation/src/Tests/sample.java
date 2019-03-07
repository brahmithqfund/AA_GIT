package Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class sample {
		
	WebDriver driver;
	static ExtentReports reports;
	ExtentTest test;
	
	public void login()
	{
		System.setProperty("webdriver.ie.driver","E:/QC_Workspace/AA_Automation/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
		  driver.findElement(By.name("loginRequestBean.userId")).sendKeys("TN4353");
	        test.log(LogStatus.PASS, "Username is entered: ");
	        
	        driver.findElement(By.name("loginRequestBean.password")).sendKeys("1234");
	        test.log(LogStatus.PASS, "Password is entered: ");
	        
	        //writeText(By.name(StoreId), storenumber);
	        driver.findElement(By.name("loginRequestBean.locNbr")).sendKeys("4353");;
	        test.log(LogStatus.PASS, "Storenumber is entered");
	        //Click Login Button
	        driver.findElement(By.name("login")).click();
	        test.log(LogStatus.PASS, "Clicked on Submit button");
	
	}
	
	

}


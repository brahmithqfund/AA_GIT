package Tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.BorrowerRegistrationpage;
import Pages.CSRLoginpage;
import Tests.ExecuteScripts;
import Utilities.ExtentReports.Excel;

public class LOCICash_Draw_Stmnt_Payoff_Draw_EODDueDate_Stmnt_Payoff_PayOffVoidDueDate_DLQ {

	

	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;


	@BeforeClass

	public synchronized void initialize() {

		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());


		String filename="AA_LOC_RegressionScenario_Scenario.No_19_"+timestamp+".html";

		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/AA_LOC_RegressionScenarios_/AA_LOC_RegressionScenario_Scenario.No_19_/"+filename, true);
	}

	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
	}


	public void Login (String username,String password,String storenumber) throws InterruptedException {										
		//Launch URL
		driver.get(appUrl);
		test.log(LogStatus.INFO, "CSR Application is launched");
		driver.manage().window().maximize();
		String usenameId = "loginRequestBean.userId";
		String passwordId = "loginRequestBean.password";
		String StoreId = "loginRequestBean.locNbr";
		String Login = "login";


		driver.findElement(By.name(usenameId)).clear();
		driver.findElement(By.name(usenameId)).sendKeys(username);
		//Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Username is entered: "+username);

Thread.sleep(2000);
		driver.findElement(By.name(passwordId)).clear();
		driver.findElement(By.name(passwordId)).sendKeys(password);
		//Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Password is entered: "+password);
		Thread.sleep(2000);
		driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
		//Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
		Thread.sleep(2000);
		driver.findElement(By.name(Login)).click();
		//Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Clicked on Submit button");
	}



	public boolean IsElementExits(String Value) {
		int secondsToWait = 5;

		try {
			new WebDriverWait(driver, secondsToWait).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Value)));
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}


	public void IETaskKiller() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
	}
	

public void NewLoan(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);   	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{		
			String State = TestData.getCellData(sheetName,"StateID",row);
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			System.out.println(ProductID);
			//String UserName = TestData.getCellData(sheetName,"UserName",row);
			//String Password = TestData.getCellData(sheetName,"Password",row);
			String ProductType = TestData.getCellData(sheetName,"ProductType",row);
			String ProductName = TestData.getCellData(sheetName,"ProductName",row);
			//String Term = TestData.getCellData(sheetName,"Term",row);
			String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
			String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
			//System.out.println(Term);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			//String stateProduct=State+" "+ProductID;
			String stateProductType=State+" "+ProductType;
			String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			System.out.println(ESign_CollateralType);
			String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
			String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
			String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
			String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
			String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
			String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
			String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
			String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
			String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
			String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
			String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
			System.out.println(last4cheknum);
			System.out.println(stateProductType);
		
			test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
			 String Parent_Window = driver.getWindowHandle();  
			for( String winHandle1 : driver.getWindowHandles())
			{
			    driver.switchTo().window(winHandle1);
			}			
			 driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 //	Selection of Product based on the Name provided in Test Data
			 if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
			 {
				 test.log(LogStatus.INFO, "NewLoan Draw Transaction with-SSN: " +SSN +" :: Starts");
				 
				 //driver.findElement(By.xpath("//input[contains(text(),"+stateProduct+")]")).click();
			//test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);	
			
				if(ProductName.equals("Line of Credit"))
				{
					
					if(StoreID.equals("4330"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
					}
					if(StoreID.equals("4324"))
					{
						//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						
					driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
					test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					
					}
					if(StoreID.equals("1343"))
					{
						//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
				}

				driver.findElement(By.name("ShareScreenBtn")).click();
				test.log(LogStatus.PASS, "ShareScreen Button clicked");

				for( String winHandle1 : driver.getWindowHandles())

				{
					if(!(winHandle1.equals(Parent_Window)))
					{
						driver.switchTo().window(winHandle1);
						Thread.sleep(6000);
						driver.findElement(By.name("confirmSummary")).click();
						test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
					}

				}
				Thread.sleep(6000);
				driver.switchTo().window(Parent_Window);

				for( String winHandle1 : driver.getWindowHandles())

				{

					driver.switchTo().window(winHandle1);

				}                    

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");
				driver.findElement(By.id("LoanButtonId")).click();

				test.log(LogStatus.PASS, "Clicked on New Loan button");
				if(ProductID.equals("LOC"))
				{
				
					test.log(LogStatus.INFO, "Navigated to New Loan Screen");
					driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
					test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
					Thread.sleep(5000);
				//	driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
				//	test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
					driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
					test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
					driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
					test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
					driver.findElement(By.name("finishadvance")).click();
					test.log(LogStatus.PASS, "Click on Finish LOC Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.xpath("//*[@id='OKBut']")).click();	
					 //driver.findElement(By.name("OKBut")).click();
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
					test.log(LogStatus.PASS, "click on Yes button ");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					{
						test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
						
					}
					else
					{
						test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
					}
				}
					
				//html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/input
			 	}
				
		}
	}

}


public void DrawLoan(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				String State = TestData.getCellData(sheetName,"StateID",row);
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
				String AllowPromotion = TestData.getCellData(sheetName,"Allow Promotion",row);
				String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
				String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
				String ESign_Checks = TestData.getCellData(sheetName,"ESign_Checks",row);
				String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
				String ESign_CheckNbr = TestData.getCellData(sheetName,"ESign_CheckNbr",row);			
				String last4cheknum= ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				this.Login(UserName,Password,StoreID);	

				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				test.log(LogStatus.INFO, "DrawLoan with-SSN: " +SSN +" :: Starts");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Draw");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 driver.findElement(By.name("loanAmt")).clear();
							
							
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														
								
							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
						 
							try { 
							    Alert alert = driver.switchTo().alert();
							    alert.accept();
							    //if alert present, accept and move on.														
								
							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}
							Thread.sleep(2000);
						 driver.findElement(By.name("loanAmt")).sendKeys("50");	
							Thread.sleep(2000);
						 driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
						 test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						 test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
						 driver.findElement(By.name("disbAmtFirst")).sendKeys("50");					
						 test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
						 driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						 test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
							Thread.sleep(2000);
							driver.findElement(By.name("finishadvance")).click();
							test.log(LogStatus.PASS, "Click on Finish Loan Button");
						
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							
							if(driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr[1]/td")).isDisplayed())
							{
								test.log(LogStatus.INFO, "NewLoan Draw Transaction with-SSN: " +SSN +" :: is Successful");
							}
							else
							{
								test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
							}
					    	
						 }
					
			}
			
		}

public void StatementGeneration(String SSN,String FileName) throws Exception
{

        Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
        int lastrow=TestData.getLastRow("NewLoan");
        System.out.println("NewLoan "+lastrow);
        String sheetName="NewLoan";
        for(int row=2;row<=lastrow;row++)
        {
                String RegSSN = TestData.getCellData(sheetName,"SSN",row);
                if(SSN.equals(RegSSN))
                {
                        String TxnType=TestData.getCellData(sheetName,"TxnType",row);
                        String TenderType = TestData.getCellData(sheetName,"TenderType",row);
                        String ProductID=TestData.getCellData(sheetName,"ProductID",row);
                        String UserName = TestData.getCellData(sheetName,"UserName",row);
                        String Password = TestData.getCellData(sheetName,"Password",row);
                        String StoreID = TestData.getCellData(sheetName,"StoreID",row);
                        String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
                        System.out.println(AdminURL);
                        test.log(LogStatus.INFO, "Scheduler-Store Aging");

                        System.out.println(ProductID);
                        String AppURL = TestData.getCellData(sheetName,"AppURL",row);
                        appUrl = AppURL;
                        this.Login(UserName,Password,StoreID);
                        String SSN1 = SSN.substring(0, 3);
                        String SSN2 = SSN.substring(3,5);
                        String SSN3 = SSN.substring(5,9);
                        Thread.sleep(5000);
                        Thread.sleep(1000);
                        driver.switchTo().frame("topFrame");
                        driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
                        test.log(LogStatus.PASS, "Clicked on Loan Transactions");
                        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                        driver.findElement(By.cssSelector("li[id='911101']")).click();
                        test.log(LogStatus.PASS, "Clicked on Transactions");
                        driver.switchTo().frame("main");
                        driver.findElement(By.name("ssn1")).sendKeys(SSN1);
                        test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
                        driver.findElement(By.name("ssn2")).sendKeys(SSN2);
                        test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
                        driver.findElement(By.name("ssn3")).sendKeys(SSN3);
                        test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
                        driver.findElement(By.name("submit1")).click();
                        test.log(LogStatus.PASS, "Click on submit Button");
                        for(String winHandle : driver.getWindowHandles()){
                                driver.switchTo().window(winHandle);
                        }
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        driver.findElement(By.name("button")).click();
                        test.log(LogStatus.PASS, "Click on GO Button");
                        for(String winHandle : driver.getWindowHandles()){
                                driver.switchTo().window(winHandle);
                        }
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");


                        if(ProductID.equals("LOC"))
                        {
                                ///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
                                driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
                        }
                        //  driver.findElement(By.name("button")).click();
                        test.log(LogStatus.PASS, "Click on GO Button");
                        for( String winHandle1 : driver.getWindowHandles())
                        {
                                driver.switchTo().window(winHandle1);
                        }
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        driver.findElement(By.name("transactionList")).sendKeys("History");
                        if(ProductID.equals("LOC"))
                        {
                                driver.findElement(By.name("button")).click();
                        }

                        for( String winHandle1 : driver.getWindowHandles())
                        {
                                driver.switchTo().window(winHandle1);
                        }
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        String DueDate=null;

                        /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
                        }

                         //String winHandleBefore = driver.getWindowHandle();
                         for(String winHandle : driver.getWindowHandles()){
                                    driver.switchTo().window(winHandle);
                                }
                         Thread.sleep(8000);
                          // driver.findElement(By.xpath("//*[@id='home']")).click();*/

                        //DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
                        DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
                                                               //*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
                        test.log(LogStatus.PASS, "Captured Statement Generation Date: "+DueDate);
                        //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
                        System.out.print(DueDate);
                        driver.close();

                        driver = new InternetExplorerDriver();
                        driver.get(AdminURL);
                        test.log(LogStatus.INFO, "Admin portal is launched");
            			driver.manage().window().maximize();
                        // storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
                        DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
                        driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
                        test.log(LogStatus.PASS, "Username is entered: admin");
                        driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
                        test.log(LogStatus.PASS, "Password is entered: "+Password);
                        //Click Login Button
                        driver.findElement(By.name("login")).click();
                        test.log(LogStatus.PASS, "Clicked on Submit button");
                        	Thread.sleep(8000);
                        	Thread.sleep(8000);
                        String DueDate0[] =DueDate.split("/");
                        String DueDate1 = DueDate0[0];
                        String DueDate2 = DueDate0[1];
                        String DueDate3 = DueDate0[2];
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("topFrame");
                        Thread.sleep(8000);
                    	Thread.sleep(8000);
                        WebDriverWait wait = new WebDriverWait(driver, 10000);
   					    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
                        driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
                        test.log(LogStatus.PASS, "Clicked on Transactions");
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        Thread.sleep(5000);
                        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
                        driver.findElement(By.linkText("Borrower")).click();
                        test.log(LogStatus.PASS, "Clicked on Borrower");
                        Thread.sleep(5000);
                        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
                        driver.findElement(By.linkText("Process Date Change")).click();
                        test.log(LogStatus.PASS, "Clicked on Process Date Change");
                        //Thread.sleep(2000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/div[6]/a/img"));
                         Actions action = new Actions(driver);
                         action.moveToElement(element).build().perform();
                        Thread.sleep(6000);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        driver.findElement(By.name("storeCode")).click();
                        //driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
                        driver.findElement(By.name("storeCode")).sendKeys(StoreID);
                        test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
                        Thread.sleep(2000);
                        driver.findElement(By.name("beginMonth")).clear();
                        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
                        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
                        driver.findElement(By.name("beginDay")).clear();
                        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
                        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
                        driver.findElement(By.name("beginYear")).clear();
                        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
                        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
                        driver.findElement(By.name("btnPreview")).click();
                        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
                        Thread.sleep(1000);
                        Thread.sleep(8000);
                        test.log(LogStatus.PASS, "Clicked on submit button");
                        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
                        {
                                test.log(LogStatus.PASS, "Process Date updated successfully");
                                driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
                        }
                        else
                        {
                                test.log(LogStatus.FAIL, "Process Date updated successfully.");
                        }


                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("topFrame");
                        driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
                        test.log(LogStatus.PASS, "Clicked on Transactions");
                        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                        driver.findElement(By.linkText("ACH")).click();
                        test.log(LogStatus.PASS, "Clicked on ACH");


                        Thread.sleep(5000);
                        driver.findElement(By.linkText("LOC")).click();
                        test.log(LogStatus.PASS, "Clicked on LOC");

                        //driver.switchTo().defaultContent();
                        //driver.switchTo().frame("mainFrame");
                        Thread.sleep(5000);
                        driver.findElement(By.linkText("Unsecure Loc Statement")).click();
                        test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

                        ///Thread.sleep(6000);  /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");
                        // /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img
                        WebElement elements = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
                        Actions actions = new Actions(driver);
                        actions.moveToElement(elements).build().perform();

                        Thread.sleep(6000);

                        driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
                        test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);


                        driver.switchTo().defaultContent();
                        driver.switchTo().frame("mainFrame");
                        driver.switchTo().frame("main");

                        driver.findElement(By.name("beginMonth")).click();
                        driver.findElement(By.name("beginMonth")).clear();
                        driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
                        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
                        driver.findElement(By.name("beginDay")).clear();
                        driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
                        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
                        driver.findElement(By.name("beginYear")).clear();
                        driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
                        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
                        driver.findElement(By.name("submit")).click();
                        test.log(LogStatus.PASS, "Clicked on submit button");
                        test.log(LogStatus.PASS, "Statement Generated");
                }
        }
}

public void DrawerDeassign(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			Thread.sleep(5000);
			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Cash Management");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			//driver.findElement(By.cssSelector("li[id='911101']")).click();	
			driver.findElement(By.linkText("Drawer")).click();
			test.log(LogStatus.PASS, "Clicked on Drawer");	
			driver.findElement(By.linkText("Deassign")).click();
			test.log(LogStatus.PASS, "Clicked on Deassign");	
			driver.switchTo().frame("main");		
			driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
			test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
			//driver.findElement(By.name("drawerDeassignRequestBean.currentCashBalance")).sendKeys("0");
			driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Banker PIN# is enetered as"+Password);	
			driver.findElement(By.name("drawerdeassign")).click();
			test.log(LogStatus.PASS, "Click on Finish De-assign Button");
			try{
				driver.close();
			}
			catch (Exception e) {
				//do what you normally would if you didn't have the alert.
			}
			Thread.sleep(2000);
			driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).clear();
			driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
			test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
			Thread.sleep(2000);
			driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);				
			driver.findElement(By.name("drawerdeassign")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table")).isDisplayed())
			{
				 WebElement htmltable=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table"));	
				    
					List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
					System.out.println("current row num "+rows.size());
					int count=0;							
					 count=driver.findElements(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr")).size();				 				
					for(int rnum=1;rnum<rows.size();rnum++)
					{                      
						System.out.println("current row num "+rnum);						
					//List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));												
														
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[5]/select")).sendKeys("Delete");
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[6]/input")).click();						
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						Thread.sleep(5000);
					}
			}
			String DrawerOverShortAmount =driver.findElement(By.name("drawerRequestBean.drawerOverShort")).getAttribute("value");
			driver.findElement(By.name("drawerRequestBean.amount")).sendKeys(DrawerOverShortAmount);
			test.log(LogStatus.PASS, "Amount entered as "+DrawerOverShortAmount);
			driver.findElement(By.name("drawerRequestBean.primary")).sendKeys("Cash Handling");
			test.log(LogStatus.PASS, "Primary Reason is selected as Cash Handling");
			driver.findElement(By.name("drawerRequestBean.notes")).sendKeys("Notes");
			test.log(LogStatus.PASS, "Notes Entered ");	
			driver.findElement(By.name("bt_AddDrawer")).click();
			test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
			Thread.sleep(3000);
			driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();

			test.log(LogStatus.PASS, "Click on Finish Drawer O/S");
			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
			{

				test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
			}
			else
			{
				test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
			}
		}
	}
}
public void StatementGeneration_EODProcessing(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			Thread.sleep(5000);	    

			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Daily Processing");
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.name("eod")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

			Thread.sleep(4000);
			// driver.findElement(By.name("requestBean.comments")).click();
			driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
			// requestBean.comments
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");
			Thread.sleep(4000);
			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}
			Thread.sleep(4000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");
			Thread.sleep(1000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			//String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
			String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

			driver.findElement(By.name("requestBean.amount")).sendKeys(SafeOverShortAmount);

			/// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/table/tbody/tr[7]/td[3]

			// driver.findElement(By.name("requestBean.amount")).sendKeys("SafeOverShortAmount");
			test.log(LogStatus.PASS,"Enter the Balance 50");
			driver.findElement(By.name("requestBean.primary")).sendKeys("Deposit Issue");
			test.log(LogStatus.PASS, "Primary Reason is selected as Deposit Issue");
			driver.findElement(By.name("requestBean.notes")).sendKeys("Notes");
			test.log(LogStatus.PASS, "Notes Entered ");	
			driver.findElement(By.name("bt_AddDrawer")).click();
			test.log(LogStatus.PASS, "Click on Add O/S Instance Button");	
			Thread.sleep(5000);
			 WebDriverWait wait = new WebDriverWait(driver, 10000);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")));
			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input")).click();
			// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input
			test.log(LogStatus.PASS, "Clicked on Next");


			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}



			Thread.sleep(1000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.name("Next"));
			// Next
			test.log(LogStatus.PASS, "Clicked on Next");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next");
			driver.findElement(By.name("requestBean.bagNbr")).sendKeys("34");
			test.log(LogStatus.PASS, "Bag number is provided as 34");
			driver.findElement(By.name("finishdeposit")).click();
			test.log(LogStatus.PASS, "Clicked on Finish Deposit");
			test.log(LogStatus.PASS, "StatmentGeneration EOD Processing Completed");
			Thread.sleep(4000);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}



		}
	}
}
public void StoreInfo(String SSN,String FileName) throws Exception
	{
 Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			
			driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");
			driver.manage().window().maximize();
			 Thread.sleep(1000);
			 
			 
 
 driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
 test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
 driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
 test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
 //Click Login Button
 driver.findElement(By.name("login")).click();
 test.log(LogStatus.PASS, "Clicked on Submit button");
 Thread.sleep(10000);
 Thread.sleep(8000);
 driver.switchTo().frame("topFrame");
 WebDriverWait wait = new WebDriverWait(driver, 10000);						   
 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Store Setup')]")));
	driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Store Setup");
	Thread.sleep(10000);
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	Thread.sleep(10000);
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Store Config")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	 driver.findElement(By.linkText("Store Config")).click();
	//Store Config
	/*WebElement element= driver.findElement(By.cssSelector("li[id='101000']"));	
	Actions action = new Actions(driver);
	action.moveToElement(element).perform();
 WebElement subElement = driver.findElement(By.cssSelector("li[id='101020']"));			        
 action.moveToElement(subElement).perform();			 
 action.click();	*/
 driver.findElement(By.linkText("Edit Store")).click();
// action.perform();
//  driver.findElement(By.cssSelector("li[id='101020']")).click();
	test.log(LogStatus.PASS, "Clicked on Store Config");
			
	test.log(LogStatus.PASS, "Clicked on Edit Store");					
	driver.switchTo().frame("main");		
	  driver.findElement(By.name("locationBean.locNbr")).sendKeys(StoreID);
	  test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
	  driver.findElement(By.name("Submit2")).click();
	  test.log(LogStatus.PASS, "Clicked on submit button");
	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			} 
 
	  
	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");													  	 	  
	     driver.findElement(By.name("locationBean.locStatusCd")).sendKeys("Crash Package");
	    // test.log(LogStatus.PASS, "Store Info Status is Chenged: "+Storestaus);
	  
	  //locationBean.locStatusCd
 
	  
	  
	  driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");													    	
		 if(driver.findElement(By.name("submitButton")).isDisplayed())
			{
			 test.log(LogStatus.PASS, "Store Aging is Successfully ");
				driver.findElement(By.name("submitButton")).click();
			}
		 else
			{
				test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
			}
		//driver.close();
}
		}
	}
public void Safeassign(String SSN,String FileName) throws Exception{
 	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				 CSRLoginpage login = new CSRLoginpage();
				 login.Login(UserName, Password, StoreId, driver, AppURL, test);
			     Thread.sleep(5000);
				/*driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Cash Management");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//driver.findElement(By.cssSelector("li[id='911101']")).click();	
				driver.findElement(By.linkText("Safe")).click();
				test.log(LogStatus.PASS, "Clicked on safe");	
				//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
				//driver.findElement(By.linkText("Drawer")).click();
				
				driver.findElement(By.linkText("Assign")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");
				

				
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				
				
			//if(driver.findElement(By.name("previous")).isDisplayed())
			
				
				
					
				driver.findElement(By.name("previous")).click();
				
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				
				
				driver.findElement(By.name("yes")).click(); */
				
									    			
				
driver.switchTo().defaultContent();				
driver.switchTo().frame("topFrame");
driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
test.log(LogStatus.PASS, "Clicked on Cash Management");
Thread.sleep(1000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//driver.findElement(By.cssSelector("li[id='911101']")).click();	
driver.findElement(By.linkText("Safe")).click();
test.log(LogStatus.PASS, "Clicked on Assign");	
//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
//driver.findElement(By.linkText("Drawer")).click();
driver.findElement(By.linkText("Assign")).click();
test.log(LogStatus.PASS, "Clicked on Assign");

					//login.Login(UserName, Password, StoreId, driver, AppURL, test);
 Thread.sleep(5000);

driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");

driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys(Password);


driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");


driver.findElement(By.name("safeassign")).click();

try { 
    Alert alert = driver.switchTo().alert();
    alert.accept();
    //if alert present, accept and move on.														
	
}
catch (NoAlertPresentException e) {
    //do what you normally would if you didn't have the alert.
	
}
Thread.sleep(5000);
driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

 driver.switchTo().defaultContent();
    driver.switchTo().frame("mainFrame");
    driver.switchTo().frame("main");
    
    ///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input
    ///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input
   // if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input")).isDisplayed())
    if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
    {

    	 test.log(LogStatus.PASS,"Safe assigned successfully with over/short.");
    	 driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
    	 //driver.findElement(By.name("done")).click();
    }
    else
    {
    	test.log(LogStatus.PASS,"Safe not assigned successfully with over/short.");
    }
}
}
}

public void Payments(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
					//	}
				//driver.manage().window().maximize();
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Payments");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
						
							 String Pmt= driver.findElement(By.name("currentBalance")).getAttribute("value");
							 driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "Password is selected as "+Password);			
							 driver.findElement(By.name("Submit22")).click();
								test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
							 
							 Thread.sleep(2000);
					
							 driver.findElement(By.id("btnADV_Yes")).click();
								test.log(LogStatus.PASS, "Clicked on Navigate to  Payoff Screen button ");																	
						
										
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 String Pmt1= driver.findElement(By.name("payOffAmount")).getAttribute("value");
								 driver.findElement(By.name("tenderType")).sendKeys(TenderType);
								 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
								driver.findElement(By.name("tenderAmount")).sendKeys(Pmt1);
								test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt1);							
								 driver.findElement(By.name("password")).sendKeys(Password);
								 test.log(LogStatus.PASS, "Password is selected as "+Password);			
								 driver.findElement(By.name("Submit22")).click();
									test.log(LogStatus.PASS, "Clicked on Finish payOff button ");
								 
								 
									try { 
									    Alert alert = driver.switchTo().alert();
									    alert.accept();
									    //if alert present, accept and move on.														
										
									}
									catch (NoAlertPresentException e) {
									    //do what you normally would if you didn't have the alert.
									}
									
									try { 
									    Alert alert = driver.switchTo().alert();
									    alert.accept();
									    //if alert present, accept and move on.														
										
									}
									catch (NoAlertPresentException e) {
									    //do what you normally would if you didn't have the alert.
									}
									Thread.sleep(2000);
								 if(driver.findElement(By.name("ok")).isDisplayed())
									{
									 test.log(LogStatus.INFO, "PayOffLoan with-SSN: " +SSN +" :: is Successful");
									 driver.findElement(By.name("ok")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Payoff not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}
	

public void AgeStore_LoanDate(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);


			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("PDL"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			String DueDate=null;

			DueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();

			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			System.out.print(DueDate);	
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);


			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);


			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, Days);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));
			Actions actions1 = new Actions(driver);								        
			actions1.moveToElement(elements1).build().perform();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(5000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(5000);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}




		}
	}
}
public WebElement Field(WebDriver driver) {


	try {
		Thread.sleep(500);
		WebElement element = (new WebDriverWait(driver, 9)).until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table")));
		return element;
	} catch (Exception e) {
		return null;
	}
}	    		

public void Drawerassign(String SSN,String FileName) throws Exception{



	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);

	int lastrow=TestData.getLastRow("NewLoan");

	System.out.println("NewLoan "+lastrow);

	String sheetName="NewLoan";

	for(int row=2;row<=lastrow;row++)

	{

		String RegSSN = TestData.getCellData(sheetName,"SSN",row);

		if(SSN.equals(RegSSN))

		{



			String AppURL = TestData.getCellData(sheetName,"AppURL",row);

			String UserName = TestData.getCellData(sheetName,"UserName",row);

			String Password = TestData.getCellData(sheetName,"Password",row);

			String StoreId = TestData.getCellData(sheetName,"StoreID",row);

			Thread.sleep(5000);

			CSRLoginpage login = new CSRLoginpage();

			login.Login(UserName, Password, StoreId, driver, AppURL, test);

			Thread.sleep(5000);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();

			test.log(LogStatus.PASS, "Clicked on Cash Management");

			Thread.sleep(1000);

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");


			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


			driver.findElement(By.linkText("Drawer")).click();

			test.log(LogStatus.PASS, "Clicked on Drawer");



			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");


			driver.findElement(By.linkText("Assign")).click();

			test.log(LogStatus.PASS, "Clicked on Assign");

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");

			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

			driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);

			driver.findElement(By.name("drawerassign")).click();

			try {

				Alert alert = driver.switchTo().alert();

				alert.accept();


			}

			catch (NoAlertPresentException e) {


			}

			Thread.sleep(2000);

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");


			if(this.Field(driver) != null )


			{

				Thread.sleep(1000);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


				driver.findElement(By.linkText("Safe")).click();

				test.log(LogStatus.PASS, "Clicked on Safe");



				driver.findElement(By.linkText("Deassign")).click();

				test.log(LogStatus.PASS, "Clicked on Deassign");

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");

				test.log(LogStatus.PASS, "Enter the Value 0");



				driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("safedeassign")).click();

				test.log(LogStatus.PASS, "Click on the Deassign");

				try {

					Alert alert = driver.switchTo().alert();

					alert.accept();


				}

				catch (NoAlertPresentException e) {


				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())

				{

					test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");

					driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();


				}

				else

				{

					driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);

					test.log(LogStatus.PASS, "Enter the Password");

					driver.findElement(By.name("safedeassign")).click();

					test.log(LogStatus.PASS, "Click on the Deassign");

					for(String winHandle : driver.getWindowHandles()){

						driver.switchTo().window(winHandle);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					String DrawerOverShortAmount =driver.findElement(By.name("safeRequestBean.safeOverShort")).getAttribute("value");

					driver.findElement(By.name("safeRequestBean.amount")).sendKeys(DrawerOverShortAmount);

					test.log(LogStatus.PASS, "Amount entered as "+DrawerOverShortAmount);

					driver.findElement(By.name("safeRequestBean.primary")).sendKeys("Counterfeit Bill");

					test.log(LogStatus.PASS, "Primary Reason is selected as Counterfeit Bill");

					driver.findElement(By.name("safeRequestBean.notes")).sendKeys("Notes");

					test.log(LogStatus.PASS, "Notes Entered ");

					driver.findElement(By.name("bt_AddDrawer")).click();

					test.log(LogStatus.PASS, "Click on Add O/S Instance Button");

					Thread.sleep(3000);

					driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);

					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();

					test.log(LogStatus.PASS, "Click on Finish Safe O/S");

					try {

						Alert alert = driver.switchTo().alert();

						alert.accept();

						//if alert present, accept and move on.

					}

					catch (NoAlertPresentException e) {

						//do what you normally would if you didn't have the alert.

					}

					Thread.sleep(2000);

					for(String winHandle : driver.getWindowHandles()){

						driver.switchTo().window(winHandle);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())

					{

						test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");

						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();

					}

					else

					{

						test.log(LogStatus.PASS,"Safe not De-assigned successfully with over/short.");

					}

				}

				Thread.sleep(1000);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


				driver.findElement(By.linkText("Safe")).click();

				test.log(LogStatus.PASS, "Clicked on Assign");



				driver.findElement(By.linkText("Assign")).click();

				test.log(LogStatus.PASS, "Clicked on Assign");


				Thread.sleep(5000);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");


				driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("900");

				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");


				driver.findElement(By.name("safeassign")).click();

				test.log(LogStatus.PASS,"Click on Safe Assigen");

				try {

					Alert alert = driver.switchTo().alert();

					alert.accept();


				}

				catch (NoAlertPresentException e) {


				}

				Thread.sleep(5000);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");


				if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())

				{

					test.log(LogStatus.PASS,"Safe assigned successfully.");

					driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();


				}

				else

				{

					test.log(LogStatus.PASS,"Safe not assigned successfully.");

				}

				Thread.sleep(1000);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");


				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


				driver.findElement(By.linkText("Drawer")).click();

				test.log(LogStatus.PASS, "Clicked on Drawer");

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.findElement(By.linkText("Assign")).click();

				test.log(LogStatus.PASS, "Clicked on Assign");

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");

				test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);

				driver.findElement(By.name("drawerassign")).click();

				try {

					Alert alert = driver.switchTo().alert();

					alert.accept();


				}

				catch (NoAlertPresentException e) {


				}

				Thread.sleep(2000);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");
				try {

					Alert alert = driver.switchTo().alert();

					alert.accept();


				}

				catch (NoAlertPresentException e) {


				}

				Thread.sleep(2000);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");


				if(this.Field(driver) != null )


				{

					Thread.sleep(1000);

					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.linkText("Safe")).click();

					test.log(LogStatus.PASS, "Clicked on Safe");


					driver.findElement(By.linkText("Deassign")).click();

					test.log(LogStatus.PASS, "Clicked on Deassign");

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");

					test.log(LogStatus.PASS, "Enter the Value 0");



					driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);

					test.log(LogStatus.PASS, "Enter the Password");

					driver.findElement(By.name("safedeassign")).click();

					test.log(LogStatus.PASS, "Click on the Deassign");

					try {

						Alert alert = driver.switchTo().alert();

						alert.accept();


					}

					catch (NoAlertPresentException e) {


					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())

					{

						test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");

						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();


					}

					else

					{

						driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);

						test.log(LogStatus.PASS, "Enter the Password");

						driver.findElement(By.name("safedeassign")).click();

						test.log(LogStatus.PASS, "Click on the Deassign");

						for(String winHandle : driver.getWindowHandles()){

							driver.switchTo().window(winHandle);

						}

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						driver.switchTo().frame("main");

						String DrawerOverShortAmount =driver.findElement(By.name("safeRequestBean.safeOverShort")).getAttribute("value");

						driver.findElement(By.name("safeRequestBean.amount")).sendKeys(DrawerOverShortAmount);

						test.log(LogStatus.PASS, "Amount entered as "+DrawerOverShortAmount);

						driver.findElement(By.name("safeRequestBean.primary")).sendKeys("Counterfeit Bill");

						test.log(LogStatus.PASS, "Primary Reason is selected as Counterfeit Bill");

						driver.findElement(By.name("safeRequestBean.notes")).sendKeys("Notes");

						test.log(LogStatus.PASS, "Notes Entered ");

						driver.findElement(By.name("bt_AddDrawer")).click();

						test.log(LogStatus.PASS, "Click on Add O/S Instance Button");

						Thread.sleep(3000);

						driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();

						test.log(LogStatus.PASS, "Click on Finish Safe O/S");

						try {

							Alert alert = driver.switchTo().alert();

							alert.accept();


						}

						catch (NoAlertPresentException e) {


						}

						Thread.sleep(2000);

						for(String winHandle : driver.getWindowHandles()){

							driver.switchTo().window(winHandle);

						}

						driver.switchTo().defaultContent();

						driver.switchTo().frame("mainFrame");

						driver.switchTo().frame("main");

						if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())

						{

							test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");

							driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();

						}

						else

						{

							test.log(LogStatus.PASS,"Safe not De-assigned successfully with over/short.");

						}

					}

					Thread.sleep(1000);

					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


					driver.findElement(By.linkText("Safe")).click();

					test.log(LogStatus.PASS, "Clicked on Assign");

					driver.findElement(By.linkText("Assign")).click();

					test.log(LogStatus.PASS, "Clicked on Assign");

					Thread.sleep(5000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");


					driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");

					test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");


					driver.findElement(By.name("safeassign")).click();

					test.log(LogStatus.PASS,"Click on Safe Assigen");

					try {

						Alert alert = driver.switchTo().alert();

						alert.accept();


					}

					catch (NoAlertPresentException e) {

					}

					Thread.sleep(5000);

					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())

					{

						test.log(LogStatus.PASS,"Safe assigned successfully.");

						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();


					}

					else

					{

						test.log(LogStatus.PASS,"Safe not assigned successfully.");

					}

					Thread.sleep(1000);

					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");


					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


					driver.findElement(By.linkText("Drawer")).click();

					test.log(LogStatus.PASS, "Clicked on Drawer");

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");


					driver.findElement(By.linkText("Assign")).click();

					test.log(LogStatus.PASS, "Clicked on Assign");

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");

					test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

					driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);

					driver.findElement(By.name("drawerassign")).click();

					try {

						Alert alert = driver.switchTo().alert();

						alert.accept();
					}

					catch (NoAlertPresentException e) {


					}

					Thread.sleep(2000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");



					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())

					{

						test.log(LogStatus.PASS,"Drawer assigned successfully .");

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();

					}

					else

					{

						test.log(LogStatus.PASS,"Drawer not assigned successfully .");

					}

				}

				else

				{
					Thread.sleep(3000);
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())

					{

						test.log(LogStatus.PASS,"Drawer Assigned successfully with over/short.");


						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
					   
						

					}

					else

					{

						test.log(LogStatus.PASS,"Drawer not Assigned successfully with over/short.");

					}
					

				}
				
				
			}

		}

	}
	
	 Thread.sleep(2000);
	//    driver.quit();
	   // driver = new InternetExplorerDriver();

}


public void PayOffLoan(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
					//	}
				//driver.manage().window().maximize();
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "PayOffLoan with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("PayOff");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");						
							// System.out.println(Pmt);
							// driver.findElement(By.name("requestBean.paymentAmt")).clear();
							// driver.findElement(By.name("tenderType")).sendKeys("10");
							 //test.log(LogStatus.PASS, "tenderType");
							 String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");
							 driver.findElement(By.name("tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("tenderAmount")).sendKeys(Pmt);
							test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
							 driver.findElement(By.name("password")).sendKeys(Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								//for( String winHandle1 : driver.getWindowHandles())
								//{
								    //driver.switchTo().window(winHandle1);
								//}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 
								 if(driver.findElement(By.name("ok")).isDisplayed())
									{
									 test.log(LogStatus.INFO, "PayOffLoan with-SSN: " +SSN +" :: is Successful");
									 driver.findElement(By.name("ok")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Payoff not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}

public boolean isAlertPresent(){
	try{
		driver.switchTo().alert();
		return true;
	}catch(NoAlertPresentException ex){
		return false;
	}
}



public void EODProcessing(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			CSRLoginpage login = new CSRLoginpage();
			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			Thread.sleep(5000);	    

			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Daily Processing')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Daily Processing");
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.name("eod")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			//driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");

			Thread.sleep(4000);
			// driver.findElement(By.name("requestBean.comments")).click();
			driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
			test.log(LogStatus.PASS,"Count of Dollar Coins is entered as comment");
			// requestBean.comments
			//Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.name("Submit2")).click();
			test.log(LogStatus.PASS,"Clicked on Balance Safe");



			Thread.sleep(1000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.name("Next"));
			// Next
			test.log(LogStatus.PASS, "Clicked on Next");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next");
			driver.findElement(By.name("requestBean.bagNbr")).sendKeys("34");
			test.log(LogStatus.PASS, "Bag number is provided as 34");
			driver.findElement(By.name("finishdeposit")).click();
			test.log(LogStatus.PASS, "Clicked on Finish Deposit");
			test.log(LogStatus.PASS, "EOD Processing Completed");
			Thread.sleep(4000);

			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.

			}



		}
	}
}


public void AgeStore(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{

			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);


			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(5000);
			Thread.sleep(1000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");


			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			}
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			if(ProductID.equals("LOC"))
			{

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.id("go_Button")).click();  
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

		//	DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			System.out.print(DueDate);	
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);


			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);


			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Borrower")).click();
			test.log(LogStatus.PASS, "Clicked on Borrower");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Date Change")).click();
			test.log(LogStatus.PASS, "Clicked on Process Date Change");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);



			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, Days);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));
			Actions actions1 = new Actions(driver);								        
			actions1.moveToElement(elements1).build().perform();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			driver.findElement(By.name("storeCode")).click();
			driver.findElement(By.name("storeCode")).sendKeys(StoreID);
			test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
			Thread.sleep(5000);
			driver.findElement(By.name("beginMonth")).clear();
			driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
			test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
			driver.findElement(By.name("beginDay")).clear();
			driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
			test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
			driver.findElement(By.name("beginYear")).clear();
			driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
			test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(1000);
			Thread.sleep(5000);
			driver.findElement(By.name("btnPreview")).click();
			test.log(LogStatus.PASS, "Clicked on submit button");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if( driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			{									        								
				test.log(LogStatus.PASS, "Process Date updated successfully");
				driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			}
			else
			{
				test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			}




		}
	}
}

public void Void_Payment(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				//String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Void_Payoff with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Void");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
							// System.out.println(Pmt);
							
							 driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);																						
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "PIN# is entered as"+Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish VoidPayOff button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
							/*	 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
									{*/
									 test.log(LogStatus.INFO, "Void_Payoff with-SSN: " +SSN +" :: Starts");
								/*		driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Void PayOff not Completed Successfully ");
									}*/
							 
					    	
						 }
					
			}
			
		}
	}
public void CurePaymentStatus(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
							
			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			 Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  //  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String CheckStaus=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
					 CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();
					 
					//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
					 test.log(LogStatus.PASS,"Payment status is Cure."+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			 //   driver.close();//need to change to close
			 driver.quit();//Uncomment 
			    driver = new InternetExplorerDriver();

		}
	}
}

public void DeliquentPaymentStatus(String SSN,String FileName) throws Exception
{
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");
							
			System.out.println(ProductID);	
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			   appUrl = AppURL;
			this.Login(UserName,Password,StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			 Thread.sleep(5000);
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "DeliquentPaymentStatus with-SSN: " +SSN +" :: Starts");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  //  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys("History");
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 String CheckStaus=null;
					 
		   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
			}
			 
			 //String winHandleBefore = driver.getWindowHandle();
			 for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			 Thread.sleep(8000);
			  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
					//*[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
					 CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
					 test.log(LogStatus.INFO, "DeliquentPaymentStatus for-SSN: " +SSN +" :: is ::"+CheckStaus);
		             //DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			   System.out.print(CheckStaus);	
			   driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				Thread.sleep(1000);
			  //driver.close();//need to change to close
			  driver.quit();//Uncomment 
			  driver = new InternetExplorerDriver();

		}
	}
}

public void Closure(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Closure Transaction with-SSN: " +SSN +" :: is Starts");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
	        driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
			}

			Thread.sleep(1000);
			if(ProductID.equals("LOC"))
			{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				//driver.findElement(By.name("button")).click();
			}
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys(TxnType);
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("button")).click(); 
			}
			else
			{
				driver.findElement(By.id("go_Button")).click();
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("close loc")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Closer Loan button ");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("ok")).click();


				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("ok")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Closure Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Closure Loan is not Completed Successfully ");
				}


			}
			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
				String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
				System.out.println(Pmt);
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.name("tenderType")).sendKeys(TenderType);
			}
			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
				driver.findElement(By.name("Submit23")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
			}
			if(ProductID.equals("TLP"))
			{
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				driver.findElement(By.name("finish")).click();
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
			}		


			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();
				//if alert present, accept and move on.														

			}
			catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("TLP"))
			{


				if(driver.findElement(By.name("Ok")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Closure Transaction with-SSN: " +SSN +" :: is Successful");
					driver.findElement(By.name("Ok")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
				}
			}
			if(ProductID.equals("PDL"))
			{

				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Closure Transaction with-SSN: " +SSN +" :: is Successful");
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
				}
			}
		}

	}
}
public void EncryptionKey_Void(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);   	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			this.Login(UserName,Password,StoreId);	
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			String Eankey=null;
			Thread.sleep(4000);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
			driver.findElement(By.cssSelector("li[id='910000']")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			test.log(LogStatus.PASS, "Transaction Type is selected as Void");	
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.id("go_Button")).click();
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			String TranID = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td")).getText();
			test.log(LogStatus.PASS, "TranId captured:"+TranID);	
			String TranID0[] =TranID.split(":");
			String TranID1 = TranID0[0];
			String TranID2 =  TranID0[1]; 
            Thread.sleep(3000);
			//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
            driver.findElement(By.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]")).click();
           //                              /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]
            //driver.findElement(By.xpath("//input[@name='NO' and @type='button']")).click();
			test.log(LogStatus.PASS, "No button is clicked ");		
//name="NO"
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);


			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
			test.log(LogStatus.PASS, "Username is entered: admin");			        
			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
			//Click Login Button
			driver.findElement(By.name("login")).click();
			test.log(LogStatus.PASS, "Clicked on Submit button");
			Thread.sleep(8000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Employee')]")).click();	
			test.log(LogStatus.PASS, "Clicked on Transactions");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Encryption Key")).click();
			test.log(LogStatus.PASS, "Clicked on Encryption Key");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.findElement(By.linkText("Encryption")).click();
			test.log(LogStatus.PASS, "Clicked on Encryption");
			Thread.sleep(5000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreId);
			test.log(LogStatus.PASS, "Store number Entered");	

			driver.findElement(By.name("requestBean.tranNbr")).sendKeys(TranID2);
			test.log(LogStatus.PASS, "Tran number Entered");	

	/*		driver.findElement(By.name("trancd")).sendKeys("Advance-ADV");
			test.log(LogStatus.PASS, "Trancd selected");*/
			
		/*	driver.findElement(By.name("trancd")).sendKeys("Early pay off-EPAY");
			test.log(LogStatus.PASS, "Trancd selected");*/

		/*	driver.findElement(By.name("trancd")).sendKeys("Payment-PAYIL");
			test.log(LogStatus.PASS, "Trancd selected");*/
			
			driver.findElement(By.name("trancd")).sendKeys("Revolving Credit Buy-RCBUY");
			test.log(LogStatus.PASS, "Trancd selected");
			
			driver.findElement(By.name("GetKey")).click();
			test.log(LogStatus.PASS, "GetKey clicked");

			Eankey = driver.findElement(By.name("EanKey")).getAttribute("value");
			test.log(LogStatus.PASS, "GetKey clicked:" +Eankey);

			driver.close();
			driver = new InternetExplorerDriver();
			this.Login(UserName,Password,StoreId);	
			Thread.sleep(4000);
			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();		
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				//driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
			}
			
			test.log(LogStatus.PASS, "Click on GO Button"); 
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Void");
			test.log(LogStatus.PASS, "Transaction Type is selected as Void");					
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.id("go_Button")).click();
			Thread.sleep(5000);

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
			 driver.findElement(By.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[1]")).click();
			//driver.findElement(By.xpath("//input[@name='YES' and @type='button']")).click();
			test.log(LogStatus.PASS, "Yes Button clicked");

			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "DisbType Type is entered as "+TenderType);
	/*			String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
				System.out.println(Pmt);
				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
				test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);
*/
				driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
				test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



			}
			
			if(ProductID.equals("LOC"))
			{
				driver.findElement(By.name("password")).sendKeys(Password);
				// Robot robot = new Robot();
				//	Thread.sleep(2000);
				//	robot.keyPress(KeyEvent.VK_F11);
				driver.findElement(By.name("Submit22")).click();
			//	robot.keyPress(KeyEvent.VK_F11);
				test.log(LogStatus.PASS, "Password is selected as "+Password);																					
				test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
			}	


			try { 
				Alert alert = driver.switchTo().alert();
				alert.accept();

			}
			catch (NoAlertPresentException e) {
			}
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("LOC"))
			{

				if(	driver.findElement(By.xpath("//input[@value='Ok' and @type='button']")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
					driver.findElement(By.xpath("//input[@value='Ok' and @type='button']")).click();
					//driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Payment is not Completed Successfully ");
				}
			}
		}
	}
}
	

public void LoanPartialPayment(String SSN,String FileName) throws Exception{
	
	
Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
	int lastrow=TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName="NewLoan";		
	for(int row=2;row<=lastrow;row++)
	{	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			 CSRLoginpage login = new CSRLoginpage();
		     login.Login(UserName, Password, StoreId, driver, AppURL, test);
			driver.switchTo().defaultContent();				
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "LoanPartialPayment with-SSN: " +SSN +" :: Starts");
			driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
			test.log(LogStatus.PASS, "Clicked on Loan Transactions");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("li[id='911101']")).click();			
			test.log(LogStatus.PASS, "Clicked on Transactions");		
			driver.switchTo().frame("main");		
			driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");		
			for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			}
		    driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}				    
			 driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    
			 
			    if(ProductID.equals("LOC"))
				 {
			    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				 }
			  //  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 driver.findElement(By.name("transactionList")).sendKeys(TxnType);
				 if(ProductID.equals("LOC"))
				 {
					 driver.findElement(By.name("button")).click(); 
				 }
				 
				 for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 if(ProductID.equals("LOC"))
					 {
						// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
						
						 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
						// System.out.println(Pmt);
						 driver.findElement(By.name("requestBean.paymentAmt")).clear();
						 driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("10");
						 test.log(LogStatus.PASS, "Payment Amt is entered as 10");
						 driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
						 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
						driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("10");
						test.log(LogStatus.PASS, "Tender Amt is entered as 10");							
						 driver.findElement(By.name("password")).sendKeys(Password);
						 driver.findElement(By.name("Submit22")).click();
						 
						 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
							test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
							// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
							//*[@id="btnADV_Yes"]
							//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
							
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 
							 if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
								{
								 test.log(LogStatus.INFO, "LoanPartialPayment with-SSN: " +SSN +" :: Successful");
									driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
								}
							 else
								{
									test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");
								}
						 
				    	
					 }
				
		}
		
	}
}

public void Void_PartialPayment(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				//String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Void_PartialPayment with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Void");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
							// System.out.println(Pmt);
							
							 driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);																						
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "PIN# is entered as"+Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish Payment button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
									{
									 test.log(LogStatus.INFO, "Void_PartialPayment with-SSN: " +SSN +" :: is Successful");
										driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Void Partial Payment not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}

	
public void Void_Payoff(String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				//String TxnType=TestData.getCellData(sheetName,"TxnType",row);
				String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				 String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				 CSRLoginpage login = new CSRLoginpage();
			     login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Void_Payoff with-SSN: " +SSN +" :: Starts");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				}
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
					}				    
				 driver.switchTo().defaultContent();
				    driver.switchTo().frame("mainFrame");
				    driver.switchTo().frame("main");
				    
				 
				    if(ProductID.equals("LOC"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					 }
				  //  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
					 driver.findElement(By.name("transactionList")).sendKeys("Void");
					 if(ProductID.equals("LOC"))
					 {
						 driver.findElement(By.name("button")).click(); 
					 }
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("LOC"))
						 {
							// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							
							 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
							// System.out.println(Pmt);
							
							 driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);																						
							 driver.findElement(By.name("password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "PIN# is entered as"+Password);
							 driver.findElement(By.name("Submit22")).click();
							 
							 test.log(LogStatus.PASS, "Password is selected as "+Password);																					
								test.log(LogStatus.PASS, "Clicked on Finish VoidPayOff button ");
								// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
								//*[@id="btnADV_Yes"]
								//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]
								
								for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
								 if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
									{
									 test.log(LogStatus.INFO, "Void_Payoff with-SSN: " +SSN +" :: Starts");
										driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
									}
								 else
									{
										test.log(LogStatus.FAIL, "Void PayOff not Completed Successfully ");
									}
							 
					    	
						 }
					
			}
			
		}
	}
	
	@Test (priority=0)
	
	 public void LOCICash_Draw_Stmnt_Payoff_Draw_EODDueDate_Stmnt_Payoff_PayOffVoidDueDate_DLQ_() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_LOCICash_Draw_Stmnt_Payoff_Draw_EODDueDate_Stmnt_Payoff_PayOffVoidDueDate_DLQ_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);  
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		//int lastrow=TestData.getLastRow("Borrower");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
		if(RunFlag.equals("Y"))
		{	
			//driver.get(appUrl);
			//test.log(LogStatus.INFO, "Application is launched");
			//driver.manage().window().maximize();
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
		       // System.out.println(Password);
		        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		        String StateID = TestData.getCellData(sheetName,"StateID",row);
		        String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);

				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String Header = StateID+ "_" + ProductID;
		        //System.out.println(SSN);
		        test = reports.startTest(Header+"_S.No:19_"+PayFrequency+"_"+CollateralType, "LOCI Cash _ Draw _ Stmnt _ Payoff _ Draw _ RUN EOD On Due Date _ Stmnt _ Payoff _ On Due Date Void Payoff_DLQ");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		         
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        this.DrawLoan(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN, FileName, -4);
		        this.PayOffLoan(SSN, FileName);
		        this.DrawLoan(SSN, FileName);
		        this.AgeStore(SSN, FileName, 0);
		
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		       
		        this.StatementGeneration(SSN, FileName);
		        this.PayOffLoan(SSN, FileName);
		        this.AgeStore(SSN, FileName, 0);
		      
		       this.EncryptionKey_Void(SSN, FileName);
		       this.DrawerDeassign(SSN, FileName);
		       this.EODProcessing(SSN, FileName);
		       this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		       this.DeliquentPaymentStatus(SSN, FileName);
		        
		        
		}
		}			
			}
	
	

	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());

			String screenshotPath = ExecuteScripts.getScreenhot(driver, result.getName());

			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(LogStatus.PASS, result.getName()+" Test Case is Passed");}

		reports.flush();

	}			
	@AfterTest

	public void endReport(){

		reports.endTest(test);
		reports.flush();
		driver.quit();



	}
	@AfterClass

	public void closeBrowser() throws Exception{

		driver.quit();

	}
	
}

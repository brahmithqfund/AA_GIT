package Tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class AA_LOCI_Draw_Dismissedbeforeduedate_LinestatustoWO {


	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;


	@BeforeClass

	public synchronized void initialize() {

		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());


		String filename="AA_LOC_RegressionScenario_Scenario.No_173_"+timestamp+".html";

		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/AA_LOC_RegressionScenarios_/AA_LOC_RegressionScenario_Scenario.No_173/"+filename, true);
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



	public void Bankrupt(String SSN,String FileName) throws Exception
		{
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
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
				String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
		        String AttorneyP2 = AttorneyPhone.substring(3, 6);
		        String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				System.out.println(AdminURL);
				///////////////////////////////////////
				this.Login(UserName,Password,StoreID);
		
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
				test.log(LogStatus.INFO, "Admin portal is launched");
				

				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, -2);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				
				////////////////////////////////////
		/*		driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");*/
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
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 
	 
	 driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
		test.log(LogStatus.PASS, "Clicked on Transactions");
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		Thread.sleep(10000);
	    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
		 driver.findElement(By.linkText("Borrower")).click();
		 test.log(LogStatus.PASS, "Clicked on Borrower");
		 
		 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	     driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
		test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		
		
		  for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
				} 
	 
		  
		       driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");		 
			    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200,100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(5000);			
				
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");  
				
		  
		  
		     driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 
			 
			 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();		
			 
			 test.log(LogStatus.PASS,"Click on Go button");		 

		     driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 Thread.sleep(3000);
			 if( driver.findElement(By.name("loanCode")).isDisplayed())
			 {
			 driver.findElement(By.name("loanCode")).click();
			 test.log(LogStatus.PASS, "Selecting Check box for loan");
			 }
			 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys(BNKstatus);
			 test.log(LogStatus.PASS, "select status as :" + BNKstatus);
			 
			 
			 

			 
			 
			 driver.findElement(By.name("bankruptcyFilingDate1")).sendKeys(DueDate1.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("bankruptcyFilingDate2")).sendKeys(DueDate2.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
				Thread.sleep(500);			
				driver.findElement(By.name("bankruptcyFilingDate3")).sendKeys(DueDate3.trim());			
				test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);			
				
				 driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
				 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
				 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
				 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
				
				
			 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
			 test.log(LogStatus.PASS, "Entered Attorny Name");
			 
			 
			 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
				test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
				Thread.sleep(500);		    
				driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
				test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
				Thread.sleep(500);			
				driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
				test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);
				
				driver.findElement(By.name("bt_AddBankruptcy")).click();			
				 test.log(LogStatus.PASS, "Status BNKPending is Saved");
			 
				 Thread.sleep(50000);
			//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
				 if( driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
				 {
				 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted");
				 }
					driver.close();

					driver = new InternetExplorerDriver();
					
				}

			}		 
			 
			 
			 
			 /*if(driver.findElement(By.name("submitButton")).isDisplayed())
				{
				 test.log(LogStatus.PASS, "Store Aging is Successfully ");
					driver.findElement(By.name("submitButton")).click();
				}
			 else
				{
					test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
				}*/
			//driver.close();
	}
			




	public void BankruptStatus(String SSN,String FileName) throws Exception
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
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();	
					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
			  // driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
				test.log(LogStatus.PASS, "History Selected in DropDown");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
					//driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String PaymentStatus=null;
				PaymentStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS,"Payment status is ::"  +PaymentStatus);
				String LineStatus=null;
				LineStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS,"Line status is ::"  +LineStatus);
				String WOReason=null;
				WOReason =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS,"Reason for Customer got WriteOff ::"  +WOReason);


			}
		}
	}


	public void Dismissed_AfterBANKRUPT(String SSN,String FileName) throws Exception
	{
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
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
			String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
			String AttorneyP1 = AttorneyPhone.substring(0, 3);
	        String AttorneyP2 = AttorneyPhone.substring(3, 6);
	        String AttorneyP3 = AttorneyPhone.substring(6, 10);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			System.out.println(AdminURL);
			String Bankstatus = null;
			///////////////////////////////////////
			this.Login(UserName,Password,StoreID);

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
			test.log(LogStatus.INFO, "Admin portal is launched");
			

			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, 10);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			
			////////////////////////////////////
	/*		driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");*/
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
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

	driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Transactions");
	Thread.sleep(10000);
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	Thread.sleep(10000);
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	 driver.findElement(By.linkText("Borrower")).click();
	 test.log(LogStatus.PASS, "Clicked on Borrower");
	 
	 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
	test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			} 
	       driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");		 
		    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			Thread.sleep(5000);
			Actions action = new Actions(driver);
			action.moveByOffset(200,100).perform();
			Thread.sleep(10000);
			action.click();
			Thread.sleep(3000);			
			
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Click on submit Button");  
	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 
		 
		 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();
		 
		 
		 test.log(LogStatus.PASS,"Click on Go button");	
		    driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 driver.findElement(By.name("menu")).sendKeys("Edit");
			 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();

	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		
		 if( driver.findElement(By.name("loanCode")).isDisplayed())
		 {
		 driver.findElement(By.name("loanCode")).click();
		 test.log(LogStatus.PASS, "Selecting Check box for loan");
		 }
		   driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Dismissed");
		    test.log(LogStatus.PASS, "select status as  Dismissed"); 
		 driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
			test.log(LogStatus.PASS, "Dismissed Filing Month is:: "+DueDate1);			
			Thread.sleep(500);		    
			driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
			test.log(LogStatus.PASS, "Dismissed Filing Day is:: "+DueDate2);			
			Thread.sleep(500);			
			driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
			test.log(LogStatus.PASS, "Dismissed Filing Year is:: "+DueDate3);			
			
			/* driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
			 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
			 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
			 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
			
			
		 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
		 test.log(LogStatus.PASS, "Entered Attorny Name");
		 
		 
		 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
			test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
			Thread.sleep(500);		    
			driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
			test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
			Thread.sleep(500);			
			driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
			test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);*/
			
			driver.findElement(By.name("bt_AddBankruptcy")).click();			
			 test.log(LogStatus.PASS, "Status Dismissed is Saved");
		 
			 Thread.sleep(50000);
		//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
			 Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
			 
			 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Dismissed");
			 
				driver.close();

				driver = new InternetExplorerDriver();
				
			}

		}		 
		 
	}


	public void Bankrupt_AfterDismissed(String SSN,String FileName) throws Exception
	{
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
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String BNKstatus=TestData.getCellData(sheetName,"BNKstatus",row);
			String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
			String AttorneyP1 = AttorneyPhone.substring(0, 3);
	        String AttorneyP2 = AttorneyPhone.substring(3, 6);
	        String AttorneyP3 = AttorneyPhone.substring(6, 10);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			System.out.println(AdminURL);
			String Bankstatus = null;
			///////////////////////////////////////
			this.Login(UserName,Password,StoreID);

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
			test.log(LogStatus.INFO, "Admin portal is launched");
			

			DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
			String DDueDate[] =DueDate.split("/");


			Date DDueDateminus1 = df.parse(DueDate);

			Calendar cal = Calendar.getInstance();

			cal.setTime(DDueDateminus1);

			cal.add(Calendar.DATE, 10);

			Date DDueDate1= cal.getTime();

			DueDate =df.format(DDueDate1);

			String DueDate0[] =DueDate.split("/");

			String DueDate1 = DueDate0[0];

			String DueDate2 = DueDate0[1];

			String DueDate3 = DueDate0[2];

			
			////////////////////////////////////
	/*		driver.get(AdminURL);
			test.log(LogStatus.INFO, "Admin portal is launched");*/
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
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]"))); 

	driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
	test.log(LogStatus.PASS, "Clicked on Transactions");
	Thread.sleep(10000);
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	Thread.sleep(10000);
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	 driver.findElement(By.linkText("Borrower")).click();
	 test.log(LogStatus.PASS, "Clicked on Borrower");
	 
	 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bankrupt/Deceased Suite")));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	 driver.findElement(By.linkText("Bankrupt/Deceased Suite")).click();
	test.log(LogStatus.PASS, "Clicked on Bankrupt/Deceased Suite");		

	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
			} 
	       driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");		 
		    driver.findElement(By.name("ssn1")).sendKeys(SSN1);
			test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
			Thread.sleep(5000);
			Actions action = new Actions(driver);
			action.moveByOffset(200,100).perform();
			Thread.sleep(10000);
			action.click();
			Thread.sleep(3000);			
			
			driver.findElement(By.name("submit")).click();
			test.log(LogStatus.PASS, "Click on submit Button");  
	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		 
		 
		 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input")).click();
		 
		 
		 test.log(LogStatus.PASS,"Click on Go button");	
		    driver.switchTo().defaultContent();
			 driver.switchTo().frame("mainFrame");
			 driver.switchTo().frame("main");
			 driver.findElement(By.name("menu")).sendKeys("Edit");
			 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();

	     driver.switchTo().defaultContent();
		 driver.switchTo().frame("mainFrame");
		 driver.switchTo().frame("main");
		
		 if( driver.findElement(By.name("loanCode")).isDisplayed())
		 {
		 driver.findElement(By.name("loanCode")).click();
		 test.log(LogStatus.PASS, "Selecting Check box for loan");
		 }
		   driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Bankrupt");
		    test.log(LogStatus.PASS, "select status as  Bankrupt"); 
		/* driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
			test.log(LogStatus.PASS, "Dismissed Filing Month is:: "+DueDate1);			
			Thread.sleep(500);		    
			driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
			test.log(LogStatus.PASS, "Dismissed Filing Day is:: "+DueDate2);			
			Thread.sleep(500);			
			driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
			test.log(LogStatus.PASS, "Dismissed Filing Year is:: "+DueDate3);			
			
			 driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
			 test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
			 driver.findElement(By.name("requestBean.typeOfBankruptcy")).sendKeys("chapter7");
			 test.log(LogStatus.PASS, "Bankrupt type is ::Chapter7");
			
			
		 driver.findElement(By.name("attorneyName")).sendKeys("Attorny");
		 test.log(LogStatus.PASS, "Entered Attorny Name");*/
		 
		 
		 driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(AttorneyP1.trim());			
			test.log(LogStatus.PASS, "PP1 is entered: "+AttorneyP1);			
			Thread.sleep(500);		    
			driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(AttorneyP2.trim());			
			test.log(LogStatus.PASS, "PP2 is entered: "+AttorneyP2);			
			Thread.sleep(500);			
			driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(AttorneyP3.trim());			
			test.log(LogStatus.PASS, "PP3 is entered: "+AttorneyP3);
			
			driver.findElement(By.name("bt_AddBankruptcy")).click();			
			 test.log(LogStatus.PASS, "Status Dismissed is Saved");
		 
			 Thread.sleep(50000);
		//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
			  if(driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td")).isDisplayed())
					  {
			 
			 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted after Dismissed");
			 
					  }
			 driver.close();

				driver = new InternetExplorerDriver();
				
			}

		}		 
		 
	}


	

public void Check_Draw(String SSN,String FileName) throws Exception

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
			//String age_store = TestData.getCellData(sheetName, "AgeStore",row);
			//int Age_store = Integer.parseInt(age_store);
			//System.out.println(Age_store);
			//System.out.println("age_store:::"+age_store);
			//int Days= Integer.parseInt(age_store);

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
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();   
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
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

			int n=driver.findElements(By.xpath("//select[@name='transactionList']/option")).size();

			for(int i=1;i<=n;i++)
			{
				String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getText();

				if(transactino_value.contains("Draw"))
				{
					test.log(LogStatus.PASS, "Draw Option is Available");	
				}
				else
				{
					test.log(LogStatus.PASS, " Available Option is ::"+transactino_value);
						
				}
			
			}
			test.log(LogStatus.PASS, "Draw Option is not Available");
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

	
	
	@Test (priority=0)
	
	 public void AA_LOCI_Draw_Dismissedbeforeduedate_LinestatustoWO_() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_AA_LOCI_Draw_Dismissedbeforeduedate_LinestatustoWO_Txn_Testdata.xls";
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
		        test = reports.startTest(Header+"_S.No:173_"+PayFrequency+"_"+CollateralType, "LOCI _Draw _St Gn _ Dismissed before due date _ Line status to WO");
		        Assert.assertTrue(true);
		        appUrl = AppURL;
		         
		        CSRLoginpage login = new CSRLoginpage();
		        login.Login(UserName, Password, StoreId, driver, AppURL, test);
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		        Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
		        this.NewLoan(SSN,FileName);
		        this.DrawLoan(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.AgeStore(SSN, FileName, -5);
		       this.Bankrupt(SSN, FileName);
		       this.Dismissed_AfterBANKRUPT(SSN, FileName);

		        this.BankruptStatus(SSN, FileName);
		     
		     
		        
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

package Tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
//import java.sql.Time;
import java.text.DateFormat;
//import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
//import java.util.Set;
//import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

//import Pages.HomePage;
//import Pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.BorrowerRegistrationpage;
import Pages.CSRLoginpage;
import Utilities.ExtentReports.Excel;
//import scala.collection.Iterator;
//import scala.collection.Set;

//import Pages.HomePage;
//import Pages.LoginPage;

public class AEA_LOCRegression_batchFile_1 {

	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	//ExtentTest logger;

	/*@BeforeClass

	public synchronized void initialize() {
		// Create an instance of ExtentsReports class and pass report storage
		// path as a parameter
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		//Date D = new Date();

		String filename="BorrowerRegistration_NewLoan_"+timestamp+".html";
		//System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/BorrowerRegistration_NewLoan/"+filename, true);

	}*/


	//@BeforeTest
	@BeforeClass
	public void setup() throws IOException {	

		reports = new ExtentReports(System.getProperty("user.dir") +"/ExecutionReports/LOC/CriticalScenarios_"+timestamp+".html", true);
		reports.addSystemInfo("Browser Version","IE 11.0");
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	

	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		//TakesScreenshot ts = (TakesScreenshot) driver;
		//File source = ts.getScreenshotAs(OutputType.FILE);	

		File source = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);		 
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/ExecutionReports/LOC/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	//@BeforeTest
	public void Login (String username,String password,String storenumber) {										
		//Launch URL
		driver.get(appUrl);
		test.log(LogStatus.INFO, "CSR Application is launched");
		driver.manage().window().maximize();
		String usenameId = "loginRequestBean.userId";
		String passwordId = "loginRequestBean.password";
		String StoreId = "loginRequestBean.locNbr";
		String Login = "login";

		// String username= "CSR353";
		// String password= "1234";
		// String storenumber= "353";

		//Enter Username(Email)
		//writeText(By.name(usenameId),username);
		driver.findElement(By.name(usenameId)).sendKeys(username);
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Username is entered: "+username);

		//Enter Password
		//writeText(By.name(passwordId), password);
		driver.findElement(By.name(passwordId)).clear();
		driver.findElement(By.name(passwordId)).sendKeys(password);
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Password is entered: "+password);

		//writeText(By.name(StoreId), storenumber);
		driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
		//Click Login Button
		driver.findElement(By.name(Login)).click();
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Clicked on Submit button");
	}
	//if(driver.findElement()


	public boolean IsElementExits(String Value) {
		int secondsToWait = 5;

		try {
			new WebDriverWait(driver, secondsToWait).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Value)));
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}

	public void NewLoanDraw(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);		
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

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				System.out.println(ProductID);
				//String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
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
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);

				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//	Selection of Product based on the Name provided in Test Data
				// if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				{

					if(ProductID.equals("TLP"))							
					{					
						System.out.println("IN TLP");
						driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
						driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
						driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
						driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
					}												
					if(ProductName.equals("TNPAYDAY"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Tennessee"))
					{
						driver.findElement(By.xpath("//*[@id='termSel1']")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Line of Credit"))
					{
						if(StoreID.equals("4353"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						}
						else
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
						}
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}


					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");

					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}                    

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.id("LoanButtonId")).click();
					//New Loan Screens
					if(ProductID.equals("PDL"))
					{	

						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						if(!(ESign_LoanAmt.isEmpty()))
						{
							driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);
							test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);
						}
						driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
						test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					
						driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						Thread.sleep(5000);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						Thread.sleep(5000);
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.xpath("//*[@id='allowCoupons']/td[3]/input")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							driver.findElement(By.xpath("//*[@id='coupon']/td[3]/div[1]/input")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
						}
						driver.findElement(By.xpath("//*[@id='idNoChecks']/td[3]/select")).sendKeys(ESign_Checks);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Checks);
						WebDriverWait wait = new WebDriverWait(driver, 1000);	
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));
						driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);
						test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);
						driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishadvance")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
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
						//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						// driver.findElement(By.name("OKBut")).click();					
						driver.findElement(By.xpath("//*[@id='OKBut']")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("bdyLoad");
						if(driver.findElement(By.name("Ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("Ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("ILP"))
					{	
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							//String mwh=driver.getWindowHandle();
							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
							//String winHandle = driver.getWindowHandle(); //Get current window handle.									
						}
						WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
						String NumofInst=ele.getAttribute("value");
						//*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/table[1]/tbody/tr[5]/td[2]/input
						System.out.println(NumofInst);
						int installments = Integer.parseInt(NumofInst);
						for(int i=0;i<installments;i++)
						{
							Random rand = new Random();
							int rand1 = rand.nextInt(100000);	
							String chknum = Integer.toString(rand1);
							driver.findElement(By.id("checkNbrs"+i)).sendKeys(chknum);

						}			 					 			
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						//for( String winHandle1 : driver.getWindowHandles())
						//{
						// driver.switchTo().window(winHandle1);
						//}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						driver.findElement(By.name("OKBut")).click();
						//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}

					if(ProductID.equals("TLP"))
					{	
						String TitleNumber= TestData.getCellData(sheetName,"TitleNumber",row);
						String AppraisalValue= TestData.getCellData(sheetName,"Appraisal Value",row);
						String ExteriorColor=TestData.getCellData(sheetName,"ExteriorColor",row);
						String LicensePlateNumber=TestData.getCellData(sheetName,"License Plate Number",row);
						//String VehicleGrade=TestData.getCellData(sheetName,"Vehicle Grade",row);
						String LicensePlateExp=TestData.getCellData(sheetName,"License Plate Expiry",row);
						String InsuranceCoverage=TestData.getCellData(sheetName,"Insurance Coverage",row);
						String PhoneNbr=TestData.getCellData(sheetName,"Phone Nbr",row);
						String PhoneNbr1 = PhoneNbr.substring(0, 3);
						String PhoneNbr2 = PhoneNbr.substring(3, 6);
						String PhoneNbr3 = PhoneNbr.substring(6, 10);
						String InsuranceCompany =TestData.getCellData(sheetName,"Insurance Company",row);
						String InsuranceExpiryDate=TestData.getCellData(sheetName,"Insurance Expiry Date",row);
						String PolicyNumber=TestData.getCellData(sheetName,"Policy Number",row);
						String InsuranceExpiryDate0[] =InsuranceExpiryDate.split("/");
						String InsuranceExpiryDate1 = InsuranceExpiryDate0[0];
						String InsuranceExpiryDate2 = InsuranceExpiryDate0[1];
						String InsuranceExpiryDate3 = InsuranceExpiryDate0[2];
						driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
						driver.findElement(By.xpath("//*[@id='appraisal']")).sendKeys(AppraisalValue);
						//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						driver.findElement(By.name("button1")).click();
						test.log(LogStatus.PASS, "click on Update 1 button ");
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));

						driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
						driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
						driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
						driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
						driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
						driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);
						driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
						driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
						driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
						driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
						driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
						driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
						driver.findElement(By.name("button2")).click();			
						driver.findElement(By.name("button2")).click();	

						test.log(LogStatus.PASS, "click on Update 2 button ");
						Thread.sleep(8000);

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
						driver.findElement(By.name("process")).click();

						test.log(LogStatus.PASS, "click on process Loan button ");
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						Thread.sleep(5000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("vehicleKey")).sendKeys("Yes");					
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("requestBean.siilBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");

							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);

						}
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						test.log(LogStatus.PASS, "Click on Finish Loan Button");
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
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("LOC"))
					{

						test.log(LogStatus.INFO, "Navigated to New Loan Screen");
						driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
						Thread.sleep(5000);
						//driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						//driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[1]/input")).click();
							test.log(LogStatus.INFO, "Navigated to Draw Screen");
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
							driver.findElement(By.name("loanAmt")).sendKeys("50");	
							driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
							driver.findElement(By.name("disbAmtFirst")).sendKeys("50");					
							test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
							driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
							test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
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
								test.log(LogStatus.PASS, "Draw New Loan is Completed Successfully ");
							}
							else
							{
								test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
							}
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}

					//html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/input
				}
				else
				{
					test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
				}
			}
		}

	}

	public void DrawLoan(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				test.log(LogStatus.INFO, "VoidDrawLoan with-SSN: " +SSN +" :: Starts");
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

	public void StatementGeneration_2(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS, "statement generation date Captured::"+DueDate);			
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
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


				//Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");

				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Unsecure Loc Statement")).click();
				test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

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

				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

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

				test.log(LogStatus.PASS, "Second time statement generarted sucessfully");



			}
		}
	}

	public void StatementGeneration(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS, "statement generation date Captured::"+DueDate);			
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
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


				//Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");

				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Unsecure Loc Statement")).click();
				test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

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

				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

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

				///Process Date  
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//Thread.sleep(5000);
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements = driver.findElement(By.linkText("Daily Jobs"));
				Actions actions = new Actions(driver);								        
				actions1.moveToElement(elements1).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
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
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}



			}
		}
	}

	public void StoreInfo(String SSN,String FileName) throws Exception
	{
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				Thread.sleep(1000);



				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: "+UserName);			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(10000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Store Setup')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Store Setup");
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(10000);
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

	public void DeliquentPaymentStatus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS,"Payment status is ."+CheckStaus);
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(CheckStaus);	
				// driver.close();

			}
		}
	}


	//}



	public void AgeStore(String SSN,String FileName,int Days) throws Exception
	{
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");						
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
				String DueDate=null;

				/* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
				}

				 //String winHandleBefore = driver.getWindowHandle();
				 for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
				 Thread.sleep(8000);
				  // driver.findElement(By.xpath("//*[@id='home']")).click();*/

				DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS, "Due date is captured::"+DueDate);	
				//*[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]	
				System.out.print(DueDate);	
				//driver.quit();
				// Thread.sleep(5000);
				/*for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}*/
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.close();															
				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
				//}
				//}
				//	}

				//public void storeupdate(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
				///{
				//DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);
				Thread.sleep(1000);

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");



				// driver.switchTo().frame("main");
				Date DDueDate = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDate);
				cal.add(Calendar.DATE, Days);
				Date DDueDateminus1= cal.getTime();

				String DueDateminus1 =df.format(DDueDateminus1);
				String DueDate0[] =DueDateminus1.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//Thread.sleep(5000);
				driver.findElement(By.linkText("Borrower")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");

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
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
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
				}
				else
				{
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}
			}

		}
		//driver.close();
	}

	public void DrawerDeassign(String SSN,String FileName) throws Exception{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");	
				driver.findElement(By.linkText("Deassign")).click();
				test.log(LogStatus.PASS, "Clicked on Deassign");	
				driver.switchTo().frame("main");		
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Banker PIN# is enetered as"+Password);	
				driver.findElement(By.name("drawerdeassign")).click();
				test.log(LogStatus.PASS, "Click on Finish De-assign Button");
				try{
					driver.close();
				}
				catch (Exception e) {
				}
				Thread.sleep(2000);
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).clear();
				driver.findElement(By.name("drawerDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Current Cash Balance is provided as 0");	
				Thread.sleep(2000);
				driver.findElement(By.name("drawerDeassignRequestBean.password")).sendKeys(Password);	
				test.log(LogStatus.PASS, "Password is entered");
				driver.findElement(By.name("drawerdeassign")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer deassign Button");
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

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[5]/select")).sendKeys("Delete");
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[6]/input")).click();						
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();

						}
						catch (NoAlertPresentException e) {
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

				}
				catch (NoAlertPresentException e) {
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


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
				//String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

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
				Thread.sleep(3000);

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
	public void Safeassign(String SSN,String FileName) throws Exception{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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


				if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input")).isDisplayed())
				{

					test.log(LogStatus.PASS,"Safe assigned successfully with over/short.");
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input")).click();
					//driver.findElement(By.name("done")).click();
				}
				else
				{
					test.log(LogStatus.PASS,"Safe not assigned successfully with over/short.");
				}
			}
		}
	}

	public void Drawerassign(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				//CSRLoginpage login = new CSRLoginpage();
				//login.Login(UserName, Password, StoreId, driver, AppURL, test);
				Thread.sleep(5000);
				//driver.switchTo().defaultContent();				
				//driver.switchTo().frame("topFrame");
				//driver.findElement(By.xpath("//*[contains(text(),'Cash Management')]")).click();			
				//test.log(LogStatus.PASS, "Clicked on Cash Management");
				//Thread.sleep(1000);
				//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
				//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				//driver.findElement(By.cssSelector("li[id='911101']")).click();	
				//driver.findElement(By.linkText("Drawer")).click();
				//test.log(LogStatus.PASS, "Clicked on Drawer");	
				//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
				//driver.findElement(By.linkText("Drawer")).click();

				//driver.findElement(By.linkText("Assign")).click();
				//test.log(LogStatus.PASS, "Clicked on Assign");

				//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");

				//driver.findElement(By.name("previous")).click();

				//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");


				//driver.findElement(By.name("yes")).click();

				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");

				//driver.close();
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				//Thread.sleep(5000);
				driver.switchTo().defaultContent();				
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
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");	
				//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
				//driver.findElement(By.linkText("Drawer")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//driver.switchTo().frame("main");
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
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if(driver.findElement(By.name("done")).isDisplayed())
				{

					test.log(LogStatus.PASS,"Drawer De-assigned successfully with over/short.");
					driver.findElement(By.name("done")).click();
				}
				else
				{
					test.log(LogStatus.PASS,"Drawer not De-assigned successfully with over/short.");
				}


			}

		}
	}
	public void EODProcessing(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

	public void CurePaymentStatus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				//driver.close();

			}
		}
	}
	public void PayOffLoan(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

	public void Agestore_StatementGeneration(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS, "statement generation date Captured::"+DueDate);			
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
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

				/*	driver.switchTo().defaultContent();
					driver.switchTo().frame("topFrame");
					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
					test.log(LogStatus.PASS, "Clicked on Transactions");
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.findElement(By.linkText("ACH")).click();
					test.log(LogStatus.PASS, "Clicked on ACH");


					//Thread.sleep(5000);
					driver.findElement(By.linkText("LOC")).click();
					test.log(LogStatus.PASS, "Clicked on LOC");

					//driver.switchTo().defaultContent();
					//driver.switchTo().frame("mainFrame");
					Thread.sleep(5000);
					driver.findElement(By.linkText("Unsecure Loc Statement")).click();
					test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

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

					driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
					test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
				 */

				/*driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");*/
				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				/*driver.findElement(By.name("beginMonth")).click();
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
				 */
				///Process Date  
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				//Thread.sleep(5000);
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on Borrower");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				/*driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					WebElement elements = driver.findElement(By.linkText("Daily Jobs"));
					Actions actions = new Actions(driver);								        
					actions.moveToElement(elements).build().perform();
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);*/
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
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
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}



			}
		}
	}


	public void NewLoan(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);		
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

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				System.out.println(ProductID);
				//String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
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
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);

				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//	Selection of Product based on the Name provided in Test Data
				// if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				{

					if(ProductID.equals("TLP"))							
					{					
						System.out.println("IN TLP");
						driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
						driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
						driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
						driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
					}												
					if(ProductName.equals("TNPAYDAY"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Tennessee"))
					{
						driver.findElement(By.xpath("//*[@id='termSel1']")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Line of Credit"))
					{
						if(StoreID.equals("4353"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						}
						else
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
						}
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}


					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");

					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}                    

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.id("LoanButtonId")).click();
					//New Loan Screens
					if(ProductID.equals("PDL"))
					{	

						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						if(!(ESign_LoanAmt.isEmpty()))
						{
							driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);
							test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);
						}
						driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
						test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					
						driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						Thread.sleep(5000);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						Thread.sleep(5000);
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.xpath("//*[@id='allowCoupons']/td[3]/input")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							driver.findElement(By.xpath("//*[@id='coupon']/td[3]/div[1]/input")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
						}
						driver.findElement(By.xpath("//*[@id='idNoChecks']/td[3]/select")).sendKeys(ESign_Checks);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Checks);
						WebDriverWait wait = new WebDriverWait(driver, 1000);	
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));
						driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);
						test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);
						driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishadvance")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
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
						//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						// driver.findElement(By.name("OKBut")).click();					
						driver.findElement(By.xpath("//*[@id='OKBut']")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("bdyLoad");
						if(driver.findElement(By.name("Ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("Ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("ILP"))
					{	
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							//String mwh=driver.getWindowHandle();
							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
							//String winHandle = driver.getWindowHandle(); //Get current window handle.									
						}
						WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
						String NumofInst=ele.getAttribute("value");
						//*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/table[1]/tbody/tr[5]/td[2]/input
						System.out.println(NumofInst);
						int installments = Integer.parseInt(NumofInst);
						for(int i=0;i<installments;i++)
						{
							Random rand = new Random();
							int rand1 = rand.nextInt(100000);	
							String chknum = Integer.toString(rand1);
							driver.findElement(By.id("checkNbrs"+i)).sendKeys(chknum);

						}			 					 			
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						//for( String winHandle1 : driver.getWindowHandles())
						//{
						// driver.switchTo().window(winHandle1);
						//}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						driver.findElement(By.name("OKBut")).click();
						//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}

					if(ProductID.equals("TLP"))
					{	
						String TitleNumber= TestData.getCellData(sheetName,"TitleNumber",row);
						String AppraisalValue= TestData.getCellData(sheetName,"Appraisal Value",row);
						String ExteriorColor=TestData.getCellData(sheetName,"ExteriorColor",row);
						String LicensePlateNumber=TestData.getCellData(sheetName,"License Plate Number",row);
						//String VehicleGrade=TestData.getCellData(sheetName,"Vehicle Grade",row);
						String LicensePlateExp=TestData.getCellData(sheetName,"License Plate Expiry",row);
						String InsuranceCoverage=TestData.getCellData(sheetName,"Insurance Coverage",row);
						String PhoneNbr=TestData.getCellData(sheetName,"Phone Nbr",row);
						String PhoneNbr1 = PhoneNbr.substring(0, 3);
						String PhoneNbr2 = PhoneNbr.substring(3, 6);
						String PhoneNbr3 = PhoneNbr.substring(6, 10);
						String InsuranceCompany =TestData.getCellData(sheetName,"Insurance Company",row);
						String InsuranceExpiryDate=TestData.getCellData(sheetName,"Insurance Expiry Date",row);
						String PolicyNumber=TestData.getCellData(sheetName,"Policy Number",row);
						String InsuranceExpiryDate0[] =InsuranceExpiryDate.split("/");
						String InsuranceExpiryDate1 = InsuranceExpiryDate0[0];
						String InsuranceExpiryDate2 = InsuranceExpiryDate0[1];
						String InsuranceExpiryDate3 = InsuranceExpiryDate0[2];
						driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
						driver.findElement(By.xpath("//*[@id='appraisal']")).sendKeys(AppraisalValue);
						//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						driver.findElement(By.name("button1")).click();
						test.log(LogStatus.PASS, "click on Update 1 button ");
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));

						driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
						driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
						driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
						driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
						driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
						driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);
						driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
						driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
						driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
						driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
						driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
						driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
						driver.findElement(By.name("button2")).click();			
						driver.findElement(By.name("button2")).click();	

						test.log(LogStatus.PASS, "click on Update 2 button ");
						Thread.sleep(8000);

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
						driver.findElement(By.name("process")).click();

						test.log(LogStatus.PASS, "click on process Loan button ");
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						Thread.sleep(5000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("vehicleKey")).sendKeys("Yes");					
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("requestBean.siilBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");

							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);

						}
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						test.log(LogStatus.PASS, "Click on Finish Loan Button");
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
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("LOC"))
					{

						test.log(LogStatus.INFO, "Navigated to New Loan Screen");
						driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
						Thread.sleep(5000);
						//driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						//driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[1]/input")).click();
						}	
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}

					//html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/input
				}
				else
				{
					test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
				}
			}
		}

	}

	public void Void(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   	
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


				if(ProductID.equals("LOC"))
				{
					String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "DisbType Type is entered as "+TenderType);
					//String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
					System.out.println(Pmt);
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

					//	driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					//	test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



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
					//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input
					if (driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
						//if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
						driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "Void Payment is not Completed Successfully ");
					}
				}
			}
		}
	}


	public void DrawLoanwithACH(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				test.log(LogStatus.INFO, "VoidDrawLoan with-SSN: " +SSN +" :: Starts");
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
				driver.findElement(By.name("disbType")).sendKeys("ACH");
				// driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
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

	public void Rescind(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   	
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
				driver.findElement(By.name("transactionList")).sendKeys("Rescind");
				test.log(LogStatus.PASS, "Transaction Type is selected as Rescind");	
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();


				if(ProductID.equals("LOC"))
				{
					String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "DisbType Type is entered as "+TenderType);
					//String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
					System.out.println(Pmt);
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

					//	driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					//	test.log(LogStatus.PASS, "Encryption key is entered as "+Eankey);



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
					//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input
					if (driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
						//if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Rescind Loan is Completed Successfully ");
						driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "Rescind Payment is not Completed Successfully ");
					}
				}
			}
		}
	}

	public void PartialPayment(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					//String Pmt= driver.findElement(By.name("payOffAmount")).getAttribute("value");						
					// System.out.println(Pmt);
					driver.findElement(By.name("requestBean.paymentAmt")).clear();
					driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("30");
					driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("30");
					test.log(LogStatus.PASS, "Tender Amt is entered as "+30);							
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();

					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
					test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
					// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
					//*[@id="btnADV_Yes"]
					//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]

					//for( String winHandle1 : driver.getWindowHandles())
					//{
					//driver.switchTo().window(winHandle1);
					//}			
					/* driver.switchTo().defaultContent();
										 driver.switchTo().frame("mainFrame");
										 driver.switchTo().frame("main");


										 if(driver.findElement(By.name("ok")).isDisplayed())
											{
											 test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
											 driver.findElement(By.name("ok")).click();
											}
										 else
											{
												test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
											}
					 */

				}

			}

		}
	}
	public void Payments(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					String Pmt= driver.findElement(By.name("requestBean.paymentAmt")).getAttribute("value");						
					System.out.println(Pmt);
					// driver.findElement(By.name("requestBean.paymentAmt")).clear();
					//driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("30");
					driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();

					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
					//*[@id="btnADV_Yes"]
					driver.findElement(By.xpath("//*[@id='btnADV_Yes']")).click();
					//*[@id="btnADV_Yes"]
					//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]

					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}	

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					String Pmt1= driver.findElement(By.name("payOffAmount")).getAttribute("value");						
					System.out.println(Pmt);
					// driver.findElement(By.name("requestBean.paymentAmt")).clear();
					//driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("30");
					driver.findElement(By.name("tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("tenderAmount")).sendKeys(Pmt1);
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();



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

	public void CustomerDefault(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture Cure End Dtae"+DueDate);
				System.out.print(DueDate);			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.close();	
				//System.out.print(DueDate);	
				// driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				//String DueDate="12/12/2020";
				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);


				Date DDueDate = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDate);
				cal.add(Calendar.DATE, 0);
				Date DDueDateminus1= cal.getTime();

				// String DueDateminus1 =df.format(DDueDateminus1);
				String DueDate0[] =DueDate.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];




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
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
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


				//Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");

				//driver.switchTo().defaultContent();
				//driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Default Loc")).click();
				test.log(LogStatus.PASS, "Clicked on Default Loc");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(6000);
				WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
				Actions action = new Actions(driver);								        
				action.moveToElement(element).build().perform();

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(6000);


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);


				// driver.findElement(By.linkText("iPads")).click();
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				Thread.sleep(6000);


			}
		}
	}

	public void DefaultPaymentStatus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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
				CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();

				test.log(LogStatus.PASS,"Payment status is Default."+CheckStaus);
				System.out.print(CheckStaus);	
				//driver.close();

			}
		}
	}

	public void PaymentStatus(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
				CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS,"Payment status is ."+CheckStaus);
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(CheckStaus);	
				// driver.close();

			}
		}
	}

	public void MinPayment(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);	
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
					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]")).getText();					
					System.out.println(Pmt);
					driver.findElement(By.name("requestBean.paymentAmt")).clear();
					driver.findElement(By.name("requestBean.paymentAmt")).sendKeys(Pmt);
					driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();

					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
					//*[@id="btnADV_Yes"]
					/* driver.findElement(By.xpath("//*[@id='btnADV_Yes']")).click();
										//*[@id="btnADV_Yes"]
										//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]

										for( String winHandle1 : driver.getWindowHandles())
										{
										    driver.switchTo().window(winHandle1);
										}	

										driver.switchTo().defaultContent();
										 driver.switchTo().frame("mainFrame");
										 driver.switchTo().frame("main");
										 String Pmt1= driver.findElement(By.name("payOffAmount")).getAttribute("value");						
										 System.out.println(Pmt);
										// driver.findElement(By.name("requestBean.paymentAmt")).clear();
										//driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("30");
										driver.findElement(By.name("tenderType")).sendKeys(TenderType);
										 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
										driver.findElement(By.name("tenderAmount")).sendKeys(Pmt1);
										test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
										 driver.findElement(By.name("password")).sendKeys(Password);
										 driver.findElement(By.name("Submit22")).click();
					 */


					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");


					if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
					{

						driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
						test.log(LogStatus.INFO, "Min Payment Completed  with-SSN: " +SSN +" :: is Successful");
					}
					else
					{
						test.log(LogStatus.FAIL, "Payoff not Completed Successfully ");
					}


				}

			}

		}
	}

	public void EncryptionKey_Void(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/"+FileName);   	
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
					//	String Pmt= driver.findElement(By.xpath(" /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();						
					//	System.out.println(Pmt);
					//driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					//test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);

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

				Thread.sleep(500);


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
				test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
				/*driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					if(ProductID.equals("LOC"))
					{

						if(driver.findElement(By.name("checkyes")).isDisplayed())
						{
							test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
							driver.findElement(By.name("checkyes")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "Void Payment is not Completed Successfully ");
						}
					}*/
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


				/*	if(ProductID.equals("LOC"))
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
	  			driver.switchTo().frame("main");*/
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


				//DueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();

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
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(4000);
				this.Login(UserName, Password, StoreId);
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
				driver.findElement(By.name("transactionList")).sendKeys("LOC Closure");
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
					test.log(LogStatus.PASS, "Password is selected as "+Password);
					driver.findElement(By.name("close loc")).click();

					test.log(LogStatus.PASS, "Clicked on Finish Closure Loan button ");
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

	public void DeliquentPaymentStatus1(String SSN,String FileName) throws Exception
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
				String CheckStaus1=null;
				CheckStaus1 = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();
				//                                      //*[@id="revolvingCreditHistTable"]/tbody/tr[10]/td[3]/span[2]
				//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
				test.log(LogStatus.PASS,"Payment status is Cure"+CheckStaus1);
				//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
				System.out.print(CheckStaus1);

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

	public void Default_PartialPayment(String SSN,String FileName) throws Exception
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
				String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Default_PartialPayment with-SSN: " +SSN +" :: is ::"+"Starts");
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

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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
				driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
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

				//String PaymentAmount=null;

				//PaymentAmount = driver.findElement(By.name("totalDue")).getAttribute("value");
				//test.log(LogStatus.PASS, "Capture the Payment Amt":+PaymentAmount);
				driver.findElement(By.name("requestBean.paymentAmt")).clear();
				driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("10");
				driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
				test.log(LogStatus.PASS, "Select the Tender Type");

				driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("10");
				test.log(LogStatus.PASS, "Enter the Tender Amount");

				driver.findElement(By.name("password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Click on Finish Payment Button");

				test.log(LogStatus.INFO, "Default_PartialPayment with-SSN: " +SSN +" :: is ::"+"Successful");


			}
		}
	}
	public void Default_Payment(String SSN,String FileName) throws Exception
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
				String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Starts");
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

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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
				driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
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

				String PaymentAmount=null;

				PaymentAmount = driver.findElement(By.name("requestBean.paymentAmt")).getAttribute("value");
				//test.log(LogStatus.PASS, "Capture the Payment Amt":+PaymentAmount);
				Thread.sleep(2000);
				driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
				test.log(LogStatus.PASS, "Select the Tender Type");

				driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(PaymentAmount);
				test.log(LogStatus.PASS, "Enter the Tender Amount");

				driver.findElement(By.name("password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Click on Finish Payment Button");

				test.log(LogStatus.INFO, "Default_Payment with-SSN: " +SSN +" :: is ::"+"Successful");


			}
		}
	}

	public void DefaultPaymentStatus1(String SSN,String FileName) throws Exception
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
				test.log(LogStatus.INFO, "DefaultPaymentStatus with-SSN: " +SSN +" :: is ::"+"Starts");
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

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
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
				CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();

				test.log(LogStatus.PASS,"Default status is :: Null");
				test.log(LogStatus.INFO, "DefaultPaymentStatus with-SSN: " +SSN +" :: is ::"+"Successful");
				System.out.print(CheckStaus);	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.quit();//need to change to close
				driver = new InternetExplorerDriver();

			}
		}
	}

	public void DefaultPayment_Void(String SSN,String FileName) throws Exception
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
				String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "DefaultPayment_Void with-SSN: " +SSN +" :: is ::"+"Starts");
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

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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

				String PaymentAmount=null;

				driver.findElement(By.name("defPaymentRequestBeanRC.tenderType")).sendKeys(ESign_TenderType);
				test.log(LogStatus.PASS, "Select the Disb Type");

				driver.findElement(By.name("password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Click on the Finish Void Default Payment");

				test.log(LogStatus.INFO, "DefaultPayment_Void with-SSN: " +SSN +" :: is ::"+"Successful");



			}
		}
	}

	public void Agestore_Loandate(String SSN,String FileName,int Days) throws Exception
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();

				login.Login(UserName, Password, StoreID, driver, AppURL, test);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);	
				appUrl = AppURL;
				Thread.sleep(5000);
				Thread.sleep(1000);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
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


				String LoanDate=null;

				LoanDate = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[3]")).getText();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[3]

				test.log(LogStatus.PASS, "Capture LoanDate"+LoanDate);
				System.out.print(LoanDate);	
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

				String DDueDate[] =LoanDate.split("/");

				Date DDueDateminus1 = df.parse(LoanDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1= cal.getTime();

				LoanDate =df.format(DDueDate1);

				String DueDate0[] =LoanDate.split("/");

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
		//driver.close();

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
				Thread.sleep(1000);

				//if(ProductID.equals("LOC"))
				//{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//}
				//  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(1000);
				int n=driver.findElements(By.xpath("//select[@name='transactionList']/option")).size();

				for(int i=1;i<=n;i++)
				{
					String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getText();


					if(transactino_value.equalsIgnoreCase("Draw"))
					{
						test.log(LogStatus.PASS, "Draw option is available in the list");
					}
					else
					{
						test.log(LogStatus.PASS, "Draw option is not available in the list");
					}
				}
			}}
	}

	public void Active_Military_Start(String SSN,String FileName) throws Exception
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
				//driver = new InternetExplorereDriver();
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
				//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("li[id='900000']")).click();				
				test.log(LogStatus.PASS, "Clicked on Borrower");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(1000);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='988190443']")));
				driver.findElement(By.cssSelector("li[id='988190443']")).click();			
				test.log(LogStatus.PASS, "Clicked on Active Military");	
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				//driver.findElement(By.xpath("//*[contains(text(),'Go')]")).click();			
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//Thread.sleep(1000);
				//driver.findElement(By.name("menu1")).sendKeys("Active Military");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.xpath("//html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input]")).click();
				///html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input
				test.log(LogStatus.PASS, "Click on GO Button");

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				test.log(LogStatus.PASS, "Accept the Alert");				
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				Thread.sleep(2000);
				driver.findElement(By.name("requestBean.activeMilitaryType")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("finishBank")).click();

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

				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@name='ok' and @type='button']")).click();
				//driver.findElement(By.xpath("///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input]")).click();
				///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input
				test.log(LogStatus.PASS, "Active Military Transaction Completed Successfully.");
			}
		}
	}
	public void Default_WOProc(String SSN,String FileName) throws Exception
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
					//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]
					//driver.findElement(By.name("button")).click();
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/*	DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();*/
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture PWO  Dtae"+DueDate);
				System.out.print(DueDate);			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.close();	
				//System.out.print(DueDate);	
				// driver.close();

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
				/*	Date DDueDate = df.parse(DueDate);
					Calendar cal = Calendar.getInstance();
					cal.setTime(DDueDate);
					cal.add(Calendar.DATE, 0);
					cal.add(Calendar.DATE, 90);
					Date DDueDateminus1= cal.getTime();

					 String DueDateminus1 =df.format(DDueDateminus1);
					String DueDate0[] =DueDate.split("/");
					String DueDate1 = DueDate0[0];
					String DueDate2 = DueDate0[1];
					String DueDate3 = DueDate0[2];*/
				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, 90);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				WebDriverWait wait = new WebDriverWait(driver, 10000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
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
				driver.findElement(By.linkText("Writeoff Loc")).click();
				test.log(LogStatus.PASS, "Clicked on Writeoff Loc");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(6000);
				/* WebElement element = driver.findElement(By.name("cancel"));
									        Actions action = new Actions(driver);								        
									        action.moveToElement(element).build().perform();*/

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
				//Thread.sleep(6000);
				//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);


				//try { 
				//Alert alert = driver.switchTo().alert();
				//alert.dismiss();
				//if alert present, accept and move on.														

				//}
				//catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
				//}


				Thread.sleep(6000);
				WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
				Actions action = new Actions(driver);								        
				action.moveToElement(element).build().perform();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);


				// driver.findElement(By.linkText("iPads")).click();
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				Thread.sleep(6000);


			}
		}
	}
	public void WOPaymentStatus(String SSN,String FileName) throws Exception
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

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					//  /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
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

				if(driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).isDisplayed())
				{
					test.log(LogStatus.PASS,"Payment status is Default Displayed sucessfully.");
					CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
					test.log(LogStatus.PASS,"Payment status is Writeoff."  +CheckStaus);

				}
				else
				{
					test.log(LogStatus.PASS,"Payment status is  Writeoff not Displayed sucessfully.");
				}

				System.out.print(CheckStaus);	
				//driver.close();

			}
		}
	}	

	public void Active_Military_End(String SSN,String FileName) throws Exception
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
				driver=new InternetExplorerDriver();
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
				//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("li[id='900000']")).click();				
				test.log(LogStatus.PASS, "Clicked on Borrower");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				Thread.sleep(1000);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='988190443']")));
				driver.findElement(By.cssSelector("li[id='988190443']")).click();			
				test.log(LogStatus.PASS, "Clicked on Active Military");	
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.bnklocnbr")).sendKeys(StoreID);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				//driver.findElement(By.xpath("//*[contains(text(),'Go')]")).click();			
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//Thread.sleep(1000);
				//driver.findElement(By.name("menu1")).sendKeys("Active Military");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.xpath("//html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input]")).click();
				///html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input
				test.log(LogStatus.PASS, "Click on GO Button");

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				test.log(LogStatus.PASS, "Accept the Alert");				
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				Thread.sleep(2000);
				driver.findElement(By.name("finishBank")).click();


				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				Thread.sleep(3000);
				driver.findElement(By.xpath("//input[@name='ok' and @type='button']")).click();
				//driver.findElement(By.xpath("///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input]")).click();
				///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr[2]/td/input
				test.log(LogStatus.PASS, "Active Military Transaction Completed Successfully.");
			}
		}
	}


	public void NACHA(String SSN,String FileName,int Days) throws Exception
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				appUrl = AppURL;


				CSRLoginpage login = new CSRLoginpage();

				login.Login(UserName, Password, StoreID, driver, AppURL, test);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);	

				appUrl = AppURL;


				Thread.sleep(5000);
				Thread.sleep(1000);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
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
				driver.findElement(By.linkText("ACH")).click();
				test.log(LogStatus.PASS, "Clicked on ACH");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Green Bank")).click();
				test.log(LogStatus.PASS, "Clicked on Green Bank");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Green Bank NACHA File")).click();
				test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("Daily Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on Daily Jobs");
				Thread.sleep(5000);

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
					test.log(LogStatus.PASS, "Process NACHA file successfully.");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process NACHA is not updated successfully.");
				}




			}
		}
	}


	public void WaiveFee(String SSN,String FileName) throws Exception{


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
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				String TranID=null;
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(4000);

				String Eankey=null;
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
				//	}
				//driver.manage().window().maximize();
				//driver = new InternetExplorerDriver();
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();	
				Thread.sleep(2000);
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

				String mainwindow=driver.getWindowHandle();
				test.log(LogStatus.PASS, "title is"+mainwindow);
				/*	String TranID = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[9]/td[4]/input")).getText();

							test.log(LogStatus.PASS, "TranId captured:"+TranID);	
							String TranID0[] =TranID.split(":");
							String TranID1 = TranID0[0];
							String TranID2 =  TranID0[1]; */
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
				driver.findElement(By.name("transactionList")).sendKeys("WaiveFee");
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
				Thread.sleep(1000);                                                      	//origTranNbr
				TranID = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[9]/td[4]/input")).getAttribute("value");
				System.out.println(TranID);                                             
				test.log(LogStatus.PASS, "TranId captured:"+TranID);	
				/*String TranID0[] =TranID.split(":");
								String TranID1 = TranID0[0];
								String TranID2 =  TranID0[1]; */
				Thread.sleep(3000);
				//driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();

				//         	
				//name="NO"





				InternetExplorerDriver driver1= new InternetExplorerDriver();
				driver1.get(AdminURL);


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
				driver1.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver1.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				//Click Login Button
				driver1.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);

				driver1.switchTo().defaultContent();
				driver1.switchTo().frame("topFrame");
				driver1.findElement(By.xpath("//*[contains(text(),'Employee')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver1.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver1.switchTo().defaultContent();
				driver1.switchTo().frame("mainFrame");
				driver1.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver1.findElement(By.linkText("Encryption Key")).click();
				test.log(LogStatus.PASS, "Clicked on Encryption Key");
				driver1.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver1.switchTo().defaultContent();
				driver1.switchTo().frame("mainFrame");
				driver1.findElement(By.linkText("Encryption")).click();
				test.log(LogStatus.PASS, "Clicked on Encryption");
				Thread.sleep(5000);

				driver1.switchTo().defaultContent();
				driver1.switchTo().frame("mainFrame");
				driver1.switchTo().frame("main");
				driver1.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreId);
				test.log(LogStatus.PASS, "Store number Entered");	
				Thread.sleep(3000);
				driver1.findElement(By.name("requestBean.tranNbr")).sendKeys(TranID);
				test.log(LogStatus.PASS, "Tran number Entered");	

				/*		driver.findElement(By.name("trancd")).sendKeys("Advance-ADV");
			test.log(LogStatus.PASS, "Trancd selected");*/

				/*	driver.findElement(By.name("trancd")).sendKeys("Early pay off-EPAY");
			test.log(LogStatus.PASS, "Trancd selected");*/

				/*	driver.findElement(By.name("trancd")).sendKeys("Payment-PAYIL");
			test.log(LogStatus.PASS, "Trancd selected");*/

				driver1.findElement(By.name("trancd")).sendKeys("Waive Fee-WF");
				test.log(LogStatus.PASS, "Trancd selected");

				driver1.findElement(By.name("GetKey")).click();
				test.log(LogStatus.PASS, "GetKey clicked");

				Eankey = driver1.findElement(By.name("EanKey")).getAttribute("value");
				test.log(LogStatus.PASS, "GetKey clicked:" +Eankey);
				Thread.sleep(2000);
				driver1.close();
				/*driver = new InternetExplorerDriver();
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
			driver.findElement(By.name("transactionList")).sendKeys("WaiveFee");
			test.log(LogStatus.PASS, "Transaction Type is selected as WaiveFee");					
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();	*/		 



				test.log(LogStatus.PASS,"title is"+mainwindow);
				driver.switchTo().window(mainwindow);
				test.log(LogStatus.PASS,"title is"+driver.getTitle());	


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[4]/input")).clear();
					String 	Interest=driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[3]/input")).getAttribute("value");

					driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[4]/input")).sendKeys(Interest);
					test.log(LogStatus.PASS, "Interest Amt is entered as "+Interest);	

					driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[8]/td[4]/input")).clear();
					String CustomaryFee=driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[8]/td[3]/input")).getAttribute("value");

					driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[8]/td[4]/input")).sendKeys(CustomaryFee);
					test.log(LogStatus.PASS, "Interest Amt is entered as "+CustomaryFee);

					driver.findElement(By.name("encryptionKey")).sendKeys(Eankey);
					test.log(LogStatus.PASS, "Encryption Key entered as "+Eankey);
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();

					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Waive button ");
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


					if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/input")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Waive fee Completed Successfully ");
						driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/input")).click();

					}
					else
					{
						test.log(LogStatus.FAIL, "waive fee not Completed Successfully ");
					}




				}

			}

		}
	}

	public void ACHReturnPosting(String SSN,String FileName) throws Exception
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


				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
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

				driver.findElement(By.linkText("ACH Return")).click();
				test.log(LogStatus.PASS, "Clicked on ACH Return");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.locationNbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);					  	        			   
				//Click Login Button
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.name("requestBean.chkName")).click();
				test.log(LogStatus.PASS, "Customer Record CheckBox Selected");					  	        			   
				//Click Login Button
				driver.findElement(By.name("rtnReasonId")).sendKeys("R01-Insufficient Funds");
				test.log(LogStatus.PASS, "Return Reason Selected as ::  R01-Insufficient Funds");
				driver.findElement(By.name("CmdReturnPosting")).click();
				test.log(LogStatus.PASS, "Clicked on ACH Return Posting button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if(driver.findElement(By.name("Ok")).isDisplayed())
				{
					driver.findElement(By.name("Ok")).click();
					test.log(LogStatus.PASS, "ACH Return Posting Done Successfull");	
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
				Thread.sleep(1000);
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
						test.log(LogStatus.PASS, "Void Partial Payment Completed Successfully ");
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


	public void Default_WOProc(String SSN,String FileName,int days) throws Exception
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
					//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]
					//driver.findElement(By.name("button")).click();
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/*	DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();*/
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
				//*[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[3]/span[2]
				test.log(LogStatus.PASS, "Capture CureEndDate as :: "+DueDate);
				System.out.print(DueDate);			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.close();	
				//System.out.print(DueDate);	
				// driver.close();

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
				/*	Date DDueDate = df.parse(DueDate);
	    			Calendar cal = Calendar.getInstance();
	    			cal.setTime(DDueDate);
	    			cal.add(Calendar.DATE, 0);
	    			cal.add(Calendar.DATE, 90);
	    			Date DDueDateminus1= cal.getTime();

	    			 String DueDateminus1 =df.format(DDueDateminus1);
	    			String DueDate0[] =DueDate.split("/");
	    			String DueDate1 = DueDate0[0];
	    			String DueDate2 = DueDate0[1];
	    			String DueDate3 = DueDate0[2];*/
				String DDueDate[] =DueDate.split("/");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				WebDriverWait wait = new WebDriverWait(driver, 10000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
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
				driver.findElement(By.linkText("Writeoff Loc")).click();
				test.log(LogStatus.PASS, "Clicked on Writeoff Loc");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(6000);
				/* WebElement element = driver.findElement(By.name("cancel"));
	    							        Actions action = new Actions(driver);								        
	    							        action.moveToElement(element).build().perform();*/

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
				//Thread.sleep(6000);
				//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);


				//try { 
				//Alert alert = driver.switchTo().alert();
				//alert.dismiss();
				//if alert present, accept and move on.														

				//}
				//catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
				//}


				Thread.sleep(6000);
				WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
				Actions action = new Actions(driver);								        
				action.moveToElement(element).build().perform();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);


				// driver.findElement(By.linkText("iPads")).click();
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				Thread.sleep(6000);
				test.log(LogStatus.INFO, "WriteOff Completed Successfully ");

			}
		}

	}

	public void EditBorrower(String SSN,String FileName,int Days) throws Exception

	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);

		int lastrow=TestData.getLastRow("NewLoan");

		System.out.println("NewLoan"+lastrow);

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

				String MonthlyPayDay=TestData.getCellData(sheetName,"MonthlyPayDay",row);

				String Income_PayFrequency=TestData.getCellData(sheetName,"Income_PayFrequency",row);

				String SemiMonOthFirstDay=TestData.getCellData(sheetName,"SemiMonOthFirstDay",row);

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

				String Monthlydate=null;

				String Monthlydate1=null;

				if(MonthlyPayDay.length()==3)

				{

					Monthlydate = MonthlyPayDay.substring(0, 1);

					Monthlydate1="0"+Monthlydate;

				}

				if(MonthlyPayDay.length()==4)

				{

					Monthlydate1 = MonthlyPayDay.substring(0, 2);

				}

				System.out.println(Monthlydate1);

				WebDriverWait wait = new WebDriverWait(driver, 1000);

				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='902000']")));

				driver.findElement(By.cssSelector("li[id='902000']")).click();

				//driver.findElement(By.cssSelector("//*[@id='902000']/a")).click();

				test.log(LogStatus.PASS, "Clicked on Edit");

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
				try {

					Alert alert = driver.switchTo().alert();

					alert.dismiss();

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

				String NextPayday =null;

				if(Income_PayFrequency.equals("Bi-Weekly"))

				{

					// //*[@id="biWk_second"] //*[@id="biWk_second"]/text()
					NextPayday = driver.findElement(By.xpath("//*[@id='biWeekly']/td/table/tbody/tr[2]/td[2]/input")).getAttribute("value");

					String PayStubReviewedDate0[] =NextPayday.split("/");

					String PayStubReviewedDate2 = PayStubReviewedDate0[0];

					String month=null;

					if(PayStubReviewedDate2.length()==1)

					{

						month = "0"+PayStubReviewedDate0[0];

					}

					else

					{

						month = PayStubReviewedDate0[0];

					}

					// int day= Integer.parseInt(PayStubReviewedDate2);

					String Day = PayStubReviewedDate0[1];

					if(Day.length()==1)

					{

						Day = "0"+PayStubReviewedDate0[1];

					}

					else

					{

						Day = PayStubReviewedDate0[1];

					}

					String Year = PayStubReviewedDate0[2];

					NextPayday = month+"/"+Day+"/"+Year;

				}

				if(Income_PayFrequency.equals("Monthly"))

				{

					driver.switchTo().defaultContent();

					driver.switchTo().frame("bottom");

					String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

					String Busdate[]=BusinessDt.split(":");

					String date = Busdate[1];

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					Date d1 = df.parse(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(d1);

					cal.add(Calendar.DATE, Days);

					Date PayStubReviewedDate1= cal.getTime();

					String PayStubReviewedDate =df.format(PayStubReviewedDate1);

					//Date D=Add(date1,7);

					//System.out.println(date);

					//System.out.println(PayStubReviewedDate);

					String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");

					String PayStubReviewedDate2 = PayStubReviewedDate0[0];

					int day= Integer.parseInt(PayStubReviewedDate2);

					String PayStubReviewedDate3 = PayStubReviewedDate0[1];

					String PayStubReviewedDate4 = PayStubReviewedDate0[2];

					int yyyy= Integer.parseInt(PayStubReviewedDate4);

					int DD= day+1;

					String month="0"+String.valueOf(DD);

					String days=Monthlydate1;

					String year=null;

					if(day==12)

					{

						yyyy=yyyy+1;

						year=String.valueOf(yyyy);

					}

					else

					{

						year=PayStubReviewedDate4;

					}

					NextPayday = month+"/"+days+"/"+year;

				}

				if(Income_PayFrequency.equals("Semi-Monthly"))

				{

					driver.switchTo().defaultContent();

					driver.switchTo().frame("bottom");

					String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

					String Busdate[]=BusinessDt.split(":");

					String date = Busdate[1];

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					Date d1 = df.parse(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(d1);

					cal.add(Calendar.DATE, 0);

					Date PayStubReviewedDate1= cal.getTime();

					String PayStubReviewedDate =df.format(PayStubReviewedDate1);

					//Date D=Add(date1,7);

					//System.out.println(date);

					//System.out.println(PayStubReviewedDate);

					String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");

					String PayStubReviewedDate2 = PayStubReviewedDate0[0];

					int day= Integer.parseInt(PayStubReviewedDate2);

					String PayStubReviewedDate3 = PayStubReviewedDate0[1];

					String PayStubReviewedDate4 = PayStubReviewedDate0[2];

					int yyyy= Integer.parseInt(PayStubReviewedDate4);

					int DD= day+1;

					String month="0"+String.valueOf(DD);

					String days="01";

					String year=null;

					if(day==12)

					{

						yyyy=yyyy+1;

						year=String.valueOf(yyyy);

					}

					else

					{

						year=PayStubReviewedDate4;

					}

					NextPayday = month+"/"+days+"/"+year;

				}

				if(Income_PayFrequency.equals("Weekly"))

				{

					driver.switchTo().defaultContent();

					driver.switchTo().frame("bottom");

					String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

					String Busdate[]=BusinessDt.split(":");

					String date = Busdate[1];

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					Date d1 = df.parse(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(d1);

					if(SemiMonOthFirstDay.equals("Monday"))

					{

						cal.add(Calendar.DATE, 1);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Tuesday"))

					{

						cal.add(Calendar.DATE, 2);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Wednesday"))

					{

						cal.add(Calendar.DATE, 3);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Thursday"))

					{

						cal.add(Calendar.DATE, 4);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Friday"))

					{

						cal.add(Calendar.DATE, 5);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Saturday"))

					{

						cal.add(Calendar.DATE, 6);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Sunday"))

					{

						cal.add(Calendar.DATE, 7);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					//Date D=Add(date1,7);

					//System.out.println(date);

					//System.out.println(PayStubReviewedDate);

				}

				test.log(LogStatus.PASS,"Next Paydate."+NextPayday);

				/* driver.switchTo().defaultContent();

		    driver.switchTo().frame("topFrame");

		    driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();*/

				driver.quit(); //need to change to c

				driver = new InternetExplorerDriver();

				driver.get(AdminURL);

				test.log(LogStatus.INFO, "Admin portal is launched");

				driver.manage().window().maximize();

				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);



				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				Thread.sleep(8000);

				Thread.sleep(8000);

				/* DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

		    Date d1 = df.parse(date);

		    Calendar cal = Calendar.getInstance();

		    cal.setTime(d1);

		    cal.add(Calendar.DATE, -10);*/

				Date DDueDate = df.parse(NextPayday);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDate);

				cal.add(Calendar.DATE,Days);

				Date DDueDateminus1= cal.getTime();

				String DueDateminus1 =df.format(DDueDateminus1);

				String NextPayday0[] =DueDateminus1.split("/");

				String NextPayday1 = NextPayday0[0];

				String NextPayday2 = NextPayday0[1];

				String NextPayday3 = NextPayday0[2];

				//WebDriverWait wait = new WebDriverWait(driver, 10000);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				driver.findElement(By.linkText("QA Jobs")).click();

				test.log(LogStatus.PASS, "Clicked on QA Jobs");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

				driver.findElement(By.name("storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

				Thread.sleep(5000);

				driver.findElement(By.name("beginMonth")).clear();

				driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1);

				test.log(LogStatus.PASS, "beginMonth is entered: "+NextPayday1);

				driver.findElement(By.name("beginDay")).clear();

				driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);

				test.log(LogStatus.PASS, "beginDay is entered: "+NextPayday2);

				driver.findElement(By.name("beginYear")).clear();

				driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);

				test.log(LogStatus.PASS, "beginYear is entered: "+NextPayday3);

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

				}

				else

				{

					test.log(LogStatus.FAIL, "Process Date updated successfully.");

				}


				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

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

				driver.findElement(By.linkText("Default Loc")).click();

				test.log(LogStatus.PASS, "Clicked on Default Loc");



				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				//Thread.sleep(6000);

				/* WebElement element = driver.findElement(By.name("cancel"));

		    Actions action = new Actions(driver);

		    action.moveToElement(element).build().perform();*/

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();

				Thread.sleep(6000);

				WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));

				Actions action = new Actions(driver);

				action.moveToElement(element).build().perform();

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				try {

					Alert alert = driver.switchTo().alert();

					alert.dismiss();

					//if alert present, accept and move on.

				}

				catch (NoAlertPresentException e) {

					//do what you normally would if you didn't have the alert.

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("beginMonth")).clear();

				driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1);

				test.log(LogStatus.PASS, "beginMonth is entered: "+NextPayday1);

				driver.findElement(By.name("beginDay")).clear();

				driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);

				test.log(LogStatus.PASS, "beginDay is entered: "+NextPayday2);

				driver.findElement(By.name("beginYear")).clear();

				driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);

				test.log(LogStatus.PASS, "beginYear is entered: "+NextPayday3);



				// driver.findElement(By.linkText("iPads")).click();

				driver.findElement(By.name("submit")).click();

				test.log(LogStatus.PASS, "Clicked on submit button");

				Thread.sleep(6000);



			}

		}

	}
	public void Check_RCCSchd_WO(String SSN,String FileName,int Days) throws Exception{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";
		String dt = null;
		String dt1 = null;
		for(int row=2;row<=lastrow;row++)
		{
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{

				String
				ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String UserName
				=TestData.getCellData(sheetName,"UserName",row);
				String Password
				=TestData.getCellData(sheetName,"Password",row);
				String StoreID =TestData.getCellData(sheetName,"StoreID",row);
				String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
				String CollateralType =
						TestData.getCellData(sheetName,"ESign_CollateralType",row);
				String PayFrequency =
						TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String Parent_Window = driver.getWindowHandle();
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL =TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				this.Login(UserName,Password,StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(5000);
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60,
						TimeUnit.SECONDS);
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

				//  <input type="button" value="RCC Schedule" class="sortbuttons"onclick="rccSchedule()">
				String val =driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).getAttribute("value");
				///html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				//    driver.findElement(By.xpath("//input[@value='Go' and@type='button']")).click();

				//if (driver.findElement(By.xpath("//input[@type='button'and @value='RCC Schedule']")).isDisplayed())
				//if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				if(val.contains("RCC"))
				{
					test.log(LogStatus.PASS, "RCC Schedule Generated");

				}
				else
				{
					// this.EditBorrower_WO(SSN3, FileName);
					String
					TxnType=TestData.getCellData(sheetName,"TxnType",row);

					String TenderType =
							TestData.getCellData(sheetName,"TenderType",row);


					String
					MonthlyPayDay=TestData.getCellData(sheetName,"MonthlyPayDay",row);

					String
					Income_PayFrequency=TestData.getCellData(sheetName,"Income_PayFrequency",row);

					String
					SemiMonOthFirstDay=TestData.getCellData(sheetName,"SemiMonOthFirstDay",row);

					System.out.println(AdminURL);

					test.log(LogStatus.INFO, "Scheduler-Store Aging");

					System.out.println(ProductID);

					appUrl = AppURL;

					this.Login(UserName,Password,StoreID);


					Thread.sleep(5000);

					String Monthlydate=null;

					String Monthlydate1=null;

					if(MonthlyPayDay.length()==3)

					{

						Monthlydate = MonthlyPayDay.substring(0, 1);

						Monthlydate1="0"+Monthlydate;

					}

					if(MonthlyPayDay.length()==4)

					{

						Monthlydate1 = MonthlyPayDay.substring(0, 2);

					}

					System.out.println(Monthlydate1);

					WebDriverWait wait = new WebDriverWait(driver, 1000);

					driver.switchTo().frame("topFrame");

					driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();

					test.log(LogStatus.PASS, "Clicked on Borrower");

					driver.manage().timeouts().implicitlyWait(120,
							TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='902000']")));

					driver.findElement(By.cssSelector("li[id='902000']")).click();

					//driver.findElement(By.cssSelector("//*[@id='902000']/a")).click();

					test.log(LogStatus.PASS, "Clicked on Edit");

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

					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't havethe alert.
					}

					for(String winHandle : driver.getWindowHandles()){

						driver.switchTo().window(winHandle);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					String NextPayday =null;

					if(Income_PayFrequency.equals("Bi-Weekly"))

					{

						// //*[@id="biWk_second"]
						//*[@id="biWk_second"]/text()
						//*[@id="biWk_second"]
						NextPayday =
								driver.findElement(By.xpath("//*[@id='biWeekly']/td/table/tbody/tr[2]/td[2]/input")).getAttribute("value");

						String PayStubReviewedDate0[] =NextPayday.split("/");

						String PayStubReviewedDate2 = PayStubReviewedDate0[0];

						String month=null;

						if(PayStubReviewedDate2.length()==1)

						{

							month = "0"+PayStubReviewedDate0[0];

						}

						else

						{

							month = PayStubReviewedDate0[0];

						}

						// int day= Integer.parseInt(PayStubReviewedDate2);

						String Day = PayStubReviewedDate0[1];

						if(Day.length()==1)

						{

							Day = "0"+PayStubReviewedDate0[1];

						}

						else

						{

							Day = PayStubReviewedDate0[1];

						}

						String Year = PayStubReviewedDate0[2];

						NextPayday = month+"/"+Day+"/"+Year;

					}

					if(Income_PayFrequency.equals("Monthly"))

					{

						driver.switchTo().defaultContent();

						driver.switchTo().frame("bottom");

						String BusinessDt=
								driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

						String Busdate[]=BusinessDt.split(":");

						String date = Busdate[1];

						DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

						Date d1 = df.parse(date);

						Calendar cal = Calendar.getInstance();

						cal.setTime(d1);

						cal.add(Calendar.DATE, Days);

						Date PayStubReviewedDate1= cal.getTime();

						String PayStubReviewedDate
						=df.format(PayStubReviewedDate1);

						//Date D=Add(date1,7);

						//System.out.println(date);

						//System.out.println(PayStubReviewedDate);

						String PayStubReviewedDate0[]
								=PayStubReviewedDate.split("/");

						String PayStubReviewedDate2 = PayStubReviewedDate0[0];

						int day= Integer.parseInt(PayStubReviewedDate2);

						String PayStubReviewedDate3 = PayStubReviewedDate0[1];

						String PayStubReviewedDate4 = PayStubReviewedDate0[2];

						int yyyy= Integer.parseInt(PayStubReviewedDate4);

						int DD= day+1;

						String month="0"+String.valueOf(DD);

						String days=Monthlydate1;

						String year=null;

						if(day==12)

						{

							yyyy=yyyy+1;

							year=String.valueOf(yyyy);

						}

						else

						{

							year=PayStubReviewedDate4;

						}

						NextPayday = month+"/"+days+"/"+year;

					}

					if(Income_PayFrequency.equals("Semi-Monthly"))

					{

						driver.switchTo().defaultContent();

						driver.switchTo().frame("bottom");

						String BusinessDt=
								driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

						String Busdate[]=BusinessDt.split(":");

						String date = Busdate[1];

						DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

						Date d1 = df.parse(date);

						Calendar cal = Calendar.getInstance();

						cal.setTime(d1);

						cal.add(Calendar.DATE, 0);

						Date PayStubReviewedDate1= cal.getTime();

						String PayStubReviewedDate
						=df.format(PayStubReviewedDate1);

						//Date D=Add(date1,7);

						//System.out.println(date);

						//System.out.println(PayStubReviewedDate);

						String PayStubReviewedDate0[]
								=PayStubReviewedDate.split("/");

						String PayStubReviewedDate2 = PayStubReviewedDate0[0];

						int day= Integer.parseInt(PayStubReviewedDate2);

						String PayStubReviewedDate3 = PayStubReviewedDate0[1];

						String PayStubReviewedDate4 = PayStubReviewedDate0[2];

						int yyyy= Integer.parseInt(PayStubReviewedDate4);

						int DD= day+1;

						String month="0"+String.valueOf(DD);

						String days="01";

						String year=null;

						if(day==12)

						{

							yyyy=yyyy+1;

							year=String.valueOf(yyyy);

						}

						else

						{

							year=PayStubReviewedDate4;

						}

						NextPayday = month+"/"+days+"/"+year;

					}

					if(Income_PayFrequency.equals("Weekly"))

					{

						driver.switchTo().defaultContent();

						driver.switchTo().frame("bottom");

						String BusinessDt=
								driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

						String Busdate[]=BusinessDt.split(":");

						String date = Busdate[1];

						DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

						Date d1 = df.parse(date);

						Calendar cal = Calendar.getInstance();

						cal.setTime(d1);

						if(SemiMonOthFirstDay.equals("Monday"))

						{

							cal.add(Calendar.DATE, 1);

							Date PayStubReviewedDate1= cal.getTime();

							NextPayday =df.format(PayStubReviewedDate1);

						}

						if(SemiMonOthFirstDay.equals("Tuesday"))

						{

							cal.add(Calendar.DATE, 2);

							Date PayStubReviewedDate1= cal.getTime();

							NextPayday =df.format(PayStubReviewedDate1);

						}

						if(SemiMonOthFirstDay.equals("Wednesday"))

						{

							cal.add(Calendar.DATE, 3);

							Date PayStubReviewedDate1= cal.getTime();

							NextPayday =df.format(PayStubReviewedDate1);

						}

						if(SemiMonOthFirstDay.equals("Thursday"))

						{

							cal.add(Calendar.DATE, 4);

							Date PayStubReviewedDate1= cal.getTime();

							NextPayday =df.format(PayStubReviewedDate1);

						}

						if(SemiMonOthFirstDay.equals("Friday"))

						{

							cal.add(Calendar.DATE, 5);

							Date PayStubReviewedDate1= cal.getTime();

							NextPayday =df.format(PayStubReviewedDate1);

						}

						if(SemiMonOthFirstDay.equals("Saturday"))

						{

							cal.add(Calendar.DATE, 6);

							Date PayStubReviewedDate1= cal.getTime();

							NextPayday =df.format(PayStubReviewedDate1);

						}

						if(SemiMonOthFirstDay.equals("Sunday"))

						{

							cal.add(Calendar.DATE, 7);

							Date PayStubReviewedDate1= cal.getTime();

							NextPayday =df.format(PayStubReviewedDate1);

						}

						//Date D=Add(date1,7);

						//System.out.println(date);

						//System.out.println(PayStubReviewedDate);

					}

					test.log(LogStatus.PASS,"Next Paydate."+NextPayday);

					/* driver.switchTo().defaultContent();

		                         driver.switchTo().frame("topFrame");

		    driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();*/

					driver.quit(); //need to change to c

					driver = new InternetExplorerDriver();

					driver.get(AdminURL);

					test.log(LogStatus.INFO, "Admin portal is launched");

					driver.manage().window().maximize();

					//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);



					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

					test.log(LogStatus.PASS, "Username is entered: admin");

					driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

					test.log(LogStatus.PASS, "Password is entered:"+Password);

					//Click Login Button

					driver.findElement(By.name("login")).click();

					test.log(LogStatus.PASS, "Clicked on Submit button");

					Thread.sleep(8000);

					Thread.sleep(8000);

					/* DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

		                         Date d1 = df.parse(date);

		                         Calendar cal = Calendar.getInstance();

		                         cal.setTime(d1);

		                         cal.add(Calendar.DATE, -10);*/

					Date DDueDate = df.parse(NextPayday);

					Calendar cal = Calendar.getInstance();

					cal.setTime(DDueDate);

					cal.add(Calendar.DATE, Days);

					Date DDueDateminus1= cal.getTime();

					String DueDateminus1 =df.format(DDueDateminus1);

					String NextPayday0[] =DueDateminus1.split("/");

					String NextPayday1 = NextPayday0[0];

					String NextPayday2 = NextPayday0[1];

					String NextPayday3 = NextPayday0[2];

					//WebDriverWait wait = new WebDriverWait(driver,10000);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					//
					//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(120,
							TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60,
							TimeUnit.SECONDS);

					driver.findElement(By.linkText("QA Jobs")).click();

					test.log(LogStatus.PASS, "Clicked on QA Jobs");

					Thread.sleep(3000);

					driver.findElement(By.linkText("Process Date Change")).click();

					test.log(LogStatus.PASS, "Clicked on Process DateChange");

					driver.manage().timeouts().implicitlyWait(120,
							TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("storeCode")).click();

					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

					driver.findElement(By.name("storeCode")).sendKeys(StoreID);

					test.log(LogStatus.PASS, "Store number is entered:"+StoreID);

					Thread.sleep(5000);

					driver.findElement(By.name("beginMonth")).clear();

					driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1);

					test.log(LogStatus.PASS, "beginMonth is entered:"+NextPayday1);

					driver.findElement(By.name("beginDay")).clear();

					driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);

					test.log(LogStatus.PASS, "beginDay is entered:"+NextPayday2);

					driver.findElement(By.name("beginYear")).clear();

					driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);

					test.log(LogStatus.PASS, "beginYear is entered:"+NextPayday3);

					Thread.sleep(2000);

					driver.manage().timeouts().implicitlyWait(120,
							TimeUnit.SECONDS);

					Thread.sleep(1000);

					Thread.sleep(5000);

					driver.findElement(By.name("btnPreview")).click();

					test.log(LogStatus.PASS, "Clicked on submit button");

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					if(
							driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())

					{

						test.log(LogStatus.PASS, "Process Date updatedsuccessfully");

					}

					else

					{

						test.log(LogStatus.FAIL, "Process Date updatedsuccessfully.");

					}


					driver.switchTo().defaultContent();

					driver.switchTo().frame("topFrame");

					//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

					driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

					test.log(LogStatus.PASS, "Clicked on Transactions");

					driver.manage().timeouts().implicitlyWait(120,
							TimeUnit.SECONDS);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.manage().timeouts().implicitlyWait(60,
							TimeUnit.SECONDS);

					driver.findElement(By.linkText("ACH")).click();

					test.log(LogStatus.PASS, "Clicked on ACH");


					Thread.sleep(5000);

					driver.findElement(By.linkText("LOC")).click();

					test.log(LogStatus.PASS, "Clicked on LOC");

					//driver.switchTo().defaultContent();

					//driver.switchTo().frame("mainFrame");

					Thread.sleep(5000);

					driver.findElement(By.linkText("Default Loc")).click();

					test.log(LogStatus.PASS, "Clicked on Default Loc");



					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					//Thread.sleep(6000);

					/* WebElement element =
		    driver.findElement(By.name("cancel"));

		                         Actions action = new Actions(driver);

		    action.moveToElement(element).build().perform();*/

					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();

					Thread.sleep(6000);

					WebElement element =
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));

					Actions action = new Actions(driver);

					action.moveToElement(element).build().perform();

					driver.manage().timeouts().implicitlyWait(120,
							TimeUnit.SECONDS);

					try {

						Alert alert = driver.switchTo().alert();

						alert.dismiss();

						//if alert present, accept and move on.

					}

					catch (NoAlertPresentException e) {

						//do what you normally would if you didn't have thealert.

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);

					test.log(LogStatus.PASS, "StoreID is entered:"+StoreID);

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.name("beginMonth")).clear();

					driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1);

					test.log(LogStatus.PASS, "beginMonth is entered:"+NextPayday1);

					driver.findElement(By.name("beginDay")).clear();

					driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);

					test.log(LogStatus.PASS, "beginDay is entered:"+NextPayday2);

					driver.findElement(By.name("beginYear")).clear();

					driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);

					test.log(LogStatus.PASS, "beginYear is entered:"+NextPayday3);



					// driver.findElement(By.linkText("iPads")).click();

					driver.findElement(By.name("submit")).click();

					test.log(LogStatus.PASS, "Clicked on submit button");

					Thread.sleep(6000);

				}

				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();




				/*

		                     ///////////////////
		                     for( String winHandle1 : driver.getWindowHandles())

		                     {
		                         if(!(winHandle1.equals(Parent_Window)))

		                         {
		                             driver.switchTo().window(winHandle1);
		                             Thread.sleep(6000);
		                             System.out.println(driver.getTitle());
		                             int ee =222;
		                             List<WebElement> rows
		    =driver.findElements(By.tagName("tr"));
		                             int ScdCnt = rows.size();
		                             test.log(LogStatus.PASS, "Rows count is"+ScdCnt);


		                             for(int j=2;j<=ScdCnt-1;j++)
		                             {
		                                 //String
		    transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+j+"]")).getText();
		                                 int k =j+1;
		                             //    String
		    transactino_value1=driver.findElement(By.xpath("//select[@name='transactionList']/option["+k+"]")).getText();

		                                 dt =
		    driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+j+"]/td[3]")).getText();
		                                 dt1 =
		    driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+k+"]/td[3]")).getText();

		                                 test.log(LogStatus.PASS, "date is"+dt);
		                                 System.out.println(dt);
		                                 test.log(LogStatus.PASS, "date is"+dt1);
		                                 System.out.println(dt1);
		                                 String DDe1[]=dt.split(" ");

		                                 String DDe2[]=dt1.split(" ");
		                             //    DateFormat df=new
		    SimpleDateFormat("yyyy-mm-dd");
		                                 String DueDate1 =DDe1[0];
		                                 String DueDate2 = DDe2[0];
		                                 SimpleDateFormat sdf = new
		    SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		                                 Date firstDate = sdf.parse(DueDate1);
		                                 Date secondDate = sdf.parse(DueDate2);
		    ///////////////
		                                 int DAY = (1000 * 60 * 60 * 24);
		                                 long utc1 =
		    Date.UTC(firstDate.getFullYear(), firstDate.getMonth(),
		    firstDate.getDate());
		                                   long utc2 =
		    Date.UTC(secondDate.getFullYear(),secondDate.getMonth(),
		    secondDate.getDate());

		                                   return Math.floor((utc2 - utc1) / DAY);
		                                 //long diff1 = (long)
		    Math.floor(diffInMillies / (1000 * 3600 * 24));

		                                     int diffDays =
		    secondDate.diff(firstDate, days);
		                                 //long diffInMillies = secondDate.getDate()
		    - firstDate.getDate();

		                                 long diffInMillies
		    =Math.negateExact(secondDate.getTime()-firstDate.getTime());
		                                 double diff1 = Math.ceil(diffInMillies /
		    (1000 * 3600 * 24));
		                                 ///////////////



		                                 //long diffInMillies =Math.abs
		    (secondDate.getTime()-firstDate.getTime());
		                                 //long diff1 =
		    TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
		                                 test.log(LogStatus.PASS, "Difference in
		    Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
		                                 if (PayFrequency.equals("Bi-Weekly"))
		                                 {

		                                     test.log(LogStatus.PASS, "Date
		    Difference for Bi-WeeklyShould be 14 day");

		                                     test.log(LogStatus.PASS, "Difference in
		    Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
		                                     //Long i = Long.parseLong(String)
		                                     String a = Double.toString(diff1);
		                                     //String a = Long.toString(diff1);
		                                     if (a.equals("14"))
		                                     {
		                                         test.log(LogStatus.PASS, "Date
		    Difference for Bi-Weekly is as Expected");
		                                     }
		                                     else
		                                     {
		                                         test.log(LogStatus.PASS, "Date
		    Difference for Bi-Weekly is not as Expected");
		                                     }



		                                 }
		                                 if (PayFrequency.equals("Monthly"))
		                                 {
		                                     test.log(LogStatus.PASS, "Date
		    Difference for Monthly Should be 30 day");
		                                     test.log(LogStatus.PASS, "Difference in
		    Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
		                                     String a = Double.toString(diff1);
		                                     //String a = Long.toString(diff1);
		                                     if (a.equals("30"))
		                                     {
		                                         test.log(LogStatus.PASS, "Date
		    Difference for Monthly is as Expected");
		                                     }
		                                     else
		                                     {
		                                         test.log(LogStatus.PASS, "Date
		    Difference for Monthly is not as Expected");
		                                     }

		                                 }
		                                 if (PayFrequency.equals("Semi-Monthly"))
		                                 {

		                                     test.log(LogStatus.PASS, "Date
		    Difference for Semi-Monthly Should be 15 days");
		                                     test.log(LogStatus.PASS, "Difference in
		    Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
		                                     //String a =  Long.toString(long)
		                                     if (diff1<=15)
		                                     {
		                                         test.log(LogStatus.PASS, "Date
		    Difference for Semi-Monthly is as Expected");
		                                     }
		                                     else
		                                     {
		                                         test.log(LogStatus.PASS, "Date
		    Difference for Semi-Monthly is not as Expected");
		                                     }
		                                 }
		                                 if (PayFrequency.equals("Weekly"))
		                                 {

		                                     test.log(LogStatus.PASS, "Date
		    Difference for Weekly Should be 14 days");
		                                     test.log(LogStatus.PASS, "Difference in
		    Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
		                                     //String a =  Long.toString(long)
		                                     if (diff1<=14)
		                                     {
		                                         test.log(LogStatus.PASS, "Date
		    Difference for Weekly is as Expected");
		                                     }
		                                     else
		                                     {
		                                         test.log(LogStatus.PASS, "Date
		    Difference for Weekly is not as Expected");
		                                     }
		                                 }
		                             }

		                         }

				 */



			}

		}

	}

	public void WO_Recovery_pymt_BeforeDueDate_4thInst(String SSN,String FileName,int Days, int i) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";
		String dt = null;
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
				String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
				String Parent_Window = driver.getWindowHandle();
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
				driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
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
					driver.findElement(By.name("button")).click();
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				///html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{
					test.log(LogStatus.PASS, "RCC Schedule Generated");

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();


					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							System.out.println(driver.getTitle());
							String PaymentAmount = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[2]")).getText();
							//    /html/body/form/table/tbody/tr[2]/td[2]
							test.log(LogStatus.PASS, "Payment Amount"+PaymentAmount);
							System.out.println(PaymentAmount);
							dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
							test.log(LogStatus.PASS, "Date" +dt);
							System.out.println(PaymentAmount);

							driver.quit();

							driver = new InternetExplorerDriver();
							driver.get(AdminURL);
							test.log(LogStatus.PASS, "date in outside loop"+dt);




							String DDe[] =dt.split(" ");

							DateFormat  df=new SimpleDateFormat("yyyy-MM-dd");


							String DueDate = DDe[0];

							String DDueDate[] =DueDate.split("-");


							Date DDueDateminus1 = df.parse(DueDate);

							Calendar cal = Calendar.getInstance();

							cal.setTime(DDueDateminus1);

							cal.add(Calendar.DATE, Days);

							Date DDueDate1= cal.getTime();

							DueDate =df.format(DDueDate1);

							String DueDate0[] =DueDate.split("-");

							String DueDate3 = DueDate0[0];
							String DueDate1 = DueDate0[1];

							String DueDate2 = DueDate0[2];


							//driver.close();
							appUrl = AppURL;
							this.Login(UserName,Password,StoreID);
							//String SSN1 = SSN.substring(0, 3);
							//String SSN2 = SSN.substring(3,5);
							//String SSN3 = SSN.substring(5,9);
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

								//driver.findElement(By.name("button")).click();
								///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
								driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
							}
							//  driver.findElement(By.name("button")).click();
							test.log(LogStatus.PASS, "Click on GO Button");
							for( String winHandle2 : driver.getWindowHandles())
							{
								driver.switchTo().window(winHandle2);
							}			
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
							driver.findElement(By.name("transactionList")).sendKeys("Write Off Recovery");
							if(ProductID.equals("LOC"))
							{
								driver.findElement(By.name("button")).click(); 
							}

							for( String winHandle2 : driver.getWindowHandles())
							{
								driver.switchTo().window(winHandle2);
							}			
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");

							//String PaymentAmount=null;

							//PaymentAmount = driver.findElement(By.name("requestBean.paymentAmt")).getAttribute("value");
							//test.log(LogStatus.PASS, "Capture the Payment Amt":+PaymentAmount);
							Thread.sleep(2000);
							driver.findElement(By.name("requestBean.paymentAmt")).clear();
							driver.findElement(By.name("requestBean.paymentAmt")).sendKeys(PaymentAmount);

							driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
							test.log(LogStatus.PASS, "Select the Tender Type");

							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(PaymentAmount);
							test.log(LogStatus.PASS, "Enter the Tender Amount");

							driver.findElement(By.name("password")).sendKeys(Password);
							test.log(LogStatus.PASS, "Enter the Password");

							driver.findElement(By.name("Submit22")).click();
							test.log(LogStatus.PASS, "Click on Finish Payment Button");

							test.log(LogStatus.INFO, "WORecovery_Payment with-SSN: " +SSN +" :: is ::"+"Successful");

							driver.close();
							driver = new InternetExplorerDriver();


							/*driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
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
			                           driver.findElement(By.linkText("QA Jobs")).click();
			                           test.log(LogStatus.PASS, "Clicked on QA Jobs");
			                           Thread.sleep(5000);
			   driver.findElement(By.linkText("Process Date Change")).click();
			                           test.log(LogStatus.PASS, "Clicked on Process Date Change");
			   driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			   driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			                           driver.switchTo().defaultContent();
			                           driver.switchTo().frame("mainFrame");
			                           WebElement elements1 =
			   driver.findElement(By.linkText("QA Jobs"));
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
			                           if(
			   driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
			                           {
			                               test.log(LogStatus.PASS, "Process Date updated successfully");
			   driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
			                           }
			                           else
			                           {
			                               test.log(LogStatus.FAIL, "Process Date not updated successfully.");
			                           }*/

						}

					}

				}



			}
		}
	}
	public void RCCStatus(String SSN,String FileName) throws Exception
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
				//test.log(LogStatus.INFO, "RCCSchduleInEligibleStatus_ActiveMilitary");

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

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
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
				String RCCStatus=null;
				String LoanStatus = null;
				String RCCIneligibleReason = null;
				/*	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed();

			   			test.log(LogStatus.PASS," RCC schdule is Displayed");*/
				//CheckStaus=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
				RCCStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[2]/span[2]")).getText();
				LoanStatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]")).getText();
				RCCIneligibleReason = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();

				//*[@id="revolvingCreditHistTable"]/tbody/tr[14]/td[2]/span[2]
				//*[@id="revolvingCreditHistTable"]/tbody/tr[12]/td[2]/span[2]
				//*[@id="revolvingCreditHistTable"]/tbody/tr[13]/td[2]/span[2]
				test.log(LogStatus.PASS," RCC Status ::"+RCCStatus);
				test.log(LogStatus.PASS," Loan Status ::"+LoanStatus);
				test.log(LogStatus.PASS," Loan Status ::"+RCCIneligibleReason);
				// //*[@id="revolvingCreditHistTable"]/tbody/tr[14]/td[2]/span[2]
				//	System.out.print(CheckStaus);	
				//driver.close();

			}
		}
	}

	public void PaymentPlan(String SSN,String FileName) throws Exception
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
				driver.findElement(By.name("transactionList")).sendKeys("Payment Plan");
				if(ProductID.equals("LOC"))
				{

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.id("go_Button")).click();  
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

				driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody/tr[6]/td[2]/input[1]")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("collateralTypeId")).sendKeys("CASH");
				test.log(LogStatus.PASS, "Collateral Type As cash ");																	
				driver.findElement(By.name("password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter Password ");
				driver.findElement(By.xpath("//*[@id='submitBtn']")).click();
				test.log(LogStatus.PASS, "Clicked Finished Payment Plan ");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("OKBut")).click();
				test.log(LogStatus.PASS, "Clicked YES Button ");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("checkno")).click();
				test.log(LogStatus.PASS, "Clicked NO Button ");

				test.log(LogStatus.INFO, "Payment Plan completed Sucessfully");



			}

		}

	}

	public void CustomerDefault_WO(String SSN,String FileName,int Days) throws Exception
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
				test.log(LogStatus.INFO, "CustomerDefault with-SSN: " +SSN +" :: is ::"+"Starts");

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


					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();

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

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture Cure End Dtae"+DueDate);
				System.out.print(DueDate);			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.quit();	//need to change to close
				//System.out.print(DueDate);	
				// driver.close();

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
				Date DDueDate = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDate);
				cal.add(Calendar.DATE, Days);
				Date DDueDateminus1= cal.getTime();
				String DueDateminus1 =df.format(DDueDateminus1);
				String DueDate0[] =DueDateminus1.split("/");
				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];
				WebDriverWait wait = new WebDriverWait(driver, 10000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
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
				}
				else
				{
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}



				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
				driver.findElement(By.linkText("Default Loc")).click();
				test.log(LogStatus.PASS, "Clicked on Default Loc");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//Thread.sleep(6000);
				/* WebElement element = driver.findElement(By.name("cancel"));
		    							        Actions action = new Actions(driver);								        
		    							        action.moveToElement(element).build().perform();*/

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
				Thread.sleep(6000);
				WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
				Actions action = new Actions(driver);								        
				action.moveToElement(element).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				try { 
					Alert alert = driver.switchTo().alert();
					alert.dismiss();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);


				// driver.findElement(By.linkText("iPads")).click();
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				test.log(LogStatus.INFO, "CustomerDefault with-SSN: " +SSN +" :: is ::"+"Completed");
				Thread.sleep(6000);


			}
		}
	}

	public void Default_WOProc_60days(String SSN,String FileName) throws Exception
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
					//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]
					//driver.findElement(By.name("button")).click();
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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

				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/*	DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();*/
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr[12]/td[4]")).getText();

				test.log(LogStatus.PASS, "Capture PWO  Dtae"+DueDate);
				System.out.print(DueDate);			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.close();	
				//System.out.print(DueDate);	
				// driver.close();

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
				/*	Date DDueDate = df.parse(DueDate);
						Calendar cal = Calendar.getInstance();
						cal.setTime(DDueDate);
						cal.add(Calendar.DATE, 0);
						cal.add(Calendar.DATE, 90);
						Date DDueDateminus1= cal.getTime();

						 String DueDateminus1 =df.format(DDueDateminus1);
						String DueDate0[] =DueDate.split("/");
						String DueDate1 = DueDate0[0];
						String DueDate2 = DueDate0[1];
						String DueDate3 = DueDate0[2];*/
				String DDueDate[] =DueDate.split(" ");


				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, 60);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				WebDriverWait wait = new WebDriverWait(driver, 10000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));
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
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
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
				driver.findElement(By.linkText("Writeoff Loc")).click();
				test.log(LogStatus.PASS, "Clicked on Writeoff Loc");


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(6000);
				/* WebElement element = driver.findElement(By.name("cancel"));
										        Actions action = new Actions(driver);								        
										        action.moveToElement(element).build().perform();*/

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
				//Thread.sleep(6000);
				//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);


				//try { 
				//Alert alert = driver.switchTo().alert();
				//alert.dismiss();
				//if alert present, accept and move on.														

				//}
				//catch (NoAlertPresentException e) {
				//do what you normally would if you didn't have the alert.
				//}


				Thread.sleep(6000);
				WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
				Actions action = new Actions(driver);								        
				action.moveToElement(element).build().perform();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);


				// driver.findElement(By.linkText("iPads")).click();
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				Thread.sleep(6000);


			}
		}
	}

	public void EditBorrower(String SSN,String FileName) throws Exception

	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);

		int lastrow=TestData.getLastRow("NewLoan");

		System.out.println("NewLoan"+lastrow);

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

				String MonthlyPayDay=TestData.getCellData(sheetName,"MonthlyPayDay",row);

				String Income_PayFrequency=TestData.getCellData(sheetName,"Income_PayFrequency",row);

				String SemiMonOthFirstDay=TestData.getCellData(sheetName,"SemiMonOthFirstDay",row);

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

				String Monthlydate=null;

				String Monthlydate1=null;

				if(MonthlyPayDay.length()==3)

				{

					Monthlydate = MonthlyPayDay.substring(0, 1);

					Monthlydate1="0"+Monthlydate;

				}

				if(MonthlyPayDay.length()==4)

				{

					Monthlydate1 = MonthlyPayDay.substring(0, 2);

				}

				System.out.println(Monthlydate1);

				WebDriverWait wait = new WebDriverWait(driver, 1000);

				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();

				test.log(LogStatus.PASS, "Clicked on Borrower");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='902000']")));

				driver.findElement(By.cssSelector("li[id='902000']")).click();

				//driver.findElement(By.cssSelector("//*[@id='902000']/a")).click();

				test.log(LogStatus.PASS, "Clicked on Edit");

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

				String NextPayday =null;

				if(Income_PayFrequency.equals("Bi-Weekly"))

				{

					// //*[@id="biWk_second"] //*[@id="biWk_second"]/text()
					NextPayday = driver.findElement(By.xpath("//*[@id='biWeekly']/td/table/tbody/tr[2]/td[2]/input")).getAttribute("value");

					String PayStubReviewedDate0[] =NextPayday.split("/");

					String PayStubReviewedDate2 = PayStubReviewedDate0[0];

					String month=null;

					if(PayStubReviewedDate2.length()==1)

					{

						month = "0"+PayStubReviewedDate0[0];

					}

					else

					{

						month = PayStubReviewedDate0[0];

					}

					// int day= Integer.parseInt(PayStubReviewedDate2);

					String Day = PayStubReviewedDate0[1];

					if(Day.length()==1)

					{

						Day = "0"+PayStubReviewedDate0[1];

					}

					else

					{

						Day = PayStubReviewedDate0[1];

					}

					String Year = PayStubReviewedDate0[2];

					NextPayday = month+"/"+Day+"/"+Year;

				}

				if(Income_PayFrequency.equals("Monthly"))

				{

					driver.switchTo().defaultContent();

					driver.switchTo().frame("bottom");

					String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

					String Busdate[]=BusinessDt.split(":");

					String date = Busdate[1];

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					Date d1 = df.parse(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(d1);

					cal.add(Calendar.DATE, -10);

					Date PayStubReviewedDate1= cal.getTime();

					String PayStubReviewedDate =df.format(PayStubReviewedDate1);

					//Date D=Add(date1,7);

					//System.out.println(date);

					//System.out.println(PayStubReviewedDate);

					String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");

					String PayStubReviewedDate2 = PayStubReviewedDate0[0];

					int day= Integer.parseInt(PayStubReviewedDate2);

					String PayStubReviewedDate3 = PayStubReviewedDate0[1];

					String PayStubReviewedDate4 = PayStubReviewedDate0[2];

					int yyyy= Integer.parseInt(PayStubReviewedDate4);

					int DD= day+1;

					String month="0"+String.valueOf(DD);

					String days=Monthlydate1;

					String year=null;

					if(day==12)

					{

						yyyy=yyyy+1;

						year=String.valueOf(yyyy);

					}

					else

					{

						year=PayStubReviewedDate4;

					}

					NextPayday = month+"/"+days+"/"+year;

				}

				if(Income_PayFrequency.equals("Semi-Monthly"))

				{

					driver.switchTo().defaultContent();

					driver.switchTo().frame("bottom");

					String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

					String Busdate[]=BusinessDt.split(":");

					String date = Busdate[1];

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					Date d1 = df.parse(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(d1);

					cal.add(Calendar.DATE, 0);

					Date PayStubReviewedDate1= cal.getTime();

					String PayStubReviewedDate =df.format(PayStubReviewedDate1);

					//Date D=Add(date1,7);

					//System.out.println(date);

					//System.out.println(PayStubReviewedDate);

					String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");

					String PayStubReviewedDate2 = PayStubReviewedDate0[0];

					int day= Integer.parseInt(PayStubReviewedDate2);

					String PayStubReviewedDate3 = PayStubReviewedDate0[1];

					String PayStubReviewedDate4 = PayStubReviewedDate0[2];

					int yyyy= Integer.parseInt(PayStubReviewedDate4);

					int DD= day+1;

					String month="0"+String.valueOf(DD);

					String days="01";

					String year=null;

					if(day==12)

					{

						yyyy=yyyy+1;

						year=String.valueOf(yyyy);

					}

					else

					{

						year=PayStubReviewedDate4;

					}

					NextPayday = month+"/"+days+"/"+year;

				}

				if(Income_PayFrequency.equals("Weekly"))

				{

					driver.switchTo().defaultContent();

					driver.switchTo().frame("bottom");

					String BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();

					String Busdate[]=BusinessDt.split(":");

					String date = Busdate[1];

					DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

					Date d1 = df.parse(date);

					Calendar cal = Calendar.getInstance();

					cal.setTime(d1);

					if(SemiMonOthFirstDay.equals("Monday"))

					{

						cal.add(Calendar.DATE, 1);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Tuesday"))

					{

						cal.add(Calendar.DATE, 2);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Wednesday"))

					{

						cal.add(Calendar.DATE, 3);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Thursday"))

					{

						cal.add(Calendar.DATE, 4);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Friday"))

					{

						cal.add(Calendar.DATE, 5);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Saturday"))

					{

						cal.add(Calendar.DATE, 6);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					if(SemiMonOthFirstDay.equals("Sunday"))

					{

						cal.add(Calendar.DATE, 7);

						Date PayStubReviewedDate1= cal.getTime();

						NextPayday =df.format(PayStubReviewedDate1);

					}

					//Date D=Add(date1,7);

					//System.out.println(date);

					//System.out.println(PayStubReviewedDate);

				}

				test.log(LogStatus.PASS,"Next Paydate."+NextPayday);

				/* driver.switchTo().defaultContent();

		driver.switchTo().frame("topFrame");

		driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();*/

				driver.quit(); //need to change to c

				driver = new InternetExplorerDriver();

				driver.get(AdminURL);

				test.log(LogStatus.INFO, "Admin portal is launched");

				driver.manage().window().maximize();

				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);



				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				Thread.sleep(8000);

				Thread.sleep(8000);

				/* DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

		Date d1 = df.parse(date);

		Calendar cal = Calendar.getInstance();

		cal.setTime(d1);

		cal.add(Calendar.DATE, -10);*/

				Date DDueDate = df.parse(NextPayday);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDate);

				cal.add(Calendar.DATE, -10);

				Date DDueDateminus1= cal.getTime();

				String DueDateminus1 =df.format(DDueDateminus1);

				String NextPayday0[] =DueDateminus1.split("/");

				String NextPayday1 = NextPayday0[0];

				String NextPayday2 = NextPayday0[1];

				String NextPayday3 = NextPayday0[2];

				//WebDriverWait wait = new WebDriverWait(driver, 10000);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				driver.findElement(By.linkText("QA Jobs")).click();

				test.log(LogStatus.PASS, "Clicked on QA Jobs");

				Thread.sleep(5000);

				driver.findElement(By.linkText("Process Date Change")).click();

				test.log(LogStatus.PASS, "Clicked on Process Date Change");

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();

				driver.findElement(By.name("storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);

				Thread.sleep(5000);

				driver.findElement(By.name("beginMonth")).clear();

				driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1);

				test.log(LogStatus.PASS, "beginMonth is entered: "+NextPayday1);

				driver.findElement(By.name("beginDay")).clear();

				driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);

				test.log(LogStatus.PASS, "beginDay is entered: "+NextPayday2);

				driver.findElement(By.name("beginYear")).clear();

				driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);

				test.log(LogStatus.PASS, "beginYear is entered: "+NextPayday3);

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

				}

				else

				{

					test.log(LogStatus.FAIL, "Process Date updated successfully.");

				}


				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

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

				driver.findElement(By.linkText("Default Loc")).click();

				test.log(LogStatus.PASS, "Clicked on Default Loc");



				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				//Thread.sleep(6000);

				/* WebElement element = driver.findElement(By.name("cancel"));

		Actions action = new Actions(driver);

		action.moveToElement(element).build().perform();*/

				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();

				Thread.sleep(6000);

				WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));

				Actions action = new Actions(driver);

				action.moveToElement(element).build().perform();

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				try {

					Alert alert = driver.switchTo().alert();

					alert.dismiss();

					//if alert present, accept and move on.

				}

				catch (NoAlertPresentException e) {

					//do what you normally would if you didn't have the alert.

				}

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");

				driver.findElement(By.name("beginMonth")).clear();

				driver.findElement(By.name("beginMonth")).sendKeys(NextPayday1);

				test.log(LogStatus.PASS, "beginMonth is entered: "+NextPayday1);

				driver.findElement(By.name("beginDay")).clear();

				driver.findElement(By.name("beginDay")).sendKeys(NextPayday2);

				test.log(LogStatus.PASS, "beginDay is entered: "+NextPayday2);

				driver.findElement(By.name("beginYear")).clear();

				driver.findElement(By.name("beginYear")).sendKeys(NextPayday3);

				test.log(LogStatus.PASS, "beginYear is entered: "+NextPayday3);



				// driver.findElement(By.linkText("iPads")).click();

				driver.findElement(By.name("submit")).click();

				test.log(LogStatus.PASS, "Clicked on submit button");

				Thread.sleep(6000);



			}

		}

	}


	public void RCC_Schedule_1stInst_Agestore(String SSN,String FileName,int Days,int i) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		String dt = null;
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

				String Parent_Window = driver.getWindowHandle();  
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "RCC_Schedule_1stInst_Agestore");

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
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//	/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{
					test.log(LogStatus.PASS, "RCC Schedule Generated");

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();


					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							System.out.println(driver.getTitle());
							dt =	driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);


							driver.quit();

							driver = new InternetExplorerDriver();
							driver.get(AdminURL);
							test.log(LogStatus.PASS, "date in outside loop"+dt);




							String DDe[] =dt.split(" ");

							DateFormat  df=new SimpleDateFormat("yyyy-mm-dd");	


							String DueDate = DDe[0];

							String DDueDate[] =DueDate.split("-");


							Date DDueDateminus1 = df.parse(DueDate);

							Calendar cal = Calendar.getInstance();

							cal.setTime(DDueDateminus1);

							cal.add(Calendar.DATE, Days);

							Date DDueDate1= cal.getTime();

							DueDate =df.format(DDueDate1);

							String DueDate0[] =DueDate.split("-");

							String DueDate3 = DueDate0[0];
							String DueDate1 = DueDate0[1];

							String DueDate2 = DueDate0[2];


							//driver.close();



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
							driver.findElement(By.linkText("QA Jobs")).click();
							test.log(LogStatus.PASS, "Clicked on QA Jobs");
							Thread.sleep(5000);
							driver.findElement(By.linkText("Process Date Change")).click();
							test.log(LogStatus.PASS, "Clicked on Process Date Change");
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
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



			}
		}
	}



	public void RCC_Schedule_1stInst_Pmt(String SSN,String FileName) throws Exception
	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);	
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		String dt = null;
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
				String ESign_TenderType = TestData.getCellData(sheetName,"TenderType",row);
				String Parent_Window = driver.getWindowHandle();  
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
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//	/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{
					test.log(LogStatus.PASS, "RCC Schedule Generated");

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();


					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							System.out.println(driver.getTitle());
							dt =	driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]")).getText();
							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);
							////
							driver.quit();

							driver = new InternetExplorerDriver();
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
							for( String winHandle2 : driver.getWindowHandles())
							{
								driver.switchTo().window(winHandle2);
							}			
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");

							driver.findElement(By.name("transactionList")).sendKeys("Default Payment");
							if(ProductID.equals("LOC"))
							{
								driver.findElement(By.name("button")).click(); 
							}

							for( String winHandle2 : driver.getWindowHandles())
							{
								driver.switchTo().window(winHandle2);
							}			
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");

							////
							driver.findElement(By.name("requestBean.paymentAmt")).clear();
							driver.findElement(By.name("requestBean.paymentAmt")).sendKeys(dt);
							driver.findElement(By.name("requestBean.tenderType")).sendKeys(ESign_TenderType);
							test.log(LogStatus.PASS, "Select the Tender Type");

							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(dt);
							test.log(LogStatus.PASS, "Enter the Tender Amount");

							driver.findElement(By.name("password")).sendKeys(Password);
							test.log(LogStatus.PASS, "Enter the Password");

							driver.findElement(By.name("Submit22")).click();
							test.log(LogStatus.PASS, "Click on Finish Payment Button");

							test.log(LogStatus.INFO, "Default_PartialPayment with-SSN: " +SSN +" :: is ::"+"Successful");
						}





					}

				}


			}
		}
	}


	public void NACHA_LOC_RCC(String SSN,String FileName,int Days,int i) throws Exception
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
				String dt;
				String Parent_Window = driver.getWindowHandle();  
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "NACHA_RCC");

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
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//	/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{
					test.log(LogStatus.PASS, "RCC Schedule Generated");
					Thread.sleep(3000);

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();


					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							System.out.println(driver.getTitle());
							dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);


							driver.quit();

							driver = new InternetExplorerDriver();
							driver.get(AdminURL);
							test.log(LogStatus.PASS, "date in outside loop"+dt);




							String DDe[] =dt.split(" ");

							DateFormat  df=new SimpleDateFormat("yyyy-mm-dd");	


							String DueDate = DDe[0];

							String DDueDate[] =DueDate.split("-");


							Date DDueDateminus1 = df.parse(DueDate);

							Calendar cal = Calendar.getInstance();

							cal.setTime(DDueDateminus1);

							cal.add(Calendar.DATE, Days);

							Date DDueDate1= cal.getTime();

							DueDate =df.format(DDueDate1);

							String DueDate0[] =DueDate.split("-");

							String DueDate3 = DueDate0[0];
							String DueDate1 = DueDate0[1];

							String DueDate2 = DueDate0[2];


							//driver.close();



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


							driver.findElement(By.linkText("ACH")).click();
							test.log(LogStatus.PASS, "Clicked on ACH");
							Thread.sleep(5000);
							driver.findElement(By.linkText("Green Bank")).click();
							test.log(LogStatus.PASS, "Clicked on Green Bank");
							Thread.sleep(5000);
							driver.findElement(By.linkText("Green Bank NACHA File")).click();
							test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
							driver.findElement(By.linkText("QA Jobs")).click();
							test.log(LogStatus.PASS, "Clicked on Daily Jobs");
							Thread.sleep(5000);




							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");

							/*	driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
				Thread.sleep(5000);*/
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
								test.log(LogStatus.PASS, "Process NACHA file successfully.");
								driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "Process NACHA is not updated successfully.");
							}




						}
					}
				}
			}
		}
	}



	public void AfterDFLT_RCCOrigination(String SSN,String FileName,int Days,int i) throws Exception
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
				String dt;
				String Parent_Window = driver.getWindowHandle();  
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "AfterDFLT_RCCOrigination");

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
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//	/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{

					Thread.sleep(3000);

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();

					test.log(LogStatus.PASS, "Clicked on RCC Schedule button");
					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							System.out.println(driver.getTitle());
							dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);


							driver.quit();

							driver = new InternetExplorerDriver();
							driver.get(AdminURL);
							test.log(LogStatus.PASS, "date in outside loop"+dt);




							String DDe[] =dt.split(" ");

							DateFormat  df=new SimpleDateFormat("yyyy-mm-dd");	


							String DueDate = DDe[0];

							String DDueDate[] =DueDate.split("-");


							Date DDueDateminus1 = df.parse(DueDate);

							Calendar cal = Calendar.getInstance();

							cal.setTime(DDueDateminus1);

							cal.add(Calendar.DATE, Days);

							Date DDueDate1= cal.getTime();

							DueDate =df.format(DDueDate1);

							String DueDate0[] =DueDate.split("-");

							String DueDate3 = DueDate0[0];
							String DueDate1 = DueDate0[1];

							String DueDate2 = DueDate0[2];


							//driver.close();



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


							driver.findElement(By.linkText("ACH")).click();
							test.log(LogStatus.PASS, "Clicked on ACH");
							Thread.sleep(5000);
							driver.findElement(By.linkText("RCC Payments")).click();
							test.log(LogStatus.PASS, "Clicked on RCC Payments");
							Thread.sleep(5000);
							driver.findElement(By.linkText("RCC Payments After DEF Origination File")).click();
							test.log(LogStatus.PASS, "Clicked on RCC Payments After DEF Origination File");
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
							driver.findElement(By.linkText("QA Jobs")).click();
							test.log(LogStatus.PASS, "Clicked on QA Jobs");
							Thread.sleep(5000);




							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");

							/*	driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
				Thread.sleep(5000);*/
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
								test.log(LogStatus.PASS, "Process AfterDFLT_RCCOrigination successfully.");
								driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "ProcessAfterDFLT_RCCOrigination is not updated successfully.");
							}




						}
					}
				}
			}
		}
	}


	public void NACHA_LOC_NoRecords(String SSN,String FileName,int Days) throws Exception
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
				String dt;
				String Parent_Window = driver.getWindowHandle();  
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
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//	/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{
					//test.log(LogStatus.PASS, "RCC Schedule Generated");

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();


					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							System.out.println(driver.getTitle());
							dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[3]")).getText();
							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);


							driver.quit();

							driver = new InternetExplorerDriver();
							driver.get(AdminURL);
							test.log(LogStatus.PASS, "date in outside loop"+dt);




							String DDe[] =dt.split(" ");

							DateFormat  df=new SimpleDateFormat("yyyy-mm-dd");	


							String DueDate = DDe[0];

							String DDueDate[] =DueDate.split("-");


							Date DDueDateminus1 = df.parse(DueDate);

							Calendar cal = Calendar.getInstance();

							cal.setTime(DDueDateminus1);

							cal.add(Calendar.DATE, -1);

							Date DDueDate1= cal.getTime();

							DueDate =df.format(DDueDate1);

							String DueDate0[] =DueDate.split("-");

							String DueDate3 = DueDate0[0];
							String DueDate1 = DueDate0[1];

							String DueDate2 = DueDate0[2];


							//driver.close();



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


							driver.findElement(By.linkText("ACH")).click();
							test.log(LogStatus.PASS, "Clicked on ACH");
							Thread.sleep(5000);
							driver.findElement(By.linkText("Green Bank")).click();
							test.log(LogStatus.PASS, "Clicked on Green Bank");
							Thread.sleep(5000);
							driver.findElement(By.linkText("Green Bank NACHA File")).click();
							test.log(LogStatus.PASS, "Clicked on Green Bank NACHA File");
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
							driver.findElement(By.linkText("Daily Jobs")).click();
							test.log(LogStatus.PASS, "Clicked on Daily Jobs");
							Thread.sleep(5000);




							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");

							/*	driver.findElement(By.name("storeCode")).click();
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
				Thread.sleep(5000);*/
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
								test.log(LogStatus.PASS, "Process NACHA file successfully.");
								driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "Process NACHA is not updated successfully.");
							}




						}
					}
				}
			}
		}
	}

	public void ACH_Deposit(String SSN,String FileName,int Days) throws Exception
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				appUrl = AppURL;


				CSRLoginpage login = new CSRLoginpage();

				login.Login(UserName, Password, StoreID, driver, AppURL, test);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);	

				appUrl = AppURL;


				Thread.sleep(5000);
				Thread.sleep(1000);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
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
				driver.findElement(By.linkText("ACH")).click();
				test.log(LogStatus.PASS, "Clicked on ACH");
				Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");
				Thread.sleep(5000);
				driver.findElement(By.linkText("LOC Pre ACH Deposit")).click();
				test.log(LogStatus.PASS, "Clicked on LOC Pre ACH Deposit");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
				Thread.sleep(5000);

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
					test.log(LogStatus.PASS, "LOC Pre ACH Deposit Process  successfully.");
					driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Process LOC Pre ACH Deposit is not updated successfully.");
				}




			}
		}
	}

	public void LOC_PreRCC_Deposit(String SSN,String FileName,int Days,int i) throws Exception
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
				String dt;
				String Parent_Window = driver.getWindowHandle();  
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "LOC_PreRCC_Deposit");

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
					driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//	/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{

					Thread.sleep(3000);

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();
					test.log(LogStatus.PASS, "Clicked On RCC Schedule Button");

					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							System.out.println(driver.getTitle());
							dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);


							driver.quit();

							driver = new InternetExplorerDriver();
							driver.get(AdminURL);
							test.log(LogStatus.PASS, "date in outside loop"+dt);




							String DDe[] =dt.split(" ");

							DateFormat  df=new SimpleDateFormat("yyyy-mm-dd");	


							String DueDate = DDe[0];

							String DDueDate[] =DueDate.split("-");


							Date DDueDateminus1 = df.parse(DueDate);

							Calendar cal = Calendar.getInstance();

							cal.setTime(DDueDateminus1);

							cal.add(Calendar.DATE, Days);

							Date DDueDate1= cal.getTime();

							DueDate =df.format(DDueDate1);

							String DueDate0[] =DueDate.split("-");

							String DueDate3 = DueDate0[0];
							String DueDate1 = DueDate0[1];

							String DueDate2 = DueDate0[2];


							//driver.close();



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


							driver.findElement(By.linkText("ACH")).click();
							test.log(LogStatus.PASS, "Clicked on ACH");
							Thread.sleep(5000);
							driver.findElement(By.linkText("RCC Payments")).click();
							test.log(LogStatus.PASS, "Clicked on RCC Payments");
							Thread.sleep(5000);
							driver.findElement(By.linkText("Process LOC Pre RCC Deposits")).click();
							test.log(LogStatus.PASS, "Clicked on RCC Payments After DEF Origination File");
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
							driver.findElement(By.linkText("QA Jobs")).click();
							test.log(LogStatus.PASS, "Clicked on QA Jobs");
							Thread.sleep(5000);


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
								test.log(LogStatus.PASS, "LOC Pre ACH Deposit Process  successfully.");
								driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "Process LOC Pre ACH Deposit is not updated successfully.");
							}




						}
					}
				}
			}
		}

	}

	public void Admin_ACH_Clear(String SSN,String FileName,int Days,int i) throws Exception
	{

		Excel TestData = new
				Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
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
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				appUrl = AppURL;
				String dt ;
				String Parent_Window = driver.getWindowHandle(); 

				CSRLoginpage login = new CSRLoginpage();

				login.Login(UserName, Password, StoreID, driver, AppURL, test);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Admin_ACH_Clear");

				System.out.println(ProductID);	

				appUrl = AppURL;


				Thread.sleep(5000);
				Thread.sleep(1000);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
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
					driver.findElement(By.name("button")).click();
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				//
				// /html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{

					Thread.sleep(3000);

					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();
					test.log(LogStatus.PASS, "Clicked on RCC Schedule Button");

					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(6000);
							System.out.println(driver.getTitle());
							dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);


							driver.quit();

							driver = new InternetExplorerDriver();
							driver.get(AdminURL);
							test.log(LogStatus.PASS, "date in outside loop"+dt);




							String DDe[] =dt.split(" ");

							DateFormat  df=new SimpleDateFormat("yyyy-mm-dd");


							String DueDate = DDe[0];

							String DDueDate[] =DueDate.split("-");


							Date DDueDateminus1 = df.parse(DueDate);

							Calendar cal = Calendar.getInstance();

							cal.setTime(DDueDateminus1);

							cal.add(Calendar.DATE, Days);

							Date DDueDate1= cal.getTime();

							DueDate =df.format(DDueDate1);

							String DueDate0[] =DueDate.split("-");

							String DueDate3 = DueDate0[0];
							String DueDate1 = DueDate0[1];

							String DueDate2 = DueDate0[2];


							//driver.close();



							driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
							test.log(LogStatus.PASS, "Username is entered::admin");
							driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
							test.log(LogStatus.PASS, "Password is entered:"+Password);
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


							driver.findElement(By.linkText("ACH")).click();
							test.log(LogStatus.PASS, "Clicked on ACH");
							Thread.sleep(5000);
							driver.findElement(By.linkText("LOC")).click();
							test.log(LogStatus.PASS, "Clicked on RCC Payments");
							Thread.sleep(5000);
							driver.findElement(By.linkText("ACH Clear")).click();
							test.log(LogStatus.PASS, "Clicked on ACH Clear");
							driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);

							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
							driver.findElement(By.linkText("QA Jobs")).click();
							test.log(LogStatus.PASS, "Clicked on QA Jobs");
							Thread.sleep(5000);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");

							driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
							driver.findElement(By.name("beginMonth")).click();
							driver.findElement(By.name("beginMonth")).clear();
							driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
							test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
							driver.findElement(By.name("beginDay")).clear();
							driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
							test.log(LogStatus.PASS, "beginDay is entered:"+DueDate2);
							driver.findElement(By.name("beginYear")).clear();
							driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
							test.log(LogStatus.PASS, "beginYear is entered:"+DueDate3);
							Thread.sleep(2000);
							driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
							Thread.sleep(1000);
							Thread.sleep(5000);
							driver.findElement(By.name("submit")).click();
							test.log(LogStatus.PASS, "Clicked on submit button");
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
							test.log(LogStatus.PASS, "ACH Clear Process updated successfully.");
							/*	if(
											driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
									{
										test.log(LogStatus.PASS, "ACH Clear Process updated successfully.");
										driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
									}
									else
									{
										test.log(LogStatus.FAIL, "ACH Clear Process not updated successfully.");
									}*/


						}
					}
				}
			}
		}
	}

	public void RCCSchduleStatus_ActiveMilitary(String SSN,String FileName) throws Exception
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
				test.log(LogStatus.INFO, "RCCSchduleInEligibleStatus_ActiveMilitary");

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

					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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
				/*	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed();

						test.log(LogStatus.PASS," RCC schdule is Displayed");*/
				CheckStaus=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
				test.log(LogStatus.PASS," RCC inEligible Reason :::"+CheckStaus);
				// //*[@id="revolvingCreditHistTable"]/tbody/tr[14]/td[2]/span[2]
				//	System.out.print(CheckStaus);	
				//driver.close();

			}
		}
	}

	public void Payments_Lessthan_Min_Payment_Amount(String SSN,String FileName) throws Exception{


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
				Thread.sleep(1000);
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

					// String Pmt= driver.findElement(By.name("currentBalance")).getAttribute("value");
					// name="requestBean.paymentAmt"
					driver.findElement(By.name("requestBean.paymentAmt")).clear();
					driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("8");
					driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("8");
					test.log(LogStatus.PASS, "Tender Amt is entered as :: 8");							
					driver.findElement(By.name("password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);			
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Payment button ");

					Thread.sleep(2000);

					/* driver.findElement(By.id("btnADV_Yes")).click();
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
												}*/
					/*		

										 try { 
											    Alert alert = driver.switchTo().alert();
											    alert.accept();
											    //if alert present, accept and move on.														

											}
											catch (NoAlertPresentException e) {



											    //do what you normally would if you didn't have the alert.
											}
					 */
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					Thread.sleep(2000);
					Thread.sleep(2000);
					/*	 if(driver.findElement(By.name("Ok")).isDisplayed())
												{*/
					test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
					/* driver.findElement(By.name("Ok")).click();*/
					/*	}
											 else
												{
													test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
												}*/


				}

			}

		}
	}

	public void VoidDrawLoan (String SSN,String FileName) throws Exception{


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
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
					System.out.println(Pmt);
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as "+Pmt);							
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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

					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
					}


				}

			}

		}
	}

	public void Payments_Partial(String SSN,String FileName) throws Exception{


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
				Thread.sleep(1000);
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

					// String Pmt= driver.findElement(By.name("currentBalance")).getAttribute("value");
					// String Pmt= driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]")).getText();
					// /html/body/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]
					// name="requestBean.paymentAmt"
					driver.findElement(By.name("requestBean.paymentAmt")).clear();
					driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("30");
					driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
					driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("30");
					test.log(LogStatus.PASS, "Tender Amt is entered as ::  Pmt");							
					driver.findElement(By.name("password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as "+Password);			
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Payment button ");

					Thread.sleep(2000);

					/* driver.findElement(By.id("btnADV_Yes")).click();
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
												}*/
					/*		

										 try { 
											    Alert alert = driver.switchTo().alert();
											    alert.accept();
											    //if alert present, accept and move on.														

											}
											catch (NoAlertPresentException e) {



											    //do what you normally would if you didn't have the alert.
											}
					 */
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					Thread.sleep(2000);
					Thread.sleep(2000);
					/*	 if(driver.findElement(By.name("Ok")).isDisplayed())
												{*/
					test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
					/* driver.findElement(By.name("Ok")).click();*/
					/*	}
											 else
												{
													test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
												}*/


				}

			}

		}
	}

	public void PayOff_Void(String SSN,String FileName) throws Exception{


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
				driver.switchTo().defaultContent();				
				driver.switchTo().frame("topFrame");
				Thread.sleep(1000);
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
					driver.findElement(By.xpath("//input[@type='button' and @value='Go']")).click();
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
					driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys("Cash");
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Password is selected as "+Password);																					
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
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
					if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					}
					else
					{
						test.log(LogStatus.FAIL, "Void Loan is not Completed Successfully ");
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
						test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
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
						test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
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
	public void NewLoan_Draw(String SSN,String FileName) throws Exception{


		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		//System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";		
		for(int row=2;row<=lastrow;row++)
		{	
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{		
				String State = TestData.getCellData(sheetName,"StateID",row);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				//System.out.println(ProductID);
				//String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType= TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				//System.out.println(ESign_CollateralType);
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
				String Parent_Window = driver.getWindowHandle();
				//System.out.println(last4cheknum);
				//System.out.println(stateProductType);

				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				//	Selection of Product based on the Name provided in Test Data


				//if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				if(driver.findElement(By.id("ShareScreenBtn")).isEnabled())
				{

					if(ProductID.equals("TLP"))							
					{					
						//System.out.println("IN TLP");
						driver.findElement(By.xpath("//*[@id='vehicleType_dd']")).sendKeys(VehicleType);
						driver.findElement(By.xpath("//*[@id='vinDD']")).sendKeys("New");
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[1]/td[2]/input")).sendKeys(NewVIN);	
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[1]/tbody/tr[2]/td[2]/input")).sendKeys(NewVIN);
						driver.findElement(By.xpath("//*[@id='vinPop']/div/table[3]/tbody/tr/td/input[2]")).click();
						driver.findElement(By.xpath("//*[@id='td.miles_tf']/input")).sendKeys("200");
						driver.findElement(By.xpath("//*[@id='bbHit_Button']")).click();				
					}												
					if(ProductName.equals("TNPAYDAY"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Tennessee"))
					{
						driver.findElement(By.xpath("//*[@id='termSel1']")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Line of Credit"))
					{
						if(StoreID.equals("4326"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						}
						else
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
						}
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}

					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button is clicked");

					Set<String> handles = driver.getWindowHandles();
					Iterator<String> i1=handles.iterator();

					while(i1.hasNext())			
					{		
						String ChildWindow=i1.next();		

						if(!Parent_Window.equalsIgnoreCase(ChildWindow))			
						{    

							driver.switchTo().window(ChildWindow);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}
					}

					driver.switchTo().window(Parent_Window);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}                    

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");
					driver.findElement(By.id("LoanButtonId")).click();
					//driver.findElement(By.id("LoanButtonId")).click();

					test.log(LogStatus.PASS, "Clicked on New Loan button");
					//New Loan Screens
					if(ProductID.equals("PDL"))
					{	

						driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						if(!(ESign_LoanAmt.isEmpty()))
						{
							driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[13]/td[3]/input")).sendKeys(ESign_LoanAmt);
							test.log(LogStatus.PASS, "Loan amount is enterted as "+ESign_LoanAmt);
						}
						driver.findElement(By.xpath("//*[@id='chkgAcctNbr']")).sendKeys(last4cheknum);
						test.log(LogStatus.PASS, "	Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);					
						driver.findElement(By.xpath("//*[@id='advanceRequestBean.disbursementType']")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						Thread.sleep(5000);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						//System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						Thread.sleep(5000);
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Electronic Communication Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.xpath("//*[@id='allowCoupons']/td[3]/input")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							driver.findElement(By.xpath("//*[@id='coupon']/td[3]/div[1]/input")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
						}
						driver.findElement(By.xpath("//*[@id='idNoChecks']/td[3]/select")).sendKeys(ESign_Checks);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Checks);
						WebDriverWait wait = new WebDriverWait(driver, 1000);	
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='chkNbr0']")));
						driver.findElement(By.xpath("//*[@id='chkNbr0']")).sendKeys(ESign_CheckNbr);
						test.log(LogStatus.PASS, "Check number is "+ESign_CheckNbr);
						driver.findElement(By.name("advanceRequestBean.loggedUserPassword")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishadvance")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
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
						//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						// driver.findElement(By.name("OKBut")).click();					
						driver.findElement(By.xpath("//*[@id='OKBut']")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("bdyLoad");
						if(driver.findElement(By.name("Ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("Ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("ILP"))
					{	
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("advanceRequestBean.advanceAmt")).getAttribute("value");
						//System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							//String mwh=driver.getWindowHandle();
							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
							//String winHandle = driver.getWindowHandle(); //Get current window handle.									
						}
						WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
						String NumofInst=ele.getAttribute("value");
						//*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/table[1]/tbody/tr[5]/td[2]/input
						//System.out.println(NumofInst);
						int installments = Integer.parseInt(NumofInst);
						for(int i=0;i<installments;i++)
						{
							Random rand = new Random();
							int rand1 = rand.nextInt(100000);	
							String chknum = Integer.toString(rand1);
							driver.findElement(By.id("checkNbrs"+i)).sendKeys(chknum);

						}			 					 			
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as "+ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						//for( String winHandle1 : driver.getWindowHandles())
						//{
						// driver.switchTo().window(winHandle1);
						//}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						driver.findElement(By.name("OKBut")).click();
						//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();				
						test.log(LogStatus.PASS, "click on Yes button ");
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}			
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}

					if(ProductID.equals("TLP"))
					{	
						String TitleNumber= TestData.getCellData(sheetName,"TitleNumber",row);
						String AppraisalValue= TestData.getCellData(sheetName,"Appraisal Value",row);
						String ExteriorColor=TestData.getCellData(sheetName,"ExteriorColor",row);
						String LicensePlateNumber=TestData.getCellData(sheetName,"License Plate Number",row);
						//String VehicleGrade=TestData.getCellData(sheetName,"Vehicle Grade",row);
						String LicensePlateExp=TestData.getCellData(sheetName,"License Plate Expiry",row);
						String InsuranceCoverage=TestData.getCellData(sheetName,"Insurance Coverage",row);
						String PhoneNbr=TestData.getCellData(sheetName,"Phone Nbr",row);
						String PhoneNbr1 = PhoneNbr.substring(0, 3);
						String PhoneNbr2 = PhoneNbr.substring(3, 6);
						String PhoneNbr3 = PhoneNbr.substring(6, 10);
						String InsuranceCompany =TestData.getCellData(sheetName,"Insurance Company",row);
						String InsuranceExpiryDate=TestData.getCellData(sheetName,"Insurance Expiry Date",row);
						String PolicyNumber=TestData.getCellData(sheetName,"Policy Number",row);
						String InsuranceExpiryDate0[] =InsuranceExpiryDate.split("/");
						String InsuranceExpiryDate1 = InsuranceExpiryDate0[0];
						String InsuranceExpiryDate2 = InsuranceExpiryDate0[1];
						String InsuranceExpiryDate3 = InsuranceExpiryDate0[2];
						driver.findElement(By.name("requestBean.titleNumber")).sendKeys(TitleNumber);
						driver.findElement(By.xpath("//*[@id='appraisal']")).sendKeys(AppraisalValue);
						//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						driver.findElement(By.name("button1")).click();
						test.log(LogStatus.PASS, "click on Update 1 button ");
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("requestBean.extClr")));

						driver.findElement(By.name("requestBean.extClr")).sendKeys(ExteriorColor);
						driver.findElement(By.name("requestBean.licensePltNbr")).sendKeys(LicensePlateNumber);
						driver.findElement(By.name("requestBean.licensePltExpire")).sendKeys(LicensePlateExp);
						driver.findElement(By.name("requestBean.paintCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.bodyCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.glassCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.tiresCondition")).sendKeys("Clean");
						driver.findElement(By.name("requestBean.coverageType")).sendKeys(InsuranceCoverage);
						driver.findElement(By.name("iPhoneNbr1")).sendKeys(PhoneNbr1);
						driver.findElement(By.name("iPhoneNbr2")).sendKeys(PhoneNbr2);
						driver.findElement(By.name("iPhoneNbr3")).sendKeys(PhoneNbr3);
						driver.findElement(By.name("requestBean.companyName")).sendKeys(InsuranceCompany);
						driver.findElement(By.name("iexpiry1")).sendKeys(InsuranceExpiryDate1);
						driver.findElement(By.name("iexpiry2")).sendKeys(InsuranceExpiryDate2);
						driver.findElement(By.name("iexpiry3")).sendKeys(InsuranceExpiryDate3);
						driver.findElement(By.name("requestBean.polocyNbr")).sendKeys(PolicyNumber);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("button2")));
						driver.findElement(By.name("button2")).click();			
						driver.findElement(By.name("button2")).click();	

						test.log(LogStatus.PASS, "click on Update 2 button ");
						Thread.sleep(8000);

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
						driver.findElement(By.name("process")).click();

						test.log(LogStatus.PASS, "click on process Loan button ");
						try { 
							Alert alert = driver.switchTo().alert();
							alert.accept();
							//if alert present, accept and move on.														

						}
						catch (NoAlertPresentException e) {
							//do what you normally would if you didn't have the alert.
						}
						Thread.sleep(5000);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("collateralType")));
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);									
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
						String Instamt=driver.findElement(By.name("cashToCust")).getAttribute("value");
						//System.out.println(Instamt);
						driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						driver.findElement(By.name("vehicleKey")).sendKeys("Yes");					
						driver.findElement(By.name("requestBean.siilBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("requestBean.siilBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						if(ESign_CourtesyCallConsent.equals("Yes"))
						{
							if(ESign_Preference.equals("Call"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("Mail"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);
							}
							if(ESign_Preference.equals("SMS"))	
							{
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as "+ESign_Preference);

								try { 
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									//if alert present, accept and move on.														

								}
								catch (NoAlertPresentException e) {
									//do what you normally would if you didn't have the alert.
								}
							}

						}
						if(AllowPromotion.equals("Yes"))
						{
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");

							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);

						}
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						test.log(LogStatus.PASS, "Click on Finish Loan Button");
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
						if(driver.findElement(By.name("ok")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}
					if(ProductID.equals("LOC"))
					{

						test.log(LogStatus.INFO, "Navigated to New Loan Screen");
						driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
						Thread.sleep(5000);
						//driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						//driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[1]/input")).click();
							test.log(LogStatus.INFO, "Navigated to Draw Screen");
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
							driver.findElement(By.name("loanAmt")).sendKeys("50");	
							driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
							test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
							driver.findElement(By.name("disbAmtFirst")).sendKeys("50");					
							test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
							driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
							test.log(LogStatus.PASS, "Password is entered as "+ESign_Password);
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
								test.log(LogStatus.PASS, "Draw New Loan is Completed Successfully ");
							}
							else
							{
								test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
							}
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


	public void PendingBNK(String SSN,String FileName) throws Exception
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

				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys(BNKstatus);
				test.log(LogStatus.PASS, "select status as :" + BNKstatus);

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
				test.log(LogStatus.PASS, "<FONT color=green style=Arial> Status BNKPending is Saved");


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


	public void DrawStatust(String SSN,String FileName) throws Exception{


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
				driver.findElement(By.name("transactionList")).sendKeys("Draw");				
				test.log(LogStatus.PASS, "<FONT color=green style=Arial> ****************Draw Trasaction is not available*************");


				/* if(ProductID.equals("LOC"))
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
												 test.log(LogStatus.PASS, "Partial Payment Completed Successfully ");
													driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
												}
											 else
												{
													test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");
												}


									 }
				 */
			}

		}
	}



	public void DrawStatus_PNBK(String SSN,String FileName) throws Exception{


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
				driver.findElement(By.name("transactionList")).sendKeys("Draw");
				test.log(LogStatus.PASS, "Draw Trasaction is available");

				/* if(ProductID.equals("LOC"))
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
												 test.log(LogStatus.PASS, "Partial Payment Completed Successfully ");
													driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
												}
											 else
												{
													test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");
												}


									 }
				 */
			}

		}
	}
	public void PendingBNK_Void(String SSN,String FileName) throws Exception
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
				String BNKstatus1=TestData.getCellData(sheetName,"BNKstatus1",row);
				String AttorneyPhone = TestData.getCellData(sheetName,"AttorneyPhone",row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
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

				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys(BNKstatus1);
				test.log(LogStatus.PASS, "select status as :" + BNKstatus1);

				driver.findElement(By.name("bt_AddBankruptcy")).click();
				test.log(LogStatus.PASS, "Status BNKPending is Saved");


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

	public void StatementGeneration_EODProcessing_NODep(String SSN,String FileName) throws Exception{


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
				Thread.sleep(4000);
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
				test.log(LogStatus.PASS, "LOC ACH Deposit Not Available");
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

	public void Check_RPP(String SSN,String FileName) throws Exception

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
				Thread.sleep(1000);

				//if(ProductID.equals("LOC"))
				//{
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//}
				//  driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(1000);
				int n=driver.findElements(By.xpath("//select[@name='transactionList']/option")).size();

				for(int i=1;i<=n;i++)
				{
					String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+i+"]")).getText();


					if(transactino_value.equalsIgnoreCase("Payment Plan"))
					{
						test.log(LogStatus.PASS, "Payment Plan option is available in the list");
					}
					else
					{
						test.log(LogStatus.PASS, "Payment Plan option is not available in the list");
					}
				}
			}}
	}




	public boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException ex){
			return false;
		}
	}

	@Test (priority=0)

	public void Loan_draw_statment_draw_DLQ_statment() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_statment_Deliquent_statement_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest("Scenario-NO_1"+Header, "LOCI Cash =>Draw =>Stmnt =>Draw 2 => Draw 3=> EOD on Due Date =>DLQ =>Stmnt2");
				appUrl = AppURL;
				//this.StatementGeneration(SSN, FileName);
				//this.AgeStore(SSN,FileName);         
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoanDraw(SSN,FileName); 
				this.StatementGeneration(SSN, FileName); 
				this.DrawLoan(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName,0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);    
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.StatementGeneration_2(SSN, FileName);


			}
		}


	}

	@Test (priority=1)

	public void Loan_statement_Draw_DLQ_Cure_MinPayment_Draw_statment() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_statment_Draw_Deliquent_Cure_MinPayment_Draw_statement_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:2"+"_"+PayFrequency+"_"+CollateralType,"LOCI Cash => Stmnt1 =>  Draw 1  on due date => DLQ =>Cure =>Payment of Min Pmnt Amnt => Draw => Stmnt2");
				// test = reports.startTest("Scenario-NO_1"+Header, "LOCI Cash =>Draw =>Stmnt =>Draw 2 => Draw 3=> EOD on Due Date =>DLQ =>Stmnt2");
				appUrl = AppURL;
				//this.StatementGeneration(SSN, FileName);
				//this.AgeStore(SSN,FileName);         
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoanDraw(SSN, FileName); 
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName,0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);    
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName); 
				this.AgeStore(SSN, FileName,10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);    
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.PayOffLoan(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				//this.StatementGeneration_2(SSN, FileName);


			}
		}


	}

	@Test (priority=2)

	public void Loan_Draw_statement_VoidDraw_EOD__Draw_statment() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_statment_VoidDraw_EOD_Draw_statement_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:3"+"_"+PayFrequency+"_"+CollateralType,"LOCI Cash => Draw On stmnt date => Void Draw => `RUN EOD No deposits should be posted => Draw =>Stmnt2");
				// test = reports.startTest("Scenario-NO_1"+Header, "LOCI Cash =>Draw =>Stmnt =>Draw 2 => Draw 3=> EOD on Due Date =>DLQ =>Stmnt2");
				appUrl = AppURL;
				//this.StatementGeneration(SSN, FileName);
				//this.AgeStore(SSN,FileName);         
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN, FileName);
				this.Agestore_StatementGeneration(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.Void(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);    
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.Agestore_StatementGeneration(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);


			}
		}


	}





	@Test (priority=3)

	public void Loan_Draw1_Draw2ACH_statement_Rescind_Draw_EOD_DLQ() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_Draw_statment_Rescind_Draw_EOD_DLQ_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:4"+"_"+PayFrequency+"_"+CollateralType,"LOCI Cash => Draw 1 => Draw 2 ACH => Stmnt => Rescind => Draw => EOD On Due Date => DLQ ");
				// test = reports.startTest("Scenario-NO_1"+Header, "LOCI Cash =>Draw =>Stmnt =>Draw 2 => Draw 3=> EOD on Due Date =>DLQ =>Stmnt2");
				appUrl = AppURL;     
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoanDraw(SSN, FileName);
				this.Agestore_StatementGeneration(SSN, FileName);
				this.DrawLoanwithACH(SSN, FileName); 
				this.StatementGeneration_2(SSN, FileName);
				this.Rescind(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);    
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);  
				this.DeliquentPaymentStatus(SSN, FileName); 



			}
		}


	}

	@Test (priority=4)

	public void Loan_Draw_statement_Draw_Void_Rescind() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_statment_Draw_Void_Rescind_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:5"+"_"+PayFrequency+"_"+CollateralType,"LOC I Cash =>Draw => Stmnt => Draw 2 =>Void Draw =>Rescind Draw 1");
				appUrl = AppURL;     
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN, FileName);
				this.Agestore_StatementGeneration(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName); 
				this.DrawLoan(SSN, FileName); 
				this.Void(SSN, FileName);  
				this.Rescind(SSN, FileName);


			}
		}


	}

	@Test (priority=5)

	public void Loan_Draw_PartialPMT_MinPayment_EOD() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_statment_PartialPayment_MinPayment_EOD_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:6"+"_"+PayFrequency+"_"+CollateralType,"LOCI Cash => Draw => Stmnt => Pmnt Partial => Pmnt Min Payment => EOD on Due Date  ");
				appUrl = AppURL;     
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName); 
				this.PartialPayment(SSN, FileName);
				this.Payments(SSN, FileName);   
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);


			}
		}


	}

	@Test (priority=6)

	public void Loan_Draw_statement_Partialpayment_EOD_DLQ_Cure_Default() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_statment_PartialPayment_EOD_DLQ_Cure_Default_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:7"+"_"+PayFrequency+"_"+CollateralType,"LOCI Cash => Draw => Stmnt => Pmnt Partial => EOD on Due Date  =>DLQ =>Cure =>Def");
				appUrl = AppURL;     
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName); 
				this.PartialPayment(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				// this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);



			}
		}


	}
	@Test (priority=7)

	public void Loan_Draw_statement_PartialPayment_EOD_DLQ_MinPayment_LoanCurrent_Draw() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_statment_PartialPayment_EOD_DLQ_MinPayment_Loancurrent_Draw_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:8"+"_"+PayFrequency+"_"+CollateralType,"LOCI Cash => Draw => Stmnt => Pmnt Partial => EOD on Due Date  =>DLQ =>Paymnt MinPayment Amount =>Loan in CURRENT => Draw ");
				appUrl = AppURL;     
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName); 
				this.PartialPayment(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.Payments(SSN, FileName);
				this.PaymentStatus(SSN, FileName);
				this.DrawLoan(SSN, FileName);




			}
		}


	}

	@Test (priority=8)

	public void Loan_Draw_statement_Draw_MinPayment_EOD_PartialPayment_statement_Draw() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_statment_Draw_MinPayment_EOD_PartialPayment_statement_Draw_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:9"+"_"+PayFrequency+"_"+CollateralType,"LOCI Cash => Draw => Stmnt => Draw => Pmnt Min Pmnt Amnt =>EOD on Due Date => Pmnt Partial =>Stmnt =>Draw");
				appUrl = AppURL;     
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName); 
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0); 
				this.MinPayment(SSN, FileName);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.Agestore_StatementGeneration(SSN, FileName);
				this.PartialPayment(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.DrawLoan(SSN, FileName);




			}
		}


	}

	@Test (priority=9)

	public void RegistrationTest() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_statment_MinPayment_Void_DLQ_Cure_Default_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:10"+"_"+PayFrequency+"_"+CollateralType,"LOCI Cash =>Draw =>Stmnt => Pmnt Min Payment =>On Due Date Void Pmnt => DLQ =>Cure=>DEF");
				appUrl = AppURL;     
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName); 
				this.Payments(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.EncryptionKey_Void(SSN, FileName);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);



			}
		}


	}
	@Test (priority=10)

	public void LOCICash_Draw_Draw2_MakePmt_Stmnt_PmtRTN_EODduedate_Dlq() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCICash_Draw_Draw2_MakePmt_Stmnt_PmtRTN_EODduedate_DLQ_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:15_"+PayFrequency+"_"+CollateralType, "LOC I Cash _Draw _ Draw 2 _ Make Pmnt _Stmnt _ Payment Return _ EOD on Due Date _DLQ");
				Assert.assertTrue(true);
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN,FileName);
				this.DrawLoan(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.AgeStore_LoanDate(SSN, FileName, 5);
				this.Payments(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.Void_Payment(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);

			}
		}			
	}

	@Test (priority=11)

	public void LOCICash_Draw_Draw2_Stmnt_Payoff_EODDueDate_() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCICash_Draw_Draw2_Stmnt_Payoff_EODDueDate_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:16_"+PayFrequency+"_"+CollateralType, "LOC I Cash _Draw _ Draw 2 _Stmnt _ Payoff_EOD On Due Date");
				Assert.assertTrue(true);
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN,FileName);
				this.DrawLoan(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.PayOffLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);


			}
		}			
	}

	@Test (priority=12)

	public void LOCICash_Draw_Stmnt_Payoff_PayoffVoidDueDate_DLQ_Cure_Payoff_Closure_() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCICash_Draw_Stmnt_Payoff_PayoffVoidDueDate_DLQ_Cure_Payoff_Closure_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:17_"+PayFrequency+"_"+CollateralType, "LOC I Cash_ Draw _Stmnt _Payoff _ On Due date Void Payoff _ DLQ _ Cure _ Payoff _ Line Closure");
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
				this.AgeStore(SSN, FileName, 0);
				this.EncryptionKey_Void(SSN, FileName);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName,10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);	
				this.PayOffLoan(SSN, FileName);
				this.Closure(SSN, FileName);



			}
		}			
	}

	@Test (priority=13)

	public void LOCICash_Draw_Stmnt_PartialPmt_PayoffDueDate_Payoffvoid_DLQ_() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCICash_Draw_Stmnt_PartialPmt_PayoffDueDate_Payoffvoid_DLQ_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:18_"+PayFrequency+"_"+CollateralType, "LOCI Cash _ Draw _Stmnt _ Pmnt Partial _ On Due Date Payoff _ Void Payoff _DLQ");
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
				this.LoanPartialPayment(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.PayOffLoan(SSN, FileName);
				this.Void_Payoff(SSN, FileName);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);




			}
		}			
	}


	@Test (priority=14)

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
	@Test (priority=15)

	public void LOCICash_Draw_Stmnt_EODDueDate_DLQ_CURE_Payoff_Draw_Stmnt_Pmnt_EOD_DLQ_() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCICash_Draw_Stmnt_EODDueDate_DLQ_CURE_Payoff_Draw_Stmnt_Pmnt_EOD_DLQ_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:22_"+PayFrequency+"_"+CollateralType, "LOC I Cash _ Draw _ Stmnt _ RUN EOD on Due Date _DLQ _ CURE _Payoff _ Draw _Stmnt _ Make Pmnt _ RUN EOD_DLQ");
				Assert.assertTrue(true);
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN,FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.PayOffLoan(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.Payments(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus1(SSN, FileName);



			}
		}			
	}

	@Test (priority=16)

	public void LOCICash_Draw_Stmnt_EOD_DLQ_CURE_DEF_DFPPartial_DFPFull_() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCICash_Draw_Stmnt_EOD_DLQ_CURE_DEF_DFPPartial_DFPFull_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:25_"+PayFrequency+"_"+CollateralType, "LOCI Cash _Draw _Stmnt _ EOD On Due Date _DLQ _ CURE _DEF _DFP Partial _ DFP Full");
				Assert.assertTrue(true);
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN,FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);
				this.Default_PartialPayment(SSN, FileName);
				this.Default_Payment(SSN, FileName);
				this.DefaultPaymentStatus1(SSN, FileName);




			}
		}			
	}

	@Test (priority=17)

	public void LOCICash_Draw_Stmnt_EOD_DLQ_CURE_DEF_PartialDFP_DFPFull_VoidDFP_() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCICash_Draw_Stmnt_EOD_DLQ_CURE_DEF_PartialDFP_DFPFull_VoidDFP_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:26_"+PayFrequency+"_"+CollateralType, "LOCI Cash _Draw _Stmnt _ EOD On Due Date _DLQ _ CURE _DEF _DFP Partial _ DFP Full _Void DFP");
				Assert.assertTrue(true);
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN,FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);
				this.Default_PartialPayment(SSN, FileName);
				this.Default_Payment(SSN, FileName);
				this.DefaultPayment_Void(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName); 


			}
		}			
	}

	@Test (priority=18)

	public void LOCI_ACTM_Drawavailable_stmt() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_ACTM_Drawshouldnotavailable_Statement_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
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
				test = reports.startTest(Header+"_S.No:60"+"_"+PayFrequency+"_"+CollateralType,"LOC Initiation - active Military - Draw should be available - Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);		       
				this.Agestore_Loandate(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.Check_Draw(SSN, FileName);
				this.StatementGeneration(SSN, FileName);

			}
		}
	}

	@Test (priority=19)

	public void LOCI_Draw_ACTMSamedrawday_stmt() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTMSamedrawday_stmt_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
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
				test = reports.startTest(Header+"_S.No:61"+"_"+PayFrequency+"_"+CollateralType,"LOC Initiation - Draw - active Military(same day) =>Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);
				this.Agestore_Loandate(SSN, FileName, 3);
				this.DrawLoan(SSN, FileName);		        
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);

			}
		}
	}

	@Test (priority=20)

	public void LOCI_ACTM_Drawshouldnotbeavailable_stmt_DLQ_CURE_DFLT_Sc62() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_ACTM_Drawshouldnotbeavailable_stmt_DLQ_CURE_DFLT_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
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
				test = reports.startTest(Header+"_S.No:62"+"_"+PayFrequency+"_"+CollateralType,"LOC Initiation - active Military - Draw - Statement - DLQ - CURE - Default");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);		       
				this.Agestore_Loandate(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);		       
				this.Agestore_Loandate(SSN, FileName, 2);
				this.DrawLoan(SSN, FileName);	
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN,FileName, 0);
				this.DrawerDeassign(SSN, FileName); 
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN,FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);	
				this.AgeStore(SSN,FileName, 30);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);	  
				this.Default_WOProc(SSN, FileName);
				this.WOPaymentStatus(SSN, FileName);


			}
		}
	}

	@Test (priority=21)

	public void LOCI_Draw_ACTM_stmt_ACTMEnd_Payoff_Clear() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_stmt_ACTMEnd_Payoff_Clear_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
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
				test = reports.startTest(Header+"_S.No:63"+"_"+PayFrequency+"_"+CollateralType,"LOC Initiation - Draw -> active Military - Statement - Terminate active Military - Payoff =>Clear");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);		        
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN,FileName, -3);
				this.PayOffLoan(SSN, FileName);   

			}
		}
	}

	@Test (priority=22)

	public void LOCI_Draw_ACTM_stmt_Draw_ACTMEnd_PaymentwithCash_ACTM_ACTMEnd_Stmt_Sc64() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_stmt_Draw_ACTMEnd_PaymentwithCash_ACTM_ACTMEnd_Stmt_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:64"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation - Draw - Generate Statement - Active Military Start- Draw - Payment@store wd Cash - Draw - Active Military end - Payment - Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);		       	        
				this.Agestore_Loandate(SSN, FileName, 9);
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);	
				this.AgeStore(SSN, FileName, -1);
				this.DrawLoan(SSN, FileName);
				this.Active_Military_End(SSN, FileName);		        
				this.AgeStore(SSN, FileName, 1);
				this.Payments(SSN, FileName);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.Active_Military_End(SSN, FileName);		        
				this.StatementGeneration(SSN, FileName);			        
			}
		}
	}

	@Test (priority=23)

	public void LOCI_Draw_ACTM_stmt_ACTMEnd_Deposit_ACTM_PaymentwithCash_ACTMEnd_Stmt2_Sc64() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_stmt_ACTMEnd_Deposit_ACTM_PaymentwithCash_ACTMEnd_Stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:65"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Active Military start -> Statement -> Active Military end -> Deposit -> Active Military start -> Payment@ Store CASH -> Active Military end -> Statement Generation");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName); 
				this.Agestore_Loandate(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.NACHA(SSN, FileName, 0);
				this.ACH_Deposit(SSN, FileName, 0);
				this.AgeStore(SSN, FileName, 3);
				this.Active_Military_Start(SSN, FileName);	
				this.AgeStore(SSN, FileName, 4);
				this.Payments(SSN, FileName);
				this.AgeStore(SSN, FileName, 7);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);		        
			}
		}
	}

	@Test (priority=24)

	public void LOCI_Draw_ACTM_ACTMEnd_stmt_ACTM_ACTMEnd_Stmt2_ACTM_ACTMEnd_Stmt3_Sc66() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_ACTMEnd_stmt_ACTM_ACTMEnd_Stmt2_ACTM_ACTMEnd_Stmt3_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:66"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Active Military start -> Active Military end -> Statement -> Active Military start -> Active Military end -> Statement -> Active Military start -> Active Military end -> Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);       
				this.Agestore_Loandate(SSN, FileName, 2);
				this.Active_Military_Start(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 5);
				this.Active_Military_End(SSN, FileName); 
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, 5);
				this.Active_Military_End(SSN, FileName); 
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, 5);
				this.Active_Military_End(SSN, FileName); 
				this.StatementGeneration(SSN, FileName);
			}
		}
	}

	@Test (priority=25)

	public void LOCI_Draw_ACTM_stmt_ACTMEnd_Draw_ACTM_WaiveFee_ACTMEnd_Stmt2_Sc67() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_stmt_ACTMEnd_Draw_ACTM_WaiveFee_ACTMEnd_Stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:67"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Active Military start -> Statement -> Active Military end -> Draw -> Active Military start -> Waive Fee -> Active Military end -> Statement Generation");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName); 
				this.Agestore_Loandate(SSN, FileName, 2);
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN, FileName, 2);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, 6);
				this.WaiveFee(SSN, FileName);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);


			}
		}
	}


	@Test (priority=26)

	public void LOCI_ACTM_ACTMEnd_Stmt_ACMT_Draw_ACTMEnd_stmt2_Sc69() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_ACTM_ACTMEnd_Stmt_ACMT_Draw_ACTMEnd_stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:69"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Active Military Start -> Active Military end -> Generate Statement ->Active Military Start -> Draw -> Active Military end-> Statement Generation ");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, -7);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, -6);
				this.DrawLoan(SSN, FileName);  
				this.AgeStore(SSN, FileName, 0);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);



			}
		}
	}

	@Test (priority=27)

	public void LOCI_ACTM_Draw_stmt_ACTMEnd_Draw_ACTM_WaiveFee_Pymtwithcash_ACTMEnd_Stmt2_Sc70() throws Exception {

		// Start test. Mention test script name
		String FileName= "LOCI_ACTM_Draw_stmt_ACTMEnd_Draw_ACTM_WaiveFee_Pymtwithcash_ACTMEnd_Stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:70"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Active Military start -> Draw -> Statement -> Active Military end -> Draw -> Active Military start -> Waive Fee -> Payment @ Store Wd CASH -> Active Military end -> Statement Generation");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 2);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, -2);
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 2);
				this.Active_Military_Start(SSN, FileName);
				this.WaiveFee(SSN, FileName);
				this.AgeStore(SSN, FileName, 4);
				this.Payments(SSN, FileName);
				this.AgeStore(SSN, FileName, 6);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);

			}
		}
	}

	@Test (priority=28)

	public void LOCI_Draw_ACTM_PayoffwithCash_ACTMEnd_stmt_Draw_ACTM_ACTMEnd_stmt2_ACTM_Pymt_ACTMEnd_stmt3_Sc71() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_PayoffwithCash_ACTMEnd_stmt_Draw_ACTM_ACTMEnd_stmt2_ACTM_Pymt_ACTMEnd_stmt3_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:71"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation - Draw - Active Military start - Payoff@ Store wd CASH - Active Military end - Statement - Draw - Active Military start - Active Military end - Statement - Active Military start - Payment @ Store wd CASH- Active Military end - Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);       
				this.Agestore_Loandate(SSN, FileName, 2);
				this.Active_Military_Start(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 6);
				this.PayOffLoan(SSN, FileName); 
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, 5);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, -3);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, 2);
				this.Payments(SSN, FileName);
				this.AgeStore(SSN, FileName, 4);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);

			}
		}
	}

	@Test (priority=29)

	public void LOCI_Draw_ACTM_stmt_ACTMEnd_Payoff_ACMT_VoidPayoff_ACTMEnd_stmt2_Sc72() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_stmt_ACTMEnd_Payoff_ACMT_VoidPayoff_ACTMEnd_stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:72"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Active Military start -> Statement -> Active Military end -> Payoff ->Active Military start -> Payoff Void -> Active Military end -> Statement Generation");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);       
				this.Agestore_Loandate(SSN, FileName, 2);
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 8);
				this.Active_Military_End(SSN, FileName); 
				this.AgeStore(SSN, FileName, 0);
				this.PayOffLoan(SSN, FileName);		       
				this.Active_Military_Start(SSN, FileName);
				this.Void_Payoff(SSN, FileName);
				this.AgeStore(SSN, FileName, 4);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);

			}
		}
	}

	@Test (priority=30)

	public void LOCI_Draw_ACTM_stmt_ACTMEnd_Deposit_Return_Payment_ACTM_PaymentVoid_ACTMEnd_Stmt_Sc73() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_stmt_ACTMEnd_Deposit_Return_Payment_ACTM_PaymentVoid_ACTMEnd_Stmt_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:73"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Active Military start -> Statement -> Active Military end -> Deposit -> Return -> Payment -> Active Military start -> Payment Void -> Active Military end -> Statement Generation");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);       
				this.Agestore_Loandate(SSN, FileName, 2);
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, -3);
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.NACHA(SSN, FileName, -1);
				this.ACH_Deposit(SSN, FileName, 0);
				this.AgeStore(SSN, FileName, 3);
				this.ACHReturnPosting(SSN, FileName);
				this.AgeStore(SSN, FileName, 4);
				this.Payments(SSN, FileName);
				this.Active_Military_Start(SSN, FileName);
				this.Void_PartialPayment(SSN, FileName);
				this.AgeStore(SSN, FileName, 5);
				this.Active_Military_End(SSN, FileName); 		        
				this.StatementGeneration(SSN, FileName);

			}
		}
	}	

	@Test (priority=31)

	public void LOCI_Draw_ACTM_Payoffwithcash_STMT_Sc74() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_Payoffwithcash_STMT_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
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
				test = reports.startTest(Header+"_S.No:74"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Active Military start -> Payoff @store wd CASH -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 6);
				this.PayOffLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);       

			}
		}
	}
	@Test (priority=32)

	public void LOCI_Draw_Stmt_ACTM_Payoff_STMT2_Sc75() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_Stmt_ACTM_Payoff_STMT2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
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
				test = reports.startTest(Header+"_S.No:75"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Generate Statement -> Active Military -> Payoff -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);		        
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN,FileName, -3);
				this.PayOffLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);       

			}
		}
	}
	@Test (priority=33)

	public void Loan_Draw_GenerateStatement_ACHDepositFlight_ActiveMilaitary_PaymentWDcash_ActiveMiltaryEnd_GenerateStatement() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_GenerateStatement_ACHDepositFlight_ActiveMilaitary_PaymentWDcash_ActiveMiltaryEnd_GenerateStatement_TestData.xls";
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
				test = reports.startTest(Header+"_S.No:76"+"_"+PayFrequency+"_"+CollateralType, "Loan Initiation_Draw_Generate Statement_ACH Deposit in Flight_Active Military Start_Payment @ Store wd CASH_Active Military end_Generate Statement");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.NACHA(SSN, FileName, 0);
				this.ACH_Deposit(SSN, FileName, 0);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.Payments(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.Active_Military_End(SSN, FileName); 
				this.StatementGeneration(SSN, FileName);



			}
		}


	}
	@Test (priority=34)

	public void LOCI_Draw_Stmt_DLQ_CURE_Default_Writeoff_RCCSchedule_1stinstallmentPaid_ActiveMiltary_RccPlacementshouldStop() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_Stmt_DLQ_CURE_Default_Writeoff_RCCSchedule_1stinstallmentPaid_ActiveMiltary_RccPlacementshouldStop_TestData.xls";
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
				test = reports.startTest(Header+"_S.No:77"+"_"+PayFrequency+"_"+CollateralType, "Loan Initiation_Draw_Generate Statement_DLQ_Cure_Default_Write off_RCC Enabled and Schedule generated_One instalment paid_Active Military_RCC Placements should stop");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 30);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);
				this.Default_WOProc(SSN, FileName,60);
				this.WOPaymentStatus(SSN, FileName);
				this.EditBorrower(SSN, FileName,-10);
				this.Check_RCCSchd_WO(SSN, FileName, -3);
				this.WO_Recovery_pymt_BeforeDueDate_4thInst(SSN, FileName, 0, 2);
				this.Active_Military_Start(SSN, FileName);
				this.RCCStatus(SSN, FileName);



			}
		}


	}

	@Test (priority=35)

	public void Loanintialitation_Draw_Generatestatement_DLQ_CURE_Default_Activemiltary_PaymentPlan_writeoff
	() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loanintialitation_Draw_Generatestatement_DLQ_CURE_Default_Activemiltary_PaymentPlan_writeoff_TestData.xls";
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
				test = reports.startTest(Header+"_S.No:78"+"_"+PayFrequency+"_"+CollateralType, "Loan Initiation_Draw_Generate Statement_DLQ _Cure_Default_Active Military_Payment Plan_Write off");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 30);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);
				this.Active_Military_Start(SSN, FileName);
				this.PaymentPlan(SSN, FileName);
				this.CustomerDefault_WO(SSN, FileName, 35);
				this.Default_WOProc_60days(SSN, FileName);
				this.WOPaymentStatus(SSN, FileName);





			}
		}


	}

	@Test (priority=36)

	public void LoanInition_Draw_Generatestatement_DLQ_Cure_ActiveMilitary_GenerateStatement() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LoanInition_Draw_Generatestatement_DLQ_Cure_ActiveMilitary_GenerateStatementTestData.xls";
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
				test = reports.startTest(Header+"_S.No:79"+"_"+PayFrequency+"_"+CollateralType, "Loan Initiation_Draw_Generate Statement_DLQ_Cure_Active Military_Generate Statement");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);


			}
		}


	}

	@Test (priority=37)

	public void Loan_Draw_Stmt__DLQ_Cure_ActMlty_Stmt_DFLT_Writeoff_() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_Stmt__DLQ_Cure_ActMlty_Stmt_DFLT_Writeoff_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:80_"+PayFrequency+"_"+CollateralType, "Loan Initiation _ Draw _ Generate Statement _ DLQ _ Cure _ Active Military _ Generate Statement _ Default _ Write off");
				Assert.assertTrue(true);
				appUrl = AppURL;

				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN,FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.Active_Military_Start(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);
				this.Default_WOProc(SSN, FileName);
				this.WOPaymentStatus(SSN, FileName);



			}
		}			
	}

	@Test (priority=38)

	public void Loan_Draw_Stmt_DLQ_Cure_Default_EnableRCC_ScdlGenerate_OneInstPay_ActMlty_RCCPlacementsStop_() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_Stmt_DLQ_Cure_Default_EnableRCC_ScdlGenerate_OneInstPay_ActMlty_RCCPlacementsStop_Txn_Testdata.xls";
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
				test = reports.startTest(Header+"_S.No:81_"+PayFrequency+"_"+CollateralType, "Loan Initiation _ Draw _ Generate Statement _ DLQ _ Cure _ Default _ RCC Enabled and Schedule generated _ One instalment paid _ Active Military _ RCC Placements should stop");
				Assert.assertTrue(true);
				appUrl = AppURL;


				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan(SSN,FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.NACHA(SSN, FileName, -1);
				this.ACH_Deposit(SSN, FileName, 0);			
				this.ACHReturnPosting(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);		
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);
				this.EditBorrower(SSN, FileName);
				this.RCC_Schedule_1stInst_Agestore(SSN, FileName,-1,2);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.AfterDFLT_RCCOrigination(SSN, FileName, -1, 2);
				this.LOC_PreRCC_Deposit(SSN, FileName, 0, 2);
				this.RCC_Schedule_1stInst_Agestore(SSN, FileName,10,2);
				this.Admin_ACH_Clear(SSN, FileName, 10, 2);
				this.Active_Military_Start(SSN, FileName);
				this.RCCSchduleStatus_ActiveMilitary(SSN, FileName);

			}
		}			
	}
	@Test (priority=39)

	public void LoanInitiation_Draw_GenerateStatement_DLQ_CURE_Default_Writeoff_Activemilitary_PaymentPlan() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LoanInitiation_Draw_GenerateStatement_DLQ_CURE_Default_Writeoff_Activemilitary_PaymentPlan_TestData.xls";
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
				test = reports.startTest(Header+"_S.No:82"+"_"+PayFrequency+"_"+CollateralType, "Loan Initiation_Draw_ Generate Statement_DLQ_Cure_Default_Write off _Active Military_Payment Plan");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 30);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);
				this.Default_WOProc(SSN, FileName);
				this.WOPaymentStatus(SSN, FileName);
				this.Active_Military_Start(SSN, FileName);
				this.PaymentPlan(SSN, FileName);



			}
		}


	}

	@Test (priority=40)

	public void Loanintillation_Draw_Generatestatement_Payoff_ActiveMilatary_payoffvoid_Activemilataryend_payment_GenerateStatement() throws Exception {

		// Start test. Mention test script name
		String FileName= "AALoanintillation_Draw_Generatestatement_Payoff_ActiveMilatary_payoffvoid_Activemilataryend_payment_GenerateStatement_TestData.xls";
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
				test = reports.startTest(Header+"_S.No:83"+"_"+PayFrequency+"_"+CollateralType, "Loan Initiation_Draw_Generate Statement_Payoff_Active Military Start_Payoff Void_Active Military end_Payment_Generate Statement");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.PayOffLoan(SSN, FileName);
				this.Active_Military_Start(SSN, FileName);
				this.Void(SSN, FileName);
				this.Active_Military_End(SSN, FileName);
				this.Payments(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
			}
		}


	}

	@Test (priority=41)

	public void Loaninitiation_Draw_Payment_Activemiltaray_Draw_Generatestatement_Waivefee_ActivemilataryEnd_Payment_GenerateStatement() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loaninitiation_Draw_Payment_Activemiltaray_Draw_Generatestatement_Waivefee_ActivemilataryEnd_Payment_GenerateStatement_TestData.xls";
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
				test = reports.startTest(Header+"_S.No:85"+"_"+PayFrequency+"_"+CollateralType, "Loan Initiation_Draw_Payment_Active Military Start_Draw_Generate Statement_Waive Fee_Active Military end_Payment_Generate Statement");
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoanDraw(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 3);
				this.Payments(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 4);
				this.Active_Military_Start(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.WaiveFee(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN, FileName, 4);
				this.Payments(SSN, FileName);
				this.StatementGeneration(SSN, FileName);




			}
		}


	}

	@Test (priority=42)

	public void LOCI_Draw_ACTM_stmt_Draw_ACTMEnd_PaymentwithCash_ACTM_ACTMEnd_Stmt2_Sc86() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_ACTM_stmt_Draw_ACTMEnd_PaymentwithCash_ACTM_ACTMEnd_Stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:86"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Generate Statement -> Active Military Start-> Draw -> Payment@store wd Cash -> Draw -> Active Military end -> Payment  -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);		        
				this.Agestore_Loandate(SSN, FileName, 9);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, -1);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 1);
				this.Payments(SSN, FileName); 
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.Active_Military_End(SSN, FileName);
				this.Payments(SSN, FileName);
				this.StatementGeneration(SSN, FileName);			        
			}
		}
	}

	@Test (priority=43)

	public void Loan_Draw_StGn_Draw_Deposit_Activemillatrystart_RTN_Activemillitryend_StGn() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_Loan_Draw_StGn_Draw_ACHDeposit_StartActiveMillatry_RTN_ActivemillartyEnd_StGn_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/"+FileName);  
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
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				//System.out.println(SSN);
				test = reports.startTest(Header+"_S.No:87"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Generate Statement -> Draw ->  Deposit -Active Military Start-> Return-> Active Military end -> Generate Statement");
				appUrl = AppURL;     
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoanDraw(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.NACHA(SSN, FileName, -1);
				this.ACH_Deposit(SSN, FileName, 0);
				this.Active_Military_Start(SSN, FileName);
				this.ACHReturnPosting(SSN, FileName);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				

			}
		}


	}

	@Test (priority=44)

	public void LOCI_ACTM_Draw_stmt_Pymtlessminpayamt_Draw_Pymt_VoidPymt_Draw_ACTMEnd_stmt2_Sc88() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_stmt_Pymtlessminpayamt_ACTM_Draw_DrawVoid_Draw_ACTMEnd_stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:88"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Active Military Start -> Draw -> Generate Statement -> Payment less than Min Payment Amount @ store -> Draw -> Payment@store -> Payment Void -> Draw -> Active Military end -> Draw -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.Active_Military_Start(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 2);
				this.DrawLoan(SSN, FileName);		        
				this.StatementGeneration(SSN, FileName); 		        
				this.Agestore_Loandate(SSN, FileName, 11);
				this.Payments_Lessthan_Min_Payment_Amount(SSN, FileName);	
				this.Agestore_Loandate(SSN, FileName, 12); 
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN,FileName, -4);
				this.Payments(SSN, FileName);
				this.Void_PartialPayment(SSN, FileName);
				this.AgeStore(SSN,FileName, -3);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN,FileName, -2);
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN,FileName, -1);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);

			}
		}
	}


	@Test (priority=45)

	public void LOCI_Draw_stmt_Pymtlessminpayamt_ACTM_Draw_DrawVoid_Draw_ACTMEnd_stmt2_Sc90() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_stmt_Pymtlessminpayamt_ACTM_Draw_DrawVoid_Draw_ACTMEnd_stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:90"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Generate Statement -> Payment less than Min Payment Amount -> Active Military Start-> Draw -> Draw Void -> Draw -> Active Military end -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);		        
				this.StatementGeneration(SSN, FileName);  
				this.Agestore_Loandate(SSN, FileName, 7);
				this.Payments_Lessthan_Min_Payment_Amount(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 8); 
				this.Active_Military_Start(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 9);
				this.DrawLoan(SSN, FileName);
				this.VoidDrawLoan(SSN, FileName);
				this.AgeStore(SSN,FileName, 0);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN,FileName, 2);
				this.Active_Military_End(SSN, FileName);
				this.StatementGeneration(SSN, FileName);		        



			}
		}
	}

	@Test (priority=46)

	public void LOCI_Draw_stmt_ACTM_DLQ_Payment_Draw_ACTMEnd_Draw_stmt2_Sc91() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_stmt_ACTM_DLQ_Payment_Draw_ACTMEnd_Draw_stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:91"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Generate Statement -> Active Military Start -> Delinquent -> Payment -> Draw -> Active Military end -> Draw -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);  
				this.AgeStore(SSN, FileName, -5);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN,FileName, 0);
				this.DrawerDeassign(SSN, FileName); 
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);		        
				this.AgeStore(SSN, FileName, 2);
				this.Payments(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.DrawLoan(SSN, FileName); 
				this.AgeStore(SSN, FileName, 5);
				this.Active_Military_End(SSN, FileName); 
				this.AgeStore(SSN, FileName, 7);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);		        

			}
		}
	}

	@Test (priority=47)

	public void LOCI_Draw_Stmt_ACTM_DLQ_CURE_Payment_VoidPymt_Pymt_Draw_ACTMEnd_Draw_Payment_stmt2_Sc92() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_Stmt_ACTM_DLQ_CURE_Payment_VoidPymt_Pymt_Draw_ACTMEnd_Draw_Payment_stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
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
				test = reports.startTest(Header+"_S.No:92"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Generate Statement -> Active Military Start -> Delinquent -> Cure -> Payment -> Payment Void -> Payment -> Draw -> Active Military end -> Draw -> Payment -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);		        
				this.Agestore_Loandate(SSN, FileName, 1);  
				this.DrawLoan(SSN, FileName);	
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN,FileName, -2);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN,FileName, 0);
				this.DrawerDeassign(SSN, FileName); 
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN,FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);		        
				this.AgeStore(SSN,FileName, -4); 
				this.Payments(SSN, FileName);
				this.Void_PartialPayment(SSN, FileName);
				this.AgeStore(SSN,FileName, -3); 
				this.Payments(SSN, FileName);
				this.AgeStore(SSN,FileName, -2);
				this.DrawLoan(SSN, FileName);
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN,FileName, 0);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN,FileName, 2);
				this.Payments_Partial(SSN, FileName);
				this.StatementGeneration(SSN, FileName);

			}
		}
	}

	@Test (priority=48)

	public void LOCI_Draw_Stmt_DLQ_CURE_ACTM_Payment_Stmt2_Draw_Payment_stmt3_ACTMEnd_Sc93() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_Stmt_DLQ_CURE_ACTM_Payment_Stmt2_Draw_Payment_stmt3_ACTMEnd_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
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
				test = reports.startTest(Header+"_S.No:93"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Generate Statement -> Delinquent -> Cure -> Active Military Start -> Payment -> Generate Statement -> Draw -> Payment -> Generate Statement -> Active Military end");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);		       
				this.Agestore_Loandate(SSN, FileName, 1);  
				this.DrawLoan(SSN, FileName);	
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN,FileName, 0);
				this.DrawerDeassign(SSN, FileName); 
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN,FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);	
				this.AgeStore(SSN,FileName, 1);		        
				this.Active_Military_Start(SSN, FileName);	
				this.AgeStore(SSN,FileName, 2);
				this.Payments(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN,FileName, -3);
				this.DrawLoan(SSN, FileName);
				this.AgeStore(SSN,FileName, -1);
				this.Payments(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN,FileName, 0);
				this.Active_Military_End(SSN, FileName);



			}
		}
	}

	@Test (priority=49)

	public void LOCI_Draw_Stmt_ACTM_Payment_Payoff_Payoffvoid_ACTMEnd_Payment_Stmt2_Sc94() throws Exception {

		// Start test. Mention test script name
		String FileName= "LOCI_Draw_Stmt_ACTM_Payment_Payoff_Payoffvoid_ACTMEnd_Payment_Stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:94"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Generate Statement -> Active Military Start-> Payment -> Payoff -> Payoff Void -> Active Military end -> Payment  -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName); 
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, -4);
				this.Active_Military_Start(SSN, FileName);
				this.AgeStore(SSN, FileName, -2);
				this.Payments(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.PayOffLoan(SSN, FileName);
				this.PayOff_Void(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.Active_Military_End(SSN, FileName);		        
				this.AgeStore(SSN, FileName, 5);
				this.Payments(SSN, FileName);	       
				this.StatementGeneration(SSN, FileName);





			}
		}
	}

	@Test (priority=50)

	public void LOCI_Draw_Payment_ACTM_Draw_Stmt_ACTMEnd_Payment_Stmt2_Sc95() throws Exception {

		// Start test. Mention test script name
		String FileName= "LOCI_Draw_Payment_ACTM_Draw_Stmt_ACTMEnd_Payment_Stmt2_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:95"+"_"+PayFrequency+"_"+CollateralType,"Loan Initiation -> Draw -> Payment -> Active Military Start -> Draw -> Generate Statement -> Active Military end -> Payment -> Generate Statement");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName); 
				this.Agestore_Loandate(SSN, FileName, 2);
				this.Payments(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 3);
				this.Active_Military_Start(SSN, FileName);
				this.Agestore_Loandate(SSN, FileName, 4);
				this.DrawLoan(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
				this.AgeStore(SSN, FileName, -2);
				this.Active_Military_End(SSN, FileName);
				this.AgeStore(SSN, FileName, 0);
				this.Payments(SSN, FileName);
				this.StatementGeneration(SSN, FileName);
			}
		}
	}

	@Test (priority=51)

	public void LOCI_Draw_PNBK_Drawnotavailble() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOC_Draw_PBNK_Drawnot_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName); 
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
				String Header = StateID+ "_" + ProductID;
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);		 

				test = reports.startTest(Header+"_S.No:96"+"_"+PayFrequency+"_"+CollateralType,"LOCI =>Draw =>Pending Bankruptcy =>Draw not availble");

				Assert.assertTrue(true);
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan_Draw(SSN,FileName);
				this.PendingBNK(SSN, FileName);
				this.DrawStatust(SSN, FileName);

			}
		}


	}


	@Test (priority=52)

	public void LOCI_Draw_PNBK_VoidPNBK_Drawavailable() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOC_Draw_PBNK_VoidPBNK_DrawAvailable_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName); 
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
				String Header = StateID+ "_" + ProductID;
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);		 

				test = reports.startTest(Header+"_S.No:97"+"_"+PayFrequency+"_"+CollateralType," LOCI =>Draw =>Pending Bankruptcy =>Draw not availble =.> Void PBNK =>Draw Available");

				Assert.assertTrue(true);
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan_Draw(SSN,FileName);
				this.PendingBNK(SSN, FileName);
				this.PendingBNK_Void(SSN, FileName);
				this.DrawStatus_PNBK(SSN, FileName);

			}
		}

	}

	@Test (priority=53)

	public void LOCI_Draw_PNBK_Drawnotavailble_VoidDraw() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOC_Draw_PBNK_Drawnot_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName); 
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
				String Header = StateID+ "_" + ProductID;
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);		 

				test = reports.startTest(Header+"_S.No:98"+"_"+PayFrequency+"_"+CollateralType,"LOCI =>Draw =>Pending Bankruptcy =>Draw not availble=>Void of Past Draw");

				Assert.assertTrue(true);
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan_Draw(SSN,FileName);
				this.PendingBNK(SSN, FileName);
				this.DrawStatust(SSN, FileName);
				this.VoidDrawLoan(SSN,FileName);

			}
		}
		//this.Login("CSR353","1234","353");

	}

	@Test (priority=54)
	public void LOCI_Draw_Statement_Draw_PBNK_Drawnotavailable() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOC_Draw_Statment_Draw_PBNK_DrawnotAvailable_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName); 
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
				String Header = StateID+ "_" + ProductID;
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);		 

				test = reports.startTest(Header+"_S.No:99"+"_"+PayFrequency+"_"+CollateralType,"LOCI =>Draw =>Statement =>Draw => PBNK=> Draw not available");

				Assert.assertTrue(true);
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan_Draw(SSN,FileName);
				this.StatementGeneration(SSN, FileName);
				this.DrawLoan(SSN, FileName);		        
				this.PendingBNK(SSN, FileName);
				this.DrawStatust(SSN, FileName);
			}
		}
		//this.Login("CSR353","1234","353");

	}

	@Test (priority=55)


	public void LOCI_Draw_Statement_Draw_PBNKbeforeduedate_NoDeposit() throws Exception {




		String FileName= "AA_LOC_Draw_Statement_Draw_PBNKbeforeDD_NoDeposit_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";

		System.out.println(lastrow);

		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	

				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);		 

				test = reports.startTest(Header+"Regression_Scenario.No_100"+"_"+PayFrequency+"_"+CollateralType,"LOCI=> Draw =>Statement =>PBNK before due date => No Deposit presentment");

				Assert.assertTrue(true);
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan_Draw(SSN,FileName);
				this.StatementGeneration(SSN, FileName);
				this.PendingBNK(SSN, FileName);			    
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing_NODep(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);



			}


		}


	}

	@Test (priority=56)

	public void LOCI_Draw_Statement_Draw_PBNKbeforeduedate_VoidPBNK_Deposit() throws Exception {

		String FileName= "AA_LOC_Draw_Statement_Draw_PBNKbeforeDD_VoidPBNK_YesDeposit_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";

		System.out.println(lastrow);

		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	

				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);		 

				test = reports.startTest(Header+"Regression_Scenario.No_101"+"_"+PayFrequency+"_"+CollateralType,"LOCI=> Draw =>Statement =>PBNK before due date=>Void PBNK on due date => Deposit presentment");

				Assert.assertTrue(true);
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan_Draw(SSN,FileName);
				this.StatementGeneration(SSN, FileName);
				this.PendingBNK(SSN, FileName);
				this.PendingBNK_Void(SSN, FileName);
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);

			}


		}


		//this.Login("CSR353","1234","353");


	}


	@Test (priority=57)
	public void LOCI_Draw_Statement_Draw_PBNKbeforeduedate_VoidPBNKAfterduedate_Deposit() throws Exception {

		String FileName= "AA_LOC_Draw_Statement_Draw_PBNKbeforeDD_VoidPBNKAfterDue_YesDeposit_Txn_Testdata.xls";
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName); 
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";

		System.out.println(lastrow);

		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	

				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName,"StoreID",row);
				String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String StateID = TestData.getCellData(sheetName,"StateID",row);
				String SSN = TestData.getCellData(sheetName,"SSN",row);	
				String Header = StateID+ "_" + ProductID;
				String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
				String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);		 

				test = reports.startTest(Header+"Regression_Scenario.No_102"+"_"+PayFrequency+"_"+CollateralType,"LOCI=> Draw =>Statement =>PBNK before due date=>Void PBNK after due date => Deposit presentment");

				Assert.assertTrue(true);
				appUrl = AppURL;
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test,AppURL, SSN,FileName);
				this.NewLoan_Draw(SSN,FileName);
				this.StatementGeneration(SSN, FileName);
				this.PendingBNK(SSN, FileName);			        
				this.AgeStore(SSN, FileName, 4);
				this.PendingBNK_Void(SSN, FileName);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);

			}


		}


	}

	@Test (priority=58)

	public void LOCI_Draw_stmt_Deposit_Return_DLQ_Cure_DFLT_PBNK_StopRPP_Sc103() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_stmt_Deposit_Return_DLQ_Cure_DFLT_PBNK_StopRPP_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:103"+"_"+PayFrequency+"_"+CollateralType,"LOCI=>Draw=>Statement =>Depsoit=>Rtn=>DLQ=>CURE=>DFLT=> PBNK=> Stop RPP");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName); 
				this.StatementGeneration(SSN, FileName); 
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.NACHA(SSN, FileName, -1);
				this.ACH_Deposit(SSN, FileName, 0);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.ACHReturnPosting(SSN, FileName);
				this.AgeStore(SSN,FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.AgeStore(SSN,FileName, 30);		       
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName); 
				this.PendingBNK(SSN, FileName);
				this.Check_RPP(SSN, FileName);


			}
		}
	}

	@Test (priority=59)

	public void LOCI_Draw_stmt_Deposit_Return_DLQ_Cure_DFLT_PBNK_StopRCC_Sc104() throws Exception {

		// Start test. Mention test script name
		String FileName= "AA_LOCI_Draw_stmt_Deposit_Return_DLQ_Cure_DFLT_PBNK_StopRCC_Txn_TestData.xls";		 
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
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
				test = reports.startTest(Header+"_S.No:104"+"_"+PayFrequency+"_"+CollateralType,"LOCI=>Draw=>Statement =>Depsoit=>Rtn=>DLQ=>CURE=>DFLT=> PBNK=> Stop RCC");
				appUrl = AppURL;

				this.Login(UserName,Password,StoreId);			        
				BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
				this.NewLoan(SSN,FileName);	
				this.Agestore_Loandate(SSN, FileName, 1);
				this.DrawLoan(SSN, FileName); 
				this.StatementGeneration(SSN, FileName); 
				this.AgeStore(SSN, FileName, -1);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.NACHA(SSN, FileName, -1);
				this.ACH_Deposit(SSN, FileName, 0);
				this.DeliquentPaymentStatus(SSN, FileName);
				this.AgeStore(SSN, FileName, 3);
				this.ACHReturnPosting(SSN, FileName); 
				this.AgeStore(SSN,FileName, 10);
				this.DrawerDeassign(SSN, FileName);
				this.StatementGeneration_EODProcessing(SSN, FileName);
				this.StoreInfo(SSN, FileName);
				this.Safeassign(SSN, FileName);
				this.Drawerassign(SSN, FileName);
				this.CurePaymentStatus(SSN, FileName);
				this.AgeStore(SSN,FileName, 30);		       
				this.CustomerDefault(SSN, FileName);
				this.DefaultPaymentStatus(SSN, FileName);
				this.PendingBNK(SSN, FileName);
				this.RCCStatus(SSN, FileName);


			}
		}
	}


	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 
			String screenshotPath = ExecuteScripts.getScreenhot(driver, result.getName());
			//To add it in the extent report 
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(LogStatus.PASS, result.getName()+" Test Case is Passed");}
		// reports.endTest(test);
		reports.flush();
		//Call close() at the very end of your session to clear all resources. 
		//If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		//Once this method is called, calling any Extent method will throw an error.
		//close() - To close all the operation
		//driver.quit();
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		// reports.endTest(test);
	}			
	@AfterTest

	public void endReport(){
		// writing everything to document
		//flush() - to write or update test information to your report.
		reports.endTest(test);
		reports.flush();
		//Call close() at the very end of your session to clear all resources. 
		//If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		//Once this method is called, calling any Extent method will throw an error.
		//close() - To close all the operation
		//driver.quit();


	}
	@AfterClass

	public void closeBrowser() throws Exception{

		driver.quit();

	}
}





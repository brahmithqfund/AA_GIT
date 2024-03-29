package Tests.LOC_Smoke;


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
//import java.util.Iterator;
import java.util.List;
import java.util.Random;
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


public class Draw_Deliquent_Statement_Cure {

	
	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;

	@BeforeClass
	public synchronized void initialize() {
		// Create an instance of ExtentsReports class and pass report storage
		// path as a parameter
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		//Date D = new Date();
				
		String filename="AA_Draw_Statement_Deliquent_Cure_Txn"+timestamp+".html";
		//System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/AA_Draw_Statement_Deliquent_Cure_Txn/"+filename, true);
	}

	@BeforeTest
	public void setup() throws IOException {
		System.setProperty("webdriver.ie.driver","E:/QC_Workspace/AA_Automation/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
		
		//appUrl = "http://192.168.2.203/cc/demoIndex.do";
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
		 
		  
		    
		  //Enter Username(Email)
	        //writeText(By.name(usenameId),username);
		    driver.findElement(By.name(usenameId)).sendKeys(username);
	        test.log(LogStatus.PASS, "Username is entered: "+username);

	        //Enter Password
	        //writeText(By.name(passwordId), password);
		    driver.findElement(By.name(passwordId)).clear();
		    driver.findElement(By.name(passwordId)).sendKeys(password);
	        test.log(LogStatus.PASS, "Password is entered: "+password);
	        
	        //writeText(By.name(StoreId), storenumber);
	        driver.findElement(By.name(StoreId)).sendKeys(storenumber);;
	        test.log(LogStatus.PASS, "Storenumber is entered: "+storenumber);
	        //Click Login Button
	        driver.findElement(By.name(Login)).click();
	        test.log(LogStatus.PASS, "Clicked on Submit button");
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
				
				 
				 //if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				 if(driver.findElement(By.id("ShareScreenBtn")).isEnabled())
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
						if(StoreID.equals("4322"))
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

                        driver.switchTo().window(winHandle1);

                    }
					Thread.sleep(1000);
					driver.findElement(By.name("confirmSummary")).click();
					test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
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
	
	
	public void Draw(String SSN,String FileName) throws Exception{
		
		
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
				String UserName = TestData.getCellData(sheetName,"UserName",row);
				String Password = TestData.getCellData(sheetName,"Password",row);
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
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
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

	// driver.findElement(By.name("button")).click();

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

	test.log(LogStatus.PASS, "Captured Statement Generation Date: "+DueDate);

	//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

	System.out.print(DueDate);

	driver.close();

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

	///Thread.sleep(6000); /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img

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
	

	
	/*public void StatementGeneration(String SSN,String FileName) throws Exception
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
						 
			   
			    driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
				}
				 
				 //String winHandleBefore = driver.getWindowHandle();
				 for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
				 Thread.sleep(8000);
				  // driver.findElement(By.xpath("//*[@id='home']")).click();
						
						DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
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
						 
						 
						 driver.switchTo().defaultContent();
						    driver.switchTo().frame("mainFrame");
						    driver.switchTo().frame("main");
						    
						 driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
						 test.log(LogStatus.PASS, "StoreID is entered: "+StoreID);	
						 
						 
					    	 driver.switchTo().defaultContent();
						    driver.switchTo().frame("mainFrame");
						    driver.switchTo().frame("main");
						   // Date DDueDate = df.parse(DueDate);
						    //Calendar cal = Calendar.getInstance();
							// cal.setTime(DDueDate);
							// cal.add(Calendar.DATE,0);
						      //Date DDueDateminus1= cal.getTime();
							 
							 
							//String DueDateminus1 =df.format(DDueDateminus1);
						    //String DueDate0[] =DueDateminus1.split("/");
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
			        
					       // EOD Batch Process
					        
					        Thread.sleep(5000);
					       driver.switchTo().defaultContent();
							driver.switchTo().frame("topFrame");
							driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
							test.log(LogStatus.PASS, "Clicked on Transactions");
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								Thread.sleep(5000);
								driver.findElement(By.linkText("EOD Batch Process")).click();
								test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
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
							        driver.findElement(By.name("submit")).click();
							        test.log(LogStatus.PASS, "Clicked on submit button");
					        	    
				    
			}
		}
	}
	*/
	
	
	public void Payment_Payoff(String SSN,String FileName) throws Exception
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
						 
						String pmtamt =  driver.findElement(By.name("totalOwed")).getAttribute("value");
						
					
							driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							test.log(LogStatus.PASS, "TenderType is entered: "+TenderType);
							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(pmtamt);
							test.log(LogStatus.PASS, "tenderAmt is entered: "+pmtamt);
							driver.findElement(By.name("password")).sendKeys(Password);
							test.log(LogStatus.PASS, "Password is entered: "+Password);
							driver.findElement(By.name("Submit22")).click();
							test.log(LogStatus.PASS, "Click on finishpayment Button");	

							 for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 //id="btnADV_Yes"          Permisssionto go payofffscreen
								 driver.findElement(By.id("btnADV_Yes")).click();
									test.log(LogStatus.PASS, "Click on finishpayment Button");
								 
								 
								 
							try { 
								Alert alert = driver.switchTo().alert();
								alert.accept();
								//if alert present, accept and move on.														

							}
							catch (NoAlertPresentException e) {
								//do what you normally would if you didn't have the alert.
							}
							Thread.sleep(5000);
						 
							 for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 
									String pmtamt1 =  driver.findElement(By.name("payOffAmount")).getAttribute("value");
									
									
									driver.findElement(By.name("tenderType")).sendKeys(TenderType);
									test.log(LogStatus.PASS, "TenderType is entered: "+TenderType);
									driver.findElement(By.name("tenderAmount")).sendKeys(pmtamt1);
									test.log(LogStatus.PASS, "tenderAmt is entered: "+pmtamt);
									driver.findElement(By.name("password")).sendKeys(Password);
									test.log(LogStatus.PASS, "Password is entered: "+Password);
									driver.findElement(By.name("Submit22")).click();
									test.log(LogStatus.PASS, "Click on finishpayment Button");	
									if(driver.findElement(By.name("ok")).isDisplayed())
									{
										driver.findElement(By.name("ok")).click();
										test.log(LogStatus.PASS, "Click on finishPayoffpayment Button");
										test.log(LogStatus.INFO, "Payment is done");
									}
						
						   
						   
						   //void screen
						   //name="transactionDataBean.tenderType"   disbtype dropdown
						   //name="password" pwdelem
						   //name="Submit22"   voidpayoff button
						   // //*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input    confirmvoid button


			}
			
		}
	}
	public void Payment_PayoffVoid(String SSN,String FileName) throws Exception
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
						 
							driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
						    test.log(LogStatus.PASS, "DisbType is entered: "+TenderType);
							driver.findElement(By.name("password")).sendKeys(Password);
							test.log(LogStatus.PASS, "password is entered: "+Password);
							driver.findElement(By.name("Submit22")).click();
							test.log(LogStatus.PASS, "Click on voidpayoff Button");	
							 
							 for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
								 {
									 test.log(LogStatus.INFO, "voidpayoff Completed");
									 driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
									 test.log(LogStatus.PASS, "Click on Confirm voidpayoff Button");
								 }
						   
						   //void screen
						   //name="transactionDataBean.tenderType"   disbtype dropdown
						   //name="password" pwdelem
						   //name="Submit22"   voidpayoff button
						   // //*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input    confirmvoid button


			}
			
		}
	}
	
	public void AgeStore_10days(String SSN,String FileName) throws Exception
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
						 
			   /* driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a")).click();
				}
				 
				 //String winHandleBefore = driver.getWindowHandle();
				 for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
				 Thread.sleep(8000);
				  // driver.findElement(By.xpath("//*[@id='home']")).click();*/
						
						DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]")).getText();
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
				         
				         
				         
				         //for(String winHandle : driver.getWindowHandles()){
							  //  driver.switchTo().window(winHandle);
								//}
							  //  driver.switchTo().defaultContent();
							  //  driver.switchTo().frame("mainFrame");
							    
							    
							    
							     //driver.switchTo().frame("main");
							     Date DDueDate = df.parse(DueDate);
								 Calendar cal = Calendar.getInstance();
								 cal.setTime(DDueDate);
								 cal.add(Calendar.DATE, +10);
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
			        
					      
					        
					       				    
			}
		}
	}
	

	
	
	
	
	
	
	public void AgeStore(String SSN,String FileName) throws Exception
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
				   storeupdate(UserName,Password,StoreID,DueDate,AdminURL);
			}
		}
	}
			
	public void storeupdate(String UserName,String Password,String StoreID,String DueDate,String AdminURL) throws Exception
			{
					DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");		
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
							 //cal.add(Calendar.DATE, -1);
							// Date DDueDateminus1= cal.getTime();
							 
							// String DueDateminus1 =df.format(DDueDateminus1);
						    String DueDate0[] =DueDate.split("/");
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
							        Thread.sleep(2000);
							        //driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
							        Thread.sleep(2000);
							        //Thread.sleep(8000);
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

							        
							        for(String winHandle : driver.getWindowHandles()){
									    driver.switchTo().window(winHandle);
										}
									   // driver.switchTo().defaultContent();
									  //  driver.switchTo().frame("mainFrame");
									    
					     
							       // for(String winHandle : driver.getWindowHandles()){
									  //  driver.switchTo().window(winHandle);
										//}
									   // driver.switchTo().defaultContent();
									   // driver.switchTo().frame("mainFrame");
									    //driver.switchTo().frame("main");
									   // Date DueDate = df.parse(DueDate);
										 //Calendar cal = Calendar.getInstance();
										 //cal.setTime(DDueDate);
										 //cal.add(Calendar.DATE, -1);
										 //Date DDueDateminus1= cal.getTime();
										 
										 //String DueDateminus1 =df.format(DDueDateminus1);
									    String DDueDate0[] =DueDate.split("/");
								        String DDueDate1 = DDueDate0[0];
								        String DDueDate2 = DDueDate0[1];
								        String DDueDate3 = DDueDate0[2];
							        
							        
							        Thread.sleep(5000);
							// driver.switchTo().defaultContent();
							 //driver.switchTo().frame("topFrame");
								//driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();	
								//test.log(LogStatus.PASS, "Clicked on Transactions");
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								//Thread.sleep(5000);
								driver.findElement(By.linkText("EOD Batch Process")).click();
								test.log(LogStatus.PASS, "Clicked on EOD Batch Process");
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
								 driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
								 test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
								 driver.findElement(By.name("beginMonth")).clear();
							        driver.findElement(By.name("beginMonth")).sendKeys(DDueDate1); 
							        test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
							        driver.findElement(By.name("beginDay")).clear();
							        driver.findElement(By.name("beginDay")).sendKeys(DDueDate2);
							        test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
							        driver.findElement(By.name("beginYear")).clear();
							        driver.findElement(By.name("beginYear")).sendKeys(DDueDate3);
							        test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
							        driver.findElement(By.name("submit")).click();
							        test.log(LogStatus.PASS, "Clicked on submit button");
							        if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
							        {
							        	test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");
							        }
							        else
							        {
							        	test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
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
	
	/*public void DrawerDeassign(String SSN,String FileName) throws Exception{


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
*/
				    
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
				Thread.sleep(5000);
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

				Thread.sleep(2000);
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.

				}
				Thread.sleep(2000);
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

					    
  /* public void Safeassign(String SSN,String FileName) throws Exception{
					    	 	
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
					    				
					    				
					    				driver.findElement(By.name("yes")).click(); 
					    				
					    									    			
					    				
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
						
						driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");
						//Password
						
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
						    if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
						    {

						    	 test.log(LogStatus.PASS,"Safe assigned successfully with over/short.");
						    	 driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
						    	 //driver.findElement(By.name("done")).click();
						    }
						    else
						    {
						    	test.log(LogStatus.PASS,"Safe not assigned successfully with over/short.");
						    }
					    }
					}
			}
	
	*/		
	
	
/*	public void Safeassign(String SSN,String FileName) throws Exception{
	 	
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
					
					
					driver.findElement(By.name("yes")).click(); 
					
										    			
					
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
	 
	 if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/h3/font/ul")).isDisplayed())
	 {
	
		 this.SafeDeassign(RegSSN, FileName);
		 
	 }
	 if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/h3/font/ul")).isDisplayed())
	 {
	
		 this.SafeDeassign(RegSSN, FileName);
		 CSRLoginpage login1 = new CSRLoginpage();
		 login1.Login(UserName, Password, StoreId, driver, AppURL, test);
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
	 else
	 {
		 
		 driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			
			
			
			
			
			driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");


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
	
*/
	
	
	
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

	
	
	
											   	
/*public void Drawerassign(String SSN,String FileName) throws Exception{
	
	
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
*/

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
	                   
	                    Thread.sleep(2000);
	                    driver.switchTo().defaultContent();
	                    driver.switchTo().frame("mainFrame");
	                    driver.switchTo().frame("main");
	                    //|| driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/h3/font")).getCssValue("color")=="red"
	                    if(this.Field(driver) != null )
	                    //if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td")).isDisplayed())
	                    {                    		                   
	                            Thread.sleep(1000);
	                            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	                            driver.switchTo().defaultContent();
	                            driver.switchTo().frame("mainFrame");
	                            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	                            //driver.findElement(By.cssSelector("li[id='911101']")).click();
	                            driver.findElement(By.linkText("Safe")).click();
	                            test.log(LogStatus.PASS, "Clicked on Safe");
	                            //driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
	                            //driver.findElement(By.linkText("Drawer")).click();
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
	                            	//if alert present, accept and move on.														
		
	                            	}
	                            catch (NoAlertPresentException e) {
	                            	//do what you normally would if you didn't have the alert.
		
	                            }
	                            driver.switchTo().defaultContent();
	                			driver.switchTo().frame("mainFrame");
	                			driver.switchTo().frame("main");
	                			if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
	                			{
	                				test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
	                				driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
	                				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).click();
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

	                            driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys("1234");
	                            //Password

	                            driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
	                            test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 500");


	                            driver.findElement(By.name("safeassign")).click();
	                            test.log(LogStatus.PASS,"Click on Safe Assigen");

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
	                           // if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
	                            if(driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed())
	                            {

	                                    test.log(LogStatus.PASS,"Safe assigned successfully.");
	                                    driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
	                                    //driver.findElement(By.name("done")).click();
	                            }
	                            else
	                            {
	                                    test.log(LogStatus.PASS,"Safe not assigned successfully.");
	                            }

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
	                            Thread.sleep(2000);
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
	                    else
	                    {                    	
	                             // if(driver.findElement(By.name("done")).isDisplayed())
	                            if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input")).isDisplayed())
	                            {
	                            	
	                                    test.log(LogStatus.PASS,"Drawer Assigned successfully with over/short.");
	                                    //driver.findElement(By.name("done")).click();
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
// /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/h3/font/ul    *Safe is Already assign error message

//name="safeDeassignRequestBean.noOfDollars"         Deassigned dollars
	
//name="safeDeassignRequestBean.password"             Password txtbox

//name="safedeassign"                  Deassign button

	//AfterRefresh
//name="safeDeassignRequestBean.password"             Password txtbox

//name="safedeassign"                  Deassign button

	//Next Screen
	
//name="safeRequestBean.safeOverShort"        SafeOvershortamount textelement
	
//name="safeRequestBean.amount"               SafeOvershortamount textbox
	
//	name="safeRequestBean.primary"              Reason for deassign textbox
	
//name="safeRequestBean.notes"                 notes txtbox
	
//name="transactionDataBean.password"          Password txtbox
	
///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input   finishSafeO/sbutton

///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input       safe deassign confirm button
	

public void SafeDeassign(String SSN,String FileName) throws Exception{
	
	
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
test.log(LogStatus.PASS, "Clicked on Safe");	
//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
//driver.findElement(By.linkText("Drawer")).click();
driver.findElement(By.linkText("Deassign")).click();
test.log(LogStatus.PASS, "Clicked on Assign");


driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");

//driver.findElement(By.name("previous")).click();

driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 0");

driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);

driver.findElement(By.name("safedeassign")).click();

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
driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
test.log(LogStatus.PASS,"Count of Dollar Coins is entered as 0");

driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);

driver.findElement(By.name("safedeassign")).click();
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

if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table")).isDisplayed())
{
	 WebElement htmltable=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table"));	
	    
		List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
		System.out.println("current row num "+rows.size());
		int count=0;							
		 count=driver.findElements(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[6]/input")).size();				 				
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

//name="safeRequestBean.safeOverShort"        SafeOvershortamount textelement

//name="safeRequestBean.amount"               SafeOvershortamount textbox
	
//	name="safeRequestBean.primary"              Reason for deassign textbox
	
//name="safeRequestBean.notes"                 notes txtbox
	
//name="transactionDataBean.password"          Password txtbox
	
///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input   finishSafeO/sbutton

///html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input       safe deassign confirm button
		  String OSAmount =  driver.findElement(By.name("safeRequestBean.safeOverShort")).getAttribute("value");
		  driver.findElement(By.name("safeRequestBean.amount")).sendKeys(OSAmount); 
		  driver.findElement(By.name("safeRequestBean.primary")).sendKeys("Counterfeit Bill");
          driver.findElement(By.name("safeRequestBean.notes")).sendKeys("Automation");
          driver.findElement(By.name("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();
      //  /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input   finishSafeO/sbutton
          driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
          driver.findElement(By.name("bt_AddDrawer")).click();
          try { 
        	   Alert alert = driver.switchTo().alert();
        	   alert.accept();
        	   //if alert present, accept and move on.														
        		
        	}
        	catch (NoAlertPresentException e) {
        	   //do what you normally would if you didn't have the alert.
        		
        	}
          //name="bt_AddDrawer"								Add os instance
          driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input")).click();
          
          driver.switchTo().defaultContent();
		    driver.switchTo().frame("mainFrame");
		    driver.switchTo().frame("main");
		    
		    if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).isDisplayed())
		    {

		    	 test.log(LogStatus.PASS,"Safe De-assigned successfully with over/short.");
		    	 driver.findElement(By.name("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
		    }
		    else
		    {
		    	test.log(LogStatus.PASS,"Safe not De-assigned successfully with over/short.");
		    }
		
         
         



driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");

driver.findElement(By.name("yes")).click();


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

			}}}






   public void Safe(String SSN,String FileName) throws Exception{
		
		
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
	//driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
	//driver.findElement(By.linkText("Drawer")).click();
	driver.findElement(By.linkText("Assign")).click();
	test.log(LogStatus.PASS, "Clicked on Assign");


	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.switchTo().frame("main");

	driver.findElement(By.name("previous")).click();
	
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.switchTo().defaultContent();
	driver.switchTo().frame("mainFrame");
	driver.switchTo().frame("main");

	driver.findElement(By.name("yes")).click();


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

				}}}
   
   
   
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
				    driver.close();
	
			}
		}
	}
   
   public void NewLoan(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData"+FileName);     	
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
				//String StoreId = TestData.getCellData(sheetName,"StoreID",row);
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
				String Parent_Window = driver.getWindowHandle();
				/*this.Login(UserName,Password,StoreId);
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
				driver.findElement(By.cssSelector("li[id='911100']")).click();			
				test.log(LogStatus.PASS, "Clicked on New Loan");		
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
			    driver.switchTo().defaultContent();
			    driver.switchTo().frame("mainFrame");
			    driver.switchTo().frame("main");
			    driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");*/
				for( String winHandle1 : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle1);
				}			
				 driver.switchTo().defaultContent();
				 driver.switchTo().frame("mainFrame");
				 driver.switchTo().frame("main");
				 //	Selection of Product based on the Name provided in Test Data
				
				 //if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				 if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				 {
					 //driver.findElement(By.xpath("//input[contains(text(),"+stateProduct+")]")).click();
				//test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);	
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
						////							*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("TNPDL all coll"))
					{								////*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
						driver.findElement(By.name("prodSel")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Tennessee"))
					{
						driver.findElement(By.xpath("//*[@id='termSel1']")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
					}
					if(ProductName.equals("Line of Credit"))
					{
						
						if(StoreID.equals("4330"))
						{
														 //*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
							//driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
						}
						if(StoreID.equals("4322"))
						{
							//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
							
						driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
						test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						
						}
						if(StoreID.equals("1343"))
						{
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[4]/td[2]/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
						
					}
					//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
				   /* WebElement htmltable=driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table"));
				    													//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table
				    
					List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
					
					int count=0;							
					 count=driver.findElements(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr")).size();	
					 									//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[1]
					 System.out.println("current row num "+count);	
					 System.out.println(" rows num "+ rows.size());
					for(int rnum=1;rnum<=count;rnum++)
					{
						System.out.println("current row num "+rnum);						
					List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));							
					
					System.out.println("columns Count "+columns.size());
						
					for(int cnum=0;cnum<columns.size();cnum++)//columns.size()
					{					
						String product_name=columns.get(cnum).getText();						
						System.out.println(product_name);	
							
						if(product_name.equals(stateProduct))
						{
								
							if(ProductID.equals("PDL"))
							{					
								rnum=rnum+1;														
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[2]/input")).click();								
								
							}
						}
						if(stateProduct.equals("MO PDL"))
						{
								
							if(ProductID.equals("PDL"))
							{					
								rnum=rnum+1;														
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input")).click();														
							}
						}
						
							if(ProductID.equals("ILP")||ProductID.equals("TLP"))							
							{	
								
								System.out.println("IN ILP/TLP");
								String Pname=driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[2]")).getText();
								System.out.println("current row of table"+Pname);
								if(Pname.equals(stateProductType))
								{
									if(Term.equals("Term1"))
									{
									driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[5]/table/tbody/tr/td[2]/table[1]/tbody/tr[1]/td/b/input")).click();								
									}
									if(Term.equals("Term2"))
									{
										driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr["+rnum+"]/td[5]/table/tbody/tr/td[2]/table[2]/tbody/tr[1]/td/b/input")).click();									
									}
								}																
								
							}																														
					}							 			
					}
					if(ProductID.equals("PDL"))
					{
						test.log(LogStatus.PASS, "Product selected as "+stateProduct);
					}
					if(ProductID.equals("ILP")||ProductID.equals("TLP"))
					{
						test.log(LogStatus.PASS, "Product selected as "+stateProductType+" Term Selected as "+Term);
					}*/
					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");
					for( String winHandle1 : driver.getWindowHandles())

                   {

                       driver.switchTo().window(winHandle1);

                   }
					Thread.sleep(1000);
					driver.findElement(By.name("confirmSummary")).click();
					test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
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
						System.out.println(Instamt);
						driver.findElement(By.name("advanceRequestBean.disbAmtFirst")).sendKeys(Instamt);					
						test.log(LogStatus.PASS, "Disb Amt is enterted as "+Instamt);
						Thread.sleep(5000);
						///driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						//test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
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
						test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
						for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("bdyLoad");
						/*if(driver.findElement(By.name("Ok")).isDisplayed())
						{
							//driver.findElement(By.name("Ok")).click();
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("Ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}*/
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
					//	for( String winHandle1 : driver.getWindowHandles())
					//	{
					//	    driver.switchTo().window(winHandle1);
					//	}			
						// driver.switchTo().defaultContent();
						// driver.switchTo().frame("mainFrame");
						// driver.switchTo().frame("main");
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
						//driver.findElement(By.name("button2")).click();	
						//driver.findElement(By.name("button2")).click();	
						test.log(LogStatus.PASS, "click on Update 2 button ");
						Thread.sleep(8000);
						//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
						/*for( String winHandle1 : driver.getWindowHandles())
						{
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");*/
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("process")));
						driver.findElement(By.name("process")).click();
						//driver.findElement(By.name("process")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
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
							//String mwh=driver.getWindowHandle();
							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as "+CouponNbr);
							//String winHandle = driver.getWindowHandle(); //Get current window handle.									
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
					
						driver.findElement(By.name("advanceRequestBean.paymentCollateralType")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "CollateralType is selected as "+ESign_CollateralType);
						Thread.sleep(5000);
						driver.findElement(By.name("advanceRequestBean.courtesyCallFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("advanceRequestBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS, "Payment Reminder Consent is selected as "+ESign_CourtesyCallConsent);
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
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
						
						if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[2]/input")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[2]/input")).click();
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
	
   //payment screen
   
   // name="requestBean.paymentAmt"   Paymentamountfield
   //name="totalOwed"        paymentamounttxteleemnt
   // name="requestBean.tenderType"    tendertype dropdown
   //name="requestBean.tenderAmt"      tenderamount textbox
   //name="password"   pass txtbox
   //name="Submit22"        finishpayment button
   //id="btnADV_Yes"          Permisssionto go payofffscreen
   
   
   //payoffscreen
   //name="payOffAmount" value txtelm
   //name="tenderType"    tendertype
   //name="tenderAmount"   tenderamountbox
   //name="password"  pwdbox
   //name="Submit22"   finishpayoff
   //name="ok"    confirm payoffpayment button
   
   
   //void screen
   //name="transactionDataBean.tenderType"   disbtype dropdown
   //name="password" pwdelem
   //name="Submit22"   voidpayoff button
   // //*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input    confirmvoid button



	@Test (priority=0)
	
	 public void RegistrationTest() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_Draw_Statement_Deliquent_Cure_Txn_Testdata.xls";
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
		        test = reports.startTest("Cure"+Header, "Loan Cure");
		        appUrl = AppURL;
		        //this.StatementGeneration(SSN, FileName);
		        //this.AgeStore(SSN,FileName);  
		        //this.StoreInfo(SSN, FileName);
		        //this.Safeassign(SSN, FileName);
		        //this.AgeStore_10days(SSN, FileName);
		       // this.LoanDeposit(SSN, FileName);
		   CSRLoginpage login = new CSRLoginpage();
		    login.Login(UserName, Password, StoreId, driver, AppURL, test);
		    BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
		    Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
		    
		        this.NewLoanDraw(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		     
		        this.StoreInfo(SSN, FileName);
		      //  this.SafeDeassign(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.Draw(SSN, FileName);
		        this.AgeStore(SSN,FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		      //  this.EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.Payment_Payoff(SSN, FileName);
		        this.Payment_PayoffVoid(SSN, FileName);
		        this.StatementGeneration(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        //this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.EODProcessing(SSN, FileName);
		        
		        
		        
		        
		        
		        this.StoreInfo(SSN, FileName);
		       // this.SafeDeassign(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        
		        
		        
		      
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        //this.StatementGeneration_EODProcessing(SSN, FileName);
		       this.EODProcessing(SSN, FileName);
		       // this.LoanDeposit(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CurePaymentStatus(SSN, FileName);
		        //this.LoanDeposit(SSN, FileName);
		      /*  this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AgeStore_10days(SSN, FileName);
		        this.DrawerDeassign(SSN, FileName);
		        this.EODProcessing(SSN, FileName);
		       // this.LoanDeposit(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CurePaymentStatus(SSN, FileName);
		       */
		        
		       		        		        	        	        
		}
		}
		
	
			}
	@AfterMethod
	@AfterTest
	public void tearDown() {
		// Ending Test
		reports.endTest(test);

		// writing everything into HTML report
		reports.flush();
	}

	@AfterClass
	public void quit() {
		// Closing browser
		driver.quit();

	}

	public void takeScreenShot(WebDriver driver, String filePath) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}






//   /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/h3/font/ul    *Safe is Already assign error message

//  name="safeDeassignRequestBean.noOfDollars"         Deassigned dollars
	
//  name="safeDeassignRequestBean.password"             Password txtbox

//  name="safedeassign"                  Deassign button

	//AfterRefresh
//  name="safeDeassignRequestBean.password"             Password txtbox

//  name="safedeassign"                  Deassign button

	//Next Screen
	
// name="safeRequestBean.safeOverShort"        SafeOvershortamount textelement
	
// name="safeRequestBean.amount"               SafeOvershortamount textbox
	
//	name="safeRequestBean.primary"              Reason for deassign textbox
	
//  name="safeRequestBean.notes"                 notes txtbox
	
// name="transactionDataBean.password"          Password txtbox
	
// name="bt_AddDrawer"								Add os instance
	
//  /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input   finishSafeO/sbutton

//  /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/input       safe deassign confirm button
	
}
	


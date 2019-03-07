package Tests.PDL;

   import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.NoAlertPresentException;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import com.relevantcodes.extentreports.LogStatus;

import Pages.BorrowerRegistrationpage;
import Pages.CSRLoginpage;
import Utilities.ExtentReports.Excel;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.Test;
	import org.testng.annotations.BeforeClass;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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

	import Utilities.ExtentReports.Excel;

	public class NewLoan_Prenote_Deposit_Verification {

	
		public WebDriverWait wait;	
		WebDriver driver;
		String appUrl;

		static ExtentReports reports;
		ExtentTest test;
		private Object ACH_voidPrePayment;

		@BeforeClass
		public synchronized void initialize() {
			// Create an instance of ExtentsReports class and pass report storage
			// path as a parameter
			//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
			//Date D = new Date();
					
			String filename="Prenotedeposit_Verification_Txn"+timestamp+".html";
			//System.out.print(filename);
			reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/Prenotedeposit_Verification/"+filename, true);
			//reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/PDL/ShortListedScenarios.html", true);
		}

		@BeforeTest
		public void setup() throws IOException {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
			//System.setProperty("webdriver.ie.driver","E:/Ncp_Workspace/Selenium/IEDriverServer.exe");
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
			 
			   // String username= "CSR353";
			   // String password= "1234";
			   // String storenumber= "353";
			    
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
			//if(driver.findElement()
		
		public void RegistrationPage(String SSN,String FileName) throws Exception{
			
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL/"+FileName);     		
			int lastrow=TestData.getLastRow("Borrower_Registration");
			
			String sheetName="Borrower_Registration";		
			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{	
					
				String AppURL = TestData.getCellData(sheetName,"AppURL",row);
				//String UserName = TestData.getCellData(sheetName,"UserName",row);
				//String Password = TestData.getCellData(sheetName,"Password",row);
		       // System.out.println(Password);
		      //  String StoreId = TestData.getCellData(sheetName,"StoreID",row);
		       // String ProductID = TestData.getCellData(sheetName,"ProductID",row);
		       // String StateID = TestData.getCellData(sheetName,"StateID",row);
		       // String SSN = TestData.getCellData(sheetName,"SSN",row);	
		        //String Header = StateID+ "_" + ProductID;	      
		       // test = reports.startTest("BorrowerRegistration_"+Header, "Register a Borrower");	          
		       String LastName = TestData.getCellData(sheetName,"LastName",row);
		       String FirstName = TestData.getCellData(sheetName,"FirstName",row);
		       String AddressLine = TestData.getCellData(sheetName,"AddressLine",row);
		       String City = TestData.getCellData(sheetName,"City",row);
		       String State = TestData.getCellData(sheetName,"State",row);	      
		       String ZipCode = TestData.getCellData(sheetName,"ZipCode",row);
		       String MonthsAtAddress = TestData.getCellData(sheetName,"MonthsAtAddress",row);	     
		       String Bank_ABARoutingNbr = TestData.getCellData(sheetName,"Bank_ABARoutingNbr",row);
		       String Bank_ChkgAcctNbr = TestData.getCellData(sheetName,"Bank_ChkgAcctNbr",row);	       
		       String Ctc_PrimaryPhone = TestData.getCellData(sheetName,"Ctc_PrimaryPhone",row);
		       String Ctc_PhoneType = TestData.getCellData(sheetName,"Ctc_PhoneType",row);
		       String Misc_PhotoIDNbr = TestData.getCellData(sheetName,"Misc_PhotoIDNbr",row);
		       String Misc_IDExpDate = TestData.getCellData(sheetName,"Misc_IDExpDate",row);	   
		       String Misc_PhotoIDType = TestData.getCellData(sheetName,"Misc_PhotoIDType",row);
		       String BorrDOB = TestData.getCellData(sheetName,"Misc_DOB",row);
		       String Income_IncomeType = TestData.getCellData(sheetName,"Income_IncomeType",row);
		       String Income_Employer = TestData.getCellData(sheetName,"Income_Employer",row);
		       String Income_WorkPhone = TestData.getCellData(sheetName,"Income_WorkPhone",row);
		       String Income_NetIncomeAmt = TestData.getCellData(sheetName,"Income_NetIncomeAmt",row);
		       String Income_GrossIncome = TestData.getCellData(sheetName,"Income_GrossIncome",row);
		       String Income_PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
		       String Income_HireDt = TestData.getCellData(sheetName,"Income_HireDt",row);
		       String Income_DirectDeposit=TestData.getCellData(sheetName,"Income_DirectDeposit",row);	
		       String ProductType=TestData.getCellData(sheetName,"ProductType",row);
		      /* String PrimaryRef_LastName = TestData.getCellData(sheetName,"PrimaryRef_LastName",row);
		       String PrimaryRef_FirstName = TestData.getCellData(sheetName,"PrimaryRef_FirstName",row);
		       String PrimaryRef_Relationship = TestData.getCellData(sheetName,"PrimaryRef_Relationship",row);
		       String PrimaryRef_PhoneNbr=TestData.getCellData(sheetName,"PrimaryRef_PhoneNbr",row);
		       String Ref_LastName = TestData.getCellData(sheetName,"Ref_LastName",row);
		       String Ref_FirstName = TestData.getCellData(sheetName,"Ref_FirstName",row);
		       String Ref_Relationship = TestData.getCellData(sheetName,"Ref_Relationship",row);
		       String Ref_PhoneNbr=TestData.getCellData(sheetName,"Ref_PhoneNbr",row);*/	       
		       String Bankruptcy=TestData.getCellData(sheetName,"Bankruptcy",row);	     
					test.log(LogStatus.INFO, "Borrower Registration-SSN: " +SSN);
					   DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
				        String SSN1 = SSN.substring(0, 3);
				        String SSN2 = SSN.substring(3,5);
				        String SSN3 = SSN.substring(5,9);
				        String PP1 = Ctc_PrimaryPhone.substring(0, 3);
				        String PP2 = Ctc_PrimaryPhone.substring(3, 6);
				        String PP3 = Ctc_PrimaryPhone.substring(6, 10);
				        String IncomeP1 = Income_WorkPhone.substring(0, 3);
				        String IncomeP2 = Income_WorkPhone.substring(3, 6);
				        String IncomeP3 = Income_WorkPhone.substring(6, 10);
				      /*  String PrimaryRef_PhoneNbr1 = PrimaryRef_PhoneNbr.substring(0, 3);
				        String PrimaryRef_PhoneNbr2 = PrimaryRef_PhoneNbr.substring(3, 6);
				        String PrimaryRef_PhoneNbr3 = PrimaryRef_PhoneNbr.substring(6, 10);
				        String Ref_PhoneNbr1 = Ref_PhoneNbr.substring(0, 3);
				        String Ref_PhoneNbr2 = Ref_PhoneNbr.substring(3, 6);
				        String Ref_PhoneNbr3 = Ref_PhoneNbr.substring(6, 10);*/			       
				        System.out.println(Misc_IDExpDate);
				        Date Misc_IDExpDt = df.parse(Misc_IDExpDate);
				        String IDExpDate0 =df.format(Misc_IDExpDt);	
				        System.out.println(IDExpDate0);
				        String IDExpDate[] =IDExpDate0.split("/");
				        String IDExpD1 = IDExpDate[0];
				        String IDExpD2 = IDExpDate[1];
				        String IDExpD3 = IDExpDate[2];
				        String DOB[] =BorrDOB.split("/");
				        String DOB1 = DOB[0];
				        String DOB2 = DOB[1];
				        String DOB3 = DOB[2];	
				        appUrl = AppURL;
				      //  this.Login(UserName, Password, StoreId);
				        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				        Thread.sleep(5000);
				   // driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 1000);	
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				//WebElement  ele = driver.findElement(By.name("topFrame"));
				//new Actions(driver).moveToElement(ele).perform();
		     	        
		        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
		        driver.findElement(By.cssSelector("li[id='900000']")).click();	
				//driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();			
				test.log(LogStatus.PASS, "Clicked on Borrower");
				//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");			 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id='901000']")));
				driver.findElement(By.cssSelector("li[id='901000']")).click();			
				test.log(LogStatus.PASS, "Clicked on Registration");			
				driver.switchTo().frame("main");
				driver.findElement(By.name("customerBean.custProdType")).sendKeys(ProductType);
				 test.log(LogStatus.PASS, "ProductType is entered: "+ProductType);
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				 test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("ssn4")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "Confirm SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn5")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "Confirm SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn6")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "Confirm SSN3 is entered: "+SSN3);			
				driver.findElement(By.name("customerBean.lastNm")).sendKeys(LastName);
				test.log(LogStatus.PASS, "LastName is entered: "+LastName);
				driver.findElement(By.name("customerBean.firstNm")).sendKeys(FirstName);
				test.log(LogStatus.PASS, "FirstName is entered: "+FirstName);
				driver.findElement(By.name("customerBean.addressLn")).sendKeys(AddressLine);
				test.log(LogStatus.PASS, "AddressLine is entered: "+AddressLine);
				driver.findElement(By.name("customerBean.city")).sendKeys(City);
				test.log(LogStatus.PASS, "City is entered: "+City);
				driver.findElement(By.name("customerBean.stateCd")).sendKeys(State);
				test.log(LogStatus.PASS, "State is entered: "+State);
				driver.findElement(By.name("customerBean.postalCd")).sendKeys(ZipCode);
				test.log(LogStatus.PASS, "ZipCode is entered: "+ZipCode);
				driver.findElement(By.name("customerBean.sameMailAddress")).click();
				test.log(LogStatus.PASS, "Mailing address is selected as same as above");
				driver.findElement(By.name("customerBean.monthsAtAddress")).sendKeys(MonthsAtAddress);
				test.log(LogStatus.PASS, "MonthsAtAddress is entered: "+MonthsAtAddress);			
				driver.findElement(By.name("customerBean.rentOwnFlg")).sendKeys("Yes");
				test.log(LogStatus.PASS, "Own Residence?* is entered: Yes");
				driver.findElement(By.name("phoneNbr1")).sendKeys(PP1);
				test.log(LogStatus.PASS, "PP1 is entered: "+PP1);
				driver.findElement(By.name("phoneNbr2")).sendKeys(PP2);
				test.log(LogStatus.PASS, "PP2 is entered: "+PP2);
				driver.findElement(By.name("phoneNbr3")).sendKeys(PP3);
				test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
				//driver.findElement(By.name("phoneNbr3"))(PP3);
				//test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
				Select PhoneType  = new Select(driver.findElement(By.name("customerBean.phoneCd")));
				PhoneType.selectByVisibleText(Ctc_PhoneType);
				test.log(LogStatus.PASS, "Phone Type is selected as: "+Ctc_PhoneType);
				driver.findElement(By.name("sphoneNbr1")).sendKeys(PP1);
				test.log(LogStatus.PASS, "SPP1 is entered: "+PP1);
				driver.findElement(By.name("sphoneNbr2")).sendKeys(PP1);
				test.log(LogStatus.PASS, "SPP2 is entered: "+PP1);
				driver.findElement(By.name("sphoneNbr3")).sendKeys(PP3);
				test.log(LogStatus.PASS, "SPP3 is entered: "+PP3);
				//driver.findElement(By.name("phoneNbr3"))(PP3);
				//test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
				Select SubPhoneType  = new Select(driver.findElement(By.name("customerBean.cphoneCd")));
				SubPhoneType.selectByVisibleText(Ctc_PhoneType);
				test.log(LogStatus.PASS, "Secondary Phone Type is selected as: "+Ctc_PhoneType);
				driver.findElement(By.name("customerBean.isCustomerEmailQuest")).click();
				test.log(LogStatus.PASS, "Does not have e-mail selected");
				driver.findElement(By.name("customerBean.driversLicNbr")).sendKeys(Misc_PhotoIDNbr);
				test.log(LogStatus.PASS, "PhotoIDNbr is entered: "+Misc_PhotoIDNbr);
				driver.findElement(By.name("customerBean.driversLicSt")).sendKeys(State);
				test.log(LogStatus.PASS, "ID State is entered: "+State);
				driver.findElement(By.name("dlexpiry1")).sendKeys(IDExpD1);
				test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD1);
				driver.findElement(By.name("dlexpiry2")).sendKeys(IDExpD2);
				test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD2);
				driver.findElement(By.name("dlexpiry3")).sendKeys(IDExpD3);
				test.log(LogStatus.PASS, "ID Expiration Date1 is entered: "+IDExpD3);
				driver.findElement(By.name("customerBean.photoIdType")).sendKeys(Misc_PhotoIDType);
				test.log(LogStatus.PASS, "PhotoIDType is entered: "+Misc_PhotoIDType);
				driver.findElement(By.name("customerBean.drivingZipcode")).sendKeys(ZipCode);
				test.log(LogStatus.PASS, "ZipCode is entered: "+ZipCode);
				driver.findElement(By.name("dob1")).sendKeys(DOB1);
				test.log(LogStatus.PASS, "DOB1 Date1 is entered: "+DOB1);
				driver.findElement(By.name("dob2")).sendKeys(DOB2);
				test.log(LogStatus.PASS, "DOB3 is entered: "+DOB2);
				driver.findElement(By.name("dob3")).sendKeys(DOB3);
				test.log(LogStatus.PASS, "DOB3 is entered: "+DOB3);
				//driver.findElement(By.name("PhoneNbr2")).sendKeys(PP3);
				driver.findElement(By.name("customerBean.incomeCdDisp")).sendKeys(Income_IncomeType);
				test.log(LogStatus.PASS, "IncomeType is entered: "+Income_IncomeType);
				driver.findElement(By.name("customerBean.empNmDisp")).sendKeys(Income_Employer);
				test.log(LogStatus.PASS, "Employer is entered: "+Income_Employer);
				driver.findElement(By.name("workPhoneNbrDisp1")).sendKeys(IncomeP1);
				test.log(LogStatus.PASS, "PP1 is entered: "+IncomeP1);
				driver.findElement(By.name("workPhoneNbrDisp2")).sendKeys(IncomeP2);
				test.log(LogStatus.PASS, "PP2 is entered: "+IncomeP2);
				driver.findElement(By.name("workPhoneNbrDisp3")).sendKeys(IncomeP3);
				test.log(LogStatus.PASS, "PP3 is entered: "+IncomeP3);
				driver.findElement(By.name("customerBean.incomeAmtDisp")).sendKeys(Income_NetIncomeAmt);
				test.log(LogStatus.PASS, "Income_NetIncomeAmt is entered: "+Income_NetIncomeAmt);
				driver.findElement(By.name("customerBean.grossAmtDisp")).sendKeys(Income_GrossIncome);
				test.log(LogStatus.PASS, "Income_GrossIncome is entered: "+Income_GrossIncome);
				driver.findElement(By.name("customerBean.payFreqCdDisp")).sendKeys(Income_PayFrequency);
				test.log(LogStatus.PASS, "Income_PayFrequency is entered: "+Income_PayFrequency);
				String Parent_Window = driver.getWindowHandle();
				if(Income_PayFrequency.equals("Semi-Monthly"))
				{
					driver.findElement(By.id("rad_semi1")).click();
					test.log(LogStatus.PASS, "The 1st and 16th day of each month is selected");
				}
				if(Income_PayFrequency.equals("Bi-Weekly"))
				{
					driver.findElement(By.id("rad_wk4")).click();
					test.log(LogStatus.PASS, "Wednesday is selected");
					driver.findElement(By.id("biwksndid")).click();
					test.log(LogStatus.PASS, "Which day is your next Pay date? is selected as last date radio button");				
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("bottom");
				 String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
				 String Busdate[]=BusinessDt.split(":");
				 String date = Busdate[1];
				
				 Date d1 = df.parse(date);
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(d1);
				 cal.add(Calendar.DATE, -20);
				 Date PayStubReviewedDate1= cal.getTime();
				 
				 String PayStubReviewedDate =df.format(PayStubReviewedDate1);
						 //Date D=Add(date1,7);
				 //System.out.println(date);
				
				 //System.out.println(PayStubReviewedDate);
				 
				 	String PayStubReviewedDate0[] =PayStubReviewedDate.split("/");
			        String PayStubReviewedDate2 = PayStubReviewedDate0[0];
			        String PayStubReviewedDate3 = PayStubReviewedDate0[1];
			        String PayStubReviewedDate4 = PayStubReviewedDate0[2];
			        driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
			        driver.findElement(By.name("payStubReviewed1")).sendKeys(PayStubReviewedDate2);
					test.log(LogStatus.PASS, "PayStubReviewed1 is entered: "+PayStubReviewedDate2);
					driver.findElement(By.name("payStubReviewed2")).sendKeys(PayStubReviewedDate3);
					test.log(LogStatus.PASS, "PayStubReviewed2 is entered: "+PayStubReviewedDate3);
					driver.findElement(By.name("payStubReviewed3")).sendKeys(PayStubReviewedDate4);
					test.log(LogStatus.PASS, "PayStubReviewed3 is entered: "+PayStubReviewedDate4);
					
					cal.add(Calendar.DATE, -30);
					 Date PayStubDate1= cal.getTime();
					 
					 String PayStubDate =df.format(PayStubDate1);
							 //Date D=Add(date1,7);
					 //System.out.println(date);
					
					 //System.out.println(PayStubReviewedDate);
					 
					 	String PayStubDate0[] =PayStubDate.split("/");
				        String PayStubDate2 = PayStubDate0[0];
				        String PayStubDate3 = PayStubDate0[1];
				        String PayStubDate4 = PayStubDate0[2];
				        driver.findElement(By.name("payStubDate1")).sendKeys(PayStubDate2);
						test.log(LogStatus.PASS, "payStubDate1 is entered: "+PayStubDate2);
						driver.findElement(By.name("payStubDate2")).sendKeys(PayStubDate3);
						test.log(LogStatus.PASS, "payStubDate2 is entered: "+PayStubDate3);
						driver.findElement(By.name("payStubDate3")).sendKeys(PayStubDate4);
						test.log(LogStatus.PASS, "payStubDate3 is entered: "+PayStubDate4);
						
						String Income_HireDt0[] =Income_HireDt.split("/");
				        String Income_HireDt1 = Income_HireDt0[0];
				        String Income_HireDt2 = Income_HireDt0[1];
				        String Income_HireDt3 = Income_HireDt0[2];
				        
				        driver.findElement(By.name("hireDate1")).sendKeys(Income_HireDt1);
						test.log(LogStatus.PASS, "hireDate1 is entered: "+Income_HireDt1);
						driver.findElement(By.name("hireDate2")).sendKeys(Income_HireDt2);
						test.log(LogStatus.PASS, "hireDate2 is entered: "+Income_HireDt2);
						driver.findElement(By.name("hireDate3")).sendKeys(Income_HireDt3);
						test.log(LogStatus.PASS, "hireDate3 is entered: "+Income_HireDt3);
			
				driver.findElement(By.name("customerBean.directDeposit")).sendKeys(Income_DirectDeposit);
				test.log(LogStatus.PASS, "DirectDeposit is entered: "+Income_DirectDeposit);
				cal.add(Calendar.DATE, -60);
				 Date Bank_AcctVerificationDt0= cal.getTime();
				 
				 String Bank_AcctVerificationDt =df.format(Bank_AcctVerificationDt0);
				 String Bank_AcctVerificationDt1[] =Bank_AcctVerificationDt.split("/");
			        String Bank_AcctVerificationDt2 = Bank_AcctVerificationDt1[0];
			        String Bank_AcctVerificationDt3 = Bank_AcctVerificationDt1[1];
			        String Bank_AcctVerificationDt4 = Bank_AcctVerificationDt1[2];
				driver.findElement(By.name("statementEndDtDisp1")).sendKeys(Bank_AcctVerificationDt2);
				test.log(LogStatus.PASS, "Bank_AcctVerificationDt1 is entered: "+Bank_AcctVerificationDt2);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.name("statementEndDtDisp2")).sendKeys(Bank_AcctVerificationDt3);
				test.log(LogStatus.PASS, "Bank_AcctVerificationDt2 is entered: "+Bank_AcctVerificationDt3);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.name("statementEndDtDisp3")).sendKeys(Bank_AcctVerificationDt4);
				test.log(LogStatus.PASS, "Bank_AcctVerificationDt3 is entered: "+Bank_AcctVerificationDt4);
				
				 //driver.findElement(By.name("customerBean.abaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
				//driver.findElement(By.name("phoneNbr3"))(PP3);
				//test.log(LogStatus.PASS, "PP3 is entered: "+PP3);
				driver.findElement(By.name("customerBean.abaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
				test.log(LogStatus.PASS, "Bank_ABARoutingNbr is entered: "+Bank_ABARoutingNbr);
				driver.findElement(By.name("checkAbaNbrDisp")).sendKeys(Bank_ABARoutingNbr);
				test.log(LogStatus.PASS, "Confirm ABA/Routing Nbr is entered: "+Bank_ABARoutingNbr);
				driver.findElement(By.name("customerBean.accountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
				test.log(LogStatus.PASS, "Chkg Acct Nbr is entered: "+Bank_ChkgAcctNbr);			
				driver.findElement(By.name("checkAccountNbrDisp")).sendKeys(Bank_ChkgAcctNbr);
				test.log(LogStatus.PASS, "Confirm Chkg Acct Nbr is entered: "+Bank_ChkgAcctNbr);			
				//driver.findElement(By.name("customerBean.drivingZipcode")).sendKeys(Bank_ChkgAcctNbr);
				//test.log(LogStatus.PASS, "drivingZipcode is entered: "+MiscZipCode);
				
				/*//Primary Reference Details
				
				
					//driver.findElement(By.name("customerBean.contName")).sendKeys(PrimaryRef_LastName);
					//test.log(LogStatus.PASS, "PRLast Name is entered: "+PrimaryRef_LastName);
				//	driver.findElement(By.name("customerBean.contactFirstName")).sendKeys(PrimaryRef_FirstName);
				//	test.log(LogStatus.PASS, "PRFirst Name is entered: "+PrimaryRef_FirstName);
				
				//driver.findElement(By.name("customerBean.contName")).sendKeys(PrimaryRef_LastName);
					driver.findElement(By.name("customerBean.contactrelationDisp")).sendKeys(PrimaryRef_Relationship);
					test.log(LogStatus.PASS, "Contactrelation is entered: "+PrimaryRef_Relationship);
					driver.findElement(By.name("cphoneNbrDisp1")).sendKeys(PrimaryRef_PhoneNbr1);
					test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is entered: "+PrimaryRef_PhoneNbr1);
					driver.findElement(By.name("cphoneNbrDisp2")).sendKeys(PrimaryRef_PhoneNbr2);
					test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is entered: "+PrimaryRef_PhoneNbr2);
					driver.findElement(By.name("cphoneNbrDisp3")).sendKeys(PrimaryRef_PhoneNbr3);
					test.log(LogStatus.PASS, "PrimaryReference Phone Nbr1 is entered: "+PrimaryRef_PhoneNbr3);
				
				// Reference Details
					
					
					driver.findElement(By.name("customerBean.nameDispSummary")).sendKeys(Ref_LastName);
					test.log(LogStatus.PASS, "RLast Name is entered: "+Ref_LastName);
					driver.findElement(By.name("customerBean.referenceFirstNameSummary")).sendKeys(Ref_FirstName);
					test.log(LogStatus.PASS, "RFirst Name is entered: "+Ref_FirstName);
					
					//driver.findElement(By.name("customerBean.contName")).sendKeys(Ref_LastName);
					//test.log(LogStatus.PASS, "PRLast Name is entered: "+Ref_LastName);
					 driver.findElement(By.name("customerBean.relationDispSummary")).sendKeys(Ref_Relationship);
					 test.log(LogStatus.PASS, "reference relation is entered: "+Ref_Relationship);
					 driver.findElement(By.name("refPhoneNbr1")).sendKeys(Ref_PhoneNbr1);
					 test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered: "+Ref_PhoneNbr1);
					 driver.findElement(By.name("refPhoneNbr2")).sendKeys(Ref_PhoneNbr2);
					 test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered: "+Ref_PhoneNbr2);
					 driver.findElement(By.name("refPhoneNbr3")).sendKeys(Ref_PhoneNbr3);
					 test.log(LogStatus.PASS, "Reference Phone Nbr1 is entered: "+Ref_PhoneNbr3);
					 driver.findElement(By.name("bt_Reference")).click();			
					 test.log(LogStatus.PASS, "Clicked on ADD Reference");
				*/
						 driver.findElement(By.name("customerBean.bankrupty")).sendKeys(Bankruptcy);
						 test.log(LogStatus.PASS, "Bankrupty is selected as: "+Bankruptcy);
						driver.findElement(By.name("SLoan")).click();							
						test.log(LogStatus.PASS, "Clicked on Save&Loan");
						Thread.sleep(5000);
					
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
									
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 
					
						 	if(driver.findElement(By.id("ShareScreenBtn")).isEnabled())
						 	{
							test.log(LogStatus.PASS, "Borrower is Registered Successfully with SSN as " +SSN);						
						 	}
							else
							{
							test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " +SSN);
							}
						 	
							
						 }
							//driver.switchTo().defaultContent();
							//driver.switchTo().frame("topFrame");
							//driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
							//*[@id="icons"]/li[7]/a
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
			  
						//driver.switchTo().alert().accept();
						
					/*	WebDriver driver = new FirefoxDriver();
						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("document.getElementById('elementid').focus();");*/
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
	public void NewLoan(String SSN,String FileName) throws Exception{
			
			
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL/"+FileName);     	
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
								driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
							}
							if(StoreID.equals("4353"))
							{
								//*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input
								
							driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/input")).click();
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
			

public void AgeStore(String SSN,String FileName,int days) throws Exception
			{

				Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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

						

						if(ProductID.equals("PDL"))

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

						if(ProductID.equals("PDL"))

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

						String DueDate=null;
						//String LoanAmount = null;
						
						//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
						DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
						//LoanAmount = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[5]/td/span[2]")).getText();
						test.log(LogStatus.PASS, "Capture DueDate"+DueDate);

						//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

						System.out.print(DueDate);

						driver.close();

						driver = new InternetExplorerDriver();

						driver.get(AdminURL);

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

						//String date = DDueDate[1];

						Date DDueDateminus1 = df.parse(DueDate);

						Calendar cal = Calendar.getInstance();

						cal.setTime(DDueDateminus1);

						cal.add(Calendar.DATE,days);

						Date DDueDate1= cal.getTime();

						DueDate =df.format(DDueDate1);

						String DueDate0[] =DueDate.split("/");

						String DueDate1 = DueDate0[0];

						String DueDate2 = DueDate0[1];

						String DueDate3 = DueDate0[2];

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
						
							test.log(LogStatus.PASS, "Process Date not updated successfully");

						}



					}
				}
			}
public void PrenoteClear_BeforeDuedate(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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
			test.log(LogStatus.PASS, "transactionList Selected as History");
			if(ProductID.equals("PDL"))
			{
				driver.findElement(By.id("go_Button")).click(); 
				test.log(LogStatus.PASS, "Click on GO Button");
				
			}

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			String DueDate=null;

			//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();

			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
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
		/*	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Daily Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
			Thread.sleep(5000);
			driver.findElement(By.linkText("PDL Pre Note Deposit Process")).click();
			test.log(LogStatus.PASS, "Clicked on PDL Pre Note Deposit Process");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS); */



			String DDueDate[] =DueDate.split("/");
			//String date = DDueDate[1];
			Date DDueDateminus1 = df.parse(DueDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(DDueDateminus1);
			cal.add(Calendar.DATE, -1);
			Date DDueDate1= cal.getTime();
			DueDate =df.format(DDueDate1);
			String DueDate0[] =DueDate.split("/");
			String DueDate1 = DueDate0[0];
			String DueDate2 = DueDate0[1];
			String DueDate3 = DueDate0[2];



			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

			

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
				if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[1]/tbody/tr/td")).isDisplayed())
				{
					     test.log(LogStatus.PASS, "OK Button as Enabled");
					test.log(LogStatus.PASS, "EOD Batch Process completed Successfully.");
					
				}
				else
				{
					test.log(LogStatus.FAIL, "EOD Batch Process not completed Successfully.");
				}
				//driver.close();
			}

		}
	}

public void DrawerDeassign(String SSN,String FileName) throws Exception{

	Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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


	Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
			String SSN1 = SSN.substring(0,3);
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
			test.log(LogStatus.PASS, "Clicked on Start EOD Processing");
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
			String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");
			//String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

			driver.findElement(By.name("requestBean.amount")).sendKeys(SafeOverShortAmount);

			/// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/table/tbody/tr[7]/td[3]

			// driver.findElement(By.name("requestBean.amount")).sendKeys("SafeOverShortAmount");
			test.log(LogStatus.PASS,"Enter the Balance ::"+SafeOverShortAmount);

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
			Thread.sleep(2000);
			driver.findElement(By.name("Next"));
			// Next
			test.log(LogStatus.PASS, "Clicked on Next Button in Deposit Screen");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}	
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();
			test.log(LogStatus.PASS, "Clicked on Next Button in End of the Day Deposit screen");
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

		public void LoanPrePayment(String SSN,String FileName) throws Exception{

				

				Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);

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

				
				this.Login(UserName,Password,StoreId);
				
			/*	CSRLoginpage login = new CSRLoginpage();

				login.Login(UserName, Password, StoreId, driver, AppURL, test);
*/
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

				

				if(ProductID.equals("PDL"))

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

				driver.findElement(By.name("transactionList")).sendKeys(TxnType);
				
				test.log(LogStatus.PASS, "TxnType is selected as "+TxnType);
				
			//driver.findElement(By.name("transactionList")).sendKeys("Partial Payment");

				if(ProductID.equals("PDL"))

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

				if(ProductID.equals("PDL"))

				{

				// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

				//String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");

				// System.out.println(Pmt);

				driver.findElement(By.name("transactionDataBean.paymentAmt")).clear();

				driver.findElement(By.name("transactionDataBean.paymentAmt")).sendKeys("20");

				test.log(LogStatus.PASS, "Payment Amt is entered as 10");

				driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);

				test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);

				driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys("20");

				test.log(LogStatus.PASS, "Tender Amt is entered as 10");

				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);

				driver.findElement(By.name("finish")).click();

				test.log(LogStatus.PASS, "Password is selected as "+Password);

				test.log(LogStatus.PASS, "Clicked on Finish Payment button ");

				Thread.sleep(1000);

				try {

				Alert alert = driver.switchTo().alert();

				alert.accept();

				//if alert present, accept and move on.

				}

				catch (NoAlertPresentException e) {

				//do what you normally would if you didn't have the alert.

				}

			
				
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

				// /html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]

				if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).isDisplayed())

				{

				test.log(LogStatus.PASS, "Partial Payment Completed Successfully ");

				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/p/input[2]")).click();

				}

				else

				{

				test.log(LogStatus.FAIL, "Partial Payment not Completed Successfully ");

				}

				

				}

				}

				}

				}
		
		public void Void_PrePayment(String SSN,String FileName) throws Exception{
			
			
			Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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

			
			this.Login(UserName,Password,StoreId);
			
		/*	CSRLoginpage login = new CSRLoginpage();

			login.Login(UserName, Password, StoreId, driver, AppURL, test);
*/
			driver.switchTo().defaultContent();
			
			Thread.sleep(3000);

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

			

			if(ProductID.equals("PDL"))

			{

			///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]

			driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
			                        //     /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]
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

			driver.findElement(By.name("transactionList")).sendKeys("void");
			
			test.log(LogStatus.PASS, "VOID is selected as ");
			
			driver.findElement(By.id("go_Button")).click();
			
		//driver.findElement(By.name("transactionList")).sendKeys("Partial Payment");
								 if(ProductID.equals("PDL"))
								 {
									// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
									
									 //String Pmt= driver.findElement(By.name("htmlPayAmt")).getAttribute("value");						
									// System.out.println(Pmt);
									
									 driver.findElement(By.name("transactionDataBean.disbursementType")).sendKeys(TenderType);
									 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);
									 //Thread.sleep(3000);
									// Robot robot = new Robot();
									//Thread.sleep(2000);
									//robot.keyPress(KeyEvent.VK_F11);
									 driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
									 test.log(LogStatus.PASS, "PIN# is entered as"+Password);
									 driver.findElement(By.name("Submit22")).click();
									 
									 //test.log(LogStatus.PASS, "Password is selected as "+Password);																					
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
					                      //  WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input"));

					                      //  Actions action = new Actions(driver);

					                        //action.moveToElement(element);

					                        //action.sendKeys(Keys.PAGE_DOWN).build().perform();

					                       // action.sendKeys(Keys.PAGE_DOWN).build().perform();

					                      //  action.sendKeys(Keys.PAGE_DOWN).build().perform();

					                        Thread.sleep(2000); 
					                                              //*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[1]
										 if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[1]")).isDisplayed())
											{
											 test.log(LogStatus.PASS, "Void Pre-Payment Completed Successfully");
												driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/p[2]/input[1]")).click();
												//robot.keyPress(KeyEvent.VK_F11);
											}
										 else
											{
												test.log(LogStatus.FAIL, "Void Partial Payment not Completed Successfully ");
											}
									 
							    	
								 }
							
					}
					
				}
			}
		public void NACHADeposit_EODProcessing(String SSN,String FileName,int Days) throws Exception{



			Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);

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

			String SSN3 = SSN.substring(5,9);/*
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);*/
			String StoreID = TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);

			CSRLoginpage login = new CSRLoginpage();

			login.Login(UserName, Password, StoreId, driver, AppURL, test);
			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);	
			//String AppURL = TestData.getCellData(sheetName,"AppURL",row);
			appUrl = AppURL;

			/*String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);*/
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
			if(ProductID.equals("PDL"))
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
			String DueDate=null;

			                                       //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			//DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
			//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();		 
			System.out.print(DueDate);	

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
			this.Login(UserName,Password,StoreID);
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
			//String date = DDueDate[1];
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
				
		public void StoreInfo(String SSN,String FileName) throws Exception
		{
			 
			Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);  		
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
		
		public void CustomerEodS_Recoredtatus(String SSN,String FileName) throws Exception
		{

		Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


				if(ProductID.equals("PDL"))
				{
					
					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
					                          //   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
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
			/*	if(ProductID.equals("LOC"))
				{*/
					//*[@id="go_Button"]
					driver.findElement(By.xpath("//*[@id='go_Button']")).click();
					//driver.findElement(By.name("button")).click(); 
				//}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStaus=null;
			//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
				CheckStaus = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[3]/td[5]")).getText();
				//CheckStaus1 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();
				if((CheckStaus).contains("Deposit"))
				{
					test.log(LogStatus.PASS,"CustomerEOD Record is  Displayed as::"+CheckStaus);
				}
				else
				{
					test.log(LogStatus.PASS,"CustomerEOD Record is not Displayed.");
				}

			}
		}
		}
		public void Safeassign(String SSN,String FileName) throws Exception{

			Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
		public void Drawerassign(String SSN,String FileName) throws Exception{


			Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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
							Thread.sleep(3000);
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
		public void PrenoteDeposit_6DaysBeforeDuedate(String SSN,String FileName,int Days) throws Exception
		{

			Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);  			int lastrow=TestData.getLastRow("NewLoan");
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
					String SSN1 = SSN.substring(0,3);
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
					 test.log(LogStatus.PASS, "Transaction List  Selected is: History ");
					if(ProductID.equals("PDL"))
					{
						driver.findElement(By.id("go_Button")).click(); 
						test.log(LogStatus.PASS, "Click on GO Button");
					}

					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					String DueDate=null;
					
					                                       //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
					DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
					
					test.log(LogStatus.PASS, "Capture DueDate:" +DueDate);
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
					driver.findElement(By.linkText("Daily Jobs")).click();
					test.log(LogStatus.PASS, "Clicked on Daily Jobs");
					Thread.sleep(5000);
					driver.findElement(By.linkText("PDL Pre Note Deposit Process")).click();
					test.log(LogStatus.PASS, "Clicked on PDL Pre Note Deposit Process");
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					String DDueDate[] =DueDate.split("/");
					//String date = DDueDate[1];
					Date DDueDateminus1 = df.parse(DueDate);
					Calendar cal = Calendar.getInstance();
					 cal.setTime(DDueDateminus1);
					 cal.add(Calendar.DATE, -7);
					 Date DDueDate1= cal.getTime();
					 DueDate =df.format(DDueDate1);
				    String DueDate0[] =DueDate.split("/");
					String DueDate1 = DueDate0[0];
					String DueDate2 = DueDate0[1];
					String DueDate3 = DueDate0[2];
   				    driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.name("requestBean.locNbrCsr")).click();
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
					driver.findElement(By.name("requestBean.locNbrCsr")).sendKeys(StoreID);
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
						test.log(LogStatus.PASS, "OK Button is Enabled");
						
					    driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
					    test.log(LogStatus.PASS, "PDL Pre Note Deposit Process updated successfully");
					   
					    
					}
					else
					{
						test.log(LogStatus.FAIL, "PDL Pre Note Deposit Process not updated successfully.");
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
			public void ACH_PrePayment(String SSN,String FileName) throws Exception
			{

			Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


					/*if(ProductID.equals("PDL"))
					{*/
						
						Thread.sleep(3000);
											//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
						driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
					/*	                          //   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
					}*/
					//  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					driver.findElement(By.name("transactionList")).sendKeys("ACH Pre-Payment");
				/*	if(ProductID.equals("LOC"))
					{*/
						//*[@id="go_Button"]
						driver.findElement(By.xpath("//*[@id='go_Button']")).click();
						test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
						//driver.findElement(By.name("button")).click(); 
					//}
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
					
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
					test.log(LogStatus.PASS, "TenderType Select as ::Cash");
					
					String PmtAmt = driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");
					
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(PmtAmt);
					test.log(LogStatus.PASS, "TenderAmount Entered is::"+PmtAmt);
					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Passwor Entered as::"+Password);
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Clicked on Finish ACH PrePayment Button");
					
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
					
					if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).isDisplayed())
					{
					driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).click();
					test.log(LogStatus.PASS, "Clicked on Confirmation Yes Button");
					}		
					
					//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

					//String CheckStaus=null;
			/*	//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
					CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
					//CheckStaus1 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();
					if((CheckStaus).contains("Deposit"))
					{
						test.log(LogStatus.PASS,"CheckStatus is  Displayed as::"+CheckStaus);
					}
					else
					{
						test.log(LogStatus.PASS,"CustomerEOD Record is not Displayed.");
					}*/

				}
			}
			}
			public void ACH_voidPrePayment(String SSN,String FileName) throws Exception
			{

			Excel TestData = new Excel("F:/QC_Workspace/AA_Automation/TestData/PDL/"+FileName);	
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


					/*if(ProductID.equals("PDL"))
					{*/
						
						Thread.sleep(3000);
											//	/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
						driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
					/*	                          //   /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]    
					}*/
					//  driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Click on GO Button");
					for( String winHandle1 : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle1);
					}			
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					driver.findElement(By.name("transactionList")).sendKeys("ACH Pre-Payment");
				/*	if(ProductID.equals("LOC"))
					{*/
						//*[@id="go_Button"]
						driver.findElement(By.xpath("//*[@id='go_Button']")).click();
						test.log(LogStatus.PASS, "Click on Go for TRANSACTION Selection Button");
						//driver.findElement(By.name("button")).click(); 
					//}
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
					
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys("Cash");
					test.log(LogStatus.PASS, "TenderType Select as ::Cash");
					
					String PmtAmt = driver.findElement(By.name("transactionDataBean.paymentAmt")).getAttribute("value");
					
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(PmtAmt);
					test.log(LogStatus.PASS, "TenderAmount Entered is::"+PmtAmt);
					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Passwor Entered as::"+Password);
					driver.findElement(By.name("Submit22")).click();
					test.log(LogStatus.PASS, "Clicked on Finish ACH PrePayment Button");
					
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
					
					if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).isDisplayed())
					{
					driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]")).click();
					test.log(LogStatus.PASS, "Clicked on Confirmation Yes Button");
					}		
					
					//*[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/p[2]/input[1]      Confirm Yes button

					//String CheckStaus=null;
			/*	//	CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
					CheckStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
					//CheckStaus1 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[3]/tbody/tr[4]/td[5]")).getText();
					if((CheckStaus).contains("Deposit"))
					{
						test.log(LogStatus.PASS,"CheckStatus is  Displayed as::"+CheckStaus);
					}
					else
					{
						test.log(LogStatus.PASS,"CustomerEOD Record is not Displayed.");
					}*/

				}
			}
			}
			@Test (priority=0)
			
			 public void Prenotedeposit_Verification() throws Exception {
			
				// Start test. Mention test script name
				String FileName= "AA_BorrowerRegistration_NewLoan_Txn_Testdata.xls";
				Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL/"+FileName);   
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
				       test = reports.startTest("AEA_Newloan_Prenotedeposit_Verification_Txn_"+Header, "AEA_Newloan_Prenotedeposit_Verification_Txn");
				        appUrl = AppURL;
				        this.Login(UserName,Password,StoreId);
				        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				        Reg.RegistrationPage_NewLoan_PDL(driver, test, AppURL, SSN, FileName);
				        this.NewLoan(SSN, FileName);
				        this.AgeStore(SSN, FileName, -7);
				        this.PrenoteDeposit_6DaysBeforeDuedate(SSN, FileName, -7);
				        this.AgeStore(SSN, FileName, 0);
				        this.PrenoteClear_BeforeDuedate(SSN, FileName,0);
				        this.DrawerDeassign(SSN, FileName);
				        this.StatementGeneration_EODProcessing(SSN, FileName);
				        this.StoreInfo(SSN, FileName);
				        this.Safeassign(SSN, FileName);
				        this.Drawerassign(SSN, FileName);
				        this.AgeStore(SSN, FileName, -7);
				        this.PrenoteDeposit_6DaysBeforeDuedate(SSN, FileName, -7);
				        
		        
				        //WebDriverWait wait = new WebDriverWait(driver, 10);		        		        		        	       
				        //wait(100);
				       // this.RegistrationPage(SSN);
				}
				}
				//this.Login("CSR353","1234","353");
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
	}
			
			
			/*
			driver.switchTo().defaultContent();
			driver.switchTo().frame("bottom");
			 String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
			 String Busdate[]=BusinessDt.split(":");
			 String date = Busdate[1];
			
			 Date d1 = df.parse(date);
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(d1);
			 cal.add(Calendar.DATE, -20);*/
			//transactionDataBean.disbursementType ..DisbType
			//transactionDataBean.password ..Password
			//Submit22 ..FinishVoid BuyBack
			//checkyes .. confirm yes button
			//checkno  ..confirm no

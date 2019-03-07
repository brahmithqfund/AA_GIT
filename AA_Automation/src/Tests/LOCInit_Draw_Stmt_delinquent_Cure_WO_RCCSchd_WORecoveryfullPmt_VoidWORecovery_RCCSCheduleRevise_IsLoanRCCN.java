package Tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class LOCInit_Draw_Stmt_delinquent_Cure_WO_RCCSchd_WORecoveryfullPmt_VoidWORecovery_RCCSCheduleRevise_IsLoanRCCN {

	public WebDriverWait wait;	
	WebDriver driver;
	String appUrl;

	static ExtentReports reports;
	ExtentTest test;

	@BeforeClass
	public synchronized void initialize() {
		// Create an instance of ExtentsReports class and pass report storage
		// path as a parameter
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		//Date D = new Date();
				
		String filename="AA_LOC_RegressionScenarios_Scenario.No_294_"+timestamp+".html";
		//System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/AA_LOC_RegressionScenarios_/AA_LOC_RegressionScenarios_Scenario.No_294_/"+filename, true);
		//reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/CriticalScenarios.html", true);
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



	
	public void RegistrationPage(String SSN,String FileName) throws Exception{
		
		
		Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/LOC/"+FileName);
		
		int lastrow=TestData.getLastRow("Borrower_Registration");
		
		String sheetName="Borrower_Registration";		
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
	        String ProductType=TestData.getCellData(sheetName,"ProductType", row);
	        String StateID = TestData.getCellData(sheetName,"StateID",row);
	        String Header = StateID+ "_" + ProductID;	      
	        test = reports.startTest("BorrowerRegistration_"+Header, "Register a Borrower");	          
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
	      // String ProductType=TestData.getCellData(sheetName,"Product Type",row);
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
			        //this.Login(UserName, Password, StoreId);
			        
			      /*  CSRLoginpage csr = new CSRLoginpage();
			        csr.Login(UserName, Password, StoreId, driver, AppURL, test);*/
			       // csr.Login(UserName, Password, StoreId, driver, AppURL,test);			        
			   //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			        Thread.sleep(8000);
		     WebDriverWait wait = new WebDriverWait(driver, 100);	
			//driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		    // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("topFrame")));
		    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mainFrame")));
		   driver.switchTo().frame("topFrame");	
		 //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'NetSpend')]")));
		   driver.findElement(By.xpath("//*[@id='900000']/a")).click();
	       //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li[id='960000']")));
	      //driver.findElement(By.cssSelector("li[id='900000']")).click();	
	      // driver.findElement(By.cssSelector("li[id='900000']")).sendKeys(Keys.ENTER);
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
			driver.findElement(By.name("customerBean.mailingAddressLn1")).sendKeys(AddressLine);
			test.log(LogStatus.PASS, "AddressLine is entered: "+AddressLine);
			driver.findElement(By.name("customerBean.mailingCity")).sendKeys(City);
			test.log(LogStatus.PASS, "City is entered: "+City);
			driver.findElement(By.name("customerBean.mailingStateCd")).sendKeys(State);
			test.log(LogStatus.PASS, "State is entered: "+State);
			driver.findElement(By.name("customerBean.mailingPostalCd")).sendKeys(ZipCode);
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
					Thread.sleep(10000);
				
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
					 
				        Thread.sleep(5000);

				        if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
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
		
	
	
public void NewLoanDraw(String SSN,String FileName) throws Exception{
    
    
    Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);         
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
                    //     Selection of Product based on the Name provided in Test Data
                    
                    
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
                                    if(StoreID.equals("4324"))
                                    {
                                    	
                                    	                     //*[@id="riskViewBdy"]/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input
                               driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
                                    	//driver.findElement(By.name("prodSel")).click();
                                   }
                                    else
                                    {
                                  
                                          driver.findElement(By.xpath("//*[@id='riskViewBdy']/table[3]/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[3]/td[2]/input")).click();
                                        //                              
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
                                    test.log(LogStatus.PASS, "      Chkg Acct Nbr(Last 4 Digits Only) is enterted as "+last4cheknum);                                       
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
                            //      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
                                            driver.findElement(By.name("loanAmt")).sendKeys("400"); 
                                            driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
                                            test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
                                            test.log(LogStatus.PASS, "Disb Amt is enterted as 400");
                                            driver.findElement(By.name("disbAmtFirst")).sendKeys("400");                                    
                                            test.log(LogStatus.PASS, "Disb Amt is enterted as 400");
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

WebDriverWait wait = new WebDriverWait(driver, 10000);

wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Transactions')]")));

driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();

test.log(LogStatus.PASS, "Clicked on Transactions");

driver.switchTo().defaultContent();

driver.switchTo().frame("mainFrame");

Thread.sleep(5000);

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));

driver.findElement(By.linkText("QA Jobs")).click();

test.log(LogStatus.PASS, "Clicked on QA Jobs");

Thread.sleep(5000);

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QA Jobs")));

driver.findElement(By.linkText("Process Date Change")).click();

test.log(LogStatus.PASS, "Clicked on Process Date Change");

Thread.sleep(2000);

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
			String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort")).getAttribute("value");

		//	String SafeOverShortAmount = driver.findElement(By.name("diffCashBal")).getAttribute("value");
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
/*public void Safeassign(String SSN,String FileName) throws Exception{
 	
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
			   	 try { 
		    		 Alert alert = driver.switchTo().alert();
		    		 alert.accept();
		    		 //if alert present, accept and move on.														

		    	 }
		    	 catch (NoAlertPresentException f) {
			    	 driver.switchTo().defaultContent();
	
		    		  if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[6]/td/input")).isDisplayed())
					     {
					    	 driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[6]/td/input")).click(); 
					    	 driver.switchTo().defaultContent();
					    	 driver.switchTo().frame("mainFrame");
					    	 driver.switchTo().frame("main");
					    	 driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					    	 driver.switchTo().defaultContent();
					    	 driver.switchTo().frame("mainFrame");
					    	 driver.switchTo().frame("main");
					     }

		    		 //do what you normally would if you didn't have the alert.

		    	 }
			   


			    	 driver.findElement(By.name("safeAssignRequestBean.empPwd")).sendKeys(Password);


			    	 driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");


			    	 driver.findElement(By.name("safeassign")).click();

			    	 try { 
			    		 Alert alert = driver.switchTo().alert();
			    		 alert.accept();
			    		 //if alert present, accept and move on.														

			    	 }
			    	 catch (NoAlertPresentException f) {
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
			

			     //do what you normally would if you didn't have the alert.

	


}
}
*/


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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA Jobs");
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

				/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));
				Actions actions1 = new Actions(driver);								        
				actions1.moveToElement(elements1).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);*/

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
    
    public void AgeStore_Defalut(String SSN,String FileName,int Days) throws Exception
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
   				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr[10]/td[4]")).getText();
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



   				String DDueDate[] =DueDate.split(" ");


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
						 test.log(LogStatus.PASS,"Payment status is Deliquent."+CheckStaus);
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

				Thread.sleep(3000);
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
   
    public void DefaultPaymentStatus(String SSN,String FileName) throws Exception
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

				test.log(LogStatus.PASS,"Payment status is Default."+CheckStaus);
				System.out.print(CheckStaus);	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.quit();//need to change to close
				 driver = new InternetExplorerDriver();

			}
		}
	}
    public void CustomerDefault(String SSN,String FileName) throws Exception
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


    				driver.findElement(By.xpath("//input[@type='button' and @value='Go']")).click();
    				                            // /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
    				                             
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
    			cal.add(Calendar.DATE, 0);
    			Date DDueDateminus1= cal.getTime();
    			// String DueDateminus1 =df.format(DDueDateminus1);
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
    			test.log(LogStatus.PASS, "Clicked on QA jobs");
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


public void Check_RCCSchd_WO(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new
			Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
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

			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName =TestData.getCellData(sheetName,"UserName",row);
			String Password =TestData.getCellData(sheetName,"Password",row);
			String StoreID =TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
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

//  <input type="button" value="RCC Schedule" class="sortbuttons" onclick="rccSchedule()">
			String val = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).getAttribute("value");
			///html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
		//	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//if (driver.findElement(By.xpath("//input[@type='button' and @value='RCC Schedule']")).isDisplayed())
			//if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
			if(val.contains("RCC"))
			{
				test.log(LogStatus.PASS, "RCC Schedule Generated");
				
			}
			else 
			{
		    // this.EditBorrower_WO(SSN3, FileName);
				  String TxnType=TestData.getCellData(sheetName,"TxnType",row);

				    String TenderType = TestData.getCellData(sheetName,"TenderType",row);


				    String MonthlyPayDay=TestData.getCellData(sheetName,"MonthlyPayDay",row);

				    String Income_PayFrequency=TestData.getCellData(sheetName,"Income_PayFrequency",row);

				    String SemiMonOthFirstDay=TestData.getCellData(sheetName,"SemiMonOthFirstDay",row);

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

				    String NextPayday =null;

				    if(Income_PayFrequency.equals("Bi-Weekly"))

				    {

				            // //*[@id="biWk_second"] //*[@id="biWk_second"]/text()
				    	                                       //*[@id="biWk_second"]
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

				    cal.add(Calendar.DATE, Days);

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
						List<WebElement> rows =driver.findElements(By.tagName("tr"));
						int ScdCnt = rows.size();
						test.log(LogStatus.PASS, "Rows count is"+ScdCnt);


						for(int j=2;j<=ScdCnt-1;j++)
						{
							//String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+j+"]")).getText();
							int k =j+1;
						//	String transactino_value1=driver.findElement(By.xpath("//select[@name='transactionList']/option["+k+"]")).getText();

							dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+j+"]/td[3]")).getText();
							dt1 = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+k+"]/td[3]")).getText();

							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);
							test.log(LogStatus.PASS, "date is"+dt1);
							System.out.println(dt1);
							String DDe1[]=dt.split(" ");

							String DDe2[]=dt1.split(" ");
						//	DateFormat df=new SimpleDateFormat("yyyy-mm-dd");
							String DueDate1 =DDe1[0];
							String DueDate2 = DDe2[0];
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

							Date firstDate = sdf.parse(DueDate1);
							Date secondDate = sdf.parse(DueDate2);
///////////////
							int DAY = (1000 * 60 * 60 * 24);
							long utc1 = Date.UTC(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate());
							  long utc2 = Date.UTC(secondDate.getFullYear(),secondDate.getMonth(), secondDate.getDate());

							  return Math.floor((utc2 - utc1) / DAY);
							//long diff1 = (long) Math.floor(diffInMillies / (1000 * 3600 * 24)); 
							
								int diffDays = secondDate.diff(firstDate, days);
							//long diffInMillies = secondDate.getDate() - firstDate.getDate();
							
							long diffInMillies =Math.negateExact(secondDate.getTime()-firstDate.getTime());
							double diff1 = Math.ceil(diffInMillies / (1000 * 3600 * 24));
							///////////////
							

						 
							//long diffInMillies =Math.abs (secondDate.getTime()-firstDate.getTime());
							//long diff1 = TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
							test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
							if (PayFrequency.equals("Bi-Weekly"))
							{

								test.log(LogStatus.PASS, "Date Difference for Bi-WeeklyShould be 14 day");

								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
								//Long i = Long.parseLong(String)
								String a = Double.toString(diff1);
								//String a =  Long.toString(diff1);
								if (a.equals("14"))
								{
									test.log(LogStatus.PASS, "Date Difference for Bi-Weekly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Bi-Weekly is not as Expected");
								}



							}
							if (PayFrequency.equals("Monthly"))
							{
								test.log(LogStatus.PASS, "Date Difference for Monthly Should be 30 day");
								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
								String a = Double.toString(diff1);
								//String a =  Long.toString(diff1);
								if (a.equals("30"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is not as Expected");
								}

							}
							if (PayFrequency.equals("Semi-Monthly"))
							{

								test.log(LogStatus.PASS, "Date Difference for Semi-Monthly Should be 15 days");
								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
								//String a =  Long.toString(long)
								if (diff1<=15)
								{
									test.log(LogStatus.PASS, "Date Difference for Semi-Monthly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Semi-Monthly is not as Expected");
								}
							}
							if (PayFrequency.equals("Weekly"))
							{

								test.log(LogStatus.PASS, "Date Difference for Weekly Should be 14 days");
								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
								//String a =  Long.toString(long)
								if (diff1<=14)
								{
									test.log(LogStatus.PASS, "Date Difference for Weekly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Weekly is not as Expected");
								}
							}
						}

					}

*/



				}

			}
	
}





public void RCC_Schedule_Status_verification(String SSN,String FileName,int i) throws Exception
{

    Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
    int lastrow=TestData.getLastRow("NewLoan");
    System.out.println("NewLoan "+lastrow);
    String sheetName="NewLoan";
    int Schedules_count =0;
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
                //test.log(LogStatus.PASS, "RCC Schedule Generated");

driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();


                for( String winHandle1 : driver.getWindowHandles())

                {
                    if(!(winHandle1.equals(Parent_Window)))
                    {
                        driver.switchTo().window(winHandle1);
                        Thread.sleep(6000);
                        System.out.println(driver.getTitle());
                        List<WebElement> options = driver.findElements(By.xpath("/html/body/form/table/tbody/tr"));
                        int schsize = options.size();
                        												
                          for(i=2; i<=schsize; i++)
                            {   
                        	  
                        	  String dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
                        	  String Schno = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[1]")).getText();
                        	  String insamt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[2]")).getText();
                        	  
                                test.log(LogStatus.PASS, "Schno:" +Schno);
                                test.log(LogStatus.PASS, "Installment Amount:" +insamt  );
                                test.log(LogStatus.PASS,  "date is:"+dt  );
                                Schedules_count = Schedules_count+1;
                                
                            }
                          test.log(LogStatus.PASS,  "Schedules Count:"+Schedules_count );
                            
                        } 
                    }
                }
                     
                        
                       /* dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
                      
                        
                        test.log(LogStatus.PASS, "date is"+dt1);
                        System.out.println(dt1);*/
                        //driver.quit();
                        
                        
                        

                        /*driver = new InternetExplorerDriver();
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
                        driver.close();
                        driver = new InternetExplorerDriver();

                    }





                }

            }




public void Check_RCCSchd(String SSN,String FileName) throws Exception
{

	Excel TestData = new
			Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);
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

			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName =TestData.getCellData(sheetName,"UserName",row);
			String Password =TestData.getCellData(sheetName,"Password",row);
			String StoreID =TestData.getCellData(sheetName,"StoreID",row);
			String AdminURL=TestData.getCellData(sheetName,"AdminURL",row);
			String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
			String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
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

			String val = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).getAttribute("value");
			///html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
		//	driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//if (driver.findElement(By.xpath("//input[@type='button' and @value='RCC Schedule']")).isDisplayed())
			//if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
			if(val.contains("RCC"))
			{
				test.log(LogStatus.PASS, "RCC Schedule Generated");
				
			}
			else 
			{
				test.log(LogStatus.PASS, "RCC Schedule not Generated");
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
						List<WebElement> rows =driver.findElements(By.tagName("tr"));
						int ScdCnt = rows.size();
						test.log(LogStatus.PASS, "Rows count is"+ScdCnt);


						for(int j=2;j<=ScdCnt-1;j++)
						{
							//String transactino_value=driver.findElement(By.xpath("//select[@name='transactionList']/option["+j+"]")).getText();
							int k =j+1;
						//	String transactino_value1=driver.findElement(By.xpath("//select[@name='transactionList']/option["+k+"]")).getText();

							dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+j+"]/td[3]")).getText();
							dt1 = driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+k+"]/td[3]")).getText();

							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);
							test.log(LogStatus.PASS, "date is"+dt1);
							System.out.println(dt1);
							String DDe1[]=dt.split(" ");

							String DDe2[]=dt1.split(" ");
						//	DateFormat df=new SimpleDateFormat("yyyy-mm-dd");
							String DueDate1 =DDe1[0];
							String DueDate2 = DDe2[0];
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

							Date firstDate = sdf.parse(DueDate1);
							Date secondDate = sdf.parse(DueDate2);
///////////////
							int DAY = (1000 * 60 * 60 * 24);
							long utc1 = Date.UTC(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate());
							  long utc2 = Date.UTC(secondDate.getFullYear(),secondDate.getMonth(), secondDate.getDate());

							  return Math.floor((utc2 - utc1) / DAY);
							//long diff1 = (long) Math.floor(diffInMillies / (1000 * 3600 * 24)); 
							
								int diffDays = secondDate.diff(firstDate, days);
							//long diffInMillies = secondDate.getDate() - firstDate.getDate();
							
							long diffInMillies =Math.negateExact(secondDate.getTime()-firstDate.getTime());
							double diff1 = Math.ceil(diffInMillies / (1000 * 3600 * 24));
							///////////////
							

						 
							//long diffInMillies =Math.abs (secondDate.getTime()-firstDate.getTime());
							//long diff1 = TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
							test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
							if (PayFrequency.equals("Bi-Weekly"))
							{

								test.log(LogStatus.PASS, "Date Difference for Bi-WeeklyShould be 14 day");

								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
								//Long i = Long.parseLong(String)
								String a = Double.toString(diff1);
								//String a =  Long.toString(diff1);
								if (a.equals("14"))
								{
									test.log(LogStatus.PASS, "Date Difference for Bi-Weekly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Bi-Weekly is not as Expected");
								}



							}
							if (PayFrequency.equals("Monthly"))
							{
								test.log(LogStatus.PASS, "Date Difference for Monthly Should be 30 day");
								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
								String a = Double.toString(diff1);
								//String a =  Long.toString(diff1);
								if (a.equals("30"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is not as Expected");
								}

							}
							if (PayFrequency.equals("Semi-Monthly"))
							{

								test.log(LogStatus.PASS, "Date Difference for Semi-Monthly Should be 15 days");
								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
								//String a =  Long.toString(long)
								if (diff1<=15)
								{
									test.log(LogStatus.PASS, "Date Difference for Semi-Monthly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Semi-Monthly is not as Expected");
								}
							}
							if (PayFrequency.equals("Weekly"))
							{

								test.log(LogStatus.PASS, "Date Difference for Weekly Should be 14 days");
								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency ::"+PayFrequency+"is ::"+diff1);
								//String a =  Long.toString(long)
								if (diff1<=14)
								{
									test.log(LogStatus.PASS, "Date Difference for Weekly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Weekly is not as Expected");
								}
							}
						}

					}

*/



				}

			}
	
}

   
    public void EditBorrower_WO(String SSN,String FileName) throws Exception

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

    cal.add(Calendar.DATE, -3);

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


    public void Bankrupt_Deceased(String SSN,String FileName) throws Exception
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
    	   driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Deceased");
    	    test.log(LogStatus.PASS, "select status as  Discharged"); 
    	 driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());			
    		test.log(LogStatus.PASS, "Bankrupt Filing Month is:: "+DueDate1);			
    		Thread.sleep(500);		    
    		driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());			
    		test.log(LogStatus.PASS, "Bankrupt Filing Day is:: "+DueDate2);			
    		Thread.sleep(500);			
    		driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());			
    		test.log(LogStatus.PASS, "Bankrupt Filing Year is:: "+DueDate3);			
    		
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
    		 test.log(LogStatus.PASS, "Status BNKPending is Saved");
    	 
    		 Thread.sleep(50000);
    	//	 /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
    		 Bankstatus = driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
    		 
    		 test.log(LogStatus.PASS,"<FONT color=green style=Arial> Customer got Bankrupted::+Bankstatus");
    		 
    			driver.close();

    			driver = new InternetExplorerDriver();
    			
    		}

    	}		 
    	 
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
			String CustStatus=null;
			CustStatus =driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[3]")).getText();
			test.log(LogStatus.PASS,"Customer status is ::"  +CustStatus);
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
				// /html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[3]
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[7]/td[11]/input[1]")).click();    
				
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[8]/td[11]/input[1]")).click();
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
				CheckStaus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]")).getText();
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
					if(StoreID.equals("4325"))
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


public void Deceased_Void(String SSN,String FileName) throws Exception
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
		

		driver = new InternetExplorerDriver();
		driver.get(AdminURL);
		test.log(LogStatus.INFO, "Admin portal is launched");
		

		

		
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
		 driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
  
     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 driver.findElement(By.name("menu")).sendKeys("Edit");
	 driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input")).click();
	 
		 

     driver.switchTo().defaultContent();
	 driver.switchTo().frame("mainFrame");
	 driver.switchTo().frame("main");
	 Thread.sleep(3000);
	 if( driver.findElement(By.name("loanCode")).isDisplayed())
	 {
	 driver.findElement(By.name("loanCode")).click();
	 test.log(LogStatus.PASS, "Selecting Check box for loan");
	 }
	 driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void");
	 test.log(LogStatus.PASS, "select status as Void");			
		
		
		
		driver.findElement(By.name("bt_AddBankruptcy")).click();			
		 test.log(LogStatus.PASS, " Clicked on Saved");
	 
		 Thread.sleep(5000);
	
       String ele= driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]")).getText();
       if(ele.contains("Void")){
  		 test.log(LogStatus.PASS, " Deceased void complted Sucessfully ");
    	   
       }
       else{
    	   test.log(LogStatus.PASS, " Deceased void not complted Sucessfully ");
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
			//	WebElement elements1 = driver.findElement(By.linkText("Daily Jobs"));    Temporary change
			WebElement elements1 = driver.findElement(By.linkText("QA Jobs"));
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
	

public void Writoff_Recovery(String SSN,String FileName) throws Exception
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
				String PaymentAMT= null;
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
					//driver.findElement(By.name("button")).click();
					///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
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
				driver.findElement(By.name("transactionList")).sendKeys("Write Off Recovery");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.name("button")).click(); 
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				//String PaymentAmount=null;
				
				PaymentAMT = driver.findElement(By.name("totalDue")).getAttribute("value");
				test.log(LogStatus.PASS, "Capture the Payment Value :"+PaymentAMT);

				driver.findElement(By.name("requestBean.tenderType")).sendKeys("Cash");
				test.log(LogStatus.PASS, "Select the Disb Type");
				
				driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(PaymentAMT);
				test.log(LogStatus.PASS, "Enter the Tender Amount");

				driver.findElement(By.name("password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Enter the Password");

				driver.findElement(By.name("Submit22")).click();
				test.log(LogStatus.PASS, "Click on the Finish Write off Recovery");
				
				 if(driver.findElement(By.name("Ok")).isDisplayed())
				 {
					 test.log(LogStatus.PASS, "Write off Recovery completed Sucessfully");
					 driver.findElement(By.name("Ok")).click();
				 }
						 
					 
					 else
					 {

				test.log(LogStatus.PASS, "Write off Recovery not completed Sucessfully");
					 }
			

			 
			}
			}

			}
		

public void WORecovery_Void(String SSN,String FileName) throws Exception
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

			//String PaymentAmount=null;

			driver.findElement(By.name("defPaymentRequestBeanRC.tenderType")).sendKeys("Cash");
			test.log(LogStatus.PASS, "Select the Tender Type:: Cash");

			driver.findElement(By.name("password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Enter the Password");

			driver.findElement(By.name("Submit22")).click();
			test.log(LogStatus.PASS, "Click on the Finish Void Write off Recovery Payment ");

			//test.log(LogStatus.PASS, "Void Write off Recovery Payment completed Sucessfully");
//Ok
		    if(driver.findElement(By.xpath("//input[@type='button' and @value='Ok']")).isDisplayed())
				{
			// /html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input[2]
		    	
		    	driver.findElement(By.xpath("//input[@type='button' and @value='Ok']")).click();
		    	test.log(LogStatus.PASS, "Void Write off Recovery Payment completed Sucessfully");
				}
		
		else
		{
			test.log(LogStatus.PASS, "Void Write off Recovery Payment Not completed Sucessfully");
		}
		}

		}
	}



    @Test (priority=0)

public void LOCInit_Draw_Stmt_delinquent_Cure_WO_RCCSchd_WORecoveryfullPmt_VoidWORecovery_RCCSCheduleRevise_IsLoanRCCN() throws Exception {

	// Start test. Mention test script name
	String FileName= "AA_LOCInit_Draw_Stmt_delinquent_Cure_WO_RCCSchd_WORecoveryfullPmt_VoidWORecovery_RCCSCheduleRevise_IsLoanRCCN_TestData.xls";
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
	        test = reports.startTest(Header+"_S.No:294"+"_"+PayFrequency+"_"+CollateralType, "LOC Init _ Draw _ Stat _ delinquent _ Cure _ WO _ WO _ RCC Schedule _ Full WO Recovery payment _ Void WO Recovery payment _RCC Schedules should be revised, is_ canceled flag should be updated to N ");
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
	        //this.NACHA(SSN, FileName, -1);
	        //this.ACH_Deposit(SSN, FileName, 0);			
	        //this.ACHReturnPosting(SSN, FileName);
	        this.DeliquentPaymentStatus(SSN, FileName);		
	        this.AgeStore(SSN, FileName, 10);
	        this.DrawerDeassign(SSN, FileName);
	        this.StatementGeneration_EODProcessing(SSN, FileName);
	        // this.EODProcessing(SSN, FileName);
	        this.StoreInfo(SSN, FileName);
	        this.Safeassign(SSN, FileName);
	        this.Drawerassign(SSN, FileName);
	        this.CurePaymentStatus(SSN, FileName);
	        this.CustomerDefault(SSN, FileName);
	        this.DefaultPaymentStatus(SSN, FileName);
	        this.DefaultPaymentStatus(SSN, FileName);
	        this.Default_WOProc(SSN, FileName,60);
	        this.WOPaymentStatus(SSN, FileName);

	        this.EditBorrower(SSN, FileName);
	        this.Check_RCCSchd_WO(SSN, FileName,-3);
	        this.Check_RCCSchd(SSN, FileName);
	        this.RCC_Schedule_Status_verification(SSN, FileName, 2);
	        this.Writoff_Recovery(SSN, FileName);
	        this.WORecovery_Void(SSN, FileName);
	        this.RCC_Schedule_Status_verification(SSN, FileName, 2);


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

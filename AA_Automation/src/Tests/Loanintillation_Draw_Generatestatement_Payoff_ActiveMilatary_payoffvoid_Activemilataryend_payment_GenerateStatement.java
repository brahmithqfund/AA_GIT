package Tests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import Utilities.ExtentReports.Excel;

public class Loanintillation_Draw_Generatestatement_Payoff_ActiveMilatary_payoffvoid_Activemilataryend_payment_GenerateStatement {

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
				
		String filename="AA_LOC_RegressionScenarios_Scenario.No_83_"+timestamp+".html";
		//System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/AA_LOC_RegressionScenarios_Scenario.No_83/"+filename, true);
		//reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/CriticalScenarios.html", true);
	}

	@BeforeTest
	public void setup() throws IOException {
	//ystem.setProperty("webdriver.ie.driver","E:/Ncp_Workspace/Selenium/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
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

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));

driver.findElement(By.linkText("Borrower")).click();

test.log(LogStatus.PASS, "Clicked on Borrower");

Thread.sleep(5000);

wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Borrower")));

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
				Thread.sleep(4000);
				//driver.get(appUrl);		
				// for(String winHandle : driver.getWindowHandles()){
				//	    driver.switchTo().window(winHandle);
					//	}
				//driver.manage().window().maximize();
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
					 driver.findElement(By.name("transactionList")).sendKeys("Payoff");
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
									 test.log(LogStatus.PASS, "Payoff Completed Successfully ");
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
   public void Void(String SSN,String FileName) throws Exception{
	
	
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
						
							// String Pmt= driver.findElement(By.name("currentBalance")).getAttribute("value");
							// name="requestBean.paymentAmt"
							 driver.findElement(By.name("requestBean.paymentAmt")).clear();
							driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("20");
							 driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
							 test.log(LogStatus.PASS, "Tender Type is Selected as "+TenderType);	
							driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("20");
							test.log(LogStatus.PASS, "Tender Amt is entered as :: 20");							
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


@Test (priority=0)

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

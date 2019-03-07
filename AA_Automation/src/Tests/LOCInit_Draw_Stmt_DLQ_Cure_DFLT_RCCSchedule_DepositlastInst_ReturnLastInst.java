package Tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.BorrowerRegistrationpage;
import Pages.CSRLoginpage;
import Tests.ExecuteScripts;
import Utilities.ExtentReports.Excel;

public class LOCInit_Draw_Stmt_DLQ_Cure_DFLT_RCCSchedule_DepositlastInst_ReturnLastInst {

	public WebDriverWait wait;	
	WebDriver driver;		
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	static ExtentReports reports;
	ExtentTest test;
	String SSN;
	String FileName;

	@BeforeClass 
	public void setup() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); //Allow OS to kill the process
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		driver = new InternetExplorerDriver();		
	}
	
	@BeforeClass
	public synchronized void initialize() {
		// Create an instance of ExtentsReports class and pass report storage
		// path as a parameter
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		//Date D = new Date();

		String filename="AA_LOC_RegressionScenarios_Scenario.No_270_"+timestamp+".html";
		//System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/AA_LOC_RegressionScenarios_/AA_LOC_RegressionScenarios_Scenario.No_270_/"+filename, true);
		//reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/LOC/CriticalScenarios.html", true);
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

		}

	}




	public void EditBorrower_Monthly(String SSN,String FileName,int Days) throws Exception

	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/LOC/"+FileName);

		int lastrow=TestData.getLastRow("NewLoan");

		System.out.println("NewLoan"+lastrow);
		String numberAsString=null;
		String year =null;
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

				// String NextPayday =null;
				driver.switchTo().defaultContent();
				driver.switchTo().frame("bottom");
				String  BusinessDt= driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
				String Busdate[]=BusinessDt.split(":");
				String date = Busdate[1];


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/* //                                         /html/body/form[1]/table/tbody/tr[2]/td/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td/table[2]/tbody[1]/tr[75]/td[3]/div[1]/input
    String a = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[2]/td/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td/table[2]/tbody[1]/tr[75]/td[3]/div[1]/input")).getAttribute("value");
// /html/body/form[1]/table/tbody/tr[2]/td/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td/table[2]/tbody[1]/tr[75]/td[3]/div[1]/input
    String b =driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[2]/td/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td/table[2]/tbody[1]/tr[75]/td[3]/div[3]/input")).getAttribute("value");
//  /html/body/form[1]/table/tbody/tr[2]/td/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td/table[2]/tbody[1]/tr[75]/td[3]/div[3]/input
    String c = driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[2]/td/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td/table[2]/tbody[1]/tr[75]/td[3]/div[5]/input")).getAttribute("value");
   // /html/body/form[1]/table/tbody/tr[2]/td/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td/table[2]/tbody[1]/tr[75]/td[3]/div[5]/input
    String joined1 = String.join("/", a, b, c);
				 */

				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

				Date d1 = df.parse(date);

				Calendar cal = Calendar.getInstance();

				cal.setTime(d1);

				cal.add(Calendar.DATE, 0);



				Date DDueDateminus1= cal.getTime();

				String DueDateminus1 =df.format(DDueDateminus1);

				String NextPayday0[] =DueDateminus1.split("/");

				String NextPayday1 = NextPayday0[0];

				String NextPayday2 = NextPayday0[1];

				String NextPayday3 = NextPayday0[2];



				if(NextPayday1.equals("12"))
				{
					numberAsString = "1";
					int yr = Integer.parseInt(NextPayday3);
					int  Reqyr = yr+1;
					year = Integer.toString(Reqyr);

				}
				else
				{
					int result = Integer.parseInt(NextPayday1);
					int  Reqnmb = result+1;
					numberAsString = Integer.toString(Reqnmb);
					year =NextPayday3;

				}
				String joined1 = String.join("/", numberAsString, "10", NextPayday3);

				Date d2 = df.parse(joined1);


				cal.setTime(d2);

				cal.add(Calendar.DATE, -10);



				Date ReqDt= cal.getTime();

				String minus =df.format(ReqDt);

				String minus0[] =minus.split("/");

				String minus01 = minus0[0];

				String minus02 = minus0[1];

				String minus03 = minus0[2];
				driver = new InternetExplorerDriver();	

				driver.get(AdminURL);

				test.log(LogStatus.INFO, "Admin portal is launched");

				driver.manage().window().maximize();

				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);




				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

				test.log(LogStatus.PASS, "Username is entered: admin");

				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

				test.log(LogStatus.PASS, "Password is entered: "+Password);

				//Click Login Button

				driver.findElement(By.name("login")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");

				Thread.sleep(8000);

				Thread.sleep(8000);




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


				driver.findElement(By.name("beginMonth")).sendKeys(minus01);

				test.log(LogStatus.PASS, "beginMonth is entered: "+minus01);

				driver.findElement(By.name("beginDay")).clear();

				driver.findElement(By.name("beginDay")).sendKeys(minus02);

				test.log(LogStatus.PASS, "beginDay is entered: "+minus02);

				driver.findElement(By.name("beginYear")).clear();

				driver.findElement(By.name("beginYear")).sendKeys(minus03);

				test.log(LogStatus.PASS, "beginYear is entered: "+minus03);

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

				driver.findElement(By.name("beginMonth")).sendKeys(minus01);

				test.log(LogStatus.PASS, "beginMonth is entered: "+minus01);

				driver.findElement(By.name("beginDay")).clear();

				driver.findElement(By.name("beginDay")).sendKeys(minus02);

				test.log(LogStatus.PASS, "beginDay is entered: "+minus02);

				driver.findElement(By.name("beginYear")).clear();

				driver.findElement(By.name("beginYear")).sendKeys(minus03);

				test.log(LogStatus.PASS, "beginYear is entered: "+minus03);



				// driver.findElement(By.linkText("iPads")).click();

				driver.findElement(By.name("submit")).click();

				test.log(LogStatus.PASS, "Clicked on submit button");

				Thread.sleep(6000);
				driver.close();
				driver = new InternetExplorerDriver();		




			}

		}

	}





	public void EditBorrower_ChangeFrequency_Monthly(String SSN,String FileName) throws Exception

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



				driver.findElement(By.name("customerBean.payFreqCdDisp")).sendKeys("Monthly");
				test.log(LogStatus.PASS, "Monthly PayFrequency Selected as :: Monthly");
				driver.findElement(By.name("customerBean.monthlyDate")).sendKeys("10th");

				test.log(LogStatus.PASS, "Monthly PayFrequency day is Selected as :: 10");
				driver.findElement(By.id("btnShowModalExit")).click();  
				test.log(LogStatus.PASS, "Click on save and exit");
				driver.close();
				driver = new InternetExplorerDriver();	


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
				driver.findElement(By.name("loanAmt")).sendKeys("120");	
				Thread.sleep(2000);
				driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Type is enterted as "+ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Amt is enterted as 120");
				driver.findElement(By.name("disbAmtFirst")).sendKeys("120");					
				test.log(LogStatus.PASS, "Disb Amt is enterted as 120");
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





	public void Check_RCCSchd_Dates_Monthly_1(String SSN,String FileName) throws Exception
	{

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


				///html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{
					test.log(LogStatus.PASS, "RCC Schedule Generated");


					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();






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
								/*							int DAY = (1000 * 60 * 60 * 24);
							long utc1 = Date.UTC(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate());
							  long utc2 = Date.UTC(secondDate.getFullYear(),secondDate.getMonth(), secondDate.getDate());

							  return Math.floor((utc2 - utc1) / DAY);*/
								//long diff1 = (long) Math.floor(diffInMillies / (1000 * 3600 * 24)); 

								/*	int diffDays = secondDate.diff(firstDate, days);*/
								//long diffInMillies = secondDate.getDate() - firstDate.getDate();

								long diffInMillies =Math.abs(secondDate.getTime()-firstDate.getTime());
								long diff1 = Math.abs(diffInMillies / (1000 * 3600 * 24));
								///////////////



								//long diffInMillies =Math.abs (secondDate.getTime()-firstDate.getTime());
								//long diff1 = TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
								test.log(LogStatus.PASS, "Difference in Schedule Payment Days for PayFrequency::"+PayFrequency +" is ::"+diff1);


								String a=String.valueOf(diff1);
								//String a = long.toString(diff1);
								//String a =  Long.toString(diff1);
								if (a.equals("30"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}

								else if (a.equals("28"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else if (a.equals("29"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else if (a.equals("31"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else if (a.equals("32"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else if (a.equals("33"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else if (a.equals("34"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else if (a.equals("35"))
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is as Expected");
								}
								else
								{
									test.log(LogStatus.PASS, "Date Difference for Monthly is not as Expected");
								}


							}

						}





					}

				}



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


				///html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{
					test.log(LogStatus.PASS, "RCC Schedule Generated");
				}
				else 
				{
					test.log(LogStatus.INFO, "RCC Schedule NOT Generated");
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


	public void Check_Record(String SSN,String FileName) throws Exception

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

				int n=driver.findElements(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr")).size();
				test.log(LogStatus.PASS, "Rows"+n);

				for(int i=1;i<=n;i++)
				{

					/*int m=driver.findElements(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr/td")).size();
				for(int j=m;j>=m;j--)
					 */
					String transactino_value=driver.findElement(By.xpath("//*[@id='revolvingCreditDetailsTable']/tbody/tr["+i+"]/td[5]")).getText();

					//  

					if(transactino_value.contains("Revolving Payments"))
					{
						test.log(LogStatus.PASS, "ACH is Deposited Sucessfully");
						test.log(LogStatus.PASS, "Value in cell ::"+transactino_value);

					}
					else
					{
						test.log(LogStatus.PASS, "  Value in cell :: "+transactino_value);

					}




				}
				test.log(LogStatus.PASS, "ACH is not Deposited Sucessfully" );
			}
		}
	}




	public void Check_RCCSchd_Dates(String SSN,String FileName) throws Exception
	{

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


				///html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
				if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
				{
					test.log(LogStatus.PASS, "RCC Schedule Generated");


					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();






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
								/*							int DAY = (1000 * 60 * 60 * 24);
							long utc1 = Date.UTC(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate());
							  long utc2 = Date.UTC(secondDate.getFullYear(),secondDate.getMonth(), secondDate.getDate());

							  return Math.floor((utc2 - utc1) / DAY);*/
								//long diff1 = (long) Math.floor(diffInMillies / (1000 * 3600 * 24)); 

								/*	int diffDays = secondDate.diff(firstDate, days);*/
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

						driver.close();



					}

				}



			}
		}
	}

	public void RCCStatus_1(String SSN,String FileName) throws Exception
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
				String LineStatus=null;
				LineStatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]")).getText();

				test.log(LogStatus.PASS,"Line Status is ::"+LineStatus);
				System.out.print(LineStatus);
				String RCCStatus=null;
				RCCStatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[2]/span[2]")).getText();

				test.log(LogStatus.PASS,"RCC Status is ::"+RCCStatus);
				System.out.print(RCCStatus);
				String RCCInEligibleReason=null;
				RCCInEligibleReason = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();

				test.log(LogStatus.PASS,"RCC InEligible Reason is ::"+RCCInEligibleReason);
				System.out.print(RCCInEligibleReason);	
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.quit();//need to change to close
				driver = new InternetExplorerDriver();

			}
		}
	}


	public void RCCRevoke(String SSN,String FileName) throws Exception
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
				driver.findElement(By.name("transactionList")).sendKeys("RCC Revoke");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.name("button")).click(); 
					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.														

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}

				driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='RCC Revoke')]")).click();
				//driver.findElement(By.name("bt_BankDetails")).click();

				test.log(LogStatus.PASS, "Click on RCCRevoke Button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				if(driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='OK')]")).isDisplayed())
				{
					driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='OK')]")).click();
					test.log(LogStatus.PASS, "Click on Confirmation OK Button");
				}
				/*				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();*/
				driver.quit();//need to change to close
				driver = new InternetExplorerDriver();

			}
		}
	}



	public void RCCAuthorization(String SSN,String FileName) throws Exception
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
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);

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
				driver.findElement(By.name("transactionList")).sendKeys("RCC Authorization");
				if(ProductID.equals("LOC"))
				{
					driver.findElement(By.name("button")).click(); 
					try { 
						Alert alert = driver.switchTo().alert();
						alert.accept();
						//if alert present, accept and move on.														

					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}

				driver.findElement(By.name("requestBean.abaCode")).sendKeys("111111118");

				driver.findElement(By.name("checkAbaNbrDisp")).sendKeys("111111118");
				test.log(LogStatus.PASS, "Confirm ABA Code is ::111111118");
				driver.findElement(By.name("requestBean.ckActNo")).sendKeys(ChkgAcctNbr);
				test.log(LogStatus.PASS, "Checking Account number  is ::"+ChkgAcctNbr);
				driver.findElement(By.name("checkAccountNbrDisp")).sendKeys(ChkgAcctNbr);
				test.log(LogStatus.PASS, "Confirm Checking Account number  is"+ChkgAcctNbr);
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password entered is ::" +Password);
				driver.findElement(By.name("bt_BankDetails")).click();
				//driver.findElement(By.name("bt_BankDetails")).click();

				test.log(LogStatus.PASS, "Click on RCCAuthorization Button");

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				if(driver.findElement(By.xpath("//input[(@type='button') and (@value='OK')]")).isDisplayed())
				{
					driver.findElement(By.xpath("//input[(@type='button') and (@value='OK')]")).click();
					test.log(LogStatus.PASS, "Click on Confirmation OK Button");
				}
				/*				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();*/
				driver.quit();//need to change to close
				driver = new InternetExplorerDriver();

			}
		}
	}


	public void WOStatus_Bankrupt(String SSN,String FileName) throws Exception
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



			}
		}
	}

	public void WORECStatus_Bankrupt(String SSN,String FileName) throws Exception
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
					test.log(LogStatus.PASS, "RCC Schedule Generated");

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

								test.log(LogStatus.PASS, "Schedule Inst No.::" +Schno+"Installment Amount::"+insamt+"Schedule date is::"+dt);
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



public void RCC_Return(String SSN,String FileName) throws Exception
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
		
		driver.findElement(By.linkText("RCC Return")).click();
		test.log(LogStatus.PASS, "Clicked on RCC Return");
		
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


			 List<WebElement> options = driver.findElements(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr"));
			 int RTNRecs = options.size();

			 for(int i=3; i<=RTNRecs; i++)
			 {   

				 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr["+i+"]/td[1]/input[2]")).click();
					test.log(LogStatus.PASS, "Customer Record CheckBox Selected");
		
				 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr["+i+"]/td[10]/select")).sendKeys("R01-Insufficient Funds");
				 test.log(LogStatus.PASS, "Return Reason Selected as ::  R01-Insufficient Funds");
			 }


			 driver.findElement(By.name("CmdReturnPosting")).click();
			 test.log(LogStatus.PASS, "Clicked on RCC Return Posting button");
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

	
	
	public void RCC_Schedule_1stInst_Agestore(String SSN,String FileName,int Days, int i) throws Exception
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
							dt =
									driver.findElement(By.xpath("/html/body/form/table/tbody/tr["+i+"]/td[3]")).getText();
							test.log(LogStatus.PASS, "date is"+dt);
							System.out.println(dt);


							driver.quit();

							driver = new InternetExplorerDriver();
							driver.get(AdminURL);
							test.log(LogStatus.PASS, "date in outside loop"+dt);




							String DDe[] =dt.split(" ");

							DateFormat  df=new
									SimpleDateFormat("yyyy-MM-dd");


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
							if(driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
							{
								test.log(LogStatus.PASS, "Process Date updated successfully");
								driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
							}
							else
							{
								test.log(LogStatus.FAIL, "Process Date not updated successfully.");
							}
							driver.close();
							driver = new InternetExplorerDriver();

						}

					}

				}

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
public void RCC_Schedule_1stInst(String SSN,String FileName) throws Exception
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
 
 
                //      /html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
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
                                                dt =    driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[3]")).getText();
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
                                                driver.findElement(By.linkText("Borrower")).click();
                                                test.log(LogStatus.PASS, "Clicked on Borrower");
                                                Thread.sleep(5000);
                                                driver.findElement(By.linkText("Process Date Change")).click();
                                                test.log(LogStatus.PASS, "Clicked on Process Date Change");
                                                driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
 
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
 
                                /*Thread.sleep(6000);
                                driver.switchTo().window(Parent_Window);
 
                                for( String winHandle1 : driver.getWindowHandles())
 
                                {
 
                                        driver.switchTo().window(winHandle1);
 
                                }   */
 
                                /*driver.switchTo().defaultContent();
 
                                driver.switchTo().frame("mainFrame");
 
                                driver.switchTo().frame("main");
                                */
                                        /*
                        driver.close();
 
                        driver = new InternetExplorerDriver();
                        driver.get(AdminURL);*/
 
 
 
/*
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
 
 
*/
 
                }
        }
}


public void RCC_1st_Installment_Deposit(String SSN,String FileName,int Days) throws Exception
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


//      /html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input
    if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed())
    {
            test.log(LogStatus.PASS, "RCC Schedule Generated");

            driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).click();


            for( String winHandle1 : driver.getWindowHandles())

            {
            	String Parent_Window = driver.getWindowHandle();
					if(!(winHandle1.equals(Parent_Window)))
                    {
                            driver.switchTo().window(winHandle1);
                            Thread.sleep(6000);
                            System.out.println(driver.getTitle());
                            String dt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[3]")).getText();
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
                            driver.findElement(By.linkText("Borrower")).click();
                            test.log(LogStatus.PASS, "Clicked on Borrower");
                            Thread.sleep(5000);
                            driver.findElement(By.linkText("Process Date Change")).click();
                            test.log(LogStatus.PASS, "Clicked on Process Date Change");
                            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

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
                    
                  
			driver.close();

			driver = new InternetExplorerDriver();
			driver.get(AdminURL);



			DateFormat  df1=new SimpleDateFormat("MM/dd/yyyy");		
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
			driver.findElement(By.linkText("Daily Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
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
							test.log(LogStatus.PASS, "RCCOrigination process updated successfully.");
							driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "RCCOrigination process not updated successfully");
						}

						  driver.close();
                          driver = new InternetExplorerDriver();


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

								/*
driver.findElement(By.name("storeCode")).click();
//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
     driver.findElement(By.name("storeCode")).sendKeys(StoreID);
     test.log(LogStatus.PASS, "Store number is entered: "+StoreID);
     Thread.sleep(5000);*/
								
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
								//Thread.sleep(5000);
								driver.findElement(By.name("submit")).click();
								test.log(LogStatus.PASS, "Clicked on submit button");
										driver.switchTo().defaultContent();
								driver.switchTo().frame("mainFrame");
								driver.switchTo().frame("main");
								test.log(LogStatus.PASS, "ACH Clear Process updated successfully.");
								/*if(
										driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).isDisplayed())
								{
									test.log(LogStatus.PASS, "ACH Clear Process updated successfully.");
											driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input")).click();
								}
								else
								{
									test.log(LogStatus.FAIL, "ACH Clear Process not updated successfully.");
								}*/
								driver.close();
						        driver = new InternetExplorerDriver();


					}
				}
			}
		}
	}
	
}



public void RCC_Revoke(String SSN,String FileName) throws Exception
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
		driver.findElement(By.name("transactionList")).sendKeys("RCC Revoke");
		test.log(LogStatus.PASS, "RCC Revoke Transaction is selected");
		if(ProductID.equals("LOC"))
		{
			driver.findElement(By.name("button")).click(); 
		}
		try { 
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    test.log(LogStatus.PASS, "Clicked on OK in Confirmation popup");
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
	    test.log(LogStatus.PASS, "Navigated to Customer RCC Authorization Details");
	    driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='RCC Revoke')]")).click();
		//driver.findElement(By.name("bt_BankDetails")).click(); 
		test.log(LogStatus.PASS, "Clicked on RCC Revoke");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		if(driver.findElement(By.name("bt_BankDetails")).isDisplayed())
		{
		 test.log(LogStatus.PASS, "RCC Revoke Transaction completed successfully.");
			driver.findElement(By.name("bt_BankDetails")).click(); 
			test.log(LogStatus.PASS, "Clicked on Ok button");
		}
		else
		{
			test.log(LogStatus.PASS, "RCC Revoke Transaction not completed successfully.");
		}
		/*Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
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
		test.log(LogStatus.PASS, "History Transaction is selected");
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
		test.log(LogStatus.PASS, "Navigated to History screen");
		if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]")).isDisplayed())
			//*[@id="revolvingCreditHistTable"]/tbody/tr[13]/td[2]/span[2]
		{
			test.log(LogStatus.PASS, "RCC Tracking Status is displayed in History screen");
			  WebElement htmltable=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]"));	
			  
				List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
				int count=0;							
				 count=driver.findElements(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]/tbody/tr[1]/td[1]/table/tbody/tr")).size();
				 
				 System.out.println("current row num "+count);
				 String Details=null;
				for(int rnum=0;rnum<count;rnum++)
				{
					System.out.println("current row num "+rnum);						
				List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));							
				
				System.out.println("columns Count "+columns.size());
					
				for(int cnum=0;cnum<columns.size();cnum++)//columns.size()
				{					
					 Details=columns.get(cnum).getText();
					 test.log(LogStatus.PASS, "RCC Tracking Status Details"+Details);
												
				}
				
				
				}
				
		}
		else
		{
			test.log(LogStatus.PASS, "RCC Tracking Status is not displayed in History screen");
		}*/
	}
}
}



/*

public void RCC_Revoke(String SSN,String FileName) throws Exception
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
		driver.findElement(By.name("transactionList")).sendKeys("RCC Revoke");
		test.log(LogStatus.PASS, "RCC Revoke Transaction is selected");
		if(ProductID.equals("LOC"))
		{
			driver.findElement(By.name("button")).click(); 
		}
		try { 
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		    test.log(LogStatus.PASS, "Clicked on OK in Confirmation popup");
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
	    test.log(LogStatus.PASS, "Navigated to Customer RCC Authorization Details");
	    driver.findElement(By.xpath("//input[(@name='bt_BankDetails') and (@value='RCC Revoke')]")).click();
		//driver.findElement(By.name("bt_BankDetails")).click(); 
		test.log(LogStatus.PASS, "Clicked on RCC Revoke");
		for( String winHandle1 : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle1);
		}			
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		driver.switchTo().frame("main");
		if(driver.findElement(By.name("bt_BankDetails")).isDisplayed())
		{
		 test.log(LogStatus.PASS, "RCC Revoke Transaction completed successfully.");
			driver.findElement(By.name("bt_BankDetails")).click(); 
			test.log(LogStatus.PASS, "Clicked on Ok button");
		}
		else
		{
			test.log(LogStatus.PASS, "RCC Revoke Transaction not completed successfully.");
		}
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.switchTo().defaultContent();
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
		test.log(LogStatus.PASS, "History Transaction is selected");
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
		test.log(LogStatus.PASS, "Navigated to History screen");
		if(driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]")).isDisplayed())
			//*[@id="revolvingCreditHistTable"]/tbody/tr[13]/td[2]/span[2]
		{
			test.log(LogStatus.PASS, "RCC Tracking Status is displayed in History screen");
			  WebElement htmltable=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]"));	
			  
				List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
				int count=0;							
				 count=driver.findElements(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[5]/tbody/tr[1]/td[1]/table/tbody/tr")).size();
				 
				 System.out.println("current row num "+count);
				 String Details=null;
				for(int rnum=0;rnum<count;rnum++)
				{
					System.out.println("current row num "+rnum);						
				List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));							
				
				System.out.println("columns Count "+columns.size());
					
				for(int cnum=0;cnum<columns.size();cnum++)//columns.size()
				{					
					 Details=columns.get(cnum).getText();
					 test.log(LogStatus.PASS, "RCC Tracking Status Details"+Details);
												
				}
				
				
				}
				
		}
		else
		{
			test.log(LogStatus.PASS, "RCC Tracking Status is not displayed in History screen");
		}
	}
}
}


*/




public void RCCSchduleStatus(String SSN,String FileName) throws Exception
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
public void Default_pymt_BeforeDueDate_4thInst(String SSN,String FileName,int Days, int i) throws Exception
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
driver.close();
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
            				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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
            			
            			//String PaymentAmount=null;
            			
            			//PaymentAmount = driver.findElement(By.name("requestBean.paymentAmt")).getAttribute("value");
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
public void RCCStatus_LoanStatus(String SSN,String FileName) throws Exception
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
			String RCCStatus=null;
			String LoanStatus = null;
			/*	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table[1]/tbody/tr/td/table[3]/tbody/tr/td[8]/input")).isDisplayed();

			test.log(LogStatus.PASS," RCC schdule is Displayed");*/
			//CheckStaus=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]")).getText();
			RCCStatus =driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[2]/span[2]")).getText();
			LoanStatus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]")).getText();
			//*[@id="revolvingCreditHistTable"]/tbody/tr[12]/td[2]/span[2]
			//*[@id="revolvingCreditHistTable"]/tbody/tr[13]/td[2]/span[2]
			test.log(LogStatus.PASS," RCC Status ::"+RCCStatus);
			test.log(LogStatus.PASS," Loan Status ::"+LoanStatus);
			// //*[@id="revolvingCreditHistTable"]/tbody/tr[14]/td[2]/span[2]
			//	System.out.print(CheckStaus);	
			//driver.close();

		}
	}
}
public void Default_pymt_BeforeDueDate_1stInst(String SSN,String FileName,int Days, int i) throws Exception
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
            				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
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
            			
            			//String PaymentAmount=null;
            			
            			//PaymentAmount = driver.findElement(By.name("requestBean.paymentAmt")).getAttribute("value");
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

	
	@Test (priority=0) //Run this scenario with store date need to check again for the date
	
	 public void LOCInit_Draw_Stmt_DLQ_Cure_DFLT_RCCSchedule_DepositlastInst_ReturnLastInst_() throws Exception {
	
		// Start test. Mention test script name
		String FileName= "AA_LOCInit_Draw_Stmt_DLQ_Cure_DFLT_RCCSchedule_DepositlastInst_ReturnLastInst_Txn_TestData.xls";		 
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
	            test = reports.startTest(Header+"_S.No:270"+"_"+PayFrequency+"_"+CollateralType,"LOC Init _ Draw _ Stat _ delinquent _ Cure _ Default   _ generate RCC Schedule  _  Deposit the last installment _ Then return the last installment ACH/RCC/NOC STORE/Online/PAM  ");
		        appUrl = AppURL;
		       
		        this.Login(UserName,Password,StoreId);			        
		        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				Reg.RegistrationPage_NewLoan(driver, test, AppURL, SSN, FileName);
		        this.NewLoan(SSN,FileName);  	       
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
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.CurePaymentStatus(SSN, FileName);
		        this.CustomerDefault(SSN, FileName);
		        this.DefaultPaymentStatus(SSN, FileName); 		        
	            this.EditBorrower(SSN, FileName); 
		        this.RCC_Schedule_1stInst_Agestore(SSN, FileName, -1, 2);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AfterDFLT_RCCOrigination(SSN, FileName, -1, 2);     
		        this.LOC_PreRCC_Deposit(SSN, FileName, 0, 2);		        
		        this.Admin_ACH_Clear(SSN, FileName, 10, 2);    
		        this.RCC_Schedule_1stInst_Agestore(SSN, FileName, -1, 3);
		        this.DrawerDeassign(SSN, FileName);
		        this.StatementGeneration_EODProcessing(SSN, FileName);
		        this.StoreInfo(SSN, FileName);
		        this.Safeassign(SSN, FileName);
		        this.Drawerassign(SSN, FileName);
		        this.AfterDFLT_RCCOrigination(SSN, FileName, -1, 3);     
		        this.LOC_PreRCC_Deposit(SSN, FileName, 0, 3);		        
		        this.Admin_ACH_Clear(SSN, FileName, 10, 3);
		        this.RCC_Return(SSN, FileName);
		        this.RCCStatus_LoanStatus(SSN, FileName);

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
		reports.flush();
	
	}			
	@AfterTest

	public void endReport(){
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

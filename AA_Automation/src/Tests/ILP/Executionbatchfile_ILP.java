package Tests.ILP;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
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
import org.openqa.selenium.Keys;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.*;
import Utilities.ExtentReports.Excel;
import bsh.This;
//import scala.collection.Iterator;
//import scala.collection.Set;

//import Pages.HomePage;
//import Pages.LoginPage;

public class Executionbatchfile_ILP {

	public WebDriverWait wait;
	WebDriver driver;
	String appUrl;
	static ExtentReports reports;
	ExtentTest test;

	@BeforeClass
	public synchronized void initialize() {
		// Create an instance of ExtentsReports class and pass report storage
		// path as a parameter
		// Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
		// Date D = new Date();

		String filename = "AA_ILP_RegressionScenarios_Scenario.No_25" + timestamp + ".html";
		// System.out.print(filename);
		reports = new ExtentReports(System.getProperty("user.dir")
				+ "/ExecutionReports/ILP/AA_ILP_RegressionScenarios_Scenario.No_25/" + filename, true);
	}

	@BeforeTest
	public void setup() throws IOException, InterruptedException {

		Runtime.getRuntime().exec("taskkill /T /F /IM IEDriverServer.exe");
		Thread.sleep(5000); // Allow OS to kill the process
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}

	// @BeforeTest
	/*
	 * public void setup() throws IOException {
	 * System.setProperty("webdriver.ie.driver",
	 * "E:/QC_Workspace/AA_Automation/IEDriverServer.exe"); driver = new
	 * InternetExplorerDriver();
	 * 
	 * //appUrl = "http://192.168.2.203/cc/demoIndex.do"; }
	 */

	// @BeforeTest
	public void Login(String username, String password, String storenumber) {

		// Launch URL
		driver.get(appUrl);
		test.log(LogStatus.INFO, "CSR Application is launched");
		driver.manage().window().maximize();
		String usenameId = "loginRequestBean.userId";
		String passwordId = "loginRequestBean.password";
		String StoreId = "loginRequestBean.locNbr";
		String Login = "login";

		// Enter Username(Email)
		// writeText(By.name(usenameId),username);
		driver.findElement(By.name(usenameId)).sendKeys(username);
		test.log(LogStatus.PASS, "Username is entered: " + username);

		// Enter Password
		// writeText(By.name(passwordId), password);
		driver.findElement(By.name(passwordId)).clear();
		driver.findElement(By.name(passwordId)).sendKeys(password);
		test.log(LogStatus.PASS, "Password is entered: " + password);

		// writeText(By.name(StoreId), storenumber);
		driver.findElement(By.name(StoreId)).sendKeys(storenumber);
		;
		test.log(LogStatus.PASS, "Storenumber is entered: " + storenumber);
		// Click Login Button
		driver.findElement(By.name(Login)).click();
		test.log(LogStatus.PASS, "Clicked on Submit button");
	}

	public void NewLoan(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String State = TestData.getCellData(sheetName, "StateID", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);

				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String ProductType = TestData.getCellData(sheetName, "ProductType", row);
				String ProductName = TestData.getCellData(sheetName, "ProductName", row);
				// String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType = TestData.getCellData(sheetName, "VehicleType", row);
				String NewVIN = TestData.getCellData(sheetName, "NewVIN", row);
				// System.out.println(Term);
				// String StoreId =
				// TestData.getCellData(sheetName,"StoreID",row);
				// String stateProduct=State+" "+ProductID;
				String stateProductType = State + " " + ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName, "ESign_LoanAmt", row);
				String ChkgAcctNbr = TestData.getCellData(sheetName, "ChkgAcctNbr", row);
				String ESign_DisbType = TestData.getCellData(sheetName, "ESign_DisbType", row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName, "ESign_CourtesyCallConsent", row);
				String AllowPromotion = TestData.getCellData(sheetName, "Allow Promotion", row);
				String CouponNbr = TestData.getCellData(sheetName, "CouponNbr", row);
				String ESign_Preference = TestData.getCellData(sheetName, "ESign_Preference", row);
				String ESign_Checks = TestData.getCellData(sheetName, "ESign_Checks", row);
				String ESign_Password = TestData.getCellData(sheetName, "ESign_Password", row);
				String ESign_CheckNbr = TestData.getCellData(sheetName, "ESign_CheckNbr", row);
				String last4cheknum = ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				String ErrorMsg = null;

				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				Thread.sleep(1000);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");

				ErrorMsg = driver.findElement(By.xpath("//*[@id='errMsg']/ul/li[2]")).getText();
				test.log(LogStatus.PASS, "capture the Error message::" + ErrorMsg);
				test.log(LogStatus.PASS, "After Loan Deceased New loan Origination should not be allowed::" + ErrorMsg);

			}
		}

	}

	public void NewLoan_ILP(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String State = TestData.getCellData(sheetName, "StateID", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);

				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String ProductType = TestData.getCellData(sheetName, "ProductType", row);
				String ProductName = TestData.getCellData(sheetName, "ProductName", row);
				// String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType = TestData.getCellData(sheetName, "VehicleType", row);
				String NewVIN = TestData.getCellData(sheetName, "NewVIN", row);
				// System.out.println(Term);
				// String StoreId =
				TestData.getCellData(sheetName, "StoreID", row);
				// String stateProduct=State+" "+ProductID;
				String stateProductType = State + " " + ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName, "ESign_LoanAmt", row);
				String ChkgAcctNbr = TestData.getCellData(sheetName, "ChkgAcctNbr", row);
				String ESign_DisbType = TestData.getCellData(sheetName, "ESign_DisbType", row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName, "ESign_CourtesyCallConsent", row);
				String AllowPromotion = TestData.getCellData(sheetName, "Allow Promotion", row);
				String CouponNbr = TestData.getCellData(sheetName, "CouponNbr", row);
				String ESign_Preference = TestData.getCellData(sheetName, "ESign_Preference", row);
				String ESign_Checks = TestData.getCellData(sheetName, "ESign_Checks", row);
				String ESign_Password = TestData.getCellData(sheetName, "ESign_Password", row);
				String ESign_CheckNbr = TestData.getCellData(sheetName, "ESign_CheckNbr", row);
				String last4cheknum = ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				String Parent_Window1 = driver.getWindowHandle();
				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				Thread.sleep(5000);
				driver.switchTo().frame("topFrame");

				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				// *[@id="911100"]/a
				driver.findElement(By.cssSelector("li[id='911100']")).click();
				test.log(LogStatus.PASS, "Clicked on New Loan");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");

				// Selection of Product based on the Name provided in
				// Test Data
				// if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				if (driver.findElement(By.name("ShareScreenBtn")).isEnabled()) {

					if (ProductName.equals("CO ILP")) {

						if (ESign_CollateralType.equals("ACH")) {
							// *[@id="termSel1"]

							driver.findElement(By.xpath("//*[@id='tableWid1']/tbody/tr[1]/td/b/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as " + ProductName);
						}

						if (ESign_CollateralType.equals("CASH")) {
							// *[@id="termSel1"]
							// driver.findElement(By.xpath("//*[@id='termSel1']")).click();
							// *[@id="tableWid1"]/tbody/tr[1]/td/b
							// *[@id="tableWid2"]/tbody/tr[1]/td/b
							driver.findElement(By.xpath("//*[@id='tableWid2']/tbody/tr[1]/td/b/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as " + ProductName);
						}
					}

					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");

					for (String winHandle1 : driver.getWindowHandles())

					{
						if (!(winHandle1.equals(Parent_Window1))) {
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window1);

					for (String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.id("LoanButtonId")).click();
					// New Loan Screens

					if (ProductID.equals("ILP")) {

						String Instamt = driver.findElement(By.name("requestBean.siilBean.disbAmt"))
								.getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as " + ESign_CollateralType);
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is entered as " + ESign_DisbType);
						Thread.sleep(5000);
						driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is entered as " + Instamt);
						driver.findElement(By.name("requestBean.siilBean.emailConsentFlag"))
								.sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS,
								"requestBean.siilBean.emailConsentFlag as " + ESign_CourtesyCallConsent);
						if (ESign_CourtesyCallConsent.equals("Yes")) {
							if (ESign_Preference.equals("Call")) {
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as " + ESign_Preference);
							}
							if (ESign_Preference.equals("Mail")) {
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as " + ESign_Preference);
							}
							if (ESign_Preference.equals("SMS")) {
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as " + ESign_Preference);

								try {
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									// if alert present, accept and move on.

								} catch (NoAlertPresentException e) {
									// do what you normally would if you didn't
									// have the alert.
								}
							}

						}
						if (AllowPromotion.equals("Yes")) {
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							// String mwh=driver.getWindowHandle();
							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as " + CouponNbr);
							// String winHandle =
							driver.getWindowHandle(); // Get current window
														// handle.
						}
						WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
						String NumofInst = ele.getAttribute("value");
						/*
						 * //*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/
						 * table[1]/tbody/tr[5]/td[2]/input
						 * System.out.println(NumofInst); int installments =
						 * Integer.parseInt(NumofInst); for(int
						 * i=0;i<installments;i++) { Random rand = new Random();
						 * int rand1 = rand.nextInt(100000); String chknum =
						 * Integer.toString(rand1);
						 * driver.findElement(By.id("checkNbrs"+i)).sendKeys(
						 * chknum);
						 * 
						 * }
						 */
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as " + ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
						try {
							Alert alert = driver.switchTo().alert();
							alert.accept();
							// if alert present, accept and move on.

						} catch (NoAlertPresentException e) {
							// do what you normally would if you didn't have the
							// alert.
						}
						Thread.sleep(500);

						for (String winHandle1 : driver.getWindowHandles()) {
							driver.switchTo().window(winHandle1);
						}
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						//

						// driver.findElement(By.xpath("//input[@value='Go' and
						// @type='button']")).click();
						/*
						 * wait.until(ExpectedConditions.
						 * visibilityOfElementLocated(By.xpath(
						 * "//input[@id='OKBut' and @type='button' and @value='Yes']"
						 * ))); driver.findElement(By.xpath(
						 * "//input[@id='OKBut' and @type='button' and @value='Yes']"
						 * )).click();
						 */
						// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[
						// @value='Yes' and @type='button' ]")));
						// driver.findElement(By.xpath("//input[ @value='Yes'
						// and @type='button' ]")).click();
						driver.findElement(By.xpath("//*[@id='OKBut']")).click();
						// *[@id="OKBut"]
						// driver.findElement(By.name("OKBut")).click();
						// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();

						test.log(LogStatus.PASS, "click on Yes button ");
						for (String winHandle1 : driver.getWindowHandles()) {
							driver.switchTo().window(winHandle1);
						}
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						if (driver.findElement(By.xpath("//input[@type='button' and @value='Ok']")).isDisplayed()) {
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							// driver.findElement(By.name("ok")).click();
						} else {
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}

				} else {
				}
			}
		}

	}

	public void StatementGeneration(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "statement generation date Captured::" + DueDate);
				// DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
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

				// Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");

				// driver.switchTo().defaultContent();
				// driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Unsecure Loc Statement")).click();
				test.log(LogStatus.PASS, "Clicked on Unsecure Loc Statement");

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

				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: " + StoreID);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				driver.findElement(By.name("beginMonth")).click();
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");

				/// Process Date
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				// Thread.sleep(5000);
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QA jobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				/*
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS); driver.switchTo().defaultContent();
				 * driver.switchTo().frame("mainFrame"); WebElement elements =
				 * driver.findElement(By.linkText("Daily Jobs")); Actions
				 * actions = new Actions(driver);
				 * actions1.moveToElement(elements1).build().perform();
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS);
				 */
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}

			}
		}
	}

	public void StatementGeneration_2(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "statement generation date Captured::" + DueDate);
				// DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
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

				// Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");

				// driver.switchTo().defaultContent();
				// driver.switchTo().frame("mainFrame");
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
				test.log(LogStatus.PASS, "StoreID is entered: " + StoreID);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				driver.findElement(By.name("beginMonth")).click();
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");

				test.log(LogStatus.PASS, "Second time statement generarted sucessfully");

			}
		}
	}

	public void Agestore_StatementGeneration(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[2]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "statement generation date Captured::" + DueDate);
				// DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];
				String DueDate2 = DueDate0[1];
				String DueDate3 = DueDate0[2];

				/// Process Date
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				// Thread.sleep(5000);
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
				actions.moveToElement(elements).build().perform();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}

			}
		}
	}

	public void DrawerDeassign(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "Banker PIN# is enetered as" + Password);
				driver.findElement(By.name("drawerdeassign")).click();
				test.log(LogStatus.PASS, "Click on Finish De-assign Button");
				try {
					driver.close();
				} catch (Exception e) {
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
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {
				}
				Thread.sleep(5000);

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table"))
						.isDisplayed()) {
					WebElement htmltable = driver.findElement(By.xpath(
							"/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table"));

					List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
					System.out.println("current row num " + rows.size());
					int count = 0;
					count = driver
							.findElements(By
									.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr"))
							.size();
					for (int rnum = 1; rnum < rows.size(); rnum++) {
						System.out.println("current row num " + rnum);

						driver.findElement(By
								.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[5]/select"))
								.sendKeys("Delete");
						driver.findElement(By
								.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[9]/td[2]/table/tbody/tr[2]/td[6]/input"))
								.click();
						try {
							Alert alert = driver.switchTo().alert();
							alert.accept();

						} catch (NoAlertPresentException e) {
						}
						Thread.sleep(5000);
					}
				}
				String DrawerOverShortAmount = driver.findElement(By.name("drawerRequestBean.drawerOverShort"))
						.getAttribute("value");
				driver.findElement(By.name("drawerRequestBean.amount")).sendKeys(DrawerOverShortAmount);
				test.log(LogStatus.PASS, "Amount entered as " + DrawerOverShortAmount);
				driver.findElement(By.name("drawerRequestBean.primary")).sendKeys("Cash Handling");
				test.log(LogStatus.PASS, "Primary Reason is selected as Cash Handling");
				driver.findElement(By.name("drawerRequestBean.notes")).sendKeys("Notes");
				test.log(LogStatus.PASS, "Notes Entered ");
				driver.findElement(By.name("bt_AddDrawer")).click();
				test.log(LogStatus.PASS, "Click on Add O/S Instance Button");
				Thread.sleep(3000);
				driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
				driver.findElement(By
						.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input"))
						.click();

				test.log(LogStatus.PASS, "Click on Finish Drawer O/S");
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {
				}
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (driver
						.findElement(By
								.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input"))
						.isDisplayed()) {

					test.log(LogStatus.PASS, "Drawer De-assigned successfully with over/short.");
					driver.findElement(
							By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input"))
							.click();
				} else {
					test.log(LogStatus.PASS, "Drawer not De-assigned successfully with over/short.");
				}
			}
		}
	}

	public void StatementGeneration_EODProcessing(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				// driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

				Thread.sleep(4000);
				// driver.findElement(By.name("requestBean.comments")).click();
				driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as comment");
				// requestBean.comments
				Thread.sleep(4000);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS, "Clicked on Balance Safe");
				Thread.sleep(4000);

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.

				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS, "Clicked on Balance Safe");

				Thread.sleep(1000);
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				// String SafeOverShortAmount =
				// driver.findElement(By.name("diffCashBal")).getAttribute("value");
				String SafeOverShortAmount = driver.findElement(By.name("requestBean.safeOverShort"))
						.getAttribute("value");

				driver.findElement(By.name("requestBean.amount")).sendKeys(SafeOverShortAmount);

				/// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td/table/tbody/tr[7]/td[3]

				// driver.findElement(By.name("requestBean.amount")).sendKeys("SafeOverShortAmount");
				test.log(LogStatus.PASS, "Enter the Balance 50");

				driver.findElement(By.name("requestBean.primary")).sendKeys("Deposit Issue");
				test.log(LogStatus.PASS, "Primary Reason is selected as Deposit Issue");
				driver.findElement(By.name("requestBean.notes")).sendKeys("Notes");
				test.log(LogStatus.PASS, "Notes Entered ");
				driver.findElement(By.name("bt_AddDrawer")).click();
				test.log(LogStatus.PASS, "Click on Add O/S Instance Button");
				Thread.sleep(3000);

				driver.findElement(By
						.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input"))
						.click();
				// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[3]/tbody/tr[11]/td[3]/input
				test.log(LogStatus.PASS, "Clicked on Next");

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.

				}

				Thread.sleep(1000);
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.name("Next"));
				// Next
				test.log(LogStatus.PASS, "Clicked on Next");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By
						.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]"))
						.click();
				test.log(LogStatus.PASS, "Clicked on Next");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By
						.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]"))
						.click();
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
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.

				}

			}
		}
	}

	public void EODProcessing(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				// driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

				Thread.sleep(4000);
				// driver.findElement(By.name("requestBean.comments")).click();
				driver.findElement(By.name("requestBean.comments")).sendKeys("comment");
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as comment");
				// requestBean.comments
				// Thread.sleep(4000);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS, "Clicked on Balance Safe");

				Thread.sleep(1000);
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.name("Next"));
				// Next
				test.log(LogStatus.PASS, "Clicked on Next");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By
						.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]"))
						.click();
				test.log(LogStatus.PASS, "Clicked on Next");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By
						.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]"))
						.click();
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
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.

				}

			}
		}
	}



public void Safeassign(String SSN,String FileName) throws Exception{
 	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);		
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
				test.log(LogStatus.PASS, "Clicked on Assign");  */
				

				
				
				
									    			
				
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

driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");


if(driver.findElement(By.name("previous")).isDisplayed())



	
driver.findElement(By.name("previous")).click();

driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
driver.switchTo().defaultContent();
driver.switchTo().frame("mainFrame");
driver.switchTo().frame("main");


driver.findElement(By.name("yes")).click(); 

					//login.Login(UserName, Password, StoreId, driver, AppURL, test);
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

	public WebElement Field(WebDriver driver) {

		try {
			Thread.sleep(500);
			WebElement element = (new WebDriverWait(driver, 9)).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table")));
			return element;
		} catch (Exception e) {
			return null;
		}
	}

	public void Drawerassign(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);

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
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Passwored is Entered");
				driver.findElement(By.name("drawerassign")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer Assigen Button");
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {

				}

				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (this.Field(driver) != null) {
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
					test.log(LogStatus.PASS, "Click on the Safe Deassign Button");

					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();

					} catch (NoAlertPresentException e) {

					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					if (driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {
						test.log(LogStatus.PASS, "Safe De-assigned successfully with over/short.");
						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
					} else {
						driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);
						test.log(LogStatus.PASS, "Enter the Password");
						driver.findElement(By.name("safedeassign")).click();
						test.log(LogStatus.PASS, "Click on the Deassign");
						for (String winHandle : driver.getWindowHandles()) {
							driver.switchTo().window(winHandle);
						}
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						String DrawerOverShortAmount = driver.findElement(By.name("safeRequestBean.safeOverShort"))
								.getAttribute("value");
						driver.findElement(By.name("safeRequestBean.amount")).sendKeys(DrawerOverShortAmount);
						test.log(LogStatus.PASS, "Amount entered as " + DrawerOverShortAmount);
						driver.findElement(By.name("safeRequestBean.primary")).sendKeys("Counterfeit Bill");
						test.log(LogStatus.PASS, "Primary Reason is selected as Counterfeit Bill");
						driver.findElement(By.name("safeRequestBean.notes")).sendKeys("Notes");
						test.log(LogStatus.PASS, "Notes Entered ");
						driver.findElement(By.name("bt_AddDrawer")).click();
						test.log(LogStatus.PASS, "Click on Add O/S Instance Button");
						Thread.sleep(3000);
						driver.findElement(By.name("transactionDataBean.password")).sendKeys(Password);
						driver.findElement(By
								.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[4]/tbody/tr[3]/td[1]/input"))
								.click();

						test.log(LogStatus.PASS, "Click on Finish Safe O/S");
						try {
							Alert alert = driver.switchTo().alert();
							alert.accept();

						} catch (NoAlertPresentException e) {
						}
						Thread.sleep(2000);
						for (String winHandle : driver.getWindowHandles()) {
							driver.switchTo().window(winHandle);
						}
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");

						if (driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {

							test.log(LogStatus.PASS, "Safe De-assigned successfully with over/short.");
							driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
						} else {
							test.log(LogStatus.PASS, "Safe not De-assigned successfully with over/short.");
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
					test.log(LogStatus.PASS, "Passwored is Entered");

					driver.findElement(By.name("safeAssignRequestBean.noOf100Dollars")).sendKeys("500");
					test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

					driver.findElement(By.name("safeassign")).click();
					test.log(LogStatus.PASS, "Click on Safe Assigen");

					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();

					} catch (NoAlertPresentException e) {

					}
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					if (driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).isDisplayed()) {

						test.log(LogStatus.PASS, "Safe assigned successfully.");
						driver.findElement(By.xpath("//input[(@type='submit') and (@value='Ok')]")).click();
					} else {
						test.log(LogStatus.PASS, "Safe not assigned successfully.");
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
					test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

					driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Passwored is Entered");
					driver.findElement(By.name("drawerassign")).click();
					test.log(LogStatus.PASS, "Click on drawer Assigen Button");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();

					} catch (NoAlertPresentException e) {

					}
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					if (driver
							.findElement(By
									.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input"))
							.isDisplayed()) {

						test.log(LogStatus.PASS, "Drawer De-assigned successfully with over/short.");
						driver.findElement(By
								.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input"))
								.click();
					} else {
						test.log(LogStatus.PASS, "Drawer not De-assigned successfully with over/short.");
					}

				} else {
					if (driver
							.findElement(By
									.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input"))
							.isDisplayed()) {

						test.log(LogStatus.PASS, "Drawer Assigned successfully with over/short.");
						// driver.findElement(By.name("done")).click();
						driver.findElement(By
								.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input"))
								.click();
					} else {
						test.log(LogStatus.PASS, "Drawer not Assigned successfully with over/short.");
					}
				}

			}
		}
		// driver.quit();
		// driver.close();
	}

	public void SafeDeassign(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
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
				// driver.findElement(By.cssSelector("li[id='911101']")).click();
				driver.findElement(By.linkText("Safe")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");
				// driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
				// driver.findElement(By.linkText("Drawer")).click();
				driver.findElement(By.linkText("Deassign")).click();
				test.log(LogStatus.PASS, "Clicked on Assign");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				// driver.findElement(By.name("previous")).click();

				driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 0");

				driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys(Password);

				driver.findElement(By.name("safedeassign")).click();

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.

				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("safeDeassignRequestBean.noOfDollars")).sendKeys("0");
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 0");

				driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys("Password");

				driver.findElement(By.name("safedeassign")).click();
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.

				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("safeDeassignRequestBean.comments")).sendKeys("chenna");
				driver.findElement(By.name("safeDeassignRequestBean.password")).sendKeys("Password");

				driver.findElement(By.name("safedeassign")).click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (driver.findElement(By.name("done")).isDisplayed()) {

					test.log(LogStatus.PASS, "Safe De-assigned successfully with over/short.");
					driver.findElement(By.name("done")).click();
				} else {
					test.log(LogStatus.PASS, "Safe not De-assigned successfully with over/short.");
				}

				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("yes")).click();

				driver.findElement(By.name("drawerAssignRequestBean.noOf100Dollars")).sendKeys("500");
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
				driver.findElement(By.name("drawerassign")).click();
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.

				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (driver.findElement(By.name("done")).isDisplayed()) {

					test.log(LogStatus.PASS, "Drawer De-assigned successfully with over/short.");
					driver.findElement(By.name("done")).click();
				} else {
					test.log(LogStatus.PASS, "Drawer not De-assigned successfully with over/short.");
				}

			}
		}
	}

	public void Safe(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
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
				// driver.findElement(By.cssSelector("li[id='911101']")).click();
				driver.findElement(By.linkText("Drawer")).click();
				test.log(LogStatus.PASS, "Clicked on Drawer");
				// driver.findElement(By.xpath("//*[@id="931010"]/a']")).click();
				// driver.findElement(By.linkText("Drawer")).click();
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
				test.log(LogStatus.PASS, "Count of Dollar Coins is entered as 500");

				driver.findElement(By.name("drawerAssignRequestBean.password")).sendKeys(Password);
				driver.findElement(By.name("drawerassign")).click();
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.

				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (driver.findElement(By.name("done")).isDisplayed()) {

					test.log(LogStatus.PASS, "Drawer De-assigned successfully with over/short.");
					driver.findElement(By.name("done")).click();
				} else {
					test.log(LogStatus.PASS, "Drawer not De-assigned successfully with over/short.");
				}

			}
		}
	}

	public void DeliquentPaymentStatus(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String BalanceStaus = null;
				BalanceStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Payment status is ." + BalanceStaus);
				// DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				System.out.print(BalanceStaus);
				// driver.close();

			}
		}
	}

	public void History_LoanStatus(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String Linestatus = null;

			
				Linestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Loan status is ." + Linestatus);
				String BalanceStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Balance status is ." + BalanceStaus);
				//System.out.print(CheckStaus);
				// driver.close();

			}
		}
	}

	public void StoreInfo(String SSN, String FileName) throws Exception {
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);

				driver.get(AdminURL);
				Thread.sleep(1000);

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: " + UserName);
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
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
				// Store Config
				/*
				 * WebElement element=
				 * driver.findElement(By.cssSelector("li[id='101000']"));
				 * Actions action = new Actions(driver);
				 * action.moveToElement(element).perform(); WebElement
				 * subElement =
				 * driver.findElement(By.cssSelector("li[id='101020']"));
				 * action.moveToElement(subElement).perform(); action.click();
				 */
				driver.findElement(By.linkText("Edit Store")).click();
				// action.perform();
				// driver.findElement(By.cssSelector("li[id='101020']")).click();
				test.log(LogStatus.PASS, "Clicked on Store Config");

				test.log(LogStatus.PASS, "Clicked on Edit Store");
				driver.switchTo().frame("main");
				driver.findElement(By.name("locationBean.locNbr")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				driver.findElement(By.name("Submit2")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("locationBean.locStatusCd")).sendKeys("Crash Package");
				// test.log(LogStatus.PASS, "Store Info Status is Chenged:
				// "+Storestaus);

				// locationBean.locStatusCd

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver.findElement(By.name("submitButton")).isDisplayed()) {
					test.log(LogStatus.PASS, "Store Aging is Successfully ");
					driver.findElement(By.name("submitButton")).click();
				} else {
					test.log(LogStatus.FAIL, "Store Aging is not Successfully ");
				}
				// driver.close();
			}
		}
	}

	public void DrawLoan(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				String State = TestData.getCellData(sheetName, "StateID", row);
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				// String Password =
				// TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName, "ProductType", row);
				String ProductName = TestData.getCellData(sheetName, "ProductName", row);
				// String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType = TestData.getCellData(sheetName, "VehicleType", row);
				String NewVIN = TestData.getCellData(sheetName, "NewVIN", row);
				// System.out.println(Term);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				// String stateProduct=State+" "+ProductID;
				String stateProductType = State + " " + ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName, "ESign_LoanAmt", row);
				String ChkgAcctNbr = TestData.getCellData(sheetName, "ChkgAcctNbr", row);
				String ESign_DisbType = TestData.getCellData(sheetName, "ESign_DisbType", row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName, "ESign_CourtesyCallConsent", row);
				String AllowPromotion = TestData.getCellData(sheetName, "Allow Promotion", row);
				String CouponNbr = TestData.getCellData(sheetName, "CouponNbr", row);
				String ESign_Preference = TestData.getCellData(sheetName, "ESign_Preference", row);
				String ESign_Checks = TestData.getCellData(sheetName, "ESign_Checks", row);
				String ESign_Password = TestData.getCellData(sheetName, "ESign_Password", row);
				String ESign_CheckNbr = TestData.getCellData(sheetName, "ESign_CheckNbr", row);
				String last4cheknum = ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				this.Login(UserName, Password, StoreID);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				test.log(LogStatus.INFO, "VoidDrawLoan with-SSN: " + SSN + " :: Starts");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Draw");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("loanAmt")).clear();

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.
				}

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				driver.findElement(By.name("loanAmt")).sendKeys("500");
				Thread.sleep(2000);
				driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Type is enterted as " + ESign_DisbType);
				// test.log(LogStatus.PASS, "Disb Amt is enterted as 200");
				driver.findElement(By.name("disbAmtFirst")).sendKeys("500");
				test.log(LogStatus.PASS, "Disb Amt is enterted as 500");
				driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
				test.log(LogStatus.PASS, "Password is entered as " + ESign_Password);
				Thread.sleep(2000);
				driver.findElement(By.name("finishadvance")).click();
				test.log(LogStatus.PASS, "Click on Finish Loan Button");

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr[1]/td"))
						.isDisplayed()) {
					test.log(LogStatus.INFO, "NewLoan Draw Transaction with-SSN: " + SSN + " :: is Successful");
				} else {
					test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
				}

			}

		}

	}

	public void NewLoan_ILP1(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String State = TestData.getCellData(sheetName, "StateID", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);

				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				System.out.println(ProductID);
				// String UserName =
				// TestData.getCellData(sheetName,"UserName",row);
				// String Password =
				// TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName, "ProductType", row);
				String ProductName = TestData.getCellData(sheetName, "ProductName", row);
				// String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType = TestData.getCellData(sheetName, "VehicleType", row);
				String NewVIN = TestData.getCellData(sheetName, "NewVIN", row);
				// System.out.println(Term);
				// String StoreId =
				// TestData.getCellData(sheetName,"StoreID",row);
				// String stateProduct=State+" "+ProductID;
				String stateProductType = State + " " + ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName, "ESign_LoanAmt", row);
				String ChkgAcctNbr = TestData.getCellData(sheetName, "ChkgAcctNbr", row);
				String ESign_DisbType = TestData.getCellData(sheetName, "ESign_DisbType", row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName, "ESign_CourtesyCallConsent", row);
				String AllowPromotion = TestData.getCellData(sheetName, "Allow Promotion", row);
				String CouponNbr = TestData.getCellData(sheetName, "CouponNbr", row);
				String ESign_Preference = TestData.getCellData(sheetName, "ESign_Preference", row);
				String ESign_Checks = TestData.getCellData(sheetName, "ESign_Checks", row);
				String ESign_Password = TestData.getCellData(sheetName, "ESign_Password", row);
				String ESign_CheckNbr = TestData.getCellData(sheetName, "ESign_CheckNbr", row);
				String last4cheknum = ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);

				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				Thread.sleep(500);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				// Selection of Product based on the Name provided in Test Data
				// if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				if (driver.findElement(By.name("ShareScreenBtn")).isEnabled()) {

					if (ProductName.equals("CO ILP")) {

						if (ESign_CollateralType.equals("ACH")) {
							driver.findElement(By.xpath("//table[@id='tableWid1']/tbody/tr/td/b/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as " + ProductName);
						}

						if (ESign_CollateralType.equals("CASH")) {
							driver.findElement(By.xpath("//table[@id='tableWid2']/tbody/tr/td/b/input']")).click();
							test.log(LogStatus.PASS, "ProductName is selected as " + ProductName);
						}
					}

					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");

					for (String winHandle1 : driver.getWindowHandles())

					{
						if (!(winHandle1.equals(Parent_Window))) {
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window);

					for (String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.id("LoanButtonId")).click();
					// New Loan Screens

					if (ProductID.equals("ILP")) {

						String Instamt = driver.findElement(By.name("requestBean.siilBean.disbAmt"))
								.getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as " + ESign_CollateralType);
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is enterted as " + ESign_DisbType);
						driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is enterted as " + Instamt);
						driver.findElement(By.name("requestBean.siilBean.emailConsentFlag"))
								.sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS,
								"requestBean.siilBean.emailConsentFlag as " + ESign_CourtesyCallConsent);
						if (ESign_CourtesyCallConsent.equals("Yes")) {
							if (ESign_Preference.equals("Call")) {
								driver.findElement(By.xpath("//*[@id='preferenceCall']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as " + ESign_Preference);
							}
							if (ESign_Preference.equals("Mail")) {
								driver.findElement(By.xpath("//*[@id='preferenceMail']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as " + ESign_Preference);
							}
							if (ESign_Preference.equals("SMS")) {
								driver.findElement(By.xpath("//*[@id='preferenceSms']")).click();
								test.log(LogStatus.PASS, "Courtesy Call Consent is selected as " + ESign_Preference);

								try {
									Alert alert = driver.switchTo().alert();
									alert.dismiss();
									// if alert present, accept and move on.

								} catch (NoAlertPresentException e) {
									// do what you normally would if you didn't
									// have the alert.
								}
							}

						}
						if (AllowPromotion.equals("Yes")) {
							driver.findElement(By.name("allowPromotion")).click();
							test.log(LogStatus.PASS, "AllowPromotion is selected ");
							// String mwh=driver.getWindowHandle();
							driver.findElement(By.name("requestBean.siilBean.couponNbr")).sendKeys(CouponNbr);
							test.log(LogStatus.PASS, "CouponNbr is selected as " + CouponNbr);
							// String winHandle = driver.getWindowHandle();
							// //Get current window handle.
						}
						WebElement ele = driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
						String NumofInst = ele.getAttribute("value");
						/*
						 * //*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/
						 * table[1]/tbody/tr[5]/td[2]/input
						 * System.out.println(NumofInst); int installments =
						 * Integer.parseInt(NumofInst); for(int
						 * i=0;i<installments;i++) { Random rand = new Random();
						 * int rand1 = rand.nextInt(100000); String chknum =
						 * Integer.toString(rand1);
						 * driver.findElement(By.id("checkNbrs"+i)).sendKeys(
						 * chknum);
						 * 
						 * }
						 */
						driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
						// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/input")).sendKeys(ESign_Password);
						test.log(LogStatus.PASS, "ESign_Checks is selected as " + ESign_Password);
						driver.findElement(By.name("finishLoan")).click();
						// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[12]/td/table/tbody/tr[1]/td[5]/input")).click();
						test.log(LogStatus.PASS, "click on Finish Loan button ");
						Thread.sleep(500);
						/*
						 * try { Alert alert = driver.switchTo().alert();
						 * alert.accept(); //if alert present, accept and move
						 * on.
						 * 
						 * } catch (NoAlertPresentException e) { //do what you
						 * normally would if you didn't have the alert. }
						 */
						// for( String winHandle1 : driver.getWindowHandles())
						// {
						// driver.switchTo().window(winHandle1);
						// }
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						// wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("OKBut")));
						driver.findElement(By.name("OKBut")).click();
						// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();
						test.log(LogStatus.PASS, "click on Yes button ");
						for (String winHandle1 : driver.getWindowHandles()) {
							driver.switchTo().window(winHandle1);
						}
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						if (driver.findElement(By.name("ok")).isDisplayed()) {
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							// driver.findElement(By.name("ok")).click();
						} else {
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}

				} else {
					test.log(LogStatus.FAIL, "Borrower is not Registered Successfully with SSN as " + SSN);
				}
			}
		}

	}

	public void DrawLoanwithACH(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				String State = TestData.getCellData(sheetName, "StateID", row);
				System.out.println(ProductID);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				// String Password =
				// TestData.getCellData(sheetName,"Password",row);
				String ProductType = TestData.getCellData(sheetName, "ProductType", row);
				String ProductName = TestData.getCellData(sheetName, "ProductName", row);
				// String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType = TestData.getCellData(sheetName, "VehicleType", row);
				String NewVIN = TestData.getCellData(sheetName, "NewVIN", row);
				// System.out.println(Term);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				// String stateProduct=State+" "+ProductID;
				String stateProductType = State + " " + ProductType;
				String ESign_CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName, "ESign_LoanAmt", row);
				String ChkgAcctNbr = TestData.getCellData(sheetName, "ChkgAcctNbr", row);
				String ESign_DisbType = TestData.getCellData(sheetName, "ESign_DisbType", row);
				String ESign_CourtesyCallConsent = TestData.getCellData(sheetName, "ESign_CourtesyCallConsent", row);
				String AllowPromotion = TestData.getCellData(sheetName, "Allow Promotion", row);
				String CouponNbr = TestData.getCellData(sheetName, "CouponNbr", row);
				String ESign_Preference = TestData.getCellData(sheetName, "ESign_Preference", row);
				String ESign_Checks = TestData.getCellData(sheetName, "ESign_Checks", row);
				String ESign_Password = TestData.getCellData(sheetName, "ESign_Password", row);
				String ESign_CheckNbr = TestData.getCellData(sheetName, "ESign_CheckNbr", row);
				String last4cheknum = ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				this.Login(UserName, Password, StoreID);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[contains(text(),'Loan Transactions')]")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				test.log(LogStatus.INFO, "VoidDrawLoan with-SSN: " + SSN + " :: Starts");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();
				test.log(LogStatus.PASS, "Clicked on Transactions");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Draw");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("loanAmt")).clear();

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.
				}

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
					// if alert present, accept and move on.

				} catch (NoAlertPresentException e) {
					// do what you normally would if you didn't have the alert.
				}
				Thread.sleep(2000);
				driver.findElement(By.name("loanAmt")).sendKeys("50");
				Thread.sleep(2000);
				driver.findElement(By.name("disbType")).sendKeys(ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Type is enterted as " + ESign_DisbType);
				test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
				driver.findElement(By.name("disbAmtFirst")).sendKeys("50");
				test.log(LogStatus.PASS, "Disb Amt is enterted as 50");
				driver.findElement(By.name("requestBean.password")).sendKeys(ESign_Password);
				test.log(LogStatus.PASS, "Password is entered as " + ESign_Password);
				Thread.sleep(2000);
				driver.findElement(By.name("finishadvance")).click();
				test.log(LogStatus.PASS, "Click on Finish Loan Button");

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr[1]/td"))
						.isDisplayed()) {
					test.log(LogStatus.INFO, "NewLoan Draw Transaction with-SSN: " + SSN + " :: is Successful");
				} else {
					test.log(LogStatus.PASS, "Draw New Loan is not Completed Successfully ");
				}

			}

		}

	}

	public void PayOffLoan(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				// driver.get(appUrl);
				// for(String winHandle : driver.getWindowHandles()){
				// driver.switchTo().window(winHandle);
				// }
				// driver.manage().window().maximize();
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "PayOffLoan with-SSN: " + SSN + " :: Starts");
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("PayOff");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("LOC")) {
					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					// String Pmt=
					// driver.findElement(By.name("payOffAmount")).getAttribute("value");
					// System.out.println(Pmt);
					// driver.findElement(By.name("requestBean.paymentAmt")).clear();
					// driver.findElement(By.name("tenderType")).sendKeys("10");
					// test.log(LogStatus.PASS, "tenderType");
					String Pmt = driver.findElement(By.name("payOffAmount")).getAttribute("value");
					driver.findElement(By.name("tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as " + TenderType);
					driver.findElement(By.name("tenderAmount")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as " + Pmt);
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();

					test.log(LogStatus.PASS, "Password is selected as " + Password);
					test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
					// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
					// *[@id="btnADV_Yes"]
					// *[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]

					// for( String winHandle1 : driver.getWindowHandles())
					// {
					// driver.switchTo().window(winHandle1);
					// }
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					if (driver.findElement(By.name("ok")).isDisplayed()) {
						test.log(LogStatus.INFO, "PayOffLoan with-SSN: " + SSN + " :: is Successful");
						driver.findElement(By.name("ok")).click();
					} else {
						test.log(LogStatus.FAIL, "Payoff not Completed Successfully ");
					}

				}

			}

		}
	}

	public void PartialPayment(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				// driver.get(appUrl);
				// for(String winHandle : driver.getWindowHandles()){
				// driver.switchTo().window(winHandle);
				// }
				// driver.manage().window().maximize();
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "PayOffLoan with-SSN: " + SSN + " :: Starts");
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Payments");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("LOC")) {
					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					// String Pmt=
					// driver.findElement(By.name("payOffAmount")).getAttribute("value");
					// System.out.println(Pmt);
					driver.findElement(By.name("requestBean.paymentAmt")).clear();
					driver.findElement(By.name("requestBean.paymentAmt")).sendKeys("30");
					driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as " + TenderType);
					driver.findElement(By.name("requestBean.tenderAmt")).sendKeys("30");
					test.log(LogStatus.PASS, "Tender Amt is entered as " + 30);
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();

					test.log(LogStatus.PASS, "Password is selected as " + Password);
					test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
					test.log(LogStatus.INFO, "Payment with-SSN: " + SSN + " :: is Successful");
					// driver.findElement(By.xpath("//*[@id='btnADV_No']")).click();
					// *[@id="btnADV_Yes"]
					// *[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]

					// for( String winHandle1 : driver.getWindowHandles())
					// {
					// driver.switchTo().window(winHandle1);
					// }
					/*
					 * driver.switchTo().defaultContent();
					 * driver.switchTo().frame("mainFrame");
					 * driver.switchTo().frame("main");
					 * 
					 * 
					 * if(driver.findElement(By.name("ok")).isDisplayed()) {
					 * test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +
					 * " :: is Successful");
					 * driver.findElement(By.name("ok")).click(); } else {
					 * test.log(LogStatus.FAIL,
					 * "Payment not Completed Successfully "); }
					 */

				}

			}

		}
	}

	public void MinPayments(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(5000);
				// driver.get(appUrl);
				// for(String winHandle : driver.getWindowHandles()){
				// driver.switchTo().window(winHandle);
				// }
				// driver.manage().window().maximize();
				CSRLoginpage login = new CSRLoginpage();
				login.Login(UserName, Password, StoreId, driver, AppURL, test);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "PayOffLoan with-SSN: " + SSN + " :: Starts");
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Payments");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("LOC")) {
					// driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);

					String Pmt = driver
							.findElement(By
									.xpath("/html/body/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[2]"))
							.getText();
					System.out.println(Pmt);
					driver.findElement(By.name("requestBean.paymentAmt")).clear();
					driver.findElement(By.name("requestBean.paymentAmt")).sendKeys(Pmt);
					driver.findElement(By.name("requestBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is Selected as " + TenderType);
					driver.findElement(By.name("requestBean.tenderAmt")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as " + Pmt);
					driver.findElement(By.name("password")).sendKeys(Password);
					driver.findElement(By.name("Submit22")).click();

					test.log(LogStatus.PASS, "Password is selected as " + Password);
					test.log(LogStatus.PASS, "Clicked on Finish Payoff button ");
					Thread.sleep(3000);
					// *[@id="btnADV_Yes"]
					driver.findElement(By.xpath("//*[@id='btnADV_Yes']")).click();
					// *[@id="btnADV_Yes"]
					// *[@id="revolvingCreditHistTable"]/tbody/tr[11]/td[2]/span[2]

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					if (driver.findElement(By.name("ok")).isDisplayed()) {
						test.log(LogStatus.INFO, "MinPayment with-SSN: " + SSN + " :: is Successful");
						driver.findElement(By.name("ok")).click();
					} else {
						test.log(LogStatus.FAIL, "MinPayment not Completed Successfully ");
					}

				}

			}

		}
	}

	public void CurePaymentStatus(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStaus = null;

				/*
				 * driver.findElement(By.xpath(
				 * "/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a"
				 * )).click(); }
				 * 
				 * //String winHandleBefore = driver.getWindowHandle();
				 * for(String winHandle : driver.getWindowHandles()){
				 * driver.switchTo().window(winHandle); } Thread.sleep(8000); //
				 * driver.findElement(By.xpath("//*[@id='home']")).click();
				 */
				// *[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]
				CheckStaus = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]")).getText();

				// *[@id='revolvingCreditHistTable']/tbody/tr[10]/td[3]/span[2]
				test.log(LogStatus.PASS, "Payment status is Cure." + CheckStaus);
				// DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
				System.out.print(CheckStaus);
				// driver.close();

			}
		}
	}

	public void EncryptionKey_Void(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				this.Login(UserName, Password, StoreId);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				String Eankey = null;
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

					// driver.findElement(By.xpath("
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Void");
				test.log(LogStatus.PASS, "Transaction Type is selected as Void");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				// driver.findElement(By.id("go_Button")).click();
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				String TranID = driver
						.findElement(By
								.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td"))
						.getText();
				test.log(LogStatus.PASS, "TranId captured:" + TranID);
				String TranID0[] = TranID.split(":");
				String TranID1 = TranID0[0];
				String TranID2 = TranID0[1];
				Thread.sleep(3000);
				// driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[2]")).click();
				driver.findElement(By
						.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]"))
						.click();
				// /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[2]
				// driver.findElement(By.xpath("//input[@name='NO' and
				// @type='button']")).click();
				test.log(LogStatus.PASS, "No button is clicked ");
				// name="NO"
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
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
				test.log(LogStatus.PASS, "GetKey clicked:" + Eankey);

				driver.close();
				driver = new InternetExplorerDriver();
				this.Login(UserName, Password, StoreId);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

					// driver.findElement(By.xpath("
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Void");
				test.log(LogStatus.PASS, "Transaction Type is selected as Void");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				// driver.findElement(By.id("go_Button")).click();
				Thread.sleep(5000);

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				// driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[7]/td/input[1]")).click();
				driver.findElement(By
						.xpath(" /html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[6]/td/input[1]"))
						.click();
				// driver.findElement(By.xpath("//input[@name='YES' and
				// @type='button']")).click();
				test.log(LogStatus.PASS, "Yes Button clicked");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("transactionDataBean.tenderType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "DisbType Type is entered as " + TenderType);
					// String Pmt= driver.findElement(By.xpath("
					// /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();
					// System.out.println(Pmt);
					// driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					// test.log(LogStatus.PASS, "Tender Amt is entered as
					// "+Pmt);

					driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					test.log(LogStatus.PASS, "Encryption key is entered as " + Eankey);

				}

				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("password")).sendKeys(Password);
					// Robot robot = new Robot();
					// Thread.sleep(2000);
					// robot.keyPress(KeyEvent.VK_F11);
					driver.findElement(By.name("Submit22")).click();
					// robot.keyPress(KeyEvent.VK_F11);
					test.log(LogStatus.PASS, "Password is selected as " + Password);
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
				}

				Thread.sleep(500);

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
				/*
				 * driver.switchTo().defaultContent();
				 * driver.switchTo().frame("mainFrame");
				 * driver.switchTo().frame("main"); if(ProductID.equals("LOC"))
				 * {
				 * 
				 * if(driver.findElement(By.name("checkyes")).isDisplayed()) {
				 * test.log(LogStatus.PASS,
				 * "Void Loan is Completed Successfully ");
				 * driver.findElement(By.name("checkyes")).click(); } else {
				 * test.log(LogStatus.FAIL,
				 * "Void Payment is not Completed Successfully "); } }
				 */
			}
		}
	}

	public void Rescind(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				this.Login(UserName, Password, StoreId);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				String Eankey = null;
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

					// driver.findElement(By.xpath("
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Rescind");
				test.log(LogStatus.PASS, "Transaction Type is selected as Rescind");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				if (ProductID.equals("LOC")) {
					String Pmt = driver
							.findElement(By
									.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/input[2]"))
							.getAttribute("value");
					driver.findElement(By.name("transactionDataBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "DisbType Type is entered as " + TenderType);
					// String Pmt= driver.findElement(By.xpath("
					// /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();
					System.out.println(Pmt);
					driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					test.log(LogStatus.PASS, "Tender Amt is entered as " + Pmt);

					// driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					// test.log(LogStatus.PASS, "Encryption key is entered as
					// "+Eankey);

				}

				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("password")).sendKeys(Password);
					// Robot robot = new Robot();
					// Thread.sleep(2000);
					// robot.keyPress(KeyEvent.VK_F11);
					driver.findElement(By.name("Submit22")).click();
					// robot.keyPress(KeyEvent.VK_F11);
					test.log(LogStatus.PASS, "Password is selected as " + Password);
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
				}

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("LOC")) {
					// *[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input
					if (driver
							.findElement(By
									.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input"))
							.isDisplayed())
					// if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Rescind Loan is Completed Successfully ");
						driver.findElement(By
								.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input"))
								.click();
					} else {
						test.log(LogStatus.FAIL, "Rescind Payment is not Completed Successfully ");
					}
				}
			}
		}
	}

	public void Void(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				this.Login(UserName, Password, StoreId);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				String Eankey = null;
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

					// driver.findElement(By.xpath("
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Void");
				test.log(LogStatus.PASS, "Transaction Type is selected as Void");
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				if (ProductID.equals("ILP")) {
					//String Pmt = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");
					driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "DisbType Type is entered as " + TenderType);
					// String Pmt= driver.findElement(By.xpath("
					// /html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[1]")).getText();
					//System.out.println(Pmt);
					//driver.findElement(By.name("transactionDataBean.tenderAmtFirst")).sendKeys(Pmt);
					//test.log(LogStatus.PASS, "Tender Amt is entered as " + Pmt);

					// driver.findElement(By.name("transactionDataBean.encryptionKey")).sendKeys(Eankey);
					// test.log(LogStatus.PASS, "Encryption key is entered as
					// "+Eankey);

				}

				if (ProductID.equals("ILP")) {
					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					// Robot robot = new Robot();
					// Thread.sleep(2000);
					// robot.keyPress(KeyEvent.VK_F11);
					driver.findElement(By.name("finish")).click();
					// robot.keyPress(KeyEvent.VK_F11);
					test.log(LogStatus.PASS, "Password is selected as " + Password);
					test.log(LogStatus.PASS, "Clicked on Finish Void Loan button ");
				}

				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();

				} catch (NoAlertPresentException e) {
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("ILP")) {
					// *[@id="totPart"]/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input
					if (driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).isDisplayed())
					// if(driver.findElement(By.name("checkyes")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Void Loan is Completed Successfully ");
						driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1]")).click();
					} else {
						test.log(LogStatus.FAIL, "Void Payment is not Completed Successfully ");
					}
				}
			}
		}
	}

	public void CustomerDefault(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {

					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "Capture Cure End Dtae" + DueDate);
				System.out.print(DueDate);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.close();
				// System.out.print(DueDate);
				// driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				// String DueDate="12/12/2020";
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);

				Date DDueDate = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDate);
				cal.add(Calendar.DATE, 0);
				Date DDueDateminus1 = cal.getTime();

				// String DueDateminus1 =df.format(DDueDateminus1);
				String DueDate0[] = DueDate.split("/");
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
				driver.findElement(By.linkText("QA Jobs")).click();
				test.log(LogStatus.PASS, "Clicked on QAJobs");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Process Date Change")).click();
				test.log(LogStatus.PASS, "Clicked on Process Date Change");
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
				} else {
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

				// Thread.sleep(5000);
				driver.findElement(By.linkText("LOC")).click();
				test.log(LogStatus.PASS, "Clicked on LOC");

				// driver.switchTo().defaultContent();
				// driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Default Loc")).click();
				test.log(LogStatus.PASS, "Clicked on Default Loc");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(6000);
				WebElement element = driver.findElement(By.xpath(
						"/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();

				// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(6000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: " + StoreID);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);

				// driver.findElement(By.linkText("iPads")).click();
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				Thread.sleep(6000);

			}
		}
	}

	public void CustomerDefault_Agestore(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {

					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "Capture Cure End Dtae" + DueDate);
				System.out.print(DueDate);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.close();
				// System.out.print(DueDate);
				// driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				// String DueDate="12/12/2020";
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);

				Date DDueDate = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDate);
				cal.add(Calendar.DATE, 0);
				Date DDueDateminus1 = cal.getTime();

				// String DueDateminus1 =df.format(DDueDateminus1);
				String DueDate0[] = DueDate.split("/");
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
				// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
				} else {
					test.log(LogStatus.FAIL, "Process Date updated successfully.");
				}

			}
		}
	}

	public void DefaultPaymentStatus(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {

					// driver.findElement(By.name("button")).click();
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					driver.findElement(By
							.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]"))
							.click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String CheckStaus = null;
				CheckStaus = driver
						.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]"))
						.getText();

				test.log(LogStatus.PASS, "Payment status is Default." + CheckStaus);
				System.out.print(CheckStaus);
				// driver.close();

			}
		}
	}

	public void Bankrupt_Deceased_Loanclosuer(String SSN, String FileName) throws Exception {
		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String BNKstatus = TestData.getCellData(sheetName, "BNKstatus", row);
				String AttorneyPhone = TestData.getCellData(sheetName, "AttorneyPhone", row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				System.out.println(AdminURL);
				String Bankstatus = null;
				///////////////////////////////////////
				this.Login(UserName, Password, StoreID);

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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("LOC")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// DueDate =
				// driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]"))
						.getText();
				// *[@id="revolvingCreditHistTable"]/tbody/tr[4]/td[3]/span[2]
				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, 10);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				////////////////////////////////////
				/*
				 * driver.get(AdminURL); test.log(LogStatus.INFO,
				 * "Admin portal is launched");
				 */
				driver.manage().window().maximize();
				Thread.sleep(1000);

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: " + UserName);
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
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

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200, 100).perform();
				Thread.sleep(10000);
				action.click();
				Thread.sleep(5000);

				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By
						.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input"))
						.click();

				test.log(LogStatus.PASS, "Click on Go button");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(3000);
				/*
				 * if( driver.findElement(By.name("loanCode")).isDisplayed()) {
				 * driver.findElement(By.name("loanCode")).click();
				 * test.log(LogStatus.PASS, "Selecting Check box for loan"); }
				 */
				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Deceased");
				test.log(LogStatus.PASS, "select status as :" + BNKstatus);
				driver.findElement(By.name("ubnkDate1")).sendKeys(DueDate1.trim());
				test.log(LogStatus.PASS, "Bankrupt Filing Month is:: " + DueDate1);
				Thread.sleep(500);
				driver.findElement(By.name("ubnkDate2")).sendKeys(DueDate2.trim());
				test.log(LogStatus.PASS, "Bankrupt Filing Day is:: " + DueDate2);
				Thread.sleep(500);
				driver.findElement(By.name("ubnkDate3")).sendKeys(DueDate3.trim());
				test.log(LogStatus.PASS, "Bankrupt Filing Year is:: " + DueDate3);

				/*
				 * driver.findElement(By.name("bkrCaseNbr")).sendKeys(SSN3);
				 * test.log(LogStatus.PASS, "Bankrupt case Number is ::"+SSN3);
				 * driver.findElement(By.name("requestBean.typeOfBankruptcy")).
				 * sendKeys("chapter7"); test.log(LogStatus.PASS,
				 * "Bankrupt type is ::Chapter7");
				 * 
				 * 
				 * driver.findElement(By.name("attorneyName")).sendKeys(
				 * "Attorny"); test.log(LogStatus.PASS, "Entered Attorny Name");
				 * 
				 * 
				 * driver.findElement(By.name("debtorAttorneyPhone1")).sendKeys(
				 * AttorneyP1.trim()); test.log(LogStatus.PASS,
				 * "PP1 is entered: "+AttorneyP1); Thread.sleep(500);
				 * driver.findElement(By.name("debtorAttorneyPhone2")).sendKeys(
				 * AttorneyP2.trim()); test.log(LogStatus.PASS,
				 * "PP2 is entered: "+AttorneyP2); Thread.sleep(500);
				 * driver.findElement(By.name("debtorAttorneyPhone3")).sendKeys(
				 * AttorneyP3.trim()); test.log(LogStatus.PASS,
				 * "PP3 is entered: "+AttorneyP3);
				 */

				driver.findElement(By.name("bt_AddBankruptcy")).click();
				test.log(LogStatus.PASS, "Status BNKPending is Saved");

				Thread.sleep(50000);
				// /html/body/form/table[2]/tbody/tr[2]/td/table/tbody/tr[1]/td[9]/table/tbody/tr[2]/td
				Bankstatus = driver
						.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]"))
						.getText();

				test.log(LogStatus.PASS, "<FONT color=green style=Arial> Customer got Bankrupted::+Bankstatus");

				driver.close();

				driver = new InternetExplorerDriver();

			}

		}

	}

	public void BankruptStatus(String SSN, String FileName) throws Exception

	{

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
					// driver.findElement(By.name("button")).click();
					/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]
					// driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				test.log(LogStatus.PASS, "History Selected in DropDown");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.xpath("//input[(@name='button') and (@value='Go')]")).click();
					// driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String PaymentStatus = null;
				PaymentStatus = driver
						.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[6]/td[3]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "Payment status is ::" + PaymentStatus);
				String LineStatus = null;
				LineStatus = driver
						.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[12]/td[2]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "Line status is ::" + LineStatus);
				String WOReason = null;
				WOReason = driver
						.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[14]/td[2]/span[2]"))
						.getText();
				test.log(LogStatus.PASS, "Reason for Customer got WriteOff ::" + WOReason);

			}
		}
	}

	public void Bankrupt_Void(String SSN, String FileName) throws Exception {
		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String BNKstatus = TestData.getCellData(sheetName, "BNKstatus", row);
				String AttorneyPhone = TestData.getCellData(sheetName, "AttorneyPhone", row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				System.out.println(AdminURL);
				///////////////////////////////////////

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");

				////////////////////////////////////
				/*
				 * driver.get(AdminURL); test.log(LogStatus.INFO,
				 * "Admin portal is launched");
				 */
				driver.manage().window().maximize();
				Thread.sleep(1000);

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: " + UserName);
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
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

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200, 100).perform();
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
				driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input"))
						.click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(3000);
				if (driver.findElement(By.name("loanCode")).isDisplayed()) {
					driver.findElement(By.name("loanCode")).click();
					test.log(LogStatus.PASS, "Selecting Check box for loan");
				}
				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void");
				test.log(LogStatus.PASS, "select status as Void");

				driver.findElement(By.name("bt_AddBankruptcy")).click();
				test.log(LogStatus.PASS, " Clicked on Saved");

				Thread.sleep(5000);

				String ele = driver
						.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]"))
						.getText();
				if (ele.contains("Void")) {
					test.log(LogStatus.PASS, " Bankrupt void complted Sucessfully ");

				} else {
					test.log(LogStatus.PASS, " Bankrupt void not complted Sucessfully ");
				}

				driver.close();

				driver = new InternetExplorerDriver();

			}

		}

		/*
		 * if(driver.findElement(By.name("submitButton")).isDisplayed()) {
		 * test.log(LogStatus.PASS, "Store Aging is Successfully ");
		 * driver.findElement(By.name("submitButton")).click(); } else {
		 * test.log(LogStatus.FAIL, "Store Aging is not Successfully "); }
		 */
		// driver.close();
	}

	public void Void_Deceased(String SSN, String FileName) throws Exception {
		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String BNKstatus = TestData.getCellData(sheetName, "BNKstatus", row);
				String AttorneyPhone = TestData.getCellData(sheetName, "AttorneyPhone", row);
				String AttorneyP1 = AttorneyPhone.substring(0, 3);
				String AttorneyP2 = AttorneyPhone.substring(3, 6);
				String AttorneyP3 = AttorneyPhone.substring(6, 10);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				System.out.println(AdminURL);
				///////////////////////////////////////

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");

				////////////////////////////////////
				/*
				 * driver.get(AdminURL); test.log(LogStatus.INFO,
				 * "Admin portal is launched");
				 */
				driver.manage().window().maximize();
				Thread.sleep(1000);

				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: " + UserName);
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
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

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				Thread.sleep(5000);
				Actions action = new Actions(driver);
				action.moveByOffset(200, 100).perform();
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
				driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[8]/input"))
						.click();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(3000);
				/*
				 * if( driver.findElement(By.name("loanCode")).isDisplayed()) {
				 * driver.findElement(By.name("loanCode")).click();
				 * test.log(LogStatus.PASS, "Selecting Check box for loan"); }
				 */
				driver.findElement(By.name("requestBean.bnkStatus")).sendKeys("Void");
				test.log(LogStatus.PASS, "select status as Void");

				driver.findElement(By.name("bt_AddBankruptcy")).click();
				test.log(LogStatus.PASS, " Clicked on Saved");

				Thread.sleep(5000);

				String ele = driver
						.findElement(By.xpath("/html/body/form/table[2]/tbody/tr[9]/td/table/tbody/tr[3]/td[2]"))
						.getText();
				if (ele.contains("Void")) {
					test.log(LogStatus.PASS, " Bankrupt void complted Sucessfully ");

				} else {
					test.log(LogStatus.PASS, " Bankrupt void not complted Sucessfully ");
				}

				driver.close();

				driver = new InternetExplorerDriver();

			}

		}

		/*
		 * if(driver.findElement(By.name("submitButton")).isDisplayed()) {
		 * test.log(LogStatus.PASS, "Store Aging is Successfully ");
		 * driver.findElement(By.name("submitButton")).click(); } else {
		 * test.log(LogStatus.FAIL, "Store Aging is not Successfully "); }
		 */
		// driver.close();
	}

	public void Default_WOProc(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String TenderType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {
					// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]
					// driver.findElement(By.name("button")).click();
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				test.log(LogStatus.PASS, "History Selected in DropDown");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[11]/td[3]/span[2]"))
						.getText();
				// DueDate =
				// driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[13]/td[3]/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture PWO  Dtae" + DueDate);
				System.out.print(DueDate);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
				driver.close();
				// System.out.print(DueDate);
				// driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);
				test.log(LogStatus.INFO, "Admin portal is launched");
				driver.manage().window().maximize();
				// storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
				// Click Login Button
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(8000);
				Thread.sleep(8000);
				Date DDueDate = df.parse(DueDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(DDueDate);
				cal.add(Calendar.DATE, 60);
				Date DDueDateminus1 = cal.getTime();
				String DueDateminus1 = df.format(DDueDateminus1);
				String DueDate0[] = DueDateminus1.split("/");
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
				// driver.manage().timeouts().implicitlyWait(120,
				// TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

				/*
				 * driver.switchTo().defaultContent();
				 * 
				 * driver.switchTo().frame("mainFrame");
				 * 
				 * WebElement elements1 = driver.findElement(By.linkText(
				 * "Daily Jobs"));
				 * 
				 * Actions actions1 = new Actions(driver);
				 * 
				 * actions1.moveToElement(elements1).build().perform();
				 * 
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS);
				 */
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
				} else {
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

				// driver.switchTo().defaultContent();
				// driver.switchTo().frame("mainFrame");
				Thread.sleep(5000);
				driver.findElement(By.linkText("Writeoff Loc")).click();
				test.log(LogStatus.PASS, "Clicked on Writeoff Loc");

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				Thread.sleep(6000);
				/*
				 * WebElement element = driver.findElement(By.name("cancel"));
				 * Actions action = new Actions(driver);
				 * action.moveToElement(element).build().perform();
				 */

				// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img")).click();
				// Thread.sleep(6000);
				// driver.manage().timeouts().implicitlyWait(120,
				// TimeUnit.SECONDS);

				// try {
				// Alert alert = driver.switchTo().alert();
				// alert.dismiss();
				// if alert present, accept and move on.

				// }
				// catch (NoAlertPresentException e) {
				// do what you normally would if you didn't have the alert.
				// }

				Thread.sleep(6000);
				WebElement element = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[2]/td[2]/div[6]/a/img"));
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("requestBean.storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "StoreID is entered: " + StoreID);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);

				// driver.findElement(By.linkText("iPads")).click();
				driver.findElement(By.name("submit")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				Thread.sleep(6000);

			}
		}
	}

	public void Payoff(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String DisbType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String TenderType = TestData.getCellData(sheetName, "Tender_Type", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(4000);
				String Payoffbalance = null;
				this.Login(UserName, Password, StoreId);
				driver.switchTo().defaultContent();
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Closure Transaction with-SSN: " + SSN + " :: is Starts");
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				Thread.sleep(1000);
				if (ProductID.equals("ILP")) {
					                              
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.name("button")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Payments");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				} else {
					driver.findElement(By.id("go_Button")).click();
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("ILP"))

				{

					driver.findElement(By.name("requestBean.paymentType")).click();
					test.log(LogStatus.PASS, "Pay off the balance option is selected ");

					Payoffbalance = driver.findElement(By.name("payOff")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payoff balance " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(DisbType);
					test.log(LogStatus.PASS, "Tender Type is selected " + TenderType);

					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payoffbalance);
					test.log(LogStatus.PASS, "Tender Amount is Entered " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as " + Password);

					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish button ");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						// if alert present, accept and move on.

					} catch (NoAlertPresentException e) {
						// do what you normally would if you didn't have the
						// alert.
					}
					for (String winHandle1 : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					// driver.findElement(By.name("ok")).click();

					// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					if (driver.findElement(By.name("checkyes")).isDisplayed()) {
						test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " + SSN + " :: is Successful");
						// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
						driver.findElement(By.name("checkyes")).click();
					} else {
						test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
					}

				}
			}

		}
	}
	

	public void Payoff_Return(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String DisbType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String TenderType = TestData.getCellData(sheetName, "Tender_Type", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(4000);
				String Payoffbalance = null;
				this.Login(UserName, Password, StoreId);
				driver.switchTo().defaultContent();
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Closure Transaction with-SSN: " + SSN + " :: is Starts");
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				Thread.sleep(1000);
				if (ProductID.equals("ILP")) {
					                              
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.name("button")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Payments");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				} else {
					driver.findElement(By.id("go_Button")).click();
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("ILP"))

				{
					
                    driver.findElement(By.xpath("//*[@id='PD3']")).click();
                    test.log(LogStatus.PASS, "Pay off the balance option is selected ");
				//	driver.findElement(By.name("requestBean.paymentType")).click();
				//	test.log(LogStatus.PASS, "Pay off the balance option is selected ");

					Payoffbalance = driver.findElement(By.name("requestBean.siilBean.payAmt")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payoff balance " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(DisbType);
					test.log(LogStatus.PASS, "Tender Type is selected " + TenderType);

					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payoffbalance);
					test.log(LogStatus.PASS, "Tender Amount is Entered " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as " + Password);

					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish button ");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						// if alert present, accept and move on.

					} catch (NoAlertPresentException e) {
						// do what you normally would if you didn't have the
						// alert.
					}
					for (String winHandle1 : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					// driver.findElement(By.name("ok")).click();

					// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					if (driver.findElement(By.name("checkyes")).isDisplayed()) {
						test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " + SSN + " :: is Successful");
						// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
						driver.findElement(By.name("checkyes")).click();
					} else {
						test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
					}

				}
			}

		}
	}
	

	public void Payoff_Installments(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel(System.getProperty("user.dir") + "/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {
				String TxnType = TestData.getCellData(sheetName, "TxnType", row);
				String DisbType = TestData.getCellData(sheetName, "TenderType", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String TenderType = TestData.getCellData(sheetName, "Tender_Type", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				Thread.sleep(4000);
				String Payoffbalance = null;
				this.Login(UserName, Password, StoreId);
				driver.switchTo().defaultContent();
				WebDriverWait wait = new WebDriverWait(driver, 100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				test.log(LogStatus.INFO, "Closure Transaction with-SSN: " + SSN + " :: is Starts");
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				Thread.sleep(1000);
				if (ProductID.equals("ILP")) {
					                            
					driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
					// driver.findElement(By.name("button")).click();
				}
				// driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("Payments");
				if (ProductID.equals("LOC")) {
					driver.findElement(By.name("button")).click();
				} else {
					driver.findElement(By.id("go_Button")).click();
				}
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (ProductID.equals("ILP"))

				{

					driver.findElement(By.name("requestBean.paymentType")).click();
					test.log(LogStatus.PASS, "Pay off the balance option is selected ");

					Payoffbalance = driver.findElement(By.name("payOff")).getAttribute("value");
					test.log(LogStatus.PASS, "Capture the Payoff balance " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(DisbType);
					test.log(LogStatus.PASS, "Tender Type is selected " + TenderType);

					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payoffbalance);
					test.log(LogStatus.PASS, "Tender Amount is Entered " + Payoffbalance);

					Thread.sleep(500);

					driver.findElement(By.name("requestBean.password")).sendKeys(Password);
					test.log(LogStatus.PASS, "Password is selected as " + Password);

					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish button ");
					try {
						Alert alert = driver.switchTo().alert();
						alert.accept();
						// if alert present, accept and move on.

					} catch (NoAlertPresentException e) {
						// do what you normally would if you didn't have the
						// alert.
					}
					for (String winHandle1 : driver.getWindowHandles()) {
						driver.switchTo().window(winHandle1);
					}
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					// driver.findElement(By.name("ok")).click();

					// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
					if (driver.findElement(By.name("checkyes")).isDisplayed()) {
						test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " + SSN + " :: is Successful");
						// driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
						driver.findElement(By.name("checkyes")).click();
					} else {
						test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
					}

				}
			}

		}
	}

	public void AgeStore(String SSN, String FileName, int Days) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// *[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				
				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
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

				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				/*
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS); driver.switchTo().defaultContent();
				 * driver.switchTo().frame("mainFrame"); WebElement elements1 =
				 * driver.findElement(By.linkText("Daily Jobs")); Actions
				 * actions1 = new Actions(driver);
				 * actions1.moveToElement(elements1).build().perform();
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS);
				 */

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

			}
		}
	}

	public void History_Paymentcaluculation(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		int Schedules_count = 0;
		int i;
		double totalorigfee1 = 0;
		int totdays1 = 0;
		double totalMHCFee1 = 0;
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
				String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/* driver.findElement(By.name("button")).click(); */
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				// driver.findElement(By.id("go_Button")).click();

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				
				
			   String Linestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Payment status is ." + Linestatus);
				String BalanceStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Payment status is ." + BalanceStaus);
               
				String TaotalPaidAMT = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[21]/td/span[2]")).getText();
				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
				test.log(LogStatus.INFO, "Total paid AMTF ee  ::" + TaotalPaidAMT);

				String PrincipalAmt = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[17]/td/span[2]")).getText();
				// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
				test.log(LogStatus.INFO, "Principal AMT ::" + PrincipalAmt);

				String TotalPaidMHCFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]")).getText();
				test.log(LogStatus.INFO, "Total MHC Fee ::" + TotalPaidMHCFee);

				String ORigiFee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[24]/td/span[2]")).getText();
				test.log(LogStatus.INFO, "Total Origination Fee Paid to date ::" + ORigiFee);
				
				//String Balancestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();  
				//test.log(LogStatus.INFO, "Balance status is::"+Balancestatus);
				
				String Latefee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[20]/td/span[2]")).getText();  
				test.log(LogStatus.INFO, "Late fee is::"+Latefee);
			
				String NSFFee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[6]/td[10]/font")).getText();  
				test.log(LogStatus.INFO, "NSFFee fee is::"+NSFFee);
				
				
				if(CollateralType.equals("CASH")){
				double caluculationAMT = Double.parseDouble(PrincipalAmt) + Double.parseDouble(TotalPaidMHCFee) +Double.parseDouble(ORigiFee)+Double.parseDouble(Latefee);
				
				String caluculationAMT1 = String.valueOf(caluculationAMT);			     
				double b = Double.parseDouble(TaotalPaidAMT);
				String b1 = String.valueOf(b);
				
				if (caluculationAMT1.contains(b1)) {
					test.log(LogStatus.INFO, "Total Amount is equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
				}else {
					test.log(LogStatus.INFO, "Total Amount is not equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
				}
				}
				
				if(CollateralType.equals("ACH")){
					double caluculationAMT = Double.parseDouble(PrincipalAmt) + Double.parseDouble(TotalPaidMHCFee) +Double.parseDouble(ORigiFee)+Double.parseDouble(Latefee)+Double.parseDouble(NSFFee);
					
					String caluculationAMT1 = String.valueOf(caluculationAMT);			     
					double b = Double.parseDouble(TaotalPaidAMT);
					String b1 = String.valueOf(b);
					
					if (caluculationAMT1.contains(b1)) {
						test.log(LogStatus.INFO, "Total Amount is equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
					}else {
						test.log(LogStatus.INFO, "Total Amount is not equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
					}
					}
				
				
			}
		}
	}
	

public void History_Payoffcaluculation(String SSN, String FileName) throws Exception {

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan " + lastrow);
	String sheetName = "NewLoan";
	int Schedules_count = 0;
	int i;
	double totalorigfee1 = 0;
	int totdays1 = 0;
	double totalMHCFee1 = 0;
	for (int row = 2; row <= lastrow; row++) {
		String RegSSN = TestData.getCellData(sheetName, "SSN", row);
		if (SSN.equals(RegSSN)) {

			String ProductID = TestData.getCellData(sheetName, "ProductID", row);
			String UserName = TestData.getCellData(sheetName, "UserName", row);
			String Password = TestData.getCellData(sheetName, "Password", row);
			String StoreID = TestData.getCellData(sheetName, "StoreID", row);
			String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
			String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
			String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);

			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);
			String AppURL = TestData.getCellData(sheetName, "AppURL", row);
			appUrl = AppURL;
			this.Login(UserName, Password, StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3, 5);
			String SSN3 = SSN.substring(5, 9);
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
			test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			/* driver.findElement(By.name("button")).click(); */
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");
			for (String winHandle1 : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle1);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			// driver.findElement(By.id("go_Button")).click();

			for (String winHandle1 : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle1);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			
		   String Linestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
			test.log(LogStatus.PASS, "Payment status is ." + Linestatus);
			String BalanceStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
			test.log(LogStatus.PASS, "Payment status is ." + BalanceStaus);
           
			String TaotalAMT = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[6]/td[5]/font")).getText();
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
			test.log(LogStatus.INFO, "Total paid AMTF ee  ::" + TaotalAMT);

			String PrincipalAmt = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[6]/td[8]/font")).getText();
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
			test.log(LogStatus.INFO, "Principal AMT ::" + PrincipalAmt);

			String TotalPaidMHCFee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[6]/td[15]/font")).getText();
			test.log(LogStatus.INFO, "Total MHC Fee ::" + TotalPaidMHCFee);

			String ORigiFee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[6]/td[16]/font")).getText();
			test.log(LogStatus.INFO, "Total Origination Fee Paid to date ::" + ORigiFee);
			
			//String Balancestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();  
			//test.log(LogStatus.INFO, "Balance status is::"+Balancestatus);
			
			String Latefee = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[20]/td/span[2]")).getText();  
			test.log(LogStatus.INFO, "Late fee is::"+Latefee);
		
			String NSFFee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[6]/td[10]/font")).getText();  
			test.log(LogStatus.INFO, "NSFFee fee is::"+NSFFee);
			
			
			if(CollateralType.equals("CASH")){
			double caluculationAMT = Double.parseDouble(PrincipalAmt) + Double.parseDouble(TotalPaidMHCFee) +Double.parseDouble(ORigiFee)+Double.parseDouble(Latefee);
			
			String caluculationAMT1 = String.valueOf(caluculationAMT);			     
			double b = Double.parseDouble(TaotalAMT);
			String b1 = String.valueOf(b);
			
			if (caluculationAMT1.contains(b1)) {
				test.log(LogStatus.INFO, "Total Amount is equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
			}else {
				test.log(LogStatus.INFO, "Total Amount is not equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
			}
			}
			
			if(CollateralType.equals("ACH")){
				double caluculationAMT = Double.parseDouble(PrincipalAmt) + Double.parseDouble(TotalPaidMHCFee) +Double.parseDouble(ORigiFee)+Double.parseDouble(Latefee)+Double.parseDouble(NSFFee);
				
				String caluculationAMT1 = String.valueOf(caluculationAMT);			     
				double b = Double.parseDouble(TaotalAMT);
				String b1 = String.valueOf(b);
				
				if (caluculationAMT1.contains(b1)) {
					test.log(LogStatus.INFO, "Total Amount is equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
				}else {
					test.log(LogStatus.INFO, "Total Amount is not equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
				}
				}
			
			
		}
	}
}


public void History_Payoffcaluculation_3rdInst(String SSN, String FileName) throws Exception {

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan " + lastrow);
	String sheetName = "NewLoan";
	int Schedules_count = 0;
	int i;
	double totalorigfee1 = 0;
	int totdays1 = 0;
	double totalMHCFee1 = 0;
	for (int row = 2; row <= lastrow; row++) {
		String RegSSN = TestData.getCellData(sheetName, "SSN", row);
		if (SSN.equals(RegSSN)) {

			String ProductID = TestData.getCellData(sheetName, "ProductID", row);
			String UserName = TestData.getCellData(sheetName, "UserName", row);
			String Password = TestData.getCellData(sheetName, "Password", row);
			String StoreID = TestData.getCellData(sheetName, "StoreID", row);
			String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
			String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
			String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);

			System.out.println(AdminURL);
			test.log(LogStatus.INFO, "Scheduler-Store Aging");

			System.out.println(ProductID);
			String AppURL = TestData.getCellData(sheetName, "AppURL", row);
			appUrl = AppURL;
			this.Login(UserName, Password, StoreID);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3, 5);
			String SSN3 = SSN.substring(5, 9);
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
			test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
			driver.findElement(By.name("ssn2")).sendKeys(SSN2);
			test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
			driver.findElement(By.name("ssn3")).sendKeys(SSN3);
			test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
			driver.findElement(By.name("submit1")).click();
			test.log(LogStatus.PASS, "Click on submit Button");
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			/* driver.findElement(By.name("button")).click(); */
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");
			for (String winHandle1 : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle1);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");

			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
			// driver.findElement(By.id("go_Button")).click();

			for (String winHandle1 : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle1);
			}
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			
		   String Linestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();
			test.log(LogStatus.PASS, "Payment status is ." + Linestatus);
			String BalanceStaus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();
			test.log(LogStatus.PASS, "Payment status is ." + BalanceStaus);
           
			String TaotalAMT = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[10]/td[5]/font")).getText();
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
			test.log(LogStatus.INFO, "Total paid AMTF ee  ::" + TaotalAMT);

			String PrincipalAmt = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[10]/td[8]")).getText();
			// //*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[23]/td/span[2]
			test.log(LogStatus.INFO, "Principal AMT ::" + PrincipalAmt);

			String TotalPaidMHCFee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[10]/td[15]/font")).getText();
			test.log(LogStatus.INFO, "Total MHC Fee ::" + TotalPaidMHCFee);

			String ORigiFee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[10]/td[16]/font")).getText();
			test.log(LogStatus.INFO, "Total Origination Fee Paid to date ::" + ORigiFee);
			
			//String Balancestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();  
			//test.log(LogStatus.INFO, "Balance status is::"+Balancestatus);
			
			String Latefee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[10]/td[12]/font")).getText();  
			test.log(LogStatus.INFO, "Late fee is::"+Latefee);
		
			String NSFFee = driver.findElement(By.xpath("//*[@id='transactionDetailsTable']/tbody/tr[10]/td[10]/font")).getText();  
			test.log(LogStatus.INFO, "NSFFee fee is::"+NSFFee);
			
			
			if(CollateralType.equals("CASH")){
			double caluculationAMT = Double.parseDouble(PrincipalAmt) + Double.parseDouble(TotalPaidMHCFee) +Double.parseDouble(ORigiFee)+Double.parseDouble(Latefee);
			
			String caluculationAMT1 = String.valueOf(caluculationAMT);			     
			double b = Double.parseDouble(TaotalAMT);
			String b1 = String.valueOf(b);
			
			if (caluculationAMT1.contains(b1)) {
				test.log(LogStatus.INFO, "Total Amount is equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
			}else {
				test.log(LogStatus.INFO, "Total Amount is not equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
			}
			}
			
			if(CollateralType.equals("ACH")){
				double caluculationAMT = Double.parseDouble(PrincipalAmt) + Double.parseDouble(TotalPaidMHCFee) +Double.parseDouble(ORigiFee)+Double.parseDouble(Latefee)+Double.parseDouble(NSFFee);
				
				String caluculationAMT1 = String.valueOf(caluculationAMT);			     
				double b = Double.parseDouble(TaotalAMT);
				String b1 = String.valueOf(b);
				
				if (caluculationAMT1.contains(b1)) {
					test.log(LogStatus.INFO, "Total Amount is equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
				}else {
					test.log(LogStatus.INFO, "Total Amount is not equal to PrincipalAmt and ORigiFee and TotalPaidMHCFee");
				}
				}
			
			
		}
	}
}

	
	public void History_Status(String SSN, String FileName) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		int Schedules_count = 0;
		int i;
		double totalorigfee1 = 0;
		int totdays1 = 0;
		double totalMHCFee1 = 0;
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);
				String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
				String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/* driver.findElement(By.name("button")).click(); */
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				/// html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				// driver.findElement(By.id("go_Button")).click();

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
               
				
				String Balancestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[3]/td/span[2]")).getText();  
				test.log(LogStatus.INFO, "Balance status is::"+Balancestatus);
				
				String Linestatus = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[2]/td/span[2]")).getText();  
				test.log(LogStatus.INFO, "Late fee is::"+Linestatus);
				
				
				
			}
		}
	}
	

	
	
	public void AgeStore_1stInstallment(String SSN, String FileName, int Days) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// *[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[2]/td[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
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

				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				/*
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS); driver.switchTo().defaultContent();
				 * driver.switchTo().frame("mainFrame"); WebElement elements1 =
				 * driver.findElement(By.linkText("Daily Jobs")); Actions
				 * actions1 = new Actions(driver);
				 * actions1.moveToElement(elements1).build().perform();
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS);
				 */

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

			}
		}
	}
	
	
	public void AgeStore_2ndInstallment(String SSN, String FileName, int Days) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[13]/input")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// *[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[3]/td[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
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

				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				/*
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS); driver.switchTo().defaultContent();
				 * driver.switchTo().frame("mainFrame"); WebElement elements1 =
				 * driver.findElement(By.linkText("Daily Jobs")); Actions
				 * actions1 = new Actions(driver);
				 * actions1.moveToElement(elements1).build().perform();
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS);
				 */

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

			}
		}
	}
	
	public void AgeStore_3rdInstallment(String SSN, String FileName, int Days) throws Exception {

		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		System.out.println("NewLoan " + lastrow);
		String sheetName = "NewLoan";
		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {

				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String StoreID = TestData.getCellData(sheetName, "StoreID", row);
				String AdminURL = TestData.getCellData(sheetName, "AdminURL", row);

				System.out.println(AdminURL);
				test.log(LogStatus.INFO, "Scheduler-Store Aging");

				System.out.println(ProductID);
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				appUrl = AppURL;
				this.Login(UserName, Password, StoreID);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
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
				test.log(LogStatus.PASS, "SSN1 is entered: " + SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: " + SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: " + SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				if (ProductID.equals("ILP")) {

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					// driver.findElement(By.id("go_Button")).click();
				}

				for (String winHandle1 : driver.getWindowHandles()) {
					driver.switchTo().window(winHandle1);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate = null;

				// *[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]
				DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[4]/td[2]")).getText();

				test.log(LogStatus.PASS, "Capture DueDate" + DueDate);
				System.out.print(DueDate);
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(AdminURL);

				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");
				test.log(LogStatus.PASS, "Username is entered: admin");
				driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is entered: " + Password);
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

				String DDueDate[] = DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1 = cal.getTime();

				DueDate = df.format(DDueDate1);

				String DueDate0[] = DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];

				/*
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS); driver.switchTo().defaultContent();
				 * driver.switchTo().frame("mainFrame"); WebElement elements1 =
				 * driver.findElement(By.linkText("Daily Jobs")); Actions
				 * actions1 = new Actions(driver);
				 * actions1.moveToElement(elements1).build().perform();
				 * driver.manage().timeouts().implicitlyWait(120,
				 * TimeUnit.SECONDS);
				 */

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("storeCode")).click();
				driver.findElement(By.name("storeCode")).sendKeys(StoreID);
				test.log(LogStatus.PASS, "Store number is entered: " + StoreID);
				Thread.sleep(5000);
				driver.findElement(By.name("beginMonth")).clear();
				driver.findElement(By.name("beginMonth")).sendKeys(DueDate1);
				test.log(LogStatus.PASS, "beginMonth is entered: " + DueDate1);
				driver.findElement(By.name("beginDay")).clear();
				driver.findElement(By.name("beginDay")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: " + DueDate2);
				driver.findElement(By.name("beginYear")).clear();
				driver.findElement(By.name("beginYear")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: " + DueDate3);
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				Thread.sleep(1000);
				Thread.sleep(5000);
				driver.findElement(By.name("btnPreview")).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				if (driver
						.findElement(By
								.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
						.isDisplayed()) {
					test.log(LogStatus.PASS, "Process Date updated successfully");
					driver.findElement(By
							.xpath("/html/body/form/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/input"))
							.click();
				} else {
					test.log(LogStatus.FAIL, "Process Date not updated successfully.");
				}

			}
		}
	}
	
	



public void Void_Payment (String SSN,String FileName) throws Exception{
	
	
	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);	
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
				    
				 
				    if(ProductID.equals("ILP"))
					 {
				    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				    	// /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input
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
					 if(ProductID.equals("ILP"))
					 {
						  driver.findElement(By.name("button")).click();
					 }
						  
				
					 
					 for( String winHandle1 : driver.getWindowHandles())
						{
						// driver.findElement(By.name("button")).click();
						    driver.switchTo().window(winHandle1);
						}			
						 driver.switchTo().defaultContent();
						 driver.switchTo().frame("mainFrame");
						 driver.switchTo().frame("main");
						 if(ProductID.equals("ILP"))
						 {
							 
							 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[6]/td/table/tbody/tr[2]/td[1]/table/tbody/tr[3]/td[2]/select")).sendKeys("cash");
							 test.log(LogStatus.PASS, "Disb type is selected as "+ "Cash");	
							 driver.findElement(By.name("requestBean.password")).sendKeys(Password);
							 test.log(LogStatus.PASS, "Password is selected as "+Password);			
							 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[10]/td/table/tbody/tr/td[3]/input")).click();
								test.log(LogStatus.PASS, "Clicked on Void Payment button ");
							 
							 Thread.sleep(2000);							
								 
								 
									try { 
									    Alert alert = driver.switchTo().alert();
									    alert.accept();
									    //if alert present, accept and move on.														
										
									}
									catch (NoAlertPresentException e) {
									    //do what you normally would if you didn't have the alert.
									}
									
							/*		try { 
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
								/*for( String winHandle1 : driver.getWindowHandles())
								{
								    driver.switchTo().window(winHandle1);
								}			
								 driver.switchTo().defaultContent();
								 driver.switchTo().frame("mainFrame");
								 driver.switchTo().frame("main");
									Thread.sleep(2000);
									 Thread.sleep(2000);
								 if(driver.findElement(By.name("checkno")).isDisplayed())
									{
									 
									 driver.findElement(By.name("checkno")).click();
									 test.log(LogStatus.INFO, "Payment with-SSN: " +SSN +" :: is Successful");
									}
								 else
									{
										test.log(LogStatus.FAIL, "Payment not Completed Successfully ");
									}*/
							
					    	
						 }
					
			}
			
		}
		}


	
	
	
	public void Void_1st_inst_Payment(String SSN,String FileName) throws Exception{
		
		
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
					    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
					    	 						//	 /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr4]/td[11]/input[1]
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
								
								 driver.findElement(By.name("defPaymentRequestBeanRC.tenderType")).sendKeys(TenderType);
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
										 test.log(LogStatus.PASS, "Void 1st installment Payment Completed Successfully ");
											driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td/input")).click();
										}
									 else
										{
											test.log(LogStatus.FAIL, "Void 1st installment Payment not Completed Successfully ");
										}
								 
						    	
							 }
						
				}
				
			}
		}
	
	public void NewLoan_ILP_NegAmt(String SSN,String FileName,String NegAmt) throws Exception{


		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);
		int lastrow=TestData.getLastRow("NewLoan");
		System.out.println("NewLoan "+lastrow);
		String sheetName="NewLoan";
		for(int row=2;row<=lastrow;row++)
		{
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				String State =TestData.getCellData(sheetName,"StateID",row);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);

				String StoreID = TestData.getCellData(sheetName,"StoreID",row);
				System.out.println(ProductID);
				String UserName =TestData.getCellData(sheetName,"UserName",row);
				String Password =TestData.getCellData(sheetName,"Password",row);
				String ProductType =TestData.getCellData(sheetName,"ProductType",row);
				String ProductName = TestData.getCellData(sheetName,"ProductName",row);
				//String Term = TestData.getCellData(sheetName,"Term",row);
				String VehicleType=TestData.getCellData(sheetName,"VehicleType",row);
				String NewVIN= TestData.getCellData(sheetName,"NewVIN",row);
				//System.out.println(Term);
				//String StoreId =
				TestData.getCellData(sheetName,"StoreID",row);
				//String stateProduct=State+" "+ProductID;
				String stateProductType=State+" "+ProductType;
				String ESign_CollateralType =TestData.getCellData(sheetName,"ESign_CollateralType",row);
				System.out.println(ESign_CollateralType);
				String ESign_LoanAmt = TestData.getCellData(sheetName,"ESign_LoanAmt",row);
				String ChkgAcctNbr = TestData.getCellData(sheetName,"ChkgAcctNbr",row);
				String ESign_DisbType = TestData.getCellData(sheetName,"ESign_DisbType",row);
				String ESign_CourtesyCallConsent =TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
				String AllowPromotion =TestData.getCellData(sheetName,"Allow Promotion",row);
				String CouponNbr = TestData.getCellData(sheetName,"CouponNbr",row);
				String ESign_Preference =TestData.getCellData(sheetName,"ESign_Preference",row);
				String ESign_Checks =TestData.getCellData(sheetName,"ESign_Checks",row);
				String ESign_Password=TestData.getCellData(sheetName,"ESign_Password",row);
				String ESign_CheckNbr =TestData.getCellData(sheetName,"ESign_CheckNbr",row);
				String last4cheknum=ChkgAcctNbr.substring(ChkgAcctNbr.length() - 4);
				String Parent_Window = driver.getWindowHandle();
				System.out.println(last4cheknum);
				System.out.println(stateProductType);
				 String Parent_Window1 = driver.getWindowHandle();  
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
					//*[@id="911100"]/a
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



				test.log(LogStatus.INFO, "Navigated to Loan decisioning Screen");
			
				//    Selection of Product based on the Name provided in
				//Test Data
				// if(driver.findElement(By.id("LoanButtonId")).isEnabled())
				if(driver.findElement(By.name("ShareScreenBtn")).isEnabled())
				{


					if(ProductName.equals("CO ILP"))
					{

						if(ESign_CollateralType.equals("ACH"))
						{
							//*[@id="termSel1"]
							
							driver.findElement(By.xpath("//*[@id='tableWid1']/tbody/tr[1]/td/b/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}

						if(ESign_CollateralType.equals("CASH"))
						{
							//*[@id="termSel1"]
							//driver.findElement(By.xpath("//*[@id='termSel1']")).click();
							//*[@id="tableWid1"]/tbody/tr[1]/td/b
							//*[@id="tableWid2"]/tbody/tr[1]/td/b
							driver.findElement(By.xpath("//*[@id='tableWid2']/tbody/tr[1]/td/b/input")).click();
							test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
						}
					}

					driver.findElement(By.name("ShareScreenBtn")).click();
					test.log(LogStatus.PASS, "ShareScreen Button clicked");

					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window1)))
						{
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.findElement(By.name("confirmSummary")).click();
							test.log(LogStatus.PASS, "ConfirmShareScreen Button clicked");
						}

					}
					Thread.sleep(3000);
					driver.switchTo().window(Parent_Window1);

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					driver.findElement(By.id("LoanButtonId")).click();
					test.log(LogStatus.PASS, "Loan Button clicked");

					for( String winHandle1 : driver.getWindowHandles())

					{

						driver.switchTo().window(winHandle1);

					}

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");
					///////////////////////
					//New Loan Screens
					///  name="negLoanAmt"



					//name="requestBean.siilBean.advAmt"


					//name="reCalculate"

					//name="negSel" NOC /html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[3]/table/tbody/tr[15]/td/input

					///html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[2]/table/tbody/tr[15]/td
					driver.findElement(By.name("negLoanAmt")).click();
					test.log(LogStatus.PASS, "NegotiableAmmount Button clicked");
					for( String winHandle1 : driver.getWindowHandles())

					{
						if(!(winHandle1.equals(Parent_Window1)))
						{
					
							Thread.sleep(3000);
							driver.switchTo().window(winHandle1);
							Thread.sleep(1000);
							driver.manage().window().maximize();
							Thread.sleep(3000);
							Robot robot = new Robot();
							Thread.sleep(2000);
							robot.keyPress(KeyEvent.VK_F11);
							test.log(LogStatus.PASS, "F11 button clicked");
							System.out.println("Before");
							Thread.sleep(8000);
							//WebElement element = driver.findElement(By.name("requestBean.siilBean.advAmt"));                                       
							///Actions builder = new Actions(driver); 
							
							Thread.sleep(3000);

							WebElement element = driver.findElement(By.name("requestBean.siilBean.advAmt"));  
							if(element.isDisplayed()){
							
								
								Actions builder = new Actions(driver); 
								builder.doubleClick()
								/* .sendKeys(element, Keys.ARROW_RIGHT)
									   .sendKeys(element, Keys.ARROW_RIGHT)
									   .doubleClick()*/
								//.sendKeys(element, Keys.DELETE)
								//.sendKeys(element, Keys.ARROW_RIGHT)
								.sendKeys(element, Keys.DELETE)
								//.sendKeys(element, Keys.CLEAR)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.build()
								.perform();
							}
							else
							{
							
								Actions builder = new Actions(driver); 
								builder.doubleClick()
								/* .sendKeys(element, Keys.ARROW_RIGHT)
									   .sendKeys(element, Keys.ARROW_RIGHT)
									   .doubleClick()*/
								//.sendKeys(element, Keys.DELETE)
								//.sendKeys(element, Keys.ARROW_RIGHT)
								.sendKeys(element, Keys.DELETE)
								//.sendKeys(element, Keys.CLEAR)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.sendKeys(element,Keys.BACK_SPACE)
								.build()
								.perform();
							}
						
							
							
							Thread.sleep(8000);
							
							driver.findElement(By.name("requestBean.siilBean.advAmt")).sendKeys(NegAmt);
							test.log(LogStatus.PASS, "Negotiable Amount Entered is::"+NegAmt);
							// name="requestBean.siilBean.advAmt"
							driver.findElement(By.name("reCalculate")).click();
							test.log(LogStatus.PASS, "ReCalculate button clicked");
							Thread.sleep(2000);
							
							    Actions builder = new Actions(driver); 
							    builder.doubleClick()
								.sendKeys(element, Keys.ARROW_DOWN)
								.sendKeys(element, Keys.ARROW_DOWN)
								.sendKeys(element, Keys.ARROW_DOWN)
								.sendKeys(element, Keys.ARROW_DOWN)
							
								.build()
								.perform();
							    Thread.sleep(2000);
								
								//     /html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[3]/table/tbody/tr[15]/td/input
							                            //	/html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[5]/table/tbody/tr[15]/td
								driver.findElement(By.xpath(" /html/body/form/table/tbody/tr[6]/td/table/tbody/tr/td[4]/table/tbody/tr[16]/td/input")).click();
								test.log(LogStatus.PASS, "ProductName is selected as "+ProductName);
								Thread.sleep(5000);
								robot.keyRelease(KeyEvent.VK_F11);
								test.log(LogStatus.PASS, "F11 button clicked");
							}

						
						
						
					}
						
					
						
					}

					Robot robot = new Robot();
					Thread.sleep(2000);
			/*		robot.keyPress(KeyEvent.VK_F11);
					test.log(LogStatus.PASS, "F11 button clicked");*/
					robot.keyRelease(KeyEvent.VK_F11);
					test.log(LogStatus.PASS, "F11 button clicked");
					Thread.sleep(5000);
					driver.switchTo().window(Parent_Window);

					Thread.sleep(5000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");
					///////////////////////////////
					if(ProductID.equals("ILP"))
					{
	// requestBean.siilBean.disbAmt

						String Instamt=driver.findElement(By.name("requestBean.siilBean.disbAmt")).getAttribute("value");
						System.out.println(Instamt);
						driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
						//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr[3]/td[3]/select")).sendKeys(ESign_CollateralType);
						test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
						driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys(ESign_DisbType);
						test.log(LogStatus.PASS, "Disb Type is entered as "+ESign_DisbType);
						driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Instamt);
						test.log(LogStatus.PASS, "Disb Amt is entered as "+Instamt);
						driver.findElement(By.name("requestBean.siilBean.emailConsentFlag")).sendKeys(ESign_CourtesyCallConsent);
						test.log(LogStatus.PASS,"requestBean.siilBean.emailConsentFlag as "+ESign_CourtesyCallConsent);
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
							//String winHandle =
							driver.getWindowHandle(); //Get current window handle.
						}
						WebElement ele =
								driver.findElement(By.name("requestBean.siilBean.nbrOfInst"));
						String NumofInst=ele.getAttribute("value");
						/*
		//*[@id="errorMessage"]/form[1]/table/tbody/tr[4]/td/table[1]/tbody/tr[5]/td[2]/input
		                        System.out.println(NumofInst);
		                        int installments = Integer.parseInt(NumofInst);
		                        for(int i=0;i<installments;i++)
		                        {
		                            Random rand = new Random();
		                            int rand1 = rand.nextInt(100000);
		                            String chknum = Integer.toString(rand1);
		driver.findElement(By.id("checkNbrs"+i)).sendKeys(chknum);

		                        }             */
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
						Thread.sleep(500);
			
						for( String winHandle1 : driver.getWindowHandles())
						{
							driver.switchTo().window(winHandle1);
						}
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");
						driver.switchTo().frame("main");
						//
						
					
						driver.findElement(By.xpath("//*[@id='OKBut']")).click();
						//*[@id="OKBut"]
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
						if(		driver.findElement(By.xpath("//input[@type='button' and @value='Ok']")).isDisplayed())
						{
							test.log(LogStatus.PASS, "New Loan is Completed Successfully ");
							//driver.findElement(By.name("ok")).click();
						}
						else
						{
							test.log(LogStatus.FAIL, "New Loan is not Completed Successfully ");
						}
					}


				

				}
			}
		}



public void Payliance_OriginationFile(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);	
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
			


			if(ProductID.equals("ILP"))
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
			if(ProductID.equals("ILP"))
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
			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			                                          
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
			driver.findElement(By.linkText("Payliance")).click();
			test.log(LogStatus.PASS, "Clicked on Payliance");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Payliance Origination File")).click();
			test.log(LogStatus.PASS, "Clicked on Payliance Origination File");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		/*	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Daily Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
			Thread.sleep(5000);*/

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




public void ACH_Deposit(String SSN,String FileName,int Days) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);	
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
			


			if(ProductID.equals("ILP"))
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
			if(ProductID.equals("ILP"))
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

			DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
			//DueDate = driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();
			                                          
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
			driver.findElement(By.linkText("Installment Loan")).click();
			test.log(LogStatus.PASS, "Clicked on Installment Loan");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process ILP Pre ACH Deposits")).click();
			test.log(LogStatus.PASS, "Clicked on Process ILP Pre ACH Deposits");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			/*driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Daily Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
			Thread.sleep(5000);*/

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
		driver.quit();
	}
}

public void ACH_Revoke(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);	
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


			if(ProductID.equals("ILP"))
			{
				
				//driver.findElement(By.name("button")).click();
				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
				//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[5]/td[11]/input[1]")).click();
				driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
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
			driver.findElement(By.name("transactionList")).sendKeys("ACH Revoke");
			test.log(LogStatus.PASS, "RCC Revoke Transaction is selected");
			if(ProductID.equals("ILP"))
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
		    driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]/input[2]")).click();
			//driver.findElement(By.name("bt_BankDetails")).click(); 
			test.log(LogStatus.PASS, "Clicked on ACH Revoke");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table[2]/tbody/tr/td/input[1]")).isDisplayed())
			{
			 test.log(LogStatus.PASS, "ACH Revoke Transaction completed successfully.");
				driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table[2]/tbody/tr/td/input[1]")).click(); 
				test.log(LogStatus.PASS, "Clicked on Ok button");
			}
			else
			{
				test.log(LogStatus.PASS, "ACH Revoke Transaction not completed successfully.");
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


			if(ProductID.equals("ILP"))
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
			if(ProductID.equals("ILP"))
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
			{
				test.log(LogStatus.PASS, "ACH Tracking Status is displayed in History screen");
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
						 test.log(LogStatus.PASS, "ACH Tracking Status Details"+Details);
													
					}
					
					
					}
					
			}
			else
			{
				test.log(LogStatus.PASS, "ACH Tracking Status is not displayed in History screen");
			}*/
		}
	}
}

public void ACHAuthorization(String SSN,String FileName) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);	
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


			if(ProductID.equals("ILP"))
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
			driver.findElement(By.name("transactionList")).sendKeys("ACH Authorization");
			if(ProductID.equals("ILP"))
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
			Thread.sleep(2000);
			
			driver.findElement(By.name("requestBean.abaCode")).sendKeys("111111118");
			
			driver.findElement(By.name("checkAbaNbrDisp")).sendKeys("111111118");
			test.log(LogStatus.PASS, "Confirm ABA Code is ::111111118");
			driver.findElement(By.name("requestBean.ckActNo")).sendKeys(ChkgAcctNbr);
			test.log(LogStatus.PASS, "Checking Account number  is ::"+ChkgAcctNbr);
			driver.findElement(By.name("checkAccountNbrDisp")).sendKeys(ChkgAcctNbr);
			test.log(LogStatus.PASS, "Confirm Checking Account number  is"+ChkgAcctNbr);
			//driver.findElement(By.name("requestBean.loanValue")).click();
			//test.log(LogStatus.PASS, "Proceed all authorization for All Loans");
			
			driver.findElement(By.name("requestBean.password")).sendKeys(Password);
			test.log(LogStatus.PASS, "Password entered is ::" +Password);
			driver.findElement(By.name("bt_BankDetails")).click();
			//driver.findElement(By.name("bt_BankDetails")).click();
			
			test.log(LogStatus.PASS, "Click on ACHAuthorization Button");
			Thread.sleep(5000);
			
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			if(driver.findElement(By.name("checkyes")).isDisplayed())                            
			//if(driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1])")).isDisplayed())
			{
				driver.findElement(By.name("checkyes")).click();
				//driver.findElement(By.xpath("//*[@id='totPart']/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td/input[1])")).click();
				test.log(LogStatus.PASS, "Click on Confirmation OK Button");
			}
/*				driver.switchTo().frame("topFrame");
			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();*/
			driver.quit();//need to change to close
			 driver = new InternetExplorerDriver();

		}
	}
}


public void EODProcessing_with_recordsChecking(String SSN,String FileName) throws Exception{


	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);	
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

			Thread.sleep(5000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")));
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td[2]/input[3]")).click();

			test.log(LogStatus.PASS, "Clicked on Next");

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");

			Thread.sleep(5000);

			try { 

				if( driver.findElement(By.name("achDepRecLoc")).isDisplayed())
					//if( driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[3]/td/table/tbody/tr[3]/td[6]")).isDisplayed())
				{									        								

					//driver.findElement(By.name("achDepRecLoc")).isDisplayed();
					test.log(LogStatus.PASS, "LOC ACH  Deposit Available");

				}
				else{
					test.log(LogStatus.PASS, "LOC ACH  Deposit Not Available.");
				}

			}
			catch (Exception e) {
				//do what you normally would if you didn't have the alert.





				test.log(LogStatus.PASS, "LOC ACH  Deposit record Not Available.");
			}


			Thread.sleep(5000);

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


public void Payment_ILP(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String DisbType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			String Payment=null;
			this.Login(UserName, Password, StoreId);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Payment_ILP Transaction with-SSN: " +SSN +" :: is Starts");
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
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

	

			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
		//		driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				//driver.findElement(By.name("button")).click();
			
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("ILP"))
				
			{
				
				
				///driver.findElement(By.name("requestBean.paymentType")).click();
				// name="requestBean.paymentType"  name="requestBean.paymentType"  name="requestBean.paymentType"
				//  value="PD4" payment    value="PD3" payoff  value="PD5" payanyotherAmt
				// /html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input[2]  payoff value
				// /html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]  payment value
				driver.findElement(By.xpath("//*[@id='PD4']")).click();
				test.log(LogStatus.PASS, "Payment option is selected ");
				
			     Payment=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");				     
			     
				test.log(LogStatus.PASS, "Capture Payment "+Payment);
				
				Thread.sleep(500);
				// name="requestBean.siilBean.tenderTypeFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Tender Type is selected "+TenderType);
				
				
				// name="requestBean.siilBean.tenderAmtFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payment);
				test.log(LogStatus.PASS, "Tender Amount is Entered "+Payment);
				
				Thread.sleep(500);
				// name="requestBean.password"
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				
				// name="finish"
				driver.findElement(By.name("finish")).click();																				
				test.log(LogStatus.PASS, "Clicked on Finish button ");
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

				//driver.findElement(By.name("ok")).click();

// name="checkyes"
				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
				}


			
		
			}
		}

	}
}


public void PaymentLess_ILP(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String DisbType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			String Payment=null;
			this.Login(UserName, Password, StoreId);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Payment_ILP Transaction with-SSN: " +SSN +" :: is Starts");
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
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

	

			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
		//		driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				//driver.findElement(By.name("button")).click();
			
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("ILP"))
				
			{
				
	
				driver.findElement(By.xpath("//*[@id='PD5']")).click();
				test.log(LogStatus.PASS, "Payment option is selected ");
				
			     Payment=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");				     
			     
				test.log(LogStatus.PASS, "Capture Payment "+Payment);
				
			double Payment1 =	Double.parseDouble(Payment);
			double LessPMT = Payment1 - 10.00;
			String LessPMT1 = String.valueOf(LessPMT);
				
				driver.findElement(By.name("requestBean.siilBean.payAmt")).clear();
				driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(LessPMT1);
				test.log(LogStatus.PASS, "Eneter the cure amount  "+LessPMT);
				
				Thread.sleep(500);
				// name="requestBean.siilBean.tenderTypeFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Tender Type is selected "+TenderType);
				
				
				// name="requestBean.siilBean.tenderAmtFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(Payment);
				test.log(LogStatus.PASS, "Tender Amount is Entered "+Payment);
				
				Thread.sleep(500);
				// name="requestBean.password"
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				
				// name="finish"
				driver.findElement(By.name("finish")).click();																				
				test.log(LogStatus.PASS, "Clicked on Finish button ");
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

				//driver.findElement(By.name("ok")).click();

// name="checkyes"
				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.INFO, "Payoff Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Payoff Loan is not Completed Successfully ");
				}


			
		
			}
		}

	}
}





public void PaymentcureAmount_ILP(String SSN,String FileName) throws Exception{
	
	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);  	
	int lastrow = TestData.getLastRow("NewLoan");
	System.out.println("NewLoan "+lastrow);
	String sheetName = "NewLoan";		
	for(int row=2; row <= lastrow; row++) {	
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String TxnType=TestData.getCellData(sheetName,"TxnType",row);
			String DisbType = TestData.getCellData(sheetName,"TenderType",row);	
			String ProductID=TestData.getCellData(sheetName,"ProductID",row);
			String UserName = TestData.getCellData(sheetName,"UserName",row);
			String Password = TestData.getCellData(sheetName,"Password",row);
			String TenderType = TestData.getCellData(sheetName,"TenderType",row);
	       // System.out.println(Password);
	        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);
			Thread.sleep(4000);
			String Payment=null;
			this.Login(UserName, Password, StoreId);
			driver.switchTo().defaultContent();	
			WebDriverWait wait = new WebDriverWait(driver, 100);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
			driver.switchTo().frame("topFrame");
			test.log(LogStatus.INFO, "Payment_ILP Transaction with-SSN: " +SSN +" :: is Starts");
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
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}				    
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");

	

			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

				///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
		//		driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
				//driver.findElement(By.name("button")).click();
			
			//  driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			
			String cureAmount = driver.findElement(By.xpath("//*[@id='CustGrid']/tbody/tr[2]/td[7]")).getText();
			test.log(LogStatus.PASS, "cure amount captured "+cureAmount);
			driver.findElement(By.name("transactionList")).sendKeys("Payment");
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			if(ProductID.equals("ILP"))
				
			{
				
				
				///driver.findElement(By.name("requestBean.paymentType")).click();
				// name="requestBean.paymentType"  name="requestBean.paymentType"  name="requestBean.paymentType"
				//  value="PD4" payment    value="PD3" payoff  value="PD5" payanyotherAmt
				// /html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/input[2]  payoff value
				// /html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]  payment value
				driver.findElement(By.xpath("//*[@id='PD5']")).click();
				test.log(LogStatus.PASS, "pay any another Amount option is selected ");
				
			  ///   Payment=driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr[8]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input[2]")).getAttribute("value");				     
				driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(cureAmount);
				test.log(LogStatus.PASS, "Eneter the cure amount  "+cureAmount);
				
				Thread.sleep(500);
				// name="requestBean.siilBean.tenderTypeFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
				test.log(LogStatus.PASS, "Tender Type is selected "+TenderType);
				
				
				// name="requestBean.siilBean.tenderAmtFirst"
				driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(cureAmount);
				test.log(LogStatus.PASS, "Tender Amount is Entered "+cureAmount);
				
				Thread.sleep(500);
				// name="requestBean.password"
				driver.findElement(By.name("requestBean.password")).sendKeys(Password);
				test.log(LogStatus.PASS, "Password is selected as "+Password);
				
				// name="finish"
				driver.findElement(By.name("finish")).click();																				
				test.log(LogStatus.PASS, "Clicked on Finish button ");
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

				//driver.findElement(By.name("ok")).click();

// name="checkyes"
				// if(driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).isDisplayed())
				if(driver.findElement(By.name("checkyes")).isDisplayed())
				{
					test.log(LogStatus.INFO, "cure payment completed  Transaction with-SSN: " +SSN +" :: is Successful");
					//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr/td[1]/input")).click();
					driver.findElement(By.name("checkyes")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "cure payment completed  is not Completed Successfully ");
				}


			
		
			}
		}

	}
}



public void EOD_BatchProcess_DueDate(String SSN,String FileName,int days,int InstNum) throws Exception

{

	Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);

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

			//driver.findElement(By.name("button")).click();
			//driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input")).click();
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			test.log(LogStatus.PASS, "Click on GO Button");

			for(String winHandle : driver.getWindowHandles()){

				driver.switchTo().window(winHandle);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");


			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			//driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();


			//driver.findElement(By.name("button")).click();
			test.log(LogStatus.PASS, "Click on GO Button");
			for( String winHandle1 : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.switchTo().frame("main");
			driver.findElement(By.name("transactionList")).sendKeys("History");
			/*if(ProductID.equals("ILP"))
{

	//driver.findElement(By.xpath("//*[@id='go_Button']")).click(); 
}
			 */
			driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();

			for( String winHandle1 : driver.getWindowHandles())

			{

				driver.switchTo().window(winHandle1);

			}

			driver.switchTo().defaultContent();

			driver.switchTo().frame("mainFrame");

			driver.switchTo().frame("main");

			String DueDate=null;

			/*List<WebElement> options = driver.findElements(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr"));
int schsize = options.size();*/


			/*
for(int cnt=2; cnt<=InstNum; cnt++)
{
			 */


			DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr["+InstNum+"]/td[2]")).getText();

			// //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
			test.log(LogStatus.INFO, "DueDate Capture is ::"+DueDate);	



			/*}
			 */
			Thread.sleep(1000);
			//*[@id="transactionHistoryTable"]/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]

			test.log(LogStatus.PASS, "DueDate:" + DueDate);


			//DueDate = driver.findElement(By.xpath("//*[@id='myTable1']/tbody[2]/tr[3]/td[2]")).getText();

			//DueDate=driver.findElement(By.xpath("//*[@id='revolvingCreditHistTable']/tbody/tr[4]/td[3]/span[2]")).getText();

			System.out.print(DueDate);

			driver.close();

			driver = new InternetExplorerDriver();

			driver.get(AdminURL);

			//storeupdate(UserName,Password,StoreID,DueDate,AdminURL);

			DateFormat df=new SimpleDateFormat("MM/dd/yyyy");

			driver.findElement(By.name("loginRequestBean.userId")).sendKeys("admin");

			test.log(LogStatus.PASS, "Username is entered: admin");

			driver.findElement(By.name("loginRequestBean.password")).sendKeys(Password);

			test.log(LogStatus.PASS, "Password is entered: "+Password);

			//Click Login Button

			driver.findElement(By.name("login")).click();

			test.log(LogStatus.PASS, "Clicked on Submit button");

			Thread.sleep(8000);

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
			test.log(LogStatus.INFO, "EOD Batch Process Completed");


		}

	}

}




public void Payliance_OriginationFile1(String SSN,String FileName,int Days,int instnum) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/CO_ILP/"+FileName);	
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
			


			if(ProductID.equals("ILP"))
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
			if(ProductID.equals("ILP"))
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
			DueDate = driver.findElement(By.xpath("//*[@id='PPNScheduleHistoryTable']/tbody/tr["+instnum+"]/td[2]")).getText();
			                                          
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
			driver.findElement(By.linkText("Payliance")).click();
			test.log(LogStatus.PASS, "Clicked on Payliance");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Payliance Origination File")).click();
			test.log(LogStatus.PASS, "Clicked on Payliance Origination File");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		/*	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Daily Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
			Thread.sleep(5000);*/

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

Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);	
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
		driver.findElement(By.linkText("Installment Loan")).click();
		test.log(LogStatus.PASS, "Clicked on Installment Loan");
		
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
					driver.findElement(By.name("requestBean.rtnReasonId")).sendKeys("R01-Insufficient Funds");
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




public void Payliance_OriginationFile(String SSN,String FileName,int Days,int i) throws Exception
{

	Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/"+FileName);	
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
			


			if(ProductID.equals("ILP"))
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
			if(ProductID.equals("ILP"))
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
			DueDate = driver.findElement(By.xpath("//*[@id='ContractScheduleTable']//tbody/tr["+i+"]/td[2]")).getText();
			   // //*[@id="ContractScheduleTable"]/tbody/tr[2]/td[2]
			// //*[@id="ContractScheduleTable"]/tbody/tr[3]/td[2]
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
			driver.findElement(By.linkText("Payliance")).click();
			test.log(LogStatus.PASS, "Clicked on Payliance");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Payliance Origination File")).click();
			test.log(LogStatus.PASS, "Clicked on Payliance Origination File");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		/*	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
			driver.findElement(By.linkText("Daily Jobs")).click();
			test.log(LogStatus.PASS, "Clicked on Daily Jobs");
			Thread.sleep(5000);*/

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




	//@Test(priority = 0)
	public void Loan_PaymentinCSRPortel() throws Exception {

		// Start test. Mention test script name
		String FileName = "AA_Loan_Payments_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		String sheetName = "NewLoan";
		// int lastrow=TestData.getLastRow("Borrower");
		System.out.println(lastrow);
		for (int row = 2; row <= lastrow; row++) {
			String RunFlag = TestData.getCellData(sheetName, "Run", row);
			// System.out.println(RunFlag);
			if (RunFlag.equals("Y")) {
				// driver.get(appUrl);
				// test.log(LogStatus.INFO, "Application is launched");
				// driver.manage().window().maximize();
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
				String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String StateID = TestData.getCellData(sheetName, "StateID", row);
				String SSN = TestData.getCellData(sheetName, "SSN", row);
				String Header = StateID + "_" + ProductID;
				// System.out.println(SSN);
				test = reports.startTest(Header + "_S.No:25" + "_" + PayFrequency + "_" + CollateralType,"Loan>select payment option from CSR dropdown> Payment screen ");
				appUrl = AppURL;
				
				    CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
	
			        this.NewLoan_ILP(SSN, FileName);
			        this.AgeStore(SSN, FileName, -5);
				    this.Payoff(SSN, FileName);
				    this.History_Paymentcaluculation(SSN, FileName);

			}
		}

	}
	
	
	//@Test(priority = 1)
	public void AA_Loan_1stInstallmentPayments() throws Exception {

		// Start test. Mention test script name
		String FileName = "AA_Loan_1stInstallmentPayments_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		String sheetName = "NewLoan";
		// int lastrow=TestData.getLastRow("Borrower");
		System.out.println(lastrow);
		for (int row = 2; row <= lastrow; row++) {
			String RunFlag = TestData.getCellData(sheetName, "Run", row);
			// System.out.println(RunFlag);
			if (RunFlag.equals("Y")) {
				// driver.get(appUrl);
				// test.log(LogStatus.INFO, "Application is launched");
				// driver.manage().window().maximize();
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
				String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String StateID = TestData.getCellData(sheetName, "StateID", row);
				String SSN = TestData.getCellData(sheetName, "SSN", row);
				String Header = StateID + "_" + ProductID;
				// System.out.println(SSN);
				test = reports.startTest(Header + "_S.No:26" + "_" + PayFrequency + "_" + CollateralType,"Loan>1# payment on due date>payoff");
				appUrl = AppURL;
				
				    CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
			        this.NewLoan_ILP(SSN, FileName);
			        this.AgeStore_1stInstallment(SSN, FileName, 0);
				    this.Payoff(SSN, FileName);
				    this.History_Paymentcaluculation(SSN, FileName);

			}
		}

	}
	
	///@Test(priority = 2)
	public void AA_Loan_1stInstallmentPaymentsUpto3rdInstallments() throws Exception {

		// Start test. Mention test script name
		String FileName = "AA_Loan_1stInstallmentPaymentsto3rdInsatllmentPayment_Txn_Testdata.xls";
		Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		String sheetName = "NewLoan";
		// int lastrow=TestData.getLastRow("Borrower");
		System.out.println(lastrow);
		for (int row = 2; row <= lastrow; row++) {
			String RunFlag = TestData.getCellData(sheetName, "Run", row);
			// System.out.println(RunFlag);
			if (RunFlag.equals("Y")) {
				// driver.get(appUrl);
				// test.log(LogStatus.INFO, "Application is launched");
				// driver.manage().window().maximize();
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String UserName = TestData.getCellData(sheetName, "UserName", row);
				String Password = TestData.getCellData(sheetName, "Password", row);
				String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
				String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
				// System.out.println(Password);
				String StoreId = TestData.getCellData(sheetName, "StoreID", row);
				String ProductID = TestData.getCellData(sheetName, "ProductID", row);
				String StateID = TestData.getCellData(sheetName, "StateID", row);
				String SSN = TestData.getCellData(sheetName, "SSN", row);
				String Header = StateID + "_" + ProductID;
				// System.out.println(SSN);
				test = reports.startTest(Header + "_S.No:27" + "_" + PayFrequency + "_" + CollateralType,"Loan>1# payment on due date>2#payment on due date>payoff(consider loan has only three installements)");
				appUrl = AppURL;
				
				   CSRLoginpage login = new CSRLoginpage();
			        login.Login(UserName, Password, StoreId, driver, AppURL, test);
			        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
			        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
			        this.NewLoan_ILP_NegAmt(SSN, FileName, "120");
			      
			        this.AgeStore_1stInstallment(SSN, FileName, 0);
				    this.Payoff(SSN, FileName);
				    this.AgeStore_2ndInstallment(SSN, FileName, 0);
				    this.Payoff_Installments(SSN, FileName);
				    this.AgeStore_3rdInstallment(SSN, FileName, 0);
				    this.Payoff_Installments(SSN, FileName);
				    this.History_Paymentcaluculation(SSN, FileName);

			}
		}

	}
	
	//@Test(priority = 3)
		public void Loan_PaymentinCSRPortel_Void() throws Exception {

			// Start test. Mention test script name
			String FileName = "AA_Loan_Payment_Void_Txn_Testdata.xls";
			Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
			int lastrow = TestData.getLastRow("NewLoan");
			String sheetName = "NewLoan";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String UserName = TestData.getCellData(sheetName, "UserName", row);
					String Password = TestData.getCellData(sheetName, "Password", row);
					String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
					String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
					// System.out.println(Password);
					String StoreId = TestData.getCellData(sheetName, "StoreID", row);
					String ProductID = TestData.getCellData(sheetName, "ProductID", row);
					String StateID = TestData.getCellData(sheetName, "StateID", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					String Header = StateID + "_" + ProductID;
					// System.out.println(SSN);
					test = reports.startTest(Header + "_S.No:28" + "_" + PayFrequency + "_" + CollateralType,"Loan>select payment option from CSR dropdown> Payment screen should show the follwing options  1.Pay Off the balance 2.Pay Installment Am t3.Pay any other Amt>select payoff balance>Enter amount>");
					appUrl = AppURL;
					
					    CSRLoginpage login = new CSRLoginpage();
				        login.Login(UserName, Password, StoreId, driver, AppURL, test);
				        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
				        this.NewLoan_ILP(SSN, FileName);
				        this.AgeStore(SSN, FileName, -5);
					    this.Payoff(SSN, FileName);
					    this.Void(SSN, FileName);
					    this.History_Paymentcaluculation(SSN, FileName);
					    

				}
			}

		}
	
	//@Test(priority = 4)
		public void AA_Loan_1stInstallmentPayments_Void() throws Exception {

			// Start test. Mention test script name
			String FileName = "AA_Loan_1stInstallmentPayments_Void_Txn_Testdata.xls";
			Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
			int lastrow = TestData.getLastRow("NewLoan");
			String sheetName = "NewLoan";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String UserName = TestData.getCellData(sheetName, "UserName", row);
					String Password = TestData.getCellData(sheetName, "Password", row);
					String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
					String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
					// System.out.println(Password);
					String StoreId = TestData.getCellData(sheetName, "StoreID", row);
					String ProductID = TestData.getCellData(sheetName, "ProductID", row);
					String StateID = TestData.getCellData(sheetName, "StateID", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					String Header = StateID + "_" + ProductID;
					// System.out.println(SSN);
					test = reports.startTest(Header + "_S.No:29" + "_" + PayFrequency + "_" + CollateralType,"Loan>1# payment on due date>void>payoff>void");
					appUrl = AppURL;
					
					    CSRLoginpage login = new CSRLoginpage();
				        login.Login(UserName, Password, StoreId, driver, AppURL, test);
				        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
				        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
				        this.NewLoan_ILP(SSN, FileName);
				        this.AgeStore_1stInstallment(SSN, FileName, 0);
					    this.Payoff(SSN, FileName);
					    this.History_Paymentcaluculation(SSN, FileName);

				}
			}

		}
	
		//@Test(priority = 5)
				public void AA_Loan_1stInstallmentPayments_2ndInstallmentPayment_Void_batchProcess_2ndInstallmentPayment_Void() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan_1stInstallmentPayments_EODbatchprocess_to3rdInsatllmentPayment_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:30" + "_" + PayFrequency + "_" + CollateralType,"Loan>1# payment on due date>2#payment on due date>void>run EOD batch process on 10th day of 2# >payoff(consider loan has only three installements)>void");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 0);
							    this.Payoff(SSN, FileName);
							    this.History_Paymentcaluculation(SSN, FileName);

						}
					}

				}
		
			//	@Test(priority = 6)
				public void AA_Loan_ACHRevoke_1stInsatallmentDeposit() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan_ACHRevoke__1stInsatllmentDeposit_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:38" + "_" + PayFrequency + "_" + CollateralType,"Loan>ach revoke >1# deposit");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        this.ACH_Revoke(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, -1);
						        this.DrawerDeassign(SSN, FileName);
						        this.EODProcessing_with_recordsChecking(SSN, FileName); 
						       // this.StoreInfo(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        
						}
					}

				}
				
				//@Test(priority = 7)
				public void AA_Loan_ACHRevoke_1stInsatallmentPayment_ACHAuthorization_2ndIsatallmentDeposit() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan_ACHRevoke__1stInsatllmentPayment_ACHAuthorigation_ACHDeposit_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:39" + "_" + PayFrequency + "_" + CollateralType,"Loan>ach revoke >1# payment>ach auth>2# deposit");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        this.ACH_Revoke(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 0);
						        this.Payment_ILP(SSN, FileName); 
							    this.ACHAuthorization(SSN, FileName);
							    this.AgeStore_2ndInstallment(SSN, FileName, -1);
							    this.DrawerDeassign(SSN, FileName);
							    this.StatementGeneration_EODProcessing(SSN, FileName);  
							    this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.Payliance_OriginationFile(SSN, FileName, -1);
						        this.ACH_Deposit(SSN, FileName, 0);
							 
						}
					}

				}
				
				//@Test(priority = 8)
				public void AA_Loan_ACHRevoke_1stInsatallmentDeposit_Clear() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan_ACHRevoke__1stInsatllmentDeposit_Clear_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:44" + "_" + PayFrequency + "_" + CollateralType,"Laon>1# deposit>Clear");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, -1);
							    this.DrawerDeassign(SSN, FileName);
							    this.StatementGeneration_EODProcessing(SSN, FileName);  
							    this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.Payliance_OriginationFile(SSN, FileName, -1);
						        this.ACH_Deposit(SSN, FileName, 0);
							    this.EOD_BatchProcess_DueDate(SSN, FileName, 8, 2);
							 
						}
					}

				}	
				
				//@Test(priority = 9)
				public void AA_Loan1stInsatallmentEODbatchprocess_Cure_Default() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan1stInsatallmentEODbatchprocess_Cure_Default_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:47" + "_" + PayFrequency + "_" + CollateralType,"Laon>Run EOD batch process on 1# due date>Run EOD Batch process(due date+10 days)>Run EOD batch process on Cure end date");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 31, 2);
						     	this.History_Paymentcaluculation(SSN, FileName);
						        
						      
							 
						}
					}

				}	
				
				
				//@Test(priority = 10)
				public void AA_Loan1stInsatallmentEODbatchprocess_Cure_BeforeCureenddateDefault() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan1stInsatallmentEODbatchprocess_Cure_BeforecureendDateDefault_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:48" + "_" + PayFrequency + "_" + CollateralType,"Laon>Run EOD batch process on 1# due date>Run EOD Batch process(due date+10 days)>Run EOD batch process on Before Cure end date");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        if(CollateralType.contains("CASH")){
						      
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
						        this.DeliquentPaymentStatus(SSN, FileName);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 28, 2);
						        }
						        if(CollateralType.contains("ACH")){
						        this.AgeStore_1stInstallment(SSN, FileName, 0);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 10);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 28);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        }
						     	this.History_LoanStatus(SSN, FileName);
						        
						      
							 
						}
					}

				}		
				
				//@Test(priority = 11)
				public void AA_Loan1stInsatallmentEODbatchprocess_Cure_1stInsatllPayment_CureenddateDefault() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan1stInsatallmentEODbatchprocess_Cure_IstInsatllPMT_CureEndDateEODbatchProcess_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:50" + "_" + PayFrequency + "_" + CollateralType,"Laon>Run EOD batch process on 1# due date>Run EOD Batch process(due date+10 days)>payment(1# amount)>Run EODbatch process on Cure end date");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        if(CollateralType.contains("CASH")){
						      
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
						        this.DeliquentPaymentStatus(SSN, FileName);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
						        this.AgeStore_1stInstallment(SSN, FileName, 31);
						        this.Payment_ILP(SSN, FileName);
						        this.PaymentcureAmount_ILP(SSN, FileName);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 31, 2);
						        }
						        if(CollateralType.contains("ACH")){
						        this.AgeStore_1stInstallment(SSN, FileName, 0);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 10);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 31);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        }
						     	this.History_LoanStatus(SSN, FileName);
						        
						      
							 
						}
					}

				}
				
				//@Test(priority = 12)
				public void AA_Loan1stInsatallmentEODbatchprocess_Cure_1stInsatllPayment_void_CureenddateDefault() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan1stInsatallmentEODbatchprocess_Cure_IstInsatllPMT_void_CureEndDateEODbatchProcess_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:51" + "_" + PayFrequency + "_" + CollateralType,"Laon>Run EOD batch process on 1# due date>Run EOD Batch process(due date+10 days)>payment(1# amount)>void>Run EOD batch process on Cure end date");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        if(CollateralType.contains("CASH")){
						      
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
						        this.DeliquentPaymentStatus(SSN, FileName);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
						        this.AgeStore_1stInstallment(SSN, FileName, 31);
						        this.Payment_ILP(SSN, FileName);
						        this.PaymentcureAmount_ILP(SSN, FileName);
						        this.Void_Payment(SSN, FileName);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 31, 2);
						        }
						        if(CollateralType.contains("ACH")){
						        this.AgeStore_1stInstallment(SSN, FileName, 0);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 10);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 31);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        }
						        this.History_Paymentcaluculation(SSN, FileName);
						        
						      
							 
						}
					}

				}
				
				//@Test(priority = 13)
				public void AA_Loan1stInsatallmentEODbatchprocess_Cure_Less1stInsatllPayment_CureenddateDefault() throws Exception {

					// Start test. Mention test script name
					String FileName = "AA_Loan1stInsatallmentEODbatchprocess_Cure_IstInsatllLessPMT_CureEndDateEODbatchProcess_Txn_Testdata.xls";
					Excel TestData = new Excel("E:/QC_Workspace/AA_Automation/TestData/ILP/" + FileName);
					int lastrow = TestData.getLastRow("NewLoan");
					String sheetName = "NewLoan";
					// int lastrow=TestData.getLastRow("Borrower");
					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {
						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {
							// driver.get(appUrl);
							// test.log(LogStatus.INFO, "Application is launched");
							// driver.manage().window().maximize();
							String AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String UserName = TestData.getCellData(sheetName, "UserName", row);
							String Password = TestData.getCellData(sheetName, "Password", row);
							String PayFrequency = TestData.getCellData(sheetName, "Income_PayFrequency", row);
							String CollateralType = TestData.getCellData(sheetName, "ESign_CollateralType", row);
							// System.out.println(Password);
							String StoreId = TestData.getCellData(sheetName, "StoreID", row);
							String ProductID = TestData.getCellData(sheetName, "ProductID", row);
							String StateID = TestData.getCellData(sheetName, "StateID", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);
							String Header = StateID + "_" + ProductID;
							// System.out.println(SSN);
							test = reports.startTest(Header + "_S.No:52" + "_" + PayFrequency + "_" + CollateralType,"Laon>Run EOD batch process on 1# due date>Run EOD Batch process(due date+10 days)>payment(less than1# amount)>Run EOD batch process on Cure end date");
							appUrl = AppURL;
							
							    CSRLoginpage login = new CSRLoginpage();
						        login.Login(UserName, Password, StoreId, driver, AppURL, test);
						        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
						        Reg.RegistrationPage_NewLoan_ILP(driver, test,AppURL, SSN,FileName);
						        this.NewLoan_ILP(SSN, FileName);
						        if(CollateralType.contains("CASH")){
						      
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 0, 2);
						        this.DeliquentPaymentStatus(SSN, FileName);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 2);
						        this.AgeStore_1stInstallment(SSN, FileName, 31);
						        this.PaymentLess_ILP(SSN, FileName);
						        this.EOD_BatchProcess_DueDate(SSN, FileName, 31, 2);
						        }
						        if(CollateralType.contains("ACH")){
						        this.AgeStore_1stInstallment(SSN, FileName, 0);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 10);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        this.AgeStore_1stInstallment(SSN, FileName, 31);
						        this.DrawerDeassign(SSN, FileName);
						        this.StatementGeneration_EODProcessing(SSN, FileName);
						        this.Safeassign(SSN, FileName);
						        this.Drawerassign(SSN, FileName);
						        }
						        this.History_Paymentcaluculation(SSN, FileName);
						        
						      
							 
						}
					}

				}
				
			//	@Test (priority=14)

				public void Loan_Deposit_Rtn_payoff() throws Exception {
					
					// Start test. Mention test script name
					String FileName= "AA_Loan1stInsatallmentDeposit_Return_Payoff_Txn_Testdata.xls";
					Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);  	
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
							
						
						    String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
							String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
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
					        test = reports.startTest(Header+"_S.No:31"+"_"+PayFrequency+"_"+CollateralType,"Loan>1#depsoit >rtn>payoff>");
					        appUrl = AppURL;
					        this.Login(UserName,Password,StoreId);
					        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					        Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
					         this.NewLoan_ILP(SSN, FileName);
					         this.AgeStore(SSN, FileName, -1);
					         this.DrawerDeassign(SSN, FileName);
					         this.StatementGeneration_EODProcessing(SSN, FileName);
					         this.Safeassign(SSN, FileName);
					         this.Drawerassign(SSN, FileName);
					         this.Payliance_OriginationFile(SSN, FileName, -1);
					         this.ACH_Deposit(SSN, FileName, 0);
					         this.AgeStore_1stInstallment(SSN, FileName, 8);
					         this.ACHReturnPosting(SSN, FileName);
					         this.Payoff_Return(SSN, FileName);
					         this.History_Payoffcaluculation(SSN, FileName);
					}       
					}
					

						}
				
				//@Test (priority=15)

				public void Loan_Deposit_Rtn_Payoff_Void() throws Exception {
					
					// Start test. Mention test script name
					String FileName= "AA_Loan1stInsatallmentDeposit_Return_Payoff_void_Txn_Testdata.xls";
					Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);  	
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
							
						
						    String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
							String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
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
					        test = reports.startTest(Header+"_S.No:33"+"_"+PayFrequency+"_"+CollateralType,"Loan>1#depsoit >rtn>payoff>void");
					        appUrl = AppURL;
					        this.Login(UserName,Password,StoreId);
					        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					        Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
					         this.NewLoan_ILP(SSN, FileName);
					         this.AgeStore(SSN, FileName, -1);
					         this.DrawerDeassign(SSN, FileName);
					         this.StatementGeneration_EODProcessing(SSN, FileName);
					         this.Safeassign(SSN, FileName);
					         this.Drawerassign(SSN, FileName);
					         this.Payliance_OriginationFile(SSN, FileName, -1);
					         this.ACH_Deposit(SSN, FileName, 0);
					         this.AgeStore_1stInstallment(SSN, FileName, 8);
					         this.ACHReturnPosting(SSN, FileName);
					         this.Payoff_Return(SSN, FileName);
					         this.Void(SSN, FileName);
					         this.History_Payoffcaluculation(SSN, FileName);
					}       
					}
					

						}
				
				//@Test (priority=16)

				public void Loan_Deposit_Rtn_clear_2InstDeposit_Return_EODBatchprocess_payoff() throws Exception {
					
					// Start test. Mention test script name
					String FileName= "AA_Loan1stInsatallmentDeposit_2ndInstDeposit_Return_EodBatch_3rdInst_Payoff_Txn_Testdata.xls";
					Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);  	
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
							
						
						    String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
							String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
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
					        test = reports.startTest(Header+"_S.No:32"+"_"+PayFrequency+"_"+CollateralType,"Loan>1#deposit >clr>2# dep>rtn>run EOD batch process on 10th from 2# due date>payoff>age the store date to one day before 3# due date(banking day)>run EOD");
					        appUrl = AppURL;
					        this.Login(UserName,Password,StoreId);
					        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					        Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
					         this.NewLoan_ILP(SSN, FileName);
					         this.AgeStore(SSN, FileName, -1);
					         this.DrawerDeassign(SSN, FileName);
					         this.StatementGeneration_EODProcessing(SSN, FileName);
					         this.Safeassign(SSN, FileName);
					         this.Drawerassign(SSN, FileName);
					         this.Payliance_OriginationFile(SSN, FileName, -1);
					         this.ACH_Deposit(SSN, FileName, 0);
					         this.EOD_BatchProcess_DueDate(SSN, FileName, 8, 2);
					         this.AgeStore_2ndInstallment(SSN, FileName, -1);
					         this.DrawerDeassign(SSN, FileName);
					         this.StatementGeneration_EODProcessing(SSN, FileName);
					         this.Safeassign(SSN, FileName);
					         this.Drawerassign(SSN, FileName); 
					         this.Payliance_OriginationFile(SSN, FileName, -1, 3);
					         this.ACH_Deposit(SSN, FileName, 0);
					         this.ACHReturnPosting(SSN, FileName);
					         this.EOD_BatchProcess_DueDate(SSN, FileName, 10, 3);
					         this.Payoff_Return(SSN, FileName);
					         this.AgeStore_3rdInstallment(SSN, FileName, -1);
					         this.DrawerDeassign(SSN, FileName);
					         this.StatementGeneration_EODProcessing(SSN, FileName);
					         this.Safeassign(SSN, FileName);
					         this.Drawerassign(SSN, FileName);
					         this.History_Payoffcaluculation_3rdInst(SSN, FileName);
					}       
					}
					

						}	
				
				
				@Test (priority=17)

				public void Loan_1stInstDeposit_Rtn() throws Exception {
					
					// Start test. Mention test script name
					String FileName= "AA_Loan_1stInsatallmentDeposit_Return_Txn_Testdata.xls";
					Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/ILP/"+FileName);  	
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
							
						
						    String PayFrequency = TestData.getCellData(sheetName,"Income_PayFrequency",row);
							String CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
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
					        test = reports.startTest(Header+"_S.No:41"+"_"+PayFrequency+"_"+CollateralType,"Laon>1# deposit>Return");
					        appUrl = AppURL;
					        this.Login(UserName,Password,StoreId);
					        BorrowerRegistrationpage Reg = new BorrowerRegistrationpage();
					        Reg.RegistrationPage_NewLoan_ILP(driver, test, AppURL, SSN, FileName);
					         this.NewLoan_ILP(SSN, FileName);
					         this.AgeStore(SSN, FileName, -1);
					         this.DrawerDeassign(SSN, FileName);
					         this.StatementGeneration_EODProcessing(SSN, FileName);
					         this.Safeassign(SSN, FileName);
					         this.Drawerassign(SSN, FileName);
					         this.Payliance_OriginationFile(SSN, FileName, -1);
					         this.ACH_Deposit(SSN, FileName, 0);
					         this.ACHReturnPosting(SSN, FileName);
					         this.History_LoanStatus(SSN, FileName);
					         
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

// *[@id="revolvingCreditHistTable"]/tbody/tr[6]/td[3]/span[2]

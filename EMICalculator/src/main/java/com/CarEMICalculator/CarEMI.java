package com.CarEMICalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.MavenEMICalculator.BaseMethods;
import com.aventstack.extentreports.Status;

public class CarEMI extends BaseMethods {

	public static String s = Keys.chord(Keys.CONTROL, "a");

	@BeforeTest
	// Open browser and navigate to the test URL
	public void openBrowser() {
		logger = report.createTest("Browser Initialization");
		invokeBrowser("chrome");
		logger.log(Status.INFO, "Browser opened Successfully");
		openURL("webURL");
		logger.log(Status.PASS, "Test Passed");
	}

	@Test
	// Send the Car Loan values
	public void carEMIForm() throws IOException {
		logger = report.createTest("Car Loan Calculation");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		elementClick("carloanmenu_Id");

		File file = new File(".\\Test_ip_data.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		// String[] a=new String[3];

		XSSFRow row2 = sheet.getRow(1);
		XSSFCell cell = row2.getCell(0);
		String a1 = cell.getStringCellValue();

		XSSFCell cell1 = row2.getCell(1);
		double a2 = cell1.getNumericCellValue();
		String a3 = Double.toString(a2);

		XSSFCell cell2 = row2.getCell(2);
		int a4 = (int) cell2.getNumericCellValue();
		String a5 = Integer.toString(a4);

		wb.close();

		getElement("loanamount_Id").sendKeys(s);
		getElement("loanamount_Id").sendKeys(Keys.DELETE);
		getElement("loanamount_Id").sendKeys(a1);
		getElement("loanamount_Id").sendKeys(Keys.ENTER);

		getElement("loaninterest_Id").sendKeys(s);
		getElement("loaninterest_Id").sendKeys(Keys.DELETE);
		getElement("loaninterest_Id").sendKeys(a3);
		getElement("loaninterest_Id").sendKeys(Keys.ENTER);

		getElement("loanterm_Id").sendKeys(s);
		getElement("loanterm_Id").sendKeys(Keys.DELETE);
		getElement("loanterm_Id").sendKeys(a5);
		getElement("loanterm_Id").sendKeys(Keys.ENTER);

		logger.log(Status.INFO, "Car Loan Calculated");
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(dependsOnMethods = { "carEMIForm" })
	// Print One month Principle Amount and Interest Amount
	public void getdata() throws Exception {

		logger = report.createTest("Car EMI Calculation");
		System.out.println("****************Car EMI Calculation*************************");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// toggle towards the payment table on the window
//		WebElement x = driver.findElement(By.xpath("//*[@id='yearheader']"));
//		js.executeScript("arguments[0].scrollIntoView();", x);
		js.executeScript("window.scrollBy(0,1800)");

		// click on current year(2022) to unfold each month details
		WebElement ee = driver.findElement(By.xpath("//*[@id='year2022']"));
		ee.click();

		// finding the principal amount of first month and retrieve the value
		WebElement alt = driver.findElement(
				By.cssSelector("#monthyear2022 > td > div > table > tbody > tr:nth-child(1) > td:nth-child(2)"));
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", alt);

		// finding the interest amount of first month and retrieve the value
		WebElement a = driver.findElement(By.xpath("//*[@id='monthyear2022']/td/div/table/tbody/tr[1]/td[3]"));
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", a);
		
		Thread.sleep(500);
		String PA = getElement("PA_cssSelector").getText();
		String[] principle = PA.split("\\s+");
		String IA = getElement("IA_cssSelector").getText();
		String[] interest = IA.split("\\s+");
		String rupees = "Rs. ";
		System.out.println("The Principal Amount for One Month is:" + rupees.concat(principle[1]));
		System.out.println("The Interest Amount for One Month is:" + rupees.concat(interest[1]));
		logger.log(Status.INFO, "EMI Calculated for a month");
		logger.log(Status.PASS, "Test Passed");
	}

	@AfterTest
	// navigate to next page
	public void close() {
		closeBrowser();
	}

	@AfterTest
	public void endReport() {
		report.flush();
	}
}
package com.HomeEMICalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.text.DateFormat;
import java.util.List;
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
import com.utils.DateUtils;

public class HomeEMI extends BaseMethods {

	@BeforeTest
	// Open browser and navigate to the test URL
	public void openBrowser() {
		logger = report.createTest("Browser Initialization");
		invokeBrowser("chrome");
		logger.log(Status.INFO, "Browser opened Successfully");
		openURL("webURL");
		logger.log(Status.PASS, "Test Passed");
		//navigate_homemi();
}
	
	@Test
	// Navigate to Home Loan EMI Calculator
	public void homeemi() {
		logger = report.createTest("Navigate to Home Loan EMI Calculator");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		elementClick("calculatormenu_Xpath");
		elementClick("homeemicalculator_Xpath");
		driver.navigate().back();
		driver.navigate().forward();
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority=1)

	public void dataexport() throws IOException {
		logger = report.createTest("Home Loan Calculation");
		System.out.println("*********************Home Loan Calculation************************");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		File file =    new File(".\\Test_ip_data.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook wb1=new XSSFWorkbook(inputStream);
        XSSFSheet sheet1=wb1.getSheet("Sheet1");
		
        XSSFRow row2=sheet1.getRow(3);     
        XSSFCell cell=row2.getCell(0);
        String a1= cell.getStringCellValue();
        
        XSSFCell cell1=row2.getCell(1);
        double a2= cell1.getNumericCellValue();
        String a3=Double.toString(a2);
   
        XSSFCell cell2=row2.getCell(2);     
        int a4= (int)cell2.getNumericCellValue();
        String a5=Integer.toString(a4);
        
        wb1.close();
        
        getElement("homevalue_Id").sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
		getElement("homevalue_Id").sendKeys(a1);
		getElement("homevalue_Id").sendKeys(Keys.ENTER);
		
        getElement("homeinterest_Id").sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
		getElement("homeinterest_Id").sendKeys(a3);
		getElement("homeinterest_Id").sendKeys(Keys.ENTER);
		
        getElement("homeloanterm_Id").sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
		getElement("homeloanterm_Id").sendKeys(a5);
		getElement("homeloanterm_Id").sendKeys(Keys.ENTER);
		
		
     // Create workbook instance that refers to .xlsx file
     	XSSFWorkbook wb = new XSSFWorkbook();
		
		// Create a Sheet object using the sheet Name
		XSSFSheet sheet = wb.createSheet("Amount");

		// Create a row object to retrieve row at index 1
		XSSFRow row = sheet.createRow(1);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1700)");
		List<WebElement> column = driver.findElements(By.xpath("//table[@class='noextras']//tbody/tr[1]/th"));
		int yearcount = Integer.parseInt(a5);
		yearcount = (yearcount * 2) + 2;
		
		for (int i = 1; i <= column.size(); i++) {
			String xpath = "//table[@class='noextras']/tbody/tr[1]/th[" + i + "]";
			String header = driver.findElement(By.xpath(xpath)).getText();
			row.createCell(i).setCellValue(header);
		}
		XSSFRow row1 = null;
		for (int i = 2; i <= yearcount; i++) {
			row1 = sheet.createRow(i);
			for (int j = 1; j <= column.size(); j++) {
				String xpath1 = "//table[@class='noextras']/tbody/tr[" + i + "]/td[" + j + "]";
				String data = driver.findElement(By.xpath(xpath1)).getText();

				row1.createCell(j).setCellValue(data);
			}
			i++;
		}
		String date1 = DateUtils.getTimeStamp();
		
		// Write the data in excel using output stream
		FileOutputStream outputStream = new FileOutputStream(new File(".\\"+date1+".xlsx"));
		wb.write(outputStream);
		wb.close();
		System.out.println("Data written successfully");
		logger.log(Status.INFO, "Data written successfully");
		logger.log(Status.INFO, "Home Loan Calculated");
		logger.log(Status.PASS, "Test Passed");
	}

	@AfterTest
	public void close() {
		quitBrowser();
	}

	@AfterTest
	public void endReport() {
		report.flush();
	}
}

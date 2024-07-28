	package com.MavenEMICalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utils.DateUtils;
import com.utils.ExtentReportManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseMethods {

	public WebDriver driver;
	public String directory = System.getProperty("user.dir");
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	/****************** Invoke Browser ***********************/
	public void invokeBrowser(String browserName) {
		
		if(prop == null)
		{
			prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(directory+ "/src/test/resources/configuration.properties");
			prop.load(file);
		}
		catch(Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
		}
		}
		
		String browser=prop.getProperty("browserName");
		//String main_url=prop.getProperty("url");
		
		if (prop.getProperty("browserName").matches("chrome")) {
			WebDriverManager.chromedriver().setup();
			//.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			driver = new ChromeDriver(); // Initializing the new chrome driver
		}

		// To Open MsEdge Browser
		if (prop.getProperty("browserName").matches("MsEdge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/****************** Open URL ***********************/
	public void openURL(String webURL) {
		try {
			driver.get(prop.getProperty(webURL));
			reportPass(webURL + " Identified Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/****************** Identify Element ***********************/
	public WebElement getElement(String locatorKey) {
		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			}
			
			else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			}
			
			else if (locatorKey.endsWith("_cssSelector")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			}
			
			else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();
		}

		return element;
	}
	

	/******************** Click Element ***********************/
	public void elementClick(String Key)
	{
		try {
			getElement(Key).click();
			reportPass(Key + " : Element Clicked Successfully");
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}
	}

	
	/****************** Element Slider Check ***********************/
	public void dragSlider(String Key) {
		try {
			WebElement slider = getElement(Key);
			Actions move = new Actions(driver);
			Action action = (Action) move.dragAndDropBy(slider, 30, 0).build();
			action.perform();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/****************** Close Browser ***********************/
	public void closeBrowser() {
		driver.close();
	}

	/****************** Quit Browser ***********************/
	public void quitBrowser() {
		driver.quit();
	}

	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(directory + "/Screenshots/" + DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(directory + "/Screenshots/" + DateUtils.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

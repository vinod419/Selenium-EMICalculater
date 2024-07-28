package com.UICheck;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.MavenEMICalculator.BaseMethods;
import com.aventstack.extentreports.Status;

public class EmiCalculator extends BaseMethods {

	@BeforeTest
	public void openBrowser() {
//Open browser and navigate to the test URL

		logger = report.createTest("Browser Initialization");
		invokeBrowser("chrome");
		logger.log(Status.INFO, "Browser opened Successfully");
		openURL("webURL");
		logger.log(Status.PASS, "Test Passed");
	}

	@Test
	public void loancalcnavigate() {
		logger = report.createTest("Loan Calculator Navigation");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		elementClick("calculatormenu_Xpath");
		elementClick("loancalculator_Xpath");
		driver.navigate().back();
		driver.navigate().forward();
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 1)
	public void print() {
		System.out.println("*********************Emi Calculator UI Check************************");
	}

	@Test(priority = 2, groups = "UICheck")
	public void loanamttextbox() {
		logger = report.createTest("Loan Amount Text Box");
		boolean iselementpresent = getElement("loanamount_Id").isDisplayed();
		if (iselementpresent == true) {
			System.out.println("Loan Amount Text Box is Visible");
		} else {
			System.out.println("Loan Amount Text Box is Not Visible");
		}
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 2, groups = "UICheck")
	public void loanamtslider() {
		logger = report.createTest("Loan Amount Slider");
		boolean iselementpresent = getElement("loanamountslider_Id").isDisplayed();
		if (iselementpresent == true) {
			System.out.println("Loan Amount Slider is Visible");
		} else {
			System.out.println("Loan Amount Slider is Not Visible");
		}
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 2, groups = "UICheck")
	public void loanamtslidercheck() {
		logger = report.createTest("Loan Amount Check Slider");
		dragSlider("loanamountslider_Id");
		System.out.println("Loan Amount Slider is Checked");
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 3, groups = "UICheck")
	public void interestratetextbox() {
		logger = report.createTest("Interest Rate Text Box");
		boolean iselementpresent = getElement("loaninterest_Id").isDisplayed();
		if (iselementpresent == true) {
			System.out.println("Interest Rate Text Box is Visible");
		} else {
			System.out.println("Interest Rate Text Box is Not Visible");
		}
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 3, groups = "UICheck")
	public void interestrateslider() {
		logger = report.createTest("Interest Rate Slider");
		boolean iselementpresent = getElement("loaninterestslider_Id").isDisplayed();
		if (iselementpresent == true) {
			System.out.println("Interest Rate Slider is Visible");
		} else {
			System.out.println("Interest Rate Slider is Not Visible");
		}
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 3, groups = "UICheck")
	public void interestrateslidercheck() {
		logger = report.createTest("Interest Rate Check Slider");
		dragSlider("loaninterestslider_Id");
		System.out.println("Interest Rate Slider is Checked");
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 4, groups = "UICheck")
	public void loantermtextbox() {
		logger = report.createTest("Loan Term Text Box");
		boolean iselementpresent = getElement("loanterm_Id").isDisplayed();
		if (iselementpresent == true) {
			System.out.println("Loan Tenure Text Box is Visible");
		} else {
			System.out.println("Loan Tenure Text Box is Not Visible");
		}
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 4, groups = "UICheck")
	public void loantermslider() {
		logger = report.createTest("Loan Tenure Slider");
		boolean iselementpresent = getElement("loantermslider_Id").isDisplayed();
		if (iselementpresent == true) {
			System.out.println("Loan Tenure Slider is Visible");
		} else {
			System.out.println("Loan Tenure Slider is Not Visible");
		}
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 4, groups = "UICheck")
	public void loantermslidercheck() {
		logger = report.createTest("Loan Term Check Slider");
		dragSlider("loantermslider_Id");
		System.out.println("Loan Tenure Slider is Checked");
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 5, groups = "UICheck")
	public void feechargestextbox() {
		logger = report.createTest("Fees & Charges Text Box");
		boolean iselementpresent = getElement("loanfees_Id").isDisplayed();
		if (iselementpresent == true) {
			System.out.println("Fees & Charges Text Box is Visible");
		} else {
			System.out.println("Fees & Charges Text Box is Not Visible");
		}
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 5, groups = "UICheck")
	public void feechargesslider() {
		logger = report.createTest("Fees & Charges Slider");
		boolean iselementpresent = getElement("loanfeesslider_Id").isDisplayed();
		if (iselementpresent == true) {
			System.out.println("Fees & Charges Slider is Visible");
		} else {
			System.out.println("Fees & Charges Slider is Not Visible");
		}
		logger.log(Status.PASS, "Test Passed");
	}

	@Test(priority = 5, groups = "UICheck")
	public void loanfeeslidercheck() {
		logger = report.createTest("Fees & Charges Check Slider");
		dragSlider("loanfeesslider_Id");
		System.out.println("Loan Fees Slider is Checked");
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
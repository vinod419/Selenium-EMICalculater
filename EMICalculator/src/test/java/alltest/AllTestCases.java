package alltest;

import org.testng.annotations.Test;


import com.CarEMICalculator.CarEMI;
import com.HomeEMICalculator.HomeEMI;
import com.MavenEMICalculator.BaseMethods;
import com.UICheck.EmiCalculator;
import com.UICheck.LoanAmountCalculator;
import com.UICheck.LoanTenureCalculator;


public class AllTestCases extends BaseMethods {
		
	CarEMI c= new CarEMI();
	HomeEMI h= new HomeEMI();
	EmiCalculator e=new EmiCalculator();
	LoanAmountCalculator la=new LoanAmountCalculator();
	LoanTenureCalculator lt=new LoanTenureCalculator();
	
	@Test(priority=0)
	public void open_browser() throws Exception {
		c.openBrowser();
	}
	
	@Test(priority=1)
	public void car_emi() throws Exception {
		
		c.carEMIForm();
		c.getdata();
		
		c.close();
		c.endReport();
	}
	
	@Test(priority=2)	
	public void home_emi() throws Exception {
		
		h.openBrowser();
		h.homeemi();
		h.dataexport();
		h.close();
		h.endReport();
	}
	
	@Test(priority=3)
	public void emi_calculator() throws Exception {
	
		e.openBrowser();
		e.loancalcnavigate();
		e.print();
		e.loanamttextbox();
		e.loanamtslider();
		e.loanamtslidercheck();
		
		e.interestratetextbox();
		e.interestrateslider();
		e.interestrateslidercheck();
		
		e.loanamttextbox();
		e.loanamtslider();
		e.loanamtslidercheck();
		
		e.feechargestextbox();
		e.feechargesslider();
		e.loanfeeslidercheck();
		e.close();
		e.endReport();
	}
	
	@Test(priority=4)
	public void loan_amount_calculator()throws Exception {
		
		la.openBrowser();
		la.loancalcnavigate();
		la.print();
		la.EmiTextBox();
		la.EmiSlider();
		la.EmiSliderCheck();
		
		la.interestratetextbox();
		la.interestrateslider();
		la.interestrateslidercheck();
		
		la.loantermtextbox();
		la.loantermslider();
		la.loantermslidercheck();
		
		la.feechargestextbox();
		la.feechargesslider();
		la.loanfeeslidercheck();
		
		la.close();
		la.endReport();
	}
	
	@Test(priority=5)
	public void loan_tenure_calculator() throws Exception{
		
		lt.openBrowser();
		lt.loancalcnavigate();
		lt.print();
		lt.loanamttextbox();
		lt.loanamtslider();
		lt.loanamtslidercheck();
		
		lt.EmiTextBox();
		lt.EmiSlider();
		lt.EmiSliderCheck();
		
		lt.interestratetextbox();
		lt.interestrateslider();
		lt.interestrateslidercheck();
		
		lt.feechargestextbox();
		lt.feechargesslider();
		lt.loanfeeslidercheck();
		
		lt.close();
		lt.endReport();
	}
}

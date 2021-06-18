package com.ems.payroll.utils;



import com.ems.payroll.entity.Salary;
import com.ems.payroll.model.response.SalaryResponse;


public class SalaryUtils {

	public static SalaryResponse calculateSalary(Salary salary){
		
		double hra = 0.2 * salary.getBasic();
		double pf =  0.11 * salary.getBasic();
		double grossSalary = salary.getBasic() + hra+salary.getAllowances()-pf;
		
		double incomeTax = incomeTax(grossSalary);
		
		SalaryResponse salaryDetails = new SalaryResponse();
		salaryDetails.setIncometax(incomeTax);
		salaryDetails.setAllowances(salary.getAllowances());
		salaryDetails.setHra(hra);
		salaryDetails.setPf(pf);
		salaryDetails.setBasic(salary.getBasic());
		salaryDetails.setBankAccount(salary.getBankAccount());
		salaryDetails.setBankName(salary.getBankName());
		salaryDetails.setPayPeriod(salary.getPayPeriod());
		salaryDetails.setPfNumber(salary.getPfNumber());
		
		return salaryDetails;
	}
	
	static double incomeTax(double i)
	{
		double tax = 0;	
		while(i > 250000) {
		if(i>1000000) {
			double temp = i - 1000000;
			tax = tax + (0.3 * temp);
			i = i - temp;
		}
		else if(i>500000) {
			double temp = i - 500000;
			tax = tax + (0.2 * temp);
			i  = i - temp;
		}
		else if(i>250000) {
			double temp = i - 250000;
			tax = tax + (0.05 * temp);
			i  = i - temp;
		}
		
		}
		return tax;
	 
	}
}
 
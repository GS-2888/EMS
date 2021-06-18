package com.ems.payroll.model.response;


public class SalaryResponse {

	private double basic;
	
	private double incometax;
	
	private double pf;
	
	private double allowances;
	
	private double hra;
	
	
	private String payPeriod;
	
	
	private String bankName;
	
	
	private String pfNumber;
	
	private String bankAccount;
	
	
	
	
	
	
	public String getPayPeriod() {
		return payPeriod;
	}





	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}





	public String getBankName() {
		return bankName;
	}





	public void setBankName(String bankName) {
		this.bankName = bankName;
	}





	public String getPfNumber() {
		return pfNumber;
	}





	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}





	public String getBankAccount() {
		return bankAccount;
	}





	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}



	
	
	public SalaryResponse() {
		
	}





	public double getBasic() {
		return basic;
	}






	public void setBasic(double basic) {
		this.basic = basic;
	}






	public double getIncometax() {
		return incometax;
	}



	public void setIncometax(double incometax) {
		this.incometax = incometax;
	}



	public double getPf() {
		return pf;
	}



	public void setPf(double pf) {
		this.pf = pf;
	}



	public double getAllowances() {
		return allowances;
	}



	public void setAllowances(double allowances) {
		this.allowances = allowances;
	}



	public double getHra() {
		return hra;
	}



	public void setHra(double hra) {
		this.hra = hra;
	}

	
}

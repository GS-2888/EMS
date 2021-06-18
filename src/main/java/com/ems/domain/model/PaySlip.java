package com.ems.domain.model;

public class PaySlip {
	
	private String name;
	private String payPeriod;
	private String grade;
	private String pan;
	private String bankName;
	private String netPay;
	private String pfNumber;
	private String bankAccount;
	private String basic;
	private String hra;
	private String allowances;
	private String pf;
	private String incometax;
	private String earningsTotal;
	private String deductionsTotal;
	
	public PaySlip() {
		
	}
	
	public String getAllowances() {
		return allowances;
	}

	public void setAllowances(String allowances) {
		this.allowances = allowances;
	}

	public PaySlip(String name,String payPeriod,String grade,String pan,String bankName,String netPay,
			String pfNumber,String bankAccount,String basic,String hra,String allowance,String pf,
			 String incomeTax,String earningsTotal,String deductionsTotal) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getNetPay() {
		return netPay;
	}

	public void setNetPay(String netPay) {
		this.netPay = netPay;
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

	public String getBasic() {
		return basic;
	}

	public void setBasic(String basic) {
		this.basic = basic;
	}

	public String getHra() {
		return hra;
	}

	public void setHra(String hra) {
		this.hra = hra;
	}

	

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	

	public String getIncometax() {
		return incometax;
	}

	public void setIncometax(String incometax) {
		this.incometax = incometax;
	}

	public String getEarningsTotal() {
		return earningsTotal;
	}

	public void setEarningsTotal(String earningsTotal) {
		this.earningsTotal = earningsTotal;
	}

	public String getDeductionsTotal() {
		return deductionsTotal;
	}

	public void setDeductionsTotal(String deductionsTotal) {
		this.deductionsTotal = deductionsTotal;
	}

}

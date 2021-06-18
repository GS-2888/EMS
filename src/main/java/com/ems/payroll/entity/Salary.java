package com.ems.payroll.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salary")
public class Salary {
	
	@Id	
	@Column(name="id")
	private int id;
	
	@Column(name="basic")
	private double basic;	
	
	@Column(name="allowances")
	private double allowances;		
	
	@Column(name="pay_period")
	private String payPeriod;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="pf_number")
	private String pfNumber;
	
	@Column(name="bank_account")
	private String bankAccount;
	
	
	

	public Salary() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getBasic() {
		return basic;
	}



	public void setBasic(double basic) {
		this.basic = basic;
	}



	public double getAllowances() {
		return allowances;
	}



	public void setAllowances(double allowances) {
		this.allowances = allowances;
	}

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

	
	}

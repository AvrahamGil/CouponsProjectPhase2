package com.gil.couponsproject.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	private long customerID;
	private long companyID;
	private String customerName;
	private String customerPassword;
	
	private List<Coupon> customerCoupons;

	

	public Customer() {

	}

	public Customer(long companyID ,String customerName, String customerPassword) {
		this.setCompanyID(companyID);
		this.setCustomerName(customerName);
		this.setCustomerPassword(customerPassword);
		
	}

	public Customer(long customerID,long companyID, String customerName, String customerPassword) {
		this.setCompanyID(companyID);
		this.setCustomerID(customerID);
		this.setCustomerName(customerName);
		this.setCustomerPassword(customerPassword);
	
	}

	public long getCustomerID() {
		return customerID;
	}
	public long getCompanyID() {
		return companyID;
	}
	public void setCompanyID(long companyID) {
		this.companyID=companyID;
	}
	
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public List<Coupon> getCustomerCoupons() {
		return customerCoupons;
	}

	public void setCustomerCoupons(List<Coupon> customerCoupons) {
		this.customerCoupons = customerCoupons;
	}

	
	public String toString() {
		return "Customer [customerID=" + customerID + ", companyID=" + companyID + ", customerName=" + customerName
				+ ", customerPassword=" + customerPassword + ", customerCoupons=" + customerCoupons + "]";
	}

	

	
	
	

}

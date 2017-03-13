package com.gil.couponsproject.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Company {

	private long companyID;
	private String companyName;
	private String companyPassword;
	private String companyEmail;
	private List<Coupon> companyCoupons;

	public Company() {

	}

	public Company(String companyName, String companyPassword, String companyEmail) {
		this.setCompanyName(companyName);
		this.setCompanyPassword(companyPassword);
		this.setCompanyEmail(companyEmail);
	}

	public Company(long companyID, String companyName, String companyPassword, String companyEmail) {
		this.setCompanyID(companyID);
		this.setCompanyName(companyName);
		this.setCompanyPassword(companyPassword);
		this.setCompanyEmail(companyEmail);
	}

	@XmlElement(name="companyID")
	public long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}
	@XmlElement(name="companyName")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@XmlElement(name="companyPassword")
	public String getCompanyPassword() {
		return companyPassword;
	}

	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}
	@XmlElement(name="companyEmail")
	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	@XmlElement(name="companyCoupons")
	public List<Coupon> getCompanyCoupons() {
		return companyCoupons;
	}

	public void setCompanyCoupons(List<Coupon> companyCoupons) {
		this.companyCoupons = companyCoupons;
	}

	public String toString() {
		return "Company ==>\tYou Work on Your Company In  DB\n " + "\nCompany name =" + companyName
				+ "\ncCompany password =" + companyPassword + "\ncCompany email =" + companyEmail + "]\n";
	}

}

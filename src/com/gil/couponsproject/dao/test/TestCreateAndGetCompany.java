package com.gil.couponsproject.dao.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.exception.ApplicationException;

public class TestCreateAndGetCompany {

	public static void main(String [] args) {
		Company company = new Company();
		TestCreateAndGetCompany testCreateAndGetCompany = new TestCreateAndGetCompany();
		//				create a company
		/*
		try {
			 testCreateAndGetCompany.createCompany(company);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//			get company details
		/*
		try {
			 testCreateAndGetCompany.getCompany(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// ---------------------- get all companies
		/*
		try {
			 testCreateAndGetCompany.getAllComanies();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		// ------------------ login 
		/*
		try {
			 testCreateAndGetCompany.loginCompany("GILCOMPANY" , "12345");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			testCreateAndGetCompany.getCustomer(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public  void createCompany (Company company) throws ApplicationException {
		CompanyDao companyDao= new CompanyDao();
		//put details in the company
		company.setCompanyName("SalsaCompany");
		company.setCompanyPassword("SalsaIsOurs");
		company.setCompanyEmail("salsa@hotmail.com");
		//create the company
		companyDao.createCompnay(company);
		
	}
	public  void getCompany (long companyID) throws ApplicationException {	
		CompanyDao companyDao= new CompanyDao();
		Company company = new Company();
		company= companyDao.getCompany(1);
		System.out.println(company);
	}
	public void getAllComanies() throws ApplicationException {
		CompanyDao companyDao= new CompanyDao();
		List<Company>listOfCompanies = companyDao.getListOfAllCompanies();
		System.out.println(listOfCompanies);
	}
	
	public  void loginCompany(String companyName,String companyPassword) throws ApplicationException {
		CompanyDao companyDao= new CompanyDao();
		companyDao.isCompanyExist(companyName , companyPassword);
		
	}
	public void getCustomer(long customerID)throws ApplicationException {
	CouponDao couponDao = new CouponDao();
	List<Coupon>listCoupon = new ArrayList<Coupon>();
	listCoupon=couponDao.getListOfCustomerCoupons(customerID);
	System.out.println(listCoupon);
	
	
		
	}

}


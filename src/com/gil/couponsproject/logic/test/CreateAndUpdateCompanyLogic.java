package com.gil.couponsproject.logic.test;

import java.text.ParseException;

import com.gil.couponsproject.api.CompanyApi;
import com.gil.couponsproject.api.CouponApi;
import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CompanyLogic;

public class CreateAndUpdateCompanyLogic {

	
	
	public static void main(String [] args) {
		Company company = new Company();
		CreateAndUpdateCompanyLogic  createAndUpdateCompanyLogic  = new CreateAndUpdateCompanyLogic ();
		
		
		//-------------------------------------create an new company
		
		try {
			createAndUpdateCompanyLogic.creareCompany(company);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ---------------------------------- update company
		/*
		try {
			createAndUpdateCompanyLogic.updateCompany(company);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public  void creareCompany(Company company) throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		company.setCompanyName("Gilush12567");
		company.setCompanyPassword("Yamiyugi1");
		company.setCompanyEmail("gilush@gmail.com");
		companyLogic.createCompany(company);
	}
	public void updateCompany (Company company) throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		
		company.setCompanyID(2);
		company.setCompanyName("gil company");
		company.setCompanyPassword("gilush");
		company.setCompanyEmail("yamiiiiiii@gmail.com");
		companyLogic.updateCompany(company);
	}
	
	
}

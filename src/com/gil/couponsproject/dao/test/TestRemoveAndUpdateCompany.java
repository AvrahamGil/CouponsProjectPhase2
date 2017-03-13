package com.gil.couponsproject.dao.test;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.exception.ApplicationException;

public class TestRemoveAndUpdateCompany {

	public static void main(String [] args) {
		Company company= new Company();
		TestRemoveAndUpdateCompany testRemoveAndUpdateCompany = new TestRemoveAndUpdateCompany();
						//					remove company			\\
		
		/*
		try {
			testRemoveAndUpdateCompany .removeCompany(2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
						//					update company			\\
		/*
		try {
			testRemoveAndUpdateCompany .updateCompany(company);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		try {
			testRemoveAndUpdateCompany .removeCompanyCoupons(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeCompany(long companyID) throws ApplicationException {
		CompanyDao companyDao= new CompanyDao();
		companyDao.removeCompany(companyID);
	}
	public  void removeCompanyCoupons(long companyID) throws ApplicationException {
		CompanyDao companyDao= new CompanyDao();
		companyDao.removeCompanyCoupons(companyID);
	}
	
	public  void updateCompany(Company company) throws ApplicationException {
		CompanyDao companyDao= new CompanyDao();
		company.setCompanyID(1);
		company.setCompanyName("gil A company");
		company.setCompanyPassword("gilush");
		company.setCompanyEmail("gilushcompany@hotmail.com");
		companyDao.updateCompany(company);
	}
}



package com.gil.couponsproject.logic.test;

import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CompanyLogic;

public class RemoveAndGetCompanyLogic {

	
	
	
	public static void main(String [] args) {
		CompanyLogic companyLogic = new CompanyLogic();
		RemoveAndGetCompanyLogic removeAndGetCompanyLogic = new RemoveAndGetCompanyLogic();
		//---------------------------------------remove company
		
		try {
			removeAndGetCompanyLogic .removeCompany(1 , 1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//--------------------------------------get company details
		/*
			try {
				removeAndGetCompanyLogic .getCompany(1);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		//------------------------------------get all companies
		/*
		try {
			removeAndGetCompanyLogic .getAllCompanies();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		}
	
	
	
	
	public  void removeCompany (long companyID , long customerID) throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		companyLogic.removeCompany(companyID , customerID);
	}
	
	public  void getCompany (long companyID) throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		companyLogic.getCompany(companyID);
	}
	public  void getAllCompanies() throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		companyLogic.listOfAllCompanies();
	}
	
}

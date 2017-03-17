package com.gil.couponsproject.logic;

import java.util.List;


import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.validationlogic.InputValidationCompanyRegistration;

public class CompanyLogic {

	// -----------------------------------------------Creating a company----------------------------------------------------
	public void createCompany(Company company) throws ApplicationException {
		InputValidationCompanyRegistration secureRegistraion = new InputValidationCompanyRegistration();
		CompanyDao companyDao = new CompanyDao();
		if (companyDao.isCompanyExistsByName(company.getCompanyName())) {
			throw new ApplicationException(
					ErrorType.COMPANY_NAME_ALREADY_IN_USE,
					"Error In companyDao, companyExsitByName(); ,cant createCompany,companyName already existt" + company);
		}
		//check if the information is correct , help us to protect our server from hacker's.
		secureRegistraion.checkIfTheInformationisCurrect(company);
		companyDao.createCompnay(company);
	}

	// -----------------------------------------------Delete Company----------------------------------------------------
	public void removeCompany(long companyID , long customerID) throws ApplicationException {
		//if we want to remove companies ,we have do 4 step's :
		//1st, we have to remove customer coupons
		//2nd, we have to remove customer's
		//3rd, we have to remove company coupons
		//4th , finally we can remove companies
		CompanyDao companyDao = new CompanyDao();
		CustomerDao customerDao = new CustomerDao();
		if (companyDao.getCompany(companyID) == null) {
			throw new ApplicationException(ErrorType.COMPANY_DOSENT_EXIST,
					"Error in CompanyLogic, removeCompany();,check your company ID again" + companyID);
		}
		customerDao.removeCustomerCoupons(customerID);
		companyDao.removeCompanyCoupons(companyID);
		companyDao.removeCompany(companyID);

	}
	
	// ------------------------------------------------Update Company--------------------------------------------------
	public void updateCompany(Company company) throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();

		if (companyDao.getCompany(company.getCompanyID()) == null) {
			throw new ApplicationException(ErrorType.COMPANY_DOSENT_EXIST,
					"Error in CompanyLogic,updateCompany(),check your companyID again" + company);
		}

		else if (companyDao.isCompanyExistsByName(company.getCompanyName())) {
			companyDao.updateCompany(company);
		} else {
			throw new ApplicationException(ErrorType.UPDATE_ERROR,
					"Error in CompanyLogic.updateCompany(),you cant change your company name");
		}

	}

	// --------------------------------------------getting information about company -------------------------------------
	public Company getCompany(long companyID) throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();
		Company company = new Company();
		if (companyDao.getCompany(companyID) == null) {
			throw new ApplicationException(ErrorType.COMPANY_DOSENT_EXIST,
					"Error in CompanyLogic,getCompany();,  check your id again" + companyID);
		}

		company = companyDao.getCompany(companyID);
		System.out.println(company);
		return company;
	}

	// --------------------------------------------list of companies-------------- -------------------------------------
	public List<Company> listOfAllCompanies() throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();
		List<Company> getlistofCompanies = companyDao.getListOfAllCompanies();
		System.out.println(getlistofCompanies);
		return getlistofCompanies;
	}
}
package com.gil.couponsproject.dao.interfaces;


import java.sql.SQLException;
import java.util.List;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.exception.ApplicationException;

public interface ICompany {

	public void createCompnay(Company company) throws ApplicationException;
	public void removeCompany(long  companyID) throws  ApplicationException; 
	public void updateCompany (Company company) throws  ApplicationException; 
	public Company getCompany(long companyID) throws  ApplicationException;
	public List<Company>getListOfAllCompanies() throws ApplicationException;
	
	
	
}

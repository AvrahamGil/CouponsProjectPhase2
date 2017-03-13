package com.gil.couponsproject.validationinterface;

import com.gil.couponsproject.exception.ApplicationException;

public interface ICompanySecurity {

	
	public String securityUserName(String companyName ) throws ApplicationException;
	public String securityUserPassword(String companyPassword)throws ApplicationException;
	public String securityUserEmail(String companyEmail)throws ApplicationException;
	
}

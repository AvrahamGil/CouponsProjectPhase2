package com.gil.couponsproject.validationinterface;

import com.gil.couponsproject.exception.ApplicationException;

public interface ICustomerSecurity {

	public String securityUserName(String customerName ) throws ApplicationException;
	public String securityUserPassword(String customerPassword)throws ApplicationException;
	
}

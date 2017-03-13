package com.gil.couponsproject.validationlogic;

import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.validationdao.CustomerDaoValidation;

public class TestValidationCustomer {
	
	public static void main(String [] args) {
		InputValidationCompanyRegistration secureCustomerRegistration = new InputValidationCompanyRegistration();
		CustomerDaoValidation customerDaoSecurity = new CustomerDaoValidation();
		//----------------------------------------securityUserName
		/*
		try {
			secureCustomerRegistration.securityUserName( "1", "GIlush1" , "bbb" , "sfs");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//--------------------------------------securityPassword
		/*
		try {
			secureCustomerRegistration.securityUserPassword("1", "GIlush1" , "Gilush" , "sfs");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	
	
	
	public void securityName (String userName) throws ApplicationException {
	InputValidationCustomerRegistration inputValidationCustomerRegistration = new InputValidationCustomerRegistration();
	inputValidationCustomerRegistration .checkingCustomerName(userName);
	}
	public void securityPassword (String userPassword) throws ApplicationException {
	InputValidationCustomerRegistration inputValidationCustomerRegistration = new InputValidationCustomerRegistration();
	inputValidationCustomerRegistration .checkingCustomerPassword(userPassword);
	}
	
}

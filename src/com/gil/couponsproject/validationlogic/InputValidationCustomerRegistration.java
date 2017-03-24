package com.gil.couponsproject.validationlogic;


import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.validationdao.CustomerDaoValidation;


public class InputValidationCustomerRegistration  {
	
	//UserName have to be with lower case and capital letter
	public  boolean checkingCustomerName(String customerName) throws ApplicationException {
		//local variables
		CustomerDaoValidation customerDaoSecurity = new CustomerDaoValidation();
	//-------------------------------------------------------------------------------------
		//allowed Up to number(in this example its 20) letter = allowed up to 20 letter
		 int allowedUpToNumberLetter = 20;
		//allowed more then number(in this example its 6) letter = allowed more then 6 letter
		 int allowMoreThenNumberLetter = 6;
		//allowed  lower case,capital letter,and numbers..
		 String userNameValid = "^[A-Z]+[a-z_-]{3,15}$";
	//-------------------------------------------------------------------------------------	 
		boolean correct = true;
		customerName =customerDaoSecurity.securityUserName(customerName);
		if (customerName.length() >= allowMoreThenNumberLetter && allowedUpToNumberLetter  > customerName.length() && customerName != null) {
			if (customerName.matches(userNameValid)) {
				return correct;
			}
			throw new ApplicationException (ErrorType.SECURITY_ERROR , "Invalid Username");
		}
		throw new ApplicationException (ErrorType.SECURITY_ERROR , "Customer Name Has To Contain More Then 6 Letters");
	}

	//UserPassword have to be with lower case and capital letter
	public   boolean checkingCustomerPassword( String customerPassword ) throws ApplicationException {
		//local variables
		CustomerDaoValidation customerDaoSecurity = new CustomerDaoValidation();
	//-------------------------------------------------------------------------------------	
		//allowed Up to number(in this example its 20) letter = allowed up to 20 letter
		 int allowedUpToNumberLetter = 20;
		//allowed more then number(in this example its 6) letter = allowed more then 6 letter
		int allowMoreThenNumberLetter = 6;
		//allowed  lower case,capital letter,and numbers..
		String passwordValid= "^[A-Z]+[a-z0-9_-]{3,15}$";
	//-------------------------------------------------------------------------------------	
		boolean correct = true;
		customerPassword = customerDaoSecurity.securityUserPassword(customerPassword);
		if (customerPassword.length() >= allowMoreThenNumberLetter && allowedUpToNumberLetter  > customerPassword.length() && customerPassword != null) {
			if (customerPassword.matches(passwordValid)) {
				return correct;
			}
			throw new ApplicationException (ErrorType.SECURITY_ERROR , "Invalid Password");
		}
		throw new ApplicationException (ErrorType.SECURITY_ERROR , "Password Has To Contain More Then 6 Letters ");
	}

	

	public  void checkIfTheInformationisCurrect (Customer customer) throws ApplicationException {
		checkingCustomerName(customer.getCustomerName());
		checkingCustomerPassword(customer.getCustomerPassword());
		
	}

	
	


}

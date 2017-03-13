package com.gil.couponsproject.logic;

import javax.xml.bind.annotation.XmlRootElement;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.beans.LoginOutput;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.enums.AccessType;
import com.gil.couponsproject.exception.ApplicationException;

@XmlRootElement
public class UserLogic {

	//Who are you?, a company manager?, customer?..or, administrator?
	public  LoginOutput userLogin(String name, String password , AccessType  type) throws ApplicationException {
		UserLogic userLogic  = new UserLogic ();
		LoginOutput loginOutPut = new LoginOutput();
		
		switch(type) {
		case ADMIN:
			loginOutPut = userLogic.adminLogin(name , password);
			break;
		case COMPANY:
			loginOutPut = userLogic.adminCompanyLogin(name,password);
		break;
		case CUSTOMER:
			loginOutPut = userLogic.customerLogin(name,password);
			break;			
		}
		return loginOutPut;
		}
	
	//administrator have a user name and password.
	//If you have user name and password.
	//you are the administrator.
	public LoginOutput adminLogin (String name , String password) throws ApplicationException {
		LoginOutput loginOutPut = new LoginOutput();
		if (name.equals("Admin") && password.equals("1234")) {
			System.out.println("login successed");
			return loginOutPut;
		}
		return null;
		
	}
	
	//company manager have a user name and password.
	//if you have user name and password
	//you are the administrator for a small company
	public LoginOutput adminCompanyLogin(String companyName , String companyPassword) throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();
		LoginOutput loginOutPut = new LoginOutput();
		 loginOutPut = companyDao.isCompanyExist(companyName, companyPassword);
		return loginOutPut;
		}
	
	
	//customer have a customer name and password.
	//if you have user name and password
	//you are a customer.
	public LoginOutput customerLogin (String customerName , String customerPassword) throws ApplicationException {
		CustomerDao customerDao = new CustomerDao();
		LoginOutput loginOutPut = new LoginOutput();
		loginOutPut = customerDao.isCustomerExist(customerName, customerPassword);
		return loginOutPut;
	}
	}


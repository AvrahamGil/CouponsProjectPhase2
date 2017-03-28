package com.gil.couponsproject.logic;

import java.util.List;

import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.validationlogic.InputValidationCustomerRegistration;

public class CustomerLogic {

	// -----------------------------------------------Creating a customer----------------------------------------------------
	public void createCustomer(Customer customer ) throws ApplicationException {
		InputValidationCustomerRegistration secureRegistraion = new InputValidationCustomerRegistration();
		CustomerDao customerDao = new CustomerDao();
		
		if (customerDao.isCustomerExistByName(customer.getCustomerName())) {
			throw new ApplicationException(
					ErrorType.CUSTOMER_NAME_ALREADY_IN_USE,
					" Customer name already in use");
		}
		
		//check if the information is correct , help us to protect our server from hacker's.
		secureRegistraion.checkIfTheInformationisCurrect(customer);
		customerDao.createCustomer(customer);
	}
	// -----------------------------------------------Delete Customer----------------------------------------------------
	public void removeCustomer(long customerID) throws ApplicationException {
		CustomerDao customerDao = new CustomerDao();
		if (customerDao.getCustomer(customerID) == null) {
			throw new ApplicationException(ErrorType.CUSTOMER_DOESNT_EXIST,
					"Check your customer ID again");
		}
		//if we want to remove a customer, we have to delete his coupons first
		customerDao.removeCustomerCoupons(customerID);
		customerDao.removeCustomer(customerID);
	}
	// -----------------------------------------------Delete Customer Coupon----------------------------------------------------
		public void removeCustomerCoupon(long customerID) throws ApplicationException {
			CustomerDao customerDao = new CustomerDao();
			if (customerDao.getCustomer(customerID) == null) {
				throw new ApplicationException(ErrorType.CUSTOMER_DOESNT_EXIST,
						"Check your customer ID again");
			}
			customerDao.removeCustomerCoupons(customerID);
		}
	// ------------------------------------------------Update Customer--------------------------------------------------
	public void updateCustomer(Customer customer) throws ApplicationException {
		InputValidationCustomerRegistration secureRegistraion = new InputValidationCustomerRegistration();
		CustomerDao customerDao = new CustomerDao();
		if (customerDao.getCustomer(customer.getCustomerID()) == null) {
			throw new ApplicationException(ErrorType.CUSTOMER_DOESNT_EXIST,"Check your customer ID again" 
							+customer);
		} else if (customerDao.isCustomerExistByName(customer.getCustomerName())) {
			secureRegistraion.checkIfTheInformationisCurrect(customer);
			customerDao.updateCustomer(customer);
		} else {
			throw new ApplicationException(
					ErrorType.UPDATE_ERROR,
					"You cant change your customer name");
		}

	}
	// --------------------------------------------getting information about customer -------------------------------------
	public Customer getCustomer(long customerID) throws ApplicationException {
		CustomerDao customerDao = new CustomerDao();
		Customer customer = new Customer();
		if (customerDao.getCustomer(customerID) == null) {
			throw new ApplicationException(ErrorType.CUSTOMER_DOESNT_EXIST,"Check your customer ID(" + customerID + ") again");
		}
		customer = customerDao.getCustomer(customerID);
		return customer;
	}
	// --------------------------------------------list of customers------------------------------------------------------
	public List<Customer> getAllCustomers() throws ApplicationException {
		CustomerDao customerDao = new CustomerDao();
		List<Customer> listofCustomers = customerDao.getListOfAllCustomers();
		System.out.println(listofCustomers);
		return listofCustomers;
	}

	
}

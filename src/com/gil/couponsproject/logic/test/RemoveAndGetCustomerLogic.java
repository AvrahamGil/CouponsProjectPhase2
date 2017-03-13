package com.gil.couponsproject.logic.test;

import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CustomerLogic;

public class RemoveAndGetCustomerLogic {

	
	public static void main(String [] args) {
		RemoveAndGetCustomerLogic removeAndGetCustomerLogic = new RemoveAndGetCustomerLogic();
		// ------------------------------ remove customer
		/*
		try {
			removeAndGetCustomerLogic.removeCustomer(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// ----------------------------- get customer
		/*
		try {
			removeAndGetCustomerLogic.getCustomer(2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		// --------------------------- get all customers
		try {
			removeAndGetCustomerLogic.getAllCustomers();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  void removeCustomer (long customerID) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		customerLogic.removeCustomer(customerID);
	}
	public  void getCustomer(long customerID) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		customerLogic.getCustomer(customerID);
	}
	public  void getAllCustomers () throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		customerLogic.getAllCustomers();
	
	}
	
	
}

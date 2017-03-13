package com.gil.couponsproject.dao.test;

import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;

public class TestRemoveAndUpdateCustomer {
	
	public static void main(String [] args) {
		 Customer customer = new Customer();
		 TestRemoveAndUpdateCustomer testRemoveAndUpdateCustomer = new TestRemoveAndUpdateCustomer();
		
		  //								update a customer			\\
		
			try {
				testRemoveAndUpdateCustomer.updateCustomer(customer);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
			
			//								remove a customer			\\
	
		 /*
			try {
				testRemoveAndUpdateCustomer.removeCustomer(4);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
	
	
	
	
	public  void removeCustomer (long customerID) throws ApplicationException  {
		CustomerDao customerDao = new CustomerDao();
		customerDao.removeCustomer(customerID);
	}
	
	public  void updateCustomer (Customer customer) throws ApplicationException {
		CustomerDao customerDao = new CustomerDao();
		customer.setCustomerID(1);
		customer.setCustomerName("gilush123");
		customer.setCustomerPassword("gilush");
		customerDao.updateCustomer(customer);
		System.out.println(customer);
}
	
}

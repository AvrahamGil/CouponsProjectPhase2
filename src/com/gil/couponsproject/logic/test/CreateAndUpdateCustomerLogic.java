package com.gil.couponsproject.logic.test;

import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CustomerLogic;

public class CreateAndUpdateCustomerLogic {

	
	
	public static void main(String [] args) {
		Customer customer = new Customer ();
		CreateAndUpdateCustomerLogic createAndUpdateCustomerLogic = new CreateAndUpdateCustomerLogic();
		//----------------------------------create customer
		
		try {
			createAndUpdateCustomerLogic.createCustomer(customer);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//------------------------------update customer
		/*
		try {
			createAndUpdateCustomerLogic.updateCustomer(customer);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public  void createCustomer (Customer customer) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		customer.setCustomerName("Gilushash123");
		customer.setCustomerPassword("Blablaush1");
		customerLogic.createCustomer(customer);
	}
	
	public  void updateCustomer (Customer customer) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		customer.setCustomerID(2);
		customer.setCustomerName("gilush");
		customer.setCustomerPassword("lalala");
		customerLogic.updateCustomer(customer);
	}
}

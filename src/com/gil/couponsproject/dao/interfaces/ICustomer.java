package com.gil.couponsproject.dao.interfaces;

import java.util.List;


import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.exception.ApplicationException;

public interface ICustomer {

	public void createCustomer(Customer customer) throws  ApplicationException;
	public void removeCustomer(long customerID) throws  ApplicationException;
	public void updateCustomer(Customer customer) throws  ApplicationException;
	public Customer getCustomer(long customerID) throws  ApplicationException;
	public List<Customer>getListOfAllCustomers() throws ApplicationException;
}

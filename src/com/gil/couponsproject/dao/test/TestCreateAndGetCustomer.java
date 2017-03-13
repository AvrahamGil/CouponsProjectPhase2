package com.gil.couponsproject.dao.test;


import java.util.List;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.exception.ApplicationException;


public class TestCreateAndGetCustomer {
	

	public static void main(String [] args)  {
		Customer customer = new Customer();
		TestCreateAndGetCustomer testCreateAndGetCustomer = new TestCreateAndGetCustomer();
		
		 		//								create a customer			\\
		
		/*
		try {
		testCreateAndGetCustomer.createCustomer(customer);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		 */
				//								get details on our customers
		/*
		try {
			testCreateAndGetCustomer.getCustomer(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//- --------------------------------------------get all customers
		/*
		try {
			testCreateAndGetCustomer.getAllCustomers();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//---------------------------------------------get all customers coupons
		/*
		try {
			testCreateAndGetCustomer.getAllCustomerCoupons(2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//------------------------------------------check if the coupon exist
		/*
		try {
			testCreateAndGetCustomer.isCouponExistByCustomerID(2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//-------------------------------------------get customer coupon by customerID
		/*
		try {
			testCreateAndGetCustomer.getCustomerCoupon(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//------------------------------------------ login customer
		
			try {
				testCreateAndGetCustomer.loginCustomer("GILA", "gilush111");
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		 		//							main is finish here 			 \\
	}
	
		public  void createCustomer (Customer customer) throws ApplicationException  {
			CustomerDao customerDao = new CustomerDao();
			customer.setCustomerName("yossix");
			customer.setCustomerPassword("yossihaha");
			customerDao.createCustomer(customer);
		}
		
		public void getCustomer (long customerID) throws ApplicationException {
			CustomerDao customerDao = new CustomerDao();
			Customer customer = new Customer();
			customer = customerDao.getCustomer(customerID);
			System.out.println(customer);
		}
		public  void getAllCustomers () throws ApplicationException {
			CustomerDao customerDao = new CustomerDao();
			List<Customer>getAllCustomers = customerDao.getListOfAllCustomers();
			System.out.println(getAllCustomers);;
		}
		
		public  void isCouponExistByCustomerID (long customerID) throws ApplicationException {
			CustomerDao customerDao = new CustomerDao();
			customerDao.isCouponExistByCustomerID(customerID);
		}
		public  void getCustomerCoupon(long customerID) throws ApplicationException {
			CustomerDao customerDao = new CustomerDao();
			customerDao.getCustomerCoupon(customerID);
		}
		
		public void loginCustomer(String customerName , String customerPassword) throws ApplicationException {
			CustomerDao customerDao = new CustomerDao();
			customerDao.isCustomerExist(customerName, customerPassword);
		}
	
		}
	

	


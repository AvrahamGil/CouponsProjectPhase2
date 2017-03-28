package com.gil.couponsproject.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.beans.LoginOutput;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CompanyLogic;
import com.gil.couponsproject.logic.CouponLogic;
import com.gil.couponsproject.logic.CustomerLogic;
import com.gil.couponsproject.utils.SessonLogin;

@Path("/api/Customers")
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerApi {

	

	@GET
	@Path("/customerID/{customerID}")
	public Customer getCustomer(@PathParam("customerID")long customerID) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		Customer customer = new Customer();
		customer = customerLogic.getCustomer(customerID);
		return customer;
	}
	@GET
	@Path("/Profile")
	public Customer getProfile(@Context HttpServletRequest request) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		Customer customer = new Customer();
		SessonLogin sessionLogin = new SessonLogin();
		long customerID = sessionLogin.getUserLogin(request);
		customer = customerLogic.getCustomer(customerID);
		System.out.println(customer);
		return customer;
		
	}

	@PUT
	public void updateCustomer(@Context HttpServletRequest request,Customer customer) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		SessonLogin sessionLogin = new SessonLogin();
		long customerID = sessionLogin.getUserLogin(request);
		customer.setCustomerID(customerID);
		customer.getCustomerName();
		customer.getCustomerPassword();
		customerLogic.updateCustomer(customer);
	}

	@DELETE
	@Path("/{customerID}")
	public void deleteCustomer(@PathParam("customerID") long customerID) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		customerLogic.removeCustomer(customerID);
	}
	@DELETE
	@Path("/customerCoupon/{customerID}")
	public void deleteCustomerCoupon(@PathParam("customerID") long customerID) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		customerLogic.removeCustomerCoupon(customerID);
	}

	@GET
	public List<Customer> getAllCustomeres() throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		List<Customer> getAllCustomer = new ArrayList<Customer>();
		getAllCustomer = customerLogic.getAllCustomers();
		return getAllCustomer;

	}
	
	@PUT
	@Path("/{couponID}/{endDate}")
	public void buyCoupon(@Context HttpServletRequest request,@PathParam("couponID")long couponID  , @PathParam("endDate") long endDate) throws ApplicationException {
		CouponLogic couponLogic = new CouponLogic();
		SessonLogin sessionLogin = new SessonLogin();
		long customerID = sessionLogin.getUserLogin(request);
		couponLogic.buyCoupon(couponID, customerID, endDate);
	}
}

package com.gil.couponsproject.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CompanyLogic;
import com.gil.couponsproject.logic.CustomerLogic;

@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class RegisterApi {

	@POST
	@Path("/company")
	public void createCompany(Company company) throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		company.getCompanyName();
		company.getCompanyPassword();
		company.getCompanyEmail();
		companyLogic.createCompany(company);

	}
	
	@POST
	@Path("/customer")
	public void createCustomer(Customer customer) throws ApplicationException {
		CustomerLogic customerLogic = new CustomerLogic();
		customer.getCustomerName();
		customer.getCustomerPassword();

		customerLogic.createCustomer(customer);
	}
	
}

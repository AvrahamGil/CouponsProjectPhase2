package com.gil.couponsproject.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.beans.LoginOutput;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.exception.ExceptionsHandler;
import com.gil.couponsproject.logic.CompanyLogic;
import com.gil.couponsproject.utils.SessonLogin;

@Path("/api/Companies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyApi {



	@GET
	@Path("/companyID/{companyID}")
	public Company getCompany(@PathParam("companyID")long companyID) throws ApplicationException {
		ExceptionsHandler exceptionHanglder = new ExceptionsHandler();
		CompanyLogic companyLogic = new CompanyLogic();
		Company company = new Company();
		
		try {
		company = companyLogic.getCompany(companyID);
		}catch(ApplicationException e) {
			e.printStackTrace();
			exceptionHanglder.toResponse(e);
		}
		return company;
		
	}

	@GET
	@Path("/Profile")
	public Company getProfile(@Context HttpServletRequest request) throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		Company company = new Company();
		SessonLogin sessionLogin = new SessonLogin();
		long companyID = sessionLogin.getUserLogin(request);
		company = companyLogic.getCompany(companyID);
		return company;
		
	}
	
	
	@PUT
	public void updateCompany(@Context HttpServletRequest request,Company company) throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		SessonLogin sessionLogin = new SessonLogin();
		long companyID = sessionLogin.getUserLogin(request);
		company.setCompanyID(companyID);
		company.getCompanyName();
		company.getCompanyPassword();
		company.getCompanyEmail();
		companyLogic.updateCompany(company);
	}

	@DELETE
	@Path("/{companyID}")
	public void removeCompany(@PathParam("companyID") long companyID ) throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		companyLogic.removeCompany(companyID);

	}

	@GET
	public List<Company> getAllCompanies() throws ApplicationException {
		CompanyLogic companyLogic = new CompanyLogic();
		List<Company> getListOfAllCompanies = new ArrayList<Company>();
		getListOfAllCompanies= companyLogic.listOfAllCompanies();
		return getListOfAllCompanies;

	}

}
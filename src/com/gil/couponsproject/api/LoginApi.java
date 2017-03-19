package com.gil.couponsproject.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.gil.couponsproject.beans.LoginOutput;
import com.gil.couponsproject.beans.LoginUserDetails;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.exception.ExceptionsHandler;
import com.gil.couponsproject.logic.UserLogic;
import com.gil.couponsproject.utils.SessonLogin;

@Path("/Login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginApi {

	@POST
	public LoginOutput login(@Context HttpServletRequest request, LoginUserDetails loginDetails)
			throws ApplicationException {
		UserLogic user = new UserLogic();
		LoginOutput loginOutPut = new LoginOutput();
		ExceptionsHandler exceptionHandler = new ExceptionsHandler();
		System.out.println("hi from login api");
		try {

			loginOutPut = user.userLogin(loginDetails.getUserName(), loginDetails.getUserPassword(),
					loginDetails.getType());

		} catch (ApplicationException e) {
			e.printStackTrace();
			exceptionHandler.toResponse(e);

		}
		if (loginOutPut == null) {
			throw new ApplicationException(ErrorType.LOGIN_ERROR, "UserName Or Password is incorrect");
		} else {
			request.getSession();
			request.getSession(false).setAttribute("userID", loginOutPut.getuserID());

		}

		return loginOutPut;
	}

	@POST
	@Path("/logOut")
	public void logOut(@Context HttpServletRequest request) throws ApplicationException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		} else {
			System.out.println("hi from logout api");
		}
	}

}

package com.gil.couponsproject.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.beans.LoginOutput;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CouponLogic;
import com.gil.couponsproject.utils.SessonLogin;

@Path("/api/Coupons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CouponApi {

	@POST
	public void createCoupon(Coupon coupon , @Context HttpServletRequest request) throws ApplicationException, ParseException {
		CouponLogic couponLogic = new CouponLogic();
		SessonLogin sessionLogin = new SessonLogin();
		long startDate = System.currentTimeMillis();
		
		coupon.getCouponTitle();
		coupon.setStartDate(startDate);
		coupon.getEndDate();
		coupon.getCouponPrice();
		coupon.getCouponMessage();
		coupon.getCouponTypeByNumber();
		coupon.getCouponAmount();
		
		long couponID = sessionLogin.getUserLogin(request);
		coupon.setcompanyID(couponID);

		couponLogic.createCoupon(coupon);
	}

	@GET
	@Path("/couponID/{couponID}")
	public Coupon getCoupon(@PathParam("couponID")long couponID) throws ApplicationException {
		Coupon coupon = new Coupon();
		CouponLogic couponLogic = new CouponLogic();
		coupon = couponLogic.getCoupon(couponID);
		return coupon;

	}

	@PUT
	public void updateCoupon(@Context HttpServletRequest request,Coupon coupon) throws ApplicationException {
		CouponLogic couponLogic = new CouponLogic();
		SessonLogin sessionLogin = new SessonLogin();
		
		long couponID = sessionLogin.getUserLogin(request);
		coupon.setcouponID(couponID);
		coupon.getEndDate();
		coupon.getCouponPrice();
		
		couponLogic.updateCoupon(coupon);

	}

	@DELETE
	@Path("/{couponID}")
	public void deleteCoupon(@PathParam("couponID") long couponID) throws ApplicationException {
		CouponLogic couponLogic = new CouponLogic();
		couponLogic.removeCoupons(couponID);

	}

	@GET
	public List<Coupon> getAllCoupons() throws ApplicationException {
		List<Coupon> getAllCoupons = new ArrayList<Coupon>();
		CouponLogic couponLogic = new CouponLogic();
		getAllCoupons = couponLogic.getAllCoupons();
		return getAllCoupons;
	}
	
	@GET
	@Path("/CompanyCoupons")
	public List<Coupon>getCompanyCoupons(@Context HttpServletRequest request) throws ApplicationException {
		List<Coupon>companyCoupons = new ArrayList<Coupon>();
		SessonLogin sessionLogin = new SessonLogin();
		CouponLogic couponLogic = new CouponLogic();
		long companyID =  sessionLogin.getUserLogin(request);
		companyCoupons = couponLogic.listOfCompanyCoupons(companyID);
		return companyCoupons;
	}
	
	@GET
	@Path("/CustomerCoupons")
	public List<Coupon>getCustomerCoupons(@Context HttpServletRequest request) throws ApplicationException {
		List<Coupon>customerCoupons = new ArrayList<Coupon>();
		SessonLogin sessionLogin = new SessonLogin();
		long companyID = sessionLogin.getUserLogin(request);
		CouponLogic couponLogic = new CouponLogic();
		customerCoupons = couponLogic.listOfCustomerCoupons(companyID);
		return customerCoupons;
	}

}
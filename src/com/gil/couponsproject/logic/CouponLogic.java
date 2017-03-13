package com.gil.couponsproject.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.dao.CompanyDao;
import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.dao.CustomerDao;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utilstest.TestJdbcTransecationManagerTest;
import com.gil.couponsproject.validationlogic.InputValidationCompanyRegistration;
import com.gil.couponsproject.validationlogic.InputValidationCoupon;

public class CouponLogic {

	// -----------------------------------------------Creating a customer----------------------------------------------------
	public void createCoupon(Coupon coupon) throws ApplicationException {
		InputValidationCoupon secureCoupon = new InputValidationCoupon();
		CouponDao couponDao = new CouponDao();
		if (couponDao.isCouponExistByTitle(coupon.getCouponTitle())) {
			throw new ApplicationException("Error in CouponLogic, createCoupon();, coupon title already exist",
					ErrorType.COUPON_NAME_ALREADY_EXIST);
		}
		secureCoupon.checkIfTheInformationCurrect(coupon);
		couponDao.createCoupon(coupon);
	}
	//------------------------------------------------Convert time to long----------------------------------------------------
	public void convertTime() throws  ParseException {
		String string_date = "12/December/2012";

		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		    Date d = f.parse(string_date);
		    long milliseconds = d.getTime();
		    System.out.println(milliseconds);
	}

	// -----------------------------------------------Update coupon----------------------------------------------------
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		if (couponDao.getCoupon(coupon.getcouponID()) == null) {
			throw new ApplicationException("Error in CouponLogic,updateCoupon();, coupon doesnt exist ",
					ErrorType.COUPON_DOESNT_EXIST);
		}
		couponDao.updateCouponPriceAndEndDate(coupon);
	}

	// -----------------------------------------------Delete Coupon----------------------------------------------------
	public void removeCoupons(long couponID) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		if (couponDao.getCoupon(couponID) == null) {
			throw new ApplicationException("Error in CouponLogic,updateCoupon();, coupon doesnt exist" + couponID,
					ErrorType.COUPON_DOESNT_EXIST);
		}
		couponDao.removeCustomerCoupons(couponID);
		couponDao.removeCoupon(couponID);
	}
	//-----------------------------------------------get Coupon-------------------------------------------------------
	public Coupon getCoupon(long couponID) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		Coupon coupon = new Coupon();
		if(couponDao.getCoupon(couponID) == null) {
			throw new ApplicationException("Error in CouponLogic,getCoupon();,coupon dosent exist" + couponID,ErrorType.COUPON_DOESNT_EXIST);
		}
		coupon = couponDao.getCoupon(couponID);
		System.out.println(coupon);
		return coupon;
	}
	//----------------------------------------------get all coupons--------------------------------------------------
	public List<Coupon> getAllCoupons() throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>getAllCoupons = couponDao.getListOfAllCoupons();
		System.out.println(getAllCoupons);
		return getAllCoupons;
	}

	// ------------------------------------------------buy coupon-----------------------------------------------------
	public void buyCoupon(long couponID, long customerID, long endDate) throws ApplicationException {
		Coupon coupon = new Coupon();
		CouponDao couponDao = new CouponDao();
		CustomerDao customerDao = new CustomerDao();
		// Put into "coupon" ,couponID
		// Checking if the coupon exist
		// Check how many coupons are left
		// Check if the coupon is expired
		// Check if the customer already got the coupon (with his customer ID)
		coupon = couponDao.getCoupon(couponID);
		int couponsleft = coupon.getCouponAmount();

		if (couponDao.getCoupon(couponID) == null) {
			throw new ApplicationException("Error in CouponLogic,buyCoupon();, coupon doesnt exist" + couponID,
					ErrorType.COUPON_DOESNT_EXIST);
		}
		if (couponsleft <= 0) {
			throw new ApplicationException("Error in CouponLogic, buyCoupon();, no coupon left",
					ErrorType.COUPON_DOESNT_EXIST);
		}
		if (couponDao.isCouponExpired(endDate) < System.currentTimeMillis()) {
			throw new ApplicationException("the coupon is expired", ErrorType.COUPON_EXPIRED);
		}
		if (customerDao.getCustomerCoupon(customerID) != null) {
			throw new ApplicationException("The customer already bought the coupon, his ID = " + customerID,
					ErrorType.CUSTOMER_ALREADY_BUY_THIS_COUPON);
		}
			couponDao.removeCouponFromStock(couponID);
			couponDao.createCustomerCoupon(couponID, customerID);
			System.out.println("Success! you buy a new coupon");
		
	}

	// -----------------------------------------------company coupons--------------------------------------
	public List<Coupon> listOfCompanyCoupons(long companyID) throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();
		CouponDao couponDao = new CouponDao();
		if (companyDao.getCompany(companyID) == null) {
			throw new ApplicationException(
					"Error in CouponLogic,ListofCompanyCoupons();,check your id again" + companyID,
					ErrorType.COMPANY_DOSENT_EXIST);
		}
		List<Coupon> listofCompany = couponDao.getListOfCompanyCoupons(companyID);
		System.out.println(listofCompany);
		return listofCompany;
	}

	// -------------------------------------------customer coupons------------------------------------------
	public List<Coupon> listOfCustomerCoupons(long customerID) throws ApplicationException {
		CustomerDao customerDao = new CustomerDao();
		CouponDao couponDao = new CouponDao();
		if (customerDao.getCustomer(customerID) == null) {
			throw new ApplicationException(
					"Error in CouponLogic,listOfCustomerCoupons();,check your id again" + customerID,
					ErrorType.CUSTOMER_DOESNT_EXIST);
		}
		List<Coupon> listofCustomer = couponDao.getListOfCustomerCoupons(customerID);
		System.out.println(listofCustomer);
		return listofCustomer;
	}

	// -----------------------------------------company coupons by date price and type------------------------
	public void listOfCompanyCouponsByTypePriceAndDate(long companyID, int couponType) throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();
		CouponDao couponDao = new CouponDao();
		Coupon coupon = new Coupon();
		//first, we have to write a date
		//second,all the coupons that created before our date will join to the list
		long couponStartDate = System.currentTimeMillis();
		// we have to choose a number 
		// to get all the coupons that lower from our number
		double couponPrice = coupon.getCouponPrice();
		// checking if company exist
		if (companyDao.getCompany(companyID) == null) {
			throw new ApplicationException("Error in CouponLogic,ListOfCompanyCouponsByTypePriceAndDate,check your company ID again"
					 + companyID,ErrorType.COMPANY_DOSENT_EXIST);
		}
		// get coupons from company by type
		List<Coupon> getCouponsCompanyByType = couponDao.getListOfCompanyCouponByType(companyID, couponType);
		System.out.println(getCouponsCompanyByType);
		// get coupons from company by price
		List<Coupon> getCouponCompanyByPrice = couponDao.getListOfCompanyPrices(companyID, couponPrice);
		System.out.println(getCouponCompanyByPrice);
		// get coupons from company by date
		List<Coupon> getCouponCompanyByDate = couponDao.getListOfCompanyCouponByDate(companyID, couponStartDate);
		System.out.println(getCouponCompanyByDate);
	}

	// -----------------------------------------customer coupons by price and type------------------------
	public List<Coupon> listOfCustomerCouponsByTypeAndPrice(long customerID, int couponType) throws ApplicationException {
		CustomerDao customerDao = new CustomerDao();
		CouponDao couponDao = new CouponDao();
		Coupon coupon = new Coupon();
		// we need to put a number to get all the coupons that lower from our
		// number
		double couponPrice = coupon.getCouponPrice() ;
		// check if customer exist
		if (customerDao.getCustomer(customerID) == null) {
			throw new ApplicationException("Error in CouponLogic,listOfCustomerCouponsByTypeAndPrice,check your customer ID again"
					+ customerID,ErrorType.CUSTOMER_DOESNT_EXIST);
		}
		// get coupons from company by type
		List<Coupon> getCouponsCustomerByType = couponDao.getListOfCustomersCouponTypes(customerID, couponType);
		System.out.println(getCouponsCustomerByType);
		// get coupons from company by price
		List<Coupon> getCouponCustomerByPrice = couponDao.getListOfCustomerPrices(customerID, couponPrice);
		System.out.println(getCouponCustomerByPrice);
		
		return getCouponsCustomerByType;

	}

	
}

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
			throw new ApplicationException(ErrorType.COUPON_NAME_ALREADY_EXIST,
					"Coupon title already exist");
		}
		secureCoupon.checkIfTheInformationCurrect(coupon);
		couponDao.createCoupon(coupon);
	}


	// -----------------------------------------------Update coupon----------------------------------------------------
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		if (couponDao.getCoupon(coupon.getcouponID()) == null) {
			throw new ApplicationException(ErrorType.COUPON_DOESNT_EXIST,
					" Check your coupon ID again ");
		}
		couponDao.updateCouponPriceAndEndDate(coupon);
	}

	// -----------------------------------------------Delete Coupon----------------------------------------------------
	public void removeCoupons(long couponID) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		if (couponDao.getCoupon(couponID) == null) {
			throw new ApplicationException(ErrorType.COUPON_DOESNT_EXIST,
					"Check your coupon ID again" + couponID);
		}
		couponDao.removeCustomerCoupons(couponID);
		couponDao.removeCoupon(couponID);
	}
	//-----------------------------------------------get Coupon-------------------------------------------------------
	public Coupon getCoupon(long couponID) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		Coupon coupon = new Coupon();
		if(couponDao.getCoupon(couponID) == null) {
			throw new ApplicationException(ErrorType.COUPON_DOESNT_EXIST,"Check Your Coupon ID(" + couponID + ") Again");
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
			throw new ApplicationException(ErrorType.COUPON_DOESNT_EXIST,
					"Check your coupon ID again" + couponID);
		}
		if (couponsleft <= 0) {
			throw new ApplicationException(ErrorType.COUPON_DOESNT_EXIST,
					" No coupons left , Please pick another one");
		}
		if (couponDao.isCouponExpired(endDate) < System.currentTimeMillis()) {
			throw new ApplicationException(ErrorType.COUPON_EXPIRED, "Coupon Expired");
		}
		if (customerDao.getCustomerCoupon(customerID) != null) {
			throw new ApplicationException(ErrorType.CUSTOMER_ALREADY_BUY_THIS_COUPON,
					"You already buy coupon from 'Coupon List'");
		}
			couponDao.removeCouponFromStock(couponID);
			couponDao.createCustomerCoupon(couponID, customerID);
			System.out.println("Success! you buy a new coupon");
		
	}
	//---------------------------------------------get list of coupons by type----------------------------
	public List<Coupon>listOfCouponsByType(int couponType) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>getListOfCouponsByType = couponDao.getListOfCouponsByType(couponType);
		System.out.println(getListOfCouponsByType);
		return getListOfCouponsByType;
		
	}
	//---------------------------------------------get list of coupons by price----------------------------
		public List<Coupon>listOfCouponsByPrice(int couponPrice) throws ApplicationException {
			CouponDao couponDao = new CouponDao();
			List<Coupon>getListOfCouponsByPrice = couponDao.getListOfCouponsByPrice(couponPrice);
			System.out.println(getListOfCouponsByPrice);
			return getListOfCouponsByPrice;
			
		}

	// -----------------------------------------------company coupons--------------------------------------
	public List<Coupon> listOfCompanyCoupons(long companyID) throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();
		CouponDao couponDao = new CouponDao();
		if (companyDao.getCompany(companyID) == null) {
			throw new ApplicationException(
					ErrorType.COMPANY_DOSENT_EXIST,
					"Check your company ID again" + companyID);
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
					ErrorType.CUSTOMER_DOESNT_EXIST,
					"Check your customer ID again" + customerID);
		}
		List<Coupon> listofCustomer = couponDao.getListOfCustomerCoupons(customerID);
		System.out.println(listofCustomer);
		return listofCustomer;
	}
	

	
	// -----------------------------------------company coupons by  type------------------------
	public List<Coupon> listOfCompanyCouponsByType(long companyID, int couponType) throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();
		CouponDao couponDao = new CouponDao();
		
		
		// checking if company exist
		if (companyDao.getCompany(companyID) == null) {
			throw new ApplicationException(ErrorType.COMPANY_DOSENT_EXIST,"Check your company ID again"
							 + companyID);
		}
		// get coupons from company by type
		List<Coupon> getCouponsCompanyByType = couponDao.getListOfCompanyCouponByType(companyID, couponType);
		System.out.println(getCouponsCompanyByType);
		return getCouponsCompanyByType;
		// get coupons from company by price
		
	}
	// -----------------------------------------company coupons by  price------------------------
	public List<Coupon> listOfCompanyCouponsByPrice(long companyID, double couponPrice) throws ApplicationException {
		CompanyDao companyDao = new CompanyDao();
		CouponDao couponDao = new CouponDao();
		
	
		// checking if company exist
		if (companyDao.getCompany(companyID) == null) {
			throw new ApplicationException(ErrorType.COMPANY_DOSENT_EXIST,"Check your company ID again"
							 + companyID);
		}
		
		// get coupons from company by price
		List<Coupon> getCompanyCouponsByPrice = couponDao.getListOfCompanyCouponsByPrice(companyID, couponPrice);
		System.out.println(getCompanyCouponsByPrice);
		return getCompanyCouponsByPrice;
		
	}

	

	
}

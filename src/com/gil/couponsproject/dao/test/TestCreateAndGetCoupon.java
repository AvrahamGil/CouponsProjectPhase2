package com.gil.couponsproject.dao.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.exception.ApplicationException;

public class TestCreateAndGetCoupon {

	
	public static void main(String [] args) {
		Coupon coupon = new Coupon();
		TestCreateAndGetCoupon testCreateAndGetCoupon = new TestCreateAndGetCoupon();
		
		Date date = new Date("16/03/2020");
		long endDate = date.getTime();
		System.out.println(endDate);
		//----------------------------------------create a new coupon
		/*
		try {
			testCreateAndGetCoupon .createCoupon(coupon);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//----------------------------------------create a new customer coupon
		/*
		try {
			testCreateAndGetCoupon.createCustomerCoupon(3, 2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//--------------------------------------get a details of our coupon
		/*
		try {
			testCreateAndGetCoupon .getCoupon(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//----------------------------------------- get all coupons
		/*
		try {
			testCreateAndGetCoupon .getAllCoupons();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//---------------------------------------- get coupon by type
		/*
		try {
			testCreateAndGetCoupon .getCouponsByType(5);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//-------------------------------------- get company coupons
		/*
		try {
			testCreateAndGetCoupon .getCompanyCoupons(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//------------------------------------- get customer coupons
		/*
		try {
			testCreateAndGetCoupon .getCustomerCoupons(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//------------------------------------list of company coupons
		/*
		try {
			testCreateAndGetCoupon.getCompanyCouponsType(5 , 2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//------------------------------------list of customer coupons by type
		/*
		try {
			testCreateAndGetCoupon.getCustomerCouponsType(1 , 5);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//-----------------------------------list of customer coupons by price
	/*
		try {
			testCreateAndGetCoupon.getCustomerCouponsPrice(3, 5000 );
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	
		//---------------------------------list of company coupons by type
		/*
		try {
			testCreateAndGetCoupon.getCompanyCouponsType(1, 5);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//------------------------------- list of company coupons by price
		/*
		try {
			testCreateAndGetCoupon.getCompanyCouponsPrice(1 , 5000);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//------------------------------ list of company coupons by date
		/*
		try {
			testCreateAndGetCoupon.getCompanyCouponsDate(1 , System.currentTimeMillis());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public  void createCoupon (Coupon coupon) throws ApplicationException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date("05/11/2016");
		long endDate = date.getTime();
		long startDate = System.currentTimeMillis();
		CouponDao couponDao = new CouponDao();
		//				create the details in the coupon
		
		coupon.setCouponTitle("gil coupon");
	//	coupon.setEndDate(startDate);
	//	coupon.setEndDate(endDate);
		coupon.setCouponAmount(5000);
		coupon.setCouponTypeByNumber(7);
		coupon.setCouponMessage("hello world");
		coupon.setcouponPrice(500);
		
		//				create the coupon
		couponDao.createCoupon(coupon);
	}
	public void createCustomerCoupon (long couponID , long customerID) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		couponDao.createCustomerCoupon(couponID, customerID);
	}
	public void getCoupon (long couponID) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		Coupon coupon = new Coupon();
		coupon = couponDao.getCoupon(couponID);
		System.out.println(coupon);
	}
	public  void getAllCoupons() throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>couponList =  couponDao.getListOfAllCoupons();
		System.out.println(couponList);
	}
	public  void getCouponsByType (int couponType) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>couponTypeList = couponDao.getListOfCouponsByType(couponType);
		System.out.println(couponTypeList);
	}
	public  void getCompanyCoupons(long companyID) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>listofCompany = couponDao.getListOfCompanyCoupons(1);
		System.out.println(listofCompany);
	}
	public  void getCustomerCoupons(long customerID) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>getCustomerCoupons = couponDao.getListOfCustomerCoupons(customerID);
		System.out.println(getCustomerCoupons);
	}
	public  void getCustomerCouponsType(long customerID , int couponType) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>getCustomerCoupons = couponDao.getListOfCompanyCouponByType(customerID, couponType);
		System.out.println(getCustomerCoupons);
	}
	public  List<Coupon> getCustomerCouponsPrice(long customerID , double couponPrice ) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>getCustomerCoupons = couponDao.getListOfCustomerPrices(customerID, couponPrice );
		System.out.println(getCustomerCoupons);
		return getCustomerCoupons;
		
	}
	public  void getCompanyCouponsType(long companyID , int couponType) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>getCompanyCouponsType = couponDao.getListOfCompanyCouponByType(companyID, couponType);
		System.out.println(getCompanyCouponsType);
	}
	public  void getCompanyCouponsPrice(long companyID , double couponPrice) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>getCompanyCouponsPrice = couponDao.getListOfCompanyPrices(companyID, couponPrice);
		System.out.println(getCompanyCouponsPrice);
	}
	public  void getCompanyCouponsDate(long companyID , long couponStartDate) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		List<Coupon>getCompanyCouponsDate = couponDao.getListOfCompanyCouponByDate(companyID, couponStartDate);
		System.out.println(getCompanyCouponsDate );
	}
}


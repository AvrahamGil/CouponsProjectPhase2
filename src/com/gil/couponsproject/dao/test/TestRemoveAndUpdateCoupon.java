package com.gil.couponsproject.dao.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.exception.ApplicationException;

public class TestRemoveAndUpdateCoupon {

	
	public static void main(String [] args) {
		Coupon coupon = new Coupon();
		TestRemoveAndUpdateCoupon testRemoveAndUpdateCoupon = new TestRemoveAndUpdateCoupon();
		//------------------------------------remove coupon
		
		/*
		try {
			testRemoveAndUpdateCoupon.removeCoupon(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//---------------------------------remove coupons from customers
		/*
		try {
			testRemoveAndUpdateCoupon.removeCustomerCoupons(2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//--------------------------------remove expired coupon
		try {
			
			testRemoveAndUpdateCoupon.removeExpiredCoupons();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//------------------------------------update coupon
		
		try {
			testRemoveAndUpdateCoupon.updateCoupon(coupon);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		//-------------------------------buy coupon
		/*
		try {
			testRemoveAndUpdateCoupon.buyCoupon(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	
	
	public void removeCoupon (long couponID) throws ApplicationException {
		CouponDao couponDao= new CouponDao();
		couponDao.removeCustomerCoupons(couponID);
		couponDao.removeCoupon(couponID);
	}
	public  void removeCustomerCoupons(long customerID) throws ApplicationException{
		CouponDao couponDao= new CouponDao();
		couponDao.removeCustomerCoupons(customerID);
	}
	public void removeExpiredCoupons () throws ApplicationException {
		CouponDao couponDao= new CouponDao();
		couponDao.removeExpiredCustomerCoupons();
		couponDao.removeExpiredCoupon();
	}
	
	public  void updateCoupon(Coupon coupon) throws ApplicationException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		@SuppressWarnings("deprecation")
		Date date = new Date("10/05/2020");
		long endDate = date.getTime();
		long startDate = System.currentTimeMillis();
		CouponDao couponDao= new CouponDao();
		//				change details in the coupon
		coupon.setCouponTitle("Dont Drink And Drive");
	//	coupon.setStartDate(startDate);
	//	coupon.setEndDate(endDate);
		coupon.setCouponAmount(100);
		coupon.setCouponTypeByNumber(8);
		coupon.setCouponMessage("hi");
		coupon.setcouponPrice(150);
		coupon.setcouponID(2);
		
		//				run it	
		couponDao.updateCoupon(coupon);
	}
	public void buyCoupon (long couponID) throws ApplicationException {
		CouponDao couponDao= new CouponDao();
		couponDao.removeCouponFromStock(couponID);
	}
}

package com.gil.couponsproject.logic.test;


import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CouponLogic;

public class RemoveAndGetCouponLogic {

	
	public static void main(String [] args) {
		RemoveAndGetCouponLogic removeAndGetCouponLogic = new RemoveAndGetCouponLogic();
		/*
		try {
			removeAndGetCouponLogic.removeCoupon(2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			removeAndGetCouponLogic.buyCoupon(2 , 4 , 1601845200000L);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void removeCoupon(long couponID) throws ApplicationException {
		CouponLogic couponLogic = new CouponLogic ();
		couponLogic.removeCoupons(couponID);
	}
	public void buyCoupon (long couponID , long customerID , long couponEndDate) throws ApplicationException {
		CouponLogic couponLogic = new CouponLogic ();
		couponLogic.buyCoupon(couponID, customerID , couponEndDate);
	}
	
}

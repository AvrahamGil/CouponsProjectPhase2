package com.gil.couponsproject.logic.test;

import com.gil.couponsproject.beans.Coupon;

import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CouponLogic;

public class CreateAndUpdateCouponLogic {

	
	
	
	public static void main(String[] args) {
		Coupon coupon = new Coupon();
		CreateAndUpdateCouponLogic createAndUpdateCouponLogic = new CreateAndUpdateCouponLogic();
		try {
			createAndUpdateCouponLogic.createCoupon(coupon);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			createAndUpdateCouponLogic.updateCouponPriceAndEndDate(coupon);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void createCoupon(Coupon coupon) throws ApplicationException {
		CouponLogic couponLogic = new CouponLogic();
	
		coupon.setCouponTitle("Gilcoup");
		coupon.setStartDate(15/8/1992);
		coupon.setEndDate(25/8/2016);
		coupon.setCouponAmount(500);
		coupon.setCouponTypeByNumber(1);
		coupon.setcouponPrice(600);
		coupon.setCouponMessage("Holyshit");
		
		
		
		couponLogic.createCoupon(coupon);
	}
	public void updateCouponPriceAndEndDate (Coupon coupon) throws ApplicationException  {
		CouponLogic couponLogic = new CouponLogic();
		coupon.setcouponID(1);
		coupon.setcouponPrice(51000);
		coupon.setEndDate(12/7/2020);
		couponLogic.updateCoupon(coupon);
	}

}

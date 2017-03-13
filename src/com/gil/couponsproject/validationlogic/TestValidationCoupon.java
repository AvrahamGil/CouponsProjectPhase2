package com.gil.couponsproject.validationlogic;

import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.validationdao.CouponDaoValidation;

public class TestValidationCoupon {
	
	public static void main(String [] args) {
		CouponDaoValidation couponDaoSecurity = new CouponDaoValidation();
		 TestValidationCoupon secureCoupon = new  TestValidationCoupon();
	
		//----------------------------------------securityCouponTitle
		try {
			secureCoupon.securityTitle("Yeahhh");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//----------------------------------------securityCouponAmount
		try {
			secureCoupon.securityCouponAmount(12345);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//----------------------------------------securityCouponMessage
		try {
			secureCoupon.securitMessage("blabla");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			secureCoupon.securityCouponPrice(15783);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void securityTitle (String couponTitle) throws ApplicationException {
		InputValidationCoupon  inputValidationCoupon  = new InputValidationCoupon ();
		inputValidationCoupon.checkingCouponTitle(couponTitle);
	}
	public void securityCouponAmount (int couponAmount) throws ApplicationException {
		InputValidationCoupon  inputValidationCoupon  = new InputValidationCoupon ();
		inputValidationCoupon.checkingCouponAmount(couponAmount);
	}
	public void securitMessage(String couponMessage) throws ApplicationException {
		InputValidationCoupon  inputValidationCoupon  = new InputValidationCoupon ();
		inputValidationCoupon.checkingCouponMessage(couponMessage);
	}
	public void securityCouponPrice (double couponPrice) throws ApplicationException {
		InputValidationCoupon  inputValidationCoupon  = new InputValidationCoupon ();
		inputValidationCoupon.checkingCouponPrice(couponPrice);
	}
}

package com.gil.couponsproject.validationlogic;

import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.validationdao.CouponDaoValidation;

public class InputValidationCoupon {


	
	//CouponTitle have to be with lower case and capital letter
	public  boolean checkingCouponTitle(String couponTitle) throws ApplicationException {
		//local variables
	//-----------------------------------------------------------------------------------------
		CouponDaoValidation couponDaoSecurity = new CouponDaoValidation();
		//allowed Up to number(in this example its 10) letter = allowed up to 20 letter
		int allowedUpToNumberLetter = 20;
		//allowed more then number(in this example its 6) letter = allowed more then 6 letter
		int allowMoreThenNumberLetter = 6;
		
	//----------------------------------------------------------------------------------------- 
		boolean correct = true;
		couponTitle =couponDaoSecurity.securityCouponTitle(couponTitle);
		if (couponTitle.length() >= allowMoreThenNumberLetter && allowedUpToNumberLetter  > couponTitle.length() && couponTitle != null) {
			return correct;
		}
		throw new ApplicationException (ErrorType.SECURITY_ERROR , "Coupon title has to contain more then 6 letters");
	}

	//Coupon amount only with numbers
	public   boolean checkingCouponAmount( int couponAmount ) throws ApplicationException {
		//local variables
	//----------------------------------------------------------------------------------------------
		CouponDaoValidation couponDaoSecurity = new CouponDaoValidation();
		int maxAmount = 1000;
		int minAmount = 200;
	//----------------------------------------------------------------------------------------------	
		boolean correct = true;
		couponAmount = couponDaoSecurity.securityCouponAmount(couponAmount);
		if (couponAmount >= minAmount && couponAmount  < maxAmount ) {
			return correct;
		}
		throw new ApplicationException (ErrorType.SECURITY_ERROR , "Coupon amount has to be more then 200 Coupons but less then 1000");
	}

	//Coupon message have to be with lower case and capital letter, and "@" + .com
	public   boolean checkingCouponMessage(String couponMessage) throws ApplicationException {
		//local variables
	//---------------------------------------------------------------------------------------
		CouponDaoValidation couponDaoSecurity = new CouponDaoValidation();
		//allowed Up to number(in this example its 10) letter = allowed up to 20 letter
		int allowedUpToNumberLetter = 20;
		//allowed more then number(in this example its 6) letter = allowed more then 6 letter
		 int allowMoreThenNumberLetter = 6;
	
	//--------------------------------------------------------------------------------------
		boolean correct = true;
		couponMessage = couponDaoSecurity.securityCouponMessage(couponMessage);
		if (couponMessage.length() >= allowMoreThenNumberLetter && couponMessage.length() < allowedUpToNumberLetter  && couponMessage != null) {
			return correct;
		}
	throw new ApplicationException (ErrorType.SECURITY_ERROR , "Coupon message has to contain more then 6 letters");
	}
	
	//Coupon price  only with numbers
		public   boolean checkingCouponPrice( double couponPrice ) throws ApplicationException {
			//local variables
		//--------------------------------------------------------------------------------------
			CouponDaoValidation couponDaoSecurity = new CouponDaoValidation();
			int maxPrice = 1000;
			int minPrice= 0;
	   //--------------------------------------------------------------------------------------	
			boolean correct = true;
			couponPrice = couponDaoSecurity.securityCouponPrice(couponPrice);
			if (couponPrice >= minPrice && couponPrice  < maxPrice ) {
				return correct;
			}
			throw new ApplicationException (ErrorType.SECURITY_ERROR , "Coupon price must be high than 500$ but lower than 1000$");
		}
	//Coupon endDate must be more then today
		public boolean checkingCouponEndDate( long endDate ) throws ApplicationException {
			//local variables :
			boolean correct = true;
		//--------------------------------------------------------------------------------------
			if (endDate < System.currentTimeMillis()) {
				throw new ApplicationException(ErrorType.SECURITY_ERROR , "End Date cannot be today");
			} else {
				return correct;
			}
		}
			

	public void checkIfTheInformationCurrect (Coupon coupon) throws ApplicationException {
		checkingCouponTitle(coupon.getCouponTitle());
		checkingCouponEndDate(coupon.getEndDate());
		checkingCouponAmount(coupon.getCouponAmount());
		checkingCouponMessage(coupon.getCouponMessage());
		checkingCouponPrice(coupon.getcouponPrice());
		
	}
}

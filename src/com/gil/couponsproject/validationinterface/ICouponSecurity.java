package com.gil.couponsproject.validationinterface;

import com.gil.couponsproject.exception.ApplicationException;

public interface ICouponSecurity {

	
	public String securityCouponTitle(String couponTitle)throws ApplicationException;
	public int securityCouponAmount(int couponAmount)throws ApplicationException;
	public String securityCouponMessage(String couponMessage)throws ApplicationException;
	public double securityCouponPrice(double couponPrice)throws ApplicationException;
		
}

package com.gil.couponsproject.dao.interfaces;
import java.util.List;

import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.enums.CouponType;
import com.gil.couponsproject.exception.ApplicationException;

public interface ICoupon {

	public void createCoupon(Coupon coupon) throws ApplicationException;

	public void removeCoupon(long couponid) throws ApplicationException;

	public void updateCoupon(Coupon coupon) throws ApplicationException;

	public Coupon getCoupon(long couponid) throws ApplicationException;
	
	public List<Coupon>getListOfAllCoupons() throws ApplicationException;
	
	public List<Coupon>getListOfCouponsByType(int couponType) throws ApplicationException;;
	
}




package com.gil.couponsproject.utilstest;

import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.CouponLogic;

public class TestTransecationManager {

	
	public static void main(String [] args) throws ApplicationException {
		CouponDao couponDao = new CouponDao();
		CouponLogic couponLogic = new CouponLogic();
		TestJdbcTransecationManagerTest transectionManager = new TestJdbcTransecationManagerTest();
		
		try {
			couponLogic.removeCoupons(2);
			transectionManager.commit();
		} catch (Exception e) {
			transectionManager.rollBack();
			e.printStackTrace();
		}
	}
	
	
	
}

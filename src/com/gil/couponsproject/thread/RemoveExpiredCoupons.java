package com.gil.couponsproject.thread;

import java.sql.Connection;
import com.gil.couponsproject.dao.CouponDao;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;
import com.gil.couponsproject.utilstest.TestJdbcTransecationManagerTest;

public class RemoveExpiredCoupons extends Thread {

	// Thread is a program that running in the background with your project
	// run he is one of the things that are running in the Thread 
	public void run () {
		Connection	connection	= null;
		CouponDao	couponDao	= new CouponDao();
		
		try {
			connection = JdbcAndConnection.getConnection();
			couponDao.removeExpiredCustomerCoupons();
			couponDao.removeExpiredCoupon();
			System.out.println("Remove expired coupons done");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
				try {
					JdbcAndConnection.closeConnection(connection);
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
			
		}
	}
	
}

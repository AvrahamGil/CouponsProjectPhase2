package com.gil.couponsproject.thread;

public class ThreadTest {

	public static void main(String [] args) {
		RemoveExpiredCouponsSchedule removeExpiredCouponsSchedule = new RemoveExpiredCouponsSchedule();
		
	//	removeExpiredCouponsSchedule.start();
		removeExpiredCouponsSchedule.shutDown();
		

	}
}

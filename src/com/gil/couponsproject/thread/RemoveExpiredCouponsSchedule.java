package com.gil.couponsproject.thread;

import java.sql.Timestamp;

public class RemoveExpiredCouponsSchedule extends Thread {

	private boolean isAlive = true;
	
	//will finish the "while" loop
	public void shutDown () {
		boolean isAlive = false;
		System.out.println("Remove Expired Coupons down =" + isAlive);
		
	}
	// run he is one of the things that are running in the Thread 
	public void run () {
		RemoveExpiredCoupons removeExpiredCoupons = new RemoveExpiredCoupons();
		Timestamp timeStamp;
		
		//shut down will finish this while loop
		while (isAlive) {
			timeStamp = new Timestamp(System.currentTimeMillis());
			System.out.println("Remove Expired Coupons Schedule started = " + timeStamp);
			
			//tell the other Thread("RemoveExpiredCoupons) to wake up to delete all the expired coupons
			removeExpiredCoupons.start();
			
			//stop for 24 hours
			System.out.println("Remove Expired Coupons, go to sleep for 24 hours");
			try {
				long millis = 3600*24;
				Thread.sleep(millis);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
}

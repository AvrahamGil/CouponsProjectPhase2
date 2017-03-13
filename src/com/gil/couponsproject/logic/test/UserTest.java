package com.gil.couponsproject.logic.test;

import com.gil.couponsproject.enums.AccessType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.logic.UserLogic;

public class UserTest {

	
	
	public static void main(String [] args) {
		UserLogic userLogic = new UserLogic();
		UserTest userTest = new UserTest();
		/*
		try {
			userTest.adminCompanyLogic("gil company" , "12345" , AccessType.COMPANY);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	/*
	public  void adminCompanyLogic(String name ,String password , AccessType accessType) throws ApplicationException {
		UserLogic userLogic = new UserLogic();
		userLogic.userLogin(name, password, accessType);
	}
	*/
}

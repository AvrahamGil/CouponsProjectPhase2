package com.gil.couponsproject.enums;


//error with a symbol number, to create a better way to understand our mistake's
public enum ErrorType {

	USER_NAME_ALREADY_IN_USE(1),COUPON_NAME_ALREADY_IN_USE(2),

	PASSWORD_MUST_BE_AT_LEAST_SIX_CHARACTER(3),WORNG_EMAIL(4),

	ERROR(5),CREATE_ERROR(6),

	UPDATE_ERROR(7),REMOVE_ERROR(8),

	GET_ERROR(9),CONNECTION_ERROR(10),
	
	COMPANY_NAME_ALREADY_IN_USE(11), CUSTOMER_NAME_ALREADY_IN_USE(12),
	
	CUSTOMER_DOESNT_EXIST(13),COUPONS_CANT_BE_REMOVE(14),
	
	COUPON_NAME_ALREADY_EXIST(15), COUPON_DOESNT_EXIST(16), 
	
	COUPON_EXPIRED(17), MISSING_LIST(18),
	
	ERROR_LIST(19), INVAILD_PASSWORD(19),
	
	COMPANY_DOSENT_EXIST(20) , CUSTOMER_DOSENT_EXIST(21), 
	
	CUSTOMER_ALREADY_BUY_THIS_COUPON(22),
	
	USER_ERROR(23), THREAD_ERROR(24),
	
	SECURITY_ERROR(25), GENERAL_ERROR(26), LOGIN_ERROR(27);
	
	

	private int generalError;

	ErrorType(int generalError) {
		this.generalError = generalError;
	}

	int getgeneralError() {
		return generalError;
	}

	public int getInternalErrorCode() {
		return generalError;
	}

}

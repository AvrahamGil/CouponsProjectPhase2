package com.gil.couponsproject.enums;

public enum AccessType {

	//choose 3 type of.. who you are and what is your role 
	ADMIN(1),
	COMPANY(2),
	CUSTOMER(3);
	
	private int type;
	
	AccessType (int type) {
		this.type=type;
	}
	
	public int getType() {
		return type;
	}
	
}

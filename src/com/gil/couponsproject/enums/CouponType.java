package com.gil.couponsproject.enums;

//What type of coupon i would like to buy
public enum CouponType {
	HOLIDAY("Holiday"),
	TRAVELLING("Traveling"),
	RESTAURANT("Restaurant");
	

	private String name;
	
	CouponType (String name) {
		this.name=name;
	}
	public String getName () {
		return name;
	}
}

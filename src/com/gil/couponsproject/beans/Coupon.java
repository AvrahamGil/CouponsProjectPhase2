package com.gil.couponsproject.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Coupon {

	private long couponID;
	private long companyID;
	private String couponTitle;
	private long startDate;
	private long endDate;
	private int couponPrice;
	private String couponMessage;
	private int couponTypeByNumber;
	private int couponAmount;

	public Coupon() {

	}

	public Coupon(long couponID, long companyID, String couponTitle, long startDate, long endDate,
			String couponMessage, int couponTypeByNumber, int couponAmount, int couponPrice) {
		this.setcouponID(couponID);
		this.setcompanyID(companyID);
		this.setCouponTitle(couponTitle);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setCouponMessage(couponMessage);
		this.setCouponTypeByNumber(couponTypeByNumber);
		this.setCouponAmount(couponAmount);
		this.setcouponPrice(couponPrice);

	}
	
	public Coupon(String couponTitle, long startDate, long endDate, String couponMessage, int couponTypeByNumber,
			int couponAmount, int couponPrice) {
		this.setCouponTitle(couponTitle);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setCouponMessage(couponMessage);
		this.setCouponTypeByNumber(couponTypeByNumber);
		this.setCouponAmount(couponAmount);
		this.setcouponPrice(couponPrice);
	}

	public Coupon(long companyID , String couponTitle, long startDate, long endDate,
			String couponMessage, int couponTypeByNumber, int couponAmount, int couponPrice ) {
		this.setcompanyID(companyID);
		this.setCouponTitle(couponTitle);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setCouponMessage(couponMessage);
		this.setCouponTypeByNumber(couponTypeByNumber);
		this.setCouponAmount(couponAmount);
		this.setcouponPrice(couponPrice);
		
	}
	
	

	public long getcompanyID() {
		return companyID;
	}

	public void setcompanyID(long companyID) {
		this.companyID = companyID;
	}

	public long getcouponID() {
		return couponID;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setcouponID(long couponID) {
		this.couponID = couponID;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public void setStartDate(long date) {
		this.startDate = date;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public int getcouponPrice() {
		return couponPrice;
	}

	public void setcouponPrice(int couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getCouponMessage() {
		return couponMessage;
	}

	public void setCouponMessage(String couponMessage) {
		this.couponMessage = couponMessage;
	}

	public int getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}

	public int getCouponTypeByNumber() {
		return couponTypeByNumber;
	}

	public void setCouponTypeByNumber(int couponTypeByNumber) {
		this.couponTypeByNumber = couponTypeByNumber;
	}



	public String toString() {
		return "Coupon [couponID=" + couponID  + ", companyID=" + companyID
				+ ", couponTitle=" + couponTitle + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", couponPrice=" + couponPrice + ", couponMessage=" + couponMessage + ", couponTypeByNumber="
				+ couponTypeByNumber + ", couponAmount=" + couponAmount + "]";
	}

	

	

}

package com.gil.couponsproject.beans;




import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Coupon {

	private long couponID;
	private long companyID;
	private String couponTitle;
	private long startDate;
	private long endDate;
	private String endDateString;
	private double couponPrice;
	private String couponMessage;
	private int couponTypeByNumber;
	private int couponAmount;

	public Coupon() {

	}

	public Coupon(long couponID, long companyID, String couponTitle, long startDate, long endDate,
			String couponMessage, int couponTypeByNumber, int couponAmount, double couponPrice,String endDateString) {
		this.setcouponID(couponID);
		this.setcompanyID(companyID);
		this.setCouponTitle(couponTitle);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setCouponMessage(couponMessage);
		this.setCouponTypeByNumber(couponTypeByNumber);
		this.setCouponAmount(couponAmount);
		this.setcouponPrice(couponPrice);
		this.setEndDateString(endDateString);
	}
	
	public Coupon(String couponTitle, long startDate, long endDate, String couponMessage, int couponTypeByNumber,String endDateString,
			int couponAmount, double couponPrice) {
		this.setCouponTitle(couponTitle);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setCouponMessage(couponMessage);
		this.setCouponTypeByNumber(couponTypeByNumber);
		this.setCouponAmount(couponAmount);
		this.setcouponPrice(couponPrice);
		this.setEndDateString(endDateString);
	}

	public Coupon(long companyID , String couponTitle, long startDate, long endDate,
			String couponMessage, int couponTypeByNumber, int couponAmount, double couponPrice , String endDateString) {
		this.setcompanyID(companyID);
		this.setCouponTitle(couponTitle);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setCouponMessage(couponMessage);
		this.setCouponTypeByNumber(couponTypeByNumber);
		this.setCouponAmount(couponAmount);
		this.setcouponPrice(couponPrice);
		this.setEndDateString(endDateString);
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

	public double getcouponPrice() {
		return couponPrice;
	}

	public void setcouponPrice(double couponPrice) {
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



	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public String toString() {
		return "Coupon [couponID=" + couponID  + ", companyID=" + companyID
				+ ", couponTitle=" + couponTitle + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", couponPrice=" + couponPrice + ", couponMessage=" + couponMessage + ", couponTypeByNumber="
				+ couponTypeByNumber + ", couponAmount=" + couponAmount + "]";
	}

	

	

}

package com.gil.couponsproject.beans;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginOutput {
	
	private long userID;
	private String userName;

	
	
	public LoginOutput() {
	}
	
	
	
	public long getuserID() {
		return userID;
	}



	public void setuserID(long userID) {
		this.userID = userID;
	}



	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	


	@Override
	public String toString() {
		return "LoginOutput [ID=" + userID + ", userName=" + userName + "]";
	}



	
	
	

}

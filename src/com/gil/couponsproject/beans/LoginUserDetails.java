package com.gil.couponsproject.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.gil.couponsproject.enums.AccessType;

@XmlRootElement
public class LoginUserDetails {
	
	private String userName;
	private String userPassword;
	private AccessType type;
	
	
	public LoginUserDetails() {
	}
	
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}



	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public AccessType  getType() {
		return type;
	}

	public void setType(AccessType  type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "LoginUserDetails [userName=" + userName + ", userPassword=" + userPassword + ", type=" + type + "]";
	}



	
	
	

}

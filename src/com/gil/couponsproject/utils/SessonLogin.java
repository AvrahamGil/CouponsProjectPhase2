package com.gil.couponsproject.utils;

import javax.servlet.http.HttpServletRequest;

import com.gil.couponsproject.beans.LoginOutput;

public class SessonLogin {

	public String ID = "ID";
	
	public Long getUserLogin(HttpServletRequest request) {
		
		return (Long)request.getSession().getAttribute("userID");
	}
}

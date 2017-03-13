package com.gil.couponsproject.api;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/rest/api/*")
public class LoginFilter implements Filter {

	
public void destroy() {
	
		
	}
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req =(HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession(false);
		String url = "http://localhost:8080/CouponsProjectPhase2/";
	
		if (session == null) {
			res.setStatus(401);
			System.out.println("filter denied");
			res.sendRedirect(url);	
			return;
		}
		
		chain.doFilter(request, response);
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	
	

}

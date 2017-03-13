package com.gil.couponsproject.validationlogic;

import com.gil.couponsproject.exception.ApplicationException;

public class TestValidationCompany {
	
		public static void main(String [] args) {
		
			//----------------------------------------securityUserName
			InputValidationCompanyRegistration secureCompanyRegistration = new InputValidationCompanyRegistration();
			/*
					try {
						 secureCompanyRegistration.securityUserName(  "GIlush1" );
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				*/	
					//--------------------------------------securityPassword
				/*	
					try {
						 secureCompanyRegistration.securityUserPassword( "Gilush" );
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					//----------------------------------------securityUserEmail
					
					try {
						 secureCompanyRegistration.checkingCompanyEmail( "gilush@gmail.com");
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
			}
	
		
		public void securityName (String userName) throws ApplicationException {
			InputValidationCompanyRegistration secureCompanyRegistration = new InputValidationCompanyRegistration();
			secureCompanyRegistration.cheackingCompanyName(userName);
		}
		public void securityPassword (String userPassword) throws ApplicationException {
			InputValidationCompanyRegistration secureCompanyRegistration = new InputValidationCompanyRegistration();
			secureCompanyRegistration.checkingCompanyPassword(userPassword);
		}
		public void securitEmail(String userEmail) throws ApplicationException {
			InputValidationCompanyRegistration secureCompanyRegistration = new InputValidationCompanyRegistration();
			 secureCompanyRegistration.checkingCompanyEmail(userEmail);
		}
		
	}




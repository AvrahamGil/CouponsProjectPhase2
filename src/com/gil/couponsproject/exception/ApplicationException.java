package com.gil.couponsproject.exception;

import com.gil.couponsproject.enums.ErrorType;

//This class help's us to show users a fake error .
//it help us to not discover our true error.

public class ApplicationException extends  Exception  {

	private ErrorType errortype;
	
	public ApplicationException(String message , ErrorType errortype) {
	
	super(message);
	this.setErrortype(errortype);
	
	}

	
	private static final long serialVersionUID = 147258369123456789L;

	public ErrorType getErrortype() {
		return errortype;
	}

	public void setErrortype(ErrorType errortype) {
		this.errortype = errortype;
	}


	
	

}

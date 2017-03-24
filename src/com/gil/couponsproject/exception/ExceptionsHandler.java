package com.gil.couponsproject.exception;



import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.gil.couponsproject.beans.ErrorBean;


 
@Provider
public class ExceptionsHandler extends Exception implements ExceptionMapper<Throwable> 
{
   
	private static final long serialVersionUID = 1L;

	@Override
    public Response toResponse(Throwable exception) 
    {
    	System.out.println("Just entered into the exceptions mapper");
    	if (exception instanceof ApplicationException){
    		ApplicationException e = (ApplicationException) exception;
    		
    		int internalErrorCode = e.getErrortype().getInternalErrorCode();
    		String message = e.getMessage();
    		ErrorBean errorBean = new ErrorBean(internalErrorCode, message);
    		return Response.status(700).entity(errorBean).build();
    	}
    	
    	System.out.println("Returning 500 as the http status");
        return Response.status(500).entity("General failure").build();
    }
}
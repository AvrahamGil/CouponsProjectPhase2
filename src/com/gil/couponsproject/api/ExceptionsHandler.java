package com.gil.couponsproject.api;

package com.gil.couponsproject.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.rk.coupons.beans.ErrorBean;
 
package com.rk.coupons.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.rk.coupons.beans.ErrorBean;
 
@Provider
public class ExceptionsHandler extends Exception implements ExceptionMapper<Throwable> 
{
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
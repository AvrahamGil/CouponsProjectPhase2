package com.gil.couponsproject.validationlogic;


import javax.xml.bind.annotation.XmlRootElement;


import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.validationdao.CompanyDaoValidation;


@XmlRootElement
public class InputValidationCompanyRegistration  {
	
	

	
	//UserName have to be with lower case and capital letter
	public  boolean cheackingCompanyName(String companyName) throws ApplicationException {
		CompanyDaoValidation companyDaoSecurity = new CompanyDaoValidation();
		//local variables
	//--------------------------------------------------------------------------------------
		//allowed Up to number(in this example its 20) letter = allowed up to 20 letter
		 int allowedUpToNumberLetter = 20;
		//allowed more then number(in this example its 6) letter = allowed more then 6 letter
		int allowMoreThenNumberLetter = 6;
		
	//------------------------------------------------------------------------------------- 
		boolean correct = true;
		companyName =companyDaoSecurity.securityUserName(companyName);
		if (companyName.length() >= allowMoreThenNumberLetter && allowedUpToNumberLetter  > companyName.length() && companyName != null) {
			return correct;
		}
		throw new ApplicationException (ErrorType.SECURITY_ERROR , "Company Name Has To Contain More Then 6 Letters");
	}

	//UserPassword have to be with lower case and capital letter
	public  boolean checkingCompanyPassword( String companyPassword ) throws ApplicationException {
		CompanyDaoValidation companyDaoSecurity = new CompanyDaoValidation();
		//local variables
	//-------------------------------------------------------------------------------------------------
		//allowed Up to number(in this example its 20) letter = allowed up to 20 letter
		 int allowedUpToNumberLetter = 20;
		//allowed more then number(in this example its 6) letter = allowed more then 6 letter
		 int allowMoreThenNumberLetter = 6;
		//allowed  lower case,capital letter,and numbers..
		String passwordValid = "^[A-Z]+[a-z0-9_-]{3,15}$";
    //------------------------------------------------------------------------------------------------	
		boolean correct = true;
		companyPassword = companyDaoSecurity.securityUserPassword(companyPassword);
		if (companyPassword.length() >= allowMoreThenNumberLetter && allowedUpToNumberLetter  > companyPassword.length() && companyPassword != null) {
			if (companyPassword.matches(passwordValid)) {
				return correct;
			}
			throw new ApplicationException (ErrorType.SECURITY_ERROR , "Invalid Password");
		}
		throw new ApplicationException (ErrorType.SECURITY_ERROR , "Company Password Has To Contain More Then 6 Letters");
	}

	//UserEmail have to be with lower case and capital letter, and "@" + .com
	public   boolean checkingCompanyEmail(String companyEmail) throws ApplicationException {
		CompanyDaoValidation companyDaoSecurity = new CompanyDaoValidation();
		//local variables
	//----------------------------------------------------------------------------------------------------------
		//allowed Up to number(in this example its 20) letter = allowed up to 20 letter
		 int allowedUpToNumberLetter = 50;
		//allowed more then number(in this example its 6) letter = allowed more then 6 letter
		 int allowMoreThenNumberLetter = 6;
		//allowed  lower case,capital letter,and numbers.. have to be "@" and .com
		 String emailValid = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	//---------------------------------------------------------------------------------------------------------- 
		boolean correct = true;
		companyEmail = companyDaoSecurity.securityUserEmail(companyEmail);
		if (companyEmail.length() >= allowMoreThenNumberLetter && companyEmail.length() < allowedUpToNumberLetter  && companyEmail != null) {
			if (companyEmail.matches(emailValid)) {
			return correct;
			}
		throw new ApplicationException (ErrorType.SECURITY_ERROR , "Invalid Email");
		}
	throw new ApplicationException (ErrorType.SECURITY_ERROR , "Company Email Has To Contain More Then 6 Letters");
	}

	public  void checkIfTheInformationisCurrect (Company company) throws ApplicationException {
		cheackingCompanyName(company.getCompanyName());
		checkingCompanyPassword(company.getCompanyPassword());
		checkingCompanyEmail(company.getCompanyEmail());
	}
	


}

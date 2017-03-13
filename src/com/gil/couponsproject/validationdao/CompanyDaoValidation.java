package com.gil.couponsproject.validationdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.bind.annotation.XmlRootElement;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;
import com.gil.couponsproject.validationinterface.ICompanySecurity;
@XmlRootElement
public class CompanyDaoValidation implements ICompanySecurity {

	
	// find details of company in DB
	public String securityUserName(String companyName) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Company company = new Company();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COMPANY WHERE COMPANY_NAME = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			
			preparedStatement.setString(1, company.getCompanyName());
			

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("Error in CompanySecurity, GetCompanyName();,check your company name again ",
					ErrorType.GET_ERROR);
		}
		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return companyName;

	}

	// find details of company in DB
	public String securityUserPassword(String companyPassword) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Company company = new Company();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COMPANY WHERE COMPANY_PASSWORD = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, company.getCompanyPassword());
			

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("Error in CompanySecurity, GetCompanyName();,check your company name again ",
					ErrorType.GET_ERROR);
		}
		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return companyPassword;

	}

	public String securityUserEmail(String companyEmail) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Company company = new Company();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COMPANY WHERE COMPANY_EMAIL = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, company.getCompanyEmail());

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					"Error in CompanySecurity, GetCompanyEmail();,check your company email again ",
					ErrorType.GET_ERROR);
		}
		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return companyEmail;

	}

	

}

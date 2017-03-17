package com.gil.couponsproject.validationdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;
import com.gil.couponsproject.validationinterface.ICustomerSecurity;

public class CustomerDaoValidation implements ICustomerSecurity {

	// find details of company in DB
	public String securityUserName(String customerName) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Customer customer = new Customer();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_NAME = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, customer.getCustomerName());
			

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.SECURITY_ERROR,
					"Error in CustomerSecurity, GetCustomerName();,check your name again ");
		}
		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return customerName;

	}

	// find details of company in DB
	public String securityUserPassword(String customerPassword) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Customer customer = new Customer();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_PASSWORD = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);
			
			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, customer.getCustomerPassword());

			// we have to put the the same parameters that we have in the sql
			// syntax
			preparedStatement.setString(1, customerPassword);

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.SECURITY_ERROR,
					"Error in CustomerSecurity, GetCustomerPassword();,check your password again ");
		}
		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return customerPassword;

	}



}

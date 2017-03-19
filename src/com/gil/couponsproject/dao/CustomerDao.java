package com.gil.couponsproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.beans.Customer;
import com.gil.couponsproject.beans.LoginOutput;
import com.gil.couponsproject.dao.interfaces.ICustomer;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;

public class CustomerDao implements ICustomer {

	// Creating a new customer
	public void createCustomer(Customer customer) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "INSERT INTO CUSTOMER ";
			sql += "(";
			sql += "CUSTOMER_NAME";
			sql += ",CUSTOMER_PASSWORD";
			sql += ")";
			sql += "VALUE ( ?,?  ) ";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerPassword());

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("Welcome");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CREATE_ERROR,
					"Error in CustomerDao,createCustomer();create customer failed, check your ID and PASSWORD again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	// delete customer from DB
	public void removeCustomer(long customerID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "DELETE FROM  CUSTOMER WHERE CUSTOMER_ID = ? ";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, customerID);

			// DB respond
			preparedStatement.executeUpdate();
			System.out.println("Delete customer success");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.REMOVE_ERROR,
					"Error in CustomerDao,removeCustomer(),remove customer failed.. please check your customer ID again");

			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}
	
	
		
	// customer can change his details
	public void updateCustomer(Customer customer) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "UPDATE CUSTOMER SET ";
			sql += "CUSTOMER_NAME = ?";
			sql += ",CUSTOMER_PASSWORD = ?";
			sql += "WHERE CUSTOMER_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerPassword());
			preparedStatement.setLong(3, customer.getCustomerID());

			// DB respond
			preparedStatement.executeUpdate();
			System.out.println("Update customer successfully");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.UPDATE_ERROR,
					"Error in CustomerDao,UpdateCustomer();,update customer failed, check  your ID is again");
		}

		// time to turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	// can watch over customers details
	public Customer getCustomer(long customerID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = new Customer();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT  * FROM CUSTOMER WHERE CUSTOMER_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, customerID);

			// DB respond + information on customers
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			customer = extractCustomerFromResultSet(resultSet);

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.GET_ERROR,
					"Error in CustomerDao,getCustomer();,check your customer ID again");

			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}

		return customer;
	}

	// make a list of customers
	public List<Customer> getListOfAllCustomers() throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;

		// make a list of customers
		List<Customer> listOfCustomers = new ArrayList<Customer>();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM CUSTOMER";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// DB respond + information on customers
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = extractCustomerFromResultSet(resultSet);
				listOfCustomers.add(customer);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					"Error In CustomerDao,listofAllCustomers, no such LIST");

			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}

		return listOfCustomers;
	}

	public boolean isCustomerExistByName(String customerName) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_NAME = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, customerName);

			// DB respond + information on customers
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}

			return true;

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CUSTOMER_NAME_ALREADY_IN_USE,
					"Error in CustomerDao isCustomerExistbyName (); , customer NAME already exist ");
		}
		// time to turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}

	}

	public LoginOutput isCustomerExist( String customerName ,String customerPassword) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		LoginOutput loginOutPut = new LoginOutput();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM CUSTOMER WHERE BINARY CUSTOMER_NAME = ? AND BINARY CUSTOMER_PASSWORD=?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, customerName);
			preparedStatement.setString(2, customerPassword);
			// DB respond + information on customers
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}
			loginOutPut.setuserID(resultSet.getLong("CUSTOMER_ID"));
			loginOutPut.setUserName(resultSet.getString("CUSTOMER_NAME"));

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.INVAILD_PASSWORD,
					"Error in CustomerDao isCustomerExist (); , check your customer PASSWORD again ");
		}
		// time to turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return loginOutPut;

	}

	public boolean isCouponExistByCustomerID(long customerID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT  * FROM CUSTOMER_COUPONS WHERE  CUSTOMER_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, customerID);

			// DB respond + information on customers
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}

			return true;

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.CUSTOMER_NAME_ALREADY_IN_USE,
					"Error in CustomerDao,isCouponExistByCustomer();,customer ID already exist");

			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}

	}
	

	public Coupon getCustomerCoupon(long customerID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();
		Customer customer = new Customer();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT  * FROM CUSTOMER_COUPONS WHERE  CUSTOMER_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we have to put the the same parameters that we have in the sql
			// syntax
			preparedStatement.setLong(1, customerID);

			// DB respond + information on customer coupons
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			coupon.setcouponID(resultSet.getLong("COUPON_ID"));
			customer.setCustomerID(resultSet.getLong("CUSTOMER_ID"));

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.CUSTOMER_NAME_ALREADY_IN_USE,
					"Error in CustomerDao,getCustomerCoupon();,customer ID already in use");

			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return coupon;
	}

	public void removeCustomerCoupons(long companyID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "DELETE  FROM customer_coupons  where COUPON_ID in (select coupon_id from coupon WHERE company_id = ?);";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, companyID);

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("Delete coupons successfully");

			// if we got problem it will tell us about it
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.REMOVE_ERROR,
					"Error in CustomerDao, removeCustomerCoupons();removeCustomerCoupon failed.. please check your ID again");

			// if we have problems "catch" will tell us
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
	}

	// extract result from the DB
	public Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {

		Customer customer = new Customer();
		// resultSet will search for this information
		customer.setCustomerID(resultSet.getLong("CUSTOMER_ID"));
		customer.setCustomerName(resultSet.getString("CUSTOMER_NAME"));
		customer.setCustomerPassword(resultSet.getString("CUSTOMER_PASSWORD"));

		return customer;
	}

}
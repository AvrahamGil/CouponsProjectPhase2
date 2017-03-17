package com.gil.couponsproject.validationdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;
import com.gil.couponsproject.validationinterface.ICouponSecurity;

public class CouponDaoValidation implements ICouponSecurity {

	

	
	public String securityCouponTitle(String couponTitle) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Coupon coupon = new Coupon();

		try {
			// try to connect to  DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON WHERE COUPON_TITLE = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, coupon.getCouponTitle());

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CREATE_ERROR,
					"Error in CouponSecurity, securityUpdateUserID();,cant create a coupon,check your coupon ID again");
		}

		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return couponTitle;
	}

	public int securityCouponAmount(int couponAmount) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Coupon coupon = new Coupon();

		try {
			// try to connect to  DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON WHERE COUPON_AMOUNT = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setInt(1, coupon.getCouponAmount());
			

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CREATE_ERROR,
					"Error in CouponSecurity, securityUpdateUserID();,cant create a coupon,check your coupon ID again");
		}

		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return couponAmount;
	}

	
	public String securityCouponMessage(String couponMessage) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Coupon coupon = new Coupon();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON WHERE COUPON_MESSAGE = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, coupon.getCouponMessage());
		

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CREATE_ERROR,
					"Error in CouponSecurity, securityUpdateUserID();,cant create a coupon,check your coupon ID again");
		}

		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return couponMessage;
	}

	public double securityCouponPrice(double couponPrice) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Coupon coupon = new Coupon();

		try {
			// try to connect to  DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON WHERE COUPON_PRICE = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setDouble(1, coupon.getcouponPrice());

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CREATE_ERROR,
					"Error in CouponSecurity, securityUpdateUserID();,cant create a coupon,check your coupon ID again");
		}

		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return couponPrice;
	}

}

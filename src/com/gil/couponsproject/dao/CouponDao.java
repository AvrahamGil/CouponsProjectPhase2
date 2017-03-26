package com.gil.couponsproject.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.Coupon;
import com.gil.couponsproject.dao.interfaces.ICoupon;
import com.gil.couponsproject.enums.CouponType;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;
import com.gil.couponsproject.utilstest.TestJdbcTransecationManagerTest;

public class CouponDao implements ICoupon {

	// Creating a new coupon
	public void createCoupon(Coupon coupon) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "INSERT INTO COUPON ";
			sql += "(";
			sql += "COMPANY_ID";
			sql += ",COUPON_TITLE";
			sql += ",START_DATE";
			sql += ",END_DATE";
			sql += ",COUPON_AMOUNT";
			sql += ",COUPON_TYPE";
			sql += ",COUPON_MESSAGE";
			sql += ",COUPON_PRICE";
			sql += ")";
			sql += "VALUE ( ? , ? , ? , ? ,? ,? , ? , ?  )";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, coupon.getcompanyID());
			preparedStatement.setString(2, coupon.getCouponTitle());
			preparedStatement.setLong(3, coupon.getStartDate());
			preparedStatement.setLong(4, coupon.getEndDate());
			preparedStatement.setInt(5, coupon.getCouponAmount());
			preparedStatement.setInt(6, coupon.getCouponTypeByNumber());
			preparedStatement.setString(7, coupon.getCouponMessage());
			preparedStatement.setDouble(8, coupon.getcouponPrice());
			

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("you create a new coupon");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CREATE_ERROR,
					"Create coupon failed");
		}

		// turn off connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
	}

	// remove coupon from table in our DB
	public void removeCoupon(long couponID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "DELETE FROM COUPON WHERE COUPON_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponID);

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("Delete coupon from coupon table successfully");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.REMOVE_ERROR,
					"Check your coupon ID again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	// change details in our coupon
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "UPDATE COUPON SET ";
			sql += "COUPON_TITLE = ?";
			sql += ",START_DATE = ?";
			sql += ",END_DATE = ?";
			sql += ",COUPON_AMOUNT = ?";
			sql += ",COUPON_TYPE = ?";
			sql += ",COUPON_MESSAGE = ?";
			sql += ",COUPON_PRICE = ?";
			sql += "WHERE COUPON_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, coupon.getCouponTitle());
			preparedStatement.setLong(2, coupon.getStartDate());
			preparedStatement.setLong(3, coupon.getEndDate());
			preparedStatement.setInt(4, coupon.getCouponAmount());
			preparedStatement.setInt(5, coupon.getCouponTypeByNumber());
			preparedStatement.setString(6, coupon.getCouponMessage());
			preparedStatement.setDouble(7, coupon.getcouponPrice());
			preparedStatement.setLong(8, coupon.getcouponID());

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("Update coupon successfully");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.UPDATE_ERROR,
					"Check your coupon ID again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	// see our coupon details
	public Coupon getCoupon(long couponID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT  * FROM COUPON WHERE  COUPON_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponID);

			// DB respond + information on coupons
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			
			coupon = extractCouponFromResultSet(resultSet);

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.GET_ERROR,
					"Check your coupon ID again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return coupon;
	}

	// make a list of coupons
	public List<Coupon> getListOfAllCoupons() throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		// make a list of coupons
		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();

			// add coupons to our list
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				couponsList.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST, "No such LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return couponsList;
	}

	// make a list of coupons
	public List<Coupon> getListOfCouponsByType(int couponType) throws ApplicationException {

		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		// make a list of coupons
		List<Coupon> listOfCouponsByType = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON  WHERE COUPON_TYPE = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponType);

			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				listOfCouponsByType.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					" No such LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCouponsByType;

	}
	// make a list of coupons
		public List<Coupon> getListOfCouponsByPrice(int couponPrice) throws ApplicationException {

			// turn on connections
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			Coupon coupon = new Coupon();

			// make a list of coupons
			List<Coupon> listOfCouponsByPrice = new ArrayList<Coupon>();

			try {
				// try to connect to our DB
				connection = JdbcAndConnection.getConnection();

				// sql syntax -->in this way we talk with our DB
				String sql ="SELECT * FROM COUPON  WHERE COUPON_PRICE < ?";

				// combining between syntax and our connection
				preparedStatement = connection.prepareStatement(sql);

				// we should have the same parameters that we have in the syntax
				preparedStatement.setInt(1, couponPrice);
				

				// DB respond +  information on coupons
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					coupon = extractCouponFromResultSet(resultSet);
					listOfCouponsByPrice.add(coupon);
				}

				// if we have problems "catch" will tell us
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(ErrorType.MISSING_LIST,
						" no such LIST");

				// turn off connections
			} finally {
				JdbcAndConnection.closeConnection(connection);
				JdbcAndConnection.closePreparedStatement(preparedStatement);
				JdbcAndConnection.closeResultSet(resultSet);
			}
			return listOfCouponsByPrice;

		}

	public List<Coupon> getListOfCompanyCoupons(long companyID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();
		
		List<Coupon> listOfCompanyCoupons = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON WHERE COMPANY_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, companyID);
			
			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				listOfCompanyCoupons.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					"no such LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCompanyCoupons;
	}


	// make a list of customers
	public List<Coupon> getListOfCustomerCoupons(long customerID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		// make a list of customers
		List<Coupon> listOfCustomerCoupons = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON JOIN CUSTOMER_COUPONS ON COUPON.COUPON_ID = CUSTOMER_COUPONS.COUPON_ID WHERE CUSTOMER_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, customerID);

			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);

				listOfCustomerCoupons.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					"No sSuch LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCustomerCoupons;
	}

	// make a list of coupons
	public List<Coupon> getListOfCustomersCouponTypes(long customerID, int couponType) throws ApplicationException {

		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		// make a list of coupons
		List<Coupon> listOfCouponsByType = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON JOIN CUSTOMER_COUPONS WHERE COUPON_TYPE = ? AND CUSTOMER_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponType);
			preparedStatement.setLong(2, customerID);
			
			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				listOfCouponsByType.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					" No such LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCouponsByType;

	}

	// make a list of coupons
	public List<Coupon> getListOfCustomerPrices(long customerID, double couponPrice) throws ApplicationException {

		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		// make a list of coupons
		List<Coupon> listOfCouponsByPrice = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql ="SELECT * FROM COUPON JOIN CUSTOMER_COUPONS WHERE COUPON_PRICE < ? AND CUSTOMER_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setDouble(1, couponPrice);
			preparedStatement.setLong(2, customerID);

			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				listOfCouponsByPrice.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					" No such LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCouponsByPrice;

	}

	// make a list of coupons
	public List<Coupon> getListOfCompanyCouponByType(long companyID, int couponType) throws ApplicationException {

		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		// make a list of coupons
		List<Coupon> listOfCouponsByType = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON WHERE COMPANY_ID = ? AND COUPON_TYPE = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setInt(1, couponType);
			preparedStatement.setLong(2, companyID);

			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				listOfCouponsByType.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					"No such LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCouponsByType;

	}

	// make a list of coupons
	public List<Coupon> getListOfCompanyCouponsByPrice(long companyID, double couponPrice) throws ApplicationException {

		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		// make a list of coupons
		List<Coupon> listOfCompanyCouponsByprice = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON WHERE  COUPON_PRICE < ?  AND COMPANY_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setDouble(1, couponPrice);
			preparedStatement.setLong(2, companyID);

			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				listOfCompanyCouponsByprice.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					"No such LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCompanyCouponsByprice;

	}

	// make a list of coupons
	public List<Coupon> getListOfCompanyCouponByDate(long companyID, long couponStartDate) throws ApplicationException {

		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = new Coupon();

		// make a list of coupons
		List<Coupon> listOfCouponsByType = new ArrayList<Coupon>();

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON JOIN COMPANY WHERE START_DATE < ? AND COMPANY.COMPANY_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponStartDate);
			preparedStatement.setLong(2, companyID);

			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				coupon = extractCouponFromResultSet(resultSet);
				listOfCouponsByType.add(coupon);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					"No such LIST");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCouponsByType;

	}

	public void removeCustomerCoupons(long couponID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "DELETE FROM CUSTOMER_COUPONS WHERE COUPON_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponID);

			// DB Updated
			preparedStatement.executeUpdate();

			System.out.println(" you delete your coupons from couponspurchased seuccesfully ");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.REMOVE_ERROR,
					"Check your coupon ID again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	public boolean isCouponExistByTitle(String couponTitle) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT  * FROM COUPON WHERE  COUPON_TITLE = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, couponTitle);

			// DB respond +  information on coupons
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return false;
			}

			return true;

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.COUPON_NAME_ALREADY_IN_USE,
					"Coupon title already in use");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}

	}

	public long isCouponExpired(long couponEndDate) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COUPON WHERE ";
			sql += "END_DATE = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponEndDate);

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.REMOVE_ERROR,
					"Coupon expired");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return couponEndDate;

	}

	// change only price and end date
	public void updateCouponPriceAndEndDate(long endDate , double couponPrice , long couponID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "UPDATE COUPON SET ";
			sql += "END_DATE = ?";
			sql += ",COUPON_PRICE = ?";
			sql += "WHERE COUPON_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, endDate);
			preparedStatement.setDouble(2, couponPrice);
			preparedStatement.setLong(3, couponID);

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("Update coupon price and end date success");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.UPDATE_ERROR,
					"Check your coupon ID again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	public void removeCouponFromStock(long couponID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "UPDATE COUPON SET ";
			sql += "COUPON_AMOUNT = COUPON_AMOUNT -1 ";
			sql += "WHERE COUPON_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponID);

			// DB Updated
			preparedStatement.executeUpdate();
				
			
			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.ERROR,
					"Check your coupon ID again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	public void createCustomerCoupon(long couponID, long customerID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "INSERT  INTO CUSTOMER_COUPONS ";
			sql += "(";
			sql += "COUPON_ID";
			sql += ",CUSTOMER_ID";
			sql += ")";
			sql += "VALUE ( ? , ?  )";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, couponID);
			preparedStatement.setLong(2, customerID);

			// DB Updated
			preparedStatement.executeUpdate();

			
			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CREATE_ERROR,
					"Check your coupon ID again");
		}

		// turn off connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	public void removeExpiredCustomerCoupons() throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		long current_time = System.currentTimeMillis();
		
		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "DELETE FROM CUSTOMER_COUPONS WHERE COUPON_ID IN (SELECT COUPON_ID FROM COUPON WHERE END_DATE < ?) ";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);
			
			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, current_time);
			// DB Updated
			preparedStatement.executeUpdate();

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.REMOVE_ERROR,
					"Check your coupon ID again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}
	public void removeExpiredCoupon() throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		long current_time = System.currentTimeMillis();
		
		try {
			// try to connect to our DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "DELETE FROM COUPON WHERE END_DATE < ? ";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);
			
			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, current_time);
			// DB Updated
			preparedStatement.executeUpdate();

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.REMOVE_ERROR,
					"Check your coupon ID again");

			// turn off connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	// extract result from the DB
	public Coupon extractCouponFromResultSet(ResultSet resultSet) throws SQLException {
		Coupon coupon = new Coupon();
		
		// resultSet will  search for this information
		coupon.setcouponID(resultSet.getLong("COUPON_ID"));
		coupon.setcompanyID(resultSet.getLong("COMPANY_ID"));
		coupon.setCouponTitle(resultSet.getString("COUPON_TITLE"));
		coupon.setStartDate(resultSet.getLong("START_DATE"));
		coupon.setEndDate(resultSet.getLong("END_DATE"));
		coupon.setCouponAmount(resultSet.getInt("COUPON_AMOUNT"));
		coupon.setCouponTypeByNumber(resultSet.getInt("COUPON_TYPE"));
		coupon.setCouponMessage(resultSet.getString("COUPON_MESSAGE"));
		coupon.setcouponPrice(resultSet.getInt("COUPON_PRICE"));
		

		return coupon;
	}

}

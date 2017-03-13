package com.gil.couponsproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;

public class JdbcAndConnection {

	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//try to connect to DataBase
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection
	("jdbc:mysql://localhost:3306/Coupons?autoReconnect=true&useSSL=false", "root",	"root"); // or Gilush1233
	
		return connection;
	}

	
	public static void closeConnection(Connection connection) throws ApplicationException {

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("Close Connection failed", ErrorType.CONNECTION_ERROR);
		}

	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) throws ApplicationException {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("close PreapredStatment failed", ErrorType.CONNECTION_ERROR);
		}

	}

	public static void closeResultSet(ResultSet resultSet) throws ApplicationException {

		try {
			if (resultSet != null) {
				resultSet.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("close connectionResult failed", ErrorType.ERROR);
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws ApplicationException {
		closeConnection(connection);
		closePreparedStatement(preparedStatement);
		closeResultSet(resultSet);
	}

}

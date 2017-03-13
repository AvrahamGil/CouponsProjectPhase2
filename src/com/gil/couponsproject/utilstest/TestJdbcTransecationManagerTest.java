package com.gil.couponsproject.utilstest;

import java.sql.Connection;
import java.sql.SQLException;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;

public class TestJdbcTransecationManagerTest {

	private Connection connection;

	// turn on connections
	public TestJdbcTransecationManagerTest() throws ApplicationException {
		try {
			this.connection = JdbcAndConnection.getConnection();
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new ApplicationException("TransecationManager failed to connection", ErrorType.GENERAL_ERROR);
		}
	}

	// check if the connection is not null
	public Connection getConnection() throws ApplicationException {
		if (this.connection != null) {
			return connection;
		}
		throw new ApplicationException("connection is null", ErrorType.GENERAL_ERROR);

	}

	// When one process is ok, and the other process is ok to.. we move on in our program.
	public void commit() throws ApplicationException {
		try {
			System.out.println("comit");
			this.connection.commit();
			this.connection.setAutoCommit(true);
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("commit error", ErrorType.GENERAL_ERROR);
		}
	}

	// When one process is ok, but the other process is failed, we have to do restart.
	public void rollBack() throws ApplicationException {

		try {
			System.out.println("rollback");
			this.connection.rollback();
			this.connection.setAutoCommit(true);
			closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("TranseactionManager failed to rollback", ErrorType.GENERAL_ERROR);
		}
	}

	// turn off connections
	public void closeConnection() throws ApplicationException {
		JdbcAndConnection.closeConnection(connection);
		connection = null;
	}

}

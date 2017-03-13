package com.gil.couponsproject.utilstest;

import java.sql.Connection;

import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;

public class JdbcConnectionTest {

	public static void main(String [] args) {
		JdbcConnectionTest jdbcConnectionTest = new JdbcConnectionTest();
		try {
			jdbcConnectionTest.testConnection();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
		public void testConnection() throws ApplicationException {
			Connection connection = null;
			
			try {
				connection = JdbcAndConnection.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcAndConnection.closeConnection(connection);
			}
		}
	
	}
	
	


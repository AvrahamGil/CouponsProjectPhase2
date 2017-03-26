package com.gil.couponsproject.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gil.couponsproject.beans.Company;
import com.gil.couponsproject.beans.LoginOutput;
import com.gil.couponsproject.dao.interfaces.ICompany;
import com.gil.couponsproject.enums.ErrorType;
import com.gil.couponsproject.exception.ApplicationException;
import com.gil.couponsproject.utils.JdbcAndConnection;

@XmlRootElement
public class CompanyDao implements ICompany {

	// Creating a new company
	public void createCompnay(Company company) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql;

			sql = "INSERT INTO COMPANY";
			sql += "(";
			sql += "COMPANY_NAME";
			sql += ",COMPANY_PASSWORD";
			sql += ",COMPANY_EMAIL";
			sql += ")";
			sql += "value (?,?,?)";

			// combining between syntax and connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyPassword());
			preparedStatement.setString(3, company.getCompanyEmail());

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("Welcome");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.CREATE_ERROR,
					"Create company failed , Check your company DETAILS again");
		}
		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	// Delete company
	public void removeCompany(long companyID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "DELETE FROM COMPANY WHERE COMPANY_ID = ? ";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, companyID);

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("you delete your company");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.REMOVE_ERROR,
					"Remove failed");
			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	// update company details
	public void updateCompany(Company company) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "UPDATE COMPANY SET";
			sql += " COMPANY_NAME = 		?";
			sql += ", COMPANY_PASSWORD =	?";
			sql += ", COMPANY_EMAIL = 		?	";
			sql += " WHERE COMPANY_ID = 	?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyPassword());
			preparedStatement.setString(3, company.getCompanyEmail());
			preparedStatement.setLong(4, company.getCompanyID());

			// DB Updated
			preparedStatement.executeUpdate();

			System.out.println("you  update your company");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.UPDATE_ERROR,
					"Update company failed,Please  check your company details again");
		}
		// time to turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
	}

	// find details of company in DB
	public Company getCompany(long companyID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = new Company();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COMPANY WHERE COMPANY_ID = ?  ";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, companyID);

			// DB respond + information on companies
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}
			company = extractCompanyFromResultSet(resultSet);

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.GET_ERROR,
					"Please check your company ID again ");
		}
		// time to turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}

		return company;
	}

	public List<Company> getListOfAllCompanies() throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;

		List<Company> listOfCompanies = new ArrayList<Company>();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COMPANY";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// DB respond + information on companies
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				company = extractCompanyFromResultSet(resultSet);
				listOfCompanies.add(company);
			}

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.MISSING_LIST,
					" No such LIST");

			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return listOfCompanies;
	}

	public boolean isCompanyExistsByName(String companyName) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COMPANY WHERE COMPANY_NAME = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, companyName);

			// DB respond + information on companies
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}

			return true;

			// if we have problems "catch" will tell us
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.COMPANY_NAME_ALREADY_IN_USE,
					" Company name already in use ");
		}
		// time to turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
	}

	public Company isCompanyExistByID(long companyID) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Company company = new Company();

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COMPANY WHERE COMPANY_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax

			preparedStatement.setLong(1, company.getCompanyID());

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.GET_ERROR,
					"Check your company name again ");
		}
		// turn off our connections
		finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}
		return company;
	}

	public LoginOutput isCompanyExist(String companyName,String companyPassword) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		LoginOutput loginOutPut = new LoginOutput();
		

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "SELECT * FROM COMPANY WHERE BINARY COMPANY_NAME=? AND BINARY COMPANY_PASSWORD = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setString(1, companyName);
			preparedStatement.setString(2, companyPassword);
			// DB respond + information on companies
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			
			loginOutPut.setuserID(resultSet.getLong("COMPANY_ID"));
			loginOutPut.setUserName(resultSet.getString("COMPANY_NAME"));
			

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.INVAILD_PASSWORD,
					" Please check your details again");

			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);
			JdbcAndConnection.closeResultSet(resultSet);
		}
		return loginOutPut;
	}

	
	public void removeCompanyCoupons(long companyID) throws ApplicationException {
		// turn on connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// try to connect to DB
			connection = JdbcAndConnection.getConnection();

			// sql syntax -->in this way we talk with our DB
			String sql = "DELETE FROM COUPON WHERE COMPANY_ID = ?";

			// combining between syntax and our connection
			preparedStatement = connection.prepareStatement(sql);

			// we should have the same parameters that we have in the syntax
			preparedStatement.setLong(1, companyID);

			// DB Updated
			preparedStatement.executeUpdate();
			System.out.println("you just sucssed to remove your company coupons.. ");

			// if we have problems "catch" will tell us
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(
					ErrorType.REMOVE_ERROR,
					" Please  check  your ID again");

			// time to turn off our connections
		} finally {
			JdbcAndConnection.closeConnection(connection);
			JdbcAndConnection.closePreparedStatement(preparedStatement);

		}

	}

	// extract result from the DB
	public Company extractCompanyFromResultSet(ResultSet resultSet) throws SQLException {

		Company company = new Company();

		// resultSet will search for this information
		company.setCompanyID(resultSet.getLong("COMPANY_ID"));
		company.setCompanyName(resultSet.getString("COMPANY_NAME"));
		company.setCompanyPassword(resultSet.getString("COMPANY_PASSWORD"));
		company.setCompanyEmail(resultSet.getString("COMPANY_EMAIL"));

		return company;
	}

}
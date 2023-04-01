package com.cims.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cims.dto.Criminal;
import com.cims.dto.CriminalImpl;
import com.cims.exception.CanNotMap;
import com.cims.exception.NoRecordFoundException;
import com.cims.exception.SomeThingWrongException;
import com.cims.ui.ConsoleColors;

public class CriminalDaoImpl implements CriminalDao{
	
	@Override
	public void addCriminal(Criminal criminal) throws  SomeThingWrongException{
		
		
		
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO criminal (criminalId, name, dob, gender, identifyingMark, firstArrestDate, arrestedFromPsArea) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, criminal.getCriminalId());
			ps.setString(2, criminal.getName());
			ps.setDate(3, Date.valueOf(criminal.getDob()));
			ps.setString(4, criminal.getGender());
			ps.setString(5, criminal.getIdentifyingMark());
			ps.setDate(6, Date.valueOf(criminal.getFirstArrestDate()));
			ps.setString(7, criminal.getArrestFromPsArea());
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			
			throw new SomeThingWrongException();
		}finally {
			
				try {
					DBUtils.closeConnection(connection);
				} catch (SQLException e) {
					throw new SomeThingWrongException();
					}				
				}
		}
	
	@Override
	public void updateCriminal(Criminal criminal) throws SomeThingWrongException, NoRecordFoundException {
		//if no category for given category id then this line will throw NoRecordFoundException
		getCriminalById(criminal.getCriminalId());
		//you are here means category found for the given category id

		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//prepare the query
			String UPDATE_QUERY = "UPDATE criminal SET name = ?, dob = ?, gender = ?, identifyingMark = ?, firstArrestDate = ?, arrestedFromPsArea = ? WHERE criminalId = ? AND isDelete !=1";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, criminal.getName());
			ps.setDate(2, Date.valueOf(criminal.getDob()));
			ps.setString(3, criminal.getGender());
			ps.setString(4, criminal.getIdentifyingMark());
			ps.setDate(5, Date.valueOf(criminal.getFirstArrestDate()));
			ps.setString(6, criminal.getArrestFromPsArea());
			ps.setString(7, criminal.getCriminalId());
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}	
	}
	
	@Override
	public Criminal getCriminalById(String criminalId) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		Criminal criminal = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM criminal WHERE criminalId = ? AND isDelete !=1";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, criminalId);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException(ConsoleColors.ANSI_RED+"No Criminal Found for given id"+ConsoleColors.ANSI_RESET);
			}
			resultSet.next();
			criminal = new CriminalImpl();
			criminal.setCriminalId(resultSet.getString("criminalId"));
			criminal.setName(resultSet.getString("name"));
			criminal.setDob(resultSet.getDate("dob").toLocalDate());
			criminal.setGender(resultSet.getString("gender"));
			criminal.setIdentifyingMark(resultSet.getString("identifyingMark"));
			criminal.setFirstArrestDate(resultSet.getDate("firstArrestDate").toLocalDate());
			criminal.setArrestFromPsArea(resultSet.getString("arrestedFromPsArea"));
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
		return criminal;
	}
	@Override
	public void assignCriminalToCrime(String crimeId,String criminalId) throws SomeThingWrongException, NoRecordFoundException, CanNotMap{
		
		getCriminalById(criminalId);
		
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO crime_criminal (crimeId, criminalId) values ("
					+ "(select id from crime where crimeId=?),"
					+ "(select id from criminal where criminalId= ?)"
					+ ")";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			ps.setString(1, crimeId);
			ps.setString(2, criminalId);
			
			//execute query
	        if(ps.executeUpdate()==0) {
	        	throw new CanNotMap("CANNOT MAPPED TO SAME CRIME AGAIN");
	        }
	       
			
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
	}
	@Override
	public void removeCriminalFromCrime(String crimeId,String criminalId) throws SomeThingWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String DELETE_QUERY = "DELETE FROM crime_criminal WHERE crimeId= "
					+ "(SELECT id FROM crime WHERE crimeId=?) AND  "
					+ "criminalId= (SELECT id FROM criminal WHERE criminalId= ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
			ps.setString(1, crimeId);
			ps.setString(2, criminalId);
			//execute query
	        ps.executeUpdate();
	       
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
	}
	
	@Override
	public void deleteCriminal(String criminalId) throws SomeThingWrongException, NoRecordFoundException {
		getCriminalById(criminalId);
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE criminal SET isDelete=1 WHERE criminalId= ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			ps.setString(1, criminalId);
			//execute query
	        ps.executeUpdate();
			
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
	}
	@Override
	public List<Criminal> getCriminalByName(String name) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		Criminal criminal = null;
		List<Criminal> criminalList=new ArrayList<>();
		try {
			//connect to database
			
			connection = DBUtils.connectToDatabase();
			//prepare the query
			
			String SELECT_QUERY = "SELECT * FROM criminal WHERE name like ? AND isDelete !=1";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, "%"+name+"%");
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Criminal Found for given id");
			}
			
			while(resultSet.next()) {
			criminal = new CriminalImpl();
			criminal.setCriminalId(resultSet.getString("criminalId"));
			criminal.setName(resultSet.getString("name"));
			criminal.setDob(resultSet.getDate("dob").toLocalDate());
			criminal.setGender(resultSet.getString("gender"));
			criminal.setIdentifyingMark(resultSet.getString("identifyingMark"));
			criminal.setFirstArrestDate(resultSet.getDate("firstArrestDate").toLocalDate());
			criminal.setArrestFromPsArea(resultSet.getString("arrestedFromPsArea"));
			criminalList.add(criminal);
			}
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
		return criminalList;
	}
	
	
}

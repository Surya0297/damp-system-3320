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

import com.cims.dto.Crime;
import com.cims.dto.CrimeImpl;
import com.cims.dto.CriminalImpl;
import com.cims.exception.NoRecordFoundException;
import com.cims.exception.SomeThingWrongException;


public class CrimeDaoImpl implements CrimeDao {
	
	@Override
	public void addCrime(Crime crime) throws SomeThingWrongException, FileNotFoundException {

		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO crime (crimeId, crimeType, description, psArea, date, victimName) VALUES (?, ?, ?, ?, ?, ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, crime.getCrimeId());
			ps.setString(2, crime.getCrimeType());
			ps.setString(3, crime.getDescription());
			ps.setString(4, crime.getPsArea());
			ps.setDate(5, Date.valueOf(crime.getDate()));
			ps.setString(6, crime.getVictimName());
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			PrintWriter pw = new PrintWriter(new FileOutputStream("log"));
			pw.append(sqlEx.getMessage());
			pw.close();
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
	public void updateCrime(Crime crime) throws SomeThingWrongException, NoRecordFoundException {
		//if no category for given category id then this line will throw NoRecordFoundException
		getCrimeById(crime.getCrimeId());
		//you are here means category found for the given category id
		
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//prepare the query
			String UPDATE_QUERY = "UPDATE crime SET crimeType = ?, description = ?, psArea = ?, date = ?, victimName = ? WHERE crimeId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, crime.getCrimeType());
			ps.setString(2, crime.getDescription());
			ps.setString(3, crime.getPsArea());
			ps.setDate(4, Date.valueOf(crime.getDate()));
			ps.setString(5, crime.getVictimName());
			ps.setString(6, crime.getCrimeId());
			
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
	public Crime getCrimeById(String crimeId) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		Crime crime = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM crime WHERE crimeId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, crimeId);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Criminal Found for given id");
			}
			resultSet.next();
			crime = new CrimeImpl();
			crime.setCrimeId(resultSet.getString("crimeId"));
			crime.setCrimeType(resultSet.getString("crimeType"));
			crime.setDescription(resultSet.getString("description"));
			crime.setPsArea(resultSet.getString("psArea"));		
			crime.setDate(resultSet.getDate("date").toLocalDate());
			crime.setVictimName(resultSet.getString("victimName"));
		
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
		return crime;
	}
	
	@Override
	public void deleteCrime(String crimeId) throws SomeThingWrongException, NoRecordFoundException {
		getCrimeById(crimeId);
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE crime SET isDelete=1 WHERE crimeId= ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			ps.setString(1, crimeId);
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
	public List<Crime> getCrimeByPsAreaAndDateRange(String psArea, LocalDate sd, LocalDate ed) throws NoRecordFoundException, SomeThingWrongException{
		Connection connection = null;
		List<Crime> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "select c.crimeType, c.description, c.psArea, c.date, c.victimName, c.status "
					+ "ci.name from "
					+ "crime c inner join crime_criminal cc on c.id = cc.crimeId "
					+ "and psArea = ? and c.isDelete !=1 "
					+ "and date between ? and ? "
					+ "inner join criminal ci on cc.criminalId = ci.id and ci.isDelete !=1;";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//stuff query
			ps.setString(1, psArea);
			ps.setDate(2, Date.valueOf(sd));
			ps.setDate(3, Date.valueOf(ed));
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			System.out.println("111111");
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Record Found");
			}
			list = new ArrayList<>();
			while(resultSet.next()) {
				list.add(new CrimeImpl(resultSet.getString("crimeType"), resultSet.getString("description"), resultSet.getString("psArea"), resultSet.getDate("date").toLocalDate(), resultSet.getString("victimName"), resultSet.getString("status"), new CriminalImpl(resultSet.getString("name"))));
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
		return list;
	
	}
	@Override
	public List<Crime> getCrimeByTypeAndDateRange(String crimeType, LocalDate sd, LocalDate ed) throws NoRecordFoundException, SomeThingWrongException{
		Connection connection = null;
		List<Crime> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "select c.crimeType, c.description, c.psArea, c.date, c.victimArea, c.status "
					+ "ci.name from "
					+ "crime c inner join crime_criminal cc on c.id = cc.crimeId "
					+ "and crimeType = ? and c.isDelete !=1 "
					+ "and date between ? and ? "
					+ "inner join criminal ci on cc.criminalId = ci.id and ci.isDelete !=1;";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//stuff query
			ps.setString(1, crimeType);
			ps.setDate(2, Date.valueOf(sd));
			ps.setDate(3, Date.valueOf(ed));
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Record Found");
			}
			list = new ArrayList<>();
			while(resultSet.next()) {
				list.add(new CrimeImpl(resultSet.getString("crimeType"), resultSet.getString("description"), resultSet.getString("psArea"), resultSet.getDate("date").toLocalDate(), resultSet.getString("victimName"), resultSet.getString("status"), new CriminalImpl(resultSet.getString("name"))));
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
		return list;
	
	}
	@Override
	public List<Crime> getCrimeByDescription(String description) throws NoRecordFoundException, SomeThingWrongException{
		Connection connection = null;
		List<Crime> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "select c.crimeType, c.description, c.psArea, c.date, c.victimArea, c.status "
					+ "ci.name from "
					+ "crime c inner join crime_criminal cc on c.id = cc.crimeId  "
					+ "and description like %?% and c.isDelete !=1 "
					+ "inner join criminal ci on cc.criminalId = ci.id and ci.isDelete !=1;";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//stuff query
			ps.setString(1, description);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Record Found");
			}
			list = new ArrayList<>();
			while(resultSet.next()) {
				list.add(new CrimeImpl(resultSet.getString("crimeType"), resultSet.getString("description"), resultSet.getString("psArea"), resultSet.getDate("date").toLocalDate(), resultSet.getString("victimName"), resultSet.getString("status"), new CriminalImpl(resultSet.getString("name"))));
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
		return list;
	
	}
	@Override
	public void updateStatus(String crimeId,String status) throws SomeThingWrongException, NoRecordFoundException {
		getCrimeById(crimeId);
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//prepare the query
			String UPDATE_QUERY = "UPDATE crime SET status = ? WHERE crimelId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, status);
			ps.setString(2, crimeId);
			
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
	
	

}
	

	
	


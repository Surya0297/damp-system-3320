package com.cims.dao;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import com.cims.dto.Crime;
import com.cims.exception.NoRecordFoundException;
import com.cims.exception.SomeThingWrongException;

public interface CrimeDao {
	
	public void addCrime(Crime crime) throws  FileNotFoundException, SomeThingWrongException;

	public Crime getCrimeById(String crimeId) throws SomeThingWrongException, NoRecordFoundException;

	void updateCrime(Crime crime) throws SomeThingWrongException, NoRecordFoundException;
	
	public void deleteCrime(String crimeId) throws SomeThingWrongException, NoRecordFoundException ;

	public List<Crime> getCrimeByPsAreaAndDateRange(String psArea, LocalDate sd, LocalDate ed)
			throws NoRecordFoundException, SomeThingWrongException;

	public List<Crime> getCrimeByTypeAndDateRange(String crimeType, LocalDate sd, LocalDate ed)
			throws NoRecordFoundException, SomeThingWrongException;

	public	List<Crime> getCrimeByDescription(String description) throws NoRecordFoundException, SomeThingWrongException;

	public void updateStatus(String crimeId, String status) throws SomeThingWrongException, NoRecordFoundException;

	List<Crime> getAllCrime() throws NoRecordFoundException, SomeThingWrongException;

}

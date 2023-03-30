package com.cims.dao;

import java.io.FileNotFoundException;

import com.cims.dto.Criminal;
import com.cims.exception.NoRecordFoundException;
import com.cims.exception.SomeThingWrongException;


public interface CriminalDao{

	public void addCriminal(Criminal criminal) throws  FileNotFoundException, SomeThingWrongException;

	public void updateCriminal(Criminal criminal) throws SomeThingWrongException, NoRecordFoundException;

	public Criminal getCriminalById(String criminalId) throws SomeThingWrongException, NoRecordFoundException;
	
	public void assignCriminalToCrime(String crimeId,String criminalId) throws SomeThingWrongException, NoRecordFoundException;

	public void removeCriminalFromCrime(String crimeId,String criminalId) throws SomeThingWrongException;
	
	public void deleteCriminal(String criminalId) throws SomeThingWrongException, NoRecordFoundException ;

	public Criminal getCriminalByName(String name) throws SomeThingWrongException, NoRecordFoundException;


}

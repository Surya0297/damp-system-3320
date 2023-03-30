package com.cims.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Crime {
	
	/**
	 * return id of the calling crime object
	 * @return - The id of crime
	 */
	public String getCrimeId();
	/**
	 * Sets the id of the calling crime object
	 * @param crimeId - The id of crime 
	 */
	public void setCrimeId(String crimeId);
	/**
	 * return crimeType of the calling crime object
	 * @return - The Type of crime
	 */
	public String getCrimeType() ;
	/**
	 * Sets the crime Type of the calling crime object
	 * @param crimeTpe - The Type of crime 
	 */
	public void setCrimeType(String crimeType);
	/**
	 * return description of the calling crime object
	 * @return - The Description of crime
	 */
	public String getDescription() ;
	/**
	 * Sets the description of the calling crime object
	 * @param description - The description of crime 
	 */
	public void setDescription(String description);
	/**
	 * return Police Station Area of the calling crime object
	 * @return - The Police Station Area of crime
	 */
	public String getPsArea() ;
	/**
	 * Sets the Ploice Station Area of the calling crime object
	 * @param psArea - The Police Station Area of crime 
	 */
	public void setPsArea(String psArea);
	/**
	 * return date of crime Area of the calling crime object
	 * @return - The date and time of crime
	 */
	public LocalDate getDate() ;
	/**
	 * Sets the date and time of the calling crime object
	 * @param date - The date and time of crime 
	 */
	public void setDate(LocalDate date);
	/**
	 * return Name of Victim of calling crime object
	 * @return - The Name of Victim 
	 */
	public String getVictimName() ;
	/**
	 * Sets the Name of Victim of the calling crime object
	 * @param victimName - The Name Of Victim 
	 */
	public void setVictimName(String victimName);
	/**
	 * return status of crime object
	 * @return - The status of crime  
	 */
	public String getStatus() ;
	/**
	 * Sets the Status of crime of the calling crime object
	 * @param status - The status Of Victim 
	 */
	public void setStatus(String status);
}

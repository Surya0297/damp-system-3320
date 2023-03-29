package com.cims.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Criminal {
	/**
	 * return id of the calling criminal object
	 * @return - The id of criminal
	 */
	public String getCriminalId();
	/**
	 * Sets the id of the calling criminal object
	 * @param criminalId - The id of criminal 
	 */
	public void setCriminalId(String criminalId);
	/**
	 * return Name of the calling criminal object
	 * @return - The name of criminal
	 */
	public String getName() ;
	/**
	 * Sets the Name of the calling criminal object
	 * @param name - The name of criminal 
	 */
	public void setName(String name) ;
	/**
	 * return Date Of Birth of the calling criminal object
	 * @return - The dob of criminal
	 */
	public LocalDate getDob() ;
	/**
	 * Sets the dob of the calling criminal object
	 * @param dob - The Date OF Birth of criminal 
	 */
	public void setDob(LocalDate dob) ;
	/**
	 * return gender of the calling criminal object
	 * @return - The gender of criminal
	 */
	public String getGender() ;
	/**
	 * Sets the gender of the calling criminal object
	 * @param gender - The Date OF Birth of criminal 
	 */
	public void setGender(String gender) ;
	/**
	 * return Identifying Mark of the calling criminal object
	 * @return - The Identifying Mark of criminal
	 */
	public String getIdentifyingMark() ;
	/**
	 * Sets the Identifying Mark of the calling criminal object
	 * @param identifyingMark - The Identifying Mark of criminal 
	 */
	public void setIdentifyingMark(String identifyingMark) ;
	/**
	 * return First Arrest Date of the calling criminal object
	 * @return - The First Arrest Date of criminal
	 */
	public LocalDate getFirstArrestDate() ;
	/**
	 * Sets the First Arrest Date of the calling criminal object
	 * @param firstArrestDate- The  First Arrest Date of criminal 
	 */
	public void setFirstArrestDate(LocalDate firstArrestDate) ;
	/**
	 * return Police Station Of Arrest of the calling criminal object
	 * @return - The Police Station Of Arrest of criminal
	 */
	public String getArrestFromPsArea() ;
	/**
	 * Sets the Police Station Of Arrest of the calling criminal object
	 * @param arrestFromPsArea- Police Station Of Arrest of criminal 
	 */
	public void setArrestFromPsArea(String arrestFromPsArea) ;

}

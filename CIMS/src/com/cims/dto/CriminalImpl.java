package com.cims.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CriminalImpl implements Criminal{
	
	private String CriminalId;
	private String name;
	private LocalDate dob;
	private String gender;
	private String IdentifyingMark;
	private LocalDate firstArrestDate;
	private String arrestFromPsArea;
	
	public CriminalImpl(String criminalId, String name, LocalDate dob, String gender, String identifyingMark,
			LocalDate firstArrestDate, String arrestFromPsArea) {
		super();
		CriminalId = criminalId;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		IdentifyingMark = identifyingMark;
		this.firstArrestDate = firstArrestDate;
		this.arrestFromPsArea = arrestFromPsArea;
	}
	@Override
	public String getCriminalId() {
		return CriminalId;
	}
	@Override
	public void setCriminalId(String criminalId) {
		CriminalId = criminalId;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public LocalDate getDob() {
		return dob;
	}
	@Override
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	@Override
	public String getGender() {
		return gender;
	}
	@Override
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String getIdentifyingMark() {
		return IdentifyingMark;
	}
	@Override
	public void setIdentifyingMark(String identifyingMark) {
		IdentifyingMark = identifyingMark;
	}
	@Override
	public LocalDate getFirstArrestDate() {
		return firstArrestDate;
	}
	@Override
	public void setFirstArrestDate(LocalDate firstArrestDate) {
		this.firstArrestDate = firstArrestDate;
	}
	@Override
	public String getArrestFromPsArea() {
		return arrestFromPsArea;
	}
	@Override
	public void setArrestFromPsArea(String arrestFromPsArea) {
		this.arrestFromPsArea = arrestFromPsArea;
	}
	@Override
	public String toString() {
		return "CriminalImpl [CriminalId=" + CriminalId + ", name=" + name + ", dob=" + dob + ", gender=" + gender
				+ ", IdentifyingMark=" + IdentifyingMark + ", firstArrestDate=" + firstArrestDate
				+ ", arrestFromPsArea=" + arrestFromPsArea + "]";
	}
	
	
	
}

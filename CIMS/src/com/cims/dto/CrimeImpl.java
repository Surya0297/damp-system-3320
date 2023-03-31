package com.cims.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CrimeImpl implements Crime {
	
	private String crimeId;
	private String crimeType;
	private String description;
	private String psArea;
	private LocalDate date;
	private String victimName;
	private String status;
	private Criminal criminal;
	
	public CrimeImpl() {
		
	}
	
	
	public CrimeImpl(String crimeType, String description, String psArea, LocalDate date,
			String victimName, String status, Criminal criminal) {
		super();
		this.crimeType = crimeType;
		this.description = description;
		this.psArea = psArea;
		this.date = date;
		this.victimName = victimName;
		this.status = status;
		this.criminal = criminal;
	}
	
	

	public CrimeImpl(String crimeId, String crimeType, String description, String psArea, LocalDate date,
			String victimName) {
		super();
		this.crimeId = crimeId;
		this.crimeType = crimeType;
		this.description = description;
		this.psArea = psArea;
		this.date = date;
		this.victimName = victimName;
	}


	public CrimeImpl(String crimeId, String crimeType, String description, String psArea, LocalDate date,
			String victimName, String status) {
		super();
		this.crimeId = crimeId;
		this.crimeType = crimeType;
		this.description = description;
		this.psArea = psArea;
		this.date = date;
		this.victimName = victimName;
		this.status = status;
	}
	@Override
	public String getCrimeId() {
		return crimeId;
	}
	@Override
	public void setCrimeId(String crimeId) {
		this.crimeId = crimeId;
	}
	@Override
	public String getCrimeType() {
		return crimeType;
	}
	@Override
	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String getPsArea() {
		return psArea;
	}
	@Override
	public void setPsArea(String psArea) {
		this.psArea = psArea;
	}
	@Override
	public LocalDate getDate() {
		return date;
	}
	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String getVictimName() {
		return victimName;
	}
	@Override
	public void setVictimName(String victimName) {
		this.victimName = victimName;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CrimeImpl [crimeId=" + crimeId + ", crimeType=" + crimeType + ", description=" + description
				+ ", psArea=" + psArea + ", date=" + date + ", victimName=" + victimName + ", status=" + status + "]";
	}
	
	
	
	
}

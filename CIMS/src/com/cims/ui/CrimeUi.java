package com.cims.ui;

import java.io.Console;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cims.dao.CrimeDao;
import com.cims.dao.CrimeDaoImpl;
import com.cims.dto.Crime;
import com.cims.dto.CrimeImpl;
import com.cims.exception.NoRecordFoundException;
import com.cims.exception.SomeThingWrongException;

public class CrimeUi {
	
	private CrimeDao crimeDao;
	private Scanner sc;
	
	public CrimeUi(Scanner sc) {
		crimeDao= new CrimeDaoImpl();
		this.sc=sc;
	}
	
	public void addCrime() {
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime ID: "+ConsoleColors.GREEN_BOLD);
		String crimeId = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Type: "+ConsoleColors.GREEN_BOLD);
		String crimeType = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Description: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String description = sc.nextLine();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Police Station Area: "+ConsoleColors.GREEN_BOLD);
		String psArea = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Date: "+ConsoleColors.GREEN_BOLD);
		LocalDate date = LocalDate.parse(sc.next());
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Victim Name: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String victimName = sc.nextLine();
		
		Crime crime = new CrimeImpl(crimeId, crimeType, description, psArea, date, victimName);
		
		try {
			crimeDao.addCrime(crime);
			System.out.println(ConsoleColors.GREEN_BOLD+"CRIME ADDED 📤 TO DATABASE"+ConsoleColors.ANSI_RESET);
		} catch (FileNotFoundException | SomeThingWrongException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}
	
	public void updateCrime() {
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime ID for which you want to update details: "+ConsoleColors.GREEN_BOLD);
		String crimeId = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Type: "+ConsoleColors.GREEN_BOLD);
		String crimeType = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Description: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String description = sc.nextLine();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Police Station Area: "+ConsoleColors.GREEN_BOLD);
		String psArea = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Date: "+ConsoleColors.GREEN_BOLD);
		LocalDate date = LocalDate.parse(sc.next());
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Victim Name: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String victimName = sc.nextLine();
		System.out.println();
		Crime crime = new CrimeImpl(crimeId, crimeType, description, psArea, date, victimName);
		
		try {
			crimeDao.updateCrime(crime);
			System.out.println(ConsoleColors.GREEN_BOLD+"CRIME DETAILS UPDATED "+ConsoleColors.ANSI_RED + "📤"+ConsoleColors.GREEN_BOLD +" IN DATABASE"+ConsoleColors.ANSI_RESET);
		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

	public void deleteCrimeById() {
		
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime ID: "+ConsoleColors.GREEN_BOLD);
		String crimeId = sc.next();
		try {
			crimeDao.deleteCrime(crimeId);
			System.out.println(ConsoleColors.GREEN_BOLD+"CRIME DELETED "+ConsoleColors.ANSI_RED +"❌"+ConsoleColors.GREEN_BOLD+" FROM DATABASE"+ConsoleColors.ANSI_RESET);
		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

	public void viewCrimeByArea() {
		List<Crime> crimeList=new ArrayList<>();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Police Station Area: "+ConsoleColors.GREEN_BOLD);
		String psArea = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Start Date: "+ConsoleColors.GREEN_BOLD);
		LocalDate sd = LocalDate.parse(sc.next());
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime End Date: "+ConsoleColors.GREEN_BOLD);
		LocalDate ed = LocalDate.parse(sc.next());
		
		try {
			 crimeList=crimeDao.getCrimeByPsAreaAndDateRange(psArea, sd, ed);
			 DisplayData.printInfo(crimeList);
		} catch (NoRecordFoundException | SomeThingWrongException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

	public void searchCrime() {
		// TODO Auto-generated method stub
		List<Crime> crimeList=new ArrayList<>();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Description of crime: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String description = sc.nextLine();
		
		
		try {
			 crimeList=crimeDao.getCrimeByDescription(description);
			 DisplayData.printInfo(crimeList);
		} catch (NoRecordFoundException | SomeThingWrongException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

	public void viewCrimeByType() {
		List<Crime> crimeList=new ArrayList<>();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Type: "+ConsoleColors.GREEN_BOLD);
		String crimeType = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Start Date: "+ConsoleColors.GREEN_BOLD);
		LocalDate sd = LocalDate.parse(sc.next());
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime End Date: "+ConsoleColors.GREEN_BOLD);
		LocalDate ed = LocalDate.parse(sc.next());
		
		try {
			 crimeList=crimeDao.getCrimeByTypeAndDateRange(crimeType, sd, ed);
			 DisplayData.printInfo(crimeList);
		} catch (NoRecordFoundException | SomeThingWrongException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

	public void updateCrimeStatus() {
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime ID: "+ConsoleColors.GREEN_BOLD);
		String crimeId = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Staus (solved / unsolved): "+ConsoleColors.GREEN_BOLD);
		String status = sc.next();
		
		try {
			crimeDao.updateStatus(crimeId,status);
			System.out.println(ConsoleColors.GREEN_BOLD+"CRIME STATUS 📤 UPDATED IN DATABASE"+ConsoleColors.ANSI_RESET);

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

	public void getAllCrime() {
		List<Crime> crimeList=new ArrayList<>();
		
		
		try {
			 crimeList=crimeDao.getAllCrime();
			 DisplayData.printCrimeData(crimeList);
		} catch (NoRecordFoundException | SomeThingWrongException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

	
	
}

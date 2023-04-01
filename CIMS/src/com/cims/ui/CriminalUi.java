package com.cims.ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.cims.dao.CrimeDao;
import com.cims.dao.CrimeDaoImpl;
import com.cims.dao.CriminalDao;
import com.cims.dao.CriminalDaoImpl;
import com.cims.dto.Crime;
import com.cims.dto.CrimeImpl;
import com.cims.dto.Criminal;
import com.cims.dto.CriminalImpl;
import com.cims.exception.CanNotMap;
import com.cims.exception.NoRecordFoundException;
import com.cims.exception.SomeThingWrongException;

public class CriminalUi {
	
	private CriminalDao criminalDao;
	private Scanner sc;
	
	public CriminalUi(Scanner sc) {
		criminalDao= new CriminalDaoImpl();
		this.sc=sc;
	}
	
	
	public void addCriminal() {
		
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal ID: "+ConsoleColors.GREEN_BOLD);
		String criminalId = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal Name: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal DOB: "+ConsoleColors.GREEN_BOLD);
		LocalDate dob = LocalDate.parse(sc.next());
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal Gender: "+ConsoleColors.GREEN_BOLD);
		String gender=sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal identification Mark: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String identifyingMark=sc.nextLine();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter First Arrest Date: "+ConsoleColors.GREEN_BOLD);
		LocalDate firstArrestDate=LocalDate.parse(sc.next());
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Police Station: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String arrestedFromPsArea=sc.nextLine();
		
		Criminal criminal = new CriminalImpl(criminalId, name, dob, gender, identifyingMark, firstArrestDate, arrestedFromPsArea);
		
		try {
			criminalDao.addCriminal(criminal);
			System.out.println(ConsoleColors.ANSI_GREEN+"CRIMINAL ADDED 📤 TO DATABASE"+ConsoleColors.ANSI_RESET);
		} catch (FileNotFoundException | SomeThingWrongException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	public void updateCriminal() {
		
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal ID: "+ConsoleColors.GREEN_BOLD);
		String criminalId = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal Name: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal DOB: "+ConsoleColors.GREEN_BOLD);
		LocalDate dob = LocalDate.parse(sc.next());
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal Gender: "+ConsoleColors.GREEN_BOLD);
		String gender=sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal identification Mark: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String identifyingMark=sc.nextLine();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter First Arrest Date: "+ConsoleColors.GREEN_BOLD);
		LocalDate firstArrestDate=LocalDate.parse(sc.next());
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Police Station: "+ConsoleColors.GREEN_BOLD);
		sc.nextLine();
		String arrestedFromPsArea=sc.nextLine();
		
		Criminal criminal = new CriminalImpl(criminalId, name, dob, gender, identifyingMark, firstArrestDate, arrestedFromPsArea);
		
		try {
			criminalDao.updateCriminal(criminal);
			System.out.println(ConsoleColors.ANSI_GREEN+"CRIMINAL INFORMATION UPDATED 📤 IN DATABASE"+ConsoleColors.ANSI_RESET);
		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}


	public void assignCriminalToCrime() {
		
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal ID: "+ConsoleColors.GREEN_BOLD);
		String criminalId = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Id To which you want to add above Criminal: "+ConsoleColors.GREEN_BOLD);
		String crimeId = sc.next();
		
		try {
			try {
				criminalDao.assignCriminalToCrime(crimeId, criminalId);
			} catch (CanNotMap e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			System.out.println(ConsoleColors.ANSI_GREEN+"CRIMINAL MAPPED TO CRIME 📤 IN DATABASE"+ConsoleColors.ANSI_RESET);
		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}


	public void removeCriminalFromCrime() {
		
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal ID: "+ConsoleColors.GREEN_BOLD);
		String criminalId = sc.next();
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Crime Id From which you want to remove above Criminal: "+ConsoleColors.GREEN_BOLD);
		String crimeId = sc.next();
		
		try {
			criminalDao.removeCriminalFromCrime(crimeId, criminalId);
			System.out.println(ConsoleColors.ANSI_GREEN+"CRIMINAL REMOVED FROM CRIME 📤 IN DATABASE"+ConsoleColors.ANSI_RESET);
		} catch (SomeThingWrongException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}


	public void deleteCriminalById() {
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal ID: "+ConsoleColors.GREEN_BOLD);
		String criminalId = sc.next();
		
		try {
			criminalDao.deleteCriminal(criminalId);
			System.out.println(ConsoleColors.GREEN_BOLD+"CRIMINAL DELETED "+ConsoleColors.ANSI_RED +"❌"+ConsoleColors.GREEN_BOLD+" FROM DATABASE"+ConsoleColors.ANSI_RESET);

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void searchCriminal() {
		System.out.print(ConsoleColors.YELLOW_BOLD+"Enter Criminal Name: "+ConsoleColors.GREEN_BOLD);
		String name = sc.next();
		
		try {
			List<Criminal> list=criminalDao.getCriminalByName(name);
			DisplayData.printInfo1(list);
			
		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	


	
}

package com.cims.ui;

import java.util.Scanner;

import com.cims.exception.NoRecordFoundException;
import com.cims.exception.SomeThingWrongException;

public class MenuDisplay {
	
	public static void welcomeAdmin() {
		try {
			Thread.sleep(500);
			System.out.println(ConsoleColors.GREEN_BOLD+ "»»—————————————————————————— WELCOME ADMIN 👤——————————————————————————««" +ConsoleColors.RESET);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}
	
	public static void adminMenu(Scanner sc) throws SomeThingWrongException, NoRecordFoundException, InterruptedException {
		int choice = 0;
		
		do {
			System.out.println();
			System.out.println(ConsoleColors.GREEN_BOLD +"-----------Menu-----------" + ConsoleColors.RESET);
			System.out.println();
			System.out.println(ConsoleColors.YELLOW_BOLD+"⛭ 1.Add Crime");
			Thread.sleep(200);
			System.out.println("⛭ 2.Update Crime Deatails");
			Thread.sleep(200);
			System.out.println("⛭ 3.Add Criminal");
			Thread.sleep(200);
			System.out.println("⛭ 4.Update Criminal");
			Thread.sleep(200);
			System.out.println("⛭ 5.Assign Criminal To Crime");
			Thread.sleep(200);
			System.out.println("⛭ 6.Remove Criminal From Crime");
			Thread.sleep(200);
			System.out.println("⛭ 7.Delete Crime By Id");
			Thread.sleep(200);
			System.out.println("⛭ 8.Delete Criminal By Id");
			Thread.sleep(200);
			System.out.println("⛭ Press 0 to Logout");
			Thread.sleep(200);
			System.out.println();
			System.out.println(ConsoleColors.YELLOW_BOLD +"** Select an Option **");
			
			System.out.print("> "+ConsoleColors.GREEN_BOLD);
			
			choice=sc.nextInt();
    		System.out.println(ConsoleColors.RESET);
			
    		CrimeUi crimeUi =new CrimeUi(sc);
    		CriminalUi criminalUi = new CriminalUi(sc); 
    		
			switch(choice) {
			case 1:
				crimeUi.addCrime();
				break;
				
			case 2:
				crimeUi.updateCrime();
				break;
				
			case 3:
				criminalUi.addCriminal();
				break;
			case 4:
				criminalUi.updateCriminal();
				break;
			case 5:
				criminalUi.assignCriminalToCrime();
				break;
			case 6:
				criminalUi.removeCriminalFromCrime();
				break;
			case 7:
				crimeUi.deleteCrimeById();
				break;
			case 8:
				criminalUi.deleteCriminalById();
				break;
			case 0:
				System.out.println(ConsoleColors.GREEN+"Thank you see you again!!!");
				break;
			default:
				System.err.println("Please Enter valid Input");
			}
			
		}while(choice != 0);
		
	}
	
	public static void welcomePublic() {
		try {
			Thread.sleep(500);
			System.out.println(ConsoleColors.GREEN_BOLD+ "»»—————————————————————————— WELCOME TO OUR ARCHIVE ——————————————————————————««" +ConsoleColors.RESET);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	public static void publicMenu(Scanner sc) throws SomeThingWrongException, NoRecordFoundException, InterruptedException {
		int choice = 0;
		
		do {
			System.out.println();
			System.out.println(ConsoleColors.GREEN_BOLD +"-----------Menu-----------" + ConsoleColors.RESET);
			System.out.println();
			System.out.println(ConsoleColors.YELLOW_BOLD+"⛭ 1.View Crime For Entered Area And Date Range");
			Thread.sleep(200);
			System.out.println("⛭ 2.View Crime For Entered Type And Date Range");
			Thread.sleep(200);
			System.out.println("⛭ 3.Search Criminal By Name");
			Thread.sleep(200);
			System.out.println("⛭ 4.Search Crime By Description");
			Thread.sleep(200);
			System.out.println();
			System.out.println(ConsoleColors.YELLOW_BOLD +"** Select an Option **");
			
			System.out.print("> "+ConsoleColors.GREEN_BOLD);
			
			choice=sc.nextInt();
    		System.out.println(ConsoleColors.RESET);
			
    		CrimeUi crimeUi =new CrimeUi(sc);
    		CriminalUi criminalUi = new CriminalUi(sc); 
    		
			switch(choice) {
			case 1:
				crimeUi.viewCrime();
				break;
				
//			case 2:
//				crimeUi.updateCrime();
//				break;
//				
			case 3:
				criminalUi.searchCriminal();
				break;
//			case 4:
//				criminalUi.updateCriminal();
//				break;
//			case 5:
//				criminalUi.assignCriminalToCrime();
//				break;
//			case 6:
//				criminalUi.removeCriminalFromCrime();
//				break;
//			case 7:
//				crimeUi.deleteCrimeById();
//				break;
//			case 8:
//				criminalUi.deleteCriminalById();
//				break;
			case 0:
				System.out.println(ConsoleColors.GREEN+"Thank you see you again!!!");
				break;
			default:
				System.err.println("Please Enter valid Input");
			}
			
		}while(choice != 0);
		
	}
	
	
	
}

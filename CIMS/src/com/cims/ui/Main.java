package com.cims.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cims.exception.NoRecordFoundException;
import com.cims.exception.SomeThingWrongException;

public class Main {
	
	static void adminLogin(Scanner sc) {
		System.out.println();
		System.out.print( ConsoleColors.ANSI_YELLOW+" Enter username "+ConsoleColors.ANSI_GREEN);
		String username = sc.next();
		System.out.print(ConsoleColors.ANSI_YELLOW+" Enter password "+ConsoleColors.ANSI_GREEN);
		String password = sc.next();
		System.out.println();
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			MenuDisplay.welcomeAdmin();
			try {
				MenuDisplay.adminMenu(sc);
			} catch (SomeThingWrongException | NoRecordFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				
			}
			
		}else {
			System.out.println(" Invalid Username and Password");
		}
	}
	
	static void publicPortal(Scanner sc) {
		MenuDisplay.welcomePublic();
		try {
			MenuDisplay.publicMenu(sc);
		} catch (SomeThingWrongException | NoRecordFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		do {
			
				System.out.println(ConsoleColors.ANSI_GREEN+"*** 🕵️ WELCOME TO ARCHIVE-IT ✍️(CRIME INFORMATION MANAGEMENT SYSTEM) ***"+ConsoleColors.ANSI_RESET);
				System.out.println();
				System.out.println(ConsoleColors.ANSI_YELLOW +" PRESS 1 FOR 👤 Admin Login ");
				System.out.println(" PRESS 2 FOR ⛭ Public Services");
				System.out.println(" PRESS 0 TO ⏏️ Exit");
				System.out.println();
				
			try {
			System.out.print(" SELECT AN OPTION: "+ConsoleColors.ANSI_GREEN);
				choice = sc.nextInt();
			}catch(InputMismatchException ex) {
				System.out.println(" Enter Valid Input");
				Main.main(null);
			}
			switch(choice) {
			case 1:
				adminLogin(sc);
				break;
			case 2:
				publicPortal(sc);
				break;
			case 0:
				System.out.println(ConsoleColors.GREEN+" Thank you for using our Services See you Again!!!"+ConsoleColors.RESET);
				System.exit(0);
				break;
				
			default:
				System.err.println(" Wrong Input!!!!");
			}
			
		}while(choice!=0);
		
		sc.close();
	}
	}



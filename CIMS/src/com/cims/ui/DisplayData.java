package com.cims.ui;

import java.util.List;

import com.cims.dto.Crime;
import com.cims.dto.Criminal;

public class DisplayData {
	

	
public static void printInfo(List<Crime> list) {
		System.out.println();
		System.out.printf("----------------------------------------------------------------------------------------------------%n");
		System.out.printf("                                           CRIME LIST                                               %n");
		System.out.printf("                                                                                                    %n");

		System.out.printf("----------------------------------------------------------------------------------------------------%n");
		System.out.printf("| %-10s | %-8s | %-10s | %-12s | %-12s | %-12s  |%n", "CRIME ID", "TYPE", "DATE", "CRIME STATUS", "CRIMINAL NAME","DESCRIPTION");
		System.out.printf("----------------------------------------------------------------------------------------------------%n");

		
		for(int i=0;i<list.size();i++) {
			System.out.printf("| %-10s | %-8s | %-10s | %-12s     | %-12s  | %-12s   |%n", list.get(i).getCrimeId(), list.get(i).getCrimeType(),  list.get(i).getDate().toString(),
					(list.get(i).getStatus().equalsIgnoreCase("solved")? ConsoleColors.ANSI_GREEN +list.get(i).getStatus():ConsoleColors.ANSI_RED +list.get(i).getStatus()+ConsoleColors.ANSI_GREEN), list.get(i).getCriminal().getName(),
					list.get(i).getDescription());
			
		}
		

		System.out.printf("-----------------------------------------------------------------------------------------------------%n");
	}

	public static void printInfo1(List<Criminal> list) {
		System.out.println();
		System.out.printf("------------------------------------------------------------------------------------------------------%n");
		System.out.printf("                                           CRIMINAL LIST                                              %n");
		System.out.printf("                                                                                                      %n");

		System.out.printf("------------------------------------------------------------------------------------------------------%n");
		System.out.printf("| %-10s | %-8s | %-10s | %-12s | %-12s | %-12s |%n", "CRIMINAL ID", "NAME", "DOB", "GENDER", "ARREST DATE","IDENTIFICATION MARK");
		System.out.printf("------------------------------------------------------------------------------------------------------%n");

		
		for(int i=0;i<list.size();i++) {
			System.out.printf("| %-10s | %-8s | %-10s | %-12s  | %-12s | %-12s |%n", list.get(i).getCriminalId(), list.get(i).getName(),  list.get(i).getDob().toString(),
					list.get(i).getGender(),list.get(i).getFirstArrestDate(),list.get(i).getIdentifyingMark());
			
	
	}
		System.out.printf("-------------------------------------------------------------------------------------------------------%n");
}
	
	public static void printCrimeData(List<Crime> list) {
		System.out.println(ConsoleColors.ANSI_GREEN);
		System.out.printf("---------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("                                           CRIME LIST                                                                %n");
		System.out.printf("                                                                                                                     %n");

		System.out.printf("--------------------------------------------------------------------------------------------------------=============%n");
		System.out.printf("| %-10s | %-8s | %-10s | %-12s | %-12s | %-12s  | %-12s  |%n", "CRIME ID", "TYPE", "DATE","AREA", "CRIME STATUS", "VICTIM NAME", "DESCRIPTION");
		System.out.printf("--------------------------------------------------------------------------------------------------------=============%n");

		
		for(int i=0;i<list.size();i++) {
			System.out.printf("| %-10s | %-8s | %-10s | %-12s | %-12s  | %-12s  | %-12s  |%n", list.get(i).getCrimeId(), list.get(i).getCrimeType(),  list.get(i).getDate().toString(), list.get(i).getPsArea(),
					(list.get(i).getStatus().equalsIgnoreCase("solved")? ConsoleColors.ANSI_GREEN +list.get(i).getStatus():ConsoleColors.ANSI_RED +list.get(i).getStatus()+ConsoleColors.ANSI_GREEN), list.get(i).getVictimName(),list.get(i).getDescription());}
		
		System.out.printf("---------------------------------------------------------------------------------------------------------============%n");
}

}

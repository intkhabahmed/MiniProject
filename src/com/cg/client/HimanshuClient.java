package com.cg.client;


import java.util.Scanner;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;


public class HimanshuClient {

	public static void main(String[] args) throws AirlineException{
		Scanner sc = new Scanner(System.in);
		AirlineServiceImpl service = new AirlineServiceImpl();
		
		/*System.out.println("Enter the flight number for which you want change the schedule");
		String flightNumber = sc.next();
		System.out.println("What do you want to change");
		System.out.println(" 1. Arrival Date\n 2. Departure Date \n 3. Arrival Time \n 4. Departure Time");
		int choiceForUpdation = sc.nextInt();
		
		if(choiceForUpdation==1){
			System.out.println("Enter the New Arrival Date in YYYY-MM-DD format");
			String newInput = sc.next();
			System.out.println(service.updateFlightSchedule(flightNumber, newInput, choiceForUpdation));
		}
		else if(choiceForUpdation==2){
			System.out.println("Enter the New Departure Date in YYYY-MM-DD format");
			String newInput = sc.next();
			System.out.println(service.updateFlightSchedule(flightNumber, newInput, choiceForUpdation));
		}
		else if(choiceForUpdation==3){
			System.out.println("Enter the New Arrival Time in HH:MM format");
			String newInput = sc.next();
			System.out.println(service.updateFlightSchedule(flightNumber, newInput, choiceForUpdation));
		}
		else if(choiceForUpdation==4){
			System.out.println("Enter the New Departure Time in HH:MM format");
			String newInput = sc.next();
			System.out.println(service.updateFlightSchedule(flightNumber, newInput, choiceForUpdation));
		}
		*/
		
		System.out.println(service.updateFlightInformation("9W", "10M"));
		sc.close();
	}

}

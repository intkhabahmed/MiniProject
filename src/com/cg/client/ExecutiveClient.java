package com.cg.client;

import java.util.Scanner;

import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;
import com.cg.service.IAirlineService;

public class ExecutiveClient {

	public void executivePortal() throws AirlineException{
		System.out.println("Hi Executive! Enter your Login Details");
		Scanner sc = new Scanner(System.in);
		String username="";
		String password="";
		String validationStatus="";
		int choice=0;
		int exitCondition=1;
		IAirlineService service = new AirlineServiceImpl();
		LoginMaster login = new LoginMaster();
		do{
			System.out.print("Enter Username: ");
			username = sc.next();
			login.setUsername(username);
			System.out.print("Enter Password: ");
			password = sc.next();
			login.setPassword(password);
			validationStatus = service.validLogin(login);
			login.setRole(validationStatus);
			if(validationStatus.equals("")){
				System.out.println("Invalid username/password");
				System.out.println("Want to continue? 1. Yes\n2. No");
				exitCondition = sc.nextInt();
				if(exitCondition==2){
					System.out.println("Thankyou! Have a nice day.");
					System.exit(0);
				}
			}else{
				break;
			}
		}while(validationStatus.equals("") || exitCondition==1);
		
		do{
			System.out.println("Hi "+username+", What do you want to do?");
			System.out.println("1.See Flight Occupancy Details\n2.Logout");
			choice = sc.nextInt();
			switch(choice){
			case 1:
				String flightNo="";
				int exitCond=1;
				do{
					System.out.println("Enter flight no to see occupancy details");
					flightNo=sc.next();
					if(service.viewListOfFlights(flightNo, "flightNo").isEmpty()){
						System.out.println("Sorry! wrong flight No, Try again.");
						System.out.println("Enter 1 to continue or \nEnter 2 to go to previous menu");
						exitCond = sc.nextInt();
						if(exitCond==2){
							break;
						}
					}else{
						int seats[] = service.flightOccupancyDetails(flightNo);
						System.out.println("No. of First Class seats: "+seats[0]);
						System.out.println("No. of Business Class seats: "+seats[1]);
					}
				}while(service.viewListOfFlights(flightNo, "flightNo").isEmpty());
				
				break;
			case 2:
				System.out.println("Thank you "+username+"! Have a nice day.");
				username="";
				System.exit(0);
				break;
			default:
				System.out.println("Enter Valid Choice");
			}
		}while(!username.isEmpty());
		sc.close();
	}
	
}

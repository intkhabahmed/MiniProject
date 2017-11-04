package com.cg.client;


import java.util.List;
import java.util.Scanner;

import com.cg.bean.Flight;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;


public class HimanshuClient {

	public static void main(String[] args) throws AirlineException{
		Scanner sc = new Scanner(System.in);
		AirlineServiceImpl service = new AirlineServiceImpl();
		
		int flag=0;
		System.out.println("Hello Admin");
		
		
		while(flag==0){
			System.out.println("Enter the flight number for which you want change the schedule");
			String flightNo = sc.next();
			List<Flight> flightList = service.viewListOfFlights(flightNo, "flightNo");
			
			if(flightList.size()!=0){
				flag=1;
				System.out.println("Details of Flight No " + flightList.get(0).getFlightNo() + " are as follows:");
				System.out.println("Current arrival date: "+ flightList.get(0).getArrDate());
				System.out.println("Current departure date: " + flightList.get(0).getDeptDate());
				System.out.println("Current arrival time: " + flightList.get(0).getArrTime());
				System.out.println("Current departure time: " + flightList.get(0).getDeptTime()+ "\n\n");
			
				System.out.println("What do you want to change");
				System.out.println(" 1. Arrival Date\n 2. Departure Date \n 3. Arrival Time \n 4. Departure Time");
				int choiceForUpdation = sc.nextInt();
				
				if(choiceForUpdation==1){
					System.out.println("Enter the New Arrival Date in YYYY-MM-DD format");
					boolean b = true;
					while(b==true){
						String newInput = sc.next();
						if(service.checkDateFormat(newInput)==1){
							System.out.println(service.updateFlightSchedule(flightNo, newInput, choiceForUpdation));
							b =false;
						}else{
							System.out.println("Please enter in YYYY-MM-DD format");
						}
					}
				}
				else if(choiceForUpdation==2){
					System.out.println("Enter the New Departure Date in YYYY-MM-DD format");
					boolean b = true;
					while(b==true){
						String newInput = sc.next();
						if(service.checkDateFormat(newInput)==1){
							System.out.println(service.updateFlightSchedule(flightNo, newInput, choiceForUpdation));
							b =false;
						}else{
							System.out.println("Please enter in YYYY-MM-DD format");
						}
					}
				}
				else if(choiceForUpdation==3){
					System.out.println("Enter the New Arrival Time in HH:MM format");
					
					
					boolean b = true;
					while(b==true){
						String newInput = sc.next();
						if(service.checkTimeFormat(newInput)==1){
							System.out.println(service.updateFlightSchedule(flightNo, newInput, choiceForUpdation));
							b=false;
						}
						else{
							System.out.println("Please enter in HH:MM format");
						}
					}
				}
				else if(choiceForUpdation==4){
					System.out.println("Enter the New Departure Time in HH:MM format");
					
					boolean b = true;
					while(b==true){
						String newInput = sc.next();
						if(service.checkTimeFormat(newInput)==1){
							System.out.println(service.updateFlightSchedule(flightNo, newInput, choiceForUpdation));
							b=false;
						}
						else{
							System.out.println("Please enter in HH:MM format");
						}
					}
				}
			}
			else{
				System.out.println("Flight with flight number "+flightNo+" does not exist");
				System.out.println("1. To continue");
				System.out.println("2. To exit");
				int choice = sc.nextInt();
				if(choice==1)
					flag=0;
				else{
					flag=1;
					System.out.println("Thank You");
				}
			}
			
		}
		
		
		
	/*	 Function to change the Flight Information Starts
		int flag = 0;
		
		System.out.println("Hello Admin");
		
		while(flag==0){
			System.out.println("Enter the flight number for which you want change the information");
			String flightNo = sc.next();
			
			List<Flight> flightList = service.viewListOfFlights(flightNo, "flightNo");
		
			if(flightList.size()!=0){
				flag = 1;
				System.out.println("Details of Flight No " + flightList.get(0).getFlightNo() + "are as follows:");
				System.out.println("Current arrival city: "+ flightList.get(0).getArrCity());
				System.out.println("Current departure city: " + flightList.get(0).getDeptCity());
				System.out.println("Current first class fares: " + flightList.get(0).getFirstSeatsFare());
				System.out.println("Current business class fares: " + flightList.get(0).getBussSeatsFare()+ "\n\n");
				

				System.out.println("What do you want to change");
				System.out.println(" 1. Arrival City\n 2. Departure City \n 3. First Class Seats Fare Time \n 4. Business Class Seats Fare");
				int choiceForChange = sc.nextInt();
				
				if(choiceForChange==1){
					System.out.println("Enter the new Arrival City");
					String newInput=sc.next();
					System.out.println(service.updateFlightInformation(flightNo,newInput,1 ));
				}
				else if(choiceForChange==2){
					System.out.println("Enter the new Departure City");
					String newInput=sc.next();
					System.out.println(service.updateFlightInformation(flightNo,newInput,2 ));
				}
				else if(choiceForChange==3){
					System.out.println("Enter the new fares for First Class");
					String newInput=sc.next();
					System.out.println(service.updateFlightInformation(flightNo,newInput,2 ));
				}
				else if(choiceForChange==4){
					System.out.println("Enter the new fares for Business Class");
					String newInput=sc.next();
					System.out.println(service.updateFlightInformation(flightNo,newInput,2 ));
				}
				
			}else{
				System.out.println("Flight with flight number "+flightNo+" does not exist");
				System.out.println("1. To continue");
				System.out.println("2. To exit");
				int choice = sc.nextInt();
				if(choice==1)
					flag=0;
				else{
					flag=1;
					System.out.println("Thank You");
				}
					
			}
		}*/
		
		sc.close();
	}

}

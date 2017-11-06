package com.cg.client;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;
import com.cg.service.IAirlineService;

public class AdminClient {

	public void adminPortal() throws AirlineException{
		System.out.println("Hi Admin! Enter your Login Details");
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

			}else if(validationStatus.equals("admin")){
				break;
			}
			else{
				validationStatus = "";
				System.out.println("Sorry! You are not admin, try again");
			}

		}while(validationStatus.equals("") || exitCondition==1);
		
		do{
			System.out.println("Hi "+username+", What do you want to do?");
			System.out.println("1. Update and Manage Flight Schedules\n2. Update and Manage Flight Information\n3. View Flights Information"
					+ "\n4. View Bookings of Flight\n5. View Passengers of a Flight\n6. Logout");
			choice = sc.nextInt();
			switch(choice){
			case 1:
				int flag=0;
				while(flag==0){
					System.out.println("Enter the flight number for which you want change the schedule");
					String flightNo = sc.next();
					
					int opt;
				do{
					List<Flight> flightList = service.viewListOfFlights(flightNo, "flightNo");
					if(flightList.size()!=0){
						flag=1;
						
							System.out.println("Details of Flight No " + flightList.get(0).getFlightNo() + " are as follows:");
							System.out.println("Current arrival date: "+ flightList.get(0).getArrDate());
							System.out.println("Current departure date: " + flightList.get(0).getDeptDate());
							System.out.println("Current arrival time: " + flightList.get(0).getArrTime());
							System.out.println("Current departure time: " + flightList.get(0).getDeptTime()+ "\n\n");
						
							System.out.println("What do you want to change");
							System.out.println(" 1. Arrival Date\n 2. Departure Date \n 3. Arrival Time \n 4. Departure Time\n 5. Go to previous menu");
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
							}else if(choiceForUpdation==5){
								flag=1;
								break;
							}
							
						
					}
					else{
						System.out.println("Flight with flight number "+flightNo+" does not exist");
						System.out.println("1. To continue");
						System.out.println("2. For Main Menu");
						int option = sc.nextInt();
						if(option==1)
							flag=0;
						else{
							flag=1;
							System.out.println("Thank You");
						}
					}
				System.out.println("Do you want to change anything else?\nEnter 1 if Yes and 2 to go to Main Menu");
					opt = sc.nextInt();
					if(opt==1){
						flag=0;
						System.out.print("Updated ");
					}
				}while(opt==1);
					
				}
				break;
				
			case 2:
				flag=0;
				while(flag==0){
					System.out.println("Enter the flight number for which you want change the information");
					String flightNo = sc.next();
				int opt;	
				do{
					List<Flight> flightList = service.viewListOfFlights(flightNo, "flightNo");
				
					if(flightList.size()!=0){
						flag = 1;
						System.out.println("Details of Flight No " + flightList.get(0).getFlightNo() + "are as follows:");
						System.out.println("Current arrival city: "+ flightList.get(0).getArrCity());
						System.out.println("Current departure city: " + flightList.get(0).getDeptCity());
						System.out.println("Current first class fares: " + flightList.get(0).getFirstSeatsFare());
						System.out.println("Current business class fares: " + flightList.get(0).getBussSeatsFare()+ "\n\n");
						

						System.out.println("What do you want to change");
						System.out.println(" 1. Arrival City\n 2. Departure City \n 3. First Class Seats Fare Time \n "
								+ "4. Business Class Seats Fare\n 5. Go to previous menu");
						int choiceForChange = sc.nextInt();
						String newInput="";
						
						if(choiceForChange==1){
							do{
								System.out.println("Enter the new Arrival City");
								newInput=sc.next();
								newInput = service.getCityAbbreviation(newInput);
								if(newInput!=""){
									System.out.println(service.updateFlightInformation(flightNo,newInput,1 ));
								}else{
									System.out.println("Entered city does not exist in database");
								}
							}while(newInput.equals(""));
							
						}
						else if(choiceForChange==2){
							do{
								System.out.println("Enter the new Departure City");
								newInput=sc.next();
								newInput = service.getCityAbbreviation(newInput);
								if(newInput!=""){
									System.out.println(service.updateFlightInformation(flightNo,newInput,2 ));
								}else{
									System.out.println("Entered city does not exist in database");
								}
							}while(newInput.equals(""));
						}
						else if(choiceForChange==3){
							do{
								System.out.println("Enter the new fares for First Class");
								newInput=sc.next();
								if(!newInput.matches("^[1-9][0-9]{1,8}.[0-9]{0,}$")){
									System.out.println("Fare shoud be more than zero and max 10 digits");
								}else{
									System.out.println(service.updateFlightInformation(flightNo,newInput,3 ));
								}
								
							}while(!newInput.matches("^[1-9][0-9]{1,8}.[0-9]{0,}$"));
						}
						else if(choiceForChange==4){
							do{
								System.out.println("Enter the new fares for Business Class");
								newInput=sc.next();
								if(!newInput.matches("^[1-9][0-9]{1,8}.[0-9]{0,}$")){
									System.out.println("Fare shoud be more than zero and max 10 digits");
								}else{
									System.out.println(service.updateFlightInformation(flightNo,newInput,4 ));
								}
								
							}while(!newInput.matches("^[1-9][0-9]{1,8}.[0-9]{0,}$"));
						}else if(choiceForChange==5){
							flag=1;
							break;
						}
						
					}else{
						System.out.println("Flight with flight number "+flightNo+" does not exist");
						System.out.println("1. To continue");
						System.out.println("2. For Main Menu");
						int option = sc.nextInt();
						if(option==1)
							flag=0;
						else{
							flag=1;
							System.out.println("Thank You");
						}
							
					}
					System.out.println("Do you want to change anything else?\nEnter 1 if Yes and 2 to go to Main Menu");
					opt = sc.nextInt();
					if(opt==1){
						flag=0;
						System.out.print("Updated ");
					}
				}while(opt==1);
				}
				break;
				
			case 3:
				flag=0;
				List<Flight> flights=null;
				while(flag==0){
					flag=1;
					System.out.println("Select the basis for viewing flights\n1. For a particular day\n2. To a particular destination\n"
							+ "3. Go to previous menu");
					int option = sc.nextInt();
					if(option==1){
						String day;
						int opt;
						do{
							System.out.println("Enter the date in YYYY-MM-DD format to see flights");
							day = sc.next();
							if(service.checkDateFormat(day)==2){
								System.out.println("Wrong Date Format, Try again");
							}else{
								flights = service.viewListOfFlights(day, "day");
								if(flights.isEmpty()){
									System.out.println("Sorry! There are no flights on the given date: "+day);
								}else{
									System.out.println(flights);
								}
							
							}
							System.out.println("Enter 1 to go to main menu or\nEnter 2 to go to previous menu\nEnter 3 to Continue Searching");
							opt = sc.nextInt();
							if(opt==2){
								flag=0;
								break;
							}
						}while(opt==3);
						
					}else if(option==2){
						String dest;
						int opt;
						do{
							System.out.println("Enter the destination city for which you want to see flights");
							dest = sc.next();
							dest = service.getCityAbbreviation(dest);
							if(dest!=""){
								flights = service.viewListOfFlights(dest, "dest");
								System.out.println(flights);
							}else{
								System.out.println("Entered city does not exists in our database, Try again");
								
							}
							System.out.println("Enter 1 to go to main menu or\nEnter 2 to go to previous menu\nEnter 3 to Continue Searching");
							opt = sc.nextInt();
							if(opt==2){
								flag=0;
								break;
							}
						}while(opt==3);
					}else if(option==3){
						break;
					}else{
						System.out.println("Invalid Option, try again");
						flag=0;
					}
				}
				break;
			case 4:
				String flightNo="";
				int exitCond=1;
				do{
					System.out.println("Enter flight no to see booking details");
					flightNo=sc.next();
					if(service.viewListOfFlights(flightNo, "flightNo").isEmpty()){
						System.out.println("Sorry! wrong flight No, Try again.");
						System.out.println("Enter 1 to continue or \nEnter 2 to go to previous menu");
						exitCond = sc.nextInt();
						if(exitCond==2){
							break;
						}
					}else{
						List<BookingInfo> bookings = service.viewBookings(flightNo, "byFlight");
						System.out.println("Following are the bookings done for flightNo - "+flightNo);
						System.out.println(bookings);
					}
				}while(service.viewListOfFlights(flightNo, "flightNo").isEmpty());
				
				break;
				
			case 5:
				flightNo="";
				exitCond=1;
				do{
					System.out.println("Enter flight no to see passenger details");
					flightNo=sc.next();
					if(service.viewListOfFlights(flightNo, "flightNo").isEmpty()){
						System.out.println("Sorry! wrong flight No, Try again.");
						System.out.println("Enter 1 to continue searching or \nEnter 2 to go to previous menu");
						exitCond = sc.nextInt();
						if(exitCond==2){
							break;
						}
					}else{
						List<BookingInfo> bookings = service.viewPassengersOfFlight(flightNo);
						System.out.println("Following are the Passengers in the flight with flightNo - "+flightNo);
						Iterator<BookingInfo> itr = bookings.iterator();
						System.out.format("%20s%20s", "Booking ID","Customer_Email\n");
						while(itr.hasNext()){
							BookingInfo booking = (BookingInfo)itr.next();
							System.out.format("%20s%20s",booking.getBookingId(),booking.getCustEmail()+"\n");
						}
					}
				}while(service.viewListOfFlights(flightNo, "flightNo").isEmpty());
				break;
				
			case 6:
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

package com.cg.client;




import java.util.List;
import java.util.Scanner;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;


public class SanjayClient {

	public static void main(String[] args) throws AirlineException {

		Scanner sc =new Scanner(System.in);

		int choice = 0;
		String username = "";
		String password = "";
		String flightNo = "";
		String bookingId = "";
		LoginMaster login = new LoginMaster();
		AirlineServiceImpl service = new AirlineServiceImpl();

		int y=1;
		while(y==1)
		{
			
			if(choice==0){
				System.out.println("Hi user! What do you want to do today");
				System.out.println("1.LogIn\n2.SignUp\n3.Flight Search");
				choice = sc.nextInt();
			}
			int z=1;
			LoopHandler: do{
				switch(choice){
				
				case 7:

					if(!username.isEmpty())
					{
						int x=1;
						while(x == 1)
						{
							System.out.println("Hi "+username+"! What do you want to do today");
							System.out.println("1.Flight Search\n2.View Booking\n3.Booking Cancel\n4.Logout");
							int option = sc.nextInt();
							if(option == 4)
							{
								System.out.println("You are successfully logged out");
								username="";
								x=0;
								z=0;
								choice=0;
								flightNo="";
								
							}
							else if(option == 1){

								//choice = 4;
								choice = 3;
								break LoopHandler;
							}
							else if(option == 3)
							{
								int status=0;
								do{
									System.out.println("Please give Booking id :");
									bookingId = sc.next();
									status = service.bookingCancel(bookingId,username);
									if(status==1){
										System.out.println("Your Flight Booking is Successfully cancelled");
									}else{
										System.out.println("Sorry! Booking Id is invalid, Try again");
									}
								}while(status==0);
							}
							else if(option == 2)
							{
								List<BookingInfo> bookings = service.viewBookings(username,"byUser");
								if(bookings.isEmpty()){
									System.out.println("Sorry! No Flight Booking done by you.");
								}else{
									System.out.println(bookings);
								}
							}
							else{
								System.out.println("Provide Valid Input");
							}
						}
					}
					break;
					
				case 4:
					if(!flightNo.isEmpty())
					{
					System.out.println("Please login or Signup");
					System.out.println("1.Login\n2.Signup");
					int option = sc.nextInt();
					if(option==1){
						//choice = 7;//Login
						choice = 1;
						continue;
					}
					else if(option == 2){
						choice = 2;//signup
						continue;
					}
					}
					else{
						
						System.out.println("Flight number is invalid");
					}
					break;
					
				case 3://flight search case
					int flag;
					do{
						flag=0;
						System.out.print("Enter Source City Name: ");
						String source = sc.next();
						System.out.print("Enter Destination City Name: ");
						String destination = sc.next();
						
						source = service.getCityAbbreviation(source);
						destination = service.getCityAbbreviation(destination);
						if(source.equals("") || destination.equals("")){
							flag=1;
							System.out.println("Sorry! No Flights between the given cities, Try Again");
						}
						else{
							List<Flight> flights = service.viewListOfFlights(source+"-"+destination, "route");
							System.out.println(flights);
							if(flights.isEmpty()){
								System.out.println("Sorry! No flights available for this route, Try Again");
								flag=1;
							}
							else{
								do{
									System.out.println("Enter Flight Number For booking :");
									flightNo = sc.next();
									if(service.viewListOfFlights(flightNo, "flightNo").isEmpty()){
										System.out.println("Sorry! wrong flight No, Try again.");
									}
								}while(service.viewListOfFlights(flightNo, "flightNo").isEmpty());
							}
						}
						
					}while(flag==1);
					
					if(!username.isEmpty()){
						choice = 5;
					}else{
						//choice = 3;
						choice = 4;
					}
					continue;
					
				case 5://booking case
					if(!flightNo.isEmpty())
					{
						String classType;
						int[] seatsAvailable=null;
						do{
							System.out.println("Enter Class Type:first or Bussiness");
							classType = sc.next();
							if(!classType.equalsIgnoreCase("first") && !classType.equalsIgnoreCase("business")){
								System.out.println("Please enter a valid class type");
							}else{
								seatsAvailable =service.flightOccupancyDetails(flightNo);
								System.out.println("First Seats: "+seatsAvailable[0]+"\nBusiness Seats: "+seatsAvailable[1]);
							}
						}while(!classType.equalsIgnoreCase("first") && !classType.equalsIgnoreCase("business"));
						
						int no_of_passengers;
						do{
							flag=0;
							System.out.println("No of passengers");
							no_of_passengers = sc.nextInt();
							if((classType.equalsIgnoreCase("first") && seatsAvailable[0] >= no_of_passengers)
									|| (classType.equalsIgnoreCase("business") && seatsAvailable[1] >= no_of_passengers)){
								String creditCard="";
								do{
									System.out.println("Credit card Number");
									creditCard = sc.next();
									if(!creditCard.matches("[0-9]{10}")){
										System.out.println("Credit card should have only 10 digits, Try again!");
									}else{
										if(service.bookingConfirm(username, flightNo, no_of_passengers, classType, creditCard)==1){
										System.out.println("Booking Confirmed! for Flight No: "+flightNo);
										
										//choice = 1;
										choice = 7;
										break LoopHandler;
										}
									}
								}while(!creditCard.matches("[0-9]{10}"));
								
							}
							else
								System.out.println("Not enough seat available, try again");
						}while(flag==0);	
					}
					break LoopHandler;
					
				case 2://signup case
					
					System.out.println("Hello new User! Please provide Some information to create your account");
					
					do{
						sc.nextLine();
						System.out.print("Enter Username: ");
						username = sc.nextLine();
						login.setUsername(username);
						if(!username.matches("^[a-zA-Z][a-zA-Z0-9._]{1,25}$"))
						{
							System.out.println("Error: Username can have only characters,digits, '.(dot)' and '_'");
						}
						else if(service.checkAvailability(username, "username") == 1){
							System.out.println("Error: Username Already Exist !! ");
						}
					}while(!username.matches("^[a-zA-Z][a-zA-Z0-9._]{1,25}$") ||(service.checkAvailability(username, "username") ==1) );
					
					long mobile;
						do{
							System.out.print("Enter Mobile No.: ");
							mobile = sc.nextLong();
							if(!(""+mobile).matches("[0-9]{10}")){
								System.out.println("Error: Mobile No. should be 10 digits long");
							}
						}while(!(""+mobile).matches("[0-9]{10}"));
						login.setMobile(mobile);
					
					String email;
					do{
						System.out.print("Enter Email :");
						email = sc.next();
						if(!email.matches("[a-zA-Z_.0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]{2,}?.[a-zA-Z0-9]{2,}")){
							System.out.println("Error: Wrong Email Format");
						}else if(service.checkAvailability(email, "email") ==1)
						{
							System.out.println("Error: Email already exists");
						}
					}while(service.checkAvailability(email, "email") ==1 || !email.matches("[a-zA-Z_.0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]{2,}?.[a-zA-Z0-9]{2,}"));
					login.setEmail(email);
					
			        sc.nextLine();
					do{
						System.out.print("Password :");
						password = sc.nextLine();
						if(!password.matches("[a-zA-Z0-9@$#!%&.]{8,20}")){
							System.out.println("Type a strong password and should be of atleast 8 characters and max 20");
						}
					}while(!password.matches("[a-zA-Z0-9@$#!%&.]{8,20}"));
					login.setPassword(password);
					
					login.setRole("customer");
					if(service.signUp(login) == 1){
					System.out.println("SignUp Successful");
					username="";
						if(!flightNo.isEmpty()){
							System.out.println("Please Enter Login Details:");
							//choice = 7;
							choice = 1;
						}else{
							choice = 8;
						}
						
						continue;
					}
					else
					System.out.println("Some error occured during signup!");
					
					break;
					
				case 1://Login case
					do{
						System.out.print("Enter Username :");
						username = sc.next();
						login.setUsername(username);
						sc.nextLine();
						System.out.print("Enter Password :");
						password = sc.nextLine();
						login.setPassword(password);
						login.setRole("customer");
						if(!service.validLogin(login).equalsIgnoreCase("customer"))
						{
							System.out.println("Invalid Username/Password, Try Again");
						}else{
							if(!flightNo.isEmpty()){
								choice = 5;
								continue;
							}else{
								//choice = 1;
								choice = 7;
								continue;
							}
						}
					}while(!service.validLogin(login).equalsIgnoreCase("customer"));	
					break;
				case 8:
					System.out.println("Hi user! What do you want to do today");
					System.out.println("1.LogIn\n2.Flight Search");
					int opt = sc.nextInt();
					if(opt==1){
						//choice = 7;
						choice = 1;
						
					}else if(opt==2){
						//choice = 4;
						choice = 3;
						
					}
					else{
						System.out.println("Invalid option");
						break;
					}
					continue;
				default:
					System.out.println("Please provide valid choice");
					break;

				}
			}while(z==1);
		}
		sc.close();


	}

}

package com.cg.client;

import java.util.List;
import java.util.Scanner;

import com.cg.bean.BookingInfo;
import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;

public class Client_doneBySanjay {
	public static void main(String[] args) throws AirlineException {

		Scanner dc =new Scanner(System.in);
		int choice = 0;
		String username = null;
		String password = null;
		String flightNo = null;
		String bookingId = null;
		String role = null;
		LoginMaster login = new LoginMaster();
		AirlineServiceImpl service = new AirlineServiceImpl();
		String user = null;
		int y=1;
		while(y==1)
		{
			System.out.println("Hi user! What do you want to do today");
			System.out.println("1.LogIn\n2.SignUp\n3.Flight Search\n4.Exit");
			choice = dc.nextInt();

			switch(choice){
			case 3:
				flightNo = service.caseFlightSearch();
				if(!flightNo.isEmpty())
				{
				System.out.println("Please login or Signup");
				System.out.println("1.Login\n2.Signup");
				choice = dc.nextInt();
				if(choice==1){
					username = service.caseLogin();
					String route[] = username.split("-");
					username = route[0];
					role = route[1];
				}
				else if(choice == 2){
					service.caseSignUp();
					System.out.println("Please login");
					username = service.caseLogin();
				}
				if(!username.isEmpty() && role.equalsIgnoreCase("Customer"))
				{
					System.out.println("No of passengers");
					int no_of_passengers = dc.nextInt();
					System.out.println("Enter Class type: first or bussiness");
					String classType = dc.next();

					if(service.flightOccupancyDetails(classType, flightNo) >= no_of_passengers){
						System.out.println("Enter Credit Card Number: ");
						String creditCard = dc.next();
						if(service.bookingConfirm(username, flightNo, no_of_passengers, classType, creditCard)==1)
						System.out.println("Booking Confirmed! for Flight No-"+flightNo);
					}
					else
						System.out.println("No seat available");
				}
				}
				else
					System.out.println("Flight number is invalid Or You are not a valid user for this");
				break;
			case 1:
				username = service.caseLogin();
			//	System.out.println(username);
				String route[] = username.split("-");
				username = route[0];
			//	System.out.println(username);
				role = route[1];
				if(role.equalsIgnoreCase("Customer")){
				if(!username.isEmpty())
				{
					int x=1;
					while(x == 1)
					{
						System.out.println("Hi "+username+"! What do you want to do today");
						System.out.println("1.Flight Search\n2.View Booking\n3.Booking Cancel\n4.Logout");
						choice = dc.nextInt();
						if(choice == 4)
						{
							username="";
							role="";
							System.out.println("Have a nice Day");
							x=0;
						}
						else if(choice == 1){

							flightNo = service.caseFlightSearch();
							if(!flightNo.isEmpty())
							{
								System.out.println("No of passengers");
								int no_of_passengers = dc.nextInt();
								System.out.println("Enter Class Type:first or Bussiness");
								String classType = dc.next();
								int pass =service.flightOccupancyDetails(classType, flightNo);
								//System.out.println(pass);
								if(pass >= no_of_passengers){
									String creditCard="";
									do{
										System.out.println("Credit card Number");
										creditCard = dc.next();
										if(!creditCard.matches("[0-9]{10}")){
											System.out.println("Credit card should have only 10 digits, Try again!");
										}else{
											if(service.bookingConfirm(username, flightNo, no_of_passengers, classType, creditCard)==1)
											System.out.println("Booking Confirmed! for Flight No"+flightNo);
										}
									}while(!creditCard.matches("[0-9]{10}"));
									
								}
								else
									System.out.println("No seat available");
							}
							else
								System.out.println("Flight number is invalid!");
						}
						else if(choice == 3)
						{
							System.out.println("Please give Booking id :");
							bookingId = dc.next();
							if(service.bookingCancel(bookingId,username)==1){
								System.out.println("Booking Successfully cancel");
							}
						}
						else if(choice == 2)
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
				}
				else if(role.equalsIgnoreCase("Executive"))
				{
					int x=1;
					do{
					System.out.println("Hi "+username+" ! What Do you want today\n"
							+ "1. flight occupancy details\n"
							+ "2. Logout");
					choice = dc.nextInt();
					
					if(choice == 2)
					{
						username="";
						role="";
						System.out.println("Have a nice Day");
						x=0;
					}
					if(choice == 1){
					System.out.println("Enter class Type");
					String classType=dc.next();
					System.out.println("Enter flight no");
					flightNo=dc.next();
					
					service.flightOccupancyDetails(classType,flightNo);
					}
					}while(x==1);
				}
				else if(role.equalsIgnoreCase("admin")){
					System.out.println("Hi "+username+" ! have a good day!");
				}
				else
				{
					System.out.println("Wrong Credentials!");
				}



				break;
			case 2:
				service.caseSignUp();
				break;
				
			case 4:
				System.out.println("Have a nice day!");
				y=0;
				break;
			default:
				System.out.println("Please provide valid input");

			}
		}
		dc.close();


	}


}
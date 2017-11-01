package com.cg.client;



import java.util.Scanner;

import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;


public class SanjayClient {

	public static void main(String[] args) throws AirlineException {
		
		Scanner dc =new Scanner(System.in);
		int choice = 0;
		String username = null;
		String password = null;
		String flightNo = null;
		String bookingId = null;
		LoginMaster login = new LoginMaster();
		AirlineServiceImpl service = new AirlineServiceImpl();
		String user = null;
		System.out.println("Hi user! What do you want to do today");
		System.out.println("1.LogIn\n2.SignUp\n3.Flight Search");
		choice = dc.nextInt();
			
			switch(choice){
			case 3:
			flightNo = service.caseFlightSearch();
				
				System.out.println("Please login or Signup");
				System.out.println("1.Login\n2.Signup");
				choice = dc.nextInt();
				if(choice==1){
					username = service.caseLogin();	
				}
				else if(choice == 2){
					service.caseSignUp();
					System.out.println("Please login");
					username = service.caseLogin();
				}
				if(!username.isEmpty() && !flightNo.isEmpty())
				{
					System.out.println("Booking Confirmed! for Flight No"+flightNo);
				}
				break;
			case 1:
				username = service.caseLogin();

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
							x=0;
						}
						else if(choice == 1){

							flightNo = service.caseFlightSearch();
							if(!flightNo.isEmpty())
							System.out.println("Booking Confirmed!");
						}
						else if(choice == 3)
						{
							System.out.println("Please give Booking id :");
							bookingId = dc.next();
							if(service.bookingCancel(bookingId,username)==1){
								System.out.println("Booking Successfully cancel");
							}
						}
						else{
							System.out.println("Provide Valid Input");
						}
					}
				}



				break;
			case 2:
				service.caseSignUp();
				break;
			default:
				System.out.println("Please provide valid input");

			}
			dc.close();

	
	}

}

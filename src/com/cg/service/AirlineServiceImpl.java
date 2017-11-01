package com.cg.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.dao.AirlineDAOImpl;
import com.cg.exception.AirlineException;

public class AirlineServiceImpl implements IAirlineService{

	AirlineDAOImpl dao = new AirlineDAOImpl();
	LoginMaster login = new LoginMaster();

	public List<Flight> viewListOfFlights() throws AirlineException{
		return dao.viewListOfFlights();
	}
	
	public List<BookingInfo> viewBookingsOfFlight(String flightNo) throws AirlineException{
		return dao.viewBookingsOfFlight(null);
	}
	
	public int signUp(LoginMaster login) throws AirlineException{
		return dao.signUp(login);
	}
	
	public int validLogin(LoginMaster login) throws AirlineException{
		return dao.validLogin(login);
	}
	
	public int mobileIsAvail(long mobile) throws AirlineException{
		return dao.mobileIsAvail(mobile);
	}
	public int emailIsAvail(String email) throws AirlineException{
		return dao.emailIsAvail(email);
	}
	
	public int usernameIsAvail(String username) throws AirlineException{
		return dao.usernameIsAvail(username);
	}
	public int flightIsAvail(String source,String destination,String flightNo) throws AirlineException{
		return dao.flightIsAvail(source,destination,flightNo);
	}
	
	@Override
	public String updateFlightSchedule(String flightNo, String newInput, int choice) throws AirlineException{
		String status = dao.updateFlightSchedule(flightNo, newInput, choice);
		return status;
	}
	
	@Override
	public String updateFlightInformation(String oldFlightNo, String newFlightNo) throws AirlineException{
		String status = dao.updateFlightInformation(oldFlightNo, newFlightNo);
		return status;
	}
	@Override
	public List<Flight>retrieveFlightList(String source, String destination) throws AirlineException{
		return dao.retrieveFlightList(source,destination);
	}
	
	
	@Override
	public String caseLogin() throws AirlineException{
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Username :");
		String username = sc.next();
		login.setUsername(username);
		sc.nextLine();
		System.out.print("Password :");
		String password = sc.nextLine();
		login.setPassword(password);
		login.setRole("customer");
		if(validLogin(login) !=0)
		{
			return login.getUsername();
		}
		return "";
	}
	
	@Override
	public void caseSignUp() throws AirlineException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello new User! Please provide Some information to create your account");
		
		String user;
		do{
			System.out.print("Username :");
			user = sc.nextLine();
			login.setUsername(user);
			if(user.contains(" "))
			{
				System.out.println("Username will not contain any Spaces");
			}
			else if(usernameIsAvail(user) == 1){
				System.out.println("Username Already Exist !! ");
			}
		}while(user.contains(" ") ||(usernameIsAvail(user) ==1) );
		
		long mobile = 0;
		do{
			System.out.print("mobile :");
			mobile = sc.nextLong();
			if(mobileIsAvail(mobile) ==1)
			{
				System.out.println("Mobile Number is already exist");
			}
		}while(mobileIsAvail(mobile) ==1);
		login.setMobile(mobile);
		
		String email = "";
		do{
			System.out.print("email :");
			email = sc.next();
			if(emailIsAvail(email) ==1)
			{
				System.out.println("email is already exist");
			}
		}while(emailIsAvail(email) ==1);
		login.setEmail(email);
		
        sc.nextLine();
		System.out.print("Password :");
		login.setPassword(sc.nextLine());
		
		login.setRole("customer");
		if(signUp(login) == 1){
		System.out.println("SignUp Success");
		}
		else
		System.out.println("Some error occured!");
	}
	
	@Override 
	public String caseFlightSearch() throws AirlineException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Source :");
		String source = null;
			 source  = sc.next();
		System.out.print("Destination :");
		String destination = null;
			destination = sc.next();
		
		ArrayList<Flight> list = (ArrayList<Flight>) retrieveFlightList(source, destination);
		Iterator<Flight> flightIt=list.iterator();
		while(flightIt.hasNext())
		{
			Flight flight = flightIt.next();
			System.out.println(flight);
		}
		if(list.isEmpty()){
			System.out.println("Sorry! No flight avail for this route");
		}
		else{
		System.out.println("Flight Number For booking :");
		String flightNo = sc.next();
		if(flightIsAvail(source, destination, flightNo) == 1){
		return flightNo;
		}
		else{
			return "";
		}
		}
		return "";
		
	}
	@Override
	public int bookingCancel(String bookingId, String username) throws AirlineException {
		return dao.bookingCancel(bookingId,username);
		
	}
	
	
}

package com.cg.service;

import java.util.List;
import java.util.Scanner;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.dao.AirlineDAOImpl;
import com.cg.dao.IAirlineDAO;
import com.cg.exception.AirlineException;

public class AirlineServiceImpl implements IAirlineService{

	IAirlineDAO dao;
	LoginMaster login;
	
	public AirlineServiceImpl() {
		dao = new AirlineDAOImpl();
		login = new LoginMaster();
	}
	
	@Override
	public String getCityAbbreviation(String cityName) throws AirlineException {
		return dao.getCityAbbreviation(cityName);
	}


	@Override
	public List<Flight> viewListOfFlights(String query, String searchBasis) throws AirlineException{
		List<Flight> flights = dao.viewListOfFlights(query,searchBasis);
		if(searchBasis.equals("dest") && flights.size()==0){
			throw new AirlineException("No Flights found for the given destination: "+query);
		}else if(searchBasis.equals("day") && flights.size()==0){
			throw new AirlineException("No Flights found for the date: "+query);
		}
		return dao.viewListOfFlights(query,searchBasis);
	}
	
	@Override
	public List<BookingInfo> viewBookings(String query, String searchBasis) throws AirlineException{
		return dao.viewBookings(query, searchBasis);
	}
	
	@Override
	public List<BookingInfo> viewPassengersOfFlight(String flightNo)
			throws AirlineException {
		return dao.viewPassengersOfFlight(flightNo);
	}
	
	@Override
	public int signUp(LoginMaster login) throws AirlineException{
		return dao.signUp(login);
	}
	
	@Override
	public int validLogin(LoginMaster login) throws AirlineException{
		return dao.validLogin(login);
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
	public String updateFlightInformation(String oldFlightNo, String newFlightNo, int choice) throws AirlineException{
		String status = dao.updateFlightInformation(oldFlightNo, newFlightNo, choice);
		return status;
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
				System.out.print("Enter Username: ");
				user = sc.nextLine();
				login.setUsername(user);
				if(!user.matches("^[a-zA-Z][a-zA-Z0-9._]{3,25}$"))
				{
					System.out.println("Error: Username can have only characters,digits, '.(dot)' and '_'");
				}
				else if(checkAvailability(user, "username") == 1){
					System.out.println("Error: Username Already Exist !! ");
				}
			}while(!user.matches("[a-zA-Z0-9._]{3,25}") ||(checkAvailability(user, "username") ==1) );
			
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
				if(!email.matches("[a-zA-Z_.0-9]{2,}@[a-zA-Z0-9]{2,}.[a-zA-Z0-9]{2,}?.[a-zA-Z0-9]{2,}")){
					System.out.println("Error: Wrong Email Format");
				}else if(checkAvailability(email, "email") ==1)
				{
					System.out.println("Error: Email already exists");
				}
			}while(checkAvailability(email, "email") ==1 || !email.matches("[a-zA-Z_.0-9]{2,}@[a-zA-Z0-9]{2,}.[a-zA-Z0-9]{2,}?.[a-zA-Z0-9]{2,}"));
			login.setEmail(email);
			
	        sc.nextLine();
	        String password;
			do{
				System.out.print("Password :");
				password = sc.nextLine();
				if(!password.matches("[a-zA-Z0-9@$#!%&.]{8,20}")){
					System.out.println("Type a strong password and should be of atleast 8 characters and max 20");
				}
			}while(!password.matches("[a-zA-Z0-9@$#!%&.]{8,20}"));
			login.setPassword(password);
			
			login.setRole("customer");
			if(signUp(login) == 1){
			System.out.println("SignUp Success");
			
			}
			else
			System.out.println("Some error occured during signup!");
		
	}
	
	@Override 
	public String caseFlightSearch() throws AirlineException{
		Scanner sc = new Scanner(System.in);
			int flag;
			do{
				flag=0;
				System.out.print("Enter Source City Name: ");
				String source = sc.next();
				System.out.print("Enter Destination City Name: ");
				String destination = sc.next();
				
				source = dao.getCityAbbreviation(source);
				destination = dao.getCityAbbreviation(destination);
				if(source.equals("") || destination.equals("")){
					flag=1;
					System.out.println("Sorry! No Flights between the given cities, Try Again");
				}
				else{
					List<Flight> flights = dao.viewListOfFlights(source+"-"+destination, "route");
					System.out.println(flights);
					if(flights.isEmpty()){
						System.out.println("Sorry! No flight avail for this route, Try Again");
						flag=1;
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
				}
				return "";
				
			}while(flag==1);
		
	}
	@Override
	public int bookingCancel(String bookingId, String username) throws AirlineException {
		return dao.bookingCancel(bookingId,username);
		
	}
	
	@Override
	public int flightOccupancyDetails(String classType, String flightNo) throws AirlineException {
		int[] seats = dao.flightOccupancyDetails(classType,flightNo);
		if(classType.equalsIgnoreCase("First")){
			return (seats[0]-seats[2]);
		}else if(classType.equalsIgnoreCase("Business")){
			return (seats[1]-seats[3]);
		}else{
			return -1;
		}
	}

	@Override
	public int bookingConfirm(String username,String flightno, int noOfPassengers, String classType,String creditCard) throws AirlineException{
	return dao.bookingConfirm(username, flightno, noOfPassengers, classType, creditCard);
	}

	@Override
	public int checkAvailability(String query, String type)
			throws AirlineException {
		return dao.checkAvailability(query, type);
	}
	
	
}

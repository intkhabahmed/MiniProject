package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.User;
import com.cg.dao.AirlineDAOImpl;
import com.cg.dao.IAirlineDAO;
import com.cg.exception.AirlineException;

public class AirlineServiceImpl implements IAirlineService{

	IAirlineDAO dao;
	User login;
	
	public AirlineServiceImpl() {
		dao = new AirlineDAOImpl();
		login = new User();
	}
	
	@Override
	public String getCityAbbreviation(String cityName) throws AirlineException {
		return dao.getCityAbbreviation(cityName);
	}


	@Override
	public List<Flight> viewListOfFlights(String query, String searchBasis) throws AirlineException{
		List<Flight> flights = dao.viewListOfFlights(query,searchBasis);
		return flights;
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
	public int signUp(User login) throws AirlineException{
		return dao.signUp(login);
	}
	
	@Override
	public String validLogin(User login) throws AirlineException{
		return dao.validLogin(login);
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
	public int bookingCancel(String bookingId, String username) throws AirlineException {
		return dao.bookingCancel(bookingId,username);
		
	}
	
	@Override
	public int[] flightOccupancyDetails(String flightNo) throws AirlineException {
		int[] seats = dao.flightOccupancyDetails(flightNo);
		
		return new int[]{(seats[0]-seats[2]), (seats[1]-seats[3])};
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
	
	@Override
	public int checkTimeFormat(String newInput){
		String validTime = "[0-9]{2}[:]{1}[0-9]{2}";
		
		if(Pattern.matches(validTime, newInput)){
			return 1;
		}else{
			return 2;
		}
	}
	
	
	@Override
	public int checkDateFormat(String newInput){
		String validDate = "[20]{2}[0-9]{2}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}";
		
		if(Pattern.matches(validDate, newInput)){
			return 1;
		}else{
			return 2;
		}
	}
}

package com.cg.service;

import java.util.List;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;

public interface IAirlineService {
	
	public String getCityAbbreviation(String cityName) throws AirlineException;
	public List<Flight> viewListOfFlights(String query, String searchBasis) throws AirlineException;
	public List<BookingInfo> viewBookingsOfFlight(String flightNo) throws AirlineException;
	public List<BookingInfo> viewPassengersOfFlight(String flightNo) throws AirlineException;
	public int signUp(LoginMaster login) throws AirlineException;
	public int validLogin(LoginMaster login) throws AirlineException;
	public String updateFlightSchedule(String flightNo, String newInput, int choice) throws AirlineException;
	public String caseLogin() throws AirlineException;
	public void caseSignUp() throws AirlineException;
	public List<Flight> retrieveFlightList(String source, String destination) throws AirlineException;
	public String caseFlightSearch() throws AirlineException;
	public int bookingCancel(String bookingId, String username) throws AirlineException;
	public String updateFlightInformation(String oldFlightNo, String newFlightNo, int choice) throws AirlineException;
	public int flightOccupancyDetails(String classType,String flightNo) throws AirlineException;
	int bookingConfirm(String username,String flightno, int noOfPassengers, String classType,
			String creditCard) throws AirlineException;
	void viewBookingsOfFlightGivenUser(String username) throws AirlineException;
	public int checkAvailability(String query, String type) throws AirlineException;
}

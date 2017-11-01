package com.cg.service;

import java.util.List;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;

public interface IAirlineService {
	public List<Flight> viewListOfFlights() throws AirlineException;
	public List<BookingInfo> viewBookingsOfFlight(String flightNo) throws AirlineException;
	public int signUp(LoginMaster login) throws AirlineException;
	public int validLogin(LoginMaster login) throws AirlineException;
	public int mobileIsAvail(long mobile) throws AirlineException;
	public int usernameIsAvail(String username) throws AirlineException;

	public String updateFlightSchedule(String flightNo, String newInput, int choice) throws AirlineException;

	public String updateFlightInformation(String oldFlightNo, String newFlightNo, int choice) throws AirlineException;
}

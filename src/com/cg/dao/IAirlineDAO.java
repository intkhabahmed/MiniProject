package com.cg.dao;

import java.sql.Date;
import java.util.List;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.exception.AirlineException;

public interface IAirlineDAO {
	public List<Flight> viewListOfFlights() throws AirlineException;
	public List<BookingInfo> viewBookingsOfFlight(String flightNo) throws AirlineException;
	public String updateFlightSchedule(String flightNo, String dateInput, int choice) throws AirlineException;
}

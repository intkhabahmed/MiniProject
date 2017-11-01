package com.cg.service;

import java.util.List;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.dao.AirlineDAOImpl;
import com.cg.exception.AirlineException;

public class AirlineServiceImpl implements IAirlineService{

	AirlineDAOImpl dao = new AirlineDAOImpl();

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
	
	public int usernameIsAvail(String username) throws AirlineException{
		return dao.usernameIsAvail(username);
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
	public void flightOccupancyDetails(String classType, String flightNo) throws AirlineException {
		// TODO Auto-generated method stub
		dao.flightOccupancyDetails(classType,flightNo);
	}
	
}

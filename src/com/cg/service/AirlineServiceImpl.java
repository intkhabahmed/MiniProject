package com.cg.service;

import java.util.List;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.dao.AirlineDAOImpl;
import com.cg.exception.AirlineException;

public class AirlineServiceImpl {

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

}

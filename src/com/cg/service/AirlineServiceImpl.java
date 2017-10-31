package com.cg.service;

import com.cg.dao.AirlineDAOImpl;
import com.cg.exception.AirlineException;

public class AirlineServiceImpl implements IAirlineService{
	
	AirlineDAOImpl dao = new AirlineDAOImpl();
	public AirlineServiceImpl() {
		
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
}

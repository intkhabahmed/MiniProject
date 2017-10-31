package com.cg.service;

import com.cg.exception.AirlineException;

public interface IAirlineService {

	public String updateFlightSchedule(String flightNo, String newInput, int choice) throws AirlineException;

	public String updateFlightInformation(String oldFlightNo, String newFlightNo) throws AirlineException;
}

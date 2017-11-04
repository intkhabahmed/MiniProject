package com.cg.client;

import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;
import com.cg.service.IAirlineService;

public class IntuClient {

	public static void main(String[] args) {
		IAirlineService service = new AirlineServiceImpl();
		
		try {
			System.out.println(service.viewListOfFlights("SG-519","flightNo"));
			System.out.println("****************************");
			System.out.println(service.viewBookings("9W-617","byFlight"));
			System.out.println("****************************");
			System.out.println(service.viewPassengersOfFlight("9W-617"));
		} catch (AirlineException e) {
			System.out.println(e.getMessage());
		}

	}

}

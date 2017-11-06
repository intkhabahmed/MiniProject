package com.cg.dao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;

public class AirlineDAOImplTest {
	private LoginMaster login;
	private AirlineServiceImpl service;
	private Flight flight;
	private BookingInfo booking;
	

	@Before
	public void setUp() throws Exception {
		login = new LoginMaster();
		service = new AirlineServiceImpl();			
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void LoginTest() throws AirlineException {		
		login.setRole("Admin");
		login.setUsername("Admin");
		login.setPassword("admin");
		assertEquals(login.getRole(),service.validLogin(login));
	}
	
	@Test
	public void viewFlightScheduleTest() throws AirlineException
	{
	flight = new Flight("9W-617", "JET_AIRWAYS", "PNQ", "BOM",
			"2017-10-23", "2012-05-06", "17:35","18:20", 30, 4567.0,40, 5432.0);
	List<Flight> flightList = new ArrayList<Flight>();
	flightList.add(flight);
	assertEquals(flightList,service.viewListOfFlights("PNQ-BOM", "route"));
	}
	
	@Test
	public void ticketBookingTest() throws AirlineException
	{
		flight = new Flight("9W-617", "JET_AIRWAYS", "PNQ", "BOM",
				"2015-09-06", "2017-04-05", "07:30","10:30", 30, 4000.0,40, 10000.0);
		//booking = new BookingInfo("1001","prashu@gmail.com",5,"first",12000.0,"1234567890","PNQ","BOM","");
		int []seatsAvailable = {30,40-0};
		assertArrayEquals(seatsAvailable,service.flightOccupancyDetails("9W-617"));
		
	}
}	

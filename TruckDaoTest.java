package com.capgemini.truckbooking.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.capgemini.truckbooking.bean.BookingBean;
import com.capgemini.truckbooking.exception.BookingException;

public class TruckDaoTest {

	TruckDao truckDao=new TruckDao();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testInsertPositive() {
		
		
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date="2018-02-02";
		LocalDate dateOftransport=LocalDate.parse(date,formatter);
		BookingBean bookingBean=new BookingBean(1005,"A111111",7978450480L,3005,4,dateOftransport);
		
		
		try
		{
			assertEquals(1,truckDao.bookTrucks(bookingBean));
		}catch(BookingException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(expected=BookingException.class)
	public final void testInsertNegative() throws BookingException {
		
		
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date="2018-02-02";
		LocalDate dateOftransport=LocalDate.parse(date,formatter);
		BookingBean bookingBean=new BookingBean(1005,"A111111",7978450480L,3005,4,dateOftransport);
		
		
		
		
			assertEquals(1,truckDao.bookTrucks(bookingBean));
		
	}

}

package com.cg.client;

import java.util.Scanner;

import com.cg.exception.AirlineException;
import com.cg.service.AirlineServiceImpl;
import com.cg.service.IAirlineService;

public class prashansaClient {

	private static IAirlineService ias=new AirlineServiceImpl();
	/*public AirlineClient()
	{
		ias = ;
	}*/
	public static void main(String args[]) throws AirlineException
	{
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter class Type");
		String classType=s.next();
		System.out.println("Enter flight no");
		String flightNo=s.next();
		
		ias.flightOccupancyDetails(flightNo);
	}

}

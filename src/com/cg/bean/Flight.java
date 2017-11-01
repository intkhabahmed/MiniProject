package com.cg.bean;

public class Flight {
	private String flightNo;
	private String flightName;
	private String arrCity;
	private String deptCity;
	private String arrDate;
	private String deptDate;
	private String arrTime;
	private String deptTime;
	private int firstSeats;
	private double firstSeatsFare;
	private int bussSeats;
	private double bussSeatsFare;
	
	public Flight(String flightNo, String flightName, String arrCity,
			String deptCity, String arrDate, String deptDate, String arrTime,
			String deptTime, int firstSeats, double firstSeatsFare,
			int bussSeats, double bussSeatsFare) {
		super();
		this.flightNo = flightNo;
		this.flightName = flightName;
		this.arrCity = arrCity;
		this.deptCity = deptCity;
		this.arrDate = arrDate;
		this.deptDate = deptDate;
		this.arrTime = arrTime;
		this.deptTime = deptTime;
		this.firstSeats = firstSeats;
		this.firstSeatsFare = firstSeatsFare;
		this.bussSeats = bussSeats;
		this.bussSeatsFare = bussSeatsFare;
	}
	
	public Flight(){
		System.out.println("Empty constructor is called");
	}

	public String getFlightNo() {
		return flightNo;
	}

	public String getFlightName() {
		return flightName;
	}

	public String getArrCity() {
		return arrCity;
	}

	public String getDeptCity() {
		return deptCity;
	}

	public String getArrDate() {
		return arrDate;
	}

	public String getDeptDate() {
		return deptDate;
	}

	public String getArrTime() {
		return arrTime;
	}

	public String getDeptTime() {
		return deptTime;
	}

	public int getFirstSeats() {
		return firstSeats;
	}

	public double getFirstSeatsFare() {
		return firstSeatsFare;
	}

	public int getBussSeats() {
		return bussSeats;
	}

	public double getBussSeatsFare() {
		return bussSeatsFare;
	}

	@Override
	public String toString() {
		return "Flight \n[flightNo=" + flightNo + ", flightName=" + flightName
				+ ", arrCity=" + arrCity + ", deptCity=" + deptCity
				+ ", arrDate=" + arrDate + ", deptDate=" + deptDate
				+ ", arrTime=" + arrTime + ", deptTime=" + deptTime
				+ ", firstSeats=" + firstSeats + ", firstSeatsFare="
				+ firstSeatsFare + ", bussSeats=" + bussSeats
				+ ", bussSeatsFare=" + bussSeatsFare + "]";
	}
	
	
	
}

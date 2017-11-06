package com.cg.bean;

public class Flight {
	private String flightNo;
	private String flightName;
	private String deptCity;
	private String arrCity;
	private String deptDate;
	private String arrDate;
	private String deptTime;
	private String arrTime;
	private int firstSeats;
	private double firstSeatsFare;
	private int bussSeats;
	private double bussSeatsFare;
	
	public Flight(String flightNo, String flightName, String deptCity, String arrCity,
			String deptDate, String arrDate, String deptTime,
			String arrTime, int firstSeats, double firstSeatsFare,
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
	
	public void formattedString(){
		System.out.format("%10s%10s%10s%10s%30s%30s%10s%10s%15s%20s%10s%15s",flightNo,flightName,deptCity,arrCity,arrDate,deptDate
				,arrTime,deptTime,firstSeats,firstSeatsFare,bussSeats,bussSeatsFare+"\n");
	}

	@Override
	public String toString() {
		return "Flight [flightNo=" + flightNo + ", flightName=" + flightName
				+ ", deptCity=" + deptCity + ", arrCity=" + arrCity
				+ ", deptDate=" + deptDate + ", arrDate=" + arrDate
				+ ", deptTime=" + deptTime + ", arrTime=" + arrTime
				+ ", firstSeats=" + firstSeats + ", firstSeatsFare="
				+ firstSeatsFare + ", bussSeats=" + bussSeats
				+ ", bussSeatsFare=" + bussSeatsFare + "]";
	}
	
	
}

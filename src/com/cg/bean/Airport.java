package com.cg.bean;

public class Airport {
	private String airpotName;
	private String abbreviation;
	private String location;
	public Airport(String airpotName, String abbreviation, String location) {
		super();
		this.airpotName = airpotName;
		this.abbreviation = abbreviation;
		this.location = location;
	}
	
	public Airport(){
		System.out.println("Empty constructor is called");
	}

	public String getAirpotName() {
		return airpotName;
	}

	public void setAirpotName(String airpotName) {
		this.airpotName = airpotName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}

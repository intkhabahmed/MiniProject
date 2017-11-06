package com.cg.bean;

public class BookingInfo {
	
	private String bookingId;
	private String custEmail;
	private int noOfPassengers;
	private String classType;
	private double totalFare;
	private String creditcardInfo;
	private String rcCity;
	private String destCity;
	private String flightNo;

	public BookingInfo() {
				
	}

	public BookingInfo(String bookingId, String custEmail, int noOfPassengers,
			String classType, double totalFare,
			String creditcardInfo, String rcCity, String destCity,String flightNo) {
		super();
		this.bookingId = bookingId;
		this.custEmail = custEmail;
		this.noOfPassengers = noOfPassengers;
		this.classType = classType;
		this.totalFare = totalFare;
		this.creditcardInfo = creditcardInfo;
		this.rcCity = rcCity;
		this.destCity = destCity;
		this.flightNo = flightNo;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	public String getCreditcardInfo() {
		return creditcardInfo;
	}

	public void setCreditcardInfo(String creditcardInfo) {
		this.creditcardInfo = creditcardInfo;
	}

	public String getRcCity() {
		return rcCity;
	}

	public void setRcCity(String rcCity) {
		this.rcCity = rcCity;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}
	

	
	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public void formattedString(){
		System.out.format("%15s%20s%15s%10s%15s%20s%20s%15s%15s",bookingId,custEmail,
				noOfPassengers,classType,totalFare,creditcardInfo,
				rcCity,destCity,flightNo+"\n");
	}

	@Override
	public String toString() {
		return "BookingInfo [bookingId=" + bookingId + ", custEmail="
				+ custEmail + ", noOfPassengers=" + noOfPassengers
				+ ", classType=" + classType + ", totalFare=" + totalFare
				+ ", creditcardInfo=" + creditcardInfo + ", rcCity=" + rcCity
				+ ", destCity=" + destCity + ", flightNo=" + flightNo + "]";
	}
	
	
}

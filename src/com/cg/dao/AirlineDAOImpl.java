package com.cg.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.utility.DBUtil;

public class AirlineDAOImpl implements IAirlineDAO {

	public AirlineDAOImpl() {
		
	}
	private Connection airlineConn = null;

	/*
	 * (non-Javadoc)
	 * @see com.cg.dao.IAirlineDAO#viewListOfFlights()
	 * Method for retrieving all flight details
	 */
	@Override
	public List<Flight> viewListOfFlights() throws AirlineException {
		List<Flight> flightList = new ArrayList<Flight>();
		ResultSet rs = null;
		Statement st = null;
		try{
			airlineConn = DBUtil.createConnection();
			String sql = "SELECT * FROM FlightInformation";
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Flight flights = new Flight(rs.getString(1),rs.getString(2)
						,rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getInt(9),rs.getDouble(10),rs.getInt(11),
						rs.getDouble(12));
				flightList.add(flights);
			}
		}catch(Exception e){
			throw new AirlineException("Cannot retrieve flight details",e);
		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Cannot close database connection",e);
			}
		}
		return flightList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.cg.dao.IAirlineDAO#viewBookingsOfFlight(java.lang.String)
	 * Method to see booking details of a particular flight
	 */
	@Override
	public List<BookingInfo> viewBookingsOfFlight(String flightNo) throws AirlineException {
		List<BookingInfo> bookingList = new ArrayList<BookingInfo>();
		ResultSet rs = null;
		Statement st = null;
		try{
			airlineConn = DBUtil.createConnection();
			String sql = "SELECT * FROM BookingInformation WHERE flightNo='"+flightNo+"'";
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				BookingInfo bookingInfo = new BookingInfo(rs.getString(1),rs.getString(2),rs.getInt(3),
						rs.getString(4),rs.getDouble(5),rs.getInt(6),rs.getString(7),
						rs.getString(8),rs.getString(9));
				bookingList.add(bookingInfo);
			}
		}catch(Exception e){
			throw new AirlineException("Cannot retrieve booking details for the given flightNo-"+flightNo,e);
		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Cannot close database connection",e);
			}
		}
		return bookingList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cg.dao.IAirlineDAO#viewPassengersOfFlight(java.lang.String)
	 * Method to retrieve passenger list of a particular flight
	 */
	@Override
	public List<BookingInfo> viewPassengersOfFlight(String flightNo)
			throws AirlineException {
		List<BookingInfo> passengerList = new ArrayList<BookingInfo>();
		ResultSet rs = null;
		Statement st = null;
		try{
			airlineConn = DBUtil.createConnection();
			String sql = "SELECT booking_id,cust_email FROM BookingInformation WHERE flightNo='"+flightNo+"'";
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				BookingInfo bookingInfo = new BookingInfo();
				bookingInfo.setBookingId(rs.getString(1));
				bookingInfo.setCustEmail(rs.getString(2));
				passengerList.add(bookingInfo);
			}
		}catch(Exception e){
			throw new AirlineException("Cannot retrieve booking details for the given flightNo-"+flightNo,e);
		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Cannot close database connection",e);
			}
		}
		return passengerList;
	}
	
	/* Method to update schedule of a particular flight*/
	@Override
	public String updateFlightSchedule(String flightNo, String newInput, int choice) throws AirlineException{
		Connection connFlight = null;
		int status = 0;
		Date newDate = null;
		
		if(choice==1 || choice==2){
			String[] str = newInput.split("-");
			int year = Integer.parseInt(str[0]);
			int month = Integer.parseInt(str[1]);
			int date = Integer.parseInt(str[2]);
			LocalDate takeDate = LocalDate.of(year, month, date);
			newDate = Date.valueOf(takeDate);
		}
		
		
		String sql1 = new String("Update Flightinformation Set Arr_Date =? where flightNo=?");
		String sql2 = new String("Update Flightinformation Set Dep_Date =? where flightNo=?");
		String sql3 = new String("Update Flightinformation Set Arr_time =? where flightNo=?");
		String sql4 = new String("Update Flightinformation Set Dep_time =? where flightNo=?");
		PreparedStatement pstFlight = null;
		try{
			connFlight = DBUtil.createConnection();
			if(choice==1){
				pstFlight = connFlight.prepareStatement(sql1);
				pstFlight.setDate(1, newDate);
				pstFlight.setString(2,flightNo);
			}
			else if(choice==2){
				pstFlight = connFlight.prepareStatement(sql2);
				pstFlight.setDate(1, newDate);
				pstFlight.setString(2,flightNo);
			}
			else if(choice==3){
				pstFlight = connFlight.prepareStatement(sql3);
				pstFlight.setString(1, newInput);
				pstFlight.setString(2,flightNo);
			}
			else if(choice==4){
				pstFlight = connFlight.prepareStatement(sql4);
				pstFlight.setString(1, newInput);
				pstFlight.setString(2,flightNo);
			}
			status = pstFlight.executeUpdate();
		}catch(Exception e){
			throw new AirlineException("Cannot update the table");
		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Cannot close database connection",e);
			}
		}
		
		if(status==0)
			return "Flight Updation Failed";
		else
			return "Schedule updated for the flight number " + flightNo;
	}
	
	public String updateFlightInformation(String oldFlightNo, String newFlightNo) throws AirlineException{
		Connection connFlight = null;
		int status = 0;
		
		String sql = new String("Update Flightinformation Set Flightno =? where flightNo=?");
		PreparedStatement pstFlight = null;
		
		try{
			connFlight = DBUtil.createConnection();
			pstFlight = connFlight.prepareStatement(sql);
			pstFlight.setString(1, oldFlightNo);
			pstFlight.setString(2, newFlightNo);
			status = pstFlight.executeUpdate();
		}catch(Exception e){
			throw new AirlineException("Cannot update the table");
		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Cannot close database connection",e);
			}
		}
		
		if(status==0)
			return "Flight Updation Failed";
		else
			return "Following changes made:\n Old Flight number: " + oldFlightNo + "\n New Flight Number: " + newFlightNo;
	}

	@Override
	public int usernameIsAvail(String username) throws AirlineException{
		int status = 0;
		Connection connBook = null;
		Statement pstBook = null;
		String sql=new String("Select count(username) from users where username='"+username+"'");

		try{
			connBook=DBUtil.createConnection();
			pstBook = connBook.createStatement();
			ResultSet rset  = pstBook.executeQuery(sql);
			if(rset.next())
			{
				status = rset.getInt(1);
			}
		}catch(SQLException se){
			throw new AirlineException("Record not inserted",se);
		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Problems in Closing Connection",se);
			}
		}
		return status;

	}
	@Override
	public int mobileIsAvail(long mobile) throws AirlineException{
		int status = 0;
		Connection connBook = null;
		Statement pstBook = null;
		String sql=new String("Select count(mobile_no) from users where mobile_no="+mobile);

		try{
			connBook=DBUtil.createConnection();
			pstBook = connBook.createStatement();
			ResultSet rset  = pstBook.executeQuery(sql);
			if(rset.next())
			{
				status = rset.getInt(1);
			}
		}catch(SQLException se){
			throw new AirlineException("Record not inserted",se);
		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Problems in Closing Connection",se);
			}
		}
		return status;

	}
	@Override
	public int validLogin(LoginMaster login) throws AirlineException{
		int status = 0;
		Connection connBook = null;
		Statement pstBook = null;
		String sql=new String("Select count(username) from users where username='"+login.getUsername()+"' AND password='"+login.getPassword()+"' AND role='"+login.getRole()+"'");

		try{
			connBook=DBUtil.createConnection();
			pstBook = connBook.createStatement();
			ResultSet rset  = pstBook.executeQuery(sql);
			if(rset.next())
			{
				status = rset.getInt(1);
			}
		}catch(SQLException se){
			throw new AirlineException("Record not inserted",se);
		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Problems in Closing Connection",se);
			}
		}
		return status;

	}
	
	@Override
	public int signUp(LoginMaster login) throws AirlineException{
		int status = 0;
		Connection connBook = null;
		PreparedStatement pstBook = null;

		String sql = new String("INSERT INTO users VALUES(booking_sequence.nextval,?,?,?,?)");

		try{
			connBook=DBUtil.createConnection();
			pstBook = connBook.prepareStatement(sql);
			pstBook.setString(1, login.getUsername());
			pstBook.setString(2, login.getPassword());
			pstBook.setString(3, login.getRole());
			pstBook.setLong(4, login.getMobile());
			status = pstBook.executeUpdate();
		}catch(SQLException se){
			throw new AirlineException("Record not inserted",se);
		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Problems in Closing Connection",se);
			}
		}
		return status;
	}
	
	
	//for getting flight occupancy details
	//public int []flightOccupancyDetails(String classType,String flightNo) throws AirlineException
	public void flightOccupancyDetails(String classType,String flightNo) throws AirlineException
	{
		int a[]=new int[4];
		ResultSet rs = null;
		Statement st = null;
		try
		{
			System.out.println("class type"+classType);
			System.out.println("flight no"+flightNo);
			int totalFirstSeats=0;
			int totalBussSeats=0;
			int bookedFirstSeats =0;
			int bookedBussSeats = 0;
			int nonOccupiedFirstSeats = 0;
			int nonOccupiedBussSeats = 0;
			
			airlineConn = DBUtil.createConnection();
			String sql1 = "select firstSeats from flightInformation where flightNo="+flightNo;
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql1);
			while(rs.next())
			{
				totalFirstSeats = rs.getInt(1);
			}
			
			String sql2 = "select bussSeats from flightInformation where flightNo="+flightNo;
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql2);
			while(rs.next())
			{
				totalBussSeats = rs.getInt(1);
			}
			
			String sql3 ="select sum(no_of_passengers) from Bookinginformation where class_type='first' and flightno="+flightNo+"group by class_type,flightno";
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql3);
			while(rs.next())
			{
					bookedFirstSeats= rs.getInt(1);
			}
			String sql4 ="select sum(no_of_passengers) from Bookinginformation where class_type='business' and flightno="+flightNo+"group by class_type,flightno";
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql4);
			
			while(rs.next())
			{
				bookedBussSeats=rs.getInt(1);
			}
			
			a[0] = totalFirstSeats;
			a[1] = totalBussSeats;
			a[2] = bookedFirstSeats;
			a[3] = bookedBussSeats;
			
			
			
			nonOccupiedFirstSeats = totalFirstSeats-bookedFirstSeats;
			nonOccupiedBussSeats = totalBussSeats-bookedBussSeats;
			System.out.println("totalFirstSeats"+totalFirstSeats);
			System.out.println("totalBussSeats "+totalBussSeats );
			System.out.println("bookedFirstSeats"+bookedFirstSeats);
			System.out.println("bookedBussSeats"+bookedBussSeats);
			System.out.println("Total number of non occupied first seats"+nonOccupiedFirstSeats);
			System.out.println("Total number of non occupied business seats"+nonOccupiedBussSeats);
		
		}
		catch(Exception e)
		{
			throw new AirlineException("Cannot get number of seats",e);
		}
		//return a;
		
	}


}

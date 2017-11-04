package com.cg.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
import com.cg.bean.LoginMaster;
import com.cg.exception.AirlineException;
import com.cg.utility.DBUtil;

public class AirlineDAOImpl implements IAirlineDAO {
	private static Logger logger = Logger.getLogger(com.cg.dao.AirlineDAOImpl.class);
	public AirlineDAOImpl() {
		
	}
	private Connection airlineConn = null;

	
	/*
	 * Method to get the city name for the given abbreviation
	 */
	@Override
	public String getCityAbbreviation(String cityName) throws AirlineException {
		ResultSet rs = null;
		Statement st = null;
		String abbr = "";
		try{
			airlineConn = DBUtil.createConnection();
			String sql = "SELECT abbreviation FROM Airport WHERE location=upper('"+cityName+"')";
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				abbr = rs.getString(1);
			}
			logger.info("Abbreviation was retrieved for the following City Name: " + cityName);
		}catch(Exception e){
			logger.error("Failed to retrieve abbreviation:" + e.getMessage());
			throw new AirlineException("Server Error: Cannot retrieve Abbreviation for given city");

		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Server Error: Cannot close database connection",e);
			}
		}
		return abbr;
	}

	/*
	 * (non-Javadoc)
	 * @see com.cg.dao.IAirlineDAO#viewListOfFlights()
	 * Method for retrieving all flight details
	 */
	@Override
	public List<Flight> viewListOfFlights(String query, String searchBasis) throws AirlineException {
		List<Flight> flightList = new ArrayList<Flight>();
		ResultSet rs = null;
		Statement st = null;
		String sql="";
		try{
			airlineConn = DBUtil.createConnection();
			if(searchBasis.equals("dest")){
				sql = "SELECT * FROM FlightInformation WHERE arr_city='"+query+"'";
			}else if(searchBasis.equals("day")){
				sql = "SELECT * FROM FlightInformation WHERE dep_date=to_date('"+query+"','yyyy-mm-dd')";
			}else if(searchBasis.equals("route")){
				String route[] = query.split("-");
				sql = "SELECT * FROM FlightInformation WHERE dep_city='"+route[0]+"' AND arr_city='"+route[1]+"'";
			}else if(searchBasis.equals("flightNo")){
				sql = "SELECT * FROM FlightInformation WHERE flightNo='"+query+"'";
			}
			
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
			logger.info("List of flights retrieved");
		}catch(Exception e){
			logger.error("Failed to retrieve list of flights:" + e.getMessage());
			throw new AirlineException("Server Error: Cannot retrieve flight details",e);

		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Server Error: Cannot close database connection",e);
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
	public List<BookingInfo> viewBookings(String query, String searchBasis) throws AirlineException {
		List<BookingInfo> bookingList = new ArrayList<BookingInfo>();
		ResultSet rs = null;
		Statement st = null;
		String sql="";
		try{
			airlineConn = DBUtil.createConnection();
			if(searchBasis.equals("byFlight")){
				sql = "SELECT * FROM BookingInformation WHERE flightNo='"+query+"'";
			}else if(searchBasis.equals("byUser")){
				sql = "SELECT * FROM BookingInformation WHERE cust_email=(SELECT cust_email FROM users WHERE username='"+query+"')";
			}
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				BookingInfo bookingInfo = new BookingInfo(rs.getString(1),rs.getString(2),rs.getInt(3),
						rs.getString(4),rs.getDouble(5),rs.getInt(6),rs.getString(7),
						rs.getString(8),rs.getString(9));
				bookingList.add(bookingInfo);
			}
			logger.info("Booking information retrieved");
		}catch(Exception e){
			logger.error("Failed to retrieve booking information:" + e.getMessage());
			throw new AirlineException("Server Error: Cannot retrieve booking details for the given query",e);

		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Server Error: Cannot close database connection",e);
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
			logger.info("List Passsengers of following was retrieved: " + flightNo);
		}catch(Exception e){

			logger.error("Failed to retrieve passengers list of: " + flightNo+ " with following error " + e.getMessage());
			throw new AirlineException("Server Error: Cannot retrieve booking details for the given flightNo-"+flightNo,e);

		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Server Error: Cannot close database connection",e);
			}
		}
		return passengerList;
	}
	
	/* Method to update schedule of a particular flight*/
	@Override
	public String updateFlightSchedule(String flightNo, String newInput, int choice) throws AirlineException{
		Connection connFlight = null;
		int status = 0;
		
		
		PreparedStatement pstFlight = null;
		try{
			connFlight = DBUtil.createConnection();
			if(choice==1){
				String sql = new String("Update Flightinformation Set arr_Date =? where flightNo=?");
				pstFlight = connFlight.prepareStatement(sql);
				Date d = Date.valueOf(newInput);
				pstFlight.setDate(1, d);

				pstFlight.setString(2,flightNo);
			}
			else if(choice==2){
				String sql = new String("Update Flightinformation Set Dep_Date =? where flightNo=?");
				pstFlight = connFlight.prepareStatement(sql);
				Date d = Date.valueOf(newInput);
				pstFlight.setDate(1, d);
				pstFlight.setString(2,flightNo);
			}
			else if(choice==3){
				String sql = new String("Update Flightinformation Set Arr_time =? where flightNo=?");
				pstFlight = connFlight.prepareStatement(sql);
				pstFlight.setString(1, newInput);
				pstFlight.setString(2,flightNo);
			}
			else if(choice==4){
				String sql = new String("Update Flightinformation Set Dep_time =? where flightNo=?");
				pstFlight = connFlight.prepareStatement(sql);
				pstFlight.setString(1, newInput);
				pstFlight.setString(2,flightNo);
			}
			status = pstFlight.executeUpdate();
			logger.info("Flight Schedule of following flight number updated: " + flightNo);
		}catch(Exception e){
			logger.error("Flight Schedule Updation falied with following error message: " + e.getMessage());
			throw new AirlineException("Server Error: Cannot update the table");

		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Server Error: Cannot close database connection",e);
			}
		}
		
		if(status==0)
			return "Flight Updation Failed";
		else
			return "Schedule Updated for the flight number " + flightNo;
	}
	
	@Override
	public String updateFlightInformation(String flightNo, String newInput, int choice) throws AirlineException{
		Connection connFlight = null;
		int status = 0;
		
		PreparedStatement pstFlight = null;
		
		try{
			connFlight = DBUtil.createConnection();
			if(choice==1){
				String sql = new String("Update Flightinformation Set arr_city =? where flightNo=?");
				pstFlight = connFlight.prepareStatement(sql);
				pstFlight.setString(1,newInput);
			}
			else if(choice==2){
				String sql = new String("Update Flightinformation Set dep_city =? where flightNo=?");
				pstFlight = connFlight.prepareStatement(sql);
				pstFlight.setString(1,newInput);
			}
			else if(choice==3){
				String sql = new String("Update Flightinformation Set firstseatfare =? where flightNo=?");
				pstFlight = connFlight.prepareStatement(sql);
				pstFlight.setDouble(1, Double.parseDouble(newInput) );
			}
			else if(choice==4){
				String sql = new String("Update Flightinformation Set BUSSSEATSFARE =? where flightNo=?");
				pstFlight = connFlight.prepareStatement(sql);
				pstFlight.setDouble(1, Double.parseDouble(newInput) );
			}
			
			pstFlight.setString(2, flightNo);
			status = pstFlight.executeUpdate();
			logger.info("Flight Information of following flight number updated: " + flightNo);
		}catch(Exception e){
			logger.error("Flight Schedule Updation falied with following error message: " + e.getMessage());
			throw new AirlineException("Server Error: Cannot update the table");

		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Server Error: Cannot close database connection",e);
			}
		}
		
		if(status==0)
			return "Flight Updation Failed";
		else
			return "Information Updated for the flight number " + flightNo;
	}
	
	@Override
	public String validLogin(LoginMaster login) throws AirlineException{
		String status = "";
		Connection connBook = null;
		Statement pstBook = null;
		String sql=new String("Select role from users where username='"+login.getUsername()+"' AND password='"+login.getPassword()+"'");

		try{
			connBook=DBUtil.createConnection();
			pstBook = connBook.createStatement();
			ResultSet rset  = pstBook.executeQuery(sql);
			if(rset.next())
			{
				status = rset.getString(1);
			}
			logger.info("Following user logged in: " + login.getUsername());
		}catch(SQLException se){
			logger.error("Login failed with user name: "+ login.getUsername()+ " " + se.getMessage());
			throw new AirlineException("Server Error: Could not retrieve login details",se);

		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Server Error: Problems in Closing Connection",se);
			}
		}
		return status;

	}
	
	@Override
	public int signUp(LoginMaster login) throws AirlineException{
		int status = 0;
		Connection connBook = null;
		PreparedStatement pstBook = null;

		String sql = new String("INSERT INTO users VALUES(booking_sequence.nextval,?,?,?,?,?)");

		try{
			connBook=DBUtil.createConnection();
			pstBook = connBook.prepareStatement(sql);
			pstBook.setString(1, login.getUsername());
			pstBook.setString(2, login.getEmail());
			pstBook.setString(3, login.getPassword());
			pstBook.setString(4, login.getRole());
			pstBook.setLong(5, login.getMobile());
			status = pstBook.executeUpdate();
			logger.info(login.getRole() + " registered with following username " + login.getUsername());
		}catch(SQLException se){
			logger.error("Signup failed for username: " +login.getUsername());
			throw new AirlineException("Server Error: Records could not be inserted",se);

		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Server Error: Problems in Closing Connection",se);
			}
		}
		return status;
	}

	@Override
	public int bookingCancel(String bookingId, String username) throws AirlineException {
		int status = 0;
		Connection connBook = null;
		PreparedStatement pstBook = null;

		String sql = new String("DELETE FROM BookingInformation WHERE Booking_id='"+bookingId+"' AND cust_email=(SELECT cust_email FROM users WHERE username='"+username+"')");

		try{
			connBook=DBUtil.createConnection();
			pstBook = connBook.prepareStatement(sql);
			status = pstBook.executeUpdate();
			logger.info("Booking done with following booking id:" + bookingId );
		}catch(SQLException se){
			logger.error("Booking failed");
			throw new AirlineException("Server Error: Problem in cancellation of Flight",se);

		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Server Error: Problems in Closing Connection",se);
			}
		}
		return status;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.cg.dao.IAirlineDAO#flightOccupancyDetails(java.lang.String, java.lang.String)
	 * for getting flight occupancy details
	 */
	public int[] flightOccupancyDetails(String flightNo) throws AirlineException
	{
		int seats[]=new int[4];
		ResultSet rs = null;
		Statement st = null;
		String sql="";
		try
		{
			
			airlineConn = DBUtil.createConnection();
			sql = "select firstSeats from flightInformation where flightNo='"+flightNo+"'";
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next())
			{
				seats[0] = rs.getInt(1);
			}
			
			sql = "select bussSeats from flightInformation where flightNo='"+flightNo+"'";
			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next())
			{
				seats[1] = rs.getInt(1);
			}
			

			sql ="select sum(no_of_passengers) from Bookinginformation where class_type='first' and flightno='"+flightNo+"' group by class_type,flightno";

			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next())
			{
					seats[2]= rs.getInt(1);
			}

			sql ="select sum(no_of_passengers) from Bookinginformation where class_type='business' and flightno='"+flightNo+"' group by class_type,flightno";


			st = airlineConn.createStatement();
			rs = st.executeQuery(sql);
			
			if(rs.next())
			{
				seats[3]=rs.getInt(1);
			}
			logger.info("Flight occupany details checked for " + flightNo);
		}
		catch(Exception e)
		{
			logger.error("Flight occupancy details checking failed");
			throw new AirlineException("Server Error: Cannot get number of seats",e);
		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Server Error: Problems in Closing Connection",se);
			}
		}
		return seats;
	}
	
	@Override
	public int bookingConfirm(String username,String flightNo,int noOfPassengers,String classType,String creditCard) throws AirlineException{
		String depCity = null;
		String arrCity = null;
		double fare = 0 ;
		int status =0;
		Connection conn = null;
		Statement st = null;
		try{
			conn = DBUtil.createConnection();;
			String sql = "SELECT * FROM FLIGHTINFORMATION WHERE FLIGHTNO='"+flightNo+"'";
			st = conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				depCity = rs.getString(3);
				arrCity = rs.getString(4);
				fare =0;
				if(classType.equalsIgnoreCase("first")){
					fare=rs.getDouble(10);
				}else
					fare=rs.getDouble(12);
				fare*=noOfPassengers;
			}
		
		String sql2 = "INSERT INTO BOOKINGINFORMATION VALUES(booking_id_seq.nextval,(SELECT cust_email FROM users WHERE username='"+username+"'),'"+noOfPassengers+"','"+classType+"',"+fare+",'"+creditCard+"','"+depCity+"','"+arrCity+"','"+flightNo+"')";
		st = conn.createStatement();
		status = st.executeUpdate(sql2);
		logger.info(username + "booked the tickets");
		}catch(Exception e){
			logger.error("Booking failed");
			throw new AirlineException("Server Error: Cannot retrieve booking details for the given user",e);
		}finally{
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				throw new AirlineException("Server Error: Cannot close database connection",e);
			}
		}
		return status;
		
		
	}

	@Override
	public int checkAvailability(String query, String type)
			throws AirlineException {
		int status = 0;
		Connection connBook = null;
		Statement pstBook = null;
		String sql="";
		if(type.equals("username")){
			sql="Select count(username) from users where username='"+query+"'";
		}else if(type.equals("email")){
			sql="Select count(cust_email) from users where cust_email='"+query+"'";
		}
		try{
			connBook=DBUtil.createConnection();
			pstBook = connBook.createStatement();
			ResultSet rset  = pstBook.executeQuery(sql);
			if(rset.next())
			{
				status = rset.getInt(1);
			}
			logger.info("Availability checked");
		}catch(SQLException se){
			logger.error("Availability checking  failed");
			throw new AirlineException("Server Error: Problem in cheking the query",se);

		}finally{
			try{
				DBUtil.closeConnection();

			}catch(SQLException se){
				throw new AirlineException("Server Error: Problems in Closing Connection",se);
			}
		}
		return status;
	}
}

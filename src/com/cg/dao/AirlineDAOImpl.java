package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


}

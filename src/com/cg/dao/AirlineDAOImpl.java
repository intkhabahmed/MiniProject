package com.cg.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.BookingInfo;
import com.cg.bean.Flight;
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

}

package com.cg.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
/*
 * Class for crating the database connection and using it whenever required
 */
public class DBUtil {
	private static Connection connAirline;
	
	/*
	 * Static method to create the database connection
	 */
	public static Connection createConnection() throws SQLException{
		ResourceBundle resOracle = ResourceBundle.getBundle("oracle");
		String url = resOracle.getString("url");
		String username = resOracle.getString("username");
		String password = resOracle.getString("password");
		
		/*
		 * Registering the DriverManager
		 */
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		connAirline = DriverManager.getConnection(url,username,password);
		return connAirline;
	}
	
	/*
	 * Static Method to close database
	 */
	public static void closeConnection() throws SQLException{
		if(connAirline!=null){
			connAirline.close();
		}
	}

}

package projects.dao;
import java.sql.*;
import projects.exception.DbException;

/**
 * 
 * @author marcy
 *
 */
public class DbConnection {
	private static String HOST = "localhost";
	private static String PASSWORD = "projects";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";

	
	/**
	 * 
	 * @return
	 * @throws DbException
	 */
	public Connection getConnection() throws DbException{
		String url = 
				String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", 
						HOST,PORT,SCHEMA,USER,PASSWORD);
		
		System.out.println("Connecting to " + url);
		
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connection to " + SCHEMA + " was succsessful");
			return conn;
		} catch (SQLException e) {
			System.out.println("Connection to " + SCHEMA + " failed");
			throw new DbException("Unable to get connection to " + url);
		} 	
	}
}

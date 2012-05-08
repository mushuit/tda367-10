package tetrix.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCommunicator {

	private Connection conn;
	private static DatabaseCommunicator dc;
	private String error = "";
	
	private DatabaseCommunicator(){
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        	error = "Couldn't find JDBC driver!";
        	System.out.println(error);
        	System.exit(1);
        }
		
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/test?" +
		                                   "user=monty&password=greatsqldb");
		} catch (SQLException ex) {
		    
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	public Connection getConnection(){
		return conn;
	}
	public static DatabaseCommunicator getInstance(){
		if(dc== null){
			dc = new DatabaseCommunicator();
		}
		return dc;
	}
}

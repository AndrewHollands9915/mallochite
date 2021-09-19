package mallochite.database;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mallochite.models.classes.Contact;

public class DatabaseConnection 
{

	  private String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
	  private String DB_URL = "jdbc:hsqldb:mem:db_file";
		
	// Database credentials
	  private String USER = "sa";
	  private String PASS = "";
	  
	  private Connection conn = null;
	  private Statement stmt = null;
	  
	  
	  
	  //default con
	DatabaseConnection(String JDBC_DRIVER, String DB_URL, String USER, String PASS) throws SQLException
	{
		
		this.JDBC_DRIVER = JDBC_DRIVER;
		this.DB_URL = DB_URL;
		this.USER = USER;
		this.PASS = PASS;
		
		//open conn
		System.out.println("Connecting to database...");
	    this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
	}
	
	
	
	//find contacts
/*	public ArrayList<Contact> getContacts() throws Exception{
	//public ResultSet getContacts() throws Exception
	{
   
		 // Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM Contacts";
	     // stmt.executeUpdate("CREATE TABLE Employees ( id INTEGER IDENTITY, first VARCHAR(256),  last VARCHAR(256),age INTEGER)");
	     // stmt.executeUpdate("INSERT INTO Employees VALUES(1,'Jack','Smith', 100)");

	      ResultSet rs = stmt.executeQuery(sql);

	      // Extract data from result set
	      while (rs.next()) {
	       Contact contact = new Contact();
	       contact.setUUID(rs.getString("UUID"));
	       Array a = rs.getArray("Messages");
	       
	       contact.setIpAddress(rs.getString("IPAddress"));	    	  
	       contact.setUsername(rs.getString("UserName"));
	    	  
	       String[] nullable = (String[])a.getArray();
	       
	       //contact.setMessages(  List<String> list = Arrays.asList(array);
	     
	      }
	      // Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	    } catch (SQLException se) {
	      se.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      // finally block used to close resources
	      try {
	        if (stmt != null)
	          stmt.close();
	      } catch (SQLException se2) {
	      }
	      try {
	        if (conn != null)
	          conn.close();
	      } catch (SQLException se) {
	        se.printStackTrace();
	      }
	    }
	    System.out.println("Goodbye!");
	  }
		
		
		
	
	
	*/
	
}




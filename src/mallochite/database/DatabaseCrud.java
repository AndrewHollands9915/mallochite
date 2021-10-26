package mallochite.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseCrud {
	
public static Connection connect() {
		
		Connection con = null;
		
		try {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase1.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e+"");
		}
		
		return con;
	}

//create new table if the table does not exsist
/*public static void CreateTablesIfNewlaunch()
{
	Connection con = DatabaseCrud.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
            
	try {	
		String sql = "CREATE TABLE COMPANY"+"(ID INT PRIMARY KEY NOT NULL,"+ 
		"NAME TEXT NOT NULL, AGE INT NOT NULL)";
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();	
	
} catch(SQLException e) {
	System.out.println(e.toString());
}
	System.out.println("table created");
}

}*/

public static void CreateTablesIfNewlaunch() throws ClassNotFoundException {
	Connection con = null;
	Statement stmt = null;
	
	try
	{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase1.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		stmt = con.createStatement();
		String sql = "CREATE TABLE COMPANY"+"(ID INT PRIMARY KEY NOT NULL,"+ 
				"NAME TEXT NOT NULL, AGE INT NOT NULL)";
		
		stmt.executeUpdate(sql);
		stmt.close();
		con.close();
} catch(SQLException e) {
	System.out.println(e.toString());
}
	System.out.println("table created");
		}
	
	

//create message table
public static void CreateTableMessage() throws ClassNotFoundException {
	Connection con = null;
	Statement stmt = null;
	
	try
	{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase1.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		stmt = con.createStatement();
		//String sql = "CREATE TABLE COMPANY"+"(ID INT PRIMARY KEY NOT NULL,"+ 
		//		"NAME TEXT NOT NULL, AGE INT NOT NULL)";

		
		String sql = "CREATE TABLE Message"+ "(ID INTEGER PRIMARY KEY NOT NULL,"+
				"TEXT	TEXT NOT NULL,"+
				"Date	TEXT NOT NULL,"+
				"ReadReciept	TEXT NOT NULL,"+
				"ContactFK	INTEGER)";
			
			
		stmt.executeUpdate(sql);
		stmt.close();
		con.close();
} catch(SQLException e) {
	System.out.println(e.toString());
}
	System.out.println("table created");
		}
	
//make contact table
public static void CreateTableContact() throws ClassNotFoundException {
	Connection con = null;
	Statement stmt = null;
	
	try
	{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase1.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		stmt = con.createStatement();
		//String sql = "CREATE TABLE COMPANY"+"(ID INT PRIMARY KEY NOT NULL,"+ 
		//		"NAME TEXT NOT NULL, AGE INT NOT NULL)";

		String sql1 = "CREATE TABLE Contact"+"(UUID	TEXT NOT NULL,"+
				"UserName TEXT NOT NULL,"+
				"IPAddress	TEXT NOT NULL,"+
				"Messages	TEXT,"+
				"PRIMARY KEY(UUID),"+
				"FOREIGN KEY(Messages)"+ "REFERENCES Message(ID))";
			
		stmt.executeUpdate(sql1);
		stmt.close();
		con.close();
} catch(SQLException e) {
	System.out.println(e.toString());
}
	System.out.println("table created");
		}



}

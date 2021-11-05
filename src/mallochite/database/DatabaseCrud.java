package mallochite.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseCrud {
	
	
	
	public static int item = 0;
public static Connection connect() {
		
		Connection con = null;
		
		try {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e+"");
			item = 1; //check if created 
		}
		
		return con;
	}


//check if the data exists by calling all contacts from the tables
//we return error if tables don;t exist
public static void readMessagesEveryUser() {
	Connection con = DatabaseCrud.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		String sql = "SELECT * FROM Contact";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();	
		
		System.out.println("ALL contacts:\n");
		while(rs.next()) {
			
			/*String UUID = rs.getString("UUID");
			String UserName = rs.getString("UserName");
			String IPAddress = rs.getString("IPAddress");*/
				}
		rs.close();
		ps.close();				
	}catch (SQLException e) {
		System.out.println(e.toString());	
		item = 1;
	}	
}

public static int getItem()
{
	if (item==0)
	{
		System.out.println("Tables exist previous scema used");
	}
	else if (item==1)
	{
		System.out.println("Tables are not created. Create new tables please");		
	}	
	System.out.println("Status: "+item);
	
return item;
}





//create message table
public static void CreateTableMessage() throws ClassNotFoundException {
	Connection con = null;
	Statement stmt = null;
	
	try
	{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		stmt = con.createStatement();
		//String sql = "CREATE TABLE COMPANY"+"(ID INT PRIMARY KEY NOT NULL,"+ 
		//		"NAME TEXT NOT NULL, AGE INT NOT NULL)";

		
		String sql = "CREATE TABLE Message"+ "(ID INTEGER PRIMARY KEY NOT NULL,"+
				"TEXT	TEXT NOT NULL,"+
				"Date	TEXT NOT NULL,"+
				"Sent	INTEGER NOT NULL,"+	
				"ReadReciept	INTEGER NOT NULL,"+
				"ContactFK	INTEGER,"+
				"ContactOwner	INTEGER)";
			
			
		stmt.executeUpdate(sql);
		stmt.close();
		con.close();
} catch(SQLException e) {
	System.out.println(e.toString());
}
	System.out.println("message table created");
		}
	
//make contact table
public static void CreateTableContact() throws ClassNotFoundException {
	Connection con = null;
	Statement stmt = null;
	
	try
	{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		stmt = con.createStatement();
		//String sql = "CREATE TABLE COMPANY"+"(ID INT PRIMARY KEY NOT NULL,"+ 
		//		"NAME TEXT NOT NULL, AGE INT NOT NULL)";

		String sql1 = "CREATE TABLE Contact"+"(UUID	TEXT NOT NULL,"+
				"UserName TEXT NOT NULL,"+
				"IPAddress	TEXT NOT NULL,"+
				"Messages	INTEGER,"+
				"FOREIGN KEY(Messages)"+ "REFERENCES Message(ID)"+
				"PRIMARY KEY(UUID))"
				;
			
		stmt.executeUpdate(sql1);
		stmt.close();
		con.close();
} catch(SQLException e) {
	System.out.println(e.toString());
}
	System.out.println("Contact table created");
		}

//create registation table
public static void CreateTableRegistration() throws ClassNotFoundException {
	Connection con = null;
	Statement stmt = null;
	
	try
	{
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		stmt = con.createStatement();

		String sql1 = "CREATE TABLE Registration"+"(Username	TEXT NOT NULL,"+
				"Password	TEXT NOT NULL,"+
				"PRIMARY KEY(Username))";		
			
		stmt.executeUpdate(sql1);
		stmt.close();
		con.close();
} catch(SQLException e) {
	System.out.println(e.toString());
}
	System.out.println("Registration table created");
		}

}

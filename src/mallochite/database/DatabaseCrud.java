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
	
	
	
}

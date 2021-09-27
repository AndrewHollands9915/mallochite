package mallochite.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseCrud {
	
public static Connection connect() {
		
		Connection con = null;
		
		try {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:UserDatabase.db"); //note if one does not exist a database is created
		System.out.println("Connected");
		
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e+"");
		}
		
		return con;
	}


}

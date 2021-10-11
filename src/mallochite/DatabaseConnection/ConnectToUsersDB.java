package mallochite.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToUsersDB {
	
	public static Connection getConnection()
	{  
        // SQLite connection string  
        String url = "jdbc:sqlite:usersdb.db";  
        Connection conn = null;  
        try 
        {  
            conn = DriverManager.getConnection(url);  
            
        } 
        catch (SQLException e)  
        {  
        	System.out.println(e.getMessage());
        	conn=null;
        }  
        return conn;  
    }  

}

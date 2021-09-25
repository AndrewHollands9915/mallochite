package mallochite.database;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mallochite.models.classes.Contact;

public class DatabaseConnection 
{
	//read data
		public static void readMessagesEveryUser() {
			Connection con = DatabaseCrud.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM Contact";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				int forKey = 0;
				
				System.out.println("ALL contacts:\n");
				while(rs.next()) {
					
					String UUID = rs.getString("UUID");
					String UserName = rs.getString("UserName");
					String IPAddress = rs.getString("IPAddress");
					forKey = rs.getInt("Messages");

					System.out.println("\nUUID: "+UUID+"\nUserName: "+UserName+"\nIPAddress: "+IPAddress);		
					readMessagesGivenUser(forKey); //read user messages for each user
				}
				rs.close();
				ps.close();
				
			/*	//for messages
				sql = "SELECT * FROM Message WHERE ContactFK ="+ forKey; //only gets for the 1st contact
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				System.out.println("ALL messages:\n");
				while(rs.next()) {
					
					int ID = rs.getInt("ID");
					String Text = rs.getString("TEXT");
					String Date = rs.getString("Date");
					String ReadReciept = rs.getString("ReadReciept");
					

					System.out.println("\nID: "+ID+"\nText: "+Text+"\nDate: "+Date+"\nReadReciept: "+ReadReciept);		
					
				}
				*/
				
				
				
			}catch (SQLException e) {
				System.out.println(e.toString());
			} finally {
				try {
					rs.close();
					ps.close();
					con.close();
				} catch(SQLException e) {
					System.out.println(e.toString());
				}
				
				
			}
			
		}


		//read data from give user
				public static void readMessagesGivenUser(int forKey) {
					Connection con = DatabaseCrud.connect();
					PreparedStatement ps = null;
					ResultSet rs = null;
						try {		
						//for messages
						String sql = "SELECT * FROM Message WHERE ContactFK ="+ forKey; //only gets for the 1st contact
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						
						System.out.println("ALL messages:\n");
						while(rs.next()) {
							
							int ID = rs.getInt("ID");
							String Text = rs.getString("TEXT");
							String Date = rs.getString("Date");
							String ReadReciept = rs.getString("ReadReciept");
							

							System.out.println("\nID: "+ID+"\nText: "+Text+"\nDate: "+Date+"\nReadReciept: "+ReadReciept);		
							
						}
						
									
						} catch(SQLException e) {
							System.out.println(e.toString());
						}
						
					
					}
					
				




}




package mallochite.database;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

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
					
				//user update prob dont need
			/*	public void UserUpdate() {
					 Scanner scan = new Scanner(System.in);  // Create a Scanner object
					    System.out.println("Enter UUID: ");

					    String UUID = scan.nextLine();  // Read user input
					    //userId.close();
					
					UserModel userModel = new UserModel();
					Contact contact= userModel.find(UUID);
					contact.setUsername("jose");
					contact.setIpAddress("jose");				
					boolean result = userModel.Update(contact);
					System.out.println(result);
					scan.close();
				}*/
				
				//insert user into database
				public static void UserInsert(String UUID, String UserName, String IPAddress) {
					
					String sql = "INSERT INTO Contact(UUID, UserName, IPAddress) VALUES(?,?,?)";				
		            
		            try {
		            	Connection con = DatabaseCrud.connect();
			            PreparedStatement ps = null;		         
		                
		                ps = con.prepareStatement(sql);		  
		                          
		                    ps.setString(1, UUID);
		                    ps.setString(2, UserName);
		                    ps.setString(3, IPAddress);
		                    ps.executeUpdate();  
		                
		                    System.out.println("User data are successfully saved!:\n");	                                          
		                
		            }catch (SQLException e) {
		                System.out.println(e.toString());
		            } 		            
				}


}




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
				
			//user update only updates UserName right now
			public static void updateUser(String UserName) {
				 //Scanner scan = new Scanner(System.in);  // Create a Scanner object
				    System.out.println("Enter UUID: ");

				   Connection con = DatabaseCrud.connect();
				PreparedStatement ps = null;
				   
			try {
				String sql = "UPDATE Contact set UserName = ? WHERE UUID = ?";
				ps = con.prepareStatement(sql);
				//ps.setString(1, "GOD PLEASE WORK PLEASE"); 
				ps.setString(1, UserName);
				ps.setString(2, "3"); //ID
				ps.execute();
				System.out.println("Data has been updated");
				
			} catch (SQLException e){
				System.out.print(e.toString());
			}
				
			}
			
			//insert user into database
			public static void UserInsert(String UUID, String UserName, String IPAddress, int Port, int Messages) {
				
				String sql = "INSERT INTO Contact(UUID, UserName, IPAddress, Messages, Port) VALUES(?,?,?,?,?); COMMIT;";				
	            
	            try {
	            	Connection con = DatabaseCrud.connect();
		            PreparedStatement ps = null;		         
	                
	                ps = con.prepareStatement(sql);		  
	                          
	                    ps.setString(1, UUID);
	                    ps.setString(2, UserName);
	                    ps.setString(3, IPAddress);
	                    ps.setInt(4,Messages);
	                    ps.setInt(5, Port);
	                    ps.executeUpdate();  
	                
	                    System.out.println("User data are successfully saved!:\n");	                                          
	                con.close();
	            }catch (SQLException e) {
	                System.out.println(e.toString());
	            } 		            
			}
			
			public static int contactCount(){
				int count = 0;
				
String sql = "SELECT * FROM Contact \n"
		+ "ORDER BY UUID DESC LIMIT 1; COMMIT;  ";				
	            
	            try {
	            	Connection con = DatabaseCrud.connect();
		            PreparedStatement ps = null;
		            ResultSet rs = null;
	                
	                ps = con.prepareStatement(sql);		  
	                rs = ps.executeQuery();  
	                count = rs.getInt("Messages");
	             	con.close();                                         
	                
	            }catch (SQLException e) {
	                System.out.println(e.toString());
	            } 
	            return count; 
			}

			public static String getUserName(){
				String username = "";
				
String sql = "SELECT Username FROM Registration; COMMIT;  ";				
	            
	            try {
	            	Connection con = DatabaseCrud.connect();
		            PreparedStatement ps = null;
		            ResultSet rs = null;
	                
	                ps = con.prepareStatement(sql);		  
	                rs = ps.executeQuery();  
	                username = rs.getString("Username");
	             	con.close();                                         
	                
	            }catch (SQLException e) {
	                System.out.println(e.toString());
	            } 
	            return username; 
			}
			
			
			public static int getMessagesRecipient(String userName){
				int id = 3;
				
String sql = "SELECT * FROM Contact WHERE UserName = Windows; Commit;" ;				
	            
	            try {
	            	Connection con = DatabaseCrud.connect();
		            PreparedStatement ps = null;
		            ResultSet rs = null;
	                
	                ps = con.prepareStatement(sql);		  
	                rs = ps.executeQuery();  
	                System.out.println(rs.getInt("Messages"));
	                System.out.println(userName);
	             	con.close();                                         
	                
	            }catch (SQLException e) {
	                System.out.println(e.toString());
	            } 
	            return id; 
			}
			
			
			
			
			//insert message into database
			public static void messageInsert(String text, String Date, int sent, int ReadReciept, int ContactFK, int ContactOwner) {
				
				String sql = "INSERT INTO Message(text, Date, Sent, ReadReciept, ContactFK, ContactOwner) VALUES(?,?,?,?,?,?)";				
	            
	            try {
	            	Connection con = DatabaseCrud.connect();
		            PreparedStatement ps = null;		         
	                
	                ps = con.prepareStatement(sql);		  
	                          
	                    ps.setString(1, text);
	                    ps.setString(2, Date);
	                    ps.setInt(3, sent);
	                    ps.setInt(4, ReadReciept);
	                    ps.setInt(5, ContactFK);
	                    ps.setInt(6, ContactOwner);
	                   
	                    ps.executeUpdate();  
	                
	                    System.out.println("User data are successfully saved!:\n");	                                          
	                con.close();
	            }catch (SQLException e) {
	                System.out.println(e.toString());
	            } 		            
			}

			public static void updateUserDB(String UUID, String UserName, String IPAddress) {
				
		        String sql = "UPDATE Contact SET UserName = ? , "
		                + "IPAddress = ? "
		                + "WHERE UUID = ?";

		        try {
		        	Connection con = DatabaseCrud.connect();
			            PreparedStatement ps = null;
		        		ps = con.prepareStatement(sql); 

		            // set the corresponding param
		            ps.setString(1, UserName);
		            ps.setString(2, IPAddress);
		            ps.setString(3, UUID);
		            // update 
		            ps.executeUpdate();
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			}
			
			//delete
			public static void deleteUserDB(String UUID)
			  {
				String sqlDelUser = "DELETE FROM Contact where UUID=?;";
			
			        try {
			        	Connection con = DatabaseCrud.connect();
			            PreparedStatement ps = null;
			            //con.setAutoCommit(false);
			            ResultSet rs = null;
			            System.out.println("Opened database successfully");
			            
			            int forKey = 0;
			            String sql = "SELECT * FROM Contact;" ;
			            ps = con.prepareStatement(sql);
				        rs = ps.executeQuery();
			            System.out.println("ALL contacts:\n");
			            while(rs.next()) {	                    
			                    //String uuid = rs.getString("UUID");
			                    //String UserName = rs.getString("UserName");
			                    //String IPAddress = rs.getString("IPAddress");
			                    forKey = rs.getInt("Messages");

			                   // System.out.println("UUID: "+uuid+"\nUserName: "+UserName+"\nIPAddress: "+IPAddress);        
			                    
				         }
			 
			          ps = con.prepareStatement(sqlDelUser);			         
			          ps.setString(1, UUID);
			          ps.executeUpdate();
			         // con.commit();
			 
			          
			          rs.close();
			          ps.close();
			          //con.close();
			          
			        
		                
		                //for messages
		                String sqlDelMsg = "DELETE FROM Message WHERE ContactFK ="+ forKey;
		                ps = con.prepareStatement(sqlDelMsg);
		                //.setString(1, ContactFK);
				        ps.executeUpdate();
				       // con.commit();
		              
		                
		                System.out.println("ALL messages are deleted:\n");
		                while(rs.next()) {	                    
		                    int ID = rs.getInt("ID");
		                    String Text = rs.getString("TEXT");
		                    String Date = rs.getString("Date");
		                    String ReadReciept = rs.getString("ReadReciept");
		                    
		                    System.out.println("\nID: "+ID+"\nText: "+Text+"\nDate: "+Date+"\nReadReciept: "+ReadReciept);        
		                    
		                }
			        } catch ( Exception e ) {
			          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			          System.exit(0);
			        }
			        System.out.println("Operation done successfully");
			  }
				
				
}




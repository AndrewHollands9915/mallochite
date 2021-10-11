package mallochite.DatabaseConnection;


import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import mallochite.models.classes.Contact;

public class UserDemos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			    UserDemos app = new UserDemos();
				Scanner scan = new Scanner(System.in);  // Create a Scanner obj
				
				
			//do {
				System.out.println("What do you want to do?");
				System.out.println("1. Find contact");
				System.out.println("2. Update contact credentials");
				System.out.println("3. Insert contact credentials");
				System.out.println("4. Delete contact credentials");
				String choice = scan.nextLine();
				//System.out.println("   Press 0 to exit.....");
				//System.out.println("Enter your choice: ");
				//choice = scan.nextInt();				
							
								
				if(choice.equals("1")) {
					app.searchUserDB();
				}
				else if(choice.equals("2")) {
					System.out.println("Enter UUID");
					String uuid = scan.nextLine();
					System.out.println("Enter Usernme");
					String username = scan.nextLine();
					System.out.println("Enter IPaddress");
					String ipaddress = scan.nextLine();
				
					app.updateUserDB(uuid,username,ipaddress);
					
				}
				else if(choice.equals("3")) {
					System.out.println("Enter UUID");
					String uuid = scan.nextLine();
					System.out.println("Enter Usernme");
					String username = scan.nextLine();
					System.out.println("Enter IPaddress");
					String ipaddress = scan.nextLine();
				
					app.insertUserDB(uuid,username,ipaddress);
				}
				else if(choice.equals("4")) {
					System.out.println("Enter UUID");
					String uuid = scan.nextLine();
				
					app.deleteUserDB(uuid);
				}
				scan.close();
			//}while(choice!=0);
			//System.out.println("Exiting.... ");
			
		}
			
				
			public void searchUserDB() {
				/*
				 Scanner scan = new Scanner(System.in);  // Create a Scanner object
				    System.out.println("Enter userId: ");

				    String UUID = scan.nextLine();  // Read user input
				   // int forKey = 0;
				    //userId.close();
				
				UserModel userModel = new UserModel();
				Contact contact = userModel.find(UUID);
				
				
				//for(Users user : users) {
					System.out.println("UUID: " + contact.getUUID());
					System.out.println("UserName: " + contact.getUsername());
					System.out.println("IPAddress: " + contact.getIpAddress());
					//forKey = contact.getMessages();
				//}
					//userId.close();		
*/
				
				Connection con = ConnectToUsersDB.getConnection();
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

	                    System.out.println("UUID: "+UUID+"\nUserName: "+UserName+"\nIPAddress: "+IPAddress);        
	                    
	                }
	               rs.close();
	               ps.close();
	                
	                //for messages
	                sql = "SELECT * FROM Message WHERE ContactFK ="+ forKey;
	                ps = con.prepareStatement(sql);
	                rs = ps.executeQuery();
	                
	                System.out.println("ALL messages:\n");
	                while(rs.next()) {
	                    
	                    int ID = rs.getInt("ID");
	                    String Text = rs.getString("TEXT");
	                    String Date = rs.getString("Date");
	                    int Sent = rs.getInt("Sent");
	                    int ReadReciept = rs.getInt("ReadReciept");
	                    int ContactFK = rs.getInt("ContactFK");
	                                        
	                    System.out.println("\nID: "+ID+"\nText: "+Text+"\nDate: "+Date+"\nSent: "+Sent+"\nReadReciept: "+ReadReciept+"\nContactFK: "+ContactFK);        
	                    
	                }
	                    
	                
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
			
			public void updateUserDB(String UUID, String UserName, String IPAddress) {
				
			        String sql = "UPDATE Contact SET UserName = ? , "
			                + "IPAddress = ? "
			                + "WHERE UUID = ?";

			        try {
			        	Connection con = ConnectToUsersDB.getConnection();
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
				
				/*
				 Scanner scan = new Scanner(System.in);  // Create a Scanner object
				   // System.out.println("Enter UUID: ");

				  //  String UUID = scan.nextLine();  // Read user input
				    //userId.close();
				
				UserModel userModel = new UserModel();
				Contact contact= userModel.find(UUID);
				contact.setUsername("jose");
				contact.setIpAddress("jose");				
				boolean result = userModel.Update(contact);
				System.out.println(result);
				scan.close();
				*/
			}
			
			public void insertUserDB(String UUID, String UserName, String IPAddress) {
				
				String sql = "INSERT INTO Contact(UUID, UserName, IPAddress) VALUES(?,?,?)";
				
	            try {
	            	Connection con = ConnectToUsersDB.getConnection();
		            PreparedStatement ps = null;
		            //ResultSet rs = null;
	                
	                ps = con.prepareStatement(sql);
	                //rs = ps.executeQuery();	
	                
	               //  int forKey = 0;
	                
	                //System.out.println("ALL contacts:\n	");
	                          
	                    ps.setString(1, UUID);
	                    ps.setString(2, UserName);
	                    ps.setString(3, IPAddress);
	                    ps.executeUpdate();  
	                   // forKey = ps.setInt("Messages");

	                    //System.out.println("UUID: "+UUID+"\nUserName: "+UserName+"\nIPAddress: "+IPAddress);        
	                    
	                
	                //rs.close();
	                //ps.close();
	                
	                //for messages
	                //sql = "SELECT * FROM Message WHERE ContactFK ="+ forKey;
	               // ps = con.prepareStatement(sql);
	               // rs = ps.executeQuery();
	                    System.out.println("User contact was successfully save!:\n");
	                                          
	                
	            }catch (SQLException e) {
	                System.out.println(e.toString());
	            } 
	                
	                
	            
	            
			}
			
			public void deleteUserDB(String UUID)
			  {
				String sqlDelUser = "DELETE FROM Contact where UUID=?;";
			
			        try {
			        	Connection con = ConnectToUsersDB.getConnection();
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


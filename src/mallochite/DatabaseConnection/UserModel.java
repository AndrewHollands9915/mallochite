package mallochite.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mallochite.models.classes.Contact;
import mallochite.models.classes.Message;

public class UserModel {
	
	public Contact find(String UUID){
		Contact contact = null;
		PreparedStatement pstmt = null;
        ResultSet rs; 
         
		int forKey = 0;
		try {
			 pstmt = ConnectToUsersDB.getConnection()
				 	.prepareStatement("SELECT * from Contact where UUID = ?");
			pstmt.setString(1, UUID);
		    rs = pstmt.executeQuery();
			if (rs.next()) {
			    contact = new Contact();
				contact.setUUID(rs.getString("UUID"));
				contact.setUsername(rs.getString("Username"));
				contact.setIpAddress(rs.getString("IpAddress"));	
				forKey = rs.getInt("Messages");
				
				
				//users.add(user);
			}
			//rs.close();
            pstmt.close();
			
            
            //for messages
           
            pstmt = ConnectToUsersDB.getConnection()
				 	.prepareStatement("SELECT * from Message where ID = " + forKey);
			//pstmt.setString(1, UUID);
		    rs = pstmt.executeQuery();
            
            System.out.println("ALL messages:\n");
            while(rs.next()) {
                
                int ID = rs.getInt("ID");
                String Text = rs.getString("TEXT");
                String Date = rs.getString("Date");
                String ReadReciept = rs.getString("ReadReciept");
                

                System.out.println("\nID: "+ID+"\nText: "+Text+"\nDate: "+Date+"\nReadReciept: "+ReadReciept);        
                
            }
		}catch (Exception e) {
		 System.out.println(e.getMessage());
		 contact = null;	
		}
		
	return contact;
	}
	
	
	public boolean Update(Contact contact) {
		boolean result = true;
		
		try {
			PreparedStatement pstmt = ConnectToUsersDB.getConnection()
				 	.prepareStatement("UPDATE Contact SET UserName = ?, IPAddress = ? where UUID = ?");
			//pstmt.setString(1, contacts.getUUID());
			pstmt.setString(1, contact.getUsername());
			pstmt.setString(2, contact.getIpAddress());
			pstmt.setString(3, contact.getUUID());
			result = pstmt.executeUpdate() > 0;
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		
				
		return result;
	}
	
/*
	public List<Users> findAll(){
		List<Users> users = new ArrayList<Users>();
		try {
			PreparedStatement pstmt = ConnectToUsersDB.getConnection()
				 	.prepareStatement("SELECT * from users");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("id"));
				user.setFirstname(rs.getString("firstname"));
				user.setSecondname(rs.getString("secondname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
		}catch (Exception e) {
		 System.out.println(e.getMessage());
		 users = null;	
		}
	return users;
	}
	*/

}

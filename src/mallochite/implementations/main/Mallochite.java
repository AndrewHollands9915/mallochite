package mallochite.implementations.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.net.Socket;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import mallochite.models.abstractClasses.nodes.Node;
import mallochite.models.classes.*;
import mallochite.models.classes.nodes.SubNode;
import mallochite.models.classes.nodes.SuperNode;
import mallochite.ui.FrameLoginChat;
import mallochite.database.DatabaseConnection;
import mallochite.database.DatabaseCrud;
import mallochite.encryption.AESEncryption;
import mallochite.encryption.RSAEncryption;
import mallochite.encryption.SecretKeyGenerator;

public class Mallochite 
{
	private static Scanner scanner;
	private static InetAddress inetAddress;
	private static User user;
	private static SubNode subNode;
	private static SuperNode superNode;
	private static ChatManager manager;
	
	public static void main ( String [] args ) throws Exception
	{
		
		  DatabaseCrud.connect(); 
	        DatabaseCrud.readMessagesEveryUser(); //check if tables exist if not create them
	        if (DatabaseCrud.getItem()==1) {
	            DatabaseCrud.CreateTableMessage();
	            DatabaseCrud.CreateTableContact();
	            DatabaseCrud.CreateTableRegistration();
	        }
		
		
		if ( args.length > 0 && args[0].equalsIgnoreCase( "super" ))
			runAsSuperNode();
		else
			runAsSubNode();
	}   
	
	
	public static void runAsSubNode() throws Exception
	{
		scanner = new Scanner( System.in );
		inetAddress = InetAddress.getLocalHost();
		user = new User();
		subNode = new SubNode();
		manager  = new ChatManager( subNode );
		
		
		Connection con = DatabaseCrud.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Contact";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();	
		
		
		
		user.setUsername( "user2" );
		user.setIP( inetAddress.getHostAddress() );
		user.setPort(42424);
		user.setUUID( "qwer-321" );
		subNode.setThisUser( user );
		
		/*
		User contact1 = new User();
		contact1.setUsername( "user1" );
		contact1.setIP("192.168.2.140");
		contact1.setPort(42424);
		contact1.setUUID( "asdf-123" );
		user.getUserList().add( contact1 );
		*/
		while(rs.next()) {
			User contact = new User();
			contact.setUsername( rs.getString("UserName") );
			contact.setIP( rs.getString("IPAddress") );
			contact.setPort(rs.getInt("Port"));
			contact.setUUID( rs.getString("UserName"));
			user.getUserList().add( contact );
		}
		
		
		
		
		
		
		

		
		try
		{
			
			if ( available( subNode.getThisUser().getPort() ) )
			{
				System.out.println("is open");
				subNode.openServerSocket( subNode.getThisUser().getPort() );
				subNode.start();
			}
			else 
			{ 
				System.out.println("is in use"); 
			}
			
			while(subNode.isListening()) {
				manager.menu();
			}
			   
      
		} catch( IOException ex )  { ex.printStackTrace(); }
		finally
		{
			if ( subNode.getServerSocket() != null )
				subNode.closeServerSocket();
		}
	}
	
	
	public static void runAsSuperNode() throws Exception
	{
		scanner = new Scanner( System.in );
		inetAddress = InetAddress.getLocalHost();
		user = new User();
		superNode = new SuperNode( user );
		
		//TODO: Get info from database
		user.setUsername( "user2" );
		user.setIP( inetAddress.getHostAddress() );
		user.setPort(42424);
		user.setUUID( "asdf-123" );
		user.setPublicKey( RSAEncryption.getpublicKey("public.key") );
		superNode.setThisUser( user );

		
		try
		{
			
			if ( available( superNode.getThisUser().getPort() ) )
			{
				superNode.openServerSocket( superNode.getThisUser().getPort() );
				superNode.start();
			}
			else 
			{ 
				System.out.println("is in use"); 
			}
			
			while( superNode.isListening() )
			{
				//superNode.
			}   
      
		}
		catch(IOException ex) 
		{
			ex.printStackTrace();
		}
		
		finally
		{
			if ( superNode.getServerSocket() != null )
				superNode.closeServerSocket();
		}
	}
	
	
	public static boolean available(int port) {
	    try (Socket ignored = new Socket("localhost", port)) {
	        return false;
	    } catch (IOException ignored) {
	    	return true;
	    }
	}
	
	public void loadUsers(User user) {
		
	}
	

}
	    
package mallochite.implementations.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.net.Socket;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import mallochite.models.classes.*;
import mallochite.models.classes.nodes.SubNode;
import mallochite.models.classes.nodes.SuperNode;
import mallochite.ui.FrameLoginChat;
import mallochite.ui.FrameRegistration;
import mallochite.ui.FrameUserChat;
import mallochite.database.DatabaseConnection;
import mallochite.database.DatabaseCrud;
import mallochite.encryption.RSAEncryption;

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
	
		//connect to the database
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
		
		
		// contact
		User contact = new User();
		contact.setUsername( "user1" );
		contact.setIP( inetAddress.getHostAddress() );
		contact.setPort(22222);
		contact.setUUID( "asdf-123" );
		contact.setPublicKey(RSAEncryption.getpublicKey("public.key"));
		
		
		User contact1 = new User();
		contact.setUsername( "user3" );
		contact.setIP( inetAddress.getHostAddress() );
		contact.setPort(22223);
		contact.setUUID( "asdf-123" );
		contact.setPublicKey(RSAEncryption.getpublicKey("public.key"));
		
		//TODO: Get info from database
		user.setUsername( "user2" );
		user.setIP( inetAddress.getHostAddress() );
		user.setPort(33333);
		user.setUUID( "qwer-321" );
		user.setPublicKey(RSAEncryption.getpublicKey("public.key"));
		
		user.getUserList().add( contact );
		//user.getUserList().add( contact1 );
		subNode.setThisUser( user );

		
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
			
			while( subNode.isListening() )
			{
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
	
	
	private static boolean available(int port) {
	    try (Socket ignored = new Socket("localhost", port)) {
	        return false;
	    } catch (IOException ignored) {
	    	return true;
	    }
	}
}
	    
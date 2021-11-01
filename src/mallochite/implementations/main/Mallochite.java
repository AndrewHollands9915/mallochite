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
import mallochite.ui.FrameLoginChat;
import mallochite.ui.FrameRegistration;
import mallochite.ui.FrameUserChat;
import mallochite.database.DatabaseConnection;
import mallochite.database.DatabaseCrud;

public class Mallochite 
{
	public static void main ( String [] args ) throws Exception
	{
		FrameLoginChat frame2 = new FrameLoginChat();
		frame2.getLogin();
		frame2.setVisible(true);
		
	//	FrameRegistration frame2 = new FrameRegistration();
		//frame2.setVisible(true);
		//frame2.getOperation();
	
		//connect to the database
		DatabaseCrud.connect(); 
		DatabaseCrud.readMessagesEveryUser(); //check if tables exist if not create them
		if (DatabaseCrud.getItem()==1) {
			DatabaseCrud.CreateTableMessage();
			DatabaseCrud.CreateTableContact();
		}
		
		
		Scanner scanner = new Scanner( System.in );
		InetAddress inetAddress = InetAddress.getLocalHost();
		SubNode subNode1 = null;
		User thisUser = new User();
		User remoteUser = new User();
		
				
			remoteUser = new User();
			
			remoteUser.setIP( inetAddress.getHostAddress() );
			remoteUser.setPort(23457);
			remoteUser.setUsername( "user1" );
			remoteUser.setUUID( "asdf-123" );
			

		
		thisUser.setUsername( "user2" );
		thisUser.setIP( inetAddress.getHostAddress() );
		thisUser.setPort(12121);
		thisUser.setUUID( "asdf-321" );
		
		thisUser.getUserList().add(remoteUser);

		subNode1 = new SubNode( thisUser );
		
		try
		{
			
			if (available(subNode1.getThisUser().getPort()))
			{
				System.out.println("is open");
				subNode1.openServerSocket( subNode1.getThisUser().getPort());
			}
			else
			{
				System.out.println("is in use");
			}
			
			
		
			
			subNode1.start();
			ChatManager manager  = new ChatManager(subNode1);
			while( subNode1.isListening() )
			{
				manager.menu();
			}   
      
		}
		catch(IOException ex) 
		{
			ex.printStackTrace();
		}
		
		finally
		{
			//System.out.println(subNode1.getServerSocket().toString());
			if ( subNode1.getServerSocket() != null )
			{
				
				subNode1.closeServerSocket();
				
			}
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
	    
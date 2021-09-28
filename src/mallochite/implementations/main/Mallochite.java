package mallochite.implementations.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.Socket;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import mallochite.models.classes.*;
import mallochite.models.classes.nodes.SubNode;
import mallochite.models.classes.nodes.SuperNode;
import mallochite.database.DatabaseConnection;
import mallochite.database.DatabaseCrud;

public class Mallochite 
{
	public static void main ( String [] args ) throws Exception
	{
		User metaUser = new User();
		SuperNode node = new SuperNode( metaUser );
		ArrayList<User> userList = DatabaseConnection.getArrayListOfUsers();
		InetAddress inetAddress = InetAddress.getLocalHost();
		
		// TODO: 
		//			- populate user list with data from database on start up
		//			- Listen for connections
		//			- Add all new users to database 
		// 			- Handle requests for contact
		

		
		node.getThisUser().setUserList( userList );
		node.getThisUser().setUsername( "metaUser" );
		node.getThisUser().setIP( inetAddress.getHostAddress() );
		node.getThisUser().setPort(12121);
		node.getThisUser().setUUID( "asdf-321" );
		
		try
		{
			if ( available(node.getThisUser().getPort()) )
			{
				node.openServerSocket( node.getThisUser().getPort());
			}
			else
			{
				System.out.println("is in use");
			}
			
			
			node.start();

      
		}
		catch(IOException ex) 
		{
			ex.printStackTrace();
		}
		
		finally
		{
			//System.out.println(subNode1.getServerSocket().toString());
			if ( node.getServerSocket() != null )
			{	
				node.closeServerSocket();
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
	    
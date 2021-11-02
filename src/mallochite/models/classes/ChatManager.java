package mallochite.models.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.Key;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPanel;

import mallochite.database.DatabaseConnection;
import mallochite.encryption.EncryptionMain;
import mallochite.encryption.RSAEncryption;
import mallochite.encryption.SecretKeyGenerator;
import mallochite.models.classes.nodes.SubNode;
import mallochite.ui.FrameAddMember;
import mallochite.ui.FrameLoginChat;
import mallochite.ui.FrameUserChat;

public class ChatManager
{
	private Socket socket;
	private SubNode subNode;
	private Scanner sc = new Scanner(System.in);
	//FrameUserChat frameChat = new FrameUserChat();

	
	public ChatManager( SubNode subNode )
	{
		this.subNode = subNode;
	}
	
	
	public void menu() throws Exception
	{
		Scanner scanner = new Scanner ( System.in );
		
		
		//have ui replace this
		System.out.println( "What would you like to do?" );
		System.out.println( "\t 0. send key" );
		System.out.println( "\t 1. send message" );
		System.out.println( "\t 2. check messages" );
		System.out.println( "\t 3. add contact" );
		System.out.println( "\t 4. list contacts" );
		
		// UI doesn't work on docker
		/*
		FrameUserChat frame = new FrameUserChat();
		frame.setVisible(true);
		
		FrameAddMember frameAdd = new FrameAddMember();
		frameAdd.setVisible(true);
		JButton test = new JButton();

	
		
		
		//send message the + icon
		JButton btu1 = new JButton();
		
		btu1 = frame.getbtnAddNew();
		btu1.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("please");
		    	
		    	displayContactsUI();
		    	
		    } 
		});
		
		
		
		//add contact form frameAddMember
				test = frameAdd.getPnlBtnAddNew_1();
		test.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("please djguiduijoh");
		    	try {
					addContactui();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    } 
		});
		
		
				
		//the arrow button
		JButton btuSend = new JButton();
		btuSend = frame.getBtnSendMsg();
		btuSend.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("please sfjnfibdsuibesuigds");	    	
		    	
		    	try {
					sendMessageui();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		    	
		    } 
		});
		
		*/
		
		String response = scanner.nextLine();
		
		if ( response.equals( "1" ) ) {
			//frameChat.setVisible(true);
			User userToContact = null;
			System.out.println( "Who would you like to send the key to?" );
			String userName = this.sc.nextLine();
			ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
			
			for(User user: userList ){
				if(user.getUsername().equals( userName )) {
														
					userToContact = user;
					this.sendMessage( userToContact );
					//frameChat.setlblFriendName(userName+"");
				}
				else
				{
					System.out.println( "user not found" );
				}
			}

			
		}
		else if ( response.equals( "2" ) ) 
		{	
			//frameChat.setVisible(true);
			
			User userToRead = null;
			System.out.println( "Whos messages would you like to check?" );
			String userName = this.sc.nextLine();
		    
			ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
			
			for(User user: userList )
			{
				if(user.getUsername().equals( userName )) 
				{		
					userToRead = user;
					
					ArrayList<String> conversation = userToRead.getConversation();
					
					for ( String message : conversation )
					{
						System.out.println( message );
						//frameChat.setTextArea_1(userName+": "+message);
						
					}
				}
			}
			
			if ( userToRead == null )
			{
				System.out.println( "user not found" );
			}
		}
		else if ( response.equals( "3" ) ) 
		{
			this.addContact();
		}
		else if ( response.equals( "4" ) )
		{
			this.displayContacts();
		}
	}
	String messageToSend = "";
	
	private void sendMessage(User userToContact) throws Exception
	{
		
		messageToSend = "";
		System.out.println("Enter message to send: ");	
				
		//frameChat.getBtnSendMsg().addActionListener((new ActionListener() {
// 
//		 
//
//			@Override
//		    public void actionPerformed(ActionEvent e) {
//		        //your actions
//		    	try {
//					//messageToSend =frameChat.gettxtChatArea()+"";
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}		
//		    	System.out.println("workd");
//		    	
//		    }
//		}));
			
		
		while (true) {
			//messageToSend = "";
			System.out.print("WHY IS THIS HERE"); //IF NOT HERE IT BREAK
			
			 if (messageToSend.length() > 0)
			 {
				 if(this.subNode.getThisUser().getUserList().get(0).getConversation().isEmpty()) {
					 this.subNode.makeConnection(userToContact, RSAEncryption.getpublicKey("public.key").toString());
				 }
				 
//				 frameChat.setTextArea_1("You: "+ messageToSend);
//				 frameChat.settxtChatArea("");
				 this.subNode.makeConnection(userToContact, messageToSend);
				 break;
			 }
		}
		
		
	
		
		
	}

	
	//add ui instead of command line interface-----------------------------------------------------------------------
	public void addContactui() throws Exception
	{
		this.addContact();
	}
	
	public void sendMessageui() throws Exception
	{
		//frameChat.setVisible(true);
		
		User userToContact = null;
		System.out.println( "Who would you like to contact?" );
		String userName = this.sc.nextLine();
	    
		ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
		
		for(User user: userList ){
			if(user.getUsername().equals( userName )) {
													
				userToContact = user;
				this.sendMessage( userToContact );
				//frameChat.setlblFriendName(userName+"");
			}
			else
			{
				System.out.println( "user not found" );
			}
		}

		
	}
	
	public void checkMessagesui()
	{
		//frameChat.setVisible(true);
		
		User userToRead = null;
		System.out.println( "Whos messages would you like to check?" );
		String userName = this.sc.nextLine();
	    
		ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
		
		for(User user: userList )
		{
			if(user.getUsername().equals( userName )) 
			{		
				userToRead = user;
				
				ArrayList<String> conversation = userToRead.getConversation();
				
				for ( String message : conversation )
				{
					System.out.println( message );
					//frameChat.setTextArea_1(userName+": "+message);
					
				}
			}
		}
	}
	
	public void displayContactsUI()
	{
		this.displayContacts();
	}
	
	
	
	//-----------------------------------------------------------------------^
	
	
	
	
	

	public void displayMessages ( User userToDisplayMessages )
	{
		
	}
	
	public void displayContacts()
	{
		ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
		
		for(User user: userList ){
			System.out.println(user.getUsername());
		}
	}
	
	public void addContact() throws Exception //throws Exception 
	{
		
		User superNode = new User();
		Scanner scanner = new Scanner ( System.in );
		// get from database
		String superNodeIPAddress = ""; // dbManager.getIpAddress
		String superNodePortNumber = "";
		
		System.out.println( "enter UUID to contact" );
		String contactUUID = this.sc.nextLine();
		
		String messageToSend = String.format( "QUERY:%s:%s:%s:%s" , this.subNode.getThisUser().getIP() ,
				this.subNode.getThisUser().getUUID() , contactUUID , this.subNode.getThisUser().getPort() );
		// QUERY:UUID:UUIDToQuery:ipv4:port
		
		//TODO get from database
		superNode.setIP( "" );
		superNode.setPort( 0 );
		
		
		this.subNode.makeConnection( superNode , messageToSend );
		
		User contact = new User();
		
		
		contact.setUsername( this.subNode.getThisUser().getUserList().get( 0 ).getUsername() );
		contact.setIP( this.subNode.getThisUser().getUserList().get( 0 ).getIP() );
		contact.setUUID( this.subNode.getThisUser().getUserList().get( 0 ).getUUID() );
		contact.setPort( this.subNode.getThisUser().getUserList().get( 0 ).getPort() );
		contact.setPublicKey(this.subNode.getThisUser().getUserList().get(0).getPublicKey());
		contact.setSecretKey(this.subNode.getThisUser().getUserList().get(0).getSecretKey());
			
			System.out.println(contact.getSecretKey());
		
			/*//System.out.println("Enter the username:");
			String contactUsername = this.sc.nextLine();
			
			//System.out.println("Enter the IP address: ");
			String contactIP = this.sc.nextLine();
			
			//System.out.println("Enter the UUID ");
			String contactUUID = this.sc.nextLine();
			
			//System.out.println("Enter the port:  ");
			int contactPort = this.sc.nextInt();

			contact.setUsername(contactUsername);
			contact.setIP(contactIP);
			contact.setUUID(contactUUID);
			//contact.setPort(contactPort);*/
		
			//works but its not int sooooooo???
			//System.out.println(this.subNode.getThisUser().getUserList().get( 0 ).getUUID());
			//add contact to the database
			/*DatabaseConnection.UserInsert("67" , 
					this.subNode.getThisUser().getUserList().get( 0 ).getUsername()+"", 
					this.subNode.getThisUser().getUserList().get( 0 ).getIP()+"");
			
			*/
			
			
		
		this.subNode.getThisUser().addUser( contact );
		this.subNode.getThisUser().addConversation( contact );
		
		//Exception e = new Exception();
		//throw e;
		
	}
}


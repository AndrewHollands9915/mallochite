package mallochite.models.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mallochite.database.DatabaseConnection;
import mallochite.encryption.RSAEncryption;
import mallochite.models.classes.nodes.SubNode;
import mallochite.ui.FrameAddMember;
import mallochite.ui.FrameLoginChat;
import mallochite.ui.FrameRegistration;
import mallochite.ui.FrameUserChat;
import java.net.InetAddress;

public class ChatManager
{
	private Socket socket;
	private SubNode subNode;
	private Scanner sc = new Scanner(System.in);
	FrameUserChat frameChat = new FrameUserChat();
	private static InetAddress inetAddress;
	
	
	DefaultListModel demoList = new DefaultListModel();
    JList<String> userlist = new JList<>( demoList );
    String user1;
    FrameUserChat frame ;
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
		
		
		FrameLoginChat frame2 = new FrameLoginChat();
		frame2.setVisible(true);	
		
		FrameRegistration FrameRegistration = new FrameRegistration();
		FrameRegistration.setVisible(false); //The register functions are in the method it is created
		//frame2.getOperation();
		
	

		//The chat window itself
		frame = new FrameUserChat();
		frame.setVisible(false);
		frame.getOperation();
		
		//the add member window to add users
		FrameAddMember frameAdd = new FrameAddMember();
		//frameAdd.setVisible(true);
		JButton test = new JButton();
		
		//close the register
		JButton btuSignup = new JButton();
		btuSignup = FrameRegistration.getlblConnect();
		btuSignup.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("signup button selected");
		    	
		  
		    	FrameRegistration.setVisible(false);
		    	
		    	
		    	frame.setVisible(true);
		    } 
		});
		
		
		//login method. Check for correct input. If its correct call normal function if not call register
		JButton btuRegister = new JButton();
		btuRegister = frame2.getlblRegister();
		btuRegister.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("Register button selected");
		    	
		  
		    	FrameRegistration.setVisible(true);
		    	
		    	frame2.setVisible(false);
		    	//frame.setVisible(true);
		    } 
		});
		
		//Connect button selected major testing!!!
		JButton btuRegisterConnect = new JButton();
		btuRegisterConnect = frame2.getBtnConnect();
		btuRegisterConnect.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("Register button selecteddskfjndsaijfdsiuodfsjhdf");
		    	
		  frame2.getLogin(); //check and validates the login
		  
		  if(frame2.continueNow())
		  {
			  System.out.println("Continue");
			  frame.setVisible(true);
		  }
		  
		    } 
		});
		
		
		

	
		
		
		//send message the + icon
		JButton btu1 = new JButton();
		btu1 = frame.getbtnAddNew();
		btu1.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("please");
		    	frameAdd.setVisible(true);
		    	displayContactsUI();
		    	
		    } 
		});
		
		
		//add contact from frameAddMember //add user button
				test = frameAdd.getPnlBtnAddNew_1();
		test.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("Add button selected");
		    	System.out.println(frameAdd.getTxtUUID());
		    	System.out.println(frameAdd.gettxtUserName());
		    	//System.out.println(frameAdd.gettxtIPAddress());
		    	String ipAdd = "10.20.300.412"; //use find ip method
		    	
		    	//add user to the database
		    	DatabaseConnection.UserInsert(frameAdd.getTxtUUID(), 
		    			frameAdd.gettxtUserName(), 
		    			//frameAdd.gettxtIPAddress()
		    			ipAdd
						);
		    	frame.getOperation();
		    	frameAdd.clearItems();
		    	
		    	
		    	//addContactui(); //----------------------------------------------------------------------------------------------------------
		    	
		    	//hide after use
		    	frameAdd.setVisible(false);
		    } 
		});
		
		userlist = frame.getUserList1(); //jlist onclick.....................................................
		
		
		
        userlist.addMouseListener(new MouseAdapter(){
              @Override
              public void mouseClicked(MouseEvent e) {

                  user1 = frame.getUserList();
                  System.out.println("User:" +user1);
           
                  
                  
                  
                  
                  try {
					addContactui(user1); //add to connect list
					
					
					sendMessageui(user1);	
					
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                            
              }                            
                        
        });
			
		//the arrow button
		//JButton btuSend = new JButton();
		//btuSend = frame.getBtnSendMsg();
		/*btuSend.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("please sfjnfibdsuibesuigds");	    			    	
		    	try {
					//sendMessageui();
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 		    	
		    } 
		});*/
		
		
		
		//wow the old ver. Still working in my heart
		String response = scanner.nextLine();
		
		
		if ( response.equals( "1" ) ) {
			//frameChat.setVisible(true);
			User userToContact = null;
			System.out.println( "Who would you like to send the message" );
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
			this.addContact("why");
			this.addContact("whwhyy");
		
		}
		else if ( response.equals( "4" ) )
		{
			this.displayContacts();
		}
	}
	
	String messageToSend = "";
	boolean send = false;
	
	//send message ui----------------------------------------------------------------------------------------------------------------------------------------------
	private void sendMessage(User userToContact) throws Exception
	{
		JButton btuSend = new JButton();
		btuSend = frame.getBtnSendMsg();
		btuSend.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	System.out.println("arrow selected");	    			    	
		    	
					//sendMessageui();
		    	JTextArea j1 = new JTextArea();
		    	
		    	 j1 = frame.getmessageDisplay();
		    	 messageToSend = j1.getText();
				send= true;
				//messageToSend = frame.getMe
		    		
				try {
					sendPls(userToContact);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//add to the database when send	
				//messageInsert(String text, String Date, int sent, int ReadReciept, int ContactFK, int ContactOwner) {
				DatabaseConnection.messageInsert(messageToSend, "09-30-2021 10:30:54", 1, 1, 3, 1);
				frame.updateList(); //buggy sometimes shows messages from other users!
				//add time here
		    } 
		});
		System.out.println("Enter message to send: ");							
	}
	
	private void sendPls(User userToContact) throws Exception{
		
		 System.out.println("Being sent: "+messageToSend);	
		 this.subNode.makeConnection(userToContact, messageToSend);
		
	}
	

		
	//redo
	public void sendMessageui(String userIn) throws Exception
	{
		User userToContact = null;
		System.out.println( "Who would you like to send the message" );
		String userName = userIn;
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
	
	//add ui instead of command line interface-----------------------------------------------------------------------
	public void addContactui(String contact) throws Exception
	{
		this.addContact(contact);
	
	}
	
	
	public void checkMessagesui()
	{
		frameChat.setVisible(true);
		
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
	//
	public void addContact(String UserName) throws Exception //throws Exception 
	{
		inetAddress = InetAddress.getLocalHost();
		
		// contact
				User contact = new User();
				contact.setUsername( UserName );
				contact.setIP( inetAddress.getHostAddress() );
				contact.setPort(22222);
				contact.setUUID( "asdf-123" ); //get form database
				contact.setPublicKey(RSAEncryption.getpublicKey("public.key"));
		
		/*User contact = new User();
		contact.setUsername( this.subNode.getThisUser().getUserList().get( 0 ).getUsername() );
		contact.setIP( this.subNode.getThisUser().getUserList().get( 0 ).getIP() );
		contact.setUUID( this.subNode.getThisUser().getUserList().get( 0 ).getUUID() );
		contact.setPort( this.subNode.getThisUser().getUserList().get( 0 ).getPort() );
		contact.setPublicKey(this.subNode.getThisUser().getUserList().get(0).getPublicKey());
		contact.setSecretKey(this.subNode.getThisUser().getUserList().get(0).getSecretKey());*/
			
				
		
		this.subNode.getThisUser().addUser( contact );
		this.subNode.getThisUser().addConversation( contact );
		
		
		
			
	}
}


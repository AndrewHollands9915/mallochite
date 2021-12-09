package mallochite.models.classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mallochite.database.DatabaseConnection;
import mallochite.encryption.AESEncryption;
import mallochite.encryption.EncryptionMain;
import mallochite.encryption.RSAEncryption;
import mallochite.encryption.SecretKeyGenerator;
import mallochite.models.classes.nodes.SubNode;
import mallochite.ui.FrameAddMember;
import mallochite.ui.FrameLoginChat;
import mallochite.ui.FrameRegistration;
import mallochite.ui.FrameUserChat;

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
    User contact;
    JTextArea message;
    String secret = "YouGay";
    int messages;
    
    

	
	public ChatManager( SubNode subNode )
	{
		this.subNode = subNode;
		
		
	}
	
	
	public void menu() throws Exception
	{
		Scanner scanner = new Scanner ( System.in );
		
		
		
		
		
		/*
		//have ui replace this
		System.out.println( "What would you like to do?" );
		System.out.println( "\t 0. send key" );
		System.out.println( "\t 1. send message" );
		System.out.println( "\t 2. check messages" );
		System.out.println( "\t 3. add contact" );
		System.out.println( "\t 4. list contacts" );
		*/
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
		
		
		
		
		
		JButton sendMessage = new JButton();
		sendMessage = frame.getBtnSendMsg();
		sendMessage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { 
				try {
					if(!frame.getmessageDisplay().getText().isEmpty()) {
					sendMessageui(frame.getUserList(),frame.getmessageDisplay().getText() );
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");  //change format?
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(dtf.format(now));

                    String CurrentDate = dtf.format(now);
					
					//add to the database when send	
					//messageInsert(String text, String Date, int sent, int ReadReciept, int ContactFK, int ContactOwner) {
					DatabaseConnection.messageInsert(frame.getmessageDisplay().getText() , CurrentDate, 1, 1, 1, 1);
					frame.updateList(); //buggy sometimes shows messages from other users!
					frame.getmessageDisplay().setText("");
					//add time here
					
					
					}
					else {
						frame.updateList(); 
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});

	
		
		
		//send message the + icon
		JButton btu1 = new JButton();
		btu1 = frame.getbtnAddNew();
		btu1.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	messages = DatabaseConnection.contactCount();
		    	System.out.println(messages);
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
		    	
		        try {
					addContactui(frameAdd.gettxtUserName());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
		    	//add user to the database
		    	DatabaseConnection.UserInsert(frameAdd.getTxtUUID(), 
		    			frameAdd.gettxtUserName(), 
		    			//frameAdd.gettxtIPAddress()
		    			contact.getIP(),
		    			contact.getPort(),
		    			messages+1
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
                  
                  
           
                            
              }                            
                        
        });
        
        
		
		
		String response = scanner.nextLine();
		
		if ( response.equals( "1" ) ) {
			frameChat.setVisible(true);
			User userToContact = null;
			System.out.println( "Who would you like to send the message" );
			String userName = this.sc.nextLine();
			ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
			
			for(User user: userList ){
				if(user.getUsername().equals( userName )) {
														
					userToContact = user;
					this.sendMessage( userToContact );
					frameChat.setlblFriendName(userName+"");
				}
				else
				{
					System.out.println( "user not found" );
				}
			}

			
		}
		else if ( response.equals( "2" ) ) 
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
			
			if ( userToRead == null )
			{
				System.out.println( "user not found" );
			}
		}
		else if ( response.equals( "3" ) ) 
		{
			this.addContact("Mansur");
		}
		else if ( response.equals( "4" ) )
		{
			this.displayContacts();
		}
	} 
	
	
	
        
	String messageToSend = "";
	
	private void sendMessage(User userToContact) throws Exception
	{
		Scanner scanner = new Scanner( System.in );
		messageToSend = "";
		System.out.println("Enter message to send: ");
		messageToSend = scanner.nextLine();
				
		/*frameChat.getBtnSendMsg().addActionListener((new ActionListener() {

		 

			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
	    	try {
					//messageToSend =frameChat.gettxtChatArea()+"";
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
		    	System.out.println("workd");
		    	
		    }
		}));*/
			
		
		while (true) {
			
			 if (messageToSend.length() > 0)
			 {
//				 if(this.subNode.getThisUser().getUserList().get(0).getConversation().isEmpty()) {
//					 this.subNode.makeConnection(userToContact, RSAEncryption.getpublicKey("public.key").toString());
//				 }
				 
//				 frameChat.setTextArea_1("You: "+ messageToSend);
//				 frameChat.settxtChatArea("");
				 this.subNode.makeConnection(userToContact, "Gay");
				 break;
			 }
		}
		
		
	
		
		
	}

	
	//add ui instead of command line interface-----------------------------------------------------------------------
	public void addContactui(String contact) throws Exception
	{
		this.addContact(contact);
	}
	
	public void sendMessageui(String userIn, String messageToSend) throws Exception
	{
		User userToContact = null;
		//System.out.println( "Who would you like to send the message" );
		
		ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
		messageToSend = MallochiteMessageManager.formatMessageToSend(DatabaseConnection.getUserName(), messageToSend);
		for(User user: userList ){
			if(user.getUsername().equals( userIn )) {
													
				userToContact = user;
				this.subNode.makeConnection(userToContact, messageToSend);
				
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
	
	public void addContact(String UserName) throws Exception //throws Exception 
	{
		inetAddress = InetAddress.getLocalHost();
		
		// contact
			    contact = new User();
				contact.setUsername( UserName );
				contact.setIP( "192.168.2.140" );
				contact.setPort(42424);
				contact.setUUID( UserName ); //get form database
				//contact.setPublicKey(RSAEncryption.getpublicKey("public.key"));
				
		
		/*User contact = new User();
		contact.setUsername( this.subNode.getThisUser().getUserList().get( 0 ).getUsername() );
		contact.setIP( this.subNode.getThisUser().getUserList().get( 0 ).getIP() );
		contact.setUUID( this.subNode.getThisUser().getUserList().get( 0 ).getUUID() );
		contact.setPort( this.subNode.getThisUser().getUserList().get( 0 ).getPort() );
		contact.setPublicKey(this.subNode.getThisUser().getUserList().get(0).getPublicKey());
		contact.setSecretKey(this.subNode.getThisUser().getUserList().get(0).getSecretKey());*/
		
		this.subNode.getThisUser().getUserList().add(contact);		
		this.subNode.getThisUser().addConversation( contact );
		System.out.println(this.subNode.getThisUser().getUserList());
						
	}
	
	
	
	public JTextArea printMessages() {
		String message;
		JTextArea messageFrame;
		
	    message =  "hola boi";
	    
	    messageFrame = new JTextArea();
	    
	    messageFrame.setBackground(new Color(155,247,192));
        messageFrame.setBorder(BorderFactory.createLineBorder(Color.decode("#2C6791")));
        messageFrame.setWrapStyleWord(true);
        messageFrame.setLineWrap(true);
        
        messageFrame.setText(message); //take the final message
		//messageFrame.setMaximumSize(new Dimension(250,(20 * (message.getLineCount()+1))));  
		messageFrame.setEditable(false);
	    
		return messageFrame;
		
	}


	
	
	
	
	
	
}


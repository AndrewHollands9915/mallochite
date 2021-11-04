package mallochite.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat.Style;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import mallochite.database.*;
import mallochite.models.classes.User;
import mallochite.models.classes.ChatManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mallochite.encryption.RSAEncryption;
import mallochite.models.classes.nodes.SubNode;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JList;


public class FrameUserChat extends JFrame {

	public JPanel contentPane;
	JTextArea txtChatArea;
	JTextArea textArea_2;
	JButton btnSendMsg;
	JButton btnAddNew;
	JLabel lblFriendName;
	JScrollPane scrollFrame;
	JLabel lblNewLabel_1;
	JTextPane textPane;
    public SubNode subNode;
    //JTextField message;
    JTextArea message;
   // JPanel panel;
    JTextArea messageDisplay;
    JPanel panel;
	
	DefaultListModel demoList = new DefaultListModel();
	JList<String> list = new JList<>( demoList );
	
	boolean test = false;
	/**
	 * Launch the application.
	 */
	JPanel userDisplay = new JPanel();
	FrameJScrollPaneDemo frame = new FrameJScrollPaneDemo();
	
	
	public static void newUserChatScreenDemo(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					FrameJScrollPaneDemo frame = new FrameJScrollPaneDemo();
					frame.setLocationRelativeTo(null);
				
					frame.setVisible(true);
					frame.getOperation();
					
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public FrameUserChat() 
	{
		test = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = Toolkit.getDefaultToolkit().getImage("src/res/image2.jpg");
		setIconImage(icon);
		setTitle("Mallochite");    
		setBounds(500, 500, 830, 650);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.setBackground(new Color(0, 102, 51));
		contentPane.setBorder(BorderFactory.createTitledBorder(null, "Mallochite", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("monospaced",Font.BOLD,20), Color.WHITE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		

		
		userDisplay.setBackground(new Color(60, 179, 113));
		userDisplay.setFont(new Font("Serif", Font.BOLD, 18));
		//userDisplay.setLineWrap(true);
		//userDisplay.setWrapStyleWord(true);
		userDisplay.setForeground(Color.BLUE);
		userDisplay.setAutoscrolls(true);
		
		//userDisplay.setEditable(false); // set textArea(userDisplay) non-editable
		
		JScrollPane scrollUser = new JScrollPane(userDisplay);
		scrollUser.setBounds(10, 50, 200, 450);
		scrollUser.setPreferredSize(new Dimension(800,300));
		scrollUser.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollUser);
		list.setBackground(new Color(60, 179, 113));
		
		
		
		
		
		
		
		//list = new JList();
		//list.setBounds(10, 50, 200, 450);
		list.setFixedCellWidth(170);
		scrollUser.setColumnHeaderView(list);
		userDisplay.add(list);
		
		
		//JPanel panel = new JPanel();
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(new Color(88,238,148));
    	panel.setBounds(225, 50, 550, 450);
       // contentPane.add(panel);
        
        JScrollPane scrollChat = new JScrollPane(panel);
		scrollChat.setBounds(225, 50, 550, 450);
		scrollChat.setPreferredSize(new Dimension(800,300));
		scrollChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.validate();
		contentPane.add(scrollChat);
		
	   
		list.addMouseListener(new MouseAdapter(){
			
	          @Override
	          public void mouseClicked(MouseEvent e) {
	        	
	        	 
	              
	              
	              String s = (String)list.getSelectedValue();
	              
	              panel.removeAll();
	        	  panel.validate();
	        	  panel.repaint();
	              
	             // lblNewLabel_1.setText("Talking to " + s);
	        	  lblNewLabel_1.setText(s);
	              getMessages(s, panel);
	              	                           
	              //sendMessageToUsers(s);
	              //ChatManager.sendMessage(s);
	              //System.out.println("Value Selected: " + s.toString());
	              
	          }
	    });
		//JTextArea messageDisplay = new JTextArea();
	    messageDisplay = new JTextArea();
		messageDisplay.setText("Type your message here...");
	
		messageDisplay.setBackground(new Color(60, 179, 113));
		messageDisplay.setAutoscrolls(true);
		//messageDisplay.setBounds(225, 510, 450, 75);
		//contentPane.add(messageDisplay);
		messageDisplay.addMouseListener(new MouseAdapter(){
	          @Override
	          public void mouseClicked(MouseEvent e) {
	        	 placeHolder(messageDisplay, "Type your message here...");
	          }
	          });
		
		

		JScrollPane scrollMessage = new JScrollPane(messageDisplay);
		scrollMessage.setBounds(225, 520, 450, 50);
		scrollMessage.setPreferredSize(new Dimension(800,300));
		scrollMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				
		contentPane.add(scrollMessage);
		
		//
		
		btnSendMsg = new JButton();
		btnSendMsg.setBounds(683, 520, 89, 50);
		btnSendMsg.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/res/arrow.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		contentPane.add(btnSendMsg);
		btnSendMsg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  String s = (String)list.getSelectedValue();
				getMessages(s, panel);		       
			      
			}
		});
		
	    btnAddNew = new JButton("+");
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddNew.setBounds(10, 531, 47, 39);
		
		btnAddNew.setToolTipText("Click to add new contact..");
		btnAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
			     //  FrameAddMember.newAddMemberScreen(null);			       
			      
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAddNew.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnAddNew.setForeground(Color.BLACK);
			}
		});
		btnAddNew.setBackground(new Color(60, 179, 113));
		btnAddNew.setBorder(new TitledBorder(new CompoundBorder(new LineBorder(new Color(171, 173, 179)), new EmptyBorder(2, 2, 2, 2)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnAddNew);
							
		
		JLabel lblNewLabel = new JLabel("Contacts");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 23, 200, 26);
		contentPane.add(lblNewLabel);
		
	    lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setBackground(new Color(0, 204, 102));
	    lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(16, 105, 40 ));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(225, 27, 550, 22);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		
		//huh
		btnAddNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String message = messageDisplay.getText();
				//textPane.getStyledDocument(); idkl what this is
			}
		});
		
	}
	
	 public void addColoredText(JTextPane pane, String text, Color color) {
	        StyledDocument doc = pane.getStyledDocument();

	        javax.swing.text.Style style = pane.addStyle("Color Style", null);
	        StyleConstants.setForeground(style, color);
	        try {
	            doc.insertString(doc.getLength(), text, style);
	        } 
	        catch (BadLocationException e) {
	            e.printStackTrace();
	        }           
	    }
	
	public void getOperation()
	{			  		 
		//Connection con = ConnectToUsersDB.getConnection();
		Connection con = DatabaseCrud.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        demoList.clear(); //clear the list so it does not repeat
        
		try
		{
			String sql = "SELECT UserName from Contact";
			
		    ps = con.prepareStatement(sql);
            rs = ps.executeQuery();			
			while(rs.next())
            {           	   
				String UserName = rs.getString("UserName");
				demoList.addElement(UserName);
				//userDisplay.append(UserName +"\n");			
				
            }
			 //list = new JList(demoList);
			 rs.close();
             ps.close();
			
			//JOptionPane.showMessageDialog(null, "Retrieved data succesfully.","Active UserName Retrieved",
				//	JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
					JOptionPane.ERROR_MESSAGE);
		}			
	} 
	
	
	public void getMessages(String userName, JPanel p) {
		//Connection con = ConnectToUsersDB.getConnection();
		Connection con = DatabaseCrud.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean test1 = false;
        try {
        	String sql = "SELECT * FROM Message WHERE ContactFK = (SELECT Messages  FROM Contact WHERE UserName = "+ "'" + userName + "'"+")";
        	
        	ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			while(rs.next())
            {         
				
				
				
				//get the message content from the database
				String message = rs.getString("text");
				//addColoredText(this.message, message + "\n", Color.BLUE);
				
				//get the owner of the message Add more code here later for 
				String ownerName = ""; 
				
				//get the owner of the message. 0 is true for the contact, 1 is us the user
				int owner = rs.getInt("ContactOwner");
				
				//get the date for each message
				String date = rs.getString("Date");            
				//System.out.println(date);	      
				
				//this.message = new JTextField();
				this.message = new JTextArea(); //changed to area to made size for the 2nd line if needed
	        	  //this.message.setPreferredSize(new Dimension(300,25));
	        	  //this.message.setAlignmentX(100);
				//this.message.setPreferredSize(new Dimension(250,10));  
				
	        	  //check the value of the owner to display
	        	  if (owner == 1){ //for some reason the values are fliped
	        		  this.message.setAlignmentX(100);
	        		  ownerName = "Admin";
	        	  } else if (owner == 0) {
	        		  this.message.setAlignmentX(0);
	        		  ownerName = userName;
	        	  } 
	        	  //use date and time values my testing
	        	  //String sDate1="31-12-1998 10:30:54";  //debug date	        	  	        	
	        	  Date date1 = null;
				try {
					date1 = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").parse(date);
					//System.out.println(date+"\t"+date1);  
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        	    
	        	 
	        	 
	             this.message.setBackground(new Color(155,247,192));
	             this.message.setBorder(BorderFactory.createLineBorder(Color.decode("#2C6791")));
	             this.message.setWrapStyleWord(true);
	             this.message.setLineWrap(true);
	  
	             
				
				//display the message and contents of the message 
	             //Right now its really rough. With the length. Maybe space this out	             
				//this.message.setText(ownerName+": " + message+ " date: "+date1);
				
				String messageToAppend = ownerName+": " + message+ " \n                   "+date1;
				int messageLength = messageToAppend.length();
				
				this.message.setText(messageToAppend); //take the final message
				this.message.setMaximumSize(new Dimension(250,(20 * (this.message.getLineCount()+1))));  
				this.message.setEditable(false);
				
				 p.add(this.message);
	             p.add(Box.createRigidArea(new Dimension(0, 15)));
	              
			
				
            }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                             
        
	}	
	
	public void getOnlineUsers(String UserName)
	{
		//Connection con = ConnectToUsersDB.getConnection();
		Connection con = DatabaseCrud.connect();
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            String query = "SELECT (count(*) > 0) as found FROM Contact WHERE UserName LIKE ?";
            ps = con.prepareStatement(query);
            
            
            ps.setString(1, UserName);

            try (ResultSet rs = ps.executeQuery()) {
                
                if (rs.next()) {
                    boolean found = rs.getBoolean(1); // "found" column
                    if (found) {
                        // You have rows
                    } else {
                        // You have no rows
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
		
	public JTextArea getmessageDisplay()
	{
		return messageDisplay;
	}
	
	public JButton getBtnSendMsg() {
		return btnSendMsg;
	}
	
	public JButton getbtnAddNew() {
		return btnAddNew;
	}


	public void setBtnSendMsg(JButton btnSendMsg) {
		this.btnSendMsg = btnSendMsg;
	}

	public String gettxtChatArea() {
		return txtChatArea.getText();
	}
	
	public void settxtChatArea(String imp) {
        txtChatArea.setText(imp);
    }
	
	public boolean getTest()
	{
		return test;
	}
	
	
	public String getTextPane() {
		return textPane.getText();
	}
	
	public void setTextPane(String imp) {
		textPane.setText(getTextPane()+imp+"\n");
	}
	
	public void setlblFriendName(String imp) {
        lblFriendName.setText("Talking with a Friend "+imp);
    }
	
	public void placeHolder(JTextArea a, String text) 
	{
		
		if(a.getText().equals(text))
		  a.setText("");
	        	  
	}
	public JList getUserList1()
    {
        return list;

    }

public void updateList(){ //update the list i hope
	  String s = (String)list.getSelectedValue(); // a bit buggy with showing the messages fix later-------------------------------------------------------------
		getMessages(s, panel);	
}

    public String getUserList() {
        return lblNewLabel_1.getText() ;

    }
	
	//return the jlist
	public JList getJlist()
	{
		
		
		return list;
		
	}
	

}
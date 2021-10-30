package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat.Style;
import java.util.ArrayList;

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

import mallochite.DatabaseConnection.*;
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
    JTextField message;
   // JPanel panel;
	
	
	DefaultListModel demoList = new DefaultListModel();
	JList<String> list = new JList<>( demoList );
	//ChatManager chatman = new ChatManager();
	
	boolean test = false;
	/**
	 * Launch the application.
	 */
	JTextArea userDisplay = new JTextArea();
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
		setBounds(500, 500, 830, 650);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.setBackground(new Color(0, 102, 51));
		contentPane.setBorder(BorderFactory.createTitledBorder(null, "Chat Area", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("monospaced",Font.BOLD,20), Color.WHITE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		userDisplay.setBackground(new Color(60, 179, 113));
		userDisplay.setFont(new Font("Serif", Font.BOLD, 18));
		userDisplay.setLineWrap(true);
		userDisplay.setWrapStyleWord(true);
		userDisplay.setForeground(Color.BLUE);
		userDisplay.setAutoscrolls(true);
		userDisplay.setEditable(false); // set textArea(userDisplay) non-editable
		
		JScrollPane scrollUser = new JScrollPane(userDisplay);
		scrollUser.setBounds(10, 50, 200, 450);
		scrollUser.setPreferredSize(new Dimension(800,300));
		scrollUser.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollUser);
		list.setBackground(new Color(0, 204, 102));
		
		
		
		
		
		
		
		//list = new JList();
		list.setBounds(10, 50, 200, 450);
		scrollUser.setColumnHeaderView(list);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(new Color(88,238,148));
    	panel.setBounds(225, 50, 550, 450);
       // contentPane.add(panel);
        
        JScrollPane scrollChat = new JScrollPane(panel);
		scrollChat.setBounds(225, 50, 550, 450);
		scrollChat.setPreferredSize(new Dimension(800,300));
		scrollChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.validate();
		contentPane.add(scrollChat);
		
		
		
		
	
	   
		list.addMouseListener(new MouseAdapter(){
			
	          @Override
	          public void mouseClicked(MouseEvent e) {
	        	
	        	  panel.removeAll();
	        	  panel.revalidate();
	        	  panel.repaint();
	              int index = list.getSelectedIndex();
	              
	              String s = (String)list.getSelectedValue();
	              lblNewLabel_1.setText(s);
	              getMessages(s, panel);
	              
	              
	              
	              
	      		
	              
	             
	              //sendMessageToUsers(s);
	              //ChatManager.sendMessage(s);
	              //System.out.println("Value Selected: " + s.toString());
	              
	          }
	    });
		
		JTextArea messageDisplay = new JTextArea();
		messageDisplay.setText("Type your message here...");
		messageDisplay.setBackground(new Color(60, 179, 113));
		messageDisplay.setAutoscrolls(true);
		//messageDisplay.setBounds(225, 510, 450, 75);
		//contentPane.add(messageDisplay);

		JScrollPane scrollMessage = new JScrollPane(messageDisplay);
		scrollMessage.setBounds(225, 520, 450, 50);
		scrollMessage.setPreferredSize(new Dimension(800,300));
		scrollMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollMessage);
		
		btnSendMsg = new JButton();
		btnSendMsg.setBounds(683, 520, 89, 50);
		btnSendMsg.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/res/arrow.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		contentPane.add(btnSendMsg);
		
		
		
	    btnAddNew = new JButton("+");
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddNew.setBounds(10, 531, 47, 39);
		
		btnAddNew.setToolTipText("Click to add new contact..");
		btnAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
			       FrameAddMember.newAddMemberScreen(null);			       
			      
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
		
		//textPane = new JTextPane();
		//textPane.setForeground(new Color(255, 0, 0));
		//textPane.setBounds(1, 1, 386, 448);
		//textPane.setBackground(new Color(60, 179, 113));
		
		//textPane.setBounds(225, 50, 547, 456);
		//contentPane.add(textPane);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Users List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 23, 200, 26);
		contentPane.add(lblNewLabel);
		
	    lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setBackground(new Color(0, 204, 102));
	    lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(225, 27, 133, 22);
		contentPane.add(lblNewLabel_1);
		
		btnAddNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String message = messageDisplay.getText();
				textPane.getStyledDocument();
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
		  		 
		Connection con = ConnectToUsersDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
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
			
			JOptionPane.showMessageDialog(null, "Retrieved data succesfully.","Active UserName Retrieved",
					JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
					JOptionPane.ERROR_MESSAGE);
		}			
	} 
	
	
	public void getMessages(String userName, JPanel p) {
		Connection con = ConnectToUsersDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        try {
        	String sql = "SELECT * FROM Message WHERE ContactFK = (SELECT Messages  FROM Contact WHERE UserName = "+ "'" + userName + "'"+")";
        	
        	ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
            {           	   
				String message = rs.getString("text");
				//addColoredText(this.message, message + "\n", Color.BLUE);
				
				this.message = new JTextField();
	        	  this.message.setPreferredSize(new Dimension(300,25));
	        	  this.message.setAlignmentX(100);
	        	  this.message.setMaximumSize( this.message.getPreferredSize() );
	              this.message.setBackground(new Color(155,247,192));
	              this.message.setBorder(BorderFactory.createLineBorder(Color.decode("#2C6791")));
	             this. message.validate();
	             
				
				
				this.message.setText(" " + message);
				
				 p.add(this.message);
	              p.add(Box.createRigidArea(new Dimension(0, 15)));
	              
	             
				
				//userDisplay.append(UserName +"\n");	
				
				
            }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
        
        
        
	}
	
	
	public void getOnlineUsers(String UserName)
	{
		Connection con = ConnectToUsersDB.getConnection();
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
	
	/*
	public void sendMessageToUsers(String username)
	{
		
		User userToContact = null;
			//System.out.println( "Who would you like to contact?" );
			//String userName = this.sc.nextLine();
		    
			ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
			
			for(User user: userList ){
				if(user.getUsername().equals(username)) {
				
					
					//frameChat.setTextArea_1();
					userToContact = user;
					ChatManager.sendMessageui();
					//frameChat.setlblFriendName(userName+"");
				}
				else
				{
					System.out.println( "user not found" );
				}
      }
      
	}
	
	*/
	
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

}


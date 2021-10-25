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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import mallochite.DatabaseConnection.*;


public class FrameJScrollPaneDemo extends JFrame {

	private JPanel contentPane;
	JTextArea txtChatArea;
	JTextArea textArea_2;
	JButton btnSendMsg;
	JLabel lblFriendName;
	JScrollPane scrollFrame;
	boolean test = false;
	/**
	 * Launch the application.
	 */
	JTextArea userDisplay = new JTextArea();
	
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
	public FrameJScrollPaneDemo() 
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
		
		JTextArea chatDisplay = new JTextArea();
		chatDisplay.setBackground(new Color(60, 179, 113));
		
		//chatDisplay.setBounds(10, 10, 563, 459);
		chatDisplay.setAutoscrolls(true);
		chatDisplay.setEditable(false); // set textArea(chatDisplay) non-editable
		
		JScrollPane scrollChat = new JScrollPane(chatDisplay);
		scrollChat.setBounds(225, 50, 550, 450);
		scrollChat.setPreferredSize(new Dimension(800,300));
		scrollChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollChat);
		
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
		
		JButton btnSendMsg = new JButton();
		btnSendMsg.setBounds(683, 520, 89, 50);
		btnSendMsg.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/res/arrow.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		contentPane.add(btnSendMsg);
		
		JButton btnAddNew = new JButton("+");
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
				userDisplay.append(UserName +"\n");
				
			
            }
			
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
	
}
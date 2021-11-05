/*
 * Joseph Escober
 */

package mallochite.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import mallochite.database.DatabaseCrud;

import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;

public class FrameLoginChat extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtUserName, txtIPAddress;
	private JTextField textField;
	JButton lblRegister;
	JButton lblConnect;
	
	//check if we continue
	boolean same = false;
	
	//private JPasswordField txtPassword;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLoginChat frame = new FrameLoginChat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameLoginChat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 400, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 4));
		setContentPane(contentPane);
		//setUndecorated(true);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(42, 228, 298, 59);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBorder(null);
		txtUserName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUserName.setText("UserName");
		txtUserName.setBounds(10, 10, 278, 39);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(42, 319, 298, 59);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtIPAddress = new JTextField();
		txtIPAddress.setBorder(null);
		txtIPAddress.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtIPAddress.setText("Password");
		txtIPAddress.setBounds(10, 10, 238, 46);
		panel_1.add(txtIPAddress);
		
		//JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setBackground(Color.WHITE);
		//lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/res/padlock.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		//lblNewLabel.setBounds(251, 10, 37, 46);
		//panel_1.add(lblNewLabel);
		
		
		JPanel pnlBtnLogin = new JPanel();
		pnlBtnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnlBtnLogin.setBackground(new Color(60, 179, 113));
		pnlBtnLogin.setBounds(42, 420, 201, 59);
		contentPane.add(pnlBtnLogin);
		pnlBtnLogin.setLayout(null);
		
		//JLabel lblConnect = new JLabel("Connect");
	    lblConnect = new JButton("Connect");
		
		lblConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//FrameLoginChat.this.dispose();			 
			       //FrameJScrollPaneDemo.newUserChatScreenDemo(null);	//OLD ON CLICK MOVED TO CHAT MANAGER
				
				System.out.println("clickdfsdsfed");
				//getLogin();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblConnect.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblConnect.setForeground(Color.BLACK);
			}
		});
		lblConnect.setForeground(Color.WHITE);
		lblConnect.setFont(new Font("Arial", Font.BOLD, 18));
		lblConnect.setBounds(59, 10, 111, 39);
		pnlBtnLogin.add(lblConnect);
		
		JLabel lblNewLabel_2 = new JLabel("allochite");
		lblNewLabel_2.setLocation(new Point(26, 0));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 34));
		lblNewLabel_2.setBounds(139, 33, 213, 43);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(60, 179, 113));
		panel_2.setBounds(0, 86, 400, 20);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(72, 10, 68, 66);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/res/image2.jpg")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "confirmation", JOptionPane.YES_NO_OPTION) == 0)
					 FrameLoginChat.this.dispose();
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblClose.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblClose.setForeground(Color.WHITE);
			}
			
		});
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClose.setBounds(376, 0, 24, 35);
		contentPane.add(lblClose);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Arial", Font.BOLD, 24));
		lblLogin.setBounds(47, 163, 195, 35);
		contentPane.add(lblLogin);
		
		JPanel pnlBtnLogin_1 = new JPanel();
		pnlBtnLogin_1.setLayout(null);
		pnlBtnLogin_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnlBtnLogin_1.setBackground(new Color(60, 179, 113));
		pnlBtnLogin_1.setBounds(42, 498, 201, 59);
		contentPane.add(pnlBtnLogin_1);
		
	    lblRegister = new JButton("Register");
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setFont(new Font("Arial", Font.BOLD, 18));
		lblRegister.setBounds(59, 10, 111, 39);
		pnlBtnLogin_1.add(lblRegister);
		
		
		setLocationRelativeTo(null);
	}
	
	public JTextField getTxtUserName() {
		return txtUserName;
	}
	
	//return the register button so we can give register a function
	public JButton getlblRegister() {
		return lblRegister;
	}
	
	//return the connect button to connect
	public JButton getBtnConnect() {
		return lblConnect;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JTextField getTxtIPAddress() {
		return txtIPAddress;
	}

	public void setTxtIPAddress(JTextField txtIPAddress) {
		this.txtIPAddress = txtIPAddress;
	}
	
	//get the boolean to check if we should open the next chat window
	public boolean continueNow()
	{
		return same;
	}
	
	public void getLogin()
	{			
		 //boolean same = false;
		Connection con = DatabaseCrud.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
		try
		{
			String sql = "SELECT * from Registration";
			
		    ps = con.prepareStatement(sql);
            rs = ps.executeQuery();			
			while(rs.next())
            {           	   
				String UserName = rs.getString("Username");			
				String Password = rs.getString("Password");	
				
				if (txtUserName.getText().equals(UserName) && txtIPAddress.getText().equals(Password))
				{
					System.out.println("Sucessfully loged in");
					same = true;
					JOptionPane.showMessageDialog(null, "Logged In","Active UserName Retrieved",
							JOptionPane.INFORMATION_MESSAGE);
					
					//good now we can turn it off
					FrameLoginChat.this.dispose();	
				}				 
            }
			 rs.close();
             ps.close();
			           
             
		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
					JOptionPane.ERROR_MESSAGE);
		}			
	}   //ff
}
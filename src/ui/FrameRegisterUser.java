
/*
 * Joseph Escober
 */

package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import mallochite.DatabaseConnection.DatabaseCrud;

import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;

public class FrameRegisterUser extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtUserName, txtPassword;
	private JTextField textField;
	
	
	//private JPasswordField txtPassword;	

	/**
	 * Launch the application.
	 */
	public static void newRegisterMemberScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameRegisterUser frame = new FrameRegisterUser();
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
	public FrameRegisterUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 400, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new LineBorder(new Color(47, 79, 79), 4));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(52, 161, 298, 59);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBorder(null);
		txtUserName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUserName.setText("Username");
		txtUserName.setBounds(10, 10, 278, 39);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(52, 262, 298, 59);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtPassword = new JTextField();
		txtPassword.setBorder(null);
		txtPassword.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtPassword.setText("Password");
		txtPassword.setBounds(10, 10, 238, 39);
		panel_1.add(txtPassword);
		
		//JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setBackground(Color.WHITE);
		//lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/res/padlock.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		//lblNewLabel.setBounds(251, 10, 37, 46);
		//panel_1.add(lblNewLabel);
		
		
		JPanel pnlBtnRegister = new JPanel();
		pnlBtnRegister.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnlBtnRegister.setBackground(new Color(60, 179, 113));
		pnlBtnRegister.setBounds(131, 403, 137, 59);
		contentPane.add(pnlBtnRegister);
		pnlBtnRegister.setLayout(null);
		
		JLabel lblConnect = new JLabel("Register");
		lblConnect.setBounds(35, 10, 81, 39);
		pnlBtnRegister.add(lblConnect);
		
		
		lblConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameRegisterUser.this.dispose();
				String username = (String) txtUserName.getText();
				String password = (String) txtPassword.getText();
				registerUser(username, password);
			
				
			       //FrameJScrollPaneDemo.newUserChatScreenDemo(null);	
				
				System.out.println("data saved.");
				//getLogin();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblConnect.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblConnect.setForeground(Color.WHITE);
			}
		});
		lblConnect.setForeground(Color.WHITE);
		lblConnect.setFont(new Font("Arial", Font.BOLD, 18));
		
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
					 FrameRegisterUser.this.dispose();
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
		
		JLabel lblLogin = new JLabel("Registration");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogin.setBounds(119, 30, 195, 35);
		contentPane.add(lblLogin);
		
		
		setLocationRelativeTo(null);
	}
	
	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtIPAddress(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}
	
	public void getLogin()
	{			
		boolean same = false;
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
				
				if (txtUserName.getText().equals(UserName) && txtPassword.getText().equals(Password))
				{
					System.out.println("Sucessfully loged in");
					same = true;
				}
				else 
					
				  System.out.println(UserName+Password + " is incorrect");
            }
			 rs.close();
             ps.close();
			
           
             
			JOptionPane.showMessageDialog(null, "Retrieved login data succesfully.","Active UserName Retrieved",
					JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
					JOptionPane.ERROR_MESSAGE);
		}			
	}   
	
	
	public void registerUser(String Username, String Password)
	{			
        String sql = "INSERT INTO Registration (Username, Password) VALUES(?,?)";
        
		try
		{			
				         
            	Connection con = DatabaseCrud.connect();
	            PreparedStatement ps = null;		         
                
                ps = con.prepareStatement(sql);		  
                          
                    ps.setString(1, Username);
                    ps.setString(2, Password);
                    ps.executeUpdate();  
                
                    System.out.println("User data are successfully saved!:\n");	                                          
                
        }
		catch (SQLException e)
		{
              System.out.println(e.toString());
        } 		            
			
           
           
	}   
}
package mallochite.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mallochite.database.DatabaseCrud;

public class FrameRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName, txtIPPassword;
	private JTextField textField;
	JButton lblConnect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameRegistration frame = new FrameRegistration();
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
	public FrameRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
		
		txtIPPassword = new JTextField();
		txtIPPassword.setBorder(null);
		txtIPPassword.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtIPPassword.setText("Password");
		txtIPPassword.setBounds(10, 10, 238, 46);
		panel_1.add(txtIPPassword);
		
		//JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setBackground(Color.WHITE);
		//lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/res/padlock.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		//lblNewLabel.setBounds(251, 10, 37, 46);
		//panel_1.add(lblNewLabel);
		
		
		JPanel pnlBtnLogin = new JPanel();
		pnlBtnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnlBtnLogin.setBackground(new Color(60, 179, 113));
		pnlBtnLogin.setBounds(91, 495, 201, 59);
		contentPane.add(pnlBtnLogin);
		pnlBtnLogin.setLayout(null);
		
		//change toa  button??
		//JLabel lblConnect = new JLabel("Sign up");
		lblConnect = new JButton("Sign up");
		
		lblConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//FrameLoginChat.this.dispose();			 
			    //   FrameJScrollPaneDemo.newUserChatScreenDemo(null);			
				System.out.println("sign up selected");
				
				//call registration method add the user into the database
				//RegisterUserInsert("no", "please");
				RegisterUserInsert(getTxtUserName(), getTxtPassword());
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
				//if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "confirmation", JOptionPane.YES_NO_OPTION) == 0)
					// FrameLoginChat.this.dispose();
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
		
		JLabel lblLogin = new JLabel("Register");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Arial", Font.BOLD, 24));
		lblLogin.setBounds(47, 163, 195, 35);
		contentPane.add(lblLogin);
		
		
		setLocationRelativeTo(null);
		
		
	}
	
	//get the items
	public String getTxtUserName() {
		return txtUserName.getText();
	}

	public String getTxtPassword() {
		return txtIPPassword.getText();
	}

	//what connect please retuinr the button!
	public JButton getlblConnect() {
		return lblConnect;
	}
	
	/*public JTextField getTxtIPAddress() {
		return txtIPAddress;
	}

	public void setTxtIPAddress(JTextField txtIPAddress) {
		this.txtIPAddress = txtIPAddress;
	}*/
	
	//insert the given register into the database
	public void RegisterUserInsert(String Username, String Password)
	{			
					
			String sql = "INSERT INTO Registration(Username, Password) VALUES(?,?)";				
            
            try {
            	Connection con = DatabaseCrud.connect();
	            PreparedStatement ps = null;		         
                
                ps = con.prepareStatement(sql);		  
                          
                    ps.setString(1, Username);
                    ps.setString(2, Password);        
                    ps.executeUpdate();  
                
                    System.out.println("User registration was successfully saved! xdxdxdxdxdxd:\n");	                                          
                
            }catch (SQLException e) {
                System.out.println(e.toString());
            } 		            
		}

}

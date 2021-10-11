
/*
 * Joseph Escober
 */

package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class FrameAddMember extends JFrame {
	private JPanel contentPane;
	private JTextField txtUUID, txtUserName, IPAddress;
	private JTextField txtIPAddress;
	
	
	/**
	 * Launch the application.
	 */
	public static void newAddMemberScreen (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAddMember frame = new FrameAddMember();
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
	public FrameAddMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 400, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(50, 105, 50), new Color(50, 105, 50), null, null));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(60, 179, 113));
		panel_2.setBounds(0, 84, 400, 20);
		contentPane.add(panel_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 3));
		panel.setBounds(65, 164, 264, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUUID = new JTextField();
		txtUUID.setBorder(null);
		txtUUID.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUUID.setText("UUID");
		txtUUID.setBounds(10, 10, 244, 33);
		panel.add(txtUUID);
		txtUUID.setColumns(10);
		
		JPanel pnlBtnAddNew = new JPanel();
		pnlBtnAddNew.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		pnlBtnAddNew.setBackground(new Color(60, 179, 113));
		pnlBtnAddNew.setBounds(65, 471, 116, 53);
		contentPane.add(pnlBtnAddNew);
		pnlBtnAddNew.setLayout(null);
		
		JLabel lblConnect = new JLabel("Add");
		lblConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameAddMember.this.dispose();			
			       FrameUserChat.newUserChatScreen(null);			
				
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
		
		lblConnect.setForeground(new Color(255, 255, 255));
		lblConnect.setFont(new Font("Arial", Font.BOLD, 18));
		lblConnect.setBounds(43, 10, 52, 33);
		pnlBtnAddNew.add(lblConnect);
		
		JLabel lblAddNewMember = new JLabel("Add New Contact");
		lblAddNewMember.setForeground(Color.WHITE);
		lblAddNewMember.setFont(new Font("Arial", Font.BOLD, 30));
		lblAddNewMember.setBounds(64, 21, 287, 53);
		contentPane.add(lblAddNewMember);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "confirmation", JOptionPane.YES_NO_OPTION) == 0)
					 FrameAddMember.this.dispose();
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
		
		
		JPanel panel_UserName = new JPanel();
		panel_UserName.setBorder(new LineBorder(Color.BLACK, 3));
		panel_UserName.setBounds(65, 259, 264, 53);
		contentPane.add(panel_UserName);
		panel_UserName.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBorder(null);
		txtUserName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUserName.setText("UserName");
		txtUserName.setBounds(10, 5, 244, 38);
		panel_UserName.add(txtUserName);
		txtUserName.setColumns(10);
		
		JPanel panel_IPAddress = new JPanel();
		panel_IPAddress.setBorder(new LineBorder(Color.BLACK, 3));
		panel_IPAddress.setBounds(65, 345, 263, 53);
		contentPane.add(panel_IPAddress);
		panel_IPAddress.setLayout(null);
		
		txtIPAddress = new JTextField();
		txtIPAddress.setBorder(null);
		txtIPAddress.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtIPAddress.setText("IPAddress");
		txtIPAddress.setBounds(10, 10, 243, 33);
		panel_IPAddress.add(txtIPAddress);
		txtIPAddress.setColumns(10);
		
		JPanel pnlBtnAddNew_1 = new JPanel();
		pnlBtnAddNew_1.setLayout(null);
		pnlBtnAddNew_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		pnlBtnAddNew_1.setBackground(new Color(60, 179, 113));
		pnlBtnAddNew_1.setBounds(220, 471, 109, 53);
		contentPane.add(pnlBtnAddNew_1);
		
		JLabel lblConnect_1 = new JLabel("Cancel");
		lblConnect_1.setForeground(Color.WHITE);
		lblConnect_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblConnect_1.setBounds(26, 10, 73, 33);
		pnlBtnAddNew_1.add(lblConnect_1);
	}
	
	public String getTxtUUID() {
		return txtUUID.getText();
	}
}
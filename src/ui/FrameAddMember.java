
/*
 * Joseph Escober
 */

package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
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
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrameAddMember extends JFrame {
	private JPanel contentPane;
	private JTextField txtUUID, txtUserName, IPAddress;
	JButton pnlBtnCancel;
	JButton lblConnect;
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(60, 179, 113));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 3));
		panel.setLayout(null);
		
		txtUUID = new JTextField();
		txtUUID.setBounds(10, 10, 244, 33);
		txtUUID.setBorder(null);
		txtUUID.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUUID.setText("UUID");
		panel.add(txtUUID);
		txtUUID.setColumns(10);
		/*
		JPanel pnlBtnAddNew = new JPanel();
		pnlBtnAddNew.setBorder(null);
		//pnlBtnAddNew.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		
		pnlBtnAddNew.setBackground(new Color(60, 179, 113));
		pnlBtnAddNew.setBounds(65, 450, 116, 53);
		contentPane.add(pnlBtnAddNew);
		pnlBtnAddNew.setLayout(null);
		*/
		//change to button-----------------------------------------------------------------
		lblConnect = new JButton("Add");
		lblConnect.setOpaque(true);
		lblConnect.setBorder(null);
		lblConnect.setBackground(new Color(60, 179, 113));
		lblConnect.setForeground(Color.WHITE);
		lblConnect.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConnect.setLayout(null);
		
		lblConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//FrameAddMember.this.dispose();			
			       //FrameUserChat.newUserChatScreenDemo(null);			
				
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
		
		
		
		JLabel lblAddNewMember = new JLabel("Add New Contact");
		lblAddNewMember.setForeground(Color.WHITE);
		lblAddNewMember.setFont(new Font("Arial", Font.BOLD, 30));
		
		/*
		
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
		*/
		
		JPanel panel_UserName = new JPanel();
		panel_UserName.setBorder(new LineBorder(Color.BLACK, 3));
		panel_UserName.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBorder(null);
		txtUserName.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		txtUserName.setText("UserName");
		txtUserName.setBounds(10, 5, 244, 38);
		panel_UserName.add(txtUserName);
		txtUserName.setColumns(10);
		
		//test change to button
	    pnlBtnCancel = new JButton("Cancel");
	    pnlBtnCancel.setForeground(Color.WHITE);
	    pnlBtnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
	    pnlBtnCancel.setOpaque(true);
	    pnlBtnCancel.setBorder(null);
		//pnlBtnCancel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		pnlBtnCancel.setBackground(new Color(60, 179, 113));
		pnlBtnCancel.setLayout(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addComponent(lblAddNewMember, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblConnect, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addGap(37)
									.addComponent(pnlBtnCancel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_UserName, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))))
					.addGap(14))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblAddNewMember, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addGap(60)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(42)
					.addComponent(panel_UserName, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(81)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConnect, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
						.addComponent(pnlBtnCancel, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
					.addGap(63))
		);
		contentPane.setLayout(gl_contentPane);
		
		pnlBtnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "confirmation", JOptionPane.YES_NO_OPTION) == 0)
					 FrameAddMember.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				pnlBtnCancel.setForeground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				pnlBtnCancel.setForeground(Color.WHITE);
			}
		});
		
		/*
		JLabel lblConnect_1 = new JLabel("Cancel");
		lblConnect_1.setForeground(Color.WHITE);
		lblConnect_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblConnect_1.setBounds(26, 10, 73, 33);
		pnlBtnAddNew_1.add(lblConnect_1);
		*/
	}
	
	
	public JButton getPnlBtnAddNew_1() {
		return lblConnect;
	}
	
	//Get items to create a new user
	public String getTxtUUID() {
		return txtUUID.getText();
	}
	
	public String gettxtUserName()
	{
		return txtUserName.getText();
	}
	/*
	public String gettxtIPAddress() {
		return txtIPAddress.getText();
	}
	*/
	//clear after pressed
	public void clearItems()
	{
		txtUUID.setText("");
		txtUserName.setText("");
		//txtIPAddress.setText("");	
	}
	
}
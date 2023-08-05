package swing_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class Project_1 extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project_1 frame = new Project_1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Project_1() throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JTextField textArea = new JTextField(15);
		textArea.setMaximumSize(new Dimension(5, 22));
		textArea.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textArea.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Login");
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user = textArea.getText();
				String pass = new String(passwordField.getPassword());
				PreparedStatement pst;
			
				try {
					pst = con.prepareStatement("select password from login where username = ?");
					pst.setString(1,user);
					ResultSet rs = pst.executeQuery();
					
					if(!rs.next())
					{
						lblNewLabel_2.setText("Incorrect Password or Username!");						
					}
					else if(pass.equals(rs.getString(1)))
					{
						Selection_window sw = new Selection_window();
						sw.show();
						dispose();					
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		tglbtnNewToggleButton.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnNewToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnNewToggleButton.setForeground(new Color(0, 0, 0));
		tglbtnNewToggleButton.setBackground(new Color(204, 255, 255));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(154)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
					.addGap(146))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(236, Short.MAX_VALUE)
					.addComponent(tglbtnNewToggleButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(228))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(240)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(233))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(234)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(225))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(113)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(tglbtnNewToggleButton, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

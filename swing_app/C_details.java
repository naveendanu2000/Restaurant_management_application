package swing_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.*;

public class C_details extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_details frame = new C_details();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public C_details() throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		String order_id_q = "select max(order_id) from orders";
		Statement get_order_id = con.createStatement();
		ResultSet rs_get_order_id = get_order_id.executeQuery(order_id_q);
		PreparedStatement orders = con.prepareStatement("insert into orders values(?,?,?,?,?)");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(400,200);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Customer Details");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("Create Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				New_order n_o;
				String c_name = textField.getText();
				
				long c_number = Long.parseLong(textField_1.getText());
				try {
					
					PreparedStatement o_queue = con.prepareStatement("insert into order_queue values(?,?,?)");
					int o_id;
					if(rs_get_order_id.next())
					{	
						o_id = rs_get_order_id.getInt(1);
						o_id++;
						orders.setInt(1,o_id);
						orders.setInt(2, 0);
						orders.setString(3, c_name);
						orders.setLong(4,c_number);
						orders.setString(5,"No");
						o_queue.setInt(1, o_id);
						o_queue.setString(2, c_name);
						o_queue.setLong(3, c_number);
						
						orders.executeUpdate();
						o_queue.executeUpdate();
						n_o = new New_order(o_id);
						n_o.show();
						dispose();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnNewButton.setBackground(new Color(204, 255, 255));
		
		JToggleButton tglbtnBack = new JToggleButton("Back");
		tglbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home_page h1;
				try {
					h1 = new Home_page();
					h1.show();
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tglbtnBack.setForeground(Color.BLACK);
		tglbtnBack.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnBack.setBackground(new Color(204, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(10)
											.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))))))
					.addGap(24))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}

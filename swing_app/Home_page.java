package swing_app;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JToggleButton;

public class Home_page extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_page frame = new Home_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Home_page() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		String q = "Select c_name from order_queue where order_id = (select min(order_id) from order_queue)";
		Statement st = con.createStatement();
		String q2 = "Select c_name from order_queue where order_id = (select min(order_id) from order_queue where order_id != (select min(order_id) from order_queue))";
		Statement st2 = con.createStatement();
		ResultSet rs = st.executeQuery(q);
		ResultSet rs2 = st2.executeQuery(q2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 153));
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("UK 07 & FOODs");
		lblNewJgoodiesTitle.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setBackground(new Color(255, 0, 0));
		lblNewJgoodiesTitle.setForeground(new Color(0, 0, 0));
		
		JToggleButton tglbtnLogout = new JToggleButton("Logout");
		tglbtnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Project_1 p;
				try {
					p = new Project_1();
					p.show();
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tglbtnLogout.setForeground(Color.BLACK);
		tglbtnLogout.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnLogout.setBackground(new Color(204, 255, 255));
		
		JToggleButton tglbtnBack = new JToggleButton("Back");
		tglbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Selection_window sw= new Selection_window();
				sw.show();
				dispose();
			}
		});
		tglbtnBack.setForeground(Color.BLACK);
		tglbtnBack.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnBack.setBackground(new Color(204, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewJgoodiesTitle, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnLogout, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnLogout, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewJgoodiesTitle, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
								.addGap(26))))
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnNewButton = new JButton("New Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C_details cd;
				try {
					cd = new C_details();
					cd.show();
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton.setBackground(new Color(204, 255, 255));
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		
		JButton btnNewButton_1 = new JButton("Update Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_order u_o;
				try {
					u_o = new Update_order();
					u_o.show();
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBackground(new Color(204, 255, 255));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_1.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		
		JButton btnNewButton_2 = new JButton("Delete Order");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete_order d_o;
				try {
					d_o = new Delete_order();
					d_o.show();
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBackground(new Color(204, 255, 255));
		btnNewButton_2.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		JButton btnNewButton_3 = new JButton("Current Order");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Current_order c_o;
				try {
					c_o = new Current_order();
					c_o.show();
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBackground(new Color(204, 255, 255));
		btnNewButton_3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_3.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		panel_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		JLabel lblNewLabel_1 = new JLabel("Order Manager");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
					.addGap(42)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(210)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(220))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
		);
		
		JLabel c_order_label = DefaultComponentFactory.getInstance().createLabel("Order no(?) is being prepared");
		c_order_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		if(rs.next())
		{
			c_order_label.setText(rs.getString(1) + "'s order is being Prepared.");
		}
		
		JLabel lblNewLabel = new JLabel("Order no(?) is next in queue");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		if(rs2.next())
		{
			lblNewLabel.setText(rs2.getString(1) + "'s order is next in queue.");
		}
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
						.addComponent(c_order_label, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(78)
					.addComponent(c_order_label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(82, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}

package swing_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Current_order extends JFrame {

	private JPanel contentPane;
	private JTextField o_id_textField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Current_order frame = new Current_order();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Current_order() throws ClassNotFoundException, SQLException{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		con.setAutoCommit(false);
		Statement orders = con.createStatement();
		String get_o = "select c_name from order_queue";
		ResultSet rs_get_o = orders.executeQuery(get_o);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblCurrentOrder = new JLabel("Current Order");
		lblCurrentOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentOrder.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		panel.setBackground(new Color(204, 255, 153));
		
		JToggleButton tglbtnBack = new JToggleButton("Back");
		tglbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con.rollback();
					Home_page hp;
					try {
						hp = new Home_page();
						hp.show();
						dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		tglbtnBack.setForeground(Color.BLACK);
		tglbtnBack.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnBack.setBackground(new Color(204, 255, 255));
		
		JToggleButton tglbtnDone = new JToggleButton("Done");
		tglbtnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					con.commit();
					Home_page hp;
					try {
						hp = new Home_page();
						hp.show();
						dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tglbtnDone.setForeground(Color.BLACK);
		tglbtnDone.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnDone.setBackground(new Color(204, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblCurrentOrder, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(tglbtnDone, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnDone, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCurrentOrder, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel_2 = new JLabel("Order Details");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addGap(80)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_1_1.setLayout(gl_panel_1_1);
		
		JLabel lblNewLabel = new JLabel("Customer Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(255, 255, 255));
		
		if(rs_get_o.next())
		{
			do {
				comboBox.addItem(rs_get_o.getString(1));
			}while(rs_get_o.next());
		}
		
		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrderId.setHorizontalAlignment(SwingConstants.CENTER);
		
		o_id_textField = new JTextField();
		o_id_textField.setEditable(false);
		o_id_textField.setColumns(10);
		
		JButton btnNewButton = new JButton("View Details");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		btnNewButton.setBackground(new Color(204, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer order_id = Integer.parseInt(o_id_textField.getText());
				try {
					PreparedStatement get_i_names = con.prepareStatement("select i_name,price,quantity from order_items where order_id = ?");
					get_i_names.setInt(1, order_id);
					ResultSet rs_get_i_names = get_i_names.executeQuery();
					PreparedStatement get_cost = con.prepareStatement("select cost from orders where order_id = ?");
					get_cost.setInt(1,order_id);
					ResultSet rs_get_cost = get_cost.executeQuery();
					
					String order_info="";
					
					if(rs_get_i_names.next() && rs_get_cost.next())
					{
						textField.setText("Total: " + rs_get_cost.getInt(1));
						do {
							int p = rs_get_i_names.getInt(2);
							int q = rs_get_i_names.getInt(3);
							String name = rs_get_i_names.getString(1);
							order_info += q + " " + name + " for " + p + "\n";
						}while(rs_get_i_names.next());
						
						textArea.setText(order_info);						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Mark Completed");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		btnNewButton_1.setBackground(new Color(204, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer order_id = Integer.parseInt(o_id_textField.getText());
				try {
					PreparedStatement up_orders = con.prepareStatement("update orders set served = 'Yes' where order_id = ?");
					up_orders.setInt(1, order_id);
					up_orders.executeUpdate();
					
					PreparedStatement del_order = con.prepareStatement("delete from order_queue where order_id = ?");
					del_order.setInt(1, order_id);
					del_order.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addComponent(lblOrderId, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGap(80))
							.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addGap(73))
							.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addComponent(btnNewButton_1)
								.addGap(69))
							.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
									.addComponent(comboBox, Alignment.LEADING, 0, 221, Short.MAX_VALUE)
									.addComponent(o_id_textField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
								.addContainerGap()))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(80))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(13)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblOrderId)
					.addGap(11)
					.addComponent(o_id_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(36))
		);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c_name_selected = (String)comboBox.getSelectedItem();
				try {
					PreparedStatement o_id = con.prepareStatement("select order_id from order_queue where c_name = ?");
					o_id.setString(1, c_name_selected);
					
					ResultSet rs_get_o_id = o_id.executeQuery();
					
					if(rs_get_o_id.next())
					{ 
						o_id_textField.setText("" + rs_get_o_id.getInt(1));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}

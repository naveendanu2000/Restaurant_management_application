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
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class Delete_order extends JFrame {

	private JPanel contentPane;
	private JTextField cost_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_order frame = new Delete_order();
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
	public Delete_order() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		con.setAutoCommit(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblDeleteOrder = new JLabel("Delete Order");
		lblDeleteOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteOrder.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		JPanel panel = new JPanel();
		
		JComboBox comboBox_1 = new JComboBox();
		Statement get_o_details = con.createStatement();
		ResultSet rs_get_o = get_o_details.executeQuery("Select c_name from order_queue");
		
		if(rs_get_o.next())
		{
			do {
				comboBox_1.addItem(rs_get_o.getString(1));
			}while(rs_get_o.next());
		}
		
		
		JTextArea order_details_textArea = new JTextArea();
		
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
				String c_name = (String)comboBox_1.getSelectedItem();
				PreparedStatement del_orders;
				PreparedStatement get_o_id;
				PreparedStatement del_o_queue;
				try {
					con.commit();
					ResultSet rs_get_o_id;
					get_o_id = con.prepareStatement("select order_id from orders where c_name = ?");
					get_o_id.setString(1, c_name);
					rs_get_o_id = get_o_id.executeQuery();
					
					if(rs_get_o_id.next())
					{						
						del_o_queue = con.prepareStatement("delete from order_queue where order_id = ?");
						del_orders = con.prepareStatement("delete from orders where order_id = ?");
						del_o_queue.setInt(1, rs_get_o_id.getInt(1));
						del_orders.setInt(1,rs_get_o_id.getInt(1));
						del_o_queue.executeUpdate();
						con.commit();
						del_orders.executeUpdate();
					}
					
					
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
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(86)
							.addComponent(lblDeleteOrder, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addGap(120)
							.addComponent(tglbtnDone, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnDone, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblDeleteOrder, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addGap(18))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))))
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1 = new JLabel("Select Customer");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNewButton_3.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		
		JPanel panel_1 = new JPanel();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c_name = (String)comboBox_1.getSelectedItem();
				PreparedStatement get_o_id;
				PreparedStatement o_details;
				ResultSet rs_get_o_id; 
				try {
					get_o_id = con.prepareStatement("select order_id,cost from orders where c_name = ?");
					get_o_id.setString(1, c_name);					
					rs_get_o_id = get_o_id.executeQuery();
					
					o_details = con.prepareStatement("Select quantity,i_name,price from order_items where order_id = ?");
					String order_details = "";
					if(rs_get_o_id.next())
					{
						o_details.setInt(1, rs_get_o_id.getInt(1));
						Integer cost =rs_get_o_id.getInt(2); 
						ResultSet rs_o_details = o_details.executeQuery();						

						if(!rs_o_details.next()){}
						else
						{
							do {
								int i_price = rs_o_details.getInt(3);
								int q = rs_o_details.getInt(1);
								String i_name = rs_o_details.getString(2);
								i_price = i_price*q;
								order_details += q + " " + i_name + " for " + i_price;
								order_details += "\n";
							}while(rs_o_details.next());
							
							order_details_textArea.setText(order_details);
							cost_textField.setText("Total = " + cost);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBox_1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBox_1.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		cost_textField = new JTextField();
		cost_textField.setColumns(10);
		
		JToggleButton tglbtnDone_1 = new JToggleButton("Delete Order");
		tglbtnDone_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String c_name = (String)comboBox_1.getSelectedItem();
				PreparedStatement del_orders;
				PreparedStatement get_o_id;
				PreparedStatement del_order_items;
				try {
					ResultSet rs_get_o_id;
					get_o_id = con.prepareStatement("select order_id from orders where c_name = ?");
					get_o_id.setString(1, c_name);
					rs_get_o_id = get_o_id.executeQuery();
					
					if(rs_get_o_id.next())
					{
						del_order_items = con.prepareStatement("delete from order_items where order_id = ?");
						del_order_items.setInt(1,rs_get_o_id.getInt(1));
						del_order_items.executeUpdate();
					}
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				order_details_textArea.setText("Click Done to Apply Changes!");
				
			}
		});
		tglbtnDone_1.setForeground(Color.BLACK);
		tglbtnDone_1.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnDone_1.setBackground(new Color(204, 255, 255));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(order_details_textArea, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(185)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(tglbtnDone_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cost_textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
							.addGap(9)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(order_details_textArea, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cost_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnDone_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String c_name = (String)comboBox_1.getSelectedItem();
				PreparedStatement get_o_id;
				PreparedStatement o_details;
				ResultSet rs_get_o_id; 
				try {
					get_o_id = con.prepareStatement("select order_id,cost from orders where c_name = ?");
					get_o_id.setString(1, c_name);					
					rs_get_o_id = get_o_id.executeQuery();
					
					o_details = con.prepareStatement("Select quantity,i_name,price from order_items where order_id = ?");
					String order_details = "";
					if(rs_get_o_id.next())
					{
						o_details.setInt(1, rs_get_o_id.getInt(1));
						Integer cost =rs_get_o_id.getInt(2); 
						ResultSet rs_o_details = o_details.executeQuery();						

						if(!rs_o_details.next()){}
						else
						{
							do {
								int i_price = rs_o_details.getInt(3);
								int q = rs_o_details.getInt(1);
								String i_name = rs_o_details.getString(2);
								i_price = i_price*q;
								order_details += q + " " + i_name + " for " + i_price;
								order_details += "\n";
							}while(rs_o_details.next());
							
							order_details_textArea.setText(order_details);
							cost_textField.setText("Total = " + cost);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		
	}
}

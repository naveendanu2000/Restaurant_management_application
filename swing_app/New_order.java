package swing_app;

import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.sql.*;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class New_order extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New_order frame = new New_order(50);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public New_order(int order_id) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		con.setAutoCommit(false);
		
		String get_category = "select category from item_categories";
		Statement category = con.createStatement();
		ResultSet rs_get_categories = category.executeQuery(get_category);
		
//		String i_number = "select max(i_number) from order_items";
//		Statement get_i_number = con.createStatement();
//		ResultSet rs_get_i_number = get_i_number.executeQuery(i_number);
//		rs_get_i_number.next();
		
		
		PreparedStatement price = con.prepareStatement("select price from menu where i_name = ?");
		PreparedStatement get_i_name = con.prepareStatement("select i_name from menu where category = ?");
		PreparedStatement add = con.prepareStatement("insert into order_items values(?,?,?,?,?)");
		Vector<Integer> cost = new Vector<Integer>();
		Vector<String> available_categories = new Vector<String>();
		Vector<String> available_i_names = new Vector<String>();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Enter order details         ");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		panel.setBackground(new Color(204, 255, 153));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 4));
		panel_1.setBackground(new Color(204, 255, 153));
		
		JToggleButton tglbtnBack = new JToggleButton("Back");
		tglbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C_details cd;
				try {
					cd = new C_details();
					cd.show();
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				String delete_o_queue = "delete from order_queue where order_id ='" + order_id + "'";
				String delete_order = "delete from orders where order_id ='"+ order_id +"'";
				try {
					Statement remove_order = con.createStatement();
					Statement remove_o_queue = con.createStatement();
					con.rollback();
					remove_o_queue.executeQuery(delete_o_queue);
					con.commit();
					remove_order.executeQuery(delete_order);
					con.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);

		
		tglbtnBack.setForeground(Color.BLACK);
		tglbtnBack.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnBack.setBackground(new Color(204, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 357, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1_1 = new JLabel("Order Details");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel_2.setBackground(new Color(255, 255, 255));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(36)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(gl_panel_1);
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		if(!rs_get_categories.next())
		{
			
		}
		else
		{
			do
			{	
				available_categories.add(rs_get_categories.getString(1));
				comboBox.addItem(rs_get_categories.getString(1));
			}while(rs_get_categories.next());
			
		}
		comboBox.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBox.setBackground(new Color(255, 255, 255));
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBox_1.setBackground(new Color(255, 255, 255));
		comboBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
	
							get_i_name.setString(1, (String) comboBox.getSelectedItem());
							ResultSet rs_get_i_names = get_i_name.executeQuery();
							if(!rs_get_i_names.next())
							{}
							else
							{
								comboBox_1.removeAllItems();
								do
								{	
									available_i_names.add(rs_get_i_names.getString(1));
									comboBox_1.addItem(rs_get_i_names.getString(1));
								}while(rs_get_i_names.next());
								
							}								
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		

		
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("Food Item");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		String[] quantity = {"1","2","3","4","5","6","7","8","9","10"};
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JComboBox comboBox_2 = new JComboBox(quantity);
		comboBox_2.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBox_2.setBackground(new Color(255, 255, 255));
		comboBox_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		JButton btnNewButton = new JButton("Add Item");
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i_num=0;
				String c = (String) comboBox.getSelectedItem();
				String i = (String) comboBox_1.getSelectedItem();
				Integer q =  Integer.parseInt((String) comboBox_2.getSelectedItem()) ;
				Integer i_price;
				try {
					PreparedStatement get_i_number = con.prepareStatement("select max(i_number) from order_items where order_id = ?");
					get_i_number.setInt(1, order_id);
					ResultSet rs_get_i_number;
					rs_get_i_number = get_i_number.executeQuery();
					if(rs_get_i_number.next())
					{
						i_num = rs_get_i_number.getInt(1);
					}
					
					price.setString(1, i);
					ResultSet rs_price = price.executeQuery();
					rs_price.next();
					i_price = rs_price.getInt(1);
					i_price = i_price * q;
					cost.add(i_price);
					int sum=0;
					
					for(int x=0 ; x<cost.size() ; x++)
					{
						sum += cost.get(x);
					}
					i_num ++;
					String new_item = q + " " + i + " for Rs." + i_price;
					String for_textArea = textArea.getText();
					for_textArea = for_textArea + "\n" + new_item;
					textArea.setText(for_textArea);
					add.setInt(1,i_num);
					add.setInt(2,order_id);
					add.setString(3, i);
					add.setInt(4, i_price);
					add.setInt(5, q);
					
					textField.setText("Total = " + sum);
					add.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnCompleteOrder = new JButton("Complete Order");
		btnCompleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					int sum=0;
					
					for(int i=0 ; i<cost.size() ; i++)
					{
						sum += cost.get(i);
					}
					PreparedStatement update_cost = con.prepareStatement("update orders set cost = ? where order_id = ?");
					update_cost.setInt(1, sum);
					update_cost.setInt(2,order_id);
					update_cost.executeUpdate();
					con.commit();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
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
		btnCompleteOrder.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCompleteOrder.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		btnCompleteOrder.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox_2, 0, 235, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 235, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addComponent(comboBox_1, 0, 235, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnCompleteOrder, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
							.addGap(82))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(btnCompleteOrder, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}

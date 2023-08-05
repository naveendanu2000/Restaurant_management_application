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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;

public class Update_order extends JFrame {

	private JPanel contentPane;
	private JTextField cost_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_order frame = new Update_order();
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
	public Update_order() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		con.setAutoCommit(false);
		Vector<String> o_i_names = new Vector<String>();
		Vector<Integer> o_i_q = new Vector<Integer>();
		Vector<String> available_categories = new Vector<String>();
		Vector<String> available_i_names = new Vector<String>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 153));
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		
		JLabel lblNewLabel = new JLabel("Update Order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(new Color(255, 255, 255));
		comboBox_1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
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
				PreparedStatement get_o_id;
				PreparedStatement o_details;
				ResultSet rs_get_o_id; 
				int sum=0;
				try {
					
					get_o_id = con.prepareStatement("select order_id from orders where c_name = ?");
					get_o_id.setString(1, c_name);					
					rs_get_o_id = get_o_id.executeQuery();
					
					o_details = con.prepareStatement("Select quantity,price from order_items where order_id = ?");
					if(rs_get_o_id.next())
					{
						o_details.setInt(1, rs_get_o_id.getInt(1));
						ResultSet rs_o_details = o_details.executeQuery();						
						
						
						
						
						if(!rs_o_details.next()){}
						else
						{
							do {
								int i_price = rs_o_details.getInt(2);
								int q = rs_o_details.getInt(1);
								i_price = i_price*q;
								sum+=i_price;
								
							}while(rs_o_details.next());
						}
						
						PreparedStatement up_cost = con.prepareStatement("update orders set cost = ? where order_id = ?");
						up_cost.setInt(1, sum);
						up_cost.setInt(2, rs_get_o_id.getInt(1));
						up_cost.executeUpdate();
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
					}
				} catch (SQLException e1) {
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
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 529, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(84)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
							.addGap(89)
							.addComponent(tglbtnDone, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(17))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnDone, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addGap(144))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Select Customer");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		
		JPanel update = new JPanel();
		update.setVisible(false);
		JPanel delete = new JPanel();
		delete.setVisible(false);
		
		JButton btnNewButton = new JButton("Delete Item");
		btnNewButton.setBackground(new Color(204, 255, 255));
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JButton btnNewButton_1 = new JButton("Update Quantity");
		btnNewButton_1.setBackground(new Color(204, 255, 255));
		btnNewButton_1.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel changes_message = new JLabel("Click Done to confirm Changes!");
		changes_message.setHorizontalAlignment(SwingConstants.CENTER);
		changes_message.setFont(new Font("Tahoma", Font.PLAIN, 14));
		changes_message.setVisible(false);
		
		JPanel new_item = new JPanel();
		new_item.setVisible(false);
		
		JButton btnNewButton_1_1 = new JButton("Add New Item");
		
		btnNewButton_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnNewButton_1_1.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnNewButton_1_1.setBackground(new Color(204, 255, 255));
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(delete, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addComponent(changes_message, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(update, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(60)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(new_item, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(new_item, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(update, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(changes_message)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(delete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1_1 = new JLabel("Category");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox<String> category_ni = new JComboBox<String>();
		
		category_ni.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		category_ni.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		category_ni.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_2_1 = new JLabel("Food Item");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox<String> i_name_ni = new JComboBox<String>();
		i_name_ni.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		i_name_ni.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		i_name_ni.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		String[] quantity = {"1","2","3","4","5","6","7","8","9","10"};
		JComboBox q_ni = new JComboBox(quantity);
		
		
		q_ni.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		q_ni.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		q_ni.setBackground(Color.WHITE);
		
		JButton add_new_item = new JButton("Add Item");
		
		add_new_item.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add_new_item.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		add_new_item.setBackground(new Color(204, 255, 255));
		GroupLayout gl_new_item = new GroupLayout(new_item);
		gl_new_item.setHorizontalGroup(
			gl_new_item.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_new_item.createSequentialGroup()
					.addGroup(gl_new_item.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_new_item.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
						.addGroup(gl_new_item.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
						.addGroup(gl_new_item.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
						.addGroup(gl_new_item.createSequentialGroup()
							.addContainerGap()
							.addComponent(category_ni, 0, 194, Short.MAX_VALUE))
						.addGroup(gl_new_item.createSequentialGroup()
							.addContainerGap()
							.addComponent(q_ni, 0, 194, Short.MAX_VALUE))
						.addGroup(gl_new_item.createSequentialGroup()
							.addGap(60)
							.addComponent(add_new_item, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_new_item.createSequentialGroup()
							.addContainerGap()
							.addComponent(i_name_ni, 0, 194, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_new_item.setVerticalGroup(
			gl_new_item.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_new_item.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(category_ni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(i_name_ni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(q_ni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(add_new_item, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		new_item.setLayout(gl_new_item);
		
		JLabel label_item_select_del = new JLabel("Select Item");
		label_item_select_del.setBackground(new Color(255, 255, 255));
		label_item_select_del.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		label_item_select_del.setHorizontalAlignment(SwingConstants.CENTER);
		label_item_select_del.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox select_item_del = new JComboBox();
		select_item_del.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		
		JButton btnNewButton_2_1 = new JButton("Delete");
		
		btnNewButton_2_1.setBackground(new Color(204, 255, 255));
		btnNewButton_2_1.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_delete = new GroupLayout(delete);
		gl_delete.setHorizontalGroup(
			gl_delete.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_delete.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_item_select_del, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(select_item_del, 0, 123, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_delete.createSequentialGroup()
					.addGap(60)
					.addComponent(btnNewButton_2_1, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(54))
		);
		gl_delete.setVerticalGroup(
			gl_delete.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_delete.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_delete.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_delete.createSequentialGroup()
							.addGap(3)
							.addComponent(label_item_select_del, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addComponent(select_item_del, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		delete.setLayout(gl_delete);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		
		JLabel label_item_select = new JLabel("Select Item");
		label_item_select.setHorizontalAlignment(SwingConstants.CENTER);
		label_item_select.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel label_new_qty = new JLabel("Enter new quantity");
		label_new_qty.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_new_qty.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnNewButton_2 = new JButton("Apply Changes");
		
		btnNewButton_2.setBackground(new Color(204, 255, 255));
		btnNewButton_2.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_update = new GroupLayout(update);
		gl_update.setHorizontalGroup(
			gl_update.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_update.createSequentialGroup()
					.addGroup(gl_update.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_update.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_item_select, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, 0, 123, Short.MAX_VALUE))
						.addGroup(gl_update.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_new_qty)
							.addGap(18)
							.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
						.addGroup(gl_update.createSequentialGroup()
							.addGap(57)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_update.setVerticalGroup(
			gl_update.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_update.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_update.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_item_select))
					.addGap(18)
					.addGroup(gl_update.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_new_qty)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton_2)
					.addGap(97))
		);
		update.setLayout(gl_update);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		
		JTextArea order_details_textArea = new JTextArea();
		order_details_textArea.setEditable(false);
		cost_textField = new JTextField();
		cost_textField.setEditable(false);
		cost_textField.setColumns(10);
		
		Statement get_o_details = con.createStatement();
		ResultSet rs_get_o = get_o_details.executeQuery("Select c_name from order_queue");
		
		if(rs_get_o.next())
		{
			do {
				comboBox_1.addItem(rs_get_o.getString(1));
			}while(rs_get_o.next());
		}
		
		
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
								o_i_names.add(i_name);
								o_i_q.add(q);
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
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel_2 = new JLabel("Order Details");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(86)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(order_details_textArea, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(cost_textField, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(order_details_textArea, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(cost_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_item.setVisible(false);
				update.setVisible(true);
				delete.setVisible(false);
				for(int i=0;i<o_i_names.size(); i++)
				{
					comboBox.addItem(o_i_names.get(i));
				}
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c_name = (String)comboBox_1.getSelectedItem();
				PreparedStatement get_o_id;
				
				String i_name_up = (String) comboBox.getSelectedItem();
				int q_up = (int) spinner.getValue();
				PreparedStatement o_up;
				try {
					ResultSet rs_get_o_id;
					get_o_id = con.prepareStatement("select order_id from orders where c_name = ?");
					get_o_id.setString(1, c_name);
					rs_get_o_id = get_o_id.executeQuery();
					
					if(rs_get_o_id.next())
					{
						o_up = con.prepareStatement("Update order_items set quantity = ? where i_name = ? and order_id = ?");
						o_up.setInt(1, q_up);
						o_up.setString(2, i_name_up);
						o_up.setInt(3, rs_get_o_id.getInt(1));
						o_up.executeUpdate();
					}
					delete.setVisible(false);
					update.setVisible(false);
					new_item.setVisible(false);
					changes_message.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_item.setVisible(false);
				delete.setVisible(true);
				update.setVisible(false);
				
				for(int i=0;i<o_i_names.size(); i++)
				{
					select_item_del.addItem(o_i_names.get(i));
				}
				
			}
		});
		
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c_name = (String)comboBox_1.getSelectedItem();
				String i_name_del = (String)select_item_del.getSelectedItem();
				PreparedStatement del_item;
				PreparedStatement get_o_id;
				try {
					ResultSet rs_get_o_id;
					get_o_id = con.prepareStatement("select order_id from orders where c_name = ?");
					get_o_id.setString(1, c_name);
					rs_get_o_id = get_o_id.executeQuery();
					
					if(rs_get_o_id.next())
					{
						del_item = con.prepareStatement("delete from order_items where i_name = ? and order_id = ?");
						del_item.setString(1,i_name_del);
						del_item.setInt(2,rs_get_o_id.getInt(1));
						del_item.executeUpdate();	
					}
					
					delete.setVisible(false);
					update.setVisible(false);
					new_item.setVisible(false);
					changes_message.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String get_category = "select category from item_categories";
				try {
					Statement category = con.createStatement();
					ResultSet rs_get_categories = category.executeQuery(get_category);
					if(!rs_get_categories.next())
					{
						
					}
					else
					{
						do
						{	
							available_categories.add(rs_get_categories.getString(1));
							category_ni.addItem(rs_get_categories.getString(1));
						}while(rs_get_categories.next());
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				new_item.setVisible(true);
				update.setVisible(false);
				delete.setVisible(false);
				
			}
		});
		
		category_ni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement get_i_name = con.prepareStatement("select i_name from menu where category = ?");					
					get_i_name.setString(1, (String) category_ni.getSelectedItem());
					ResultSet rs_get_i_names = get_i_name.executeQuery();
					if(!rs_get_i_names.next())
					{}
					else
					{
						i_name_ni.removeAllItems();
						do
						{	
							available_i_names.add(rs_get_i_names.getString(1));
							i_name_ni.addItem(rs_get_i_names.getString(1));
						}while(rs_get_i_names.next());
						
					}								
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
			}
		});
		
		add_new_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String c_name = (String)comboBox_1.getSelectedItem();
				PreparedStatement get_o_id;
				PreparedStatement add_ni;
				PreparedStatement price;
				ResultSet rs_get_o_id;
				ResultSet rs_get_i_number;
				ResultSet rs_get_price;
				try {
					price = con.prepareStatement("select price from menu where i_name = ?");
					add_ni = con.prepareStatement("insert into order_items values(?,?,?,?,?)");
					get_o_id = con.prepareStatement("select order_id from orders where c_name = ?");
					get_o_id.setString(1, c_name);
					
					String i_name = (String) i_name_ni.getSelectedItem();
					Integer q = Integer.parseInt( (String) q_ni.getSelectedItem());
					price.setString(1,i_name);
					PreparedStatement get_i_number = con.prepareStatement("select max(i_number) from order_items where order_id = ?");
					rs_get_o_id = get_o_id.executeQuery();
					rs_get_price = price.executeQuery();
					
					if(rs_get_price.next() && rs_get_o_id.next())
					{	
						get_i_number.setInt(1, rs_get_o_id.getInt(1));
						rs_get_i_number = get_i_number.executeQuery();
						int p = rs_get_price.getInt(1);
						
						p = p * q;
						if(rs_get_i_number.next())
						{
							add_ni.setInt(1, (rs_get_i_number.getInt(1)+1));
							add_ni.setInt(2,rs_get_o_id.getInt(1));							
							add_ni.setString(3,i_name);
							add_ni.setInt(4,p);
							add_ni.setInt(5, q);
						}
						
						add_ni.executeUpdate();
					}
					
					delete.setVisible(false);
					update.setVisible(false);
					new_item.setVisible(false);
					changes_message.setVisible(true);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		panel_3.setLayout(gl_panel_3);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}

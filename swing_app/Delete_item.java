package swing_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Delete_item extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_item frame = new Delete_item();
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
	public Delete_item() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		con.setAutoCommit(false);
		
		
		
		
		Vector<Integer> prices = new Vector<Integer>();
		Vector<String> available_categories = new Vector<String>();
		Vector<String> available_i_names = new Vector<String>();
		
		
		String get_category = "select category from item_categories";
		Statement category = con.createStatement();
		ResultSet rs_get_categories = category.executeQuery(get_category);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JToggleButton tglbtnBack = new JToggleButton("Back");
		tglbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con.rollback();
					Menu_manager mm;
					try {
						mm = new Menu_manager();
						mm.show();
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
		
		JLabel lblDeleteFoodItem = new JLabel("Delete Food Item");
		lblDeleteFoodItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteFoodItem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		panel.setBackground(new Color(204, 255, 153));
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBox.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBox.setBackground(Color.WHITE);
		
		JButton btnDone = new JButton("Delete");
		
		btnDone.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDone.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnDone.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1_1 = new JLabel("Food Item");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
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
		comboBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBox_1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBox_1.setBackground(Color.WHITE);
		
		JButton btnDone_2 = new JButton("Done");
		btnDone_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con.commit();
					Menu_manager mm;
					try {
						mm = new Menu_manager();
						mm.show();
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
		btnDone_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDone_2.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnDone_2.setBackground(Color.WHITE);
		
		JLabel ack_message = new JLabel("");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBox, 0, 229, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(82)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDone_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(100)
							.addComponent(ack_message)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(ack_message)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(btnDone_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(38))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_1_1 = new JPanel();
		
		JLabel lblNewLabel_3 = new JLabel("Category Details");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextArea category_info_textArea = new JTextArea();
		category_info_textArea.setEditable(false);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addComponent(category_info_textArea, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addGap(66)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(70))))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(category_info_textArea, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1_1.setLayout(gl_panel_1_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDeleteFoodItem, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDeleteFoodItem, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				available_i_names.clear();
				prices.clear();
				comboBox_1.removeAllItems();
				category_info_textArea.setText("");
				String cat = (String)comboBox.getSelectedItem();
				try {
					String cat_info="";
					PreparedStatement get_i_name = con.prepareStatement("select i_name,price from menu where category = ?");
					get_i_name.setString(1, cat);
					ResultSet rs_get_i_names = get_i_name.executeQuery();
					
					if(rs_get_i_names.next())
					{
						do {
							available_i_names.add(rs_get_i_names.getString(1));
							prices.add(rs_get_i_names.getInt(2));
							comboBox_1.addItem(rs_get_i_names.getString(1));
						}while(rs_get_i_names.next());
					}
					
					for(int i=0 ; i<prices.size(); i++)
					{
						cat_info += available_i_names.get(i) + " for " + prices.get(i) + "\n";
					}
					
					category_info_textArea.setText(cat_info);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String i_name = (String)comboBox_1.getSelectedItem();
				String cat_info="";
				PreparedStatement del_i_name;
				try {
					category_info_textArea.setText("");
					del_i_name = con.prepareStatement("delete from menu where i_name = ?");
					del_i_name.setString(1,i_name);
					del_i_name.executeUpdate();
					
					int index = available_i_names.indexOf(i_name);
					available_i_names.remove(i_name);
					prices.remove(index);
					
					for(int i=0 ; i<prices.size(); i++ )
					{
						cat_info += available_i_names.get(i) + " for " + prices.get(i) + "\n";
					}
					
					category_info_textArea.setText(cat_info);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}

}

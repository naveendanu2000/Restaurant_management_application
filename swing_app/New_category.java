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
import javax.swing.JToggleButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class New_category extends JFrame {

	private JPanel contentPane;
	private JTextField categoryNameField;
	private JTextField itemNameField;
	private JTextField priceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New_category frame = new New_category();
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
	public New_category() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		con.setAutoCommit(false);
		Vector<String> i_names = new Vector<String>();
		Vector<Integer> i_prices = new Vector<Integer>();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblDeleteCategory = new JLabel("New Category      ");
		lblDeleteCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteCategory.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JToggleButton tglbtnBack = new JToggleButton("Back");
		tglbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					con.rollback();
					PreparedStatement del_cat = con.prepareStatement("delete from item_categories where category = ?");
					String cat_name = categoryNameField.getText();
					del_cat.setString(1,cat_name);
					del_cat.executeUpdate();
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
		tglbtnBack.setForeground(Color.BLACK);
		tglbtnBack.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnBack.setBackground(new Color(204, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 153));
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		
		JButton addCategoryButton = new JButton("Add Category");
		addCategoryButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		addCategoryButton.setBackground(new Color(255, 255, 255));
		
		addCategoryButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel category_name = new JLabel("Category Name");
		category_name.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		categoryNameField = new JTextField();
		categoryNameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		categoryNameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Category Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextArea category_info_textArea = new JTextArea();
		category_info_textArea.setEditable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(category_name)
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
							.addComponent(categoryNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(addCategoryButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(79))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(category_info_textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(categoryNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(category_name))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(addCategoryButton)))
					.addGap(27)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(category_info_textArea, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel addItemPanel = new JPanel();
		addItemPanel.setBackground(new Color(204, 255, 153));
		addItemPanel.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		addItemPanel.setVisible(false);
		
		JLabel ack_message = new JLabel("");
		ack_message.setHorizontalAlignment(SwingConstants.CENTER);
		ack_message.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Item Name");
		lblNewLabel_4_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		itemNameField = new JTextField();
		itemNameField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		itemNameField.setColumns(10);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Price");
		lblNewLabel_4_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		priceField = new JTextField();
		priceField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		priceField.setColumns(10);
		
		JButton addItemButton = new JButton("Add Item");
		
		addItemButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		addItemButton.setBackground(new Color(255, 255, 255));
		addItemButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		JButton btnDone = new JButton("Add all items to the category");
		btnDone.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnDone.setBackground(new Color(255, 255, 255));
		
		btnDone.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		JToggleButton tglbtnBack_1 = new JToggleButton("Done");
		tglbtnBack_1.addActionListener(new ActionListener() {
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
		tglbtnBack_1.setForeground(Color.BLACK);
		tglbtnBack_1.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		tglbtnBack_1.setBackground(new Color(204, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(addItemPanel, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(lblDeleteCategory, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
							.addComponent(tglbtnBack_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDeleteCategory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnBack_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
						.addComponent(addItemPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		GroupLayout gl_addItemPanel = new GroupLayout(addItemPanel);
		gl_addItemPanel.setHorizontalGroup(
			gl_addItemPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addItemPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addItemPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ack_message, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
						.addGroup(gl_addItemPanel.createSequentialGroup()
							.addComponent(lblNewLabel_4_1_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(itemNameField, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
						.addGroup(gl_addItemPanel.createSequentialGroup()
							.addComponent(lblNewLabel_4_2_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(priceField, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_addItemPanel.createSequentialGroup()
							.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(22))
						.addGroup(Alignment.TRAILING, gl_addItemPanel.createSequentialGroup()
							.addComponent(addItemButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(66)))
					.addContainerGap())
		);
		gl_addItemPanel.setVerticalGroup(
			gl_addItemPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addItemPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_addItemPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4_1_1)
						.addComponent(itemNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_addItemPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4_2_1)
						.addComponent(priceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(addItemButton)
					.addGap(26)
					.addComponent(btnDone)
					.addGap(41)
					.addComponent(ack_message, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(48))
		);
		addItemPanel.setLayout(gl_addItemPanel);
		
		addCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					i_names.clear();
					i_prices.clear();
					category_info_textArea.setText("");
					PreparedStatement new_cat = con.prepareStatement("insert into item_categories values(?)");
					String cat_name = categoryNameField.getText();
					new_cat.setString(1,cat_name);
					new_cat.executeUpdate();
					addItemPanel.setVisible(true);
					addCategoryButton.setEnabled(false);
					con.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String i_name = itemNameField.getText();
				Integer price = Integer.parseInt(priceField.getText());
				String cat_name = categoryNameField.getText();
				
				try {
					PreparedStatement ins_menu = con.prepareStatement("insert into menu values(?,?,?)");
					ins_menu.setString(1,cat_name);
					ins_menu.setString(2,i_name);
					ins_menu.setInt(3, price);
					
					i_names.add(i_name);
					i_prices.add(price);
					
					ins_menu.executeUpdate();
					
					//category_info_textArea
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String cat_info="";
				for(int i=0 ; i <i_names.size();i++)
				{
					cat_info += i_names.get(i) + " for " + i_prices.get(i) + "\n";
				}
				
				ack_message.setText("Item added Press done to Confirm!");
				category_info_textArea.setText(cat_info);
			}
		});
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					con.commit();
					addItemPanel.setVisible(false);
					addCategoryButton.setEnabled(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.setLayout(gl_contentPane);
	}
}

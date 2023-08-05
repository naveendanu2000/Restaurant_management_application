package swing_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
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

public class New_item extends JFrame {

	private JPanel contentPane;
	private JTextField i_name_ni;
	private JTextField price_ni;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New_item frame = new New_item();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public New_item() throws Exception{
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		con.setAutoCommit(false);
		
		Vector<Integer> prices = new Vector<Integer>();	
		Vector<String> available_i_names = new Vector<String>();
		
		String get_category = "select category from item_categories";
		Statement category = con.createStatement();
		ResultSet rs_get_categories = category.executeQuery(get_category);
		PreparedStatement get_i_names = con.prepareStatement("select i_name,price from menu where category = ?");
		
		
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
		
		JLabel lblAddItemTo = new JLabel("Add Item to Existing Category");
		lblAddItemTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddItemTo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 153));
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(lblAddItemTo, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddItemTo, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_1_1 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
					.addGap(12))
		);
		
		JLabel lblNewLabel_3 = new JLabel("Category Details");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextArea category_info_textArea = new JTextArea();
		category_info_textArea.setEditable(false);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(72)
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(73))
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(category_info_textArea, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(category_info_textArea, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1_1.setLayout(gl_panel_1_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		if(!rs_get_categories.next())
		{
			
		}
		else
		{	
			
			do
			{
				comboBox.addItem(rs_get_categories.getString(1));
			}while(rs_get_categories.next());
			
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs_get_i_names;
				String cat_info="";
				try {
					
					category_info_textArea.setText("");
					get_i_names.setString(1, (String) comboBox.getSelectedItem());
					rs_get_i_names = get_i_names.executeQuery();
					if(rs_get_i_names.next())
					{
						do {
							prices.add(rs_get_i_names.getInt(2));
							available_i_names.add(rs_get_i_names.getString(1));
						}while(rs_get_i_names.next());
					}
					
					
					for(int i=0 ; i< prices.size() ; i++)
					{
						cat_info += available_i_names.get(i) + " for " + prices.get(i) + "\n";
					}
					
					category_info_textArea.setText(cat_info);
					available_i_names.clear();
					prices.clear();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBox.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBox.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		i_name_ni = new JTextField();
		i_name_ni.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		i_name_ni.setColumns(10);
		
		price_ni = new JTextField();
		price_ni.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		price_ni.setColumns(10);
		
		JButton addItemButton = new JButton("Add Item");
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String new_i_name = i_name_ni.getText();
				Integer new_price = Integer.parseInt(price_ni.getText());			
				ResultSet rs_get_i_names;
				String cat_info="";
				try {
					
					category_info_textArea.setText("");
					get_i_names.setString(1, (String) comboBox.getSelectedItem());
					rs_get_i_names = get_i_names.executeQuery();
					if(rs_get_i_names.next())
					{
						do {
							prices.add(rs_get_i_names.getInt(2));
							available_i_names.add(rs_get_i_names.getString(1));
						}while(rs_get_i_names.next());
					}
					
					available_i_names.add(i_name_ni.getText());
					prices.add(Integer.parseInt(price_ni.getText()));
					
					for(int i=0 ; i< prices.size() ; i++)
					{
						cat_info += available_i_names.get(i) + " for " + prices.get(i) + "\n";
					}
					PreparedStatement new_item = con.prepareStatement("insert into menu values (?,?,?)");
					new_item.setString(1, (String) comboBox.getSelectedItem());
					new_item.setString(2, new_i_name);
					new_item.setInt(3, new_price);
					
					new_item.executeUpdate();
					
					category_info_textArea.setText(cat_info);
					available_i_names.clear();
					prices.clear();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		addItemButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		addItemButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		addItemButton.setBackground(Color.WHITE);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
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
		btnDone.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDone.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnDone.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Enter Item name");
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 225, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(price_ni, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
								.addComponent(i_name_ni, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(85, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(addItemButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addGap(75))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(i_name_ni, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(price_ni, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addComponent(addItemButton, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

}

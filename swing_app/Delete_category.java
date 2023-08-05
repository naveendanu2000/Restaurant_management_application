package swing_app;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
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

public class Delete_category extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_category frame = new Delete_category();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Delete_category() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","rmanager","mca");
		con.setAutoCommit(false);
		
		
		String get_category = "select category from item_categories";
		Statement category = con.createStatement();
		ResultSet rs_get_categories = category.executeQuery(get_category);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(575,463);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
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
		
		JLabel lblDeleteCategory = new JLabel("Delete Category      ");
		lblDeleteCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteCategory.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		panel.setBackground(new Color(204, 255, 153));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblDeleteCategory, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tglbtnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDeleteCategory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_1_1 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel_3 = new JLabel("Category Details");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextArea category_info_textArea = new JTextArea();
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addComponent(category_info_textArea, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel_1_1.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(74))))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(category_info_textArea, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addContainerGap())
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
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBox.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBox.setBackground(Color.WHITE);
		
		JButton btnDone = new JButton("Delete");
		
		btnDone.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDone.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnDone.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDone_2 = new JButton("Done");
		btnDone_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con.commit();
					String cat = (String)comboBox.getSelectedItem();
					PreparedStatement del_cat = con.prepareStatement("delete from item_categories where category = ?");
					del_cat.setString(1, cat);
					del_cat.executeUpdate();
					con.commit();
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
		ack_message.setHorizontalAlignment(SwingConstants.CENTER);
		ack_message.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(80)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDone_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(ack_message, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
								.addComponent(comboBox, 0, 229, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(ack_message)
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addComponent(btnDone_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
		);
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cat = (String)comboBox.getSelectedItem();
				PreparedStatement del_i_names;
				try {
					del_i_names = con.prepareStatement("delete from menu where category = ?");
					del_i_names.setString(1, cat);
					del_i_names.executeUpdate();
					
					ack_message.setText("Category Deleted click on Done to Confirm");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cat_name = (String) comboBox.getSelectedItem();
				try {
					category_info_textArea.setText("");
					PreparedStatement get_cat_info = con.prepareStatement("select i_name,price from menu where category = ?");
					get_cat_info.setString(1,cat_name);
					ResultSet rs_get_cat_info = get_cat_info.executeQuery(); 
					String info = "";
					if(rs_get_cat_info.next())
					{
						do
						{
							String i_name = rs_get_cat_info.getString(1);
							int price = rs_get_cat_info.getInt(2);
							
							info += i_name + " for " + price + "\n";
						}while(rs_get_cat_info.next());
						
						category_info_textArea.setText(info);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}

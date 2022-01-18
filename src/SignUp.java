import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtCreatePass;
	private JPasswordField txtConfirmPass;
	private JCheckBoxMenuItem chckbxShowPass1;
	private JCheckBoxMenuItem chckbxShowPass;
	private JButton btnSignUp;
	private JLabel lblNewLabel_1;
	
	public boolean verifyFields() {
		
		String username =  txtUsername.getText();
		String createpassword =  String.valueOf(txtCreatePass.getPassword());
		String confirmpassword = String.valueOf(txtConfirmPass.getPassword());
		
		//check if the two passwords are equal
		if(!createpassword.equals(confirmpassword)) {
					
					JOptionPane.showMessageDialog(null, "Password is not match", "Confirm Password", 2);
					return false;
					
		}
		
		//check if fields are empty
		else if(username.equals(null) || createpassword.equals(null) || confirmpassword.equals(null)) {
			
			
			JOptionPane.showMessageDialog(null, "Some fields are empty", "Empty Fields", 2);
			return false;
		}
		
		
		//if everything is clear
		else {
			
			return true;
		}
	}
	
		//create a function to check if the entered username is already registered
		
	public boolean checkUsername(String username){
		
		// to represent a precompiled SQL statement
		PreparedStatement st;
		
		// to represent a database result set
		ResultSet rs;
		
		boolean username_exist = false;
		
		String query = "SELECT * FROM `users` WHERE `username` = ?";
		
		try {
			st = mycnx.getConnection().prepareStatement(query);
			st.setString(1, username);
			rs = st.executeQuery();
		
			if(rs.next()) {
				
				username_exist = true;
				JOptionPane.showMessageDialog(null, "This username is already taken", "Error",2);
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return username_exist;
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setTitle("SIGN UP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("USERNAME: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(87, 46, 90, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCreatePassword = new JLabel("CREATE PASSWORD:");
		lblCreatePassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreatePassword.setBounds(35, 92, 128, 14);
		contentPane.add(lblCreatePassword);
		
		JLabel lblConfirmPassword = new JLabel("CONFIRM PASSWORD:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConfirmPassword.setBounds(35, 135, 128, 14);
		contentPane.add(lblConfirmPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(170, 33, 192, 31);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtCreatePass = new JPasswordField();
		txtCreatePass.setBounds(173, 85, 187, 31);
		contentPane.add(txtCreatePass);
		
		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setBounds(173, 128, 187, 31);
		contentPane.add(txtConfirmPass);
		
		chckbxShowPass = new JCheckBoxMenuItem("SHOW PASSWORD");
		chckbxShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if the check box is selected then the password sill show
				if(chckbxShowPass.isSelected()) {
					
					txtCreatePass.setEchoChar((char)0);
				
				//if the check box is not selected then the pass will hide
				}else {
				
					txtCreatePass.setEchoChar('*');
					
				}
			}
		});
		chckbxShowPass.setBounds(380, 92, 137, 26);
		contentPane.add(chckbxShowPass);
		
		chckbxShowPass1 = new JCheckBoxMenuItem("SHOW PASSWORD");
		chckbxShowPass1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if the check box is selected then the password sill show
				if(chckbxShowPass1.isSelected()) {
					
					txtConfirmPass.setEchoChar((char)0);
				
				//if the check box is not selected then the pass will hide
				}else {
					
					txtConfirmPass.setEchoChar('*');
					
				}
			}
		});
		chckbxShowPass1.setBounds(380, 135, 137, 26);
		contentPane.add(chckbxShowPass1);
		
		btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txtUsername.getText();
				String createpassword = String.valueOf(txtCreatePass.getPassword());
				String confirmpassword = String.valueOf(txtConfirmPass.getPassword());
				
				if(verifyFields()) {
					
					if(!checkUsername(username)) {
						
						PreparedStatement ps;
						ResultSet rs;
						String registeredUser = " INSERT INTO `users`(`username`, `password`) VALUES (?,?)";
						
						try {
							
							ps = mycnx.getConnection().prepareStatement(registeredUser);
							ps.setString(1, username);
							ps.setString(2, createpassword);
							
							if(ps.executeUpdate() != 0) {
								
								JOptionPane.showMessageDialog(null, "You account has been created");
							
							}else {
							
								JOptionPane.showMessageDialog(null, "Error: Check your information");
								
							}
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
				}
				
			}
		});
		btnSignUp.setBounds(216, 176, 89, 23);
		contentPane.add(btnSignUp);
		
		lblNewLabel_1 = new JLabel("<HTML><U>Already have account?</U><HTML>");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				LoginForm login = new LoginForm();
				login.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setBounds(205, 222, 137, 14);
		contentPane.add(lblNewLabel_1);
	}
}

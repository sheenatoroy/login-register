import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBoxMenuItem;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JButton btnLogin;
	private JPasswordField txtPassword;
	private JCheckBoxMenuItem chckbxShowPass;
	private JButton btnSignUp;

	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("USERNAME:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(37, 61, 82, 15);
		contentPane.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(128, 54, 265, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(37, 118, 82, 15);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement st;
				ResultSet rs;
				
				//get the username and password
				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				
				//create a select query to check if the username or and the password exist in database
				String query = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?";
				
				try {
					st = mycnx.getConnection().prepareStatement(query);
					st.setString(1, username);
					st.setString(2, password);
					
					rs = st.executeQuery();
					
					if(rs.next()) {
						
						//show a new form
						
						MainMenu main = new MainMenu();
						main.setVisible(true);
						
						dispose();
						
					}else {
						
						//error message will appear
						JOptionPane.showMessageDialog(contentPane, "Invalid Username/Password", " Login Failed", 2);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(128, 207, 89, 23);
		contentPane.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(128, 111, 265, 30);
		contentPane.add(txtPassword);
		
		chckbxShowPass = new JCheckBoxMenuItem("SHOW PASSWORD");
		chckbxShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//to show the password is correct
				//if the check box is selected then
				if(chckbxShowPass.isSelected()) {
					
					//the password entered will be show
					txtPassword.setEchoChar((char)0);
	
				//and if the user unchecked it then the password will hide
				//or show ass asterisk
				}else {
					
					txtPassword.setEchoChar('*');
				}
				
			}
		});
		chckbxShowPass.setBounds(163, 156, 137, 26);
		contentPane.add(chckbxShowPass);
		
		btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SignUp signup = new SignUp();
				signup.setVisible(true)	;
				dispose();
				
			}
		});
		btnSignUp.setBounds(268, 207, 89, 23);
		contentPane.add(btnSignUp);
	}
}

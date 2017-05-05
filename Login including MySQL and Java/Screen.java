
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;

public class Screen {

	private JFrame frmLoginSystem;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen window = new Screen();
					window.frmLoginSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection = new Connection();
		
		
		frmLoginSystem = new JFrame();
		frmLoginSystem.setTitle("Login System");
		frmLoginSystem.getContentPane().setBackground(new Color(204, 153, 255));
		frmLoginSystem.setBounds(100, 100, 714, 418);
		frmLoginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginSystem.getContentPane().setLayout(null);
		
		JLabel lblLoginSystem = new JLabel("Login System");
		lblLoginSystem.setFont(new Font("Algerian", Font.BOLD, 26));
		lblLoginSystem.setBounds(247, 27, 207, 20);
		frmLoginSystem.getContentPane().add(lblLoginSystem);
		
		textField = new JTextField();
		textField.setBounds(269, 131, 146, 26);
		frmLoginSystem.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.BLUE);
		lblUserName.setBounds(298, 95, 100, 20);
		frmLoginSystem.getContentPane().add(lblUserName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(269, 200, 146, 26);
		frmLoginSystem.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblEmail = new JLabel("E-Mail");
		lblEmail.setForeground(Color.BLUE);
		lblEmail.setBounds(298, 170, 69, 20);
		frmLoginSystem.getContentPane().add(lblEmail);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(269, 280, 146, 26);
		frmLoginSystem.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setBounds(298, 250, 69, 20);
		frmLoginSystem.getContentPane().add(lblPassword);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(500, 175, 90, 20);
		frmLoginSystem.getContentPane().add(registerButton);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String email = textField_2.getText();
				String password = textField_1.getText();
				connection.register(new Register(username, email, password));
			}          
	      });
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(500, 215, 69, 20);
		frmLoginSystem.getContentPane().add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = textField_2.getText();
				String password = textField_1.getText();
				connection.login(new Login(email, password));
			}          
	      });
		
	}
}

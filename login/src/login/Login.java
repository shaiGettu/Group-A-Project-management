package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class Login {

	private JFrame frmLoginSystem;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		textField_1 = new JTextField();
		textField_1.setBounds(269, 245, 146, 26);
		frmLoginSystem.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setBounds(298, 209, 69, 20);
		frmLoginSystem.getContentPane().add(lblPassword);
	}
}

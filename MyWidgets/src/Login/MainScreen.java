package Login;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

//import Calendar.CalendarWidget;
import Calendar.SwingCalendar;
import PictureAlbum.PictureAlbumWidget;
import ToDoList.taskListPage.TaskListByUser;
import TodayInHistory.TodayInHistoryWidget;

import java.awt.Color;
import java.io.IOException;

public class MainScreen {

	private JFrame frmLoginSystem;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private Connection connection;
	private boolean isLoggedIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		isLoggedIn = false;
		connection = new Connection();
		
		frmLoginSystem = new JFrame();
		frmLoginSystem.setTitle("Login System");
		frmLoginSystem.getContentPane().setBackground(new Color(204, 153, 255));
		frmLoginSystem.setBounds(100, 100, 714, 500);
		frmLoginSystem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		registerButton.setBounds(500, 100, 90, 20);
		frmLoginSystem.getContentPane().add(registerButton);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String email = textField_2.getText();
				String password = textField_1.getText();
				String message = connection.register(new Register(username, email, password));
				if (message.contains("false"))
					JOptionPane.showMessageDialog(frmLoginSystem, "You registered succesfully"
							+ " with Username: " + username + " and Email: " + email);
			}          
	      });
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(500, 150, 69, 20);
		frmLoginSystem.getContentPane().add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = textField_2.getText();
				String password = textField_1.getText();
				String message = connection.login(new Login(email, password));
				if (message.contains("false")) {
					JOptionPane.showMessageDialog(frmLoginSystem, "You logged in succesfully"
							+ " with Email: " + email);
					isLoggedIn = true;
				}
			}          
	      });
		
		JButton forogtPasswordButton = new JButton("Forgot Password");
		forogtPasswordButton.setBounds(500, 200, 140, 20);
		frmLoginSystem.getContentPane().add(forogtPasswordButton);
		forogtPasswordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String email = textField_2.getText();
				String message = connection.forgotPassword(new ForgotPassword(username, email));
				if (message.contains("false")) {
					JOptionPane.showMessageDialog(frmLoginSystem, "You password is" + message);
				}
			}          
	      });
		
		JButton toDoListButton = new JButton("To Do List");
		toDoListButton.setBounds(500, 250, 140, 20);
		frmLoginSystem.getContentPane().add(toDoListButton);
		toDoListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = textField_2.getText();
				if (isLoggedIn)
					try {
						TaskListByUser.launch(email);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				else
					JOptionPane.showMessageDialog(frmLoginSystem, "You are not logged in");
			}          
	      });
		
		JButton todayInHistoryButton = new JButton("Today in History");
		todayInHistoryButton.setBounds(500, 300, 140, 20);
		frmLoginSystem.getContentPane().add(todayInHistoryButton);
		todayInHistoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isLoggedIn)
					TodayInHistoryWidget.launch();
				else
					JOptionPane.showMessageDialog(frmLoginSystem, "You are not logged in");
			}          
	      });
		
		JButton calendarButton = new JButton("Calendar");
		calendarButton.setBounds(500, 350, 90, 20);
		frmLoginSystem.getContentPane().add(calendarButton);
		calendarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isLoggedIn)
					try {
						SwingCalendar.launch();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				else
					JOptionPane.showMessageDialog(frmLoginSystem, "You are not logged in");
			}          
	      });
		
		JButton pictureAlbumButton = new JButton("Picture Album");
		pictureAlbumButton.setBounds(500, 400, 120, 20);
		frmLoginSystem.getContentPane().add(pictureAlbumButton);
		pictureAlbumButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isLoggedIn)
					PictureAlbumWidget.launch();
				else
					JOptionPane.showMessageDialog(frmLoginSystem, "You are not logged in");
			}          
	      });
		
	}
}

package login;

/**
 * Created by Shai Gettu on 03-Jun-17.
 */
import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import calendar.SwingCalendar;
import pictureAlbum.PictureAlbum_GUI;
import taskListPage.TaskListByUser;
import todayInHistory.TodayInHistoryWidget;

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
    private static MainScreen instance;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainScreen window = getInstance();
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
    public static MainScreen getInstance(){
        if(instance == null){
            instance = new MainScreen();
        }
        return instance;
    }
    private MainScreen() {
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
        frmLoginSystem.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        registerButton.addActionListener(e -> {
            String username = textField.getText();
            String email = textField_2.getText();
            String password = textField_1.getText();
            if (username.equals("")){
                JOptionPane.showMessageDialog(frmLoginSystem, "Please enter username");
                return;
            }
            else if (email.equals("")){
                JOptionPane.showMessageDialog(frmLoginSystem, "Please enter email");
                return;
            }
            else if(password.equals("")){
                JOptionPane.showMessageDialog(frmLoginSystem, "Please enter password");
                return;
            }
            String message = connection.register(new Register(username, email, password));
            if (message.contains("false")) {
                JOptionPane.showMessageDialog(frmLoginSystem, "You registered succesfully"
                        + " with Username: " + username + " and Email: " + email);
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
            }
            else
                JOptionPane.showMessageDialog(frmLoginSystem,message.split("\"msg\":\"")[1]);
        });

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(500, 150, 69, 20);
        frmLoginSystem.getContentPane().add(loginButton);
        loginButton.addActionListener(e -> {
            String email = textField_2.getText();
            String password = textField_1.getText();
            if(email.equals("")){
                JOptionPane.showMessageDialog(frmLoginSystem, "Please enter email");
                return;
            }
            else if(password.equals("")){
                JOptionPane.showMessageDialog(frmLoginSystem, "Please enter password");
                return;
            }
            String message = connection.login(new Login(email, password));
            if (message.contains("false")) {
                JOptionPane.showMessageDialog(frmLoginSystem, "You logged in succesfully"
                        + " with Email: " + email);
                isLoggedIn = true;
                TaskListByUser.setStaticEmail(email);
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
            }
            else{
                JOptionPane.showMessageDialog(frmLoginSystem,"Wrong login details");
            }
        });

        JButton forogtPasswordButton = new JButton("Forgot Password");
        forogtPasswordButton.setBounds(500, 200, 140, 20);
        frmLoginSystem.getContentPane().add(forogtPasswordButton);
        forogtPasswordButton.addActionListener(e -> {
            String username = textField.getText();
            String email = textField_2.getText();
            if (username.equals("")){
                JOptionPane.showMessageDialog(frmLoginSystem, "Please enter username");
                return;
            }
            if(email.equals("")){
                JOptionPane.showMessageDialog(frmLoginSystem, "Please enter email");
                return;
            }
            String message = connection.forgotPassword(new ForgotPassword(username, email));
            if (message.contains("false")) {
                JOptionPane.showMessageDialog(frmLoginSystem, "You password is" + message);
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
            }
            else {
                JOptionPane.showMessageDialog(frmLoginSystem,"username and email are wrong");
            }
        });

        JButton toDoListButton = new JButton("To Do List");
        toDoListButton.setBounds(500, 250, 140, 20);
        frmLoginSystem.getContentPane().add(toDoListButton);
        toDoListButton.addActionListener(e -> {
            if (isLoggedIn) {
                try {
                    TaskListByUser.launch();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frmLoginSystem.dispose();
            }
            else
                JOptionPane.showMessageDialog(frmLoginSystem, "You are not logged in");
        });

        JButton todayInHistoryButton = new JButton("Today in History");
        todayInHistoryButton.setBounds(500, 300, 140, 20);
        frmLoginSystem.getContentPane().add(todayInHistoryButton);
        todayInHistoryButton.addActionListener(e -> {
            if (isLoggedIn) {
                TodayInHistoryWidget.launch();
                frmLoginSystem.dispose();
            }
            else
                JOptionPane.showMessageDialog(frmLoginSystem, "You are not logged in");
        });

        JButton calendarButton = new JButton("Calendar");
        calendarButton.setBounds(500, 350, 90, 20);
        frmLoginSystem.getContentPane().add(calendarButton);
        calendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLoggedIn) {
                    try {
                        SwingCalendar.launch();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    frmLoginSystem.dispose();
                }
                else
                    JOptionPane.showMessageDialog(frmLoginSystem, "You are not logged in");
            }
        });

        JButton pictureAlbumButton = new JButton("Picture Album");
        pictureAlbumButton.setBounds(500, 400, 120, 20);
        frmLoginSystem.getContentPane().add(pictureAlbumButton);
        pictureAlbumButton.addActionListener(e -> {
            if (isLoggedIn) {
                //PictureAlbum_GUI.launch();
                frmLoginSystem.dispose();
            }
            else
                JOptionPane.showMessageDialog(frmLoginSystem, "You are not logged in");
        });

    }

    public void setVisible(boolean visible) {
        this.frmLoginSystem.setVisible(visible);
    }
}

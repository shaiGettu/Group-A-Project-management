import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import java.awt.Canvas;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.TextArea;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class toDolist {

	private JFrame frmToDoList;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					toDolist window = new toDolist();
					window.frmToDoList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public toDolist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmToDoList = new JFrame();
		frmToDoList.setTitle("To Do List");
		frmToDoList.getContentPane().setBackground(Color.GRAY);
		frmToDoList.setResizable(false);
		frmToDoList.setBounds(100, 100, 787, 373);
		frmToDoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmToDoList.getContentPane().setLayout(null);
		
		JLabel lblToDoList = new JLabel("  To Do List  ");
		lblToDoList.setBounds(295, 0, 142, 25);
		lblToDoList.setForeground(Color.PINK);
		lblToDoList.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		frmToDoList.getContentPane().add(lblToDoList);
		
		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.setBackground(Color.ORANGE);
		btnLoadData.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnLoadData.setBounds(15, 37, 115, 29);
		frmToDoList.getContentPane().add(btnLoadData);
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.setBackground(Color.ORANGE);
		btnAddTask.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnAddTask.setBounds(145, 37, 115, 29);
		frmToDoList.getContentPane().add(btnAddTask);
		
		JButton btnApplyUpdates = new JButton("Apply Updates");
		btnApplyUpdates.setBackground(Color.RED);
		btnApplyUpdates.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnApplyUpdates.setBounds(534, 37, 199, 29);
		frmToDoList.getContentPane().add(btnApplyUpdates);
		
		JButton btnDeleteTask = new JButton("Delete Task");
		btnDeleteTask.setBackground(Color.ORANGE);
		btnDeleteTask.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnDeleteTask.setBounds(405, 37, 115, 29);
		frmToDoList.getContentPane().add(btnDeleteTask);
		
		JButton btnEditTesk = new JButton("Edit Tesk");
		btnEditTesk.setBackground(Color.ORANGE);
		btnEditTesk.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnEditTesk.setBounds(275, 37, 115, 29);
		frmToDoList.getContentPane().add(btnEditTesk);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(15, 82, 505, 26);
		frmToDoList.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Apply Filter");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setBounds(534, 82, 199, 29);
		frmToDoList.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(730, 316, -680, -195);
		frmToDoList.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setBounds(15, 129, 718, 188);
		frmToDoList.getContentPane().add(table);
	}
}

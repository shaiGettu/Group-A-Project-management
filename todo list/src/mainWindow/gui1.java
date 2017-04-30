package mainWindow;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class gui1 extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui1 frame = new gui1();
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
	public gui1() {
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton OpenButton = new JButton("Open");
		OpenButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void openButtonActionPerformed(java.awt.event.ActionEvent evt) {
				
			//open the source file of the mp3 music 
				JFileChooser fileChooser = new JFileChooser();
				@SuppressWarnings("unused")
				int result = fileChooser.showOpenDialog(null);
				File selectedFile = fileChooser.getSelectedFile();
				
				System.out.println("Selected File: " + selectedFile.getAbsolutePath());
			//convert the file type to mp3
			}

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(OpenButton);
		
		JToggleButton PlayButton = new JToggleButton("Play");
		panel.add(PlayButton);
		
		JToggleButton StopButton = new JToggleButton("Stop");
		panel.add(StopButton);
		
		JToggleButton NextButton = new JToggleButton("Next >>");
		panel.add(NextButton);
		
		JLabel lblNewLabel = new JLabel("       My Mp3 Player");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Ravie", Font.BOLD, 23));
		lblNewLabel.setBackground(Color.DARK_GRAY);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
	}

}

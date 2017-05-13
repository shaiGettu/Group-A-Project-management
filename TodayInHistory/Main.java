import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.Color;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main mainWindow = new Main();
					mainWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Today in History");
		frame.getContentPane().setBackground(new Color(204, 153, 255));
		frame.setBounds(100, 100, 714, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Today in History");
		label.setFont(new Font("Algerian", Font.BOLD, 26));
		label.setBounds(247, 27, 2070, 200);
		frame.getContentPane().add(label);
		
		String months[] = {"January", "February", "March", "April", "May", "June", "July", 
							"August", "September", "October", "November", "December"};
		GregorianCalendar calendar = new GregorianCalendar();
		String currentMonth = months[calendar.get(Calendar.MONTH)];
		int currentDay = calendar.get(Calendar.DATE);
		
		JButton goButton = new JButton("Go");
		goButton.setBounds(500, 27, 90, 20);
		frame.getContentPane().add(goButton);
		
		goButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columns = new String[] {"Year", "Event"};
				String[] result = connectToPHP(new Today(currentMonth, currentDay));
				Object[][] data = new Object[result.length][2];
				for (int i = 0; i < result.length; i++) {
					String[] splitted = result[i].split("u2013");
					data[i][0] = splitted[0];
					data[i][1] = splitted[1];
				}
				JTable table = new JTable(data, columns);
				JScrollPane pane = new JScrollPane(table);
				pane.setBounds(60, 50, 900, 900);
				frame.getContentPane().add(pane);
			}          
	      });
		
	}

	protected String[] connectToPHP(Today today) {
		try {	
			Gson gsonToSend = new GsonBuilder().create();
			String jsonOutput = gsonToSend.toJson(today);
			// send HTTP GET request
			URL url = new URL("http://localhost/myfiles/TodayInHistory/today_in_history.php?"
					+ "today=" + jsonOutput);
			URLConnection conn = url.openConnection();			 
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				String segments[] = line.split(",");
				String[] array = parse(segments);
				return array;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String[] parse(String[] segments) {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(segments));
		for (int i = 5; i < list.size(); i++) {
			if (list.get(i).startsWith(" ")) {
				String temp = list.get(i);
				list.set(i - 1, list.get(i - 1) + "," + temp);
				list.remove(i);
				i--;
			}
		}
		for (int i = 5; i < list.size(); i++) {
			if (!list.get(i).contains("2013")) {
				String temp = list.get(i);
				list.set(i - 1, list.get(i - 1) + "," + temp);
				list.remove(i);
				i--;
			}
		}
		for (int i = 5; i < list.size(); i++) {
			list.set(i, list.get(i).substring(1));
		}
		int endIndex = -1;
		boolean isContinue = true;
		for (int i = 5; i < list.size() - 1 && isContinue; i++) {
			String arr1[] = list.get(i).split(" ");
			String temp1 = arr1[0];
			String arr2[] = list.get(i + 1).split(" ");
			String temp2 = arr2[0];
			if (Integer.parseInt(temp1) > Integer.parseInt(temp2)) {
				endIndex = i;
				isContinue = false;
			}
		}
		String array[] = new String[endIndex - 4];
		for (int i = 5; i <= endIndex; i++) {
			array[i - 5] = list.get(i);
		}
		return array;
	}
}


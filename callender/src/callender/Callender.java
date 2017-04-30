package callender;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Font;

public class Callender {

	private JFrame frmCallender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Callender window = new Callender();
					window.frmCallender.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Callender() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCallender = new JFrame();
		frmCallender.setTitle("Callender");
		frmCallender.setBounds(100, 100, 835, 454);
		frmCallender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCallender.getContentPane().setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.getMonthChooser().getComboBox().setBackground(new Color(255, 245, 238));
		calendar.getDayChooser().setForeground(new Color(178, 34, 34));
		calendar.getDayChooser().setDecorationBackgroundColor(new Color(255, 182, 193));
		calendar.getDayChooser().setBackground(new Color(221, 160, 221));
		calendar.getDayChooser().getDayPanel().setForeground(new Color(176, 224, 230));
		calendar.getYearChooser().getSpinner().setFont(new Font("Tahoma", Font.BOLD, 20));
		calendar.getMonthChooser().getComboBox().setFont(new Font("Tahoma", Font.BOLD, 18));
		calendar.getDayChooser().getDayPanel().setBackground(new Color(255, 250, 205));
		calendar.setBounds(81, 36, 564, 346);
		frmCallender.getContentPane().add(calendar);
	}
}

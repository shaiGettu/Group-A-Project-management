package calendar;

/**
 * Created by Shai Gettu on 03-Jun-17.
 */
import login.MainScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class SwingCalendar extends JFrame {

    private DefaultTableModel model;
    private Calendar cal = new GregorianCalendar();
    private JLabel label;
    private String jsonIL, jsonUS;
    private String usHolidays[];
    private String[] ilHolidays;
    private String[] usDates;
    private String[] ilDates;

    SwingCalendar() throws IOException {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Swing Calandar");
        this.setSize(950,290);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JButton b1 = new JButton("<-");
        b1.addActionListener(ae -> {
            cal.add(Calendar.MONTH, -1);
            try {
                updateMonth();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JButton b2 = new JButton("->");
        b2.addActionListener(ae -> {
            cal.add(Calendar.MONTH, +1);
            try {
                updateMonth();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        JButton jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {
            this.dispose();
            MainScreen.getInstance().setVisible(true);
        });
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());
        panel.add(b1,BorderLayout.WEST);
        panel.add(panel1,BorderLayout.CENTER);
        panel1.add(label, BorderLayout.CENTER);
        panel1.add(jButtonBack, BorderLayout.WEST);
        panel.add(b2,BorderLayout.EAST);
        String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        model = new DefaultTableModel(null,columns);
        JTable table = new JTable(model);
        table.setRowHeight(table.getRowHeight()+25);
        table.setFont(new Font("David",Font.BOLD,13));
        table.setDefaultEditor(Object.class,null);
        JScrollPane pane = new JScrollPane(table);
        this.add(panel,BorderLayout.NORTH);
        this.add(pane,BorderLayout.CENTER);
        this.updateMonth();
    }

    private void initDates() {
        usDates = jsonUS.split("date");
        int i = 0;
        for (String string:usDates){
            if(string.substring(0,3).equals("\":\"")){
                usDates[i] = string.substring(3);
                String s[] = usDates[i].split(",");
                usDates[i] = s[0];
                s = usDates[i].split("-");
                usDates[i] = s[2];
                if(usDates[i].substring(0,1).equals("0")){
                    usDates[i] = usDates[i].substring(1,2);
                }
                else {
                    usDates[i] = usDates[i].substring(0, 2);
                }
            }
            ++i;
        }
        ilDates = jsonIL.split("date");
        i = 0;
        for (String string:ilDates){
            if(string.substring(0,3).equals("\":\"")){
                ilDates[i] = string.substring(3);
                String s[] = ilDates[i].split(",");
                ilDates[i] = s[0];
                s = ilDates[i].split("-");
                ilDates[i] = s[2];
                if(ilDates[i].substring(0,1).equals("0")){
                    ilDates[i] = ilDates[i].substring(1,2);
                }
                else {
                    ilDates[i] = ilDates[i].substring(0, 2);
                }
            }
            ++i;
        }
    }

    private String connectServer(int month, int year, String country) throws IOException {
        URL url = new URL("http://localhost/PHP/Calender/index.php");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        PrintStream ps = new PrintStream(connection.getOutputStream());
        ps.print("&country="+country);
        ps.print("&year="+year);
        ps.print("&month="+month);
        connection.getInputStream();
        ps.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        String line1;
        while ((line1 = in.readLine()) != null) {
            line = line1;
        }
        return line;
    }

    void updateMonth() throws IOException {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);
        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
        ////// Json Decoder
        jsonIL = connectServer(cal.get(Calendar.MONTH) + 1 , year, "IL");
        jsonUS = connectServer(cal.get(Calendar.MONTH) + 1 , year, "US");
        initHolidays();
        initDates();
        ////// end
        model.setRowCount(0);
        model.setRowCount(weeks);
        int i = startDay-1;
        String dayString;
        for(int day=1;day<=numberOfDays;day++){
            dayString = day + "\n  " + holidays(day,month);
            if(dayString.length()>=20){

            }
            model.setValueAt(dayString, i/7 , i%7 );
            i = i + 1;
        }
    }

    private String holidays(int day, String month) {
        if(month.equals(cal.get(Calendar.MONTH) + 1)){
            return "";
        }
        String Holidays = "";
        for (int i = 1;i<usDates.length; ++i){
            String s = usDates[i];
            if(s.equals(String.valueOf(day))){
                Holidays+="\n"+usHolidays[i-1];
            }
        }
        for (int i = 1;i<ilDates.length; ++i){
            if(ilDates[i].equals(String.valueOf(day))){
                Holidays+="\n"+ilHolidays[i-1];
            }
        }
        return Holidays;
    }

    private void initHolidays() throws IOException {

        usHolidays = jsonUS.split("name");
        int i=0;
        for (String string:usHolidays){
            if(string.substring(0,3).equals("\":\"")) {
                usHolidays[i] = string.substring(3);
                String s[] = usHolidays[i].split(",");
                usHolidays[i] = s[0];
                ++i;
            }
        }
        ilHolidays = jsonIL.split("name");
        i=0;
        for (String string:ilHolidays){
            if(string.substring(0,3).equals("\":\"")) {
                ilHolidays[i] = string.substring(3);
                String s[] = ilHolidays[i].split(",");
                ilHolidays[i] = s[0];
                ++i;
            }
        }
    }

    public static void launch() throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new SwingCalendar();
    }
}


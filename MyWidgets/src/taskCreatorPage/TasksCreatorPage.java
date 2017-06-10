package taskCreatorPage;
import taskListPage.TaskListByUser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Shai Gettu on 21-Apr-17.
 */
public class TasksCreatorPage extends JFrame {
    private JPanel top;
    private JPanel bottom;
    private JButton submitTaskButton;
    private JTextField TextFieldTaskTitle;
    private JPanel TaskCreator;
    private JTextField TextFieldDueDate;
    private JButton cancelButton;
    private JTextArea TextFieldText;
    private JComboBox ComboBoxPriority;
    private JComboBox ComboBoxStatus;
    private JFrame creatorFrame;
    private static JFrame staticFrame;
    private static int count=0;

    public JFrame getCreatorFrame() {
        return creatorFrame;
    }

    public TasksCreatorPage() {
        initialize();
        creatorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        submitTaskButton.addActionListener(e-> {
            String title = TextFieldTaskTitle.getText();
            String dueDate = TextFieldDueDate.getText();
            String text = TextFieldText.getText();
            String priority = (String )ComboBoxPriority.getSelectedItem();
            String Status= (String )ComboBoxStatus.getSelectedItem();
            if (!isValidDate(dueDate)) {
                JOptionPane.showMessageDialog(null, "Wrong date");
            } else if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter the task");
            }
            else if(title.isEmpty()){
                JOptionPane.showMessageDialog(null,"Enter title of the task");
            }
            else {
                try {
                    String  result = AddTask(TaskListByUser.getStaticEmail(),title, dueDate, text, priority,Status);
                    if(result.equals("Task stored")) {
                        new TaskListByUser();
                        creatorFrame.dispose();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        cancelButton.addActionListener(e-> {
            try {
                new TaskListByUser();
                creatorFrame.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void initialize(){
        if(count!=0) {
            staticFrame.dispose();
        }
        creatorFrame = new JFrame("Create task");
        creatorFrame.setContentPane(TaskCreator);
        creatorFrame.setSize(500, 500);
        creatorFrame.setVisible(true);
        count = 1;
        staticFrame=getCreatorFrame();
    }

    /**
     * Open source from:
     * http://stackoverflow.com/questions/11480542/fastest-way-to-tell-if-a-string-is-a-valid-date
     *
     * @param dateString
     * @return if it's valid date
     */
    public static boolean isValidDate(String dateString) {
        if (dateString == null || dateString.length() != "dd/MM/yyyy".length()) {
            return false;
        }
        //Changed format from the original
        String date1 = dateString.substring(6, 10) + dateString.substring(3, 5) + dateString.substring(0, 2);
        dateString = date1;

        int date;
        try {
            date = Integer.parseInt(dateString);
        } catch (NumberFormatException e) {
            return false;
        }

        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        // leap years calculation not valid before 1581
        boolean yearOk = year >= 2017;
        boolean monthOk = (month >= 1) && (month <= 12);
        boolean dayOk = (day >= 1) && (day <= daysInMonth(year, month));

        return (yearOk && monthOk && dayOk);
    }

    private static int daysInMonth(int year, int month) {
        int daysInMonth;
        switch (month) {
            case 1: // fall through
            case 3: // fall through
            case 5: // fall through
            case 7: // fall through
            case 8: // fall through
            case 10: // fall through
            case 12:
                daysInMonth = 31;
                break;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
                break;
            default:
                // returns 30 even for nonexistant months
                daysInMonth = 30;
        }
        return daysInMonth;
    }

    private String AddTask(String user, String title, String dueDate, String text, String priority,String Status) throws IOException {

        String type = "application/x-www-form-urlencoded";
        URL url = new URL("http://localhost/PHP/ToDoList/include/CreateTask.php");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        PrintStream ps = new PrintStream(connection.getOutputStream());
        ps.print("u=" + user);
        ps.print("&ti=" + title);
        ps.print("&du=" + dueDate);
        ps.print("&te=" + text);
        ps.print("&p=" + priority);
        ps.print("&s="+Status);
        connection.getInputStream();
        ps.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        String line1;
        while ((line1 = in.readLine()) != null) {
            line = line1;
        }
        System.out.println(line);
        if (line.substring(9, 14).equals("false")) {
            JOptionPane.showMessageDialog(null, "task stored");
            TextFieldTaskTitle.setText(null);
            TextFieldDueDate.setText(null);
            TextFieldText.setText(null);
            ComboBoxPriority.setSelectedItem("Low");
            ComboBoxStatus.setSelectedItem("Progress");
            return "Task stored";
        }
        else if (line.contains("\"Task already exist\"")) {
            JOptionPane.showMessageDialog(null, "task already existed");
            return "Task already exist";
        }
        else
        {
            return "Unknown error occurred in storing task";
        }
    }
}

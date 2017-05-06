package viewTaskPage;

import org.json.simple.JSONObject;
import taskListPage.TaskListByUser;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by HP on 04-May-17.
 */
public class ViewTaskPage {
    private JPanel Top;
    private JPanel Bottom;
    private JLabel JLabelUserName;
    private JButton JButtonBack;
    private JPanel Center;
    private JLabel JLabelTitle;
    private JLabel JLabelPriority;
    private JLabel JLabelText;
    private JLabel JLabelDate;
    private JPanel ViewTask;
    private JLabel JLabelStatus;
    private JButton JButtonDone;
    private JFrame viewFrame;
    private static int count=0;
    private JSONObject json;
    private static JFrame staticFrame;
    private static boolean doneTicket;

    public static void setDoneTicket(boolean doneTicket) {
        ViewTaskPage.doneTicket = doneTicket;
    }

    public static boolean isDoneTicket() {
        return doneTicket;
    }

    public JFrame getViewFrame() {
        return viewFrame;
    }

    public ViewTaskPage(){
        initialize();

        JButtonBack.addActionListener(e -> {
            try {
                new TaskListByUser();
                viewFrame.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        JButtonDone.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"Task completed!");
            doneTicket=true;
            try {
                new TaskListByUser();
                viewFrame.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    private void initialize(){
        if(count!=0){
            staticFrame.dispose();
        }
        viewFrame = new JFrame("Task");
        viewFrame.setContentPane(ViewTask);
        viewFrame.setSize(500, 500);
        viewFrame.setVisible(true);
        staticFrame=getViewFrame();
        count=1;
        json=TaskListByUser.getJson();
        String title="title"+TaskListByUser.getNumberTaskToView();
        String date="dueDate"+TaskListByUser.getNumberTaskToView();
        String text="textDetails"+TaskListByUser.getNumberTaskToView();
        String priority="priority"+TaskListByUser.getNumberTaskToView();
        String status="status"+TaskListByUser.getNumberTaskToView();
        JLabelTitle.setText((String)json.get(title));
        JLabelDate.setText((String)json.get(date));
        JLabelText.setText((String)json.get(text));
        JLabelPriority.setText((String)json.get(priority));
        status=(String)json.get(status);
        JLabelStatus.setText(status);
        if(status.equals("Done")||status.equals("Expired")) {
            JButtonDone.setVisible(false);
        }
        doneTicket=false;
    }
}

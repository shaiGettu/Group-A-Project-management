package taskListPage;

import login.MainScreen;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import taskCreatorPage.TasksCreatorPage;
import viewTaskPage.ViewTaskPage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by HP on 01-May-17.
 */
public class TaskListByUser extends JFrame {
    private JPanel Top;
    private JPanel Left;
    private JPanel Bottom;
    private JButton JButtonView1;
    private JButton JButtonView2;
    private JButton JButtonView4;
    private JButton JButtonView3;
    private JButton viewButtons[];
    private JButton BackButton;
    private JLabel JLabelUser;
    private JLabel JLabelTitle4;
    private JLabel JLabelTitle1;
    private JLabel JLabelTitle2;
    private JLabel JLabelTitle3;
    private JLabel labels[];
    private JFrame mainFrame;
    private JPanel Main;
    private JPanel Right;
    private JButton JButtonDelete1;
    private JButton JButtonDelete2;
    private JButton JButtonDelete4;
    private JButton JButtonDelete3;
    private JButton deleteButtons[];
    private JButton createTaskButton;
    private static JFrame staticFrame;
    private static int count=0;
    private static int numberTaskToView;
    private static JSONObject json;
    private static String staticEmail;


    public static void setNumberTaskToView(int numberTaskToView) {
        TaskListByUser.numberTaskToView = numberTaskToView;
    }

    public static int getNumberTaskToView(){
        return numberTaskToView;
    }

    public static JSONObject getJson() {
        return json;
    }

    public static String getStaticEmail() {
        return staticEmail;
    }

    public static void setStaticEmail(String email){
        staticEmail = email;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public TaskListByUser() throws IOException {
        initialize();
        createTaskButton.addActionListener(e -> {
            long amount=(long)json.get("amountTasks");
            System.out.println(amount);
            if(amount<4) {
                new TasksCreatorPage();
                mainFrame.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"Maximum tasks stored");
            }
        });
        BackButton.addActionListener(e -> {
            MainScreen.getInstance().setVisible(true);
            mainFrame.dispose();
        });
        JButtonDelete1.addActionListener(e -> {
                try {
                    deleteTask("title1");

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        });

        JButtonDelete2.addActionListener(e ->  {
                try {
                    deleteTask("title2");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        });

        JButtonDelete3.addActionListener(e -> {
            try {
                deleteTask("title3");
            } catch (IOException e1){
                e1.printStackTrace();
            }
        });

        JButtonDelete4.addActionListener(e -> {
            try {
                deleteTask("title4");
            } catch (IOException e1){
                e1.printStackTrace();
            }
        });

        JButtonView1.addActionListener(e -> {TaskListByUser.setNumberTaskToView(1); new ViewTaskPage(); mainFrame.dispose();});

        JButtonView2.addActionListener(e -> {TaskListByUser.setNumberTaskToView(2); new ViewTaskPage(); mainFrame.dispose();});

        JButtonView3.addActionListener(e -> {TaskListByUser.setNumberTaskToView(3); new ViewTaskPage(); mainFrame.dispose();});

        JButtonView4.addActionListener(e -> {TaskListByUser.setNumberTaskToView(4); new ViewTaskPage(); mainFrame.dispose();});

    }

    private void deleteTask(String title) throws IOException {
        String response = connectServer("DeleteTask", "t="+ json.get(title));
        Object object= JSONValue.parse(response);
        JSONObject res=(JSONObject)object;
        System.out.println(res.toString());
        boolean flag= (boolean) res.get("error");
        if(!flag){
            JOptionPane.showMessageDialog(null,"Task deleted");
        }
        mainFrame.dispose();
        new TaskListByUser();
    }

    private void initialize() throws IOException {
        JLabelUser.setText(staticEmail);
        if(ViewTaskPage.isDoneTicket()){
            String s;
            s=connectServer("ChangeStatus","t="+json.get("title"+numberTaskToView));
            System.out.println(s);
            ViewTaskPage.setDoneTicket(false);
        }
        viewButtons=new JButton[4];
        viewButtons[0]=JButtonView1;
        viewButtons[1]=JButtonView2;
        viewButtons[2]=JButtonView3;
        viewButtons[3]=JButtonView4;
        deleteButtons=new JButton[4];
        deleteButtons[0]=JButtonDelete1;
        deleteButtons[1]=JButtonDelete2;
        deleteButtons[2]=JButtonDelete3;
        deleteButtons[3]=JButtonDelete4;
        labels=new JLabel[4];
        labels[0]=JLabelTitle1;
        labels[1]=JLabelTitle2;
        labels[2]=JLabelTitle3;
        labels[3]=JLabelTitle4;
        if(count!=0){
            staticFrame.dispose();
        }
        mainFrame = new JFrame("Tasks List");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setContentPane(Main);
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
        staticFrame=getMainFrame();
        count=1;
        String line = connectServer("GetTasks","u="+staticEmail);
        System.out.println(line);
        Object object= JSONValue.parse(line);
        json=(JSONObject)object;
        jsonDecoder(json);
    }

    private String connectServer(String phpFile,String send) throws IOException {

        URL url = new URL("http://localhost/PHP/ToDoList/include/"+phpFile+".php");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        PrintStream ps = new PrintStream(connection.getOutputStream());
        ps.print(send);
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

    private void jsonDecoder(JSONObject line){

        for(int i=1;i<=4;++i) {
            String title=(String) line.get("title"+i);
            if(title==null){
                viewButtons[i-1].setVisible(false);
                deleteButtons[i-1].setVisible(false);
            }
            else
                labels[i-1].setText(title);
        }
    }

    public static void launch() throws IOException {
        new TaskListByUser();
    }
}

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class OnThisDay extends JFrame {
    private JPanel list;

    public OnThisDay(List<String> str) { // TODO: get List of history objects, List<History> historyList
        this.setTitle("On this day... " + (new SimpleDateFormat("dd/M")).format(new Date()));
        this.setVisible(true);
        this.setSize(600, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        list = new JPanel();
        list.setBackground(Color.WHITE);
        list.setLayout(new BoxLayout(list, BoxLayout.PAGE_AXIS));
        this.add(list);

        for (String i : str) { // History i : historyList
            list.add(new JLabel("\u2022 " + i));
        }
    }

    public static void main(String[] args) {
        ArrayList<String> lst = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            lst.add(((Integer) i).toString ());
        }
        new OnThisDay(lst);
    }
}
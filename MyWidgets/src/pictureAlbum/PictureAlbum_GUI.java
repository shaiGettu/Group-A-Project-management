package pictureAlbum; /**
 * Created by Shai Gettu on 27-May-17.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class PictureAlbum_GUI extends JPanel {


    private BufferedImage image;
    private JButton jButtonPPic, jButtonNPic, jButtonClear, jButtonUpload, jButtonDownload, jButtonVerify;
    private String json;
    File selected;

    public PictureAlbum_GUI() {
        setSize(350, 350);
        setLayout(new BorderLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.weighty = 1;
        jButtonPPic = new JButton("<-");
        jButtonNPic = new JButton("->");
        jButtonClear = new JButton("Clear");
        jButtonUpload = new JButton("Upload an image");
        jButtonDownload = new JButton("Get photos");
        jButtonVerify = new JButton("Verify upload");
        JPanel jPanel = new JPanel();
        add("West",jPanel);
        jPanel.add("Center",jButtonPPic);
        JPanel panel1 = new JPanel();
        add(BorderLayout.EAST, panel1);
        panel1.add(BorderLayout.SOUTH, jButtonNPic);
        JPanel panel = new JPanel();
        panel.setLayout( new GridLayout(1, 4));
        add("South", panel);
        panel.add(jButtonClear);
        panel.add(jButtonUpload);
        panel.add(jButtonDownload);
        panel.add(jButtonVerify);
        jButtonClear.setVisible(true);
        jButtonUpload.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showDialog(new JFrame("file selector"), "choose");
            if (result == JFileChooser.APPROVE_OPTION) {
                selected = fileChooser.getSelectedFile();
            }
            if (selected == null)
                return;
            try {
                image = ImageIO.read(selected);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null,"The file is not image");
            }
            repaint();
        });
        jButtonClear.addActionListener(e -> {
            image = null;
        });
        jButtonDownload.addActionListener(e -> {
            try {
                json = connectServer("g", null,"shai");
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null,"Server problem");
            }
            System.out.println(json);
        });
        jButtonVerify.addActionListener(e -> {
            if(image!=null) {
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    String string = selected.getAbsoluteFile().toString();
                    ImageIO.write(image, string.substring(string.length()-3), baos);
                    baos.flush();
                    byte[] imageInByte = baos.toByteArray();
                    baos.close();
                    String base64 = Base64.getEncoder().encodeToString(imageInByte);
                    System.out.println(imageInByte);
                    json = connectServer("s", base64, "shai");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Server problem");
                }
                System.out.println(json);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 700, 600, this);
        repaint();
    }
    private String connectServer(String method, String path, String user) throws IOException {
        URL url = new URL("http://localhost/PHP/PictureAlbum/pictureAlbum.php");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        PrintStream ps = new PrintStream(connection.getOutputStream());
        ps.print("&method="+method);
        ps.print("&user="+user);
        if(method.equals("s")) {
            ps.print("&picture=" + path);
        }
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Picture Album");
        frame.setLayout(new BorderLayout());
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PictureAlbum_GUI pa = new PictureAlbum_GUI();
        frame.add(pa);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}
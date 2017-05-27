package PictureAlbum;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class PictureAlbumWidget extends JFrame {
	
	private JPanel contentPane;

    /**
     * Create the frame.
     */
    public PictureAlbumWidget() {
        super("Picture Album");
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 565, 411);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLayeredPane layeredPane = new JLayeredPane();
        contentPane.add(layeredPane, BorderLayout.CENTER);
        JButton btnNewButton = new JButton("<-");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(10, 144, 61, 42);
        layeredPane.add(btnNewButton);

        JButton btnNext = new JButton("->");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnNext.setBounds(468, 144, 61, 42);
        layeredPane.add(btnNext);


        JButton btnFullScren = new JButton("Full screen");
        btnFullScren.setBounds(421, 11, 108, 23);
        layeredPane.add(btnFullScren);

        JButton btnUploadAnImage = new JButton("Upload an image");
        btnUploadAnImage.setBounds(280, 11, 131, 23);
        layeredPane.add(btnUploadAnImage);


        JButton btnNewButton_1 = new JButton("Download ");
        btnNewButton_1.setBounds(156, 11, 114, 23);
        layeredPane.add(btnNewButton_1);

        JButton btnSetDate = new JButton("Set date");
        btnSetDate.setBounds(65, 328, 89, 23);
        layeredPane.add(btnSetDate);
    }
    
    public static void launch() {
    	PictureAlbumWidget pa = new PictureAlbumWidget();
    }

}


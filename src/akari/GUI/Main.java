package akari.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel  = new JPanel();
        frame.getContentPane();
        JLabel label = new JLabel(new ImageIcon("graphics/test_tile.png"));
        Dimension size = label.getPreferredSize();
        label.setBounds(10,10,size.width,size.height);
        panel.setLayout(null);
        panel.add(label);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        panel.setBackground(Color.GRAY);

        label.setLocation(0,0);






        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX()+ " " + e.getY());
            }
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);


    }
}

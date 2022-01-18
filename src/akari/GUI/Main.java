package akari.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args)  {

        int X = 10;
        int Y = 10;
        JFrame frame = new JFrame();
        JPanel panel  = new JPanel();
        frame.getContentPane();

        ImageIcon tile1 = new ImageIcon("graphics/test_tile1.png");
        ImageIcon tile2 = new ImageIcon("graphics/test_tile2.png");


        Dimension size = new Dimension(40,40);

        panel.setLayout(null);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.GRAY);

        JLabel[][] pictures = new JLabel[10][10];
        final boolean[][] color = new boolean[X][Y];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pictures[i][j] = new JLabel(tile1);
                pictures[i][j].setBounds(0,0,size.width,size.height);
                panel.add(pictures[i][j]);
                pictures[i][j].setLocation(41*i+1,41*j+1);
                int finalI = i;
                int finalJ = j;
                pictures[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println(finalI+ " " + finalJ);
                        if(color[finalI][finalJ]){
                            pictures[finalI][finalJ].setIcon(new ImageIcon("graphics/test_tile1.png"));
                            color[finalI][finalJ] = false;
                        }
                        else{
                            pictures[finalI][finalJ].setIcon(new ImageIcon("graphics/test_tile2.png"));
                            color[finalI][finalJ] = true;
                        }


                    }
                });
            }
        }










        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(409,409);
        frame.setVisible(true);


    }
}

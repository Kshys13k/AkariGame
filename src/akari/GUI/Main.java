package akari.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main {
    public static void main(String[] args)  {

        int X = 10;
        int Y = 10;
        JFrame frame = new JFrame();
        JPanel panel  = new JPanel();
        JPanel panel2 = new JPanel();
 //       frame.getContentPane();
        frame.setResizable(false);
        ImageIcon tile1 = new ImageIcon("graphics/test_tile1.png");
        ImageIcon tile2 = new ImageIcon("graphics/test_tile2.png");


        Dimension size = new Dimension(40,40);

        frame.setSize(409,500);

   //     panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.GRAY);
        panel2.setBackground(Color.GRAY);
        frame.setBackground(Color.GRAY);

        BorderLayout main = new BorderLayout();
        GridLayout test = new GridLayout(10,10,1,1);
        GridLayout test2 = new GridLayout(1,3,1,10);
        frame.setLayout(main);


        panel2.add(new JLabel("1"));
        panel2.add(new JLabel("2"));
        panel2.add(new JLabel("3"));


        panel.setLayout(test);
        panel2.setLayout(test2);
        panel2.setSize(400,100);


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

        frame.add(panel,BorderLayout.NORTH);
        frame.add(panel2,BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(409,409);
        panel.setSize(409,409);
        frame.pack();
        frame.setVisible(true);


    }
}

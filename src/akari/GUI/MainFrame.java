package akari.GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


    public MainFrame(){

        //Panels
        JPanel mainPanel = new JPanel();


        //Labels
        JLabel playLabel = new JLabel("Play");
        JLabel loadLabel = new JLabel("Load");
        JLabel settingsLabel = new JLabel("Settings");

        mainPanel.add(playLabel);
        mainPanel.add(loadLabel);
        mainPanel.add(settingsLabel);


        //Layouts
        BoxLayout mainLayout = new BoxLayout(mainPanel , BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        //MainFrame setup
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setTitle("Akari");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(100,100));
        setBackground(Color.GRAY);
        //mainPanel.setLayout(mainLayout);
        getContentPane().add(mainPanel);




    }
}

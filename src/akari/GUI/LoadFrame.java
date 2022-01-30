package akari.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoadFrame extends JFrame{
    private JLabel loadLogoLabel;
    private JList savesList;
    private JPanel loadPanel;


    public LoadFrame(){
        //basic setup
        setContentPane(loadPanel);
        setTitle("Settings");
        setResizable(false);
        setPreferredSize(new Dimension(400,200) );
        loadPanel.setBackground(Color.GRAY);
        savesList.setBackground(Color.GRAY);


        loadLogoLabel.setIcon(new ImageIcon("graphics/loadLogo.png"));



    }

}

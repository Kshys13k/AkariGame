package akari.GUI;

import javax.swing.*;
import java.awt.*;

public class SettingFrame extends JFrame {
    private JPanel settingPanel;
    private JLabel settingsLogoLabel;



    public SettingFrame(){
        //basic setup
        setContentPane(settingPanel);
        setTitle("Settings");
        setResizable(false);
        setPreferredSize(new Dimension(400,200) );
        settingPanel.setBackground(Color.GRAY);


        settingsLogoLabel.setIcon(new ImageIcon("graphics/settingsLogo.png"));


        //mouse listeners

    }
}

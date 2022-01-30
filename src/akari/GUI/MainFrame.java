package akari.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class MainFrame extends JFrame {

    private boolean isLocked= false;

    private JPanel mainPanel;
    private JLabel akariLogoLAbel;
    private JLabel playLabel;
    private JLabel loadLabel;
    private JLabel settingsLabel;


    private GameFrame gameFrame;
    private int size = 7;



    private float wallsMin = (float) 0.35;
    private float wallsMax = (float) 0.55;
    private float toNumberChance = (float) 0.4;


    private SettingFrame settingFrame;
    private LoadFrame loadFrame;



    public MainFrame(){

        //basic setup
        setContentPane(mainPanel);
        setTitle("Akari");
        setResizable(false);
        setPreferredSize(new Dimension(350,400) );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setBackground(Color.GRAY);

        //graphics loading
        akariLogoLAbel.setIcon(new ImageIcon("graphics/AkariLogo.png"));
        playLabel.setIcon(new ImageIcon("graphics/playButton.png"));
        loadLabel.setIcon(new ImageIcon("graphics/loadButton.png"));
        settingsLabel.setIcon(new ImageIcon("graphics/settingsButton.png"));

        //listeners
        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!isLocked) {
                    isLocked = true;
                    setVisible(false);
                    gameFrame = new GameFrame(size, wallsMin, wallsMax, toNumberChance);
                    gameFrame.pack();
                    gameFrame.setVisible(true);
                    gameFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            isLocked = false;
                            setVisible(true);
                            super.windowClosing(e);
                        }
                    });
                }
            }
        });

        loadLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!isLocked) {
                    isLocked = true;
                    setVisible(false);
                    loadFrame = new LoadFrame();
                    loadFrame.pack();
                    loadFrame.setVisible(true);
                    loadFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            isLocked = false;
                            setVisible(true);
                            super.windowClosing(e);
                        }
                    });
                }
            }
        });

        settingsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!isLocked) {
                    isLocked = true;
                    setVisible(false);
                    settingFrame = new SettingFrame();
                    settingFrame.pack();
                    settingFrame.setVisible(true);
                    settingFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            isLocked = false;
                            setVisible(true);
                            super.windowClosing(e);
                        }
                    });
                }
            }

        });


    }


    public void setSize(int size) {
        this.size = size;
    }

    public void setWallsMin(float wallsMin) {
        this.wallsMin = wallsMin;
    }

    public void setWallsMax(float wallsMax) {
        this.wallsMax = wallsMax;
    }

    public void setToNumberChance(float toNumberChance) {
        this.toNumberChance = toNumberChance;
    }

}

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
    private int size;
    private float wallsMin;
    private float wallsMax;
    private float toNumberChance;


    private SettingsFrame settingFrame;
    private LoadFrame loadFrame;



    public MainFrame(){

        //basic setup
        setContentPane(mainPanel);
        setTitle("Akari");
        setResizable(false);
        setPreferredSize(new Dimension(350,400) );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setBackground(Color.GRAY);

        setNormalDifficulty();

        //graphics loading
        akariLogoLAbel.setIcon(new ImageIcon("graphics/mainFrame/AkariLogo.png"));
        playLabel.setIcon(new ImageIcon("graphics/mainFrame/playButton.png"));
        loadLabel.setIcon(new ImageIcon("graphics/mainFrame/loadButton.png"));
        settingsLabel.setIcon(new ImageIcon("graphics/mainFrame/settingsButton.png"));

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
                    settingFrame = new SettingsFrame();
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
    private void setEasyDifficulty(){
        MainFrame.this.setSize(9);
        MainFrame.this.setWallsMin((float) 0.1);
        MainFrame.this.setWallsMax((float) 0.4);
        MainFrame.this.setToNumberChance((float) 0.3);

    }
    private void setNormalDifficulty(){
        MainFrame.this.setSize(11);
        MainFrame.this.setWallsMin((float) 0.2);
        MainFrame.this.setWallsMax((float) 0.5);
        MainFrame.this.setToNumberChance((float) 0.5);
    }
    private void setHardDifficulty(){
        MainFrame.this.setSize(15);
        MainFrame.this.setWallsMin((float) 0.3);
        MainFrame.this.setWallsMax((float) 0.6);
        MainFrame.this.setToNumberChance((float) 0.6);
    }

    private class SettingsFrame extends JFrame {
        private JPanel settingPanel;
        private JLabel settingsLogoLabel;
        private JLabel easyLabel;
        private JLabel normalLabel;
        private JLabel hardLabel;


        public SettingsFrame(){
            //basic setup
            setContentPane(settingPanel);
            setTitle("Settings");
            setResizable(false);
            setPreferredSize(new Dimension(400,200) );
            settingPanel.setBackground(Color.GRAY);


            settingsLogoLabel.setIcon(new ImageIcon("graphics/settingsFrame/settingsLogo.png"));
            easyLabel.setIcon(new ImageIcon("graphics/settingsFrame/easyButton.png"));
            normalLabel.setIcon(new ImageIcon("graphics/settingsFrame/normalButton.png"));
            hardLabel.setIcon(new ImageIcon("graphics/settingsFrame/hardButton.png"));


            //mouse listeners for difficulty settings
            easyLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    MainFrame.this.setEasyDifficulty();
                    MainFrame.this.isLocked = false;
                    MainFrame.this.setVisible(true);
                    setVisible(false);
                }
            });
            normalLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    MainFrame.this.setNormalDifficulty();
                    MainFrame.this.isLocked = false;
                    MainFrame.this.setVisible(true);
                    setVisible(false);
                }
            });
            hardLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    MainFrame.this.setHardDifficulty();
                    MainFrame.this.isLocked = false;
                    MainFrame.this.setVisible(true);
                    setVisible(false);
                }
            });

        }
    }

    private class LoadFrame extends JFrame{
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


            loadLogoLabel.setIcon(new ImageIcon("graphics/loadFrame/loadLogo.png"));



        }

    }
}

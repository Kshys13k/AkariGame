package akari.GUI;

import akari.model.Engine;
import akari.model.SaveBoard;
import akari.model.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;

public final class GameFrame extends JFrame {


    private final int sizeX;
    private final int sizeY;

    private final JLabel[][] cells;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private JPanel savePanel;

    private final Engine engine;
    private boolean stillPlaying = true;
    private boolean saveVisible = false;

    //Pictures
    final static private ImageIcon bulb = new ImageIcon("graphics/gameFrame/bulb.png");
    final static private ImageIcon empty = new ImageIcon("graphics/gameFrame/empty.png");
    final static private ImageIcon lighted = new ImageIcon("graphics/gameFrame/lighted.png");
    final static private ImageIcon wall = new ImageIcon("graphics/gameFrame/wall.png");
    final static private ImageIcon wall0 = new ImageIcon("graphics/gameFrame/wall0.png");
    final static private ImageIcon wall1 = new ImageIcon("graphics/gameFrame/wall1.png");
    final static private ImageIcon wall2 = new ImageIcon("graphics/gameFrame/wall2.png");
    final static private ImageIcon wall3 = new ImageIcon("graphics/gameFrame/wall3.png");
    final static private ImageIcon wall4 = new ImageIcon("graphics/gameFrame/wall4.png");
    final static private ImageIcon resetButton = new ImageIcon("graphics/gameFrame/resetButton.png");
    final static private ImageIcon saveButton = new ImageIcon("graphics/gameFrame/saveButton.png");
    final static private ImageIcon solveButton = new ImageIcon("graphics/gameFrame/solveButton.png");


    /**
     * Game initialization, generate new board with specified settings
     * @param size assigns preferred size of the board
     * @param wallsMin assigns minimum percentage of walls on the board
     * @param wallsMax assigns maximum percentage of walls on the board
     * @param toNumberChance assigns chance of wall having a number on itself, for each wall
     */
    public GameFrame(int size, float wallsMin, float wallsMax, float toNumberChance ) {
        //basic setup
        this.sizeX = size;
        this.sizeY = size;
        this.cells = new JLabel[sizeX][sizeY];
        this.engine = new Engine();
        engine.generateBoard(size, wallsMin, wallsMax, toNumberChance);

        //setup
        gameFrameSetUp();
        gamePanelSetUp();
        menuPanelSetUp();
        savePanelSetUp();
        update();

        add(gamePanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        add(savePanel, BorderLayout.SOUTH);

    }

    /**
     * Game initialization, allow making game from pre rendered board
     * @param board pre rendered board
     */
    public GameFrame(Engine.Field[][] board) {
        int size = board.length-2;
        this.sizeX = size;
        this.sizeY = size;
        this.cells = new JLabel[sizeX][sizeY];
        this.engine = new Engine();

        engine.setBoard(board);

        //setup
        gameFrameSetUp();
        gamePanelSetUp();
        menuPanelSetUp();
        savePanelSetUp();
        update();

        add(gamePanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        add(savePanel, BorderLayout.SOUTH);
    }

    /**
     * sets up hole frame to be working
     */
    private void gameFrameSetUp() {
        getContentPane();
        setTitle("Akari " + sizeX + " x" + sizeY);
        setResizable(false);
        setBackground(Color.GRAY);
        BorderLayout mainLayout = new BorderLayout();
        setLayout(mainLayout);
    }

    /**
     * sets up menu on the bottom of the window
     */
    private void menuPanelSetUp() {
        //menuPanel setup
        FlowLayout menuLayout = new FlowLayout();
        menuPanel = new JPanel();
        menuPanel.setLayout(menuLayout);
        menuPanel.setBackground(Color.GRAY);

        //menu mouse listeners setup
        JLabel[] menu = new JLabel[3];
        menu[0] = new JLabel();
        menuPanel.add(menu[0]);
        menu[0].setIcon(resetButton);
        menu[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                stillPlaying = true;
                for (int i = 1; i < sizeX+1; i++) {
                    for (int j = 1; j < sizeY+1; j++) {
                        if(engine.getBoard()[i][j] == Engine.Field.BULB ||
                                engine.getBoard()[i][j]== Engine.Field.LIGHTED ||
                                engine.getBoard()[i][j] == Engine.Field.LIGHTED2)
                        {
                            engine.getBoard()[i][j] = Engine.Field.EMPTY;
                        }
                    }
                }
                update();
            }
        });

        menu[1] = new JLabel();
        menuPanel.add(menu[1]);
        menu[1].setIcon(saveButton);
        menu[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!saveVisible) {
                    saveVisible = true;
                    savePanel.setVisible(true);
                    pack();
                }else{
                    saveVisible = false;
                    savePanel.setVisible(false);
                    pack();
                }
            }
        });

        menu[2] = new JLabel();
        menuPanel.add(menu[2]);
        menu[2].setIcon(solveButton);
        menu[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (stillPlaying) {
                    solve();
                    update();
                }
            }
        });
    }

    /**
     * sets up game graphics and mechanics
     */
    private void gamePanelSetUp() {

        GridLayout gameLayout = new GridLayout(sizeX, sizeY, 1, 1);
        //gamePanel setup
        gamePanel = new JPanel();
        gamePanel.setLayout(gameLayout);
        gamePanel.setBackground(Color.GRAY);
        gamePanel.setPreferredSize(new Dimension(41*sizeX-1,41*sizeY-1));

        //game mouse listeners setup
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                cells[i][j] = new JLabel();
                cells[i][j].setVerticalAlignment(CENTER);
                cells[i][j].setHorizontalAlignment(CENTER);
                this.gamePanel.add(cells[i][j]);
                int finalI = i;
                int finalJ = j;
                cells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (stillPlaying) {
                            clicked(finalI, finalJ);
                        }
                    }
                });
            }
        }
    }

    /**
     * Sets up save panel, saving GUI
     */
    private void savePanelSetUp() {
        savePanel = new JPanel();

        GridLayout saveLayout = new GridLayout(1, 4);
        savePanel.setLayout(saveLayout);
        savePanel.setBackground(Color.GRAY);
        JLabel save1Label = new JLabel(new ImageIcon("graphics/loadFrame/save1.png"), CENTER);
        JLabel save2Label = new JLabel(new ImageIcon("graphics/loadFrame/save2.png"), CENTER);
        JLabel save3Label = new JLabel(new ImageIcon("graphics/loadFrame/save3.png"), CENTER);
        JLabel save4Label = new JLabel(new ImageIcon("graphics/loadFrame/save4.png"), CENTER);
        savePanel.add(save1Label);
        savePanel.add(save2Label);
        savePanel.add(save3Label);
        savePanel.add(save4Label);
        savePanel.setVisible(false);

        save1Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    saveBoard(1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        save2Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    saveBoard(2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        save3Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    saveBoard(3);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        save4Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    saveBoard(4);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    /**
     * Defines what happen after single cell on board being clicked
     * @param x x coordinate
     * @param y y coordinate
     */
    private void clicked(int x, int y) {
        System.out.println(x + " " + y);
        JLabel cell = cells[x][y];
        if (cell.getIcon() == empty) {
            cell.setIcon(bulb);

            engine.placeBulb(engine.getBoard(), x + 1, y + 1);
            update();

        } else if (cell.getIcon() == bulb) {
            cell.setIcon(empty);
            engine.placeBulb(engine.getBoard(), x + 1, y + 1);
            update();
        }
    }

    /**
     * updates all images on screen
     */
    private void update() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                ImageIcon icon = switch (engine.getBoard()[i + 1][j + 1]) {
                    case WALL -> wall;
                    case BULB -> bulb;
                    case EMPTY -> empty;
                    case LIGHTED, LIGHTED2 -> lighted;
                    case WALL0 -> wall0;
                    case WALL1 -> wall1;
                    case WALL2 -> wall2;
                    case WALL3 -> wall3;
                    case WALL4 -> wall4;
                };
                cells[i][j].setIcon(icon);
            }
        }
        if (!engine.endGame(engine.getBoard())) {
            System.out.println("You won");

            stillPlaying = false;
        }

    }

    /**
     * Method that solves current game through solver object
     */
    private void solve(){
        Solver solver = new Solver();
        engine.setBoard(solver.solve(engine.getBoard()));
    }

    /**
     * Method that saves current board to chosen csv save file
     * @param saveNumber csv save file order number
     * @throws IOException thrown by SaveBoard
     */
    private void saveBoard(int saveNumber) throws IOException {
        SaveBoard saveBoard = new SaveBoard();
        saveBoard.setBoard(engine.getBoard());
        saveBoard.save(saveNumber);
    }

}

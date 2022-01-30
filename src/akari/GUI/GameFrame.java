package akari.GUI;

import akari.model.Engine;
import akari.model.Generator;
import akari.model.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class GameFrame extends JFrame {
    private final int sizeX;
    private final int sizeY;
    private final JLabel[][] cells;

    private final Engine engine;
    private boolean stillPlaying = true;

    //Pictures

//    final static private ImageIcon testTile1 = new ImageIcon("graphics/test_tile1.png");
//    final static private ImageIcon testTile2 = new ImageIcon("graphics/test_tile2.png");
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
     * Game initialization
     *
     * @param size size of the board is size x size
     */
    public GameFrame(int size, float wallsMin, float wallsMax, float toNumberChance) {
        //basic setup
        this.sizeX = size;
        this.sizeY = size;
        this.cells = new JLabel[sizeX][sizeY];
        this.engine = new Engine();

        Generator generator = new Generator();
        engine.generateBoard(size, wallsMin, wallsMax, toNumberChance);


        //Layouts
        BorderLayout mainLayout = new BorderLayout();
        GridLayout gameLayout = new GridLayout(sizeX, sizeY, 1, 1);
        FlowLayout menuLayout = new FlowLayout();


        //GameFrame setup
        getContentPane();
        setTitle("Akari " + sizeX + " x" + sizeY);
        setResizable(false);
        setBackground(Color.GRAY);
        setLayout(mainLayout);

        //gamePanel setup
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(gameLayout);
        gamePanel.setBackground(Color.GRAY);
        gamePanel.setPreferredSize(new Dimension(41*size-1,41*size-1));

        //game mouse listeners setup
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                cells[i][j] = new JLabel();
                cells[i][j].setVerticalAlignment(SwingConstants.CENTER);
                cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                gamePanel.add(cells[i][j]);
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


        //menuPanel setup
        JPanel menuPanel = new JPanel();
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
                super.mousePressed(e);
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



        update();

        add(gamePanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.SOUTH);


    }

    /**
     * Defines what happen after single cell on board is clicked
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    void clicked(int x, int y) {
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
    void update() {
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

    void solve(){
        Solver solver = new Solver();
        engine.setBoard(solver.solve(engine.getBoard()));
    }

}

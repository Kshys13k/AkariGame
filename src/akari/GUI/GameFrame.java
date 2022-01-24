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
    private final JLabel[] menu;
    private Engine.Field[][] board;
    private final Engine engine;
    private boolean stillPlaying = true;

    //Pictures

    final static private ImageIcon testTile1 = new ImageIcon("graphics/test_tile1.png");
    final static private ImageIcon testTile2 = new ImageIcon("graphics/test_tile2.png");
    final static private ImageIcon bulb = new ImageIcon("graphics/bulb.png");
    final static private ImageIcon empty = new ImageIcon("graphics/empty.png");
    final static private ImageIcon lighted = new ImageIcon("graphics/lighted.png");
    final static private ImageIcon wall = new ImageIcon("graphics/wall.png");
    final static private ImageIcon wall0 = new ImageIcon("graphics/wall0.png");
    final static private ImageIcon wall1 = new ImageIcon("graphics/wall1.png");
    final static private ImageIcon wall2 = new ImageIcon("graphics/wall2.png");
    final static private ImageIcon wall3 = new ImageIcon("graphics/wall3.png");
    final static private ImageIcon wall4 = new ImageIcon("graphics/wall4.png");
    final static private ImageIcon quitButton = new ImageIcon("graphics/quit_button.png");
    final static private ImageIcon saveButton = new ImageIcon("graphics/save_button.png");
    final static private ImageIcon solveButton = new ImageIcon("graphics/solve_button.png");


    /**
     * Game initialization
     *
     * @param size size of the board is size x size
     */
    public GameFrame(int size) {
        //basic setup
        this.sizeX = size;
        this.sizeY = size;
        this.cells = new JLabel[sizeX][sizeY];
        this.engine = new Engine();

        Generator generator = new Generator();
        board = generator.generate(size + 2);


        //Layouts
        BorderLayout mainLayout = new BorderLayout();
        GridLayout gameLayout = new GridLayout(sizeX, sizeY, 1, 1);
        GridLayout menuLayout = new GridLayout(1, 3, 1, 10);

        //GameFrame setup
        getContentPane();
        setTitle("Akari " + sizeX + "x" + sizeY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GRAY);
        setLayout(mainLayout);

        //gamePanel setup
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(gameLayout);
        gamePanel.setBackground(Color.GRAY);

        //game mouse listeners setup
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                cells[i][j] = new JLabel();
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
        menu = new JLabel[3];
        menu[0] = new JLabel();
        menuPanel.add(menu[0]);
        menu[0].setIcon(quitButton);
        menu[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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

                solve();
                update();
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

            engine.placeBulb(board, x + 1, y + 1);
            update();

        } else if (cell.getIcon() == bulb) {
            cell.setIcon(empty);
            engine.placeBulb(board, x + 1, y + 1);
            update();
        }
    }

    /**
     * updates all images on screen
     */
    void update() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                ImageIcon icon = switch (board[i + 1][j + 1]) {
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
        if (!engine.endGame(board)) {
            System.out.println("You won");

            stillPlaying = false;
        }

    }

    void solve(){
        Solver solver = new Solver();
        board = solver.solve(board);
    }

}

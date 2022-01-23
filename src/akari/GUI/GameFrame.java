package akari.GUI;

import akari.model.Engine;
import akari.model.Generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class GameFrame extends JFrame{
    private int sizeX;
    private int sizeY;
    private Dimension dimension = null;
    private JPanel gamePanel = new JPanel();
    private JPanel menuPanel = new JPanel();
    private JLabel[][] cells = null;
    private Engine.Field[][] board = null;
    private Engine engine = null;
    private Generator generator = new Generator();

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



    public GameFrame(int size){
        //basic setup
        this.sizeX = size;
        this.sizeY = size;
        this.cells = new JLabel[sizeX][sizeY];

        this.engine = new Engine();
        this.board = generator.generate(size+2);

        // TODO: eventually future dimension setup

        //Layouts
        BorderLayout mainLayout = new BorderLayout();
        GridLayout gameLayout = new GridLayout(sizeX,sizeY,1,1);
        GridLayout menuLayout = new GridLayout(1,3,1,10);

        //GameFrame setup
        getContentPane();
        setTitle("Akari "+ sizeX +"x"+ sizeY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GRAY);
        setLayout(mainLayout);

        //gamePanel setup
        this.gamePanel.setLayout(gameLayout);
        this.gamePanel.setBackground(Color.GRAY);

        //menuPanel setup
        this.menuPanel.setLayout(menuLayout);
        this.menuPanel.setBackground(Color.GRAY);

        //mouse listeners setup
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                cells[i][j] = new JLabel();
                this.gamePanel.add(cells[i][j]);
                int finalI = i;
                int finalJ = j;
                cells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        clicked(finalI, finalJ);
                    }
                });
            }
        }
        update();

        add(gamePanel,BorderLayout.NORTH);
        add(menuPanel,BorderLayout.SOUTH);



    }

    /**
     * Defines what happen after single cell on board is clicked
     * @param x x coordinate
     * @param y y coordinate
     */
    void clicked(int x, int y){
        System.out.println(x + " " +y);
        JLabel cell = cells[x][y];
        if(cell.getIcon() == empty){
            cell.setIcon(bulb);

            engine.placeBulb(board,x+1,y+1);
            update();

        }else if(cell.getIcon() == bulb){
            cell.setIcon(empty);
            engine.placeBulb(board,x+1,y+1);
            update();
        }

        System.out.println("stop");
    }


    /**
     *
     * @param x
     * @param y
     * @param type
     * @throws WrongCellTypeException
     */
    void setCell(int x, int y, char type) throws WrongCellTypeException {
        JLabel cell = cells[x][y];
        switch(type){
            case ' ':
                cell.setIcon(empty);
                break;
            case '*':
                cell.setIcon(bulb);
                break;
            case '.':
            case ':':
                cell.setIcon(lighted);
                break;
            case '#':
                cell.setIcon(wall);
                break;
            case '1':
                cell.setIcon(wall1);
                break;
            case '2':
                cell.setIcon(wall2);
                break;
            case '3':
                cell.setIcon(wall3);
                break;
            case '4':
                cell.setIcon(wall4);
                break;
            default:
                    throw new WrongCellTypeException("Cell types are expressed as one of the following characters: ' ', '*', '.', ':', '#', '1', '2', '3', '4'");

        }
    }

    /**
     * updates all images on screen
     */
    void update(){
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                ImageIcon icon = switch (board[i+1][j+1]) {
                    case WALL -> this.wall;
                    case BULB -> this.bulb;
                    case EMPTY -> this.empty;
                    case LIGHTED, LIGHTED2 -> this.lighted;
                    case WALL0 -> this.wall0;
                    case WALL1 -> this.wall1;
                    case WALL2 -> this.wall2;
                    case WALL3 -> this.wall3;
                    case WALL4 -> this.wall4;

                    default -> throw new IllegalStateException("Unexpected value: " + board[i][j]);
                };
                cells[i][j].setIcon(icon);
            }
        }
    }

}

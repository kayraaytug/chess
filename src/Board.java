import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private int ROWS = 8;

    private int COLS = 8;
    private Tile[][] tiles = new Tile[8][8];

    public Board(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                tiles[i][j] = new Tile(i,j, j%2 == 0 ? 'w':'b');
            }
        }

        for (int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                System.out.println(tiles[i][j].getPosX() + "," + tiles[i][j].getPosY() + "" + tiles[i][j].getColor());
            }
        }
    }
    public void paintComponent(Graphics g){
        g.fillRect(100,100,100,100);
    }
}

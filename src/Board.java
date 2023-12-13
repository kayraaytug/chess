import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Board extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private int ROWS = 8;
    private String COLS = "abcdefgh";
    private Tile[][] tiles = new Tile[8][8];

    public Board(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                tiles[i][j] = new Tile(i,j, j%2 == 0 ? 'w':'b', new String[][] {{"a"}, {"8"}});
            }
        }

    }
    public void paintComponent(Graphics g){
        Pawn p1 = new Pawn('w');
        g.fillRect(100,100,100,100);
        g.drawImage(p1.image, 10,10, null);
    }
}

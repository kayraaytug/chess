package Pieces;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Piece   {
    public int posX, posY;
    public char team;
    public BufferedImage image;
    public int positionOnBoardX;
    public int positionOnBoardY;
    public int moveLimit;
    public ArrayList<String> moveType = new ArrayList<>();

    // Use uppercase letters for black and lowercase for white
    public char label;

    public Piece(int posX, int posY, char team){
        this.posX = posX;
        this.posY = posY;
        this.team = team;
        this.positionOnBoardX = posX/100;
        this.positionOnBoardY = posY/100;
    }
}

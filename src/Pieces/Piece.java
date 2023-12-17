package Pieces;
import java.awt.image.BufferedImage;


public class Piece   {
    public int posX, posY;
    public char team;
    public BufferedImage image;

    // Use uppercase letters for black and lowercase for white
    public char label;

    public Piece(int posX, int posY, char team){
        this.posX = posX;
        this.posY = posY;
        this.team = team;
    }

}

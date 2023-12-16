package Pieces;
import utils.Range;
import java.awt.image.BufferedImage;


public class Piece   {
    public int posX, posY;
    public char team;
    public BufferedImage image;
    public Range clickArea;

    public Piece(int posX, int posY, char team){
        this.posX = posX;
        this.posY = posY;
        this.team = team;
        clickArea = new Range(this.posX, this.posX+100, this.posY, this.posY+100);
    }

}

package Pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Piece implements Cloneable {
    public int posX, posY;
    public char team;
    public BufferedImage image;
    public int positionOnBoardX;
    public int positionOnBoardY;
    public int moveLimit;
    public List<String> moveType = new ArrayList<>();
    public char label;

    public Piece(int posX, int posY, char team) {
        this.posX = posX;
        this.posY = posY;
        this.team = team;
        this.positionOnBoardX = posX / 100;
        this.positionOnBoardY = posY / 100;
    }

    @Override
    public Piece clone() throws CloneNotSupportedException {
        Piece clonedPiece = (Piece) super.clone();
        return clonedPiece;
    }
}

package Pieces;

import javax.imageio.ImageIO;
import java.io.File;

public class Pawn extends Piece {
    public Pawn(int posX, int posY, char team){
        super(posX, posY, team);
        this.moveLimit = 2;
        this.moveType.add("DIAGONAL");
        this.moveType.add("VERTICAL");
        try{
            if (team == 'b') {
                this.label = 'P';
                this.image = ImageIO.read(new File("src/images/black-pawn.png"));
            }
            else{
                this.label = 'p';
                this.image = ImageIO.read(new File("src/images/white-pawn.png"));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}

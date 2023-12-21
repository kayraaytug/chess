package Pieces;

import javax.imageio.ImageIO;
import java.io.File;

public class King extends Piece {
    public King(int posX, int posY, char team){
        super(posX, posY, team);
        this.moveLimit = 1;
        this.moveType.add("DIAGONAL");
        this.moveType.add("VERTICAL");
        this.moveType.add("HORIZONTAL");
        try{
            if (team == 'b') {
                this.label = 'K';
                this.image = ImageIO.read(new File("src/images/black-king.png"));
            }
            else{
                this.label = 'k';
                this.image = ImageIO.read(new File("src/images/white-king.png"));
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

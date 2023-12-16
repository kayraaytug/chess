package Pieces;

import javax.imageio.ImageIO;
import java.io.File;

public class Bishop extends Piece {
    public Bishop(int posX, int posY, char team){
        super(posX, posY, team);
        try{
            if (team == 'b') {
                this.image = ImageIO.read(new File("src/images/black-bishop.png"));
            }
            else{
                this.image = ImageIO.read(new File("src/images/white-bishop.png"));
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

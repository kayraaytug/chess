package Pieces;

import javax.imageio.ImageIO;
import java.io.File;
import utils.Range;

public class Knight extends Piece {
    public Knight(int posX, int posY, char team){
        super(posX, posY, team);
        try{
            if (team == 'b') {
                this.image = ImageIO.read(new File("src/images/black-knight.png"));
            }
            else{
                this.image = ImageIO.read(new File("src/images/white-knight.png"));
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

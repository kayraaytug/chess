import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pawn extends Piece{
    BufferedImage image;
    public Pawn(char team){

        try{
            if (team == 'b') {
                this.image = ImageIO.read(new File("src/images/bpawn.png"));
            }
            else{
                this.image = ImageIO.read(new File("src/images/wpawn.png"));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

}

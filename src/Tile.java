import utils.Range;
public class Tile {
    private int posX, posY;
    private char color;
    public Range clickArea;
    public Tile(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.clickArea = new Range(posX, posX+100, posY, posY+100);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

}

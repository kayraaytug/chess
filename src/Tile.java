public class Tile {
    private int posX, posY;
    char color;
    public Tile(int posX, int posY, char color){
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public char getColor() {
        return color;
    }
}

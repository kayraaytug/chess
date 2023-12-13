public class Tile {
    private int posX, posY;
    private String[][] label;
    private char color;
    public Tile(int posX, int posY, char color, String[][] label){
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.label = label;
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

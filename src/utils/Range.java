package utils;

public class Range
{
    public int x_min;
    public int x_max;
    public int y_min;
    public int y_max;

    public Range(int x_min, int x_max, int y_min, int y_max){
        this.x_min = x_min;
        this.x_max = x_max;
        this.y_min = y_min;
        this.y_max = y_max;
    }

    public boolean contains(int posX, int posY){
        return (posX >= x_min && posX <= x_max && posY>=y_min && posY<=y_max);
    }

    public void update(int x, int y){
        x_min = x;
        x_max = x+100;
        y_min = y;
        y_max = y+100;
    }
}

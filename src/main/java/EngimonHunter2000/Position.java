package EngimonHunter2000;

import java.io.Serializable;

public class Position implements Serializable {
    public static final long serialVersionUID = 1L;
    private int posX;
    private int posY;

    public Position() {
        this.posX = 1;
        this.posY = 1;
    }

    public Position(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public void setX(int left) {
        this.posX = left;
    }

    public void setY(int right) {
        this.posY = right;
    }

    public void setPosition(Position pos) {
        this.posX = pos.posX;
        this.posY = pos.posY;
    }

    public void setPosition(int left, int right) {
        this.posX = left;
        this.posY = right;
    }

    public double distanceTo(Position other){
        return Math.sqrt(Math.pow(this.posX - other.posX, 2) + Math.pow(this.posY - other.posY, 2));
    }
}

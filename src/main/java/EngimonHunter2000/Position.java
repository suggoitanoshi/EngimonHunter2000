package EngimonHunter2000;

class Position {
    private int posX;
    private int posY;

    Position() {
        this.posX = 1;
        this.posY = 1;
    }

    Position(int x, int y) {
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

    public void setPair(Position pair) {
        this.posX = pair.posX;
        this.posY = pair.posY;
    }

    public void setPair(int left, int right) {
        this.posX = left;
        this.posY = right;
    }
}

package EngimonHunter2000;

class Position{
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

    public int getLeft() {
        return this.posX;
    }

    public int getRight() {
        return this.posY;
    }
    
    public void setLeft(int left) {
        this.posX = left;
    }

    public void setRight(int right) {
        this.posY = right;
    }

    public void setPair(Position pair) {
        this.posX = pair.getLeft();
        this.posY = pair.getRight();
    }

    public void setPair(int left, int right) {
        this.posX = left;
        this.posY = right;
    }
}
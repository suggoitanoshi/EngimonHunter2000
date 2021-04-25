package EngimonHunter2000;

public class Tile {
	public static final int maxX = 20;
    public static final int maxY = 20;
    private TileType type;
    private boolean isNabrak;
    private boolean canMoveW=true;
    private boolean canMoveA=true;
    private boolean canMoveS=true;
    private boolean canMoveD=true;
    private String path;
    private int x;
    private int y;
    private char icon;

	public Tile(String p, TileType t, int x, int y, boolean in){
		path = p;
		type = t;
		this.x = x;
		this.y = y;
		isNabrak = in;
	}

    public Tile(TileType t, int x, int y) {
        if (y == 0) {
            canMoveW = false;
        }
        if (y >= (maxY - 1)) {
            canMoveS = false;
        }

        if (x == 0) {
            canMoveA = false;
        }

        if (x >= (maxX - 1)) {
            canMoveD = false;
        }

        type = t;
        isNabrak = false;
        this.x = x;
        this.y = y;
        if (t == TileType.GRASS) {
            path = "data/resource/grass.png";
            this.icon = '1';
        } else if (t == TileType.WATER) {
            path = "data/resource/watergif.gif";
            this.icon = '2';
        }
        else if (t == TileType.MOUNTAIN) {
            path = "data/resource/mountain.png";
            this.icon = '4';
        }
        else if (t == TileType.TUNDRA) {
            path = "data/resource/tundra.png";
            this.icon = '3';
        }
        else if (t == TileType.EDGE_GRASS) {
            path = "data/resource/edge1.png";
            this.icon = 'a';
        }
        else if (t == TileType.EDGE_TUNDRA) {
            path = "data/resource/edge3.png";
            this.icon = 'b';
        }
        else if (t == TileType.EDGE1_MOUNTAIN) {
            path = "data/resource/edge5.png";
            this.icon = 'c';
        }
        else if (t == TileType.EDGE2_MOUNTAIN) {
            path = "data/resource/edge4.png";
            this.icon = 'd';
        }
        else if (t == TileType.EDGE3_MOUNTAIN) {
            path = "data/resource/edge6.png";
            this.icon = 'e';
        }
    }

	public void setType(TileType t){
		this.type = t;
	}
    public TileType getType() {
        return this.type;
    }

    public String getPath() {
        return this.path;
    }

    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }

    public char getIcon() {
        return this.icon;
    }

    public boolean isOccupied() {
        return isNabrak;
    }

    public void makeOccupied() {
        isNabrak = true;
    }

    public void makeFree() {
        isNabrak = false;
    }
}

package EngimonHunter2000;

public class Tile {
    TileType type;
    boolean isNabrak;
    boolean canMoveW=false;
    boolean canMoveA=false;
    boolean canMoveS=false;
    boolean canMoveD=false;
    String path;
    int x;
    int y;
    char icon;

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
        if (y == 19) {
            canMoveS = false;
        }

        if (x == 0) {
            canMoveA = false;
        }

        if (x == 19) {
            canMoveD = false;
        }

        if (t == TileType.GRASS) {
            type = TileType.GRASS;
            isNabrak = false;
            path = "data/resource/grass.png";
            this.x = x;
            this.y = y;
            this.icon = '1';
        } else if (t == TileType.WATER) {
            type = TileType.WATER;
            isNabrak = false;
            path = "data/resource/watergif.gif";
            this.x = x;
            this.y = y;
            this.icon = '2';
        }
        else if (t == TileType.MOUNTAIN) {
            type = TileType.MOUNTAIN;
            isNabrak = false;
            path = "data/resource/mountain.png";
            this.x = x;
            this.y = y;
            this.icon = '4';
        }
        else if (t == TileType.TUNDRA) {
            type = TileType.TUNDRA;
            isNabrak = false;
            path = "data/resource/tundra.png";
            this.x = x;
            this.y = y;
            this.icon = '3';
        }
        else if (t == TileType.EDGE_GRASS) {
            type = TileType.EDGE_GRASS;
            isNabrak = false;
            path = "data/resource/edge1.png";
            this.x = x;
            this.y = y;
            this.icon = 'a';
        }
        else if (t == TileType.EDGE_TUNDRA) {
            type = TileType.EDGE_TUNDRA;
            isNabrak = false;
            path = "data/resource/edge3.png";
            this.x = x;
            this.y = y;
            this.icon = 'b';
        }
        else if (t == TileType.EDGE1_MOUNTAIN) {
            type = TileType.EDGE1_MOUNTAIN;
            isNabrak = false;
            path = "data/resource/edge5.png";
            this.x = x;
            this.y = y;
            this.icon = 'c';
        }
        else if (t == TileType.EDGE2_MOUNTAIN) {
            type = TileType.EDGE2_MOUNTAIN;
            isNabrak = false;
            path = "data/resource/edge4.png";
            this.x = x;
            this.y = y;
            this.icon = 'd';
        }
        else if (t == TileType.EDGE3_MOUNTAIN) {
            type = TileType.EDGE3_MOUNTAIN;
            isNabrak = false;
            path = "data/resource/edge6.png";
            this.x = x;
            this.y = y;
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

    public char getIcon() {
        return this.icon;
    }
}

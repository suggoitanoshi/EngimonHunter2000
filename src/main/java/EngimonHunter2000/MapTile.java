package EngimonHunter2000;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class MapTile {
    Tile[][] map;
    public int sizeX;
    public int sizeY;

    public MapTile() {
		readMap();
    }

    public void readMap() {
        try {
            File maptxt = new File("data/map.txt");
            Scanner r = new Scanner(maptxt);

            sizeX = Integer.parseInt(r.nextLine());
            sizeY = Integer.parseInt(r.nextLine());

            map = new Tile[sizeY][sizeX];

            int y = 0;
            while (r.hasNextLine()) {
                String line = r.nextLine();
                int x = 0;
                for (char c : line.toCharArray()) {
                    if (c == '1') {
                        Tile t = new Tile(TileType.GRASS, x, y);
                        map[y][x] = t;
                    } else if (c == '2') {
                        Tile t = new Tile(TileType.WATER, x, y);
                        map[y][x] = t;
                    } else if (c == '3') {
                        Tile t = new Tile(TileType.TUNDRA, x, y);
                        map[y][x] = t;
                    } else if (c == '4') {
                        Tile t = new Tile(TileType.MOUNTAIN, x, y);
                        map[y][x] = t;
                    } else if (c == 'a') {
                        Tile t = new Tile(TileType.EDGE_GRASS, x, y);
                        map[y][x] = t;
                    } else if (c == 'b') {
                        Tile t = new Tile(TileType.EDGE_TUNDRA, x, y);
                        map[y][x] = t;
                    } else if (c == 'c') {
                        Tile t = new Tile(TileType.EDGE1_MOUNTAIN, x, y);
                        map[y][x] = t;
                    } else if (c == 'd') {
                        Tile t = new Tile(TileType.EDGE2_MOUNTAIN, x, y);
                        map[y][x] = t;
                    } else if (c == 'e') {
                        Tile t = new Tile(TileType.EDGE3_MOUNTAIN, x, y);
                        map[y][x] = t;
                    }
                    x = x + 1;
                }
                y = y + 1;
            }
            r.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    // public Tile getTile(int x, int y) {
    //     return map[x][y];
    // }
    // public static void main(String[] args){
    //   MapTile m = new MapTile();
    //   m.readMap();
    //   System.out.println(Integer.toString(m.sizeX));
    //   System.out.println(Integer.toString(m.sizeY));

    // }
}

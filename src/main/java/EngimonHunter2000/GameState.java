package EngimonHunter2000;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class GameState implements Serializable {
    public static final long serialVersionUID = 1L;
    transient private SkillDex skillDex;
    transient private EngiDex engiDex;
    transient private MapTile maptile;
	transient private Tile[][] map;
    private Player player;
    private ArrayList<Engimon> wildEngimons;

    public GameState() {
        try {
            skillDex = new SkillDex();
            engiDex = new EngiDex(skillDex);
            maptile = new MapTile();
			map = maptile.getMap();
            player = new Player(engiDex);
            wildEngimons = new ArrayList<>();

			setEntities();

        } catch (EngimonHunter2000Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void save(String filename) {
        try {
            FileOutputStream file = new FileOutputStream(new File(filename));
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }

    public static GameState load(String filename) {
        GameState a = null;
        try {
            FileInputStream file = new FileInputStream(new File(filename));
            ObjectInputStream in = new ObjectInputStream(file);
            a = (GameState) in.readObject();
            a.skillDex = new SkillDex();
            a.engiDex = new EngiDex(a.skillDex);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (EngimonHunter2000Exception e) {
            e.printStackTrace();
            System.err.println("Gagal loading");
            System.exit(-1);

        }

        return a;
    }

    /**
     * Metode untuk mendapatkan {@link Tile} kosong pada map game. Isi list-nya
     * adalah reference ke tile pada map
     */
    private ArrayList<ArrayList<Tile>> getEmptyTiles() {
        ArrayList<ArrayList<Tile>> ret = new ArrayList<>();
        for (int x = 0; x < Tile.maxX; ++x) {
            ArrayList<Tile> perantara = new ArrayList<>();
            for (int y = 0; y < Tile.maxY; ++y) {
                if (!map[y][x].isOccupied()) {
                    perantara.add(map[y][x]);
                }
            }
            ret.add(perantara);
        }
        return ret;
    }

	public Tile[][] getMap(){
		return map;
	}

	public void setEntities(){

		switch(map[player.getPositionY()][player.getPositionX()].getType()) {
			case EDGE1_MOUNTAIN:
				Tile tile1 = new Tile("data/resource/char_edge5.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile1;
				break;
			case EDGE2_MOUNTAIN:
				Tile tile2 = new Tile("data/resource/char_edge4.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile2;
				break;
			case EDGE3_MOUNTAIN:
				Tile tile3 = new Tile("data/resource/char_edge6.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile3;
				break;
			case EDGE_GRASS:
				Tile tile4 = new Tile("data/resource/char_edge1.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile4;
				break;
			case EDGE_TUNDRA:
				Tile tile5 = new Tile("data/resource/char_edge3.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile5;
				break;
			case GRASS:
				Tile tile6 = new Tile("data/resource/char_grass.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile6;
				break;
			case MOUNTAIN:
				Tile tile7 = new Tile("data/resource/char_mountain.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile7;
				break;
			case TUNDRA:
				Tile tile8 = new Tile("data/resource/char_tundra.png", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile8;
				break;
			case WATER:
				Tile tile_p = new Tile("data/resource/char_watergif.gif", TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
				map[player.getPositionY()][player.getPositionX()] = tile_p;
				break;
			default:
				break;
		}
		//set active 
		int i = 0;
		int j = 0;

		switch(player.getDir()){
			case 'w':
				i = 0;
				j = 1;
				break;
			case 'a':
				i = 1;
				j = 0;
				break;
			case 's':
				i = 0;
				j = -1;
				break;
			case 'd':
				i = -1;
				j = 0;
				break;
			default:
				break;
		}
		
		switch (map[player.getPositionY()+j][player.getPositionX()+i].getType()){
			case EDGE1_MOUNTAIN:
				String path1 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_edge5.png";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path1,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			case EDGE2_MOUNTAIN:
				String path2 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_edge4.png";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path2,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			case EDGE3_MOUNTAIN:
				String path3 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_edge6.png";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path3,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			case EDGE_GRASS:
				String path4 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_edge1.png";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path4,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			case EDGE_TUNDRA:
				String path5 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_edge3.png";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path5,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			case GRASS:
				String path6 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_grass.png";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path6,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			case MOUNTAIN:
				String path7 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_mountain.png";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path7,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			case TUNDRA:
				String path8 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_tundra.png";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path8,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			case WATER:
				String path9 = "data/resource/"+ player.getActiveEngimon().getSpecies() +"/"+ player.getActiveEngimon().getSpecies() +"_watergif.gif";
				map[player.getPositionY()+j][player.getPositionX()+i] = new Tile(path9,TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, true);
				break;
			default:
				break;

		}
		
	}

	public void updateGameState(){
		maptile = new MapTile();
		map = maptile.getMap();
		setEntities();
	}

    private Engimon makeWildEngimon() throws EngimonHunter2000Exception {
        Random seeder = new Random();
        Random rand = new Random(seeder.nextLong());

        int lvlMinBound = player.getHighestEngimonLevel();
        int lvlMaxBound = player.getActiveEngimon().getLvl() + 5;
        int engiLvl =
            Math.min(
                Math.max(
                    (rand.nextInt() % (lvlMaxBound - lvlMinBound)) + lvlMinBound,
                    lvlMinBound), lvlMaxBound);

        Set<String> possibleNames = engiDex.getDex().keySet();
        Iterator<String> it = possibleNames.iterator();
        String name = "";
        int engieIdx = rand.nextInt() % engiDex.getDex().size();
        if (engieIdx < 0) engieIdx += engiDex.getDex().size();
        while (it.hasNext() && engieIdx-- >= 0) {
            name = it.next();
        }

        return new Engimon(engiDex, name, name, engiLvl,
                           engiLvl * Engimon.MAX_EXP, new Position(), 1,
                           name, name);
    }

    private void putWildEngimon(Engimon engie) {
        int x;
        int y;
        Random rand = new Random();
        ArrayList<ArrayList<Tile>> emptyTiles = getEmptyTiles();
        Tile emptyTile;

        do {
            x = rand.nextInt() % Tile.maxX;
            if (x < 0) x += Tile.maxX;
            y = rand.nextInt() % Tile.maxY;
            if (y < 0) y += Tile.maxY;
            emptyTile = map[y][x];
        } while (x >= emptyTiles.size() ||
                 y >= emptyTiles.get(x).size() ||
                 !emptyTiles.get(x).contains(emptyTile));

        engie.setPos(x, y);
        map[y][x].makeOccupied();
        emptyTiles.get(x).remove(y);
    }

    /**
     * Fungsi untuk membuat `count` buah wild {@link Engimon} Fungsi tidak akan
     * melakukan apa-apa jika banyak wild engimon sudah >= `count`.
     * @param count banyak wild engimon yang ingin dibuat
     * @author Josep Marcello
     */
    public void spawnWildEngimons(int count) {
        if (wildEngimons.size() >= count) {
            return;
        }

        for (int i = 0; i < count; ++i) {
            Engimon engie = null;
            try {
                engie = makeWildEngimon();
                putWildEngimon(engie);
            } catch (EngimonHunter2000Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
}

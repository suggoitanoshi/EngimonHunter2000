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

    public void save(String filename) throws GameStateException {
        try {
            FileOutputStream file = new FileOutputStream(new File(filename));
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
        } catch (IOException e) {
            throw new GameStateException(2);
        }
    }

    public static GameState load(String filename) throws GameStateException {
        GameState a = null;
        try {
            FileInputStream file = new FileInputStream(new File(filename));
            ObjectInputStream in = new ObjectInputStream(file);
            a = (GameState) in.readObject();
            a.skillDex = new SkillDex();
            a.engiDex = new EngiDex(a.skillDex);
        } catch (Exception e) {
            throw new GameStateException(1);
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

    public MapTile getMapTile() {
        return maptile;
    }

	public void setEntities() {
        String path;
		switch(map[player.getPositionY()][player.getPositionX()].getType()) {
			case EDGE1_MOUNTAIN:
                path = "data/resource/char_edge5.png";
				break;
			case EDGE2_MOUNTAIN:
                path = "data/resource/char_edge4.png";
				break;
			case EDGE3_MOUNTAIN:
                path = "data/resource/char_edge6.png";
				break;
			case EDGE_GRASS:
                path = "data/resource/char_edge1.png";
				break;
			case EDGE_TUNDRA:
                path = "data/resource/char_edge3.png";
				break;
			case GRASS:
                path = "data/resource/char_grass.png";
				break;
			case MOUNTAIN:
                path = "data/resource/char_mountain.png";
				break;
			case TUNDRA:
                path = "data/resource/char_tundra.png";
				break;
            default: // water
                path = "data/resource/char_watergif.gif";
		}

        Tile tile = new Tile(path, TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
        // return tile to original sprite
        map[player.getPositionY()][player.getPositionX()] = tile;
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

        path = getEngimonSprite(player.getActiveEngimon(),
                                map[player.getPositionY() + j][player.getPositionX() + i]
                                    .getType());
        tile = new Tile(path, TileType.ENGIMON, player.getPositionX()+i, player.getPositionY()+j, false);
        map[player.getPositionY()+j][player.getPositionX()+i] = tile;
		
	}

    private String getEngimonSprite(Engimon engie, TileType tipe) {
        StringBuilder SB = new StringBuilder("data/resource/");
        SB.append(engie.getSpecies().toLowerCase());
        SB.append("/");
        SB.append(engie.getSpecies().toLowerCase());
		switch (tipe){
			case EDGE1_MOUNTAIN:
				SB.append("_edge5.png");
                break;
			case EDGE2_MOUNTAIN:
				SB.append("_edge4.png");
                break;
			case EDGE3_MOUNTAIN:
				SB.append("_edge6.png");
                break;
			case EDGE_GRASS:
				SB.append("_edge1.png");
                break;
			case EDGE_TUNDRA:
				SB.append("_edge3.png");
                break;
			case GRASS:
				SB.append("_grass.png");
                break;
			case MOUNTAIN:
				SB.append("_mountain.png");
                break;
			case TUNDRA:
				SB.append("_tundra.png");
                break;
            default: //water
				SB.append("_watergif.gif");
		}

        return SB.toString();
    }

	public void updateGameState() {
        maptile = new MapTile();
        map = maptile.getMap();
		setEntities();
	}

    private Engimon makeWildEngimon() throws
        EngimonException, ElementsListException, EngimonSpeciesException {
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
                --i; // coba lagi kalo gagal
                continue;
                // e.printStackTrace();
                // System.exit(-1);
            }
        }
    }
}

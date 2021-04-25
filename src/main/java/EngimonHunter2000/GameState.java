package EngimonHunter2000;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class GameState {
	Player player;
	SkillDex skillDex;
	EngiDex engiDex;
	MapTile maptile;
	Tile[][] map;
	ArrayList<Engimon> wildEngimons;

	public GameState(){
		try{
			skillDex = new SkillDex();
			skillDex.getDexDataFromFile("data/Skills.csv");
			engiDex = new EngiDex(skillDex);
			engiDex.getDexDataFromFile("data/Engimons.csv");
			maptile = new MapTile();
			map = maptile.getMap();
			player = new Player(engiDex);

			//set entities
			setEntities();

		} catch (EngimonHunter2000Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}

	public Player getPlayer() {
		return player;
	}

    public void save() {}

    public GameState load() {
        return this;
    }

    /**
     * Metode untuk mendapatkan {@link Tile} kosong pada map game.
     */
    private ArrayList<ArrayList<Tile>> getEmptyTiles() {
        ArrayList<ArrayList<Tile>> ret = new ArrayList<>();
        for (int x = 0; x < Tile.maxX; ++x) {
            for (int y = 0; y < Tile.maxY; ++y) {
                ArrayList<Tile> perantara = new ArrayList<>();
                if (!map[y][x].isOccupied()) {
                    perantara.add(map[y][x]);
                }
                ret.add(perantara);
            }
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

    private Engimon makeWildEngimon() throws ElementsListException, EngimonSpeciesException {
        Random seeder = new Random();
        Random rand = new Random(seeder.nextLong());

        int lvlMinBound = player.getHighestEngimonLevel();
        int lvlMaxBound = player.getActiveEngimon().getLvl() + 5;
        int engiLvl =
            Math.min((rand.nextInt() % (lvlMaxBound - lvlMinBound)) + lvlMinBound, lvlMaxBound);

        Set<String> possibleNames = skillDex.getDex().keySet();
        Iterator<String> it = possibleNames.iterator();
        String name = "";
        int engieIdx = rand.nextInt();
        while (it.hasNext() && engieIdx-- > 0) {
            name = it.next();
        }

        return new Engimon(engiDex, name, name);
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

        ArrayList<ArrayList<Tile>> emptyTiles = getEmptyTiles();
        Random rand = new Random();
        for (int i = 0; i < count; ++i) {
            Engimon engie = null;
            try {
                engie = makeWildEngimon();
            } catch (ElementsListException | EngimonSpeciesException e) {
                System.err.println(e);
                System.exit(-1);
            }

            int x;
            do {
                x = rand.nextInt() % Tile.maxX;
            } while (x < emptyTiles.size());
            int y;
            do {
                y = rand.nextInt() % Tile.maxY;
            } while (y < emptyTiles.get(x).size());

            engie.setPos(x, y);
            map[y][x].makeOccupied();
            emptyTiles.get(x).remove(y);
        }
    }
}

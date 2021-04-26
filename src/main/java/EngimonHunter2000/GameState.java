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
    private static final int wildEngieCount = 30;
    transient private SkillDex skillDex;
    transient private EngiDex engiDex;
    transient private MapTile maptile;
	transient private Tile[][] map;
    private Player player;
    private ArrayList<Engimon> wildEngimons;
    private int turn;
	private boolean isGameOver;

    public GameState() {
        try {
            skillDex = new SkillDex();
            engiDex = new EngiDex(skillDex);
            maptile = new MapTile();
			map = maptile.getMap();
            player = new Player(engiDex);
            wildEngimons = new ArrayList<>();
            turn = 1;
			isGameOver = false;

            fillMap();
        } catch (EngimonHunter2000Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

	public void updateGameState() {
        turn++;

        // tambahin exp dan move engimon
        if (turn % 5 == 0) {
            activateWildEngimons();
        }

        maptile = new MapTile();
        map = maptile.getMap();

        fillMap();
	}

    private void fillMap() {
        applyPlayerPosition();
        applyActiveEngimonPosition();
        spawnWildEngimons(wildEngieCount - wildEngimons.size());
        setSprite();
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
        try {
            GameState a = null;
            FileInputStream file = new FileInputStream(new File(filename));
            ObjectInputStream in = new ObjectInputStream(file);

            a = (GameState) in.readObject();
            a.skillDex = new SkillDex();
            a.maptile = new MapTile();
			a.map = a.maptile.getMap();
            a.engiDex = new EngiDex(a.skillDex);

            a.fillMap();

            return a;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GameStateException(1);
        }
    }

	public Tile[][] getMap(){
		return map;
	}

    public MapTile getMapTile() {
        return maptile;
    }

    public void applyPlayerPosition() {
        // map[player.getPositionY()][player.getPositionX()].setType(TileType.PLAYER);
        // maptile.makeOccupied(player.getPositionX(), player.getPositionY());
		map[player.getPositionY()][player.getPositionX()].makeOccupied();
    }

	public void setSprite() {
		if (!isGameOver){
			String path;
			switch(map[player.getPositionY()][player.getPositionX()].getType()) {
				case EDGE1_MOUNTAIN:
					path = "/char_edge5.png";
					break;
				case EDGE2_MOUNTAIN:
					path = "/char_edge4.png";
					break;
				case EDGE3_MOUNTAIN:
					path = "/char_edge6.png";
					break;
				case EDGE_GRASS:
					path = "/char_edge1.png";
					break;
				case EDGE_TUNDRA:
					path = "/char_edge3.png";
					break;
				case GRASS:
					path = "/char_grass.png";
					break;
				case MOUNTAIN:
					path = "/char_mountain.png";
					break;
				case TUNDRA:
					path = "/char_tundra.png";
					break;
				default: // water
					path = "/char_watergif.gif";
			}

			// set player sprite
			Tile tile = new Tile(path, TileType.PLAYER, player.getPositionX(),player.getPositionY(),true);
			map[player.getPositionY()][player.getPositionX()] = tile;

			// set active engimon sprite
			Engimon aE = player.getActiveEngimon();
			path = getEngimonSprite(aE,
									map[aE.getPosition().getY()][aE.getPosition().getX()].getType());
			tile = new Tile(path, TileType.ENGIMON,
							aE.getPosition().getX(), aE.getPosition().getY(), true);
			map[aE.getPosition().getY()][aE.getPosition().getX()] = tile;

			// set wild sprite
			for (Engimon el : wildEngimons){
				String pathW;
				if (el.getLvl() > player.getActiveEngimon().getLvl()){
					pathW = getEngimonSpriteWild(el, "big", map[el.getPosition().getY()][el.getPosition().getX()].getType());
				}
				else{
					pathW = getEngimonSpriteWild(el, "small", map[el.getPosition().getY()][el.getPosition().getX()].getType());
				}
				Tile tileW = new Tile(pathW, TileType.ENGIMON, el.getPosition().getX(), el.getPosition().getY(), true);
				map[el.getPosition().getY()][el.getPosition().getX()] = tileW;
			}
		}
		else{
			for (int i=0; i<maptile.getSizeY(); i++){
				for(int j=0; j<maptile.getSizeX(); j++){
					map[i][j] = new Tile("data/resource/black.png", TileType.PLAYER, i,j, true);
				}
			}
		}
	}

    public void applyActiveEngimonPosition() {
		int i;
		int j;

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
            default:
				i = -1;
				j = 0;
		}

        Engimon aE = player.getActiveEngimon();
        aE.setPos(player.getPosition().getX() + i,
                  player.getPosition().getY() + j);
		map[aE.getPosition().getY()][aE.getPosition().getX()].makeOccupied();
    }

    private String getEngimonSprite(Engimon engie, TileType tipe) {
        StringBuilder SB = new StringBuilder("/");
        SB.append(engie.getSpecies().replaceAll("\\s+", "").toLowerCase());
        SB.append("/");
        SB.append(engie.getSpecies().replaceAll("\\s+", "").toLowerCase());
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

    private String getEngimonSpriteWild(Engimon engie, String type, TileType tipe) {
        StringBuilder SB = new StringBuilder("/");
        SB.append(engie.getSpecies().replaceAll("\\s+", "").toLowerCase());
        SB.append("/");
        SB.append(engie.getSpecies().replaceAll("\\s+", "").toLowerCase());
		SB.append("_wild_");
		SB.append(type);
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

    public boolean canMoveEntity(int dx, int dy, int x, int y) {
        if (y + dy >= maptile.getSizeY()
            || x + dx >= maptile.getSizeX()
            || x + dx < 0
            || y + dy < 0) {
            return false;
        }

        // if (maptile.isOccupied(x + dx, y + dy)) {
        //     return false;
        // }
		if (map[y+dy][x+dx].isOccupied() == true) {
            return false;
        }

        return true;
    }

    public boolean canMoveEngimon(int dx, int dy, int x, int y, String engieSpecies) {
        if (!canMoveEntity(dx, dy, x, y)) {
            return false;
        }

        Tile targetTile = map[y + dy][x + dx];
        Set<Element> elSet = engiDex.getEntity(engieSpecies).getListElement().getElementsList();
        switch (targetTile.getType()) {
            case ENGIMON:
                return false;
            case PLAYER:
                return false;
			case EDGE1_MOUNTAIN:
                return elSet.contains(Element.FIRE);
			case EDGE2_MOUNTAIN:
                return elSet.contains(Element.FIRE);
			case EDGE3_MOUNTAIN:
                return elSet.contains(Element.FIRE);
			case MOUNTAIN:
                return elSet.contains(Element.FIRE);
			case EDGE_GRASS:
                return elSet.contains(Element.GROUND) || elSet.contains(Element.ELECTRIC);
			case GRASS:
                return elSet.contains(Element.GROUND) || elSet.contains(Element.ELECTRIC);
			case EDGE_TUNDRA:
                return elSet.contains(Element.ICE);
			case TUNDRA:
                return elSet.contains(Element.ICE);
            default: //water
                return elSet.contains(Element.WATER);
        }
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

    private void activateWildEngimons() {
        for (Engimon wildEngimon : wildEngimons) {
            // move
            Random rand = new Random();
            boolean horizontalMove = Math.abs(rand.nextInt()) % 2 == 0;
            boolean neg = Math.abs(rand.nextInt()) % 2 == 1;
            int dx = 0;
            int dy = 0;

            if (!horizontalMove) {
                dy = 1 * (neg ? -1 : 1);
            } else {
                dx = 1 * (neg ? -1 : 1);
            }

            Position oldPos = wildEngimon.getPosition();
            int oldX = oldPos.getX();
            int oldY = oldPos.getY();
            if (canMoveEngimon(dx, dy, oldX, oldY, wildEngimon.getSpecies())) {
                maptile.makeFree(oldY, oldX);
                wildEngimon.setPos(dx + oldX, dy + oldY);
                // maptile.makeOccupied(oldX + dx, oldY + dy);
				map[oldY + dy][oldX + dx].makeOccupied();
            }

            // add exp
            int dExp = Math.abs(rand.nextInt()) % 10;
            try {
                if (!wildEngimon.addExp(dExp)) {
                    wildEngimons.remove(wildEngimon);
                }
            } catch (EngimonException e) {
                // do nothing ae lah
            }
        }
    }

    private Engimon makeWildEngimon() throws
        EngimonException, ElementsListException, EngimonSpeciesException {
        Random seeder = new Random();
        Random rand = new Random(seeder.nextLong());

        int lvlMinBound = player.getHighestEngimonLevel();
        int lvlMaxBound = player.getActiveEngimon().getLvl() + 5;
        int engiLvl;
        try {
            engiLvl = Math.max(Math.abs(rand.nextInt()) % (lvlMaxBound - lvlMinBound), 0)
                        + lvlMinBound;
        } catch (ArithmeticException e) {
            engiLvl = lvlMinBound;
        }

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

        do {
            x = rand.nextInt() % Tile.maxX;
            if (x < 0) x += Tile.maxX;
            y = rand.nextInt() % Tile.maxY;
            if (y < 0) y += Tile.maxY;
        } while (x >= emptyTiles.size()
                || y >= emptyTiles.get(x).size()
                || !canMoveEngimon(0, 0, x, y, engie.getSpecies()));

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
        if (wildEngimons.size() >= wildEngieCount || count == 0) {
            return;
        }

        for (int i = 0; i < count; ++i) {
            try {
                Engimon engie = makeWildEngimon();
                putWildEngimon(engie);
                wildEngimons.add(engie);
            } catch (EngimonHunter2000Exception e) {
                --i; // coba lagi kalo gagal
                continue;
            }
        }
    }

	public void setGameOver(){
		isGameOver = true;
	}

    public EngiDex getEDex(){ return this.engiDex; }
    public SkillDex getSkillDex(){ return this.skillDex; }
    public ArrayList<Engimon> getWildEngimons(){ return this.wildEngimons; }
}

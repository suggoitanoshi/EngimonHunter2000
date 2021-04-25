package EngimonHunter2000;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class GameState {
    Player player;
    SkillDex skillDex;
    EngiDex engiDex;
    MapTile map;
    ArrayList<Engimon> wildEngimons;

    public GameState() {
        try {
            skillDex = new SkillDex();
            skillDex.getDexDataFromFile("data/Skills.csv");
            engiDex = new EngiDex(skillDex);
            engiDex.getDexDataFromFile("data/Engimons.csv");
            map = new MapTile();
            player = new Player(engiDex);
        } catch (EngimonHunter2000Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Tile[][] getMap() {
        return map.getMap();
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
                if (!map.getTile(x, y).isOccupied()) {
                    perantara.add(map.getTile(x, y));
                }
                ret.add(perantara);
            }
        }

        return ret;
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
            map.getTile(x, y).makeOccupied();
            emptyTiles.get(x).remove(y);
        }
    }
}

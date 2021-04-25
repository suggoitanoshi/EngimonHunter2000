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
    transient private MapTile map;
    private Player player;
    private ArrayList<Engimon> wildEngimons;

    public GameState() {
        try {
            skillDex = new SkillDex();
            engiDex = new EngiDex(skillDex);
            map = new MapTile();
            player = new Player(engiDex);
            wildEngimons = new ArrayList<>();
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
                if (!map.getTile(x, y).isOccupied()) {
                    perantara.add(map.getTile(x, y));
                }
            }
            ret.add(perantara);
        }

        return ret;
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
            emptyTile = map.getTile(x, y);
        } while (x >= emptyTiles.size() ||
                 y >= emptyTiles.get(x).size() ||
                 !emptyTiles.get(x).contains(emptyTile));

        engie.setPos(x, y);
        map.getTile(x, y).makeOccupied();
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

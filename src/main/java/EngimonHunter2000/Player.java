package EngimonHunter2000;

import java.io.Serializable;

/**
 * class untuk menyimpan semua keperluan Player {@link EngimonHunter2000}
 * @author Cynthia Rusadi
 */

public class Player implements Serializable {
    public static final long serialVersionUID = 1L;
    private Inventory<Engimon> listEngimon;
    private Inventory<Item> listItem;
    private Engimon activeEngi;
    private Position position;
    private char dir;

    // constructor
    public Player(EngiDex dex)
            throws EngimonSpeciesException, ElementsListException, EngimonException, InventoryException {
        Engimon engie = new Engimon(dex, "Picakhu", "Picakhu");
        engie.addLevel(10);
        Inventory.setMax(50);
        this.listEngimon = new Inventory<Engimon>();
        this.listEngimon.addItem(engie);
        this.listItem = new Inventory<Item>();
        this.activeEngi = engie;
        this.dir = 'a';
        this.position = new Position();
    }

    // getters
    public Inventory<Engimon> getInventoryEngimon() {
        return this.listEngimon;
    }

    public Inventory<Item> getInventoryItem() {
        return this.listItem;
    }

    public Engimon getActiveEngimon() {
        return this.activeEngi;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getPositionX() {
        return this.position.getX();
    }

    public int getPositionY() {
        return this.position.getY();
    }

    public char getDir() {
        return this.dir;
    }

    // setters
    public void setPosition(Position pos) {
        this.position.setPosition(pos);
    }

    public void setPositionX(int pos) {
        this.position.setX(pos);
    }

    public void setPositionY(int pos) {
        this.position.setY(pos);
    }

    public void setDir(char _dir) {
        this.dir = _dir;
    }

    // methods
    public void checkActiveEngimon() {
        this.activeEngi.showEngimon();
    }

    public void switchEngimon(int engiIdx) throws InventoryException {
        Position currPos = this.activeEngi.getPosition();
        this.activeEngi.setPos(-1, -1);

        this.listEngimon.at(engiIdx).setPos(currPos.getX(), currPos.getY());
        this.activeEngi = this.listEngimon.at(engiIdx);
    }

    // specifically only one engimon
    public void showEngimon(int engiIdx) throws InventoryException {
        this.listEngimon.at(engiIdx).showEngimon();
    }

    // print all engimon in inventory
    public void showEngimon() throws InventoryException {
        if (this.listEngimon.getAllInvenTotalItemCount() == 0)
            throw new InventoryException(1);
        System.out.println("Engimon di dalam Inventory:");
        this.listEngimon.showInventory();
    }

    public void changeEngimonName(int engiIdx, String newName) throws EngimonException, InventoryException {
        this.listEngimon.at(engiIdx).setName(newName);
    }

    public void addEngimon(Engimon engi) throws InventoryException {
        this.listEngimon.addItem(engi);
    }

    public void useItem(int itemIdx) throws ItemException, InventoryException, SkillEngimonException {
        int engiIdx = this.listEngimon.getItemFromIdx(this.activeEngi);
        if (engiIdx != -1) {
            this.listItem.at(itemIdx).learn(this.listEngimon.at(engiIdx));
            int qty = this.listItem.at(itemIdx).getQuantity();
            if (qty - 1 == 0) {
                listItem.removeItem(listItem.at(itemIdx));
            } else {
                this.listItem.at(itemIdx).setQuantity(qty - 1);
            }
        } else {
            throw new InventoryException(3);
        }
    }

    public void addItem(Item item) throws InventoryException {
        this.listItem.addItemNoDupe(item);
    }

    public void removeEngimon(int idx) throws InventoryException {
        this.listEngimon.removeItem(this.listEngimon.at(idx));
        if (this.listEngimon.at(idx).equals(this.activeEngi)) {
            this.activeEngi = this.listEngimon.at(0);
            // kalo broken, artinya dia cuman punya 1 engimon
        }
    }

    public void removeActiveEngimon() throws InventoryException {
        int idx = this.listEngimon.getItemFromIdx(this.activeEngi);
        if (idx != -1) {
            this.listEngimon.removeItem(this.listEngimon.at(idx));
        } else {
            throw new InventoryException(3);
        }
    }

    public void removeItem(int itemIdx, int n) throws InventoryException {
        if (this.listItem.at(itemIdx).getQuantity() == n) {
            this.listItem.removeItem(listItem.at(itemIdx), n);
        } else {
            for (int i = 0; i < n; i++) {
                this.listItem.at(itemIdx).decQuantity();
            }
            this.listItem.removeItemNoDupe(this.listItem.at(itemIdx), n);
        }
    }

    public void interact() {
        this.activeEngi.interact();
    }

    /**
     * Fungsi untuk mendapatkan engimon player yang memiliki level tertiinggi
     */
    public int getHighestEngimonLevel() {
        int highest = 0xFFFFFFFF;
        for (int i = 0; i < listEngimon.getAllInvenTotalItemCount(); ++i) {
            try {
                Engimon disEngie = listEngimon.at(i);
                highest = disEngie.getLvl() > highest ? disEngie.getLvl() : highest;
            } catch (InventoryException e) {
                System.err.println(e.what());
                break; // idk what to do, really ¯\_(ツ)_/¯
            }
        }

        return highest;
    }
}

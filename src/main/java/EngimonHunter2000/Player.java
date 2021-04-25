package EngimonHunter2000;

/**
 * class untuk menyimpan semua keperluan Player {@link EngimonHunter2000}
 * @author Cynthia Rusadi
 */

public class Player {
    private Inventory<Engimon> listEngimon;
    private Inventory<Item> listItem;
    private Engimon activeEngi;
    private Position position;
    private char dir;

    // constructor
    public Player(EngiDex dex) throws EngimonSpeciesException, ElementsListException {
        this.listEngimon = new Inventory<Engimon>();
        this.listItem = new Inventory<Item>();
        this.activeEngi = new Engimon(dex, "Picakhu", "Picakhu");
        this.dir = 'a';
        this.position = new Position();
    }

    // getters
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

    public Engimon getEngimonByID(int idx) throws InventoryException {
        return this.listEngimon.at(idx);
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
        if (this.listEngimon.getItemCount() == 0) throw new InventoryException(1);
        System.out.println("Engimon di dalam Inventory:");
        this.listEngimon.showInventory();
    }

    public void changeEngimonName(int engiIdx, String newName) throws EngimonException, InventoryException {
        this.listEngimon.at(engiIdx).setName(newName);
    }

    public void addEngimon(Engimon engi) throws InventoryException {
        this.listEngimon.addItem(engi);
    }

    public void showItem(int itemIdx) {
        this.listItem.at(itemIdx).showItem();
    }

    public void showItem() throws InventoryException {
        if (this.listItem.getItemCount() == 0) throw new InventoryException(1);
        System.out.println("Skill Item di dalam Inventory:");
        this.listItem.showInventory();
    }

    public void useItem(int engiIdx, int itemIdx, Dex dex) {
        this.listItem.at(itemIdx).learn(this.listEngimon.at(engiIdx), dex);
        if (this.listItem.at(itemIdx).getQuantity() == 0) {
            listItem.removeItem(listItem.at(itemIdx));
        }
    }

    public void addItem(Item item) throws InventoryException {
        this.listItem.addItemNoDupe(item);
    }

    public void removeItem(int itemIdx, int n) throws InventoryException {
        if (this.listItem.at(itemIdx).getQuantity() == n) {
            this.listItem.removeItem(listItem.at(itemIdx), n);
        }
        else {
            for (int i = 0; i < n; i++) {
                this.listItem.at(itemIdx).decQuantity();
            }
            this.listItem.removeItemNoDupe(this.listItem.at(itemIdx), n);
        }
    }

    public void interact() {
        this.activeEngi.interact();
    }
}

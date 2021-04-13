package EngimonHunter2000;

/**
 * class untuk menyimpan semua keperluan Player {@link EngimonHunter2000}
 * @author Cynthia Rusadi
 */

// TODO: engimon + item related stuff
public class Player {
    // private Inventory<Engimon> listEngimon;
    // private Inventory<Item> listItem;
    // private Engimon activeEngi;
    private Position position;
    private char dir;

    // constructor
    Player() {
        // this.listEngimon = new Inventory<Engimon>();
        // this.listItem = new Inventory<Item>();
        // this.activeEngi = 0;
        this.dir = 'a';
        this.position = new Position();
    }

    // getters
    // public Engimon getActiveEngimon() {
    //     return this.activeEngi;
    // }

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

    // public Engimon getEngimonByID(int idx) {
    //     return this.listEngimon[idx];
    // }

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
    // public void checkActiveEngimon() {
        // this.activeEngi.showEngimon();
    // }

    // public void switchEngimon(int engiIdx) {
    //     Position currPos = this.activeEngi.getPosition();
    //     this.activeEngi.setPosition(-1, -1);

    //     this.listEngimon[engiIdx].setPosition(currPos.getX(), currPos.getY());
    //     this.activeEngi = this.listengimon[engiIdx];
    // }

    // specifically only one engimon
    // public void showEngimon(int engiIdx) {
        // this.listEngimon[engiIdx].showEngimon();
    // }

    // print all engimon in inventory
    // public void showEngimon() throws InventoryException {
    //     if (this.listEngimon.getItemCount() == 0) throw new InventoryException(1);
    //     System.out.println("Engimon di dalam Inventory:");
    //     this.listEngimon.showInventory();
    // }

    // public void changeEngimonName(int engiIdx, String newName) {
    //     this.listEngimon[engiIdx].setName(newName);
    // }

    // public void addEngimon(Engimon engi) {
    //     this.listEngimon.addItem(engi);
    // }

    // public void showItem(int itemIdx) {
    //     this.listItem[itemIdx].showItem();
    // }

    // public void showItem() throws InventoryException {
    //     if (this.listItem.getItemCount() == 0) throw new InventoryException(1);
    //     System.out.println("Skill Item di dalam Inventory:");
    //     this.listItem.showInventory();
    // }

    // public void useItem(int engiIdx, int itemIdx, Dex dex) {
    //     this.listItem[itemIdx].learn(this.listEngimon[engiIdx], dex);
    //     if (this.listItem[itemIdx].getQuantity() == 0) {
    //         removeItem(listItem[itemIdx]);
    //     }
    // }

    // public void addItem(Item item) {
    //     this.listItem.addItemNoDupe(item);
    // }

    // public void removeItem(Item item) {
    //     this.listItem.removeItemNoDupe(item);
    // }

    // public void removeItem(String itemIdx, int n) {

    // }

    // public void interact() {
    //     this.activeEngi.interact();
    // }

    // TODO: mikirin ini butuh apa kagak
    // public void itemIsEmpty() {

    // }

    // public void inventoryIsFull() {

    // }
}

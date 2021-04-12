package EngimonHunter2000;

/**
 * class untuk menyimpan semua keperluan Player {@link EngimonHunter2000}
 * 
 * @author Cynthia Rusadi
 */

// TODO: engimon + item related stuff
public class Player {
    // private Inventory<Engimon> listEngimon;
    // private Inventory<Item> listItem;
    private int activeEngi;
    private Position position;
    private char dir;

    // constructor
    Player() {
        this.activeEngi = 0;
        this.dir = 'a';
        this.position = new Position();
    }

    // getters
    // public Engimon getActiveEngimon() {

    // }

    public Position getPosition() {
        return this.position;
    }

    public int getPositionX() {
        return this.position.getLeft();
    }

    public int getPositionY() {
        return this.position.getRight();
    }

    public char getDir() {
        return this.dir;
    }

    // public Engimon getEngimonByID(int idx) {
    //     return this.listEngimon[idx];
    // }

    // public static Engimon getEngimonFromName(String engiName) throws InventoryException, PlayerException {
    //     if (listEngimon.getItemCount() == 0) throw InventoryException(1);
    //     for (int i = 0; i < listEngimon.getItemCount(); i++) {
    //         if (engiName.equals(listEngimon.at(i).getName())) {
    //             return listEngimon.at(i);
    //         }
    //     }
    //     throw PlayerException(0);
    // }

    // public Item getItemFromName(String itemName) throws InventoryException, PlayerException {
        // if (this.listItem.getItemCount() == 0) throw InventoryException(1);
        // for (int i = 0; i < this.listItem.getItemCount(); i++) {
        //     if (itemName.equals(this.listItem.at(i).getName())) {
        //         return this.listEngimon.at(i);
        //     }
        // }
        // throw PlayerException(1);
    // }

    // public int getEngimonIdxFromName(String engiName) {
    //     Engimon temp = getEngimonFromName(engiName);
    //     return this.listEngimon.getFirstItemIndex(temp);
    // }

    // public int getItemIdxFromName(String itemName) {
    //     Item temp = getItemFromName(itemName);
    //     return this.listItem.getFirstItemIndex(temp);
    // }

    // setters
    public void setPosition(Position pos) {
        this.position.setPair(pos);
    }

    public void setPositionX(int pos) {
        this.position.setLeft(pos);
    }

    public void setPositionY(int pos) {
        this.position.setRight(pos);
    }

    public void setDir(char _dir) {
        this.dir = _dir;
    }

    // methods
    // public void checkActiveEngimon() {
        // this.listEngimon[activeEngi].showEngimon();
    // }

    // public void switchEngimon(int engiIdx) {
        // Position currPos = this.listEngimon[this.activeEngi].getPosition();
        // this.listEngimon[this.activeEngi].setPair(-1, -1);

        // this.listEngimon[engiIdx].setPair(currPos.getLeft(), currPos.getRight());
        // this.activeEngi = engiIdx;
    // }

    // specifically only one engimon
    // public void showEngimon(int engiIdx) {
        // this.listEngimon[engiIdx].showEngimon();
    // }

    // print all engimon in inventory
    // public void showEngimon() {
        // System.out.println("Engimon di dalam Inventory:");
        // this.litEngimon.showInventory();
    // }

    // public void changeEngimonName(int engiIdx, String newName) {
    //     this.listEngimon[engiIdx].setName(newName);
    // }

    // public void addEngimon(Engimon engi) {
    //     this.listEngimon.addItem(engi);
    // }

    // public void addEngimon(String engiName) {
    //     Engimon temp = getEngimonFromName(engiName);
    //     addEngimon(temp);
    // }

    // public void removeEngimon(Engimon engi) {
    //     this.listEngimon.removeItem(engi);        
    // }

    // public void removeEngimon(String engiName) {
    //     Engimon temp = getEngimonFromName(engiName);
    //     removeEngimon(temp);
    // }

    // public void showItem(String itemName) {
    //     Item temp = getItemFromName(itemName);
    //     temp.showItem();
    // }

    // public void showItem(int itemIdx) {
    //     this.listItem[itemIdx].showItem();
    // }

    // public void showItem() {
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

    // public void addItem(String itemName) {
    //     Item temp;
    //     try {
    //         temp = getItemFromName(itemName);
    //         temp.setQuantity(temp.getQuantity() + 1);
    //     }
    //     catch (Exception e) {
    //         Dex dex;
    //         temp = Item(dex.getSkill(itemName), 1);
    //     }
    //     addItem(temp);
    // }

    // public void removeItem(Item item) {
    //     this.listItem.removeItemNoDupe(item);
    // }

    // public void removeItem(Item item, int n) {

    // }

    // public void removeItem(String itemName) {
    //     Item temp = getItemFromName(itemName);
    //     removeItem(temp);
    // }

    // public void removeItem(String item, int n) {

    // }

    // public void interact() {
        // listEngimon[activeEngi].interact();
    // }

    // TODO: mikirin ini butuh apa kagak
    // public void engimonIsEmpty() {

    // }

    // public void itemIsEmpty() {

    // }

    // public void inventoryIsFull() {

    // }
}

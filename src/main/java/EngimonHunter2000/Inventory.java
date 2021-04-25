package EngimonHunter2000;

import java.util.ArrayList;
import java.io.Serializable;

class Inventory<T> implements Serializable {
    public static final long serialVersionUID = 1L;
    private ArrayList<T> container;
    private static int max;
    private static int itemCount = 0;
    public Inventory() {
        this.container = new ArrayList<T>();
    }

    public static void setMax(int max) {
        Inventory.max = max;
    }

    public void addItem(T item) throws InventoryException {
        if (Inventory.itemCount < Inventory.max) {
            this.container.add(item);
            Inventory.itemCount++;
        } else {
            throw new InventoryException(0);
        }
    }

    public void addItemNoDupe(T item) throws InventoryException {
        if (Inventory.itemCount < Inventory.max) {
            if (!this.container.contains(item)) {
                this.container.add(item);
            }
            Inventory.itemCount++;
        } else {
            throw new InventoryException(0);
        }
    }

    public void removeItem(T item) throws InventoryException {
        if (this.container.size() == 0)
            throw new InventoryException(1);
        if (this.container.contains(item)) {
            this.container.remove(item);
            Inventory.itemCount--;
        } else {
            throw new InventoryException(2);
        }
    }

    public void removeItem(T item, int n) throws InventoryException {
        if (this.container.size() == 0)
            throw new InventoryException(1);
        if (this.container.contains(item)) {
            this.container.remove(item);
            Inventory.itemCount = Inventory.itemCount - n;
        }
        else {
            throw new InventoryException(2);
        }
    }

    public void removeItemNoDupe(T item, int n) throws InventoryException {
        if (this.container.size() == 0)
            throw new InventoryException(1);
        if (this.container.contains(item)) {
            Inventory.itemCount = Inventory.itemCount - n;
        }
        else {
            throw new InventoryException(2);
        }
    }

    public void showInventory() {
        for (int i = 0; i < this.container.size(); i++) {
            System.out.println((i + 1) + ". " + this.container.get(i).toString());
        }
    }

    public T at(int i) throws InventoryException {
        if (i < this.container.size()) {
            return this.container.get(i);
        } else {
            throw new InventoryException(3);
        }
    }

    public int getItemCount() {
        return Inventory.itemCount;
    }

    public boolean isFull() {
        return Inventory.itemCount == Inventory.max;
    }

    public static void resetItems() {
        Inventory.itemCount = 0;
    }

    public int getItemFromIdx(T item) throws InventoryException {
        if (this.container.size() == 0)
            throw new InventoryException(1);
        if (this.container.contains(item)) {
            for (int i = 0; i < this.container.size(); i++) {
                if (this.container.get(i).equals(item)) {
                    return i;
                }
            }
        }
        else {
            throw new InventoryException(2);
        }
        return -1;
    }
}

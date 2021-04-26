/**
 * TODO: Add skill item/engimon to test this class
 */
package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InventoryTest {
    @BeforeAll
    public static void initAll() {
        Inventory.resetItems();
        Inventory.setMax(5);
    }

    @AfterEach
    public void cleanup() {
        Inventory.resetItems();
    }

    @Test
    public void constructOne() {
        Inventory<Integer> i1 = new Inventory<Integer>();
        assertAll("Konstruk satu inventory",
            () -> assertEquals(0, i1.getItemCount()));
    }

    @Test
    public void constructTwo() {
        Inventory<Integer> i1 = new Inventory<Integer>();
        Inventory<Float> i2 = new Inventory<Float>();
        assertAll("Konstruk dua inventory",
            () -> assertEquals(0, i1.getItemCount()),
            () -> assertEquals(0, i2.getItemCount()));
    }

    @Test
    public void addOnlyOneInventory() throws InventoryException {
        Inventory<Integer> i1 = new Inventory<Integer>();
        i1.addItem(0);
        assertAll("Tambah item ke satu inventory",
            () -> assertEquals(1, i1.getItemCount()));
    }

    @Test
    public void addTwoInventories() throws InventoryException {
        Inventory<Integer> i1 = new Inventory<Integer>();
        Inventory<Float> i2 = new Inventory<Float>();
        i1.addItem(0);
        i2.addItem(0f);
        assertAll("Tambah item ke dua inventory",
            () -> assertEquals(2, i1.getItemCount()),
            () -> assertEquals(2, i2.getItemCount()));
    }

    @Test
    public void addOneLimit() throws InventoryException {
        Inventory<Integer> i1 = new Inventory<Integer>();
        for (int i = 0; i < 5; i++) {
            i1.addItem(i);
        }
        assertThrows(InventoryException.class, () -> i1.addItem(6));
    }

    @Test
    public void addTwoLimit() throws InventoryException {
        Inventory<Integer> i1 = new Inventory<Integer>();
        Inventory<Float> i2 = new Inventory<Float>();
        for (int i = 0; i < 5; i++) {
            i1.addItem(i);
        }
        assertThrows(InventoryException.class, () -> i2.addItem(6f));
    }

    @Test
    public void removeOneEmpty() {
        Inventory<Integer> i1 = new Inventory<Integer>();
        assertThrows(InventoryException.class, () -> i1.removeItem(0));
    }

    public void removeOneValid() throws InventoryException {
        Inventory<Integer> i1 = new Inventory<Integer>();
        i1.addItem(0);
        i1.removeItem(0);
        assertAll("Penghapusan invalid",
            () -> assertEquals(0, i1.getItemCount()),
            () -> assertThrows(InventoryException.class, () -> i1.at(0)));
    }

    public void removeTwoInvalid() throws InventoryException {
        Inventory<Integer> i1 = new Inventory<Integer>();
        Inventory<Float> i2 = new Inventory<Float>();
        i1.addItem(0);
        assertAll("Penghapusan invalid pada inventory ke-2",
            () -> assertThrows(
                    InventoryException.class, () -> i2.removeItem(0f)));
    }
}

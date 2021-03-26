/**
 * SkillTest.cpp
 * y e e wangy wangy
 *
 * untuk menguji implementasi class Inventory
 */

#include <gtest/gtest.h>

#include "../src/headers/BaseInventory.hpp"
#include "../src/headers/InventoryException.hpp"
#include "../src/impl/Inventory.cpp"

// uji Inventory dengan int
// uji konstruktor
TEST(Inventory, IntInventoryConstructor) {
    Inventory<int> inv = Inventory<int>();
    EXPECT_EQ(inv.getItemCount(), 0);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 0);
}
// uji tambah item
TEST(Inventory, IntInventoryAddItem) {
    Inventory<int> inv = Inventory<int>();
    int item = 0;
    EXPECT_NO_THROW(inv.addItem(item));
    EXPECT_EQ(inv.getItemCount(), 1);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 1);
}
// uji inventory penuh
TEST(Inventory, IntInventoryFullAddItem) {
    Inventory<int> inv = Inventory<int>();
    for (int i = 0; i < BaseInventory::getMaxCapacity(); i++) {
        BaseInventory::incrementItem();
    }
    int item = 0;
    EXPECT_THROW(inv.addItem(item), InventoryException);
    for (int i = 0; i < BaseInventory::getMaxCapacity(); i++) {
        BaseInventory::subtractItem();
    }
}
// uji hapus item
TEST(Inventory, IntInventoryRemoveItem) {
    Inventory<int> inv = Inventory<int>();
    int item = 0;
    inv.addItem(item);
    EXPECT_NO_THROW(inv.removeItem(item));
    EXPECT_EQ(inv.getItemCount(), 0);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 0);
}
// uji inventory kosong
TEST(Inventory, IntInventoryEmptyRemoveItem) {
    Inventory<int> inv = Inventory<int>();
    int item = 0;
    EXPECT_THROW(inv.removeItem(item), InventoryException);
}
// uji hapus item yang tidak ada
TEST(Inventory, IntInventoryNonexistentRemoveItem) {
    Inventory<int> inv = Inventory<int>();
    int item1 = 0, item2 = 1;
    int invaliditem = 2;
    inv.addItem(item1);
    inv.addItem(item2);
    EXPECT_THROW(inv.removeItem(invaliditem), InventoryException);
}
// uji pengambilan elemen ke-i
TEST(Inventory, IntInventoryBracketOperator) {
    Inventory<int> inv = Inventory<int>();
    int items[5] = {0, 2, 4, 6, 8};
    for (int i = 0; i < 5; i++) {
        inv.addItem(items[i]);
    }
    for (int i = 4; i >= 0; i--) {
        EXPECT_EQ(inv[i], i * 2);
    }
}
// uji pengambilan indeks tidak valid
TEST(Inventory, IntInventoryBracketOperatorInvalid) {
    Inventory<int> inv = Inventory<int>();
    EXPECT_THROW(inv[0], InventoryException);
}

// uji dua inventory berbeda
TEST(Inventory, IntInventoryTwoInstance) {
    Inventory<int> inv1 = Inventory<int>();
    Inventory<int> inv2 = Inventory<int>();
    int item = 0;
    inv1.addItem(item);
    inv2.addItem(item);
    EXPECT_EQ(inv1.getItemCount(), 1);
    EXPECT_EQ(inv2.getItemCount(), 1);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 2);
}
// uji penghapusan dari satu dari dua instans
TEST(Inventory, IntInventoryTwoInstanceRemove) {
    Inventory<int> inv1 = Inventory<int>();
    Inventory<int> inv2 = Inventory<int>();
    int item = 0;
    inv1.addItem(item);
    inv2.addItem(item);
    inv2.removeItem(item);
    EXPECT_EQ(inv1.getItemCount(), 1);
    EXPECT_EQ(inv2.getItemCount(), 0);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 1);
}
// uji dua tipe berbeda
TEST(Inventory, InventoryMultipleType) {
    Inventory<int> inv1 = Inventory<int>();
    Inventory<float> inv2 = Inventory<float>();
    int item1 = 0;
    float item2 = 0;
    inv1.addItem(item1);
    inv2.addItem(item2);
    EXPECT_EQ(inv1.getItemCount(), 1);
    EXPECT_EQ(inv2.getItemCount(), 1);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 2);
}
// uji penghapusan dari satu dari dua instans
TEST(Inventory, InventoryMultipleTypeRemove) {
    Inventory<int> inv1 = Inventory<int>();
    Inventory<float> inv2 = Inventory<float>();
    int item1 = 0;
    float item2 = 0;
    inv1.addItem(item1);
    inv2.addItem(item2);
    inv2.removeItem(item2);
    EXPECT_EQ(inv1.getItemCount(), 1);
    EXPECT_EQ(inv2.getItemCount(), 0);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 1);
}

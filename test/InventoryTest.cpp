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
    EXPECT_NO_THROW(inv.addItem(0));
    EXPECT_EQ(inv.getItemCount(), 1);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 1);
}
// uji inventory penuh
TEST(Inventory, IntInventoryFullAddItem) {
    Inventory<int> inv = Inventory<int>();
    for (int i = 0; i < BaseInventory::getMaxCapacity(); i++) {
        BaseInventory::incrementItem();
    }
    EXPECT_THROW(inv.addItem(0), InventoryException);
    for (int i = 0; i < BaseInventory::getMaxCapacity(); i++) {
        BaseInventory::subtractItem();
    }
}
// uji hapus item
TEST(Inventory, IntInventoryRemoveItem) {
    Inventory<int> inv = Inventory<int>();
    inv.addItem(0);
    EXPECT_NO_THROW(inv.removeItem(0));
    EXPECT_EQ(inv.getItemCount(), 0);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 0);
}
// uji inventory kosong
TEST(Inventory, IntInventoryEmptyRemoveItem) {
    Inventory<int> inv = Inventory<int>();
    EXPECT_THROW(inv.removeItem(0), InventoryException);
}
// uji hapus item yang tidak ada
TEST(Inventory, IntInventoryNonexistentRemoveItem) {
    Inventory<int> inv = Inventory<int>();
    inv.addItem(0);
    inv.addItem(1);
    EXPECT_THROW(inv.removeItem(2), InventoryException);
}
// uji pengambilan elemen ke-i
TEST(Inventory, IntInventoryBracketOperator) {
    Inventory<int> inv = Inventory<int>();
    for (int i = 0; i < 5; i++) {
        inv.addItem(i * 2);
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
    inv1.addItem(0);
    inv2.addItem(0);
    EXPECT_EQ(inv1.getItemCount(), 1);
    EXPECT_EQ(inv2.getItemCount(), 1);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 2);
}
// uji penghapusan dari satu dari dua instans
TEST(Inventory, IntInventoryTwoInstanceRemove) {
    Inventory<int> inv1 = Inventory<int>();
    Inventory<int> inv2 = Inventory<int>();
    inv1.addItem(0);
    inv2.addItem(0);
    inv2.removeItem(0);
    EXPECT_EQ(inv1.getItemCount(), 1);
    EXPECT_EQ(inv2.getItemCount(), 0);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 1);
}
// uji dua tipe berbeda
TEST(Inventory, InventoryMultipleType) {
    Inventory<int> inv1 = Inventory<int>();
    Inventory<float> inv2 = Inventory<float>();
    inv1.addItem(0);
    inv2.addItem(0);
    EXPECT_EQ(inv1.getItemCount(), 1);
    EXPECT_EQ(inv2.getItemCount(), 1);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 2);
}
// uji penghapusan dari satu dari dua instans
TEST(Inventory, InventoryMultipleTypeRemove) {
    Inventory<int> inv1 = Inventory<int>();
    Inventory<float> inv2 = Inventory<float>();
    inv1.addItem(0);
    inv2.addItem(0);
    inv2.removeItem(0);
    EXPECT_EQ(inv1.getItemCount(), 1);
    EXPECT_EQ(inv2.getItemCount(), 0);
    EXPECT_EQ(BaseInventory::getTotalItemCount(), 1);
}

/**
 * PlayerTest.cpp
 * y e e wangy wangy
 *
 * untuk menguji implementasi class Player
 */

#include <gtest/gtest.h>

#include "../src/headers/BaseInventory.hpp"
#include "../src/headers/InventoryException.hpp"
#include "../src/impl/Inventory.cpp"
#include "../src/impl/Player.cpp"

// uji konstruktor
TEST(Player, ConstructorPlayer) {
    Player p = Player();
    EXPECT_EQ("yee", p.getName());
    EXPECT_EQ(1, p.getPositionX());
    EXPECT_EQ(1, p.getPositionY());
    EXPECT_EQ('a', p.getDir());
}

// uji setters
TEST(Player, SetterPlayer) {
    Player p = Player();
    p.setName("imba");
    EXPECT_EQ("imba", p.getName());
    p.setPosition(make_tuple(5, 6));
    EXPECT_EQ(5, p.getPositionX());
    EXPECT_EQ(6, p.getPositionY());
    p.setDir('d');
    EXPECT_EQ('d', p.getDir());
}
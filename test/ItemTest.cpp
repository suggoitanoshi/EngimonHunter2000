/**
 * ItemTest.cpp
 * y e e wangy wangy
 */

#include <gtest/gtest.h>

#include <vector>

#include "../src/headers/Item.hpp"

TEST(Item, ConstructorVector) {
    std::vector<Elements> el;
    EXPECT_THROW(Item i("test 1", 0, 100, el, 10), SkillException);
}
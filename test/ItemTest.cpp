/**
 * ItemTest.cpp
 * y e e wangy wangy
 */

#include <gtest/gtest.h>

#include <vector>

#include "../src/headers/Item.hpp"

TEST(Item, ConstructorVector) {
    vector<Elements> el;
    EXPECT_THROW(Item("test 1", 0, 100, el, 10), SkillException);

    EXPECT_NO_THROW(Item("test 1", 0, 100, ELECTRIC, 10));

    el.push_back(ELECTRIC);
    el.push_back(FIRE);
    ASSERT_NO_THROW(Item("test 1", 0, 100, el, 10));

    Item t1("test 1", 0, 100, el, 10);

    EXPECT_EQ(t1.getName(), "test 1");
    EXPECT_EQ(t1.getQuantity(), 10);
    EXPECT_EQ(t1.getElements()[0], ELECTRIC);
}

TEST(Item, ConstructorElement) {
    Item t1("test 1", 0, 100, ELECTRIC, 10);

    EXPECT_EQ(t1.getName(), "test 1");
    EXPECT_EQ(t1.getQuantity(), 10);
    EXPECT_EQ(t1.getElements()[0], ELECTRIC);
}

TEST(Item, CopyConstructor) {
    Item t1("test 1", 100, 1, vector<Elements>{ELECTRIC, FIRE, GROUND}, 10);
    ASSERT_NO_THROW(Item t2(t1));
    Item t2(t1);

    EXPECT_EQ(t1.getName(), t2.getName());
    EXPECT_EQ(t1.getQuantity(), t2.getQuantity());
    EXPECT_EQ(t1.getElements(), t2.getElements());
}

TEST(Item, OperatorEq) {
    Item t1("test 1", 100, 1, vector<Elements>{ELECTRIC, FIRE, GROUND}, 10);
    Item t2(t1);
    Item t3(t1);
    t3.setQuantity(5);

    EXPECT_EQ(t2 == t1, true);
    EXPECT_EQ(t3 == t1, false);
}

TEST(Item, Learn) {
    Dex d("data/Test_Engimons.csv", "data/Test_Skills.csv");
    Engimon e(d.getEngi("Picakhu"));
    Item t(d.getSkill("Tackle"), 10);

    ASSERT_NO_THROW(t.learn(e, d));
    t.learn(e, d);
    EXPECT_EQ(t.getQuantity(), 9);
    EXPECT_EQ(e.getSkillByIndex(0).getName(), d.getSkill("Tackle").getName());
    EXPECT_EQ(e.getSkillByIndex(0).getBasePower(),
              d.getSkill("Tackle").getBasePower());
    EXPECT_EQ(e.getSkillByIndex(0).getElements(),
              d.getSkill("Tackle").getElements());
    EXPECT_EQ(e.getSkillByIndex(0).getMasteryLevel(),
              d.getSkill("Tackle").getMasteryLevel());
}

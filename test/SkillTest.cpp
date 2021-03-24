/**
 * SkillTest.cpp
 * y e e wangy wangy
 *
 * untuk menguji implementasi class Skill
 */

#include <gtest/gtest.h>

#include <vector>

#include "../src/headers/Elements.hpp"
#include "../src/headers/Skill.hpp"

// nguji konstruktor Skill pake vektor
TEST(Skill, ConstructorVector) {
    set<Elements> vec;
    EXPECT_THROW(Skill s1("test 1", 0, 100, vec), SkillException);

    vec.insert(FIRE);
    vec.insert(ELECTRIC);
    EXPECT_NO_THROW(Skill s1("test 1", 0, 100, vec));
}

// nguji getters dari Skill
TEST(Skill, Getter) {
    set<Elements> vec;
    vec.insert(FIRE);
    vec.insert(ELECTRIC);
    vec.insert(GROUND);

    Skill s1("test 1", 100, 0, vec);

    EXPECT_EQ(s1.getName(), "test 1");
    EXPECT_EQ(s1.getBasePower(), 100);
    EXPECT_EQ(s1.getMasteryLevel(), 0);
    EXPECT_EQ(*(s1.getElements().find(FIRE)), FIRE);
    EXPECT_EQ(*(s1.getElements().find(ELECTRIC)), ELECTRIC);
    EXPECT_EQ(*(s1.getElements().find(GROUND)), GROUND);
}

// nguji konstruktor Skill pake satu elemen enum Elements
TEST(Skill, ConstructorSingleElement) {
    EXPECT_NO_THROW(Skill s1("test 1", 100, 0, ELECTRIC));

    Skill s1("test 1", 100, 0, ELECTRIC);
    EXPECT_EQ(*(s1.getElements().find(ELECTRIC)), ELECTRIC);
}

// nguji copy constructor Skill
TEST(Skill, CopyConstructor) {
    set<Elements> vec;
    vec.insert(FIRE);
    vec.insert(ELECTRIC);

    Skill s1("test 1", 100, 0, vec);
    Skill s2(s1);

    EXPECT_EQ(s1.getName(), s2.getName());
    EXPECT_EQ(s1.getBasePower(), s2.getBasePower());
    EXPECT_EQ(s1.getMasteryLevel(), s2.getMasteryLevel());
    EXPECT_EQ(*(s1.getElements().find(FIRE)), *(s2.getElements().find(FIRE)));
    EXPECT_EQ(*(s1.getElements().find(ELECTRIC)),
              *(s2.getElements().find(ELECTRIC)));
}

TEST(Skill, OperatorAssign) {
    set<Elements> vec;
    vec.insert(FIRE);
    vec.insert(ELECTRIC);

    Skill s1("test 1", 100, 0, vec);
    Skill s2("test 2", 0, 0, ELECTRIC);

    s2 = s1;

    EXPECT_EQ(s1.getName(), s2.getName());
    EXPECT_EQ(s1.getBasePower(), s2.getBasePower());
    EXPECT_EQ(s1.getMasteryLevel(), s2.getMasteryLevel());
    EXPECT_EQ(*(s1.getElements().find(FIRE)), *(s2.getElements().find(FIRE)));
    EXPECT_EQ(*(s1.getElements().find(ELECTRIC)),
              *(s2.getElements().find(ELECTRIC)));
}

// nguji setter Skill
TEST(Skill, Setter) {
    Skill s1("test 1", 100, 0, ELECTRIC);
    s1.setMasteryLevel(10);
    EXPECT_EQ(s1.getMasteryLevel(), 10);
}

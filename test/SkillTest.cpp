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
    std::vector<Elements::el> vec;
    EXPECT_THROW(Skill s1("test 1", 0, 100, vec), SkillException);

    vec.push_back(Elements::FIRE);
    vec.push_back(Elements::ELECTRIC);
    EXPECT_NO_THROW(Skill s1("test 1", 0, 100, vec));
}

// nguji getters dari Skill
TEST(Skill, Getter) {
    std::vector<Elements::el> vec;
    vec.push_back(Elements::FIRE);
    vec.push_back(Elements::ELECTRIC);
    vec.push_back(Elements::GROUND);

    Skill s1("test 1", 100, 0, vec);

    EXPECT_EQ(s1.getName(), "test 1");
    EXPECT_EQ(s1.getBasePower(), 100);
    EXPECT_EQ(s1.getMasteryLevel(), 0);
    EXPECT_EQ(s1.getElements()[0], Elements::FIRE);
    EXPECT_EQ(s1.getElements()[1], Elements::ELECTRIC);
    EXPECT_EQ(s1.getElements()[2], Elements::GROUND);
}

// nguji konstruktor Skill pake satu elemen enum Elements
TEST(Skill, ConstructorSingleElement) {
    EXPECT_NO_THROW(Skill s1("test 1", 100, 0, Elements::ELECTRIC));

    Skill s1("test 1", 100, 0, Elements::ELECTRIC);
    EXPECT_EQ(s1.getElements()[0], Elements::ELECTRIC);
}

// nguji copy constructor Skill
TEST(Skill, CopyConstructor) {
    std::vector<Elements::el> vec;
    vec.push_back(Elements::FIRE);
    vec.push_back(Elements::ELECTRIC);

    Skill s1("test 1", 100, 0, vec);
    Skill s2(s1);

    EXPECT_EQ(s1.getName(), s2.getName());
    EXPECT_EQ(s1.getBasePower(), s2.getBasePower());
    EXPECT_EQ(s1.getMasteryLevel(), s2.getMasteryLevel());
    EXPECT_EQ(s1.getElements()[0], s2.getElements()[0]);
    EXPECT_EQ(s1.getElements()[1], s2.getElements()[1]);
}

TEST(Skill, OperatorAssign) {
    std::vector<Elements::el> vec;
    vec.push_back(Elements::FIRE);
    vec.push_back(Elements::ELECTRIC);

    Skill s1("test 1", 100, 0, vec);
    Skill s2("test 2", 0, 0, Elements::ELECTRIC);
    s2 = s1;
    EXPECT_EQ(s1.getName(), s2.getName());
    EXPECT_EQ(s1.getBasePower(), s2.getBasePower());
    EXPECT_EQ(s1.getMasteryLevel(), s2.getMasteryLevel());
    EXPECT_EQ(s1.getElements()[0], s2.getElements()[0]);
    EXPECT_EQ(s1.getElements()[1], s2.getElements()[1]);
}

// nguji setter Skill
TEST(Skill, Setter) {
    Skill s1("test 1", 100, 0, Elements::ELECTRIC);
    s1.setMasteryLevel(10);
    EXPECT_EQ(s1.getMasteryLevel(), 10);
}

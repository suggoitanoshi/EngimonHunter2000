/**
 * DexTest.cpp
 * y e e wangy wangy
 *
 * untuk menguji implementasi class Skill
 */
#include <gtest/gtest.h>

#include <vector>

#include "../src/headers/Dex.hpp"

TEST(Dex, Constructor) { ASSERT_NO_THROW(Dex d); }

TEST(Dex, SkillGetter) {
    Dex d("data/Test_Engimons.csv", "data/Test_Skills.csv");
    string skillName = "THUNDAAA";

    ASSERT_THROW(d.getSkill("ahsdjfhsdjklfhusfhhahdshdushueh"), DexException);
    ASSERT_NO_THROW(d.getSkill(skillName));
    Skill dSkill = d.getSkill(skillName);
    EXPECT_EQ(dSkill.getName(), "THUNDAAA");
    EXPECT_EQ(dSkill.getBasePower(), 40);
    EXPECT_EQ(dSkill.getMasteryLevel(), 1);
    EXPECT_EQ(dSkill.getElements().size(), 1);
    EXPECT_EQ(dSkill.getElements()[0], Elements::ELECTRIC);
}

TEST(Dex, EngimonGetter) {
    Dex d("data/Test_Engimons.csv", "data/Test_Skills.csv");
    string engiName = "Picakhu";

    ASSERT_THROW(d.getEngi("ASLDKSADHSAJHDSJ"), DexException);
    ASSERT_NO_THROW(d.getEngi(engiName));
    EngimonSpecies dEngi = d.getEngi(engiName);
    EXPECT_EQ(dEngi.getSpecies(), engiName);
    ASSERT_EQ(dEngi.getElements().size(), 1);
    EXPECT_EQ(dEngi.getElements()[0], Elements::ELECTRIC);

    Skill s1(dEngi.getStarterSkill()), s2(d.getSkill("THUNDAAA"));
    EXPECT_EQ(s1.getName(), s2.getName());
    EXPECT_EQ(s1.getBasePower(), s2.getBasePower());
    EXPECT_EQ(s1.getMasteryLevel(), 1);
    EXPECT_EQ(s1.getElements()[0], Elements::ELECTRIC);
    EXPECT_EQ(s1.getElements().size(), s2.getElements().size());
}

TEST(Dex, AddNonExistantSkill) {
    Dex d("data/Test_Engimons.csv", "data/Test_Skills.csv");
    string skillName = "ih";

    d.addSkill(skillName,
               Skill(skillName, 100, 1,
                     vector<Elements::el>{Elements::FIRE, Elements::GROUND}));

    ASSERT_NO_THROW(d.getSkill(skillName));
    Skill dSkill = d.getSkill(skillName);
    EXPECT_EQ(dSkill.getName(), "ih");
    EXPECT_EQ(dSkill.getBasePower(), 100);
    EXPECT_EQ(dSkill.getMasteryLevel(), 1);
    EXPECT_EQ(dSkill.getElements().size(), 2);
    EXPECT_EQ(dSkill.getElements()[0], Elements::FIRE);
    EXPECT_EQ(dSkill.getElements()[1], Elements::GROUND);
}

TEST(Dex, AddExistingSkill) {
    Dex d("data/Test_Engimons.csv", "data/Test_Skills.csv");
    string skillName = "THUNDAAA";

    EXPECT_THROW(
        d.addSkill(skillName, Skill(skillName, 100, 1,
                                    vector<Elements::el>{Elements::FIRE,
                                                         Elements::GROUND})),
        DexException);
}

TEST(Dex, AddNonExistantEngimon) {
    Dex d("data/Test_Engimons.csv", "data/Test_Skills.csv");
    string engiName = "ih";
    string engiSlogan = "hadeh";

    d.addEngi(engiName, EngimonSpecies(engiName, engiSlogan,
                                       vector<Elements::el>{Elements::FIRE,
                                                            Elements::WATER},
                                       Skill()));

    ASSERT_NO_THROW(d.getEngi(engiName));
    EngimonSpecies dEngi = d.getEngi(engiName);
    EXPECT_EQ(dEngi.getSpecies(), engiName);
    ASSERT_EQ(dEngi.getElements().size(), 2);
    EXPECT_EQ(dEngi.getElements()[0], Elements::FIRE);
    EXPECT_EQ(dEngi.getElements()[1], Elements::WATER);

    Skill s1(dEngi.getStarterSkill()), s2(d.getSkill("THUNDAAA"));
    EXPECT_EQ(s1.getName(), s2.getName());
    EXPECT_EQ(s1.getBasePower(), s2.getBasePower());
    EXPECT_EQ(s1.getMasteryLevel(), 1);
    EXPECT_EQ(s1.getElements()[0], Elements::ELECTRIC);
}

TEST(Dex, AddExistingEngimon) {
    Dex d("data/Test_Engimons.csv", "data/Test_Skills.csv");
    string engiName = "Picakhu";

    EXPECT_THROW(
        d.addEngi(
            engiName,
            EngimonSpecies(engiName, "",
                           vector<Elements::el>{Elements::ELECTRIC}, Skill())),
        DexException);
}

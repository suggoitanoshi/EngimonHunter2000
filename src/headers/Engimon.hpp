/**
 * Engimon.hpp
 * y e e wangy wangy
 *
 * header berisi definisi atribut serta metode dari class Item
 */

#ifndef _ENGIMON_HPP_
#define _ENGIMON_HPP_
#include <string>
#include <tuple>
#include <vector>

#include "Elements.hpp"
#include "EngimonSpecies.hpp"
#include "Skill.hpp"
using namespace std;

class Engimon : public EngimonSpecies {
private:
    string name;
    tuple<string, string> parents[2];
    vector<Skill> skills;
    int lvl;
    unsigned exp;
    unsigned cexp;
    tuple<int, int> location;

public:
    const static unsigned defaultLevel = 1;
    // konstruktor & destruktor
    Engimon(EngimonSpecies ES = EngimonSpecies());  // untuk wild engimon (namanya=nama spesies)
    Engimon(EngimonSpecies ES,
            string oname);  // untuk engimon player dengan custom name
    Engimon(EngimonSpecies ES, string oname,
            tuple<string, string> oparents[2]);  // untuk hasil breeding
    Engimon(EngimonSpecies ES, string oname, tuple<string, string> oparents[2],
            vector<Skill> skills);  // untuk hasil breeding
    Engimon(const Engimon &);

    // getter
    string getName();
    int getLvl();
    unsigned getElementCount();
    tuple<int, int> getPosition();
    vector<Skill> getSkills();
    Skill& getSkillByIndex(int index);
    int getSkillsCount();

    // setter
    void setPos(int x, int y);
    void setSkills(int index, Skill oskill);
    void setLevel(int level);  // untuk breeding

    // methods
    void addExp(int exp);
    unsigned getBattlePower(int elmtAdv);
    void getEngiInfo();
    bool operator==(const Engimon &Eng) const;
    Engimon &operator=(const Engimon &Eng);
    friend ostream &operator<<(ostream &os, const Engimon &src);
    void showEngimon() const;
    void interact() const;
};

#endif

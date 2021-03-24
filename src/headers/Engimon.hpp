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
    vector<Skill> skill;
    int lvl;
    unsigned exp;
    unsigned cexp;
    tuple<int, int> location;

public:
    const static unsigned defaultLevel = 1;
    // konstruktor & destruktor
    Engimon();                   // Default constructor untuk first engimon
    Engimon(EngimonSpecies ES);  // untuk wild engimon (namanya=nama spesies)
    Engimon(EngimonSpecies ES,
            string oname);  // untuk engimon player dengan custom name
    Engimon(EngimonSpecies ES, string oname,
            tuple<string, string> oparents[2]);  // untuk hasil breeding
    Engimon(EngimonSpecies ES, string oname,
            tuple<string, string> oparents[2], vector<Skill> skills);  // untuk hasil breeding
    Engimon(const Engimon&);

    // getter
    string getName();
    int getLvl();
    vector<Elements> getElements();
    unsigned getElementCount();
    tuple<int, int> getPosition();
    Skill getSkills(int index);
    int getSkillsCount();

    // setter
    void setPos(int x, int y);
    void setSkills(int index, Skill oskill);
    void setLevel(int level);  // untuk breeding

    // methods
    void addExp(int exp);
    unsigned getBattlePower(int elmtAdv);
    void getEngiInfo();
    bool operator==(const Engimon &Eng);
    bool operator==(const string &oname);
    Engimon &operator=(const Engimon &Eng);
    friend ostream& operator<<(ostream& os, const Engimon& src);
};

#endif

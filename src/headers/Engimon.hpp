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

class Engimon : EngimonSpecies {
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
    //Engimon();                   // DON'T EVER USE THIS.
    Engimon(EngimonSpecies ES);  // untuk wild engimon (namanya=nama spesies)
    Engimon(string ESName);  // untuk wild engimon (namanya=nama spesies)
    Engimon(EngimonSpecies ES,
            string oname);  // untuk engimon player dengan custom name
    Engimon(EngimonSpecies ES, string oname,
            tuple<string, string> oparents[2]);  // untuk hasil breeding

    // getter
    string getName();
    int getLvl();
    string getSpecies();
    vector<Elements> getElements();
    unsigned getElementCount();
    tuple<int, int> getPosition();
    string getSkills(int index);
    int getSkillsCount();

    // setter
    void setPos();
    void setSkills(int index, Skill oskill);
    void setLevel();  // untuk breeding

    // methods
    void addExp(int exp);
    unsigned getBattlePower();
    void getEngiInfo();
    bool operator==(const Engimon &);
};

#endif

/**
 * Engimon.hpp
 * y e e wangy wangy
 *
 * header berisi definisi atribut serta metode dari class Item
 */

#ifndef _ENGIMON_HPP_
#define _ENGIMON_HPP_
#include <hash_map>
#include <string>
#include <tuple>
#include <vector>

#include "Elements.hpp"
#include "Skill.hpp"
using namespace std;

// Abstract Base Class
class EngimonSpecies {
protected:
    // Nama spesies
    string species;
    string slogan;
    Skill starterSkill;
    // Elemen yang dimiliki
    vector<Elements> element;

public:
    const static unsigned maxCumulExp = 10000;
    const static unsigned maxSkills = 4;
    // con, ccon, dest
    EngimonSpecies(string ospec, string oslogan, vector<Elements> oelem, Skill oskill);
    EngimonSpecies(const EngimonSpecies &ES);
    virtual ~EngimonSpecies();
};

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
    Engimon(EngimonSpecies ES);  // untuk wild engimon (namanya=nama spesies)
    Engimon(EngimonSpecies ES,
            string oname);  // untuk engimon player dengan custom name
    Engimon(EngimonSpecies ES, string oname,
            tuple<string, string> oparents[2]);  // untuk hasil breeding
    ~Engimon();


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
    void setLevel(); // untuk breeding

    // methods
    void addExp(int exp);
    unsigned getBattlePower();
    void getEngiInfo();
    bool operator==(Engimon);
};

#endif

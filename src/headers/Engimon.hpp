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
#include "Skill.hpp"
using namespace std;

// Abstract Base Class
class EngimonSpecies {
protected:
    // Nama spesies
    string species;
    // Elemen yang dimiliki
    vector<Elements> element;

public:
    const static unsigned MAX_CEXP = 10000;
    const static unsigned MAX_SKILLS = 4;
    // konstruktor & destruktor
    EngimonSpecies(string ospec, vector<Elements> oelem);
    // ~EngimonSpecies();
    
};

class Engimon : EngimonSpecies {
private:
    string name;
    tuple<string, string> parents[2];
    vector<Skill> skill;
    int lvl;
    unsigned exp;
    unsigned cexp;
    tuple<int,int> location;
    

public:
    // konstruktor & destruktor
    Engimon();
    Engimon(string name);
    Engimon(string name, tuple<string, string> parents[2]);
    ~Engimon();

    // getter
    string getName();
    int getLvl();
    //int getExp();
    string getSpecies();
    vector<Elements> getElements();
    tuple<int,int> getPosition();
    string getSkills(int index);

    // setter
    void setPos();
    void setSkills(int index, Skills oskill);

    // methods
    void addExp(int exp);
    unsigned getBattlePower();


};

#endif
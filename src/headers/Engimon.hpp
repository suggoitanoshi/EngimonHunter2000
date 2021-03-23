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
    // Jumlah elemen spesies (yang terisi)
    int _element;
    // Elemen yang dimiliki
    vector<Elements> element;

public:
    const static unsigned MAX_CEXP = 10000;
    // konstruktor & destruktor
    EngimonSpecies(string ospec, int o_elem, vector<Elements> oelem);
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

public:
    // konstruktor & destruktor
    Engimon();
    Engimon(string name);
    Engimon(string name, string parentName[]);
    ~Engimon();

    // getter
    string getName();
    int getLvl();
    //int getExp();
    string getSpecies();

    // methods
    void addExp(int exp);
};

#endif
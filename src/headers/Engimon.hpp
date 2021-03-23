/**
 * Engimon.hpp
 * y e e wangy wangy
 *
 * header berisi definisi atribut serta metode dari class Item
 */

#ifndef _ENGIMON_HPP_
#define _ENGIMON_HPP_
#include <string>
#include "Elements.hpp"
using namespace std;
#define MAX_ELMNT 2
#define MAX_CEXP 10000

class EngimonSpecies {
protected:
    // Nama spesies
    string species;
    // Jumlah elemen spesies (yang terisi)
    int _elem;
    // Elemen yang dimiliki
    Elements* element[MAX_ELMNT];
public:
    // konstruktor
    EngimonSpecies();

};

class Engimon : public EngimonSpecies {
private:
    string name;
    string parentName[2];
    int lvl;
    int exp;
    int cexp;
public:
    // konstruktor & destruktor
    Engimon();
    Engimon(string name);
    Engimon(string name, string parentName[]);
    ~Engimon();

    // getter
    string getName();
    int getLvl();
    int getExp();

    // methods
    void addExp(int exp);
};

#endif
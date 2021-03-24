/**
 * Engimon.cpp
 * y e e wangy wangy
 *
 * Implementasi class Engimon dan SpeciesEngimon
 */

#include "../headers/Engimon.hpp"
#include "../headers/Dex.hpp"

EngimonSpecies::EngimonSpecies(string ospec, string oslogan, vector<Elements> oelem, Skill oskill) {
    this->species = ospec;
    this->slogan = oslogan;
    this->starterSkill = oskill;
    element = oelem;
}

Engimon::Engimon(EngimonSpecies ES) : EngimonSpecies(ES) {
    this->name = species;
    parents[0] = make_tuple("Wild",species);
    parents[1] = make_tuple("Wild",species);
    skill.push_back(starterSkill);
    this->lvl = defaultLevel;
    this->exp = 0;
    this->cexp = 0;
    location = make_tuple(-1,-1);
}
Engimon::Engimon(EngimonSpecies ES, string oname) : EngimonSpecies(ES) {
    tuple<string,string> oparents[2];
    oparents[0] = make_tuple("Wild",species);
    oparents[1] = make_tuple("Wild",species);
    Engimon(ES, oname, oparents);
}
Engimon::Engimon(EngimonSpecies ES, string oname, tuple<string, string> oparents[2]) : EngimonSpecies(ES) {
    this->name = name;
    parents[0] = oparents[0];
    parents[1] = oparents[1];
    skill.push_back(starterSkill);
    this->lvl = defaultLevel;
    this->exp = 0;
    this->cexp = 0;
    location = make_tuple(-1,-1);
}
Engimon::~Engimon() {
    delete parents;
}
#include "../headers/EngimonSpecies.hpp"

EngimonSpecies::EngimonSpecies() {
    species = "Picakhu";
    slogan = "Pica Pica Khu!";
    element.push_back(Elements::ELECTRIC);
}

EngimonSpecies::EngimonSpecies(string ospec, string oslogan,
                               vector<Elements::el> oelem, Skill oskill)
    : starterSkill(oskill) {
    species = ospec;
    slogan = oslogan;
    element = oelem;
}

EngimonSpecies::EngimonSpecies(const EngimonSpecies &ES)
    : starterSkill(ES.starterSkill) {
    species = ES.species;
    slogan = ES.slogan;
    element = ES.element;
}

EngimonSpecies &EngimonSpecies::operator=(const EngimonSpecies &ES) {
    species = ES.species;
    slogan = ES.slogan;
    starterSkill = ES.starterSkill;
    element = ES.element;

    return *this;
}

vector<Elements::el> EngimonSpecies::getElements() const { return element; }

Skill EngimonSpecies::getStarterSkill() const { return starterSkill; }
string EngimonSpecies::getSpecies() const { return species; }

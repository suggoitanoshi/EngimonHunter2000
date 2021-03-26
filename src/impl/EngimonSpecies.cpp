#include "../headers/EngimonSpecies.hpp"

EngimonSpecies::EngimonSpecies(string ospec, string oslogan,
                               vector<Elements::el> oelem, Skill oskill)
    : starterSkill(oskill) {
    species = ospec;
    slogan = oslogan;
    element = oelem;
}

EngimonSpecies::EngimonSpecies(const EngimonSpecies &ES)
    : EngimonSpecies(ES.species, ES.slogan, ES.element, ES.starterSkill) {}

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

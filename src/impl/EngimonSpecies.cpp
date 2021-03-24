#include "../headers/EngimonSpecies.hpp"

EngimonSpecies::EngimonSpecies(string ospec, string oslogan,
                               vector<Elements> oelem, Skill oskill)
    : starterSkill(oskill) {
    this->species = ospec;
    this->slogan = oslogan;
    element = oelem;
}

EngimonSpecies::EngimonSpecies(const EngimonSpecies &ES)
    : starterSkill(ES.starterSkill) {
    this->species = ES.species;
    this->slogan = ES.slogan;
    element = ES.element;
}

EngimonSpecies &EngimonSpecies::operator=(const EngimonSpecies &ES) {
    this->species = ES.species;
    this->slogan = ES.slogan;
    this->starterSkill = ES.starterSkill;
    this->element = ES.element;

    return *this;
}

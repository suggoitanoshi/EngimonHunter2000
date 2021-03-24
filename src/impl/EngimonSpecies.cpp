#include "../headers/EngimonSpecies.hpp"

EngimonSpecies::EngimonSpecies() {
    this->species = "";
    this->slogan = "";
    this->starterSkill = Skill();
    element = vector<Elements>();
}

EngimonSpecies::EngimonSpecies(string ospec, string oslogan, vector<Elements> oelem, Skill oskill) {
    this->species = ospec;
    this->slogan = oslogan;
    this->starterSkill = oskill;
    element = oelem;
}

EngimonSpecies::EngimonSpecies(const EngimonSpecies &ES) {
    this->species = ES.species;
    this->slogan = ES.slogan;
    this->starterSkill = ES.starterSkill;
    element = ES.element;
}

EngimonSpecies& EngimonSpecies::operator=(const EngimonSpecies &ES) {
    this->species = ES.species;
    this->slogan = ES.slogan;
    this->starterSkill = ES.starterSkill;
    this->element = ES.element;

    return *this;
}

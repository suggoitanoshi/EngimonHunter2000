#ifndef _ENGIMON_SPECIES_HPP_
#define _ENGIMON_SPECIES_HPP_

#include <string>
#include <tuple>
#include <unordered_map>
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
    vector<Elements::el> element;

public:
    const static unsigned maxCumulExp = 10000;
    const static unsigned maxSkills = 4;
    // con, ccon, dest
    EngimonSpecies(
        string ospec = "Picakhu", string oslogan = "Pica Pica Khu!",
        vector<Elements::el> oelem = vector<Elements::el>{Elements::ELECTRIC},
        Skill oskill = Skill());
    EngimonSpecies(const EngimonSpecies &ES);
    EngimonSpecies &operator=(const EngimonSpecies &ES);

    // getter
    Skill getStarterSkill() const;
    vector<Elements::el> getElements() const;
    string getSpecies() const;
};

#endif

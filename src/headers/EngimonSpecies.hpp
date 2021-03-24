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
    vector<Elements> element;

public:
    const static unsigned maxCumulExp = 10000;
    const static unsigned maxSkills = 4;
    // con, ccon, dest
    EngimonSpecies();  // DON'T EVER USE THIS.
    EngimonSpecies(string ospec, string oslogan, vector<Elements> oelem,
                   Skill oskill);
    EngimonSpecies(const EngimonSpecies &ES);
    EngimonSpecies& operator=(const EngimonSpecies&);
};

#endif

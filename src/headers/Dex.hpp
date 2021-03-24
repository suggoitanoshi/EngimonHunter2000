#ifndef _DEX_HPP_
#define _DEX_HPP_

#include <unordered_map>

#include "Engimon.hpp"
#include "Skill.hpp"

using namespace std;

class Dex {
private:
    unordered_map<string, Skill> skillDex;
    unordered_map<string, EngimonSpecies> engiDex;

public:
    // konstruktor
    Dex();

    // getter
    Skill getSkill(string skillname);
    EngimonSpecies getEngi(string enginame);

    // setter
    void addSkill(string skillname, Skill skill);
    void addEngi(string enginame, EngimonSpecies engimon);
};

#endif


#ifndef _ENGIDEX_HPP_
#define _ENGIDEX_HPP_

#include "Engimon.hpp"
#include "Skill.hpp"
#include <unordered_map>

using namespace std;

class Dex {
private:
    unordered_map<string,Skill> skillDex;
    unordered_map<string,EngimonSpecies> engiDex;

public:
    // konstruktor
    Dex();

    // getter
    Skill getSkillDex(string skillname);
    EngimonSpecies getEngiDex(string enginame);

    // setter
    void addSkill(Skill skill);
    void addEngi(EngimonSpecies engimon);

};


#endif
#include "../headers/Dex.hpp"

Dex::Dex() {
}

Skill Dex::getSkill(string skillname) {
    return skillDex.at(skillname);
}

EngimonSpecies Dex::getEngi(string enginame) {
    return engiDex.at(enginame);
}

void Dex::addSkill(string skillname, Skill skill) {
    skillDex.emplace(skillname, skill);
}

void Dex::addEngi(string enginame, EngimonSpecies engimon) {
    engiDex.emplace(enginame, engimon);
}

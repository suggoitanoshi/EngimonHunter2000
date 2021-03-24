/**
 * Engimon.cpp
 * y e e wangy wangy
 *
 * Implementasi class Engimon dan SpeciesEngimon
 */

#include "../headers/Engimon.hpp"

#include <iostream>

#include "../headers/Dex.hpp"

Engimon::Engimon()
    : Engimon(EngimonSpecies(
          "Picakhu", "Pica Pica Khu!", vector<Elements>{ELECTRIC},
          Skill("THUNDAAA", 40, 1, vector<Elements>{ELECTRIC}))) {}

Engimon::Engimon(EngimonSpecies ES) : EngimonSpecies(ES) {
    this->name = species;
    parents[0] = make_tuple("Wild", species);
    parents[1] = make_tuple("Wild", species);
    skill.push_back(starterSkill);
    this->lvl = defaultLevel;
    this->exp = 0;
    this->cexp = 0;
    location = make_tuple(-1, -1);
}

Engimon::Engimon(EngimonSpecies ES, string oname) : EngimonSpecies(ES) {
    tuple<string, string> oparents[2];
    oparents[0] = make_tuple("Wild", species);
    oparents[1] = make_tuple("Wild", species);
    Engimon(ES, oname, oparents);
}

Engimon::Engimon(EngimonSpecies ES, string oname,
                 tuple<string, string> oparents[2])
    : EngimonSpecies(ES) {
    name = oname;
    parents[0] = oparents[0];
    parents[1] = oparents[1];
    skill.push_back(starterSkill);
    lvl = defaultLevel;
    exp = 0;
    cexp = 0;
    location = make_tuple(-1, -1);
}

Engimon::Engimon(const Engimon& src) : EngimonSpecies(src) {
    name = src.name;
    parents[0] = src.parents[0];
    parents[1] = src.parents[1];
    skill = src.skill;
    lvl = src.lvl;
    exp = src.exp;
    cexp = src.cexp;
    location = src.location;
}

string Engimon::getName() { return this->name; }
int Engimon::getLvl() { return this->lvl; }
string Engimon::getSpecies() { return this->species; }
vector<Elements> Engimon::getElements() { return element; }
unsigned Engimon::getElementCount() { return element.size(); }
tuple<int, int> Engimon::getPosition() { return location; }
string Engimon::getSkills(int index) { return skill[index].getName(); }
int Engimon::getSkillsCount() { return skill.size(); }
void Engimon::setSkills(int index, Skill oskill) { skill[index] = oskill; }

void Engimon::setPos(int x, int y) {
    get<0>(this->location) = x;
    get<0>(this->location) = y;
}
void Engimon::setLevel(int level) {
    if (this->lvl - level < 0) {
        throw "Level tidak cukup";
    } else {
        this->lvl -= level;
    }
}  // untuk breeding

// methods
void Engimon::addExp(int oexp) {
    this->exp += oexp;
    this->cexp += oexp;
    if (exp >= 100) {
        this->exp -= 100;
        this->lvl++;
    }
    if (cexp >= maxCumulExp) {
        throw "Engimon dihapus dari program";
    }
}
unsigned Engimon::getBattlePower(int elmtAdv) {
    unsigned sum = 0;
    for (int i = 0; i < getSkillsCount(); i++) {
        sum += skill[i].getBasePower() * skill[i].getMasteryLevel();
    }
    return (this->lvl * elmtAdv);
}
void Engimon::getEngiInfo() {
    cout << this->name << endl;
    cout << get<0>(parents[0]) << get<1>(parents[0]) << endl;
    cout << get<0>(parents[1]) << get<1>(parents[1]) << endl;
    for (int i = 0; i < getSkillsCount(); i++) {
        cout << skill[i].getName() << endl;
        cout << skill[i].getBasePower() << endl;
        // cout << skill[i].getElements() << endl;
        cout << skill[i].getMasteryLevel() << endl;
    }
}

bool Engimon::operator==(const Engimon &Eng) {
    return (this->name == Eng.name && this->species == Eng.species);
}

bool Engimon::operator==(const string &oname) { return (this->name == oname); }

Engimon &Engimon::operator=(const Engimon &Eng) {
    if (this != &Eng) {
        EngimonSpecies::operator=(Eng);
        this->name = Eng.name;
        parents[0] = Eng.parents[0];
        parents[1] = Eng.parents[1];
        skill = Eng.skill;
        this->lvl = Eng.lvl;
        this->exp = Eng.exp;
        this->cexp = Eng.cexp;
        location = Eng.location;
    }
    return *this;
}

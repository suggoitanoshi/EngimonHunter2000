/**
 * Engimon.cpp
 * y e e wangy wangy
 *
 * Implementasi class Engimon dan SpeciesEngimon
 */

#include "../headers/Engimon.hpp"

#include <iostream>

#include "../headers/Dex.hpp"

Engimon::Engimon() : EngimonSpecies() {
    name = species;
    parents[0] = make_tuple("Wild", species);
    parents[1] = make_tuple("Wild", species);
    skill.push_back(starterSkill);
    lvl = defaultLevel;
    exp = 0;
    cexp = 0;
    location = make_tuple(-1, -1);
}

Engimon::Engimon(EngimonSpecies ES) : EngimonSpecies(ES) {
    name = species;
    parents[0] = make_tuple("Wild", species);
    parents[1] = make_tuple("Wild", species);
    skill.push_back(starterSkill);
    lvl = defaultLevel;
    exp = 0;
    cexp = 0;
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
        Engimon(ES, oname, oparents, vector<Skill>{ES.getStarterSkill()});
}

Engimon::Engimon(EngimonSpecies ES, string oname,
                 tuple<string, string> oparents[2], vector<Skill> skills)
    : EngimonSpecies(ES) {
    name = oname;
    parents[0] = oparents[0];
    parents[1] = oparents[1];
    skill.insert(skill.end(), skills.begin(), skills.end());
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

string Engimon::getName() { return name; }
int Engimon::getLvl() { return lvl; }
vector<Elements> Engimon::getElements() { return element; }
unsigned Engimon::getElementCount() { return element.size(); }
tuple<int, int> Engimon::getPosition() { return location; }
Skill Engimon::getSkills(int index) { return skill[index]; }
int Engimon::getSkillsCount() { return skill.size(); }
void Engimon::setSkills(int index, Skill oskill) { skill[index] = oskill; }

void Engimon::setPos(int x, int y) {
    get<0>(location) = x;
    get<0>(location) = y;
}
void Engimon::setLevel(int level) {
    if (lvl - level < 0) {
        throw "Level tidak cukup";
    } else {
        lvl -= level;
    }
}  // untuk breeding

// methods
void Engimon::addExp(int oexp) {
    exp += oexp;
    cexp += oexp;
    if (exp >= 100) {
        exp -= 100;
        lvl++;
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
    return (lvl * elmtAdv);
}
void Engimon::getEngiInfo() {
    cout << name << endl;
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
    return (name == Eng.name && species == Eng.species);
}

bool Engimon::operator==(const string &oname) { return (name == oname); }

Engimon &Engimon::operator=(const Engimon &Eng) {
    if (this != &Eng) {
        EngimonSpecies::operator=(Eng);
        name = Eng.name;
        parents[0] = Eng.parents[0];
        parents[1] = Eng.parents[1];
        skill = Eng.skill;
        lvl = Eng.lvl;
        exp = Eng.exp;
        cexp = Eng.cexp;
        location = Eng.location;
    }
    return *this;
}

ostream& operator<<(ostream& os, const Engimon& src) {
    vector<Elements> els = src.element;
    os << "Name\t:" << src.name << "\n";
    os << "Experience\t: " << src.exp << "\n";
    os << "Cumulative Experience/Maximum\t: " << src.cexp << src.maxCumulExp << "\n";
    os << "Element(s)\t: ";
    for (size_t i = 0; i < els.size(); i++) {
        os << els[i] << endl;
        if (i != els.size() - 1) {
            os << ", ";
        }
    }
    os << "\n";
    return os;
}

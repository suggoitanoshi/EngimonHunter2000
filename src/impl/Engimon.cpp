/**
 * Engimon.cpp
 * y e e wangy wangy
 *
 * Implementasi class Engimon dan SpeciesEngimon
 */

#include "../headers/Engimon.hpp"

#include <iostream>

#include "../headers/Dex.hpp"

Engimon::Engimon(EngimonSpecies ES) : EngimonSpecies(ES) {
    name = species;
    parents[0] = make_tuple("Wild", species);
    parents[1] = make_tuple("Wild", species);
    skills.push_back(starterSkill);
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
                 tuple<string, string> oparents[2], vector<Skill> oskills)
    : EngimonSpecies(ES) {
    name = oname;
    parents[0] = oparents[0];
    parents[1] = oparents[1];
    skills.insert(skills.end(), oskills.begin(), oskills.end());
    lvl = defaultLevel;
    exp = 0;
    cexp = 0;
    location = make_tuple(-1, -1);
}

Engimon::Engimon(const Engimon& src) : EngimonSpecies(src) {
    name = src.name;
    parents[0] = src.parents[0];
    parents[1] = src.parents[1];
    skills = src.skills;
    lvl = src.lvl;
    exp = src.exp;
    cexp = src.cexp;
    location = src.location;
}

string Engimon::getName() { return name; }
int Engimon::getLvl() { return lvl; }
unsigned Engimon::getElementCount() { return element.size(); }
tuple<int, int> Engimon::getPosition() { return location; }
vector<Skill> Engimon::getSkills() { return skills; }
Skill Engimon::getSkillByIndex(int index) { return skills[index]; }
int Engimon::getSkillsCount() { return skills.size(); }

void Engimon::setSkills(int index, Skill oskill) {
    skills.insert(skills.begin() + index, oskill);
}
void Engimon::setPos(int x, int y) {
    get<0>(location) = x;
    get<1>(location) = y;
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
    while (exp >= 100) {
        exp -= 100;
        lvl++;
    }
    if (cexp >= maxCumulExp) {
        delete this;
        //throw "Engimon dihapus dari program";
    }
}
unsigned Engimon::getBattlePower(int elmtAdv) {
    unsigned sum = 0;
    for (size_t i = 0; i < getSkills().size(); i++) {
        sum += skills[i].getBasePower() * skills[i].getMasteryLevel();
    }
    return (lvl * elmtAdv);
}
void Engimon::getEngiInfo() {
    cout << name << endl;
    cout << get<0>(parents[0]) << get<1>(parents[0]) << endl;
    cout << get<0>(parents[1]) << get<1>(parents[1]) << endl;
    for (size_t i = 0; i < getSkills().size(); i++) {
        cout << skills[i].getName() << endl;
        cout << skills[i].getBasePower() << endl;
        cout << skills[i].getMasteryLevel() << endl;
    }
}

bool Engimon::operator==(const Engimon& Eng) const {
    return (name == Eng.name && species == Eng.species);
}

Engimon& Engimon::operator=(const Engimon& Eng) {
    if (this != &Eng) {
        EngimonSpecies::operator=(Eng);
        name = Eng.name;
        parents[0] = Eng.parents[0];
        parents[1] = Eng.parents[1];
        skills = Eng.skills;
        lvl = Eng.lvl;
        exp = Eng.exp;
        cexp = Eng.cexp;
        location = Eng.location;
    }
    return *this;
}

ostream& operator<<(ostream& os, const Engimon& src) {
    os << "Name\t: " << src.name << "\n";
    return os;
}

void Engimon::showEngimon() const {
    vector<Elements::el> els = getElements();
    cout << "Name\t\t\t\t: " << name << "\n";
    cout << "Level\t\t\t\t: " << lvl << "\n";
    cout << "Experience\t\t\t: " << exp << "\n";
    cout << "Cumulative Experience/Maximum\t: " << cexp << "/" << maxCumulExp
         << "\n";
    cout << "Element(s)\t\t\t: ";
    for (size_t i = 0; i < els.size(); i++) {
        cout << Elements::getName(els[i]);
        if (i != els.size() - 1) {
            cout << ", ";
        }
    }
    cout << endl;
}

void Engimon::interact() const {
    cout << name << ": ";
    cout << slogan << "\n";
}

/**
 * Skill.cpp
 * y e e wangy wangy
 *
 * Implementasi class Skill
 */

#include "../headers/Skill.hpp"

#include <iostream>
#include <vector>

// Bagian Constructor
Skill::Skill() {
    name = "THUNDAAA";
    basePower = 40;
    masteryLevel = 1;
    elements.push_back(Elements::ELECTRIC);
}

Skill::Skill(const string _name, const unsigned _basePower,
             const unsigned _masteryLevel,
             const vector<Elements::el>& _elements)
    : name(_name), basePower(_basePower), masteryLevel(_masteryLevel) {
    if (_elements.size() == 0) {
        throw SkillException(0);
    } else {
        elements = _elements;
    }
}

Skill::Skill(const string _name, const unsigned _basePower,
             const unsigned _masteryLevel, const Elements::el _elements)
    : name(_name), basePower(_basePower), masteryLevel(_masteryLevel) {
    elements.push_back(_elements);
}

Skill::Skill(const Skill& src)
    : name(src.name),
      basePower(src.basePower),
      masteryLevel(src.masteryLevel),
      elements(src.elements) {}

Skill& Skill::operator=(const Skill& src) {
    name = src.name;
    basePower = src.basePower;
    masteryLevel = src.masteryLevel;
    elements = src.elements;

    return *this;
}

bool Skill::operator==(const Skill& sblh) {
    return name == sblh.name && basePower == sblh.basePower &&
           masteryLevel == sblh.masteryLevel && elements == sblh.elements;
}

string Skill::getName() const { return name; }
unsigned Skill::getBasePower() const { return basePower; }
unsigned Skill::getMasteryLevel() const { return masteryLevel; }
vector<Elements::el> Skill::getElements() const { return elements; }

void Skill::setMasteryLevel(unsigned _masteryLevel) {
    masteryLevel = _masteryLevel;
}

// bagian SkillException
SkillException::SkillException(int x) : msgID(x) {}
const char* SkillException::what() { return msg[msgID].c_str(); }
void SkillException::bruh() { cout << what() << endl; }
string SkillException::msg[] = {"Element tidak valid"};

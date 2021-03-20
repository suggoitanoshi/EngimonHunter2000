/**
 * Elements.cpp
 * y e e wangy wangy
 *
 * Implementasi class Skill
 */

#include "../headers/Skill.hpp"

Skill::Skill(const std::string _name, const unsigned _basePower,
             const unsigned _masteryLevel,
             const std::vector<Elements>& _elements)
    : name(_name),
      basePower(_basePower),
      masteryLevel(_masteryLevel),
      elements(_elements) {}

Skill::Skill(const std::string _name, const unsigned _basePower,
             const unsigned _masteryLevel, const Elements _elements)
    : name(_name), basePower(_basePower), masteryLevel(_masteryLevel) {
    elements.push_back(_elements);
}

Skill::Skill(const Skill& src)
    : name(src.name),
      basePower(src.basePower),
      masteryLevel(src.masteryLevel),
      elements(src.elements) {}

std::string Skill::getName() const { return name; }
unsigned Skill::getBasePower() const { return basePower; }
unsigned Skill::getMasteryLevel() const { return masteryLevel; }
std::vector<Elements> Skill::getElements() const { return elements; }

void Skill::setMasteryLevel(unsigned _masteryLevel) {
    masteryLevel = _masteryLevel;
}

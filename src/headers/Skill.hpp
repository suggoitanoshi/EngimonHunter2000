/**
 * Skill.hpp
 * y e e wangy wangy
 *
 * header berisi definisi atribut serta metode dari class Skill
 */

#ifndef _SKILL_HPP_
#define _SKILL_HPP_

#include <exception>
#include <string>
#include <vector>

using namespace std;

#include "Elements.hpp"

/**
 * Class untuk menyimpan skill yang (dapat) dimiliki Engimon
 */
class Skill {
protected:
    string name;
    unsigned basePower;
    unsigned masteryLevel;
    vector<Elements::el> elements;  // elemen yang dapat mempelajari skill ini

public:
    // constructors
    Skill(const std::string = "THUNDAAA", const unsigned _basePower = 40,
          const unsigned _masteryLevel = 1,
          const vector<Elements::el>& = vector<Elements::el>{
              Elements::ELECTRIC}); // default constructor
        // default values are:
        // name = THUNDAAA
        // basePower = 40
        // masteryLevel = 1
        // elements = { ELECTRIC }
    Skill(const std::string, const unsigned _basePower,
          const unsigned _masteryLevel, const Elements::el);
    Skill(const Skill&);
    Skill& operator=(const Skill&);

    // operators
    bool operator==(const Skill&) const;

    // getters
    string getName() const;
    unsigned getBasePower() const;
    unsigned getMasteryLevel() const;
    vector<Elements::el> getElements() const;

    // setters
    void setMasteryLevel(unsigned);
};

class SkillException : exception {
private:
    const int msgID;
    static string msg[];

public:
    SkillException(int);
    const char* what();
    void bruh();
};

#endif

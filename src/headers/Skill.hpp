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
    vector<Elements> elements;  // elemen yang dapat mempelajari skill ini

public:
    // constructors
    Skill();
    Skill(const std::string, const unsigned _basePower,
          const unsigned _masteryLevel, const vector<Elements>&);
    Skill(const std::string, const unsigned _basePower,
          const unsigned _masteryLevel, const Elements);
    Skill(const Skill&);
    virtual Skill& operator=(const Skill&);

    // operators
    virtual bool operator==(const Skill&);

    // getters
    string getName() const;
    unsigned getBasePower() const;
    unsigned getMasteryLevel() const;
    vector<Elements> getElements() const;

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
